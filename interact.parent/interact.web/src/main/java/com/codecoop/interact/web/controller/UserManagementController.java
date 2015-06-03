package com.codecoop.interact.web.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecoop.interact.core.dto.FStaffRegistrationDto;
import com.codecoop.interact.core.dto.FacilityStaffDto;
import com.codecoop.interact.core.dto.UserManageDto;
import com.codecoop.interact.core.service.FacilityStaffService;
import com.codecoop.interact.core.service.ManageStaffService;
import com.codecoop.interact.core.service.PatientEpisodeService;
import com.codecoop.interact.core.service.StaffService;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.core.util.PasswordGenUtil;
import com.codecoop.interact.web.model.AcuteCareModel;
import com.codecoop.interact.web.model.AdmissionForm;
import com.codecoop.interact.web.model.DashboardForm;
import com.codecoop.interact.web.model.FacilityModel;
import com.codecoop.interact.web.model.SessionManagedLists;
import com.codecoop.interact.web.model.StaffDetailsResponse;
import com.codecoop.interact.web.model.StaffRegistrationForm;
import com.codecoop.interact.web.model.StopAndWatchForm;
import com.codecoop.interact.web.model.UserManageMentResponse;
import com.codecoop.interact.web.util.DateUtil;


@RequestMapping("/mangeUser")
@Controller
public class UserManagementController extends SessionManagedLists{
	private static final Logger LOG = Logger.getLogger(UserManagementController.class);
	@Autowired
	private FacilityStaffService facilityStaffService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private ManageStaffService manageStaffService;

	@Autowired
	private PatientEpisodeService patientEpisodeService;

	@RequestMapping(value="/facilityStaff",method={RequestMethod.GET})
	public @ResponseBody UserManageMentResponse getFacilityStaff(@RequestParam(value="searchUserName") String searchUserName,  
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap){
		modelMap.addAttribute("dashboardForm", new DashboardForm());
		modelMap.addAttribute("staffRegistrationForm", new StaffRegistrationForm());
		modelMap.addAttribute("acuteCareModel", new AcuteCareModel());

		HttpSession session = request.getSession(true);
		UserManageMentResponse userMangeResp=new UserManageMentResponse();

		List<UserManageDto> userManagerDtoList = new ArrayList<UserManageDto>();

		Long facilityId = (Long)session.getAttribute("facilityId");
		Long staffId = (Long)session.getAttribute("staffId");

		boolean isRoleAdmin = request.isUserInRole(Constants.ADMIN_ROLE); 
		boolean isRoleSuperAdmin = request.isUserInRole(Constants.SUPER_ADMIN_ROLE); 

		if(StringUtils.isEmpty(searchUserName)){
			userManagerDtoList = staffService.getAllStaffMembersInFacility(facilityId);
		}else{
			userManagerDtoList = staffService.getAllStaffMembersIByUserName(searchUserName, facilityId);
		}
		List<List<String>> toData=new ArrayList<List<String>>();
		if(!CollectionUtils.isEmpty(userManagerDtoList)){
			for(UserManageDto userManageDto:userManagerDtoList){
				List<String> data=new ArrayList<String>();
				data.add(userManageDto.getName());
				data.add(userManageDto.getUserName());
				data.add(userManageDto.getPersonalEmail());
				data.add(userManageDto.getMobileNumber());
				//				data.add(String.valueOf(userManageDto.getRoleId()));
				//				data.add(facilityStaffService.findRoleByRoleId(userManageDto.getRoleId()).getDescription());
				data.add(userManageDto.getRoleDescription());
				//				data.add((String.valueOf(userManageDto.getFacilityId())));
				//				data.add(facilityStaffService.findFacilityById(userManageDto.getFacilityId()).getFacilityName());
				data.add(userManageDto.getFacilityName());
				boolean inFacility = false;
				if(facilityId == userManageDto.getFacilityId()){
					inFacility = true;
				}
				if(isRoleAdmin || isRoleSuperAdmin){
					if(inFacility){
						data.add("true|true|"+ userManageDto.getStaffId() + "|" + userManageDto.isActiveUser());
					}else{
						data.add("true|false|"+ userManageDto.getStaffId() + "|" + userManageDto.isActiveUser());
					}
				}else{
					data.add("false|true|"+ userManageDto.getStaffId() + "|" + userManageDto.isActiveUser());
				}
				data.add(String.valueOf(userManageDto.getStaffId()));
				if(isRoleSuperAdmin){
					data.add("4|5");
					session.setAttribute("hideRow", "4|5");
				}else{
					data.add("2|3");
					session.setAttribute("hideRow", "2|3");
				}
				toData.add(data);
			} 
		}
		userMangeResp.setData(toData);
		userMangeResp.setRecordsTotal(toData.size());
		return userMangeResp;
	}

	@RequestMapping(value="/getStaffDetails",method={RequestMethod.GET})
	public @ResponseBody StaffDetailsResponse getAllDataForAStaff(@RequestParam(value="staffId") Long staffId,@RequestParam(value="operation") String operation,
			HttpServletRequest request){
		Long facilityId = (Long)request.getSession(true).getAttribute("facilityId");
		StaffDetailsResponse staffDetailsResp=new StaffDetailsResponse(manageStaffService.getAllDataForAStaff(staffId, facilityId), operation);
		staffDetailsResp.setOperation(operation);
		return staffDetailsResp;
	}

	@RequestMapping(value = "/registration", method = {RequestMethod.POST})
	public String registrationSubmitPage(
			@Valid @ModelAttribute("staffRegistrationForm") StaffRegistrationForm registration,
			BindingResult result, HttpServletRequest req, ModelMap modelMap) {
		modelMap.addAttribute("dashboardForm", new DashboardForm());
		modelMap.addAttribute("stopAndWatchForm",new StopAndWatchForm());
		modelMap.addAttribute("admissionForm", new AdmissionForm());
		modelMap.addAttribute("acuteCareModel", new AcuteCareModel());
		modelMap.addAttribute("facilityModel",new FacilityModel());

		HttpSession session = req.getSession(true);
		Long facilityId=(Long)session.getAttribute("facilityId");
		String url=(String)session.getAttribute("url");
		//	String url = req.getRequestURI().toString();
		if (result.hasErrors()) {
			req.setAttribute("errorPage", "RegistrationModalError");
			//			req.setAttribute("errors", "true"); errorPage=RegistrationModalError";
			return "carecent";
		}
		String status=null;
		boolean isRoleAdmin = req.isUserInRole(Constants.ADMIN_ROLE);
		boolean isRoleSuperAdmin =  req.isUserInRole(Constants.SUPER_ADMIN_ROLE);
		SecureRandom random = new SecureRandom();
		FStaffRegistrationDto fStaffregDto = populateFStaffRegistrationDto(registration, req.getUserPrincipal().getName(), facilityId);
		if("new".equals(registration.getOperation())){
			try{
				fStaffregDto.setPasswd(PasswordGenUtil.generatePswd());
				//req.getRequestURL();
				facilityStaffService.saveNewStaffMember(fStaffregDto,url,facilityId);
				status = "Staff Registration Successful, User ID and Temporary Password sent to registered Email";
			}catch(Exception e){
				e.printStackTrace();
				LOG.error(e.getMessage(), e);
				status = "Staff Member Creation Failed";
			}
		}else if("add".equals(registration.getOperation())){
			try{
				facilityStaffService.addStaffMemberToFacility(fStaffregDto,url);
				status = "Staff Member Added Successfully.......";
			}catch(Exception e){
				e.printStackTrace();
				LOG.error(e.getMessage(), e);
				status = "Unable to add Staff member due to an unexpected error... please try again.......";
			}
		}else if("edit".equals(registration.getOperation())){
			try{
				facilityStaffService.editStaffMember(fStaffregDto,url);
				status = "Profile  Updated Successfully.........";
			}catch(Exception e){
				e.printStackTrace();
				LOG.error(e.getMessage(), e);
				status = "Profile Updation Failed";
			}
		}
		return "redirect:/welcome?fromPage=registration&isRoleAdmin="+isRoleAdmin+"&status="+status+"&isRoleSuperAdmin="+isRoleSuperAdmin;

	}

	private FStaffRegistrationDto populateFStaffRegistrationDto(StaffRegistrationForm registration, String userName, Long facilityId){
		FStaffRegistrationDto fStaffregDto =new FStaffRegistrationDto(registration.getFirstName(),registration.getLastName(),
				registration.getMiddleName(),registration.getUserName(),
				registration.getStaffRoleId(),registration.getEmpId(), registration.getGender(),
				DateUtil.string2Date(registration.getDateOfHire()) , DateUtil.string2Date(registration.getRelievingDate()),registration.getAddress(), 
				registration.getStreet(), registration.getCity(), registration.getState(),registration.getZipcode() ,
				registration.getContactType() ,registration.getMobileNo(),registration.getEmail(),registration.getWorkNumber(),
				registration.getWorkEmail().trim(),true/*req.getParameter("primaryFlag")*/,
				userName, new Date(),facilityId, registration.getStaffId(),registration.isDeactive(),registration.getDocRoleId());

		return fStaffregDto;
	}

	@RequestMapping(value = { "/generateUser" }, method = RequestMethod.GET)
	public @ResponseBody String registrationValidating(
			@ModelAttribute("registration") StaffRegistrationForm registration,
			HttpServletRequest req, HttpServletResponse resp)
					throws IOException {
		String firstName =  req.getParameter("firstName");
		String lastName =  req.getParameter("lastName");
		if(firstName.length() < 4 || lastName.length() < 4 ){
			return "";
		}
		int i = 1;
		boolean cont = true;
		String name;
		do {
			name = firstName.trim().substring(0, 4)
					+ lastName.trim().substring(0, 4) + i;

			i++;
			if (staffService.findStaffByUserName(name) == null) {
				registration.setUserName(name);
				cont = false;
			}
		} while (cont);
		return registration.getUserName();
	}

	@RequestMapping(value="/findStates",method={RequestMethod.GET})
	public @ResponseBody String[] getStates(/*@RequestParam(value="state") Long state,*/HttpServletRequest request){

		int size=staffService.getAllStates().size();
		return staffService.getAllStates().toArray(new String[size]);
	}

	@RequestMapping(value="/getDoctorsInFacility",method={RequestMethod.GET})
	public @ResponseBody List<StaffDetailsResponse> getMDPAofCureentFacility(HttpServletRequest request){

		List<StaffDetailsResponse> responeList = new ArrayList<StaffDetailsResponse>();
		Long facilityId = (Long)request.getSession(true).getAttribute("facilityId");
		if(facilityId !=null){
			List<FacilityStaffDto> staffListDto = facilityStaffService.getMDPAofCurrentFacility(facilityId);
			for(FacilityStaffDto list:staffListDto){
				StaffDetailsResponse respone = new StaffDetailsResponse();
				respone.setStaffId(list.getStaffId());
				respone.setFirstName(list.getName());
				respone.setWorkNumber(list.getWorkNumber());
				responeList.add(respone);
			}
		}
		return responeList;

	}
//	@RequestMapping(value="/getDocMdInFacility",method={RequestMethod.GET})
//	public @ResponseBody List<StaffDetailsResponse> getDocMdofCurrentFacility(HttpServletRequest request){
//		
//				List<StaffDetailsResponse> responeList = new ArrayList<StaffDetailsResponse>();
//				Long facilityId = (Long)request.getSession(true).getAttribute("facilityId");
//				if(facilityId !=null){
//					List<FacilityStaffDto> staffListDto = facilityStaffService.getMDofCurrentFacility(facilityId);
//					for(FacilityStaffDto list:staffListDto){
//						StaffDetailsResponse respone = new StaffDetailsResponse();
//						respone.setfStaffId(list.getId());
//						respone.setFirstName(list.getName());
//						respone.setWorkNumber(list.getWorkNumber());
//						responeList.add(respone);
//					}
//				}
//		return null;
//	}

	@RequestMapping(value="/getPatientAsigndDoc",method={RequestMethod.GET})
	public @ResponseBody FacilityStaffDto getPatientAsigndDoc(HttpServletRequest request){
		Long patientEposodeId=Long.parseLong(request.getParameter("patientEpisodeId"));
		FacilityStaffDto facilityStaffDto=patientEpisodeService.getPatientAsignedDoc(patientEposodeId);
		return facilityStaffDto;
	}
}