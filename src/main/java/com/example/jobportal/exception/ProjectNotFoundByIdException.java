package com.example.jobportal.exception;

public class ProjectNotFoundByIdException extends RuntimeException{

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ProjectNotFoundByIdException(String message) {
		super();
		this.message = message;
	}
	
}
