package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;

public class AcuteCareDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long patientId;
	private Long patientEpisodeId;
	private String  primDiagForAdmission;
	private String  sentToHospital;
	private String   formCompletedBy;
	private Date   dateOfTransfer;
	private String   sentFrom;
	private String  fromUnit;
	private String  familyAndOtherIssues;
	private String  behaviouralIssuesAndInterventions;
	private Long   calledNurseForQuestion;
	private String  socialWorkerName;
	private String  socialWorkerTel;
	private boolean  transferNotified;
	private boolean  clinicalSituationAware;
	private boolean  fullCode;
	private boolean  dnr;
	private boolean  dni;
	private boolean dnh;
	private boolean  comfortCareOnly;
	private boolean  uncertain;
	private String msgScenariocode;
	private Boolean alertMailFlag;
	private String patientStatusInAcuteCare;
	private Boolean sendToHospitalFlag;
	private Boolean stayInAcutecareFlag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	public String getPrimDiagForAdmission() {
		return primDiagForAdmission;
	}
	public void setPrimDiagForAdmission(String primDiagForAdmission) {
		this.primDiagForAdmission = primDiagForAdmission;
	}
	public String getSentToHospital() {
		return sentToHospital;
	}
	public void setSentToHospital(String sentToHospital) {
		this.sentToHospital = sentToHospital;
	}
	public String getFormCompletedBy() {
		return formCompletedBy;
	}
	public void setFormCompletedBy(String formCompletedBy) {
		this.formCompletedBy = formCompletedBy;
	}
	public Date getDateOfTransfer() {
		return dateOfTransfer;
	}
	public void setDateOfTransfer(Date dateOfTransfer) {
		this.dateOfTransfer = dateOfTransfer;
	}
	public String getSentFrom() {
		return sentFrom;
	}
	public void setSentFrom(String sentFrom) {
		this.sentFrom = sentFrom;
	}
	public String getFromUnit() {
		return fromUnit;
	}
	public void setFromUnit(String fromUnit) {
		this.fromUnit = fromUnit;
	}
	public String getFamilyAndOtherIssues() {
		return familyAndOtherIssues;
	}
	public void setFamilyAndOtherIssues(String familyAndOtherIssues) {
		this.familyAndOtherIssues = familyAndOtherIssues;
	}
	public String getBehaviouralIssuesAndInterventions() {
		return behaviouralIssuesAndInterventions;
	}
	public void setBehaviouralIssuesAndInterventions(
			String behaviouralIssuesAndInterventions) {
		this.behaviouralIssuesAndInterventions = behaviouralIssuesAndInterventions;
	}
	public Long getCalledNurseForQuestion() {
		return calledNurseForQuestion;
	}
	public void setCalledNurseForQuestion(Long calledNurseForQuestion) {
		this.calledNurseForQuestion = calledNurseForQuestion;
	}
	public String getSocialWorkerName() {
		return socialWorkerName;
	}
	public void setSocialWorkerName(String socialWorkerName) {
		this.socialWorkerName = socialWorkerName;
	}
	public String getSocialWorkerTel() {
		return socialWorkerTel;
	}
	public void setSocialWorkerTel(String socialWorkerTel) {
		this.socialWorkerTel = socialWorkerTel;
	}
	public boolean isTransferNotified() {
		return transferNotified;
	}
	public void setTransferNotified(boolean transferNotified) {
		this.transferNotified = transferNotified;
	}
	public boolean isClinicalSituationAware() {
		return clinicalSituationAware;
	}
	public void setClinicalSituationAware(boolean clinicalSituationAware) {
		this.clinicalSituationAware = clinicalSituationAware;
	}
	public boolean isFullCode() {
		return fullCode;
	}
	public void setFullCode(boolean fullCode) {
		this.fullCode = fullCode;
	}
	public boolean isDnr() {
		return dnr;
	}
	public void setDnr(boolean dnr) {
		this.dnr = dnr;
	}
	public boolean isDni() {
		return dni;
	}
	public void setDni(boolean dni) {
		this.dni = dni;
	}
	public boolean isDnh() {
		return dnh;
	}
	public void setDnh(boolean dnh) {
		this.dnh = dnh;
	}
	public boolean isComfortCareOnly() {
		return comfortCareOnly;
	}
	public void setComfortCareOnly(boolean comfortCareOnly) {
		this.comfortCareOnly = comfortCareOnly;
	}
	public boolean isUncertain() {
		return uncertain;
	}
	public void setUncertain(boolean uncertain) {
		this.uncertain = uncertain;
	}
	public String getMsgScenariocode() {
		return msgScenariocode;
	}
	public void setMsgScenariocode(String msgScenariocode) {
		this.msgScenariocode = msgScenariocode;
	}
	public Boolean getAlertMailFlag() {
		return alertMailFlag;
	}
	public void setAlertMailFlag(Boolean alertMailFlag) {
		this.alertMailFlag = alertMailFlag;
	}
	public String getPatientStatusInAcuteCare() {
		return patientStatusInAcuteCare;
	}
	public void setPatientStatusInAcuteCare(String patientStatusInAcuteCare) {
		this.patientStatusInAcuteCare = patientStatusInAcuteCare;
	}
	public Boolean getSendToHospitalFlag() {
		return sendToHospitalFlag;
	}
	public void setSendToHospitalFlag(Boolean sendToHospitalFlag) {
		this.sendToHospitalFlag = sendToHospitalFlag;
	}
	public Boolean getStayInAcutecareFlag() {
		return stayInAcutecareFlag;
	}
	public void setStayInAcutecareFlag(Boolean stayInAcutecareFlag) {
		this.stayInAcutecareFlag = stayInAcutecareFlag;
	}
}
