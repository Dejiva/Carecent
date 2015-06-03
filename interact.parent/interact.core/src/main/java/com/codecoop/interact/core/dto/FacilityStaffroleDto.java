package com.codecoop.interact.core.dto;

import java.util.Date;

import com.codecoop.interact.core.domain.StaffRoles;


public class FacilityStaffroleDto implements java.io.Serializable {

	private Long id;
	private String facilityStaffrole;
	private String description;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	
	public FacilityStaffroleDto() {
	}
	public FacilityStaffroleDto(StaffRoles fSRole) {
		this.id = fSRole.getId();
		this.facilityStaffrole = fSRole.getRoleName();
		this.description = fSRole.getDescription();
		this.dateCreated = fSRole.getDateCreated();
		this.userCreated = fSRole.getUserCreated();
		this.dateModified = fSRole.getDateModified();
		this.userModified = fSRole.getUserModified();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFacilityStaffrole() {
		return facilityStaffrole;
	}
	public void setFacilityStaffrole(String facilityStaffrole) {
		this.facilityStaffrole = facilityStaffrole;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

}
