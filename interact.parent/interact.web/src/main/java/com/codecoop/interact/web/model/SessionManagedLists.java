package com.codecoop.interact.web.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.dao.ChangeInConditionDao;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.dao.SbarDaoImpl;
import com.codecoop.interact.core.domain.Facility;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.StaffRoles;
import com.codecoop.interact.core.dto.ChangeInConditionMessageDto;
import com.codecoop.interact.core.dto.FacilityDto;
import com.codecoop.interact.core.dto.FacilityStaffDto;
import com.codecoop.interact.core.dto.MedicineResolutionDto;
import com.codecoop.interact.core.model.LoggedUserModel;
import com.codecoop.interact.core.model.PatientDetailsModel;
import com.codecoop.interact.core.service.AcuteCareService;
import com.codecoop.interact.core.service.FacilityStaffService;
import com.codecoop.interact.core.service.ManageFacilityService;
import com.codecoop.interact.core.service.MedicineResolutionService;
import com.codecoop.interact.core.service.PatientEpisodeService;
import com.codecoop.interact.core.service.SBARService;
import com.codecoop.interact.core.service.StopAndWatchService;
import com.codecoop.interact.core.util.Constants;

@Component
public class SessionManagedLists  {
	private static final Logger LOG = Logger.getLogger(SessionManagedLists.class);
	@Autowired
	private FacilityStaffService facilityStaffService;
	@Autowired
	private PatientEpisodeService patientEpisodeService;
	@Autowired
	private StopAndWatchService stopAndWatchService;
	@Autowired
	private ChangeInConditionDao changeInConditionDao;
	@Autowired
	private PatientEpisodeDao patientEpisodeDao;
	@Autowired
	SbarDaoImpl sbarDao;
	@Autowired
	ManageFacilityService manageFacilityService;
	@Autowired
	SBARService sbarService;
	@Autowired
	AcuteCareService acuteCareService;

	@Autowired
	private MedicineResolutionService medicineResolutionService;

	protected Map<Integer, String> loggedInUserFacilityMap;
	protected Map<Integer, String> selectedInUserFacilityMap;
	@SuppressWarnings("unchecked")
	protected Map<Long, String> populateRoleMap(HttpSession session,HttpServletRequest req){
		Map<Long, String> rolesMap = (Map<Long, String>)session.getAttribute("rolesMap");
		if(CollectionUtils.isEmpty(rolesMap)){
			rolesMap = new HashMap<Long, String>();
			for ( StaffRoles roles :  facilityStaffService.findAllRoles()) {
				if(roles.getRoleName().equalsIgnoreCase(Constants.GUEST_ROLE)){
					continue ;
				}
				if(!(req.isUserInRole(Constants.ADMIN_ROLE) && roles.getRoleName().equalsIgnoreCase(Constants.SUPER_ADMIN_ROLE))){
					rolesMap.put(roles.getId(),roles.getDescription());
				}
			}
			session.setAttribute("rolesMap", rolesMap);
		}
		return rolesMap;
	}

	protected Map<Long, String> populateActiveFacilityStaff(HttpSession session,Long facilityId){
		Map<Long, String> activefacilityStaffMap = new HashMap<Long, String>();
		String staffName=null;
		for ( FacilityStaff facilityStaff :  facilityStaffService.findAllActiveFacilityStaffByFaclityId(facilityId)) {
			String staffRole=facilityStaff.getStaffRoles().getRoleName();
			String role=staffRole.substring(staffRole.indexOf("_")+1, staffRole.length());

			if(role.equals("LPN")||role.equals("RN"))
			{


				staffName=facilityStaff.getStaff().getFirstName()+" "+facilityStaff.getStaff().getMiddleName()+" "+facilityStaff.getStaff().getLastName()+"("+role+")";

				activefacilityStaffMap.put(facilityStaff.getStaff().getId(),staffName);

			}

			session.setAttribute("activefacilityStaffMap", activefacilityStaffMap);
		}
		return activefacilityStaffMap;
	}

	protected Map<Long, String> pupulateSbarPatientStatus(HttpSession session,Long facilityId){

		Map<Long, String> sabrPatientStatusMap = new LinkedHashMap<Long, String>();
		for (Sbar sbar : sbarService.findByFacilityId(facilityId)) {
			String status="NONE";
			List<ChangeInConditionMessageDto> changeInConditionMessagelist = sbarService.getChangeInConditionDateForEvaluation(sbar.getPatientEpisode().getId());
			if(changeInConditionMessagelist.size()>0){
				boolean notifyImmediate=false;
				for(ChangeInConditionMessageDto changeInConditionMessageDto:changeInConditionMessagelist)
				{   
					if(changeInConditionMessageDto.getMessage().equals(Constants.CIC_NOTIFY_IMMEDIATE))
					{	
						notifyImmediate=true;
						break;
					}
				}
				if(notifyImmediate){
					status=Constants.CIC_NOTIFY_IMMEDIATE;
				}else{
					status=Constants.CIC_NOTIFY_NEXT_DAY;
				}
			}
			sabrPatientStatusMap.put(sbar.getPatientEpisode().getPatient().getId(),status);

		}
		session.setAttribute("sbarPatientStatusMap", sabrPatientStatusMap);
		return sabrPatientStatusMap;
	}

	@SuppressWarnings("unchecked")
	protected Map<Long, String> pupulateMedicineResolutions(HttpSession session){
		Map<Long, String> resolutionsMap = (Map<Long, String>)session.getAttribute("resolutionsMap");
		if(CollectionUtils.isEmpty(resolutionsMap)){
			resolutionsMap = new HashMap<Long, String>();
			for (MedicineResolutionDto dto :  medicineResolutionService.getAllMedicineResolutionTypes()) {
				resolutionsMap.put(dto.getId(),dto.getResolutionType());
			}
			session.setAttribute("resolutionsMap", resolutionsMap);
		}
		return resolutionsMap;
	}


	protected void createAssignedToMePatientsMap(HttpSession session,Long staffId){
		LoggedUserModel user=loggedUserDetails(staffId, session);
		Long facilityId = (Long)session.getAttribute("facilityId");
		Long fstaffId = user.getFstaffId();
		String roleName = user.getRoleName();
		Long pcpId = null;
		if(roleName.equalsIgnoreCase("MD")){
			pcpId = fstaffId;
		}else if(roleName.equalsIgnoreCase("NP") || roleName.equalsIgnoreCase("PA")){
			pcpId = facilityStaffService.getAssignedMDByFStaffId(fstaffId);
		}
		List<PatientDetailsModel> patients = patientEpisodeService.populateAssignedPatients(facilityId, pcpId);
		session.setAttribute("patientsInCurrentFacility", populateFacilityPatientsMap1(patients));
	}

	private SessionPatientModel populateFacilityPatientsMap1(List<PatientDetailsModel> listOfPatients){
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

	protected void populateAllPatientsMap(HttpSession session,Long facilityId){
		List<PatientDetailsModel>	listOfPatients = patientEpisodeService.findAllPatientsFromFacility1(facilityId);
		session.setAttribute("patientsInCurrentFacility", populateFacilityPatientsMap1(listOfPatients));
	}
	//	TODO: we need to refactor this method....
	@SuppressWarnings("unchecked")
	protected LinkedHashMap<Long, String> populateLoggedInUserFacilityMap(HttpSession session, Long staffId){
		LinkedHashMap<Long, String>  loggedInUserFacilityMap = (LinkedHashMap<Long, String>)session.getAttribute("loggedInUserFacilityMap");
		if(CollectionUtils.isEmpty(loggedInUserFacilityMap)){
			loggedInUserFacilityMap = new LinkedHashMap<Long, String>();

			for (Facility facility :  facilityStaffService.findFacilityByStaffId(staffId)) {
				LOG.info("LoggedUserFacilities:"+facility.getFacilityName());
				loggedInUserFacilityMap.put(facility.getId(),facility.getFacilityName());
			}
			session.setAttribute("loggedInUserFacilityMap", loggedInUserFacilityMap);
		}else{
			LoggedUserModel currentUser = (LoggedUserModel)session.getAttribute("loggedUser");
			if(currentUser != null){
				LinkedHashMap<Long, String> currentLoggedFacilityMap = new LinkedHashMap<Long, String>();
				currentLoggedFacilityMap.put(currentUser.getFacilityId(), currentUser.getFacilityName());
				for (Iterator itr = loggedInUserFacilityMap.entrySet().iterator(); itr.hasNext();)
				{
					Map.Entry<Long, String> entrySet = (Map.Entry) itr.next();
					Long key = entrySet.getKey();
					currentLoggedFacilityMap.put(key, loggedInUserFacilityMap.get(key));
				}
				session.setAttribute("loggedInUserFacilityMap", currentLoggedFacilityMap);
			}
		}
		return loggedInUserFacilityMap;
	}

	@SuppressWarnings("unchecked")
	protected Map<Long, String> populateAllFacilityMap(HttpSession session){
		Map<Long, String>  allFacilityMap = (Map<Long, String>)session.getAttribute("loggedInUserFacilityMap");
		if(CollectionUtils.isEmpty(loggedInUserFacilityMap)){
			allFacilityMap = new LinkedHashMap<Long, String>();

			Long cureentFaciliytId = (Long)session.getAttribute("facilityId");
			FacilityDto currentFacility= manageFacilityService.findFacilityById(cureentFaciliytId);
			LOG.info("loggedFacility:"+currentFacility.getFacilityName());
			LOG.info("**********END***********");
			allFacilityMap.put(currentFacility.getId(), currentFacility.getFacilityName());

			for(FacilityDto facility :  manageFacilityService.getAllFacilities()) {
				allFacilityMap.put(facility.getId(),facility.getFacilityName());
			}
			session.setAttribute("allFacilityMap", allFacilityMap);
		}
		return allFacilityMap;
	}

	protected LoggedUserModel loggedUserDetails(Long staffId,HttpSession session){
		Long facilityId = (Long)session.getAttribute("facilityId");
		// logged User become null only for superAdmin because he can access all facilities without resident of facilities....
		LoggedUserModel user = facilityStaffService.getLoggedUser(staffId, facilityId);
		if(!StringUtils.isEmpty(user)){
			session.setAttribute("loggedUser", user);
		}else{
			user = (LoggedUserModel)session.getAttribute("loggedUser");
			return user.getRoleValue().equalsIgnoreCase("ROLE_SUPER_ADMIN")?user:null;
		}
		return user;
	}

	public void  getDocMdofCurrentFacility(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		Map<Long, MDINCurrentFacilityModel> mdMap=new TreeMap<Long, MDINCurrentFacilityModel>();
		Long facilityId = (Long)session.getAttribute("facilityId");
		if(facilityId !=null){
			List<FacilityStaffDto> staffListDto = facilityStaffService.getMDofCurrentFacility(facilityId);
			for(FacilityStaffDto list:staffListDto){
				MDINCurrentFacilityModel md=new MDINCurrentFacilityModel();
				md.setId(list.getId());
				md.setName(list.getName());
				md.setWorkNumber(list.getWorkNumber());
				mdMap.put(list.getId(), md);
			}
			session.setAttribute("mdInFacilityMap", mdMap);
		}
	}

	protected void populateNuresesInCurrenFacility(HttpSession session){
		Long facilityId = (Long)session.getAttribute("facilityId");
		Map<Long,FacilityStaffDto> nureses=new LinkedHashMap<Long,FacilityStaffDto>();
		for(FacilityStaffDto nurse:facilityStaffService.getNursesOfCurrentFacility(facilityId)){
			nureses.put(nurse.getId(), nurse);	
		}
		session.setAttribute("nursesIncurrentFacility", nureses);
	}

}
