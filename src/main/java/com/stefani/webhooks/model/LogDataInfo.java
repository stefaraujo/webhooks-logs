package com.stefani.webhooks.model;

import java.util.List;

public class LogDataInfo {
	
	private List<UrlRequested> urlData;
	private List<StatusResponded> statusData;
	
	public List<UrlRequested> getUrlData() {
		return urlData;
	}
	public void setUrlData(List<UrlRequested> urlData) {
		this.urlData = urlData;
	}
	public List<StatusResponded> getStatusData() {
		return statusData;
	}
	public void setStatusData(List<StatusResponded> statusData) {
		this.statusData = statusData;
	}

}
