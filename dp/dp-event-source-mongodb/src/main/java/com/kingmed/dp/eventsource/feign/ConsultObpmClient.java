package com.kingmed.dp.eventsource.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kingmed.dp.dto.dpms.SlideMetadata;

@FeignClient(name = "dp-event-source", fallback = ConsultObpmClient.class)
public interface ConsultObpmClient {

	@PostMapping("/obpm/cst/slideReady")
	int doSlideReady(@RequestBody SlideMetadata event);
}

@Component
class ConsultObpmClientFallback implements ConsultObpmClient {

	@Override
	public int doSlideReady(SlideMetadata event) {
		int re = -99;
		return re;
	}
	
}