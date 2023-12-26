package com.example.jobportal.entity;

import java.util.List;

import com.example.jobportal.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String username;
	private String email;
	private String password;
	private UserRole userrole;
	
	@OneToMany(mappedBy = "user")
	private List<Company> companyList;		                //only employer can add multiple companies
	
	@OneToOne(mappedBy = "user")		                    //only applicant can have one resume
	private Resume resume;
	
	@OneToMany
	private List<JobApplication> jobApplicationList;		//only applicant can have the resume
	
	public List<Company> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
	public List<JobApplication> getJobApplicationList() {
		return jobApplicationList;
	}
	public void setJobApplicationList(List<JobApplication> jobApplicationList) {
		this.jobApplicationList = jobApplicationList;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getUserrole() {
		return userrole;
	}
	public void setUserrole(UserRole userrole) {
		this.userrole = userrole;
	}
	
}
