package com.jobportal.service;

import com.jobportal.dto.JobDTO;
import com.jobportal.model.Job;

import java.util.List;

public interface JobService {
    
    JobDTO createJob(JobDTO jobDTO);
    
    List<JobDTO> getEmployerJobs(Long employerId);
    
    JobDTO getJobById(Long jobId);
    
    JobDTO updateJob(Long jobId, JobDTO jobDTO);
    
    void deleteJob(Long jobId);
    
    Job findJobEntityById(Long jobId);
}