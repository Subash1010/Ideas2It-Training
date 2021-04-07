package com.I2I.aws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

import com.I2I.aws.Service.SQSService;

@SpringBootApplication(exclude = { ContextInstanceDataAutoConfiguration.class, ContextStackAutoConfiguration.class,
		ContextRegionProviderAutoConfiguration.class })
public class SqsListenerApplication {

	@Autowired
	public SqsListenerApplication(SQSService sqsService) {
		this.sqsService = sqsService;
	}

	private final SQSService sqsService;

	private static final Logger logger = LoggerFactory.getLogger(SqsListenerApplication.class);

	@SqsListener("http://localhost:4566/000000000000/LocalStack")
	public void receiveMessage(String stringJson) {
		logger.info("Message Received using SQS Listner " + stringJson);
		System.out.println("==========================================");
		System.out.println(stringJson);
		sqsService.insertRecord(stringJson);

	}

	public static void main(String[] args) {
		SpringApplication.run(SqsListenerApplication.class, args);
	}

}
