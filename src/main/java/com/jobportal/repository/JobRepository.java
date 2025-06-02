package com.jobportal.repository;

import com.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    
    List<Job> findByEmployerId(Long employerId);
    
    @Query("SELECT j FROM Job j WHERE j.employer.id = :employerId AND j.active = true")
    List<Job> findActiveJobsByEmployerId(@Param("employerId") Long employerId);
}