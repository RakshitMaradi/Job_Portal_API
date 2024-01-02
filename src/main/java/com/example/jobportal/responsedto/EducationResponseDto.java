package com.example.jobportal.responsedto;

import java.time.LocalDate;

import com.example.jobportal.enums.DegreeType;

public class EducationResponseDto {

	private String instituteName;
	private DegreeType degreeType;
	private String degreeName;
	private String fieldOfStudy;
	private LocalDate startDate;
	private LocalDate endDate;
    private boolean present;
    private double marks;
    
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public DegreeType getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(DegreeType degreeType) {
		this.degreeType = degreeType;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
    
}
