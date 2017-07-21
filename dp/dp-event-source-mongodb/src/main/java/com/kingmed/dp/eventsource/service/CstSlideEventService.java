package com.kingmed.dp.eventsource.service;

import com.kingmed.dp.dto.CstSlideEvent;

public interface CstSlideEventService {
	
	CstSlideEvent save(CstSlideEvent cstSlideEvent);
	
	CstSlideEvent findByName(String name);
}
