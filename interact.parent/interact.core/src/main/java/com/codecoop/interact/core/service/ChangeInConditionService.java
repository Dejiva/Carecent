package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.codecoop.interact.core.dao.CICDecisionParmsDaoImpl;
import com.codecoop.interact.core.dao.ChangeInConditionDao;
import com.codecoop.interact.core.dao.PatientEncounterDaoImpl;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.dao.SbarDaoImpl;
import com.codecoop.interact.core.dao.SignsSymptomsLabworkAttrDao;
import com.codecoop.interact.core.domain.ChangeInCondition;
import com.codecoop.interact.core.domain.CicDecisionParms;
import com.codecoop.interact.core.domain.PatientEncounter;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.SignsSymptomsLabworkAttr;
import com.codecoop.interact.core.dto.ChangeInConditionDto;
import com.codecoop.interact.core.enumpack.ChangeInConditionType;
import com.codecoop.interact.core.util.Constants;

/**
 * 
 * @author jgumparthi
 *
 */
@Service
public class ChangeInConditionService {
	
	private static final Logger LOG = Logger.getLogger(ChangeInConditionService.class);
	
	@Autowired
	private ChangeInConditionDao changeInConditionDao;
	
	@Autowired
	private SignsSymptomsLabworkAttrDao signsSymptomsLabworkAttrDao;
	@Autowired
	private SbarDaoImpl sbarDao;
	@Autowired
	private CICDecisionParmsDaoImpl cicDecisionParmsDao;
	@Autowired
	PatientEpisodeDao patientEpisodeDao;
	@Autowired
	PatientEncounterDaoImpl patientEncounterDao;
    @Autowired
    SBARService sbarService;
	@Transactional
	public ChangeInCondition getChangeInConditionByAttrId(Long changeInConditionId){
		
		return changeInConditionDao.findById(changeInConditionId);
	}
	
	@Transactional
	public void deleteChangeInConditionRecordById(Long changeInConditionId){
		ChangeInCondition cic = changeInConditionDao.findById(changeInConditionId);
		changeInConditionDao.delete(cic);
	}
	
	@Transactional
	public void deleteChangeInConditionRecordByEpisodeAndSymptomId(Long patientEpisodeId, Long symptomId){
		PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
		List<ChangeInCondition> cicList = fetchChangeInConditionsByEpisodeId(sbarDao.findByPatientEpisode(patientEpisode).getId());
		for(ChangeInCondition cic : cicList){
			SignsSymptomsLabworkAttr attr =  signsSymptomsLabworkAttrDao.findById(cic.getSignsSymptomsLabworkAttrId());
			if(attr.getParentId().compareTo(symptomId) == 0){
				changeInConditionDao.delete(cic);
			}
		}
	}
	@Transactional
	public void deleteVitalSignRecordByEpisodeAndSymptomId(Long patientEpisodeId, Long symptomId){
		PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
		 changeInConditionDao.deleteChangeInConditionRecordByEpisodeAndSymptomAttrId(sbarDao.findByPatientEpisode(patientEpisode).getId(), symptomId);
		
			
			
		}
	
	
	
	
	@Transactional
	public List<ChangeInConditionDto> getChangeInCondByEpisodeId(Long episodeId){
		PatientEpisode patientEpisode=patientEpisodeDao.findById(episodeId);
		List<ChangeInCondition> changeInConditionList = fetchChangeInConditionsByEpisodeId(sbarDao.findByPatientEpisode(patientEpisode).getId());
		List<ChangeInConditionDto> dtoList = buildDtoFromChangeInConditionDomain(changeInConditionList);
		return dtoList;
	}
	
	@Transactional
	public List<ChangeInConditionDto> getChangeInCondByEpisodeIdAndType(Long episodeId, ChangeInConditionType type){
		PatientEpisode patientEpisode=patientEpisodeDao.findById(episodeId);
		List<ChangeInCondition> changeInConditionList = changeInConditionDao.getChangeinCondByEpisodeIdAndType(sbarDao.findByPatientEpisode(patientEpisode).getId(), type);
		List<ChangeInConditionDto> dtoList = buildDtoFromChangeInConditionDomain(changeInConditionList);
		return dtoList;
	}
	
	@Transactional
	public List<ChangeInConditionDto> getChangeInCondByEpisodeIdAndSymptomId(Long episodeId, Long symptomId){
		
		List<ChangeInCondition> changeInConditionList = fetchChangeInConditionByEpisodeAndSymptomId(
				episodeId, symptomId);
		List<ChangeInConditionDto> dtoList = buildDtoFromChangeInConditionDomain(changeInConditionList);
		
		return dtoList;
	}

	
	@Transactional
	public void saveOrUpdateChangeInConditions(List<ChangeInConditionDto> changeInConditionsList, Long symptomId, String userName) throws Exception{
		Long patientEpisodeId = (CollectionUtils.isEmpty(changeInConditionsList))?null:changeInConditionsList.get(0).getPatientEpisodeId();
		
		if(patientEpisodeId != null && patientEpisodeId != 0){
			List<Long> dtoAttrIdsList = new ArrayList<Long>();
			for(ChangeInConditionDto dto : changeInConditionsList){
				dtoAttrIdsList.add(dto.getSignsSymptomsLabworkAttrId());
			}
			
			List<Long> domainAttrIdsList = new ArrayList<Long>();			
			List<ChangeInCondition> domainsList = fetchChangeInConditionByEpisodeAndSymptomId(patientEpisodeId, symptomId);
			Map<Long, ChangeInCondition> domainMap = new HashMap<Long, ChangeInCondition>();
			for(ChangeInCondition domain : domainsList){
				domainAttrIdsList.add(domain.getSignsSymptomsLabworkAttrId());
				domainMap.put(domain.getSignsSymptomsLabworkAttrId(), domain);
			}
			
			List<Long> existingAttrIdList = new ArrayList<Long>();
			for(Long attrId : dtoAttrIdsList){
				if(domainAttrIdsList.contains(attrId)){
					existingAttrIdList.add(attrId);
				} 
			}
			
			domainAttrIdsList.removeAll(existingAttrIdList);
			
			for(ChangeInConditionDto dto : changeInConditionsList){
				ChangeInCondition changeInCondition = null;
				if(existingAttrIdList.contains(dto.getSignsSymptomsLabworkAttrId())){
					changeInCondition = domainMap.get(dto.getSignsSymptomsLabworkAttrId());
					populateChangeInConditionDomainObject(userName, dto, changeInCondition);
				}else{
					changeInCondition = buildChangeInConditionDomainFromDto(
							userName, dto);
				}
				
				changeInConditionDao.saveOrUpdate(changeInCondition);
				Sbar sbar=sbarDao.findById(changeInCondition.getSbarId());
				
			    sbarService.sbarsaveIntialSbar(sbar.getPatientEpisode(),userName);
							}
			
			for(Long attrId : domainAttrIdsList){
				PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
				changeInConditionDao.deleteChangeInConditionRecordByEpisodeAndSymptomAttrId(sbarDao.findByPatientEpisode(patientEpisode).getId(), attrId);
			}
		}
	}
	
	
	private ChangeInCondition buildChangeInConditionDomainFromDto(
			String userName, ChangeInConditionDto dto) {
		Long changeInConditionId = dto.getId();
		ChangeInCondition changeInCondition = null;
		if(changeInConditionId != null){
			changeInCondition = changeInConditionDao.findById(changeInConditionId);
		}
		
		if(changeInCondition == null){
			changeInCondition = new ChangeInCondition();
			changeInCondition.setDateCreated(new Date());
			changeInCondition.setUserCreated(userName);
			
		}
		
		populateChangeInConditionDomainObject(userName, dto, changeInCondition);
		return changeInCondition;
	}

	private void populateChangeInConditionDomainObject(String userName,
			ChangeInConditionDto dto, ChangeInCondition changeInCondition) {
		PatientEpisode patientEpisode=patientEpisodeDao.findById(dto.getPatientEpisodeId());
		
		changeInCondition.setSbarId(sbarDao.findByPatientEpisode(patientEpisode).getId());
		changeInCondition.setSignsSymptomsLabworkAttrId(dto.getSignsSymptomsLabworkAttrId());
		changeInCondition.setChangeInConditionValue(dto.getChangeInConditionValue());
		changeInCondition.setComments(dto.getComments());
		changeInCondition.setDateModified(new Date());
		boolean gottenBetter = "BETTER".equalsIgnoreCase(dto.getSymptomStatus());
		boolean gottenWorse = "WORSE".equalsIgnoreCase(dto.getSymptomStatus());
		changeInCondition.setGottenBetter(gottenBetter);
		changeInCondition.setGottenWorse(gottenWorse);
		changeInCondition.setOccurredBefore(dto.getOccurredBefore());
		changeInCondition.setStartedDate(dto.getSymptomOccuredDate());
		changeInCondition.setThingsMadeSymptomWorse(dto.getThingsMadeSymptomWorse());
		changeInCondition.setThinsMadeSymptomBetter(dto.getThinsMadeSymptomBetter());
		changeInCondition.setTreatmentForLastEpisode(dto.getTreatmentForLastEpisode());
		changeInCondition.setUserModified(userName);
		
	}
	
	private List<ChangeInConditionDto> buildDtoFromChangeInConditionDomain(
			List<ChangeInCondition> changeInConditionList) {
		List<ChangeInConditionDto> dtoList = new ArrayList<ChangeInConditionDto>();
		if(!CollectionUtils.isEmpty(changeInConditionList)){
			for(ChangeInCondition cic : changeInConditionList){
				ChangeInConditionDto dto = new ChangeInConditionDto();
				dto.setId(cic.getId());
				
				dto.setChangeInConditionValue(cic.getChangeInConditionValue());
				Long signsSymptomsLabworkAttrId=cic.getSignsSymptomsLabworkAttrId();
				SignsSymptomsLabworkAttr signsSymptomsLabworkAttr=signsSymptomsLabworkAttrDao.findById(signsSymptomsLabworkAttrId);
				List<CicDecisionParms> cicDecisionParms=cicDecisionParmsDao.findBySignSymptomLabAttrId(signsSymptomsLabworkAttrId);
				if(signsSymptomsLabworkAttr.getDatatype().equals(Constants.BOOLEAN_DATATYPE))
				{  for(CicDecisionParms cicDecisionPam:cicDecisionParms){
					if(cicDecisionPam.getImmediateFlag()){
					   dto.setDocterNotification(Constants.CIC_NOTIFY_IMMEDIATE);
					   break;
					}else
					{
						dto.setDocterNotification(Constants.CIC_NOTIFY_NEXT_DAY);
				 	}
				 } 
				}
				dto.setComments(cic.getComments());
				dto.setOccurredBefore(cic.getOccurredBefore());
				dto.setSbarId(cic.getSbarId());
				dto.setSignsSymptomsLabworkAttrId(cic.getSignsSymptomsLabworkAttrId());
				dto.setSymptomOccuredDate(cic.getStartedDate()); 
				String status = BooleanUtils.isTrue(cic.getGottenWorse()) ? "WORSE" : (BooleanUtils.isTrue(cic.getGottenBetter())?"BETTER":"SAME");
				dto.setSymptomStatus(status);
				dto.setThingsMadeSymptomWorse(cic.getThingsMadeSymptomWorse());
				dto.setThinsMadeSymptomBetter(cic.getThinsMadeSymptomBetter());
				dto.setTreatmentForLastEpisode(cic.getTreatmentForLastEpisode());
				dto.setDateCreated(cic.getDateCreated());
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}
	
	private List<ChangeInCondition> fetchChangeInConditionsByEpisodeId(
			Long sbarId) {
		return changeInConditionDao.getChangeinCondByEpisodeId(sbarId);
	}
	
	@SuppressWarnings("unchecked")
	private List<ChangeInCondition> fetchChangeInConditionByEpisodeAndSymptomId(
			Long episodeId, Long symptomId) {
		PatientEpisode patientEpisode=patientEpisodeDao.findById(episodeId);
		List<ChangeInCondition> changeInConditionList = changeInConditionDao.getChangeinCondByEpisodeIdAndSymptomId(sbarDao.findByPatientEpisode(patientEpisode).getId(), symptomId);
		
	
		return changeInConditionList;
	}

	@Transactional
	public void saveOrUpdateChangeInConditionsData(
			List<ChangeInConditionDto> changeInConditionList, String userName) {
			Long signsSymptomsLabworkAttrId=null;
		for(ChangeInConditionDto changeInConditionDto:changeInConditionList){
			PatientEpisode patientEpisode=patientEpisodeDao.findById(changeInConditionDto.getPatientEpisodeId());
			
			 signsSymptomsLabworkAttrId=changeInConditionDto.getSignsSymptomsLabworkAttrId();
			 ChangeInCondition changeInCondition=changeInConditionDao.findBySignsSymptomsLabworkAttrId(sbarDao.findByPatientEpisode(patientEpisode).getId(), signsSymptomsLabworkAttrId);
			 if(changeInCondition==null)
			 {
				 changeInCondition=new ChangeInCondition();
				 changeInCondition.setUserCreated(userName);
				 changeInCondition.setDateCreated(new Date());
			 }
			 else
			 {
				 changeInCondition.setUserModified(userName);
				 changeInCondition.setDateModified(new Date());
				 
			 }
			 changeInCondition.setSbarId(sbarDao.findByPatientEpisode(patientEpisode).getId());
			 changeInCondition.setSignsSymptomsLabworkAttrId(signsSymptomsLabworkAttrId);
			 changeInCondition.setChangeInConditionValue(changeInConditionDto.getChangeInConditionValue());
			
			changeInConditionDao.saveOrUpdate(changeInCondition);
			 
			
		}
		
	}
	@Transactional
	public List<ChangeInConditionDto> getChangeInCondByEncounterAndType(Long encounterId,
			ChangeInConditionType type) {
		List<ChangeInConditionDto> dtoList=new ArrayList<ChangeInConditionDto>();
		
		Long sbarId=sbarService.getSbarIdbyEncounterId(encounterId);
		if(sbarId!=null){
		List<ChangeInCondition> changeInConditionList = changeInConditionDao.getChangeinCondByEpisodeIdAndType(sbarId, type);
	     dtoList = buildDtoFromChangeInConditionDomain(changeInConditionList);
		}
		return dtoList;
		
	}
	

}
