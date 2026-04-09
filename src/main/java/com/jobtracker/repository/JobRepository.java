package com.jobtracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.JobStatus;
import com.jobtracker.entity.User;

public interface JobRepository extends JpaRepository<JobApplication, Long> {

    Page<JobApplication> findByUserEmail(String email, Pageable pageable);

    Page<JobApplication> findByUserEmailAndStatus(String email, JobStatus status, Pageable pageable);

    Page<JobApplication> findByUserEmailAndCompanyContainingIgnoreCase(
            String email,
            String company,
            Pageable pageable
    );

    Page<JobApplication> findByUserEmailAndStatusAndCompanyContainingIgnoreCase(
            String email,
            JobStatus status,
            String company,
            Pageable pageable
    );

    long countByUser(User user);

    long countByUserAndStatus(User user, JobStatus status);
}