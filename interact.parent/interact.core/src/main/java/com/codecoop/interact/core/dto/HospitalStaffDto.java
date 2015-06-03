package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class HospitalStaffDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4533765242857331535L;
	private Long hospitalStaffId;
	private Long hsptlStaffTypeId;
	private Long patientEpisodeId;
	private String staffName;
	private String contactNumber;
	private String emailId;
	private Long specialityId;
	private String specialityName;
	
	public Long getHospitalStaffId() {
		return hospitalStaffId;
	}
	public void setHospitalStaffId(Long hospitalStaffId) {
		this.hospitalStaffId = hospitalStaffId;
	}
	public Long getHsptlStaffTypeId() {
		return hsptlStaffTypeId;
	}
	public void setHsptlStaffTypeId(Long hsptlStaffTypeId) {
		this.hsptlStaffTypeId = hsptlStaffTypeId;
	}
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Long getSpecialityId() {
		return specialityId;
	}
	public void setSpecialityId(Long specialityId) {
		this.specialityId = specialityId;
	}
	public String getSpecialityName() {
		return specialityName;
	}
	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}
}
