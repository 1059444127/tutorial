package com.kingmed.dp.eventsource.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingmed.dp.dto.CstCaseEvent;
import com.kingmed.dp.dto.CstSlideEvent;
import com.kingmed.dp.dto.dpms.SlideMetadata;
import com.kingmed.dp.dto.dpms.SnapshotMetadata;
import com.kingmed.dp.dto.dpms.UploadMeta;
import com.kingmed.dp.eventsource.service.CstCaseEventService;
import com.kingmed.dp.eventsource.service.CstSlideEventService;
import com.kingmed.dp.eventsource.service.SlideMetadataService;

@RestController
public class EventSourceController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EventSourceController.class);
	private static Gson gson = new GsonBuilder().create();
	
/*	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventService eventService;
	*/
	@Autowired
	private CstCaseEventService cstCaseEventService;
	@Autowired
	private CstSlideEventService cstSlideEventService;
	@Autowired
	private SlideMetadataService slideMetadataService;
	
/*	@GetMapping("/cst/case/{testId}")
	public List<ConsultEvent> findByTestId(@PathVariable String testId){
		List<ConsultEvent> events = this.eventService.findConsultCaseWithQuery(testId);
		return events;
	}*/
	
	/**
	 * 记录病人信息
	 * @param event
	 * @return
	 */
	@PostMapping("/cst/case")
	public CstCaseEvent doUploadCase(@RequestBody CstCaseEvent event){
		LOG.info("新会诊事件" + gson.toJson(event));
		
		this.cstCaseEventService.save(event);
		return event;
	}
	
	
	@GetMapping("/cst/case/{testId}")
	public CstCaseEvent findCstCase(@PathVariable String testId){
		return this.cstCaseEventService.findByTestId(testId);
	}
	
	@GetMapping("/cst/case/reqId/{requestId}")
	public CstCaseEvent findCstCaseByRequestId(@PathVariable String requestId){
		return this.cstCaseEventService.findByRquestId(requestId);
	}
	
	//TODO
	//@GetMapping("/cst/case/reqId/{requestId}/{requestCode}")
	public CstCaseEvent findCstCaseByRequestIdAndRequestCode(@PathVariable String requestId, @PathVariable String requestCode){
		return this.cstCaseEventService.findByRequestIdAndRequestCode(requestId, requestCode);
	}
	
	/**
	 * 记录数字切片信息
	 * @param event
	 * @return
	 */
	@PostMapping("/cst/slide")
	public CstSlideEvent doUploadSlide(@RequestBody CstSlideEvent event){
		LOG.info("新会诊事件" + gson.toJson(event));
		
		return this.cstSlideEventService.save(event);
	}
	
	@GetMapping("/cst/slide/{name}")
	public CstSlideEvent findCstSlide(@PathVariable String name){
		LOG.info("查找数字切片: " + name);
		return this.cstSlideEventService.findByName(name);
	}

	
	/*@PostMapping("/cst/slide")
	public CstSlideEvent doUploadSlide(@RequestBody CstSlideEvent event){
		return this.eventService.save(event);
	}*/
	
	
	/**
	 * 根据条码查询数字切片的meta
	 * @param barcode
	 * @return
	 */
	@GetMapping("/dpms/slide/meta")
	public UploadMeta findSlideMeta(@RequestBody List<String> barcodes) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 如果此病例所有的数字切片都转换完毕，则开始发起会诊请求（病人、数字切片）
	 * 1. 记录事件
	 * 2. 转发消息到 dp-consult-obpm/slideReady
	 * @param slideMetadata
	 */
	@PostMapping("/dpms/webhook/slideReady")
	public void slideReady(@RequestBody SlideMetadata slideMetadata){
		System.out.println(gson.toJson(slideMetadata));
		LOG.info("slideReady: " + gson.toJson(slideMetadata));
		this.slideMetadataService.save(slideMetadata);
	}

	@PostMapping("/dpms/webhook/snapshotAdded")
	public void snapshotAdded(@RequestBody SnapshotMetadata snapshotMetadata){
		System.out.println(JSON.toJSONString(snapshotMetadata));
	}
	
	@PostMapping("/dpms/webhook/snapshotDeleted")
	public void snapshotDeleted(@RequestBody SnapshotMetadata snapshotMetadata){
		System.out.println(JSON.toJSONString(snapshotMetadata));
	}
}
