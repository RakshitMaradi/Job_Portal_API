package com.example.jobportal.exception;

public class RoleNotPresentException extends RuntimeException {
	
	String message;
	
	public RoleNotPresentException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
