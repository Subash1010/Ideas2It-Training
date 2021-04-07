package com.kmaxwell.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

@Configuration
@Slf4j
public class DynamoConfig {

	private static final String TABLE_NAME = "entities";
	private static final Region region = Region.US_EAST_1;

	private final String awsEndpoint;

	public DynamoConfig(@Value("${aws.local.endpoint:#{null}}") String awsEndpoint) {
		super();
		this.awsEndpoint = awsEndpoint;
	}

	private DynamoDbClient getDdbClient() {
		DynamoDbClient dynamoDB = null;
		try {
			DynamoDbClientBuilder builder = DynamoDbClient.builder();
			// awsLocalEndpoint is set only in local environments
			if (awsEndpoint != null) {
				// override aws endpoint with localstack URL in dev environment
				builder.endpointOverride(new URI(awsEndpoint));
			}
			dynamoDB = builder.region(region).build();
		} catch (URISyntaxException ex) {
			log.error("Invalid url {}", awsEndpoint);
			throw new IllegalStateException("Invalid url " + awsEndpoint, ex);
		}
		return dynamoDB;
	}
}
