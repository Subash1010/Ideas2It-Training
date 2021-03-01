package com.I2I.healthCare.Index;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "patient_index", shards = 2)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientIndex implements Serializable {

	private static final long serialVersionUID = -6303297266361893838L;

	@Id
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

}
