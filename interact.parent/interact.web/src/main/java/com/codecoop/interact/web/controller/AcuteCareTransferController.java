package com.codecoop.interact.web.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codecoop.interact.core.domain.AcuteCareAttributes;
import com.codecoop.interact.core.dto.AcuteCareAttributesValuesDto;
import com.codecoop.interact.core.dto.AcuteCareDto;
import com.codecoop.interact.core.dto.AcuteCareFormAttibNdValuesDto;
import com.codecoop.interact.core.dto.AdmissionDto;
import com.codecoop.interact.core.dto.FacilityStaffDto;
import com.codecoop.interact.core.model.LoggedUserModel;
import com.codecoop.interact.core.service.AcuteCareService;
import com.codecoop.interact.core.service.FacilityStaffService;
import com.codecoop.interact.core.service.PatientEpisodeService;
import com.codecoop.interact.web.model.AcuteCareModel;
import com.codecoop.interact.web.model.AdmissionForm;
import com.codecoop.interact.web.model.DashboardForm;
import com.codecoop.interact.web.model.FacilityModel;
import com.codecoop.interact.web.model.StaffRegistrationForm;
import com.codecoop.interact.web.model.StopAndWatchForm;
import com.codecoop.interact.web.util.DateUtil;

@Controller
@RequestMapping(value ="/acuteCare")
public class AcuteCareTransferController {

	private static final Logger LOG = Logger.getLogger(AcuteCareTransferController.class);
	@Autowired
	AcuteCareService acuteCareService;

	@Autowired
	AcuteCareModel acuteCareModelObj;
	@Autowired
	FacilityStaffService facilityStaffService;
	@Autowired
	PatientEpisodeService patientEpisodeService;

	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	//	public @ResponseBody AcuteCareModel  save(@ModelAttribute("acuteCareModel") AcuteCareModel acuteCareModel,ModelMap modelMap){
	public ModelAndView  save(@Valid @ModelAttribute("acuteCareModel") AcuteCareModel acuteCareModel,BindingResult result,ModelMap modelMap,HttpServletRequest request,HttpServletRequest req){

		modelMap.addAttribute("dashboardForm", new DashboardForm());
		modelMap.addAttribute("staffRegistrationForm", new StaffRegistrationForm());
		modelMap.addAttribute("stopAndWatchForm", new StopAndWatchForm());
		modelMap.addAttribute("admissionForm", new AdmissionForm());
		modelMap.addAttribute("facilityModel",new   FacilityModel());

		if(result.hasErrors()){
			return new ModelAndView("carecent", "openAcuteCare", "true");	
		}
		AcuteCareDto acuteCareDto = new AcuteCareDto();
		HttpSession session = request.getSession();
		LoggedUserModel user=(LoggedUserModel)session.getAttribute("loggedUser");
		// setting data to dto which is from form.....
		acuteCareDto.setPrimDiagForAdmission(acuteCareModel.getInAcutepdsforAdmission());
		acuteCareDto.setSentToHospital(acuteCareModel.getInAcutesentTo());
		//		acuteCareDto.setFormCompletedBy(acuteCareModel);
		if(!StringUtils.isEmpty(acuteCareModel.getInAcutedot()) && acuteCareModel.getInAcutedot().equalsIgnoreCase("0")){
		}else if(!StringUtils.isEmpty(acuteCareModel.getInAcutedot())){
			acuteCareDto.setDateOfTransfer(DateUtil.parseDate(acuteCareModel.getInAcutedot(), "MM/dd/yyyy hh:mm:ss"));
		}
		acuteCareDto.setSentFrom(acuteCareModel.getInAcutesendFrom());
		acuteCareDto.setFromUnit(acuteCareModel.getInAcuteunit());
		//		acuteCareDto.setFamilyAndOtherIssues(acuteCareModel.get);
		//		acuteCareDto.setCalledNurseForQuestion(acuteCareModel.getInAcutenhContactPName());
		acuteCareDto.setCalledNurseForQuestion(acuteCareModel.getInAcutenhContactPerson());
		acuteCareDto.setSocialWorkerName(acuteCareModel.getInAcutenhContactPName());
		acuteCareDto.setSocialWorkerTel(acuteCareModel.getInAcutenhContactPTel());
		if(!StringUtils.isEmpty(acuteCareModel.getInAcutenotifiedOfTransfer())){
			acuteCareDto.setTransferNotified(acuteCareModel.getInAcutenotifiedOfTransfer().equals("Yes")?true:false);
		}
		if(!StringUtils.isEmpty(acuteCareModel.getInAcuteawarOfClinicalSituation())){
			acuteCareDto.setClinicalSituationAware(acuteCareModel.getInAcuteawarOfClinicalSituation().equals("Yes")?true:false);
		}
		acuteCareDto.setPatientStatusInAcuteCare(acuteCareModel.getPatientStatusInAcuteCare());		

		acuteCareDto.setPatientId(acuteCareModel.getInAcutePatinetId());				// getting patientId from page
		//		acuteCareDto.setPatientEpisodeId(acuteCareModel.getPatientEpisodeId()); // getting pateintEpisodeId from page


		acuteCareDto.setFullCode(false);		// Default value for FullCode
		acuteCareDto.setDnr(false);				// Default value for DNR
		acuteCareDto.setDni(false);				// Default value for DNI
		acuteCareDto.setDnh(false);				// Default value for DNH
		acuteCareDto.setComfortCareOnly(false);	// Default value for COMFORTCAREONLY
		acuteCareDto.setUncertain(false);		// Default value for UNCERTAIN

		if(acuteCareModel.getInAcutecodeStatus() !=null ){
			for(String st:acuteCareModel.getInAcutecodeStatus()){
				switch(st){
				case "FullCode":
					acuteCareDto.setFullCode(true);
					break;
				case "DNR":
					acuteCareDto.setDnr(true);
					break;
				case "DNI":
					acuteCareDto.setDni(true);
					break;
				case "DNH":
					acuteCareDto.setDnh(true);
					break;
				case "ComfortCareOnly":
					acuteCareDto.setComfortCareOnly(true);
					break;
				case "Uncertain":
					acuteCareDto.setUncertain(true);
					break;
				}
			}
		}

		// Preparing acuteCare Attributes codes and values 
		String Status = acuteCareService.saveAcuteCareAttribValues(prepareAcuteCareAttrubuteValuesDto(acuteCareModel),acuteCareDto,user);
		if(!StringUtils.isEmpty(acuteCareModel.getPatientStatusInAcuteCare())){
			if(!StringUtils.isEmpty(acuteCareModel.getPatientStatusInAcuteCare()) && acuteCareModel.getPatientStatusInAcuteCare().equalsIgnoreCase("stay")){
				req.setAttribute("acuteCareStatus", "openPatient");
				req.setAttribute("patientId", acuteCareModel.getInAcutePatinetId());
			}
			return new ModelAndView("carecent", "openAcuteCare", "false");	
		}
		try{
			req.setAttribute("acuteCareStatus","Patient Details Saved Successfully......");
			req.setAttribute("acuteCareObj", "true");
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			req.setAttribute("acuteCareObj", "false");
			req.setAttribute("acuteCareStatus","Patient Details not saved please try again...");
			return new ModelAndView("carecent", "openAcuteCare", "false");	
		}
		return new ModelAndView("carecent", "openAcuteCare", "true");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/openAcuteCare",method = RequestMethod.POST)
	public  String  open(@ModelAttribute("acuteCareModel") AcuteCareModel acuteCareModel,ModelMap modelMap,HttpServletRequest request){

		modelMap.addAttribute("dashboardForm", new DashboardForm());
		modelMap.addAttribute("staffRegistrationForm", new StaffRegistrationForm());
		modelMap.addAttribute("stopAndWatchForm", new StopAndWatchForm());
		modelMap.addAttribute("admissionForm", new AdmissionForm());
		modelMap.addAttribute("facilityModel",new   FacilityModel());

		HttpSession session = request.getSession();
		Long facilityId = (Long)session.getAttribute("facilityId");
		Map<Long,FacilityStaffDto> nurse = (Map<Long,FacilityStaffDto>)session.getAttribute("nursesIncurrentFacility");
		String operation = acuteCareModel.getOpenMode();
		Map<Long, String> facilityMap = (Map<Long, String>)session.getAttribute("loggedInUserFacilityMap");
		String facilityName = facilityMap.get(facilityId);

		AdmissionDto patientDto = new AdmissionDto();
		patientDto.setPatientId(acuteCareModel.getInAcutePatinetId());

		patientDto = acuteCareService.getPatientFromAdmission(patientDto, facilityId);

		if(!StringUtils.isEmpty(patientDto)){

			acuteCareModel.setInAcutefirstName(patientDto.getPatientFirstName());
			acuteCareModel.setInAcutelastName(patientDto.getPatientLastName());
			acuteCareModel.setInAcutedob(patientDto.getDob());

			if(!StringUtils.isEmpty(patientDto.getCanSpeakEnglish())){
				acuteCareModel.setInAcutelanguage(patientDto.getCanSpeakEnglish()?"English":"Other");
			}else{
				acuteCareModel.setInAcutelanguage("Other");
				acuteCareModel.setInAcutelanguageText(patientDto.getOtherLanguage());
			}
			acuteCareModel.setInAcutedateAdmited(patientDto.getDoj());
			acuteCareModel.setInAcutesendFrom(facilityName);
			if(!StringUtils.isEmpty(patientDto.getCaregiverName())){

				acuteCareModel.setInAcutecntPerson(patientDto.getCaregiverName());
				acuteCareModel.setInAcutecntPsnTel(patientDto.getCareGiverTelephoneNumber());
				acuteCareModel.setInAcutecntPsnRelationShip("Relative");
			}else if(!StringUtils.isEmpty(patientDto.getGuardianName())){
				acuteCareModel.setInAcutecntPerson(patientDto.getGuardianName());
				acuteCareModel.setInAcutecntPsnTel(patientDto.getGuardianTelephoneNumber());
				acuteCareModel.setInAcutecntPsnRelationShip("Guardian");
			}
			acuteCareModel.setInAcutepccInNhName(patientDto.getPcpAtSiteName());
			acuteCareModel.setInAcutepccInNhTel(patientDto.getPcpWorkNumber());
			acuteCareModel.setInAcutepccInNh(patientDto.getPcpDesig());
			List<String> codeStatus = new ArrayList<String>();

			if(operation.equalsIgnoreCase("edit")){
				AcuteCareDto acuteCare = acuteCareService.getAcuteCareDtoByEpisodeId(patientDto.getPatientEpisodeId());
				if(!StringUtils.isEmpty(acuteCare)){
					patientDto.setFullCode(acuteCare.isFullCode());
					patientDto.setDnr(acuteCare.isDnr());
					patientDto.setDni(acuteCare.isDni());
					patientDto.setDnh(acuteCare.isDnh());
					patientDto.setComfortCare(acuteCare.isComfortCareOnly());
					if(acuteCare.isUncertain()){
						codeStatus.add("Uncertain");
					}
					acuteCareModel.setInAcutenhContactPerson(acuteCare.getCalledNurseForQuestion());
					if(!StringUtils.isEmpty(acuteCare.getCalledNurseForQuestion())){
						acuteCareModel.setInAcutenhContactPTel(nurse.get(acuteCare.getCalledNurseForQuestion()).getWorkNumber());
					}
					acuteCareModel.setInAcuteSnd2HsptlFlag(acuteCare.getSendToHospitalFlag());
					acuteCareModel.setStayInAcutecareFlag(acuteCare.getStayInAcutecareFlag());
					acuteCareModel.setInAcutepdsforAdmission(acuteCare.getPrimDiagForAdmission());
					acuteCareModel.setInAcutesentTo(acuteCare.getSentToHospital());
					acuteCareModel.setInAcutedot(StringUtils.isEmpty(acuteCare.getDateOfTransfer())?"":DateUtil.parseStringDate(acuteCare.getDateOfTransfer(), "MM/dd/yyyy HH:mm:ss"));
					acuteCareModel.setInAcutesendFrom(acuteCare.getSentFrom());
					acuteCareModel.setInAcuteunit(acuteCare.getFromUnit());
					//				from here we need clarification	FAMILY_AND_OTHER_ISSUES BEHAVIOURAL_ISSUES_AND_INTERVENTIONS

				}
			}
			if(!StringUtils.isEmpty(patientDto.getFullCode()) && patientDto.getFullCode()){
				codeStatus.add("FullCode");
			}
			if(!StringUtils.isEmpty(patientDto.getDnr()) && patientDto.getDnr()){
				codeStatus.add("DNR");
			}
			if(!StringUtils.isEmpty(patientDto.getDni()) && patientDto.getDni()){
				codeStatus.add("DNI");
			}
			if(!StringUtils.isEmpty( patientDto.getDnh()) && patientDto.getDnh()){
				codeStatus.add("DNH");
			}
			if(!StringUtils.isEmpty(patientDto.getComfortCare()) && patientDto.getComfortCare()){
				codeStatus.add("ComfortCareOnly");
			}

			acuteCareModel.setInAcutecodeStatus(codeStatus.toArray(new String[codeStatus.size()]));

			/********************************************  Attributes Section *********************************************/
			List<AcuteCareFormAttibNdValuesDto> pAcuteCareAttribValues = acuteCareService.getAcuteCareAttributeValuesByEpisodeId(patientDto.getPatientEpisodeId());
			Map<String,List<String>> result = prepareAcuteCareAttribValuesMapping(pAcuteCareAttribValues,acuteCareModel);

			for(String st:result.keySet()){
				switch(st){
				case "inAcuterelevantDiagnoses":
					acuteCareModel.setInAcuterelevantDiagnoses(result.get(st).toArray(new String[result.get(st).size()]));
					break;
				case "inAcuteriskAlerts":
					acuteCareModel.setInAcuteriskAlerts(result.get(st).toArray(new String[result.get(st).size()]));
					break;
				case "inAcutepbswResident":
					acuteCareModel.setInAcutepbswResident(result.get(st).toArray(new String[result.get(st).size()]));
					break;
				case "inAcutenharufconditions":
					acuteCareModel.setInAcutenharufconditions(result.get(st).toArray(new String[result.get(st).size()]));
					break;
				case "inAcutepgctOfTransfer":
					acuteCareModel.setInAcutepgctOfTransfer(result.get(st).toArray(new String[result.get(st).size()]));
					break;
				case "inAcuteigeneral":
					acuteCareModel.setInAcuteigeneral(result.get(st).toArray(new String[result.get(st).size()]));
					break;
				case "inAcuteimusculoskeletal":
					acuteCareModel.setInAcuteimusculoskeletal(result.get(st).toArray(new String[result.get(st).size()]));
					break;
				case "inAcuteumsAlert":
					acuteCareModel.setInAcuteumsAlert(result.get(st).toArray(new String[result.get(st).size()]));
					break;
				case "inAcuteufsAmbulates":
					acuteCareModel.setInAcuteufsAmbulates(result.get(st).toArray(new String[result.get(st).size()]));
					break;

				}

			}
		}

		request.setAttribute("openAcuteCare", "true");
		return "carecent";
	}

	private Map<String,List<String>> prepareAcuteCareAttribValuesMapping(List<AcuteCareFormAttibNdValuesDto> pAcuteCareAttribValues,AcuteCareModel acuteCareModel){

		Class<?> cl = acuteCareModel.getClass();
		Field[] fields = cl.getDeclaredFields();
		Map<String,List<String>> map =new HashMap<String,List<String>>();
		try{
			for(AcuteCareFormAttibNdValuesDto fieldObj:pAcuteCareAttribValues){
				for(Field f:fields){
					if(f.getName().equals(fieldObj.getAttibute())){
						f.setAccessible(true);
						if(!fieldObj.getType().equalsIgnoreCase("Array")){
							f.set(acuteCareModel, fieldObj.getAttibValue());
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

	private List<AcuteCareAttributesValuesDto> prepareAcuteCareAttrubuteValuesDto(AcuteCareModel acuteCareModel){

		List<AcuteCareAttributesValuesDto> dtoList =new ArrayList<AcuteCareAttributesValuesDto>();

		Class<?> cl = acuteCareModel.getClass();
		Field[] fields = cl.getDeclaredFields();
		try{
			for(Field f : fields){
				String fieldName = f.getName();

				List<AcuteCareAttributes> list= acuteCareService.getAttibuteDetailByFormAttrib(fieldName);

				if(!CollectionUtils.isEmpty(list)){
					String methodName = fieldName.toUpperCase().substring(0, 1) + fieldName.substring(1);
					Method method = cl.getDeclaredMethod("get"+methodName, null);
					if(method.invoke(acuteCareModel, null) instanceof String){
						AcuteCareAttributesValuesDto dto =new AcuteCareAttributesValuesDto();
						String fieldValue =  (String)method.invoke(acuteCareModel, null);
						if(!fieldValue.equalsIgnoreCase("null") && !fieldValue.equalsIgnoreCase("")){
							dto.setAcuteCareAttributeId(list.get(0).getId());
							dto.setAttributeValue(fieldValue);
							dtoList.add(dto);
						}
					}else if(method.invoke(acuteCareModel, null) instanceof String[]){
						for(String fieldValue:(String[])method.invoke(acuteCareModel, null))
						{
							AcuteCareAttributesValuesDto dto =new AcuteCareAttributesValuesDto();
							for(AcuteCareAttributes attrib:list){
								if(fieldValue.equals(attrib.getAttributeCode())){
									dto.setAcuteCareAttributeId(attrib.getId());
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
		return dtoList;
	}

	//	@RequestMapping(value="/stayInNursingHome", method={RequestMethod.GET})
	//	public @ResponseBody String stayInNursingHome (HttpServletRequest request){
	//		Long patientId=Long.parseLong(request.getParameter("patientId"));
	//		HttpSession session = request.getSession(true);
	//		Long staffId=(Long)session.getAttribute("staffId");
	//		Long facilityId =(Long)session.getAttribute("facilityId");
	//		FacilityStaff fStaff=facilityStaffService.findFacilityStaff(facilityId, staffId);
	//		try{
	//			PatientEpisode patientEpisode=patientEpisodeService.findActicePatientEpisode(patientId,facilityId);
	//			acuteCareService.stayInNursingHome(patientEpisode.getId(),fStaff);
	//			return "SUCESS" ;
	//		}catch(Exception e)
	//		{
	//			
	//			return "FAILED" ;
	//		}
	//		
	//	
	//		
	//	}

	//	@RequestMapping(value="/transferredToHospital", method={RequestMethod.GET})
	//	public @ResponseBody String transferredToHospital (HttpServletRequest request){
	//		Long patientId=Long.parseLong(request.getParameter("patientId"));
	//		HttpSession session = request.getSession(true);
	//		Long staffId=(Long)session.getAttribute("staffId");
	//		Long facilityId =(Long)session.getAttribute("facilityId");
	//		FacilityStaff fStaff=facilityStaffService.findFacilityStaff(facilityId, staffId);
	//		try{
	//			PatientEpisode patientEpisode=patientEpisodeService.findActicePatientEpisode(patientId,facilityId);
	//			acuteCareService.transferredToHospital(patientEpisode,fStaff);
	//			return "SUCESS" ;
	//		}catch(Exception e)
	//		{
	//			
	//			return "FAILED" ;
	//		}



	//	}

}
