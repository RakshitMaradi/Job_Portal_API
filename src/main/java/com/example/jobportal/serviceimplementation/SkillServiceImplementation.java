package com.example.jobportal.serviceimplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.Skill;
import com.example.jobportal.exception.ResumeNotFoundByIdException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SkillRepository;
import com.example.jobportal.requestdto.SkillRequestDto;
import com.example.jobportal.responsedto.SkillResponseDto;
import com.example.jobportal.service.SkillService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class SkillServiceImplementation implements SkillService{

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	ResumeRepository resumeRepository;

	public Skill convertToSkill(SkillRequestDto skillRequest)
	{
		Skill skill=new Skill();
		return skill;
	}

	public SkillRequestDto convertToRequest(Skill skill)
	{
		SkillRequestDto skillRequest=new SkillRequestDto();
		return skillRequest;
	}

	public SkillResponseDto convertToSkillResponse(Skill skill)
	{
		SkillResponseDto skillResponse=new SkillResponseDto();
		skillResponse.setSkillId(skill.getSkillId());
		skillResponse.setSkillName(skill.getSkillName());
		return skillResponse;
	}


	private Skill checkSkill(String skill)            // checks if the skill is present in database and adds if not present
	{
		Skill oldSkill = skillRepository.findBySkillName(skill);
		if(oldSkill==null)
		{
			Skill newSkill=new Skill();
			newSkill.setSkillName(skill);
			skillRepository.save(newSkill);
			return newSkill;
		}
		return oldSkill;
	}

	private List<Skill> convertToSkillList(SkillRequestDto skillRequest)// convert List<SkillRequestDto> to List<Skill> 
	{																    // Validates if the skill is already present before adding to list
		ArrayList<Skill> skillList=new ArrayList<>();
		String[] skills = skillRequest.getSkills();
		for(String sk:skills)
		{
			Skill skill=checkSkill(sk);
			skillList.add(skill);
		}
		return skillList;
	}

	private List<SkillResponseDto> convertToSkillResponseList(List<Skill> skillList)   // convert List<Skill> to List<SkillResponseDto>
	{
		List<SkillResponseDto> skillsResponseList=new ArrayList<>();
		for(Skill skill:skillList)
		{
			skillsResponseList.add(convertToSkillResponse(skill));
		}
		return skillsResponseList;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<SkillResponseDto>>> insertSkill(SkillRequestDto skillRequest,int resumeId) {

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));
		
			List<Skill> newSkillList = convertToSkillList(skillRequest);
			List<SkillResponseDto> skillResponseList = convertToSkillResponseList(newSkillList);
			
			List<Skill> existingSkillsList = resume.getSkillsList();
			existingSkillsList.addAll(newSkillList);
			resumeRepository.save(resume);
			
			ResponseStructure<List<SkillResponseDto>> responseStructure=new ResponseStructure<>();
			responseStructure.setData(skillResponseList);
			responseStructure.setMessage("Skills inserted successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<SkillResponseDto>>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<SkillResponseDto>>> getSkillsForResume(int resumeId) {

		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));
		
		List<Skill> skillsList = resume.getSkillsList();
		
		List<SkillResponseDto> skillResponseList = convertToSkillResponseList(skillsList);
		ResponseStructure<List<SkillResponseDto>> responseStructure=new ResponseStructure<>();
		responseStructure.setData(skillResponseList);
		responseStructure.setMessage("Skills found for resume successfully");
		responseStructure.setStatusCode(HttpStatus.FOUND.value());

		return new ResponseEntity<ResponseStructure<List<SkillResponseDto>>>(responseStructure, HttpStatus.FOUND);
		
	}

	@Override
	public ResponseEntity<ResponseStructure<List<SkillResponseDto>>> updateSkillsForResume(
			@Valid SkillRequestDto updatedSkillsRequest, int resumeId) {
		
		Resume resume = resumeRepository.findById(resumeId).orElseThrow(()
				->new ResumeNotFoundByIdException("Resume not found with id "+resumeId));
		
		List<Skill> updatedskillList = convertToSkillList(updatedSkillsRequest);   //
		resume.setSkillsList(updatedskillList);
		resumeRepository.save(resume);
		
		List<SkillResponseDto> skillResponseList = convertToSkillResponseList(updatedskillList);
		
		ResponseStructure<List<SkillResponseDto>> responseStructure=new ResponseStructure<>();
		responseStructure.setData(skillResponseList);
		responseStructure.setMessage("Skills updated for resumeid "+ resumeId +" successfully");
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<List<SkillResponseDto>>>(responseStructure, HttpStatus.OK);
	}
	
}
	
	

	

