package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PatientEpisodeDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8615563404407758541L;
	private Long patientEpisodeId;
	
	private Date fromHospitalAdmissionDate;
	private Date fromHospitalDischargeDate;
	private String fromHospitalUnit;
	private Date facilityAdmissionDate;
	private Date facilityDischargeDate;
	private String bedNo;
	private String listProcedures;
	private String keyFindings;
	private String hospitalPriorityCareNeeds;
	private String pendingLabResult;
	private String followUpTests;
	private String fullCode;
	private Boolean dnr;
	private Boolean dni;
	private Boolean dnh;
	private Boolean noArtificialFeeding;
	private Boolean comfortCare;
	private Boolean hospiceCare;
	private String otherCare;
	private String goalsCareDiscussionText;
	private String hospitalNurseVerbalReport;
	private String toHospitalUnit;
	private String facilityStayType;
	private Date toHospitalAdmissionDate;
	private Date toHospitalDischargeDate;
	private String facilityUnit;
	private String canTakeDecision;
	private String goalsOfCareDiscussed;
	private String reqProxy;
	private String nurseToNurseVerbalReport;
	
	private Long facilityId;
	private String userId;
	
	private Long admittedByStaffId;
	private String admittedByStaffName;
	private Long facilityDischargeRNId;
	private Long facilityDischargeMDId;
	
	private HospitalDto fromHospital;
	private HospitalDto toHospital;
	
	private HospitalStaffDto hospitalDischargeNurse;
	private HospitalStaffDto hospitalDischargeDoctor;
	
	private Long pcpAtSite;
	
//	private HospitalStaffDto primaryCareTeam;
//	private HospitalStaffDto specialist1;
//	private HospitalStaffDto specialist2;
	
	private List<HospitalStaffDto> physicianCareTeamListDto;
	private PatientDto patient;
	private Boolean stopAndWatchEligiblity;
	private String roomNumber;
	private String patientCareTypes;
	private String patientInsurencePlan;
	private Long patientEncounterId;
	
	public Long getPatientEncounterId() {
		return patientEncounterId;
	}
	public void setPatientEncounterId(Long patientEncounterId) {
		this.patientEncounterId = patientEncounterId;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public String getPatientCareTypes() {
		return patientCareTypes;
	}
	public void setPatientCareTypes(String patientCareTypes) {
		this.patientCareTypes = patientCareTypes;
	}
	public String getPatientInsurencePlan() {
		return patientInsurencePlan;
	}
	public void setPatientInsurencePlan(String patientInsurencePlan) {
		this.patientInsurencePlan = patientInsurencePlan;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}

	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}

	public Date getFromHospitalAdmissionDate() {
		return fromHospitalAdmissionDate;
	}

	public void setFromHospitalAdmissionDate(Date fromHospitalAdmissionDate) {
		this.fromHospitalAdmissionDate = fromHospitalAdmissionDate;
	}

	public Date getFromHospitalDischargeDate() {
		return fromHospitalDischargeDate;
	}

	public void setFromHospitalDischargeDate(Date fromHospitalDischargeDate) {
		this.fromHospitalDischargeDate = fromHospitalDischargeDate;
	}

	public String getFromHospitalUnit() {
		return fromHospitalUnit;
	}

	public void setFromHospitalUnit(String fromHospitalUnit) {
		this.fromHospitalUnit = fromHospitalUnit;
	}

	public Date getFacilityAdmissionDate() {
		return facilityAdmissionDate;
	}

	public void setFacilityAdmissionDate(Date facilityAdmissionDate) {
		this.facilityAdmissionDate = facilityAdmissionDate;
	}

	public Date getFacilityDischargeDate() {
		return facilityDischargeDate;
	}

	public void setFacilityDischargeDate(Date facilityDischargeDate) {
		this.facilityDischargeDate = facilityDischargeDate;
	}

	public String getBedNo() {
		return bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getListProcedures() {
		return listProcedures;
	}

	public void setListProcedures(String listProcedures) {
		this.listProcedures = listProcedures;
	}

	public String getKeyFindings() {
		return keyFindings;
	}

	public void setKeyFindings(String keyFindings) {
		this.keyFindings = keyFindings;
	}

	public String getHospitalPriorityCareNeeds() {
		return hospitalPriorityCareNeeds;
	}

	public void setHospitalPriorityCareNeeds(String hospitalPriorityCareNeeds) {
		this.hospitalPriorityCareNeeds = hospitalPriorityCareNeeds;
	}

	public String getPendingLabResult() {
		return pendingLabResult;
	}

	public void setPendingLabResult(String pendingLabResult) {
		this.pendingLabResult = pendingLabResult;
	}

	public String getFollowUpTests() {
		return followUpTests;
	}

	public void setFollowUpTests(String followUpTests) {
		this.followUpTests = followUpTests;
	}

	public String getFullCode() {
		return fullCode;
	}

	public void setFullCode(String fullCode) {
		this.fullCode = fullCode;
	}

	public Boolean getDnr() {
		return dnr;
	}

	public void setDnr(Boolean dnr) {
		this.dnr = dnr;
	}

	public Boolean getDni() {
		return dni;
	}

	public void setDni(Boolean dni) {
		this.dni = dni;
	}

	public Boolean getDnh() {
		return dnh;
	}

	public void setDnh(Boolean dnh) {
		this.dnh = dnh;
	}

	public Boolean getNoArtificialFeeding() {
		return noArtificialFeeding;
	}

	public void setNoArtificialFeeding(Boolean noArtificialFeeding) {
		this.noArtificialFeeding = noArtificialFeeding;
	}

	public Boolean getComfortCare() {
		return comfortCare;
	}

	public void setComfortCare(Boolean comfortCare) {
		this.comfortCare = comfortCare;
	}
	
	public Boolean getHospiceCare() {
		return hospiceCare;
	}

	public void setHospiceCare(Boolean hospiceCare) {
		this.hospiceCare = hospiceCare;
	}

	public String getOtherCare() {
		return otherCare;
	}

	public void setOtherCare(String otherCare) {
		this.otherCare = otherCare;
	}

	public String getGoalsCareDiscussionText() {
		return goalsCareDiscussionText;
	}

	public void setGoalsCareDiscussionText(String goalsCareDiscussionText) {
		this.goalsCareDiscussionText = goalsCareDiscussionText;
	}

	public String getHospitalNurseVerbalReport() {
		return hospitalNurseVerbalReport;
	}

	public void setHospitalNurseVerbalReport(String hospitalNurseVerbalReport) {
		this.hospitalNurseVerbalReport = hospitalNurseVerbalReport;
	}

	public String getToHospitalUnit() {
		return toHospitalUnit;
	}

	public void setToHospitalUnit(String toHospitalUnit) {
		this.toHospitalUnit = toHospitalUnit;
	}

	public String getFacilityStayType() {
		return facilityStayType;
	}

	public void setFacilityStayType(String facilityStayType) {
		this.facilityStayType = facilityStayType;
	}

	public Date getToHospitalAdmissionDate() {
		return toHospitalAdmissionDate;
	}

	public void setToHospitalAdmissionDate(Date toHospitalAdmissionDate) {
		this.toHospitalAdmissionDate = toHospitalAdmissionDate;
	}

	public Date getToHospitalDischargeDate() {
		return toHospitalDischargeDate;
	}

	public void setToHospitalDischargeDate(Date toHospitalDischargeDate) {
		this.toHospitalDischargeDate = toHospitalDischargeDate;
	}

	public String getFacilityUnit() {
		return facilityUnit;
	}

	public void setFacilityUnit(String facilityUnit) {
		this.facilityUnit = facilityUnit;
	}
	public String getCanTakeDecision() {
		return canTakeDecision;
	}

	public void setCanTakeDecision(String canTakeDecision) {
		this.canTakeDecision = canTakeDecision;
	}

	public String getGoalsOfCareDiscussed() {
		return goalsOfCareDiscussed;
	}

	public void setGoalsOfCareDiscussed(String goalsOfCareDiscussed) {
		this.goalsOfCareDiscussed = goalsOfCareDiscussed;
	}

	public String getReqProxy() {
		return reqProxy;
	}

	public void setReqProxy(String reqProxy) {
		this.reqProxy = reqProxy;
	}

	public String getNurseToNurseVerbalReport() {
		return nurseToNurseVerbalReport;
	}

	public void setNurseToNurseVerbalReport(String nurseToNurseVerbalReport) {
		this.nurseToNurseVerbalReport = nurseToNurseVerbalReport;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getAdmittedByStaffId() {
		return admittedByStaffId;
	}

	public void setAdmittedByStaffId(Long admittedByStaffId) {
		this.admittedByStaffId = admittedByStaffId;
	}

	public String getAdmittedByStaffName() {
		return admittedByStaffName;
	}

	public void setAdmittedByStaffName(String admittedByStaffName) {
		this.admittedByStaffName = admittedByStaffName;
	}

	public Long getFacilityDischargeRNId() {
		return facilityDischargeRNId;
	}

	public void setFacilityDischargeRNId(Long facilityDischargeRNId) {
		this.facilityDischargeRNId = facilityDischargeRNId;
	}

	public Long getFacilityDischargeMDId() {
		return facilityDischargeMDId;
	}

	public void setFacilityDischargeMDId(Long facilityDischargeMDId) {
		this.facilityDischargeMDId = facilityDischargeMDId;
	}

	public HospitalDto getFromHospital() {
		return fromHospital;
	}

	public void setFromHospital(HospitalDto fromHospital) {
		this.fromHospital = fromHospital;
	}

	public HospitalDto getToHospital() {
		return toHospital;
	}

	public void setToHospital(HospitalDto toHospital) {
		this.toHospital = toHospital;
	}

	public HospitalStaffDto getHospitalDischargeNurse() {
		return hospitalDischargeNurse;
	}

	public void setHospitalDischargeNurse(HospitalStaffDto hospitalDischargeNurse) {
		this.hospitalDischargeNurse = hospitalDischargeNurse;
	}

	public HospitalStaffDto getHospitalDischargeDoctor() {
		return hospitalDischargeDoctor;
	}

	public void setHospitalDischargeDoctor(HospitalStaffDto hospitalDischargeDoctor) {
		this.hospitalDischargeDoctor = hospitalDischargeDoctor;
	}
	
	public Long getPcpAtSite() {
		return pcpAtSite;
	}

	public void setPcpAtSite(Long pcpAtSite) {
		this.pcpAtSite = pcpAtSite;
	}
	
	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

//	public HospitalStaffDto getPrimaryCareTeam() {
//		return primaryCareTeam;
//	}
//
//	public void setPrimaryCareTeam(HospitalStaffDto primaryCareTeam) {
//		this.primaryCareTeam = primaryCareTeam;
//	}
//
//	public HospitalStaffDto getSpecialist1() {
//		return specialist1;
//	}
//
//	public void setSpecialist1(HospitalStaffDto specialist1) {
//		this.specialist1 = specialist1;
//	}
//
//	public HospitalStaffDto getSpecialist2() {
//		return specialist2;
//	}
//
//	public void setSpecialist2(HospitalStaffDto specialist2) {
//		this.specialist2 = specialist2;
//	}

	public List<HospitalStaffDto> getPhysicianCareTeamListDto() {
		return physicianCareTeamListDto;
	}

	public Boolean getStopAndWatchEligiblity() {
		return stopAndWatchEligiblity;
	}

	public void setStopAndWatchEligiblity(Boolean stopAndWatchEligiblity) {
		this.stopAndWatchEligiblity = stopAndWatchEligiblity;
	}

	public void setPhysicianCareTeamListDto(
			List<HospitalStaffDto> physicianCareTeamListDto) {
		this.physicianCareTeamListDto = physicianCareTeamListDto;
	}

	
	// PATIENT_ID	bigint(25) -- patientDto
	// GUARDIAN_ID	bigint(25) -- guardianDto
	// FACILITY_ID	bigint(25) -- facilityDto
	
	
}
