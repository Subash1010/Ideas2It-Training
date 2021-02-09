package com.I2I.healthCare.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.I2I.healthCare.Models.PatientEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto implements Serializable {

	private static final long serialVersionUID = -6303297266361893838L;

	private long patientId;

	private String firstName;

	private String lastName;

	private Date dob;

	private int age;

	private long phoneNumber;

	private long alternatePhoneNumber;

	private String gender;

	private String permanentAddress;

	private String communicationAddress;

	private String email;

	private Date initialAdmitDate;

	private Date latestAdmitDate;

	private String createdBy;

	private LocalDateTime createdAt;

	private String updatedBy;

	private LocalDateTime updatedAt;

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

}
