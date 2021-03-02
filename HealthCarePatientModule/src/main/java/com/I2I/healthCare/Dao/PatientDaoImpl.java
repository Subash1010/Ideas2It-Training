package com.I2I.healthCare.Dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger logger = LoggerFactory.getLogger(PatientDaoImpl.class);

	@Override
	public PatientEntity addPatient(PatientEntity patientEntity) {
		try {
			return patientRepository.save(patientEntity);
		} catch (Exception exception) {
			logger.error("Error in insertion of new record" + exception);
			return null;
		}
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
				patientRepository.save(existingPatientEntity);
			} else {
				return "No Record is found for Updation";
			}
		} catch (Exception exception) {
			logger.error("Error in Updation of the record" + exception);
			return "Record not Updated";
		}
		return "Record Updated Successfully!!!";
	}

	@Override
	public String deletePatientDetails(long pId) {
		try {
			Optional<PatientEntity> existingRecord = getPatientDetailsById(pId);
			if (existingRecord.isPresent() && !existingRecord.isEmpty()) {
				patientRepository.deleteById(pId);
			} else {
				return "Record with Patient Id " + pId + " is not found for Deletion";
			}
		} catch (Exception exception) {
			logger.error("Error in Deletion of the record with Patient Id " + pId + StringUtils.EMPTY + exception);
			return "Record with Patient Id  " + pId + "is not Deleted";
		}

		return "Record with Patient Id " + pId + " Deleted Successfully!!!";
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
