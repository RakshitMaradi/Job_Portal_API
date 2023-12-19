package com.example.jobportal.exception;

public class JobNotFoundByIdException extends RuntimeException{

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JobNotFoundByIdException(String message) {
		super();
		this.message = message;
	}
	
}
