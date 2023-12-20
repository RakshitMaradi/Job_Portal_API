package com.example.jobportal.service;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.ResumeRequestDto;
import com.example.jobportal.responsedto.ResumeResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface ResumeService {

	ResponseEntity<ResponseStructure<ResumeResponseDto>> insertResume(@Valid ResumeRequestDto resumeRequest, int userId);

	ResponseEntity<ResponseStructure<ResumeResponseDto>> findResumeById(int resumeId, int userId);

	ResponseEntity<ResponseStructure<ResumeResponseDto>> deleteResumeById(int resumeId, int userId);

	ResponseEntity<ResponseStructure<ResumeResponseDto>> updateResume(@Valid ResumeRequestDto resumeRequest,
			int userId);

}
