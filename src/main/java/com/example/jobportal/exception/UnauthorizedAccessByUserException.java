package com.example.jobportal.exception;

public class UnauthorizedAccessByUserException extends RuntimeException{
	
	String message;

	public UnauthorizedAccessByUserException(String message) {
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
