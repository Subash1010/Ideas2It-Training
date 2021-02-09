package com.I2I.healthCare.Dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Dto.VitalSignDto;
import com.I2I.healthCare.Model.VitalSignEntity;
import com.I2I.healthCare.Repository.VitalSignRepository;

@Repository
public class VitalSignDaoImpl implements VitalSignDao {

	@Lazy
	@Autowired
	public VitalSignDaoImpl(VitalSignRepository vitalSignRepository) {
		super();
		this.vitalSignRepository = vitalSignRepository;
	}

	private final VitalSignRepository vitalSignRepository;

	@Override
	public String addPatient(VitalSignEntity vitalSignEntity) {
		try {
			vitalSignRepository.save(vitalSignEntity);
		} catch (Exception exception) {
			System.out.println("Error in insertion of new record" + exception);
			return "Record not Inserted";
		}
		return "Added new record Successfully!!!";
	}

	@Override
	public VitalSignDto getCheckupDetails(long pId, Date checkUpDate) {
		VitalSignEntity vitalSignEntity = vitalSignRepository.findByPatientIdAndCheckupDate(pId, checkUpDate);
		if (Objects.nonNull(vitalSignEntity)) {
			return VitalSignDto.convertToVitalSignDto(vitalSignEntity);
		} else {
			return null;
		}
	}

	@Override
	public String updateVitalSign(long pId, Date checkUpDate, VitalSignEntity vitalSignEntity) {
		try {
			VitalSignDto existingVitalSignDto = getCheckupDetails(pId, checkUpDate);
			VitalSignEntity updatedVitalSignEntity = new VitalSignEntity();
			if (Objects.nonNull(existingVitalSignDto)) {
				updatedVitalSignEntity.setCheckupId(vitalSignEntity.getCheckupId());
				updatedVitalSignEntity.setPatientId(vitalSignEntity.getPatientId());
				updatedVitalSignEntity.setCheckupDate(vitalSignEntity.getCheckupDate());
				updatedVitalSignEntity.setPulseRate(vitalSignEntity.getPulseRate());
				updatedVitalSignEntity.setBloodPressure(vitalSignEntity.getBloodPressure());
				updatedVitalSignEntity.setTemperature(vitalSignEntity.getTemperature());
				updatedVitalSignEntity.setRespirationRate(vitalSignEntity.getRespirationRate());
				updatedVitalSignEntity.setBloodSugar(vitalSignEntity.getBloodSugar());
				updatedVitalSignEntity.setHeight(vitalSignEntity.getHeight());
				updatedVitalSignEntity.setWeight(vitalSignEntity.getWeight());
				updatedVitalSignEntity.setCreatedAt(vitalSignEntity.getCreatedAt());
				updatedVitalSignEntity.setCreatedBy(vitalSignEntity.getCreatedBy());
				updatedVitalSignEntity.setUpdatedBy(vitalSignEntity.getUpdatedBy());
				updatedVitalSignEntity.setUpdateAt(LocalDateTime.now());
				vitalSignRepository.save(updatedVitalSignEntity);
			} else {
				return "No Record is found for Updation";
			}
		} catch (Exception exception) {
			System.out.println("Error in Updation of the record" + exception);
			return "Record not Updated";
		}
		return "Record Updated Successfully!!!";
	}

	@Override
	public String deleteCheckup(long pId, Date checkUpDate) {
		try {
			VitalSignDto existingVitalSignDto = getCheckupDetails(pId, checkUpDate);
			if (Objects.nonNull(existingVitalSignDto)) {
				vitalSignRepository.deleteById(existingVitalSignDto.getCheckupId());
			} else {
				return "Record Not Found For Deletion.";
			}
		} catch (Exception exception) {
			System.out.println("Error in Deletion of the record" + exception);
			return "Record not Deleted";
		}
		return "Record Deleted Successfully!!!";
	}

}
