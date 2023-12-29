package com.example.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/resumes/{resumeId}/experience")
	public ResponseEntity<ResponseStructure<ExperienceResponseDto>> addExperience(@RequestBody @Valid ExperienceRequestDto experienceRequest, 
			@PathVariable int resumeId)
	{
		return experienceService.insertExperience(experienceRequest,resumeId);
	}
	
    @GetMapping("/resumes/{resumeId}/experience")
    public ResponseEntity<ResponseStructure<List<ExperienceResponseDto>>> getExperienceListByResumeId(@PathVariable int resumeId)
    {
    	return experienceService.getExperienceListByResumeId(resumeId);
    }
    
    @GetMapping("/resumes/experience/{experienceId}")
    public ResponseEntity<ResponseStructure<ExperienceResponseDto>> getExperienceByExperienceId(@PathVariable int experienceId)
    {
    	return experienceService.getExperienceByExperienceId(experienceId);
    }
    
	

}
