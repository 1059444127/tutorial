package com.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.kingmed.dp.gateway.util.ModelUtil;
import com.test.util.HttpRequstService;

import net.sf.json.JSONObject;

public class TestDoc {

	//@Test
	public void testDoc(){
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		HashMap<String,Object> caseMaps = new HashMap<String,Object>();
		HashMap<String,Object> leaveMaps = new HashMap<String,Object>();
		HashMap<String,Object> sectionMaps = new HashMap<String,Object>();
		caseMaps.put("ci_test_id", 11);
		caseMaps.put("ci_request_code", 11);
		caseMaps.put("ci_itemcode", 11);
		caseMaps.put("ci_name", 11);
		caseMaps.put("ci_gender", 1);
		caseMaps.put("ci_age", 1);
		caseMaps.put("ci_unit", 1);
		caseMaps.put("ci_lczd", 1);
		caseMaps.put("ci_lczs", 1);
		caseMaps.put("ci_jts", 1);
		caseMaps.put("ci_qtbs", 1);
		caseMaps.put("ci_qtfzjc", 1);
		caseMaps.put("ci_ssbw", 1);
		caseMaps.put("ci_sssj", 1);
		caseMaps.put("ci_czdblzd", 1);
		caseMaps.put("ci_zdz", 1);
		//caseMaps.put("caseLib.caseId", parameters.get("ci_sqrq"));//申请日期
		caseMaps.put("ci_hjbw", 1);
		caseMaps.put("ci_child_company", 1);
		caseMaps.put("ci_state", 0);
		
		sectionMaps.put("ns_request_id", "105958751");
		sectionMaps.put("ns_request_code", "1703898943");
		sectionMaps.put("ns_name", 22);
		sectionMaps.put("ns_url", 22);
		sectionMaps.put("ns_vender", 22);
		sectionMaps.put("ns_antibody", 22);
		sectionMaps.put("ns_antibody", 22);
		
		leaveMaps.put("parentid", "1148");
		leaveMaps.put("lea_msg", "测试反馈");
		leaveMaps.put("lea_type", "1");
		

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", "lonestarr");
		map.put("password", "12345");
		String cookie = HttpRequstService.postMethod("http://localhost:8080/dp/dp/login", map);
		
		params.put("formName", "leave_info_new");
		params.put("caseParams", JSONObject.fromObject(caseMaps).toString());
		params.put("leaveParams", JSONObject.fromObject(leaveMaps).toString());
		params.put("sectionParams", JSONObject.fromObject(sectionMaps).toString());
		params.put("domainUserId", "11e4-89a2-64d40056-afc0-bf7d9fc712b0");
		params.put("applicationId", "11e4-868e-476f6c83-afc0-bf7d9fc712b0");
		
		HttpRequstService.postMethod("http://localhost:8080/dp/cst/case/createDocument",params,cookie);
	}
	//@Test
	public void testNodeJs(){
		String params = "testId=B1600026&barcode=16.1_20160414_1606059949_1&antiBody=HE-1";
		String result = HttpRequstService.sendGet("http://localhost:8080/dp/cst/case/slide/snapshot",params);
		System.out.println("nodejs生成的文件名："+result);
	}
	//@Test
	public void testAllUsers(){
		String result = HttpRequstService.sendGet("http://epathology.kingmed.com.cn:99/dp/cst/dr","");
		System.out.println("会诊平台所有专家的json字符串："+result);
	}	

}
