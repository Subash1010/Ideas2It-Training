package com.I2I.healthCare.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Advice.TrackExecutionTime;
import com.I2I.healthCare.Index.UserIndex;
import com.I2I.healthCare.Service.UserIndexService;

/**
 * UserSearchController works as End point for User module to perform CRUD
 * operations.
 * 
 * @author Subash_Sakthivel
 * @since 21.02
 *
 */
@RestController
@RefreshScope
@RequestMapping("/usersearch")
public class UserSearchController {

	@Lazy
	@Autowired
	public UserSearchController(UserIndexService userIndexService) {
		this.userIndexService = userIndexService;
	}

	private final UserIndexService userIndexService;

	/**
	 * getAllUsers method is used to return all the Users Details From Elastic
	 * Search.
	 * 
	 * @return List of users Details
	 */
	@GetMapping("/")
	@TrackExecutionTime
	public List<UserIndex> getAllUsers() {
		return userIndexService.findAll();
	}

	/**
	 * getUserById method is used to return User Details based on ID From Elastic
	 * Search.
	 * 
	 * @param userId
	 * @return UserDto
	 */
	@GetMapping("/{userId}")
	@TrackExecutionTime
	public UserIndex getUserById(@PathVariable long userId) {
		return userIndexService.findById(userId);
	}

	/**
	 * getUserByRoleId method is used to return User Details based on roleId from
	 * Elastic Search.
	 * 
	 * @param roleId
	 * @return List of User Details
	 */
	@GetMapping("/getByUserCredentials")
	@TrackExecutionTime
	public SearchHits<UserIndex> getByUserCredentials(@RequestParam String userName, @RequestParam String password) {
		return userIndexService.getByUserCredentials(userName, password);
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
		return userIndexService.delete(userId);
	}

	/**
	 * getUserByRoleId method is used to return User Details based on roleId from
	 * Elastic Search.
	 * 
	 * @param roleId
	 * @return List of User Details
	 */
	@GetMapping("/search")
	@TrackExecutionTime
	public SearchHits<UserIndex> getUserSearchData(@RequestParam String searchData) {
		return userIndexService.getUserSearchData(searchData);
	}

	/**
	 * getUserByRoleId method is used to return User Details based on roleId from
	 * Elastic Search.
	 * 
	 * @param roleId
	 * @return List of User Details
	 */
	@GetMapping("/multiMatchQuery")
	@TrackExecutionTime
	public SearchHits<UserIndex> multiMatchQuery(@RequestParam String input) {
		return userIndexService.multiMatchQuery(input);
	}
}
