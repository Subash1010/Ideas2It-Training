package com.I2I.healthCare.Model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.I2I.healthCare.Dto.DataDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "RabbitAudit")
public class RabbitAuditEntity implements Serializable {

	private static final long serialVersionUID = -3838469704956797352L;

	@Id
	private String id;

	private String userName;

	private String serviceName;

	private String methodName;

	private String request;

	private DataDto data;

	private String action;

	private String logDate;

	private String logTime;
}
