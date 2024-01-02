package com.example.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.requestdto.EducationRequestDto;
import com.example.jobportal.responsedto.EducationResponseDto;
import com.example.jobportal.service.EducationService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class EducationContoller {
	
	@Autowired
	EducationService educationService;

	@PostMapping("/resumes/{resumeId}/educations")
	public ResponseEntity<ResponseStructure<EducationResponseDto>> addEducation(@RequestBody @Valid EducationRequestDto educationRequest, 
			@PathVariable int resumeId)
	{
		return educationService.insertEducation(educationRequest,resumeId);
	}
	
	@PutMapping("/resumes/{resumeId}/educations/{educationId}")
	public ResponseEntity<ResponseStructure<EducationResponseDto>> updateEducation(@RequestBody @Valid EducationRequestDto educationRequest, 
			@PathVariable int resumeId,@PathVariable int educationId)
	{
		return educationService.updateEducation(educationRequest,resumeId,educationId);
	}

	@GetMapping("/resumes/{resumeId}/educations")
	public ResponseEntity<ResponseStructure<List<EducationResponseDto>>> getEduationListByResumeId(@PathVariable int resumeId)
	{
		return educationService.getEducationListByResumeId(resumeId);
	}
	
	
	@GetMapping("/resumes/educations/{educationId}")
	public ResponseEntity<ResponseStructure<EducationResponseDto>> getEduationByEducationId(@PathVariable int educationId)
	{
		return educationService.getEduationByEducationId(educationId);
	}
	
	@DeleteMapping("/resumes/educations/{educationId}")
	public ResponseEntity<ResponseStructure<EducationResponseDto>> deleteEduationByEducationId(@PathVariable int educationId)
	{
		return educationService.deleteEduationByEducationId(educationId);
	}
	
	
}
