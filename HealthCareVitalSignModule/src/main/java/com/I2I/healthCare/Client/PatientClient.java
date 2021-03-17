package com.I2I.healthCare.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.I2I.healthCare.Dto.PatientDto;

@FeignClient(url = "${vitalsignmodule.patient.client.url}", name = "${vitalsignmodule.patient.client.name}")
@FunctionalInterface
public interface PatientClient {

	@GetMapping("/{pId}")
	public PatientDto getPatientDetailsById(@PathVariable long pId);
}
