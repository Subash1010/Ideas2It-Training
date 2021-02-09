package com.I2I.healthCare.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Model.VitalSignEntity;

@Repository
public interface VitalSignRepository extends JpaRepository<VitalSignEntity, Long> {

	VitalSignEntity findByPatientIdAndCheckupDate(long pId, Date checkupDate);
}
