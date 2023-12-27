package com.example.jobportal.responsedto;

import java.util.Set;

import com.example.jobportal.entity.Resume;

import jakarta.persistence.ManyToOne;

public class ProjectResponseDto {

	private String projectName;
	private Set<String> techStack;
	private String description;
	private String website;
	private String sourceCode;
	
	@ManyToOne
	private Resume resume;

	public Set<String> getTechStack() {
		return techStack;
	}

	public void setTechStack(Set<String> techStack) {
		this.techStack = techStack;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}
}
