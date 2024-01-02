package com.example.jobportal.serviceimplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Education;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.enums.DegreeType;
import com.example.jobportal.exception.DuplicateEducationException;
import com.example.jobportal.exception.ResumeNotFoundByIdException;
import com.example.jobportal.repository.EducationRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.requestdto.EducationRequestDto;
import com.example.jobportal.responsedto.EducationResponseDto;
import com.example.jobportal.service.EducationService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class EducationServiceImplementation implements EducationService
{
	@Autowired
	EducationRepository educationRepository;

	@Autowired
	ResumeRepository resumeRepository;

	private Education convertToEducation(EducationRequestDto educationRequest)
	{
		Education education=new Education();
		education.setInstituteName(educationRequest.getInstituteName());
		education.setDegreeType(educationRequest.getDegreeType());
		education.setDegreeName(educationRequest.getDegreeName());
		education.setFieldOfStudy(educationRequest.getFieldOfStudy());
		education.setStartDate(educationRequest.getStartDate());
		education.setEndDate(educationRequest.getEndDate());
		education.setPresent(checkIfPresent(educationRequest.getEndDate()));
		education.setMarks(educationRequest.getMarks());

		return education;
	}

	private EducationResponseDto convertToEducationResponse(Education education)
	{
		EducationResponseDto educationResponse=new EducationResponseDto();

		educationResponse.setInstituteName(education.getInstituteName());
		educationResponse.setDegreeType(education.getDegreeType());
		educationResponse.setDegreeName(education.getDegreeName());
		educationResponse.setFieldOfStudy(education.getFieldOfStudy());
		educationResponse.setStartDate(education.getStartDate());
		educationResponse.setEndDate(education.getEndDate());
		educationResponse.setPresent(education.isPresent());
		educationResponse.setMarks(education.getMarks());

		return educationResponse;
	}

	private List<EducationResponseDto> convertToEducationResponseList(List<Education> educationList)
	{
		List<EducationResponseDto> educationResponseList=new ArrayList<>();
		for(Education education:educationList)
		{
			educationResponseList.add(convertToEducationResponse(education));
		}
		return educationResponseList;
	}

	private boolean checkIfPresent(LocalDate endDate) {    // It returns true if endDate is null else false

		if(endDate==null)
		{
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<ResponseStructure<EducationResponseDto>> insertEducation(@Valid EducationRequestDto educationRequest, 
			int resumeId) 
	{
		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

		List<Education> existingEducationList = resume.getEducationList();   // To check if intermediate and matriculation 
		                                                                     //	are duplicating while inserting  
		for(Education education:existingEducationList)
		{
			if(educationRequest.getDegreeType().equals(DegreeType.METRICULATION) || 
					educationRequest.getDegreeType().equals(DegreeType.INTERMEDIATE))
			{
				if(education.getDegreeType().equals(educationRequest.getDegreeType()))
				{
					throw new DuplicateEducationException("You are entering this details again");
				}
			}
		}

		Education education = convertToEducation(educationRequest);
		education.setResume(resume);
		educationRepository.save(education);

		List<Education> educationList=new ArrayList<>();
		educationList.add(education);

		resume.setEducationList(educationList);
		resumeRepository.save(resume);

		EducationResponseDto educationResponse = convertToEducationResponse(education);

		ResponseStructure<EducationResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(educationResponse);
		responseStructure.setMessage("Education inserted successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<EducationResponseDto>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<EducationResponseDto>> updateEducation(
			@Valid EducationRequestDto educationRequest, int resumeId, int educationId) {

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

		List<Education> existingEducationList = resume.getEducationList();   // To check if intermediate and matriculation 
		                                                                     //	are duplicating while inserting  
		for(Education education:existingEducationList)
		{
			if(educationRequest.getDegreeType().equals(DegreeType.METRICULATION) || 
					educationRequest.getDegreeType().equals(DegreeType.INTERMEDIATE))
			{
				if(education.getDegreeType().equals(educationRequest.getDegreeType()))
				{
					throw new DuplicateEducationException("You are entering this details again");
				}
			}
		}

		Education existingEducation = educationRepository.findById(educationId).orElseThrow(()
				->new ResumeNotFoundByIdException("Education not found with id "+educationId));

		Education updatedEducation = convertToEducation(educationRequest);
		updatedEducation.setEducationId(existingEducation.getEducationId());
		updatedEducation.setResume(resume);

		educationRepository.save(updatedEducation);

		EducationResponseDto educationResponse = convertToEducationResponse(updatedEducation);

		ResponseStructure<EducationResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(educationResponse);
		responseStructure.setMessage("Education updated successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<EducationResponseDto>>(responseStructure, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponseStructure<List<EducationResponseDto>>> getEducationListByResumeId(int resumeId) {

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

		List<Education> educationList = resume.getEducationList();

		List<EducationResponseDto> educationResponseList = convertToEducationResponseList(educationList);

		ResponseStructure<List<EducationResponseDto>> responseStructure=new ResponseStructure<>();
		responseStructure.setData(educationResponseList);
		responseStructure.setMessage("Education updated successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<List<EducationResponseDto>>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<EducationResponseDto>> getEduationByEducationId(int educationId) {

		Education education = educationRepository.findById(educationId).orElseThrow(()
				->new ResumeNotFoundByIdException("Education not found with id "+educationId));

		EducationResponseDto educationResponse = convertToEducationResponse(education);

		ResponseStructure<EducationResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(educationResponse);
		responseStructure.setMessage("Education found successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<EducationResponseDto>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<EducationResponseDto>> deleteEduationByEducationId(int educationId) {

		Education education = educationRepository.findById(educationId).orElseThrow(()
				->new ResumeNotFoundByIdException("Education not found with id "+educationId));

		educationRepository.delete(education);

		EducationResponseDto educationResponse = convertToEducationResponse(education);

		ResponseStructure<EducationResponseDto> responseStructure=new ResponseStructure<>();
		responseStructure.setData(educationResponse);
		responseStructure.setMessage("Education found successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<EducationResponseDto>>(responseStructure, HttpStatus.OK);
	}

}
