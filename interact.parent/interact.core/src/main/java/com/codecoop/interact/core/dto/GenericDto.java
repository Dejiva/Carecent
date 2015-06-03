package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class GenericDto implements Serializable{
	private Long eventId;;
	private Long stopAndWatchId;
	private Long reportedFacilityStaffId;
	private Date reportedDateTime;
	private Date dateCreated;
	private String previousScenarioCode;
	private String currentScenarioCode;
	private Long waitTime;
	private Long maxWaitTime;
	private String msgSubj;
	private String tableName;
	private Long patientEpisodeId;
	private Long facilityId;
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	

	public Long getReportedFacilityStaffId() {
		return reportedFacilityStaffId;
	}

	public void setReportedFacilityStaffId(Long reportedFacilityStaffId) {
		this.reportedFacilityStaffId = reportedFacilityStaffId;
	}

	

	/*public String getFacilityStaffResponse() {
		return facilityStaffResponse;
	}

	public void setFacilityStaffResponse(String facilityStaffResponse) {
		this.facilityStaffResponse = facilityStaffResponse;
	}*/



	

	public Date getReportedDateTime() {
		return reportedDateTime;
	}

	public void setReportedDateTime(Date reportedDateTime) {
		this.reportedDateTime = reportedDateTime;
	}

	public Long getStopAndWatchId() {
		return stopAndWatchId;
	}

	public void setStopAndWatchId(Long stopAndWatchId) {
		this.stopAndWatchId = stopAndWatchId;
	}

	

	

	public Long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(Long waitTime) {
		this.waitTime = waitTime;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(Long maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public String getPreviousScenarioCode() {
		return previousScenarioCode;
	}

	public void setPreviousScenarioCode(String previousScenarioCode) {
		this.previousScenarioCode = previousScenarioCode;
	}

	public String getCurrentScenarioCode() {
		return currentScenarioCode;
	}

	public void setCurrentScenarioCode(String currentScenarioCode) {
		this.currentScenarioCode = currentScenarioCode;
	}

	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}

	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public String getMsgSubj() {
		return msgSubj;
	}

	public void setMsgSubj(String msgSubj) {
		this.msgSubj = msgSubj;
	}

	
	

}
