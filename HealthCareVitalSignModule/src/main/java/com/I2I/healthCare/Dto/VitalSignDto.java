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
public class VitalSignDto implements Serializable {

	private static final long serialVersionUID = 3640201391320286975L;

	private long checkupId;

	private long patientId;

	private Date checkupDate;

	private float pulseRate;

	private float bloodPressure;

	private float temperature;

	private float respirationRate;

	private float bloodSugar;

	private float height;

	private float weight;

	private String createdBy;

	private LocalDateTime createdAt;

	private String updatedBy;

	private LocalDateTime updateAt;

}
