package com.codecoop.interact.core.dto;

import java.io.Serializable;

/**
 * @author Ramesh
 *
 */
public class CarePathLabworkAppRejDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long staffId;
	private String userName;
	private Long fStaffId;
	private String role;
	private Boolean approvedFlag;
	private Boolean rejectedFlag;
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getApprovedFlag() {
		return approvedFlag;
	}
	public void setApprovedFlag(Boolean approvedFlag) {
		this.approvedFlag = approvedFlag;
	}
	public Boolean getRejectedFlag() {
		return rejectedFlag;
	}
	public void setRejectedFlag(Boolean rejectedFlag) {
		this.rejectedFlag = rejectedFlag;
	}
	
}
