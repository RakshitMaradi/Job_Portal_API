package com.example.jobportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.requestdto.JobRequestDto;
import com.example.jobportal.responsedto.JobResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface JobService {

	ResponseEntity<ResponseStructure<JobResponseDto>> deleteJob(int jobId);

	ResponseEntity<ResponseStructure<JobResponseDto>> updateJobById(int jobId, int userId,
			@Valid JobRequestDto jobRequest);

	ResponseEntity<ResponseStructure<JobResponseDto>> insertJob(@Valid JobRequestDto jobRequest, int companyId);
	
	ResponseEntity<ResponseStructure<JobResponseDto>> findJobById(int jobId);

	ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByName(String jobTitle);

	ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByLocation(String jobLocation);

	ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByPackage(long jobPackage);
	
}
