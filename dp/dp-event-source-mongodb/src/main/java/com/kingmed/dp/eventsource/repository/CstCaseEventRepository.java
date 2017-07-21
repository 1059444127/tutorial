package com.kingmed.dp.eventsource.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.kingmed.dp.dto.CstCaseEvent;

public interface CstCaseEventRepository extends MongoRepository<CstCaseEvent, String>{
	
	@Query("{'parameters.ci_test_id': ?0}")
	CstCaseEvent findByTestId(String testId);
	
	@Query("{'parameters.ci_request_id': ?0}")
	CstCaseEvent findByRequestId(String requestId);
	
	@Query("{'parameters.ci_request_id': ?0, 'parameters.ci_request_code': ?1}")
	CstCaseEvent findByRequestIdAndRequestCode(String requestId, String requestCode);
}
