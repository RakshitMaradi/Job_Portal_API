package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.EducationRequestDto;
import com.example.jobportal.responsedto.EducationResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface EducationService {

	ResponseEntity<ResponseStructure<EducationResponseDto>> insertEducation(@Valid EducationRequestDto educationRequest,
			int resumeId);

	ResponseEntity<ResponseStructure<EducationResponseDto>> updateEducation(@Valid EducationRequestDto educationRequest,
			int resumeId, int educationId);

	ResponseEntity<ResponseStructure<List<EducationResponseDto>>> getEducationListByResumeId(int resumeId);

	ResponseEntity<ResponseStructure<EducationResponseDto>> getEduationByEducationId(int educationId);

	ResponseEntity<ResponseStructure<EducationResponseDto>> deleteEduationByEducationId(int educationId);


}
