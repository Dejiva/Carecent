package com.codecoop.interact.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.StopAndWatchHistory;
import com.codecoop.interact.core.dto.PatientDto;
import com.codecoop.interact.core.dto.SAWActivePatientsDto;
import com.codecoop.interact.core.dto.SNWHasResponseDto;
import com.codecoop.interact.core.dto.StopAndWatchDto;
import com.codecoop.interact.core.dto.StopAndWatchHistoryDto;
import com.codecoop.interact.core.service.FacilityStaffService;
import com.codecoop.interact.core.service.PatientEpisodeService;
import com.codecoop.interact.core.service.StopAndWatchService;
import com.codecoop.interact.core.util.PatientUtils;
import com.codecoop.interact.web.auth.InteractUserDetailsService;
import com.codecoop.interact.web.model.SessionManagedLists;
import com.codecoop.interact.web.model.StopAndWatchForm;

@RequestMapping("/stopAndWatch")
@Controller
public class StopAndWatchController extends SessionManagedLists {

	private static final Logger LOG = Logger.getLogger(StopAndWatchController.class);

	@Autowired
	InteractUserDetailsService interactUserDetailsService;

	@Autowired
	private FacilityStaffService facilityStaffService;

	@Autowired
	private StopAndWatchService stopAndWatchService;
	@Autowired
	private PatientEpisodeService patientEpisodeService;

	@RequestMapping(value = "/submit", method = { RequestMethod.POST })
	public String stopAndWatchPage(HttpServletRequest request,
			@ModelAttribute("stopAndWatchForm") StopAndWatchForm stopAndWatch) throws Exception{
		
		// ModelAndView model=new ModelAndView();
		HttpSession session = request.getSession(true);
		String url = request.getRequestURL().toString();

		Long staffId = (Long) session.getAttribute("staffId");
		Long facilityId = (Long) session.getAttribute("facilityId");
		
		if(StringUtils.isEmpty(stopAndWatch.getGuestName())&&staffId==null){
			if (StringUtils.isEmpty(session.getAttribute("role"))) {

				return "redirect:/guest?facilityId=" + facilityId;
			}
			else{
			return "redirect:/?snwsuccess=stopandwatchFailed";
			}
		}else{
		// populateActiveFacilityStaff(session,facilityId).;
		StopAndWatchDto stopAndWatchDto = new StopAndWatchDto(
				stopAndWatch.getSeemsDifferentThanUsual(),
				stopAndWatch.getCommunicatesLess(),
				stopAndWatch.getNeedsMoreHelp(),
				stopAndWatch.getPain(),
				
				stopAndWatch.getAteLess(),
				stopAndWatch.getNoBowelMovement(), 
				stopAndWatch.getDrankLess(),
				stopAndWatch.getWeightChange(), 
				stopAndWatch.getAgitated(),
				stopAndWatch.getTired(),
				stopAndWatch.getChangeInSkinCondition(),
				stopAndWatch.getHelpWithWalking()
				);
		if (stopAndWatch.getGuestName() == null) {
			stopAndWatchDto.setRepotedById(staffId);
		}
		if (stopAndWatch.getReportedDateAndTime() != null
				&& !stopAndWatch.getReportedDateAndTime().isEmpty()) {
			try {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
				Date repotedDate = (Date) df.parse(stopAndWatch
						.getReportedDateAndTime());

				stopAndWatchDto.setReportedDateAndTime(repotedDate);
			} catch (Exception e) {
				stopAndWatchDto.setReportedDateAndTime(new Date());
			}
		}
		if (stopAndWatch.getResponseDateAndTime() != null
				&& !stopAndWatch.getResponseDateAndTime().isEmpty()) {
			try {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
				Date responseDate = (Date) df.parse(stopAndWatch
						.getResponseDateAndTime());
				stopAndWatchDto.setResponseDateAndTime(responseDate);
			} catch (Exception e) {
					stopAndWatchDto.setResponseDateAndTime(new Date());
			}
		}
		
		
		stopAndWatchDto.setStopAndWatchHistoryId(stopAndWatch.getStopAndWatchHistoryId());
		stopAndWatchDto.setFacilityStafResponse(stopAndWatch
				.getFacilityStafResponse());
		stopAndWatchDto.setReportedToFacilityStaffId(stopAndWatch
				.getReportedToFacilityStaffId());
		stopAndWatchDto.setGuestName(stopAndWatch.getGuestName());
		stopAndWatchDto.setResidentId(stopAndWatch.getResidentId());
		stopAndWatchDto.setNurseId(stopAndWatch.getNurseId());
		stopAndWatchDto.setFacilityId(facilityId);
        stopAndWatchDto.setOther(stopAndWatch.getSnwOther());
		stopAndWatchDto.setOtherSymptom(stopAndWatch.getSnwOtherSymptom());
		Long patientId = stopAndWatch.getResidentId();
		Long stopAndWatchHistoryId = -1l;
		if (stopAndWatch.getStopAndWatchHistoryId() == null) {
			try {
				
				stopAndWatchHistoryId = stopAndWatchService
						.saveStopAndwatchHistory(stopAndWatchDto);
			} catch (Exception e) {
				return "redirect:/?snwsuccess=stopandwatchFailed";
			}
		} else {
			FacilityStaff modifiedBy = facilityStaffService.findFacilityStaff(
					facilityId, staffId);
			try {
			stopAndWatchService.editStopAndWatchHistory(
					stopAndWatchHistoryId=stopAndWatch.getStopAndWatchHistoryId(), stopAndWatchDto,
					modifiedBy);
			
			//stopAndWatchHistoryId = stopAndWatch.getStopAndWatchHistoryId();
			if(stopAndWatchHistoryId!=null&&stopAndWatchHistoryId!=-1l){
			return "redirect:/?snwsuccess=stopandwatchsuccessupdate";
			}
			else{
				return "redirect:/";
			}}
			catch(Exception e){
				return "redirect:/?snwsuccess=stopandwatchFailed";
			}
	}
		if (StringUtils.isEmpty(session.getAttribute("role"))) {

			return "redirect:/guest?facilityId=" + facilityId;
		} else {
			if(stopAndWatchHistoryId!=null&&stopAndWatchHistoryId!=-1l){
			return "redirect:/?snwsuccess=stopandwatchsuccess";
			}
			else{
				return "redirect:/";
			}
		}
		}
	}
	
	@RequestMapping(value = "/getPatientNames", method = { RequestMethod.GET })
	public @ResponseBody Map<Long, String> getPatientNames(
			@RequestParam("residentName") String residentName,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long facilityId = (Long) session.getAttribute("facilityId");
		Map<Long, String> map = new HashMap<Long, String>();
		List<PatientDto> PatientNameslist = stopAndWatchService
				.getPatientNames(residentName, facilityId);
		for (PatientDto patient : PatientNameslist) {
			map.put(patient.getPatientId(),PatientUtils.getPatientFullName(patient.getPatientFirstName(), patient.getPatientLastName()));

		}

		return map;

	}

	@RequestMapping(value = "/getPatientNamesForGuest", method = { RequestMethod.GET })
	public @ResponseBody Map<Long, String> getPatientNamesForGuest(
			@RequestParam("residentName") String residentName,
			HttpServletRequest request){
		HttpSession session = request.getSession();
		Long facilityId = (Long) session.getAttribute("facilityId");

		Map<Long, String> map = new HashMap<Long, String>();
		List<PatientDto> PatientNameslist = stopAndWatchService
				.getPatientNames(residentName, facilityId);
		for (PatientDto patient : PatientNameslist) {
			PatientEpisode patientEpisode = patientEpisodeService.findActicePatientEpisode(patient.getPatientId(),facilityId);
			
			if (patientEpisode!=null && patientEpisode.getSbarRised() != null) {
				if (!patientEpisode.getSbarRised())

					map.put(patient.getPatientId(), PatientUtils.getPatientFullName(patient.getPatientFirstName(), patient.getPatientLastName()));
			}

		}
		return map;

	}

	@RequestMapping(value = "/getPatientHistory", method = { RequestMethod.GET })
	public @ResponseBody List<StopAndWatchHistoryDto> papulatestopAndWatchModelPage(
			HttpServletRequest request) {
		Long patientId = Long.parseLong(request.getParameter("currPatientId"));
		HttpSession session = request.getSession(true);
		Long facilityId = (Long) session.getAttribute("facilityId");
		
	
		return stopAndWatchService.showStopAndwatchHistoryOfPatient(patientId,facilityId);
	}

	@RequestMapping(value = "/getPatientHistoryForSbar", method = { RequestMethod.GET })
	public @ResponseBody List<StopAndWatchHistoryDto> papulatestopAndWatchModelForsbarPage(
			HttpServletRequest request) {
		Long patientId = Long.parseLong(request.getParameter("currPatientId"));
		HttpSession session = request.getSession(true);
		Long facilityId = (Long) session.getAttribute("facilityId");
		Long encounterId=null;
		try{
		 encounterId = Long.parseLong(request.getParameter("encounterId"));
		}
		catch(Exception e){}
		return stopAndWatchService.showStopAndwatchHistoryOfPatientForSbar(patientId,facilityId,encounterId);
		
	}

	@RequestMapping(value = "/getStopAndWatchPatients", method = { RequestMethod.GET, })
	public @ResponseBody List<SAWActivePatientsDto> getStopAndWatchPatients(
			HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Long facilityId = (Long) session.getAttribute("facilityId");

		return stopAndWatchService.getActiveStopAndWatchPatients(facilityId);
	}

	@RequestMapping(value = "/getStopAndWatchHistoryData", method = { RequestMethod.GET, })
	public @ResponseBody StopAndWatchForm getStopAndWatchHistoryData(
			HttpServletRequest request) {
		DateFormat df1 = null;
		Long stopAndWatchHistoryId = Long.parseLong(request
				.getParameter("stopAndWatchHistoryId"));
		StopAndWatchHistory stopAndWatchHistory = stopAndWatchService
				.getStopAndWatchHistory(stopAndWatchHistoryId);
		StopAndWatchForm stopAndWatchForm = new StopAndWatchForm();
		stopAndWatchForm.setAgitated(stopAndWatchHistory.getAgitated());
		stopAndWatchForm.setAteLess(stopAndWatchHistory.getAteLess());
		stopAndWatchForm.setChangeInSkinCondition(stopAndWatchHistory
				.getChangeInSkinCondition());
		stopAndWatchForm.setCommunicatesLess(stopAndWatchHistory
				.getCommunicatesLess());
		
		stopAndWatchForm.setDrankLess(stopAndWatchHistory.getDrankLess());
		
		
		stopAndWatchForm.setHelpWithWalking(stopAndWatchHistory
				.getHelpWithWalking());
		stopAndWatchForm.setNeedsMoreHelp(stopAndWatchHistory
				.getNeedsMoreHelp());
		stopAndWatchForm.setNoBowelMovement(stopAndWatchHistory
				.getNoBowelMovement());
		stopAndWatchForm.setPain(stopAndWatchHistory.getPain());
		
		stopAndWatchForm.setSeemsDifferentThanUsual(stopAndWatchHistory
				.getSeemsDifferentThanUsual());
		stopAndWatchForm.setTired(stopAndWatchHistory.getTired());
		
		
		stopAndWatchForm.setWeightChange(stopAndWatchHistory.getWeightChange());
		stopAndWatchForm.setSnwOther(stopAndWatchHistory.getOther());
		stopAndWatchForm.setSnwOtherSymptom(stopAndWatchHistory.getOtherSymptom());
		stopAndWatchForm.setResidentId(stopAndWatchHistory.getStopAndWatch()
				.getPatientEpisode().getPatient().getId());
		stopAndWatchForm.setResidentName( PatientUtils.getPatientFullName(stopAndWatchHistory.getStopAndWatch()
				.getPatientEpisode().getPatient().getPatientFirstName(),stopAndWatchHistory.getStopAndWatch()
				.getPatientEpisode().getPatient().getPatientLastName()));
		if (stopAndWatchHistory.getFacilityStaff() != null)
			stopAndWatchForm.setYourName(stopAndWatchHistory.getFacilityStaff()
					.getStaff().getFirstName());
		if (stopAndWatchHistory.getGuestName() != null)
			stopAndWatchForm.setGuestName(stopAndWatchHistory.getGuestName());
	if (stopAndWatchHistory.getReportedToFacilityStaff() != null)
			stopAndWatchForm.setReportedToFacilityStaffId(stopAndWatchHistory.getReportedToFacilityStaff().getStaff().getId());
		if (stopAndWatchHistory.getReportedDateAndTime() != null) {
			df1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			String snwrepotedDate = (String) df1.format(stopAndWatchHistory
					.getReportedDateAndTime());
     		stopAndWatchForm.setReportedDateAndTime(snwrepotedDate);
		}
		 return stopAndWatchForm;
	}

	@RequestMapping(value = "/deleteStopAndWatchHistoryData", method = { RequestMethod.GET })
	public @ResponseBody Long deleteStopAndWatchHistoryData(
			HttpServletRequest request) {
		Long stopAndWatchHistoryId = Long.parseLong(request
				.getParameter("stopAndWatchHistoryId"));
		HttpSession session = request.getSession();
		String staffName = (String) session.getAttribute("staffName");
		return stopAndWatchService.getStopAndWatchDelete(stopAndWatchHistoryId,
				staffName);

	}

	@RequestMapping(value = "/hasResponse", method = { RequestMethod.GET })
	public @ResponseBody SNWHasResponseDto hasResponse(
			HttpServletRequest request){
		Long patientId = Long.parseLong(request.getParameter("patientId"));
		HttpSession session = request.getSession(true);
		Long facilityId = (Long) session.getAttribute("facilityId");

		return stopAndWatchService.hasResponse(patientId,facilityId);

	}
	
	@RequestMapping(value = "/invalidateStopAndWatchHistoryData", method = { RequestMethod.GET })
	public @ResponseBody Long  invalidateStopAndWatchHistory(HttpServletRequest request) throws Exception {
		Long stopAndWatchHistoryId=null;
		HttpSession session = request.getSession();
		String staffName = (String) session.getAttribute("staffName");
		
		Long facilityId = (Long) session.getAttribute("facilityId");

		if(request.getParameter("stopAndWatchHistoryId")!=null){
			stopAndWatchHistoryId = Long.parseLong(request.getParameter("stopAndWatchHistoryId"));
			
			
		}
		
		return  stopAndWatchService.invalidateStopAndWatchHistoryData(stopAndWatchHistoryId,staffName,facilityId);
		 
		

	}

	
}