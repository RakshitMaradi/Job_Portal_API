package com.example.jobportal.responsedto;

import java.util.Map;

public class ResumeResponseDto {

	private int resumeId;
	private String name;
	private String objective;
	
	Map<String,String> Options;

	public Map<String, String> getOptions() {
		return Options;
	}
	public void setOptions(Map<String, String> options) {
		Options = options;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
