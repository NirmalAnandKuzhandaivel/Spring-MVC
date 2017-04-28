package com.webtools.airline.model;

public class SuccessBean implements ResponseWrapper{
	
	private String successCode;
	private String successMsg;
	
	public SuccessBean(String successCode, String successMsg){
		this.successCode = successCode;
		this.successMsg = successMsg;
	}
	
	public String getSuccessCode() {
		return successCode;
	}
	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

}
