package com.example.jobportal.responsedto;

import java.util.List;
import java.util.Map;

public class JobResponseDto {

	private int jobId;
	private String jobTitle;
	private long jobPackage;
	private String jobLocation;
	private List<String> jobSkills;
	private String jobExperienceRequired;
	private Map<String,String> companyOptions;
	
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
	public Map<String, String> getCompanyOptions() {
		return companyOptions;
	}
	public void setCompanyOptions(Map<String, String> companyOptions) {
		this.companyOptions = companyOptions;
	}
}
