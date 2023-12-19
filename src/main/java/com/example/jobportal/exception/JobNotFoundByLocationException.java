package com.example.jobportal.exception;

public class JobNotFoundByLocationException extends RuntimeException{

	String message;

	public JobNotFoundByLocationException(String message) {
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
