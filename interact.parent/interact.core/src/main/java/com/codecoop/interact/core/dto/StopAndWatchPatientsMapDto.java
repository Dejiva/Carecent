package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class StopAndWatchPatientsMapDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8046644477811017060L;
	private Long patientId;
	private String patientName;
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	

}
