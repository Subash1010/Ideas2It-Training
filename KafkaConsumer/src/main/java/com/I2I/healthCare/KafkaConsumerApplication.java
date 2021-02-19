package com.I2I.healthCare;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Model.User;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {

	List<String> messageList = new ArrayList();

	User userDetails = new User();

	@GetMapping("/")
	public List<String> consumedMessage() {
		return messageList;
	}

	@GetMapping("/Json")
	public User consumedJsonMessage() {
		return userDetails;
	}

	@KafkaListener(groupId = "group1", topics = "event01", containerFactory = "kafkaContainerConsumerFactory")
	public List<String> getMessage(String message) {
		messageList.add(message);
		return messageList;
	}

	@KafkaListener(groupId = "group2", topics = "event01", containerFactory = "kafkaContainerObjectConsumerFactory")
	public User getJsonMessage(User user) {
		userDetails = user;
		return userDetails;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
