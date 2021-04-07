package io.localstack.models;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import io.localstack.util.VitalSignConversionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "AuditVitalSign14")
public class AuditVitalSignModel implements Serializable {

	private static final long serialVersionUID = 2343201328285573125L;

	private String auditId;

	private String database;

	private String table;

	private String type;

	private long ts;

	private int xid;

	private boolean commit;

	private VitalSignModel latestData;

	private VitalSignModel oldData;

	private String logDate;

	private String logTime;

	@DynamoDBHashKey(attributeName = "auditId")
	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	@DynamoDBRangeKey(attributeName = "database")
	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	@DynamoDBAttribute(attributeName = "table")
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@DynamoDBAttribute(attributeName = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@DynamoDBAttribute(attributeName = "ts")
	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	@DynamoDBAttribute(attributeName = "xid")
	public int getXid() {
		return xid;
	}

	public void setXid(int xid) {
		this.xid = xid;
	}

	@DynamoDBAttribute(attributeName = "commit")
	public boolean isCommit() {
		return commit;
	}

	public void setCommit(boolean commit) {
		this.commit = commit;
	}

	@DynamoDBTypeConverted(converter = VitalSignConversionUtil.class)
	@DynamoDBAttribute(attributeName = "latestData")
	public VitalSignModel getLatestData() {
		return latestData;
	}

	@DynamoDBAttribute(attributeName = "latestData")
	public void setLatestData(VitalSignModel latestData) {
		this.latestData = latestData;
	}

	@DynamoDBTypeConverted(converter = VitalSignConversionUtil.class)
	@DynamoDBAttribute(attributeName = "oldData")
	public VitalSignModel getOldData() {
		return oldData;
	}

	@DynamoDBAttribute(attributeName = "oldData")
	public void setOldData(VitalSignModel oldData) {
		this.oldData = oldData;
	}

	@DynamoDBAttribute(attributeName = "logDate")
	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	@DynamoDBAttribute(attributeName = "logTime")
	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

}
