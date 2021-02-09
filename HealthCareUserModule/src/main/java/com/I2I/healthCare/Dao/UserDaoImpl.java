package com.I2I.healthCare.Dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
		super();
		this.userRepository = userRepository;
	}

	private final UserRepository userRepository;

	@Override
	public String addNewUser(UserEntity userEntity) {
		try {
			userRepository.save(userEntity);
		} catch (Exception exception) {
			System.out.println("Error in insertion of new record" + exception);
			return "Record not Inserted";
		}
		return "Added new record Successfully!!!";
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
			userRepository.deleteById(userId);
		} catch (Exception exception) {
			System.out.println("Error in Deletion of the record" + exception);
			return "Record not Deleted";
		}
		return "Record Deleted Successfully!!!";
	}

	@Override
	public String updateUser(UserEntity userEntity) {
		try {
			UserEntity existingUserEntity = getUserById(userEntity.getUserId()).orElse(null);
			if (Objects.nonNull(existingUserEntity)) {
				existingUserEntity.setUserName(userEntity.getUserName());
				existingUserEntity.setRoleEntity(userEntity.getRoleEntity());
				existingUserEntity.setUpdatedBy(userEntity.getUserName());
				existingUserEntity.setUpdatedAt(LocalDateTime.now());
				userRepository.save(existingUserEntity);
			} else {
				return "No Record is found for Updation";
			}
		} catch (Exception exception) {
			System.out.println("Error in Updation of the record" + exception);
			return "Record not Updated";
		}
		return "Record Updated Successfully!!!";
	}

}
