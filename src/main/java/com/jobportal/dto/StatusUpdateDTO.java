package com.jobportal.dto;

import com.jobportal.model.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateDTO {
    
    @NotNull(message = "Status is required")
    private ApplicationStatus status;
    
    private String employerNotes;
}