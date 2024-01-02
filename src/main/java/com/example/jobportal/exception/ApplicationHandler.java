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
	
	@ExceptionHandler(BusinessTypeNotPresentException.class)
	public ResponseEntity<ErrorStructure> businessNotFound(BusinessTypeNotPresentException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Business type not found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnauthorizedAccessByUserException.class)
	public ResponseEntity<ErrorStructure> unauthorizedAccessByUser(UnauthorizedAccessByUserException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Not authorised");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(CompanyNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> companyNotFoundById(CompanyNotFoundByIdException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Company not found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CompanyNotFoundByNameException.class)
	public ResponseEntity<ErrorStructure> companyNotFoundByName(CompanyNotFoundByNameException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Company not found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CompaniesNotPresentException.class)
	public ResponseEntity<ErrorStructure> companiesNotPresent(CompaniesNotPresentException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Companies not found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(JobNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> jobNotFoundById(JobNotFoundByIdException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Job not found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(JobNotFoundByTitleException.class)
	public ResponseEntity<ErrorStructure> jobNotFoundByName(JobNotFoundByTitleException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Job not found for the mentioned job title");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler(JobNotFoundByLocationException.class)
	public ResponseEntity<ErrorStructure> jobNotFoundByLocation(JobNotFoundByLocationException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Job not found at this location");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(JobNotFoundByPackageException.class)
	public ResponseEntity<ErrorStructure> jobNotFoundByPackage(JobNotFoundByPackageException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Job not found for this package");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResumeNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> resumeNotFoundById(ResumeNotFoundByIdException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Resume not found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResumesNotFoundBySkillException.class)
	public ResponseEntity<ErrorStructure> resumeNotFoundBySkill(ResumesNotFoundBySkillException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Resume not found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ProjectNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> projectNotFoundById(ProjectNotFoundByIdException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Project not found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ExperienceNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> experienceNotFoundById(ExperienceNotFoundByIdException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Experience not found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EducationNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> experienceNotFoundById(EducationNotFoundByIdException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Education not found");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(DuplicateEducationException.class)
	public ResponseEntity<ErrorStructure> duplicateEducationException(DuplicateEducationException exception) {
		
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Duplicating education");
		
		return new ResponseEntity<ErrorStructure>(errorStructure, HttpStatus.BAD_REQUEST);
	}
	
	
}
