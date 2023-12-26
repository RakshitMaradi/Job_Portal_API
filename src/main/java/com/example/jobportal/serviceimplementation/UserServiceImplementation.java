package com.example.jobportal.serviceimplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.User;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exception.RoleNotPresentException;
import com.example.jobportal.exception.UserNotFoundByIdException;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.UserRequestDto;
import com.example.jobportal.responsedto.UserResponseDto;
import com.example.jobportal.service.UserService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> insertUser(UserRequestDto userRequestDto,UserRole userRole) {

		User user = converToUser(userRequestDto,userRole);
		userRepository.save(user);

		if(userRole!=null)
		{
			UserResponseDto userResponseDto = convertToUserResponseDto(user);
			ResponseStructure<UserResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(userResponseDto);
			responseStructure.setMessage("User inserted succefully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());

			return new ResponseEntity<ResponseStructure<UserResponseDto>>(responseStructure, HttpStatus.CREATED);
		}
		else
		{
			throw new RoleNotPresentException("No such role present"); 
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> updateUser(@Valid UserRequestDto updateUser,
			UserRole userrole, int userId) {

		Optional<User> optional = userRepository.findById(userId);

		if(optional.isPresent())
		{
			User user = converToUser(updateUser,userrole);
			user.setUserId(userId);    
			userRepository.save(user);

			UserResponseDto userResponseDto = convertToUserResponseDto(user);
			ResponseStructure<UserResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(userResponseDto);
			responseStructure.setMessage("User with id "+userId+" updated uccessfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<UserResponseDto>>(responseStructure, HttpStatus.OK);
		}
		else
		{
			throw new UserNotFoundByIdException("User with this Id is not present to update");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> findUserById(int userId) {

		Optional<User> optional = userRepository.findById(userId);

		if(optional.isPresent())
		{
			UserResponseDto userResponseDto = convertToUserResponseDto(optional.get());
			
			ResponseStructure<UserResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(userResponseDto);
			responseStructure.setMessage("User with id "+userId+" found successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<UserResponseDto>>(responseStructure, HttpStatus.OK);
		}
		else
		{
			throw new UserNotFoundByIdException("Cannot find user with id "+userId);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> deleteUserById(int userId) {
		Optional<User> optional = userRepository.findById(userId);

		if(optional.isPresent())
		{
			User user = optional.get();
			userRepository.delete(user);
			
			UserResponseDto userResponseDto = convertToUserResponseDto(user);
			
			ResponseStructure<UserResponseDto> responseStructure=new ResponseStructure<>();
			responseStructure.setData(userResponseDto);
			responseStructure.setMessage("User with id "+userId+" deleted successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<UserResponseDto>>(responseStructure, HttpStatus.OK);
		}
		else
		{
			throw new UserNotFoundByIdException("Cannot find user with id "+userId);
		}
	}
	
	private User converToUser(UserRequestDto userRequestDto,UserRole userRole)
	{
		User user=new User();
		user.setEmail(userRequestDto.getEmail());
		user.setPassword(userRequestDto.getPassword());
		user.setUsername(userRequestDto.getUsername());
		user.setUserrole(userRole);
		return user;
	}

	private UserResponseDto convertToUserResponseDto(User user)
	{
		UserResponseDto userResponseDto=new UserResponseDto();
		userResponseDto.setEmail(user.getUsername());
		userResponseDto.setUsername(user.getEmail());
		userResponseDto.setUserRole(user.getUserrole());
		userResponseDto.setUserId(user.getUserId());

		return userResponseDto;
	}
}
