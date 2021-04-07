package io.localstack.controllers;

import java.util.List;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.localstack.config.SQSConfig;
import io.localstack.dto.AuditVitalSignDto;
import io.localstack.services.LocalStackService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/localstack")
@Slf4j
public class LocalStackController {

	@Autowired
	public LocalStackController(LocalStackService localStackService, SQSConfig sqsConfig) {
		this.localStackService = localStackService;
		this.sqsConfig = sqsConfig;
	}

	private final LocalStackService localStackService;

	private final SQSConfig sqsConfig;

	@KafkaListener(topics = "Audit", groupId = "healthcare")
	public void consumeJson(String maxwellInput) throws JsonMappingException, JsonProcessingException {
		System.out.println(maxwellInput);
		log.debug("got the message: " + maxwellInput);
		try {
			AuditVitalSignDto auditVitalSignDto = new ObjectMapper().readValue(maxwellInput, AuditVitalSignDto.class);
			localStackService.createRecord(auditVitalSignDto);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

	@GetMapping("/")
	@ResponseBody
	public List<String> getAllItems() {
		return localStackService.fetchAllItems();
	}

	@PostMapping("/send/")
	public void sendMessageToQueue() {
		try {
			AmazonSQS sqs = sqsConfig.queueMessagingTemplate();
			List<String> itemList = localStackService.fetchAllItems();
			itemList.stream().forEach(currentMessage -> {
				SendMessageRequest send_msg_request = new SendMessageRequest()
						.withQueueUrl("http://localhost:4566/000000000000/LocalStack").withMessageBody(currentMessage)
						.withDelaySeconds(5);
				sqs.sendMessage(send_msg_request);
			});
		} catch (AmazonSQSException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/receive/")
	public void consumeMessageFromQueue(String message) {
		try {
			AmazonSQS sqs = sqsConfig.queueMessagingTemplate();
			List<Message> messages = sqs.receiveMessage("http://localhost:4566/000000000000/LocalStack").getMessages();

			messages.stream().forEach(currentMessage -> {
				System.out.println("============================================");
				System.out.println(currentMessage);
			});
		} catch (AmazonSQSException e) {
			e.printStackTrace();
		}
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

}
