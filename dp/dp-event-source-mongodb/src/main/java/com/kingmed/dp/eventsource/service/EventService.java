package com.kingmed.dp.eventsource.service;

import java.util.List;

import com.kingmed.dp.dto.ConsultEvent;
import com.kingmed.dp.dto.dpms.SlideMetadata;
import com.kingmed.dp.dto.dpms.SnapshotMetadata;
import com.kingmed.dp.dto.dpms.UploadMeta;


public interface EventService {
	
	/**
	 * 查询请求会诊的病人信息
	 * @param testId
	 * @return
	 */
	ConsultEvent findOneConsultCase(String testId);
	
	List<ConsultEvent> findConsultCaseWithQuery(String testId);
	
	ConsultEvent save(ConsultEvent event);
	
	/**
	 * 保存数字切片信息
	 * 当所有数字切片全部ready，传输病例信息，数字切片信息到obpm
	 * @param slideMetadata
	 */
	void doSlideready(SlideMetadata slideMetadata);
	
	/**
	 * 保存截图信息
	 * 下载截图
	 * 传输截图信息到obpm
	 * @param snapshotMetadata
	 */
	void doSnapshotAdded(SnapshotMetadata snapshotMetadata);
	
	void doSnapshotDeleted(SnapshotMetadata snapshotMetadata);
	
	/**
	 * 获取数字切片的uploadMeta，不包括MD5，客户端需要单独计算md5
	 * @param barcode
	 * @return
	 */
	List<UploadMeta> findSlideMeta(List<String> barcodes);
	
}
