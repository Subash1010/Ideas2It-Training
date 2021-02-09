package com.I2I.healthCare.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto implements Serializable {

	private static final long serialVersionUID = -1082119060818889157L;

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

}
