package com.kingmed.dp.eventsource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.dto.CstSlideEvent;
import com.kingmed.dp.eventsource.repository.CstSlideEventRepository;
import com.kingmed.dp.eventsource.service.CstSlideEventService;

@Component("cstSlideEventService")
@Transactional
public class CstSlideEventServiceImpl implements CstSlideEventService {

	@Autowired
	private CstSlideEventRepository cstSlideEventRepository;

	@Override
	public CstSlideEvent save(CstSlideEvent cstSlideEvent) {
		return this.cstSlideEventRepository.save(cstSlideEvent);
	}

	@Override
	public CstSlideEvent findByName(String name) {
		return this.cstSlideEventRepository.findByName(name);
	}
	


}
