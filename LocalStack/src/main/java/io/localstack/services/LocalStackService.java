package io.localstack.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.localstack.dto.AuditVitalSignDto;
import io.localstack.repositories.LocalStackRepository;
import io.localstack.util.VitalSignDataUtil;

/**
 * @author Pratik Das
 *
 */
@Service
public class LocalStackService {

	@Autowired
	public LocalStackService(LocalStackRepository localStackRepository) {
		super();
		this.localStackRepository = localStackRepository;
	}

	private final LocalStackRepository localStackRepository;

	public String createRecord(AuditVitalSignDto auditVitalSignDto) {
		String secondaryKey = localStackRepository
				.insertRecord(VitalSignDataUtil.convertToVitalSignModel(auditVitalSignDto));
//		String primaryKey = localStackRepository
//				.insertAuditRecord(VitalSignDataUtil.convertToAuditVitalSignModel(auditVitalSignDto));
		return secondaryKey;
	}

	public List<String> fetchAllItems() {

		return localStackRepository.fetchAllRecord();
	}

}
