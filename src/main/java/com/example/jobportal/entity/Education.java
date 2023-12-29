package com.example.jobportal.entity;

import java.time.LocalDate;

import com.example.jobportal.enums.DegreeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int educationId;
	private String instituteName;
	private DegreeType degreeType;
	private String degreeName;
	private String fieldOfStudy;
	private LocalDate startDate;
	private LocalDate endDate;
    private boolean present;
   
	
}
