package com.kingmed.dp.consult.obpm.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingmed.dp.consult.obpm.feign.EventSourceClient;
import com.kingmed.dp.consult.obpm.service.ObpmDocumentService;
import com.kingmed.dp.dto.CstCaseEvent;
import com.kingmed.dp.dto.CstSlideEvent;
import com.kingmed.dp.dto.SlideParameter;
import com.kingmed.dp.dto.dpms.SlideMetadata;
import com.kingmed.dp.dto.obpm.Screenshot;

import cn.myapps.webservice.client.DocumentService;
import cn.myapps.webservice.client.DocumentServiceProxy;
import cn.myapps.webservice.fault.DocumentServiceFault;
import cn.myapps.webservice.model.SimpleDocument;

@Component("obpmDocumentService")
public class ObpmDocumentServiceImpl implements ObpmDocumentService {
	
	//TODO 参数占位符	@Value("${obpmDocumentEndpoint}")
	private String obpmDocumentEndpoint = "http://10.28.13.185:99/obpm/services/DocumentService";
	
	private static final Logger LOG = LoggerFactory.getLogger(ObpmDocumentServiceImpl.class);
	
	private DocumentService document = new DocumentServiceProxy(obpmDocumentEndpoint);
	
	private Gson gson = new GsonBuilder().create();
	
	@Autowired
	private EventSourceClient eventSourceClient;
	
	@Override
	public int doUploadCase(CstCaseEvent cstCase) {
		LOG.info("会诊请求的病人信息: " + gson.toJson(cstCase));
		String formName=cstCase.getFormName();
		String parameters = gson.toJson(cstCase.getParameters().get(0));
		String domainUserId = cstCase.getDomainUserId();
		String applicationId = cstCase.getApplicationId();
		int re = -1;
		try {
			re = document.createDocumentAndStartFlowByDomainUser(formName, parameters, domainUserId, applicationId);
		} catch (DocumentServiceFault e) {
			LOG.error("创建OBPM的病人信息失败，数据异常, " + "会诊请求的病人信息: " + gson.toJson(cstCase),e);	
		} catch (RemoteException e) {
			LOG.error("创建OBPM的病人信息失败，通信异常, " + "会诊请求的病人信息: " + gson.toJson(cstCase),e);	
		}
		LOG.info("创建OBPM的病人信息结果:" + re + "会诊请求的病人信息: " + gson.toJson(cstCase));	
		
		//TODO 记录病人信息到事件源
		
		return re;
	}

	/**
	 * 
	<li>检测是否有必要转换数字切片到DZI格式</li>
	<li>如果是，则直接记录到事件源，不上传到obpm</li>
	<li>如果不，则直接上传到obpm</li>
	 * 
	 * @param cstSlide
	 * @return
	 */
	@Override
	public int doUploadSlide(CstSlideEvent cstSlide) {
		int re = -1;
		String slideJson = gson.toJson(cstSlide);
		LOG.info("会诊请求的数字切片: " + slideJson);
		SlideParameter slideParameter = cstSlide.getParameters().get(0);
		boolean needConvertToDZI = this.doCheckIfConvertToDZI(slideParameter);
		
		if(!needConvertToDZI) {
			LOG.info("上传数字切片信息到OBPM: " + slideJson);
			String formName=cstSlide.getFormName();
			String parameters = gson.toJson(slideParameter);
			String domainUserId = cstSlide.getDomainUserId();
			String applicationId = cstSlide.getApplicationId();
			try {
				re = document.createDocumentAndStartFlowByDomainUser(formName, parameters, domainUserId, applicationId);
			} catch (DocumentServiceFault e) {
				LOG.error("创建OBPM的数字切片失败，数据异常, " + "数字切片信息: " + slideJson,e);	
			} catch (RemoteException e) {
				LOG.error("创建OBPM的数字切片失败，通信异常, " + "数字切片信息: " + slideJson,e);	
			}
			LOG.info("创建OBPM数字切片结果:" + re + "会诊数字切片信息: " + slideJson);	
		} 
		
		LOG.info("记录数字切片事件: " + slideJson);
		CstSlideEvent cse = this.eventSourceClient.doUploadSlide(cstSlide);
		if("XXX".equalsIgnoreCase(cse.getFormName())) {
			LOG.info("失败，记录数字切片事件结果：" + gson.toJson(cse));
			re = -1;
		}else {
			LOG.error("成功，记录数字切片事件结果：" + gson.toJson(cse));
			re = 0;
		}
		return re;
	}

	@Override
	public int doSlideReady(SlideMetadata event) {
		int re = -1;
		String slideId = event.getSlideId();
		CstSlideEvent cstSlideEvent = this.eventSourceClient.findCstSlide(slideId);
		 //从事件源中已经找到数字切片信息,上传数字切片
		if(!"XXX".equalsIgnoreCase(cstSlideEvent.getFormName())) {
			LOG.info("从事件源中已经找到数字切片信息,上传数字切片到obpm");
			SlideParameter slideParameter = cstSlideEvent.getParameters().get(0);
			
			//修改数字切片的地址，数字切片的类型 DPMS,数字切片的地址
			//http://10.28.13.224/sso?token=${DPMS_TOKEN}&caseId=test&slideId=001&type=RDV
			String dpmsToken = "eyJ4NWMiOnt9LCJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InVzZXIxIn0.i6_PAUvtAmzys9UdwJg9Em2B1X3cVBI7qjKqlSeZsHXLzNHtGXWLmbA0x7q-Yy0wcfyLYlgDzDESLksUv_y9bt5kgkZCRwYYVKFOUHKrSmyEvTmQJMWO4hKP2rnVoomOGV6Me2ahML8cHTRKQxwL5WFcdMC08RMKHhVukJFQLP4mVjNkxdjo7sEJvb0ks5U_IXyogk_0Da-v84FgQJH7PFxVU3vs8fhZ-09NL_r2Bs7__8pzbs8l70Ra55cuJwuIpDwuzHnotfod5MTvOk5PD-kGoPzssjUUhyzvjqijjgJQ-7Ya7Vk97JFjvXYdsjMhBGRm5dY-zPxb-5VGecpuEQ";
			String caseId = event.getCaseId();
			String type = "RDV";
			String nsUrl = "http://10.28.13.224/sso?caseId=" + caseId + "&slideId=" + slideId + "&type=" + type + "&token="+dpmsToken;
			
			slideParameter.setNs_vender("DPMS");
			slideParameter.setNs_url(nsUrl);
			List<SlideParameter> paras = new ArrayList<SlideParameter>();
			paras.add(slideParameter);
			
			cstSlideEvent.setParameters(paras);
			
			re = doUploadSlideToOBPM(cstSlideEvent);
		} else {
			LOG.error("未找到数字切片" + gson.toJson(event));
		}
		return re;
	}

	@Override
	public int doScreenshot(Screenshot screenshot) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("....................");
		DocumentService ds = new DocumentServiceProxy();
		String domainUserId = "11e4-d9d5-6f2a1de2-87cf-356da46a08eb";
		String applicationId = "11e4-868e-476f6c83-afc0-bf7d9fc712b0";
		SimpleDocument sd = ds.searchDocumentByFilter("未诊断列表", "\"ci_request_id\": \"125669516\"", applicationId,
				domainUserId);
		System.out.println(sd.toString());
	}

    /**
     * 当请求类型是数字切片的时候，检测是否需要转换WSI为DZI
     * 当WSI来自于广州的Aperio扫描仪的时候，转换WSI为DZI
     */
    private boolean doCheckIfConvertToDZI(SlideParameter slide ) {
    	String vender = slide.getNs_vender();
    	String imageUrl = slide.getNs_url();
    	if(imageUrl.contains("www.kingmed.com.cn:7380")) {
    		LOG.info("需要转换数字切片到DZI格式, 数字切片是 " + vender + "," + imageUrl);
    		return true;
    	}
    	return false;
    }
    
	@Override
	public void doTest() {
		try {
			System.out.println("....................");
			DocumentService ds = new DocumentServiceProxy();
			String domainUserId = "11e4-d9d5-6f2a1de2-87cf-356da46a08eb";
			String applicationId = "11e4-868e-476f6c83-afc0-bf7d9fc712b0";
			SimpleDocument sd = ds.searchDocumentByFilter("未诊断列表", "\"ci_request_id\": \"125669516\"", applicationId,
					domainUserId);
			System.out.println(sd.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private int doUploadSlideToOBPM(CstSlideEvent cstSlide) {
		int re = -1;
		
		String slideJson = gson.toJson(cstSlide);
		LOG.info("上传数字切片信息到OBPM: " + slideJson);

		SlideParameter slideParameter = cstSlide.getParameters().get(0);
		
		String formName=cstSlide.getFormName();
		String parameters = gson.toJson(slideParameter);
		String domainUserId = cstSlide.getDomainUserId();
		String applicationId = cstSlide.getApplicationId();
		try {
			re = document.createDocumentAndStartFlowByDomainUser(formName, parameters, domainUserId, applicationId);
		} catch (DocumentServiceFault e) {
			LOG.error("创建OBPM的数字切片失败，数据异常, " + "数字切片信息: " + slideJson,e);	
		} catch (RemoteException e) {
			LOG.error("创建OBPM的数字切片失败，通信异常, " + "数字切片信息: " + slideJson,e);	
		}
		LOG.info("创建OBPM数字切片结果:" + re + "会诊数字切片信息: " + slideJson);
		
		return re;
	}
}
