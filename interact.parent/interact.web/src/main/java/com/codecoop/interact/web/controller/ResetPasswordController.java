package com.codecoop.interact.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codecoop.interact.core.service.ResetPasswordService;

@Controller
@RequestMapping("/recover")
public class ResetPasswordController {
	private static final Logger LOG = Logger.getLogger(ResetPasswordController.class);
	@Autowired
	private ResetPasswordService resetPassWordService;
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String passWordRecovery(HttpServletRequest request){
		String workMail = request.getParameter("workMail");
	try{
	String status = resetPassWordService.resetPassword(workMail);
	if(status.equalsIgnoreCase("success")){
		request.setAttribute("status", "success");
		return "/login";
	}else{
		request.setAttribute("status", "failed");
		return "/login";
	}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error(e.getMessage(),e);
		request.setAttribute("status", "failed");
		return "/login";
	}
	}
	
}
