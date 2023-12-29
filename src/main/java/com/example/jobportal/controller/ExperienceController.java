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

import com.example.jobportal.requestdto.ExperienceRequestDto;
import com.example.jobportal.responsedto.ExperienceResponseDto;
import com.example.jobportal.service.ExperienceService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class ExperienceController {
	
	@Autowired
	ExperienceService experienceService;
	
	@PostMapping("/resumes/{resumeId}/experiences")
	public ResponseEntity<ResponseStructure<ExperienceResponseDto>> addExperience(@RequestBody @Valid ExperienceRequestDto experienceRequest, 
			@PathVariable int resumeId)
	{
		return experienceService.insertExperience(experienceRequest,resumeId);
	}
	
    @GetMapping("/resumes/{resumeId}/experiences")
    public ResponseEntity<ResponseStructure<List<ExperienceResponseDto>>> getExperienceListByResumeId(@PathVariable int resumeId)
    {
    	return experienceService.getExperienceListByResumeId(resumeId);
    }
    
    @GetMapping("/resumes/experiences/{experienceId}")
    public ResponseEntity<ResponseStructure<ExperienceResponseDto>> getExperienceByExperienceId(@PathVariable int experienceId)
    {
    	return experienceService.getExperienceByExperienceId(experienceId);
    }
    
	@PutMapping("/resumes/{resumeId}/experiences/{experienceId}")
	public ResponseEntity<ResponseStructure<ExperienceResponseDto>> updateExperienceByExperienceId(@RequestBody @Valid ExperienceRequestDto experienceRequest, 
			@PathVariable int resumeId,@PathVariable int experienceId)
    {
    	return experienceService.updateExperienceByExperienceId(experienceRequest,experienceId,resumeId);
    }
	
	@DeleteMapping("/resumes/experiences/{experienceId}")
    public ResponseEntity<ResponseStructure<ExperienceResponseDto>> deleteExperienceByExperienceId(@PathVariable int experienceId)
    {
    	return experienceService.deleteExperienceByExperienceId(experienceId);
    }

}
