package io.localstack.models;

import java.io.Serializable;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "AuditVitalSign12")
public class AuditVitalSign implements Serializable {

	private static final long serialVersionUID = 2343201328285573125L;

	@DynamoDBHashKey(attributeName = "auditId")
	private String auditId;

	@DynamoDBAttribute(attributeName = "database")
	private String database;

	@DynamoDBAttribute(attributeName = "table")
	private String table;

	@DynamoDBAttribute(attributeName = "type")
	private String type;

	@DynamoDBAttribute(attributeName = "ts")
	private long ts;

	@DynamoDBAttribute(attributeName = "xid")
	private int xid;

	@DynamoDBAttribute(attributeName = "commit")
	private boolean commit;

	@DynamoDBAttribute(attributeName = "latestData")
	private Map<String, String> latestData;

	@DynamoDBAttribute(attributeName = "oldData")
	private Map<String, String> oldData;

	@DynamoDBAttribute(attributeName = "logDate")
	private String logDate;

	@DynamoDBAttribute(attributeName = "logTime")
	private String logTime;
}
