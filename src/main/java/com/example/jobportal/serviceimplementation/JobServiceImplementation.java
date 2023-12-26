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
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exception.CompanyNotFoundByIdException;
import com.example.jobportal.exception.JobNotFoundByIdException;
import com.example.jobportal.exception.JobNotFoundByLocationException;
import com.example.jobportal.exception.JobNotFoundByTitleException;
import com.example.jobportal.exception.UnauthorizedAccessByUserException;
import com.example.jobportal.exception.UserNotFoundByIdException;
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
		job.setJobTitle(jobRequest.getJobTitle());
		job.setJobStatus(jobRequest.getJobStatus());
		return job;
	}


	private JobResponseDto convertJobResponseDto(Job job)
	{
		JobResponseDto jobResponseDto=new JobResponseDto();
		jobResponseDto.setJobId(job.getJobId());
		jobResponseDto.setJobExperienceRequired(job.getJobExperienceRequired());
		jobResponseDto.setJobLocation(job.getJobLocation());
		jobResponseDto.setJobPackage(job.getJobPackage());
		jobResponseDto.setJobTitle(job.getJobTitle());
		jobResponseDto.setJobStatus(job.getJobStatus());

		return jobResponseDto;
	}

	@Override
	public ResponseEntity<ResponseStructure<JobResponseDto>> insertJob(@Valid JobRequestDto jobRequest,int companyId,int userId) 
	{

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));

		if(user.getUserrole().equals(UserRole.EMPLOYER))
		{

			Company company = companyRepository.findById(companyId)
					.orElseThrow(() -> new CompanyNotFoundByIdException("No company present with the id " + companyId));

			Job job = convertToJob(jobRequest);
			job.setCompany(company);          ///////
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
			throw new UnauthorizedAccessByUserException("Not allowed to access");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<JobResponseDto>> deleteJob(int jobId,int userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));

		if(user.getUserrole().equals(UserRole.EMPLOYER))
		{
			Job job = jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundByIdException("Job not found with th id "+jobId));
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
			throw new UnauthorizedAccessByUserException("Not allowed to access");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<JobResponseDto>> updateJobById(int jobId, int userId,
			@Valid JobRequestDto jobRequest) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));

		if(user.getUserrole().equals(UserRole.EMPLOYER))
		{
			Job existingJob = jobRepository.findById(jobId)
					.orElseThrow(() -> new JobNotFoundByIdException("Job not found with th id "+jobId));
			Job updatedJob = convertToJob(jobRequest);
			updatedJob.setJobId(existingJob.getJobId());
			updatedJob.setCompany(existingJob.getCompany());
			jobRepository.save(updatedJob);

			String url="/companies/"+existingJob.getCompany().getCompanyId();
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
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByTitle(String jobTitle) {

		List<Job> jobList = jobRepository.findByJobTitle(jobTitle);

		if (!jobList.isEmpty()) {
			List<JobResponseDto> jobResponseList = new ArrayList<>();

			for (Job job : jobList) {
				Company company = job.getCompany();
				JobResponseDto jobResponse = convertJobResponseDto(job);

				String url = "/companies/" + company.getCompanyId();
				Map<String, String> companyOption = new HashMap<>();
				companyOption.put("companyDetails", url);

				jobResponse.setCompanyOptions(companyOption);
				jobResponseList.add(jobResponse);
			}

			ResponseStructure<List<JobResponseDto>> responseStructure = new ResponseStructure<>();
			responseStructure.setData(jobResponseList);
			responseStructure.setMessage("Job found successfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());

			return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new JobNotFoundByTitleException("Job not found with the title " + jobTitle);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByLocation(String jobLocation) {

		List<Job> jobList = jobRepository.findByJobLocation(jobLocation);

		if(!jobList.isEmpty())
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
			throw new JobNotFoundByLocationException("Job not found at the location "+jobLocation);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByPackage(String jobPackage) {

		List<Job> jobList = jobRepository.findByJobPackage(jobPackage);

		if(!jobList.isEmpty())
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
			throw new JobNotFoundByLocationException("Job not found for the package of "+jobPackage);
		}
	}
}
