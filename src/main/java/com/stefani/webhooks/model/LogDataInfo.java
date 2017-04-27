package com.stefani.webhooks.model;

import java.util.List;

public class LogDataInfo {
	
	private List<UrlRequested> urlData;
	private List<StatusCode> statusData;
	
	public List<UrlRequested> getUrlData() {
		return urlData;
	}
	public void setUrlData(List<UrlRequested> urlData) {
		this.urlData = urlData;
	}
	public List<StatusCode> getStatusCode() {
		return statusData;
	}
	public void setStatusCode(List<StatusCode> statusCode) {
		this.statusData = statusCode;
	}

}
