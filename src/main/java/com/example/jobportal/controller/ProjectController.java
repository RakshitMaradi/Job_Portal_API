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

import com.example.jobportal.requestdto.ProjectRequestDto;
import com.example.jobportal.responsedto.ProjectResponseDto;
import com.example.jobportal.service.ProjectService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@PostMapping("/resumes/{resumeId}/projects")
	public ResponseEntity<ResponseStructure<ProjectResponseDto>> addProject(@RequestBody @Valid ProjectRequestDto projectRequest, 
			@PathVariable int resumeId)
	{
		return projectService.insertProject(projectRequest,resumeId);
	}
	
	@GetMapping("/resumes/{resumeId}/projects")
	public ResponseEntity<ResponseStructure<List<ProjectResponseDto>>> getProjectsByResumeId(@PathVariable int resumeId) 
	{
		return projectService.getProjectsByResumeId(resumeId);
	}
	
	@GetMapping("resumes/projects/{projectId}")
	public ResponseEntity<ResponseStructure<ProjectResponseDto>> getProjectsByProjectId(@PathVariable int projectId) 
	{
		return projectService.getProjectByProjectId(projectId);
	}
	

	@PutMapping("/resumes/{resumeId}/projects/{projectId}")
	public ResponseEntity<ResponseStructure<ProjectResponseDto>> updateProjectByResumeId(@RequestBody @Valid  ProjectRequestDto projectRequest,
			@PathVariable int resumeId,@PathVariable int projectId)
	{
		return projectService.updateProjectByResumeId(projectRequest,projectId);
	}
	
	@DeleteMapping("/resumes/{resumeId}/projects/{projectId}")
	public ResponseEntity<ResponseStructure<ProjectResponseDto>> deleteProjectByResumeId(@PathVariable int resumeId,@PathVariable int projectId)
	{
		return projectService.deleteProjectByResumeId(resumeId,projectId);
	}
	
	
	
}
