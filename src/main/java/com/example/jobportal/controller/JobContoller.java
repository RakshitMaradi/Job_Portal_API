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
	
	@PostMapping("/jobs/companies/{companyId}")
	public ResponseEntity<ResponseStructure<JobResponseDto>> insert(@PathVariable int companyId,
			@RequestBody @Valid JobRequestDto jobRequest)
	{
		return jobService.insertJob(jobRequest,companyId);
	}

	@PutMapping("/users/{userId}/jobs/{jobId}")
	public ResponseEntity<ResponseStructure<JobResponseDto>> update(@RequestBody @Valid JobRequestDto jobRequest,
			@PathVariable int jobId,@PathVariable int userId)
	{
		return jobService.updateJobById(jobId,userId,jobRequest);
	} 
	
	@DeleteMapping("/jobs/{jobId}")
	public ResponseEntity<ResponseStructure<JobResponseDto>> delete(@PathVariable int jobId)
	{
		return jobService.deleteJob(jobId);
	} 
	
	@GetMapping("/jobs/{jobId}")
	public ResponseEntity<ResponseStructure<JobResponseDto>> findByid(@PathVariable int jobId)
	{
		return jobService.findJobById(jobId);
	} 
	

	@GetMapping("/jobs/{jobTitle}")
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findByName(@PathVariable String jobTitle)
	{
		return jobService.findJobByName(jobTitle);
	} 
	
	@GetMapping("/jobs/{jobLocation}")
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findByLocation(@PathVariable String jobLocation)
	{
		return jobService.findJobByLocation(jobLocation);
	} 
	
	@GetMapping("/jobs/{jobPackage}")
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findByPackage(@PathVariable long jobPackage)
	{
		return jobService.findJobByPackage(jobPackage);
	}
	
}
