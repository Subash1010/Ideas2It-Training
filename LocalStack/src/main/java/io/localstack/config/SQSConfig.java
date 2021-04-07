package io.localstack.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Configuration
public class SQSConfig {

	public SQSConfig(@Value("${aws.local.endpoint:#{null}}") String awsEndpoint) {
		super();
		this.awsEndpoint = awsEndpoint;
	}

	private final String awsEndpoint;

	@Bean
	public AmazonSQS queueMessagingTemplate() {
		AmazonSQS sqs = AmazonSQSClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, "us-east-1"))
				.build();
		return sqs;
	}

//	private AmazonSQSAsync amazonSQSAsync() {
//		return AmazonSQSAsyncClientBuilder.standard()
//				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
//						"http://localhost:4566/000000000000/LocalStack", "us-east-1"))
//				.build();
//	}
}
