package io.localstack.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import io.localstack.dto.AuditVitalSignDto;
import io.localstack.models.AuditVitalSign;
import io.localstack.models.AuditVitalSignModel;
import io.localstack.models.VitalSignModel;

public class VitalSignDataUtil {

	public static AuditVitalSign convertToVitalSignModel(AuditVitalSignDto vitalSignDto) {
		if (vitalSignDto == null) {
			return null;
		}
		AuditVitalSign auditVitalSign = new AuditVitalSign();
		auditVitalSign.setAuditId(UUID.randomUUID().toString());
		auditVitalSign.setDatabase(vitalSignDto.getDatabase());
		auditVitalSign.setTable(vitalSignDto.getTable());
		auditVitalSign.setType(vitalSignDto.getType());
		auditVitalSign.setTs(vitalSignDto.getTs());
		auditVitalSign.setXid(vitalSignDto.getXid());
		auditVitalSign.setCommit(vitalSignDto.isCommit());
		auditVitalSign.setLatestData(vitalSignDto.getData());
		auditVitalSign.setOldData(vitalSignDto.getOld());
		auditVitalSign.setLogDate(LocalDate.now().toString());
		auditVitalSign.setLogTime(LocalDateTime.now().toString());

		return auditVitalSign;
	}

	public static AuditVitalSignModel convertToAuditVitalSignModel(AuditVitalSignDto vitalSignDto) {
		if (vitalSignDto == null) {
			return null;
		}
		AuditVitalSignModel auditVitalSign = new AuditVitalSignModel();
		auditVitalSign.setAuditId(UUID.randomUUID().toString());
		auditVitalSign.setDatabase(vitalSignDto.getDatabase());
		auditVitalSign.setTable(vitalSignDto.getTable());
		auditVitalSign.setType(vitalSignDto.getType());
		auditVitalSign.setTs(vitalSignDto.getTs());
		auditVitalSign.setXid(vitalSignDto.getXid());
		auditVitalSign.setCommit(vitalSignDto.isCommit());
		auditVitalSign.setLatestData(getVitalSignData(vitalSignDto.getData()));
		auditVitalSign.setOldData(getVitalSignData(vitalSignDto.getOld()));
		auditVitalSign.setLogDate(LocalDate.now().toString());
		auditVitalSign.setLogTime(LocalDateTime.now().toString());

		return auditVitalSign;
	}

	private static VitalSignModel getVitalSignData(Map<String, String> vitalSignDto) {
		VitalSignModel vitalSignEntity = new VitalSignModel();
		vitalSignEntity.setCheckupId(vitalSignDto.get("checkup_id"));
		vitalSignEntity.setPatientId(vitalSignDto.get("patient"));
		vitalSignEntity.setCheckupDate(vitalSignDto.get("checkup_date"));
		vitalSignEntity.setPulseRate(vitalSignDto.get("pulse_rate"));
		vitalSignEntity.setBloodPressure(vitalSignDto.get("blood_pressure"));
		vitalSignEntity.setTemperature(vitalSignDto.get("temperature"));
		vitalSignEntity.setRespirationRate(vitalSignDto.get("respiration_rate"));
		vitalSignEntity.setBloodSugar(vitalSignDto.get("blood_sugar"));
		vitalSignEntity.setHeight(vitalSignDto.get("height"));
		vitalSignEntity.setWeight(vitalSignDto.get("weight"));
		vitalSignEntity.setCreatedAt(vitalSignDto.get("created_by"));
		vitalSignEntity.setCreatedBy(vitalSignDto.get("created_at"));
		vitalSignEntity.setUpdateAt(vitalSignDto.get("updated_by"));
		vitalSignEntity.setUpdatedBy(vitalSignDto.get("updated_at"));
		return vitalSignEntity;
	}
}
