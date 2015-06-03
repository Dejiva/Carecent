package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codecoop.interact.core.dto.SbarSymtomDto;

@Entity
@Table(name = "SBAR", catalog = "INTERACT")
public class Sbar implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2279368442754917225L;
	private Long id;
	private PatientEpisode patientEpisode;
	private Staff nursingStaff;
	private Staff doctor;
	private Boolean medAlertChangeLastWeek;
	private String allergies;
	private Boolean medAlertInWarfarin;
	private String resultOfLastInr;
	private Date latestInrDate;
	private Boolean providerVisitFlag;
	private Boolean transferToHospitalFlag;
	private Boolean otherNewOrdersFlag;
	private String otherNewOrders;
	private String notesOnChangeInCondition;
	private Boolean oximetryOnRoomair;
	private String oximetryOnO2; 
    private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	private Date repotedToDate;
	private String otherResidentCare;
	private Boolean manageInFacilityFlag;
	private String MsgScenariocode;
	private Boolean alertMailFlag;
	private String plannedDetails;
	//private List<SbarSymtomDto> SymtomList;

	

	public Sbar() {
	}
	
	@Column(name = "MED_ALERT_CHANGE_LAST_WEEK")
	public Boolean getMedAlertChangeLastWeek() {
		return medAlertChangeLastWeek;
	}

	public void setMedAlertChangeLastWeek(Boolean medAlertChangeLastWeek) {
		this.medAlertChangeLastWeek = medAlertChangeLastWeek;
	}
//	@Column(name = "SYMPTOMS_LIST")
//    public List<SbarSymtomDto> getSymtomList() {
//		return SymtomList;
//	}
//
//	public void setSymtomList(List<SbarSymtomDto> symtomList) {
//		SymtomList = symtomList;
//	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
    
	

	public void setId(Long id) {
		this.id = id;
	}
    
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PATIENT_EPISODE_ID")
	public PatientEpisode getPatientEpisode() {
		return patientEpisode;
	}

	public void setPatientEpisode(PatientEpisode patientEpisode) {
		this.patientEpisode = patientEpisode;
	}
    
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NURSING_STAFF_ID")
	public Staff getNursingStaff() {
		return nursingStaff;
	}


	public void setNursingStaff(Staff nursingStaff) {
		this.nursingStaff = nursingStaff;
	}
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DOCTOR_ID")
	public Staff getDoctor() {
		return doctor;
	}


	public void setDoctor(Staff doctor) {
		this.doctor = doctor;
	}
	@Column(name = "ALLERGIES", length = 250)
	public String getAllergies() {
		return allergies;
	}


	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	@Column(name = "MED_ALERT_ON_WARFARIN")
	public Boolean getMedAlertInWarfarin() {
		return medAlertInWarfarin;
	}


	public void setMedAlertInWarfarin(Boolean medAlertInWarfarin) {
		this.medAlertInWarfarin = medAlertInWarfarin;
	}

	@Column(name = "RESULT_OF_LAST_INR", length = 50)
	public String getResultOfLastInr() {
		return resultOfLastInr;
	}


	public void setResultOfLastInr(String resultOfLastInr) {
		this.resultOfLastInr = resultOfLastInr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LATEST_INR_DATE", length = 19)
		public Date getLatestInrDate() {
		return latestInrDate;
	}


	public void setLatestInrDate(Date latestInrDate) {
		this.latestInrDate = latestInrDate;
	}

	
	@Column(name = "PROVIDER_VISIT_FLAG")
	public Boolean getProviderVisitFlag() {
		return providerVisitFlag;
	}


	public void setProviderVisitFlag(Boolean providerVisitFlag) {
		this.providerVisitFlag = providerVisitFlag;
	}

	@Column(name = "TRANSFER_TO_HOSPITAL_FLAG")
	public Boolean getTransferToHospitalFlag() {
		return transferToHospitalFlag;
	}


	public void setTransferToHospitalFlag(Boolean transferToHospitalFlag) {
		this.transferToHospitalFlag = transferToHospitalFlag;
	}

	@Column(name = "OTHER_NEW_ORDERS_FLAG")
	public Boolean getOtherNewOrdersFlag() {
		return otherNewOrdersFlag;
	}


	public void setOtherNewOrdersFlag(Boolean otherNewOrdersFlag) {
		this.otherNewOrdersFlag = otherNewOrdersFlag;
	}

	@Column(name = "OTHER_NEW_ORDERS", length = 100)
	public String getOtherNewOrders() {
		return otherNewOrders;
	}


	public void setOtherNewOrders(String otherNewOrders) {
		this.otherNewOrders = otherNewOrders;
	}

	@Column(name = "NOTES_ON_CHANGE_IN_CONDITION", length = 100)
	public String getNotesOnChangeInCondition() {
		return notesOnChangeInCondition;
	}


	public void setNotesOnChangeInCondition(String notesOnChangeInCondition) {
		this.notesOnChangeInCondition = notesOnChangeInCondition;
	}
	
	@Column(name = "OXIMETRY_ON_ROOMAIR")
	public Boolean getOximetryOnRoomair() {
		return oximetryOnRoomair;
	}

	public void setOximetryOnRoomair(Boolean oximetryOnRoomair) {
		this.oximetryOnRoomair = oximetryOnRoomair;
	}
	
	@Column(name = "OXIMETRY_ON_O2")
	public String getOximetryOnO2() {
		return oximetryOnO2;
	}

	public void setOximetryOnO2(String oximetryOnO2) {
		this.oximetryOnO2 = oximetryOnO2;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPOTED_TO_DATE", length = 19)
	public Date getRepotedToDate() {
		return repotedToDate;
	}


	public void setRepotedToDate(Date repotedToDate) {
		this.repotedToDate = repotedToDate;
	}

	@Column(name = "RESIDENT_CARE", length = 200)
	public String getOtherResidentCare() {
		return otherResidentCare;
	}
	public void setOtherResidentCare(String otherResidentCare) {
		this.otherResidentCare = otherResidentCare;
	}
	@Column(name ="MANAGE_IN_FACILITY_FLAG")
	public Boolean getManageInFacilityFlag() {
		return manageInFacilityFlag;
	}

	public void setManageInFacilityFlag(Boolean manageInFacilityFlag) {
		this.manageInFacilityFlag = manageInFacilityFlag;
	}
	@Column(name ="MSG_SCENARIO_CODE")
	public String getMsgScenariocode() {
		return MsgScenariocode;
	}

	public void setMsgScenariocode(String msgScenariocode) {
		MsgScenariocode = msgScenariocode;
	}
	@Column(name ="ALERT_MAIL_FLAG")
	public Boolean isAlertMailFlag() {
		return alertMailFlag;
	}

	public void setAlertMailFlag(Boolean alertMailFlag) {
		this.alertMailFlag = alertMailFlag;
	}
	@Column(name="PLANNED_DETAILS")
	public String getPlannedDetails() {
		return plannedDetails;
	}

	public void setPlannedDetails(String plannedDetails) {
		this.plannedDetails = plannedDetails;
	}
	
	
}
