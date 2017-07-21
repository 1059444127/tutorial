package com.kingmed.dp.eventsource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.dto.CstCaseEvent;
import com.kingmed.dp.eventsource.repository.CstCaseEventRepository;
import com.kingmed.dp.eventsource.service.CstCaseEventService;

@Component("cstCaseEventService")
@Transactional
public class CstCaseEventServiceImpl implements CstCaseEventService {

	@Autowired
	private CstCaseEventRepository cstCaseEventRepository;
	
	@Override
	public CstCaseEvent save(CstCaseEvent cstCaseEvent) {
		return this.cstCaseEventRepository.save(cstCaseEvent);
	}

	@Override
	public CstCaseEvent findByTestId(String requestId) {
		return this.cstCaseEventRepository.findByTestId(requestId);
	}

	@Override
	public CstCaseEvent findByRquestId(String requestId) {
		return this.cstCaseEventRepository.findByRequestId(requestId);
	}
	
	@Override
	public CstCaseEvent findByRequestIdAndRequestCode(String requestId, String requestCode) {
		return this.cstCaseEventRepository.findByRequestIdAndRequestCode(requestId, requestCode);
	}

}
