package com.example.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobportal.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

	public List<Job> findByJobTitle(String jobTitle);
	
	public List<Job> findByJobLocation(String jobLocation);
	
	public List<Job> findByJobPackage(long jobPackage);
}
