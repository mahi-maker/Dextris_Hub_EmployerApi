package com.jobportal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, length = 5000)
    private String description;
    
    private String location;
    
    @Column(nullable = false)
    private String employmentType;
    
    private Double salaryMin;
    
    private Double salaryMax;
    
    private String currency;
    
    @Column(nullable = false)
    private String experience;
    
    @Column(nullable = false)
    private String skills;
    
    @Column(nullable = false)
    private boolean active = true;
    
    @Column(nullable = false)
    private LocalDateTime postedDate;
    
    private LocalDateTime expirationDate;
    
    private LocalDateTime updatedDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;
    
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        postedDate = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}