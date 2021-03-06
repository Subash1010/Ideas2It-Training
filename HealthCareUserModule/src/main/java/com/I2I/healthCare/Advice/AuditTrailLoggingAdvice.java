package com.I2I.healthCare.Advice;

import java.net.InetAddress;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
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
import com.I2I.healthCare.Dto.DataDto;
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
		long currentTimeMillis = System.currentTimeMillis();
		Date currentLoggingDate = new Date(currentTimeMillis);
		Time currentLoggingTime = new Time(currentTimeMillis);
		InetAddress myHost = InetAddress.getLocalHost();
		Object response = pjp.proceed();
		UserDto newValue = (UserDto) response;
		auditDto.setUserName(userConfig.getUserName());
		auditDto.setServiceName("USER");
		auditDto.setMethodName(methodName);
		auditDto.setRequest(mapper.writeValueAsString(inputArgs).replaceAll(pattern, ""));
		auditDto.setAction(StringUtils.EMPTY);
		auditDto.setLogDate(currentLoggingDate.toString());
		auditDto.setLogTime(currentLoggingTime.toString());
		DataDto dataDto = new DataDto();
		List<String> fieldName = new ArrayList<>();
		List<Object> oldList = new ArrayList<>();
		List<Object> newList = new ArrayList<>();
		if (Objects.nonNull(beforeUpdate)) {
			if (!(beforeUpdate.getUserName().equals(newValue.getUserName()))) {
				fieldName.add("User Name");
				oldList.add(beforeUpdate.getUserName());
				newList.add(newValue.getUserName());
			}
			if (!(beforeUpdate.getRoleId() == newValue.getRoleId())) {
				fieldName.add("Role Id");
				oldList.add(beforeUpdate.getRoleId());
				newList.add(newValue.getRoleId());
			}
			dataDto.setFieldName(fieldName);
			dataDto.setOldValue(oldList);
			dataDto.setNewValue(newList);
			auditDto.setData(dataDto);
		} else {
			fieldName.add("User Name");
			newList.add(newValue.getUserName());
			fieldName.add("Password");
			newList.add(newValue.getPassword());
			fieldName.add("Role Id");
			newList.add(newValue.getRoleId());
			dataDto.setFieldName(fieldName);
			dataDto.setOldValue(oldList);
			dataDto.setNewValue(newList);
			auditDto.setData(dataDto);
		}
		kafkaTemplate.send("Audit", auditDto);
		rabbitTemplate.convertAndSend("healthcare_exchange", "healthcare_routing", auditDto);
		return response;
	}
}
