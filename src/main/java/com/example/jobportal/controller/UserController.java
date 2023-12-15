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

import com.example.jobportal.enums.UserRole;
import com.example.jobportal.requestdto.UserRequestDto;
import com.example.jobportal.responsedto.UserResponseDto;
import com.example.jobportal.service.UserService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/userroles/{userrole}/users")
	public ResponseEntity<ResponseStructure<UserResponseDto>> insert(@RequestBody @Valid UserRequestDto userRequestDto,
			@PathVariable UserRole userrole) {

		return userService.insertUser(userRequestDto,userrole);
	}
	
	@PutMapping("users/userroles/{userrole}/{userId}")
	public ResponseEntity<ResponseStructure<UserResponseDto>> update(@RequestBody @Valid UserRequestDto updatedUser,
			@PathVariable UserRole userrole,@PathVariable int userId) {

		return userService.updateUser(updatedUser,userrole,userId);
	}
	
	@GetMapping("users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponseDto>> findUser(@PathVariable int userId)
    {
		return userService.findUserById(userId);
	}
	
	@DeleteMapping("users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponseDto>> deleteUser(@PathVariable int userId)
    {
		return userService.deleteUserById(userId);
	}
}
