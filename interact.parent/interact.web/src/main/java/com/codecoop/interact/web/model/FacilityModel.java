package com.codecoop.interact.web.model;

import java.io.Serializable;
import java.util.Date;

public class FacilityModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String facilityName;
	private String faddress;
	private String fstreet;
	private String fcity;
	private String fstate;
	private String fzipcode;
	private String fcontactType;
	private String fcontact;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	private String standingOrders;
	private String fstatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public String getFstreet() {
		return fstreet;
	}
	public void setFstreet(String fstreet) {
		this.fstreet = fstreet;
	}
	public String getFcity() {
		return fcity;
	}
	public void setFcity(String fcity) {
		this.fcity = fcity;
	}
	public String getFstate() {
		return fstate;
	}
	public void setFstate(String fstate) {
		this.fstate = fstate;
	}
	public String getFzipcode() {
		return fzipcode;
	}
	public void setFzipcode(String fzipcode) {
		this.fzipcode = fzipcode;
	}
	public String getFcontactType() {
		return fcontactType;
	}
	public void setFcontactType(String fcontactType) {
		this.fcontactType = fcontactType;
	}
	public String getFcontact() {
		return fcontact;
	}
	public void setFcontact(String fcontact) {
		this.fcontact = fcontact;
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
	public String getFstatus() {
		return fstatus;
	}
	public void setFstatus(String fstatus) {
		this.fstatus = fstatus;
	}
	public String getStandingOrders() {
		return standingOrders;
	}
	public void setStandingOrders(String standingOrders) {
		this.standingOrders = standingOrders;
	}
	
	}
