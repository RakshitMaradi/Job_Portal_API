package com.example.jobportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resumeId;
	private String Objective;

	public int getResumeId() {
		return resumeId;
	}
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}
	public String getObjective() {
		return Objective;
	}
	public void setObjective(String objective) {
		Objective = objective;
	}
}
