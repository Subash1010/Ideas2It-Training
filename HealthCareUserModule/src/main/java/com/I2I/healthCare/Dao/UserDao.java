package com.I2I.healthCare.Dao;

import java.util.List;
import java.util.Optional;

import com.I2I.healthCare.Models.UserEntity;

public interface UserDao {

	String addNewUser(UserEntity userEntity);

	List<UserEntity> getAllUsers();

	Optional<UserEntity> getUserById(long userId);

	String deleteById(long userId);

	String updateUser(UserEntity userEntity);

}