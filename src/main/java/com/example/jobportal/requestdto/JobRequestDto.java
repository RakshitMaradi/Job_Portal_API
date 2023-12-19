package com.example.jobportal.requestdto;

import java.util.List;

import com.example.jobportal.entity.Company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobRequestDto {
	
	@NotBlank(message = "Job title cannot be blank")
	@NotNull(message = "Job title cannot be null")
	private String jobTitle;
	
	@NotBlank(message = "Package cannot be blank")
	@NotNull(message = "Package cannot be null")
	private long jobPackage;
	
	@NotBlank(message = "Location cannot be blank")
	@NotNull(message = "Location cannot be null")
	private String jobLocation;
	
	@NotBlank(message = "Skills cannot be blank")
	@NotNull(message = "Skills cannot be null")
	private List<String> jobSkills;
	
//	@NotBlank(message = "Location cannot be blank")
//	@NotNull(message = "Location cannot be null")
	private String jobExperienceRequired;
	
	
	private Company company;
	
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
