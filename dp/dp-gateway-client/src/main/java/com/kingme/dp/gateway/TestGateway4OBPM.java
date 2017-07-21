package com.kingme.dp.gateway;

import java.rmi.RemoteException;

import com.kingmed.dp.soap.service.client.DocumentWS;
import com.kingmed.dp.soap.service.client.DocumentWSProxy;
import com.kingmed.dp.soap.service.fault.DocumentWSFault;

public class TestGateway4OBPM {

	public static void main(String[] args) throws DocumentWSFault, RemoteException {
//		TestGateway4OBPM.testDoUploadCase();
		TestGateway4OBPM.testDoUploadSlide();
	}
	
	public static void testDoUploadCase() throws DocumentWSFault, RemoteException {
		DocumentWS ws = new DocumentWSProxy();
		
		String formName = "未诊断列表";
		String parameters = "{\"ci_request_id\":\"125836122\",\"ci_request_code\":\"0610515692\",\"ci_test_id\":\"KB1720906\",\"ci_name\":\"张蕾\",\"ci_gender\":\"f\",\"ci_age\":\"39\",\"ci_unit\":\"岁\",\"ci_hospi\":\"南京金域医学检测中心\",\"ci_section\":\"\",\"ci_lczd\":\"慢性肾炎\",\"ci_lczs\":\"\",\"ci_jts\":\"\",\"ci_qtbs\":\"\",\"ci_qtfzjc\":\"\",\"ci_ssbw\":\"\",\"ci_sssj\":\"\",\"ci_czdblzd\":\"符合轻-中度系膜增生性肾小球肾炎。\",\"ci_zdz\":\"11e6-69d6-a713817c-867e-61475650d998\",\"ci_sqrq\":\"2017-07-10 16:05:06\",\"ci_hjbw\":\"\",\"ci_state\":\"0\",\"ci_itemcode\":\"5240\",\"ci_child_company\":\"01.1\",\"ci_src_app\":\"LIR\",\"ci_env\":\"P\",\"ci_version\":\"1.0.18.626\"}";
		String domainUserId = "11e4-d9d5-6f2a1de2-87cf-356da46a08eb";
		String applicationId = "11e4-868e-476f6c83-afc0-bf7d9fc712b0";
		//String domainUserId = "xx";
		//String applicationId = "xx";
		int re = ws.createDocumentByDomainUser(formName, parameters, domainUserId, applicationId);
		System.out.println("re = " + re) ;
	}
	
	public static void testDoUploadSlide() throws DocumentWSFault, RemoteException {
		DocumentWS ws = new DocumentWSProxy();
		
		String formName = "number_section";
		String parameters = "{\"ns_request_id\":\"125836122\",\"ns_request_code\":\"0610515692\",\"ns_name\":\"01.1_20170710_0610515692_1\",\"ns_url\":\"http://KMpathology:KM-CCF@www.kingmed.com.cn:7380/@171203\",\"ns_vender\":\"Aperio\",\"ns_antibody\":\"HE\",\"ns_img_url\":\"\"}" ;
		String domainUserId = "11e4-d9d5-6f2a1de2-87cf-356da46a08eb";
		String applicationId = "11e4-868e-476f6c83-afc0-bf7d9fc712b0";
		//String domainUserId = "xx";
		//String applicationId = "xx";
		int re = ws.createDocumentByDomainUser(formName, parameters, domainUserId, applicationId);
		System.out.println("re = " + re) ;
	}


}
