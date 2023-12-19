package com.example.jobportal.requestdto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.jobportal.enums.BusinessType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CompanyRequestDto {
	
	@NotBlank(message = "Username cannot be blank")
	@NotNull(message = "Username cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Invalid format. Must start with an uppercase letter, followed by letters or spaces.")
	private String companyName;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDate foundedDate;
	
	@NotBlank(message = "Description cannot be blank")
	@NotNull(message = "Description cannot be null")
	private String description;
	
	private BusinessType businessType;
	
	@NotBlank(message = "Email cannot be blank")
	@NotNull(message = "Email cannot be null")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[g][m][a][i][l]+.[c][o][m]", message = "invalid email--Should be in the extension of '@gmail.com' ")
	private String contactEmail;
	
	@NotBlank(message = "Phone number cannot be blank")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number format. It should be 10 digits.")
	private long contactPhNo;
	
	@NotBlank(message = "url cannot be blank")
	@NotNull(message = "url cannot be null")
	private String website;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public LocalDate getFoundedDate() {
		return foundedDate;
	}
	public void setFoundedDate(LocalDate foundedDate) {
		this.foundedDate = foundedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BusinessType getBusinessType() {
		return businessType;
	}
	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public long getContactPhNo() {
		return contactPhNo;
	}
	public void setContactPhNo(long contactPhNo) {
		this.contactPhNo = contactPhNo;
	}
	public String getWebsite() {
		return website;
	}
}
