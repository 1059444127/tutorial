package com.kingmed.dp.consult.obpm.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 日志实体类
 */
@Entity
@Table(name="T_DP_DIAGNOSE_LOG")
public class DPDiagnoseLog {

	@Id
	private String id;
	
	private String previewPath;//预览图片的路径
	
	private String remark;//备注

	private String testId;//病理号
	
	private String loginUser;//登陆用户
	
	private String createTime;//创建时间
	
	private String flag; //发送报告单标记："0"是未发送 ，"1"是已经发送
	
	private String sendPath; //发送lir端报告单图片的路径
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPreviewPath() {
		return previewPath;
	}
	public void setPreviewPath(String previewPath) {
		this.previewPath = previewPath;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSendPath() {
		return sendPath;
	}
	public void setSendPath(String sendPath) {
		this.sendPath = sendPath;
	}

	
}
