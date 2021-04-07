package com.kmaxwell.config;

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

import com.kmaxwell.model.MData;

@Configuration
@EnableKafka
public class KafkaConfiguration {

	@Bean
	public ConsumerFactory<String, MData> consumerObjectFactory() {
		Map<String, Object> consumerMap = new HashMap<>();
		consumerMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		consumerMap.put(ConsumerConfig.GROUP_ID_CONFIG, "healthcare");
		consumerMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(consumerMap, new StringDeserializer(),
				new JsonDeserializer<>(MData.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, MData> kafkaContainerObjectConsumerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, MData> consumerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		consumerFactory.setConsumerFactory(consumerObjectFactory());
		return consumerFactory;
	}
}
