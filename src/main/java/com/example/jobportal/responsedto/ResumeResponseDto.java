package com.example.jobportal.responsedto;

import java.util.Map;

public class ResumeResponseDto {

	private int resumeId;
	private String username;
	private String objective;
	
	Map<String,String> skillsListOption; 

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
