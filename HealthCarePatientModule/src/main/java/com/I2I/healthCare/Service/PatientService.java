package com.I2I.healthCare.Service;

import java.util.List;

import com.I2I.healthCare.Dto.PatientDto;

public interface PatientService {

	String addPatient(PatientDto patientDto);

	List<String> getAllPatientsName();

	PatientDto getPatientDetailsById(long pId);

	String updatePatientDetails(PatientDto patientDto);

	String deletePatientDetails(long pId);

	List<String> getAllPatientsNameByAge(int age);

	List<PatientDto> getAllPatientsByName(String firstName, String lastName);

}
