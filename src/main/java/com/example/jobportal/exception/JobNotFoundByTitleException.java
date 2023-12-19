package com.example.jobportal.exception;

public class JobNotFoundByTitleException extends RuntimeException{

	String message;

	public JobNotFoundByTitleException(String message) {
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
