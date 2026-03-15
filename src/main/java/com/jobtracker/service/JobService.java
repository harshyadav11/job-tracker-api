package com.jobtracker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobtracker.dto.DashboardResponse;
import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.JobStatus;
import com.jobtracker.entity.User;
import com.jobtracker.exception.InvalidStatusTransitionException;
import com.jobtracker.repository.JobRepository;
import com.jobtracker.repository.UserRepository;


@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

   public Page<JobApplication> getUserJobs(String email,
                                        String status,
                                        String company,
                                        Pageable pageable) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (status != null) {

        JobStatus jobStatus = JobStatus.valueOf(status.toUpperCase());

        return jobRepository.findByUserAndStatus(user, jobStatus, pageable);
    }

    if (company != null) {
        return jobRepository.findByUserAndCompanyContaining(user, company, pageable);
    }

    return jobRepository.findByUser(user, pageable);
}

    public JobService(JobRepository jobRepository,
                      UserRepository userRepository) {

        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public JobApplication createJob(JobApplication job, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        job.setUser(user);

        return jobRepository.save(job);
    }

    //Dashboard stats

    public DashboardResponse getDashboard(String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    long total = jobRepository.countByUser(user);
    long interviews = jobRepository.countByUserAndStatus(user, JobStatus.INTERVIEW);
    long offers = jobRepository.countByUserAndStatus(user, JobStatus.OFFER);
    long rejected = jobRepository.countByUserAndStatus(user, JobStatus.REJECTED);

    return new DashboardResponse(total, interviews, offers, rejected);
}

    //Update status of a job application
    public JobApplication updateStatus(Long jobId, JobStatus newStatus) {

    JobApplication job = jobRepository.findById(jobId)
            .orElseThrow(() -> new RuntimeException("Job not found"));

    JobStatus currentStatus = job.getStatus();

    if (!isValidTransition(currentStatus, newStatus)) {
        throw new InvalidStatusTransitionException("Invalid status transition");
    }

    job.setStatus(newStatus);

    return jobRepository.save(job);
}

private boolean isValidTransition(JobStatus current, JobStatus next) {

    if (current == JobStatus.APPLIED) {
        return next == JobStatus.INTERVIEW || next == JobStatus.REJECTED;
    }

    if (current == JobStatus.INTERVIEW) {
        return next == JobStatus.OFFER || next == JobStatus.REJECTED;
    }

    return false;
}




   
}