package com.I2I.healthCare.Dao;

import com.I2I.healthCare.Models.RoleEntity;

public interface RoleDao {

	String addNewRole(RoleEntity roleEntity);

	RoleEntity updateRole(RoleEntity roleEntity);

	String deleteRoleById(long roleId);

	RoleEntity getRoleById(long roleId);

}