package com.codecoop.interact.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.dto.FStaffRegistrationDto;
import com.codecoop.interact.core.model.LoggedUserModel;
import com.codecoop.interact.core.model.PatientDetailsModel;
import com.codecoop.interact.core.service.FacilityStaffService;
import com.codecoop.interact.core.service.MessageService;
import com.codecoop.interact.core.service.PatientEpisodeService;
import com.codecoop.interact.core.service.StaffService;
import com.codecoop.interact.core.service.StopAndWatchService;
import com.codecoop.interact.core.util.PasswordGenUtil;
import com.codecoop.interact.web.auth.InteractUserDetailsService;
import com.codecoop.interact.web.model.AcuteCareModel;
import com.codecoop.interact.web.model.AdmissionForm;
import com.codecoop.interact.web.model.DashboardForm;
import com.codecoop.interact.web.model.FacilityModel;
import com.codecoop.interact.web.model.SbarForm;
import com.codecoop.interact.web.model.SessionManagedLists;
import com.codecoop.interact.web.model.SessionPatientModel;
import com.codecoop.interact.web.model.StaffRegistrationForm;
import com.codecoop.interact.web.model.StopAndWatchForm;

@Controller
public class MainController extends SessionManagedLists{

	private static final Logger LOG =  Logger.getLogger(MainController.class);

	@Autowired
	InteractUserDetailsService interactUserDetailsService;

	@Autowired
	private FacilityStaffService facilityStaffService;

	@Autowired
	private StaffService staffService;
	@Autowired
	private StopAndWatchService stopAndWatchService;
	@Autowired
	private PatientEpisodeService patientEpisodeService;
	@Autowired
	MessageService messageService;
	@Autowired
	LoggedUserModel loggedUser;

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage(HttpServletRequest request,
			@ModelAttribute("dashboardForm") DashboardForm dashboardForm,
			@ModelAttribute("stopAndWatchForm") StopAndWatchForm stopAndWatchForm,
			@ModelAttribute("sbarForm") SbarForm sbarForm, ModelMap modelMap,
			@ModelAttribute("facilityModel") FacilityModel facilityModel,
			@ModelAttribute("acuteCareModel") AcuteCareModel acuteCareModel,
			Authentication auth) {
		if(request.isUserInRole("ROLE_GUEST")){
			return new ModelAndView("redirect:/j_spring_security_logout");
		}	
		String url = request.getRequestURL().toString();
		if(url.indexOf("welcome")!=-1)
		{ 
			url=url.substring(0, url.indexOf("welcome"));
		}
		HttpSession session = request.getSession(true);
		populateRoleMap(session,request);
		pupulateMedicineResolutions(session);
		String userName = request.getUserPrincipal().getName();
		Long staffId = staffService.getStaffIdByUserName(userName);
		session.setAttribute("staffId", staffId);
		session.setAttribute("url", url);
		//		Staff staff=staffService.findStaffById(staffId);
		LOG.info("**********START***********");
		LOG.info("LoggedUserId:"+userName);
		ModelAndView model = new ModelAndView();
		if(dashboardForm == null){
			modelMap.addAttribute("dashboardForm", new DashboardForm());
		}
		modelMap.addAttribute("staffRegistrationForm", new StaffRegistrationForm());
		if(stopAndWatchForm==null){
			modelMap.addAttribute("stopAndWatchForm", new StopAndWatchForm());
		}
		if( sbarForm==null)
		{
			modelMap.addAttribute("sbarForm", new SbarForm());
		}
		modelMap.addAttribute("dashboardForm", new DashboardForm());
		modelMap.addAttribute("staffRegistrationForm", new StaffRegistrationForm());
		modelMap.addAttribute("admissionForm", new AdmissionForm());
		LinkedHashMap<Long, String> loggedInUserFacilityMap = populateLoggedInUserFacilityMap(session, staffId);
		model.addObject("loggedInUserFacilityMap", loggedInUserFacilityMap);
		if((getFacilityID(session) == null || getFacilityID(session) == 0)){
			if(loggedInUserFacilityMap.size() == 1){
				session.setAttribute("facilityId", loggedInUserFacilityMap.keySet().toArray()[0]);
			}else{
				if(dashboardForm.getFacilityId() > 0){
					session.setAttribute("facilityId",dashboardForm.getFacilityId());
				}else{
					model.addObject("showFacilitySelect", "true");
					model.setViewName("carecent");
					return model;
				}
			}
		}
		loggedUser = loggedUserDetails(staffId,session);
		populateLoggedInUserFacilityMap(session, staffId);
		changeRoleForNewFacility();
		getDocMdofCurrentFacility(request);
		session.setAttribute("staffName", loggedUser.getFullName());
		session.setAttribute("role",loggedUser.getRoleName());

		if(!isAllPatientSearch(session.getAttribute("isAllPatientsSearch")) && (loggedUser.getRoleName().equalsIgnoreCase("PA")||
				loggedUser.getRoleName().equalsIgnoreCase("NP")|| loggedUser.getRoleName().equalsIgnoreCase("MD")))
		{
			createAssignedToMePatientsMap(session, staffId);
		}else if(!isAllPatientSearch(session.getAttribute("specificPatientsFlag"))){
			populateAllPatientsMap(session,(Long)session.getAttribute("facilityId"));
		}
		if (loggedUser.getUserModified() == null) {
			model.setViewName("carecent");
			model.addObject("firstTimeUser", true);
		} else {

			model.setViewName("carecent");
		}
		pupulateSbarPatientStatus(session,(Long)session.getAttribute("facilityId"));
		populateActiveFacilityStaff(session,(Long)session.getAttribute("facilityId"));
		populateNuresesInCurrenFacility(session);
		Map<Long, String> allFacilityMap = populateAllFacilityMap(session);
		model.addObject("allFacilityMap", allFacilityMap);

		return model;
	}

	private boolean isAllPatientSearch(Object allPatientsFlag) {
		if(allPatientsFlag != null){
			return (Boolean)allPatientsFlag ;
		}
		return false;
	}

	private void changeRoleForNewFacility(){
		Staff Staff = staffService.findStaffByUserName(loggedUser.getUserName());
		InteractUserDetailsService useDetailService = new InteractUserDetailsService();
		List<GrantedAuthority> authorities =  useDetailService.buildUserAuthority(loggedUser.getRoleValue());
		useDetailService.buildUserForAuthentication(Staff, authorities);
	}

	private Long getFacilityID(HttpSession session){
		if(session.getAttribute("facilityId") != null){
			return (Long)session.getAttribute("facilityId");
		}
		return null;
	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage(){
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return model;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String changePasscode(HttpServletRequest request,
			HttpServletResponse response) {
		String status;
		try{
			FStaffRegistrationDto fStaffregDto = new FStaffRegistrationDto();
			fStaffregDto.setUserName(request.getUserPrincipal().getName());
			String currentpassCode = PasswordGenUtil.encriptPassword(request.getParameter("currentPassword"));
			String passCode = PasswordGenUtil.encriptPassword(request.getParameter("password"));
			fStaffregDto.setCurrentPassword(currentpassCode);
			fStaffregDto.setPasswd(passCode);
			fStaffregDto.setUserModified(request.getUserPrincipal().getName());
			fStaffregDto.setDateModified(new Date());

			if(facilityStaffService.editStaffDetails(fStaffregDto)){
				facilityStaffService.editStaffDetails(fStaffregDto);
				status = "passChangedSuccess";
			}else{
				status = "Invalid";
			}

		}catch(Exception e){
			status= "passChangedFailed";	
		}
		return "redirect:welcome?status="+status;
	}

	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
	public String login(@RequestParam(value="authfailed", required=false) boolean authfailed,  
			@RequestParam(value="loggedout", required=false) boolean loggedout, 
			HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("authfailed", authfailed);
		request.setAttribute("loggedout", loggedout);
		return "login";
	}

	@RequestMapping(value = "/denied", method = {RequestMethod.POST, RequestMethod.GET})
	public String denied(HttpServletRequest request,
			HttpServletResponse response){

		return "denied";
	}

	@SuppressWarnings("unused")
	private String getRole(Authentication auth){
		String role = null;
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			role = grantedAuthority.getAuthority();
		}
		return role;
	}

	@RequestMapping(value="/getSearchNames", method={RequestMethod.GET})
	public  @ResponseBody String   getSearchNames(HttpServletRequest request)
	{   	
		HttpSession session=request.getSession();
		Long facilityId= (Long)session.getAttribute("facilityId");
		session.setAttribute("isAllPatientsSearch", true);
		List<PatientDetailsModel> patients=new ArrayList<PatientDetailsModel>();
		if(request.getParameter("searchId")!=""){
			session.setAttribute("specificPatientsFlag", true);
			String searchId=request.getParameter("searchId");
			String searchName[]=searchId.split(" ");
			patients=patientEpisodeService.findByPatientId(facilityId,searchName);
		}else{
			session.setAttribute("specificPatientsFlag", false);
		}
		session.setAttribute("patientsInCurrentFacility", populateFacilityPatientsMap(patients));
		return "success";	    
	}
	@RequestMapping(value="/findPatientsFromFacility", method={RequestMethod.GET})
	public @ResponseBody String   findPatientsFromFacility(HttpServletRequest request)
	{   
		HttpSession session=request.getSession();
		Long facilityId=Long.parseLong(request.getParameter("facilityId"));
		session.setAttribute("facilityId", facilityId);
		return "success";	    
	}
	@RequestMapping(value="/findAssignPatients", method={RequestMethod.GET})
	public @ResponseBody String   findAssignPatients(HttpServletRequest request)
	{  
		HttpSession session=request.getSession();
		session.setAttribute("specificPatientsFlag", false);
		session.setAttribute("isAllPatientsSearch", false);
		return "success";   
	}

	@RequestMapping(value="/checkSession", method={RequestMethod.GET})
	public @ResponseBody String checkSession(){
		return "valid";
	}

	private SessionPatientModel populateFacilityPatientsMap(List<PatientDetailsModel> listOfPatients){
		SessionPatientModel patientMod = new SessionPatientModel();
		LinkedHashMap<Long,PatientDetailsModel> map = new LinkedHashMap<Long,PatientDetailsModel>();
		for (PatientDetailsModel patient : listOfPatients){
			map.put(patient.getId(), patient);
			patientMod.setAdmissionQueueSize(patient.getAdmissionQueueCount());
			patientMod.setStopNWatchQueueSize(patient.getStopAndWathQueueCount());
			patientMod.setObservationQueueSize(patient.getObservationQueueCount());
			patientMod.setAcuteCareQueueSize(patient.getAcutecareQueueCount());
		}
		patientMod.setPatientDetailsModelMap(map);
		return patientMod;
	}

	//	@Override
	//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//		Staff Staff = staffService.findStaffByUserName(loggedUser.getUserName());
	//		List<GrantedAuthority> authorities =  interactUserDetailsService.buildUserAuthority(loggedUser.getRoleValue());
	//		System.out.println("VERRIDDEN UserDetails"+loggedUser.getRoleValue());
	//		System.out.println("authorities"+authorities);
	//		return interactUserDetailsService.buildUserForAuthentication(Staff, authorities);
	//	}
	
	

}