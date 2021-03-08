package com.I2I.healthCare.Service;

import java.util.List;

import com.I2I.healthCare.Dto.PatientDto;

public interface PatientService {

	PatientDto addPatient(PatientDto patientDto);

	List<PatientDto> getAllPatients();

	PatientDto getPatientDetailsById(long pId);

	PatientDto updatePatientDetails(PatientDto patientDto);

	String deletePatientDetails(long pId);

	List<String> getAllPatientsNameByAge(int age);

	List<PatientDto> getAllPatientsByName(String firstName, String lastName);

}
