package com.example.jobportal.entity;

import java.util.List;

import com.example.jobportal.enums.JobStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobId;
	private String jobTitle;
	private long jobPackage;
	private String jobLocation;
	private List<String> jobSkills;
	private JobStatus jobStatus;
	private String jobExperienceRequired;
	
	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	@ManyToOne
	private Company company;

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public long getJobPackage() {
		return jobPackage;
	}

	public void setJobPackage(long jobPackage) {
		this.jobPackage = jobPackage;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public List<String> getJobSkills() {
		return jobSkills;
	}

	public void setJobSkills(List<String> jobSkills) {
		this.jobSkills = jobSkills;
	}

	public String getJobExperienceRequired() {
		return jobExperienceRequired;
	}

	public void setJobExperienceRequired(String jobExperienceRequired) {
		this.jobExperienceRequired = jobExperienceRequired;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
