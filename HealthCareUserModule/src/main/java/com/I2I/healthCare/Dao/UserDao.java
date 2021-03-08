package com.I2I.healthCare.Dao;

import java.util.List;

import com.I2I.healthCare.Models.UserEntity;

public interface UserDao {

	UserEntity addNewUser(UserEntity userEntity);

	UserEntity getUserById(long userId);

	String deleteById(long userId);

	UserEntity updateUser(UserEntity userEntity);

	List<UserEntity> getAllUsers(int offset, int limit);

}