package com.codecoop.interact.web.rest.bean;

import java.util.List;

public class RestPatientResponse {
	private String status;
	private List<RestPatient> patients;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<RestPatient> getPatients() {
		return patients;
	}
	public void setPatients(List<RestPatient> patients) {
		this.patients = patients;
	}

}
