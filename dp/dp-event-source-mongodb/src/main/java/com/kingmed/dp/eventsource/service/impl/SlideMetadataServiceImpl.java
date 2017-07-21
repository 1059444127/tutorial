package com.kingmed.dp.eventsource.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingmed.dp.dto.dpms.SlideMetadata;
import com.kingmed.dp.eventsource.feign.ConsultObpmClient;
import com.kingmed.dp.eventsource.repository.SlideMetadataRepository;
import com.kingmed.dp.eventsource.service.SlideMetadataService;

@Component("slideMetadataService")
@Transactional
public class SlideMetadataServiceImpl implements SlideMetadataService {

	private static final Logger LOG = LoggerFactory.getLogger(SlideMetadataServiceImpl.class);
	private static Gson gson = new GsonBuilder().create();
	
	@Autowired
	private SlideMetadataRepository slideMetadataRepository;
	
	@Autowired ConsultObpmClient consultObpmClient;
	
	@Override
	public SlideMetadata save(SlideMetadata slideMetadata) {
		LOG.info("保存SlideMetadata:" + gson.toJson(slideMetadata));
		SlideMetadata meta = this.slideMetadataRepository.save(slideMetadata);
		
		int r = consultObpmClient.doSlideReady(meta);
		LOG.info("通知 dp-consult-obpm slide ready 返回值：" + r);
		return meta;
	}

}
