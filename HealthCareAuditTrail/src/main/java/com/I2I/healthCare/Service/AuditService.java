package com.I2I.healthCare.Service;

import java.util.List;

import com.I2I.healthCare.Dto.AuditDto;

public interface AuditService {

	String findAction(String methodOperation, String requestParams, String beforeValue);

	List<AuditDto> getAuditLogsByDate(String startDate, String endDate, String serviceName);

}
