package com.codecoop.interact.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	public static final String ALL_CATEGORIES = "ALL Categories";
	public static final String PUBLISHED_ON_DATE_FORMAT = "E, dd MMM yyyy H:m:s Z";
	
	public static final String ALCHEMY_RESPONSE_SUCCESS = "OK";
	
	public static final String SQL_FUN_MIN = "MIN";
	public static final String SQL_FUN_MAX = "MAX";
	
	public static final String RATING_AGENCY_MOODY = "MOODYS";
	public static final String RATING_AGENCY_SANDP = "SANDP";
	public static final String RATING_AGENCY_MORNINGSTAR = "MORNINGSTAR";
	
	public static final String ADMIN_ROLE = "ROLE_ADMIN";
	public static final String CNA_ROLE = "ROLE_CNA";
	public static final String LPN_ROLE = "ROLE_LPN";
	public static final String RN_ROLE = "ROLE_RN";
	public static final String PA_ROLE = "ROLE_PA";
	public static final String MD_ROLE = "ROLE_MD";
	public static final String NP_ROLE = "ROLE_NP";
	public static final String SUPER_ADMIN_ROLE = "ROLE_SUPER_ADMIN";
	public static final String GUEST_ROLE ="ROLE_GUEST";
	
	public static final String FACILITY_CONTACT_MOBILE_TYPE ="Mobile/Phone No";
	
	public static final String CONTACT_TYPE_EMAIL = "email";
	public static final String CONTACT_TYPE_MOBILE = "mobile";
	
	public static final List<String> nonVendorList;
	public static final String SANP500_NAME = "S&P 500";
	public static final Map<String, String> nonVendorNameStockCodeMap;
	
	public static final String  SIGN_FLAG="SIGN_FLAG";
	public static final String  SYMPTOM_FLAG="SYMPTOM_FLAG";
	public static final String  LABWORK_FLAG="LABWORK_FLAG";
	
	public static final String CAREPATH_NOTIFY_DOCTOR = "NOTIFY_MD_NP_PA";
	public static final String CAREPATH_DO_NOTHING = "DO_NOTHING";	
	
	public static final String ADMISSION_ATTR_TYPE_BOOLEAN = "BOOLEAN";
	public static final String ADMISSION_ATTR_CODE_O2SAT="KCIVS_02SAT";
	
	public static final String PRIMARYDIAGNOSES = "KCID_PDD";
	public static final String OTHERPERTINENTHISTORY="KCID_OMD";
	
	public static final String HOSPITAL_RECOMMENDED="hospital recommended";
	public static final String PRIOR_TO_HOSPITALIZATION="prior to hospitalization";
	// for SymtomSignLabWorkAttr Table DataType Column Values
	public static final String BOOLEAN_DATATYPE = "BOOLEAN";
	public static final String INT_DATATYPE = "INT";
	public static final String FLOAT_DATATYPE = "FLOAT";
	public static final String VARCHAR_DATATYPE="VARCHAR";
	public static final String CIC_NOTIFY_IMMEDIATE = "Immediate Notify!";
	public static final String CIC_NOTIFY_NEXT_DAY = "Notify Next Day!";
	public static final String SBAR_XML_LOCATION = "SBAR_XML_LOC";
	
	public static final String DOCTOR_TYPE = "DOCTOR";
	public static final String NURSE_TYPE = "NURSE";
	public static final String ADMIN_TYPE = "ADMIN";
	public static final String CNA_TYPE = "CNA";
	public static final String GUEST_TYPE = "GUEST";
	
	public static final String PATIENT_ADMITED="PATIENT_ADMITED";
	public static final String SNW_REPORTEDTO="SNW_REPORTEDTO";
	public static final String SNW_NOTREPORTEDTO="SNW_NOTREPORTEDTO";
	public static final String SNW_NOBODYRESPONDS="SNW_NOBODYRESPONDS";
	public static final String SNW_RESPONDED="SNW_RESPONDED";
	public static final String  PATIENT_ADMISSION_ASSIGNED_DOC="PATIENT_ADMISSION_ASSIGNED_DOC";
	public static final String SNW_INVALID="SNW_INVALID";
	public static final String SNW_NOBODYASSIGNED="SNW_NOBODYASSIGNED";
	public static final String NURSE_NOTIFY_DOC="NURSE_NOTIFY_DOC";
	
	public static final String OBSERVATION_CREATED="OBSERVATION_CREATED";
	public static final String CP_SUGG_NOTIFYDOC_NOTREPORTED="CP_SUGG_NOTIFYDOC_NOTREPORTED";
	public static final String CP_SUGG_NOTIFYDOC_REPORTED="CP_SUGG_NOTIFYDOC_REPORTED";
	public static final String CP_SUGG_CONSORDER_MANGFACILITY="CP_SUGG_CONSORDER_MANGFACILITY";
	public static final String PATIENT_TRANSFER_ACUTECARE="PATIENT_TRANSFER_ACUTECARE";
	public static final String PATIENT_TRANSFER_MANGFACILITY="PATIENT_TRANSFER_MANGFACILITY";
	public static final String PATIENT_RECOVERD_TRANSFER="PATIENT_RECOVERD_TRANSFER";
	public static final String PATIENT_TRANSFER_HOSPITAL="PATIENT_TRANSFER_HOSPITAL";
	public static final String PATIENT_BACKIN_FACILITY="PATIENT_BACKIN_FACILITY";
	public static final String MSG_ALERT_TEMPLATE_PATIENT_CODE="$P#";
	public static final String MSG_ALERT_TEMPLATE_NURSE_CODE="$N#";
	public static final String MSG_ALERT_TEMPLATE_DOCTOR_CODE="$D#";
	public static final String MSG_ALERT_TEMPLATE_NURSEINGHOME_CODE="$NH#";
	
	public static final String MESSAGE="MESSAGE";
	public static final String ALERT="ALERT";
	
	public static final String ADMISSION_SUBJECT="ADMISSION";
	public static final String SNW_SUBJECT="STOP AND WATCH";
	public static final String OBSERVATION_SUBJECT="CLINICAL ASSESSMENT";
	public static final String CP_SUBJECT="CAREPATH SUGGESTION";
	public static final String ACUTECARE_SUBJECT="ACUTE CARE TRANSFER";
	public static final String CONSIDER_ORDERS="CONSIDER_ORDERS";
	public static final String MANG_FACILITY_SUBJECT="MANAGE IN FACILITY";
	public static final String PATIENT_RECOVERD_SUBJECT="PATIENT_RECOVERD";
	public static final String TRANSFER_HOSPITAL_SUBJ="TRANSFERRED TO HOSPITAL";
	public static final String BACKIN_FACILITY_SUBJ="BACK IN FACILITY";
	public static final String NEED_APPROVE="require_approve_from_Doctor";
	
	static{
		nonVendorList = new ArrayList<String>();
		nonVendorList.add(SANP500_NAME);
		nonVendorNameStockCodeMap = new HashMap<String,String>();
		nonVendorNameStockCodeMap.put(SANP500_NAME, "^GSPC");
	}

}
