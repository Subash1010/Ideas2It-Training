package com.I2I.healthCare.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Model.KafkaAuditEntity;
import com.I2I.healthCare.Service.AuditService;

@RestController
@RequestMapping("/audits")
@CrossOrigin
public class AuditLogController {

	@Autowired
	public AuditLogController(AuditService auditService) {
		super();
		this.auditService = auditService;
	}

	private final AuditService auditService;

	@GetMapping("/logs")
	public List<KafkaAuditEntity> getAuditLogsByDate(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("serviceName") String serviceName)
			throws ParseException {
		return auditService.getAuditLogsByDate(startDate, endDate, serviceName);
	}

	/**
	 * getAllAudit method is used to fetch Audit information.
	 * 
	 * @return List<Audit>
	 */
	@GetMapping("/")
	public List<KafkaAuditEntity> getAllAudit() {
		return auditService.getAllAudit();
	}

	/**
	 * getAuditByServiceName method is used to fetch the audit details based on
	 * service Name
	 * 
	 * @param serviceName
	 * @return List<Audit>
	 */
	@GetMapping("/{serviceName}")
	public List<KafkaAuditEntity> getAuditByServiceName(@PathVariable String serviceName) {
		return auditService.getAuditByServiceName(serviceName);
	}
}
