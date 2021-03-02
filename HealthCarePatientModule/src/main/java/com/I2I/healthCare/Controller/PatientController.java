package com.I2I.healthCare.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Dto.PatientDto;
import com.I2I.healthCare.Service.PatientService;

/**
 * PatientController works as End point for Patient module to perform CRUD
 * operations.
 * 
 * @author Subash_Sakthivel
 * @since 21.02
 *
 */
@RestController
@RequestMapping("/patients")
public class PatientController {

	@Lazy
	@Autowired
	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}

	private final PatientService patientService;

	/**
	 * addNewPatient method is used to register new Patients.
	 * 
	 * @param patientDto
	 * @return String
	 */
	@PostMapping("/")
	public PatientDto addNewPatient(@RequestBody PatientDto patientDto) {
		return patientService.addPatient(patientDto);
	}

	/**
	 * getAllPatients method is used to return all the Patients.
	 * 
	 * @return List of patients
	 */
	@GetMapping("/")
	public List<PatientDto> getAllPatients() {
		return patientService.getAllPatients();
	}

	/**
	 * getAllPatientsNameByAge method is used to return Patients name based on age.
	 * 
	 * @param age
	 * @return List of patients name
	 */
	@GetMapping("/age")
	public List<String> getAllPatientsNameByAge(@RequestParam int age) {
		return patientService.getAllPatientsNameByAge(age);
	}

	/**
	 * getAllPatientsByName method is used to return all the details of the Patients
	 * by Name.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return List of Patients
	 */
	@GetMapping("/{firstName}/{lastName}")
	public List<PatientDto> getAllPatientsByName(@PathVariable String firstName, @PathVariable String lastName) {
		return patientService.getAllPatientsByName(firstName, lastName);
	}

	/**
	 * getPatientDetailsById method is used to return the Patients details based on
	 * Patient Id.
	 * 
	 * @param patientId
	 * @return patientDto
	 */
	@GetMapping("/{pId}")
	public PatientDto getPatientDetailsById(@PathVariable long pId) {
		return patientService.getPatientDetailsById(pId);
	}

	/**
	 * updatePatientDetails method is used to updated the already existing Patients
	 * details.
	 * 
	 * @param patientDto
	 * @return String
	 */
	@PutMapping("/")
	public String updatePatientDetails(@RequestBody PatientDto patientDto) {
		return patientService.updatePatientDetails(patientDto);
	}

	/**
	 * deletePatientDetails method is used to delete the Patients based on Patient
	 * Id.
	 * 
	 * @param patientId
	 * @return String
	 */
	@DeleteMapping("/{pId}")
	public String deletePatientDetails(@PathVariable long pId) {
		return patientService.deletePatientDetails(pId);
	}
}
