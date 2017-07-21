package com.kingmed.dp.consult.obpm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TLK_未诊断列表")
public class ConCase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Column(name="ITEM_CI_TEST_ID")
	private String testId;
	
	@Column(name="ITEM_CI_STATE")
	private String state;
	
	@Column(name="ITEM_CI_JT")
	private String jt;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getJt() {
		return jt;
	}

	public void setJt(String jt) {
		this.jt = jt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
