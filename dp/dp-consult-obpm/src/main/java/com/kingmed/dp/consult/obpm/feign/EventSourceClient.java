package com.kingmed.dp.consult.obpm.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kingmed.dp.dto.CstSlideEvent;

@FeignClient(name = "dp-event-source", fallback = EventSoureClientFallback.class)
public interface EventSourceClient {
	
	@PostMapping("/cst/slide")
	CstSlideEvent doUploadSlide(@RequestBody CstSlideEvent event);
	
	@GetMapping("/cst/slide/{name}")
	public CstSlideEvent findCstSlide(@PathVariable("name") String name);
}

@Component
class EventSoureClientFallback implements EventSourceClient {

	@Override
	public CstSlideEvent doUploadSlide(CstSlideEvent event) {
		CstSlideEvent cse = new CstSlideEvent();
		cse.setFormName("XXX");
		return cse;
	}

	@Override
	public CstSlideEvent findCstSlide(String name) {
		CstSlideEvent cse = new CstSlideEvent();
		cse.setFormName("XXX");
		return cse;
	}
	
}
