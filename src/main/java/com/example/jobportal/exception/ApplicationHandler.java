package com.example.jobportal.exception;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.jobportal.utility.ErrorStructure;

@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler{


	@ExceptionHandler(RoleNotPresentException.class)
	public ResponseEntity<ErrorStructure> roleNotFound(RoleNotPresentException exception) {

		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Role entered in not present");

		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> userNotFoundById(UserNotFoundByIdException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("User Id not present");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> list=ex.getAllErrors();
		HashMap<String, String> errorMap=new HashMap<>();
		for(ObjectError error:list)
		{
			FieldError fieldError=(FieldError) error;
			String fieldName=fieldError.getField();
			String message=error.getDefaultMessage();
			errorMap.put(fieldName, message);
		}
		return new ResponseEntity<>(errorMap, headers, status);
	}
}
