package com.jobtracker.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobtracker.dto.DashboardResponse;
import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.JobStatus;
import com.jobtracker.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    
    @GetMapping("/dashboard")
    public DashboardResponse dashboard(Authentication authentication) {

        String email = authentication.getName();
        return jobService.getDashboard(email);
    }

    @PostMapping
    public JobApplication createJob(@RequestBody JobApplication job,
                                    Authentication authentication) {

        String email = authentication.getName();
        return jobService.createJob(job, email);
    }

    @GetMapping
    public Page<JobApplication> getMyJobs(Authentication authentication,
                                          @RequestParam(required = false) String status,
                                          @RequestParam(required = false) String company,
                                          Pageable pageable) {

        String email = authentication.getName();
        return jobService.getUserJobs(email, status, company, pageable);
    }

    @PutMapping("/{id}/status")
    public JobApplication updateStatus(@PathVariable Long id,
                                       @RequestParam JobStatus status) {

        return jobService.updateStatus(id, status);
    }

}