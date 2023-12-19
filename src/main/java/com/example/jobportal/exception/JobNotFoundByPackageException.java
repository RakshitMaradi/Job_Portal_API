package com.example.jobportal.exception;

public class JobNotFoundByPackageException extends RuntimeException
{
    String message;

	public JobNotFoundByPackageException(String message) {
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
