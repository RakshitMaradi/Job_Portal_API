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

import com.example.jobportal.requestdto.ResumeRequestDto;
import com.example.jobportal.responsedto.ResumeResponseDto;
import com.example.jobportal.service.ResumeService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class ResumeController {
	
	@Autowired
	ResumeService resumeService;

	@PostMapping("/resumes/users/{userId}")
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> insert(@PathVariable int userId,
			@RequestBody @Valid ResumeRequestDto resumeRequest)
	{
		return resumeService.insertResume(resumeRequest,userId);
	}
	
	@PutMapping("/users/{userId}/resumes/{resumeId}")
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> update(@PathVariable int userId,@PathVariable int resumeId,
			@RequestBody @Valid ResumeRequestDto resumeRequest)
	{
		return resumeService.updateResume(resumeRequest,userId,resumeId);
	}
	
	@GetMapping("/users/{userId}/resumes/{resumeId}")
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> findById(@PathVariable int resumeId,@PathVariable int userId)
	{
		return resumeService.findResumeById(resumeId,userId);
	}
	
	@GetMapping("/users/{userId}/skills/{skillName}")
	public ResponseEntity<ResponseStructure<List<ResumeResponseDto>>> findBySkill(@PathVariable int userId,@PathVariable String skillName )
	{
		return resumeService.findResumeBySkill(userId,skillName);
	}

	@DeleteMapping("/users/{userId}/resumes/{resumeId}")
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> deleteById(@PathVariable int resumeId,@PathVariable int userId)
	{
		return resumeService.deleteResumeById(resumeId,userId);
	}
	
}
