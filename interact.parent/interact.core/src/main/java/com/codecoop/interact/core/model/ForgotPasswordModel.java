package com.codecoop.interact.core.model;

import java.io.Serializable;

public class ForgotPasswordModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long staffId;
	private String userName;
	private Long fStaffId;
	private String workEmail;
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getfStaffId() {
		return fStaffId;
	}
	public void setfStaffId(Long fStaffId) {
		this.fStaffId = fStaffId;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	
}
