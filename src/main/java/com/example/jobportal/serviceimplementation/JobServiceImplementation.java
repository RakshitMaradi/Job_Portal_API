package com.example.jobportal.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.User;
import com.example.jobportal.exception.CompanyNotFoundByIdException;
import com.example.jobportal.exception.JobNotFoundByIdException;
import com.example.jobportal.exception.JobNotFoundByLocationException;
import com.example.jobportal.exception.JobNotFoundByTitleException;
import com.example.jobportal.exception.UnauthorizedAccessByUserException;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.JobRequestDto;
import com.example.jobportal.responsedto.JobResponseDto;
import com.example.jobportal.service.JobService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class JobServiceImplementation implements JobService{

	@Autowired
	JobRepository jobRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyRepository companyRepository;

	private Job convertToJob(JobRequestDto jobRequest)
	{
		Job job =new Job();
		job.setCompany(jobRequest.getCompany());
		job.setJobExperienceRequired(jobRequest.getJobExperienceRequired());
		job.setJobLocation(jobRequest.getJobLocation());
		job.setJobPackage(jobRequest.getJobPackage());
		job.setJobSkills(jobRequest.getJobSkills());
		job.setJobTitle(jobRequest.getJobTitle());
		return job;
	}


	private JobResponseDto convertJobResponseDto(Job job)
	{
		JobResponseDto jobResponseDto=new JobResponseDto();
		jobResponseDto.setJobId(job.getJobId());
		jobResponseDto.setJobExperienceRequired(job.getJobExperienceRequired());
		jobResponseDto.setJobLocation(job.getJobLocation());
		jobResponseDto.setJobPackage(job.getJobPackage());
		jobResponseDto.setJobSkills(job.getJobSkills());
		jobResponseDto.setJobTitle(job.getJobTitle());

		return jobResponseDto;
	}

	@Override
	public ResponseEntity<ResponseStructure<JobResponseDto>> insertJob(@Valid JobRequestDto jobRequest,int companyId) 
	{

		Optional<Company> optional = companyRepository.findById(companyId);

		if(optional.isPresent())
		{	
			Company company = jobRequest.getCompany();

			Job job = convertToJob(jobRequest);
			jobRepository.save(job);
			JobResponseDto jobResponse = convertJobResponseDto(job);

			Map<String,String> companyOption=new HashMap<>();
			String url="/companies/"+company.getCompanyId();
			companyOption.put("companyDetails",url);

			jobResponse.setCompanyOptions(companyOption);

			ResponseStructure<JobResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(jobResponse);
			responseStructure.setMessage("Job inserted successfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());

			return new ResponseEntity<ResponseStructure<JobResponseDto>>(responseStructure, HttpStatus.CREATED);
		}
		else
		{
			throw new CompanyNotFoundByIdException("Company not found the with id"+companyId);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<JobResponseDto>> deleteJob(int jobId) {

		Optional<Job> optional = jobRepository.findById(jobId);

		if(optional.isPresent())
		{
			Job job = optional.get();
			jobRepository.delete(job);

			Company company = job.getCompany();
			JobResponseDto jobResponse = convertJobResponseDto(job);

			String url="/companies/"+company.getCompanyId();

			Map<String,String> companyOption=new HashMap<>();
			companyOption.put("companyDetails",url);

			jobResponse.setCompanyOptions(companyOption);

			ResponseStructure<JobResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(jobResponse);
			responseStructure.setMessage("Job deleted successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<JobResponseDto>>(responseStructure,HttpStatus.OK);

		}
		else
		{
			throw new JobNotFoundByIdException("Job not found with th id "+jobId);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<JobResponseDto>> findJobById(int jobId) {

		Optional<Job> optional = jobRepository.findById(jobId);

		if(optional.isPresent())
		{
			Job job = optional.get();

			Company company = job.getCompany();
			JobResponseDto jobResponse = convertJobResponseDto(job);

			String url="/companies/"+company.getCompanyId();

			Map<String,String> companyOption=new HashMap<>();
			companyOption.put("companyDetails",url);

			jobResponse.setCompanyOptions(companyOption);

			ResponseStructure<JobResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(jobResponse);
			responseStructure.setMessage("Job found successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<JobResponseDto>>(responseStructure,HttpStatus.OK);

		}
		else
		{
			throw new JobNotFoundByIdException("Job not found with th id "+jobId);
		}
	}


	@Override
	public ResponseEntity<ResponseStructure<JobResponseDto>> updateJobById(int jobId, int userId,
			@Valid JobRequestDto jobRequest) {

		Optional<User> optional = userRepository.findById(userId);

		if(optional.isPresent())
		{
			Optional<Job> optional2 = jobRepository.findById(jobId);
			Job existingJob = optional2.get();
			Job updatedJob = convertToJob(jobRequest);
			updatedJob.setJobId(existingJob.getJobId());

			Company company = updatedJob.getCompany();

			String url="/companies/"+company.getCompanyId();
			Map<String,String> companyOption=new HashMap<>();
			companyOption.put("companyDetails",url);

			JobResponseDto jobResponse = convertJobResponseDto(updatedJob);
			jobResponse.setCompanyOptions(companyOption);

			ResponseStructure<JobResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(jobResponse);
			responseStructure.setMessage("Job found successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<JobResponseDto>>(responseStructure,HttpStatus.OK);
		}
		else
		{
			throw new UnauthorizedAccessByUserException("Not allowed to access");
		}
	}


	@Override
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByName(String jobTitle) {

		List<Job> jobList = jobRepository.findByJobTitle(jobTitle);
		if(jobList!=null)
		{
			List<JobResponseDto> jobResponseList=new ArrayList<>();
			for(Job job:jobList)
			{
				Company company=job.getCompany();
				JobResponseDto jobResponse=convertJobResponseDto(job);

				String url="/companies/"+company.getCompanyId();
				Map<String,String> companyOption=new HashMap<>();
				companyOption.put("companyDetails",url);

				jobResponse.setCompanyOptions(companyOption);
				jobResponseList.add(jobResponse);
			}
			ResponseStructure<List<JobResponseDto>> responseStructure=new ResponseStructure<>();
			responseStructure.setData(jobResponseList);
			responseStructure.setMessage("Job found successfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());

			return new ResponseEntity<ResponseStructure<List<JobResponseDto>>>(responseStructure,HttpStatus.FOUND);
		}
		else
		{
			throw new JobNotFoundByTitleException("Job not found with the title"+jobTitle);
		}
	}


	@Override
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByLocation(String jobLocation) {

		List<Job> jobList = jobRepository.findByJobLocation(jobLocation);

		if(jobList!=null)
		{
			List<JobResponseDto> jobResponseList=new ArrayList<>();
			for(Job job:jobList)
			{
				Company company=job.getCompany();
				JobResponseDto jobResponse=convertJobResponseDto(job);

				String url="/companies/"+company.getCompanyId();
				Map<String,String> companyOption=new HashMap<>();
				companyOption.put("companyDetails",url);

				jobResponse.setCompanyOptions(companyOption);
				jobResponseList.add(jobResponse);

			}

			ResponseStructure<List<JobResponseDto>> responseStructure=new ResponseStructure<>();
			responseStructure.setData(jobResponseList);
			responseStructure.setMessage("Job found successfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());

			return new ResponseEntity<ResponseStructure<List<JobResponseDto>>>(responseStructure,HttpStatus.FOUND);
		}
		else
		{
			throw new JobNotFoundByLocationException("Job not found at the location"+jobLocation);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByPackage(long jobPackage) {

		List<Job> jobList = jobRepository.findByJobPackage(jobPackage);

		if(jobList!=null)
		{
			List<JobResponseDto> jobResponseList=new ArrayList<>();
			for(Job job:jobList)
			{
				Company company=job.getCompany();
				JobResponseDto jobResponse=convertJobResponseDto(job);

				String url="/companies/"+company.getCompanyId();
				Map<String,String> companyOption=new HashMap<>();
				companyOption.put("companyDetails",url);

				jobResponse.setCompanyOptions(companyOption);
				jobResponseList.add(jobResponse);
			}

			ResponseStructure<List<JobResponseDto>> responseStructure=new ResponseStructure<>();
			responseStructure.setData(jobResponseList);
			responseStructure.setMessage("Job found successfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());

			return new ResponseEntity<ResponseStructure<List<JobResponseDto>>>(responseStructure,HttpStatus.FOUND);
		}
		else
		{
			throw new JobNotFoundByLocationException("Job not found for the package of"+jobPackage);
		}
	}

}
