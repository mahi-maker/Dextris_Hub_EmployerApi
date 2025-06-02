package com.jobportal.service.impl;

import com.jobportal.dto.ApplicationDTO;
import com.jobportal.dto.StatusUpdateDTO;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.model.Application;
import com.jobportal.model.Job;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.service.ApplicationService;
import com.jobportal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    
    private final ApplicationRepository applicationRepository;
    private final JobService jobService;
    
    @Override
    @Transactional(readOnly = true)
    public List<ApplicationDTO> getApplicationsByJobId(Long jobId) {
        // Verify job exists
        jobService.findJobEntityById(jobId);
        
        List<Application> applications = applicationRepository.findByJobId(jobId);
        return applications.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public ApplicationDTO getApplicationById(Long applicationId) {
        Application application = findApplicationEntityById(applicationId);
        return mapToDTO(application);
    }
    
    @Override
    @Transactional
    public ApplicationDTO updateApplicationStatus(Long applicationId, StatusUpdateDTO statusUpdateDTO) {
        Application application = findApplicationEntityById(applicationId);
        
        application.setStatus(statusUpdateDTO.getStatus());
        if (statusUpdateDTO.getEmployerNotes() != null) {
            application.setEmployerNotes(statusUpdateDTO.getEmployerNotes());
        }
        
        Application updatedApplication = applicationRepository.save(application);
        return mapToDTO(updatedApplication);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Application findApplicationEntityById(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + applicationId));
    }
    
    private ApplicationDTO mapToDTO(Application application) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(application.getId());
        dto.setJobId(application.getJob().getId());
        dto.setApplicantName(application.getApplicantName());
        dto.setApplicantEmail(application.getApplicantEmail());
        dto.setResumeUrl(application.getResumeUrl());
        dto.setCoverLetter(application.getCoverLetter());
        dto.setStatus(application.getStatus());
        dto.setSubmissionDate(application.getSubmissionDate());
        dto.setStatusUpdateDate(application.getStatusUpdateDate());
        dto.setEmployerNotes(application.getEmployerNotes());
        return dto;
    }
}