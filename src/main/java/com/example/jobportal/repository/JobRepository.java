package com.example.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobportal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

	public List<Job> findByJobTitle(String jobTitle);
	
	public List<Job> findByJobLocation(String jobLocation);
	
	public List<Job> findByJobPackage(String jobPackage);
}
