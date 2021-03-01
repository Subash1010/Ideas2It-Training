package com.I2I.healthCare.Repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Index.PatientIndex;

@Repository
public interface PatientIndexRepository extends ElasticsearchRepository<PatientIndex, Long> {

}
