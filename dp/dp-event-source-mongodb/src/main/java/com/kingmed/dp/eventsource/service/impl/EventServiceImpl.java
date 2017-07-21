package com.kingmed.dp.eventsource.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.dto.ConsultEvent;
import com.kingmed.dp.dto.dpms.SlideMetadata;
import com.kingmed.dp.dto.dpms.SnapshotMetadata;
import com.kingmed.dp.dto.dpms.UploadMeta;
import com.kingmed.dp.eventsource.repository.EventRepository;
import com.kingmed.dp.eventsource.service.EventService;

@Component("eventService")
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public ConsultEvent save(ConsultEvent event) {
		return eventRepository.save(event);
	}

	@Override
	public void doSlideready(SlideMetadata slideMetadata) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSnapshotAdded(SnapshotMetadata snapshotMetadata) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSnapshotDeleted(SnapshotMetadata snapshotMetadata) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UploadMeta> findSlideMeta(List<String> barcodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConsultEvent findOneConsultCase(String testId) {
		String formName="xxx";
		ConsultEvent event = eventRepository.findOne( testId);
		System.out.println("event ========================== " +event);
		return event;
	}

	@Override
	public List<ConsultEvent> findConsultCaseWithQuery(String testId) {
		// TODO Auto-generated method stub
		return null;
	}



}
