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

import com.example.jobportal.requestdto.JobRequestDto;
import com.example.jobportal.responsedto.JobResponseDto;
import com.example.jobportal.service.JobService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class JobContoller {
	
	@Autowired
	JobService jobService;
	
	@PostMapping("/users/{userId}/jobs/companies/{companyId}")
	public ResponseEntity<ResponseStructure<JobResponseDto>> insert(@PathVariable int companyId,@PathVariable int userId,
			@RequestBody @Valid JobRequestDto jobRequest)
	{
		return jobService.insertJob(jobRequest,companyId,userId);
	}

	@PutMapping("/users/{userId}/jobs/{jobId}")
	public ResponseEntity<ResponseStructure<JobResponseDto>> update(@RequestBody @Valid JobRequestDto jobRequest,
			@PathVariable int jobId,@PathVariable int userId)
	{
		return jobService.updateJobById(jobId,userId,jobRequest);
	} 
	
	@DeleteMapping("users/{userId}/jobs/{jobId}")
	public ResponseEntity<ResponseStructure<JobResponseDto>> delete(@PathVariable int jobId,@PathVariable int userId)
	{
		return jobService.deleteJob(jobId,userId);
	} 
	
	@GetMapping("/companies/jobs/{jobId}")
	public ResponseEntity<ResponseStructure<JobResponseDto>> findByid(@PathVariable int jobId)
	{
		return jobService.findJobById(jobId);
	} 

	@GetMapping("/jobs/job-Title/{jobTitle}")
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findByTitle(@PathVariable String jobTitle)
	{
		return jobService.findJobByTitle(jobTitle);
	} 
	
	@GetMapping("/jobs/job-Location/{jobLocation}")
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findByLocation(@PathVariable String jobLocation)
	{
		return jobService.findJobByLocation(jobLocation);
	} 
	
	@GetMapping("/jobs/job-Package/{jobPackage}")
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findByPackage(@PathVariable String jobPackage)
	{
		return jobService.findJobByPackage(jobPackage);
	}
	
}
