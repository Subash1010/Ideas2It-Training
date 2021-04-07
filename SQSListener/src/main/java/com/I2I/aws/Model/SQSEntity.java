package com.I2I.aws.Model;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "SQSMessage")
public class SQSEntity implements Serializable {

	private static final long serialVersionUID = -3838469704956797352L;

	@Id
	private String auditId;

	private String database;

	private String table;

	private String type;

	private long ts;

	private int xid;

	private boolean commit;

	private Map<String, String> oldData;

	private Map<String, String> latestData;

	private String logDate;

	private String logTime;

}
