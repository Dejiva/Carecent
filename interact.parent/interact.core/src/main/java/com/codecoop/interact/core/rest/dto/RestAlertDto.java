package com.codecoop.interact.core.rest.dto;

import java.util.Date;

public class RestAlertDto {
	
	private Long patientEpisodeId;
	private Long fStaffId;
	private String facilityName;
	private String patientName;
	private Date sentDate;
	private String msgBody;
	private String roleName;
	
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	public Long getfStaffId() {
		return fStaffId;
	}
	public void setfStaffId(Long fStaffId) {
		this.fStaffId = fStaffId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	

}
