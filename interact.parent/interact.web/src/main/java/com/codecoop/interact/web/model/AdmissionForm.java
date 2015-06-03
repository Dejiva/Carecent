package com.codecoop.interact.web.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.dto.MedicalReconciliationDto;

@Component
public class AdmissionForm {


	// START : Patient Information
	@NotEmpty(message = "First name shoud have value")
	private String patientFirstName;

	private String patientLastName;

	@NotEmpty(message = "DOB should have value")
	private String dob;

	private String doj;

	@NotEmpty(message = "SSN should not be empty")
	private String sSNumber;


	private String pcpWorkNumber;

	@NotNull(message = "PCP at site should be selected")
	private Long pcpAtSite;


	@NotEmpty(message = "Gender shoud specify")
	private String patientGender;
	private String canSpeakEnglish;
	private String otherLanguage;

	@NotEmpty(message = "Ethnicity should be specified")
	private String ethnicityType;

	private String otherEthnicityType;
	private String roomNumber;

	// END : Patient Information

	// START : Family/Caregiver/proxy contact

	//	@NotEmpty(message = "Care giver name is required")
	private String caregiverName;

	//	@NotEmpty(message = "care giver Telephone Number is required")
	private String careGiverTelephoneNumber;
	//	
	//	@NotEmpty(message = "guardian name is required")
	private String guardianName;

	//	@NotEmpty(message = "guardian telephone number is required")
	private String guardianTelephoneNumber;
	// END :Family/Caregiver/proxy contact

	// START : Advance Directives/Goals of Care

	//	@AssertTrue(message = "FULLCODE is required")
	private Boolean fullCode;

	//	@AssertTrue(message = "DNR is required")
	private Boolean dnr;

	//	@AssertTrue(message = "DNI is required")
	private Boolean dni;

	//	@AssertTrue(message = "DNH is required")
	private Boolean dnh;

	//	@AssertTrue(message = "NOARTIFICIALFEEDING is required")
	private Boolean noArtificialFeeding;

	//	@AssertTrue(message = "COMFORTCARE is required")
	private Boolean comfortCare;

	//	@AssertTrue(message = "HOSPICECARE is required")
	private Boolean hospiceCare;

	private Boolean otherDirectiveCheckBox;
	private String otherAdvanceDirectives;
	private String goalsOfCareDiscussed; //(NO OR YES)
	private String specifyGoalsOfCareDiscussed;
	private String canTakeDecision;
	private String reqProxy;
	// END : Advance Directives/Goals of Care

	// START : Transferring Hospital Information
	private String hospitalName;
	private Long hospitalId; // populates from Auto suggest of hospital name
	private String hospitalUnit;
	private String dischargingRN;
	private Long dischargingRNId;  // populates from Auto suggest of RN
	private String dishcargingRNTelephoneNumber;
	private String dischargingMD;  
	private Long dischargingMDId;  // populates from Auto suggest of MD
	private String dishcargingMDTelephoneNumber;
	private String hospitalAdmissionDate;
	private String fromHospitalDischargeDate;
	private String patientCareTypes;
	private String patientInsurencePlan;
	// END : Transferring Hospital Information

	// START : Post Acute Care Information
	private String transferredToPostAcuteCare;
	private Long facilityId; // populates from Auto suggest of Facility
	private Long patientId;
	private Long admittedByStaffId;
	private String admittedByStaffName;
	private String admittedByStaffType;
	private Long patientEpisodeId;
	private Long gCareGiverId;
	private Long gProxyId;
	private String postAcuteCareTelephoneNumber;
	private String nurseToNurseVerbalReport; //(NO OR YES)
	private String nurseToNurseVerbalReportText;
	// END : Post Acute Care; Information

	// START : Hospital Physician Care Team Information
	private String primaryCarePhysician;
	private Long primaryCarePhysicianId;
	private String primaryCarePhysicianTelephoneNumber;
	private String specialist1;
	private Long specialist1Id;
	private String specialist1Speciality;
	private Long specialist1SpecialityId;
	private String specialist1TelephoneNumber;
	private String specialist2;
	private Long specialist2Id;
	private String specialist2Speciality;
	private Long specialist2SpecialityId;
	private String specialist2TelephoneNumber;
	private String hospitalCareTeamId;
	// END : Hospital Physician Care Team Information

	// START : Procedure and Key Findings
	private String listProcedures;
	private String keyFindings;
	// End : Procedure and Key Findings

	// START : Critical Transitional Cares and  Information : Pending Tests and Follow-up
	private String summarizeHighPriorityNeeds;
	private String pendingLabAndTestResults;
	private String recommendedFollowupTests;
	// End : Critical Transitional Cares and  Information : Pending Tests and Follow-up

	// Added in admission form new fields....
	@SuppressWarnings("serial")
	public static final Map<String,String> INSURENCEPLAN= Collections.unmodifiableMap(
			new HashMap<String,String>(){
				{
					put("RMFFS", "Regular Medicare Fee-for-Service");
					put("BCBSTX", "BCBSTX");
					put("AUHG", "Aetna UHG");
					put("CIGNA", "Cigna");
				}
			});
	@SuppressWarnings("serial")
	public static final Map<String,String> CARETYPES= Collections.unmodifiableMap(
			new HashMap<String,String>(){
				{
					put("PAC", "Post-Acute Care");
					put("CLTC", "Chronic Long Term Care");
				}
			});

	// START : Admission Attributes section
	private String inAdmissionKcivsTimeTaken; 		//KCIVS_TIME_TAKEN;   			inAdmissionKcivsTimeTaken
	private String inAdmissionKcivsPainRating;		//KCIVS_PAIN_RATING; 			inAdmissionKcivsPainRating
	private String inAdmissionKcivsNotApplicable; 	//KCIVS_NOT_APPLICABLE;			inAdmissionKcivsNotApplicable
	private String inAdmissionKcivsPainSite;		//KCIVS_PAIN_SITE;		 		inAdmissionKcivsPainSite
	private String inAdmissionKcivsTemp;			//KCIVS_TEMP		 			inAdmissionKcivsTemp
	private String inAdmissionKcivsBp;				//KCIVS_BP;						inAdmissionKcivsBp
	private String inAdmissionKcivsHr;				//KCIVS_HR;						inAdmissionKcivsHr
	private String inAdmissionKcivsRr;				//KCIVS_RR;						inAdmissionKcivsRr
	private String inAdmissionKcivs02sat;			//KCIVS_02SAT;					inAdmissionKcivs02sat
	private String inAdmissionKcivsWght;			//KCIVS_WGHT;					inAdmissionKcivsWght
	private String inAdmissionMentalStatus;			//KCIMS_ALRT;KCIMS_DFC;KCIMS_DCFC;KCIMS_N_ALRT;			inAdmissionMentalStatus[] radio Buttons
	private String inAdmissionKcidPdd;				//KCID_PDD;						inAdmissionKcidPdd
	private String inAdmissionKcidOmd;				//KCID_OMD;					inAdmissionKcidOmd
	private String inAdmissionKcidMhd;				//KCID_MHD;					inAdmissionKcidMhd
	private String inAdmissionhrcOrTiFalrsk;		//HRC_OR_TI_FALRSK;			inAdmissionhrcOrTiFalrsk
	private String inAdmissionHrcOrTiPrec;			//HRC_OR_TI_PREC;			inAdmissionHrcOrTiPrec
	private String inAdmissionHrcOrTiHf;			//HRC_OR_TI_HF;				inAdmissionHrcOrTiHf
	private String inAdmissionHrcOrTiHfNewdiag;			//HRC_OR_TI_HF_NEWDIAG;						inAdmissionHrcOrTiHfNewdiag
	private String inAdmissionHrcOrTiHfExthadmssion;	//HRC_OR_TI_HF_EXTHADMSSION;				inAdmissionHrcOrTiHfExthadmssion
	private String inAdmissionHrcOrTiHfExthadmssionLstdte;	//HRC_OR_TI_HF_EXTHADMSSION_LSTDTE;		inAdmissionHrcOrTiHfExthadmssionLstdte
	private String inAdmissionHrcOrTiHfEf;					//HRC_OR_TI_HF_EF;						inAdmissionHrcOrTiHfEf
	private String inAdmissionHrcOrTiHfEfp;					//HRC_OR_TI_HF_EFP;						inAdmissionHrcOrTiHfEfp
	private String inAdmissionHrcOrTiHfEfDw;				//HRC_OR_TI_HF_EF_DW;					inAdmissionHrcOrTiHfEfDw
	private String inAdmissionhrcOrTiAc;					//HRC_OR_TI_AC;							inAdmissionhrcOrTiAc;
	//	private String HRC_OR_TI_AC_REASON;// VARCHAR		not using
	private String[] inAdmissionhrcOrTiAcReasons;				//HRC_OR_TI_AC_R_AFIB;HRC_OR_TI_AC_R_DVT_OR_PE;HRC_OR_TI_AC_R_MECVAL;HRC_OR_TI_AC_R_POP;HRC_OR_TI_AC_R_LOWEF;HRC_OR_TI_AC_R_OTHR		inAdmissionhrcOrTiAcReasons[]	check boxes
	private String inAdmissionHrcOrTicROthrText;				//HRC_OR_TI_AC_R_OTHR_TEXT;			inAdmissionHrcOrTicROthrText
	private String inAdmissionHrcOrTiAcRDurtn;					//HRC_OR_TI_AC_R_DURTN;				inAdmissionHrcOrTiAcRDurtn
	private String inAdmissionGoalInr[];						//HRC_OR_TI_GI_1_5_2_5;HRC_OR_TI_GI_2_3;HRC_OR_TI_GI_OTHR				inAdmissionGoalInr[]  radio buttons
	private String inAdmissionHrcOrTiGiOthrText;				//HRC_OR_TI_GI_OTHR_TEXT;			inAdmissionHrcOrTiGiOthrText		
	private String inAdmissionHrcOrTiOp;						//HRC_OR_TI_OP;						inAdmissionHrcOrTiOp
	//	private String HRC_OR_TI_OP_INDCTN;// VARCHAR		not using
	private String[] inAdmissionHrcOrTiOpIndications;				//HRC_OR_TI_OP__I_IHP_AND_CBD;HRC_OR_TI_OP_SD;		inAdmissionHrcOrTiOpIndications[] radio buttons
	private String inAdmissionHrcOrTiOpIndicationsText;			//HRC_OR_TI_OP_SD_TEXT;								inAdmissionHrcOrTiOpIndicationsText
	private String inAdmissionhrcOrTiOab;						//HRC_OR_TI_OAB;									inAdmissionhrcOrTiOab				
	private String inAdmissionHrcOrTiOabIndctn;					//HRC_OR_TI_OAB_INDCTN;								inAdmissionHrcOrTiOabIndctn
	private String inAdmissionHrcOrTiOabTtcd;					//HRC_OR_TI_OAB_TTCD;								inAdmissionHrcOrTiOabTtcd
	private String inAdmissionHrcOrTiOabDs;						//HRC_OR_TI_OAB_DS;									inAdmissionHrcOrTiOabDs
	private String inAdmissionHrcOrTid;							//HRC_OR_TI_D;	 									inAdmissionHrcOrTid			
	private String inAdmissionHrcOrTiDMrgd;						//HRC_OR_TI_D_MRGD;									inAdmissionHrcOrTiDMrgd
	private String inAdmissionHrcOrTiDMrgt;						//HRC_OR_TI_D_MRGT;									inAdmissionHrcOrTiDMrgt
	private String inAdmissionMAndAMla;							//M_AND_A_MLA;										inAdmissionMAndAMla
	private String inAdmissionMAndAAllrgs;						//M_AND_A_ALLRGS_NK;M_AND_A_ALLRGS_Y;				inAdmissionMAndAAllrgs[] radio button
	private String inAdmissionMAndAAllrgsYSpcfy;				//M_AND_A_ALLRGS_Y_SPCFY;							inAdmissionMAndAAllrgsYSpcfy
	private String inAdmissionMAndAPm;							//M_AND_A_PM_N;M_AND_A_PM_Y;						inAdmissionMAndAPm[] radio button
	private String inAdmissionMAndAPmText;						//M_AND_A_PM_SPCFY;									inAdmissionMAndAPmText
	private String inAdmissionMAndAPmD;							//M_AND_A_PM_D;										inAdmissionMAndAPmD
	private String inAdmissionMAndAPmLd;						//M_AND_A_PM_LD;									inAdmissionMAndAPmLd
	//K. Nursing Care
	private String inAdmissionAmbulation;						//NCPSF_AMBLTN_INDPNDNT;NCPSF_AMBLTN_WA;NCPSF_AMBLTN_WAD;NCPSF_AMBLTN_NTA;		inAdmissionAmbulation[] radio buttons
	private String inAdmissionWeightBearing;					//NCPSF_WB_FULL;NCPSF_WB_PARTL_L_OR_R;NCPSF_WB_NONE;	inAdmissionWeightBearing[] radio buttons
	private String inAdmissionTransfer;							//NCPSF_TRNSFR_SLF;NCPSF_TRNSFR_OPA;NCPSF_TRNSFR_TPA;	inAdmissionTransfer[] radio buttons
	private String inAdmissionSensoryFunctionSight;				//NCPSF_SF_S_N;NCPSF_SF_S_IMPRD;NCPSF_SF_S_BLND;		inAdmissionSensoryFunctionSight[] radio buttons
	private String inAdmissionSensoryFunctionHearing;			//NCPSF_SF_H_N;NCPSF_SF_H_IMPRD;NCPSF_SF_H_DF			inAdmissionSensoryFunctionHearing[] radio buttons
	private String[] inAdmissionDevices;						//NCPSF_DEV_WC;NCPSF_DEV_WLKR;NCPSF_DEV_C;NCPSF_DEV_CRTCHS;NCPSF_DEV_PROS;NCPSF_DEV_GLSES;NCPSF_DEV_CNTCTS;NCPSF_DEV_D;NCPSF_DEV_HAL_OR_R	inAdmissionDevices[] check boxes
	private String[] inAdmissionContinence;						//NCPSF_C_CONTNENT;NCPSF_C_INCONTNENT;NCPSF_C_CATHTR;	inAdmissionContinence[] check boxes
	private String inAdmissionContinenceText;					//NCPSF_C_CATHTR_DI;									inAdmissionContinenceText				
	private String[] inAdmissionReasonforcatheter;				//NCPSF_C_RFC_R;NCPSF_C_RFC_SP;NCPSF_C_RFC_OTHR		inAdmissionReasonforcatheter[] check boxes
	private String inAdmissionReasonforcatheterText;			//NCPSF_C_RFC_OTHR_SPFY;							inAdmissionReasonforcatheterText
	private String[] inAdmissionNcpsfC;							//NCPSF_C_BOWLINCONTNENT;NCPSF_C_OSTMY;NCPSF_C_DOLBM	inAdmissionNcpsfC[]	check boxes
	private String inAdmissionNcpsfCText;						//NCPSF_C_DOLB;										inAdmissionNcpsfCText
	private String inAdmissionNcpsfNAndHD;						//NCPSF_N_AND_H_D;		inAdmissionNcpsfNAndHD					
	private String inAdmissionNcpsfNAndHC;						//NCPSF_N_AND_H_C;		inAdmissionNcpsfNAndHC
	private String inAdmissionNcpsfNAndHFwr;					//NCPSF_N_AND_H_FWR;	inAdmissionNcpsfNAndHFwr
	private String[] inAdmissionEatingInstructions;				//NCPSF_N_AND_H_EI_SLF;NCPSF_N_AND_H_EI_WA;NCPSF_N_AND_H_EI_DS;NCPSF_N_AND_H_EI_DS_ASTRIA;	inAdmissionEatingInstructions[]	check boxes	
	private String inAdmissionTubeFeeding;						//NCPSF_N_AND_H_TF_GT;NCPSF_N_AND_H_TF_JT;NCPSF_N_AND_H_TF_DI;	inAdmissionTubeFeeding[]	radio buttons
	//	private String inAdmissionNcpsfNAndHTfDi;					//NCPSF_N_AND_H_TF_DI;						inAdmissionNcpsfNAndHTfDi
	private String inAdmissionNcpsfNAndHTfDiText;				//NCPSF_N_AND_H_TF_DIT;						inAdmissionNcpsfNAndHTfDiText
	private String inAdmissionNcpsfNAndHTfDiFwbCc;				//NCPSF_N_AND_H_TF_DI_FWB_CC				inAdmissionNcpsfNAndHTfDiFwbCc
	private String inAdmissionNcpsfNAndHTfDiFwbHrs;				//NCPSF_N_AND_H_TF_DI_FWB_HRS;				inAdmissionNcpsfNAndHTfDiFwbHrs
	private String inAdmissionNcpsfNAndHTfTfp;					//NCPSF_N_AND_H_TF_TFP;						inAdmissionNcpsfNAndHTfTfp				
	private String inAdmissionNcpsfNAndHTfTfpt;					//NCPSF_N_AND_H_TF_TFP_T;					inAdmissionNcpsfNAndHTfTfpt					
	private String inAdmissionncpsfNAndHTfTfpRteCcPerH;			//NCPSF_N_AND_H_TF_TFP_RTE_CC_PER_H;		inAdmissionncpsfNAndHTfTfpRteCcPerH			
	private String inAdmissionncpsfNAndHTfTfpEveryHOrDay;		//NCPSF_N_AND_H_TF_TFP_EVERY_H_OR_DAY;		inAdmissionncpsfNAndHTfTfpEveryHOrDay
	private String[] inAdmissionNcpsfTtdPicc;					// NCPSF_TTD_PICC;NCPSF_TTD_PRTCTH;			inAdmissionNcpsfTtdPicc[] check boxes
	private String inAdmissionNcpsfTtdPiccText;					//NCPSF_TTD_PRTCTH_DI;						inAdmissionNcpsfTtdPiccText									
	private String[] inAdmissionCardiac;							//NCPSF_TTD_C_PM;NCPSF_TTD_C_ICD;NCPSF_TTD_C_OTHR		inAdmissionCardiac[]	check boxes
	private String inAdmissionCardiacText;						//NCPSF_TTD_C_OTHR_TEXT;								inAdmissionCardiacText
	private String[] inAdmissionRespiratory;					//NCPSF_TTD_R_CPAP;NCPSF_TTD_R_BIPAP;NCPSF_TTD_R_O2;NCPSF_TTD_R_PRN;NCPSF_TTD_R_CNTNOUS;NCPSF_TTD_R_SCTN;NCPSF_TTD_R_TS;	inAdmissionRespiratory[] check boxes
	private String inAdmissionRespiratoryO2Text;				//NCPSF_TTD_R_O2_TXT;									inAdmissionRespiratoryO2Text
	private String inAdmissionRespiratoryTsText;				//NCPSF_TTD_R_TS_TEXT;									inAdmissionRespiratoryTsText
	private String[] inAdmissionTherapies;						//NCPSF_THRPS_PT;NCPSF_THRPS_OT;NCPSF_THRPS_S;NCPSF_THRPS_R;NCPSF_THRPS_D;	inAdmissionTherapies[] check boxes
	private String inAdmissionNcpsfScNsb;						//NCPSF_SC_NSB;										inAdmissionNcpsfScNsb
	private String inAdmissionNcpsfScpU;						//NCPSF_SC_PU;										inAdmissionNcpsfScpU
	private String inAdmissionNcpsfScPuStage;					//NCPSF_SC_PU_STAGE;								inAdmissionNcpsfScPuStage						
	private String inAdmissionNcpsfScPuLocation;				//NCPSF_SC_PU_LOCATION;								inAdmissionNcpsfScPuLocation
	private String inAdmissionNcpsfScSpu;						//NCPSF_SC_SPU;										inAdmissionNcpsfScSpu						
	private String inAdmissionNcpsfScSpuStage;					//NCPSF_SC_SPU_STAGE;								inAdmissionNcpsfScSpuStage
	private String inAdmissionNcpsfScSpuLocation;				//NCPSF_SC_SPU_LOCATION;							inAdmissionNcpsfScSpuLocation
	private String inAdmissionNcpsfScOthr;						//NCPSF_SC_OTHR;									inAdmissionNcpsfScOthr
	private String inAdmissionNcpsfScOthrSpcfy;					//NCPSF_SC_OTHR_SPCFY;								inAdmissionNcpsfScOthrSpcfy
	// NCPSF_R&P_FALL BOOLEAN
	private String[] inAdmissionRisksandPrecautions;			//NCPSF_R_AND_P_FALL;NCPSF_R_AND_P_DELRUM;NCPSF_R_AND_P_AGTATN;NCPSF_R_AND_P_AGGRSN;NCPSF_R_AND_P_UEE;NCPSF_R_AND_P_ASPRTN;NCPSF_R_AND_P_OTHR	inAdmissionRisksandPrecautions[] check boxes
	private String inAdmissionRisksandPrecautionsText;			//NCPSF_R_AND_P_OTHR_TEXT;			inAdmissionRisksandPrecautionsText
	private String inAdmissionNcpsfRAndPOtherPrecautions;		//NCPSF_R_AND_P_OTHER_PRECAUTIONS;	inAdmissionNcpsfRAndPOtherPrecautions				
	private String[] inAdmissionInfectionRColonization;			//NCPSF_ICI_I_OR_C_MRSA;NCPSF_ICI_I_OR_C_VRE;NCPSF_ICI_I_OR_C_CDI;NCPSF_ICI_I_OR_C_ESBL;NCPSF_ICI_I_OR_C_NV;NCPSF_ICI_I_OR_C_F_OR_R			inAdmissionInfectionRColonization[] check boxes
	private String inAdmissionIsolationPrecautions;				//NCPSF_ICI_IP_NONE;NCPSF_ICI_IP_CNTCT;NCPSF_ICI_IP_CNTCTPLUS;NCPSF_ICI_IP_DRPLT;NCPSF_ICI_IP_ARBRNE;	inAdmissionIsolationPrecautions[] radio buttons
	private String inAdmissionNcpsfIciIInflunza;				//NCPSF_ICI_I_INFLUNZA;			inAdmissionNcpsfIciIInflunza
	private String inAdmissionNcpsfIciI;						//NCPSF_ICI_I_NO;NCPSF_ICI_I_Y;	inAdmissionNcpsfIciI
	private String inAdmissionNcpsfIciIText;					//NCPSF_ICI_I_YES_DTE;			inAdmissionNcpsfIciIText
	private String inAdmissionNcpsfIciIPneumococcal;			//NCPSF_ICI_I_P;				inAdmissionNcpsfIciIPneumococcal
	private String inAdmissionNcpsfIciIP;						//NCPSF_ICI_I_P_NO;NCPSF_ICI_I_P_YES;	inAdmissionNcpsfIciIP
	private String inAdmissionNcpsfIciIPText;					//NCPSF_ICI_I_P_YES_DTE;				inAdmissionNcpsfIciIPText
	private String modeOpen;
	private List<MedicalReconciliationDto> medicalReconciliationDto;
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getsSNumber() {
		return sSNumber;
	}
	public void setsSNumber(String sSNumber) {
		this.sSNumber = sSNumber;
	}
	public String getPcpWorkNumber() {
		return pcpWorkNumber;
	}
	public void setPcpWorkNumber(String pcpWorkNumber) {
		this.pcpWorkNumber = pcpWorkNumber;
	}
	public Long getPcpAtSite() {
		return pcpAtSite;
	}
	public void setPcpAtSite(Long pcpAtSite) {
		this.pcpAtSite = pcpAtSite;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public String getCanSpeakEnglish() {
		return canSpeakEnglish;
	}
	public void setCanSpeakEnglish(String canSpeakEnglish) {
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
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
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
	public void setSpecifyGoalsOfCareDiscussed(String specifyGoalsOfCareDiscussed) {
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
	public void setDishcargingRNTelephoneNumber(String dishcargingRNTelephoneNumber) {
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
	public void setDishcargingMDTelephoneNumber(String dishcargingMDTelephoneNumber) {
		this.dishcargingMDTelephoneNumber = dishcargingMDTelephoneNumber;
	}
	public String getHospitalAdmissionDate() {
		return hospitalAdmissionDate;
	}
	public void setHospitalAdmissionDate(String hospitalAdmissionDate) {
		this.hospitalAdmissionDate = hospitalAdmissionDate;
	}
	public String getFromHospitalDischargeDate() {
		return fromHospitalDischargeDate;
	}
	public void setFromHospitalDischargeDate(String fromHospitalDischargeDate) {
		this.fromHospitalDischargeDate = fromHospitalDischargeDate;
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
	public String getAdmittedByStaffType() {
		return admittedByStaffType;
	}
	public void setAdmittedByStaffType(String admittedByStaffType) {
		this.admittedByStaffType = admittedByStaffType;
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
	public String getPostAcuteCareTelephoneNumber() {
		return postAcuteCareTelephoneNumber;
	}
	public void setPostAcuteCareTelephoneNumber(String postAcuteCareTelephoneNumber) {
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
	public void setNurseToNurseVerbalReportText(String nurseToNurseVerbalReportText) {
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
	public Long getSpecialist1SpecialityId() {
		return specialist1SpecialityId;
	}
	public void setSpecialist1SpecialityId(Long specialist1SpecialityId) {
		this.specialist1SpecialityId = specialist1SpecialityId;
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
	public Long getSpecialist2SpecialityId() {
		return specialist2SpecialityId;
	}
	public void setSpecialist2SpecialityId(Long specialist2SpecialityId) {
		this.specialist2SpecialityId = specialist2SpecialityId;
	}
	public String getSpecialist2TelephoneNumber() {
		return specialist2TelephoneNumber;
	}
	public void setSpecialist2TelephoneNumber(String specialist2TelephoneNumber) {
		this.specialist2TelephoneNumber = specialist2TelephoneNumber;
	}
	public String getHospitalCareTeamId() {
		return hospitalCareTeamId;
	}
	public void setHospitalCareTeamId(String hospitalCareTeamId) {
		this.hospitalCareTeamId = hospitalCareTeamId;
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
	public String getInAdmissionKcivsTimeTaken() {
		return inAdmissionKcivsTimeTaken;
	}
	public void setInAdmissionKcivsTimeTaken(String inAdmissionKcivsTimeTaken) {
		this.inAdmissionKcivsTimeTaken = inAdmissionKcivsTimeTaken;
	}
	public String getInAdmissionKcivsPainRating() {
		return inAdmissionKcivsPainRating;
	}
	public void setInAdmissionKcivsPainRating(String inAdmissionKcivsPainRating) {
		this.inAdmissionKcivsPainRating = inAdmissionKcivsPainRating;
	}
	public String getInAdmissionKcivsNotApplicable() {
		return inAdmissionKcivsNotApplicable;
	}
	public void setInAdmissionKcivsNotApplicable(
			String inAdmissionKcivsNotApplicable) {
		this.inAdmissionKcivsNotApplicable = inAdmissionKcivsNotApplicable;
	}
	public String getInAdmissionKcivsPainSite() {
		return inAdmissionKcivsPainSite;
	}
	public void setInAdmissionKcivsPainSite(String inAdmissionKcivsPainSite) {
		this.inAdmissionKcivsPainSite = inAdmissionKcivsPainSite;
	}
	public String getInAdmissionKcivsTemp() {
		return inAdmissionKcivsTemp;
	}
	public void setInAdmissionKcivsTemp(String inAdmissionKcivsTemp) {
		this.inAdmissionKcivsTemp = inAdmissionKcivsTemp;
	}
	public String getInAdmissionKcivsBp() {
		return inAdmissionKcivsBp;
	}
	public void setInAdmissionKcivsBp(String inAdmissionKcivsBp) {
		this.inAdmissionKcivsBp = inAdmissionKcivsBp;
	}
	public String getInAdmissionKcivsHr() {
		return inAdmissionKcivsHr;
	}
	public void setInAdmissionKcivsHr(String inAdmissionKcivsHr) {
		this.inAdmissionKcivsHr = inAdmissionKcivsHr;
	}
	public String getInAdmissionKcivsRr() {
		return inAdmissionKcivsRr;
	}
	public void setInAdmissionKcivsRr(String inAdmissionKcivsRr) {
		this.inAdmissionKcivsRr = inAdmissionKcivsRr;
	}
	public String getInAdmissionKcivs02sat() {
		return inAdmissionKcivs02sat;
	}
	public void setInAdmissionKcivs02sat(String inAdmissionKcivs02sat) {
		this.inAdmissionKcivs02sat = inAdmissionKcivs02sat;
	}
	public String getInAdmissionKcivsWght() {
		return inAdmissionKcivsWght;
	}
	public void setInAdmissionKcivsWght(String inAdmissionKcivsWght) {
		this.inAdmissionKcivsWght = inAdmissionKcivsWght;
	}
	public String getInAdmissionMentalStatus() {
		return inAdmissionMentalStatus;
	}
	public void setInAdmissionMentalStatus(String inAdmissionMentalStatus) {
		this.inAdmissionMentalStatus = inAdmissionMentalStatus;
	}
	public String getInAdmissionKcidPdd() {
		return inAdmissionKcidPdd;
	}
	public void setInAdmissionKcidPdd(String inAdmissionKcidPdd) {
		this.inAdmissionKcidPdd = inAdmissionKcidPdd;
	}
	public String getInAdmissionKcidOmd() {
		return inAdmissionKcidOmd;
	}
	public void setInAdmissionKcidOmd(String inAdmissionKcidOmd) {
		this.inAdmissionKcidOmd = inAdmissionKcidOmd;
	}
	public String getInAdmissionKcidMhd() {
		return inAdmissionKcidMhd;
	}
	public void setInAdmissionKcidMhd(String inAdmissionKcidMhd) {
		this.inAdmissionKcidMhd = inAdmissionKcidMhd;
	}
	public String getInAdmissionhrcOrTiFalrsk() {
		return inAdmissionhrcOrTiFalrsk;
	}
	public void setInAdmissionhrcOrTiFalrsk(String inAdmissionhrcOrTiFalrsk) {
		this.inAdmissionhrcOrTiFalrsk = inAdmissionhrcOrTiFalrsk;
	}
	public String getInAdmissionHrcOrTiPrec() {
		return inAdmissionHrcOrTiPrec;
	}
	public void setInAdmissionHrcOrTiPrec(String inAdmissionHrcOrTiPrec) {
		this.inAdmissionHrcOrTiPrec = inAdmissionHrcOrTiPrec;
	}
	public String getInAdmissionHrcOrTiHf() {
		return inAdmissionHrcOrTiHf;
	}
	public void setInAdmissionHrcOrTiHf(String inAdmissionHrcOrTiHf) {
		this.inAdmissionHrcOrTiHf = inAdmissionHrcOrTiHf;
	}
	public String getInAdmissionHrcOrTiHfNewdiag() {
		return inAdmissionHrcOrTiHfNewdiag;
	}
	public void setInAdmissionHrcOrTiHfNewdiag(String inAdmissionHrcOrTiHfNewdiag) {
		this.inAdmissionHrcOrTiHfNewdiag = inAdmissionHrcOrTiHfNewdiag;
	}
	public String getInAdmissionHrcOrTiHfExthadmssion() {
		return inAdmissionHrcOrTiHfExthadmssion;
	}
	public void setInAdmissionHrcOrTiHfExthadmssion(
			String inAdmissionHrcOrTiHfExthadmssion) {
		this.inAdmissionHrcOrTiHfExthadmssion = inAdmissionHrcOrTiHfExthadmssion;
	}
	public String getInAdmissionHrcOrTiHfExthadmssionLstdte() {
		return inAdmissionHrcOrTiHfExthadmssionLstdte;
	}
	public void setInAdmissionHrcOrTiHfExthadmssionLstdte(
			String inAdmissionHrcOrTiHfExthadmssionLstdte) {
		this.inAdmissionHrcOrTiHfExthadmssionLstdte = inAdmissionHrcOrTiHfExthadmssionLstdte;
	}
	public String getInAdmissionHrcOrTiHfEf() {
		return inAdmissionHrcOrTiHfEf;
	}
	public void setInAdmissionHrcOrTiHfEf(String inAdmissionHrcOrTiHfEf) {
		this.inAdmissionHrcOrTiHfEf = inAdmissionHrcOrTiHfEf;
	}
	public String getInAdmissionHrcOrTiHfEfp() {
		return inAdmissionHrcOrTiHfEfp;
	}
	public void setInAdmissionHrcOrTiHfEfp(String inAdmissionHrcOrTiHfEfp) {
		this.inAdmissionHrcOrTiHfEfp = inAdmissionHrcOrTiHfEfp;
	}
	public String getInAdmissionHrcOrTiHfEfDw() {
		return inAdmissionHrcOrTiHfEfDw;
	}
	public void setInAdmissionHrcOrTiHfEfDw(String inAdmissionHrcOrTiHfEfDw) {
		this.inAdmissionHrcOrTiHfEfDw = inAdmissionHrcOrTiHfEfDw;
	}
	public String getInAdmissionhrcOrTiAc() {
		return inAdmissionhrcOrTiAc;
	}
	public void setInAdmissionhrcOrTiAc(String inAdmissionhrcOrTiAc) {
		this.inAdmissionhrcOrTiAc = inAdmissionhrcOrTiAc;
	}
	public String[] getInAdmissionhrcOrTiAcReasons() {
		return inAdmissionhrcOrTiAcReasons;
	}
	public void setInAdmissionhrcOrTiAcReasons(String[] inAdmissionhrcOrTiAcReasons) {
		this.inAdmissionhrcOrTiAcReasons = inAdmissionhrcOrTiAcReasons;
	}
	public String getInAdmissionHrcOrTicROthrText() {
		return inAdmissionHrcOrTicROthrText;
	}
	public void setInAdmissionHrcOrTicROthrText(String inAdmissionHrcOrTicROthrText) {
		this.inAdmissionHrcOrTicROthrText = inAdmissionHrcOrTicROthrText;
	}
	public String getInAdmissionHrcOrTiAcRDurtn() {
		return inAdmissionHrcOrTiAcRDurtn;
	}
	public void setInAdmissionHrcOrTiAcRDurtn(String inAdmissionHrcOrTiAcRDurtn) {
		this.inAdmissionHrcOrTiAcRDurtn = inAdmissionHrcOrTiAcRDurtn;
	}
	public String[] getInAdmissionGoalInr() {
		return inAdmissionGoalInr;
	}
	public void setInAdmissionGoalInr(String[] inAdmissionGoalInr) {
		this.inAdmissionGoalInr = inAdmissionGoalInr;
	}
	public String getInAdmissionHrcOrTiGiOthrText() {
		return inAdmissionHrcOrTiGiOthrText;
	}
	public void setInAdmissionHrcOrTiGiOthrText(String inAdmissionHrcOrTiGiOthrText) {
		this.inAdmissionHrcOrTiGiOthrText = inAdmissionHrcOrTiGiOthrText;
	}
	public String getInAdmissionHrcOrTiOp() {
		return inAdmissionHrcOrTiOp;
	}
	public void setInAdmissionHrcOrTiOp(String inAdmissionHrcOrTiOp) {
		this.inAdmissionHrcOrTiOp = inAdmissionHrcOrTiOp;
	}
	public String[] getInAdmissionHrcOrTiOpIndications() {
		return inAdmissionHrcOrTiOpIndications;
	}
	public void setInAdmissionHrcOrTiOpIndications(
			String[] inAdmissionHrcOrTiOpIndications) {
		this.inAdmissionHrcOrTiOpIndications = inAdmissionHrcOrTiOpIndications;
	}
	public String getInAdmissionHrcOrTiOpIndicationsText() {
		return inAdmissionHrcOrTiOpIndicationsText;
	}
	public void setInAdmissionHrcOrTiOpIndicationsText(
			String inAdmissionHrcOrTiOpIndicationsText) {
		this.inAdmissionHrcOrTiOpIndicationsText = inAdmissionHrcOrTiOpIndicationsText;
	}
	public String getInAdmissionhrcOrTiOab() {
		return inAdmissionhrcOrTiOab;
	}
	public void setInAdmissionhrcOrTiOab(String inAdmissionhrcOrTiOab) {
		this.inAdmissionhrcOrTiOab = inAdmissionhrcOrTiOab;
	}
	public String getInAdmissionHrcOrTiOabIndctn() {
		return inAdmissionHrcOrTiOabIndctn;
	}
	public void setInAdmissionHrcOrTiOabIndctn(String inAdmissionHrcOrTiOabIndctn) {
		this.inAdmissionHrcOrTiOabIndctn = inAdmissionHrcOrTiOabIndctn;
	}
	public String getInAdmissionHrcOrTiOabTtcd() {
		return inAdmissionHrcOrTiOabTtcd;
	}
	public void setInAdmissionHrcOrTiOabTtcd(String inAdmissionHrcOrTiOabTtcd) {
		this.inAdmissionHrcOrTiOabTtcd = inAdmissionHrcOrTiOabTtcd;
	}
	public String getInAdmissionHrcOrTiOabDs() {
		return inAdmissionHrcOrTiOabDs;
	}
	public void setInAdmissionHrcOrTiOabDs(String inAdmissionHrcOrTiOabDs) {
		this.inAdmissionHrcOrTiOabDs = inAdmissionHrcOrTiOabDs;
	}
	public String getInAdmissionHrcOrTid() {
		return inAdmissionHrcOrTid;
	}
	public void setInAdmissionHrcOrTid(String inAdmissionHrcOrTid) {
		this.inAdmissionHrcOrTid = inAdmissionHrcOrTid;
	}
	public String getInAdmissionHrcOrTiDMrgd() {
		return inAdmissionHrcOrTiDMrgd;
	}
	public void setInAdmissionHrcOrTiDMrgd(String inAdmissionHrcOrTiDMrgd) {
		this.inAdmissionHrcOrTiDMrgd = inAdmissionHrcOrTiDMrgd;
	}
	public String getInAdmissionHrcOrTiDMrgt() {
		return inAdmissionHrcOrTiDMrgt;
	}
	public void setInAdmissionHrcOrTiDMrgt(String inAdmissionHrcOrTiDMrgt) {
		this.inAdmissionHrcOrTiDMrgt = inAdmissionHrcOrTiDMrgt;
	}
	public String getInAdmissionMAndAMla() {
		return inAdmissionMAndAMla;
	}
	public void setInAdmissionMAndAMla(String inAdmissionMAndAMla) {
		this.inAdmissionMAndAMla = inAdmissionMAndAMla;
	}
	public String getInAdmissionMAndAAllrgs() {
		return inAdmissionMAndAAllrgs;
	}
	public void setInAdmissionMAndAAllrgs(String inAdmissionMAndAAllrgs) {
		this.inAdmissionMAndAAllrgs = inAdmissionMAndAAllrgs;
	}
	public String getInAdmissionMAndAAllrgsYSpcfy() {
		return inAdmissionMAndAAllrgsYSpcfy;
	}
	public void setInAdmissionMAndAAllrgsYSpcfy(String inAdmissionMAndAAllrgsYSpcfy) {
		this.inAdmissionMAndAAllrgsYSpcfy = inAdmissionMAndAAllrgsYSpcfy;
	}
	public String getInAdmissionMAndAPm() {
		return inAdmissionMAndAPm;
	}
	public void setInAdmissionMAndAPm(String inAdmissionMAndAPm) {
		this.inAdmissionMAndAPm = inAdmissionMAndAPm;
	}
	public String getInAdmissionMAndAPmText() {
		return inAdmissionMAndAPmText;
	}
	public void setInAdmissionMAndAPmText(String inAdmissionMAndAPmText) {
		this.inAdmissionMAndAPmText = inAdmissionMAndAPmText;
	}
	public String getInAdmissionMAndAPmD() {
		return inAdmissionMAndAPmD;
	}
	public void setInAdmissionMAndAPmD(String inAdmissionMAndAPmD) {
		this.inAdmissionMAndAPmD = inAdmissionMAndAPmD;
	}
	public String getInAdmissionMAndAPmLd() {
		return inAdmissionMAndAPmLd;
	}
	public void setInAdmissionMAndAPmLd(String inAdmissionMAndAPmLd) {
		this.inAdmissionMAndAPmLd = inAdmissionMAndAPmLd;
	}
	public String getInAdmissionAmbulation() {
		return inAdmissionAmbulation;
	}
	public void setInAdmissionAmbulation(String inAdmissionAmbulation) {
		this.inAdmissionAmbulation = inAdmissionAmbulation;
	}
	public String getInAdmissionWeightBearing() {
		return inAdmissionWeightBearing;
	}
	public void setInAdmissionWeightBearing(String inAdmissionWeightBearing) {
		this.inAdmissionWeightBearing = inAdmissionWeightBearing;
	}
	public String getInAdmissionTransfer() {
		return inAdmissionTransfer;
	}
	public void setInAdmissionTransfer(String inAdmissionTransfer) {
		this.inAdmissionTransfer = inAdmissionTransfer;
	}
	public String getInAdmissionSensoryFunctionSight() {
		return inAdmissionSensoryFunctionSight;
	}
	public void setInAdmissionSensoryFunctionSight(
			String inAdmissionSensoryFunctionSight) {
		this.inAdmissionSensoryFunctionSight = inAdmissionSensoryFunctionSight;
	}
	public String getInAdmissionSensoryFunctionHearing() {
		return inAdmissionSensoryFunctionHearing;
	}
	public void setInAdmissionSensoryFunctionHearing(
			String inAdmissionSensoryFunctionHearing) {
		this.inAdmissionSensoryFunctionHearing = inAdmissionSensoryFunctionHearing;
	}
	public String[] getInAdmissionDevices() {
		return inAdmissionDevices;
	}
	public void setInAdmissionDevices(String[] inAdmissionDevices) {
		this.inAdmissionDevices = inAdmissionDevices;
	}
	public String[] getInAdmissionContinence() {
		return inAdmissionContinence;
	}
	public void setInAdmissionContinence(String[] inAdmissionContinence) {
		this.inAdmissionContinence = inAdmissionContinence;
	}
	public String getInAdmissionContinenceText() {
		return inAdmissionContinenceText;
	}
	public void setInAdmissionContinenceText(String inAdmissionContinenceText) {
		this.inAdmissionContinenceText = inAdmissionContinenceText;
	}
	public String[] getInAdmissionReasonforcatheter() {
		return inAdmissionReasonforcatheter;
	}
	public void setInAdmissionReasonforcatheter(
			String[] inAdmissionReasonforcatheter) {
		this.inAdmissionReasonforcatheter = inAdmissionReasonforcatheter;
	}
	public String getInAdmissionReasonforcatheterText() {
		return inAdmissionReasonforcatheterText;
	}
	public void setInAdmissionReasonforcatheterText(
			String inAdmissionReasonforcatheterText) {
		this.inAdmissionReasonforcatheterText = inAdmissionReasonforcatheterText;
	}
	public String[] getInAdmissionNcpsfC() {
		return inAdmissionNcpsfC;
	}
	public void setInAdmissionNcpsfC(String[] inAdmissionNcpsfC) {
		this.inAdmissionNcpsfC = inAdmissionNcpsfC;
	}
	public String getInAdmissionNcpsfCText() {
		return inAdmissionNcpsfCText;
	}
	public void setInAdmissionNcpsfCText(String inAdmissionNcpsfCText) {
		this.inAdmissionNcpsfCText = inAdmissionNcpsfCText;
	}
	public String getInAdmissionNcpsfNAndHD() {
		return inAdmissionNcpsfNAndHD;
	}
	public void setInAdmissionNcpsfNAndHD(String inAdmissionNcpsfNAndHD) {
		this.inAdmissionNcpsfNAndHD = inAdmissionNcpsfNAndHD;
	}
	public String getInAdmissionNcpsfNAndHC() {
		return inAdmissionNcpsfNAndHC;
	}
	public void setInAdmissionNcpsfNAndHC(String inAdmissionNcpsfNAndHC) {
		this.inAdmissionNcpsfNAndHC = inAdmissionNcpsfNAndHC;
	}
	public String getInAdmissionNcpsfNAndHFwr() {
		return inAdmissionNcpsfNAndHFwr;
	}
	public void setInAdmissionNcpsfNAndHFwr(String inAdmissionNcpsfNAndHFwr) {
		this.inAdmissionNcpsfNAndHFwr = inAdmissionNcpsfNAndHFwr;
	}
	public String[] getInAdmissionEatingInstructions() {
		return inAdmissionEatingInstructions;
	}
	public void setInAdmissionEatingInstructions(
			String[] inAdmissionEatingInstructions) {
		this.inAdmissionEatingInstructions = inAdmissionEatingInstructions;
	}
	public String getInAdmissionTubeFeeding() {
		return inAdmissionTubeFeeding;
	}
	public void setInAdmissionTubeFeeding(String inAdmissionTubeFeeding) {
		this.inAdmissionTubeFeeding = inAdmissionTubeFeeding;
	}
	public String getInAdmissionNcpsfNAndHTfDiText() {
		return inAdmissionNcpsfNAndHTfDiText;
	}
	public void setInAdmissionNcpsfNAndHTfDiText(
			String inAdmissionNcpsfNAndHTfDiText) {
		this.inAdmissionNcpsfNAndHTfDiText = inAdmissionNcpsfNAndHTfDiText;
	}
	public String getInAdmissionNcpsfNAndHTfDiFwbCc() {
		return inAdmissionNcpsfNAndHTfDiFwbCc;
	}
	public void setInAdmissionNcpsfNAndHTfDiFwbCc(
			String inAdmissionNcpsfNAndHTfDiFwbCc) {
		this.inAdmissionNcpsfNAndHTfDiFwbCc = inAdmissionNcpsfNAndHTfDiFwbCc;
	}
	public String getInAdmissionNcpsfNAndHTfDiFwbHrs() {
		return inAdmissionNcpsfNAndHTfDiFwbHrs;
	}
	public void setInAdmissionNcpsfNAndHTfDiFwbHrs(
			String inAdmissionNcpsfNAndHTfDiFwbHrs) {
		this.inAdmissionNcpsfNAndHTfDiFwbHrs = inAdmissionNcpsfNAndHTfDiFwbHrs;
	}
	public String getInAdmissionNcpsfNAndHTfTfp() {
		return inAdmissionNcpsfNAndHTfTfp;
	}
	public void setInAdmissionNcpsfNAndHTfTfp(String inAdmissionNcpsfNAndHTfTfp) {
		this.inAdmissionNcpsfNAndHTfTfp = inAdmissionNcpsfNAndHTfTfp;
	}
	public String getInAdmissionNcpsfNAndHTfTfpt() {
		return inAdmissionNcpsfNAndHTfTfpt;
	}
	public void setInAdmissionNcpsfNAndHTfTfpt(String inAdmissionNcpsfNAndHTfTfpt) {
		this.inAdmissionNcpsfNAndHTfTfpt = inAdmissionNcpsfNAndHTfTfpt;
	}
	public String getInAdmissionncpsfNAndHTfTfpRteCcPerH() {
		return inAdmissionncpsfNAndHTfTfpRteCcPerH;
	}
	public void setInAdmissionncpsfNAndHTfTfpRteCcPerH(
			String inAdmissionncpsfNAndHTfTfpRteCcPerH) {
		this.inAdmissionncpsfNAndHTfTfpRteCcPerH = inAdmissionncpsfNAndHTfTfpRteCcPerH;
	}
	public String getInAdmissionncpsfNAndHTfTfpEveryHOrDay() {
		return inAdmissionncpsfNAndHTfTfpEveryHOrDay;
	}
	public void setInAdmissionncpsfNAndHTfTfpEveryHOrDay(
			String inAdmissionncpsfNAndHTfTfpEveryHOrDay) {
		this.inAdmissionncpsfNAndHTfTfpEveryHOrDay = inAdmissionncpsfNAndHTfTfpEveryHOrDay;
	}
	public String[] getInAdmissionNcpsfTtdPicc() {
		return inAdmissionNcpsfTtdPicc;
	}
	public void setInAdmissionNcpsfTtdPicc(String[] inAdmissionNcpsfTtdPicc) {
		this.inAdmissionNcpsfTtdPicc = inAdmissionNcpsfTtdPicc;
	}
	public String getInAdmissionNcpsfTtdPiccText() {
		return inAdmissionNcpsfTtdPiccText;
	}
	public void setInAdmissionNcpsfTtdPiccText(String inAdmissionNcpsfTtdPiccText) {
		this.inAdmissionNcpsfTtdPiccText = inAdmissionNcpsfTtdPiccText;
	}
	public String[] getInAdmissionCardiac() {
		return inAdmissionCardiac;
	}
	public void setInAdmissionCardiac(String[] inAdmissionCardiac) {
		this.inAdmissionCardiac = inAdmissionCardiac;
	}
	public String getInAdmissionCardiacText() {
		return inAdmissionCardiacText;
	}
	public void setInAdmissionCardiacText(String inAdmissionCardiacText) {
		this.inAdmissionCardiacText = inAdmissionCardiacText;
	}
	public String[] getInAdmissionRespiratory() {
		return inAdmissionRespiratory;
	}
	public void setInAdmissionRespiratory(String[] inAdmissionRespiratory) {
		this.inAdmissionRespiratory = inAdmissionRespiratory;
	}
	public String getInAdmissionRespiratoryO2Text() {
		return inAdmissionRespiratoryO2Text;
	}
	public void setInAdmissionRespiratoryO2Text(String inAdmissionRespiratoryO2Text) {
		this.inAdmissionRespiratoryO2Text = inAdmissionRespiratoryO2Text;
	}
	public String getInAdmissionRespiratoryTsText() {
		return inAdmissionRespiratoryTsText;
	}
	public void setInAdmissionRespiratoryTsText(String inAdmissionRespiratoryTsText) {
		this.inAdmissionRespiratoryTsText = inAdmissionRespiratoryTsText;
	}
	public String[] getInAdmissionTherapies() {
		return inAdmissionTherapies;
	}
	public void setInAdmissionTherapies(String[] inAdmissionTherapies) {
		this.inAdmissionTherapies = inAdmissionTherapies;
	}
	public String getInAdmissionNcpsfScNsb() {
		return inAdmissionNcpsfScNsb;
	}
	public void setInAdmissionNcpsfScNsb(String inAdmissionNcpsfScNsb) {
		this.inAdmissionNcpsfScNsb = inAdmissionNcpsfScNsb;
	}
	public String getInAdmissionNcpsfScpU() {
		return inAdmissionNcpsfScpU;
	}
	public void setInAdmissionNcpsfScpU(String inAdmissionNcpsfScpU) {
		this.inAdmissionNcpsfScpU = inAdmissionNcpsfScpU;
	}
	public String getInAdmissionNcpsfScPuStage() {
		return inAdmissionNcpsfScPuStage;
	}
	public void setInAdmissionNcpsfScPuStage(String inAdmissionNcpsfScPuStage) {
		this.inAdmissionNcpsfScPuStage = inAdmissionNcpsfScPuStage;
	}
	public String getInAdmissionNcpsfScPuLocation() {
		return inAdmissionNcpsfScPuLocation;
	}
	public void setInAdmissionNcpsfScPuLocation(String inAdmissionNcpsfScPuLocation) {
		this.inAdmissionNcpsfScPuLocation = inAdmissionNcpsfScPuLocation;
	}
	public String getInAdmissionNcpsfScSpu() {
		return inAdmissionNcpsfScSpu;
	}
	public void setInAdmissionNcpsfScSpu(String inAdmissionNcpsfScSpu) {
		this.inAdmissionNcpsfScSpu = inAdmissionNcpsfScSpu;
	}
	public String getInAdmissionNcpsfScSpuStage() {
		return inAdmissionNcpsfScSpuStage;
	}
	public void setInAdmissionNcpsfScSpuStage(String inAdmissionNcpsfScSpuStage) {
		this.inAdmissionNcpsfScSpuStage = inAdmissionNcpsfScSpuStage;
	}
	public String getInAdmissionNcpsfScSpuLocation() {
		return inAdmissionNcpsfScSpuLocation;
	}
	public void setInAdmissionNcpsfScSpuLocation(
			String inAdmissionNcpsfScSpuLocation) {
		this.inAdmissionNcpsfScSpuLocation = inAdmissionNcpsfScSpuLocation;
	}
	public String getInAdmissionNcpsfScOthr() {
		return inAdmissionNcpsfScOthr;
	}
	public void setInAdmissionNcpsfScOthr(String inAdmissionNcpsfScOthr) {
		this.inAdmissionNcpsfScOthr = inAdmissionNcpsfScOthr;
	}
	public String getInAdmissionNcpsfScOthrSpcfy() {
		return inAdmissionNcpsfScOthrSpcfy;
	}
	public void setInAdmissionNcpsfScOthrSpcfy(String inAdmissionNcpsfScOthrSpcfy) {
		this.inAdmissionNcpsfScOthrSpcfy = inAdmissionNcpsfScOthrSpcfy;
	}
	public String[] getInAdmissionRisksandPrecautions() {
		return inAdmissionRisksandPrecautions;
	}
	public void setInAdmissionRisksandPrecautions(
			String[] inAdmissionRisksandPrecautions) {
		this.inAdmissionRisksandPrecautions = inAdmissionRisksandPrecautions;
	}
	public String getInAdmissionRisksandPrecautionsText() {
		return inAdmissionRisksandPrecautionsText;
	}
	public void setInAdmissionRisksandPrecautionsText(
			String inAdmissionRisksandPrecautionsText) {
		this.inAdmissionRisksandPrecautionsText = inAdmissionRisksandPrecautionsText;
	}
	public String getInAdmissionNcpsfRAndPOtherPrecautions() {
		return inAdmissionNcpsfRAndPOtherPrecautions;
	}
	public void setInAdmissionNcpsfRAndPOtherPrecautions(
			String inAdmissionNcpsfRAndPOtherPrecautions) {
		this.inAdmissionNcpsfRAndPOtherPrecautions = inAdmissionNcpsfRAndPOtherPrecautions;
	}
	public String[] getInAdmissionInfectionRColonization() {
		return inAdmissionInfectionRColonization;
	}
	public void setInAdmissionInfectionRColonization(
			String[] inAdmissionInfectionRColonization) {
		this.inAdmissionInfectionRColonization = inAdmissionInfectionRColonization;
	}
	public String getInAdmissionIsolationPrecautions() {
		return inAdmissionIsolationPrecautions;
	}
	public void setInAdmissionIsolationPrecautions(
			String inAdmissionIsolationPrecautions) {
		this.inAdmissionIsolationPrecautions = inAdmissionIsolationPrecautions;
	}
	public String getInAdmissionNcpsfIciIInflunza() {
		return inAdmissionNcpsfIciIInflunza;
	}
	public void setInAdmissionNcpsfIciIInflunza(String inAdmissionNcpsfIciIInflunza) {
		this.inAdmissionNcpsfIciIInflunza = inAdmissionNcpsfIciIInflunza;
	}
	public String getInAdmissionNcpsfIciI() {
		return inAdmissionNcpsfIciI;
	}
	public void setInAdmissionNcpsfIciI(String inAdmissionNcpsfIciI) {
		this.inAdmissionNcpsfIciI = inAdmissionNcpsfIciI;
	}
	public String getInAdmissionNcpsfIciIText() {
		return inAdmissionNcpsfIciIText;
	}
	public void setInAdmissionNcpsfIciIText(String inAdmissionNcpsfIciIText) {
		this.inAdmissionNcpsfIciIText = inAdmissionNcpsfIciIText;
	}
	public String getInAdmissionNcpsfIciIPneumococcal() {
		return inAdmissionNcpsfIciIPneumococcal;
	}
	public void setInAdmissionNcpsfIciIPneumococcal(
			String inAdmissionNcpsfIciIPneumococcal) {
		this.inAdmissionNcpsfIciIPneumococcal = inAdmissionNcpsfIciIPneumococcal;
	}
	public String getInAdmissionNcpsfIciIP() {
		return inAdmissionNcpsfIciIP;
	}
	public void setInAdmissionNcpsfIciIP(String inAdmissionNcpsfIciIP) {
		this.inAdmissionNcpsfIciIP = inAdmissionNcpsfIciIP;
	}
	public String getInAdmissionNcpsfIciIPText() {
		return inAdmissionNcpsfIciIPText;
	}
	public void setInAdmissionNcpsfIciIPText(String inAdmissionNcpsfIciIPText) {
		this.inAdmissionNcpsfIciIPText = inAdmissionNcpsfIciIPText;
	}
	public List<MedicalReconciliationDto> getMedicalReconciliationDto() {
		return medicalReconciliationDto;
	}
	public void setMedicalReconciliationDto(
			List<MedicalReconciliationDto> medicalReconciliationDto) {
		this.medicalReconciliationDto = medicalReconciliationDto;
	}
	public String getModeOpen() {
		return modeOpen;
	}
	public void setModeOpen(String modeOpen) {
		this.modeOpen = modeOpen;
	}

}
