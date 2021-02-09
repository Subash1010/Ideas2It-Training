package com.I2I.healthCare.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "vitalsign")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSignEntity implements Serializable {

	private static final long serialVersionUID = 6313577177732488030L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vital_sign_id_generator")
	@SequenceGenerator(initialValue = 1, allocationSize = 100, name = "vital_sign_id_generator")
	@Column(name = "checkup_id")
	private long checkupId;

	@Column(name = "patient_id")
	private long patientId;

	@Column(name = "checkup_date")
	private Date checkupDate;

	@Column(name = "pulse_rate")
	private float pulseRate;

	@Column(name = "blood_pressure")
	private float bloodPressure;

	@Column(name = "temperature")
	private float temperature;

	@Column(name = "respiration_rate")
	private float respirationRate;

	@Column(name = "blood_sugar")
	private float bloodSugar;

	@Column(name = "height")
	private float height;

	@Column(name = "weight")
	private float weight;

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
	private LocalDateTime updateAt;
}
