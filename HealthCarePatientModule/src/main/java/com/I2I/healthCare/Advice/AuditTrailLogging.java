package com.I2I.healthCare.Advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.I2I.healthCare.Models.PatientEntity;

@Target({ ElementType.METHOD, ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditTrailLogging {

	public static PatientEntity oldValue = new PatientEntity();

}
