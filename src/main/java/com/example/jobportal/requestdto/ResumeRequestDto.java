package com.example.jobportal.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResumeRequestDto {

	@NotBlank(message = "Objective cannot be blank")
	@NotNull(message = "Objective cannot be null")
	private String objective;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}
    
}
