package com.I2I.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JenkinsEc2Application {

	@GetMapping("/")
	public String home() {
		return "This is EC2 Instance";
	}

	public static void main(String[] args) {
		SpringApplication.run(JenkinsEc2Application.class, args);
	}

}
