package com.I2I.healthCare.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Dto.RoleDto;
import com.I2I.healthCare.Service.RoleService;

/**
 * RoleController works as End point for Role module to perform CRUD operations.
 * 
 * @author Subash_Sakthivel
 * @since 21.02
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {

	@Lazy
	@Autowired
	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	private final RoleService roleService;

	/**
	 * home method is used to check if controller is reachable.
	 * 
	 * @return String
	 */
	@RequestMapping({ "/home" })
	public String home() {
		return "This is Role Page";
	}

	/**
	 * addRole method is used to register new Role.
	 * 
	 * @param roleDto
	 * @return String
	 */
	@PostMapping("/")
	public String addRole(@RequestBody RoleDto roleDto) {
		return roleService.addNewRole(roleDto);
	}

	/**
	 * getRoleById method is used to return the Role details based on Role Id.
	 * 
	 * @param roleId
	 * @return roleDto
	 */
	@GetMapping("/{roleId}")
	public RoleDto getRoleById(@PathVariable long roleId) {
		return roleService.getRoleById(roleId);
	}

	/**
	 * deleteById method is used to delete the Role based on Role Id.
	 * 
	 * @param roleId
	 * @return String
	 */
	@DeleteMapping("/{roleId}")
	public String deleteById(@PathVariable long roleId) {
		return roleService.deleteRoleById(roleId);
	}

	/**
	 * updateUser method is used to updated the already existing Role details.
	 * 
	 * @param roleDto
	 * @return String
	 */
	@PutMapping("/")
	public String updateUser(@RequestBody RoleDto roleDto) {
		return roleService.updateRole(roleDto);
	}

}
