package com.codecoop.interact.core.dto;

import java.util.Date;

import com.codecoop.interact.core.domain.PatientEpisode;

public class StopAndWatchDto implements java.io.Serializable {

	public StopAndWatchDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1247734949619519138L;
	/**
	 * 
	 */
	private Long id;
	private Long stopAndWatchHistoryId;
	private PatientEpisode patientEpisode;
	private Long residentId;
	private Boolean seemsDifferentThanUsual;
	private Boolean communicatesLess;
	private Boolean needsMoreHelp;
	private Boolean pain;
//	private Boolean participatedLessInActivities;
	private Boolean ateLess;
//	private Boolean diarrhea;
	private Boolean noBowelMovement;
	private Boolean drankLess;
	private Boolean weightChange;
	private Boolean agitated;
	private Boolean tired;
//	private Boolean weak;
//	private Boolean confused;
//	private Boolean drowsy;
	private Boolean changeInSkinCondition;
	private Boolean helpWithWalking;
	private Boolean transferring;
	private Boolean toiletingMoreThanUsual;
	private Long repotedById;
	private String guestName;
	private Long reportedToFacilityStaffId;
	private Date reportedDateAndTime;
	private String facilityStafResponse;
	private Long nurseId;
	private Date responseDateAndTime;
	private Long facilityId;
    private String message;
    private Boolean other;
    private String otherSymptom;
    private Boolean invalidateFlag;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StopAndWatchDto(Boolean seemsDifferentThanUsual,
			Boolean communicatesLess, Boolean needsMoreHelp, Boolean pain,
			 Boolean ateLess,
			 Boolean noBowelMovement, Boolean drankLess,
			Boolean weightChange, Boolean agitated, Boolean tired,
			
			Boolean changeInSkinCondition, Boolean helpWithWalking
			) {
		super();
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
		
	}

	public Boolean getOther() {
		return other;
	}

	public void setOther(Boolean other) {
		this.other = other;
	}

	public String getOtherSymptom() {
		return otherSymptom;
	}

	public void setOtherSymptom(String otherSymptom) {
		this.otherSymptom = otherSymptom;
	}

	public Long getResidentId() {
		return residentId;
	}
  
	public Long getStopAndWatchHistoryId() {
		return stopAndWatchHistoryId;
	}

	public void setStopAndWatchHistoryId(Long stopAndWatchHistoryId) {
		this.stopAndWatchHistoryId = stopAndWatchHistoryId;
	}

	public Long getReportedToFacilityStaffId() {
		return reportedToFacilityStaffId;
	}

	public void setReportedToFacilityStaffId(Long reportedToFacilityStaffId) {
		this.reportedToFacilityStaffId = reportedToFacilityStaffId;
	}

	public void setResidentId(Long residentId) {
		this.residentId = residentId;
	}

	public Long getNurseId() {
		return nurseId;
	}

	public void setNurseId(Long nurseId) {
		this.nurseId = nurseId;
	}

	public PatientEpisode getPatientEpisode() {
		return patientEpisode;
	}

	public void setPatientEpisode(PatientEpisode patientEpisode) {
		this.patientEpisode = patientEpisode;
	}

	public Boolean getSeemsDifferentThanUsual() {
		return seemsDifferentThanUsual;
	}

	public void setSeemsDifferentThanUsual(Boolean seemsDifferentThanUsual) {
		this.seemsDifferentThanUsual = seemsDifferentThanUsual;
	}

	public Boolean getCommunicatesLess() {
		return communicatesLess;
	}

	public void setCommunicatesLess(Boolean communicatesLess) {
		this.communicatesLess = communicatesLess;
	}

	public Boolean getNeedsMoreHelp() {
		return needsMoreHelp;
	}

	public void setNeedsMoreHelp(Boolean needsMoreHelp) {
		this.needsMoreHelp = needsMoreHelp;
	}

	public Boolean getPain() {
		return pain;
	}

	public void setPain(Boolean pain) {
		this.pain = pain;
	}

	
	public Boolean getAteLess() {
		return ateLess;
	}

	public void setAteLess(Boolean ateLess) {
		this.ateLess = ateLess;
	}

	

	public Boolean getNoBowelMovement() {
		return noBowelMovement;
	}

	public void setNoBowelMovement(Boolean noBowelMovement) {
		this.noBowelMovement = noBowelMovement;
	}

	public Boolean getDrankLess() {
		return drankLess;
	}

	public void setDrankLess(Boolean drankLess) {
		this.drankLess = drankLess;
	}

	public Boolean getWeightChange() {
		return weightChange;
	}

	public void setWeightChange(Boolean weightChange) {
		this.weightChange = weightChange;
	}

	public Boolean getAgitated() {
		return agitated;
	}

	public void setAgitated(Boolean agitated) {
		this.agitated = agitated;
	}

	public Boolean getTired() {
		return tired;
	}

	public void setTired(Boolean tired) {
		this.tired = tired;
	}

	
	public Boolean getChangeInSkinCondition() {
		return changeInSkinCondition;
	}

	public void setChangeInSkinCondition(Boolean changeInSkinCondition) {
		this.changeInSkinCondition = changeInSkinCondition;
	}

	public Boolean getHelpWithWalking() {
		return helpWithWalking;
	}

	public void setHelpWithWalking(Boolean helpWithWalking) {
		this.helpWithWalking = helpWithWalking;
	}

	public Boolean getTransferring() {
		return transferring;
	}

	public void setTransferring(Boolean transferring) {
		this.transferring = transferring;
	}

	public Boolean getToiletingMoreThanUsual() {
		return toiletingMoreThanUsual;
	}

	public void setToiletingMoreThanUsual(Boolean toiletingMoreThanUsual) {
		this.toiletingMoreThanUsual = toiletingMoreThanUsual;
	}

	public Long getRepotedById() {
		return repotedById;
	}

	public void setRepotedById(Long repotedById) {
		this.repotedById = repotedById;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Date getReportedDateAndTime() {
		return reportedDateAndTime;
	}

	public void setReportedDateAndTime(Date reportedDateAndTime) {
		this.reportedDateAndTime = reportedDateAndTime;
	}

	public String getFacilityStafResponse() {
		return facilityStafResponse;
	}

	public void setFacilityStafResponse(String facilityStafResponse) {
		this.facilityStafResponse = facilityStafResponse;
	}

	public Date getResponseDateAndTime() {
		return responseDateAndTime;
	}

	public void setResponseDateAndTime(Date responseDateAndTime) {
		this.responseDateAndTime = responseDateAndTime;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public void setReportedToFacilityStaffId(String reportedToFacilityStaffName2) {
		// TODO Auto-generated method stub

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getInvalidateFlag() {
		return invalidateFlag;
	}

	public void setInvalidateFlag(Boolean invalidateFlag) {
		this.invalidateFlag = invalidateFlag;
	}


}
