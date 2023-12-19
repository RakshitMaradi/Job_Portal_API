package com.example.jobportal.service;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface CompanyService {

	ResponseEntity<ResponseStructure<CompanyResponseDto>> insertCompany(@Valid CompanyRequestDto companyRequest,
			int userId, BusinessType businessType);

	ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompanyById(int companyId);

	ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompanyByName(String companyName);

	ResponseEntity<ResponseStructure<CompanyResponseDto>> deleteCompanyById(int companyId,
			 int userId);

	ResponseEntity<ResponseStructure<CompanyResponseDto>> updateCompanyById(int companyId, int userId,
			@Valid CompanyRequestDto companyRequest);

}
