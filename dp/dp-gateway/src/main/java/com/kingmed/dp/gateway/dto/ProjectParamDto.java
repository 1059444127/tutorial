package com.kingmed.dp.gateway.dto;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class ProjectParamDto implements Serializable{

	private static final long serialVersionUID = 1558927582217570349L;
	
	private String documentId;
	private Map<String,Object> parameters;
	private String domainUserId;
	private String applicationId;
	
	public ProjectParamDto() {
		super();
	}
	
	public ProjectParamDto(HttpServletRequest request) {
		this.documentId = request.getParameter("documentId");
		this.domainUserId = request.getParameter("domainUserId");
		this.applicationId = request.getParameter("applicationId");
		String parameters = request.getParameter("parameters");
		JSONObject jsonObject = JSONObject.fromObject(parameters);
		this.parameters = JSONObject.fromObject(jsonObject);
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String getDomainUserId() {
		return domainUserId;
	}

	public void setDomainUserId(String domainUserId) {
		this.domainUserId = domainUserId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	
}
