package com.I2I.healthCare.Util;

import com.I2I.healthCare.Dto.AuditDto;
import com.I2I.healthCare.Model.KafkaAuditEntity;
import com.I2I.healthCare.Model.RabbitAuditEntity;

public class AuditUtil {

	public static KafkaAuditEntity convertToKafkaEntity(AuditDto auditDto) {
		if (auditDto == null) {
			return null;
		}
		KafkaAuditEntity kafkaAuditEntity = new KafkaAuditEntity();
		kafkaAuditEntity.setUserName(auditDto.getUserName());
		kafkaAuditEntity.setServiceName(auditDto.getServiceName());
		kafkaAuditEntity.setMethodName(auditDto.getMethodName());
		kafkaAuditEntity.setRequest(auditDto.getRequest());
		kafkaAuditEntity.setData(auditDto.getData());
		kafkaAuditEntity.setAction(auditDto.getAction());
		kafkaAuditEntity.setLogDate(auditDto.getLogDate());
		kafkaAuditEntity.setLogTime(auditDto.getLogTime());
		return kafkaAuditEntity;

	}

	public static RabbitAuditEntity convertToRabbitEntity(AuditDto auditDto) {
		if (auditDto == null) {
			return null;
		}
		RabbitAuditEntity rabbitAuditEntity = new RabbitAuditEntity();
		rabbitAuditEntity.setUserName(auditDto.getUserName());
		rabbitAuditEntity.setServiceName(auditDto.getServiceName());
		rabbitAuditEntity.setMethodName(auditDto.getMethodName());
		rabbitAuditEntity.setRequest(auditDto.getRequest());
		rabbitAuditEntity.setData(auditDto.getData());
		rabbitAuditEntity.setAction(auditDto.getAction());
		rabbitAuditEntity.setLogDate(auditDto.getLogDate());
		rabbitAuditEntity.setLogTime(auditDto.getLogTime());
		return rabbitAuditEntity;

	}
}
