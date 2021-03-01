package com.I2I.healthCare.Prototype;

import com.I2I.healthCare.Dto.UserDto;

public class UserDataPrototype {

	public static UserDto getUserDto() {
		UserDto userDto = new UserDto();
		userDto.setUserId(1);
		userDto.setUserName("Test001");
		userDto.setRoleId(500);
		return userDto;

	}

}
