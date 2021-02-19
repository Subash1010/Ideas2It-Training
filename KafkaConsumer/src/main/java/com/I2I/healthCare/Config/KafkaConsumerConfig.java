package com.I2I.healthCare.Config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.I2I.healthCare.Model.User;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> consumerMap = new HashMap<>();
		consumerMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		consumerMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerMap.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
		return new DefaultKafkaConsumerFactory<>(consumerMap);
	}

	@Bean
	public ConsumerFactory<String, User> consumerObjectFactory() {
		Map<String, Object> consumerMap = new HashMap<>();
		consumerMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		consumerMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		consumerMap.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");
		return new DefaultKafkaConsumerFactory<>(consumerMap, new StringDeserializer(),
				new JsonDeserializer<>(User.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaContainerConsumerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> consumerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		consumerFactory.setConsumerFactory(consumerFactory());
		return consumerFactory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, User> kafkaContainerObjectConsumerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, User> consumerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		consumerFactory.setConsumerFactory(consumerObjectFactory());
		return consumerFactory;
	}
}
