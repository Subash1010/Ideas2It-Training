package io.localstack.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditVitalSignDto implements Serializable {

	private static final long serialVersionUID = 2343201328285573125L;

	private String database;

	private String table;

	private String type;

	private long ts;

	private int xid;

	private boolean commit;

	private Map<String, String> data;

	private Map<String, String> old;

}
