package io.localstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;

@SpringBootApplication(exclude = { ContextInstanceDataAutoConfiguration.class, ContextStackAutoConfiguration.class,
		ContextRegionProviderAutoConfiguration.class })
@EnableSqs
public class LocalStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalStackApplication.class, args);
	}
}
