package com.jobtracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.JobStatus;
import com.jobtracker.entity.User;

public interface JobRepository extends JpaRepository<JobApplication, Long> {

    Page<JobApplication> findByUser(User user, Pageable pageable);
    long countByUser(User user);

long countByUserAndStatus(User user, JobStatus status);


  Page<JobApplication> findByUserAndStatus(User user, JobStatus status, Pageable pageable);

Page<JobApplication> findByUserAndCompanyContaining(User user, String company, Pageable pageable);
   

}