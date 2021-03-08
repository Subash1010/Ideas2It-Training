package com.I2I.healthCare;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

import com.I2I.healthCare.Dto.AuditDto;
import com.I2I.healthCare.Model.KafkaAuditEntity;
import com.I2I.healthCare.Model.RabbitAuditEntity;
import com.I2I.healthCare.Repository.KafkaAuditRepository;
import com.I2I.healthCare.Repository.RabbitAuditRepository;
import com.I2I.healthCare.Service.AuditService;
import com.I2I.healthCare.Util.AuditUtil;

@SpringBootApplication
public class HealthCareAuditTrailApplication {

	@Autowired
	public HealthCareAuditTrailApplication(KafkaAuditRepository kafkaAuditRepository,
			RabbitAuditRepository rabbitAuditRepository, AuditService kafkaService) {
		this.kafkaAuditRepository = kafkaAuditRepository;
		this.rabbitAuditRepository = rabbitAuditRepository;
		this.auditService = kafkaService;
	}

	private KafkaAuditRepository kafkaAuditRepository;

	private RabbitAuditRepository rabbitAuditRepository;

	private AuditService auditService;

	@KafkaListener(topics = "Audit", groupId = "healthcare", containerFactory = "kafkaContainerObjectConsumerFactory")
	public void consumeFromKafka(AuditDto auditDto) throws ParseException {
		setAction(auditDto);
		KafkaAuditEntity kafkaAuditEntity = AuditUtil.convertToKafkaEntity(auditDto);
		kafkaAuditRepository.save(kafkaAuditEntity);
	}

	@RabbitListener(queues = "healthcare_queue")
	public void consumeFromRabbit(AuditDto auditDto) throws ParseException {
		setAction(auditDto);
		RabbitAuditEntity rabbitAuditEntity = AuditUtil.convertToRabbitEntity(auditDto);
		rabbitAuditRepository.save(rabbitAuditEntity);
	}

	private void setAction(AuditDto auditDto) {
		String action = auditService.findAction(auditDto.getMethodName(), auditDto.getRequest());
		String request = auditService.findRequestType(auditDto.getMethodName());
		auditDto.setRequest(request);
		auditDto.setAction(action);
	}

	@Bean
	public Queue queue() {
		return new Queue("healthcare_queue", false, false, true);
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	public static void main(String[] args) {
		SpringApplication.run(HealthCareAuditTrailApplication.class, args);
	}

}
