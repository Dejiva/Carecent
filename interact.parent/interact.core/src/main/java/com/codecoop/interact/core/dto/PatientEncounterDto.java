package com.codecoop.interact.core.dto;



import java.io.Serializable;
import java.util.Date;

import com.codecoop.interact.core.domain.PatientEpisode;

public class PatientEncounterDto implements Serializable{

	private static final long serialVersionUID = -788210113696671798L;
	private Long id;
	private Long patientEpisodeId;
	private Integer encounterNum;
	private Date startDate;
	private Date endDate;
	private String reason;
    private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	private Long fromHospitalId;
	private Date fromHsptalAdmissionDate;
	private Date fromHsptalDischargeDate;
	private String formHospitalUnit;
	
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
	public Integer getEncounterNum() {
		return encounterNum;
	}
	public void setEncounterNum(Integer encounterNum) {
		this.encounterNum = encounterNum;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getUserCreated() {
		return userCreated;
	}
	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public String getUserModified() {
		return userModified;
	}
	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}
	public Long getFromHospitalId() {
		return fromHospitalId;
	}
	public void setFromHospitalId(Long fromHospitalId) {
		this.fromHospitalId = fromHospitalId;
	}
	public Date getFromHsptalAdmissionDate() {
		return fromHsptalAdmissionDate;
	}
	public void setFromHsptalAdmissionDate(Date fromHsptalAdmissionDate) {
		this.fromHsptalAdmissionDate = fromHsptalAdmissionDate;
	}
	public Date getFromHsptalDischargeDate() {
		return fromHsptalDischargeDate;
	}
	public void setFromHsptalDischargeDate(Date fromHsptalDischargeDate) {
		this.fromHsptalDischargeDate = fromHsptalDischargeDate;
	}
	public String getFormHospitalUnit() {
		return formHospitalUnit;
	}
	public void setFormHospitalUnit(String formHospitalUnit) {
		this.formHospitalUnit = formHospitalUnit;
	}

}
