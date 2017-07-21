package com.kingmed.dp.eventsource;

import java.util.ArrayList;
import java.util.List;

import com.kingmed.dp.dto.Parameter;

public class Slide {
	private String formName;
	private List<Parameter> parameters = new ArrayList<Parameter>();
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public List<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
}
