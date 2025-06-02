package com.jobportal.controller;

import com.jobportal.dto.JobDTO;
//import com.jobportal.dto.response.ApiResponse;
import com.jobportal.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employer/jobs")
@RequiredArgsConstructor
@Tag(name = "Job Controller", description = "API for employer job management")
public class JobController {
    
    private final JobService jobService;
    
    @PostMapping
    @Operation(summary = "Create a new job posting")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Job created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Employer not found")
    })
    public ResponseEntity<com.jobportal.dto.response.ApiResponse<JobDTO>> createJob(
            @Valid @RequestBody JobDTO jobDTO) {
        JobDTO createdJob = jobService.createJob(jobDTO);
        return new ResponseEntity<>(
                com.jobportal.dto.response.ApiResponse.success("Job created successfully", createdJob), 
                HttpStatus.CREATED);
    }
    
    @GetMapping
    @Operation(summary = "Get all jobs for an employer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Jobs retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Employer not found")
    })
    public ResponseEntity<com.jobportal.dto.response.ApiResponse<List<JobDTO>>> getEmployerJobs(
            @Parameter(description = "Employer ID", required = true)
            @RequestParam Long employerId) {
        List<JobDTO> jobs = jobService.getEmployerJobs(employerId);
        return ResponseEntity.ok(com.jobportal.dto.response.ApiResponse.success(jobs));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get job details by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Job retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Job not found")
    })
    public ResponseEntity<com.jobportal.dto.response.ApiResponse<JobDTO>> getJobById(
            @Parameter(description = "Job ID", required = true)
            @PathVariable("id") Long jobId) {
        JobDTO job = jobService.getJobById(jobId);
        return ResponseEntity.ok(com.jobportal.dto.response.ApiResponse.success(job));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing job")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Job updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Job not found")
    })
    public ResponseEntity<com.jobportal.dto.response.ApiResponse<JobDTO>> updateJob(
            @Parameter(description = "Job ID", required = true)
            @PathVariable("id") Long jobId,
            @Valid @RequestBody JobDTO jobDTO) {
        JobDTO updatedJob = jobService.updateJob(jobId, jobDTO);
        return ResponseEntity.ok(
                com.jobportal.dto.response.ApiResponse.success("Job updated successfully", updatedJob));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a job")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Job deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Job not found")
    })
    public ResponseEntity<Void> deleteJob(
            @Parameter(description = "Job ID", required = true)
            @PathVariable("id") Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.noContent().build();
    }
}