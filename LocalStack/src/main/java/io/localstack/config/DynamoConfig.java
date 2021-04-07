package io.localstack.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DynamoConfig {

	public DynamoConfig(@Value("${aws.local.endpoint:#{null}}") String awsEndpoint) {
		super();
		this.awsEndpoint = awsEndpoint;
	}

	private final String awsEndpoint;

	@Bean
	public DynamoDBMapper getDynamoDBMapper() {
		try {
			AmazonDynamoDB client = null;
			if (awsEndpoint != null) {
				client = AmazonDynamoDBClientBuilder.standard()
						.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, "us-east-1"))
						.build();
			}
			DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);
			return dynamoDBMapper;
		} catch (AmazonDynamoDBException ex) {
			log.error("Invalid url {}", awsEndpoint);
			throw new IllegalStateException("Invalid url " + awsEndpoint, ex);
		}
	}

	@Bean
	public void createTable() throws InterruptedException {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		if (awsEndpoint != null) {
			client = AmazonDynamoDBClientBuilder.standard()
					.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, "us-east-1"))
					.build();
		}
		DynamoDB dynamoDB = new DynamoDB(client);

		List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
		attributeDefinitions.add(new AttributeDefinition().withAttributeName("auditId").withAttributeType("S"));
		attributeDefinitions.add(new AttributeDefinition().withAttributeName("database").withAttributeType("S"));

		List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
		keySchema.add(new KeySchemaElement().withAttributeName("auditId").withKeyType(KeyType.HASH));
		keySchema.add(new KeySchemaElement().withAttributeName("database").withKeyType(KeyType.RANGE));

		CreateTableRequest request = new CreateTableRequest().withTableName("AuditVitalSign13").withKeySchema(keySchema)
				.withAttributeDefinitions(attributeDefinitions).withProvisionedThroughput(
						new ProvisionedThroughput().withReadCapacityUnits(5L).withWriteCapacityUnits(6L));

//		TableDescription tableDescription = dynamoDB.getTable("AuditVitalSign1").describe();
//		if (!tableDescription.getTableStatus().equals("ACTIVE")) {
		Table table = dynamoDB.createTable(request);
		table.waitForActive();
//		}

		TableCollection<ListTablesResult> tables = dynamoDB.listTables();
		Iterator<Table> iterator = tables.iterator();

		while (iterator.hasNext()) {
			Table tableName = iterator.next();
			System.out.println("=========================");
			System.out.println(tableName.getTableName());
		}
	}

	@Bean
	public DynamoDB getDynamoDB() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		if (awsEndpoint != null) {
			client = AmazonDynamoDBClientBuilder.standard()
					.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, "us-east-1"))
					.build();
		}
		DynamoDB dynamoDB = new DynamoDB(client);
		return dynamoDB;
	}

}
