package com.codecoop.interact.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codecoop.interact.core.dto.FStaffRegistrationDto;
import com.codecoop.interact.core.service.FacilityStaffService;

@RequestMapping("/firstlogin")
@Controller
public class FirstLoginController {
	private static final Logger LOG = Logger.getLogger(FirstLoginController.class);
	@Autowired
	private FacilityStaffService facilityStaffService;
	
	private FStaffRegistrationDto fStaffregDto;
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String checkFirstLogin(HttpServletRequest request,HttpServletResponse response){
		
//		if(interactSampleService.checkUserIsExists(request.getParameter("userId")) !=null){
//			request.setAttribute("valid",true);
//			request.setAttribute("UserId",request.getParameter(name) );
//		}
		return "first_login";
	}
	
}
