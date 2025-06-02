package com.jobportal.dto;

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
public class JobDTO {
    
    private Long id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    private String location;
    
    @NotBlank(message = "Employment type is required")
    private String employmentType;
    
    private Double salaryMin;
    
    private Double salaryMax;
    
    private String currency;
    
    @NotBlank(message = "Experience level is required")
    private String experience;
    
    @NotBlank(message = "Required skills are required")
    private String skills;
    
    private boolean active = true;
    
    private LocalDateTime postedDate;
    
    private LocalDateTime expirationDate;
    
    private LocalDateTime updatedDate;
    
    @NotNull(message = "Employer ID is required")
    @Positive(message = "Employer ID must be positive")
    private Long employerId;
}