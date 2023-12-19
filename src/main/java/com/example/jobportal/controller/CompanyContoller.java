package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.service.CompanyService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class CompanyContoller {
	
	@Autowired
	CompanyService companyService;

	@PostMapping("/users/{userId}/business-types/{businessType}/companies")
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> insert(@RequestBody @Valid CompanyRequestDto companyRequest,
			@PathVariable int userId,@PathVariable BusinessType businessType) {

		return companyService.insertCompany(companyRequest,userId,businessType);
	}
	
	@GetMapping("/companies/{companyId}")
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> findById(@PathVariable int companyId)
    {
		return companyService.findCompanyById(companyId);
	}
	
	@GetMapping("/companies/{companyName}")
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> findByName(@PathVariable String companyName)
    {
		return companyService.findCompanyByName(companyName);
	}
	
	@DeleteMapping("users/{userId}/companies/{companyId}")
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> deleteCompany(@PathVariable int companyId,@PathVariable int userId)
    {
		return companyService.deleteCompanyById(companyId,userId);
	}

	
	@PutMapping("users/{userId}/companies/{companyId}")
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> updateCompany(@PathVariable int companyid,@PathVariable int userId,
			@RequestBody @Valid CompanyRequestDto companyRequest)
    {
		return companyService.updateCompanyById(companyid,userId,companyRequest);
	}

}
