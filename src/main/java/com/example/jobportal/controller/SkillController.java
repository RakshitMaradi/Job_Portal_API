package com.example.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.requestdto.SkillRequestDto;
import com.example.jobportal.responsedto.SkillResponseDto;
import com.example.jobportal.service.SkillService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class SkillController {

	@Autowired
	SkillService skillService;

	@PostMapping("/resumes/{resumeId}/skills")
	public ResponseEntity<ResponseStructure<List<SkillResponseDto>>> addSkills(@RequestBody @Valid SkillRequestDto skillRequest, 
			@PathVariable int resumeId)
	{
		return skillService.insertSkill(skillRequest,resumeId);
	}

	@GetMapping("/resumes/{resumeId}/get-skills/skills")
	public ResponseEntity<ResponseStructure<List<SkillResponseDto>>> getSkillsForResume(@PathVariable int resumeId) 
	{
		return skillService.getSkillsForResume(resumeId);
	}

	@PutMapping("/resumes/{resumeId}/skills")
	public ResponseEntity<ResponseStructure<List<SkillResponseDto>>> updateSkillsForResume(@RequestBody @Valid SkillRequestDto updatedSkillRequest,
			@PathVariable int resumeId)
	{
		return skillService.updateSkillsForResume(updatedSkillRequest,resumeId);
	}


}

























