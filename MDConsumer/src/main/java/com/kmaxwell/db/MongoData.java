package com.kmaxwell.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.kmaxwell.model.MData;

@Component
public class MongoData {
	@Autowired
	private MongoTemplate mongoTemplate;

	public void saveData(MData mData) {
		mongoTemplate.insert(mData, mData.getDatabase() + "_" + mData.getTable());

	}

}
