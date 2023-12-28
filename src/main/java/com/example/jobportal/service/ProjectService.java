package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.ProjectRequestDto;
import com.example.jobportal.responsedto.ProjectResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface ProjectService {

	ResponseEntity<ResponseStructure<ProjectResponseDto>> insertProject(@Valid ProjectRequestDto projectRequest,
			int resumeId);

	ResponseEntity<ResponseStructure<List<ProjectResponseDto>>> getProjectsByResumeId(int resumeId);

	ResponseEntity<ResponseStructure<ProjectResponseDto>> getProjectByProjectId(int projectId);

	ResponseEntity<ResponseStructure<ProjectResponseDto>> updateProjectByResumeId(
			@Valid ProjectRequestDto projectRequest, int projectId);

	ResponseEntity<ResponseStructure<ProjectResponseDto>> deleteProjectByResumeId(int resumeId, int projectId);
	
	

}
