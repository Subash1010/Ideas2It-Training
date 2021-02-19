package com.I2I.healthCare.Dao;

import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Dto.VitalSignDto;
import com.I2I.healthCare.Model.VitalSignEntity;
import com.I2I.healthCare.Repository.VitalSignRepository;
import com.I2I.healthCare.Util.VitalSignDataUtil;

@Repository
public class VitalSignDaoImpl implements VitalSignDao {

	@Lazy
	@Autowired
	public VitalSignDaoImpl(VitalSignRepository vitalSignRepository) {
		super();
		this.vitalSignRepository = vitalSignRepository;
	}

	private final VitalSignRepository vitalSignRepository;

	Logger logger = LoggerFactory.getLogger(VitalSignDaoImpl.class);

	@Override
	public String addPatient(VitalSignEntity vitalSignEntity) {
		try {
			vitalSignRepository.save(vitalSignEntity);
		} catch (Exception exception) {
			logger.error("Error in insertion of new record" + exception);
			return "Record not Inserted";
		}
		return "Added new record Successfully!!!";
	}

	@Override
	public VitalSignDto getCheckupDetails(long pId, Date checkUpDate) {
		VitalSignEntity vitalSignEntity = vitalSignRepository.findByPatientIdAndCheckupDate(pId, checkUpDate);
		if (Objects.nonNull(vitalSignEntity)) {
			return VitalSignDataUtil.convertToVitalSignDto(vitalSignEntity);
		} else {
			return null;
		}
	}

	@Override
	public String updateVitalSign(long pId, Date checkUpDate, VitalSignEntity vitalSignEntity) {
		try {
			VitalSignDto existingVitalSignDto = getCheckupDetails(pId, checkUpDate);
			if (Objects.nonNull(existingVitalSignDto)) {
				vitalSignRepository.save(vitalSignEntity);
			} else {
				return "No Record is found for Updation";
			}
		} catch (Exception exception) {
			logger.error("Error in Updation of the record" + exception);
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
			logger.error("Error in Deletion of the record" + exception);
			return "Record not Deleted";
		}
		return "Record Deleted Successfully!!!";
	}

}
