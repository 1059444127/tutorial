package com.kingmed.dp.eventsource.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.kingmed.dp.dto.ConsultEvent;

public interface EventRepository extends MongoRepository<ConsultEvent, String>{
	
	@Query("{ 'parameters.ns_request_id': ?0 }")
	List<ConsultEvent> findWithQuery(String ci_test_id);
	
	@Query("{ 'parameters.ns_request_id': ?0 }")
	ConsultEvent findOne(String ci_test_id);
}
