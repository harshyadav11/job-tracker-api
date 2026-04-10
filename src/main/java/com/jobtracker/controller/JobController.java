package com.jobtracker.controller;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobtracker.dto.ApiResponse;
import com.jobtracker.dto.DashboardResponse;
import com.jobtracker.dto.JobResponseDTO;
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

    // TEMP: using fixed email
    private final String TEMP_EMAIL = "test@gmail.com";

    @GetMapping("/dashboard")
    public DashboardResponse dashboard() {
        return jobService.getDashboard(TEMP_EMAIL);
    }

    @PostMapping
    public JobApplication createJob(@RequestBody JobApplication job) {
        return jobService.createJob(job, TEMP_EMAIL);
    }

  @GetMapping
public ApiResponse<List<JobResponseDTO>> getMyJobs(
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String company,
        Pageable pageable) {

    String email = "test@gmail.com";

    Page<JobResponseDTO> page = jobService.getUserJobs(email, status, company, pageable);

    return new ApiResponse<>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements()
    );
}

    @PutMapping("/{id}/status")
    public JobApplication updateStatus(@PathVariable Long id,
                                       @RequestParam JobStatus status) {

        return jobService.updateStatus(id, status);
    }
}