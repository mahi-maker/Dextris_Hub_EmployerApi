package com.jobportal.service.impl;

import com.jobportal.dto.JobDTO;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.model.Employer;
import com.jobportal.model.Job;
import com.jobportal.repository.EmployerRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    
    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;
    
    @Override
    @Transactional
    public JobDTO createJob(JobDTO jobDTO) {
        Employer employer = employerRepository.findById(jobDTO.getEmployerId())
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found with id: " + jobDTO.getEmployerId()));
        
        Job job = mapToEntity(jobDTO);
        job.setEmployer(employer);
        job.setPostedDate(LocalDateTime.now());
        
        Job savedJob = jobRepository.save(job);
        return mapToDTO(savedJob);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<JobDTO> getEmployerJobs(Long employerId) {
        // Check if employer exists
        if (!employerRepository.existsById(employerId)) {
            throw new ResourceNotFoundException("Employer not found with id: " + employerId);
        }
        
        List<Job> jobs = jobRepository.findByEmployerId(employerId);
        return jobs.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public JobDTO getJobById(Long jobId) {
        Job job = findJobEntityById(jobId);
        return mapToDTO(job);
    }
    
    @Override
    @Transactional
    public JobDTO updateJob(Long jobId, JobDTO jobDTO) {
        Job existingJob = findJobEntityById(jobId);
        
        // Update fields
        existingJob.setTitle(jobDTO.getTitle());
        existingJob.setDescription(jobDTO.getDescription());
        existingJob.setLocation(jobDTO.getLocation());
        existingJob.setEmploymentType(jobDTO.getEmploymentType());
        existingJob.setSalaryMin(jobDTO.getSalaryMin());
        existingJob.setSalaryMax(jobDTO.getSalaryMax());
        existingJob.setCurrency(jobDTO.getCurrency());
        existingJob.setExperience(jobDTO.getExperience());
        existingJob.setSkills(jobDTO.getSkills());
        existingJob.setActive(jobDTO.isActive());
        existingJob.setExpirationDate(jobDTO.getExpirationDate());
        
        Job updatedJob = jobRepository.save(existingJob);
        return mapToDTO(updatedJob);
    }
    
    @Override
    @Transactional
    public void deleteJob(Long jobId) {
        if (!jobRepository.existsById(jobId)) {
            throw new ResourceNotFoundException("Job not found with id: " + jobId);
        }
        jobRepository.deleteById(jobId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Job findJobEntityById(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));
    }
    
    private Job mapToEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setEmploymentType(jobDTO.getEmploymentType());
        job.setSalaryMin(jobDTO.getSalaryMin());
        job.setSalaryMax(jobDTO.getSalaryMax());
        job.setCurrency(jobDTO.getCurrency());
        job.setExperience(jobDTO.getExperience());
        job.setSkills(jobDTO.getSkills());
        job.setActive(jobDTO.isActive());
        job.setExpirationDate(jobDTO.getExpirationDate());
        return job;
    }
    
    private JobDTO mapToDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setEmploymentType(job.getEmploymentType());
        jobDTO.setSalaryMin(job.getSalaryMin());
        jobDTO.setSalaryMax(job.getSalaryMax());
        jobDTO.setCurrency(job.getCurrency());
        jobDTO.setExperience(job.getExperience());
        jobDTO.setSkills(job.getSkills());
        jobDTO.setActive(job.isActive());
        jobDTO.setPostedDate(job.getPostedDate());
        jobDTO.setExpirationDate(job.getExpirationDate());
        jobDTO.setUpdatedDate(job.getUpdatedDate());
        jobDTO.setEmployerId(job.getEmployer().getId());
        return jobDTO;
    }
}