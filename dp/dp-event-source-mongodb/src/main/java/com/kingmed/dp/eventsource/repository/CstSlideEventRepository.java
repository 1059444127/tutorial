package com.kingmed.dp.eventsource.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.kingmed.dp.dto.CstSlideEvent;

public interface CstSlideEventRepository extends MongoRepository<CstSlideEvent, String>{
	
	@Query("{'parameters.ns_name': ?0}")
	CstSlideEvent findByName(String name);
}
