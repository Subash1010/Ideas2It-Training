package io.localstack.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import io.localstack.models.VitalSignModel;

public class VitalSignConversionUtil implements DynamoDBTypeConverter<String, VitalSignModel> {

	@Override
	public String convert(VitalSignModel object) {
		VitalSignModel vitalSignModel = (VitalSignModel) object;
		String dimension = null;
		try {
			if (vitalSignModel != null) {
				dimension = String.format("%s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x %s x%s x %s",
						vitalSignModel.getCheckupId(), vitalSignModel.getPatientId(), vitalSignModel.getCheckupDate(),
						vitalSignModel.getPulseRate(), vitalSignModel.getBloodPressure(),
						vitalSignModel.getTemperature(), vitalSignModel.getRespirationRate(),
						vitalSignModel.getBloodSugar(), vitalSignModel.getHeight(), vitalSignModel.getWeight(),
						vitalSignModel.getCreatedBy(), vitalSignModel.getCreatedAt(), vitalSignModel.getUpdatedBy(),
						vitalSignModel.getUpdateAt());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dimension;
	}

	@Override
	public VitalSignModel unconvert(String s) {

		VitalSignModel vitalSignModel = new VitalSignModel();
		try {
			if (s != null && s.length() != 0) {
				String[] data = s.split("x");
				vitalSignModel.setCheckupId(data[0].trim());
				vitalSignModel.setPatientId(data[1].trim());
				vitalSignModel.setCheckupDate(data[2].trim());
				vitalSignModel.setPulseRate(data[3].trim());
				vitalSignModel.setBloodPressure(data[4].trim());
				vitalSignModel.setTemperature(data[5].trim());
				vitalSignModel.setRespirationRate(data[6].trim());
				vitalSignModel.setBloodSugar(data[7].trim());
				vitalSignModel.setHeight(data[8].trim());
				vitalSignModel.setWeight(data[9].trim());
				vitalSignModel.setCreatedBy(data[10].trim());
				vitalSignModel.setCreatedAt(data[11].trim());
				vitalSignModel.setUpdatedBy(data[12].trim());
				vitalSignModel.setUpdateAt(data[13].trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vitalSignModel;
	}
}