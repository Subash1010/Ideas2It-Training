package com.I2I.healthCare.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(value = {
		@NamedQuery(name = "PatientEntity.getAllPatientsNameByAge", query = "Select CONCAT(p.firstName, '_', p.lastName) From PatientEntity as p Where p.age = ?1 order by p.firstName"),
		@NamedQuery(name = "PatientEntity.getPatientInfoByName", query = "Select p From PatientEntity as p Where p.firstName = ?1 And p.lastName = ?2  order by p.firstName") })
public class PatientEntity implements Serializable {

	private static final long serialVersionUID = -1660387656600048948L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pId_Generator")
	@SequenceGenerator(initialValue = 1, name = "pId_Generator")
	@Column(name = "patient_id")
	private long patientId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "age")
	private int age;

	@Column(name = "phone_number")
	private long phoneNumber;

	@Column(name = "alternate_phone_number")
	private long alternatePhoneNumber;

	@Column(name = "gender")
	private String gender;

	@Column(name = "permanent_address")
	private String permanentAddress;

	@Column(name = "communication_address")
	private String communicationAddress;

	@Column(name = "email")
	private String email;

	@Column(name = "initial_admit_date")
	private Date initialAdmitDate;

	@Column(name = "latest_admit_date")
	private Date latestAdmitDate;

	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;

	@CreatedDate
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
