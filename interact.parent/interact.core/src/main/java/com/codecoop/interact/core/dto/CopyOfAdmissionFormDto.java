package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CopyOfAdmissionFormDto implements Serializable {

	// START : Patient Information
	private String patientName;
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
	private Boolean canTakeDecision;
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
	// End : Critical Transitional Cares and Information : Pending Tests and
	// Follow-up

	// START : Admission Attributes Table
	private String KCIVS_TIME_TAKEN; // INTEGER
	private String KCIVS_PAIN_RATING; // INTEGER
	private Boolean KCIVS_NOT_APPLICABLE;// Boolean
	private String KCIVS_PAIN_SITE;// VARCHAR
	private Integer KCIVS_TEMP;// INTEGER
	private String KCIVS_BP;// INTEGER
	private Integer KCIVS_HR;// INTEGER
	private Integer KCIVS_RR;// INTEGER
	private String KCIVS_02SAT;// VARCHAR
	private Integer KCIVS_WGHT;// INTEGER
	private Boolean KCIMS_ALRT;// BOOLEAN
	private Boolean KCIMS_DFC;// BOOLEAN
	private Boolean KCIMS_DCFC;// BOOLEAN
	private Boolean KCIMS_N_ALRT;// BOOLEAN
	private String KCID_PDD;// VARCHAR
	private String KCID_OMD;// VARCHAR
	private String KCID_MHD;// VARCHAR
	private Boolean HRC_OR_TI_FALRSK;// BOOLEAN
	private String HRC_OR_TI_PREC;// VARCHAR
	private Boolean HRC_OR_TI_HF;// BOOLEAN
	private Boolean HRC_OR_TI_HF_NEWDIAG;// BOOLEAN
	private Boolean HRC_OR_TI_HF_EXTHADMSSION;// BOOLEAN
	private String HRC_OR_TI_HF_EXTHADMSSION_LSTDTE;// VARCHAR
	private Boolean HRC_OR_TI_HF_EF;// BOOLEAN
	private Integer HRC_OR_TI_HF_EFP;// INTEGER
	private String HRC_OR_TI_HF_EF_DW;// VARCHAR
	private Boolean HRC_OR_TI_AC;// BOOLEAN
	private String HRC_OR_TI_AC_REASON;// VARCHAR
	private Boolean HRC_OR_TI_AC_R_AFIB;// BOOLEAN
	private Boolean HRC_OR_TI_AC_R_DVT_OR_PE;// BOOLEAN
	private Boolean HRC_OR_TI_AC_R_MECVAL;// BOOLEAN
	private Boolean HRC_OR_TI_AC_R_POP;// BOOLEAN
	private Boolean HRC_OR_TI_AC_R_LOWEF;// BOOLEAN
	private Boolean HRC_OR_TI_AC_R_OTHR;// BOOLEAN
	private String HRC_OR_TI_AC_R_OTHR_TEXT;// VARCHAR ? no matching found
	private String HRC_OR_TI_AC_R_DURTN;// VARCHAR
	private Boolean HRC_OR_TI_GI_1_5_2_5;// BOOLEAN // commented enable by
											// RAMESH and rename "." as "_"
	private Boolean HRC_OR_TI_GI_2_3;// BOOLEAN
	private Boolean HRC_OR_TI_GI_OTHR;// BOOLEAN
	private BigDecimal HRC_OR_TI_GI_OTHR_TEXT;// FLOAT(5,2) ? no matching found
	private Boolean HRC_OR_TI_OP;// VARCHAR // needed check box here as for form
									// available String changed to boolean
	private String HRC_OR_TI_OP_INDCTN;// VARCHAR
	private Boolean HRC_OR_TI_OP__I_IHP_AND_CBD;// BOOLEAN
	private String HRC_OR_TI_OP_SD;// VARCHAR
	private String HRC_OR_TI_OP_SD_TEXT;// VARCHAR ? no matching found
	private Boolean HRC_OR_TI_OAB;// BOOLEAN
	private String HRC_OR_TI_OAB_INDCTN;// VARCHAR
	private String HRC_OR_TI_OAB_TTCD;// VARCHAR
	private String HRC_OR_TI_OAB_DS;// VARCHAR
	private Boolean HRC_OR_TI_D;// BOOLEAN
	private String HRC_OR_TI_D_MRGD;// VARCHAR
	private String HRC_OR_TI_D_MRGT;// VARCHAR
	private Boolean M_AND_A_MLA;// BOOLEAN
	private Boolean M_AND_A_ALLRGS_NK;// BOOLEAN
	private Boolean M_AND_A_ALLRGS_Y;// BOOLEAN
	private String M_AND_A_ALLRGS_Y_SPCFY;// VARCHAR
	private Boolean M_AND_A_PM_N;// BOOLEAN
	private Boolean M_AND_A_PM_Y;// BOOLEAN
	private String M_AND_A_PM_SPCFY;// VARCHAR
	private String M_AND_A_PM_D;// VARCHAR
	private String M_AND_A_PM_LD;// VARCHAR
	private Boolean NCPSF_AMBLTN_INDPNDNT;// BOOLEAN
	private Boolean NCPSF_AMBLTN_WA;// BOOLEAN
	private Boolean NCPSF_AMBLTN_WAD;// BOOLEAN
	private Boolean NCPSF_AMBLTN_NTA;// BOOLEAN
	private Boolean NCPSF_WB_FULL;// BOOLEAN
	private Boolean NCPSF_WB_PARTL_L_OR_R;// BOOLEAN
	private Boolean NCPSF_WB_NONE;// BOOLEAN
	private Boolean NCPSF_TRNSFR_SLF;// BOOLEAN
	private Boolean NCPSF_TRNSFR_OPA;// BOOLEAN
	private Boolean NCPSF_TRNSFR_TPA;// BOOLEAN
	private Boolean NCPSF_SF_S_N;// BOOLEAN
	private Boolean NCPSF_SF_S_IMPRD;// BOOLEAN
	private Boolean NCPSF_SF_S_BLND;// BOOLEAN
	private Boolean NCPSF_SF_H_N;// BOOLEAN
	private Boolean NCPSF_SF_H_IMPRD;// BOOLEAN
	private Boolean NCPSF_SF_H_DF;// BOOLEAN
	private Boolean NCPSF_DEV_WC;// BOOLEAN
	private Boolean NCPSF_DEV_WLKR;// BOOLEAN
	private Boolean NCPSF_DEV_C;// BOOLEAN
	private Boolean NCPSF_DEV_CRTCHS;// BOOLEAN
	private Boolean NCPSF_DEV_PROS;// BOOLEAN
	private Boolean NCPSF_DEV_GLSES;// BOOLEAN
	private Boolean NCPSF_DEV_CNTCTS;// BOOLEAN
	private Boolean NCPSF_DEV_D;// BOOLEAN
	private Boolean NCPSF_DEV_HAL_OR_R;// BOOLEAN
	private Boolean NCPSF_C_CONTNENT;// BOOLEAN
	private Boolean NCPSF_C_INCONTNENT;// BOOLEAN
	private Boolean NCPSF_C_CATHTR;// BOOLEAN
	private String NCPSF_C_CATHTR_DI;// VARCHAR
	private Boolean NCPSF_C_RFC_R;// VARCHAR needed Boolean as per pdf doc,
									// String modified as Boolean
	private Boolean NCPSF_C_RFC_SP;// VARCHAR needed boolean
	private Boolean NCPSF_C_RFC_OTHR;// VARCHAR needed boolean
	private String NCPSF_C_RFC_OTHR_SPFY;// VARCHAR
	private Boolean NCPSF_C_BOWLINCONTNENT;// BOOLEAN
	private Boolean NCPSF_C_OSTMY;// BOOLEAN
	private Boolean NCPSF_C_DOLBM; // BOOLEAN Created by RAMESH
	private String NCPSF_C_DOLB;// VARCHAR
	private String NCPSF_N_AND_H_D;// BOOLEAN needed String as per pdf
									// doc,Boolean modified as String by RAMESH
	private String NCPSF_N_AND_H_C;// BOOLEAN
	private String NCPSF_N_AND_H_FWR;// BOOLEAN
	private Boolean NCPSF_N_AND_H_EI_SLF;// BOOLEAN
	private Boolean NCPSF_N_AND_H_EI_WA;// BOOLEAN
	private Boolean NCPSF_N_AND_H_EI_DS;// BOOLEAN
	private Boolean NCPSF_N_AND_H_EI_DS_ASTRIA;// BOOLEAN
	private Boolean NCPSF_N_AND_H_TF_GT;// BOOLEAN
	private Boolean NCPSF_N_AND_H_TF_JT;// BOOLEAN
	private Boolean NCPSF_N_AND_H_TF_DI;// VARCHAR need Boolean as per pdf doc,
										// String modified as Boolean by RAMESH
	private String NCPSF_N_AND_H_TF_DIT;// VARCHAR
	private String NCPSF_N_AND_H_TF_DI_FWB_CC;// VARCHAR
	private String NCPSF_N_AND_H_TF_DI_FWB_HRS;// VARCHAR
	private Boolean NCPSF_N_AND_H_TF_TFP;// BOOLEAN
	private String NCPSF_N_AND_H_TF_TFP_T;// BOOLEAN need String as per pdf doc,
											// Boolean modified as String by
											// RAMESH
	private String NCPSF_N_AND_H_TF_TFP_RTE_CC_PER_H;// VARCHAR need String as
														// per pdf doc, Boolean
														// modified as String by
														// RAMESH
	private String NCPSF_N_AND_H_TF_TFP_EVERY_H_OR_DAY;// BOOLEAN need String as
														// per pdf doc, Boolean
														// modified as String by
														// RAMESH
	private Boolean NCPSF_TTD_PICC;// BOOLEAN
	private Boolean NCPSF_TTD_PRTCTH;// BOOLEAN
	private String NCPSF_TTD_PRTCTH_DI;// VARCHAR
	private Boolean NCPSF_TTD_C_PM;// BOOLEAN
	private Boolean NCPSF_TTD_C_ICD;// BOOLEAN
	private Boolean NCPSF_TTD_C_OTHR;// BOOLEAN
	private String NCPSF_TTD_C_OTHR_TEXT;// VARCHAR
	private Boolean NCPSF_TTD_R_CPAP;// BOOLEAN
	private Boolean NCPSF_TTD_R_BIPAP;// BOOLEAN
	private Boolean NCPSF_TTD_R_O2;// BOOLEAN
	private String NCPSF_TTD_R_O2_TXT;// VARCHAR
	private Boolean NCPSF_TTD_R_PRN;// BOOLEAN
	private Boolean NCPSF_TTD_R_CNTNOUS;// BOOLEAN
	private Boolean NCPSF_TTD_R_SCTN;// BOOLEAN
	private Boolean NCPSF_TTD_R_TS;// BOOLEAN
	private String NCPSF_TTD_R_TS_TEXT;// VARCHAR
	private Boolean NCPSF_THRPS_PT;// BOOLEAN
	private Boolean NCPSF_THRPS_OT;// BOOLEAN
	private Boolean NCPSF_THRPS_S;// BOOLEAN
	private Boolean NCPSF_THRPS_R;// BOOLEAN
	private Boolean NCPSF_THRPS_D;// BOOLEAN
	private Boolean NCPSF_SC_NSB;// BOOLEAN
	private Boolean NCPSF_SC_PU;// BOOLEAN
	private String NCPSF_SC_PU_STAGE;// BOOLEAN need String as per pdf doc,
										// Boolean modified as String by RAMESH
	private String NCPSF_SC_PU_LOCATION;// BOOLEAN need String as per pdf doc,
										// Boolean modified as String by RAMESH
	private Boolean NCPSF_SC_SPU;// BOOLEAN
	private String NCPSF_SC_SPU_STAGE;// BOOLEAN
	private String NCPSF_SC_SPU_LOCATION;// BOOLEAN need String as per pdf doc,
											// Boolean modified as String by
											// RAMESH
	private Boolean NCPSF_SC_OTHR;// BOOLEAN
	private String NCPSF_SC_OTHR_SPCFY;// VARCHAR
	// NCPSF_R&P_FALL BOOLEAN
	private Boolean NCPSF_R_AND_P_FALL; // BOOLEAN not available Created by
										// RAMESH
	private Boolean NCPSF_R_AND_P_DELRUM;// BOOLEAN
	private Boolean NCPSF_R_AND_P_AGTATN;// BOOLEAN
	private Boolean NCPSF_R_AND_P_AGGRSN;// BOOLEAN
	private Boolean NCPSF_R_AND_P_UEE;// BOOLEAN
	private Boolean NCPSF_R_AND_P_ASPRTN;// BOOLEAN
	private Boolean NCPSF_R_AND_P_OTHR;// BOOLEAN
	private String NCPSF_R_AND_P_OTHR_TEXT;// VARCHAR
	private String NCPSF_R_AND_P_OTHER_PRECAUTIONS;
	private Boolean NCPSF_ICI_I_OR_C_MRSA;// BOOLEAN
	private Boolean NCPSF_ICI_I_OR_C_VRE;// BOOLEAN
	private Boolean NCPSF_ICI_I_OR_C_CDI; // BOOLEAN
	private Boolean NCPSF_ICI_I_OR_C_ESBL;// BOOLEAN
	private Boolean NCPSF_ICI_I_OR_C_NV;// BOOLEAN
	private Boolean NCPSF_ICI_I_OR_C_F_OR_R;// BOOLEAN
	private Boolean NCPSF_ICI_IP_NONE;// BOOLEAN
	private Boolean NCPSF_ICI_IP_CNTCT;// BOOLEAN
	private Boolean NCPSF_ICI_IP_CNTCTPLUS;// BOOLEAN
	private Boolean NCPSF_ICI_IP_DRPLT;// BOOLEAN
	private Boolean NCPSF_ICI_IP_ARBRNE;// BOOLEAN
	private Boolean NCPSF_ICI_I_INFLUNZA;// BOOLEAN
	private Boolean NCPSF_ICI_I_NO;// BOOLEAN
	private Boolean NCPSF_ICI_I_Y;// BOOLEAN
	private String NCPSF_ICI_I_YES_DTE;// VARCHAR
	private Boolean NCPSF_ICI_I_P;// BOOLEAN
	private Boolean NCPSF_ICI_I_P_NO;// BOOLEAN
	private Boolean NCPSF_ICI_I_P_YES;// BOOLEAN
	private String NCPSF_ICI_I_P_YES_DTE;// VARCHAR

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public Boolean getCanTakeDecision() {
		return canTakeDecision;
	}

	public void setCanTakeDecision(Boolean canTakeDecision) {
		this.canTakeDecision = canTakeDecision;
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

	public String getKCIVS_TIME_TAKEN() {
		return KCIVS_TIME_TAKEN;
	}

	public void setKCIVS_TIME_TAKEN(String kCIVS_TIME_TAKEN) {
		KCIVS_TIME_TAKEN = kCIVS_TIME_TAKEN;
	}

	public String getKCIVS_PAIN_RATING() {
		return KCIVS_PAIN_RATING;
	}

	public void setKCIVS_PAIN_RATING(String kCIVS_PAIN_RATING) {
		KCIVS_PAIN_RATING = kCIVS_PAIN_RATING;
	}

	public Boolean getKCIVS_NOT_APPLICABLE() {
		return KCIVS_NOT_APPLICABLE;
	}

	public void setKCIVS_NOT_APPLICABLE(Boolean kCIVS_NOT_APPLICABLE) {
		KCIVS_NOT_APPLICABLE = kCIVS_NOT_APPLICABLE;
	}

	public String getKCIVS_PAIN_SITE() {
		return KCIVS_PAIN_SITE;
	}

	public void setKCIVS_PAIN_SITE(String kCIVS_PAIN_SITE) {
		KCIVS_PAIN_SITE = kCIVS_PAIN_SITE;
	}

	public Integer getKCIVS_TEMP() {
		return KCIVS_TEMP;
	}

	public void setKCIVS_TEMP(Integer kCIVS_TEMP) {
		KCIVS_TEMP = kCIVS_TEMP;
	}

	public String getKCIVS_BP() {
		return KCIVS_BP;
	}

	public void setKCIVS_BP(String kCIVS_BP) {
		KCIVS_BP = kCIVS_BP;
	}

	public Integer getKCIVS_HR() {
		return KCIVS_HR;
	}

	public void setKCIVS_HR(Integer kCIVS_HR) {
		KCIVS_HR = kCIVS_HR;
	}

	public Integer getKCIVS_RR() {
		return KCIVS_RR;
	}

	public void setKCIVS_RR(Integer kCIVS_RR) {
		KCIVS_RR = kCIVS_RR;
	}

	public String getKCIVS_02SAT() {
		return KCIVS_02SAT;
	}

	public void setKCIVS_02SAT(String kCIVS_02SAT) {
		KCIVS_02SAT = kCIVS_02SAT;
	}

	public Integer getKCIVS_WGHT() {
		return KCIVS_WGHT;
	}

	public void setKCIVS_WGHT(Integer kCIVS_WGHT) {
		KCIVS_WGHT = kCIVS_WGHT;
	}

	public Boolean getKCIMS_ALRT() {
		return KCIMS_ALRT;
	}

	public void setKCIMS_ALRT(Boolean kCIMS_ALRT) {
		KCIMS_ALRT = kCIMS_ALRT;
	}

	public Boolean getKCIMS_DFC() {
		return KCIMS_DFC;
	}

	public void setKCIMS_DFC(Boolean kCIMS_DFC) {
		KCIMS_DFC = kCIMS_DFC;
	}

	public Boolean getKCIMS_DCFC() {
		return KCIMS_DCFC;
	}

	public void setKCIMS_DCFC(Boolean kCIMS_DCFC) {
		KCIMS_DCFC = kCIMS_DCFC;
	}

	public Boolean getKCIMS_N_ALRT() {
		return KCIMS_N_ALRT;
	}

	public void setKCIMS_N_ALRT(Boolean kCIMS_N_ALRT) {
		KCIMS_N_ALRT = kCIMS_N_ALRT;
	}

	public String getKCID_PDD() {
		return KCID_PDD;
	}

	public void setKCID_PDD(String kCID_PDD) {
		KCID_PDD = kCID_PDD;
	}

	public String getKCID_OMD() {
		return KCID_OMD;
	}

	public void setKCID_OMD(String kCID_OMD) {
		KCID_OMD = kCID_OMD;
	}

	public String getKCID_MHD() {
		return KCID_MHD;
	}

	public void setKCID_MHD(String kCID_MHD) {
		KCID_MHD = kCID_MHD;
	}

	public Boolean getHRC_OR_TI_FALRSK() {
		return HRC_OR_TI_FALRSK;
	}

	public void setHRC_OR_TI_FALRSK(Boolean hRC_OR_TI_FALRSK) {
		HRC_OR_TI_FALRSK = hRC_OR_TI_FALRSK;
	}

	public String getHRC_OR_TI_PREC() {
		return HRC_OR_TI_PREC;
	}

	public void setHRC_OR_TI_PREC(String hRC_OR_TI_PREC) {
		HRC_OR_TI_PREC = hRC_OR_TI_PREC;
	}

	public Boolean getHRC_OR_TI_HF() {
		return HRC_OR_TI_HF;
	}

	public void setHRC_OR_TI_HF(Boolean hRC_OR_TI_HF) {
		HRC_OR_TI_HF = hRC_OR_TI_HF;
	}

	public Boolean getHRC_OR_TI_HF_NEWDIAG() {
		return HRC_OR_TI_HF_NEWDIAG;
	}

	public void setHRC_OR_TI_HF_NEWDIAG(Boolean hRC_OR_TI_HF_NEWDIAG) {
		HRC_OR_TI_HF_NEWDIAG = hRC_OR_TI_HF_NEWDIAG;
	}

	public Boolean getHRC_OR_TI_HF_EXTHADMSSION() {
		return HRC_OR_TI_HF_EXTHADMSSION;
	}

	public void setHRC_OR_TI_HF_EXTHADMSSION(Boolean hRC_OR_TI_HF_EXTHADMSSION) {
		HRC_OR_TI_HF_EXTHADMSSION = hRC_OR_TI_HF_EXTHADMSSION;
	}

	public String getHRC_OR_TI_HF_EXTHADMSSION_LSTDTE() {
		return HRC_OR_TI_HF_EXTHADMSSION_LSTDTE;
	}

	public void setHRC_OR_TI_HF_EXTHADMSSION_LSTDTE(
			String hRC_OR_TI_HF_EXTHADMSSION_LSTDTE) {
		HRC_OR_TI_HF_EXTHADMSSION_LSTDTE = hRC_OR_TI_HF_EXTHADMSSION_LSTDTE;
	}

	public Boolean getHRC_OR_TI_HF_EF() {
		return HRC_OR_TI_HF_EF;
	}

	public void setHRC_OR_TI_HF_EF(Boolean hRC_OR_TI_HF_EF) {
		HRC_OR_TI_HF_EF = hRC_OR_TI_HF_EF;
	}

	public Integer getHRC_OR_TI_HF_EFP() {
		return HRC_OR_TI_HF_EFP;
	}

	public void setHRC_OR_TI_HF_EFP(Integer hRC_OR_TI_HF_EFP) {
		HRC_OR_TI_HF_EFP = hRC_OR_TI_HF_EFP;
	}

	public String getHRC_OR_TI_HF_EF_DW() {
		return HRC_OR_TI_HF_EF_DW;
	}

	public void setHRC_OR_TI_HF_EF_DW(String hRC_OR_TI_HF_EF_DW) {
		HRC_OR_TI_HF_EF_DW = hRC_OR_TI_HF_EF_DW;
	}

	public Boolean getHRC_OR_TI_AC() {
		return HRC_OR_TI_AC;
	}

	public void setHRC_OR_TI_AC(Boolean hRC_OR_TI_AC) {
		HRC_OR_TI_AC = hRC_OR_TI_AC;
	}

	public String getHRC_OR_TI_AC_REASON() {
		return HRC_OR_TI_AC_REASON;
	}

	public void setHRC_OR_TI_AC_REASON(String hRC_OR_TI_AC_REASON) {
		HRC_OR_TI_AC_REASON = hRC_OR_TI_AC_REASON;
	}

	public Boolean getHRC_OR_TI_AC_R_AFIB() {
		return HRC_OR_TI_AC_R_AFIB;
	}

	public void setHRC_OR_TI_AC_R_AFIB(Boolean hRC_OR_TI_AC_R_AFIB) {
		HRC_OR_TI_AC_R_AFIB = hRC_OR_TI_AC_R_AFIB;
	}

	public Boolean getHRC_OR_TI_AC_R_DVT_OR_PE() {
		return HRC_OR_TI_AC_R_DVT_OR_PE;
	}

	public void setHRC_OR_TI_AC_R_DVT_OR_PE(Boolean hRC_OR_TI_AC_R_DVT_OR_PE) {
		HRC_OR_TI_AC_R_DVT_OR_PE = hRC_OR_TI_AC_R_DVT_OR_PE;
	}

	public Boolean getHRC_OR_TI_AC_R_MECVAL() {
		return HRC_OR_TI_AC_R_MECVAL;
	}

	public void setHRC_OR_TI_AC_R_MECVAL(Boolean hRC_OR_TI_AC_R_MECVAL) {
		HRC_OR_TI_AC_R_MECVAL = hRC_OR_TI_AC_R_MECVAL;
	}

	public Boolean getHRC_OR_TI_AC_R_POP() {
		return HRC_OR_TI_AC_R_POP;
	}

	public void setHRC_OR_TI_AC_R_POP(Boolean hRC_OR_TI_AC_R_POP) {
		HRC_OR_TI_AC_R_POP = hRC_OR_TI_AC_R_POP;
	}

	public Boolean getHRC_OR_TI_AC_R_LOWEF() {
		return HRC_OR_TI_AC_R_LOWEF;
	}

	public void setHRC_OR_TI_AC_R_LOWEF(Boolean hRC_OR_TI_AC_R_LOWEF) {
		HRC_OR_TI_AC_R_LOWEF = hRC_OR_TI_AC_R_LOWEF;
	}

	public Boolean getHRC_OR_TI_AC_R_OTHR() {
		return HRC_OR_TI_AC_R_OTHR;
	}

	public void setHRC_OR_TI_AC_R_OTHR(Boolean hRC_OR_TI_AC_R_OTHR) {
		HRC_OR_TI_AC_R_OTHR = hRC_OR_TI_AC_R_OTHR;
	}

	public String getHRC_OR_TI_AC_R_OTHR_TEXT() {
		return HRC_OR_TI_AC_R_OTHR_TEXT;
	}

	public void setHRC_OR_TI_AC_R_OTHR_TEXT(String hRC_OR_TI_AC_R_OTHR_TEXT) {
		HRC_OR_TI_AC_R_OTHR_TEXT = hRC_OR_TI_AC_R_OTHR_TEXT;
	}

	public String getHRC_OR_TI_AC_R_DURTN() {
		return HRC_OR_TI_AC_R_DURTN;
	}

	public void setHRC_OR_TI_AC_R_DURTN(String hRC_OR_TI_AC_R_DURTN) {
		HRC_OR_TI_AC_R_DURTN = hRC_OR_TI_AC_R_DURTN;
	}

	public Boolean getHRC_OR_TI_GI_1_5_2_5() {
		return HRC_OR_TI_GI_1_5_2_5;
	}

	public void setHRC_OR_TI_GI_1_5_2_5(Boolean hRC_OR_TI_GI_1_5_2_5) {
		HRC_OR_TI_GI_1_5_2_5 = hRC_OR_TI_GI_1_5_2_5;
	}

	public Boolean getHRC_OR_TI_GI_2_3() {
		return HRC_OR_TI_GI_2_3;
	}

	public void setHRC_OR_TI_GI_2_3(Boolean hRC_OR_TI_GI_2_3) {
		HRC_OR_TI_GI_2_3 = hRC_OR_TI_GI_2_3;
	}

	public Boolean getHRC_OR_TI_GI_OTHR() {
		return HRC_OR_TI_GI_OTHR;
	}

	public void setHRC_OR_TI_GI_OTHR(Boolean hRC_OR_TI_GI_OTHR) {
		HRC_OR_TI_GI_OTHR = hRC_OR_TI_GI_OTHR;
	}

	public BigDecimal getHRC_OR_TI_GI_OTHR_TEXT() {
		return HRC_OR_TI_GI_OTHR_TEXT;
	}

	public void setHRC_OR_TI_GI_OTHR_TEXT(BigDecimal hRC_OR_TI_GI_OTHR_TEXT) {
		HRC_OR_TI_GI_OTHR_TEXT = hRC_OR_TI_GI_OTHR_TEXT;
	}

	public Boolean getHRC_OR_TI_OP() {
		return HRC_OR_TI_OP;
	}

	public void setHRC_OR_TI_OP(Boolean hRC_OR_TI_OP) {
		HRC_OR_TI_OP = hRC_OR_TI_OP;
	}

	public String getHRC_OR_TI_OP_INDCTN() {
		return HRC_OR_TI_OP_INDCTN;
	}

	public void setHRC_OR_TI_OP_INDCTN(String hRC_OR_TI_OP_INDCTN) {
		HRC_OR_TI_OP_INDCTN = hRC_OR_TI_OP_INDCTN;
	}

	public Boolean getHRC_OR_TI_OP__I_IHP_AND_CBD() {
		return HRC_OR_TI_OP__I_IHP_AND_CBD;
	}

	public void setHRC_OR_TI_OP__I_IHP_AND_CBD(
			Boolean hRC_OR_TI_OP__I_IHP_AND_CBD) {
		HRC_OR_TI_OP__I_IHP_AND_CBD = hRC_OR_TI_OP__I_IHP_AND_CBD;
	}

	public String getHRC_OR_TI_OP_SD() {
		return HRC_OR_TI_OP_SD;
	}

	public void setHRC_OR_TI_OP_SD(String hRC_OR_TI_OP_SD) {
		HRC_OR_TI_OP_SD = hRC_OR_TI_OP_SD;
	}

	public String getHRC_OR_TI_OP_SD_TEXT() {
		return HRC_OR_TI_OP_SD_TEXT;
	}

	public void setHRC_OR_TI_OP_SD_TEXT(String hRC_OR_TI_OP_SD_TEXT) {
		HRC_OR_TI_OP_SD_TEXT = hRC_OR_TI_OP_SD_TEXT;
	}

	public Boolean getHRC_OR_TI_OAB() {
		return HRC_OR_TI_OAB;
	}

	public void setHRC_OR_TI_OAB(Boolean hRC_OR_TI_OAB) {
		HRC_OR_TI_OAB = hRC_OR_TI_OAB;
	}

	public String getHRC_OR_TI_OAB_INDCTN() {
		return HRC_OR_TI_OAB_INDCTN;
	}

	public void setHRC_OR_TI_OAB_INDCTN(String hRC_OR_TI_OAB_INDCTN) {
		HRC_OR_TI_OAB_INDCTN = hRC_OR_TI_OAB_INDCTN;
	}

	public String getHRC_OR_TI_OAB_TTCD() {
		return HRC_OR_TI_OAB_TTCD;
	}

	public void setHRC_OR_TI_OAB_TTCD(String hRC_OR_TI_OAB_TTCD) {
		HRC_OR_TI_OAB_TTCD = hRC_OR_TI_OAB_TTCD;
	}

	public String getHRC_OR_TI_OAB_DS() {
		return HRC_OR_TI_OAB_DS;
	}

	public void setHRC_OR_TI_OAB_DS(String hRC_OR_TI_OAB_DS) {
		HRC_OR_TI_OAB_DS = hRC_OR_TI_OAB_DS;
	}

	public Boolean getHRC_OR_TI_D() {
		return HRC_OR_TI_D;
	}

	public void setHRC_OR_TI_D(Boolean hRC_OR_TI_D) {
		HRC_OR_TI_D = hRC_OR_TI_D;
	}

	public String getHRC_OR_TI_D_MRGD() {
		return HRC_OR_TI_D_MRGD;
	}

	public void setHRC_OR_TI_D_MRGD(String hRC_OR_TI_D_MRGD) {
		HRC_OR_TI_D_MRGD = hRC_OR_TI_D_MRGD;
	}

	public String getHRC_OR_TI_D_MRGT() {
		return HRC_OR_TI_D_MRGT;
	}

	public void setHRC_OR_TI_D_MRGT(String hRC_OR_TI_D_MRGT) {
		HRC_OR_TI_D_MRGT = hRC_OR_TI_D_MRGT;
	}

	public Boolean getM_AND_A_MLA() {
		return M_AND_A_MLA;
	}

	public void setM_AND_A_MLA(Boolean m_AND_A_MLA) {
		M_AND_A_MLA = m_AND_A_MLA;
	}

	public Boolean getM_AND_A_ALLRGS_NK() {
		return M_AND_A_ALLRGS_NK;
	}

	public void setM_AND_A_ALLRGS_NK(Boolean m_AND_A_ALLRGS_NK) {
		M_AND_A_ALLRGS_NK = m_AND_A_ALLRGS_NK;
	}

	public Boolean getM_AND_A_ALLRGS_Y() {
		return M_AND_A_ALLRGS_Y;
	}

	public void setM_AND_A_ALLRGS_Y(Boolean m_AND_A_ALLRGS_Y) {
		M_AND_A_ALLRGS_Y = m_AND_A_ALLRGS_Y;
	}

	public String getM_AND_A_ALLRGS_Y_SPCFY() {
		return M_AND_A_ALLRGS_Y_SPCFY;
	}

	public void setM_AND_A_ALLRGS_Y_SPCFY(String m_AND_A_ALLRGS_Y_SPCFY) {
		M_AND_A_ALLRGS_Y_SPCFY = m_AND_A_ALLRGS_Y_SPCFY;
	}

	public Boolean getM_AND_A_PM_N() {
		return M_AND_A_PM_N;
	}

	public void setM_AND_A_PM_N(Boolean m_AND_A_PM_N) {
		M_AND_A_PM_N = m_AND_A_PM_N;
	}

	public Boolean getM_AND_A_PM_Y() {
		return M_AND_A_PM_Y;
	}

	public void setM_AND_A_PM_Y(Boolean m_AND_A_PM_Y) {
		M_AND_A_PM_Y = m_AND_A_PM_Y;
	}

	public String getM_AND_A_PM_SPCFY() {
		return M_AND_A_PM_SPCFY;
	}

	public void setM_AND_A_PM_SPCFY(String m_AND_A_PM_SPCFY) {
		M_AND_A_PM_SPCFY = m_AND_A_PM_SPCFY;
	}

	public String getM_AND_A_PM_D() {
		return M_AND_A_PM_D;
	}

	public void setM_AND_A_PM_D(String m_AND_A_PM_D) {
		M_AND_A_PM_D = m_AND_A_PM_D;
	}

	public String getM_AND_A_PM_LD() {
		return M_AND_A_PM_LD;
	}

	public void setM_AND_A_PM_LD(String m_AND_A_PM_LD) {
		M_AND_A_PM_LD = m_AND_A_PM_LD;
	}

	public Boolean getNCPSF_AMBLTN_INDPNDNT() {
		return NCPSF_AMBLTN_INDPNDNT;
	}

	public void setNCPSF_AMBLTN_INDPNDNT(Boolean nCPSF_AMBLTN_INDPNDNT) {
		NCPSF_AMBLTN_INDPNDNT = nCPSF_AMBLTN_INDPNDNT;
	}

	public Boolean getNCPSF_AMBLTN_WA() {
		return NCPSF_AMBLTN_WA;
	}

	public void setNCPSF_AMBLTN_WA(Boolean nCPSF_AMBLTN_WA) {
		NCPSF_AMBLTN_WA = nCPSF_AMBLTN_WA;
	}

	public Boolean getNCPSF_AMBLTN_WAD() {
		return NCPSF_AMBLTN_WAD;
	}

	public void setNCPSF_AMBLTN_WAD(Boolean nCPSF_AMBLTN_WAD) {
		NCPSF_AMBLTN_WAD = nCPSF_AMBLTN_WAD;
	}

	public Boolean getNCPSF_AMBLTN_NTA() {
		return NCPSF_AMBLTN_NTA;
	}

	public void setNCPSF_AMBLTN_NTA(Boolean nCPSF_AMBLTN_NTA) {
		NCPSF_AMBLTN_NTA = nCPSF_AMBLTN_NTA;
	}

	public Boolean getNCPSF_WB_FULL() {
		return NCPSF_WB_FULL;
	}

	public void setNCPSF_WB_FULL(Boolean nCPSF_WB_FULL) {
		NCPSF_WB_FULL = nCPSF_WB_FULL;
	}

	public Boolean getNCPSF_WB_PARTL_L_OR_R() {
		return NCPSF_WB_PARTL_L_OR_R;
	}

	public void setNCPSF_WB_PARTL_L_OR_R(Boolean nCPSF_WB_PARTL_L_OR_R) {
		NCPSF_WB_PARTL_L_OR_R = nCPSF_WB_PARTL_L_OR_R;
	}

	public Boolean getNCPSF_WB_NONE() {
		return NCPSF_WB_NONE;
	}

	public void setNCPSF_WB_NONE(Boolean nCPSF_WB_NONE) {
		NCPSF_WB_NONE = nCPSF_WB_NONE;
	}

	public Boolean getNCPSF_TRNSFR_SLF() {
		return NCPSF_TRNSFR_SLF;
	}

	public void setNCPSF_TRNSFR_SLF(Boolean nCPSF_TRNSFR_SLF) {
		NCPSF_TRNSFR_SLF = nCPSF_TRNSFR_SLF;
	}

	public Boolean getNCPSF_TRNSFR_OPA() {
		return NCPSF_TRNSFR_OPA;
	}

	public void setNCPSF_TRNSFR_OPA(Boolean nCPSF_TRNSFR_OPA) {
		NCPSF_TRNSFR_OPA = nCPSF_TRNSFR_OPA;
	}

	public Boolean getNCPSF_TRNSFR_TPA() {
		return NCPSF_TRNSFR_TPA;
	}

	public void setNCPSF_TRNSFR_TPA(Boolean nCPSF_TRNSFR_TPA) {
		NCPSF_TRNSFR_TPA = nCPSF_TRNSFR_TPA;
	}

	public Boolean getNCPSF_SF_S_N() {
		return NCPSF_SF_S_N;
	}

	public void setNCPSF_SF_S_N(Boolean nCPSF_SF_S_N) {
		NCPSF_SF_S_N = nCPSF_SF_S_N;
	}

	public Boolean getNCPSF_SF_S_IMPRD() {
		return NCPSF_SF_S_IMPRD;
	}

	public void setNCPSF_SF_S_IMPRD(Boolean nCPSF_SF_S_IMPRD) {
		NCPSF_SF_S_IMPRD = nCPSF_SF_S_IMPRD;
	}

	public Boolean getNCPSF_SF_S_BLND() {
		return NCPSF_SF_S_BLND;
	}

	public void setNCPSF_SF_S_BLND(Boolean nCPSF_SF_S_BLND) {
		NCPSF_SF_S_BLND = nCPSF_SF_S_BLND;
	}

	public Boolean getNCPSF_SF_H_N() {
		return NCPSF_SF_H_N;
	}

	public void setNCPSF_SF_H_N(Boolean nCPSF_SF_H_N) {
		NCPSF_SF_H_N = nCPSF_SF_H_N;
	}

	public Boolean getNCPSF_SF_H_IMPRD() {
		return NCPSF_SF_H_IMPRD;
	}

	public void setNCPSF_SF_H_IMPRD(Boolean nCPSF_SF_H_IMPRD) {
		NCPSF_SF_H_IMPRD = nCPSF_SF_H_IMPRD;
	}

	public Boolean getNCPSF_SF_H_DF() {
		return NCPSF_SF_H_DF;
	}

	public void setNCPSF_SF_H_DF(Boolean nCPSF_SF_H_DF) {
		NCPSF_SF_H_DF = nCPSF_SF_H_DF;
	}

	public Boolean getNCPSF_DEV_WC() {
		return NCPSF_DEV_WC;
	}

	public void setNCPSF_DEV_WC(Boolean nCPSF_DEV_WC) {
		NCPSF_DEV_WC = nCPSF_DEV_WC;
	}

	public Boolean getNCPSF_DEV_WLKR() {
		return NCPSF_DEV_WLKR;
	}

	public void setNCPSF_DEV_WLKR(Boolean nCPSF_DEV_WLKR) {
		NCPSF_DEV_WLKR = nCPSF_DEV_WLKR;
	}

	public Boolean getNCPSF_DEV_C() {
		return NCPSF_DEV_C;
	}

	public void setNCPSF_DEV_C(Boolean nCPSF_DEV_C) {
		NCPSF_DEV_C = nCPSF_DEV_C;
	}

	public Boolean getNCPSF_DEV_CRTCHS() {
		return NCPSF_DEV_CRTCHS;
	}

	public void setNCPSF_DEV_CRTCHS(Boolean nCPSF_DEV_CRTCHS) {
		NCPSF_DEV_CRTCHS = nCPSF_DEV_CRTCHS;
	}

	public Boolean getNCPSF_DEV_PROS() {
		return NCPSF_DEV_PROS;
	}

	public void setNCPSF_DEV_PROS(Boolean nCPSF_DEV_PROS) {
		NCPSF_DEV_PROS = nCPSF_DEV_PROS;
	}

	public Boolean getNCPSF_DEV_GLSES() {
		return NCPSF_DEV_GLSES;
	}

	public void setNCPSF_DEV_GLSES(Boolean nCPSF_DEV_GLSES) {
		NCPSF_DEV_GLSES = nCPSF_DEV_GLSES;
	}

	public Boolean getNCPSF_DEV_CNTCTS() {
		return NCPSF_DEV_CNTCTS;
	}

	public void setNCPSF_DEV_CNTCTS(Boolean nCPSF_DEV_CNTCTS) {
		NCPSF_DEV_CNTCTS = nCPSF_DEV_CNTCTS;
	}

	public Boolean getNCPSF_DEV_D() {
		return NCPSF_DEV_D;
	}

	public void setNCPSF_DEV_D(Boolean nCPSF_DEV_D) {
		NCPSF_DEV_D = nCPSF_DEV_D;
	}

	public Boolean getNCPSF_DEV_HAL_OR_R() {
		return NCPSF_DEV_HAL_OR_R;
	}

	public void setNCPSF_DEV_HAL_OR_R(Boolean nCPSF_DEV_HAL_OR_R) {
		NCPSF_DEV_HAL_OR_R = nCPSF_DEV_HAL_OR_R;
	}

	public Boolean getNCPSF_C_CONTNENT() {
		return NCPSF_C_CONTNENT;
	}

	public void setNCPSF_C_CONTNENT(Boolean nCPSF_C_CONTNENT) {
		NCPSF_C_CONTNENT = nCPSF_C_CONTNENT;
	}

	public Boolean getNCPSF_C_INCONTNENT() {
		return NCPSF_C_INCONTNENT;
	}

	public void setNCPSF_C_INCONTNENT(Boolean nCPSF_C_INCONTNENT) {
		NCPSF_C_INCONTNENT = nCPSF_C_INCONTNENT;
	}

	public Boolean getNCPSF_C_CATHTR() {
		return NCPSF_C_CATHTR;
	}

	public void setNCPSF_C_CATHTR(Boolean nCPSF_C_CATHTR) {
		NCPSF_C_CATHTR = nCPSF_C_CATHTR;
	}

	public String getNCPSF_C_CATHTR_DI() {
		return NCPSF_C_CATHTR_DI;
	}

	public void setNCPSF_C_CATHTR_DI(String nCPSF_C_CATHTR_DI) {
		NCPSF_C_CATHTR_DI = nCPSF_C_CATHTR_DI;
	}

	public Boolean getNCPSF_C_RFC_R() {
		return NCPSF_C_RFC_R;
	}

	public void setNCPSF_C_RFC_R(Boolean nCPSF_C_RFC_R) {
		NCPSF_C_RFC_R = nCPSF_C_RFC_R;
	}

	public Boolean getNCPSF_C_RFC_SP() {
		return NCPSF_C_RFC_SP;
	}

	public void setNCPSF_C_RFC_SP(Boolean nCPSF_C_RFC_SP) {
		NCPSF_C_RFC_SP = nCPSF_C_RFC_SP;
	}

	public Boolean getNCPSF_C_RFC_OTHR() {
		return NCPSF_C_RFC_OTHR;
	}

	public void setNCPSF_C_RFC_OTHR(Boolean nCPSF_C_RFC_OTHR) {
		NCPSF_C_RFC_OTHR = nCPSF_C_RFC_OTHR;
	}

	public String getNCPSF_C_RFC_OTHR_SPFY() {
		return NCPSF_C_RFC_OTHR_SPFY;
	}

	public void setNCPSF_C_RFC_OTHR_SPFY(String nCPSF_C_RFC_OTHR_SPFY) {
		NCPSF_C_RFC_OTHR_SPFY = nCPSF_C_RFC_OTHR_SPFY;
	}

	public Boolean getNCPSF_C_BOWLINCONTNENT() {
		return NCPSF_C_BOWLINCONTNENT;
	}

	public void setNCPSF_C_BOWLINCONTNENT(Boolean nCPSF_C_BOWLINCONTNENT) {
		NCPSF_C_BOWLINCONTNENT = nCPSF_C_BOWLINCONTNENT;
	}

	public Boolean getNCPSF_C_OSTMY() {
		return NCPSF_C_OSTMY;
	}

	public void setNCPSF_C_OSTMY(Boolean nCPSF_C_OSTMY) {
		NCPSF_C_OSTMY = nCPSF_C_OSTMY;
	}

	public Boolean getNCPSF_C_DOLBM() {
		return NCPSF_C_DOLBM;
	}

	public void setNCPSF_C_DOLBM(Boolean nCPSF_C_DOLBM) {
		NCPSF_C_DOLBM = nCPSF_C_DOLBM;
	}

	public String getNCPSF_C_DOLB() {
		return NCPSF_C_DOLB;
	}

	public void setNCPSF_C_DOLB(String nCPSF_C_DOLB) {
		NCPSF_C_DOLB = nCPSF_C_DOLB;
	}

	public String getNCPSF_N_AND_H_D() {
		return NCPSF_N_AND_H_D;
	}

	public void setNCPSF_N_AND_H_D(String nCPSF_N_AND_H_D) {
		NCPSF_N_AND_H_D = nCPSF_N_AND_H_D;
	}

	public String getNCPSF_N_AND_H_C() {
		return NCPSF_N_AND_H_C;
	}

	public void setNCPSF_N_AND_H_C(String nCPSF_N_AND_H_C) {
		NCPSF_N_AND_H_C = nCPSF_N_AND_H_C;
	}

	public String getNCPSF_N_AND_H_FWR() {
		return NCPSF_N_AND_H_FWR;
	}

	public void setNCPSF_N_AND_H_FWR(String nCPSF_N_AND_H_FWR) {
		NCPSF_N_AND_H_FWR = nCPSF_N_AND_H_FWR;
	}

	public Boolean getNCPSF_N_AND_H_EI_SLF() {
		return NCPSF_N_AND_H_EI_SLF;
	}

	public void setNCPSF_N_AND_H_EI_SLF(Boolean nCPSF_N_AND_H_EI_SLF) {
		NCPSF_N_AND_H_EI_SLF = nCPSF_N_AND_H_EI_SLF;
	}

	public Boolean getNCPSF_N_AND_H_EI_WA() {
		return NCPSF_N_AND_H_EI_WA;
	}

	public void setNCPSF_N_AND_H_EI_WA(Boolean nCPSF_N_AND_H_EI_WA) {
		NCPSF_N_AND_H_EI_WA = nCPSF_N_AND_H_EI_WA;
	}

	public Boolean getNCPSF_N_AND_H_EI_DS() {
		return NCPSF_N_AND_H_EI_DS;
	}

	public void setNCPSF_N_AND_H_EI_DS(Boolean nCPSF_N_AND_H_EI_DS) {
		NCPSF_N_AND_H_EI_DS = nCPSF_N_AND_H_EI_DS;
	}

	public Boolean getNCPSF_N_AND_H_EI_DS_ASTRIA() {
		return NCPSF_N_AND_H_EI_DS_ASTRIA;
	}

	public void setNCPSF_N_AND_H_EI_DS_ASTRIA(Boolean nCPSF_N_AND_H_EI_DS_ASTRIA) {
		NCPSF_N_AND_H_EI_DS_ASTRIA = nCPSF_N_AND_H_EI_DS_ASTRIA;
	}

	public Boolean getNCPSF_N_AND_H_TF_GT() {
		return NCPSF_N_AND_H_TF_GT;
	}

	public void setNCPSF_N_AND_H_TF_GT(Boolean nCPSF_N_AND_H_TF_GT) {
		NCPSF_N_AND_H_TF_GT = nCPSF_N_AND_H_TF_GT;
	}

	public Boolean getNCPSF_N_AND_H_TF_JT() {
		return NCPSF_N_AND_H_TF_JT;
	}

	public void setNCPSF_N_AND_H_TF_JT(Boolean nCPSF_N_AND_H_TF_JT) {
		NCPSF_N_AND_H_TF_JT = nCPSF_N_AND_H_TF_JT;
	}

	public Boolean getNCPSF_N_AND_H_TF_DI() {
		return NCPSF_N_AND_H_TF_DI;
	}

	public void setNCPSF_N_AND_H_TF_DI(Boolean nCPSF_N_AND_H_TF_DI) {
		NCPSF_N_AND_H_TF_DI = nCPSF_N_AND_H_TF_DI;
	}

	public String getNCPSF_N_AND_H_TF_DIT() {
		return NCPSF_N_AND_H_TF_DIT;
	}

	public void setNCPSF_N_AND_H_TF_DIT(String nCPSF_N_AND_H_TF_DIT) {
		NCPSF_N_AND_H_TF_DIT = nCPSF_N_AND_H_TF_DIT;
	}

	public String getNCPSF_N_AND_H_TF_DI_FWB_CC() {
		return NCPSF_N_AND_H_TF_DI_FWB_CC;
	}

	public void setNCPSF_N_AND_H_TF_DI_FWB_CC(String nCPSF_N_AND_H_TF_DI_FWB_CC) {
		NCPSF_N_AND_H_TF_DI_FWB_CC = nCPSF_N_AND_H_TF_DI_FWB_CC;
	}

	public String getNCPSF_N_AND_H_TF_DI_FWB_HRS() {
		return NCPSF_N_AND_H_TF_DI_FWB_HRS;
	}

	public void setNCPSF_N_AND_H_TF_DI_FWB_HRS(
			String nCPSF_N_AND_H_TF_DI_FWB_HRS) {
		NCPSF_N_AND_H_TF_DI_FWB_HRS = nCPSF_N_AND_H_TF_DI_FWB_HRS;
	}

	public Boolean getNCPSF_N_AND_H_TF_TFP() {
		return NCPSF_N_AND_H_TF_TFP;
	}

	public void setNCPSF_N_AND_H_TF_TFP(Boolean nCPSF_N_AND_H_TF_TFP) {
		NCPSF_N_AND_H_TF_TFP = nCPSF_N_AND_H_TF_TFP;
	}

	public String getNCPSF_N_AND_H_TF_TFP_T() {
		return NCPSF_N_AND_H_TF_TFP_T;
	}

	public void setNCPSF_N_AND_H_TF_TFP_T(String nCPSF_N_AND_H_TF_TFP_T) {
		NCPSF_N_AND_H_TF_TFP_T = nCPSF_N_AND_H_TF_TFP_T;
	}

	public String getNCPSF_N_AND_H_TF_TFP_RTE_CC_PER_H() {
		return NCPSF_N_AND_H_TF_TFP_RTE_CC_PER_H;
	}

	public void setNCPSF_N_AND_H_TF_TFP_RTE_CC_PER_H(
			String nCPSF_N_AND_H_TF_TFP_RTE_CC_PER_H) {
		NCPSF_N_AND_H_TF_TFP_RTE_CC_PER_H = nCPSF_N_AND_H_TF_TFP_RTE_CC_PER_H;
	}

	public String getNCPSF_N_AND_H_TF_TFP_EVERY_H_OR_DAY() {
		return NCPSF_N_AND_H_TF_TFP_EVERY_H_OR_DAY;
	}

	public void setNCPSF_N_AND_H_TF_TFP_EVERY_H_OR_DAY(
			String nCPSF_N_AND_H_TF_TFP_EVERY_H_OR_DAY) {
		NCPSF_N_AND_H_TF_TFP_EVERY_H_OR_DAY = nCPSF_N_AND_H_TF_TFP_EVERY_H_OR_DAY;
	}

	public Boolean getNCPSF_TTD_PICC() {
		return NCPSF_TTD_PICC;
	}

	public void setNCPSF_TTD_PICC(Boolean nCPSF_TTD_PICC) {
		NCPSF_TTD_PICC = nCPSF_TTD_PICC;
	}

	public Boolean getNCPSF_TTD_PRTCTH() {
		return NCPSF_TTD_PRTCTH;
	}

	public void setNCPSF_TTD_PRTCTH(Boolean nCPSF_TTD_PRTCTH) {
		NCPSF_TTD_PRTCTH = nCPSF_TTD_PRTCTH;
	}

	public String getNCPSF_TTD_PRTCTH_DI() {
		return NCPSF_TTD_PRTCTH_DI;
	}

	public void setNCPSF_TTD_PRTCTH_DI(String nCPSF_TTD_PRTCTH_DI) {
		NCPSF_TTD_PRTCTH_DI = nCPSF_TTD_PRTCTH_DI;
	}

	public Boolean getNCPSF_TTD_C_PM() {
		return NCPSF_TTD_C_PM;
	}

	public void setNCPSF_TTD_C_PM(Boolean nCPSF_TTD_C_PM) {
		NCPSF_TTD_C_PM = nCPSF_TTD_C_PM;
	}

	public Boolean getNCPSF_TTD_C_ICD() {
		return NCPSF_TTD_C_ICD;
	}

	public void setNCPSF_TTD_C_ICD(Boolean nCPSF_TTD_C_ICD) {
		NCPSF_TTD_C_ICD = nCPSF_TTD_C_ICD;
	}

	public Boolean getNCPSF_TTD_C_OTHR() {
		return NCPSF_TTD_C_OTHR;
	}

	public void setNCPSF_TTD_C_OTHR(Boolean nCPSF_TTD_C_OTHR) {
		NCPSF_TTD_C_OTHR = nCPSF_TTD_C_OTHR;
	}

	public String getNCPSF_TTD_C_OTHR_TEXT() {
		return NCPSF_TTD_C_OTHR_TEXT;
	}

	public void setNCPSF_TTD_C_OTHR_TEXT(String nCPSF_TTD_C_OTHR_TEXT) {
		NCPSF_TTD_C_OTHR_TEXT = nCPSF_TTD_C_OTHR_TEXT;
	}

	public Boolean getNCPSF_TTD_R_CPAP() {
		return NCPSF_TTD_R_CPAP;
	}

	public void setNCPSF_TTD_R_CPAP(Boolean nCPSF_TTD_R_CPAP) {
		NCPSF_TTD_R_CPAP = nCPSF_TTD_R_CPAP;
	}

	public Boolean getNCPSF_TTD_R_BIPAP() {
		return NCPSF_TTD_R_BIPAP;
	}

	public void setNCPSF_TTD_R_BIPAP(Boolean nCPSF_TTD_R_BIPAP) {
		NCPSF_TTD_R_BIPAP = nCPSF_TTD_R_BIPAP;
	}

	public Boolean getNCPSF_TTD_R_O2() {
		return NCPSF_TTD_R_O2;
	}

	public void setNCPSF_TTD_R_O2(Boolean nCPSF_TTD_R_O2) {
		NCPSF_TTD_R_O2 = nCPSF_TTD_R_O2;
	}

	public String getNCPSF_TTD_R_O2_TXT() {
		return NCPSF_TTD_R_O2_TXT;
	}

	public void setNCPSF_TTD_R_O2_TXT(String nCPSF_TTD_R_O2_TXT) {
		NCPSF_TTD_R_O2_TXT = nCPSF_TTD_R_O2_TXT;
	}

	public Boolean getNCPSF_TTD_R_PRN() {
		return NCPSF_TTD_R_PRN;
	}

	public void setNCPSF_TTD_R_PRN(Boolean nCPSF_TTD_R_PRN) {
		NCPSF_TTD_R_PRN = nCPSF_TTD_R_PRN;
	}

	public Boolean getNCPSF_TTD_R_CNTNOUS() {
		return NCPSF_TTD_R_CNTNOUS;
	}

	public void setNCPSF_TTD_R_CNTNOUS(Boolean nCPSF_TTD_R_CNTNOUS) {
		NCPSF_TTD_R_CNTNOUS = nCPSF_TTD_R_CNTNOUS;
	}

	public Boolean getNCPSF_TTD_R_SCTN() {
		return NCPSF_TTD_R_SCTN;
	}

	public void setNCPSF_TTD_R_SCTN(Boolean nCPSF_TTD_R_SCTN) {
		NCPSF_TTD_R_SCTN = nCPSF_TTD_R_SCTN;
	}

	public Boolean getNCPSF_TTD_R_TS() {
		return NCPSF_TTD_R_TS;
	}

	public void setNCPSF_TTD_R_TS(Boolean nCPSF_TTD_R_TS) {
		NCPSF_TTD_R_TS = nCPSF_TTD_R_TS;
	}

	public String getNCPSF_TTD_R_TS_TEXT() {
		return NCPSF_TTD_R_TS_TEXT;
	}

	public void setNCPSF_TTD_R_TS_TEXT(String nCPSF_TTD_R_TS_TEXT) {
		NCPSF_TTD_R_TS_TEXT = nCPSF_TTD_R_TS_TEXT;
	}

	public Boolean getNCPSF_THRPS_PT() {
		return NCPSF_THRPS_PT;
	}

	public void setNCPSF_THRPS_PT(Boolean nCPSF_THRPS_PT) {
		NCPSF_THRPS_PT = nCPSF_THRPS_PT;
	}

	public Boolean getNCPSF_THRPS_OT() {
		return NCPSF_THRPS_OT;
	}

	public void setNCPSF_THRPS_OT(Boolean nCPSF_THRPS_OT) {
		NCPSF_THRPS_OT = nCPSF_THRPS_OT;
	}

	public Boolean getNCPSF_THRPS_S() {
		return NCPSF_THRPS_S;
	}

	public void setNCPSF_THRPS_S(Boolean nCPSF_THRPS_S) {
		NCPSF_THRPS_S = nCPSF_THRPS_S;
	}

	public Boolean getNCPSF_THRPS_R() {
		return NCPSF_THRPS_R;
	}

	public void setNCPSF_THRPS_R(Boolean nCPSF_THRPS_R) {
		NCPSF_THRPS_R = nCPSF_THRPS_R;
	}

	public Boolean getNCPSF_THRPS_D() {
		return NCPSF_THRPS_D;
	}

	public void setNCPSF_THRPS_D(Boolean nCPSF_THRPS_D) {
		NCPSF_THRPS_D = nCPSF_THRPS_D;
	}

	public Boolean getNCPSF_SC_NSB() {
		return NCPSF_SC_NSB;
	}

	public void setNCPSF_SC_NSB(Boolean nCPSF_SC_NSB) {
		NCPSF_SC_NSB = nCPSF_SC_NSB;
	}

	public Boolean getNCPSF_SC_PU() {
		return NCPSF_SC_PU;
	}

	public void setNCPSF_SC_PU(Boolean nCPSF_SC_PU) {
		NCPSF_SC_PU = nCPSF_SC_PU;
	}

	public String getNCPSF_SC_PU_STAGE() {
		return NCPSF_SC_PU_STAGE;
	}

	public void setNCPSF_SC_PU_STAGE(String nCPSF_SC_PU_STAGE) {
		NCPSF_SC_PU_STAGE = nCPSF_SC_PU_STAGE;
	}

	public String getNCPSF_SC_PU_LOCATION() {
		return NCPSF_SC_PU_LOCATION;
	}

	public void setNCPSF_SC_PU_LOCATION(String nCPSF_SC_PU_LOCATION) {
		NCPSF_SC_PU_LOCATION = nCPSF_SC_PU_LOCATION;
	}

	public Boolean getNCPSF_SC_SPU() {
		return NCPSF_SC_SPU;
	}

	public void setNCPSF_SC_SPU(Boolean nCPSF_SC_SPU) {
		NCPSF_SC_SPU = nCPSF_SC_SPU;
	}

	public String getNCPSF_SC_SPU_STAGE() {
		return NCPSF_SC_SPU_STAGE;
	}

	public void setNCPSF_SC_SPU_STAGE(String nCPSF_SC_SPU_STAGE) {
		NCPSF_SC_SPU_STAGE = nCPSF_SC_SPU_STAGE;
	}

	public String getNCPSF_SC_SPU_LOCATION() {
		return NCPSF_SC_SPU_LOCATION;
	}

	public void setNCPSF_SC_SPU_LOCATION(String nCPSF_SC_SPU_LOCATION) {
		NCPSF_SC_SPU_LOCATION = nCPSF_SC_SPU_LOCATION;
	}

	public Boolean getNCPSF_SC_OTHR() {
		return NCPSF_SC_OTHR;
	}

	public void setNCPSF_SC_OTHR(Boolean nCPSF_SC_OTHR) {
		NCPSF_SC_OTHR = nCPSF_SC_OTHR;
	}

	public String getNCPSF_SC_OTHR_SPCFY() {
		return NCPSF_SC_OTHR_SPCFY;
	}

	public void setNCPSF_SC_OTHR_SPCFY(String nCPSF_SC_OTHR_SPCFY) {
		NCPSF_SC_OTHR_SPCFY = nCPSF_SC_OTHR_SPCFY;
	}

	public Boolean getNCPSF_R_AND_P_FALL() {
		return NCPSF_R_AND_P_FALL;
	}

	public void setNCPSF_R_AND_P_FALL(Boolean nCPSF_R_AND_P_FALL) {
		NCPSF_R_AND_P_FALL = nCPSF_R_AND_P_FALL;
	}

	public Boolean getNCPSF_R_AND_P_DELRUM() {
		return NCPSF_R_AND_P_DELRUM;
	}

	public void setNCPSF_R_AND_P_DELRUM(Boolean nCPSF_R_AND_P_DELRUM) {
		NCPSF_R_AND_P_DELRUM = nCPSF_R_AND_P_DELRUM;
	}

	public Boolean getNCPSF_R_AND_P_AGTATN() {
		return NCPSF_R_AND_P_AGTATN;
	}

	public void setNCPSF_R_AND_P_AGTATN(Boolean nCPSF_R_AND_P_AGTATN) {
		NCPSF_R_AND_P_AGTATN = nCPSF_R_AND_P_AGTATN;
	}

	public Boolean getNCPSF_R_AND_P_AGGRSN() {
		return NCPSF_R_AND_P_AGGRSN;
	}

	public void setNCPSF_R_AND_P_AGGRSN(Boolean nCPSF_R_AND_P_AGGRSN) {
		NCPSF_R_AND_P_AGGRSN = nCPSF_R_AND_P_AGGRSN;
	}

	public Boolean getNCPSF_R_AND_P_UEE() {
		return NCPSF_R_AND_P_UEE;
	}

	public void setNCPSF_R_AND_P_UEE(Boolean nCPSF_R_AND_P_UEE) {
		NCPSF_R_AND_P_UEE = nCPSF_R_AND_P_UEE;
	}

	public Boolean getNCPSF_R_AND_P_ASPRTN() {
		return NCPSF_R_AND_P_ASPRTN;
	}

	public void setNCPSF_R_AND_P_ASPRTN(Boolean nCPSF_R_AND_P_ASPRTN) {
		NCPSF_R_AND_P_ASPRTN = nCPSF_R_AND_P_ASPRTN;
	}

	public Boolean getNCPSF_R_AND_P_OTHR() {
		return NCPSF_R_AND_P_OTHR;
	}

	public void setNCPSF_R_AND_P_OTHR(Boolean nCPSF_R_AND_P_OTHR) {
		NCPSF_R_AND_P_OTHR = nCPSF_R_AND_P_OTHR;
	}

	public String getNCPSF_R_AND_P_OTHR_TEXT() {
		return NCPSF_R_AND_P_OTHR_TEXT;
	}

	public void setNCPSF_R_AND_P_OTHR_TEXT(String nCPSF_R_AND_P_OTHR_TEXT) {
		NCPSF_R_AND_P_OTHR_TEXT = nCPSF_R_AND_P_OTHR_TEXT;
	}

	public String getNCPSF_R_AND_P_OTHER_PRECAUTIONS() {
		return NCPSF_R_AND_P_OTHER_PRECAUTIONS;
	}

	public void setNCPSF_R_AND_P_OTHER_PRECAUTIONS(
			String nCPSF_R_AND_P_OTHER_PRECAUTIONS) {
		NCPSF_R_AND_P_OTHER_PRECAUTIONS = nCPSF_R_AND_P_OTHER_PRECAUTIONS;
	}

	public Boolean getNCPSF_ICI_I_OR_C_MRSA() {
		return NCPSF_ICI_I_OR_C_MRSA;
	}

	public void setNCPSF_ICI_I_OR_C_MRSA(Boolean nCPSF_ICI_I_OR_C_MRSA) {
		NCPSF_ICI_I_OR_C_MRSA = nCPSF_ICI_I_OR_C_MRSA;
	}

	public Boolean getNCPSF_ICI_I_OR_C_VRE() {
		return NCPSF_ICI_I_OR_C_VRE;
	}

	public void setNCPSF_ICI_I_OR_C_VRE(Boolean nCPSF_ICI_I_OR_C_VRE) {
		NCPSF_ICI_I_OR_C_VRE = nCPSF_ICI_I_OR_C_VRE;
	}

	public Boolean getNCPSF_ICI_I_OR_C_CDI() {
		return NCPSF_ICI_I_OR_C_CDI;
	}

	public void setNCPSF_ICI_I_OR_C_CDI(Boolean nCPSF_ICI_I_OR_C_CDI) {
		NCPSF_ICI_I_OR_C_CDI = nCPSF_ICI_I_OR_C_CDI;
	}

	public Boolean getNCPSF_ICI_I_OR_C_ESBL() {
		return NCPSF_ICI_I_OR_C_ESBL;
	}

	public void setNCPSF_ICI_I_OR_C_ESBL(Boolean nCPSF_ICI_I_OR_C_ESBL) {
		NCPSF_ICI_I_OR_C_ESBL = nCPSF_ICI_I_OR_C_ESBL;
	}

	public Boolean getNCPSF_ICI_I_OR_C_NV() {
		return NCPSF_ICI_I_OR_C_NV;
	}

	public void setNCPSF_ICI_I_OR_C_NV(Boolean nCPSF_ICI_I_OR_C_NV) {
		NCPSF_ICI_I_OR_C_NV = nCPSF_ICI_I_OR_C_NV;
	}

	public Boolean getNCPSF_ICI_I_OR_C_F_OR_R() {
		return NCPSF_ICI_I_OR_C_F_OR_R;
	}

	public void setNCPSF_ICI_I_OR_C_F_OR_R(Boolean nCPSF_ICI_I_OR_C_F_OR_R) {
		NCPSF_ICI_I_OR_C_F_OR_R = nCPSF_ICI_I_OR_C_F_OR_R;
	}

	public Boolean getNCPSF_ICI_IP_NONE() {
		return NCPSF_ICI_IP_NONE;
	}

	public void setNCPSF_ICI_IP_NONE(Boolean nCPSF_ICI_IP_NONE) {
		NCPSF_ICI_IP_NONE = nCPSF_ICI_IP_NONE;
	}

	public Boolean getNCPSF_ICI_IP_CNTCT() {
		return NCPSF_ICI_IP_CNTCT;
	}

	public void setNCPSF_ICI_IP_CNTCT(Boolean nCPSF_ICI_IP_CNTCT) {
		NCPSF_ICI_IP_CNTCT = nCPSF_ICI_IP_CNTCT;
	}

	public Boolean getNCPSF_ICI_IP_CNTCTPLUS() {
		return NCPSF_ICI_IP_CNTCTPLUS;
	}

	public void setNCPSF_ICI_IP_CNTCTPLUS(Boolean nCPSF_ICI_IP_CNTCTPLUS) {
		NCPSF_ICI_IP_CNTCTPLUS = nCPSF_ICI_IP_CNTCTPLUS;
	}

	public Boolean getNCPSF_ICI_IP_DRPLT() {
		return NCPSF_ICI_IP_DRPLT;
	}

	public void setNCPSF_ICI_IP_DRPLT(Boolean nCPSF_ICI_IP_DRPLT) {
		NCPSF_ICI_IP_DRPLT = nCPSF_ICI_IP_DRPLT;
	}

	public Boolean getNCPSF_ICI_IP_ARBRNE() {
		return NCPSF_ICI_IP_ARBRNE;
	}

	public void setNCPSF_ICI_IP_ARBRNE(Boolean nCPSF_ICI_IP_ARBRNE) {
		NCPSF_ICI_IP_ARBRNE = nCPSF_ICI_IP_ARBRNE;
	}

	public Boolean getNCPSF_ICI_I_INFLUNZA() {
		return NCPSF_ICI_I_INFLUNZA;
	}

	public void setNCPSF_ICI_I_INFLUNZA(Boolean nCPSF_ICI_I_INFLUNZA) {
		NCPSF_ICI_I_INFLUNZA = nCPSF_ICI_I_INFLUNZA;
	}

	public Boolean getNCPSF_ICI_I_NO() {
		return NCPSF_ICI_I_NO;
	}

	public void setNCPSF_ICI_I_NO(Boolean nCPSF_ICI_I_NO) {
		NCPSF_ICI_I_NO = nCPSF_ICI_I_NO;
	}

	public Boolean getNCPSF_ICI_I_Y() {
		return NCPSF_ICI_I_Y;
	}

	public void setNCPSF_ICI_I_Y(Boolean nCPSF_ICI_I_Y) {
		NCPSF_ICI_I_Y = nCPSF_ICI_I_Y;
	}

	public String getNCPSF_ICI_I_YES_DTE() {
		return NCPSF_ICI_I_YES_DTE;
	}

	public void setNCPSF_ICI_I_YES_DTE(String nCPSF_ICI_I_YES_DTE) {
		NCPSF_ICI_I_YES_DTE = nCPSF_ICI_I_YES_DTE;
	}

	public Boolean getNCPSF_ICI_I_P() {
		return NCPSF_ICI_I_P;
	}

	public void setNCPSF_ICI_I_P(Boolean nCPSF_ICI_I_P) {
		NCPSF_ICI_I_P = nCPSF_ICI_I_P;
	}

	public Boolean getNCPSF_ICI_I_P_NO() {
		return NCPSF_ICI_I_P_NO;
	}

	public void setNCPSF_ICI_I_P_NO(Boolean nCPSF_ICI_I_P_NO) {
		NCPSF_ICI_I_P_NO = nCPSF_ICI_I_P_NO;
	}

	public Boolean getNCPSF_ICI_I_P_YES() {
		return NCPSF_ICI_I_P_YES;
	}

	public void setNCPSF_ICI_I_P_YES(Boolean nCPSF_ICI_I_P_YES) {
		NCPSF_ICI_I_P_YES = nCPSF_ICI_I_P_YES;
	}

	public String getNCPSF_ICI_I_P_YES_DTE() {
		return NCPSF_ICI_I_P_YES_DTE;
	}

	public void setNCPSF_ICI_I_P_YES_DTE(String nCPSF_ICI_I_P_YES_DTE) {
		NCPSF_ICI_I_P_YES_DTE = nCPSF_ICI_I_P_YES_DTE;
	}

}
