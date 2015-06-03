package com.codecoop.interact.core.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


public class MessageDto implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1232939145919081074L;
	private Long patientId;
	private Long reportedToNurse;
	private Long reportedToDocter;
	private Long facilityId;
	private Long messageAlertDetailsId;
	private String msgSubj;
	private String msgBody;
	private Date sentDate;
	private Boolean readFlag;
    private Boolean deleteFlag;
    private Boolean suspendedFlag;
    private Long eventId;
    private Long patientEpisodeId;
    private String prevScenarioCode;
    
    private String currentScenarioCode;
    private Long msgCount;
    private Long alertCount;
    private List<Long> docList;
    private List<Long> messageAlertDetailsList;
    private String contactInfo;
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public MessageDto(){}
	
	public MessageDto(  String msgSubj,
			String msgBody, Date sentDate,Boolean readFlag,Long messageAlertDetailsId) {
		super();
		
		this.msgSubj = msgSubj;
		this.msgBody = msgBody;
		
		this.sentDate = sentDate;
		this.readFlag=readFlag;
		this.messageAlertDetailsId=messageAlertDetailsId;
		
	}

	
	public String getMsgSubj() {
		return msgSubj;
	}

	public void setMsgSubj(String msgSubj) {
		this.msgSubj = msgSubj;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Boolean getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Boolean readFlag) {
		this.readFlag = readFlag;
	}

	public Long getMessageAlertDetailsId() {
		return messageAlertDetailsId;
	}

	public void setMessageAlertDetailsId(Long messageAlertDetailsId) {
		this.messageAlertDetailsId = messageAlertDetailsId;
	}

	
	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}



	public Long getReportedToNurse() {
		return reportedToNurse;
	}

	public void setReportedToNurse(Long reportedToNurse) {
		this.reportedToNurse = reportedToNurse;
	}

	public Long getReportedToDocter() {
		return reportedToDocter;
	}

	public void setReportedToDocter(Long reportedToDocter) {
		this.reportedToDocter = reportedToDocter;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	public Boolean getSuspendedFlag() {
		return suspendedFlag;
	}

	public void setSuspendedFlag(Boolean suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	

	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}

	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}

	public String getPrevScenarioCode() {
		return prevScenarioCode;
	}

	public void setPrevScenarioCode(String prevScenarioCode) {
		this.prevScenarioCode = prevScenarioCode;
	}

	public String getCurrentScenarioCode() {
		return currentScenarioCode;
	}

	public void setCurrentScenarioCode(String currentScenarioCode) {
		this.currentScenarioCode = currentScenarioCode;
	}

	public Long getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(Long msgCount) {
		this.msgCount = msgCount;
	}

	public Long getAlertCount() {
		return alertCount;
	}

	public void setAlertCount(Long alertCount) {
		this.alertCount = alertCount;
	}

	public List<Long> getDocList() {
		return docList;
	}

	public void setDocList(List<Long> docList) {
		this.docList = docList;
	}

	public List<Long> getMessageAlertDetailsList() {
		return messageAlertDetailsList;
	}

	public void setMessageAlertDetailsList(List<Long> messageAlertDetailsList) {
		this.messageAlertDetailsList = messageAlertDetailsList;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

    
	
  	
	
	}
