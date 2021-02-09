package com.I2I.healthCare.Service;

import com.I2I.healthCare.Dto.RoleDto;

public interface RoleService {

	String addNewRole(RoleDto roleDto);  
	
	String updateRole(RoleDto roleDto);    

	String deleteRoleById(long roleId);
	
	RoleDto getRoleById(long roleId);
	
}