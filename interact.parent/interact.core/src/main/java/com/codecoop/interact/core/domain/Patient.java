package com.codecoop.interact.core.domain;

// Generated Jul 26, 2014 9:46:18 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Patient generated by hbm2java
 */
@Entity
@Table(name = "PATIENT", catalog = "INTERACT")
public class Patient implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5694539995204035730L;
	private Long id;
	private String patientFirstName;
	private String patientLastName;
	private Long age;
	private Date dob;
	private String sSNumber;
	private String gender;
	private String bloodgroup;
	private Long ethnicityId;
	private String ethnicityUd;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	private Set<PatientAddress> patientAddresses=new HashSet<PatientAddress>();
	private Set<Guardian> gurdianList;
	private Set<PatientContact> patientContacts=new HashSet<PatientContact>();
	private Set<PLanguage> pLanguages=new HashSet<PLanguage>();
	private Set<PatientEpisode> patientEpisodeList=new HashSet<PatientEpisode>();
	
	public Patient() {
	}
	public Patient(String patientFirstName,String patientLastName, Long age, Date dob, String gender,
			String bloodgroup, Long ethnicityId, String ethnicityUd,Date dateCreated,
			String userCreated, Date dateModified, String userModified) {
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.age = age;
		this.dob = dob;
		this.gender = gender;
		this.bloodgroup = bloodgroup;
		this.ethnicityId = ethnicityId;
		this.ethnicityUd = ethnicityUd;
		this.dateCreated = dateCreated;
		this.userCreated = userCreated;
		this.dateModified = dateModified;
		this.userModified = userModified;
	}
	
	public Patient(String patientFirstName,String patientLastName, Date dob, String gender,
			String bloodgroup, Date dateCreated,
			String userCreated, String userModified) {
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.dob = dob;
		this.gender = gender;
		this.bloodgroup = bloodgroup;
		this.dateCreated = dateCreated;
		this.userCreated = userCreated;
		this.dateModified = new Date();
		this.userModified = userModified;
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
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	
	@Column(name = "LAST_NAME", length = 20)
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	
	@Column(name = "AGE")
	public Long getAge() {
		return this.age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DOB", length = 19)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "SSN", length = 20)
	public String getsSNumber() {
		return sSNumber;
	}
	public void setsSNumber(String sSNumber) {
		this.sSNumber = sSNumber;
	}
	@Column(name = "GENDER", length = 50)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "BLOODGROUP", length = 50)
	public String getBloodgroup() {
		return this.bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	@Column(name = "ETHNICITY_ID")
	public Long getEthnicityId() {
		return this.ethnicityId;
	}

	public void setEthnicityId(Long ethnicityId) {
		this.ethnicityId = ethnicityId;
	}
	@Column(name="ETHNICITY_UD")
	public String getEthnicityUd() {
		return ethnicityUd;
	}
	public void setEthnicityUd(String ethnicityUd) {
		this.ethnicityUd = ethnicityUd;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED", length = 19)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "USER_CREATED", length = 50)
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

	@Column(name = "USER_MODIFIED", length = 50)
	public String getUserModified() {
		return this.userModified;
	}

	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}

	
	public void setPatientAddresses(Set<PatientAddress> patientAddresses) {
		this.patientAddresses = patientAddresses;
	}
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "patient")
	//@JoinColumn(name = "PATIENT_ID")
	@Cascade(CascadeType.ALL)
	public Set<PatientAddress> getPatientAddresses() {
		return patientAddresses;
	}

	public void setPatientContacts(Set<PatientContact> patientContacts) {
		this.patientContacts = patientContacts;
	}

	@OneToMany(fetch = FetchType.EAGER ,mappedBy = "patient")
	@Cascade(CascadeType.ALL)
	public Set<PatientContact> getPatientContacts() {
		return patientContacts;
	}

	public void setpLanguages(Set<PLanguage> pLanguages) {
		this.pLanguages = pLanguages;
	}
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="patient")
	@Cascade(CascadeType.ALL)
	public Set<PLanguage> getpLanguages() {
		return pLanguages;
	}
   
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	@Cascade(CascadeType.ALL)
	public Set<PatientEpisode> getPatientEpisodeList() {
		return patientEpisodeList;
	}

	public void setPatientEpisodeList(Set<PatientEpisode> patientEpisodeList) {
		this.patientEpisodeList = patientEpisodeList;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="PATIENT_ID")
	public Set<Guardian> getGurdianList() {
		return gurdianList;
	}
	public void setGurdianList(Set<Guardian> gurdianList) {
		this.gurdianList = gurdianList;
	}

	
}
