package com.example.jobportal.serviceimplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.User;
import com.example.jobportal.exception.ResumeNotFoundByIdException;
import com.example.jobportal.exception.UnauthorizedAccessByUserException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.ResumeRequestDto;
import com.example.jobportal.responsedto.ResumeResponseDto;
import com.example.jobportal.service.ResumeService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class ResumeServiceImplementation implements ResumeService
{
	@Autowired
	UserRepository userRepository;

	@Autowired
	ResumeRepository resumeRepository;

	private Resume converToResume(ResumeRequestDto resumeRequest)
	{
		Resume resume=new Resume();
		resume.setObjective(resumeRequest.getObjective());
		return resume;
	}

	private ResumeResponseDto convertToResumeResponse(Resume resume)
	{
		ResumeResponseDto resumeResponse=new ResumeResponseDto();
		resumeResponse.setResumeId(resume.getResumeId());
		resumeResponse.setObjective(resume.getObjective());
		return resumeResponse;
	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> insertResume(@Valid ResumeRequestDto resumeRequest,
			int userId) {

		Optional<User> optional = userRepository.findById(userId);

		if(optional.isPresent())
		{
			Resume resume = converToResume(resumeRequest);
			resumeRepository.save(resume);
			ResumeResponseDto resumeResponse = convertToResumeResponse(resume);


			ResponseStructure<ResumeResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(resumeResponse);
			responseStructure.setMessage("Resume inserted successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<ResumeResponseDto>>(responseStructure, HttpStatus.OK);
		}
		else
		{
			throw new UnauthorizedAccessByUserException("No access for this operation");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> findResumeById(int resumeId) {

		Optional<Resume> optional = resumeRepository.findById(resumeId);
		if(optional.isPresent())
		{
			ResumeResponseDto resumeResponse = convertToResumeResponse(optional.get());

			ResponseStructure<ResumeResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(resumeResponse);
			responseStructure.setMessage("Resume found successfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());

			return new ResponseEntity<ResponseStructure<ResumeResponseDto>>(responseStructure,HttpStatus.FOUND);
		}
		else
		{
			throw new ResumeNotFoundByIdException("Resume not found with id "+resumeId);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> deleteResumeById(int resumeId) {

		Optional<Resume> optional = resumeRepository.findById(resumeId);
		if(optional.isPresent())
		{
			Resume resume = optional.get();
			resumeRepository.delete(resume);
			ResumeResponseDto resumeResponse = convertToResumeResponse(resume);

			ResponseStructure<ResumeResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(resumeResponse);
			responseStructure.setMessage("Resume deleted successfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());

			return new ResponseEntity<ResponseStructure<ResumeResponseDto>>(responseStructure,HttpStatus.FOUND);
		}
		else
		{
			throw new ResumeNotFoundByIdException("Resume  with id "+resumeId+ "not found to delete");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> updateResume(@Valid ResumeRequestDto resumeRequest,
			int userId) {

		Optional<User> optional = userRepository.findById(userId);

		if(optional.isPresent())
		{
			Resume resume = converToResume(resumeRequest);
			resumeRepository.save(resume);
			ResumeResponseDto resumeResponse = convertToResumeResponse(resume);


			ResponseStructure<ResumeResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(resumeResponse);
			responseStructure.setMessage("Resume inserted successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<ResumeResponseDto>>(responseStructure, HttpStatus.OK);
		}
		else
		{
			throw new UnauthorizedAccessByUserException("No access for this operation");
		}
	}

}
