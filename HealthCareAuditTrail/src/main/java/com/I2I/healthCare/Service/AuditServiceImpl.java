package com.I2I.healthCare.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.I2I.healthCare.Dto.AuditDto;

@Service
public class AuditServiceImpl implements AuditService {

	@Override
	public String findAction(String methodOperation, String requestParams, String beforeValue) {
		String action = "";
		if (methodOperation.startsWith("update")) {
			if (beforeValue.equals("")) {
				action = "No changes made as previous and current Values are same";
			} else {
				action = "UPDATED the Record with " + requestParams + "Previous Record was" + beforeValue;
			}
		} else if (methodOperation.startsWith("add")) {
			action = "CREATED a new record with " + requestParams;
		} else {
			action = "AUTHENTICATION is DONE";
		}
		return action;
	}

	@Override
	public List<AuditDto> getAuditLogsByDate(String startDate, String endDate, String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}
}
