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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.I2I.healthCare.Index.UserIndex;
import com.I2I.healthCare.Repository.UserIndexRepository;
import com.google.common.collect.ImmutableList;

@Service
public class UserIndexServiceImpl implements UserIndexService {

	@Lazy
	@Autowired
	public UserIndexServiceImpl(UserIndexRepository userIndexRepository,
			ElasticsearchOperations elasticsearchOperations) {
		super();
		this.userIndexRepository = userIndexRepository;
		this.elasticsearchOperations = elasticsearchOperations;
	}

	private UserIndexRepository userIndexRepository;

	private ElasticsearchOperations elasticsearchOperations;

	Logger logger = LoggerFactory.getLogger(UserIndexServiceImpl.class);

	Consumer<String> errorConsumer = exception -> logger.error(exception);

	@Override
	public UserIndex save(UserIndex userIndex) {
		return userIndexRepository.save(userIndex);
	}

	@Override
	public String delete(long userId) {
		try {
			userIndexRepository.deleteById(userId);
		} catch (Exception exception) {
			errorConsumer.accept(exception.toString());
			return "Record Not Deleted";
		}
		return "Record Deleted Successfully!!!";
	}

	@Override
	public UserIndex findById(long userId) {
		Optional<UserIndex> optionalUserIndex = userIndexRepository.findById(userId);
		if (optionalUserIndex.isPresent() && !optionalUserIndex.isEmpty()) {
			return optionalUserIndex.get();
		}
		return null;
	}

	@Override
	public List<UserIndex> findAll() {
		Iterable<UserIndex> userList = userIndexRepository.findAll(Sort.by("userId"));
		List<UserIndex> userIndexList = ImmutableList.copyOf(userList);
		return userIndexList;
	}

	@Override
	public Page<UserIndex> findByUserName(String userName, PageRequest pageRequest) {
		return userIndexRepository.findByUserName(userName, pageRequest);
	}

	@Override
	public SearchHits<UserIndex> getByUserCredentials(String userName, String password) {
		QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("userName", userName))
				.must(QueryBuilders.matchQuery("password", password));
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
		return elasticsearchOperations.search(nativeSearchQuery, UserIndex.class);
	}

	@Override
	public SearchHits<UserIndex> getUserSearchData(String input) {
		String search = ".*" + input + ".*";
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withFilter(QueryBuilders.regexpQuery("userName", search)).build();
		return elasticsearchOperations.search(searchQuery, UserIndex.class);
	}

	@Override
	public SearchHits<UserIndex> multiMatchQuery(String text) {
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.multiMatchQuery(text)).withFields("userName").withFields("password").build();
		return elasticsearchOperations.search(nativeSearchQuery, UserIndex.class);
	}

}
