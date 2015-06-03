package com.codecoop.interact.web.rest.bean;

import java.util.List;


public class RestLoginResponse {
	
	private String status;
	private List<RestFacilitiesResponse> facilitiesList;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<RestFacilitiesResponse> getFacilitiesList() {
		return facilitiesList;
	}
	public void setFacilitiesList(List<RestFacilitiesResponse> facilitiesList) {
		this.facilitiesList = facilitiesList;
	}
}
