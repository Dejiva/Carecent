package com.codecoop.interact.web.controller;

import java.util.HashMap;
import java.util.Map;

import com.codecoop.interact.core.dto.AdmissionDto;

public class AdmissionAttributesEditResponse {
	// START : Patient Information
	private String patientFirstName;
	private String patientLastName;
	private Long pcpAtSite;
	private String pcpWorkNumber;
	private String dob;
	private String patientGender;
	private Boolean canSpeakEnglish;
	private String otherLanguage;
	private String ethnicityType;
	private String otherEthnicityType;
	// END : Patient Information

	// START : Family/Caregiver/proxy contact
	private String caregiverName;
	private String careGiverTelephoneNumber;
	private String guardianName;
	private String guardianTelephoneNumber;
	// END :Family/Caregiver/proxy contact

	// START : Advance Directives/Goals of Care
	private Boolean fullCode;
	private Boolean dnr;
	private Boolean dni;
	private Boolean dnh;
	private Boolean noArtificialFeeding;
	private Boolean comfortCare;
	private Boolean hospiceCare;
	private Boolean otherDirectiveCheckBox;
	private String otherAdvanceDirectives;
	private String goalsOfCareDiscussed; // (NO OR YES)
	private String specifyGoalsOfCareDiscussed;
	private String canTakeDecision;
	private String reqProxy;
	// END : Advance Directives/Goals of Care

	// START : Transferring Hospital Information
	private String hospitalName;
	private Long hospitalId; // populates from Auto suggest of hospital name
	private String hospitalUnit;
	private String dischargingRN;
	private Long dischargingRNId; // populates from Auto suggest of RN
	private String dishcargingRNTelephoneNumber;
	private String dischargingMD;
	private Long dischargingMDId; // populates from Auto suggest of MD
	private String dishcargingMDTelephoneNumber;
	private String hospitalAdmissionDate;
	// END : Transferring Hospital Information

	// START : Post Acute Care Information
	private String transferredToPostAcuteCare;
	private Long facilityId; // populates from Auto suggest of Facility
	private Long patientId;
	private Long patientEpisodeId;
	private Long gCareGiverId;
	private Long gProxyId;
	private String postAcuteCareTelephoneNumber;
	private String nurseToNurseVerbalReport; // (NO OR YES)
	private String nurseToNurseVerbalReportText;
	// END : Post Acute Care; Information

	// START : Hospital Physician Care Team Information
	private String primaryCarePhysician;
	private Long primaryCarePhysicianId;
	private String primaryCarePhysicianTelephoneNumber;
	private String specialist1;
	private Long specialist1Id;
	private String specialist1Speciality;
	private String specialist1TelephoneNumber;
	private String specialist2;
	private Long specialist2Id;
	private String specialist2Speciality;
	private String specialist2TelephoneNumber;
	private String hospitalCareTeamId;
	// END : Hospital Physician Care Team Information

	// START : Procedure and Key Findings
	private String listProcedures;
	private String keyFindings;
	// End : Procedure and Key Findings

	// START : Critical Transitional Cares and Information : Pending Tests and
	// Follow-up
	private String summarizeHighPriorityNeeds;
	private String pendingLabAndTestResults;
	private String recommendedFollowupTests;

	public AdmissionAttributesEditResponse() {
		// TODO Auto-generated constructor stub
	}

	private Map<String, String> attributeValuesMap;

	public AdmissionAttributesEditResponse(AdmissionDto formDto) {
		this.patientFirstName = formDto.getPatientFirstName();
		this.patientLastName = formDto.getPatientLastName();
		this.pcpAtSite = formDto.getPcpAtSite();
		this.pcpWorkNumber = formDto.getPcpWorkNumber();
		this.dob = formDto.getDob();
		this.patientGender = formDto.getPatientGender();
		this.canSpeakEnglish = formDto.getCanSpeakEnglish();
		this.otherLanguage = formDto.getOtherLanguage();
		this.ethnicityType = formDto.getEthnicityType();
		this.otherEthnicityType = formDto.getOtherEthnicityType();
		this.caregiverName = formDto.getCaregiverName();
		this.careGiverTelephoneNumber = formDto.getCareGiverTelephoneNumber();
		this.guardianName = formDto.getGuardianName();
		this.guardianTelephoneNumber = formDto.getGuardianTelephoneNumber();
		this.fullCode = formDto.getFullCode();
		this.dnr = formDto.getDnr();
		this.dni = formDto.getDni();
		this.dnh = formDto.getDnh();
		this.noArtificialFeeding = formDto.getNoArtificialFeeding();
		this.comfortCare = formDto.getComfortCare();
		this.hospiceCare = formDto.getHospiceCare();
		this.otherDirectiveCheckBox = formDto.getOtherDirectiveCheckBox();
		this.otherAdvanceDirectives = formDto.getOtherAdvanceDirectives();
		this.goalsOfCareDiscussed = formDto.getGoalsOfCareDiscussed();
		this.specifyGoalsOfCareDiscussed = formDto
				.getSpecifyGoalsOfCareDiscussed();
		this.canTakeDecision = formDto.getCanTakeDecision();
		this.hospitalName = formDto.getHospitalName();
		this.hospitalId = formDto.getHospitalId();
		this.hospitalUnit = formDto.getHospitalUnit();
		this.dischargingRN = formDto.getDischargingRN();
		this.dischargingRNId = formDto.getDischargingRNId();
		this.dishcargingRNTelephoneNumber = formDto
				.getDishcargingRNTelephoneNumber();
		this.dischargingMD = formDto.getDischargingMD();
		this.dischargingMDId = formDto.getDischargingMDId();
		this.dishcargingMDTelephoneNumber = formDto
				.getDishcargingMDTelephoneNumber();
		this.hospitalAdmissionDate = formDto.getHospitalAdmissionDate();
		this.transferredToPostAcuteCare = formDto
				.getTransferredToPostAcuteCare();
		this.facilityId = formDto.getFacilityId();
		this.patientId = formDto.getPatientId();
		this.postAcuteCareTelephoneNumber = formDto
				.getPostAcuteCareTelephoneNumber();
		this.nurseToNurseVerbalReport = formDto.getNurseToNurseVerbalReport();
		this.nurseToNurseVerbalReportText = formDto
				.getNurseToNurseVerbalReportText();
		this.primaryCarePhysician = formDto.getPrimaryCarePhysician();
		this.primaryCarePhysicianId = formDto.getPrimaryCarePhysicianId();
		this.primaryCarePhysicianTelephoneNumber = formDto
				.getPrimaryCarePhysicianTelephoneNumber();
		this.specialist1 = formDto.getSpecialist1();
		this.specialist1Id = formDto.getSpecialist1Id();
		this.specialist1Speciality = formDto.getSpecialist1Speciality();
		this.specialist1TelephoneNumber = formDto
				.getSpecialist1TelephoneNumber();
		this.specialist2 = formDto.getSpecialist2();
		this.specialist2Id = formDto.getSpecialist2Id();
		this.specialist2Speciality = formDto.getSpecialist2Speciality();
		this.specialist2TelephoneNumber = formDto
				.getSpecialist2TelephoneNumber();
		this.listProcedures = formDto.getListProcedures();
		this.keyFindings = formDto.getKeyFindings();
		this.summarizeHighPriorityNeeds = formDto
				.getSummarizeHighPriorityNeeds();
		this.pendingLabAndTestResults = formDto.getPendingLabAndTestResults();
		this.recommendedFollowupTests = formDto.getRecommendedFollowupTests();
		this.patientEpisodeId = formDto.getPatientEpisodeId();
		this.gProxyId = formDto.getgProxyId();
		this.gCareGiverId = formDto.getgCareGiverId();
		this.hospitalCareTeamId = formDto.getHospitalCareTeamId();
		this.attributeValuesMap = formDto.getAttributeValuesMap();
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public Long getPcpAtSite() {
		return pcpAtSite;
	}

	public void setPcpAtSite(Long pcpAtSite) {
		this.pcpAtSite = pcpAtSite;
	}

	public String getPcpWorkNumber() {
		return pcpWorkNumber;
	}

	public void setPcpWorkNumber(String pcpWorkNumber) {
		this.pcpWorkNumber = pcpWorkNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public Boolean getCanSpeakEnglish() {
		return canSpeakEnglish;
	}

	public void setCanSpeakEnglish(Boolean canSpeakEnglish) {
		this.canSpeakEnglish = canSpeakEnglish;
	}

	public String getOtherLanguage() {
		return otherLanguage;
	}

	public void setOtherLanguage(String otherLanguage) {
		this.otherLanguage = otherLanguage;
	}

	public String getEthnicityType() {
		return ethnicityType;
	}

	public void setEthnicityType(String ethnicityType) {
		this.ethnicityType = ethnicityType;
	}

	public String getOtherEthnicityType() {
		return otherEthnicityType;
	}

	public void setOtherEthnicityType(String otherEthnicityType) {
		this.otherEthnicityType = otherEthnicityType;
	}

	public String getCaregiverName() {
		return caregiverName;
	}

	public void setCaregiverName(String caregiverName) {
		this.caregiverName = caregiverName;
	}

	public String getCareGiverTelephoneNumber() {
		return careGiverTelephoneNumber;
	}

	public void setCareGiverTelephoneNumber(String careGiverTelephoneNumber) {
		this.careGiverTelephoneNumber = careGiverTelephoneNumber;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianTelephoneNumber() {
		return guardianTelephoneNumber;
	}

	public void setGuardianTelephoneNumber(String guardianTelephoneNumber) {
		this.guardianTelephoneNumber = guardianTelephoneNumber;
	}

	public Boolean getFullCode() {
		return fullCode;
	}

	public void setFullCode(Boolean fullCode) {
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

	public Boolean getOtherDirectiveCheckBox() {
		return otherDirectiveCheckBox;
	}

	public void setOtherDirectiveCheckBox(Boolean otherDirectiveCheckBox) {
		this.otherDirectiveCheckBox = otherDirectiveCheckBox;
	}

	public String getOtherAdvanceDirectives() {
		return otherAdvanceDirectives;
	}

	public void setOtherAdvanceDirectives(String otherAdvanceDirectives) {
		this.otherAdvanceDirectives = otherAdvanceDirectives;
	}

	public String getGoalsOfCareDiscussed() {
		return goalsOfCareDiscussed;
	}

	public void setGoalsOfCareDiscussed(String goalsOfCareDiscussed) {
		this.goalsOfCareDiscussed = goalsOfCareDiscussed;
	}

	public String getSpecifyGoalsOfCareDiscussed() {
		return specifyGoalsOfCareDiscussed;
	}

	public void setSpecifyGoalsOfCareDiscussed(
			String specifyGoalsOfCareDiscussed) {
		this.specifyGoalsOfCareDiscussed = specifyGoalsOfCareDiscussed;
	}
	public String getCanTakeDecision() {
		return canTakeDecision;
	}

	public void setCanTakeDecision(String canTakeDecision) {
		this.canTakeDecision = canTakeDecision;
	}

	public String getReqProxy() {
		return reqProxy;
	}

	public void setReqProxy(String reqProxy) {
		this.reqProxy = reqProxy;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalUnit() {
		return hospitalUnit;
	}

	public void setHospitalUnit(String hospitalUnit) {
		this.hospitalUnit = hospitalUnit;
	}

	public String getDischargingRN() {
		return dischargingRN;
	}

	public void setDischargingRN(String dischargingRN) {
		this.dischargingRN = dischargingRN;
	}

	public Long getDischargingRNId() {
		return dischargingRNId;
	}

	public void setDischargingRNId(Long dischargingRNId) {
		this.dischargingRNId = dischargingRNId;
	}

	public String getDishcargingRNTelephoneNumber() {
		return dishcargingRNTelephoneNumber;
	}

	public void setDishcargingRNTelephoneNumber(
			String dishcargingRNTelephoneNumber) {
		this.dishcargingRNTelephoneNumber = dishcargingRNTelephoneNumber;
	}

	public String getDischargingMD() {
		return dischargingMD;
	}

	public void setDischargingMD(String dischargingMD) {
		this.dischargingMD = dischargingMD;
	}

	public Long getDischargingMDId() {
		return dischargingMDId;
	}

	public void setDischargingMDId(Long dischargingMDId) {
		this.dischargingMDId = dischargingMDId;
	}

	public String getDishcargingMDTelephoneNumber() {
		return dishcargingMDTelephoneNumber;
	}

	public void setDishcargingMDTelephoneNumber(
			String dishcargingMDTelephoneNumber) {
		this.dishcargingMDTelephoneNumber = dishcargingMDTelephoneNumber;
	}

	public String getHospitalAdmissionDate() {
		return hospitalAdmissionDate;
	}

	public void setHospitalAdmissionDate(String hospitalAdmissionDate) {
		this.hospitalAdmissionDate = hospitalAdmissionDate;
	}

	public String getTransferredToPostAcuteCare() {
		return transferredToPostAcuteCare;
	}

	public void setTransferredToPostAcuteCare(String transferredToPostAcuteCare) {
		this.transferredToPostAcuteCare = transferredToPostAcuteCare;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPostAcuteCareTelephoneNumber() {
		return postAcuteCareTelephoneNumber;
	}

	public void setPostAcuteCareTelephoneNumber(
			String postAcuteCareTelephoneNumber) {
		this.postAcuteCareTelephoneNumber = postAcuteCareTelephoneNumber;
	}

	public String getNurseToNurseVerbalReport() {
		return nurseToNurseVerbalReport;
	}

	public void setNurseToNurseVerbalReport(String nurseToNurseVerbalReport) {
		this.nurseToNurseVerbalReport = nurseToNurseVerbalReport;
	}

	public String getNurseToNurseVerbalReportText() {
		return nurseToNurseVerbalReportText;
	}

	public void setNurseToNurseVerbalReportText(
			String nurseToNurseVerbalReportText) {
		this.nurseToNurseVerbalReportText = nurseToNurseVerbalReportText;
	}

	public String getPrimaryCarePhysician() {
		return primaryCarePhysician;
	}

	public void setPrimaryCarePhysician(String primaryCarePhysician) {
		this.primaryCarePhysician = primaryCarePhysician;
	}

	public Long getPrimaryCarePhysicianId() {
		return primaryCarePhysicianId;
	}

	public void setPrimaryCarePhysicianId(Long primaryCarePhysicianId) {
		this.primaryCarePhysicianId = primaryCarePhysicianId;
	}

	public String getPrimaryCarePhysicianTelephoneNumber() {
		return primaryCarePhysicianTelephoneNumber;
	}

	public void setPrimaryCarePhysicianTelephoneNumber(
			String primaryCarePhysicianTelephoneNumber) {
		this.primaryCarePhysicianTelephoneNumber = primaryCarePhysicianTelephoneNumber;
	}

	public String getSpecialist1() {
		return specialist1;
	}

	public void setSpecialist1(String specialist1) {
		this.specialist1 = specialist1;
	}

	public Long getSpecialist1Id() {
		return specialist1Id;
	}

	public void setSpecialist1Id(Long specialist1Id) {
		this.specialist1Id = specialist1Id;
	}

	public String getSpecialist1Speciality() {
		return specialist1Speciality;
	}

	public void setSpecialist1Speciality(String specialist1Speciality) {
		this.specialist1Speciality = specialist1Speciality;
	}

	public String getSpecialist1TelephoneNumber() {
		return specialist1TelephoneNumber;
	}

	public void setSpecialist1TelephoneNumber(String specialist1TelephoneNumber) {
		this.specialist1TelephoneNumber = specialist1TelephoneNumber;
	}

	public String getSpecialist2() {
		return specialist2;
	}

	public void setSpecialist2(String specialist2) {
		this.specialist2 = specialist2;
	}

	public Long getSpecialist2Id() {
		return specialist2Id;
	}

	public void setSpecialist2Id(Long specialist2Id) {
		this.specialist2Id = specialist2Id;
	}

	public String getSpecialist2Speciality() {
		return specialist2Speciality;
	}

	public void setSpecialist2Speciality(String specialist2Speciality) {
		this.specialist2Speciality = specialist2Speciality;
	}

	public String getSpecialist2TelephoneNumber() {
		return specialist2TelephoneNumber;
	}

	public void setSpecialist2TelephoneNumber(String specialist2TelephoneNumber) {
		this.specialist2TelephoneNumber = specialist2TelephoneNumber;
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

	public String getSummarizeHighPriorityNeeds() {
		return summarizeHighPriorityNeeds;
	}

	public void setSummarizeHighPriorityNeeds(String summarizeHighPriorityNeeds) {
		this.summarizeHighPriorityNeeds = summarizeHighPriorityNeeds;
	}

	public String getPendingLabAndTestResults() {
		return pendingLabAndTestResults;
	}

	public void setPendingLabAndTestResults(String pendingLabAndTestResults) {
		this.pendingLabAndTestResults = pendingLabAndTestResults;
	}

	public String getRecommendedFollowupTests() {
		return recommendedFollowupTests;
	}

	public void setRecommendedFollowupTests(String recommendedFollowupTests) {
		this.recommendedFollowupTests = recommendedFollowupTests;
	}

	public Map<String, String> getAttributeValuesMap() {
		if (this.attributeValuesMap == null) {
			this.attributeValuesMap = new HashMap<String, String>();
		}
		return attributeValuesMap;
	}

	public void setAttributeValuesMap(Map<String, String> attributeValuesMap) {
		this.attributeValuesMap = attributeValuesMap;
	}

	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}

	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}

	public Long getgCareGiverId() {
		return gCareGiverId;
	}

	public void setgCareGiverId(Long gCareGiverId) {
		this.gCareGiverId = gCareGiverId;
	}

	public Long getgProxyId() {
		return gProxyId;
	}

	public void setgProxyId(Long gProxyId) {
		this.gProxyId = gProxyId;
	}

	public String getHospitalCareTeamId() {
		return hospitalCareTeamId;
	}

	public void setHospitalCareTeamId(String hospitalCareTeamId) {
		this.hospitalCareTeamId = hospitalCareTeamId;
	}

}
