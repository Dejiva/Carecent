package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "STOP_AND_WATCH_HISTORY", catalog = "INTERACT")
public class StopAndWatchHistory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6633609862553481374L;

	private Long id;
    private StopAndWatch stopAndWatch;
	private Boolean seemsDifferentThanUsual;
	private Boolean communicatesLess;
	private Boolean needsMoreHelp;
	private Boolean pain;
	
	private Boolean ateLess;
	
	private Boolean noBowelMovement;
	private Boolean drankLess;
	private Boolean weightChange;
	private Boolean agitated;
	private Boolean tired;
	
	private Boolean changeInSkinCondition;
	private Boolean helpWithWalking;
	
	private FacilityStaff facilityStaff;
	private FacilityStaff reportedToFacilityStaff;
	private String guestName;
	private Date reportedDateAndTime;
	private String facilityStafResponse;
	private FacilityStaff nurse;
    private Date responseDateAndTime;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	private Boolean deleteFlag;
	private Boolean other;
	private String otherSymptom;
	private String msgScenarioCode;
	private Long waitTime;
    private Boolean alertMailFlag;
    private Boolean invalidateFlag;
	
	@Column(name = "OTHER")
	public Boolean getOther() {
		return other;
	}

	public void setOther(Boolean other) {
		this.other = other;
	}
	@Column(name = "OTHER_SYMPTOM", length = 200)
	public String getOtherSymptom() {
		return otherSymptom;
	}

	public void setOtherSymptom(String otherSymptom) {
		this.otherSymptom = otherSymptom;
	}

	public StopAndWatchHistory() {
	}

	public StopAndWatchHistory( StopAndWatch stopAndWatch,
			Boolean seemsDifferentThanUsual, Boolean communicatesLess,
			Boolean needsMoreHelp, Boolean pain,
			 Boolean ateLess,
			 Boolean noBowelMovement, Boolean drankLess,
			Boolean weightChange, Boolean agitated, Boolean tired,
			
			Boolean changeInSkinCondition, Boolean helpWithWalking,
			
			FacilityStaff facilityStaff, String guestName,
			FacilityStaff reportedToFacilityStaff, Date reportedDateAndTime,
			String facilityStafResponse, Date responseDateAndTime,
			Date dateCreated, String userCreated, Date dateModified,
			String userModified) {
		super();
	
		this.stopAndWatch = stopAndWatch;
		this.seemsDifferentThanUsual = seemsDifferentThanUsual;
		this.communicatesLess = communicatesLess;
		this.needsMoreHelp = needsMoreHelp;
		this.pain = pain;
		
		this.ateLess = ateLess;
		
		this.noBowelMovement = noBowelMovement;
		this.drankLess = drankLess;
		this.weightChange = weightChange;
		this.agitated = agitated;
		this.tired = tired;
		
		this.changeInSkinCondition = changeInSkinCondition;
		this.helpWithWalking = helpWithWalking;
	
		this.facilityStaff= facilityStaff;
		this.guestName = guestName;
		this.reportedToFacilityStaff= reportedToFacilityStaff;
		this.reportedDateAndTime = reportedDateAndTime;
		this.facilityStafResponse = facilityStafResponse;
		this.responseDateAndTime = responseDateAndTime;
		this.dateCreated = dateCreated;
		this.userCreated = userCreated;
		this.dateModified = dateModified;
		this.userModified = userModified;
	}
	@Column(name = "DELETE_FLAG")
	public Boolean getDeleteFlag() {
		if(deleteFlag == null){
			deleteFlag = false;
		}
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NURSE_ID")
	public FacilityStaff getNurse() {
		return nurse;
	}

	public void setNurse(FacilityStaff nurse) {
		this.nurse = nurse;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STOP_AND_WATCH_ID")
	public StopAndWatch getStopAndWatch() {
		return stopAndWatch;
	}

	public void setStopAndWatch(StopAndWatch stopAndWatch) {
		this.stopAndWatch = stopAndWatch;
	}

	@Column(name = "SEEMS_DIFFERENT_THAN_USUAL")
	public Boolean getSeemsDifferentThanUsual() {
		return seemsDifferentThanUsual;
	}

	public void setSeemsDifferentThanUsual(Boolean seemsDifferentThanUsual) {
		this.seemsDifferentThanUsual = seemsDifferentThanUsual;
	}

	@Column(name = "COMMUNICATES_LESS")
	public Boolean getCommunicatesLess() {
		return communicatesLess;
	}

	public void setCommunicatesLess(Boolean communicatesLess) {
		this.communicatesLess = communicatesLess;
	}

	@Column(name = "NEEDS_MORE_HELP")
	public Boolean getNeedsMoreHelp() {
		return needsMoreHelp;
	}

	public void setNeedsMoreHelp(Boolean needsMoreHelp) {
		this.needsMoreHelp = needsMoreHelp;
	}

	@Column(name = "PAIN")
	public Boolean getPain() {
		return pain;
	}

	public void setPain(Boolean pain) {
		this.pain = pain;
	}

	

	@Column(name = "ATE_LESS")
	public Boolean getAteLess() {
		return ateLess;
	}

	public void setAteLess(Boolean ateLess) {
		this.ateLess = ateLess;
	}

	

	@Column(name = "DRANK_LESS")
	public Boolean getDrankLess() {
		return drankLess;
	}

	public void setDrankLess(Boolean drankLess) {
		this.drankLess = drankLess;
	}

	@Column(name = "WEIGHT_CHANGE")
	public Boolean getWeightChange() {
		return weightChange;
	}

	public void setWeightChange(Boolean weightChange) {
		this.weightChange = weightChange;
	}

	@Column(name = "NO_BOWEL_MOVEMENT")
	public Boolean getNoBowelMovement() {
		return noBowelMovement;
	}

	public void setNoBowelMovement(Boolean noBowelMovement) {
		this.noBowelMovement = noBowelMovement;
	}

	
	@Column(name = "AGITATED")
	public Boolean getAgitated() {
		return agitated;
	}

	public void setAgitated(Boolean agitated) {
		this.agitated = agitated;
	}

	@Column(name = "TIRED")
	public Boolean getTired() {
		return tired;
	}

	public void setTired(Boolean tired) {
		this.tired = tired;
	}

	

	@Column(name = "CHANGE_IN_SKIN_CONDITION")
	public Boolean getChangeInSkinCondition() {
		return changeInSkinCondition;
	}

	public void setChangeInSkinCondition(Boolean changeInSkinCondition) {
		this.changeInSkinCondition = changeInSkinCondition;
	}

	@Column(name = "HELP_WITH_WALKING")
	public Boolean getHelpWithWalking() {
		return helpWithWalking;
	}

	public void setHelpWithWalking(Boolean helpWithWalking) {
		this.helpWithWalking = helpWithWalking;
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FACILITY_STAFF_ID")
	public FacilityStaff getFacilityStaff() {
		return facilityStaff;
	}
    
	public void setFacilityStaff(FacilityStaff facilityStaff) {
		this.facilityStaff = facilityStaff;
	}

	@Column(name = "GUEST_NAME", length = 50)
	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORTED_TO_FACILITY_STAFF_ID")
//	@Column(name = "REPORTED_TO_FACILITY_STAFF_ID")
	public FacilityStaff getReportedToFacilityStaff() {
		return reportedToFacilityStaff;
	}

	public void setReportedToFacilityStaff(FacilityStaff reportedToFacilityStaff) {
		this.reportedToFacilityStaff = reportedToFacilityStaff;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPORTED_DATE_AND_TIME", length = 19)
	public Date getReportedDateAndTime() {
		return reportedDateAndTime;
	}

	public void setReportedDateAndTime(Date reportedDateAndTime) {
		this.reportedDateAndTime = reportedDateAndTime;
	}

	@Column(name = "FACILITY_STAF_RESPONSE", length = 50)
	public String getFacilityStafResponse() {
		return facilityStafResponse;
	}

	public void setFacilityStafResponse(String facilityStafResponse) {
		this.facilityStafResponse = facilityStafResponse;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESPONSE_DATE_AND_TIME", length = 19)
	public Date getResponseDateAndTime() {
		return responseDateAndTime;
	}

	public void setResponseDateAndTime(Date responseDateAndTime) {
		this.responseDateAndTime = responseDateAndTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED", length = 19)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "USER_CREATED", length = 50)
	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFIED", length = 19)
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Column(name = "USER_MODIFIED", length = 50)
	public String getUserModified() {
		return this.userModified;
	}

	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}
	@Column(name ="MSG_SCENARIO_CODE", length = 49)
	public String getMsgScenarioCode() {
		return msgScenarioCode;
	}

	public void setMsgScenarioCode(String msgScenarioCode) {
		this.msgScenarioCode = msgScenarioCode;
	}
	
	@Column(name ="WAIT_TIME")
	public Long getWaitTime() {
		return waitTime;
	}

	

	public void setWaitTime(Long waitTime) {
		this.waitTime = waitTime;
	}
	@Column(name ="ALERT_MAIL_FLAG")
	public Boolean getAlertMailFlag() {
		return alertMailFlag;
	}

	public void setAlertMailFlag(Boolean alertMailFlag) {
		this.alertMailFlag = alertMailFlag;
	}
	@Column(name ="INVALIDATE_FLAG")
	public Boolean getInvalidateFlag() {
		return invalidateFlag;
	}

	public void setInvalidateFlag(Boolean invalidateFlag) {
		this.invalidateFlag = invalidateFlag;
	}


}
