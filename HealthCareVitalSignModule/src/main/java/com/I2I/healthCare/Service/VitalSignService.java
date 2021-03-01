package com.I2I.healthCare.Service;

import java.util.Date;

import com.I2I.healthCare.Dto.PatientDto;
import com.I2I.healthCare.Dto.VitalSignDto;

public interface VitalSignService {

	VitalSignDto addCheckupDetails(VitalSignDto vitalSignDto);

	VitalSignDto getCheckupDetails(long pId, Date date);

	VitalSignDto updateVitalSign(long pId, Date date, VitalSignDto vitalSignDto);

	String deleteCheckup(long pId, Date date);

	PatientDto getPatientDetails(long pId);

}
