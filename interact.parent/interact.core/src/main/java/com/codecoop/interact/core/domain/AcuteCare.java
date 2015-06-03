package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOSPITAL_TRANSFER", catalog = "INTERACT")
public class AcuteCare implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
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
	private boolean  stayInAcuteCareFlag;
	private boolean  sendToHospitalFlag;
	private Date dateCreated;
	private Date dateModified;
	private String userCreated;
	private String userModified;
	private String msgScenariocode;
	private Boolean alertMailFlag;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "PATIENT_EPISODE_ID")
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	@Column(name = "PRIM_DIAG_FOR_ADMISSION")
	public String getPrimDiagForAdmission() {
		return primDiagForAdmission;
	}
	public void setPrimDiagForAdmission(String primDiagForAdmission) {
		this.primDiagForAdmission = primDiagForAdmission;
	}
	@Column(name = "SENT_TO_HOSPITAL")
	public String getSentToHospital() {
		return sentToHospital;
	}
	public void setSentToHospital(String sentToHospital) {
		this.sentToHospital = sentToHospital;
	}
	@Column(name = "FORM_COMPLETED_BY")
	public String getFormCompletedBy() {
		return formCompletedBy;
	}
	public void setFormCompletedBy(String formCompletedBy) {
		this.formCompletedBy = formCompletedBy;
	}
	@Column(name = "DATE_OF_TRANSFER")
	public Date getDateOfTransfer() {
		return dateOfTransfer;
	}
	public void setDateOfTransfer(Date dateOfTransfer) {
		this.dateOfTransfer = dateOfTransfer;
	}
	@Column(name = "SENT_FROM")
	public String getSentFrom() {
		return sentFrom;
	}
	public void setSentFrom(String sentFrom) {
		this.sentFrom = sentFrom;
	}
	@Column(name = "FROM_UNIT")
	public String getFromUnit() {
		return fromUnit;
	}
	public void setFromUnit(String fromUnit) {
		this.fromUnit = fromUnit;
	}
	@Column(name = "FAMILY_AND_OTHER_ISSUES")
	public String getFamilyAndOtherIssues() {
		return familyAndOtherIssues;
	}
	public void setFamilyAndOtherIssues(String familyAndOtherIssues) {
		this.familyAndOtherIssues = familyAndOtherIssues;
	}
	@Column(name = "BEHAVIOURAL_ISSUES_AND_INTERVENTIONS")
	public String getBehaviouralIssuesAndInterventions() {
		return behaviouralIssuesAndInterventions;
	}
	public void setBehaviouralIssuesAndInterventions(
			String behaviouralIssuesAndInterventions) {
		this.behaviouralIssuesAndInterventions = behaviouralIssuesAndInterventions;
	}
	@Column(name = "CALLED_NURSE_FOR_QUESTION")
	public Long getCalledNurseForQuestion() {
		return calledNurseForQuestion;
	}
	public void setCalledNurseForQuestion(Long calledNurseForQuestion) {
		this.calledNurseForQuestion = calledNurseForQuestion;
	}
	@Column(name = "SOCIAL_WORKER_NAME")
	public String getSocialWorkerName() {
		return socialWorkerName;
	}
	public void setSocialWorkerName(String socialWorkerName) {
		this.socialWorkerName = socialWorkerName;
	}
	@Column(name = "SOCIAL_WORKER_TEL")
	public String getSocialWorkerTel() {
		return socialWorkerTel;
	}
	public void setSocialWorkerTel(String socialWorkerTel) {
		this.socialWorkerTel = socialWorkerTel;
	}
	@Column(name = "TRANSFER_NOTIFIED")
	public boolean isTransferNotified() {
		return transferNotified;
	}
	public void setTransferNotified(boolean transferNotified) {
		this.transferNotified = transferNotified;
	}
	@Column(name = "CLINICAL_SITUATION_AWARE")
	public boolean isClinicalSituationAware() {
		return clinicalSituationAware;
	}
	public void setClinicalSituationAware(boolean clinicalSituationAware) {
		this.clinicalSituationAware = clinicalSituationAware;
	}
	@Column(name = "FULL_CODE")
	public boolean isFullCode() {
		return fullCode;
	}
	public void setFullCode(boolean fullCode) {
		this.fullCode = fullCode;
	}
	@Column(name = "DNR")
	public boolean isDnr() {
		return dnr;
	}
	public void setDnr(boolean dnr) {
		this.dnr = dnr;
	}
	@Column(name = "DNI")
	public boolean isDni() {
		return dni;
	}
	public void setDni(boolean dni) {
		this.dni = dni;
	}
	@Column(name = "DNH")
	public boolean isDnh() {
		return dnh;
	}
	public void setDnh(boolean dnh) {
		this.dnh = dnh;
	}
	@Column(name = "COMFORT_CARE_ONLY")
	public boolean isComfortCareOnly() {
		return comfortCareOnly;
	}
	public void setComfortCareOnly(boolean comfortCareOnly) {
		this.comfortCareOnly = comfortCareOnly;
	}
	@Column(name = "UNCERTAIN")
	public boolean isUncertain() {
		return uncertain;
	}
	public void setUncertain(boolean uncertain) {
		this.uncertain = uncertain;
	}
	
	@Column(name="STAY_IN_ACUTECARE_FLAG")
	public boolean isStayInAcuteCareFlag() {
		return stayInAcuteCareFlag;
	}
	public void setStayInAcuteCareFlag(boolean stayInAcuteCareFlag) {
		this.stayInAcuteCareFlag = stayInAcuteCareFlag;
	}
	@Column(name="SEND_TO_HOSPITAL_FLAG")
	public boolean isSendToHospitalFlag() {
		return sendToHospitalFlag;
	}
	public void setSendToHospitalFlag(boolean sendToHospitalFlag) {
		this.sendToHospitalFlag = sendToHospitalFlag;
	}
	@Column(name="DATE_CREATED")
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Column(name="DATE_MODIFIED")
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	@Column(name="USER_CREATED")
	public String getUserCreated() {
		return userCreated;
	}
	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}
	@Column(name="USER_MODIFIED")
	public String getUserModified() {
		return userModified;
	}
	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}
	@Column(name="MSG_SCENARIO_CODE")
	public String getMsgScenariocode() {
		return msgScenariocode;
	}
	public void setMsgScenariocode(String msgScenariocode) {
		this.msgScenariocode = msgScenariocode;
	}
	@Column(name="ALERT_MAIL_FLAG")
	public Boolean getAlertMailFlag() {
		return alertMailFlag;
	}
	public void setAlertMailFlag(Boolean alertMailFlag) {
		this.alertMailFlag = alertMailFlag;
	}

}
