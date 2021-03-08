package com.I2I.healthCare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
public class HealthCareVitalSignModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCareVitalSignModuleApplication.class, args);
	}

}
