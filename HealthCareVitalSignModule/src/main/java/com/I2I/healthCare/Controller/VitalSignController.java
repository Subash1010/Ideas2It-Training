package com.I2I.healthCare.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Advice.AuditTrailLogging;
import com.I2I.healthCare.Dto.PatientDto;
import com.I2I.healthCare.Dto.VitalSignDto;
import com.I2I.healthCare.Service.VitalSignService;

import io.swagger.annotations.ApiOperation;

/**
 * VitalSignController works as End point for Vital Sign module to perform CRUD
 * operations.
 * 
 * @author Subash_Sakthivel
 * @since 21.02
 *
 */
@RestController
@RequestMapping("/vitalsign")
@CrossOrigin
public class VitalSignController {

	@Lazy
	@Autowired
	public VitalSignController(VitalSignService vitalSignService) {
		super();
		this.vitalSignService = vitalSignService;
	}

	private final VitalSignService vitalSignService;

	/**
	 * addCheckupDetails method is used to register new check up Details.
	 * 
	 * @param vitalSignDto
	 * @return String
	 */
	@PostMapping("/")
	@AuditTrailLogging
	@ApiOperation(value = "Insert New Vital Sign Detail of the Patient", response = VitalSignDto.class)
	public VitalSignDto addCheckupDetails(@RequestBody VitalSignDto vitalSignDto) {
		return vitalSignService.addCheckupDetails(vitalSignDto);
	}

	/**
	 * getCheckupDetails method is used to return check up Details.
	 * 
	 * @return VitalSignDto List
	 */
	@GetMapping("/")
	@ApiOperation(value = "Fetch CheckUp Details of the Patient by passing Patient Id and CheckUp date", response = VitalSignDto.class)
	public List<VitalSignDto> getCheckupDetails() {
		return vitalSignService.getCheckupDetails();
	}

	/**
	 * getCheckupDetails method is used to return check up Details based on patient
	 * Id and check up Date.
	 * 
	 * @param pId
	 * @param check up date
	 * @return VitalSignDto
	 */
	@GetMapping("/{pId}/{date}")
	@ApiOperation(value = "Fetch CheckUp Details of the Patient by passing Patient Id and CheckUp date", response = VitalSignDto.class)
	public VitalSignDto getCheckupDetails(@PathVariable long pId,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return vitalSignService.getCheckupDetails(pId, date);
	}

	/**
	 * getPatientDetails method is used to return check up Details of patient based
	 * on patient Id.
	 * 
	 * @param pId
	 * @return PatientDto
	 */
	@GetMapping("/patients/{pId}")
	@ApiOperation(value = "Fetch Details of the Patient by passing Patient Id", response = PatientDto.class)
	public PatientDto getPatientDetails(@PathVariable long pId) {
		return vitalSignService.getPatientDetails(pId);
	}

	/**
	 * updateVitalSigns method is used to update check up Details of patient based
	 * on patient Id and check up date.
	 * 
	 * @param pId
	 * @param check up date
	 * @param vital Sign values
	 * @return String
	 */
	@PutMapping("/{pId}/{date}/")
	@AuditTrailLogging
	@ApiOperation(value = "Update CheckUp Details of the Patient by passing Patient Id and CheckUp date", response = VitalSignDto.class)
	public VitalSignDto updateVitalSigns(@PathVariable long pId,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestBody VitalSignDto vitalSignDto) {
		return vitalSignService.updateVitalSign(pId, date, vitalSignDto);
	}

	/**
	 * deleteCheckup method is used to delete check up Details of patient based on
	 * patient Id and check up date.
	 * 
	 * @param pId
	 * @param check up date
	 * @return String
	 */
	@DeleteMapping("/{pId}/{date}")
	@ApiOperation(value = "Delete CheckUp Details of the Patient by passing Patient Id and CheckUp date", response = String.class)
	public String deleteCheckup(@PathVariable long pId,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return vitalSignService.deleteCheckup(pId, date);
	}
}
