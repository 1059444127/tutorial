package com.kingmed.dp.lis.facade;

public class Response {
	private String errorMsg;
	private String errorCode;// 1为成功，非1为失败

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
