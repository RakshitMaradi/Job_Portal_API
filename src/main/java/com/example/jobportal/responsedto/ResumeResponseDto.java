package com.example.jobportal.responsedto;

import java.util.Map;

public class ResumeResponseDto {

	private int resumeId;
	private String name;
	private String objective;
	
	Map<String,String> skillsListOption; 
	Map<String,String> projectOptions;

	public Map<String, String> getProjectOptions() {
		return projectOptions;
	}
	public void setProjectOptions(Map<String, String> projectOptions) {
		this.projectOptions = projectOptions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getSkillsListOption() {
		return skillsListOption;
	}
	public void setSkillsListOption(Map<String, String> skillsListOption) {
		this.skillsListOption = skillsListOption;
	}
	public int getResumeId() {
		return resumeId;
	}
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
}
