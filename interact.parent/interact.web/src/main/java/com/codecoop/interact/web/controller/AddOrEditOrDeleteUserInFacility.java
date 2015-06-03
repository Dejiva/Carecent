package com.codecoop.interact.web.controller;

import java.io.Serializable;
import java.util.Date;

public class AddOrEditOrDeleteUserInFacility implements Serializable {
	
	private String firstName;
	private String lastName;
	private String middleName;
	private String userName;
	private String fStaffrole;
	private String empId;
	private Date dateOfHire;
	private Date relievingDate;
	private String address;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String mobileNo;
	private String email;
	private String workNumber;
	private String workEmail;
	private Boolean primaryFlag;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	private Long facilityId;
	private Long StaffId;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getfStaffrole() {
		return fStaffrole;
	}
	public void setfStaffrole(String fStaffrole) {
		this.fStaffrole = fStaffrole;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Date getDateOfHire() {
		return dateOfHire;
	}
	public void setDateOfHire(Date dateOfHire) {
		this.dateOfHire = dateOfHire;
	}
	public Date getRelievingDate() {
		return relievingDate;
	}
	public void setRelievingDate(Date relievingDate) {
		this.relievingDate = relievingDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkNumber() {
		return workNumber;
	}
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
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
	public Long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}
	public Long getStaffId() {
		return StaffId;
	}
	public void setStaffId(Long staffId) {
		StaffId = staffId;
	}

}
