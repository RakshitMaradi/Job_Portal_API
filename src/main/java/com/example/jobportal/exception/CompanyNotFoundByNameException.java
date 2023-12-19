package com.example.jobportal.exception;

public class CompanyNotFoundByNameException extends RuntimeException{

	String message;

	public CompanyNotFoundByNameException(String message) {
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
