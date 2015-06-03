package com.codecoop.interact.web.rest.bean;

import com.codecoop.interact.core.rest.dto.RestPatientObservationDto;

public class RestObservationResponse {
	
	private String status;
	private RestPatientObservationDto observation;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public RestPatientObservationDto getObservation() {
		return observation;
	}
	public void setObservation(RestPatientObservationDto observation) {
		this.observation = observation;
	}

}
