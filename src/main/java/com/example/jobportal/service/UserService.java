package com.example.jobportal.service;

import org.springframework.http.ResponseEntity;

import com.example.jobportal.enums.UserRole;
import com.example.jobportal.requestdto.UserRequestDto;
import com.example.jobportal.responsedto.UserResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponseDto>> insertUser(UserRequestDto userRequestDto,UserRole userRole);

	ResponseEntity<ResponseStructure<UserResponseDto>> updateUser(@Valid UserRequestDto updatedUser,
			UserRole userrole, int userId);

	ResponseEntity<ResponseStructure<UserResponseDto>> findUserById(int userId);

	ResponseEntity<ResponseStructure<UserResponseDto>> deleteUserById(int userId);

}
