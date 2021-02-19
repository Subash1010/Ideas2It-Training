package com.I2I.healthCare.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.I2I.healthCare.Index.UserIndex;

@Repository
public interface UserIndexRepository extends ElasticsearchRepository<UserIndex, Long> {

	Page<UserIndex> findByUserName(String userName, PageRequest pageRequest);

}
