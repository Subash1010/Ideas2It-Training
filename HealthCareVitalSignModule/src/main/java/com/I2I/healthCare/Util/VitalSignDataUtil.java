package com.I2I.healthCare.Util;

import java.sql.Date;
import java.util.Calendar;

import com.I2I.healthCare.Dto.VitalSignDto;
import com.I2I.healthCare.Model.VitalSignEntity;

public class VitalSignDataUtil {
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
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vitalSignEntity.getCheckupDate());
		String formatedNewDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DATE);
		vitalSignDto.setCheckupDate(Date.valueOf(formatedNewDate));
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
