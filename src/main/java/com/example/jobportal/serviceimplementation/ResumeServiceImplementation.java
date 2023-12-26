package com.example.jobportal.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.Skill;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exception.ResumeNotFoundByIdException;
import com.example.jobportal.exception.ResumesNotFoundBySkillException;
import com.example.jobportal.exception.UnauthorizedAccessByUserException;
import com.example.jobportal.exception.UserNotFoundByIdException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SkillRepository;
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

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	SkillServiceImplementation skillServiceImplementation;

	private Resume convertToResume(ResumeRequestDto resumeRequest)
	{
		Resume resume=new Resume();
		resume.setName(resumeRequest.getName());
		resume.setObjective(resumeRequest.getObjective());
		return resume;
	}

	private ResumeResponseDto convertToResumeResponse(Resume resume,int userId)
	{
//		Optional<User>  optional= userRepository.findById(userId);
//		String username = optional.get().getUsername();
		ResumeResponseDto resumeResponse=new ResumeResponseDto();
		resumeResponse.setResumeId(resume.getResumeId());
		resumeResponse.setUsername(resume.getName());
		resumeResponse.setObjective(resume.getObjective());
		return resumeResponse;
	}

	private ResumeResponseDto convertToResumeResponse(Resume resume)
	{
		ResumeResponseDto resumeResponse=new ResumeResponseDto();
		resumeResponse.setResumeId(resume.getResumeId());
		resumeResponse.setObjective(resume.getObjective());
		return resumeResponse;
	}

	private List<ResumeResponseDto> convertToResumeResponseList(List<Resume> resumeList)
	{
		List<ResumeResponseDto> resumeResponseList=new ArrayList<>();

		for(Resume resume:resumeList)
		{
			String url="/resumes/"+resume.getResumeId()+"/get-skills/skills";
			Map<String,String> skillsOption=new HashMap<>();
			skillsOption.put("skillsOption",url);

			ResumeResponseDto resumeResponse = convertToResumeResponse(resume);
			resumeResponse.setSkillsListOption(skillsOption);
			resumeResponseList.add(resumeResponse);
		}
		return resumeResponseList;
	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> insertResume(@Valid ResumeRequestDto resumeRequest,
			int userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));

		if(user.getUserrole().equals(UserRole.APPLICANT))
		{

			Resume resume = convertToResume(resumeRequest);
			resume.setUser(user);
			resumeRepository.save(resume);
			ResumeResponseDto resumeResponse = convertToResumeResponse(resume,userId);

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
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> findResumeById(int resumeId,int userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));

		if(user.getUserrole().equals(UserRole.APPLICANT))
		{
			Resume resume = resumeRepository.findById(resumeId).orElseThrow(()->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

			String url="/resumes/"+resumeId+"/get-skills/skills";
			Map<String,String> skillsOption=new HashMap<>();
			skillsOption.put("skillsOption",url);

			ResumeResponseDto resumeResponse = convertToResumeResponse(resume,userId);
			resumeResponse.setSkillsListOption(skillsOption);

			ResponseStructure<ResumeResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(resumeResponse);
			responseStructure.setMessage("Resume found successfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());

			return new ResponseEntity<ResponseStructure<ResumeResponseDto>>(responseStructure,HttpStatus.FOUND);
		}
		else
		{
			throw new UnauthorizedAccessByUserException("Not allowed to access");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> deleteResumeById(int resumeId,int userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));

		if(user.getUserrole().equals(UserRole.APPLICANT))
		{
			Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
					->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

			ResumeResponseDto resumeResponse = convertToResumeResponse(resume,userId);

			ResponseStructure<ResumeResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(resumeResponse);
			responseStructure.setMessage("Resume deleted successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<ResumeResponseDto>>(responseStructure,HttpStatus.OK);
		}
		else
		{
			throw new UnauthorizedAccessByUserException("Not allowed to access");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> updateResume(@Valid ResumeRequestDto resumeRequest,
			int userId,int resumeId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));

		if(user.getUserrole().equals(UserRole.APPLICANT))
		{
			Resume existingResume = resumeRepository.findById(resumeId)
					.orElseThrow(()->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));

			Resume updatedResume = convertToResume(resumeRequest);
			updatedResume.setResumeId(existingResume.getResumeId());
			updatedResume.setSkillsList(existingResume.getSkillsList());

			resumeRepository.save(updatedResume);
			ResumeResponseDto resumeResponse = convertToResumeResponse(updatedResume,userId);

			String url="/resumes/"+resumeId+"/get-skills/skills";
			Map<String,String> skillsOption=new HashMap<>();
			skillsOption.put("skillsOption",url);

			resumeResponse.setSkillsListOption(skillsOption);

			ResponseStructure<ResumeResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(resumeResponse);
			responseStructure.setMessage("Resume updated successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<ResumeResponseDto>>(responseStructure, HttpStatus.OK);
		}
		else
		{
			throw new UnauthorizedAccessByUserException("No access for this operation");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ResumeResponseDto>>> findResumeBySkill(int userId, String skillName) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));

		if(user.getUserrole().equals(UserRole.EMPLOYER))
		{
			List<Resume> skilledList=new ArrayList<>();

			List<Resume> resumeList = resumeRepository.findAll();
			if(!resumeList.isEmpty())
			{
				for(Resume resume:resumeList)
				{
					List<Skill> skillsList = resume.getSkillsList();
					for(Skill skill:skillsList)
					{
						if(skill.getSkillName().equals(skillName.toLowerCase()))
						{
							skilledList.add(resume);
						}
					}
				}
			}
			else
			{
				throw new ResumesNotFoundBySkillException("Resumes not found with the given skill "+skillName);
			}

			List<ResumeResponseDto> resumeResponseList = convertToResumeResponseList(skilledList);

			ResponseStructure<List<ResumeResponseDto>> responseStructure=new ResponseStructure<>();
			responseStructure.setData(resumeResponseList);
			responseStructure.setMessage("Resumes found successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<ResumeResponseDto>>>(responseStructure, HttpStatus.OK);
		}
		else
		{
			throw new UnauthorizedAccessByUserException("Not allowed to access");
		}

	}
}
