package com.I2I.healthCare.Util;

import com.I2I.healthCare.Dto.PatientDto;
import com.I2I.healthCare.Index.PatientIndex;
import com.I2I.healthCare.Models.PatientEntity;

public class PatientDataUtil {
	public static PatientDto convertToPatientDto(PatientEntity patientEntity) {
		if (patientEntity == null) {
			return null;
		}
		PatientDto patientDto = new PatientDto();
		patientDto.setPatientId(patientEntity.getPatientId());
		patientDto.setFirstName(patientEntity.getFirstName());
		patientDto.setLastName(patientEntity.getLastName());
		patientDto.setDob(patientEntity.getDob());
		patientDto.setAge(patientEntity.getAge());
		patientDto.setPhoneNumber(patientEntity.getPhoneNumber());
		patientDto.setAlternatePhoneNumber(patientEntity.getAlternatePhoneNumber());
		patientDto.setGender(patientEntity.getGender());
		patientDto.setPermanentAddress(patientEntity.getPermanentAddress());
		patientDto.setCommunicationAddress(patientEntity.getCommunicationAddress());
		patientDto.setEmail(patientEntity.getEmail());
		patientDto.setInitialAdmitDate(patientEntity.getInitialAdmitDate());
		patientDto.setLatestAdmitDate(patientEntity.getLatestAdmitDate());
		patientDto.setCreatedAt(patientEntity.getCreatedAt());
		patientDto.setCreatedBy(patientEntity.getCreatedBy());
		patientDto.setUpdatedBy(patientEntity.getUpdatedBy());
		patientDto.setUpdatedAt(patientEntity.getUpdatedAt());

		return patientDto;

	}

	public static PatientEntity convertToPatientEntity(PatientDto patientDto) {
		if (patientDto == null) {
			return null;
		}
		PatientEntity patientEntity = new PatientEntity();
		patientEntity.setPatientId(patientDto.getPatientId());
		patientEntity.setFirstName(patientDto.getFirstName());
		patientEntity.setLastName(patientDto.getLastName());
		patientEntity.setDob(patientDto.getDob());
		patientEntity.setAge(patientDto.getAge());
		patientEntity.setPhoneNumber(patientDto.getPhoneNumber());
		patientEntity.setAlternatePhoneNumber(patientDto.getAlternatePhoneNumber());
		patientEntity.setGender(patientDto.getGender());
		patientEntity.setPermanentAddress(patientDto.getPermanentAddress());
		patientEntity.setCommunicationAddress(patientDto.getCommunicationAddress());
		patientEntity.setEmail(patientDto.getEmail());
		patientEntity.setInitialAdmitDate(patientDto.getInitialAdmitDate());
		patientEntity.setLatestAdmitDate(patientDto.getLatestAdmitDate());
		patientEntity.setCreatedAt(patientDto.getCreatedAt());
		patientEntity.setCreatedBy(patientDto.getCreatedBy());
		patientEntity.setUpdatedBy(patientDto.getUpdatedBy());
		patientEntity.setUpdatedAt(patientDto.getUpdatedAt());

		return patientEntity;
	}

	public static PatientIndex convertToPatientIndex(PatientEntity patientEntity) {
		if (patientEntity == null) {
			return null;
		}
		PatientIndex patientIndex = new PatientIndex();
		patientIndex.setPatientId(patientEntity.getPatientId());
		patientIndex.setFirstName(patientEntity.getFirstName());
		patientIndex.setLastName(patientEntity.getLastName());
		patientIndex.setDob(patientEntity.getDob());
		patientIndex.setAge(patientEntity.getAge());
		patientIndex.setPhoneNumber(patientEntity.getPhoneNumber());
		patientIndex.setAlternatePhoneNumber(patientEntity.getAlternatePhoneNumber());
		patientIndex.setGender(patientEntity.getGender());
		patientIndex.setPermanentAddress(patientEntity.getPermanentAddress());
		patientIndex.setCommunicationAddress(patientEntity.getCommunicationAddress());
		patientIndex.setEmail(patientEntity.getEmail());
		patientIndex.setInitialAdmitDate(patientEntity.getInitialAdmitDate());
		patientIndex.setLatestAdmitDate(patientEntity.getLatestAdmitDate());

		return patientIndex;

	}
}
