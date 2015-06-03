package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class MailAlertSchedulesDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1897969355055164661L;
	private Long id;
	private Long patientEpisodeId;
	private Long staffResponseTrackerId;
	private Long responseTimeCal;
	private Boolean alertMailFlag;
	private Long reportedToFacilitystaffId;
	private Long eventId;
	public MailAlertSchedulesDto()
	{
		
		}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	
	public Long getResponseTimeCal() {
		return responseTimeCal;
	}
	public void setResponseTimeCal(Long responseTimeCal) {
		this.responseTimeCal = responseTimeCal;
	}
	
	
	public Long getReportedToFacilitystaffId() {
		return reportedToFacilitystaffId;
	}
	public void setReportedToFacilitystaffId(Long reportedToFacilitystaffId) {
		this.reportedToFacilitystaffId = reportedToFacilitystaffId;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public Long getStaffResponseTrackerId() {
		return staffResponseTrackerId;
	}
	public void setStaffResponseTrackerId(Long staffResponseTrackerId) {
		this.staffResponseTrackerId = staffResponseTrackerId;
	}

	public Boolean getAlertMailFlag() {
		return alertMailFlag;
	}

	public void setAlertMailFlag(Boolean alertMailFlag) {
		this.alertMailFlag = alertMailFlag;
	}
	
	

}
