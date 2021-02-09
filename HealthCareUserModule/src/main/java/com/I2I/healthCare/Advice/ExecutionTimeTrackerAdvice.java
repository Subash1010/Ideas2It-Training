package com.I2I.healthCare.Advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeTrackerAdvice {

	Logger logger = LoggerFactory.getLogger(ExecutionTimeTrackerAdvice.class);

	@Around("@annotation(com.I2I.healthCare.Advice.TrackExecutionTime)")
	public Object executionTime(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object obj = pjp.proceed();
		long endTime = System.currentTimeMillis();
		logger.info("Method Name " + pjp.getSignature() + "took " + (endTime - startTime) + " for Execution.");
		return obj;
	}
}
