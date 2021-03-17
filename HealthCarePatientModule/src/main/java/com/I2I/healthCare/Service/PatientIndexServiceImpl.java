package com.I2I.healthCare.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.I2I.healthCare.Index.PatientIndex;
import com.I2I.healthCare.Repository.PatientIndexRepository;
import com.google.common.collect.ImmutableList;

@Service
public class PatientIndexServiceImpl implements PatientIndexService {

	@Lazy
	@Autowired
	public PatientIndexServiceImpl(PatientIndexRepository patientIndexRepository,
			ElasticsearchOperations elasticsearchOperations) {
		super();
		this.patientIndexRepository = patientIndexRepository;
		this.elasticsearchOperations = elasticsearchOperations;
	}

	private PatientIndexRepository patientIndexRepository;

	private ElasticsearchOperations elasticsearchOperations;

	Logger logger = LoggerFactory.getLogger(PatientIndexServiceImpl.class);

	Consumer<String> errorConsumer = exception -> logger.error(exception);

	@Override
	public PatientIndex save(PatientIndex patientIndex) {
		return patientIndexRepository.save(patientIndex);
	}

	@Override
	public String delete(long pId) {
		try {
			patientIndexRepository.deleteById(pId);
		} catch (Exception exception) {
			errorConsumer.accept(exception.toString());
			return "Record Not Deleted";
		}
		return "Record Deleted Successfully!!!";
	}

	@Override
	public PatientIndex findById(long pId) {
		Optional<PatientIndex> optionalPatientIndex = patientIndexRepository.findById(pId);
		if (optionalPatientIndex.isPresent() && !optionalPatientIndex.isEmpty()) {
			return optionalPatientIndex.get();
		}
		return null;
	}

	@Override
	public List<PatientIndex> findAll() {
		Iterable<PatientIndex> patientList = patientIndexRepository.findAll(Sort.by("patientId"));
		List<PatientIndex> patientIndexList = ImmutableList.copyOf(patientList);
		return patientIndexList;
	}

	@Override
	public SearchHits<PatientIndex> getByUserName(String firstName, String lastName) {
		QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("firstName", firstName))
				.must(QueryBuilders.matchQuery("lastName", lastName));
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
		return elasticsearchOperations.search(nativeSearchQuery, PatientIndex.class);
	}

	@Override
	public SearchHits<PatientIndex> getUserSearchData(String input) {
		String search = ".*" + input + ".*";
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withFilter(QueryBuilders.regexpQuery("firstName", search)).build();
		return elasticsearchOperations.search(searchQuery, PatientIndex.class);
	}

	@Override
	public SearchHits<PatientIndex> multiMatchQuery(String text) {
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.multiMatchQuery(text)).withFields("firstName").withFields("lastName").build();
		return elasticsearchOperations.search(nativeSearchQuery, PatientIndex.class);
	}

}
