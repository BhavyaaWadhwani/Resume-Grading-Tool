package com.resumescreener.controller;

import com.resumescreener.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private ResumeRepository repository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/applicant")
    public String applicant() {
        return "applicant";
    }

    @GetMapping("/hr")
    public String hr() {
        return "hr";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("resumes", repository.findAllByOrderByMatchScoreDesc());
        return "dashboard";
    }
}
