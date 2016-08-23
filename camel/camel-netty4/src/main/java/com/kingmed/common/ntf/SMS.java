package com.kingmed.common.ntf;

import java.util.List;

/**
 * 短信息
 * @author zhengjunjie
 *
 */
public class SMS {
	
	private List<String> receivers;
	
	private String content;

	public List<String> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<String> receivers) {
		this.receivers = receivers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
