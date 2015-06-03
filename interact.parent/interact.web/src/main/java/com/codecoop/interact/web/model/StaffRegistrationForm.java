package com.codecoop.interact.web.model;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class StaffRegistrationForm{
	
	@Size(min=4,max=20,message = "first name length should be in 4 to 20")
	@NotEmpty(message = "first name is required")
	private String firstName;
	
	@Size(min=4,max=20,message = "last name length should be in 4 to 20")
	@NotEmpty(message = "last name is required")
	private String lastName;
	private String middleName;
	
	@NotEmpty(message = "userId should not be empty please provide first name and last name")
	private String userName;
	
	private String passwd;
	
	@Valid
	@Min(1)
	private Long staffRoleId;
	/*(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[^a-zA-Z]).{8,}  8 char long lower case and upper case one none alpha numaric */
/*	@Pattern(regexp="(?=.*?[0-9].*?[0-9])(?=.*?[A-Z]).{8,}",
			message = "password should contains at least 8 chars, two digits, one capital")*/
	private String empId;
	private String dob;
	
    @NotNull( message = "Doctor shoud be specified")
	private Long docRoleId;
	@NotEmpty(message = "Gender shoud be specified")
	private String gender;
	private String dateOfHire;
	private String relievingDate;
	private String address;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String contactType;
//	@Pattern(regexp="[0-9]{10}",message="Invalid MobileNumber")
	private String mobileNo;
	
	@Email(message="invalid email")
	private String email;
	
	@NotEmpty(message = "WorkNumber shoud be specified")
//	@Pattern(regexp="[0-9]{10}",message="Invalid WorkNumber")
	private String workNumber;
	
	@NotEmpty(message = "Work email shoud be specified")
	@Email(message="invalid work email")
	private String workEmail;
	
	private String primaryFlag;
	private String dateCreated;
	private String userCreated;
	private String dateModified;
	private String userModified;
	private Long facilityId;
	private Long staffId;
	private String operation;
	private boolean deactive;

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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfHire() {
		return dateOfHire;
	}
	public void setDateOfHire(String dateOfHire) {
		this.dateOfHire = dateOfHire;
	}
	public String getRelievingDate() {
		return relievingDate;
	}
	public void setRelievingDate(String relievingDate) {
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
	public String getPrimaryFlag() {
		return primaryFlag;
	}
	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getUserCreated() {
		return userCreated;
	}
	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}
	public String getDateModified() {
		return dateModified;
	}
	public void setDateModified(String dateModified) {
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
	public Long getDocRoleId() {
		return docRoleId;
	}
	public void setDocRoleId(Long docRoleId) {
		this.docRoleId = docRoleId;
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
	public boolean isDeactive() {
		return deactive;
	}
	public void setDeactive(boolean deactive) {
		this.deactive = deactive;
	}
	
}
