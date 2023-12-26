package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.SkillRequestDto;
import com.example.jobportal.responsedto.SkillResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface SkillService {

	ResponseEntity<ResponseStructure<List<SkillResponseDto>>> insertSkill(@Valid SkillRequestDto skillRequest, int resumeId);

	ResponseEntity<ResponseStructure<List<SkillResponseDto>>> getSkillsForResume(int resumeId);

	ResponseEntity<ResponseStructure<List<SkillResponseDto>>> updateSkillsForResume(
			@Valid  SkillRequestDto updatedSkillRequest, int resumeId);

}
