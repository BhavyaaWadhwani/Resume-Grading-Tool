package com.resumescreener.repository;

import com.resumescreener.model.ResumeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeData, Long> {
    List<ResumeData> findByJobTitleContainingIgnoreCase(String jobTitle);
    List<ResumeData> findAllByOrderByMatchScoreDesc();
}
