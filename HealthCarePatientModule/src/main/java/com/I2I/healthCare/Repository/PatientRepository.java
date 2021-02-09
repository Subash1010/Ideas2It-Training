package com.I2I.healthCare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Models.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

	List<String> getAllPatientsNameByAge(int age);

	List<PatientEntity> getPatientInfoByName(String firstName, String Lastname);

}
