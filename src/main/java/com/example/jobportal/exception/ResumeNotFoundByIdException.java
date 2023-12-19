package com.example.jobportal.exception;

public class ResumeNotFoundByIdException extends RuntimeException{

	String message;

	public ResumeNotFoundByIdException(String message) {
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
