package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MAIL_ALERT_SCHEDULES", catalog = "INTERACT")
public class MailAlertSchedules implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7807939465760794127L;
	private Long id;
	private Long patientEpisodeId;
	private Long staffResponseTrackerId;
	private Long responseTimeCal;
	private Boolean AlertMailFlag;
	private Long reportedToFacilitystaffId;
	private Long eventId;
	public MailAlertSchedules(){
		
	}
    public MailAlertSchedules(Long patientEpisodeId,Long staffResponseTrackerId,Long responseTimeCal,Boolean responseFlag,Long reportedToFacilitystaffId,Long stopandwatchHistoryId){
    	this.patientEpisodeId=patientEpisodeId;
    	this.staffResponseTrackerId=staffResponseTrackerId;
    	this.responseTimeCal=responseTimeCal;
    	this.AlertMailFlag=AlertMailFlag;
    	this.reportedToFacilitystaffId=reportedToFacilitystaffId;
    	this.eventId=eventId;
    	
    }
	public void setId(Long id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	@Column(name ="PATIENT_EPISODE_ID")
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	
	
	
	@Column(name ="RESPONSE_TIME_CAL")
	public Long getResponseTimeCal() {
		return responseTimeCal;
	}

	@Column(name ="STAFF_RESPONSE_TRACKER_ID")
	public Long getStaffResponseTrackerId() {
		return staffResponseTrackerId;
	}
	public void setStaffResponseTrackerId(Long staffResponseTrackerId) {
		this.staffResponseTrackerId = staffResponseTrackerId;
	}
	public void setResponseTimeCal(Long responseTimeCal) {
		this.responseTimeCal = responseTimeCal;
	}
	
	

	@Column(name ="REPORTED_FACILITY_STAFF_ID")
	public Long getReportedToFacilitystaffId() {
		return reportedToFacilitystaffId;
	}
	@Column(name ="ALERT_MAIL_FLAG")
	public Boolean getAlertMailFlag() {
		return AlertMailFlag;
	}
	public void setAlertMailFlag(Boolean alertMailFlag) {
		AlertMailFlag = alertMailFlag;
	}
	public void setReportedToFacilitystaffId(Long reportedToFacilitystaffId) {
		this.reportedToFacilitystaffId = reportedToFacilitystaffId;
	}
	@Column(name ="EVENT_ID")
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	
}
