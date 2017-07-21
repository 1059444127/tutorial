package com.kingmed.dp.gateway.feign;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kingmed.dp.dto.CaseParameter;
import com.kingmed.dp.dto.CstCaseEvent;
import com.kingmed.dp.dto.CstSlideEvent;
import com.kingmed.dp.dto.SlideParameter;

public class Test {
	static String domainUserId = "11e4-d9d5-6f2a1de2-87cf-356da46a08eb";
	static String applicationId = "11e4-868e-476f6c83-afc0-bf7d9fc712b0";
	static String url_with_dzi = "www.kingmed.com.cn:7380";
	static String url_without_dzi = "http://www.kingmed.com.cn:7090";
	public static void main(String[] args) {
		//doUploadCase();
		doUploadSlideWithoutDZI();
		//doUploadSlideWithDZI();
	}

	
	public static void doUploadSlideWithoutDZI() {
		
		Date date = new Date(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
		
		CstSlideEvent event = new CstSlideEvent();
		SlideParameter sp = new SlideParameter();
		sp.setNs_url(url_without_dzi);
		List<SlideParameter> paras = new ArrayList<SlideParameter>();
		paras.add(sp);
		
		String formName = "number_section";
		
		event.setFormName(formName);
		event.setDomainUserId(domainUserId);
		event.setApplicationId(applicationId);
		event.setParameters(paras);
		event.setApplicationId(applicationId);
		event.setCreateTime(df.format(date));
		
		ConsultSlideObpmCommand cmd = new ConsultSlideObpmCommand(event);
		int i = cmd.execute();
		System.out.println(i);
	}

	public static void doUploadSlideWithDZI() {
		
		Date date = new Date(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
		
		CstSlideEvent event = new CstSlideEvent();
		SlideParameter sp = new SlideParameter();
		sp.setNs_url(url_with_dzi);
		List<SlideParameter> paras = new ArrayList<SlideParameter>();
		paras.add(sp);
		
		String formName = "number_section";
		
		event.setFormName(formName);
		event.setDomainUserId(domainUserId);
		event.setApplicationId(applicationId);
		event.setParameters(paras);
		event.setApplicationId(applicationId);
		event.setCreateTime(df.format(date));
		
		ConsultSlideObpmCommand cmd = new ConsultSlideObpmCommand(event);
		int i = cmd.execute();
		System.out.println(i);
	}
	
	public static void doUploadCase() {
		String formName = "未诊断列表";
		
		CstCaseEvent event = new CstCaseEvent();
		
		CaseParameter cp = new CaseParameter();
		cp.setCi_request_id("test123");
		List<CaseParameter> paras = new ArrayList<CaseParameter>();
		paras.add(cp);
		
		event.setFormName(formName);
		event.setDomainUserId(domainUserId);
		event.setApplicationId(applicationId);
		event.setParameters(paras);
		event.setApplicationId(applicationId);
		
		Date date = new Date(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
		event.setCreateTime(df.format(date));
		ConsultCaseObpmCommand cmd = new ConsultCaseObpmCommand(event);
		int i = cmd.execute();
		
		System.out.println(i);
	}

}
