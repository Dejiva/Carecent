package com.codecoop.interact.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecoop.interact.core.dto.AnnouncementDto;
import com.codecoop.interact.core.service.AnnouncementService;

@Controller
public class AnnouncementController {
	private static final Logger LOG = Logger.getLogger(AnnouncementController.class);
	
	@Autowired
	AnnouncementService announcementService;

	@RequestMapping(value = "/getAllAnnouncement", method = RequestMethod.GET)
	public @ResponseBody List<AnnouncementDto> getAllAnnouncement(HttpServletRequest request) {
		 HttpSession session = request.getSession(true);
		  
		  Long facilityId=(Long)session.getAttribute("facilityId");
		return announcementService.getAllAnnouncement(facilityId);
	}
	
	@RequestMapping(value = "/saveAnnouncement", method = RequestMethod.GET)
	public @ResponseBody AnnouncementDto saveAnnouncement(HttpServletRequest request) {
		 HttpSession session = request.getSession(true);
		  
		  Long facilityId=(Long)session.getAttribute("facilityId");
		AnnouncementDto dto=new AnnouncementDto();
		String announcement=request.getParameter("announcement");
		String userCreated=request.getUserPrincipal().getName();
		dto.setAnnouncementText(announcement);
		dto.setUserCreated(userCreated);
		dto.setDeletedFlag(0);
		dto.setActiveFlag(1);
		dto.setFacilityId(facilityId);
		return announcementService.saveAnnouncement(dto);
	}
	
	@RequestMapping(value = "/activateAnnouncement", method = RequestMethod.GET)
	public @ResponseBody String activateAnnouncement(HttpServletRequest request) {
		Integer annId=Integer.parseInt(request.getParameter("annId"));
		Integer actFlag=("true".equals(request.getParameter("actFlag"))?1:0);
		announcementService.avtivateAnnouncement(annId, actFlag);
		return "200OK";
	}
	
	
	@RequestMapping(value = "/deleteAnnouncement", method = RequestMethod.GET)
	public @ResponseBody String deleteAnnouncement(HttpServletRequest request) {
		Integer annId=Integer.parseInt(request.getParameter("annId"));
		announcementService.deleteAnnouncement(annId);
		return "200OK";
	}
	
	@RequestMapping(value = "/updateAnnouncement", method = RequestMethod.GET)
	public @ResponseBody AnnouncementDto updateAnnouncement(HttpServletRequest request) {
		Integer annId=Integer.parseInt(request.getParameter("annId"));
		String annText=request.getParameter("announcement");
		return announcementService.updateAnnouncement(annId, annText);
	}
	
	
	@RequestMapping(value = "/getAllActiveAnnouncement", method = RequestMethod.GET)
	public @ResponseBody List<AnnouncementDto> getAllActiveAnnouncement(HttpServletRequest request) {
		 HttpSession session = request.getSession(true);
		  
		  Long facilityId=(Long)session.getAttribute("facilityId");
		  if(facilityId != null){
		return announcementService.getAllActiveAnnouncement(facilityId);
		  }else{
			 return  new ArrayList<AnnouncementDto>();
		  }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
