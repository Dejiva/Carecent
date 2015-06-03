package com.codecoop.interact.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codecoop.interact.core.domain.Facility;
import com.codecoop.interact.core.service.FacilityStaffService;
import com.codecoop.interact.core.service.PatientEpisodeService;
import com.codecoop.interact.core.service.StopAndWatchService;
import com.codecoop.interact.web.model.SessionManagedLists;
import com.codecoop.interact.web.model.StopAndWatchForm;

@Controller
public class GuestController extends SessionManagedLists{
	private static final Logger LOG = Logger.getLogger(GuestController.class);
	@Autowired
	StopAndWatchController stopAndWatchController;
	
	@Autowired
	private FacilityStaffService facilityStaffService;
	
	@Autowired
	private StopAndWatchService stopAndWatchService;
	
	@Autowired
	private PatientEpisodeService patientEpisodeService;

	@RequestMapping(value="/guest", method={RequestMethod.GET})
	public String guestStopAndWatchPage(HttpServletRequest request,
			@ModelAttribute("stopAndWatchForm") StopAndWatchForm stopAndWatch) {
		HttpSession session = request.getSession();
		// here if user is exists I will invalidate session..
		if(!StringUtils.isEmpty(session.getAttribute("user"))){
			session.invalidate();
			session = request.getSession();
		}
		Long facilityId = null;
		Long staffId=null;
		Boolean isAllPatients=false;
		try{
			facilityId = Long.parseLong(request.getParameter("facilityId"));
			Facility facility=facilityStaffService.findFacilityById(facilityId);
			session.setAttribute("FacilityName",facility.getFacilityName());
			request.setAttribute("fromPage", "guest");
			session.setAttribute("facilityId",facilityId);
		}catch(Exception e){
			request.setAttribute("fromPage", "noFacilityId");
		}
		
		populateAllPatientsMap(session,facilityId);
		populateActiveFacilityStaff(session,facilityId);
		return "guest";
	}
	
}
