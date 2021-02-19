package com.I2I.healthCare.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Models.AuthenticationRequest;
import com.I2I.healthCare.Models.AuthenticationResponse;
import com.I2I.healthCare.Service.UserService;

/**
 * AuthenticationController works as End point for User Authentication.
 * 
 * @author Subash_Sakthivel
 * @since 21.02
 *
 */
@RestController
@RefreshScope
@RequestMapping("/authenticate")
public class AuthenticationController {

	@Lazy
	@Autowired
	public AuthenticationController(UserService userService) {
		this.userService = userService;
	}

	private final UserService userService;

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
}
