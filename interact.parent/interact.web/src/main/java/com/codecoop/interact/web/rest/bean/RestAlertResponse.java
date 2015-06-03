package com.codecoop.interact.web.rest.bean;

import java.util.List;

import com.codecoop.interact.core.rest.dto.RestAlertDto;

public class RestAlertResponse {
	private String status;
	private List<RestAlertDto> alert;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<RestAlertDto> getAlert() {
		return alert;
	}
	public void setAlert(List<RestAlertDto> alert) {
		this.alert = alert;
	}
	
	
	

}
