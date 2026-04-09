package com.jobtracker.dto;

import com.jobtracker.entity.JobStatus;

public class JobResponseDTO {

    private String company;
    private String role;
    private JobStatus status;

    public JobResponseDTO(String company, String role, JobStatus status) {
        this.company = company;
        this.role = role;
        this.status = status;
    }

    public String getCompany() { return company; }
    public String getRole() { return role; }
    public JobStatus getStatus() { return status; }
}