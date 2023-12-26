package com.example.jobportal.exception;

public class ResumesNotFoundBySkillException extends RuntimeException{
	
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResumesNotFoundBySkillException(String message) {
		super();
		this.message = message;
	}
	
}
