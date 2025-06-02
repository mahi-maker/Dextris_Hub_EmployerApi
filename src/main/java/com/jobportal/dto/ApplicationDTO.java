package com.jobportal.dto;

import com.jobportal.model.enums.ApplicationStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    
    private Long id;
    
    @NotNull(message = "Job ID is required")
    @Positive(message = "Job ID must be positive")
    private Long jobId;
    
    @NotBlank(message = "Applicant name is required")
    private String applicantName;
    
    @NotBlank(message = "Applicant email is required")
    @Email(message = "Invalid email format")
    private String applicantEmail;
    
    private String resumeUrl;
    
    private String coverLetter;
    
    private ApplicationStatus status = ApplicationStatus.SUBMITTED;
    
    private LocalDateTime submissionDate;
    
    private LocalDateTime statusUpdateDate;
    
    private String employerNotes;
}