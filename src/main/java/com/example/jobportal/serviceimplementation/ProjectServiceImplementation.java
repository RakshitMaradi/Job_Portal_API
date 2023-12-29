package com.example.jobportal.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Project;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.exception.ProjectNotFoundByIdException;
import com.example.jobportal.exception.ResumeNotFoundByIdException;
import com.example.jobportal.repository.ProjectRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.requestdto.ProjectRequestDto;
import com.example.jobportal.responsedto.ProjectResponseDto;
import com.example.jobportal.service.ProjectService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class ProjectServiceImplementation implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ResumeRepository resumeRepository;

	private Project convertToProject(ProjectRequestDto projectRequest)
	{
		Project project=new Project();
		project.setProjectName(projectRequest.getProjectName());
		project.setTechStack(convertingTocaseInsensitiveSet(projectRequest.getTechStack()));
		project.setDescription(projectRequest.getDescription());
		project.setWebsite(projectRequest.getWebsite());
		project.setSourceCode(projectRequest.getSourceCode());
		return project;
	}

	private ProjectResponseDto convertToProjectResponse(Project project)
	{
		ProjectResponseDto projectResponse=new ProjectResponseDto();
		projectResponse.setProjectId(project.getProjectId());
		projectResponse.setProjectName(project.getProjectName());
		projectResponse.setTechStack(convertToString(project.getTechStack()));
		projectResponse.setDescription(project.getDescription());
		projectResponse.setWebsite(project.getWebsite());
		projectResponse.setSourceCode(project.getSourceCode());
		return projectResponse;
	}

	private Set<String> convertingTocaseInsensitiveSet(Set<String> techStack)      // converting Array of Strings to Set<String> 
	{													      // Stored in a case insensitive order
															  // Set is chose to avoid repetition 	
		Set<String> teckSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		for(String tech:techStack)
		{
			teckSet.add(tech);
		}
		return teckSet;
	}

	private String convertToString(Set<String> techStack)    // converting Set<String> to String of technologies separated by comma
	{
		String technolgies="";
		for(String tech:techStack)
		{
			if(technolgies!="")
			{
			   technolgies=technolgies+","+tech;
			}
			else
			{
				technolgies=tech;
			}	
		}
		return technolgies;
	}

	private List<ProjectResponseDto> convertToProjectResponseList(List<Project> projectList)  
	{
	   //converting List<Project> to List<ProjectResponseDto>	
		 List<ProjectResponseDto> projectResponseList=new ArrayList<>();
		for(Project project:projectList)
		{
			projectResponseList.add(convertToProjectResponse(project));
		}
		return projectResponseList;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<ProjectResponseDto>> insertProject(@Valid ProjectRequestDto projectRequest,
			int resumeId) {

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

		Project project = convertToProject(projectRequest);

		project.setResume(resume);
		projectRepository.save(project);

		List<Project> projectList=new ArrayList<>();      //  creating a project list and adding the current project into it
		projectList.add(project);

		resume.setProjectList(projectList);               //  setting projectList and persisting the resume object
		resumeRepository.save(resume);

		ProjectResponseDto projectResponse = convertToProjectResponse(project);

		ResponseStructure<ProjectResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(projectResponse);
		responseStructure.setMessage("Project inserted successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<ProjectResponseDto>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ProjectResponseDto>>> getProjectsByResumeId(int resumeId) {

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

		List<Project> projectList = resume.getProjectList();
		
		List<ProjectResponseDto> projectResponseList = convertToProjectResponseList(projectList);
		
		ResponseStructure<List<ProjectResponseDto>> responseStructure=new ResponseStructure<>();
		responseStructure.setData(projectResponseList);
		responseStructure.setMessage("Projects found successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<List<ProjectResponseDto>>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<ProjectResponseDto>> updateProjectByResumeId(
			@Valid ProjectRequestDto projectRequest,int projectId) {

		Project project = projectRepository.findById(projectId).orElseThrow(()
				->new ProjectNotFoundByIdException("Project not found with id "+projectId));
		
		Project updatedProject=convertToProject(projectRequest);
		
		updatedProject.setProjectId(project.getProjectId());
		projectRepository.save(updatedProject);
		
		ProjectResponseDto projectResponse = convertToProjectResponse(updatedProject);
		
		ResponseStructure<ProjectResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(projectResponse);
		responseStructure.setMessage("Projects found successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<ProjectResponseDto>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<ProjectResponseDto>> deleteProjectByResumeId(int resumeId, int projectId) {

		
		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));
		
		Project project = projectRepository.findById(projectId).orElseThrow(()
				->new ProjectNotFoundByIdException("Project not found with id "+projectId));
		
		
		projectRepository.delete(project);
		
        ProjectResponseDto projectResponse = convertToProjectResponse(project);
		
		ResponseStructure<ProjectResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(projectResponse);
		responseStructure.setMessage("Projects found successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<ProjectResponseDto>>(responseStructure, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<ResponseStructure<ProjectResponseDto>> getProjectByProjectId(int projectId) {

		Project project = projectRepository.findById(projectId).orElseThrow(()
				->new ProjectNotFoundByIdException("Project not found with id "+projectId)); 
		 
        ProjectResponseDto projectResponse = convertToProjectResponse(project);
		
		ResponseStructure<ProjectResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(projectResponse);
		responseStructure.setMessage("Projects found successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<ProjectResponseDto>>(responseStructure, HttpStatus.OK);
	}


}
