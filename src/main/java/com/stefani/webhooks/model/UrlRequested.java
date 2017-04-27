package com.stefani.webhooks.model;

public class UrlRequested {

	private String url;
	private Integer quantity;

	public UrlRequested() {
	}

	public UrlRequested(String url) {
		this.url = url;
	}

	public UrlRequested(String string, Integer quantity) {
		this.url = string;
		this.quantity = quantity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
