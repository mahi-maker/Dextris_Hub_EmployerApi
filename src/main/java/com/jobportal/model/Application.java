package com.jobportal.model;

import com.jobportal.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    
    @Column(nullable = false)
    private String applicantName;
    
    @Column(nullable = false)
    private String applicantEmail;
    
    private String resumeUrl;
    
    private String coverLetter;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.SUBMITTED;
    
    @Column(nullable = false)
    private LocalDateTime submissionDate;
    
    private LocalDateTime statusUpdateDate;
    
    private String employerNotes;
    
    @PrePersist
    protected void onCreate() {
        submissionDate = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        statusUpdateDate = LocalDateTime.now();
    }
}