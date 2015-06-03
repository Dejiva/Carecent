package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;


public class SSNCheckDto implements Serializable{
	private static final long serialVersionUID = -2629017345206038967L;
	private Long patientId;
	private String SSN;
	private Long facilityId;
	private Date fdischrgeDt;
	
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public Long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}
	public Date getFdischrgeDt() {
		return fdischrgeDt;
	}
	public void setFdischrgeDt(Date fdischrgeDt) {
		this.fdischrgeDt = fdischrgeDt;
	}
	
}
