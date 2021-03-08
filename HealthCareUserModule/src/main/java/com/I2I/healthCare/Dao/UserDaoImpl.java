package com.I2I.healthCare.Dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Advice.AuditTrailLogging;
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
		logger.info("Added new record Successfully!!! UserId is " + savedUserEntity.getUserId());
		return savedUserEntity;
	}

	@Override
	public List<UserEntity> getAllUsers(int limit, int offset) {
		Pageable pageable = new OffsetBasedPageRequest(limit, offset);
		return userRepository.findAll(pageable).getContent();

	}

	@Override
	public UserEntity getUserById(long userId) {
		Optional<UserEntity> optionalEntity = userRepository.findById(userId);
		if (!optionalEntity.isEmpty() && optionalEntity.isPresent()) {
			return optionalEntity.get();
		} else {
			return null;
		}
	}

	@Override
	public String deleteById(long userId) {
		try {
			UserEntity userEntity = getUserById(userId);
			if (Objects.nonNull(userEntity)) {
				userRepository.deleteById(userId);
			} else {
				logger.error("Record with User Id " + userId + " is not found for Deletion");
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
			@AuditTrailLogging()
			UserEntity existingUserEntity = getUserById(userEntity.getUserId());
			if (Objects.nonNull(existingUserEntity)) {
				existingUserEntity.setUserName(userEntity.getUserName());
				existingUserEntity.setPassword(userEntity.getPassword());
				existingUserEntity.setRoleEntity(userEntity.getRoleEntity());
				logger.info("User Record with ID " + existingUserEntity.getUserId() + " is Updated");
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
