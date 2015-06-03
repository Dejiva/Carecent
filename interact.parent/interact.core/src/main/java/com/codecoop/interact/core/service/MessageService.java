package com.codecoop.interact.core.service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.AlertsToAssignedDaoImpl;
import com.codecoop.interact.core.dao.AlertsToOtherStaffDaoImpl;
import com.codecoop.interact.core.dao.AssignedDocRelationDaoImpl;
import com.codecoop.interact.core.dao.FacilityDaoImpl;
import com.codecoop.interact.core.dao.FacilityStaffDaoImpl;
import com.codecoop.interact.core.dao.MessageAlertDaoImpl;
import com.codecoop.interact.core.dao.MessageAlertDetailsDaoImpl;
import com.codecoop.interact.core.dao.MessageAlertScenariosDaoImpl;
import com.codecoop.interact.core.dao.MessageEligibilityStaffDaoImpl;
import com.codecoop.interact.core.dao.PatientDaoImpl;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.dao.StopAndWatchDaoImpl;
import com.codecoop.interact.core.dao.StopAndWatchHistoryDaoImpl;
import com.codecoop.interact.core.dao.SystemPropertiesDaoImpl;
import com.codecoop.interact.core.domain.AlertsToAssigned;
import com.codecoop.interact.core.domain.AlertsToOtherStaff;
import com.codecoop.interact.core.domain.Facility;
import com.codecoop.interact.core.domain.FacilityContact;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.MessageAlert;
import com.codecoop.interact.core.domain.MessageAlertDetails;
import com.codecoop.interact.core.domain.MessageAlertScenarios;
import com.codecoop.interact.core.domain.MessageEligibilityStaff;
import com.codecoop.interact.core.domain.Patient;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.StopAndWatch;
import com.codecoop.interact.core.domain.StopAndWatchHistory;
import com.codecoop.interact.core.domain.SystemProperties;
import com.codecoop.interact.core.dto.GenericDto;
import com.codecoop.interact.core.dto.MessageDto;
import com.codecoop.interact.core.dto.SbarDto;
import com.codecoop.interact.core.dto.SecondAlertMapperDto;
import com.codecoop.interact.core.dto.StaffDto;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.core.util.CoreAppPropertyReader;
import com.codecoop.interact.core.util.PatientUtils;
@EnableScheduling
@Service
public class MessageService {

	private static final Logger LOG=Logger.getLogger(MessageService.class);


	@Autowired
	SystemPropertiesDaoImpl property;
	
	@Autowired
	PatientDaoImpl patientDao;
	@Autowired
	PatientEpisodeDao PatientEpisodeDao;

	@Autowired
	MessageAlertDaoImpl messageDao;

	@Autowired
	FacilityDaoImpl facilityDao;
	@Autowired
	MessageAlertDetailsDaoImpl messageDetailsDao;


	@Autowired
	FacilityStaffDaoImpl facilityStaffDao;

	@Autowired
	MessageEligibilityStaffDaoImpl messageEligibilityStaffDao;
	@Autowired
	MessageAlertScenariosDaoImpl messageAlertScenariosDao;
	@Autowired
	AlertsToAssignedDaoImpl alertsToAssignedDao;
	@Autowired
	AlertsToOtherStaffDaoImpl  alertsToOtherStaffDao;
	@Autowired
	MessageAlertDetailsDaoImpl messageAlertDetailsDao;
	@Autowired 
	MailService mailService;
	@Autowired
	MessageAlertDaoImpl messageAlertDao;
	@Autowired
	StopAndWatchHistoryDaoImpl stopAndWatchHistoryDao;
	@Autowired
	CoreAppPropertyReader coreAppPropertyReader;


	@Autowired
	AssignedDocRelationDaoImpl assignedDocRelationDao;
	@Autowired
	StopAndWatchDaoImpl stopAndWatchDao;
	@Transactional
	public List<MessageDto> getMessages(Long staffId,Long facilityId, String msgSubj,String msgType){
		FacilityStaff facilityStaff=facilityStaffDao.findFacilityStaff(facilityId, staffId);
		List<MessageDto> messageDtoList = new ArrayList<MessageDto>();
		if(facilityStaff !=null){
			messageDtoList=messageDao.getMessages(facilityStaff.getId(),msgSubj,msgType);
		}
		return messageDtoList;
	}
	/*@Transactional
	public List<MessageDto> getMessagesBySubj(String msgSubj,Long staffId,Long facilityId,String msgType){
		FacilityStaff facilityStaff=facilityStaffDao.findFacilityStaff(facilityId, staffId);

		List<MessageDto>	messageDtoList=messageDao.getMessagesBySubj(msgSubj,facilityStaff.getId(),msgType);
		return messageDtoList;
	}*/
	@Transactional
	public List<MessageDto> getLimitedMessages(Long staffId,Long facilityId,String msgType, int offset, int noOfRecords,String msgSubject){
		FacilityStaff facilityStaff=facilityStaffDao.findFacilityStaff(facilityId, staffId);
		List<MessageDto> messageDtoList = new ArrayList<MessageDto>();
		if(facilityStaff !=null){
			messageDtoList=messageDao.getLimitedMessages(facilityStaff.getId(), msgType, offset, noOfRecords,msgSubject);
		}
		return messageDtoList;
	}
	@Transactional
	public List<MessageDto> getAlerts(Long staffId,Long facilityId,String alertType){
		FacilityStaff facilityStaff=facilityStaffDao.findFacilityStaff(facilityId,staffId);
		List<MessageDto> messageDtoList = new ArrayList<MessageDto>();
		if(facilityStaff !=null){
			messageDtoList=messageDao.getAlerts(facilityStaff.getId(), alertType);
		}
		return messageDtoList;
	}

	@Transactional
	public SbarDto getSbarData(Long Patient_episode_id){
		SbarDto sbarDto=messageDao.getSbarData(Patient_episode_id);
		return  sbarDto;
	}


	@Transactional
	public void saveMessages(MessageDto dto) {
		Long msgAlertscenarioId = null;
		String msgTemp=null;
		String msgTemplate=null;
		MessageAlert messageAlert = new MessageAlert();
		if(dto.getCurrentScenarioCode()!=null){
			MessageAlertScenarios messageAlertScenarios = messageAlertScenariosDao.getMsgScenarioById(dto.getCurrentScenarioCode());
			msgAlertscenarioId=messageAlertScenarios.getId();
			msgTemp = messageAlertScenariosDao.getMsgScenarioById(dto.getCurrentScenarioCode()).getMessageTemplate();
			msgTemplate = msgAlertTemplate(msgTemp, dto);
			if(messageAlertScenarios.getSuspendedPrevScenarioFlag()){


				suspendedPreviousMessages(dto.getEventId(),dto.getPrevScenarioCode(),Constants.MESSAGE);
			}

		}


		if(messageEligibilityStaffDao.getSuspendedFlag(msgAlertscenarioId)!=null){
			if(messageEligibilityStaffDao.getSuspendedFlag(msgAlertscenarioId)){

				suspendedPreviousMessages(dto.getEventId(),dto.getPrevScenarioCode(),Constants.MESSAGE);
			}
		}
		List<MessageEligibilityStaff> messageEligibiltyStaffList = messageEligibilityStaffDao.getEligibiltyStaff(msgAlertscenarioId);
		for (MessageEligibilityStaff messageEligibilityStaff : messageEligibiltyStaffList) {

			List<FacilityStaff> staffList = messageEligibilityStaffDao.getStaffList(dto.getFacilityId(),messageEligibilityStaff.getResponsibilitiesId());
			messageAlert.setMsgSubj(dto.getMsgSubj());
			messageAlert.setPatientEpisodeId(dto.getPatientEpisodeId());
			messageAlert.setMsgBody(msgTemplate);
			messageAlert.setEventId(dto.getEventId());
			messageAlert.setMsgScenarioCode(dto.getCurrentScenarioCode());
			if(dto.getReportedToNurse()!=null){
				messageAlert.setAssignedFacilityStaffId(dto.getReportedToNurse());
			}
			else if(dto.getReportedToDocter()!=null){

				messageAlert.setAssignedFacilityStaffId(dto.getReportedToDocter());
			}

			messageAlert.setMessageAlertType(Constants.MESSAGE);
			messageDao.saveOrUpdate(messageAlert);
			for(FacilityStaff staff : staffList) {
				if(staff!=null&& staff.getId()!=null){

					MessageAlertDetails	messageAlertDetails = new MessageAlertDetails();
					if(dto.getReportedToNurse()!=null){
						if(dto.getReportedToNurse().equals(staff.getId())){
							messageAlertDetails.setAssignedFlag(true);
						}
					}
					else if(dto.getReportedToDocter()!=null){
						if(dto.getReportedToDocter().equals(staff.getId())){
							messageAlertDetails.setAssignedFlag(true);
						}
					}
					messageAlertDetails.setFacilityStaffId(staff.getId());
					messageAlertDetails.setMessageAlert(messageAlert);
					messageAlertDetails.setReadFlag(false);
					messageAlertDetails.setSentDate(dto.getSentDate());
					messageAlertDetails.setDeleteFlag(false);
					messageAlertDetails.setSuspendedFlag(false);
					messageAlert.getmessageAlertDetailsList().add(messageAlertDetails);
					messageDetailsDao.saveOrUpdate(messageAlertDetails);
				}
			}						
		}

	}



	@Transactional
	public  Long deleteMessages(Long messageAlertDetailsId){
		return  messageAlertDetailsDao.deleteMessages(messageAlertDetailsId);

	}

	@Transactional
	public  Long deleteAlerts(Long messageAlertDetailsId){
		return  messageAlertDetailsDao.deleteAlerts(messageAlertDetailsId);

	}


	@Transactional
	public  List<MessageDto> findByMsgsubj(String msgSubj,Long facilityId,Long staffId,int offSet,int noOfRecords){
		FacilityStaff facilityStaff=facilityStaffDao.findFacilityStaff(facilityId, staffId);
		List<MessageDto> messageDtolist=new ArrayList<MessageDto>();
		if(facilityStaff!=null){
		return  messageAlertDao.findByMsgsubj(msgSubj,facilityStaff.getId(),offSet,noOfRecords);
		}else{
			return messageDtolist;
		}

	}


	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void getGenericData(){
		/*List<SecondAlertMapperDto> secondAlertMapperDtoList=secondAlertMapperDao.getSecondAlertMapper();
		for(SecondAlertMapperDto secondAlertMapperDto:secondAlertMapperDtoList){
			List<GenericDto>  genericDtoIds=secondAlertMapperDao.getGenericDtoList(secondAlertMapperDto.getReferenceTable(),secondAlertMapperDto.getReferenceColumn());
			for(GenericDto genericDto :genericDtoIds){
				Long eventId=genericDto.getId();
				Date reportedDate=genericDto.getReportedDate();
				Date dateCreated=genericDto.getDateCreated();
				long diffMinutes;
				String referenceTable= secondAlertMapperDto.getReferenceTable();
				if(referenceTable.equalsIgnoreCase("STOP_AND_WATCH_HISTORY")){
					if(reportedDate!=null){
						long diff =new Date().getTime()- reportedDate.getTime();
						diffMinutes =(int) (diff / (60 * 1000));
						StopAndWatchHistory stopAndWatchHistory=stopAndWatchHistoryDao.findById(eventId);
						stopAndWatchHistory.setWaitTime(diffMinutes);
						stopAndWatchHistory.setMsgScenarioCode(genericDto.getMsgScenarioCode());
						stopAndWatchHistoryDao.saveOrUpdate(stopAndWatchHistory);
					}
					else{
						long diff =new Date().getTime()- dateCreated.getTime();
						diffMinutes =(int) (diff / (60 * 1000));
						StopAndWatchHistory stopAndWatchHistory=stopAndWatchHistoryDao.findById(eventId);
						stopAndWatchHistory.setWaitTime(diffMinutes);
						stopAndWatchHistory.setMsgScenarioCode(genericDto.getMsgScenarioCode());
						stopAndWatchHistoryDao.saveOrUpdate(stopAndWatchHistory);
					}

				}



		 */
	}	




	@Transactional
	@Scheduled(cron="0 0/10 * * * ?")
	public void sendMailAlertSchedules() throws Exception{
		List<GenericDto>  genericDtoList=messageAlertDetailsDao.getSecondAlertViewData();
		for(GenericDto  genericDto:genericDtoList){
			Long eventId=genericDto.getEventId();

			if(genericDto.getWaitTime()!=null && genericDto.getWaitTime() > genericDto.getMaxWaitTime()){
				MessageDto messageDto=new MessageDto();
				if(genericDto.getReportedFacilityStaffId()!=null){
					messageDto.setReportedToNurse(genericDto.getReportedFacilityStaffId());
				}
				messageDto.setMsgSubj(genericDto.getMsgSubj());
				messageDto.setFacilityId(genericDto.getFacilityId());
				messageDto.setPatientEpisodeId(genericDto.getPatientEpisodeId());
				messageDto.setCurrentScenarioCode(genericDto.getCurrentScenarioCode());
				messageDto.setPrevScenarioCode(genericDto.getPreviousScenarioCode());
				messageDto.setSentDate(new Date());
				messageDto.setEventId(eventId);
				saveMessages(messageDto);
				saveAlerts(messageDto);
				Long val=messageAlertDetailsDao.updateMailAlertAndScenarioCode(genericDto.getTableName(),genericDto.getCurrentScenarioCode(),genericDto.getEventId());

			}

		}
	}



	@Transactional
	public void saveAlerts(MessageDto dto) throws Exception {
		
		String alertMessage=null;
		List<String> devices=new ArrayList<String>();
		
		Long MsgAlertscenarioId=null;
		String facilityContact="";
		List<StaffDto> staffList = new ArrayList<StaffDto>();
		Facility facility=facilityDao.findById(dto.getFacilityId());
		String facilityName=facility.getFacilityName();
		for(FacilityContact fcontact:facility.getFacilityContacts()){
			if(fcontact.getContactType().equals(Constants.FACILITY_CONTACT_MOBILE_TYPE)){
				 facilityContact=fcontact.getContact();
				break;
			}
			
		}
		
		
		StaffDto staffDto=null;
		MessageAlert messageAlert = new MessageAlert();
		if(dto.getCurrentScenarioCode()!=null){
			MsgAlertscenarioId = messageAlertScenariosDao.getMsgScenarioById(dto.getCurrentScenarioCode()).getId();
		}
		AlertsToAssigned alertsToAssignedStaff = alertsToAssignedDao.getAlertsToAssignedStaff(MsgAlertscenarioId);
		if(alertsToAssignedStaff!=null){
			if(alertsToAssignedStaff.getSendToAssign()) {
				if(dto.getReportedToNurse()!=null){
					FacilityStaff fStaff=facilityStaffDao.findById(dto.getReportedToNurse());
					staffDto=new StaffDto();
					staffDto.setStaffId(dto.getReportedToNurse());
					staffDto.setWorkEmail(fStaff.getWorkEmail());
					staffDto.setStaffName(fStaff.getStaff().getFirstName());
					staffDto.setDeviceToken(fStaff.getStaff().getDeviceToken());
					staffDto.setDeviceType(fStaff.getStaff().getDeviceType());
					staffDto.setDeviceLoggedStatus(fStaff.getStaff().getDeviceLoggedStatus());
				}
				else if(dto.getReportedToDocter()!=null)
				{
					staffDto=new StaffDto();
					FacilityStaff fStaff1=facilityStaffDao.findById(dto.getReportedToDocter());
					staffDto.setStaffId(dto.getReportedToDocter());	
					staffDto.setWorkEmail(fStaff1.getWorkEmail());
					staffDto.setStaffName(fStaff1.getStaff().getFirstName());	
					staffDto.setDeviceToken(fStaff1.getStaff().getDeviceToken());
					staffDto.setDeviceType(fStaff1.getStaff().getDeviceType());
					staffDto.setDeviceLoggedStatus(fStaff1.getStaff().getDeviceLoggedStatus());
					if(dto.getDocList()!=null){
						for(Long facilityStaffId:dto.getDocList()){
							FacilityStaff paNpStaff=facilityStaffDao.findById(facilityStaffId);
							StaffDto facilitystaffDto=new StaffDto();
							facilitystaffDto.setStaffId(facilityStaffId);
							facilitystaffDto.setStaffName(paNpStaff.getStaff().getFirstName());
							facilitystaffDto.setWorkEmail(paNpStaff.getWorkEmail());
							String alertTemplate = msgAlertTemplate(alertsToAssignedStaff.getAlertTemplateAssign(), dto);
							facilitystaffDto.setAlertTemplate(alertTemplate);
							staffList.add(facilitystaffDto);
						}
					}
				}
				if(staffDto!=null){
					String alertTemplate = msgAlertTemplate(alertsToAssignedStaff.getAlertTemplateAssign(), dto);
					staffDto.setAlertTemplate(alertTemplate);
					staffList.add(staffDto);
				}
			}

			if(alertsToAssignedStaff.getSuspendedPrevScenarioFlag()){

				suspendedPreviousAlerts(dto.getEventId(),dto.getPrevScenarioCode(),Constants.ALERT);
			}
			List<AlertsToOtherStaff> alertsToOtherStaffList = alertsToAssignedDao.getAlertsToOtherStaff(alertsToAssignedStaff.getId());
			for(AlertsToOtherStaff alertsToOtherStaff:alertsToOtherStaffList){
				List<FacilityStaff> facilityStaffList=alertsToOtherStaffDao.getOtherStaffList(alertsToOtherStaff.getToOtherStaff(),dto.getFacilityId());
				for(FacilityStaff facilityStaff:facilityStaffList){
					StaffDto staffDto1=new StaffDto();
					staffDto1.setStaffId(facilityStaff.getId());
					String alertTemplate = msgAlertTemplate(alertsToOtherStaff.getAlertTemplateOtherStaff(), dto);
					staffDto1.setAlertTemplate(alertTemplate);
					staffDto1.setWorkEmail(facilityStaff.getWorkEmail());
					staffDto1.setStaffName(facilityStaff.getStaff().getFirstName());
					staffDto1.setDeviceToken(facilityStaff.getStaff().getDeviceToken());
					staffDto1.setDeviceType(facilityStaff.getStaff().getDeviceType());
					staffDto1.setDeviceLoggedStatus(facilityStaff.getStaff().getDeviceLoggedStatus());
					staffList.add(staffDto1);

				}
				if(alertsToOtherStaff.getSuspendedPrevScenarioFlag()){
					List<MessageAlertDetails> messageAlertDetailsList=messageDao.getMessageAlertDetailsData(dto.getEventId(),dto.getPrevScenarioCode(),Constants.ALERT);

					suspendedPreviousAlerts(dto.getEventId(),dto.getPrevScenarioCode(),Constants.ALERT);
				}
			}
			for(StaffDto staff: staffList){
				if(staff!=null&& staff.getStaffId()!=null){
					
					//code to send the mobile notification to perticular staff.
					if(staff.getDeviceLoggedStatus()!=null){
						if(staff.getDeviceLoggedStatus()){	
							try {
								sendMobileNotifications(staff.getAlertTemplate(), staff.getDeviceToken());
							} catch (Exception e) {
									e.printStackTrace();
							}
						}
					}
					
					messageAlert.setMsgBody(staff.getAlertTemplate());
					messageAlert.setMsgSubj(dto.getMsgSubj());
					messageAlert.setEventId(dto.getEventId());
					messageAlert.setMsgScenarioCode(dto.getCurrentScenarioCode());
					messageAlert.setMessageAlertType(Constants.ALERT);
					messageAlert.setPatientEpisodeId(dto.getPatientEpisodeId());

					if(dto.getReportedToNurse()!=null){
						messageAlert.setAssignedFacilityStaffId(dto.getReportedToNurse());
					}
					else if(dto.getReportedToDocter()!=null){

						messageAlert.setAssignedFacilityStaffId(dto.getReportedToDocter());

					}

					messageDao.saveOrUpdate(messageAlert);

					MessageAlertDetails	messageAlertDetails = new MessageAlertDetails();
					if(dto.getReportedToNurse()!=null){
						if(dto.getReportedToNurse().equals(staff.getStaffId())){
							messageAlertDetails.setAssignedFlag(true);
						}
					}
					else if(dto.getReportedToDocter()!=null){
						if(dto.getReportedToDocter().equals(staff.getStaffId())){
							messageAlertDetails.setAssignedFlag(true);
						}
					}
					messageAlertDetails.setFacilityStaffId(staff.getStaffId());
					messageAlertDetails.setMessageAlert(messageAlert);

					messageAlertDetails.setReadFlag(false);
					messageAlertDetails.setSentDate(dto.getSentDate());
					messageAlertDetails.setDeleteFlag(false);
					messageAlertDetails.setSuspendedFlag(false);
					messageAlert.getmessageAlertDetailsList().add(messageAlertDetails);
					messageDetailsDao.saveOrUpdate(messageAlertDetails);
					try{
					String messageBody1="Dear "+staff.getStaffName()+",\n\n"+staff.getAlertTemplate()+" at "+facilityName;
					if(dto.getContactInfo()!=null &&!StringUtils.isEmpty(dto.getContactInfo())){
						messageBody1+="\n\n"+dto.getContactInfo();
					}
					if(facilityContact!=null&&!facilityContact.isEmpty()){
						messageBody1+="\n\nFacility contact number: "+facilityContact;
					}
					
					if(staff.getWorkEmail()!=null){

						mailService.sendMail(dto.getMsgSubj(), messageBody1, staff.getWorkEmail(),coreAppPropertyReader.getMessageMailSender());
					}
					}
					catch(Exception e){
						LOG.error(e);
						throw new Exception();

					}
				}


			}
		}
	
		
		
	}






	public String msgAlertTemplate(String msgtemplate,MessageDto dto)
	{
		String patientName="";
		String staffName="";
		Long patientEpisodeId=dto.getPatientEpisodeId();
		PatientEpisode patientEpisode=PatientEpisodeDao.findById(patientEpisodeId);
		if(patientEpisode.getPatient().getId()!=null){
			if(msgtemplate!=null){
				Patient p= patientDao.findById(patientEpisode.getPatient().getId());
				patientName=PatientUtils.getPatientFullName(p.getPatientFirstName(), p.getPatientLastName());
				msgtemplate=msgtemplate.replace(Constants.MSG_ALERT_TEMPLATE_PATIENT_CODE, patientName);
			}
		}
		if(dto.getReportedToNurse()!=null){
			if(msgtemplate!=null){
				staffName=facilityStaffDao.findById(dto.getReportedToNurse()).getStaff().getFirstName();
				msgtemplate= msgtemplate.replace(Constants.MSG_ALERT_TEMPLATE_NURSE_CODE, staffName);
			}
		}
		if(dto.getReportedToDocter()!=null){
			if(msgtemplate!=null){

				staffName=facilityStaffDao.findById(dto.getReportedToDocter()).getStaff().getFirstName();
				msgtemplate= msgtemplate.replace(Constants.MSG_ALERT_TEMPLATE_DOCTOR_CODE,staffName);

			}
		}

		return msgtemplate;
	}



	@Transactional
	public List<String> getMsgSubj(Long facilityId,Long staffId)
	{    
		FacilityStaff facilityStaff=facilityStaffDao.findFacilityStaff(facilityId, staffId);
		if(facilityStaff !=null){
			return messageAlertDetailsDao.getMsgSubj(facilityStaff.getId());
		}else{
			return new ArrayList<String>();
		}
	}

	public void suspendedPreviousMessages(Long eventId,String prevScenariocode,String messageAlertType){
		MessageAlert messageAlert=new MessageAlert();
		List<MessageAlertDetails> messageAlertDetailsList=messageDao.getMessageAlertDetailsData(eventId,prevScenariocode,messageAlertType);
		for(MessageAlertDetails messageAlertDetails:messageAlertDetailsList){
			messageAlert.setMessageAlertType(Constants.MESSAGE);
			messageAlertDetails.setSuspendedFlag(true);
			messageAlert.getmessageAlertDetailsList().add(messageAlertDetails);
			messageDetailsDao.saveOrUpdate(messageAlertDetails);

		}
	}

	public void suspendedPreviousAlerts(Long eventId,String prevScenariocode,String messageAlertType){
		MessageAlert messageAlert=new MessageAlert();
		List<MessageAlertDetails> messageAlertDetailsList=messageDao.getMessageAlertDetailsData(eventId,prevScenariocode,messageAlertType);
		for(MessageAlertDetails messageAlertDetails:messageAlertDetailsList){
			messageAlert.setMessageAlertType(Constants.MESSAGE);
			messageAlertDetails.setSuspendedFlag(true);
			messageAlert.getmessageAlertDetailsList().add(messageAlertDetails);
			messageDetailsDao.saveOrUpdate(messageAlertDetails);

		}
	}
	
	@Transactional
	public void sendMobileNotifications(String message, String devices) {
		try {

			SystemProperties p12FilePath = property
					.findByPropertyName("P12_FILE");

			List<PushedNotification> notifications = Push.alert(message,
					p12FilePath.getPropertyValue(), "Aa123456", true, devices);

			for (PushedNotification notification : notifications) {
				if (notification.isSuccessful()) {
					/* Apple accepted the notification and should deliver it */
					System.out
							.println("Push notification sent successfully to: "
									+ notification.getDevice().getToken());
					/* Still need to query the Feedback Service regularly */
				} else {
					String invalidToken = notification.getDevice().getToken();
					/* Add code here to remove invalidToken from your database */

					/* Find out more about what the problem was */
					Exception theProblem = notification.getException();
					theProblem.printStackTrace();

					/*
					 * If the problem was an error-response packet returned by
					 * Apple, get it
					 */
					ResponsePacket theErrorResponse = notification
							.getResponse();
					if (theErrorResponse != null) {
						System.out.println(theErrorResponse.getMessage());
					}
				}
			}

		} catch (KeystoreException e) {
			/* A critical problem occurred while trying to use your keystore */
			e.printStackTrace();

		} catch (CommunicationException e) {
			/*
			 * A critical communication error occurred while trying to contact
			 * Apple servers
			 */
			e.printStackTrace();
		}

	}
}