package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;

public class PatientDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4976456598424181132L;
	private Long patientId;
	private String patientFirstName;
	private String patientLastName;
	private Date dob;
	private Date doj;
	private String sSNumber;
	private String gender;
	private String bloodgroup;
	private String language;
	private String ethnicityType;
	private String ethnicityUd;
	private GuardianDto careGiver;
	private GuardianDto guardianOrProxy;
		
	
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getsSNumber() {
		return sSNumber;
	}
	public void setsSNumber(String sSNumber) {
		this.sSNumber = sSNumber;
	}
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getEthnicityType() {
		return ethnicityType;
	}
	public void setEthnicityType(String ethnicityType) {
		this.ethnicityType = ethnicityType;
	}
	public String getEthnicityUd() {
		return ethnicityUd;
	}
	public void setEthnicityUd(String ethnicityUd) {
		this.ethnicityUd = ethnicityUd;
	}
	public void setCareGiver(GuardianDto careGiver) {
		this.careGiver = careGiver;
	}
	public GuardianDto getCareGiver() {
		return careGiver;
	}
	public void setGuardianOrProxy(GuardianDto guardianOrProxy) {
		this.guardianOrProxy = guardianOrProxy;
	}
	public GuardianDto getGuardianOrProxy() {
		return guardianOrProxy;
	}
	
}
