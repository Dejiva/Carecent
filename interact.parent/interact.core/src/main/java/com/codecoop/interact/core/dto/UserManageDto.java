package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;

public class UserManageDto implements Serializable {
	
	private String name;
	private Long staffId;
	private String userName;
	private String personalEmail;
	private String mobileNumber;
	private Long facilityId;
	private Long roleId;
	private String roleDescription;
	private String facilityName;
	private Date relivingDate;
	private boolean activeUser;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}
	
	public boolean isActiveUser() {
		if(this.relivingDate == null){
			this.activeUser = true;
		}else{
			this.activeUser = false;
		}
		return activeUser;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public void setActiveUser(boolean activeUser) {
		this.activeUser = activeUser;
	}
	public Date getRelivingDate() {
		return relivingDate;
	}
	public void setRelivingDate(Date relivingDate) {
		this.relivingDate = relivingDate;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	
}
