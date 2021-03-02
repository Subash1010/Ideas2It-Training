package com.I2I.healthCare.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.I2I.healthCare.Dao.PatientDao;
import com.I2I.healthCare.Dto.PatientDto;
import com.I2I.healthCare.Models.PatientEntity;
import com.I2I.healthCare.Util.PatientDataUtil;

@Service
public class PatientServiceImpl implements PatientService {

	@Lazy
	@Autowired
	public PatientServiceImpl(PatientDao patientDao, PatientIndexService patientIndexService) {
		this.patientDao = patientDao;
		this.patientIndexService = patientIndexService;
	}

	private final PatientDao patientDao;

	private final PatientIndexService patientIndexService;

	Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

	@Override
	public PatientDto addPatient(PatientDto patientDto) {
		if (Objects.nonNull(patientDto)) {
			PatientEntity patientEntity = PatientDataUtil.convertToPatientEntity(patientDto);
			PatientEntity updatedPatientEntity = patientDao.addPatient(patientEntity);
			patientIndexService.save(PatientDataUtil.convertToPatientIndex(updatedPatientEntity));
			return PatientDataUtil.convertToPatientDto(updatedPatientEntity);
		}
		logger.error("Error in Addition of new record - Empty Record Can't be Added");
		return null;
	}

	@Override
	public List<PatientDto> getAllPatients() {
		List<PatientEntity> patientEntityList = patientDao.getAllPatientsName();
		if (CollectionUtils.isEmpty(patientEntityList)) {
			return new ArrayList<>();
		} else {
			return patientEntityList.stream().map(patientEntity -> PatientDataUtil.convertToPatientDto(patientEntity))
					.collect(Collectors.toList());
		}
	}

	@Override
	public PatientDto getPatientDetailsById(long pId) {
		Optional<PatientEntity> optionalPatientDetails = patientDao.getPatientDetailsById(pId);
		if (optionalPatientDetails.isPresent() && !optionalPatientDetails.isEmpty()) {
			PatientEntity patientEntity = optionalPatientDetails.get();
			return PatientDataUtil.convertToPatientDto(patientEntity);
		} else {
			return null;
		}
	}

	@Override
	public String updatePatientDetails(PatientDto patientDto) {
		if (Objects.nonNull(patientDto)) {
			PatientEntity patientEntity = PatientDataUtil.convertToPatientEntity(patientDto);
			return patientDao.updatePatientDetails(patientEntity);
		}
		logger.error("Error in Addition of new record - Empty Record Can't be Updated");
		return "Record not Updated";
	}

	@Override
	public String deletePatientDetails(long pId) {
		return patientDao.deletePatientDetails(pId);
	}

	@Override
	public List<String> getAllPatientsNameByAge(int age) {
		return patientDao.getAllPatientsNameByAge(age);
	}

	@Override
	public List<PatientDto> getAllPatientsByName(String firstName, String lastName) {
		List<PatientEntity> patientEntityList = patientDao.getPatientDetailsByName(firstName, lastName);
		if (CollectionUtils.isEmpty(patientEntityList)) {
			return new ArrayList<>();
		} else {
			return patientEntityList.stream().map(patientEntity -> PatientDataUtil.convertToPatientDto(patientEntity))
					.collect(Collectors.toList());
		}
	}

}
