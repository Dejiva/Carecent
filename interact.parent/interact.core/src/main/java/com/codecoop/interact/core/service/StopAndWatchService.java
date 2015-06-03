package com.codecoop.interact.core.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.codecoop.interact.core.dao.FacilityStaffDaoImpl;
import com.codecoop.interact.core.dao.MessageAlertDaoImpl;
import com.codecoop.interact.core.dao.MessageAlertDetailsDaoImpl;
import com.codecoop.interact.core.dao.PatientDaoImpl;
import com.codecoop.interact.core.dao.PatientEncounterDaoImpl;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.dao.StaffDaoImpl;
import com.codecoop.interact.core.dao.StaffRolesDaoImpl;
import com.codecoop.interact.core.dao.StopAndWatchDaoImpl;
import com.codecoop.interact.core.dao.StopAndWatchHistoryDaoImpl;
import com.codecoop.interact.core.dao.StopAndWatchHistoryModifiedDaoImpl;
import com.codecoop.interact.core.domain.AcuteCare;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.MessageAlert;
import com.codecoop.interact.core.domain.MessageAlertDetails;
import com.codecoop.interact.core.domain.MessageAlertDetails;
import com.codecoop.interact.core.domain.Patient;
import com.codecoop.interact.core.domain.PatientEncounter;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.domain.StaffRoles;
import com.codecoop.interact.core.domain.StopAndWatch;
import com.codecoop.interact.core.domain.StopAndWatchHistory;
import com.codecoop.interact.core.domain.StopAndWatchHistoryModified;
import com.codecoop.interact.core.dto.MessageDto;
import com.codecoop.interact.core.dto.PatientDto;
import com.codecoop.interact.core.dto.SAWActivePatientsDto;
import com.codecoop.interact.core.dto.SNWHasResponseDto;
import com.codecoop.interact.core.dto.StopAndWatchDto;
import com.codecoop.interact.core.dto.StopAndWatchHistoryDto;
import com.codecoop.interact.core.dto.StopAndWatchPatientsDto;
import com.codecoop.interact.core.dto.StopAndWatchPatientsMapDto;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.core.util.PatientUtils;
import com.codecoop.interact.core.util.StaffUtils;
@Service
public class StopAndWatchService {
	
	private static final Logger LOG = Logger.getLogger(StopAndWatchService.class);
	@Autowired
	FacilityStaffDaoImpl facilityStaffDao;
	@Autowired
	StaffDaoImpl staffDao;
	@Autowired
	StaffService staffService;
	@Autowired
	FacilityStaffService facilityStaffService;
	@Autowired
	PatientEpisodeDao patientEpisodeDao;
	@Autowired
	PatientEncounterDaoImpl patientEncounterDao;
	@Autowired
	PatientDaoImpl patientDao;
	@Autowired
	PatientEpisodeService patientEpisodeService;
	@Autowired
	StopAndWatchDaoImpl  stopAndWatchDao;
	@Autowired
	StopAndWatchHistoryDaoImpl  stopAndWatchHistoryDao;
	@Autowired
	StopAndWatchHistoryModifiedDaoImpl  stopAndWatchHistoryModifiedDao;
	
	@Autowired
	MessageService messageService;
	@Autowired
	MessageAlertDaoImpl messageDao;
	@Autowired 
	MailService mailService;
	@Autowired
	StaffRolesDaoImpl staffRolesDao;
	@Autowired
	MessageAlertDetailsDaoImpl messageDetailsDao;
	
	@Transactional
	
	public Long saveStopAndwatchHistory(StopAndWatchDto stopAndWatchDto) throws Exception{
		if(StringUtils.isEmpty(stopAndWatchDto.getGuestName())&&stopAndWatchDto.getRepotedById()==null){
			return -1l;
		}else{
			try{
		Long facilityId=stopAndWatchDto.getFacilityId();
			
	    String msgSenarioCode = null;
	    String guestName=stopAndWatchDto.getGuestName();
	    Long recidentId=stopAndWatchDto.getResidentId();
	    Long nurseId=stopAndWatchDto.getNurseId();
	    Long reportedToFacilityStaffId=stopAndWatchDto.getReportedToFacilityStaffId();
		Long  repotedById=stopAndWatchDto.getRepotedById();
		FacilityStaff reportedToFacilityStaff=facilityStaffDao.findFacilityStaff(facilityId, reportedToFacilityStaffId);
		FacilityStaff nurse=facilityStaffDao.findFacilityStaff(facilityId, nurseId);
		FacilityStaff reportedBy=facilityStaffDao.findFacilityStaff(facilityId, repotedById);
		Date responseDateAndTime=stopAndWatchDto.getResponseDateAndTime();
		Date repotedDateAndTime=stopAndWatchDto.getReportedDateAndTime();
	    Patient patient= patientDao.findById(recidentId);
		PatientEpisode patientEpisode=patientEpisodeDao.findActiveEpisodeByPatient(patient,facilityId);
		StopAndWatch stopAndWatch=stopAndWatchDao.findActiveStopAndWatch(patientEpisode);
		
		if(stopAndWatch==null){ 
			String staffName="";
			stopAndWatch=new StopAndWatch(patientEpisode,new Date(), null, new Date(), null, null, null);
			if(reportedBy!=null){
				
				staffName=reportedBy.getStaff().getFirstName();
			}
			else{
				staffName=guestName+"(GUEST)";
			}
			stopAndWatch.setUserCreated(staffName);
			PatientEncounter patientEncounter=patientEncounterDao.findActiveEncounter(patientEpisode.getId());
			if(patientEncounter==null){
				int encounterNum=0;
				patientEncounter=patientEncounterDao.findLastEcounterNumber(patientEpisode.getId());
				if(patientEncounter!=null){
					encounterNum=patientEncounter.getEncounterNum();
				}
	
		    	patientEncounter=new PatientEncounter(patientEpisode, encounterNum+1, new Date(), null, null, new Date(), staffName, null, null,null,null,null,null);
		    	patientEncounterDao.saveOrUpdate(patientEncounter);
			}
			stopAndWatchDao.saveOrUpdate(stopAndWatch);
		}
		
		StopAndWatchHistory stopAndWatchHostory=new StopAndWatchHistory(stopAndWatch,stopAndWatchDto.getSeemsDifferentThanUsual(),
				stopAndWatchDto.getCommunicatesLess(), 
				stopAndWatchDto.getNeedsMoreHelp(), 
				stopAndWatchDto.getPain(),
				stopAndWatchDto.getAteLess(), 
				stopAndWatchDto.getNoBowelMovement(), 
				stopAndWatchDto.getDrankLess(),
				stopAndWatchDto.getWeightChange(), 
				stopAndWatchDto.getAgitated(), 
				stopAndWatchDto.getTired(),
				stopAndWatchDto.getChangeInSkinCondition(), 
				stopAndWatchDto.getHelpWithWalking(), 
				
				null, guestName, reportedToFacilityStaff,repotedDateAndTime, stopAndWatchDto.getFacilityStafResponse(), responseDateAndTime, new Date(), null, null, null);
				
		       stopAndWatchHostory.setNurse(nurse);
		       if(reportedBy!=null){
		    	   stopAndWatchHostory.setUserCreated(reportedBy.getStaff().getFirstName());
				}
				else{
					stopAndWatchHostory.setUserCreated(guestName+"(GUEST)");
				}

		       stopAndWatchHostory.setOther(stopAndWatchDto.getOther());
		       stopAndWatchHostory.setOtherSymptom(stopAndWatchDto.getOtherSymptom());
		       stopAndWatchHostory.setFacilityStaff(reportedBy);
		       stopAndWatchHostory.setDeleteFlag(false);
		       stopAndWatchHostory.setInvalidateFlag(false);
		       stopAndWatchHostory.setAlertMailFlag(false);
		       MessageDto messageDto=new MessageDto();
			   messageDto.setFacilityId(facilityId);
			   messageDto.setPatientId(recidentId);
			  
			   	if(reportedToFacilityStaffId!=null)
				{
			   		messageDto.setPatientEpisodeId(stopAndWatchHostory.getStopAndWatch().getPatientEpisode().getId());
			   		messageDto.setFacilityId(stopAndWatchHostory.getStopAndWatch().getPatientEpisode().getFacilityId());
			   		messageDto.setReportedToNurse(reportedToFacilityStaff.getId());
					msgSenarioCode=Constants.SNW_REPORTEDTO;
					messageDto.setCurrentScenarioCode(msgSenarioCode);
					messageDto.setMsgSubj(Constants.SNW_SUBJECT);
				    messageDto.setSentDate(new Date());
				    stopAndWatchHostory.setMsgScenarioCode(msgSenarioCode);
				    stopAndWatchHistoryDao.saveOrUpdate(stopAndWatchHostory);
					messageDto.setEventId(stopAndWatchHostory.getId());
				
				   }
				
				else if(reportedToFacilityStaffId==null)
				{
					messageDto.setPatientEpisodeId(stopAndWatchHostory.getStopAndWatch().getPatientEpisode().getId());
					messageDto.setFacilityId(stopAndWatchHostory.getStopAndWatch().getPatientEpisode().getFacilityId());
					msgSenarioCode=Constants.SNW_NOTREPORTEDTO;
					messageDto.setCurrentScenarioCode(msgSenarioCode);
					messageDto.setMsgSubj(Constants.SNW_SUBJECT);
					messageDto.setSentDate(new Date());
					stopAndWatchHostory.setMsgScenarioCode(msgSenarioCode);
					stopAndWatchHistoryDao.saveOrUpdate(stopAndWatchHostory);
					messageDto.setEventId(stopAndWatchHostory.getId());
				}
			   	
			   	
			    String prevScenariocode=messageDao.getPrevScenarioCodeByEventId(stopAndWatchHostory.getStopAndWatch().getPatientEpisode().getId());
			    messageService.suspendedPreviousMessages(stopAndWatchHostory.getStopAndWatch().getPatientEpisode().getId(),prevScenariocode,Constants.MESSAGE);
			    messageService.suspendedPreviousAlerts(stopAndWatchHostory.getStopAndWatch().getPatientEpisode().getId(),prevScenariocode,Constants.ALERT);
			   	messageService.saveMessages(messageDto);
			   	messageService.saveAlerts(messageDto);
			   	
				List<Sbar> sbarList=messageDao.getPrevScenarioCodeBySbarId(stopAndWatchHostory.getStopAndWatch().getPatientEpisode().getId());
				for(Sbar sbar:sbarList){
					messageService.suspendedPreviousMessages(sbar.getId(),sbar.getMsgScenariocode(),Constants.MESSAGE);
					messageService.suspendedPreviousAlerts(sbar.getId(),sbar.getMsgScenariocode(),Constants.ALERT);
				}
				List<AcuteCare> acuteCareList=messageDao.getPrevScenarioCodeByAcuteCareId(stopAndWatchHostory.getStopAndWatch().getPatientEpisode().getId());
				for(AcuteCare acuteCare:acuteCareList){
					messageService.suspendedPreviousMessages(acuteCare.getId(),acuteCare.getMsgScenariocode(),Constants.MESSAGE);
					messageService.suspendedPreviousAlerts(acuteCare.getId(),acuteCare.getMsgScenariocode(),Constants.ALERT);
				}
			
				return stopAndWatchHostory.getId();
			}catch(Exception e){
				throw new Exception();
			}
		}
		
	}
	


	@Transactional
	public List<StopAndWatchHistoryDto> showStopAndwatchHistoryOfPatient(Long patientId,Long facilityId){
		List<StopAndWatchHistoryDto> stopAndWatchHistoryDtoList =new ArrayList<StopAndWatchHistoryDto>();
		Patient patient= patientDao.findById(patientId);
		PatientEpisode patientEpisode=patientEpisodeDao.findActiveEpisodeByPatient(patient,facilityId);
		StopAndWatch stopAndWatch=stopAndWatchDao.findActiveStopAndWatch(patientEpisode);

	List<StopAndWatchHistory> stopAndWatchHistoryList =null;
		if(stopAndWatch!=null){
		 stopAndWatchHistoryList = stopAndWatchHistoryDao.findALLSortedStopAndWatchHistorys(stopAndWatch);

		if(!CollectionUtils.isEmpty(stopAndWatchHistoryList)){
			for(StopAndWatchHistory stopAndWatchHistory:stopAndWatchHistoryList){ 
				if(stopAndWatchHistory.getDeleteFlag()!= null && stopAndWatchHistory.getInvalidateFlag()!=null){
					stopAndWatchHistoryDtoList.add(getStopAndWatchHistoryById(stopAndWatchHistory.getId()));
				}
			}	
		}
 	  }
	return stopAndWatchHistoryDtoList;
	}
	@Transactional
	public List<StopAndWatchHistoryDto> showStopAndwatchHistoryOfPatientForSbar(Long patientId ,Long facilityId,Long encounterId){
		
		List<StopAndWatchHistoryDto> stopAndWatchHistoryDtoList =new ArrayList<StopAndWatchHistoryDto>();
		List<StopAndWatch> stopAndWatchs=new ArrayList<StopAndWatch>();
		if(encounterId!=null){
			PatientEncounter patientEncounter=patientEncounterDao.findById(encounterId);
			stopAndWatchs.addAll(getStopAndWatchsOnStartAndEnd(patientEncounter.getPatientEpisode().getId(),patientEncounter.getStartDate(),patientEncounter.getEndDate()));
		}else{
		Patient patient= patientDao.findById(patientId);
		PatientEpisode patientEpisode=patientEpisodeDao.findActiveEpisodeByPatient(patient,facilityId);
		stopAndWatchs.add(stopAndWatchDao.findRecentlyClosedStopAndWatch(patientEpisode));
		}
		for(StopAndWatch stopAndWatch:stopAndWatchs){
		List<StopAndWatchHistory> stopAndWatchHistoryList = stopAndWatchHistoryDao.findALLSortedStopAndWatchHistorys(stopAndWatch);
		
		if(!CollectionUtils.isEmpty(stopAndWatchHistoryList)){
			for(StopAndWatchHistory stopAndWatchHistory:stopAndWatchHistoryList){ 
								stopAndWatchHistoryDtoList.add(getStopAndWatchHistoryById(stopAndWatchHistory.getId()));
			}	
		}
		}
		return stopAndWatchHistoryDtoList;
	}
	@Transactional
	private List<StopAndWatch> getStopAndWatchsOnStartAndEnd(Long patientEpisodeId,
			Date startDate, Date endDate) {
		 
		return stopAndWatchDao.getStopAndWatchsOnStartAndEnd(patientEpisodeDao.findById( patientEpisodeId),startDate,endDate);
	}



	@Transactional
	public List<SAWActivePatientsDto> getStopAndWatchPatients(long facilityId){

		return stopAndWatchDao.findAllActiveStopAndWatchesByFacilityId(facilityId);
		}
	@Transactional
	public List<SAWActivePatientsDto> getActiveStopAndWatchPatients(Long facilityId) {
		return stopAndWatchDao.findAllActiveStopAndWatchesByFacilityId(facilityId);
	}		
	
	@Transactional
	public StopAndWatchHistoryDto getStopAndWatchHistoryById(long stopAndWatchHistoryId){
		StopAndWatchHistoryDto stopAndWatchHistoryDto=new StopAndWatchHistoryDto();
		StopAndWatchHistory stopAndWatchHistory=stopAndWatchHistoryDao.findById(stopAndWatchHistoryId);
		String patientName=PatientUtils.getPatientFullName(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getPatient().getPatientFirstName(), stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getPatient().getPatientLastName());
		List<String> symtomList = getStopAndWatchSymtomList(stopAndWatchHistory);
	
		stopAndWatchHistoryDto.setRepotedDate(stopAndWatchHistory.getReportedDateAndTime());
		
		
			SimpleDateFormat ft =  new SimpleDateFormat ("MM/dd/yyyy hh:mm:ss a");
			if(stopAndWatchHistory.getReportedDateAndTime()!=null){
		         Date dateNtime = stopAndWatchHistory.getReportedDateAndTime();
		          stopAndWatchHistoryDto.setRepotedDateString(ft.format(dateNtime));
			}
			 else{stopAndWatchHistoryDto.setRepotedDateString("");
		      }
	     
	      if(stopAndWatchHistory.getResponseDateAndTime()!=null){
	            Date responseDateNtime=stopAndWatchHistory.getResponseDateAndTime();
	            stopAndWatchHistoryDto.setResponseDateString(ft.format(responseDateNtime));
	      }
	      else{stopAndWatchHistoryDto.setResponseDateString("");
	      }
	//		stopAndWatchHistoryDto.setRepotedDateString(stopAndWatchHistory.getReportedDateAndTime().toString());
	    stopAndWatchHistoryDto.setStopAndWatchHistoryId(stopAndWatchHistory.getId());
	    stopAndWatchHistoryDto.setResidentName(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getPatient().getPatientFirstName()
	    		+" "+stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getPatient().getPatientLastName());
	    if(stopAndWatchHistory.getReportedToFacilityStaff()!=null){
	    	    Staff staff=stopAndWatchHistory.getReportedToFacilityStaff().getStaff();
			    //stopAndWatchHistoryDto.setRepotedBy(stopAndWatchHistory.getGuestName()+"(Guest)");
		        String repotedToStaffName=StaffUtils.getStaffFullName(staff.getFirstName(), staff.getMiddleName(), staff.getLastName());
		        stopAndWatchHistoryDto.setRepotedTo(repotedToStaffName);
//	    	   stopAndWatchHistoryDto.setRepotedTo(stopAndWatchHistory.getReportedToFacilityStaff().getStaff().getFirstName());
	    }
	    if(stopAndWatchHistory.getFacilityStaff()!=null)
		{ 
	    	     Staff staff=stopAndWatchHistory.getFacilityStaff().getStaff();
	    	
			   //stopAndWatchHistoryDto.setRepotedBy(stopAndWatchHistory.getGuestName()+"(Guest)");
			  
			   String repotedByStaffName=StaffUtils.getStaffFullName(staff.getFirstName(), staff.getMiddleName(), staff.getLastName());
			   //   stopAndWatchHistoryDto.setRepotedBy(stopAndWatchHistory.getFacilityStaff().getStaff().getFirstName());
			   stopAndWatchHistoryDto.setRepotedBy(repotedByStaffName);
			   StaffRoles staffRoles= staffRolesDao.findById(stopAndWatchHistory.getFacilityStaff().getStaffRoles().getId());
			   stopAndWatchHistoryDto.setRole(staffRoles.getRoleName());
			
		}
		else
		{
			stopAndWatchHistoryDto.setRepotedBy(stopAndWatchHistory.getGuestName()+"(Guest)");
		
		}
	    if(stopAndWatchHistory.getNurse()!=null){
	    	stopAndWatchHistoryDto.setNurseName(stopAndWatchHistory.getNurse().getStaff().getFirstName());
	    	//stopAndWatchHistoryDto.setResponseDateString(responseDateString);
	    } else{
	    	stopAndWatchHistoryDto.setNurseName("");
	    }
		
	    stopAndWatchHistoryDto.setSymtomsList(symtomList );
		stopAndWatchHistoryDto.setResponse(stopAndWatchHistory.getFacilityStafResponse());
		 
		return stopAndWatchHistoryDto;
	}
	public List<String> getStopAndWatchSymtomList(StopAndWatchHistory stopAndWatchHistory){
		List<String> symtomList = new ArrayList<String>();
		if (stopAndWatchHistory.getAgitated()) {
			symtomList.add("Agitated or nervous more than usual");
		}
		if (stopAndWatchHistory.getAteLess()) {
            symtomList.add("Ate Less");
		}
		if(stopAndWatchHistory.getCommunicatesLess()) {	
			symtomList.add("Talks or communicates less");
		}
		if (stopAndWatchHistory.getChangeInSkinCondition()) {
			symtomList.add("Change in skin color or condition");
		}
		if (stopAndWatchHistory.getDrankLess()) {
			symtomList.add("Drank Less");
		}
		if (stopAndWatchHistory.getHelpWithWalking()) {
			symtomList.add("Help with walking, transferring, toileting more than usual");
		}
		if (stopAndWatchHistory.getNeedsMoreHelp()) {
			symtomList.add("Overall needs more help");
		}
		if (stopAndWatchHistory.getNoBowelMovement()) {
			symtomList.add("No bowel movement in 3 days; or diarrhea");
		}
		if (stopAndWatchHistory.getPain()) {
			symtomList.add("Pain – new or worsening; Participated less in activities");
		}
		if (stopAndWatchHistory.getSeemsDifferentThanUsual()) {
			symtomList.add("Seems Different Than Usual");
		}
		if (stopAndWatchHistory.getTired()) {
			symtomList.add("Tired, weak, confused, or drowsy");
		}
		if (stopAndWatchHistory.getWeightChange()) {
			symtomList.add("Weight Change");
		}
		if(stopAndWatchHistory.getOther()!=null&&stopAndWatchHistory.getOther())
		{
			symtomList.add(stopAndWatchHistory.getOtherSymptom());
		}
		return symtomList;
	}
	@Transactional
	public StopAndWatchHistory getStopAndWatchHistory(long stopAndWatchHistoryId)
	{
		
		StopAndWatchHistory stopAndWatchHistory=stopAndWatchHistoryDao.findById(stopAndWatchHistoryId);
		return stopAndWatchHistory;
	}

	@Transactional
	public void editStopAndWatchHistory(Long  stopAndWatchHistoryId,
			StopAndWatchDto stopAndWatchDto, FacilityStaff modifiedBy) throws Exception {
		Long facilityId=stopAndWatchDto.getFacilityId();
		String msgSenarioCode = null;
		Date repotedDateAndTime=stopAndWatchDto.getReportedDateAndTime();
		FacilityStaff nurse=facilityStaffDao.findFacilityStaff(facilityId, stopAndWatchDto.getNurseId());
		FacilityStaff reportedToFacilityStaff=facilityStaffDao.findFacilityStaff(facilityId,stopAndWatchDto.getReportedToFacilityStaffId());
		StopAndWatchHistory stopAndWatchHistory=stopAndWatchHistoryDao.findById(stopAndWatchHistoryId);
		stopAndWatchHistory.setFacilityStafResponse(stopAndWatchDto.getFacilityStafResponse());
		MessageDto messageDto=new MessageDto();
		
		 if(stopAndWatchHistory.getReportedToFacilityStaff()!=null && reportedToFacilityStaff!=null ){
				if(stopAndWatchHistory.getReportedToFacilityStaff()!=reportedToFacilityStaff){
					
				
						messageDto.setFacilityId(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getFacilityId());
						messageDto.setPrevScenarioCode(stopAndWatchHistory.getMsgScenarioCode());
				    	messageDto.setEventId(stopAndWatchHistory.getId());
						messageDto.setMsgSubj(Constants.SNW_SUBJECT);
						messageDto.setPatientEpisodeId(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getId());	
						messageDto.setReportedToNurse(stopAndWatchHistory.getReportedToFacilityStaff().getId());
					
						
				}
		    	
			}
			else{
					messageDto.setFacilityId(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getFacilityId());
					messageDto.setPrevScenarioCode(stopAndWatchHistory.getMsgScenarioCode());
					messageDto.setEventId(stopAndWatchHistory.getId());
					messageDto.setMsgSubj(Constants.SNW_SUBJECT);
					messageDto.setPatientEpisodeId(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getId());	
					messageDto.setReportedToNurse(null);
					
			}
		
		
	stopAndWatchHistory.setNurse(nurse);
		if(stopAndWatchDto.getReportedToFacilityStaffId()!=null)
		{
			stopAndWatchHistory.setReportedToFacilityStaff(reportedToFacilityStaff);
		}
		if(stopAndWatchDto.getReportedDateAndTime()!=null){
			stopAndWatchHistory.setReportedDateAndTime(stopAndWatchDto.getReportedDateAndTime());
		}
		if(stopAndWatchDto.getResponseDateAndTime()!=null){
			stopAndWatchHistory.setResponseDateAndTime(stopAndWatchDto.getResponseDateAndTime());
		}
		String modifiedByName=modifiedBy.getStaff().getFirstName();
		stopAndWatchHistory.setUserModified(modifiedByName);
		stopAndWatchHistory.setDateModified(new Date());
		
		stopAndWatchHistoryDao.saveOrUpdate(stopAndWatchHistory);
		if(stopAndWatchHistory.getReportedToFacilityStaff()!=null ){
				if(stopAndWatchHistory.getFacilityStafResponse()!=null && !stopAndWatchHistory.getFacilityStafResponse().isEmpty()){
						messageDto.setPrevScenarioCode(stopAndWatchHistory.getMsgScenarioCode());
						msgSenarioCode=Constants.SNW_RESPONDED;
						messageDto.setPatientId(stopAndWatchDto.getResidentId());
						messageDto.setCurrentScenarioCode(msgSenarioCode);
						messageDto.setFacilityId(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getFacilityId());
						messageDto.setMsgSubj(Constants.SNW_SUBJECT);
						messageDto.setPatientEpisodeId(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getId());	
						messageDto.setReportedToNurse(stopAndWatchHistory.getReportedToFacilityStaff().getId());
						stopAndWatchHistory.setMsgScenarioCode(msgSenarioCode);
						messageDto.setEventId(stopAndWatchHistory.getId());
				}
			else{
					messageDto.setPrevScenarioCode(stopAndWatchHistory.getMsgScenarioCode());
					msgSenarioCode=Constants.SNW_REPORTEDTO;
					messageDto.setPatientId(stopAndWatchDto.getResidentId());
					messageDto.setCurrentScenarioCode(msgSenarioCode);
					messageDto.setFacilityId(stopAndWatchDto.getFacilityId());
					messageDto.setReportedToNurse(stopAndWatchHistory.getReportedToFacilityStaff().getId());
					stopAndWatchHistory.setMsgScenarioCode(msgSenarioCode);
					messageDto.setPatientEpisodeId(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getId());
					messageDto.setMsgSubj(Constants.SNW_SUBJECT);
					messageDto.setSentDate(new Date());
					messageDto.setEventId(stopAndWatchHistory.getId());
			
			}
		} 
		
		messageService.saveMessages(messageDto);
		messageService.saveAlerts(messageDto);
		StopAndWatchHistoryModified  stopAndWatchHistoryModified=new StopAndWatchHistoryModified(stopAndWatchHistory, modifiedByName, new Date());
		stopAndWatchHistoryModifiedDao.saveOrUpdate(stopAndWatchHistoryModified);
	}

	@Transactional
	public List<PatientDto> getPatientNames(String patientName,Long facilityId)
	{
		List<PatientDto> patientNames=patientDao.findByName(patientName,facilityId);
		return patientNames;
	}
	


	@Transactional
	public Long getStopAndWatchDelete(long stopAndWatchHistoryId,String staffName)
	{

		StopAndWatchHistory stopAndWatchHistory=stopAndWatchHistoryDao.findById(stopAndWatchHistoryId);
		stopAndWatchHistory.setDeleteFlag(true);
		stopAndWatchHistory.setUserModified(staffName);
		stopAndWatchHistory.setDateModified(new Date());
		stopAndWatchHistoryDao.saveOrUpdate(stopAndWatchHistory);
		List<MessageAlert> messageAlertList=messageDetailsDao.getMessageAlertList(stopAndWatchHistoryId);
		List<MessageAlertDetails> messageAlertDetailsList=messageDetailsDao.getMessageAlertDetails(stopAndWatchHistoryId);
		for(MessageAlertDetails messageAlertDetails:messageAlertDetailsList){
				messageAlertDetails.setSuspendedFlag(true);
				for(MessageAlert messageAlert:messageAlertList){
				messageAlert.getmessageAlertDetailsList().add(messageAlertDetails);
				messageDetailsDao.saveOrUpdate(messageAlertDetails);
			
		}
		}
		return stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getPatient().getId();
		
	}
	@Transactional

	public StopAndWatch findByPatientId(Long searchId) {
		return stopAndWatchDao.findById(searchId);
	}
	
	@Transactional
	public StopAndWatchHistory getStopAndWatchId(Long facilityStaffId)
	{
		
		StopAndWatchHistory stopAndWatchHistory=stopAndWatchHistoryDao.findById(facilityStaffId);
		return stopAndWatchHistory;
	}
	

@Transactional
	public List<Patient> findByReportedId(FacilityStaff facilityStaff,Long staffId)
	{
		return stopAndWatchHistoryDao.findByReportedId(facilityStaff,staffId);
	}

	@Transactional
	public SNWHasResponseDto hasResponse(Long patientId,Long facilityId) {
		boolean responseExist=false;
		SNWHasResponseDto response=new SNWHasResponseDto();
		Patient patient=patientDao.findById(patientId);
		String patientInfo=patient.getId()+"="+patient.getPatientFirstName()+" "+patient.getPatientLastName();
		PatientEpisode patientEpisode=patientEpisodeDao.findActiveEpisodeByPatient(patient,facilityId);
	    StopAndWatch stopAndWatch=stopAndWatchDao.findActiveStopAndWatch(patientEpisode);
	    if(stopAndWatch!=null){
		for(StopAndWatchHistory stopAndWatchHistory:stopAndWatchHistoryDao.findALLSortedStopAndWatchHistorys(stopAndWatch)){
			if(stopAndWatchHistory.getFacilityStafResponse()!=null&&!stopAndWatchHistory.getFacilityStafResponse().isEmpty()&&!stopAndWatchHistory.getDeleteFlag()&& !stopAndWatchHistory.getInvalidateFlag()){
				responseExist=true;
				break;
			}
		}
		   response.setStopAndWatchFlag(true);
	    }else{
	    	response.setStopAndWatchFlag(false);
	    }
	//	response.setStopAndWatch(stopAndWatch);
		response.setPatientInfo(patientInfo);
		response.setHasRespnse(responseExist);
		return response;
	}

	@Transactional
	public List<PatientDto> getPatientNamesForGuest(String residentName,
			Long facilityId) {
		List<PatientDto> patientNames=patientDao.findByName(residentName,facilityId);
		return patientNames;
		
	}

	@Transactional
	public Long invalidateStopAndWatchHistoryData(Long stopAndWatchHistoryId,String staffName,Long facilityId) throws Exception {
	   StopAndWatchHistory 	stopAndWatchHistory=stopAndWatchHistoryDao.findById(stopAndWatchHistoryId);
	  
		if (stopAndWatchHistory != null) {
				stopAndWatchHistory.setInvalidateFlag(true);
				stopAndWatchHistory.setUserModified(staffName);
				stopAndWatchHistory.setDateModified(new Date());
				stopAndWatchHistoryDao.save(stopAndWatchHistory);
			if (showStopAndwatchHistoryOfPatient(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getPatient().getId(),facilityId).size() == 0) {
					StopAndWatch stopAndwatch = stopAndWatchHistory.getStopAndWatch();
					stopAndwatch.setEndDate(new Date());
					stopAndWatchDao.saveOrUpdate(stopAndwatch);
			}
			
			MessageDto messageDto=new MessageDto();
			messageDto.setPatientId(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getPatient().getId());
			messageDto.setFacilityId(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getFacilityId());
			messageDto.setPatientEpisodeId(stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getId());	
			String	msgSenarioCode=Constants.SNW_INVALID;
			messageDto.setPrevScenarioCode(stopAndWatchHistory.getMsgScenarioCode());
			
			messageDto.setCurrentScenarioCode(msgSenarioCode);
		
			stopAndWatchHistory.setMsgScenarioCode(msgSenarioCode);
			messageDto.setEventId(stopAndWatchHistory.getId());
			
			messageService.saveMessages(messageDto);
			messageService.saveAlerts(messageDto);
			
			}
		return stopAndWatchHistory.getStopAndWatch().getPatientEpisode().getPatient().getId();
	}


	@Transactional
	public List<StopAndWatchHistory> getActiveSNWHistoyofPatient(Long patientId,
			Long facilityId) {
		PatientEpisode patientEpisode=patientEpisodeService.findActicePatientEpisode(patientId, facilityId);
		StopAndWatch stopAndWatch=stopAndWatchDao.findActiveStopAndWatch(patientEpisode);
		List<StopAndWatchHistory> stopAndWatchHistoryList =null;
		if(stopAndWatch!=null){
		 stopAndWatchHistoryList = stopAndWatchHistoryDao.findALLSortedStopAndWatchHistorys(stopAndWatch);
	    }
		return stopAndWatchHistoryList;
	}


	@Transactional
	public StopAndWatch getActiveSNWByPatientEpisodeId(PatientEpisode patientEpisode) {


		return stopAndWatchDao.findActiveStopAndWatch(patientEpisode);
	}
}



