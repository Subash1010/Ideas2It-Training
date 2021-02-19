package com.I2I.healthCare.Dao;

import java.util.List;
import java.util.Optional;

import com.I2I.healthCare.Models.UserEntity;

public interface UserDao {

	UserEntity addNewUser(UserEntity userEntity);

	List<UserEntity> getAllUsers();

	Optional<UserEntity> getUserById(long userId);

	String deleteById(long userId);

	UserEntity updateUser(UserEntity userEntity);

}