package com.I2I.healthCare.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchHits;

import com.I2I.healthCare.Index.UserIndex;

public interface UserIndexService {

	UserIndex save(UserIndex UserIndex);

	String delete(long userId);

	UserIndex findById(long userId);

	List<UserIndex> findAll();

	Page<UserIndex> findByUserName(String userName, PageRequest pageRequest);

	SearchHits<UserIndex> getByUserCredentials(String userName, String password);

	SearchHits<UserIndex> getUserSearchData(String input);

	SearchHits<UserIndex> multiMatchQuery(String text);

}
