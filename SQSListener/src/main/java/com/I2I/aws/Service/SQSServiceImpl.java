package com.I2I.aws.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.I2I.aws.Model.SQSEntity;
import com.I2I.aws.Repository.SQSRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SQSServiceImpl implements SQSService {

	@Autowired
	public SQSServiceImpl(SQSRepository sqsRepository) {
		this.sqsRepository = sqsRepository;
	}

	private final SQSRepository sqsRepository;

	@Override
	public void insertRecord(String stringJson) {
		SQSEntity sqsEntity;
		try {
			sqsEntity = new ObjectMapper().readValue(stringJson, SQSEntity.class);
			sqsRepository.save(sqsEntity);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
