package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;

public class FStaffRegistrationFormDto implements Serializable {

	private String firstName;
	private String lastName;
	private String middleName;
	private String userName;
	private String passwd;
	private Long staffRoleId;
	private String empId;
	private Date dob;
	private String gender;
	private Date dateOfHire;
	private Date relievingDate;
	private String address;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String contactType;
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
	private Long staffId;
	private String operation;
	public FStaffRegistrationFormDto() {
	}

	public FStaffRegistrationFormDto(String firstName, String lastName,
			String middleName, String userName, String passwd,
			Long staffRoleId, String empId, Date dob, String gender,
			Date dateOfHire, Date relievingDate, String address, String street,
			String city, String state, String zipcode, String contactType,
			String mobileNo, String email, String workNumber, String workEmail,
			Boolean primaryFlag, Date dateCreated, String userCreated,
			Date dateModified, String userModified,Long facilityId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.userName = userName;
		this.passwd = passwd;
		this.staffRoleId = staffRoleId;
		this.empId = empId;
		this.dob = dob;
		this.gender = gender;
		this.dateOfHire = dateOfHire;
		this.relievingDate = relievingDate;
		this.address = address;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.contactType = contactType;
		this.mobileNo = mobileNo;
		this.email = email;
		this.workNumber = workNumber;
		this.workEmail = workEmail;
		this.primaryFlag = primaryFlag;
		this.dateCreated = dateCreated;
		this.userCreated = userCreated;
		this.dateModified = dateModified;
		this.userModified = userModified;
		this.facilityId = facilityId;
	}

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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public Long getStaffRoleId() {
		return staffRoleId;
	}

	public void setStaffRoleId(Long staffRoleId) {
		this.staffRoleId = staffRoleId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
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
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
	}
