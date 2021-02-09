package com.I2I.healthCare.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Advice.TrackExecutionTime;
import com.I2I.healthCare.Dto.UserDto;
import com.I2I.healthCare.Models.AuthenticationRequest;
import com.I2I.healthCare.Models.AuthenticationResponse;
import com.I2I.healthCare.Service.UserService;

/**
 * UserController works as End point for Patient module to perform CRUD
 * operations.
 * 
 * @author Subash_Sakthivel
 * @since 21.02
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Lazy
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	private final UserService userService;

	/**
	 * home method is used to check if controller is reachable.
	 * 
	 * @return String
	 */
	@RequestMapping({ "/home" })
	public String hello() {
		return "This is User Page";
	}

	/**
	 * createAuthenticatedToken method is used to generate new JWT token based on
	 * the credentials used.
	 * 
	 * @param AuthenticationRequest
	 * @return AuthenticationResponse
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponse> createAuthenticatedToken(
			@RequestBody AuthenticationRequest userAuthenticateRequest) throws Exception {
		final String jwt = userService.extractJWT(userAuthenticateRequest);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

	/**
	 * addUser method is used to register new Users.
	 * 
	 * @param userDto
	 * @return String
	 */
	@PostMapping("/")
	@TrackExecutionTime
	public String addUser(@RequestBody UserDto userDto) {
		return userService.addNewUser(userDto);
	}

	/**
	 * getAllUsers method is used to return all the Users Details.
	 * 
	 * @return List of users Details
	 */
	@GetMapping("/")
	@TrackExecutionTime
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * getUserById method is used to return User Details based on ID.
	 * 
	 * @param userId
	 * @return UserDto
	 */
	@GetMapping("/{userId}")
	@TrackExecutionTime
	public UserDto getUserById(@PathVariable long userId) {
		return userService.getUserById(userId);
	}

	/**
	 * getUserByRoleId method is used to return User Details based on roleId.
	 * 
	 * @param roleId
	 * @return List of User Details
	 */
	@GetMapping("/getByRoleId/{roleId}")
	@TrackExecutionTime
	public List<UserDto> getUserByRoleId(@PathVariable long roleId) {
		return userService.getUserByRoleId(roleId);
	}

	/**
	 * deleteById method is used to delete User Details based on userId.
	 * 
	 * @param userId
	 * @return String
	 */
	@DeleteMapping("/{userId}")
	@TrackExecutionTime
	public String deleteById(@PathVariable long userId) {
		return userService.deleteById(userId);
	}

	/**
	 * updateUser method is used to updated the already existing User details.
	 * 
	 * @param userDto
	 * @return String
	 */
	@PutMapping("/")
	@TrackExecutionTime
	public String updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}

}
