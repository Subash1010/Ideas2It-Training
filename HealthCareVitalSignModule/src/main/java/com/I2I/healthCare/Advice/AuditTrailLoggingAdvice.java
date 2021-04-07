package com.I2I.healthCare.Advice;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.catalina.startup.UserConfig;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.I2I.healthCare.Dto.AuditDto;
import com.I2I.healthCare.Dto.DataDto;
import com.I2I.healthCare.Dto.VitalSignDto;
import com.I2I.healthCare.Service.VitalSignService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class AuditTrailLoggingAdvice {

	@Autowired
	public AuditTrailLoggingAdvice(KafkaTemplate<String, AuditDto> kafkaTemplate, RabbitTemplate rabbitTemplate,
			VitalSignService vitalService) {
		this.kafkaTemplate = kafkaTemplate;
		this.rabbitTemplate = rabbitTemplate;
		this.vitalService = vitalService;
	}

	private KafkaTemplate<String, AuditDto> kafkaTemplate;

	private RabbitTemplate rabbitTemplate;

	private VitalSignService vitalService;

	@Around("@annotation(com.I2I.healthCare.Advice.AuditTrailLogging)")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		AuditDto auditDto = new AuditDto();
		UserConfig userConfig = new UserConfig();
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		Object[] inputArgs = pjp.getArgs();
		String pattern = "[" + Pattern.quote("{[]}") + "]";
		long currentTimeMillis = System.currentTimeMillis();
		Date currentLoggingDate = new Date(currentTimeMillis);
		Time currentLoggingTime = new Time(currentTimeMillis);
		Object response = pjp.proceed();
		VitalSignDto newValue = (VitalSignDto) response;
		auditDto.setUserName("User001");
		auditDto.setServiceName("VITALSIGN");
		auditDto.setMethodName(methodName);
		auditDto.setRequest(mapper.writeValueAsString(inputArgs).replaceAll(pattern, ""));
		auditDto.setAction(StringUtils.EMPTY);
		auditDto.setLogDate(currentLoggingDate.toString());
		auditDto.setLogTime(currentLoggingTime.toString());
		DataDto dataDto = new DataDto();
		List<String> fieldName = new ArrayList<>();
		List<Object> oldList = new ArrayList<>();
		List<Object> newList = new ArrayList<>();
		fieldName.add("Patient Id");
		newList.add(newValue.getPatientId());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(newValue.getCheckupDate());
		String formatedNewCheackupDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DATE);
		fieldName.add("Checkup Date");
		newList.add(formatedNewCheackupDate);
		fieldName.add("Pulse Rate");
		newList.add(newValue.getPulseRate());
		fieldName.add("Blood Pressure");
		newList.add(newValue.getBloodPressure());
		fieldName.add("Weight");
		newList.add(newValue.getWeight());
		fieldName.add("Height");
		newList.add(newValue.getHeight());
		fieldName.add("Body Temperature");
		newList.add(newValue.getTemperature());
		fieldName.add("Blood Sugar");
		newList.add(newValue.getBloodSugar());
		fieldName.add("Respiration Rate");
		newList.add(newValue.getRespirationRate());
		dataDto.setFieldName(fieldName);
		dataDto.setOldValue(oldList);
		dataDto.setNewValue(newList);
		auditDto.setData(dataDto);
//		kafkaTemplate.send("Audit", auditDto);
//		rabbitTemplate.convertAndSend("healthcare_exchange", "healthcare_routing", auditDto);
		return response;
	}
}
