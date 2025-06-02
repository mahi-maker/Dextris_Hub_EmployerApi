package com.jobportal.controller;

import com.jobportal.dto.ApplicationDTO;
import com.jobportal.dto.StatusUpdateDTO;
import com.jobportal.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Application Controller", description = "API for managing job applications")
public class ApplicationController {
    
    private final ApplicationService applicationService;
    
    @GetMapping("/employer/jobs/{jobId}/applications")
    @Operation(summary = "Get all applications for a job")
    public ResponseEntity<com.jobportal.dto.response.ApiResponse<List<ApplicationDTO>>> getApplicationsByJobId(
            @Parameter(description = "Job ID", required = true)
            @PathVariable Long jobId) {
        List<ApplicationDTO> applications = applicationService.getApplicationsByJobId(jobId);
        return ResponseEntity.ok(
                com.jobportal.dto.response.ApiResponse.success(applications));
    }
    
    @PutMapping("/employer/applications/{id}/status")
    @Operation(summary = "Update application status")
    public ResponseEntity<com.jobportal.dto.response.ApiResponse<ApplicationDTO>> updateApplicationStatus(
            @Parameter(description = "Application ID", required = true)
            @PathVariable("id") Long applicationId,
            @Valid @RequestBody StatusUpdateDTO statusUpdateDTO) {
        ApplicationDTO updatedApplication = applicationService.updateApplicationStatus(applicationId, statusUpdateDTO);
        return ResponseEntity.ok(
                com.jobportal.dto.response.ApiResponse.success("Application status updated successfully", updatedApplication));
    }
    
    @GetMapping("/employer/applications/{id}")
    @Operation(summary = "Get application details by ID")
    public ResponseEntity<com.jobportal.dto.response.ApiResponse<ApplicationDTO>> getApplicationById(
            @Parameter(description = "Application ID", required = true)
            @PathVariable("id") Long applicationId) {
        ApplicationDTO application = applicationService.getApplicationById(applicationId);
        return ResponseEntity.ok(
                com.jobportal.dto.response.ApiResponse.success(application));
    }
}