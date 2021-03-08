package com.I2I.healthCare.Advice;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.I2I.healthCare.Dto.PatientDto;
import com.I2I.healthCare.Service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class AuditTrailLoggingAdvice {

	@Autowired
	public AuditTrailLoggingAdvice(KafkaTemplate<String, AuditDto> kafkaTemplate, RabbitTemplate rabbitTemplate,
			PatientService patientService) {
		this.kafkaTemplate = kafkaTemplate;
		this.rabbitTemplate = rabbitTemplate;
		this.patientService = patientService;
	}

	private KafkaTemplate<String, AuditDto> kafkaTemplate;

	private RabbitTemplate rabbitTemplate;

	private PatientService patientService;

	@Around("@annotation(com.I2I.healthCare.Advice.AuditTrailLogging)")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		AuditDto auditDto = new AuditDto();
		UserConfig userConfig = new UserConfig();
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		Object[] inputArgs = pjp.getArgs();
		String pattern = "[" + Pattern.quote("{[]}") + "]";
		PatientDto userDto = (PatientDto) inputArgs[0];
		long pId = userDto.getPatientId();
		PatientDto beforeUpdate = patientService.getPatientDetailsById(pId);
		long currentTimeMillis = System.currentTimeMillis();
		Date currentLoggingDate = new Date(currentTimeMillis);
		Time currentLoggingTime = new Time(currentTimeMillis);
		Object response = pjp.proceed();
		PatientDto newValue = (PatientDto) response;
		auditDto.setUserName("User001");
		auditDto.setServiceName("PATIENT");
		auditDto.setMethodName(methodName);
		auditDto.setRequest(mapper.writeValueAsString(inputArgs).replaceAll(pattern, ""));
		auditDto.setAction(StringUtils.EMPTY);
		auditDto.setLogDate(currentLoggingDate.toString());
		auditDto.setLogTime(currentLoggingTime.toString());
		DataDto dataDto = new DataDto();
		Calendar calendar = Calendar.getInstance();
		List<String> fieldName = new ArrayList<>();
		List<Object> oldList = new ArrayList<>();
		List<Object> newList = new ArrayList<>();
		if (Objects.nonNull(beforeUpdate)) {
			if (!(beforeUpdate.getFirstName().equals(newValue.getFirstName()))) {
				fieldName.add("Patient First Name");
				oldList.add(beforeUpdate.getFirstName());
				newList.add(newValue.getFirstName());
			}
			if (!(beforeUpdate.getLastName().equals(newValue.getLastName()))) {
				fieldName.add("Patient Last Name");
				oldList.add(beforeUpdate.getLastName());
				newList.add(newValue.getLastName());
			}
			calendar.setTime(newValue.getDob());
			String formatedNewDob = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.DATE);

			calendar.setTime(beforeUpdate.getDob());
			String formatedOldDob = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.DATE);
			if (!(formatedNewDob.equals(formatedOldDob))) {
				fieldName.add("DOB");
				oldList.add(formatedOldDob);
				newList.add(formatedNewDob);
			}
			if (!(beforeUpdate.getAge() == newValue.getAge())) {
				fieldName.add("Age");
				oldList.add(beforeUpdate.getAge());
				newList.add(newValue.getAge());
			}
			if (!(beforeUpdate.getGender().equals(newValue.getGender()))) {
				fieldName.add("Gender");
				oldList.add(beforeUpdate.getGender());
				newList.add(newValue.getGender());
			}
			if (!(beforeUpdate.getPhoneNumber() == newValue.getPhoneNumber())) {
				fieldName.add("Phone No");
				oldList.add(beforeUpdate.getPhoneNumber());
				newList.add(newValue.getPhoneNumber());
			}
			if (!(beforeUpdate.getAlternatePhoneNumber() == newValue.getAlternatePhoneNumber())) {
				fieldName.add("Alternate Phone No");
				oldList.add(beforeUpdate.getAlternatePhoneNumber());
				newList.add(newValue.getAlternatePhoneNumber());
			}
			if (!(beforeUpdate.getEmail().equals(newValue.getEmail()))) {
				fieldName.add("Email Id");
				oldList.add(beforeUpdate.getEmail());
				newList.add(newValue.getEmail());
			}
			if (!(beforeUpdate.getPermanentAddress().equals(newValue.getPermanentAddress()))) {
				fieldName.add("Permananet Address");
				oldList.add(beforeUpdate.getPermanentAddress());
				newList.add(newValue.getPermanentAddress());
			}
			if (!(beforeUpdate.getCommunicationAddress().equals(newValue.getCommunicationAddress()))) {
				fieldName.add("Communication Address");
				oldList.add(beforeUpdate.getCommunicationAddress());
				newList.add(newValue.getCommunicationAddress());
			}
			calendar.setTime(newValue.getInitialAdmitDate());
			String formatedNewRegDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.DATE);

			calendar.setTime(beforeUpdate.getInitialAdmitDate());
			String formatedOldRegDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.DATE);
			if (!(formatedNewRegDate.equals(formatedOldRegDate))) {
				fieldName.add("Register Date");
				oldList.add(formatedOldRegDate);
				newList.add(formatedNewRegDate);
			}
			calendar.setTime(newValue.getLatestAdmitDate());
			String formatedNewCheckupgDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1)
					+ "-" + calendar.get(Calendar.DATE);

			calendar.setTime(beforeUpdate.getLatestAdmitDate());
			String formatedOldCheckUpDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.DATE);
			if (!(formatedNewCheckupgDate.equals(formatedOldCheckUpDate))) {
				fieldName.add("Register Date");
				oldList.add(formatedOldRegDate);
				newList.add(formatedNewRegDate);
			}
			dataDto.setFieldName(fieldName);
			dataDto.setOldValue(oldList);
			dataDto.setNewValue(newList);
			auditDto.setData(dataDto);
		} else {
			fieldName.add("Patient First Name");
			newList.add(newValue.getFirstName());
			fieldName.add("Patient Last Name");
			newList.add(newValue.getLastName());
			calendar.setTime(newValue.getDob());
			String formatedNewDob = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.DATE);
			fieldName.add("DOB");
			newList.add(formatedNewDob);
			fieldName.add("Age");
			newList.add(newValue.getAge());
			fieldName.add("Gender");
			newList.add(newValue.getGender());
			fieldName.add("Phone No");
			newList.add(newValue.getPhoneNumber());
			fieldName.add("Alternate Phone No");
			newList.add(newValue.getAlternatePhoneNumber());
			fieldName.add("Email Id");
			newList.add(newValue.getEmail());
			fieldName.add("Permanent Address");
			newList.add(newValue.getPermanentAddress());
			fieldName.add("Communication Address");
			newList.add(newValue.getCommunicationAddress());
			calendar.setTime(newValue.getInitialAdmitDate());
			String formatedNewRegDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.DATE);
			fieldName.add("Initial Register Date");
			newList.add(formatedNewRegDate);
			calendar.setTime(newValue.getLatestAdmitDate());
			String formatedLatestRegDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
					+ calendar.get(Calendar.DATE);
			fieldName.add("Latest CheckUp Date");
			newList.add(formatedLatestRegDate);
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
