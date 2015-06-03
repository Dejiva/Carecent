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
@Table(name = "FACILITY", catalog = "INTERACT")
public class Facility implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5840004474450697497L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "FACILITY_NAME", length = 150)
	private String facilityName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED", length = 19)
	private Date dateCreated;
	
	@Column(name = "USER_CREATED", length = 50)
	private String userCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFIED", length = 19)
	private Date dateModified;
	
	@Column(name = "USER_MODIFIEd", length = 50)
	private String userModified;
	
	@Column(name = "STANDING_ORDERS")
	private boolean standingOrders;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="FACILITY_ID")
	private Set<FacilityAddress> facilityAddresses = new HashSet<FacilityAddress>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="FACILITY_ID")
	private Set<FacilityStaff> facilityStaffs = new HashSet<FacilityStaff>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="FACILITY_ID")
	private Set<FacilityContact> facilityContacts = new HashSet<FacilityContact>(0);
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facility")
//	private Set<PatientEpisode> patientEpisodes = new HashSet<PatientEpisode>(0);

	public Facility() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getFacilityName() {
		return this.facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	
	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	
	public String getUserModified() {
		return this.userModified;
	}

	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}
	
	public boolean isStandingOrders() {
		return standingOrders;
	}

	public void setStandingOrders(boolean standingOrders) {
		this.standingOrders = standingOrders;
	}

	public Set<FacilityAddress> getFacilityAddresses() {
		if(facilityAddresses == null){
			return facilityAddresses =new HashSet<FacilityAddress>();
		}
		return this.facilityAddresses;
	}

	public void setFacilityAddresses(Set<FacilityAddress> facilityAddresses) {
		this.facilityAddresses = facilityAddresses;
	}

	
	public Set<FacilityStaff> getFacilityStaffs() {
		return this.facilityStaffs;
	}

	public void setFacilityStaffs(Set<FacilityStaff> facilityStaffs) {
		this.facilityStaffs = facilityStaffs;
	}

	
	public Set<FacilityContact> getFacilityContacts() {
		if(facilityContacts == null){
			return facilityContacts =new HashSet<FacilityContact>();
		}
		return this.facilityContacts;
	}

	public void setFacilityContacts(Set<FacilityContact> facilityContacts) {
		this.facilityContacts = facilityContacts;
	}

	
//	public Set<PatientEpisode> getPatientEpisodes() {
//		return this.patientEpisodes;
//	}
//
//	public void setPatientEpisodes(Set<PatientEpisode> patientEpisodes) {
//		this.patientEpisodes = patientEpisodes;
//	}

}
