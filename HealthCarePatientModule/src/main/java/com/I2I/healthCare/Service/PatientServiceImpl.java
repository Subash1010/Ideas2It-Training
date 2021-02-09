package com.I2I.healthCare.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.I2I.healthCare.Dao.PatientDao;
import com.I2I.healthCare.Dto.PatientDto;
import com.I2I.healthCare.Models.PatientEntity;

@Service
public class PatientServiceImpl implements PatientService {

	@Lazy
	@Autowired
	public PatientServiceImpl(PatientDao patientDao) {
		super();
		this.patientDao = patientDao;
	}

	private final PatientDao patientDao;

	@Override
	public String addPatient(PatientDto patientDto) {
		if (Objects.nonNull(patientDto)) {
			PatientEntity patientEntity = PatientDto.convertToPatientEntity(patientDto);
			return patientDao.addPatient(patientEntity);
		}
		System.out.println("Error in Addition of new record - Empty Record Can't be Added");
		return "Record not Added";
	}

	@Override
	public List<String> getAllPatientsName() {
		List<PatientEntity> patientEntityList = patientDao.getAllPatientsName();
		if (CollectionUtils.isEmpty(patientEntityList)) {
			return new ArrayList<>();
		} else {
			return patientEntityList.stream()
					.map(patientEntity -> patientEntity.getFirstName() + "_" + patientEntity.getLastName())
					.collect(Collectors.toList());
		}
	}

	@Override
	public PatientDto getPatientDetailsById(long pId) {
		Optional<PatientEntity> optionalPatientDetails = patientDao.getPatientDetailsById(pId);
		if (optionalPatientDetails.isPresent() && !optionalPatientDetails.isEmpty()) {
			PatientEntity patientEntity = optionalPatientDetails.get();
			return PatientDto.convertToPatientDto(patientEntity);
		} else {
			return null;
		}
	}

	@Override
	public String updatePatientDetails(PatientDto patientDto) {
		if (Objects.nonNull(patientDto)) {
			PatientEntity patientEntity = PatientDto.convertToPatientEntity(patientDto);
			return patientDao.updatePatientDetails(patientEntity);
		}
		System.out.println("Error in Addition of new record - Empty Record Can't be Updated");
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
			return patientEntityList.stream().map(patientEntity -> PatientDto.convertToPatientDto(patientEntity))
					.collect(Collectors.toList());
		}
	}

}
