package com.jobportal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String companyName;
    
    private String companyDescription;
    
    private String location;
    
    private String website;
    
    @Column(nullable = false)
    private String industry;
    
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<Job> jobs = new ArrayList<>();
}