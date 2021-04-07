package io.localstack.repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;

import io.localstack.config.DynamoConfig;
import io.localstack.models.AuditVitalSign;
import io.localstack.models.AuditVitalSignModel;

@Repository
public class LocalStackRepository {

	@Autowired
	public LocalStackRepository(DynamoConfig dynamoConfig) {
		super();
		this.dynamoConfig = dynamoConfig;
	}

	private final DynamoConfig dynamoConfig;

	/**
	 * Creates a New Record in dynamodb table
	 * 
	 * @param vitalSignModel
	 * @return Row ID generated using uuid
	 */
	public String insertRecord(AuditVitalSign vitalSign) {
		try {

			DynamoDB dynamoDB = dynamoConfig.getDynamoDB();
			Table table = dynamoDB.getTable("AuditVitalSign13");
			PutItemOutcome outcome = table.putItem(new Item().withPrimaryKey("auditId", vitalSign.getAuditId())
					.with("database", vitalSign.getDatabase()).with("table", vitalSign.getTable())
					.with("type", vitalSign.getType()).with("ts", vitalSign.getTs()).with("xid", vitalSign.getXid())
					.with("commit", vitalSign.isCommit()).withMap("latestData", vitalSign.getLatestData())
					.withMap("oldData", vitalSign.getOldData()).with("logDate", vitalSign.getLogDate())
					.with("logTime", vitalSign.getLogTime()));

			if (Objects.nonNull(outcome)) {
				return "Success";
			} else {
				return "Failure";
			}
		} catch (DynamoDBMappingException exception) {
			System.out.println(exception);
		}
		return "";

	}

	public List<String> fetchAllRecord() {
		List<String> itemList = new ArrayList<>();
		DynamoDB dynamoDB = dynamoConfig.getDynamoDB();
		Table table = dynamoDB.getTable("AuditVitalSign13");
		ScanSpec scanSpec = new ScanSpec();

		try {
			ItemCollection<ScanOutcome> items = table.scan(scanSpec);

			Iterator<Item> iter = items.iterator();
			while (iter.hasNext()) {
				Item item = iter.next();
				itemList.add(item.toJSON());
			}

		} catch (Exception e) {
			System.err.println("Unable to scan the table:");
			System.err.println(e.getMessage());
		}
		return itemList;
	}

	public String insertAuditRecord(AuditVitalSignModel vitalSign) {
		try {

			DynamoDBMapper dynamoDBMapper = dynamoConfig.getDynamoDBMapper();
			dynamoDBMapper.save(vitalSign);
		} catch (DynamoDBMappingException exception) {
			System.out.println(exception);
		}
		return "";

	}

}
