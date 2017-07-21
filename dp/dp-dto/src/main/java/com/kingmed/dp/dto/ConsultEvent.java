package com.kingmed.dp.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 实验室系统发起的请求
 * @author zhengjunjie
 *
 */
public class ConsultEvent {
	private String type;
	
	//--------------------------传送到会诊系统obpm的入参
	private String formName;
	private List<Parameter> parameters = new ArrayList<Parameter>();
	private String domainUserId="11e4-d9d5-6f2a1de2-87cf-356da46a08eb";
	private String applicationId="11e4-868e-476f6c83-afc0-bf7d9fc712b0";
	private Date createTime=new Date();
	//--------------------------传送到会诊系统obpm的入参
		
	public String getFormName() {
		return formName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setFormName(String formName) {
		this.formName = formName;
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
	public List<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "ConsultEvent [type=" + type + ", formName=" + formName + ", parameters=" + parameters
				+ ", domainUserId=" + domainUserId + ", applicationId=" + applicationId + ", createTime=" + createTime
				+ "]";
	}
	
	
}
