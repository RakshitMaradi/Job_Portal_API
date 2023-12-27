package com.example.jobportal.service;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.ProjectRequestDto;
import com.example.jobportal.responsedto.ProjectResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface ProjectService {

	ResponseEntity<ResponseStructure<ProjectResponseDto>> insertProject(@Valid ProjectRequestDto projectRequest,
			int resumeId);
	
	

}
