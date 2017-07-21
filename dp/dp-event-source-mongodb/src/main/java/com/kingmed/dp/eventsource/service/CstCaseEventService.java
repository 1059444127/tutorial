package com.kingmed.dp.eventsource.service;

import com.kingmed.dp.dto.CstCaseEvent;

public interface CstCaseEventService {
	
	CstCaseEvent save(CstCaseEvent cstCaseEvent);
	
	CstCaseEvent findByTestId(String testId);
	
	CstCaseEvent findByRquestId(String requestId);

	CstCaseEvent findByRequestIdAndRequestCode(String requestId, String requestCode);
}
