package com.I2I.healthCare.Dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Models.UserEntity;
import com.I2I.healthCare.Repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Lazy
	@Autowired
	public UserDaoImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private final UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public UserEntity addNewUser(UserEntity userEntity) {
		UserEntity savedUserEntity = new UserEntity();
		try {
			savedUserEntity = userRepository.save(userEntity);
		} catch (Exception exception) {
			logger.error("Error in insertion of new record" + exception);
			return savedUserEntity;
		}
		logger.info("Added new record Successfully!!!");
		return savedUserEntity;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserEntity> getUserById(long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public String deleteById(long userId) {
		try {
			Optional<UserEntity> optionalUserEntity = getUserById(userId);
			if (optionalUserEntity.isPresent() && !optionalUserEntity.isEmpty()) {
				userRepository.deleteById(userId);
			} else {
				return "Record with User Id " + userId + " is not found for Deletion";
			}
		} catch (Exception exception) {
			logger.error("Error in Deletion of the record with User Id " + userId + StringUtils.EMPTY + exception);
			return "Record with User Id  " + userId + "is not Deleted";
		}

		return "Record with User Id " + userId + " Deleted Successfully!!!";
	}

	@Override
	public UserEntity updateUser(UserEntity userEntity) {
		try {
			UserEntity existingUserEntity = getUserById(userEntity.getUserId()).orElse(null);
			if (Objects.nonNull(existingUserEntity)) {
				existingUserEntity.setUserId(userEntity.getUserId());
				existingUserEntity.setUserName(userEntity.getUserName());
				existingUserEntity.setPassword(userEntity.getPassword());
				existingUserEntity.setRoleEntity(userEntity.getRoleEntity());
				return userRepository.save(existingUserEntity);
			} else {
				logger.error("No Record is found for Updation");
				return null;
			}
		} catch (Exception exception) {
			logger.error("Error in Updation of the record" + exception);
			return null;
		}
	}

}
