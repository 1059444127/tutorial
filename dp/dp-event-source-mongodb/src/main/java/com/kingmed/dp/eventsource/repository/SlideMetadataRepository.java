package com.kingmed.dp.eventsource.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kingmed.dp.dto.dpms.SlideMetadata;

public interface SlideMetadataRepository extends MongoRepository<SlideMetadata, String>{
	

}
