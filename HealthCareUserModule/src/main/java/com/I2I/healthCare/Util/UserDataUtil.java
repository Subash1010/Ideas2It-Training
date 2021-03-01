package com.I2I.healthCare.Util;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import com.I2I.healthCare.Dto.UserDto;
import com.I2I.healthCare.Models.RoleEntity;
import com.I2I.healthCare.Models.UserEntity;

public class UserDataUtil {

	public static UserDto convertToUserDto(UserEntity userEntity) {
		if (userEntity == null) {
			return null;
		}
		UserDto userDto = new UserDto();
		userDto.setUserId(userEntity.getUserId());
		userDto.setUserName(userEntity.getUserName());
		if (Objects.nonNull(userEntity.getRoleEntity())) {
			userDto.setRoleId(userEntity.getRoleEntity().getRoleId());
			userDto.setRoleName(userEntity.getRoleEntity().getRoleName());
		} else {
			userDto.setRoleId(500);
			userDto.setRoleName(StringUtils.EMPTY);
		}
		userDto.setCreatedAt(userEntity.getCreatedAt());
		userDto.setCreatedBy(userEntity.getCreatedBy());
		userDto.setUpdatedBy(userEntity.getUpdatedBy());
		userDto.setUpdatedAt(userEntity.getUpdatedAt());

		return userDto;

	}

	public static UserEntity convertToUserEntity(UserDto userDto) {
		if (userDto == null) {
			return null;
		}
		UserEntity userEntity = new UserEntity();
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleId(userDto.getRoleId());
		userEntity.setUserId(userDto.getUserId());
		userEntity.setUserName(userDto.getUserName());
		userEntity.setPassword(userDto.getPassword());
		userEntity.setRoleEntity(roleEntity);
		userEntity.setCreatedAt(userDto.getCreatedAt());
		userEntity.setCreatedBy(userDto.getCreatedBy());
		userEntity.setUpdatedBy(userDto.getUpdatedBy());
		userEntity.setUpdatedAt(userDto.getUpdatedAt());

		return userEntity;
	}
}
