package com.I2I.aws.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.I2I.aws.Model.SQSEntity;

@Repository
public interface SQSRepository extends MongoRepository<SQSEntity, String> {

}
