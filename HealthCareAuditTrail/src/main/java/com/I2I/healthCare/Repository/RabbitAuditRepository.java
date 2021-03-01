package com.I2I.healthCare.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Model.RabbitAuditEntity;

@Repository
public interface RabbitAuditRepository extends MongoRepository<RabbitAuditEntity, String> {

}
