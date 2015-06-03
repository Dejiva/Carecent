package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FACILITY_STAFF", catalog = "INTERACT")
public class FacilityStaff implements java.io.Serializable {

	private Long id;
	private StaffRoles staffRoles;
	private Staff staff;
	private Facility facility;
	private String empId;
	private Date dateOfJoining;
	private Date relievingDate;
	private String workEmail;
	private String workNumber;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
//	private Set messageBoxesForToId = new HashSet(0);
//	private Set messageBoxesForFromId = new HashSet(0);
//	private Set stopAndWatchHistoriesForReportedToFacilityStaffId = new HashSet(0);
//	private Set patientEpisodesForFacilityDischargeMdId = new HashSet(0);
//	private Set stopAndWatchHistoriesForFacilityStaffId = new HashSet(0);
//	private Set patientEpisodesForFacilityDischargeRnId = new HashSet(0);
//	private Set patientEpisodesForAdmittedByStaffId = new HashSet(0);

	public FacilityStaff() {
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	public StaffRoles getStaffRoles() {
		return this.staffRoles;
	}

	public void setStaffRoles(StaffRoles staffRoles) {
		this.staffRoles = staffRoles;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STAFF_ID")
	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FACILITY_ID")
	public Facility getFacility() {
		return this.facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@Column(name = "EMP_ID", length = 20)
	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_OF_JOINING", length = 19)
	public Date getDateOfJoining() {
		return this.dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RELIEVING_DATE", length = 19)
	public Date getRelievingDate() {
		return this.relievingDate;
	}

	public void setRelievingDate(Date relievingDate) {
		this.relievingDate = relievingDate;
	}

	@Column(name = "WORK_EMAIL", length = 60)
	public String getWorkEmail() {
		return this.workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	@Column(name = "WORK_NUMBER", length = 30)
	public String getWorkNumber() {
		return this.workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
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

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facilityStaffByToId")
//	public Set getMessageBoxesForToId() {
//		return this.messageBoxesForToId;
//	}
//
//	public void setMessageBoxesForToId(Set messageBoxesForToId) {
//		this.messageBoxesForToId = messageBoxesForToId;
//	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facilityStaffByFromId")
//	public Set getMessageBoxesForFromId() {
//		return this.messageBoxesForFromId;
//	}
//
//	public void setMessageBoxesForFromId(Set messageBoxesForFromId) {
//		this.messageBoxesForFromId = messageBoxesForFromId;
//	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facilityStaffByReportedToFacilityStaffId")
//	public Set getStopAndWatchHistoriesForReportedToFacilityStaffId() {
//		return this.stopAndWatchHistoriesForReportedToFacilityStaffId;
//	}
//
//	public void setStopAndWatchHistoriesForReportedToFacilityStaffId(
//			Set stopAndWatchHistoriesForReportedToFacilityStaffId) {
//		this.stopAndWatchHistoriesForReportedToFacilityStaffId = stopAndWatchHistoriesForReportedToFacilityStaffId;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facilityStaffByFacilityDischargeMdId")
//	public Set getPatientEpisodesForFacilityDischargeMdId() {
//		return this.patientEpisodesForFacilityDischargeMdId;
//	}
//
//	public void setPatientEpisodesForFacilityDischargeMdId(
//			Set patientEpisodesForFacilityDischargeMdId) {
//		this.patientEpisodesForFacilityDischargeMdId = patientEpisodesForFacilityDischargeMdId;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facilityStaffByFacilityStaffId")
//	public Set getStopAndWatchHistoriesForFacilityStaffId() {
//		return this.stopAndWatchHistoriesForFacilityStaffId;
//	}
//
//	public void setStopAndWatchHistoriesForFacilityStaffId(
//			Set stopAndWatchHistoriesForFacilityStaffId) {
//		this.stopAndWatchHistoriesForFacilityStaffId = stopAndWatchHistoriesForFacilityStaffId;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facilityStaffByFacilityDischargeRnId")
//	public Set getPatientEpisodesForFacilityDischargeRnId() {
//		return this.patientEpisodesForFacilityDischargeRnId;
//	}
//
//	public void setPatientEpisodesForFacilityDischargeRnId(
//			Set patientEpisodesForFacilityDischargeRnId) {
//		this.patientEpisodesForFacilityDischargeRnId = patientEpisodesForFacilityDischargeRnId;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facilityStaffByAdmittedByStaffId")
//	public Set getPatientEpisodesForAdmittedByStaffId() {
//		return this.patientEpisodesForAdmittedByStaffId;
//	}
//
//	public void setPatientEpisodesForAdmittedByStaffId(
//			Set patientEpisodesForAdmittedByStaffId) {
//		this.patientEpisodesForAdmittedByStaffId = patientEpisodesForAdmittedByStaffId;
//	}

}
