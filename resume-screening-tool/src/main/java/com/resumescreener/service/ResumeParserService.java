package com.resumescreener.service;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResumeParserService {

    private final Tika tika = new Tika();

    /**
     * Extract plain text from uploaded PDF or DOCX file
     */
    public String extractText(MultipartFile file) throws Exception {
        return tika.parseToString(file.getInputStream());
    }

    /**
     * Extract email address using regex
     */
    public String extractEmail(String text) {
        Matcher m = Pattern.compile(
            "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        ).matcher(text);
        return m.find() ? m.group() : "Not found";
    }

    /**
     * Extract Indian phone number using regex
     */
    public String extractPhone(String text) {
        Matcher m = Pattern.compile(
            "(\\+91[\\s-]?)?[6-9][0-9]{9}"
        ).matcher(text);
        return m.find() ? m.group() : "Not found";
    }

    /**
     * Extract years of experience from resume text
     */
    public int extractExperience(String text) {
        Matcher m = Pattern.compile(
            "(\\d+)\\s*(\\+\\s*)?(years?|yrs?)"
        ).matcher(text.toLowerCase());
        return m.find() ? Integer.parseInt(m.group(1)) : 0;
    }
}
