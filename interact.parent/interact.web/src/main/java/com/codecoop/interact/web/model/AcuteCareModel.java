package com.codecoop.interact.web.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;


@Component
public class AcuteCareModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// section 1
	private String inAcutefirstName;				//	Resident First Name
	private String inAcutelastName;				//	Resident Last Name
	private String inAcutelanguage;				//	English or Other
	private String inAcutelanguageText;			// 	Other language
	private String inAcuteresidentIs;				//  SNF/rehab or Long-term 
	private String inAcutedateAdmited;				//	Date Admitted (most recent)
	private String inAcutedob;						//	Date of Birth
	private String inAcutepdsforAdmission;			//	Primary diagnosis(es) for admission 
	
	@NotEmpty(message = "Outcome of Transfer should be specified")
	private String inAcuteOutComeTransfers;
	private String inAcuteOutComeTransfersTxt;
	
	
	// section 2
	private String inAcutesentTo;					//	Name of hospital 
	@NotEmpty(message = "Date of Transfer should be specified")
	private String inAcutedot;						//	Date of transfer 
	private String inAcutesendFrom;				//	Name of nursing home 
	private String inAcuteunit;					//	Unit
	
	// section 3
	private String inAcutecntPerson;				//	Contact Person
	private String inAcutecntPsnRelationShip;		//	Relative/ Health care proxy/Guardian /Other 
	private String inAcutecntPsnTel;				//	Contact person Telephone Number
	private String  inAcutenotifiedOfTransfer;		//	Yes/No
	private String inAcuteawarOfClinicalSituation; //	Yes/No
	
	// section 4 :- Who to Call at the Nursing Home to Get Questions Answered 
	private Long inAcutenhContactPerson;
	private String inAcutenhContactPName;			//	Nursing home contact person Name/Title
	private String inAcutenhContactPTel;			//	Nursing home contact person Tel
	
	// section 5 :- Primary Care Clinician in Nursing Home 
	private String inAcutepccInNh;					//	MD/NP/PA/
	private String inAcutepccInNhName;				//	Name
	private String inAcutepccInNhTel;				// Tel
	
	// section 6 :- Code Status 
	private String[] inAcutecodeStatus;				//	Full Code/DNR/DNI/DNH/Comfort Care Only/Uncertain
	private String openMode;
	//Codes are starting from here.....
	
	// section 7 :- Key Clinical Information
	private String inAcutekciReasonTransfer;			//	Reason(s) for transfer 
	private String inAcutekciPrimaryReasonTransfer;	//	YES/NO
	private String inAcutekciTests;					//	Tests	
	private String[] inAcuterelevantDiagnoses;			//	Relevant diagnoses  
	private String inAcutekciRdOther;					//	Other
	private String inAcutekciRdOtherTxt;				//	Other
	private String inAcutekciVsBp;						//	BP
	private String inAcutekciVsHr;						//	HR
	private String inAcutekciVsRr;						//	RR
	private String inAcutekciVsTemp;					//	Temp
	private String inAcutekciVsO2sat;					//	O2Sat
	private String inAcutekciVsTt;						//	TimeTaken(am/pm)
	private String inAcutekciMrpl;						//	Most Recent pain level
	private String inAcutekciMrplNa;					//	N/A
	private String inAcutekciMrplPl;					//	Pain Location
	private String inAcutekciMrpm;						//	Most Recent pain med
	private String inAcutekciMrpmDg;					//	Date given
	private String inAcutekciMrpmTime;					//	Time(am/pm) 
	
	// section 8,9,10 :- Usual Mental Status, Usual Functional Status, Additional Clinical Information
//	private String umsAlertOriented;			//	Alert, oriented, follows instructions
	private String[] inAcuteumsAlert;					//	Alert, oriented, follows instructions
//	private String umsAlertDisoriented;			//	Alert, disoriented,but can follow simple instructions
//	private String umsAlertDisorientedNotFollow;	//	Alert, disoriented, but cannot follow simple instructions
//	private String umsNotAlert;				//	Not Alert
	private String[] inAcuteufsAmbulates;					//	Ambulates independently
//	private String ufsAad;					//	Ambulates with assistive device
//	private String ufsAha;					//	Ambulates only with human assistance
//	private String ufsNa;					//	Not ambulatory
	private String inAcuteaciIncluded;				//	SBAR Acute Change in Condition Note included
//	private String aciOcni;					//	Other clinical notes included
	private String inAcuteaciDtv;					// Date of last tetanus vaccination (if known)
	
	// section 11 :- Devices and Treatments, Isolation Precautions, Allergies
	private String inAcutedtO2;					//	O2 at
	private String inAcutedtO2Lm;					//	O2 atL/M by
	private String inAcutedtOlNc;					//	Nasal Canula
	private String inAcutedtOlM;					//	Mask
	private String inAcutedtOlMCN;					//	Chronic
//	private String dtOlMN;					//	New
	private String inAcutedtNt;					//	Nebulizer therapy
	private String inAcutedtNtCN;					//	Chronic
//	private String dtNtN;					//	New
	private String inAcutedtCpap;					//	CPAP
	private String inAcutedtBipap;					//	BIPAP
	private String inAcutedtP;						// Pacemaker
	private String inAcutedtIv;					// IV
	private String inAcutedtPiccl;					// PICCLine
	private String inAcutedtBc;					//	Bladder(Foley)Catheter;
	private String inAcutedtBcCN;					// Chronic
//	private String dtBcN;					// New
	private String inAcutedtId;					// Internal Defibrillator
	private String inAcutedtEf;					//	Enternal Feeding
	private String inAcutedtTpn;					//	TPN
	private String inAcutedtOther;					//	Other
	private String inAcutedtOtherTxt;				//	Other
	
	// section 12 :- Isolation Precautions 
	private String inAcuteipMrsa;					// MRSA
	private String inAcuteipVre;					// VRE
	private String inAcuteipSite;					//	Site
	private String inAcuteipDfl;					// C.difficile
	private String inAcuteipNv;					// Norovirus
	private String inAcuteipRvf;					// Respiratory virus or flu
	private String inAcuteipO;						// Other
	private String inAcuteipOTxt;					// Other
	private String inAcutealrg;					// Allergies 67
	
	// section 13 :- Risk Alerts 
	private String[] inAcuteriskAlerts;
//	private String raAc;					//	Anticoagulation
//	private String raF;						// Falls
//	private String raPu;					// Pressure ulcer(s)
//	private String raA;						// Aspiration
//	private String raSz;					// Seizures
//	private String raHso;					// Harm to self or others
//	private String raRs;					// Restraints
//	private String raLnwb;					// Limited/non-weighted bearing
//	private String raLnwbL;					// Left
//	private String raLnwbRl;				// Right
//	private String raMae;					// May attempt to exit
//	private String raSps;					// Swallowing Precautions
//	private String raNmc;					// Needs meds crushed
//	private String raOt;					// Other
	private String inAcuteraOtTxt;					// Other 82
	
	// section 14 :- Personal belongs sent with resident
	private String[] inAcutepbswResident;					// Eyeglasses
//	private String pbsrHa;					// Hearing Aid
//	private String pbsrDa;					// Dental Appliance
//	private String pbsrJ;					// Jewelry
//	private String pbsrOt;					// Other
	private String inAcutepbsrOtTxt;				// Other
	
	// seciton 15 :- Nursing Home Would be able to Accept Resident Back Under the Following Conditions
	private String[] inAcutenharufconditions;				// ER determines diagnoses,and treatment can be done in NH
//	private String nharufcVs;				// VS stabilized and follow up plan can be done in NH 
//	private String nharufcOt;				// Other
	private String inAcutenharufcOtTxt;			// Other
	
	// section 16 :-  Additional Transfer Information  on a Second Page
	private String inAcuteatisPage;				// Included
//	private String atispWsl;			// Will be sent later 94

	// section 23 :- Primary Goals of Care at Time of Transfer 
	private String[] inAcutepgctOfTransfer;			// Rehabilitation and/or Medical Therapy with intent of returning home
//	private String pgcttCltc;			// Chronic long-term care
//	private String pgcttP;				// Palliative or end-of-life-care
//	private String pgcttRhc;			// Receiving hospice care
//	private String pgcttO;				// Other
	private String inAcutepgcttOtTxt;			// Other
	
	// section 24 :- 
		private String inAcutetreatMentsNdFr;					// Treatments and Frequency(dialysis,chemotherapy,transfusions,radiation,TPN
		
		// section 25 :- Diet
		private String inAcutednaf;				// Needs assistance with feeding? Yes/NO
		private String inAcutedts;				// Trouble swallowing? YES/NO
		private String inAcutedsc;				// Special consistency YES/NO
		private String inAcutedscTxt;				//  (thickened liquids, crush meds, etc…)? 
		private String inAcutedetf;				// Enteral tube feeding? YES/NO	
		private String inAcutedetfFrTxt;			// Formula/rate
		
		// section 26 :- Skin/Wound Care
		private String inAcuteswCarePU;				// Pressure Ulcers (stage, location,  appearance, treatments) 
		
		// section 27 :- Immunizations 
		private String inAcuteiinfluenza;					// Influenza
		private String inAcuteiinfDate;				// Date
		private String inAcuteipneumococcal;		// Pneumococcal
		private String inAcuteipnDate;				// Date 116
		
		// section 28 :- Physical Rehabilitation Therapy 
		private String inAcuteprhRrtrh; 			// Resident is receiving therapy with goal of returning home? No/Yes 
		private String inAcuteprhPTherapy;			// Physical Therapy
		private String inAcuteprhPtIt;				// Interventions
		private String inAcuteprhOt;				// Occupational Therapy
		private String inAcuteprhOtIt;				// Interventions
		private String inAcuteprhSt;				// Speech Therapy
		private String inAcuteprhStIt;				// Interventions
		
		// section 29 :- ADLs  Mark   I = Independent   D = Dependent   A = Needs Assistance
		private String inAcuteadlsB;				// Bathing
		private String inAcuteadlsD;				// Dressing
		private String inAcuteadlsT;				// Transfers
		private String inAcuteadlsTl;				// Toileting
		private String inAcuteadlsE;				// Eating
		private String inAcuteadlsCai;				// Can ambulate independently
		private String inAcuteadlsCaiTxt;			// Can ambulate independently
		private String inAcuteadlsAd;				// Assistive device
		private String inAcuteadlsAdTxt;			// Assistive device
		private String inAcuteadlsHaa;				// Need human assistance to ambulate
		private String inAcuteadlsHaaTxt;			// Need human assistance to ambulate 138
		
		// section 30 :- Impairments – General
		private String[] inAcuteigeneral;				// Cognitive
//		private String igS;				// Speech
//		private String igH;				// Hearing
//		private String igV;				// Vision
//		private String igSt;			// Sensation
//		private String igOt;			// Other
		private String inAcuteigOtTxt;			// Other
		
		// section 31 :- Impairments – Musculoskeletal 
		private String[] inAcuteimusculoskeletal;				// Amputation
//		private String imPl;				// Paralysis
//		private String imCts;				// Contractures
//		private String imOt;				// Other
		private String inAcuteimOtTxt;				// Other
			
		// section 32 :- Continence
		private String inAcutectBlOrBr;		// Bowel
		//private String stayInFacility;
//		private String ctBr;				// Bladder
		private String inAcutectDlbm;				// Date of last BM 
		private Boolean inAcuteSnd2HsptlFlag;
		private Boolean stayInAcutecareFlag;
		private Long inAcutePatinetId;
		private Long inAcutePatientEpisodeId;
		private String patientStatusInAcuteCare;
		
		public String getInAcutefirstName() {
			return inAcutefirstName;
		}
		public void setInAcutefirstName(String inAcutefirstName) {
			this.inAcutefirstName = inAcutefirstName;
		}
		public String getInAcutelastName() {
			return inAcutelastName;
		}
		public void setInAcutelastName(String inAcutelastName) {
			this.inAcutelastName = inAcutelastName;
		}
		public String getInAcutelanguage() {
			return inAcutelanguage;
		}
		public void setInAcutelanguage(String inAcutelanguage) {
			this.inAcutelanguage = inAcutelanguage;
		}
		public String getInAcutelanguageText() {
			return inAcutelanguageText;
		}
		public void setInAcutelanguageText(String inAcutelanguageText) {
			this.inAcutelanguageText = inAcutelanguageText;
		}
		public String getInAcuteresidentIs() {
			return inAcuteresidentIs;
		}
		public void setInAcuteresidentIs(String inAcuteresidentIs) {
			this.inAcuteresidentIs = inAcuteresidentIs;
		}
		public String getInAcutedateAdmited() {
			return inAcutedateAdmited;
		}
		public void setInAcutedateAdmited(String inAcutedateAdmited) {
			this.inAcutedateAdmited = inAcutedateAdmited;
		}
		public String getInAcutedob() {
			return inAcutedob;
		}
		public void setInAcutedob(String inAcutedob) {
			this.inAcutedob = inAcutedob;
		}
		public String getInAcutepdsforAdmission() {
			return inAcutepdsforAdmission;
		}
		public void setInAcutepdsforAdmission(String inAcutepdsforAdmission) {
			this.inAcutepdsforAdmission = inAcutepdsforAdmission;
		}
		public String getInAcuteOutComeTransfers() {
			return inAcuteOutComeTransfers;
		}
		public void setInAcuteOutComeTransfers(String inAcuteOutComeTransfers) {
			this.inAcuteOutComeTransfers = inAcuteOutComeTransfers;
		}
		public String getInAcuteOutComeTransfersTxt() {
			return inAcuteOutComeTransfersTxt;
		}
		public void setInAcuteOutComeTransfersTxt(String inAcuteOutComeTransfersTxt) {
			this.inAcuteOutComeTransfersTxt = inAcuteOutComeTransfersTxt;
		}
		public String getInAcutesentTo() {
			return inAcutesentTo;
		}
		public void setInAcutesentTo(String inAcutesentTo) {
			this.inAcutesentTo = inAcutesentTo;
		}
		public String getInAcutedot() {
			return inAcutedot;
		}
		public void setInAcutedot(String inAcutedot) {
			this.inAcutedot = inAcutedot;
		}
		public String getInAcutesendFrom() {
			return inAcutesendFrom;
		}
		public void setInAcutesendFrom(String inAcutesendFrom) {
			this.inAcutesendFrom = inAcutesendFrom;
		}
		public String getInAcuteunit() {
			return inAcuteunit;
		}
		public void setInAcuteunit(String inAcuteunit) {
			this.inAcuteunit = inAcuteunit;
		}
		public String getInAcutecntPerson() {
			return inAcutecntPerson;
		}
		public void setInAcutecntPerson(String inAcutecntPerson) {
			this.inAcutecntPerson = inAcutecntPerson;
		}
		public String getInAcutecntPsnRelationShip() {
			return inAcutecntPsnRelationShip;
		}
		public void setInAcutecntPsnRelationShip(String inAcutecntPsnRelationShip) {
			this.inAcutecntPsnRelationShip = inAcutecntPsnRelationShip;
		}
		public String getInAcutecntPsnTel() {
			return inAcutecntPsnTel;
		}
		public void setInAcutecntPsnTel(String inAcutecntPsnTel) {
			this.inAcutecntPsnTel = inAcutecntPsnTel;
		}
		public String getInAcutenotifiedOfTransfer() {
			return inAcutenotifiedOfTransfer;
		}
		public void setInAcutenotifiedOfTransfer(String inAcutenotifiedOfTransfer) {
			this.inAcutenotifiedOfTransfer = inAcutenotifiedOfTransfer;
		}
		public String getInAcuteawarOfClinicalSituation() {
			return inAcuteawarOfClinicalSituation;
		}
		public void setInAcuteawarOfClinicalSituation(
				String inAcuteawarOfClinicalSituation) {
			this.inAcuteawarOfClinicalSituation = inAcuteawarOfClinicalSituation;
		}
		public String getInAcutenhContactPName() {
			return inAcutenhContactPName;
		}
		public void setInAcutenhContactPName(String inAcutenhContactPName) {
			this.inAcutenhContactPName = inAcutenhContactPName;
		}
		public Long getInAcutenhContactPerson() {
			return inAcutenhContactPerson;
		}
		public void setInAcutenhContactPerson(Long inAcutenhContactPerson) {
			this.inAcutenhContactPerson = inAcutenhContactPerson;
		}
		public String getInAcutenhContactPTel() {
			return inAcutenhContactPTel;
		}
		public void setInAcutenhContactPTel(String inAcutenhContactPTel) {
			this.inAcutenhContactPTel = inAcutenhContactPTel;
		}
		public String getInAcutepccInNh() {
			return inAcutepccInNh;
		}
		public void setInAcutepccInNh(String inAcutepccInNh) {
			this.inAcutepccInNh = inAcutepccInNh;
		}
		public String getInAcutepccInNhName() {
			return inAcutepccInNhName;
		}
		public void setInAcutepccInNhName(String inAcutepccInNhName) {
			this.inAcutepccInNhName = inAcutepccInNhName;
		}
		public String getInAcutepccInNhTel() {
			return inAcutepccInNhTel;
		}
		public void setInAcutepccInNhTel(String inAcutepccInNhTel) {
			this.inAcutepccInNhTel = inAcutepccInNhTel;
		}
		public String[] getInAcutecodeStatus() {
			return inAcutecodeStatus;
		}
		public void setInAcutecodeStatus(String[] inAcutecodeStatus) {
			this.inAcutecodeStatus = inAcutecodeStatus;
		}
		public String getOpenMode() {
			return openMode;
		}
		public void setOpenMode(String openMode) {
			this.openMode = openMode;
		}
		public String getInAcutekciReasonTransfer() {
			return inAcutekciReasonTransfer;
		}
		public void setInAcutekciReasonTransfer(String inAcutekciReasonTransfer) {
			this.inAcutekciReasonTransfer = inAcutekciReasonTransfer;
		}
		public String getInAcutekciPrimaryReasonTransfer() {
			return inAcutekciPrimaryReasonTransfer;
		}
		public void setInAcutekciPrimaryReasonTransfer(
				String inAcutekciPrimaryReasonTransfer) {
			this.inAcutekciPrimaryReasonTransfer = inAcutekciPrimaryReasonTransfer;
		}
		public String getInAcutekciTests() {
			return inAcutekciTests;
		}
		public void setInAcutekciTests(String inAcutekciTests) {
			this.inAcutekciTests = inAcutekciTests;
		}
		public String[] getInAcuterelevantDiagnoses() {
			return inAcuterelevantDiagnoses;
		}
		public void setInAcuterelevantDiagnoses(String[] inAcuterelevantDiagnoses) {
			this.inAcuterelevantDiagnoses = inAcuterelevantDiagnoses;
		}
		public String getInAcutekciRdOther() {
			return inAcutekciRdOther;
		}
		public void setInAcutekciRdOther(String inAcutekciRdOther) {
			this.inAcutekciRdOther = inAcutekciRdOther;
		}
		public String getInAcutekciRdOtherTxt() {
			return inAcutekciRdOtherTxt;
		}
		public void setInAcutekciRdOtherTxt(String inAcutekciRdOtherTxt) {
			this.inAcutekciRdOtherTxt = inAcutekciRdOtherTxt;
		}
		public String getInAcutekciVsBp() {
			return inAcutekciVsBp;
		}
		public void setInAcutekciVsBp(String inAcutekciVsBp) {
			this.inAcutekciVsBp = inAcutekciVsBp;
		}
		public String getInAcutekciVsHr() {
			return inAcutekciVsHr;
		}
		public void setInAcutekciVsHr(String inAcutekciVsHr) {
			this.inAcutekciVsHr = inAcutekciVsHr;
		}
		public String getInAcutekciVsRr() {
			return inAcutekciVsRr;
		}
		public void setInAcutekciVsRr(String inAcutekciVsRr) {
			this.inAcutekciVsRr = inAcutekciVsRr;
		}
		public String getInAcutekciVsTemp() {
			return inAcutekciVsTemp;
		}
		public void setInAcutekciVsTemp(String inAcutekciVsTemp) {
			this.inAcutekciVsTemp = inAcutekciVsTemp;
		}
		public String getInAcutekciVsO2sat() {
			return inAcutekciVsO2sat;
		}
		public void setInAcutekciVsO2sat(String inAcutekciVsO2sat) {
			this.inAcutekciVsO2sat = inAcutekciVsO2sat;
		}
		public String getInAcutekciVsTt() {
			return inAcutekciVsTt;
		}
		public void setInAcutekciVsTt(String inAcutekciVsTt) {
			this.inAcutekciVsTt = inAcutekciVsTt;
		}
		public String getInAcutekciMrpl() {
			return inAcutekciMrpl;
		}
		public void setInAcutekciMrpl(String inAcutekciMrpl) {
			this.inAcutekciMrpl = inAcutekciMrpl;
		}
		public String getInAcutekciMrplNa() {
			return inAcutekciMrplNa;
		}
		public void setInAcutekciMrplNa(String inAcutekciMrplNa) {
			this.inAcutekciMrplNa = inAcutekciMrplNa;
		}
		public String getInAcutekciMrplPl() {
			return inAcutekciMrplPl;
		}
		public void setInAcutekciMrplPl(String inAcutekciMrplPl) {
			this.inAcutekciMrplPl = inAcutekciMrplPl;
		}
		public String getInAcutekciMrpm() {
			return inAcutekciMrpm;
		}
		public void setInAcutekciMrpm(String inAcutekciMrpm) {
			this.inAcutekciMrpm = inAcutekciMrpm;
		}
		public String getInAcutekciMrpmDg() {
			return inAcutekciMrpmDg;
		}
		public void setInAcutekciMrpmDg(String inAcutekciMrpmDg) {
			this.inAcutekciMrpmDg = inAcutekciMrpmDg;
		}
		public String getInAcutekciMrpmTime() {
			return inAcutekciMrpmTime;
		}
		public void setInAcutekciMrpmTime(String inAcutekciMrpmTime) {
			this.inAcutekciMrpmTime = inAcutekciMrpmTime;
		}
		public String[] getInAcuteumsAlert() {
			return inAcuteumsAlert;
		}
		public void setInAcuteumsAlert(String[] inAcuteumsAlert) {
			this.inAcuteumsAlert = inAcuteumsAlert;
		}
		public String[] getInAcuteufsAmbulates() {
			return inAcuteufsAmbulates;
		}
		public void setInAcuteufsAmbulates(String[] inAcuteufsAmbulates) {
			this.inAcuteufsAmbulates = inAcuteufsAmbulates;
		}
		public String getInAcuteaciIncluded() {
			return inAcuteaciIncluded;
		}
		public void setInAcuteaciIncluded(String inAcuteaciIncluded) {
			this.inAcuteaciIncluded = inAcuteaciIncluded;
		}
		public String getInAcuteaciDtv() {
			return inAcuteaciDtv;
		}
		public void setInAcuteaciDtv(String inAcuteaciDtv) {
			this.inAcuteaciDtv = inAcuteaciDtv;
		}
		public String getInAcutedtO2() {
			return inAcutedtO2;
		}
		public void setInAcutedtO2(String inAcutedtO2) {
			this.inAcutedtO2 = inAcutedtO2;
		}
		public String getInAcutedtO2Lm() {
			return inAcutedtO2Lm;
		}
		public void setInAcutedtO2Lm(String inAcutedtO2Lm) {
			this.inAcutedtO2Lm = inAcutedtO2Lm;
		}
		public String getInAcutedtOlNc() {
			return inAcutedtOlNc;
		}
		public void setInAcutedtOlNc(String inAcutedtOlNc) {
			this.inAcutedtOlNc = inAcutedtOlNc;
		}
		public String getInAcutedtOlM() {
			return inAcutedtOlM;
		}
		public void setInAcutedtOlM(String inAcutedtOlM) {
			this.inAcutedtOlM = inAcutedtOlM;
		}
		public String getInAcutedtOlMCN() {
			return inAcutedtOlMCN;
		}
		public void setInAcutedtOlMCN(String inAcutedtOlMCN) {
			this.inAcutedtOlMCN = inAcutedtOlMCN;
		}
		public String getInAcutedtNt() {
			return inAcutedtNt;
		}
		public void setInAcutedtNt(String inAcutedtNt) {
			this.inAcutedtNt = inAcutedtNt;
		}
		public String getInAcutedtNtCN() {
			return inAcutedtNtCN;
		}
		public void setInAcutedtNtCN(String inAcutedtNtCN) {
			this.inAcutedtNtCN = inAcutedtNtCN;
		}
		public String getInAcutedtCpap() {
			return inAcutedtCpap;
		}
		public void setInAcutedtCpap(String inAcutedtCpap) {
			this.inAcutedtCpap = inAcutedtCpap;
		}
		public String getInAcutedtBipap() {
			return inAcutedtBipap;
		}
		public void setInAcutedtBipap(String inAcutedtBipap) {
			this.inAcutedtBipap = inAcutedtBipap;
		}
		public String getInAcutedtP() {
			return inAcutedtP;
		}
		public void setInAcutedtP(String inAcutedtP) {
			this.inAcutedtP = inAcutedtP;
		}
		public String getInAcutedtIv() {
			return inAcutedtIv;
		}
		public void setInAcutedtIv(String inAcutedtIv) {
			this.inAcutedtIv = inAcutedtIv;
		}
		public String getInAcutedtPiccl() {
			return inAcutedtPiccl;
		}
		public void setInAcutedtPiccl(String inAcutedtPiccl) {
			this.inAcutedtPiccl = inAcutedtPiccl;
		}
		public String getInAcutedtBc() {
			return inAcutedtBc;
		}
		public void setInAcutedtBc(String inAcutedtBc) {
			this.inAcutedtBc = inAcutedtBc;
		}
		public String getInAcutedtBcCN() {
			return inAcutedtBcCN;
		}
		public void setInAcutedtBcCN(String inAcutedtBcCN) {
			this.inAcutedtBcCN = inAcutedtBcCN;
		}
		public String getInAcutedtId() {
			return inAcutedtId;
		}
		public void setInAcutedtId(String inAcutedtId) {
			this.inAcutedtId = inAcutedtId;
		}
		public String getInAcutedtEf() {
			return inAcutedtEf;
		}
		public void setInAcutedtEf(String inAcutedtEf) {
			this.inAcutedtEf = inAcutedtEf;
		}
		public String getInAcutedtTpn() {
			return inAcutedtTpn;
		}
		public void setInAcutedtTpn(String inAcutedtTpn) {
			this.inAcutedtTpn = inAcutedtTpn;
		}
		public String getInAcutedtOther() {
			return inAcutedtOther;
		}
		public void setInAcutedtOther(String inAcutedtOther) {
			this.inAcutedtOther = inAcutedtOther;
		}
		public String getInAcutedtOtherTxt() {
			return inAcutedtOtherTxt;
		}
		public void setInAcutedtOtherTxt(String inAcutedtOtherTxt) {
			this.inAcutedtOtherTxt = inAcutedtOtherTxt;
		}
		public String getInAcuteipMrsa() {
			return inAcuteipMrsa;
		}
		public void setInAcuteipMrsa(String inAcuteipMrsa) {
			this.inAcuteipMrsa = inAcuteipMrsa;
		}
		public String getInAcuteipVre() {
			return inAcuteipVre;
		}
		public void setInAcuteipVre(String inAcuteipVre) {
			this.inAcuteipVre = inAcuteipVre;
		}
		public String getInAcuteipSite() {
			return inAcuteipSite;
		}
		public void setInAcuteipSite(String inAcuteipSite) {
			this.inAcuteipSite = inAcuteipSite;
		}
		public String getInAcuteipDfl() {
			return inAcuteipDfl;
		}
		public void setInAcuteipDfl(String inAcuteipDfl) {
			this.inAcuteipDfl = inAcuteipDfl;
		}
		public String getInAcuteipNv() {
			return inAcuteipNv;
		}
		public void setInAcuteipNv(String inAcuteipNv) {
			this.inAcuteipNv = inAcuteipNv;
		}
		public String getInAcuteipRvf() {
			return inAcuteipRvf;
		}
		public void setInAcuteipRvf(String inAcuteipRvf) {
			this.inAcuteipRvf = inAcuteipRvf;
		}
		public String getInAcuteipO() {
			return inAcuteipO;
		}
		public void setInAcuteipO(String inAcuteipO) {
			this.inAcuteipO = inAcuteipO;
		}
		public String getInAcuteipOTxt() {
			return inAcuteipOTxt;
		}
		public void setInAcuteipOTxt(String inAcuteipOTxt) {
			this.inAcuteipOTxt = inAcuteipOTxt;
		}
		public String getInAcutealrg() {
			return inAcutealrg;
		}
		public void setInAcutealrg(String inAcutealrg) {
			this.inAcutealrg = inAcutealrg;
		}
		public String[] getInAcuteriskAlerts() {
			return inAcuteriskAlerts;
		}
		public void setInAcuteriskAlerts(String[] inAcuteriskAlerts) {
			this.inAcuteriskAlerts = inAcuteriskAlerts;
		}
		public String getInAcuteraOtTxt() {
			return inAcuteraOtTxt;
		}
		public void setInAcuteraOtTxt(String inAcuteraOtTxt) {
			this.inAcuteraOtTxt = inAcuteraOtTxt;
		}
		public String[] getInAcutepbswResident() {
			return inAcutepbswResident;
		}
		public void setInAcutepbswResident(String[] inAcutepbswResident) {
			this.inAcutepbswResident = inAcutepbswResident;
		}
		public String getInAcutepbsrOtTxt() {
			return inAcutepbsrOtTxt;
		}
		public void setInAcutepbsrOtTxt(String inAcutepbsrOtTxt) {
			this.inAcutepbsrOtTxt = inAcutepbsrOtTxt;
		}
		public String[] getInAcutenharufconditions() {
			return inAcutenharufconditions;
		}
		public void setInAcutenharufconditions(String[] inAcutenharufconditions) {
			this.inAcutenharufconditions = inAcutenharufconditions;
		}
		public String getInAcutenharufcOtTxt() {
			return inAcutenharufcOtTxt;
		}
		public void setInAcutenharufcOtTxt(String inAcutenharufcOtTxt) {
			this.inAcutenharufcOtTxt = inAcutenharufcOtTxt;
		}
		public String getInAcuteatisPage() {
			return inAcuteatisPage;
		}
		public void setInAcuteatisPage(String inAcuteatisPage) {
			this.inAcuteatisPage = inAcuteatisPage;
		}
		public String[] getInAcutepgctOfTransfer() {
			return inAcutepgctOfTransfer;
		}
		public void setInAcutepgctOfTransfer(String[] inAcutepgctOfTransfer) {
			this.inAcutepgctOfTransfer = inAcutepgctOfTransfer;
		}
		public String getInAcutepgcttOtTxt() {
			return inAcutepgcttOtTxt;
		}
		public void setInAcutepgcttOtTxt(String inAcutepgcttOtTxt) {
			this.inAcutepgcttOtTxt = inAcutepgcttOtTxt;
		}
		public String getInAcutetreatMentsNdFr() {
			return inAcutetreatMentsNdFr;
		}
		public void setInAcutetreatMentsNdFr(String inAcutetreatMentsNdFr) {
			this.inAcutetreatMentsNdFr = inAcutetreatMentsNdFr;
		}
		public String getInAcutednaf() {
			return inAcutednaf;
		}
		public void setInAcutednaf(String inAcutednaf) {
			this.inAcutednaf = inAcutednaf;
		}
		public String getInAcutedts() {
			return inAcutedts;
		}
		public void setInAcutedts(String inAcutedts) {
			this.inAcutedts = inAcutedts;
		}
		public String getInAcutedsc() {
			return inAcutedsc;
		}
		public void setInAcutedsc(String inAcutedsc) {
			this.inAcutedsc = inAcutedsc;
		}
		public String getInAcutedscTxt() {
			return inAcutedscTxt;
		}
		public void setInAcutedscTxt(String inAcutedscTxt) {
			this.inAcutedscTxt = inAcutedscTxt;
		}
		public String getInAcutedetf() {
			return inAcutedetf;
		}
		public void setInAcutedetf(String inAcutedetf) {
			this.inAcutedetf = inAcutedetf;
		}
		public String getInAcutedetfFrTxt() {
			return inAcutedetfFrTxt;
		}
		public void setInAcutedetfFrTxt(String inAcutedetfFrTxt) {
			this.inAcutedetfFrTxt = inAcutedetfFrTxt;
		}
		public String getInAcuteswCarePU() {
			return inAcuteswCarePU;
		}
		public void setInAcuteswCarePU(String inAcuteswCarePU) {
			this.inAcuteswCarePU = inAcuteswCarePU;
		}
		public String getInAcuteiinfluenza() {
			return inAcuteiinfluenza;
		}
		public void setInAcuteiinfluenza(String inAcuteiinfluenza) {
			this.inAcuteiinfluenza = inAcuteiinfluenza;
		}
		public String getInAcuteiinfDate() {
			return inAcuteiinfDate;
		}
		public void setInAcuteiinfDate(String inAcuteiinfDate) {
			this.inAcuteiinfDate = inAcuteiinfDate;
		}
		public String getInAcuteipneumococcal() {
			return inAcuteipneumococcal;
		}
		public void setInAcuteipneumococcal(String inAcuteipneumococcal) {
			this.inAcuteipneumococcal = inAcuteipneumococcal;
		}
		public String getInAcuteipnDate() {
			return inAcuteipnDate;
		}
		public void setInAcuteipnDate(String inAcuteipnDate) {
			this.inAcuteipnDate = inAcuteipnDate;
		}
		public String getInAcuteprhRrtrh() {
			return inAcuteprhRrtrh;
		}
		public void setInAcuteprhRrtrh(String inAcuteprhRrtrh) {
			this.inAcuteprhRrtrh = inAcuteprhRrtrh;
		}
		public String getInAcuteprhPTherapy() {
			return inAcuteprhPTherapy;
		}
		public void setInAcuteprhPTherapy(String inAcuteprhPTherapy) {
			this.inAcuteprhPTherapy = inAcuteprhPTherapy;
		}
		public String getInAcuteprhPtIt() {
			return inAcuteprhPtIt;
		}
		public void setInAcuteprhPtIt(String inAcuteprhPtIt) {
			this.inAcuteprhPtIt = inAcuteprhPtIt;
		}
		public String getInAcuteprhOt() {
			return inAcuteprhOt;
		}
		public void setInAcuteprhOt(String inAcuteprhOt) {
			this.inAcuteprhOt = inAcuteprhOt;
		}
		public String getInAcuteprhOtIt() {
			return inAcuteprhOtIt;
		}
		public void setInAcuteprhOtIt(String inAcuteprhOtIt) {
			this.inAcuteprhOtIt = inAcuteprhOtIt;
		}
		public String getInAcuteprhSt() {
			return inAcuteprhSt;
		}
		public void setInAcuteprhSt(String inAcuteprhSt) {
			this.inAcuteprhSt = inAcuteprhSt;
		}
		public String getInAcuteprhStIt() {
			return inAcuteprhStIt;
		}
		public void setInAcuteprhStIt(String inAcuteprhStIt) {
			this.inAcuteprhStIt = inAcuteprhStIt;
		}
		public String getInAcuteadlsB() {
			return inAcuteadlsB;
		}
		public void setInAcuteadlsB(String inAcuteadlsB) {
			this.inAcuteadlsB = inAcuteadlsB;
		}
		public String getInAcuteadlsD() {
			return inAcuteadlsD;
		}
		public void setInAcuteadlsD(String inAcuteadlsD) {
			this.inAcuteadlsD = inAcuteadlsD;
		}
		public String getInAcuteadlsT() {
			return inAcuteadlsT;
		}
		public void setInAcuteadlsT(String inAcuteadlsT) {
			this.inAcuteadlsT = inAcuteadlsT;
		}
		public String getInAcuteadlsTl() {
			return inAcuteadlsTl;
		}
		public void setInAcuteadlsTl(String inAcuteadlsTl) {
			this.inAcuteadlsTl = inAcuteadlsTl;
		}
		public String getInAcuteadlsE() {
			return inAcuteadlsE;
		}
		public void setInAcuteadlsE(String inAcuteadlsE) {
			this.inAcuteadlsE = inAcuteadlsE;
		}
		public String getInAcuteadlsCai() {
			return inAcuteadlsCai;
		}
		public void setInAcuteadlsCai(String inAcuteadlsCai) {
			this.inAcuteadlsCai = inAcuteadlsCai;
		}
		public String getInAcuteadlsCaiTxt() {
			return inAcuteadlsCaiTxt;
		}
		public void setInAcuteadlsCaiTxt(String inAcuteadlsCaiTxt) {
			this.inAcuteadlsCaiTxt = inAcuteadlsCaiTxt;
		}
		public String getInAcuteadlsAd() {
			return inAcuteadlsAd;
		}
		public void setInAcuteadlsAd(String inAcuteadlsAd) {
			this.inAcuteadlsAd = inAcuteadlsAd;
		}
		public String getInAcuteadlsAdTxt() {
			return inAcuteadlsAdTxt;
		}
		public void setInAcuteadlsAdTxt(String inAcuteadlsAdTxt) {
			this.inAcuteadlsAdTxt = inAcuteadlsAdTxt;
		}
		public String getInAcuteadlsHaa() {
			return inAcuteadlsHaa;
		}
		public void setInAcuteadlsHaa(String inAcuteadlsHaa) {
			this.inAcuteadlsHaa = inAcuteadlsHaa;
		}
		public String getInAcuteadlsHaaTxt() {
			return inAcuteadlsHaaTxt;
		}
		public void setInAcuteadlsHaaTxt(String inAcuteadlsHaaTxt) {
			this.inAcuteadlsHaaTxt = inAcuteadlsHaaTxt;
		}
		public String[] getInAcuteigeneral() {
			return inAcuteigeneral;
		}
		public void setInAcuteigeneral(String[] inAcuteigeneral) {
			this.inAcuteigeneral = inAcuteigeneral;
		}
		public String getInAcuteigOtTxt() {
			return inAcuteigOtTxt;
		}
		public void setInAcuteigOtTxt(String inAcuteigOtTxt) {
			this.inAcuteigOtTxt = inAcuteigOtTxt;
		}
		public String[] getInAcuteimusculoskeletal() {
			return inAcuteimusculoskeletal;
		}
		public void setInAcuteimusculoskeletal(String[] inAcuteimusculoskeletal) {
			this.inAcuteimusculoskeletal = inAcuteimusculoskeletal;
		}
		public String getInAcuteimOtTxt() {
			return inAcuteimOtTxt;
		}
		public void setInAcuteimOtTxt(String inAcuteimOtTxt) {
			this.inAcuteimOtTxt = inAcuteimOtTxt;
		}
		public String getInAcutectBlOrBr() {
			return inAcutectBlOrBr;
		}
		public void setInAcutectBlOrBr(String inAcutectBlOrBr) {
			this.inAcutectBlOrBr = inAcutectBlOrBr;
		}
		public String getInAcutectDlbm() {
			return inAcutectDlbm;
		}
		public Boolean getInAcuteSnd2HsptlFlag() {
			return inAcuteSnd2HsptlFlag;
		}
		public Boolean getStayInAcutecareFlag() {
			return stayInAcutecareFlag;
		}
		public void setStayInAcutecareFlag(Boolean stayInAcutecareFlag) {
			this.stayInAcutecareFlag = stayInAcutecareFlag;
		}
		public void setInAcuteSnd2HsptlFlag(Boolean inAcuteSnd2HsptlFlag) {
			this.inAcuteSnd2HsptlFlag = inAcuteSnd2HsptlFlag;
		}
		public void setInAcutectDlbm(String inAcutectDlbm) {
			this.inAcutectDlbm = inAcutectDlbm;
		}
		public Long getInAcutePatinetId() {
			return inAcutePatinetId;
		}
		public void setInAcutePatinetId(Long inAcutePatinetId) {
			this.inAcutePatinetId = inAcutePatinetId;
		}
		public Long getInAcutePatientEpisodeId() {
			return inAcutePatientEpisodeId;
		}
		public void setInAcutePatientEpisodeId(Long inAcutePatientEpisodeId) {
			this.inAcutePatientEpisodeId = inAcutePatientEpisodeId;
		}
		public String getPatientStatusInAcuteCare() {
			return patientStatusInAcuteCare;
		}
		public void setPatientStatusInAcuteCare(String patientStatusInAcuteCare) {
			this.patientStatusInAcuteCare = patientStatusInAcuteCare;
		}
		
}
