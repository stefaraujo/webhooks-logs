package com.stefani.webhooks.model;

public class Line {
	
	private String requestTo;
	private String responseStatus;
	
	public String getRequestTo() {
		return requestTo;
	}
	public void setRequestTo(String requestTo) {
		this.requestTo = requestTo;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

}
