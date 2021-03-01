package com.I2I.healthCare.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Dto.AuditDto;
import com.I2I.healthCare.Service.AuditService;

@RestController
@RequestMapping("/audits")
public class AuditLogController {

	@Autowired
	public AuditLogController(AuditService auditservice) {
		super();
		this.auditservice = auditservice;
	}

	private final AuditService auditservice;

	@GetMapping("/logs")
	public List<AuditDto> getAuditLogsByDate(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("serviceName") String serviceName)
			throws ParseException {
		return auditservice.getAuditLogsByDate(startDate, endDate, serviceName);
	}
}
