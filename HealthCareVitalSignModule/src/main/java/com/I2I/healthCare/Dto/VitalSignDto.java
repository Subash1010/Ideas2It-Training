package com.I2I.healthCare.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.I2I.healthCare.Model.VitalSignEntity;

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

	public static VitalSignEntity convertToVitalSignEntity(VitalSignDto vitalSignDto) {
		if (vitalSignDto == null) {
			return null;
		}
		VitalSignEntity vitalSignEntity = new VitalSignEntity();
		vitalSignEntity.setCheckupId(vitalSignDto.getCheckupId());
		vitalSignEntity.setPatientId(vitalSignDto.getPatientId());
		vitalSignEntity.setCheckupDate(vitalSignDto.getCheckupDate());
		vitalSignEntity.setPulseRate(vitalSignDto.getPulseRate());
		vitalSignEntity.setBloodPressure(vitalSignDto.getBloodPressure());
		vitalSignEntity.setTemperature(vitalSignDto.getTemperature());
		vitalSignEntity.setRespirationRate(vitalSignDto.getRespirationRate());
		vitalSignEntity.setBloodSugar(vitalSignDto.getBloodSugar());
		vitalSignEntity.setHeight(vitalSignDto.getHeight());
		vitalSignEntity.setWeight(vitalSignDto.getWeight());
		vitalSignEntity.setCreatedAt(vitalSignDto.getCreatedAt());
		vitalSignEntity.setCreatedBy(vitalSignDto.getCreatedBy());
		vitalSignEntity.setUpdateAt(vitalSignDto.getUpdateAt());
		vitalSignEntity.setUpdatedBy(vitalSignDto.getUpdatedBy());
		return vitalSignEntity;
	}

	public static VitalSignDto convertToVitalSignDto(VitalSignEntity vitalSignEntity) {
		if (vitalSignEntity == null) {
			return null;
		}
		VitalSignDto vitalSignDto = new VitalSignDto();
		vitalSignDto.setCheckupId(vitalSignEntity.getCheckupId());
		vitalSignDto.setPatientId(vitalSignEntity.getPatientId());
		vitalSignDto.setCheckupDate(vitalSignEntity.getCheckupDate());
		vitalSignDto.setPulseRate(vitalSignEntity.getPulseRate());
		vitalSignDto.setBloodPressure(vitalSignEntity.getBloodPressure());
		vitalSignDto.setTemperature(vitalSignEntity.getTemperature());
		vitalSignDto.setRespirationRate(vitalSignEntity.getRespirationRate());
		vitalSignDto.setBloodSugar(vitalSignEntity.getBloodSugar());
		vitalSignDto.setHeight(vitalSignEntity.getHeight());
		vitalSignDto.setWeight(vitalSignEntity.getWeight());
		vitalSignDto.setCreatedAt(vitalSignEntity.getCreatedAt());
		vitalSignDto.setCreatedBy(vitalSignEntity.getCreatedBy());
		vitalSignDto.setUpdateAt(vitalSignEntity.getUpdateAt());
		vitalSignDto.setUpdatedBy(vitalSignEntity.getUpdatedBy());
		return vitalSignDto;
	}
}
