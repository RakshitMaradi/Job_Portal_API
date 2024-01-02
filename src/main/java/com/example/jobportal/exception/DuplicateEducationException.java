package com.example.jobportal.exception;

public class DuplicateEducationException extends RuntimeException
{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DuplicateEducationException(String message) {
		super();
		this.message = message;
	}
	
}
