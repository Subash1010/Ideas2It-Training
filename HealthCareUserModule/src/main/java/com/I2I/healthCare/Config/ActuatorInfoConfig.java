package com.I2I.healthCare.Config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.I2I.healthCare.Repository.UserRepository;

@Component
public class ActuatorInfoConfig implements InfoContributor {

	@Lazy
	@Autowired
	public ActuatorInfoConfig(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	private final UserRepository userRepository;

	@Override
	public void contribute(Builder builder) {
		Map<String, Integer> userCountMap = new LinkedHashMap<>();
		userCountMap.put("Count", userRepository.findAll().size());
		builder.withDetail("Total_Users", userCountMap);
	}

}
