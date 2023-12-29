package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.ExperienceRequestDto;
import com.example.jobportal.responsedto.ExperienceResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface ExperienceService {

	ResponseEntity<ResponseStructure<ExperienceResponseDto>> insertExperience(
			@Valid ExperienceRequestDto experienceRequest, int resumeId);

	ResponseEntity<ResponseStructure<List<ExperienceResponseDto>>> getExperienceListByResumeId(int resumeId);

	ResponseEntity<ResponseStructure<ExperienceResponseDto>> getExperienceByExperienceId(int experienceId);

}
