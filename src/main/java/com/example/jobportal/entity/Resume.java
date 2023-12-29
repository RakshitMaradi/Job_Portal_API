package com.example.jobportal.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resumeId;
	private String name;
	private String objective;
	
	@OneToOne
	private User user;

	@ManyToMany
	private List<Skill> skillsList;
	
	@OneToMany(mappedBy="resume")
	private List<Project> projectList;
	
	@OneToMany(mappedBy="resume")
	private List<Experience> experienceList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Skill> getSkillsList() {
		return skillsList;
	}
	public void setSkillsList(List<Skill> skillsList) {
		this.skillsList = skillsList;
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
	public List<Project> getProjectList() {
		return projectList;
	}
	public List<Experience> getExperienceList() {
		return experienceList;
	}
	public void setExperienceList(List<Experience> experienceList) {
		this.experienceList = experienceList;
	}
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	
}
