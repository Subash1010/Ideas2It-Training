package com.I2I.healthCare.Util;

import com.I2I.healthCare.Dto.RoleDto;
import com.I2I.healthCare.Models.RoleEntity;

public class RoleDataUtil {
	public static RoleDto convertToRoleDto(RoleEntity roleEntity) {
		if (roleEntity == null) {
			return null;
		}
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleId(roleEntity.getRoleId());
		roleDto.setRoleName(roleEntity.getRoleName());
		roleDto.setCreatedAt(roleEntity.getCreatedAt());
		roleDto.setCreatedBy(roleEntity.getCreatedBy());
		roleDto.setUpdatedBy(roleEntity.getUpdatedBy());
		roleDto.setUpdatedAt(roleEntity.getUpdatedAt());

		return roleDto;

	}

	public static RoleEntity convertToRoleEntity(RoleDto roleDto) {
		if (roleDto == null) {
			return null;
		}
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleId(roleDto.getRoleId());
		roleEntity.setRoleName(roleDto.getRoleName());
		roleEntity.setCreatedAt(roleDto.getCreatedAt());
		roleEntity.setCreatedBy(roleDto.getCreatedBy());
		roleEntity.setUpdatedBy(roleDto.getUpdatedBy());
		roleEntity.setUpdatedAt(roleDto.getUpdatedAt());

		return roleEntity;
	}
}
