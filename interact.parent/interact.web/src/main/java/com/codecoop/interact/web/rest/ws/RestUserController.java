package com.codecoop.interact.web.rest.ws;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecoop.interact.core.model.PatientDetailsModel;
import com.codecoop.interact.core.rest.dto.RestAlertDto;
import com.codecoop.interact.core.rest.dto.RestFacilitiesDto;
import com.codecoop.interact.core.rest.dto.RestLabOrdersDto;
import com.codecoop.interact.core.rest.dto.RestPatientObservationDto;
import com.codecoop.interact.core.rest.dto.RestPractitionerNotesDto;
import com.codecoop.interact.core.rest.dto.ShortNotesDto;
import com.codecoop.interact.core.service.PatientEpisodeService;
import com.codecoop.interact.core.service.RestService;
import com.codecoop.interact.core.util.CoreDateUtils;
import com.codecoop.interact.web.rest.bean.RestAlertRequest;
import com.codecoop.interact.web.rest.bean.RestAlertResponse;
import com.codecoop.interact.web.rest.bean.RestFacilitiesResponse;
import com.codecoop.interact.web.rest.bean.RestLoginRequest;
import com.codecoop.interact.web.rest.bean.RestLoginResponse;
import com.codecoop.interact.web.rest.bean.RestObservationRequest;
import com.codecoop.interact.web.rest.bean.RestObservationResponse;
import com.codecoop.interact.web.rest.bean.RestPatient;
import com.codecoop.interact.web.rest.bean.RestPatientResponse;
import com.codecoop.interact.web.rest.bean.RestUpdateLabOrdersRequest;
import com.codecoop.interact.web.rest.bean.ShortNotesResponse;

/**
 * @author Ramesh
 *
 */
@RequestMapping("/rest")
@Controller
public class RestUserController {
	private static final Logger LOG =  Logger.getLogger(RestUserController.class);

	@Autowired
	private RestService restService;

	@Autowired
	private PatientEpisodeService patientEpisodeService;

	// when user logs in if user is authenticated then  return registered facilities list....
	@RequestMapping(value = "/user/restloginRlogout", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody RestLoginResponse restUserLogin(@RequestBody RestLoginRequest request){
		RestLoginResponse response = new RestLoginResponse();
		List<RestFacilitiesResponse> list=new ArrayList<RestFacilitiesResponse>();
		Date today = CoreDateUtils.parseDate(CoreDateUtils.parseStringDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd");
		try{
			List<RestFacilitiesDto> facilities = restService.getFacilitiesByUserName(request.getUserName(),request.getPasswd());
			if(CollectionUtils.isEmpty(facilities)){
				response.setStatus("bad credentials");
			}else if(facilities.get(0).getRelievingDate() == null || facilities.get(0).getRelievingDate().compareTo(today) >0 ? true : false){
				for(RestFacilitiesDto fList:facilities){
					list.add(new RestFacilitiesResponse(fList.getfStaffId(), fList.getFacilityId(), fList.getFacilityName()));
				}
				response.setStatus("success");
				if(request.getDeviceLoggedStatus() && restService.updateDeviceDetailsOfTheStaff(request.getUserName(), request.getDeviceToken(), request.getDeviceType(), request.getDeviceLoggedStatus())){
				response.setFacilitiesList(list);
				}
			}else{
				response.setStatus("relieved from this facility");
			}
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			response.setStatus("failed");
		}
		return response;
	}

	// by using facility staffId getting list of patients in facility staff belonging facility
	@RequestMapping(value = "/patients", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody RestPatientResponse restPatientsInFacility(@RequestBody RestFacilitiesResponse request){
		RestPatientResponse response=new RestPatientResponse();
		try{
			List<PatientDetailsModel> patients = patientEpisodeService.populateAssignedPatients(request.getFacilityId(),request.getfStaffId());
			List<RestPatient> respPatients = new ArrayList<RestPatient>();
			if(CollectionUtils.isEmpty(patients)){
				response.setStatus("No Data");
			}else if(!CollectionUtils.isEmpty(patients)){
				for(PatientDetailsModel patient:patients){
					if(patient.isObservationQueue()) //if(patient.isAdmissionQueue())
						respPatients.add(new RestPatient(patient.getId(), patient.getPatientEpisodeId(), patient.getFullName()));
				}
				response.setPatients(respPatients);
				response.setStatus("success");
			}
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			response.setStatus("failed");
		}
		return response;
	}

	@RequestMapping(value = "/patient/Observation", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody RestObservationResponse ObservationOfPatient(@RequestBody RestObservationRequest request){
		return  populateObservationOfPatient(request.getPatientEpisodeId());
	}

	@RequestMapping(value = "/patient/Observation/updateLabwork", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody RestObservationResponse updateLabwork(@RequestBody RestUpdateLabOrdersRequest request){
		RestObservationResponse response = new RestObservationResponse();
		RestLabOrdersDto labOrder=new RestLabOrdersDto();
		try{
			labOrder.setId(request.getId());
			labOrder.setSbarId(request.getSbarId());
			labOrder.setSignsSymptomsLabworkId(request.getSignsSymptomsLabworkId());
			labOrder.setApproved(request.getApproved());
			labOrder.setRejected(request.getRejected());
			labOrder.setFacilityStaffId(request.getFacilityStaffId());
			labOrder.setSignSymtomLabworkName(request.getSignSymtomLabworkName());
			labOrder.setStndingOrder(request.getStndingOrder());
			RestPatientObservationDto data = restService.updateLabwork(labOrder);
			if(StringUtils.isEmpty(data)){
				response.setStatus("No Data");
			}else if(!StringUtils.isEmpty(data)){
				response.setObservation(data);
				response.setStatus("success");
			}
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			response.setStatus("failed");
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "patient/Alerts", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody RestAlertResponse getAlerts(@RequestBody RestAlertRequest request){
		RestAlertResponse response=new RestAlertResponse();
		try{
			List<RestAlertDto> l= restService.alertsData(request.getUserName(),request.getType(),request.getOrder());
			if(l.isEmpty()){
				response.setStatus("No Data");
			}
			else if(!(l.isEmpty())){
				response.setStatus("Success");
				response.setAlert(l);
			}

		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			response.setStatus("failed");
			e.printStackTrace();

		}
		return response;



	}
	@RequestMapping(value = "shortNotes", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody ShortNotesResponse getShortNotes(){
		ShortNotesResponse response=new ShortNotesResponse();
		try{
			List<ShortNotesDto> l= restService.getShortNotes();
			if(l.isEmpty()){
				response.setStatus("No Data");
			}
			else if(!(l.isEmpty())){
				response.setStatus("Success");
				response.setNotes(l);
			}

		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			response.setStatus("failed");
			e.printStackTrace();

		}

		return response;

	}

	@RequestMapping(value = "addNotes", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody RestObservationResponse addNotes(@RequestBody RestPractitionerNotesDto request){
		if(restService.saveSbarNotes(request)){
			return  populateObservationOfPatient(request.getPatientEpisodeId());
		}else{
			RestObservationResponse response = new RestObservationResponse();
			response.setStatus("failed");
			return response;
		}

	}

	private RestObservationResponse populateObservationOfPatient(Long patientEpisodeId){
		RestObservationResponse response = new RestObservationResponse();
		try{
			RestPatientObservationDto data = restService.ObservationOfPatient(patientEpisodeId);
			if(StringUtils.isEmpty(data)){
				response.setStatus("No Data");
			}else if(!StringUtils.isEmpty(data)){
				response.setObservation(data);
				response.setStatus("success");
			}

		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			response.setStatus("failed");
			e.printStackTrace();
		}
		return response;
	}	




}
