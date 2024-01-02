package com.example.jobportal.serviceimplementation;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Experience;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.exception.ExperienceNotFoundByIdException;
import com.example.jobportal.exception.ResumeNotFoundByIdException;
import com.example.jobportal.repository.ExperienceRepositoy;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.requestdto.ExperienceRequestDto;
import com.example.jobportal.responsedto.ExperienceResponseDto;
import com.example.jobportal.service.ExperienceService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class ExperienceServiceImplementation implements ExperienceService{

	@Autowired
	ExperienceRepositoy experienceRepositoy;

	@Autowired
	ResumeRepository resumeRepository;

	private Experience convertToExperience(ExperienceRequestDto experienceRequest)
	{
		Experience experience=new Experience();
		experience.setCompanyName(experienceRequest.getCompanyName());
		experience.setDesignation(experienceRequest.getDesignation());
		experience.setStartDate(experienceRequest.getStartDate());
		experience.setEndDate(experienceRequest.getEndDate());
		experience.setPresent(checkIfPresent(experienceRequest.getEndDate()));
		experience.setDescription(experienceRequest.getDescription());
		if(experienceRequest.getEndDate()!=null)
		{
			experience.setExperience(convertToYearAndMonth(experienceRequest.getStartDate(),experienceRequest.getEndDate()));
		}
		else 
		{
			experience.setExperience("Still working");
		}
		return experience;
	}

	public String convertToYearAndMonth(LocalDate startDate, LocalDate endDate)    // to convert to year and month   
	{
		if (startDate.isAfter(endDate)) {
			throw new IllegalArgumentException("Start date cannot be after end date");
		}


		Period period=Period.between(startDate, endDate);

		int years = period.getYears();
		int months = period.getMonths();

		if(years==0)
		{
			return months+" months ";
		}
		else
		{
			return years+" years "+months+" months ";
		}
	}
	
	private boolean checkIfPresent(LocalDate endDate) {    // It returns true if endDate is null else false

		if(endDate==null)
		{
			return true;
		}
		return false;
	}

	private ExperienceResponseDto convertToExperienceResponse(Experience experience)
	{
		ExperienceResponseDto experienceResponse=new ExperienceResponseDto();
		experienceResponse.setExperienceId(experience.getExperienceId());
		experienceResponse.setCompanyName(experience.getCompanyName());
		experienceResponse.setDesignation(experience.getDesignation());
		experienceResponse.setStartDate(experience.getStartDate());
		experienceResponse.setEndDate(experience.getEndDate());
		experienceResponse.setPresent(experience.isPresent());
		experienceResponse.setDescription(experience.getDescription());
		experienceResponse.setExperience(experience.getExperience());

		return experienceResponse;
	}

	private List<ExperienceResponseDto> convertToExperienceResponse(List<Experience> experiencesList)
	{
		List<ExperienceResponseDto> experienceResponseList=new ArrayList<>();
		for(Experience experience:experiencesList)
		{
			experienceResponseList.add(convertToExperienceResponse(experience));
		}
		return experienceResponseList;
	}

	@Override
	public ResponseEntity<ResponseStructure<ExperienceResponseDto>> insertExperience(
			@Valid ExperienceRequestDto experienceRequest, int resumeId) {

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

		Experience experience = convertToExperience(experienceRequest);
		experience.setResume(resume);
		experienceRepositoy.save(experience);

		List<Experience> experienceList=new ArrayList<>();      //  creating a experience list and adding the current project into it
		experienceList.add(experience);

		resume.setExperienceList(experienceList);               //  setting  experienceList and persisting the resume object
		resumeRepository.save(resume);

		ExperienceResponseDto experienceResponse = convertToExperienceResponse(experience);

		ResponseStructure<ExperienceResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(experienceResponse);
		responseStructure.setMessage("Experience inserted successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<ExperienceResponseDto>>(responseStructure, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponseStructure<List<ExperienceResponseDto>>> getExperienceListByResumeId(int resumeId) {

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

		List<Experience> experienceList = resume.getExperienceList();

		List<ExperienceResponseDto> experienceResponseList=convertToExperienceResponse(experienceList);

		ResponseStructure<List<ExperienceResponseDto>> responseStructure=new ResponseStructure<>();
		responseStructure.setData(experienceResponseList);
		responseStructure.setMessage("Experiences found successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<List<ExperienceResponseDto>>>(responseStructure, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponseStructure<ExperienceResponseDto>> getExperienceByExperienceId(int experienceId) {

		Experience experience = experienceRepositoy.findById(experienceId).orElseThrow(()
				->new ExperienceNotFoundByIdException("Experience not found with id "+experienceId));

		ExperienceResponseDto experienceResponse = convertToExperienceResponse(experience);

		ResponseStructure<ExperienceResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(experienceResponse);
		responseStructure.setMessage("Experience found successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<ExperienceResponseDto>>(responseStructure, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponseStructure<ExperienceResponseDto>> updateExperienceByExperienceId(@Valid ExperienceRequestDto experienceRequest,
			int experienceId,int resumeId) {

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

		Experience existingExperience = experienceRepositoy.findById(experienceId).orElseThrow(()
				->new ExperienceNotFoundByIdException("Experience not found with id "+experienceId));

		Experience updatedExperience = convertToExperience(experienceRequest);
		updatedExperience.setResume(resume);                                        // setting resume
		updatedExperience.setExperienceId(existingExperience.getExperienceId());   // setting resumeId
		
		experienceRepositoy.save(updatedExperience);          // persisting the updatedExperience object

		ExperienceResponseDto experienceResponse = convertToExperienceResponse(updatedExperience);

		ResponseStructure<ExperienceResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(experienceResponse);
		responseStructure.setMessage("Experience updated successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<ExperienceResponseDto>>(responseStructure, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponseStructure<ExperienceResponseDto>> deleteExperienceByExperienceId(int experienceId) {
		
		Experience experience = experienceRepositoy.findById(experienceId).orElseThrow(()
				->new ExperienceNotFoundByIdException("Experience not found with id "+experienceId));

		experienceRepositoy.delete(experience);
		
		ExperienceResponseDto experienceResponse = convertToExperienceResponse(experience);

		ResponseStructure<ExperienceResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(experienceResponse);
		responseStructure.setMessage("Experience deleted successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<ExperienceResponseDto>>(responseStructure, HttpStatus.OK);
		
	}
}
