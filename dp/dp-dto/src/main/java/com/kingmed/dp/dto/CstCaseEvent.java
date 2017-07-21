package com.kingmed.dp.dto;

import java.util.ArrayList;
import java.util.List;

public class CstCaseEvent {
	private String type;
	
	//--------------------------传送到会诊系统obpm的入参
	private String formName;
	private List<CaseParameter> parameters = new ArrayList<CaseParameter>();
	private String domainUserId="11e4-d9d5-6f2a1de2-87cf-356da46a08eb";
	private String applicationId="11e4-868e-476f6c83-afc0-bf7d9fc712b0";
	private String createTime;
	//--------------------------传送到会诊系统obpm的入参
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public List<CaseParameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<CaseParameter> parameters) {
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
