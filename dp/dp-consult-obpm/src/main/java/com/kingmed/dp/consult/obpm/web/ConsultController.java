package com.kingmed.dp.consult.obpm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingmed.dp.consult.obpm.service.AccountService;
import com.kingmed.dp.consult.obpm.service.ObpmDocumentService;
import com.kingmed.dp.consult.obpm.service.impl.ObpmDocumentServiceImpl;
import com.kingmed.dp.dto.CstCaseEvent;
import com.kingmed.dp.dto.CstSlideEvent;
import com.kingmed.dp.dto.dpms.SlideMetadata;
import com.kingmed.dp.dto.obpm.Screenshot;

@RestController
public class ConsultController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ObpmDocumentServiceImpl.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ObpmDocumentService obpmDocumentService;
	
	/**
	 * Test
	 * @param id
	 * @return
	 */
	@GetMapping("/test/account/{id}")
	public String findAccountById(@PathVariable String id){
		return this.accountService.findById(id).toString();
	}
	
	/**
	 * Test
	 * @param id
	 * @return
	 */
	@GetMapping("/obpm/test")
	public void doTestQueryOBPM(){
		this.obpmDocumentService.doTest();
	}
	
	/**
	 * 请求会诊，上传病人信息
	 * 上传病人信息到obpm
	 * 记录病人信息到事件源
	 * @param event
	 * @return
	 */
	@PostMapping("/obpm/cst/case")
	public int doUploadCase(@RequestBody CstCaseEvent cstCase) {
		int r = this.obpmDocumentService.doUploadCase(cstCase);
		LOG.info("创建病人信息结果:"+r);
		return r;
	}
	
	/**
	 * 请求会诊，上传数字切片， 记录数字切片信息到事件源
	 * 如果无须转换DZI，则需要上传病人信息、数字切片信息
	 * @param event
	 * @return
	 */
	@PostMapping("/obpm/cst/slide")
	public int doUploadSlide(@RequestBody CstSlideEvent event) {
		int r = this.obpmDocumentService.doUploadSlide(event);
		LOG.info("创建数字切片信息:"+r);
		return r;
	}
	
	/**
	 * 当DZI格式的数字切片转换完毕，DPMS推送通知到webhook，webhook转发通知到此服务
	 * @param event
	 * @return
	 */
	@PostMapping("/obpm/cst/slideReady")
	public int doSlideReady(@RequestBody SlideMetadata event) {
		int i = this.obpmDocumentService.doSlideReady(event);
		return i;
		
	}
	
	/**
	 * 当用户在RDV中完成截图，DPMS推送通知到webhook，webhook组装截图详情然后转发截图信息到此服务
	 * @param screenshot
	 * @return
	 */
	@PostMapping("/obpm/screenshot")
	public Screenshot doScreenshot(@RequestBody Screenshot screenshot) {
		return screenshot;
	}
	
	
}
