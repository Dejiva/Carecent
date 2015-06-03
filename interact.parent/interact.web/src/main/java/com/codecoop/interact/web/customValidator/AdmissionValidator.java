package com.codecoop.interact.web.customValidator;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codecoop.interact.web.model.AdmissionForm;

@Component
public class AdmissionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AdmissionForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object admissionForm, Errors errors) {

		AdmissionForm admission = (AdmissionForm)admissionForm;

		//	A. Patient Information Block

		if(StringUtils.isEmpty(admission.getOtherEthnicityType()) && !StringUtils.isEmpty(admission.getEthnicityType())){
			if(  admission.getEthnicityType().equalsIgnoreCase("OTHER")){
				errors.rejectValue("otherEthnicityType", "NotEmpty.admissionForm.otherEthnicityType", "other ethnicity should have value when check the other option");
			}
		}
		
		if(!StringUtils.isEmpty(admission.getCanSpeakEnglish()) && admission.getCanSpeakEnglish().equalsIgnoreCase("Other")){
			if(StringUtils.isEmpty(admission.getOtherLanguage())){
				errors.rejectValue("canSpeakEnglish", "canSpeakEnglish.otherLanguage", "other language should have value when check the other option");
			}
			
		}
	/*	if(StringUtils.isEmpty(admission.getOtherLanguage()) && (admission.getCanSpeakEnglish()== null ? false:admission.getCanSpeakEnglish())){
			if(StringUtils.isEmpty(admission.getOtherLanguage()) && (admission.getCanSpeakEnglish()== null ? false:true)){
			
			errors.rejectValue("canSpeakEnglish", "canSpeakEnglish.otherLanguage", "other language should have value when check the other option");

		}*/


		//	B. Family/Caregiver/Proxy Contact

				if(StringUtils.isEmpty(admission.getCaregiverName()) && StringUtils.isEmpty(admission.getCareGiverTelephoneNumber()) 
						&& StringUtils.isEmpty(admission.getGuardianName()) && StringUtils.isEmpty(admission.getGuardianTelephoneNumber())){
					errors.rejectValue("caregiverName", "admissionForm.caregiverName", "either of the caregiver or guardian details should be required");
				}
				if(StringUtils.isEmpty(admission.getCaregiverName()) && !StringUtils.isEmpty(admission.getCareGiverTelephoneNumber())){
					errors.rejectValue("caregiverName", "admissionForm.caregiverName", "caregiver Name should have value");
				}
				if(StringUtils.isEmpty(admission.getGuardianName()) && !StringUtils.isEmpty(admission.getGuardianTelephoneNumber())){
					errors.rejectValue("guardianName", "admissionForm.guardianName", "guardian name  should have value");
				}
				if(!StringUtils.isEmpty(admission.getCaregiverName()) && StringUtils.isEmpty(admission.getCareGiverTelephoneNumber())){
					errors.rejectValue("careGiverTelephoneNumber", "admissionForm.careGiverTelephoneNumber", "caregiver telephone number should have value");
				}
				if(!StringUtils.isEmpty(admission.getGuardianName()) && StringUtils.isEmpty(admission.getGuardianTelephoneNumber())){
					errors.rejectValue("guardianTelephoneNumber", "admissionForm.guardianTelephoneNumber", "guardian telephone number should have value");
				}
				
		//		if(StringUtils.isEmpty(admission.getCareGiverTelephoneNumber())){
		//			errors.rejectValue("careGiverTelephoneNumber", "admissionForm.careGiverTelephoneNumber", "caregiver telephone number should have value");
		//		}
		//
		//		if(StringUtils.isEmpty(admission.getGuardianName())){
		//			errors.rejectValue("guardianName", "admissionForm.guardianName", "guardian name  should have value");
		//		}
		//
		//		if(StringUtils.isEmpty(admission.getGuardianTelephoneNumber())){
		//			errors.rejectValue("guardianTelephoneNumber", "admissionForm.guardianTelephoneNumber", "guardian telephone number should have value");
		//		}

		//	C. Advance Directives/Goals of Care

		if(admission.getOtherDirectiveCheckBox() && StringUtils.isEmpty(admission.getOtherAdvanceDirectives())){

			errors.rejectValue("otherAdvanceDirectives", "admissionForm.otherAdvanceDirectives", "Advance Directives/Goals of Care other specification should have value");
		}
		if(!StringUtils.isEmpty(admission.getGoalsOfCareDiscussed()) && StringUtils.isEmpty(admission.getSpecifyGoalsOfCareDiscussed())){
			if(admission.getGoalsOfCareDiscussed().equalsIgnoreCase("YES")){
				errors.rejectValue("goalsOfCareDiscussed", "admissionForm.goalsOfCareDiscussed", "Were goals of care discussed during this hospitalization other specification should have value");
			}
		}

//		if(admission.getCanTakeDecision() == null && admission.getReqProxy() == null){
//			errors.rejectValue("canTakeDecision", "admissionForm.canTakeDecision", "either of Capable of making decisions or Requires proxy should be checked");
//		}

		//	E. Post-Acute Care Information

		if(StringUtils.isEmpty(admission.getNurseToNurseVerbalReportText()) && !StringUtils.isEmpty(admission.getNurseToNurseVerbalReport())){
			if(admission.getNurseToNurseVerbalReport().equalsIgnoreCase("YES")){
				errors.rejectValue("NurseToNurseVerbalReportText", "admissionForm.NurseToNurseVerbalReportText", "Nurse to Nurse verbal report should be specified");
			}
		}

		// 	H. High Risk Conditions/Treatment Information (check all that apply)

		if(!StringUtils.isEmpty(admission.getInAdmissionHrcOrTiHfEf())  && StringUtils.isEmpty(admission.getInAdmissionHrcOrTiHfEfp())){

			errors.rejectValue("inAdmissionHrcOrTiHfEfp", "admissionForm.inAdmissionHrcOrTiHfEfp", "EF(%) should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionhrcOrTiAcReasons())   && StringUtils.isEmpty(admission.getInAdmissionHrcOrTicROthrText())){
			if(Arrays.asList(admission.getInAdmissionhrcOrTiAcReasons()).contains("HRC_OR_TI_AC_R_OTHR"))
			errors.rejectValue("inAdmissionHrcOrTicROthrText", "admissionForm.inAdmissionHrcOrTicROthrText", "Anticoagulated: Reason: other should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionGoalInr())  && StringUtils.isEmpty(admission.getInAdmissionHrcOrTiGiOthrText())){
			if(Arrays.asList(admission.getInAdmissionGoalInr()).contains("HRC_OR_TI_GI_OTHR"))
			errors.rejectValue("inAdmissionHrcOrTiGiOthrText", "admissionForm.inAdmissionHrcOrTiGiOthrText", "Anticoagulated: Reason: Goal INR: other should have value");
		}

		if(!StringUtils.isEmpty(admission.getInAdmissionHrcOrTiOpIndications())  && StringUtils.isEmpty(admission.getInAdmissionHrcOrTiOpIndicationsText())){
			if(Arrays.asList(admission.getInAdmissionHrcOrTiOpIndications()).contains("HRC_OR_TI_OP_SD"))
			errors.rejectValue("inAdmissionHrcOrTiOpIndicationsText", "admissionForm.inAdmissionHrcOrTiOpIndicationsText", "Specific Dx: should have value");
		}
		// J. Medications and Allergies


		if(!StringUtils.isEmpty(admission.getInAdmissionMAndAAllrgs()) && StringUtils.isEmpty(admission.getInAdmissionMAndAAllrgsYSpcfy())){
			if(admission.getInAdmissionMAndAAllrgs().equalsIgnoreCase("M_AND_A_ALLRGS_Y"))
			errors.rejectValue("inAdmissionMAndAAllrgsYSpcfy", "admissionForm.inAdmissionMAndAAllrgsYSpcfy", "Allergies specification should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionMAndAPm())  && StringUtils.isEmpty(admission.getInAdmissionMAndAPmText())){
			if(Arrays.asList(admission.getInAdmissionContinence()).contains("NCPSF_C_CATHTR"))
			errors.rejectValue("inAdmissionMAndAPm", "admissionForm.inAdmissionMAndAPm", "Pain med specification should have value");
		}

		//	K. Nursing Care
		if(!StringUtils.isEmpty(admission.getInAdmissionContinence())   && StringUtils.isEmpty(admission.getInAdmissionContinenceText())){
			if(Arrays.asList(admission.getInAdmissionContinence()).contains("NCPSF_C_CATHTR"))
			errors.rejectValue("inAdmissionContinenceText", "admissionForm.inAdmissionContinenceText", "Catheter Date inserted  should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionReasonforcatheter()) && StringUtils.isEmpty(admission.getInAdmissionReasonforcatheterText())){
			if(Arrays.asList(admission.getInAdmissionReasonforcatheter()).contains("NCPSF_C_RFC_OTHR"))
			errors.rejectValue("inAdmissionReasonforcatheterText", "admissionForm.inAdmissionReasonforcatheterText", "Reason for catheter other  should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionNcpsfC()) && StringUtils.isEmpty(admission.getInAdmissionNcpsfCText())){
			if(Arrays.asList(admission.getInAdmissionNcpsfC()).contains("NCPSF_C_DOLBM"))
			errors.rejectValue("inAdmissionNcpsfCText", "admissionForm.inAdmissionNcpsfCText", "Date of last BM  should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionNcpsfNAndHTfTfp()) && StringUtils.isEmpty(admission.getInAdmissionNcpsfNAndHTfTfpt())){
			errors.rejectValue("inAdmissionNcpsfNAndHTfTfpt", "admissionForm.inAdmissionNcpsfNAndHTfTfpt", "tube feed product should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionTubeFeeding())  && StringUtils.isEmpty(admission.getInAdmissionNcpsfNAndHTfDiText())){
			if(admission.getInAdmissionTubeFeeding().equalsIgnoreCase("NCPSF_N_AND_H_TF_DI"))
			errors.rejectValue("inAdmissionNcpsfNAndHTfDiText", "admissionForm.inAdmissionNcpsfNAndHTfDiText", "Date inserted should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionNcpsfTtdPicc())  && StringUtils.isEmpty(admission.getInAdmissionNcpsfTtdPiccText())){
			if(Arrays.asList(admission.getInAdmissionNcpsfTtdPicc()).contains("NCPSF_TTD_PRTCTH"))
			errors.rejectValue("inAdmissionNcpsfTtdPiccText", "admissionForm.inAdmissionNcpsfTtdPiccText", "Portacath Date inserted should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionCardiac())   && StringUtils.isEmpty(admission.getInAdmissionCardiacText())){
			if(Arrays.asList(admission.getInAdmissionCardiac()).contains("NCPSF_TTD_C_OTHR"))
			errors.rejectValue("inAdmissionCardiacText", "admissionForm.inAdmissionCardiacText", "Cardiac other should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionRespiratory())  && StringUtils.isEmpty(admission.getInAdmissionRespiratoryO2Text())){
			if(Arrays.asList(admission.getInAdmissionRespiratory()).contains("NCPSF_TTD_R_O2"))
			errors.rejectValue("inAdmissionRespiratoryO2Text", "admissionForm.inAdmissionRespiratoryO2Text", "O2(In L) should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionRespiratory()) && StringUtils.isEmpty(admission.getInAdmissionRespiratoryTsText())){
			if(Arrays.asList(admission.getInAdmissionRespiratory()).contains("NCPSF_TTD_R_TS"))
			errors.rejectValue("inAdmissionRespiratoryTsText", "admissionForm.inAdmissionRespiratoryTsText", "Trach size should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionNcpsfScpU()) && StringUtils.isEmpty(admission.getInAdmissionNcpsfScPuStage()) && StringUtils.isEmpty(admission.getInAdmissionNcpsfScPuLocation())){
			errors.rejectValue("inAdmissionNcpsfScpU", "admissionForm.inAdmissionNcpsfScpU", "Pressure ulcer stage  & location should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionNcpsfScSpu())   && StringUtils.isEmpty(admission.getInAdmissionNcpsfScSpuStage()) && StringUtils.isEmpty(admission.getInAdmissionNcpsfScSpuLocation())){

			errors.rejectValue("inAdmissionNcpsfScSpu", "admissionForm.inAdmissionNcpsfScSpu", "2nd Pressure ulcer stage  & location should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionNcpsfScOthr())  && StringUtils.isEmpty(admission.getInAdmissionNcpsfScOthrSpcfy())){

			errors.rejectValue("inAdmissionNcpsfScOthrSpcfy", "admissionForm.inAdmissionNcpsfScOthrSpcfy", "Other wounds should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionRisksandPrecautions())    && StringUtils.isEmpty(admission.getInAdmissionRisksandPrecautionsText())){
			if(Arrays.asList(admission.getInAdmissionRisksandPrecautions()).contains("NCPSF_R_AND_P_OTHR"))
			errors.rejectValue("inAdmissionRisksandPrecautionsText", "admissionForm.inAdmissionRisksandPrecautionsText", "Risks and Precautions other should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionNcpsfIciI())  && StringUtils.isEmpty(admission.getInAdmissionNcpsfIciIText())){
			if(admission.getInAdmissionNcpsfIciI().equalsIgnoreCase("NCPSF_ICI_I_Y"))
			errors.rejectValue("inAdmissionNcpsfIciIText", "admissionForm.inAdmissionNcpsfIciIText", "Immunizations  Influenzadate should have value");
		}
		if(!StringUtils.isEmpty(admission.getInAdmissionNcpsfIciIP())  && StringUtils.isEmpty(admission.getInAdmissionNcpsfIciIPText())){
			if(admission.getInAdmissionNcpsfIciIP().equalsIgnoreCase("NCPSF_ICI_I_P_YES"))
			errors.rejectValue("inAdmissionNcpsfIciIPText", "admissionForm.inAdmissionNcpsfIciIPText", "Pneumococcal date should have value");
		}

	}

}
