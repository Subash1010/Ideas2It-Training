package com.I2I.healthCare.Service;

import java.util.List;

import org.springframework.data.elasticsearch.core.SearchHits;

import com.I2I.healthCare.Index.PatientIndex;

public interface PatientIndexService {

	PatientIndex save(PatientIndex patientIndex);

	String delete(long pId);

	PatientIndex findById(long pId);

	List<PatientIndex> findAll();

	SearchHits<PatientIndex> getByUserName(String firstName, String lastName);

	SearchHits<PatientIndex> getUserSearchData(String input);

	SearchHits<PatientIndex> multiMatchQuery(String text);

}
