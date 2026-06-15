package com.resumescreener.controller;

import com.resumescreener.model.ResumeData;
import com.resumescreener.repository.ResumeRepository;
import com.resumescreener.service.NLPService;
import com.resumescreener.service.ResumeParserService;
import com.resumescreener.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired private ResumeParserService parserService;
    @Autowired private NLPService nlpService;
    @Autowired private ScoringService scoringService;
    @Autowired private ResumeRepository repository;

    /**
     * Applicant submits resume — no scoring, just extraction
     */
    @PostMapping("/submit")
    public String submitResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobTitle") String jobTitle,
            Model model) {
        try {
            String text = parserService.extractText(file);
            List<String> skills = nlpService.extractSkills(text);

            ResumeData data = new ResumeData();
            data.setCandidateName(nlpService.extractName(text));
            data.setEmail(parserService.extractEmail(text));
            data.setPhone(parserService.extractPhone(text));
            data.setSkills(String.join(", ", skills));
            data.setExperienceYears(parserService.extractExperience(text));
            data.setJobTitle(jobTitle);
            data.setMatchScore(0);
            data.setUploadedAt(LocalDateTime.now());
            repository.save(data);

            model.addAttribute("resume", data);
            model.addAttribute("skillList", skills);
            model.addAttribute("education", nlpService.extractEducation(text));
            model.addAttribute("isApplicant", true);
            model.addAttribute("message", "✅ Resume submitted successfully! HR will review your application.");
            return "result";

        } catch (Exception e) {
            model.addAttribute("error", "Error processing resume: " + e.getMessage());
            return "applicant";
        }
    }

    /**
     * HR screens resume against job requirements — includes scoring
     */
    @PostMapping("/screen")
    public String screenResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("requiredSkills") String requiredSkillsStr,
            @RequestParam("requiredExperience") int requiredExp,
            @RequestParam("jobTitle") String jobTitle,
            Model model) {
        try {
            String text = parserService.extractText(file);
            List<String> resumeSkills = nlpService.extractSkills(text);
            List<String> requiredSkills = Arrays.stream(requiredSkillsStr.split(","))
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            double score = scoringService.calculateScore(
                    resumeSkills, requiredSkills,
                    parserService.extractExperience(text), requiredExp
            );

            ResumeData data = new ResumeData();
            data.setCandidateName(nlpService.extractName(text));
            data.setEmail(parserService.extractEmail(text));
            data.setPhone(parserService.extractPhone(text));
            data.setSkills(String.join(", ", resumeSkills));
            data.setExperienceYears(parserService.extractExperience(text));
            data.setJobTitle(jobTitle);
            data.setMatchScore(score);
            data.setUploadedAt(LocalDateTime.now());
            repository.save(data);

            // Find which required skills are missing
            List<String> missingSkills = requiredSkills.stream()
                    .filter(r -> resumeSkills.stream()
                            .noneMatch(s -> s.equalsIgnoreCase(r)))
                    .collect(Collectors.toList());

            model.addAttribute("resume", data);
            model.addAttribute("skillList", resumeSkills);
            model.addAttribute("requiredSkills", requiredSkills);
            model.addAttribute("missingSkills", missingSkills);
            model.addAttribute("education", nlpService.extractEducation(text));
            model.addAttribute("scoreLabel", scoringService.getScoreLabel(score));
            model.addAttribute("scoreClass", scoringService.getScoreClass(score));
            model.addAttribute("isApplicant", false);
            return "result";

        } catch (Exception e) {
            model.addAttribute("error", "Error processing resume: " + e.getMessage());
            return "hr";
        }
    }
}
