package com.resumescreener.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class NLPService {

    // Common tech skill keywords to detect in resumes
    private static final List<String> SKILL_POOL = Arrays.asList(
        "java", "spring", "spring boot", "spring mvc", "hibernate",
        "python", "sql", "mysql", "postgresql", "mongodb", "oracle",
        "react", "angular", "vue", "html", "css", "javascript",
        "docker", "kubernetes", "aws", "azure", "gcp",
        "machine learning", "deep learning", "nlp", "tensorflow",
        "rest api", "microservices", "git", "maven", "gradle",
        "junit", "kafka", "redis", "elasticsearch", "jenkins",
        "linux", "agile", "scrum", "c++", "c#", ".net", "node.js"
    );

    /**
     * Match skills found in resume text against known skill pool
     */
    public List<String> extractSkills(String text) {
        String lower = text.toLowerCase();
        return SKILL_POOL.stream()
                .filter(lower::contains)
                .collect(Collectors.toList());
    }

    /**
     * Extract candidate name from top portion of resume
     */
    public String extractName(String text) {
        // Try to get first capitalized full name from top 300 chars
        String top = text.trim().substring(0, Math.min(300, text.length()));
        Matcher m = Pattern.compile("([A-Z][a-z]+ [A-Z][a-z]+)").matcher(top);
        return m.find() ? m.group() : "Candidate";
    }

    /**
     * Extract education level from resume text
     */
    public String extractEducation(String text) {
        String lower = text.toLowerCase();
        if (lower.contains("ph.d") || lower.contains("phd"))        return "PhD";
        if (lower.contains("m.tech") || lower.contains("m.e"))      return "M.Tech";
        if (lower.contains("mba"))                                   return "MBA";
        if (lower.contains("m.sc") || lower.contains("msc"))        return "M.Sc";
        if (lower.contains("b.tech") || lower.contains("b.e"))      return "B.Tech";
        if (lower.contains("bsc") || lower.contains("b.sc"))        return "B.Sc";
        if (lower.contains("bachelor"))                              return "Bachelor's";
        if (lower.contains("master"))                                return "Master's";
        if (lower.contains("diploma"))                               return "Diploma";
        return "Not specified";
    }
}
