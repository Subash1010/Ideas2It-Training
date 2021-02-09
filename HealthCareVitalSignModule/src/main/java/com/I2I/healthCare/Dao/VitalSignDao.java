package com.I2I.healthCare.Dao;

import java.util.Date;

import com.I2I.healthCare.Dto.VitalSignDto;
import com.I2I.healthCare.Model.VitalSignEntity;

public interface VitalSignDao {

	String addPatient(VitalSignEntity vitalSignEntity);

	VitalSignDto getCheckupDetails(long pId, Date checkUpDate);

	String updateVitalSign(long pId, Date checkUpDate, VitalSignEntity vitalSignEntity);

	String deleteCheckup(long pId, Date checkUpDate);

}
