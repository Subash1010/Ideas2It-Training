package com.I2I.healthCare.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSignDto implements Serializable {

	private static final long serialVersionUID = 3640201391320286975L;

	@ApiModelProperty(notes = "Check Up Id of the Patient", name = "checkupId", required = true)
	private long checkupId;

	@ApiModelProperty(notes = "Unique Id of the Patient", name = "patientId", required = true)
	private long patientId;

	@ApiModelProperty(notes = "Date of the Checkup", name = "checkupDate", required = true)
	private Date checkupDate;

	@ApiModelProperty(notes = "Pulse Rate of the Patient on that Checkup date", name = "pulseRate", required = false)
	private float pulseRate;

	@ApiModelProperty(notes = "Blood Pressure of the Patient on that Checkup date", name = "bloodPressure", required = false)
	private float bloodPressure;

	@ApiModelProperty(notes = "Temperature of the Patient on that Checkup date", name = "temperature", required = false)
	private float temperature;

	@ApiModelProperty(notes = "Respiration Rate of the Patient on that Checkup date", name = "respirationRate", required = false)
	private float respirationRate;

	@ApiModelProperty(notes = "Blood Sugar Level of the Patient on that Checkup date", name = "bloodSugar", required = false)
	private float bloodSugar;

	@ApiModelProperty(notes = "Height of the Patient on that Checkup date", name = "height", required = false)
	private float height;

	@ApiModelProperty(notes = "Weight of the Patient on that Checkup date", name = "weight", required = false)
	private float weight;

	@ApiModelProperty(notes = "User who Created the entity", name = "createdBy", required = true)
	private String createdBy;

	private LocalDateTime createdAt;

	@ApiModelProperty(notes = "User who Updated the entity", name = "updatedBy", required = true)
	private String updatedBy;

	private LocalDateTime updateAt;

}
