package com.example.jobportal.exception;

public class ExperienceNotFoundByIdException extends RuntimeException{

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExperienceNotFoundByIdException(String message) {
		super();
		this.message = message;
	}
	
}
