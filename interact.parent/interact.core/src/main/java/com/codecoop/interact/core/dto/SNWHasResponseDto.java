package com.codecoop.interact.core.dto;



public class SNWHasResponseDto {

	private String  patientInfo;
	private Boolean  hasRespnse;
	private Boolean stopAndWatchFlag;
	public String getPatientInfo() {
		return patientInfo;
	}
	public void setPatientInfo(String patientInfo) {
		this.patientInfo = patientInfo;
	}
	public Boolean getHasRespnse() {
		return hasRespnse;
	}
	public void setHasRespnse(Boolean hasRespnse) {
		this.hasRespnse = hasRespnse;
	}
	public Boolean getStopAndWatchFlag() {
		return stopAndWatchFlag;
	}
	public void setStopAndWatchFlag(Boolean stopAndWatchFlag) {
		this.stopAndWatchFlag = stopAndWatchFlag;
	}


}
