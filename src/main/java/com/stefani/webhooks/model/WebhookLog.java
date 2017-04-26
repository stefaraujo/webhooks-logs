package com.stefani.webhooks.model;

import java.util.List;

public class WebhookLog {

	private List<String> requestTo;
	private List<String> responseStatus;

	public List<String> getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(List<String> requestTo) {
		this.requestTo = requestTo;
	}

	public List<String> getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(List<String> responseStatus) {
		this.responseStatus = responseStatus;
	}
}
