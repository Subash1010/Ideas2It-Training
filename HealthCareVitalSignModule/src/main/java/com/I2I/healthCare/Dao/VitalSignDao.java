package com.I2I.healthCare.Dao;

import java.util.Date;
import java.util.List;

import com.I2I.healthCare.Dto.VitalSignDto;
import com.I2I.healthCare.Model.VitalSignEntity;

public interface VitalSignDao {

	VitalSignEntity addPatient(VitalSignEntity vitalSignEntity);

	VitalSignDto getCheckupDetails(long pId, Date checkUpDate);

	VitalSignEntity updateVitalSign(long pId, Date checkUpDate, VitalSignEntity vitalSignEntity);

	String deleteCheckup(long pId, Date checkUpDate);

	List<VitalSignDto> getCheckupDetails();

}
