package com.example.jobportal.exception;

public class CompanyNotFoundByIdException extends RuntimeException{

	String message;

	public CompanyNotFoundByIdException(String message) {
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
