package com.kingmed.dp.gateway.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class ParamsDto implements Serializable{

	private static final long serialVersionUID = -8627872998073118758L;

	public static final String TESTID = "testId";
	public static final String BARCODE = "barcode";
	public static final String ANTIBODY = "antiBody";
	
	private Map<String,Object> map;

	public ParamsDto() {
		super();
	}
	public ParamsDto(HttpServletRequest request,String[] keys) {
		map = new HashMap<String,Object>();
		for(String key : keys){
			map.put(key, request.getParameter(key));
		}
	}
	public ParamsDto(String jsonStr,String[] keys) {
		JSONObject fromObject = JSONObject.fromObject(jsonStr);
		map = new HashMap<String,Object>();
		for(String key : keys){
			map.put(key, fromObject.get(key));
		}
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}
