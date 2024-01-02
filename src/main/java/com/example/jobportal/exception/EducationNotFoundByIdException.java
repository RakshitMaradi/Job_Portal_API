package com.example.jobportal.exception;

public class EducationNotFoundByIdException extends RuntimeException{
	
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EducationNotFoundByIdException(String message) {
		super();
		this.message = message;
	}
	
}
