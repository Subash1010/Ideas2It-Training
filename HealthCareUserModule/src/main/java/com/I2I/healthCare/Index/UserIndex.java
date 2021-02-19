package com.I2I.healthCare.Index;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.I2I.healthCare.Dto.UserDto;
import com.I2I.healthCare.Models.RoleEntity;
import com.I2I.healthCare.Models.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "user_index", shards = 2)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIndex implements Serializable {

	private static final long serialVersionUID = 7911446475052134190L;

	@Id
	private long userId;

	private String userName;

	private String password;

	private RoleEntity roleEntity;

	public static UserIndex convertFromUserDto(UserDto userDto) {
		if (userDto == null) {
			return null;
		}
		UserIndex userIndex = new UserIndex();
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleId(userDto.getRoleId());
		userIndex.setUserId(userDto.getUserId());
		userIndex.setUserName(userDto.getUserName());
		userIndex.setPassword(userDto.getPassword());
		userIndex.setRoleEntity(roleEntity);

		return userIndex;
	}

	public static UserIndex convertFromUserEntity(UserEntity userEntity) {
		if (userEntity == null) {
			return null;
		}
		UserIndex userIndex = new UserIndex();
		userIndex.setUserId(userEntity.getUserId());
		userIndex.setUserName(userEntity.getUserName());
		userIndex.setPassword(userEntity.getPassword());
		userIndex.setRoleEntity(userEntity.getRoleEntity());

		return userIndex;
	}

}
