package com.example.jobportal.responsedto;

import java.time.LocalDate;

import com.example.jobportal.enums.BusinessType;

public class CompanyResponseDto {

	private int companyId;
	private String companyName;
	private LocalDate foundedDate;
	private String description;
	private BusinessType businessType;
	private String contactEmail;
	private long contactPhNo;
	private String website;
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
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
	public void setWebsite(String website) {
		this.website = website;
	}
	
}
