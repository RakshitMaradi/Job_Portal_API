package com.example.jobportal.responsedto;

import com.example.jobportal.enums.UserRole;

public class UserResponseDto {
	
	private int userId;
	private String username;
	private String email;
	private UserRole userrole;
	
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
	public UserRole getUserRole() {
		return userrole;
	}
	public void setUserRole(UserRole userrole) {
		this.userrole = userrole;
	}

}
