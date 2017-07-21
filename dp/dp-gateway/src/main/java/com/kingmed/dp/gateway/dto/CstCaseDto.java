package com.kingmed.dp.gateway.dto;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class CstCaseDto implements Serializable{

	private static final long serialVersionUID = 7181284698728902557L;
	
	private String formName;
	
	private Map<String,Object> caseParams;
	
	private Map<String,Object> leaveParams;
	
	private Map<String,Object> sectionParams;
	
	private String domainUserId;
	
	private String applicationId;
	
	public CstCaseDto() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public CstCaseDto(HttpServletRequest request) {
		this.formName = request.getParameter("formName");
		this.domainUserId = request.getParameter("domainUserId");
		this.applicationId = request.getParameter("applicationId");
		
		String caseJson = request.getParameter("caseParams");
		this.caseParams = JSONObject.fromObject(caseJson);
		
		String leaveJson = request.getParameter("leaveParams");
		this.leaveParams = JSONObject.fromObject(leaveJson);
		
		String sectionJson = request.getParameter("sectionParams");
		this.sectionParams = JSONObject.fromObject(sectionJson);
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Map<String, Object> getCaseParams() {
		return caseParams;
	}

	public void setCaseParams(Map<String, Object> caseParams) {
		this.caseParams = caseParams;
	}

	public Map<String, Object> getLeaveParams() {
		return leaveParams;
	}

	public void setLeaveParams(Map<String, Object> leaveParams) {
		this.leaveParams = leaveParams;
	}

	public Map<String, Object> getSectionParams() {
		return sectionParams;
	}

	public void setSectionParams(Map<String, Object> sectionParams) {
		this.sectionParams = sectionParams;
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
