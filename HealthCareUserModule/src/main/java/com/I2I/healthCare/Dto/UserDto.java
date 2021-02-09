package com.I2I.healthCare.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.I2I.healthCare.Models.RoleEntity;
import com.I2I.healthCare.Models.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

	private static final long serialVersionUID = -7134195975158556179L;

	private long userId;

	private String userName;

	private String password;

	private long roleId;

	private String roleName;

	private String createdBy;

	private LocalDateTime createdAt;

	private String updatedBy;

	private LocalDateTime updatedAt;

	public static UserDto convertToUserDto(UserEntity userEntity) {
		if (userEntity == null) {
			return null;
		}
		UserDto userDto = new UserDto();
		userDto.setUserId(userEntity.getUserId());
		userDto.setUserName(userEntity.getUserName());
		userDto.setRoleId(userEntity.getRoleEntity().getRoleId());
		userDto.setRoleName(userEntity.getRoleEntity().getRoleName());
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
