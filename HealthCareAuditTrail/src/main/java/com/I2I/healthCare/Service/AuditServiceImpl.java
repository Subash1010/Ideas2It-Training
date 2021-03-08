package com.I2I.healthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.I2I.healthCare.Model.KafkaAuditEntity;
import com.I2I.healthCare.Repository.KafkaAuditRepository;

@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	public AuditServiceImpl(KafkaAuditRepository kafkaAuditRepository) {
		this.kafkaAuditRepository = kafkaAuditRepository;
	}

	private final KafkaAuditRepository kafkaAuditRepository;

	@Override
	public String findAction(String methodOperation, String requestParams) {
		String action = "";
		if (methodOperation.startsWith("update")) {
			action = "UPDATED the Record with New Values";
		} else if (methodOperation.startsWith("add")) {
			action = "CREATED a new record";
		}
		return action;
	}

	@Override
	public String findRequestType(String request) {
		String result = "";
		if (request.startsWith("update")) {
			result = "UPDATE";
		} else if (request.startsWith("add")) {
			result = "CREATE";
		}
		return result;
	}

	@Override
	public List<KafkaAuditEntity> getAuditLogsByDate(String startDate, String endDate, String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KafkaAuditEntity> getAllAudit() {
		return kafkaAuditRepository.findAll();
	}

	@Override
	public List<KafkaAuditEntity> getAuditByServiceName(String serviceName) {
		return kafkaAuditRepository.findByServiceName(serviceName);
	}
}
