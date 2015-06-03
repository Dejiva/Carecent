package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "STAFF", catalog = "INTERACT")
public class Staff implements java.io.Serializable {

	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dob;
	private String gender;
	private String username;
	private String passwd;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	private String deviceToken;
	private String deviceType;
	private Boolean deviceLoggedStatus;
	private Set<FacilityStaff> facilityStaffs = new HashSet<FacilityStaff>(0);
	private Set<StaffContact> staffContacts = new HashSet<StaffContact>(0);
	private Set<StaffAddress> staffAddresses = new HashSet<StaffAddress>(0);

	public Staff() {
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FIRST_NAME", length = 20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", length = 20)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "MIDDLE_NAME", length = 20)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DOB", length = 19)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "GENDER", length = 20)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "USERNAME", length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWD", length = 50)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED", length = 19)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "USER_CREATED", length = 20)
	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFIED", length = 19)
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Column(name = "USER_MODIFIED", length = 20)
	public String getUserModified() {
		return this.userModified;
	}

	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}

	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "STAFF_ID")
	public Set<FacilityStaff> getFacilityStaffs() {
		return this.facilityStaffs;
	}

	public void setFacilityStaffs(Set<FacilityStaff> facilityStaffs) {
		this.facilityStaffs = facilityStaffs;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "STAFF_ID")
	public Set<StaffContact> getStaffContacts() {
		return this.staffContacts;
	}

	public void setStaffContacts(Set<StaffContact> staffContacts) {
		this.staffContacts = staffContacts;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "STAFF_ID")
	public Set<StaffAddress> getStaffAddresses() {
		return this.staffAddresses;
	}

	public void setStaffAddresses(Set<StaffAddress> staffAddresses) {
		this.staffAddresses = staffAddresses;
	}

	@Column(name = "DEVICE_TOKEN")
	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	@Column(name = "DEVICE_TYPE")
	public String getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Column(name = "DEVICE_LOGGED_STATUS")
	public Boolean getDeviceLoggedStatus() {
		return deviceLoggedStatus;
	}

	public void setDeviceLoggedStatus(Boolean deviceLoggedStatus) {
		this.deviceLoggedStatus = deviceLoggedStatus;
	}

}
