package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.JobRequestDto;
import com.example.jobportal.responsedto.JobResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface JobService {

	ResponseEntity<ResponseStructure<JobResponseDto>> updateJobById(int jobId, int userId,
			@Valid JobRequestDto jobRequest);

	ResponseEntity<ResponseStructure<JobResponseDto>> findJobById(int jobId);

	ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByTitle(String jobTitle);

	ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByLocation(String jobLocation);

	ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByPackage(String jobPackage);

	ResponseEntity<ResponseStructure<JobResponseDto>> deleteJob(int jobId, int userId);

	ResponseEntity<ResponseStructure<JobResponseDto>> insertJob(@Valid JobRequestDto jobRequest, int companyId,
			int userId);
	
}
