package com.kmaxwell.model;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "KafkaAudit")
public class MData implements Serializable {

	private static final long serialVersionUID = 2343201328285573125L;

	private String database;

	private String table;

	private String type;

	private long ts;

	private int xid;

	private boolean commit;

	private Map<String, Object> data;

	private Map<String, Object> old;

}
