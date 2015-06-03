package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class GuardianDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4088982953365196579L;
	
	private Long guardianId;
	private String guardianName;
	private String contactNumber;
	
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Long getGuardianId() {
		return guardianId;
	}
	public void setGuardianId(Long guardianId) {
		this.guardianId = guardianId;
	}
	
}
