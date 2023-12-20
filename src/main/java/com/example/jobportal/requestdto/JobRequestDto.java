package com.example.jobportal.requestdto;

import com.example.jobportal.entity.Company;
import com.example.jobportal.enums.JobStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobRequestDto {
	
	@NotBlank(message = "Job title cannot be blank")
	@NotNull(message = "Job title cannot be null")
	private String jobTitle;
	
	@NotNull(message = "Package cannot be null")
	@NotNull(message = "Location cannot be null")
	private String jobPackage;
	
	@NotBlank(message = "Location cannot be blank")
	@NotNull(message = "Location cannot be null")
	private String jobLocation;
	
	@NotBlank(message = "Experience cannot be blank")
	@NotNull(message = "Experience cannot be null")
	private String jobExperienceRequired;
	
	private JobStatus jobStatus;
	
	public JobStatus getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}
	private Company company;
	
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobPackage() {
		return jobPackage;
	}
	public void setJobPackage(String jobPackage) {
		this.jobPackage = jobPackage;
	}
	public String getJobLocation() {
		return jobLocation;
	}
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
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
