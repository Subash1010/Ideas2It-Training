package com.I2I.healthCare.Service;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.I2I.healthCare.Client.PatientClient;
import com.I2I.healthCare.Dao.VitalSignDao;
import com.I2I.healthCare.Dto.PatientDto;
import com.I2I.healthCare.Dto.VitalSignDto;
import com.I2I.healthCare.Model.VitalSignEntity;

@Service
public class VitalSignServiceImpl implements VitalSignService {

	@Lazy
	@Autowired
	public VitalSignServiceImpl(VitalSignDao vitalSignDao, PatientClient patientClient) {
		super();
		this.vitalSignDao = vitalSignDao;
		this.patientClient = patientClient;
	}

	private final VitalSignDao vitalSignDao;

	private final PatientClient patientClient;

	@Override
	public String addCheckupDetails(VitalSignDto vitalSignDto) {
		if (Objects.nonNull(vitalSignDto)) {
			VitalSignEntity vitalSignEntity = VitalSignDto.convertToVitalSignEntity(vitalSignDto);
			return vitalSignDao.addPatient(vitalSignEntity);
		}
		System.out.println("Error in Addition of new record - Empty Record Can't be Added");
		return "Record not Added";
	}

	@Override
	public VitalSignDto getCheckupDetails(long pId, Date checkUpDate) {
		return vitalSignDao.getCheckupDetails(pId, checkUpDate);
	}

	@Override
	public String updateVitalSign(long pId, Date checkUpDate, VitalSignDto vitalSignDto) {
		if (Objects.nonNull(vitalSignDto)) {
			VitalSignEntity vitalSignEntity = VitalSignDto.convertToVitalSignEntity(vitalSignDto);
			return vitalSignDao.updateVitalSign(pId, checkUpDate, vitalSignEntity);
		}
		System.out.println("Error in Updation of the record - Empty Record Can't be Updated");
		return "Record not Updated";
	}

	@Override
	public String deleteCheckup(long pId, Date checkUpDate) {
		return vitalSignDao.deleteCheckup(pId, checkUpDate);
	}

	@Override
	public PatientDto getPatientDetails(long pId) {
		return patientClient.getPatientDetailsById(pId);
	}

}
