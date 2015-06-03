package com.codecoop.interact.web.model;

import java.util.Date;

public class SSNBean {
	private Long patientId;
	private String SSN;
	private Long facilityId;
	private Date fdischrgeDt;
	private String status;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
