package com.I2I.healthCare.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.I2I.healthCare.HealthCareUserModuleApplication;
import com.I2I.healthCare.Dao.UserDao;
import com.I2I.healthCare.Dto.UserDto;
import com.I2I.healthCare.Index.UserIndex;
import com.I2I.healthCare.Models.UserEntity;
import com.I2I.healthCare.Prototype.UserDataPrototype;
import com.I2I.healthCare.Repository.UserRepository;
import com.I2I.healthCare.Service.UserIndexService;
import com.I2I.healthCare.Service.UserService;
import com.I2I.healthCare.Util.UserDataUtil;

/**
 * UserControllerTest works as Test End point for User module to perform CRUD
 * operations.
 * 
 * @author Subash_Sakthivel
 * @since 21.02
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HealthCareUserModuleApplication.class)
public class UserControllerTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserIndexService userIndexService;

	@Autowired
	private UserDao userDao;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserController userController;

	@Test
	public void addUserTest() {
		UserDto userDto = UserDataPrototype.getUserDto();
		UserEntity userEntity = UserDataUtil.convertToUserEntity(userDto);
		when(userRepository.save(userEntity)).thenReturn(userEntity);
		when(userIndexService.save(UserIndex.convertFromUserEntity(userEntity))).thenReturn(new UserIndex());
		UserDto userDtoResponse = userController.addUser(userDto);
		assertEquals(userDtoResponse, userDto);
	}

	@Test
	public void getUserByIdTest() {
		UserDto userDto = UserDataPrototype.getUserDto();
		UserEntity userEntity = UserDataUtil.convertToUserEntity(userDto);
		Optional<UserEntity> optionalEntity = Optional.of(userEntity);
		when(userRepository.findById(userDto.getUserId())).thenReturn(optionalEntity);
		UserDto userDtoResponse = userController.getUserById(userDto.getUserId());
		assertThat(userDtoResponse).isNotNull();
		assertEquals(userDtoResponse, userDto);
	}

	@Test
	public void deleteVitalSignTest01() {
		UserDto userDto = UserDataPrototype.getUserDto();
		String messageExpected = "Record with User Id " + userDto.getUserId() + " Deleted Successfully!!!";
		UserEntity userEntity = UserDataUtil.convertToUserEntity(userDto);
		Optional<UserEntity> optionalEntity = Optional.of(userEntity);
		when(userRepository.findById(userDto.getUserId())).thenReturn(optionalEntity);
		String userResponse = userController.deleteById(userDto.getUserId());
		assertEquals(userResponse, messageExpected);
	}

	@Test
	public void deleteVitalSignTest02() {
		UserDto userDto = UserDataPrototype.getUserDto();
		String messageExpected = "Record with User Id  " + userDto.getUserId() + "is not Deleted";
		when(userRepository.findById(userDto.getUserId())).thenReturn(null);
		String userResponse = userController.deleteById(userDto.getUserId());
		assertEquals(userResponse, messageExpected);
	}

	@Test
	public void updateUserTest() {
		UserDto userDto = UserDataPrototype.getUserDto();
		UserEntity userEntity = UserDataUtil.convertToUserEntity(userDto);
		Optional<UserEntity> optionalEntity = Optional.of(userEntity);
		when(userRepository.findById(userDto.getUserId())).thenReturn(optionalEntity);
		when(userRepository.save(userEntity)).thenReturn(userEntity);
		ResponseEntity<UserDto> userDtoResponse = userController.updateUser(userDto);
		assertThat(userDtoResponse).isNotNull();
		assertEquals(userDtoResponse, userDto);
	}

	@Test
	public void updateUserTest01() {
		UserDto userDto = UserDataPrototype.getUserDto();
		Optional<UserEntity> optionalEntity = Optional.of(new UserEntity());
		when(userRepository.findById(userDto.getUserId())).thenReturn(optionalEntity);
		ResponseEntity<UserDto> userDtoResponse = userController.updateUser(userDto);
		assertThat(userDtoResponse).isNull();
		assertEquals(userDtoResponse, null);
	}

}
