package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FacilityDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String facilityName;
	private boolean standingOrders;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	
	private Set<FacilityAddressDto> facilityAddresses;
	private Set<FacilityStaffDto> facilityStaffs;
	private Set<FacilityContactDto> facilityContacts;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public boolean isStandingOrders() {
		return standingOrders;
	}
	public void setStandingOrders(boolean standingOrders) {
		this.standingOrders = standingOrders;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
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
	public Set<FacilityAddressDto> getFacilityAddresses() {
		if(facilityAddresses == null){
			return facilityAddresses = new HashSet<FacilityAddressDto>();
		}
		return facilityAddresses;
	}
	public void setFacilityAddresses(Set<FacilityAddressDto> facilityAddresses) {
		this.facilityAddresses = facilityAddresses;
	}
	public Set<FacilityStaffDto> getFacilityStaffs() {
		if(facilityStaffs == null){
			return facilityStaffs = new HashSet<FacilityStaffDto>();
		}
		return facilityStaffs;
	}
	public void setFacilityStaffs(Set<FacilityStaffDto> facilityStaffs) {
		this.facilityStaffs = facilityStaffs;
	}
	public Set<FacilityContactDto> getFacilityContacts() {
		if(facilityContacts == null){
			return facilityContacts = new HashSet<FacilityContactDto>();
		}
		return facilityContacts;
	}
	public void setFacilityContacts(Set<FacilityContactDto> facilityContacts) {
		this.facilityContacts = facilityContacts;
	}
	
}
