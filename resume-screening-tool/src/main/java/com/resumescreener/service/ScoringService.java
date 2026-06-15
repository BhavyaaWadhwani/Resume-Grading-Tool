package com.resumescreener.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoringService {

    /**
     * Calculate resume match score out of 100
     * Skill match  = 70% weight
     * Experience   = 30% weight
     */
    public double calculateScore(List<String> resumeSkills,
                                  List<String> requiredSkills,
                                  int expYears,
                                  int requiredExp) {
        if (requiredSkills == null || requiredSkills.isEmpty()) return 0.0;

        // Skill score (70%)
        long matched = resumeSkills.stream()
                .filter(s -> requiredSkills.stream()
                        .anyMatch(r -> r.trim().equalsIgnoreCase(s.trim())))
                .count();
        double skillScore = ((double) matched / requiredSkills.size()) * 70;

        // Experience score (30%)
        double expScore = requiredExp == 0 ? 30 :
                (Math.min(expYears, requiredExp) / (double) requiredExp) * 30;

        double total = skillScore + expScore;
        return Math.round(total * 100.0) / 100.0;
    }

    /**
     * Return a label based on score
     */
    public String getScoreLabel(double score) {
        if (score >= 70) return "Strong Match";
        if (score >= 40) return "Partial Match";
        return "Low Match";
    }

    /**
     * Return CSS class based on score for color coding
     */
    public String getScoreClass(double score) {
        if (score >= 70) return "score-high";
        if (score >= 40) return "score-medium";
        return "score-low";
    }
}
