package com.kingmed.dp.consult.obpm.service;

import com.kingmed.dp.dto.CstCaseEvent;
import com.kingmed.dp.dto.CstSlideEvent;
import com.kingmed.dp.dto.dpms.SlideMetadata;
import com.kingmed.dp.dto.obpm.Screenshot;

public interface ObpmDocumentService {
	/**
	 * 上传病人信息
	 * @param cstCase
	 * @return
	 */
	int doUploadCase(CstCaseEvent cstCase);
	
	/**
	 * 上传数字切片
	 * @param cstSlide
	 * @return
	 */
	int doUploadSlide(CstSlideEvent cstSlide);
	
	int doSlideReady(SlideMetadata event);
	
	int doScreenshot(Screenshot screenshot);
	
	void doTest() ;
	
}
