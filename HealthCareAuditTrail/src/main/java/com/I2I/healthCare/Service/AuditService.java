package com.I2I.healthCare.Service;

import java.util.List;

import com.I2I.healthCare.Model.KafkaAuditEntity;

public interface AuditService {

	String findAction(String methodOperation, String requestParams);

	List<KafkaAuditEntity> getAuditLogsByDate(String startDate, String endDate, String serviceName);

	List<KafkaAuditEntity> getAllAudit();

	List<KafkaAuditEntity> getAuditByServiceName(String serviceName);

	String findRequestType(String request);

}
