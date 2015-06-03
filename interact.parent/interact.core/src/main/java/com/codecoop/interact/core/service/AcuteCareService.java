package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.dao.AcuteCareAttibuteValuesDao;
import com.codecoop.interact.core.dao.AcuteCareAttributesDao;
import com.codecoop.interact.core.dao.AcuteCareDao;
import com.codecoop.interact.core.dao.MessageAlertDaoImpl;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.domain.AcuteCare;
import com.codecoop.interact.core.domain.AcuteCareAttributes;
import com.codecoop.interact.core.domain.AcuteCareAttributesValues;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.dto.AcuteCareAttributesValuesDto;
import com.codecoop.interact.core.dto.AcuteCareDto;
import com.codecoop.interact.core.dto.AcuteCareFormAttibNdValuesDto;
import com.codecoop.interact.core.dto.AdmissionDto;
import com.codecoop.interact.core.dto.MessageDto;
import com.codecoop.interact.core.model.LoggedUserModel;
import com.codecoop.interact.core.util.Constants;

@Service
public class AcuteCareService {

	private static final Logger LOG = Logger.getLogger(AcuteCareService.class);

	@Autowired
	AcuteCareAttributesDao acuteCareAttributesDao;

	@Autowired
	AcuteCareAttibuteValuesDao acuteCareAttibuteValuesDao;

	@Autowired
	AdmissionService admissionService;

	@Autowired
	AcuteCareDao acuteCareDao;

	@Autowired
	PatientEpisodeDao patientEpisodeDao;
	@Autowired
	SBARService sbarService;
	@Autowired
	MessageService messageService;
	@Autowired
	MessageAlertDaoImpl messageDao;
	@Transactional
	public List<AcuteCareAttributes> getAttibuteDetailByFormAttrib(String attib){
		return acuteCareAttributesDao.getAttibuteDetailByFormAttrib(attib);
	}

	@Transactional
	public String saveAcuteCareAttribValues(List<AcuteCareAttributesValuesDto> list,AcuteCareDto acuteCareDto,LoggedUserModel user){
		try{
			AcuteCare acuteCare =null;
			Long patinetId = acuteCareDto.getPatientId();
			Long pEpisodeId=getPatinetEpisodeByPatinetId(patinetId);
			if(!StringUtils.isEmpty(pEpisodeId))
				acuteCare = getAcuteCareByEpisodeId(pEpisodeId);
			if(StringUtils.isEmpty(acuteCare)){
				acuteCare =new AcuteCare();
			}
			acuteCare.setPatientEpisodeId(pEpisodeId);
			acuteCare.setPrimDiagForAdmission(acuteCareDto.getPrimDiagForAdmission());
			acuteCare.setSentToHospital(acuteCareDto.getSentToHospital());
			acuteCare.setDateOfTransfer(acuteCareDto.getDateOfTransfer());
			acuteCare.setSentFrom(acuteCareDto.getSentFrom());
			acuteCare.setFromUnit(acuteCareDto.getFromUnit());
			acuteCare.setFamilyAndOtherIssues(acuteCareDto.getFamilyAndOtherIssues());
			acuteCare.setBehaviouralIssuesAndInterventions(acuteCareDto.getBehaviouralIssuesAndInterventions());
			acuteCare.setCalledNurseForQuestion(acuteCareDto.getCalledNurseForQuestion());
			acuteCare.setSocialWorkerName(acuteCareDto.getSocialWorkerName());
			acuteCare.setSocialWorkerTel(acuteCareDto.getSocialWorkerTel());
			acuteCare.setTransferNotified(acuteCareDto.isTransferNotified());
			acuteCare.setClinicalSituationAware(acuteCareDto.isClinicalSituationAware());
			acuteCare.setFullCode(acuteCareDto.isFullCode());
			acuteCare.setDnr(acuteCareDto.isDnr());
			acuteCare.setDni(acuteCareDto.isDni());
			acuteCare.setDnh(acuteCareDto.isDnh());
			acuteCare.setComfortCareOnly(acuteCareDto.isComfortCareOnly());
			acuteCare.setUncertain(acuteCareDto.isUncertain());
			acuteCareDao.saveOrUpdate(acuteCare);		// Saving AcuteCare here....
			String status = acuteCareDto.getPatientStatusInAcuteCare();
			if(!StringUtils.isEmpty(status)){
				if(status.equalsIgnoreCase("stay")){
					stayInNursingHome(acuteCare,user);
				}
				if(status.equalsIgnoreCase("transfer")){
					transferredToHospital(acuteCare, user,null);
				}
				if(status.equalsIgnoreCase("movedFromFac")){
					transferredToHospital(acuteCare, user,"movedFromFac");
				}
			}
			List<AcuteCareAttributesValues> gotList = acuteCareAttibuteValuesDao.getAcuteCareAttributeValuesByEpisodeId(pEpisodeId);	// Getting AcuteCare attrib values

			// Here deleting Attributes values.......
			for(AcuteCareAttributesValues value:gotList){
				acuteCareAttibuteValuesDao.delete(value);
			}

			// Here saving Attributes values.......
			for(AcuteCareAttributesValuesDto dto:list){
				AcuteCareAttributesValues value=new AcuteCareAttributesValues();
				value.setpEpisodeId(pEpisodeId);
				value.setAcuteCareAttributeId(dto.getAcuteCareAttributeId());
				value.setAttributeValue(dto.getAttributeValue());
				acuteCareAttibuteValuesDao.save(value);
			}
			return "Success";
		}catch(Exception e){
			e.printStackTrace();
			return "Failed";
		}
	}

	public AdmissionDto getPatientFromAdmission(AdmissionDto admissionDto,Long facilityId){

		try{
			return admissionService.getAdmissionForm(admissionDto,facilityId,false);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("getting Patinet Information failed in AcuteCareService");
			return null;
		}

	}

	@Transactional
	public List<AcuteCareFormAttibNdValuesDto> getAcuteCareAttributeValuesByEpisodeId(Long episodeId){

		List<AcuteCareAttributesValues> attribsNdValues=null;
		List<AcuteCareFormAttibNdValuesDto> dtoList = new ArrayList<AcuteCareFormAttibNdValuesDto>();
		attribsNdValues=acuteCareAttibuteValuesDao.getAcuteCareAttributeValuesByEpisodeId(episodeId);
		for(AcuteCareAttributesValues attrVal:attribsNdValues){
			AcuteCareFormAttibNdValuesDto dto = new AcuteCareFormAttibNdValuesDto();
			AcuteCareAttributes acuteAttibObj = acuteCareAttributesDao.findById(attrVal.getAcuteCareAttributeId());
			dto.setAttibute(acuteAttibObj.getFormAttrib());
			dto.setType(acuteAttibObj.getValueType());
			dto.setAttibValue(attrVal.getAttributeValue());
			dto.setAttribId(attrVal.getId());
			dtoList.add(dto);
		}
		return dtoList;

	}

	private Long getPatinetEpisodeByPatinetId(Long patinetId){
		if(!StringUtils.isEmpty(patinetId)){
			PatientEpisode pEpisode = patientEpisodeDao.findActiveEpisodeByPatientId(patinetId);
			return pEpisode.getId();
		}else{
			return null;
		}

	}

	@Transactional
	public AcuteCare getAcuteCareByEpisodeId(Long pEpisodeId){
		return acuteCareDao.getAcuteCareByEpisodeId(pEpisodeId);
	}

	@Transactional
	public AcuteCareDto  getAcuteCareDtoByEpisodeId(Long pEpisodeId){
		AcuteCare acuteCare = acuteCareDao.getAcuteCareByEpisodeId(pEpisodeId);
		if(!StringUtils.isEmpty(acuteCare)){
			AcuteCareDto dto = new AcuteCareDto();
			dto.setId(acuteCare.getId());
			dto.setPatientEpisodeId(acuteCare.getPatientEpisodeId());
			dto.setPrimDiagForAdmission(acuteCare.getPrimDiagForAdmission());
			dto.setSentToHospital(acuteCare.getSentToHospital());
			dto.setFormCompletedBy(acuteCare.getFormCompletedBy());
			dto.setDateOfTransfer(acuteCare.getDateOfTransfer());
			dto.setSentFrom(acuteCare.getSentFrom());
			dto.setFromUnit(acuteCare.getFromUnit());
			dto.setFamilyAndOtherIssues(acuteCare.getFamilyAndOtherIssues());
			dto.setBehaviouralIssuesAndInterventions(acuteCare.getBehaviouralIssuesAndInterventions());
			dto.setCalledNurseForQuestion(acuteCare.getCalledNurseForQuestion());
			dto.setSocialWorkerName(acuteCare.getSocialWorkerName());
			dto.setSocialWorkerTel(acuteCare.getSocialWorkerTel());
			dto.setTransferNotified(acuteCare.isTransferNotified());
			dto.setClinicalSituationAware(acuteCare.isClinicalSituationAware());
			dto.setFullCode(acuteCare.isFullCode());
			dto.setDnr(acuteCare.isDnr());
			dto.setDni(acuteCare.isDni());
			dto.setDnh(acuteCare.isDnh());
			dto.setComfortCareOnly(acuteCare.isComfortCareOnly());
			dto.setUncertain(acuteCare.isUncertain());
			dto.setSendToHospitalFlag(acuteCare.isSendToHospitalFlag());
			dto.setStayInAcutecareFlag(acuteCare.isStayInAcuteCareFlag());
			return dto;
		}
		return new AcuteCareDto();
	}
	@Transactional
	public void stayInNursingHome(AcuteCare acuteCare, LoggedUserModel user) throws Exception {
		try{
			PatientEpisode patientEpisode=patientEpisodeDao.findById(acuteCare.getPatientEpisodeId());
			if(acuteCare!=null)
			{
				acuteCare.setStayInAcuteCareFlag(true);
				acuteCare.setDateModified(new Date());
				//acuteCareDao.saveOrUpdate(acuteCare);
				String reason="Patient came from hospital imediatlly (closed in Accute care Transger queue)";
				sbarService.encounterColse(patientEpisode,user.getFullName(),reason);

				List<Sbar> sbarList=messageDao.getPrevScenarioCodeBySbarId(patientEpisode.getId());
				for(Sbar sbar:sbarList){
					messageService.suspendedPreviousMessages(sbar.getId(),sbar.getMsgScenariocode(),Constants.MESSAGE);
					messageService.suspendedPreviousAlerts(sbar.getId(),sbar.getMsgScenariocode(),Constants.ALERT);
				}
				MessageDto messageDto=new MessageDto();
				messageDto.setReportedToDocter(user.getFstaffId());
				messageDto.setFacilityId(patientEpisode.getFacilityId());
				messageDto.setPatientEpisodeId(patientEpisode.getId());
				messageDto.setPatientId(patientEpisode.getPatient().getId());
				messageDto.setMsgSubj(Constants.BACKIN_FACILITY_SUBJ);
				messageDto.setPrevScenarioCode(acuteCare.getMsgScenariocode());
				messageDto.setCurrentScenarioCode(Constants.PATIENT_BACKIN_FACILITY);
				acuteCare.setMsgScenariocode(Constants.PATIENT_BACKIN_FACILITY);
				acuteCareDao.saveOrUpdate(acuteCare);
				messageDto.setEventId(acuteCare.getId());
				messageDto.setDeleteFlag(false);
				messageDto.setReadFlag(false);
				messageDto.setSentDate(new Date());
				messageService.saveMessages(messageDto);
				messageService.saveAlerts(messageDto);

			}

		}catch(Exception e){
			throw new Exception();
		}

	}
	@Transactional
	public void transferredToHospital(AcuteCare acuteCare, LoggedUserModel user,String status) throws Exception {
		try{

			PatientEpisode patientEpisode=patientEpisodeDao.findById(acuteCare.getPatientEpisodeId());
			if(acuteCare!=null)
			{
				acuteCare.setSendToHospitalFlag(true);
				acuteCare.setDateModified(new Date());
				acuteCareDao.saveOrUpdate(acuteCare);
				String reason="Patient Sended to hospital for LongTearm (Episode Closed in AcuteCare queue)";
				sbarService.encounterColse(patientEpisode,user.getFullName(),reason);

				if(!StringUtils.isEmpty(status) && status.equalsIgnoreCase("movedFromFac")){
					patientEpisode.setFacilityDischargeDate(new Date());
					patientEpisode.setDateModified(new Date());
					patientEpisode.setUserModified(user.getFacilityName());
					patientEpisodeDao.saveOrUpdate(patientEpisode);
				}else{
					MessageDto messageDto=new MessageDto();
					List<Sbar> sbarList=messageDao.getPrevScenarioCodeBySbarId(patientEpisode.getId());
					for(Sbar sbar:sbarList){
						messageService.suspendedPreviousMessages(sbar.getId(),sbar.getMsgScenariocode(),Constants.MESSAGE);
						messageService.suspendedPreviousAlerts(sbar.getId(),sbar.getMsgScenariocode(),Constants.ALERT);
					}
					messageDto.setReportedToDocter(user.getFstaffId());
					messageDto.setFacilityId(patientEpisode.getFacilityId());
					messageDto.setPatientEpisodeId(patientEpisode.getId());
					messageDto.setPatientId(patientEpisode.getPatient().getId());
					messageDto.setMsgSubj(Constants.TRANSFER_HOSPITAL_SUBJ);
					messageDto.setPrevScenarioCode(acuteCare.getMsgScenariocode());
					messageDto.setCurrentScenarioCode(Constants.PATIENT_TRANSFER_HOSPITAL);
					acuteCare.setMsgScenariocode(Constants.PATIENT_TRANSFER_HOSPITAL);
					acuteCareDao.saveOrUpdate(acuteCare);
					messageDto.setEventId(acuteCare.getId());
					messageDto.setDeleteFlag(false);
					messageDto.setReadFlag(false);
					messageDto.setSentDate(new Date());
					messageService.saveMessages(messageDto);
					messageService.saveAlerts(messageDto);
				}
			}

		}catch(Exception e){
			throw new Exception();
		}
	}

}
