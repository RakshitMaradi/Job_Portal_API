package com.example.jobportal.exception;

public class BusinessTypeNotPresentException extends RuntimeException{

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BusinessTypeNotPresentException(String message) {
		super();
		this.message = message;
	}
	
}