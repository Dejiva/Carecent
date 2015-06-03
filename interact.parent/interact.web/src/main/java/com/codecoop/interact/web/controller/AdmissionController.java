package com.codecoop.interact.web.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecoop.interact.core.dto.AdmissionAttributeValuesDto;
import com.codecoop.interact.core.dto.AdmissionAttributesDto;
import com.codecoop.interact.core.dto.AdmissionDto;
import com.codecoop.interact.core.dto.AdmissionFormAttribNdValuesDto;
import com.codecoop.interact.core.dto.GuardianDto;
import com.codecoop.interact.core.dto.HospitalDto;
import com.codecoop.interact.core.dto.HospitalStaffDto;
import com.codecoop.interact.core.dto.HospitalStaffSpecialityDto;
import com.codecoop.interact.core.dto.HsptlPhyCareTeam;
import com.codecoop.interact.core.dto.MedicalReconciliationDto;
import com.codecoop.interact.core.dto.PatientDto;
import com.codecoop.interact.core.dto.PatientEpisodeDto;
import com.codecoop.interact.core.dto.PatientInfoDto;
import com.codecoop.interact.core.dto.SSNCheckDto;
import com.codecoop.interact.core.service.AdmissionService;
import com.codecoop.interact.core.service.AttributesService;
import com.codecoop.interact.web.customValidator.AdmissionValidator;
import com.codecoop.interact.web.model.AcuteCareModel;
import com.codecoop.interact.web.model.AdmissionForm;
import com.codecoop.interact.web.model.DashboardForm;
import com.codecoop.interact.web.model.FacilityModel;
import com.codecoop.interact.web.model.SSNBean;
import com.codecoop.interact.web.model.StaffRegistrationForm;
import com.codecoop.interact.web.model.StopAndWatchForm;
import com.codecoop.interact.web.util.DateUtil;

@Controller
public class AdmissionController {

	private static final Logger LOG = Logger.getLogger(AdmissionController.class);

	@Autowired
	AttributesService attributesService;

	@Autowired
	AdmissionService admissionService;

	@Autowired
	AdmissionValidator admissionValidator;

	@RequestMapping(value = "/getPatient", method = RequestMethod.POST)
	public 
	String openAdmissionForm(@RequestParam(value = "patientId") Long patientId,@RequestParam(value = "ssnCheck") Boolean ssnCheck,
			@ModelAttribute("admissionForm") AdmissionForm admissionForm,
			ModelMap modelMap,HttpServletRequest request){

		modelMap.addAttribute("dashboardForm", new DashboardForm());
		modelMap.addAttribute("staffRegistrationForm", new StaffRegistrationForm());
		modelMap.addAttribute("stopAndWatchForm", new StopAndWatchForm());
		modelMap.addAttribute("acuteCareModel", new AcuteCareModel());
		modelMap.addAttribute("facilityModel", new FacilityModel());
		Long facilityId = (Long)request.getSession().getAttribute("facilityId");
		AdmissionDto admissionFormDto = new AdmissionDto();
		String ssn = request.getParameter("ssn");
		if(patientId != 0){
			admissionFormDto.setPatientId(patientId);
		}else if(!StringUtils.isEmpty(ssn)){
			admissionFormDto.setsSNumber(ssn);
		}
		admissionFormDto = admissionService.getAdmissionForm(admissionFormDto,facilityId,ssnCheck);
		if(StringUtils.isEmpty(admissionFormDto)){
			return null;
		}
		if(!StringUtils.isEmpty(admissionFormDto.getPatientEncounterId())){
		List<MedicalReconciliationDto>	medicalReconList = admissionService.getMedicalReconcilationDetailsByEncounterId(admissionFormDto.getPatientEncounterId());
		admissionFormDto.setMedicalReconList(medicalReconList);
		}
		prepareAdmissionForm(admissionForm,admissionFormDto);
		if(Boolean.valueOf(request.getParameter("propStatus"))){
		request.setAttribute("propStatus","disbleProp");
		}
		if(!StringUtils.isEmpty(request.getParameter("reloadDashBoard")) && Boolean.valueOf(request.getParameter("reloadDashBoard")))
		request.setAttribute("admissionStatus", "reloadDashBoard");
		request.setAttribute("status", "success");
		return "carecent";
	}

	@RequestMapping(value = "/getCareTeam", method = RequestMethod.GET)
	public @ResponseBody List<HsptlPhyCareTeam>/* HospitalCareTeamResponse*/ getAllHospitalCareTeam() {

		//		HospitalCareTeamResponse careTeamListResp = new HospitalCareTeamResponse();
		List<HsptlPhyCareTeam> careTeamDtoList =new ArrayList<HsptlPhyCareTeam>();
		List<HospitalStaffDto> careTeamList = admissionService.getAllHospitalCareTeam();

		for(HospitalStaffDto dtoList:careTeamList){

			HsptlPhyCareTeam dto = new HsptlPhyCareTeam();

			dto.setHospitalStaffId(dtoList.getHospitalStaffId());
			dto.setHsptlStaffTypeId(dtoList.getHsptlStaffTypeId());
			dto.setSpecialityId(dtoList.getSpecialityId());
			dto.setSpecialityName(dtoList.getSpecialityName());
			dto.setPatientEpisodeId(dtoList.getPatientEpisodeId());
			dto.setStaffName(dtoList.getStaffName());
			dto.setContactNumber(dtoList.getContactNumber());
			dto.setEmailId(dtoList.getEmailId());
			careTeamDtoList.add(dto);

		}
		//		careTeamListResp.setHsptlPhyCareTeam(careTeamDtoList);

		return careTeamDtoList;

	}

	@RequestMapping(value = "/getAllSpecialities", method = RequestMethod.GET)
	public @ResponseBody List<HospitalStaffSpecialityDto> getAllSpecialities() {
		return admissionService.getAllSpecialities();
	}

	@RequestMapping(value="/newAdmission", method = RequestMethod.GET)
	public String newPatientAdmission(@Valid @ModelAttribute("admissionForm") AdmissionForm admissionForm,
			BindingResult result,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpServletRequest req, ModelMap modelMap)throws Exception{

		modelMap.addAttribute("dashboardForm", new DashboardForm());
		modelMap.addAttribute("staffRegistrationForm", new StaffRegistrationForm());
		modelMap.addAttribute("stopAndWatchForm", new StopAndWatchForm());
		modelMap.addAttribute("acuteCareModel", new AcuteCareModel());
		modelMap.addAttribute("facilityModel", new FacilityModel());


		return "redirect:welcome?admissionOperation=addNewPatient";
	}



	@RequestMapping(value="/admission", method = RequestMethod.POST)
	public String saveAdmissionForm(@Valid @ModelAttribute("admissionForm") AdmissionForm admissionForm,
			BindingResult result,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpServletRequest req, ModelMap modelMap){

		modelMap.addAttribute("dashboardForm", new DashboardForm());
		modelMap.addAttribute("staffRegistrationForm", new StaffRegistrationForm());
		modelMap.addAttribute("stopAndWatchForm", new StopAndWatchForm());
		modelMap.addAttribute("acuteCareModel", new AcuteCareModel());
		modelMap.addAttribute("facilityModel", new FacilityModel());

		admissionValidator.validate(admissionForm, result);
		if(result.hasErrors()){
			String medReconJson="";
			ObjectMapper mapper = new ObjectMapper();
			try {
				medReconJson = mapper.writeValueAsString(admissionForm.getMedicalReconciliationDto());
				req.setAttribute("medReconcilation",medReconJson);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error(e.getMessage(), e);
			} 
			req.setAttribute("patientId", req.getParameter("patientId"));
			req.setAttribute("parmMap",admissionForm);
			req.setAttribute("errorPage", "admissionModalError");
			return "carecent";
		}
		//		PatientInfoDto patientInfo = prepareAdmissionAttributesMap(admissionForm);
		PatientInfoDto patientInfo = prepareAdmissionAttrubuteValuesDto(admissionForm);
		if(!CollectionUtils.isEmpty(admissionForm.getMedicalReconciliationDto())){
			for(MedicalReconciliationDto list: admissionForm.getMedicalReconciliationDto()){
				list.setPatientEncounterId(patientInfo.getPatientEncounterId());
			}
			saveMedicalReconciliation(admissionForm.getMedicalReconciliationDto(),req.getUserPrincipal().getName());
		}
		try{
			req.setAttribute("patientIdndEId",patientInfo.getPatientId()+"|"+patientInfo.getPatientEpisodeId());
			req.setAttribute("admissionStatus","Patient Details Saved Successfully......");
			req.setAttribute("status", "success");
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			//			return "carecent?status=FailedAdmission";
			req.setAttribute("status", "FailedAdmission");
			req.setAttribute("admissionStatus","Patient Details not saved please try again...");
			return "carecent";
		}
		//		return "carecent?status=success|"+patientInfo.getPatientId();
		return "carecent";
	}

	private PatientEpisodeDto preparePatientEpisode(AdmissionForm form){

		PatientDto patientDto = new PatientDto();
		patientDto.setPatientId(form.getPatientId());
		patientDto.setDob(DateUtil.string2Date(form.getDob()));
		patientDto.setsSNumber(form.getsSNumber());
		patientDto.setEthnicityType(form.getEthnicityType());
		if(!StringUtils.isEmpty(form.getOtherEthnicityType())){
			patientDto.setEthnicityUd(form.getOtherEthnicityType());
		}
		patientDto.setGender(form.getPatientGender());
		//		String language = form.getCanSpeakEnglish() ? "English" : form.getOtherLanguage();
		String language=null;
		language = StringUtils.isEmpty(form.getOtherLanguage()) ? "English" : form.getOtherLanguage();
		patientDto.setLanguage(language);
		patientDto.setPatientFirstName(form.getPatientFirstName());
		patientDto.setPatientLastName(form.getPatientLastName());

		GuardianDto careGiver = new GuardianDto();
		careGiver.setGuardianId(form.getgCareGiverId());
		careGiver.setGuardianName(form.getCaregiverName());
		careGiver.setContactNumber(form.getCareGiverTelephoneNumber());
		patientDto.setCareGiver(careGiver);

		GuardianDto guardianOrProxy = new GuardianDto();
		guardianOrProxy.setGuardianId(form.getgProxyId());
		guardianOrProxy.setGuardianName(form.getGuardianName());
		guardianOrProxy.setContactNumber(form.getGuardianTelephoneNumber());
		patientDto.setGuardianOrProxy(guardianOrProxy);

		HospitalDto fromHospitalDto = new HospitalDto();
		fromHospitalDto.setHospitalId(form.getHospitalId());
		fromHospitalDto.setHospitalName(form.getHospitalName());

		HospitalStaffDto dischargeNurseDto = new HospitalStaffDto();
		dischargeNurseDto.setContactNumber(form.getDishcargingRNTelephoneNumber());
		//dischargeNurseDto.setEmailId(form.)
		dischargeNurseDto.setHospitalStaffId(form.getDischargingRNId());
		dischargeNurseDto.setStaffName(form.getDischargingRN());

		HospitalStaffDto dischargeMDDto = new HospitalStaffDto();
		dischargeMDDto.setContactNumber(form.getDishcargingMDTelephoneNumber());
		//dischargeNurseDto.setEmailId(form.)
		dischargeMDDto.setHospitalStaffId(form.getDischargingMDId());
		dischargeMDDto.setStaffName(form.getDischargingMD());


		List<HospitalStaffDto> physicianCareTeamListDto =new ArrayList<HospitalStaffDto>();


		HospitalStaffDto primaryCarePhysicianDto=new HospitalStaffDto();
		primaryCarePhysicianDto.setHospitalStaffId(form.getPrimaryCarePhysicianId());
		primaryCarePhysicianDto.setStaffName(form.getPrimaryCarePhysician());
		primaryCarePhysicianDto.setContactNumber(form.getPrimaryCarePhysicianTelephoneNumber());
		//	primaryCarePhysicianDto.setSpecialityId(form.getSpecialist1SpecialityId());
		primaryCarePhysicianDto.setSpecialityId(1L);
		primaryCarePhysicianDto.setSpecialityName("PCP");

		physicianCareTeamListDto.add(primaryCarePhysicianDto);

		HospitalStaffDto specialist1Dto=new HospitalStaffDto();
		specialist1Dto.setHospitalStaffId(form.getSpecialist1Id());
		specialist1Dto.setStaffName(form.getSpecialist1());
		specialist1Dto.setContactNumber(form.getSpecialist1TelephoneNumber());
		specialist1Dto.setSpecialityName(form.getSpecialist1Speciality());
		specialist1Dto.setSpecialityId(form.getSpecialist1SpecialityId());

		physicianCareTeamListDto.add(specialist1Dto);

		HospitalStaffDto specialist2Dto=new HospitalStaffDto();
		specialist2Dto.setHospitalStaffId(form.getSpecialist2Id());
		specialist2Dto.setStaffName(form.getSpecialist2());
		specialist2Dto.setContactNumber(form.getSpecialist2TelephoneNumber());
		specialist2Dto.setSpecialityName(form.getSpecialist2Speciality());
		specialist2Dto.setSpecialityId(form.getSpecialist2SpecialityId());

		physicianCareTeamListDto.add(specialist2Dto);

		PatientEpisodeDto patientEpisodeDto = new PatientEpisodeDto();
		patientEpisodeDto.setPatientEpisodeId(form.getPatientEpisodeId());
		patientEpisodeDto.setPatient(patientDto);
		patientEpisodeDto.setCanTakeDecision(form.getCanTakeDecision());
		patientEpisodeDto.setComfortCare(form.getComfortCare());
		patientEpisodeDto.setHospiceCare(form.getHospiceCare());
		patientEpisodeDto.setDnh(form.getDnh());
		patientEpisodeDto.setDni(form.getDni());
		patientEpisodeDto.setDnr(form.getDnr());
		patientEpisodeDto.setPatientCareTypes(AdmissionForm.CARETYPES.get(form.getPatientCareTypes()));
		patientEpisodeDto.setPatientInsurencePlan(AdmissionForm.INSURENCEPLAN.get(form.getPatientInsurencePlan()));
		patientEpisodeDto.setFromHospital(fromHospitalDto);
		if(!StringUtils.isEmpty(form.getHospitalAdmissionDate())){
			patientEpisodeDto.setFromHospitalAdmissionDate(DateUtil.string2Date(form.getHospitalAdmissionDate()));}
		if(!StringUtils.isEmpty(form.getFromHospitalDischargeDate())){
			patientEpisodeDto.setFromHospitalDischargeDate(DateUtil.string2Date(form.getFromHospitalDischargeDate()));}
		patientEpisodeDto.setRoomNumber(form.getRoomNumber());
		patientEpisodeDto.setFromHospitalUnit(form.getHospitalUnit());
		patientEpisodeDto.setHospitalDischargeDoctor(dischargeMDDto);
		patientEpisodeDto.setHospitalDischargeNurse(dischargeNurseDto);

		patientEpisodeDto.setPhysicianCareTeamListDto(physicianCareTeamListDto);	
		patientEpisodeDto.setGoalsCareDiscussionText(form.getSpecifyGoalsOfCareDiscussed());

		patientEpisodeDto.setAdmittedByStaffId(form.getAdmittedByStaffId());
		if(!StringUtils.isEmpty(form.getDoj())){
			patientEpisodeDto.setFacilityAdmissionDate(DateUtil.string2Date(form.getDoj()));
		}
		patientEpisodeDto.setPcpAtSite(form.getPcpAtSite());
		/*patientEpisodeDto.setBedNo(bedNo)
		patientEpisodeDto.setFacilityAdmissionDate(facilityAdmissionDate)
		patientEpisodeDto.setFacilityDischargeDate(facilityDischargeDate)
		patientEpisodeDto.setFacilityDischargeRNId(facilityDischargeRNId)
		patientEpisodeDto.setFacilityDischargeMDId(facilityDischargeMDId)*/
		patientEpisodeDto.setFacilityId(form.getFacilityId());
		patientEpisodeDto.setFacilityStayType(form.getAdmittedByStaffType().replaceAll("[^\\w\\s]", ""));
		/*patientEpisodeDto.setFacilityUnit(facilityUnit)*/

		patientEpisodeDto.setFullCode(form.getFullCode() ?"YES":"NO");	

		patientEpisodeDto.setHospitalNurseVerbalReport(form.getNurseToNurseVerbalReportText());
		patientEpisodeDto.setHospitalPriorityCareNeeds(form.getSummarizeHighPriorityNeeds());
		patientEpisodeDto.setFollowUpTests(form.getRecommendedFollowupTests());
		patientEpisodeDto.setKeyFindings(form.getKeyFindings());
		patientEpisodeDto.setListProcedures(form.getListProcedures());
		patientEpisodeDto.setNoArtificialFeeding(form.getNoArtificialFeeding());
		patientEpisodeDto.setOtherCare(form.getOtherAdvanceDirectives());
		patientEpisodeDto.setReqProxy(form.getReqProxy());
		patientEpisodeDto.setNurseToNurseVerbalReport(form.getNurseToNurseVerbalReport());
		patientEpisodeDto.setGoalsOfCareDiscussed(form.getGoalsOfCareDiscussed());
		/*	checking stopAndWatchEligibility */
		patientEpisodeDto.setStopAndWatchEligiblity(checkStopAndWatchEligibility(form));
		/*	RAMESH	*/
		/*	RAMESH	*/

		patientEpisodeDto.setPendingLabResult(form.getPendingLabAndTestResults());
		patientEpisodeDto.setUserId(form.getAdmittedByStaffName());
		//		try{
		admissionService.admitPatient(patientEpisodeDto);
		//		}catch(Exception e){
		//			e.printStackTrace();
		//			LOG.error(e.getMessage(), e);
		//		}

		//		return null;
		return patientEpisodeDto;

	}

	private Map<String,List<String>> prepareAdmissionAttribValuesMapping(List<AdmissionFormAttribNdValuesDto> pAdmissionAttribValues,AdmissionForm admissionModel){

		Class<?> cl = admissionModel.getClass();
		Field[] fields = cl.getDeclaredFields();
		Map<String,List<String>> map =new HashMap<String,List<String>>();
		try{
			for(AdmissionFormAttribNdValuesDto fieldObj:pAdmissionAttribValues){
				for(Field f:fields){
					if(f.getName().equals(fieldObj.getAttibute())){
						f.setAccessible(true);
						if(!fieldObj.getType().equalsIgnoreCase("Array")){
							f.set(admissionModel, fieldObj.getAttibValue());
						}else if(fieldObj.getType().equalsIgnoreCase("Array")){
							if(!CollectionUtils.isEmpty(map)){
								List<String> list= map.get(fieldObj.getAttibute());
								if(list == null){
									list=new ArrayList<String>();
								}
								list.add(fieldObj.getAttibValue());
								map.put(fieldObj.getAttibute(), list);
							}else{
								List<String> value=new ArrayList<String>();
								value.add(fieldObj.getAttibValue());
								map.put(fieldObj.getAttibute(), value);
							}
						}
					}
				}
			}
			return map;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private PatientInfoDto prepareAdmissionAttrubuteValuesDto(AdmissionForm admissionModel){

		List<AdmissionAttributeValuesDto> dtoList =new ArrayList<AdmissionAttributeValuesDto>();

		Class<?> cl = admissionModel.getClass();
		Field[] fields = cl.getDeclaredFields();
		try{
			for(Field f : fields){
				String fieldName = f.getName();

				List<AdmissionAttributesDto> list= attributesService.findAdmissionAttributeByFormField(fieldName);

				if(!CollectionUtils.isEmpty(list)){
					String methodName = fieldName.toUpperCase().substring(0, 1) + fieldName.substring(1);
					Method method = cl.getDeclaredMethod("get"+methodName, null);
					if(method.invoke(admissionModel, null) instanceof String){
						AdmissionAttributeValuesDto dto =new AdmissionAttributeValuesDto();
						String fieldValue =  (String)method.invoke(admissionModel, null);
						if(!fieldValue.equalsIgnoreCase("null") && !fieldValue.equalsIgnoreCase("")){
							dto.setAdmissionAttributeId(list.get(0).getId());
							dto.setAttributeValue(fieldValue);
							dtoList.add(dto);
						}
					}else if(method.invoke(admissionModel, null) instanceof String[]){
						for(String fieldValue:(String[])method.invoke(admissionModel, null))
						{
							AdmissionAttributeValuesDto dto =new AdmissionAttributeValuesDto();
							for(AdmissionAttributesDto attrib:list){
								if(fieldValue.equals(attrib.getAttributeCode())){
									dto.setAdmissionAttributeId(attrib.getId());
									dto.setAttributeValue(fieldValue);
									dtoList.add(dto);
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		PatientEpisodeDto patientEpisodeDto = preparePatientEpisode(admissionModel);
		PatientDto patientDto = patientEpisodeDto.getPatient();
		Long patientEpisodeId = patientEpisodeDto.getPatientEpisodeId();

		PatientInfoDto patientInfoDto = new PatientInfoDto();
		patientInfoDto.setPatientEpisodeId(patientEpisodeId);
		patientInfoDto.setPatientId(patientDto.getPatientId());
		patientInfoDto.setPatientEncounterId(patientEpisodeDto.getPatientEncounterId());
		attributesService.saveAdmissionAttributeValues(dtoList,patientEpisodeId );
		return patientInfoDto;
	}

	public void saveMedicalReconciliation(List<MedicalReconciliationDto> medicalReconcilationList,String user){
		admissionService.saveMedicalReconciliation(medicalReconcilationList, user);
	}

	public Boolean checkStopAndWatchEligibility(AdmissionForm admissionData){

		/*if((admissionData.getOtherDirectiveCheckBox() ? !StringUtils.isEmpty(admissionData.getOtherAdvanceDirectives()) :true ) 
					&& (admissionData.getGoalsOfCareDiscussed().equalsIgnoreCase("YES")  ? !StringUtils.isEmpty(admissionData.getSpecifyGoalsOfCareDiscussed()):true)
					&& (admissionData.getNurseToNurseVerbalReport().equalsIgnoreCase("YES") ? !StringUtils.isEmpty(admissionData.getNurseToNurseVerbalReportText()):true)
					&& (admissionData.getHRC_OR_TI_AC_R_OTHR()? !StringUtils.isEmpty(admissionData.getHRC_OR_TI_AC_R_OTHR_TEXT()):true)
					&& (admissionData.getHRC_OR_TI_GI_OTHR() ? !StringUtils.isEmpty(admissionData.getHRC_OR_TI_GI_OTHR_TEXT()):true)
					&& (admissionData.getNCPSF_SC_PU() ? (!StringUtils.isEmpty(admissionData.getNCPSF_SC_PU_STAGE()) && !StringUtils.isEmpty(admissionData.getNCPSF_SC_PU_LOCATION())):true)
					&& (admissionData.getNCPSF_SC_SPU() ? (!StringUtils.isEmpty(admissionData.getNCPSF_SC_SPU_STAGE()) && !StringUtils.isEmpty(admissionData.getNCPSF_SC_SPU_LOCATION())):true)){
		 */
		//		if(admissionData.getFullCode() && admissionData.getDnr() && admissionData.getDni() && admissionData.getDnh() &&
		//				admissionData.getNoArtificialFeeding() && admissionData.getHospiceCare() 
		//				&& (admissionData.getCanTakeDecision() || (admissionData.getReqProxy() == null ? false:true))
		//				&& !StringUtils.isEmpty(admissionData.getGoalsOfCareDiscussed()) && !StringUtils.isEmpty(admissionData.getHospitalName()) && !StringUtils.isEmpty(admissionData.getHospitalUnit())
		//				&& !StringUtils.isEmpty(admissionData.getDischargingRN()) && !StringUtils.isEmpty(admissionData.getDishcargingRNTelephoneNumber())
		//				&& !StringUtils.isEmpty(admissionData.getDischargingMD()) && !StringUtils.isEmpty(admissionData.getDishcargingMDTelephoneNumber())
		//				&& !StringUtils.isEmpty(admissionData.getHospitalAdmissionDate()) && !StringUtils.isEmpty(admissionData.getNurseToNurseVerbalReport())
		//				&& !StringUtils.isEmpty(admissionData.getPrimaryCarePhysician()) && !StringUtils.isEmpty(admissionData.getPrimaryCarePhysicianTelephoneNumber())
		//				&& !StringUtils.isEmpty(admissionData.getSpecialist1()) && !StringUtils.isEmpty(admissionData.getSpecialist1Speciality())
		//				&& !StringUtils.isEmpty(admissionData.getSpecialist1TelephoneNumber()) && !StringUtils.isEmpty(admissionData.getSpecialist2())
		//				&& !StringUtils.isEmpty(admissionData.getSpecialist2Speciality()) && !StringUtils.isEmpty(admissionData.getSpecialist2TelephoneNumber())
		//				&& !StringUtils.isEmpty(admissionData.getKCIVS_02SAT()) && !StringUtils.isEmpty(admissionData.getKCID_PDD()) && !StringUtils.isEmpty(admissionData.getKCID_OMD())
		//				&& !StringUtils.isEmpty(admissionData.getKCID_MHD())
		//				&& admissionData.getHRC_OR_TI_AC() && admissionData.getHRC_OR_TI_AC_R_AFIB() && admissionData.getHRC_OR_TI_AC_R_DVT_OR_PE()
		//				&& admissionData.getHRC_OR_TI_AC_R_MECVAL() && admissionData.getHRC_OR_TI_AC_R_POP() && admissionData.getHRC_OR_TI_AC_R_LOWEF()
		//				&& admissionData.getHRC_OR_TI_OAB() && !StringUtils.isEmpty(admissionData.getHRC_OR_TI_OAB_INDCTN())
		//				&& !StringUtils.isEmpty(admissionData.getHRC_OR_TI_OAB_TTCD()) && !StringUtils.isEmpty(admissionData.getHRC_OR_TI_OAB_DS())
		//				&& admissionData.getNCPSF_SC_NSB() && admissionData.getNCPSF_SC_PU() && !StringUtils.isEmpty(admissionData.getNCPSF_SC_PU_STAGE()) &&  !StringUtils.isEmpty(admissionData.getNCPSF_SC_PU_LOCATION())
		//				&& admissionData.getNCPSF_SC_SPU() && !StringUtils.isEmpty(admissionData.getNCPSF_SC_SPU_STAGE()) &&  !StringUtils.isEmpty(admissionData.getNCPSF_SC_SPU_LOCATION())){
		//			//		if(!StringUtils.isEmpty(admissionData.getCaregiverName())){
		return true;
		/*}*/
		/*return false;*/
	}

	private void prepareAdmissionForm(AdmissionForm admissionForm,AdmissionDto admDto){

		admissionForm.setPatientFirstName(admDto.getPatientFirstName());
		admissionForm.setPatientLastName(admDto.getPatientLastName());
		admissionForm.setDob(admDto.getDob());
		admissionForm.setDoj(admDto.getDoj());
		admissionForm.setsSNumber(admDto.getsSNumber());
		admissionForm.setPcpWorkNumber(admDto.getPcpWorkNumber());
		admissionForm.setPcpAtSite(admDto.getPcpAtSite());
		admissionForm.setPatientGender(admDto.getPatientGender());
		if(StringUtils.isEmpty(admDto.getCanSpeakEnglish())){
			admissionForm.setOtherLanguage(admDto.getOtherLanguage());
			admissionForm.setCanSpeakEnglish("Other");
		}else{
			admissionForm.setCanSpeakEnglish(admDto.getCanSpeakEnglish()?"English":"Other");
		}
		admissionForm.setEthnicityType(admDto.getEthnicityType());
		admissionForm.setOtherEthnicityType(admDto.getOtherEthnicityType());
		admissionForm.setRoomNumber(admDto.getRoomNumber());
		admissionForm.setCaregiverName(admDto.getCaregiverName());
		admissionForm.setCareGiverTelephoneNumber(admDto.getCareGiverTelephoneNumber());
		admissionForm.setGuardianName(admDto.getGuardianName());
		admissionForm.setGuardianTelephoneNumber(admDto.getGuardianTelephoneNumber());
		admissionForm.setFullCode(admDto.getFullCode());
		admissionForm.setDnr(admDto.getDnr());
		admissionForm.setDni(admDto.getDni());
		admissionForm.setDnh(admDto.getDnh());
		for(Map.Entry<String, String> entry:AdmissionForm.CARETYPES.entrySet()){
			if(!StringUtils.isEmpty(admDto.getPatientCareTypes()) &&  admDto.getPatientCareTypes().equals(entry.getValue())){
				admissionForm.setPatientCareTypes(entry.getKey());
			}
		}
		for(Map.Entry<String, String> entry:AdmissionForm.INSURENCEPLAN.entrySet()){
			if(!StringUtils.isEmpty(admDto.getPatientInsurencePlan()) &&  admDto.getPatientInsurencePlan().equals(entry.getValue())){
				admissionForm.setPatientInsurencePlan(entry.getKey());
			}
		}
		admissionForm.setNoArtificialFeeding(admDto.getNoArtificialFeeding());
		admissionForm.setComfortCare(admDto.getComfortCare());
		admissionForm.setHospiceCare(admDto.getHospiceCare());
//		admissionForm.setOtherDirectiveCheckBox(admDto.getOtherDirectiveCheckBox()); // missing in table
		admissionForm.setOtherDirectiveCheckBox(StringUtils.isEmpty(admDto.getOtherAdvanceDirectives())?false:true);
		admissionForm.setOtherAdvanceDirectives(admDto.getOtherAdvanceDirectives());
		admissionForm.setGoalsOfCareDiscussed(admDto.getGoalsOfCareDiscussed());
		admissionForm.setSpecifyGoalsOfCareDiscussed(admDto.getSpecifyGoalsOfCareDiscussed());
		admissionForm.setCanTakeDecision(admDto.getCanTakeDecision());
		admissionForm.setReqProxy(admDto.getReqProxy());
		admissionForm.setNurseToNurseVerbalReport(admDto.getNurseToNurseVerbalReport());
		admissionForm.setHospitalName(admDto.getHospitalName());
		admissionForm.setHospitalName(admDto.getHospitalName());
		admissionForm.setHospitalId(admDto.getHospitalId());
		admissionForm.setHospitalId(admDto.getHospitalId());
		admissionForm.setHospitalUnit(admDto.getHospitalUnit());
		admissionForm.setDischargingRN(admDto.getDischargingRN());
		admissionForm.setDischargingRNId(admDto.getDischargingRNId());
		admissionForm.setDishcargingRNTelephoneNumber(admDto.getDishcargingRNTelephoneNumber());
		admissionForm.setDischargingMD(admDto.getDischargingMD());
		admissionForm.setDischargingMDId(admDto.getDischargingMDId());
		admissionForm.setDishcargingMDTelephoneNumber(admDto.getDishcargingMDTelephoneNumber());
		admissionForm.setHospitalAdmissionDate(admDto.getHospitalAdmissionDate());
		admissionForm.setFromHospitalDischargeDate(admDto.getFromHospitalDischargeDate());
		admissionForm.setTransferredToPostAcuteCare(admDto.getTransferredToPostAcuteCare());
		admissionForm.setFacilityId(admDto.getFacilityId());
		admissionForm.setFacilityId(admDto.getFacilityId());
		admissionForm.setPatientId(admDto.getPatientId());
		//		admissionForm.setAdmittedByStaffId(admDto.getadmittedByStaffId); TODO need to see
		//		admissionForm.setAdmittedByStaffName(admDto.getadmittedByStaffName); TODO need to see
		admissionForm.setPatientEpisodeId(admDto.getPatientEpisodeId());
		admissionForm.setgCareGiverId(admDto.getgCareGiverId());
		admissionForm.setgProxyId(admDto.getgProxyId());
		admissionForm.setPostAcuteCareTelephoneNumber(admDto.getPostAcuteCareTelephoneNumber());
		admissionForm.setNurseToNurseVerbalReport(admDto.getNurseToNurseVerbalReport());
		admissionForm.setNurseToNurseVerbalReportText(admDto.getNurseToNurseVerbalReportText());

		if(admDto.getHsptlPhyCareTeam().size() >= 1){
			admissionForm.setPrimaryCarePhysician(admDto.getHsptlPhyCareTeam().get(0).getStaffName());
			admissionForm.setPrimaryCarePhysicianId(admDto.getHsptlPhyCareTeam().get(0).getHospitalStaffId());
			admissionForm.setPrimaryCarePhysicianTelephoneNumber(admDto.getHsptlPhyCareTeam().get(0).getContactNumber());
		}

		if(admDto.getHsptlPhyCareTeam().size() >= 2){
			admissionForm.setSpecialist1(admDto.getHsptlPhyCareTeam().get(1).getStaffName());
			admissionForm.setSpecialist1Id(admDto.getHsptlPhyCareTeam().get(1).getHospitalStaffId());
			admissionForm.setSpecialist1Speciality(admDto.getHsptlPhyCareTeam().get(1).getSpecialityName());
			admissionForm.setSpecialist1SpecialityId(admDto.getHsptlPhyCareTeam().get(1).getSpecialityId()); 
			admissionForm.setSpecialist1TelephoneNumber(admDto.getHsptlPhyCareTeam().get(1).getContactNumber());
		}

		if(admDto.getHsptlPhyCareTeam().size() >= 3){
			admissionForm.setSpecialist2(admDto.getHsptlPhyCareTeam().get(2).getStaffName());
			admissionForm.setSpecialist2Id(admDto.getHsptlPhyCareTeam().get(2).getHospitalStaffId());
			admissionForm.setSpecialist2Speciality(admDto.getHsptlPhyCareTeam().get(2).getSpecialityName());
			admissionForm.setSpecialist2SpecialityId(admDto.getHsptlPhyCareTeam().get(2).getSpecialityId()); 
			admissionForm.setSpecialist2TelephoneNumber(admDto.getHsptlPhyCareTeam().get(2).getContactNumber());
		}

		admissionForm.setHospitalCareTeamId(admDto.getHospitalCareTeamId());
		admissionForm.setListProcedures(admDto.getListProcedures());
		admissionForm.setKeyFindings(admDto.getKeyFindings());
		admissionForm.setKeyFindings(admDto.getKeyFindings());
		admissionForm.setSummarizeHighPriorityNeeds(admDto.getSummarizeHighPriorityNeeds());
		admissionForm.setPendingLabAndTestResults(admDto.getPendingLabAndTestResults());
		admissionForm.setRecommendedFollowupTests(admDto.getRecommendedFollowupTests());
		admissionForm.setMedicalReconciliationDto(admDto.getMedicalReconList());

		List<AdmissionFormAttribNdValuesDto> admissionAttribValuesList  = attributesService.getAdmissionAttribValuesByPatientEpisodeId(admDto.getPatientEpisodeId());
		Map<String,List<String>>  map= prepareAdmissionAttribValuesMapping(admissionAttribValuesList, admissionForm);

		for(String st:map.keySet()){
			switch(st){
			case "inAdmissionhrcOrTiAcReasons":
				admissionForm.setInAdmissionhrcOrTiAcReasons(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionGoalInr":
				admissionForm.setInAdmissionGoalInr(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionHrcOrTiOpIndications":
				admissionForm.setInAdmissionHrcOrTiOpIndications(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionDevices":
				admissionForm.setInAdmissionDevices(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionContinence":
				admissionForm.setInAdmissionContinence(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionReasonforcatheter":
				admissionForm.setInAdmissionReasonforcatheter(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionNcpsfC":
				admissionForm.setInAdmissionNcpsfC(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionEatingInstructions":
				admissionForm.setInAdmissionEatingInstructions(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionNcpsfTtdPicc":
				admissionForm.setInAdmissionNcpsfTtdPicc(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionCardiac":
				admissionForm.setInAdmissionCardiac(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionRespiratory":
				admissionForm.setInAdmissionRespiratory(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionTherapies":
				admissionForm.setInAdmissionTherapies(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionRisksandPrecautions":
				admissionForm.setInAdmissionRisksandPrecautions(map.get(st).toArray(new String[map.get(st).size()]));
				break;
			case "inAdmissionInfectionRColonization":
				admissionForm.setInAdmissionInfectionRColonization(map.get(st).toArray(new String[map.get(st).size()]));
				break;

			}

		}

	}

	@RequestMapping(value = "/checkSSN", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody SSNBean checkSSNumberforPatient(@RequestBody SSNBean ssn,HttpServletRequest req){
		SSNBean result = new SSNBean();
		try{
			HttpSession session = req.getSession();
			Long currentFacilityId = (Long)session.getAttribute("facilityId");
			SSNCheckDto	ssnCheckDto = admissionService.checKSSNforPatient(ssn.getSSN());
			if(StringUtils.isEmpty(ssnCheckDto)){
				result.setStatus("New");
				return result;
			}
			result.setPatientId(ssnCheckDto.getPatientId());
			result.setSSN(ssnCheckDto.getSSN());
			result.setFacilityId(ssnCheckDto.getFacilityId());
			result.setFdischrgeDt(ssnCheckDto.getFdischrgeDt());
			if(result.getFacilityId() == currentFacilityId){
				result.setStatus("ok");
			}else{
				result.setStatus("duplicate");
			}
			return result;
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
			result.setStatus("Failed");
			return result;
		}
	}

}
