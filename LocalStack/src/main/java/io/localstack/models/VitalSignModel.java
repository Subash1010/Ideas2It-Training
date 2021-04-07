package io.localstack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSignModel {

	private String checkupId;

	private String patientId;

	private String checkupDate;

	private String pulseRate;

	private String bloodPressure;

	private String temperature;

	private String respirationRate;

	private String bloodSugar;

	private String height;

	private String weight;

	private String createdBy;

	private String createdAt;

	private String updatedBy;

	private String updateAt;

}
