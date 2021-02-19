package com.I2I.healthCare.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class HealthCareGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCareGatewayApplication.class, args);
	}

}
