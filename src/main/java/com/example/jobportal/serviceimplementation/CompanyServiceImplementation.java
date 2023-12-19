package com.example.jobportal.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.exception.BusinessTypeNotPresentException;
import com.example.jobportal.exception.CompaniesNotPresentException;
import com.example.jobportal.exception.CompanyNotFoundByIdException;
import com.example.jobportal.exception.CompanyNotFoundByNameException;
import com.example.jobportal.exception.UnauthorizedAccessByUserException;
import com.example.jobportal.exception.UserNotFoundByIdException;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.service.CompanyService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class CompanyServiceImplementation implements CompanyService
{
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	UserRepository userRepository;
	
	private Company convertToCompany(CompanyRequestDto companyRequest,BusinessType businessType)
	{
		Company company=new Company();
		company.setBusinessType(businessType);
		company.setCompanyName(companyRequest.getCompanyName());
		company.setContactEmail(companyRequest.getContactEmail());
		company.setContactPhNo(companyRequest.getContactPhNo());
		company.setFoundedDate(companyRequest.getFoundedDate());
		company.setDescription(companyRequest.getDescription());
		company.setWebsite(companyRequest.getWebsite());

		return company;
	}

	private CompanyResponseDto convertToCompanyResponseDto(Company company)
	{
		CompanyResponseDto companyResponseDto=new CompanyResponseDto();
		companyResponseDto.setBusinessType(company.getBusinessType());
		companyResponseDto.setCompanyId(company.getCompanyId());
		companyResponseDto.setCompanyName(company.getCompanyName());
		companyResponseDto.setContactEmail(company.getContactEmail());
		companyResponseDto.setContactPhNo(company.getContactPhNo());
		companyResponseDto.setDescription(company.getDescription());
		companyResponseDto.setWebsite(company.getWebsite());

		return companyResponseDto;
	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> insertCompany(
			@Valid CompanyRequestDto companyRequest, int userId, BusinessType businessType)
	{
		Optional<User> optional = userRepository.findById(userId);
		User user = optional.get();
		if(optional.isPresent())
		{
			if(user.getUserrole()!=null)
			{
				if(businessType!=null)
				{
					Company company = companyRepository.save(convertToCompany(companyRequest, businessType));
					CompanyResponseDto companyResponse = convertToCompanyResponseDto(company);
					ResponseStructure<CompanyResponseDto> responseStructure=new ResponseStructure<>();
					responseStructure.setData(companyResponse);
					responseStructure.setMessage("Company inserted successfully");
					responseStructure.setStatusCode(HttpStatus.CREATED.value());

					return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(responseStructure, HttpStatus.CREATED);
				}
				else
				{
					throw new BusinessTypeNotPresentException("No such Business type exist");
				}
			}
			else
			{
				throw new UnauthorizedAccessByUserException("Not authorised to access this option");			
			}
		}
		else
		{
			throw new UserNotFoundByIdException("User with this id is not present");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompanyById(int companyId) {

		Optional<Company> optional = companyRepository.findById(companyId);

		if(optional.isPresent())
		{
			Company company = optional.get();
			CompanyResponseDto companyResponse = convertToCompanyResponseDto(company);
			ResponseStructure<CompanyResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(companyResponse);
			responseStructure.setMessage("Company found successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(responseStructure, HttpStatus.OK);
		}
		else
		{
			throw new CompanyNotFoundByIdException("No company present with the is"+companyId);
		}
	}


	@Override
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompanyByName(String companyName) {

	    List<Company> companyList = companyRepository.findAll(); 
	    
	    if(companyList!=null)
	    {
	    	for(Company company:companyList)
	    	{
	    		if(company.getCompanyName().equalsIgnoreCase(companyName))
	    		{
	    			CompanyResponseDto companyResponse = convertToCompanyResponseDto(company);
	    			ResponseStructure<CompanyResponseDto> responseStructure=new ResponseStructure<>();
	    			responseStructure.setData(companyResponse);
	    			responseStructure.setMessage("Company found successfully");
	    			responseStructure.setStatusCode(HttpStatus.OK.value());

	    			return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(responseStructure, HttpStatus.OK);
	    		}
	    	}
	    	throw new CompanyNotFoundByNameException("No company found with this name");
	    }
	    else
	    {
	    	throw new CompaniesNotPresentException("Companies are not present in database");
	    }
	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> deleteCompanyById(int companyId, int userId) {

		Optional<User> optional = userRepository.findById(userId);
		
		if(optional.isPresent())
		{
			Optional<Company> optional2 = companyRepository.findById(companyId);
			if(optional2.isPresent())
			{
				Company company=optional2.get();
				companyRepository.delete(company);
				CompanyResponseDto companyResponse = convertToCompanyResponseDto(company);
				ResponseStructure<CompanyResponseDto> responseStructure=new ResponseStructure<>();
    			responseStructure.setData(companyResponse);
    			responseStructure.setMessage("Company deleted succefully");
    			responseStructure.setStatusCode(HttpStatus.OK.value());

    			return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(responseStructure, HttpStatus.OK);
			}
			else
			{
				throw new CompanyNotFoundByIdException("No company present with the id"+companyId);
			}
		}
		else
		{
			throw new UnauthorizedAccessByUserException("Not authorized to access this option");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> updateCompanyById(int companyId, int userId,
			@Valid CompanyRequestDto companyRequest) {

		Optional<User> userOptional = userRepository.findById(userId);
		if(userOptional.isPresent())
		{
			Optional<Company> companyOptional = companyRepository.findById(companyId);
			if(companyOptional.isPresent())
			{
				Company existingCompany = companyOptional.get();
				Company updatedCompany = convertToCompany(companyRequest,companyRequest.getBusinessType());
				updatedCompany.setCompanyId(existingCompany.getCompanyId());
				CompanyResponseDto companyResponse = convertToCompanyResponseDto(updatedCompany);
				
				ResponseStructure<CompanyResponseDto> responseStructure=new ResponseStructure<>();
    			responseStructure.setData(companyResponse);
    			responseStructure.setMessage("Company details updated successfully");
    			responseStructure.setStatusCode(HttpStatus.OK.value());

    			return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(responseStructure, HttpStatus.OK);
			}
			else
			{
				throw new CompanyNotFoundByIdException("No company present with the id"+companyId);
			}
		}
		else
		{
			throw new UnauthorizedAccessByUserException("Not authorized to access this option");
		}
	}
	

}
