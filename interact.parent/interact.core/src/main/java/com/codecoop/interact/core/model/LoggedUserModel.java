package com.codecoop.interact.core.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * @author Ramesh
 *
 */
@Component
public class LoggedUserModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long fstaffId;
	private String userName;
	private Long facilityId;
	private String facilityName;
	private String fcontact;
	private Boolean  standingOrders;
	private Long roleId;
	private String roleName;
	private String roleValue;
	private Long responsibilityId;
	private String responsibilityName;
	private String fullName;
	private String userModified;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public Long getFstaffId() {
		return fstaffId;
	}
	public void setFstaffId(Long fstaffId) {
		this.fstaffId = fstaffId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getFcontact() {
		return fcontact;
	}
	public void setFcontact(String fcontact) {
		this.fcontact = fcontact;
	}
	public Boolean getStandingOrders() {
		return standingOrders;
	}
	public void setStandingOrders(Boolean standingOrders) {
		this.standingOrders = standingOrders;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public String getRoleValue() {
		return roleValue;
	}
	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getResponsibilityId() {
		return responsibilityId;
	}
	public void setResponsibilityId(Long responsibilityId) {
		this.responsibilityId = responsibilityId;
	}
	public String getResponsibilityName() {
		return responsibilityName;
	}
	public void setResponsibilityName(String responsibilityName) {
		this.responsibilityName = responsibilityName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserModified() {
		return userModified;
	}
	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}

}
