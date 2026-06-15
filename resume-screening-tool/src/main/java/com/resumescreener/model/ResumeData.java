package com.resumescreener.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "resumes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateName;
    private String email;
    private String phone;

    @Column(length = 1000)
    private String skills;

    private int experienceYears;
    private String jobTitle;
    private double matchScore;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;
}
