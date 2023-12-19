package com.example.jobportal.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequestDto {
	
	@NotBlank(message = "Username cannot be blank")
	@NotNull(message = "Username cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Invalid format. Must start with an uppercase letter, followed by letters or spaces.")
	private String username;
	
	@NotBlank(message = "Email cannot be blank")
	@NotNull(message = "Email cannot be null")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[g][m][a][i][l]+.[c][o][m]", message = "invalid email--Should be in the extension of '@gmail.com' ")
	private String email;
	
	@NotBlank(message = "Password cannot be blank")
	@NotNull(message = "Password cannot be null")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "8 characters mandatory(Include atleast 1 upperCase,1 lowerCase,1 special Character,1 number)")	
	private String password;
	
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
}
