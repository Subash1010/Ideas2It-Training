package com.I2I.healthCare.Dao;

import java.util.List;
import java.util.Optional;

import com.I2I.healthCare.Models.PatientEntity;

public interface PatientDao {

	String addPatient(PatientEntity patientEntity);

	List<PatientEntity> getAllPatientsName();

	Optional<PatientEntity> getPatientDetailsById(long pId);

	String updatePatientDetails(PatientEntity patientEntity);

	String deletePatientDetails(long pId);

	List<String> getAllPatientsNameByAge(int age);

	List<PatientEntity> getPatientDetailsByName(String firstName, String lastName);

}
