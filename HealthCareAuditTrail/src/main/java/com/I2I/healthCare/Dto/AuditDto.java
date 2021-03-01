package com.I2I.healthCare.Dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditDto implements Serializable {

	private static final long serialVersionUID = -3838469704956797352L;

	private String userName;

	private String serviceName;

	private String methodName;

	private String request;

	private String beforeValue;

	private String action;

	private String logDate;

	private String logTime;

}
