package com.stefani.webhooks.model;

public class StatusCode {
	
	private String status;
	private Integer quantity;
	
	public StatusCode(String string, Integer quantity) {
		this.status = string;
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
