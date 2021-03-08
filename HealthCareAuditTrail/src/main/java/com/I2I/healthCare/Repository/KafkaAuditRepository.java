package com.I2I.healthCare.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Model.KafkaAuditEntity;

@Repository
public interface KafkaAuditRepository extends MongoRepository<KafkaAuditEntity, String> {

	/**
	 * findByServiceName method is used to fetch Audit detail based on service name.
	 * 
	 * @param serviceName
	 * @return List<Audit>
	 */
	List<KafkaAuditEntity> findByServiceName(String serviceName);
}
