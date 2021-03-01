package com.I2I.healthCare.Advice;

import java.net.InetAddress;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.I2I.healthCare.Config.UserConfig;
import com.I2I.healthCare.Dto.AuditDto;
import com.I2I.healthCare.Dto.UserDto;
import com.I2I.healthCare.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class AuditTrailLoggingAdvice {

	@Autowired
	public AuditTrailLoggingAdvice(KafkaTemplate<String, AuditDto> kafkaTemplate, RabbitTemplate rabbitTemplate,
			UserService userService) {
		this.kafkaTemplate = kafkaTemplate;
		this.rabbitTemplate = rabbitTemplate;
		this.userService = userService;
	}

	private KafkaTemplate<String, AuditDto> kafkaTemplate;

	private RabbitTemplate rabbitTemplate;

	private UserService userService;

	@Around("@annotation(com.I2I.healthCare.Advice.AuditTrailLogging)")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		AuditDto auditDto = new AuditDto();
		UserConfig userConfig = new UserConfig();
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		Object[] inputArgs = pjp.getArgs();
		String pattern = "[" + Pattern.quote("{[]}") + "]";
		UserDto userDto = (UserDto) inputArgs[0];
		long userId = userDto.getUserId();
		UserDto beforeUpdate = userService.getUserById(userId);
		if (Objects.nonNull(beforeUpdate)) {
			beforeUpdate.setUpdatedAt(null);
			beforeUpdate.setCreatedAt(null);
			auditDto.setBeforeValue(mapper.writeValueAsString(beforeUpdate).replaceAll(pattern, ""));
		} else {
			auditDto.setBeforeValue(StringUtils.EMPTY);
		}
		long currentTimeMillis = System.currentTimeMillis();
		Date currentLoggingDate = new Date(currentTimeMillis);
		Time currentLoggingTime = new Time(currentTimeMillis);
		InetAddress myHost = InetAddress.getLocalHost();
		Object response = pjp.proceed();
		auditDto.setUserName(userConfig.getUserName());
		auditDto.setServiceName("USER");
		auditDto.setMethodName(methodName);
		auditDto.setRequest(mapper.writeValueAsString(inputArgs).replaceAll(pattern, ""));
		auditDto.setAction(StringUtils.EMPTY);
		auditDto.setLogDate(currentLoggingDate.toString());
		auditDto.setLogTime(currentLoggingTime.toString());
		kafkaTemplate.send("Audit", auditDto);
		System.out.println("---------------------------");
		System.out.println(auditDto);
		rabbitTemplate.convertAndSend("healthcare_exchange", "healthcare_routing", auditDto);
		return response;
	}
}
