package com.I2I.healthCare.Dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Models.PatientEntity;
import com.I2I.healthCare.Repository.PatientRepository;

@Repository
public class PatientDaoImpl implements PatientDao {

	@Lazy
	@Autowired
	public PatientDaoImpl(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	private final PatientRepository patientRepository;

	@Override
	public String addPatient(PatientEntity patientEntity) {
		try {
			patientRepository.save(patientEntity);
		} catch (Exception exception) {
			System.out.println("Error in insertion of new record" + exception);
			return "Record not Inserted";
		}
		return "Added new record Successfully!!!";
	}

	@Override
	public List<PatientEntity> getAllPatientsName() {
		return patientRepository.findAll();
	}

	@Override
	public Optional<PatientEntity> getPatientDetailsById(long pId) {
		return patientRepository.findById(pId);
	}

	@Override
	public String updatePatientDetails(PatientEntity patientEntity) {
		try {
			PatientEntity existingPatientEntity = getPatientDetailsById(patientEntity.getPatientId()).orElse(null);
			if (Objects.nonNull(existingPatientEntity)) {
				existingPatientEntity.setPatientId(patientEntity.getPatientId());
				existingPatientEntity.setFirstName(patientEntity.getFirstName());
				existingPatientEntity.setLastName(patientEntity.getLastName());
				existingPatientEntity.setDob(patientEntity.getDob());
				existingPatientEntity.setAge(patientEntity.getAge());
				existingPatientEntity.setPhoneNumber(patientEntity.getPhoneNumber());
				existingPatientEntity.setAlternatePhoneNumber(patientEntity.getAlternatePhoneNumber());
				existingPatientEntity.setGender(patientEntity.getGender());
				existingPatientEntity.setPermanentAddress(patientEntity.getPermanentAddress());
				existingPatientEntity.setCommunicationAddress(patientEntity.getCommunicationAddress());
				existingPatientEntity.setEmail(patientEntity.getEmail());
				existingPatientEntity.setInitialAdmitDate(patientEntity.getInitialAdmitDate());
				existingPatientEntity.setLatestAdmitDate(patientEntity.getLatestAdmitDate());
				existingPatientEntity.setCreatedAt(patientEntity.getCreatedAt());
				existingPatientEntity.setCreatedBy(patientEntity.getCreatedBy());
				existingPatientEntity.setUpdatedBy(patientEntity.getUpdatedBy());
				existingPatientEntity.setUpdatedAt(LocalDateTime.now());
				patientRepository.save(existingPatientEntity);
			} else {
				return "No Record is found for Updation";
			}
		} catch (Exception exception) {
			System.out.println("Error in Updation of the record" + exception);
			return "Record not Updated";
		}
		return "Record Updated Successfully!!!";
	}

	@Override
	public String deletePatientDetails(long pId) {
		try {
			patientRepository.deleteById(pId);
		} catch (Exception exception) {
			System.out.println("Error in Deletion of the record" + exception);
			return "Record not Deleted";
		}
		return "Record Deleted Successfully!!!";
	}

	@Override
	public List<String> getAllPatientsNameByAge(int age) {
		return patientRepository.getAllPatientsNameByAge(age);
	}

	@Override
	public List<PatientEntity> getPatientDetailsByName(String firstName, String lastName) {
		return patientRepository.getPatientInfoByName(firstName, lastName);
	}

}
