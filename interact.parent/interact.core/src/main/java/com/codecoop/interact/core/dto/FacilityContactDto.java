package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;

public class FacilityContactDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private FacilityDto facility;
	private String contactType;
	private String contact;
	private Boolean primaryFlag;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public FacilityDto getFacility() {
		return facility;
	}
	public void setFacility(FacilityDto facility) {
		this.facility = facility;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Boolean getPrimaryFlag() {
		return primaryFlag;
	}
	public void setPrimaryFlag(Boolean primaryFlag) {
		this.primaryFlag = primaryFlag;
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
