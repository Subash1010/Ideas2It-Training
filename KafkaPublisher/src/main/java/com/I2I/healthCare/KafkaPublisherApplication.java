package com.I2I.healthCare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Model.User;

@RestController
@SpringBootApplication
public class KafkaPublisherApplication {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@GetMapping("/{name}")
	public String publishMessage(@PathVariable String name) {
		kafkaTemplate.send("event01", name);
		return "Data Published";
	}

	@GetMapping("/")
	public String publishMessage() {
		User user = new User();
		user.setUserId("001");
		user.setUserName("aaa");
		user.setAddress(new String[] { "Dubai", "Dubai Main Road" });
		kafkaTemplate.send("event01", user);
		return "JSON Data Published";
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
