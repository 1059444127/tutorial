package com.kingmed.dp.gateway.feign;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ConsultObpmClient {
	@RequestLine("POST /obpm/cst/case")
	@Headers("Content-Type: application/json")
	@Body("{event}")
	int doUploadCase(@Param("event") String event);
	
	
	@RequestLine("POST /obpm/cst/slide")
	@Headers("Content-Type: application/json")
	@Body("{event}")
	int doUploadSlide(@Param("event") String event);
	
	@RequestLine("GET /test/account/{id}")
	String doTest(@Param("id") String id);
	
}
