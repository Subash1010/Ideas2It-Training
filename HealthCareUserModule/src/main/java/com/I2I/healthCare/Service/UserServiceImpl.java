package com.I2I.healthCare.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.I2I.healthCare.Dao.UserDao;
import com.I2I.healthCare.Dto.UserDto;
import com.I2I.healthCare.Index.UserIndex;
import com.I2I.healthCare.Models.AuthenticationRequest;
import com.I2I.healthCare.Models.UserEntity;
import com.I2I.healthCare.Repository.UserRepository;
import com.I2I.healthCare.Util.JwtUtil;
import com.I2I.healthCare.Util.UserDataUtil;

@Service
@Profile(value = { "dev", "prod" })
public class UserServiceImpl implements UserDetailsService, UserService {

	@Lazy
	@Autowired
	public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, UserDao userDao,
			JwtUtil jwtUtil, UserIndexService userIndexService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.userDao = userDao;
		this.jwtUtil = jwtUtil;
		this.userIndexService = userIndexService;
	}

	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;

	private final UserDao userDao;

	private final JwtUtil jwtUtil;

	private final UserIndexService userIndexService;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(userName);
		if (optionalUserEntity.isPresent()) {
			UserEntity userEntity = optionalUserEntity.get();
			return new User(userEntity.getUserName(), userEntity.getPassword(), new ArrayList<>());
		}
		return null;
	}

	@Override
	public String extractJWT(AuthenticationRequest userAuthenticateRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userAuthenticateRequest.getUserName(), userAuthenticateRequest.getPassword()));
		} catch (BadCredentialsException exception) {
			throw new Exception("Incorrect UserName or Password", exception);
		}

		final UserDetails userDetails = loadUserByUsername(userAuthenticateRequest.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		return jwt;
	}

	@Override
	@CachePut(value = "user", key = "#userDto.userId")
	public UserDto addNewUser(UserDto userDto) {
		System.out.println("=========== Inside the method ==============");
		if (Objects.nonNull(userDto)) {
			UserEntity userEntity = userDao.addNewUser(UserDataUtil.convertToUserEntity(userDto));
			userIndexService.save(UserIndex.convertFromUserEntity(userEntity));
			return UserDataUtil.convertToUserDto(userEntity);
		}
		logger.error("Error in Addition of new record - Empty Record Can't be Added");
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		System.out.println("=========== Inside the method ==============");
		List<UserEntity> userEntityList = userDao.getAllUsers();
		if (CollectionUtils.isEmpty(userEntityList)) {
			return new ArrayList<>();
		} else {
			return userEntityList.stream().map(currentUserEntity -> UserDataUtil.convertToUserDto(currentUserEntity))
					.collect(Collectors.toList());
		}
	}

	@Override
	@Cacheable(value = "user", key = "#userId")
	public UserDto getUserById(long userId) {
		System.out.println("=========== Inside the method ==============");
		Optional<UserEntity> optionalUserEntity = userDao.getUserById(userId);
		if (optionalUserEntity.isPresent() && !optionalUserEntity.isEmpty()) {
			UserEntity userEntity = optionalUserEntity.get();
			return UserDataUtil.convertToUserDto(userEntity);
		} else {
			return new UserDto();
		}
	}

	@Override
	@Cacheable(value = "role", key = "#roleId")
	public List<UserDto> getUserByRoleId(long roleId) {
		System.out.println("=========== Inside the method ==============");
		List<UserEntity> userEntityList = userDao.getAllUsers();
		if (CollectionUtils.isEmpty(userEntityList)) {
			return new ArrayList<>();
		} else {
			return userEntityList.stream().filter(userEntity -> userEntity.getRoleEntity().getRoleId() == roleId)
					.map(currentUserEntity -> UserDataUtil.convertToUserDto(currentUserEntity))
					.collect(Collectors.toList());
		}
	}

	@Override
	@CacheEvict(value = "user", key = "#userId")
	public String deleteById(long userId) {
		System.out.println("=========== Inside the method ==============");
		return userDao.deleteById(userId);
	}

	@Override
	@CachePut(value = "user", key = "#userDto.userId")
	public UserDto updateUser(UserDto userDto) {
		System.out.println("=========== Inside the method ==============");
		if (Objects.nonNull(userDto)) {
			return UserDataUtil.convertToUserDto(userDao.updateUser(UserDataUtil.convertToUserEntity(userDto)));
		}
		logger.error("Error in Updation of the record - Empty Record Can't be Updated");
		return null;
	}

}
