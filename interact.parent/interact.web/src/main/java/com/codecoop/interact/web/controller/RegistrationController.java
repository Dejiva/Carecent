//package com.codecoop.interact.web.controller;
//
//import java.io.IOException;
//import java.math.BigInteger;
//import java.security.SecureRandom;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.codecoop.interact.core.domain.FacilityStaffrole;
//import com.codecoop.interact.core.dto.FStaffRegistrationFormDto;
//import com.codecoop.interact.core.service.FacilityStaffSeravice;
//import com.codecoop.interact.web.auth.InteractUserDetailsService;
//import com.codecoop.interact.web.model.StaffRegistrationForm;
//import com.codecoop.interact.web.util.DateUtil;
//
//@Controller
//@RequestMapping("/registration")
//public class RegistrationController {
//	@Autowired	
//	InteractUserDetailsService interactUserDetailsService;
//
//	@Autowired
//	private FacilityStaffSeravice facilityStaffService;
//
//	private FStaffRegistrationFormDto fStaffregDto;
//
//	@Autowired
//	StaffRegistrationForm regFormBean;
//
//	@RequestMapping(method = RequestMethod.POST)
//	public String registrationSubmitPage(@Valid @ModelAttribute("registration") StaffRegistrationForm registration,BindingResult result,HttpServletRequest req) {
//
//		if(result.hasErrors())
//		{
//			System.out.println("Yes I have errors");
//			return "registration";
//
//		}
//
//		SecureRandom random=new SecureRandom();
//		registration.setPasswd(new BigInteger(20, random).toString());
//
//		fStaffregDto =new FStaffRegistrationFormDto(registration.getFirstName(), registration.getLastName(),
//				registration.getMiddleName(), registration.getUserName(), registration.getPasswd(), 
//				registration.getfStaffrole(), DateUtil.string2Date(registration.getDob()), registration.getGender(), 
//				DateUtil.string2Date(registration.getJoiningDate()), null, registration.getAddress(), 
//				registration.getStreet(), registration.getCity(), registration.getState(), registration.getZipcode(),
//				registration.getContactType(), registration.getContact(), registration.getPrimaryFlag(),new Date(),req.getUserPrincipal().getName(),null,null);
//
//		facilityStaffService.registrationSave(fStaffregDto);
//
//		//	Md5PasswordEncoder encoder=new Md5PasswordEncoder();
//		//	String encriptPass=encoder.encodePassword(registration.getPasscode(),null);
//
//		//	registration.setPasscode(encriptPass);
//
//
//		/*	registrationDto =new RegistrationDto(registration.getUserName(), registration.getPasscode(), 
//				registration.getEnabled(), registration.getRole(), registration.getMobile(), 
//				registration.getAddress1(), registration.getAddress2(), registration.getGender());
//
//		interactSampleService.registrationSave(registrationDto);
//		 */	
//
//		return "success";
//
//	}
//
//	@ModelAttribute("rolesList")
//	public List<String> populateRoleskList() {
//
//		List<String> rolesList=new ArrayList<String>();
//		for(FacilityStaffrole roles:facilityStaffService.findAllRoles()){
//			rolesList.add(roles.getFacilityStaffrole());
//		}
//		return rolesList;
//	}
//
//	@RequestMapping(value={"/input"}, method = RequestMethod.GET)
//	public String registrationPage(@ModelAttribute("registration") StaffRegistrationForm registration) {
//
//		return "registration";
//
//	}
//
//	@RequestMapping(value={"/generateUser"}, method = RequestMethod.GET)
//	public @ResponseBody String registrationValidating(@ModelAttribute("registration") StaffRegistrationForm registration,HttpServletRequest req,HttpServletResponse resp) throws IOException {
//
//		System.out.println("req.getParameter(firstName)"+req.getParameter("firstName"));
//		System.out.println("req.getParameter(lastName)"+req.getParameter("lastName"));
//		int i=1;
//		boolean cont=true;
//		String name;
//		do{
//			name=req.getParameter("firstName").substring(0, 4)+req.getParameter("lastName").substring(0, 4)+i;
//
//			i++;
//			if(facilityStaffService.findFacilityStaffByName(name) == null){
//				System.out.println("Username:"+name);
//				registration.setUserName(name);
//				cont=false;
//			}
//		}while(cont);
//		return registration.getUserName();
//	}
//
//}
