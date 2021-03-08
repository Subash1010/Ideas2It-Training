package com.I2I.healthCare.Advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.I2I.healthCare.Model.VitalSignEntity;

@Target({ ElementType.METHOD, ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditTrailLogging {

	public static VitalSignEntity oldValue = new VitalSignEntity();

}
