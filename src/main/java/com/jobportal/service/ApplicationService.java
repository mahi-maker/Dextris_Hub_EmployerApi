package com.jobportal.service;

import com.jobportal.dto.ApplicationDTO;
import com.jobportal.dto.StatusUpdateDTO;
import com.jobportal.model.Application;

import java.util.List;

public interface ApplicationService {
    
    List<ApplicationDTO> getApplicationsByJobId(Long jobId);
    
    ApplicationDTO getApplicationById(Long applicationId);
    
    ApplicationDTO updateApplicationStatus(Long applicationId, StatusUpdateDTO statusUpdateDTO);
    
    Application findApplicationEntityById(Long applicationId);
}