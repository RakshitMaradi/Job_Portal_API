package com.example.jobportal.exception;

public class CompaniesNotPresentException extends RuntimeException{
	
	String message;

	public CompaniesNotPresentException(String message) {
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
