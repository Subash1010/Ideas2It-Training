package com.I2I.healthCare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HealthCareVitalSignModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCareVitalSignModuleApplication.class, args);
	}

}
