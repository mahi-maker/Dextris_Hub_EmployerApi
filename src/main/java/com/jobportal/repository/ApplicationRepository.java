package com.jobportal.repository;

import com.jobportal.model.Application;
import com.jobportal.model.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
    List<Application> findByJobId(Long jobId);
    
    List<Application> findByJobIdAndStatus(Long jobId, ApplicationStatus status);
    
    List<Application> findByJobEmployerId(Long employerId);
}