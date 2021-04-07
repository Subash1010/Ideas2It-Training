package com.kmaxwell;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmaxwell.db.MongoData;
import com.kmaxwell.model.MData;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class KafkaMaxwellApplication {

	@Autowired
	public KafkaMaxwellApplication(MongoData mongoData) {
		this.mongoData = mongoData;
	}

	private MongoData mongoData;

	public static void main(String[] args) {
		SpringApplication.run(KafkaMaxwellApplication.class, args);
	}

	@KafkaListener(topics = "Audit", groupId = "healthcare")
	public void consumeJson(String maxwellInput) {
		System.out.println(maxwellInput);
		log.debug("got the message: " + maxwellInput);
		try {
			MData mdata = new ObjectMapper().readValue(maxwellInput, MData.class);
			mongoData.saveData(mdata);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
}
