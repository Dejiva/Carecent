package com.codecoop.interact.web.rest.bean;

public class RestObservationRequest {
	private Long fStaffId;
	private String userName;
	private Long patientEpisodeId;

	public Long getfStaffId() {
		return fStaffId;
	}
	public void setfStaffId(Long fStaffId) {
		this.fStaffId = fStaffId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	
	

}
