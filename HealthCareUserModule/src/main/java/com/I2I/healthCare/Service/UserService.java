package com.I2I.healthCare.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.I2I.healthCare.Dto.UserDto;
import com.I2I.healthCare.Models.AuthenticationRequest;

public interface UserService {

	UserDetails loadUserByUsername(String userName);

	String extractJWT(AuthenticationRequest userAuthenticateRequest) throws Exception;

	String addNewUser(UserDto userDto);

	List<UserDto> getAllUsers();

	UserDto getUserById(long userId);

	List<UserDto> getUserByRoleId(long roleId);

	String deleteById(long userId);

	String updateUser(UserDto userDto);

}