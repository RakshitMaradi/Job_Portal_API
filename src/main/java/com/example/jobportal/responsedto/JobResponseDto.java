package com.example.jobportal.responsedto;

import java.util.Map;

import com.example.jobportal.enums.JobStatus;

public class JobResponseDto {

	private int jobId;
	private String jobTitle;
	private String jobPackage;
	private String jobLocation;
	private String jobExperienceRequired;
	private JobStatus jobStatus;
	private Map<String,String> companyOptions;

	public JobStatus getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	public String getJobPackage() {
		return jobPackage;
	}
	public void setJobPackage(String jobPackage) {
		this.jobPackage = jobPackage;
	}

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
	public Map<String, String> getCompanyOptions() {
		return companyOptions;
	}
	public void setCompanyOptions(Map<String, String> companyOptions) {
		this.companyOptions = companyOptions;
	}
}
