package com.example.jobportal.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.enums.UserRole;
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
public class CompanyServiceImplementation implements CompanyService {
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	UserRepository userRepository;

	private Company convertToCompany(CompanyRequestDto companyRequest, BusinessType businessType) {
		Company company = new Company();
		company.setBusinessType(businessType);
		company.setCompanyName(companyRequest.getCompanyName());
		company.setContactEmail(companyRequest.getContactEmail());
		company.setContactPhNo(companyRequest.getContactPhNo());
		company.setFoundedDate(companyRequest.getFoundedDate());
		company.setDescription(companyRequest.getDescription());
		company.setWebsite(companyRequest.getWebsite());

		return company;
	}

	private CompanyResponseDto convertToCompanyResponseDto(Company company) {
		CompanyResponseDto companyResponseDto = new CompanyResponseDto();
		companyResponseDto.setBusinessType(company.getBusinessType());
		companyResponseDto.setCompanyId(company.getCompanyId());
		companyResponseDto.setCompanyName(company.getCompanyName());
		companyResponseDto.setFoundedDate(company.getFoundedDate());
		companyResponseDto.setContactEmail(company.getContactEmail());
		companyResponseDto.setContactPhNo(company.getContactPhNo());
		companyResponseDto.setDescription(company.getDescription());
		companyResponseDto.setWebsite(company.getWebsite());

		return companyResponseDto;
	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> insertCompany(@Valid CompanyRequestDto companyRequest,
			int userId, BusinessType businessType) {
		
		User user = userRepository.findById(userId)
			    .orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));
	
		UserRole userrole = user.getUserrole();
		
			if (userrole.equals(UserRole.EMPLOYER)) 
			{
				Company company = convertToCompany(companyRequest, businessType);
				company.setUser(user);                                    
				companyRepository.save(company);
				CompanyResponseDto companyResponse = convertToCompanyResponseDto(company);
				ResponseStructure<CompanyResponseDto> responseStructure = new ResponseStructure<>();
				responseStructure.setData(companyResponse);
				responseStructure.setMessage("Company inserted successfully");
				responseStructure.setStatusCode(HttpStatus.CREATED.value());

				return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(responseStructure, HttpStatus.CREATED);
			} else {
				throw new UnauthorizedAccessByUserException("Not authorised to access this option");
			}
		
	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompanyById(int companyId) {

		Optional<Company> optional = companyRepository.findById(companyId);

		if (optional.isPresent()) {
			Company company = optional.get();
			CompanyResponseDto companyResponse = convertToCompanyResponseDto(company);
			ResponseStructure<CompanyResponseDto> responseStructure = new ResponseStructure<>();
			responseStructure.setData(companyResponse);
			responseStructure.setMessage("Company found successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new CompanyNotFoundByIdException("No company present with the is " + companyId);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CompanyResponseDto>>> findCompanyByName(String companyName) {

		List<Company> companyList = companyRepository.findByCompanyName(companyName);

		if (companyList != null) {
			List<CompanyResponseDto> companyResponseList = new ArrayList<>();
			for (Company company : companyList) {
				CompanyResponseDto companyResponse = convertToCompanyResponseDto(company);
				companyResponseList.add(companyResponse);
			}

			ResponseStructure<List<CompanyResponseDto>> responseStructure = new ResponseStructure<>();
			responseStructure.setData(companyResponseList);
			responseStructure.setMessage("Company found successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<CompanyResponseDto>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new CompanyNotFoundByNameException("No company found with the name " + companyName);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> deleteCompanyById(int companyId, int userId) {

		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			if (user.getUserrole().equals(UserRole.EMPLOYER)) {
				Optional<Company> optional2 = companyRepository.findById(companyId);
				if (optional2.isPresent()) {
					Company company = optional2.get();
					companyRepository.delete(company);
					CompanyResponseDto companyResponse = convertToCompanyResponseDto(company);
					ResponseStructure<CompanyResponseDto> responseStructure = new ResponseStructure<>();
					responseStructure.setData(companyResponse);
					responseStructure.setMessage("Company deleted succefully");
					responseStructure.setStatusCode(HttpStatus.OK.value());

					return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(responseStructure, HttpStatus.OK);
				} else {
					throw new CompanyNotFoundByIdException("No company present with the id " + companyId);
				}
			} else {
				throw new UnauthorizedAccessByUserException("Not authorized to access this option");
			}
		} else {
			throw new UserNotFoundByIdException("User not found with the id " + userId);
		}

	}

	@Override
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> updateCompanyById(int companyId, int userId,
			@Valid CompanyRequestDto companyRequest) {

		User user = userRepository.findById(userId)
				    .orElseThrow(() -> new UserNotFoundByIdException("User not found with the id " + userId));
		    // Used lamda expression for throwing exception
			// User not found exception

		if (user.getUserrole().equals(UserRole.EMPLOYER))   // checking if the user is an EMPLOYER
		{
			
			Company existingCompany = companyRepository.findById(companyId)
					.orElseThrow(() -> new CompanyNotFoundByIdException("No company present with the id " + companyId));
			//Company not found exception
			
			Company updatedCompany = convertToCompany(companyRequest,companyRequest.getBusinessType());
			updatedCompany.setCompanyId(existingCompany.getCompanyId());
			companyRepository.save(updatedCompany);
			CompanyResponseDto companyResponse = convertToCompanyResponseDto(updatedCompany);

			ResponseStructure<CompanyResponseDto> responseStructure = new ResponseStructure<>();
			responseStructure.setData(companyResponse);
			responseStructure.setMessage("Company details updated successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<CompanyResponseDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new UnauthorizedAccessByUserException("Not authorized to access this option");
		}

	}
}
