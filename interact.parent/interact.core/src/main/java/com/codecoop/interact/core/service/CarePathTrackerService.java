package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.codecoop.interact.core.dao.CarePathDaoImpl;
import com.codecoop.interact.core.dao.CarePathTrackerAttrDaoImpl;
import com.codecoop.interact.core.dao.CarePathTrackerDaoImpl;
import com.codecoop.interact.core.dao.ChangeInConditionDao;
import com.codecoop.interact.core.dao.FacilityStaffDaoImpl;
import com.codecoop.interact.core.dao.GenericDao;
import com.codecoop.interact.core.dao.PatientDaoImpl;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.dao.ResponsibilitiesDao;
import com.codecoop.interact.core.dao.SbarDaoImpl;
import com.codecoop.interact.core.dao.SignsSymptomsLabworkAttrDao;
import com.codecoop.interact.core.dao.SignsSymptomsLabworkDao;
import com.codecoop.interact.core.dao.StaffDaoImpl;
import com.codecoop.interact.core.domain.CarePath;
import com.codecoop.interact.core.domain.CarePathTracker;
import com.codecoop.interact.core.domain.CarePathTrackerAttr;
import com.codecoop.interact.core.domain.ChangeInCondition;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.SignsSymptomsLabworkAttr;
import com.codecoop.interact.core.dto.CPAttrValueDto;
import com.codecoop.interact.core.dto.CarePathLabworksDto;
import com.codecoop.interact.core.dto.CarePathListingDto;
import com.codecoop.interact.core.dto.CarePathStepTrackerDto;
import com.codecoop.interact.core.dto.CarePathStepsResponseDto;
import com.codecoop.interact.core.dto.CicAttrRefCarePathDto;
import com.codecoop.interact.core.dto.RunningCarePathAttrDetails;
import com.codecoop.interact.core.dto.ViewAllCarePathsResponseDto;
import com.codecoop.interact.core.dto.carepath.CarePathDto;
import com.codecoop.interact.core.dto.carepath.StepDto;
import com.codecoop.interact.core.util.CarePathUtil;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.core.util.ShowInSBARCarePathStepEnum;


@Service
public class CarePathTrackerService {
	private static final Logger LOG = Logger.getLogger(CarePathTrackerService.class);

	@Autowired
	CarePathDaoImpl carePathDao;
	@Autowired
	CarePathTrackerDaoImpl carePathTrackerDao;
	@Autowired
	CarePathTrackerAttrDaoImpl carePathTrackerAttrDao;
	@Autowired
	PatientEpisodeDao patientEpisodeDao;
	@Autowired
	PatientDaoImpl patientDao;
	@Autowired
	CarePathGeneratorService  carePathGeneratorService;
	@Autowired
	SignsSymptomsLabworkAttrDao signsSymptomsLabworkAttrDao;
	@Autowired
	GenericDao genericDao;
	@Autowired
	StaffDaoImpl staffDao;
	@Autowired
	ChangeInConditionDao changeInConditionDao;
	@Autowired
	MessageService messageService;
	@Autowired
	FacilityStaffDaoImpl facilityStaffDao;
	@Autowired 
	MailService mailService;
	@Autowired
	ResponsibilitiesDao responsibilitiesDao;
	@Autowired
	SignsSymptomsLabworkDao signsSymptomsLabworkDao;
	@Autowired
	CarePathLabworksService carePathLabworksService;
	@Autowired
	SbarDaoImpl sbarDao;
	@Autowired
	SBARService sbarService;

	@Transactional
	public String getCarePathName(String carePathCode)
	{
		return carePathDao.findByCarePathCode(carePathCode).getCarePathName();

	}

	@Transactional
	public void savePatientCarePathTraker(Long staffId,CarePathStepTrackerDto carePathStepTrackerDto)
	{ 
		String staffName=staffDao.findById(staffId).getFirstName();
		PatientEpisode patientEpisode=patientEpisodeDao.findById(carePathStepTrackerDto.getPatientEpisodeId());
		Sbar sbar=sbarDao.findByPatientEpisode(patientEpisode);
		CarePath carePath=carePathDao.findByCarePathCode(carePathStepTrackerDto.getCurrCarePathCode());
		boolean notifyDoctorFlag=carePathStepTrackerDto.isNotifyDoctorFlag();
		int stepId=carePathStepTrackerDto.getStepId();
		int stepOrder=carePathStepTrackerDto.getStepOrder();
		CarePathTracker carePathTracker=carePathTrackerDao.findByStepId(sbar, carePath, stepId);
		if(carePathTracker==null){
			carePathTracker=new CarePathTracker(sbar, carePath, stepId, new Date(), staffName, null, null);
			carePathTracker.setExceSequence(stepOrder);
			carePathTracker.setStepName(carePathStepTrackerDto.getStepName());
			if(carePathStepTrackerDto.getStepType()!=null){
				carePathTracker.setStepType(carePathStepTrackerDto.getStepType());
				if (ShowInSBARCarePathStepEnum.isStepTypeExists(carePathStepTrackerDto.getStepType())) {
					carePathTracker.setShowInSbar(true);
				}
			}
			else{
				carePathTracker.setShowInSbar(false);	
			}

			carePathTrackerDao.saveOrUpdate(carePathTracker);
		}
		List<String> selectedCarePathAttrCodes=new ArrayList<String>();
		if(!carePathStepTrackerDto.getAttrValues().isEmpty()){

			for(CPAttrValueDto cPAttrValueDto:carePathStepTrackerDto.getAttrValues()) {
				boolean notifyDoc;
				String carePathAttrCode = cPAttrValueDto.getCarePathAttrCode();
				selectedCarePathAttrCodes.add(carePathAttrCode);
				String carePathAttrName = cPAttrValueDto.getCarePathAttrName();
				// String minValue=cPAttrValueDto.
				if (notifyDoctorFlag == true) {
					notifyDoc = cPAttrValueDto.isMetCondition();
				} else {
					notifyDoc = false;
				}
				String value = cPAttrValueDto.getValue();
				CarePathTrackerAttr carePathTrackerAttr = carePathTrackerAttrDao
						.findByCPathCodeAndCPTId(carePathAttrCode,
								carePathTracker);
				if (carePathTrackerAttr == null) {
					carePathTrackerAttr = new CarePathTrackerAttr(
							carePathTracker, carePathAttrCode,
							carePathAttrName, value, notifyDoctorFlag,
							new Date(), staffName, null, null);
				} else {

					// carePathTracker.setExceSequence(stepOrder);
					carePathTrackerAttr.setValue(value);
					carePathTrackerAttr.setNotifyDoctorFlag(notifyDoc);
					carePathTrackerAttr.setDateModified(new Date());
					carePathTrackerAttr.setUserModified(staffName);
				}
				if (cPAttrValueDto.getMinValue() != null)
					carePathTrackerAttr.setMinValue(cPAttrValueDto
							.getMinValue());
				if (cPAttrValueDto.getMaxValue() != null)
					carePathTrackerAttr.setMaxValue(cPAttrValueDto
							.getMaxValue());
				if (cPAttrValueDto.getDataType() != null)
					carePathTrackerAttr.setDataType(cPAttrValueDto
							.getDataType());
				// carePathTrackerDao.saveOrUpdate(carePathTracker);
				//postDataToChangeInCondition(patientEpisode,carePathAttrCode,value);
				carePathTrackerAttrDao.saveOrUpdate(carePathTrackerAttr);

			}
			deleteUnSelectedAttrsInCPT(carePathTracker, selectedCarePathAttrCodes);

		}else{
			deleteUnSelectedAttrsInCPT(carePathTracker,selectedCarePathAttrCodes);
		}
	}
	private void deleteAllAttrInStep(CarePathTracker carePathTracker) {
		carePathTrackerAttrDao.deleteAllCarePathTrackerAtt(carePathTracker);
	}
	private void deleteUnselectedAttrsInCIC(PatientEpisode patientepisode, List<CarePathTrackerAttr> carePathTrackerAttrList)
	{
		for(SignsSymptomsLabworkAttr signsSymptomsLabworkAttr:signsSymptomsLabworkAttrDao.getCarePathCodeRecords()){
			boolean isAvilbale=false;
			ChangeInCondition changeInCondition=changeInConditionDao.findBySignsSymptomsLabworkAttrId(patientepisode.getId(), signsSymptomsLabworkAttr.getId());
			if(changeInCondition!=null){
				for(CarePathTrackerAttr carePathTrackerAttr:carePathTrackerAttrList){
					if(signsSymptomsLabworkAttr.getCarePathAttrCode().equals(carePathTrackerAttr.getCarePathAttrCode())){
						isAvilbale=true;
						break;
					}
				}
				if(isAvilbale==false){
					changeInConditionDao.delete(changeInCondition);
				}
			}

		}

	}
	private void deleteUnSelectedAttrsInCPT(CarePathTracker carePathTracker,List<String> selectedCarePathAttrCodes) {

		if(selectedCarePathAttrCodes.size()==0){
			for(CarePathTrackerAttr carePathTrackerAttr:carePathTrackerAttrDao.findByCPT(carePathTracker)){
				carePathTrackerAttrDao.delete(carePathTrackerAttr);
			}
		}
		for(CarePathTrackerAttr carePathTrackerAttr:carePathTrackerAttrDao.findByCPT(carePathTracker)){
			boolean isAvilbale=false;
			for(String carePathAttrCode:selectedCarePathAttrCodes){
				if(carePathAttrCode.equals(carePathTrackerAttr.getCarePathAttrCode())){
					isAvilbale=true;
					break;
				}
			}
			if(isAvilbale==false){
				carePathTrackerAttrDao.delete(carePathTrackerAttr);
				SignsSymptomsLabworkAttr signsSymptomsLabworkAttr = signsSymptomsLabworkAttrDao.findByCarePathAttrCode(carePathTrackerAttr.getCarePathAttrCode());
				if (signsSymptomsLabworkAttr != null) {
					ChangeInCondition changeInCondition = changeInConditionDao.findBySignsSymptomsLabworkAttrId(carePathTrackerAttr.getCarePathTracker().getSbar().getId(),signsSymptomsLabworkAttr.getId());
					if (changeInCondition != null) {
						changeInConditionDao.delete(changeInCondition);
					}
				}  
			}
		}

	}
	public void deleteAll(Sbar sbar,String carePathCode,Long facilityId)
	{   // PatientEpisode patientEpisode=patientEpisodeDao.findActiveEpisodeByPatient(patientDao.findById(patientId),facilityId);
		CarePath carePath=carePathDao.findByCarePathCode(carePathCode);

		for(CarePathTracker carePathTracker:carePathTrackerDao.findAll(sbar,carePath)){
			for(CarePathTrackerAttr carePathTrackerAttr:carePathTrackerAttrDao.findByCPT(carePathTracker) ){
				carePathTrackerAttrDao.delete(carePathTrackerAttr);
			}
			carePathTrackerDao.delete(carePathTracker);
		}
	}

	@Transactional
	public List<CarePathTracker> findAll(Sbar sbar,String currCarePathCode) {
		CarePath carePath=carePathDao.findByCarePathCode(currCarePathCode);
		return carePathTrackerDao.findAll(sbar,carePath);
	}

	@Transactional
	public Integer findLastStepSequence(PatientEpisode patientEpisode,String currCarePathCode) {
		CarePath carePath=carePathDao.findByCarePathCode(currCarePathCode);
		return carePathTrackerDao.findLastStepSequence(sbarDao.findByPatientEpisode(patientEpisode),carePath);
	}
	@Transactional
	public CarePathTracker findByExceSequence(PatientEpisode patientEpisode, String carePathCode,
			int exceSequence) {
		CarePath carePath=carePathDao.findByCarePathCode(carePathCode);
		return carePathTrackerDao.findByExceSequence(sbarDao.findByPatientEpisode(patientEpisode),carePath,exceSequence);

	}
	@Transactional
	public Map<String,String> getCarePathTrackerAttrMap(PatientEpisode patientEpisode,
			String currCarePathCode) {
		Sbar sbar=sbarDao.findByPatientEpisode(patientEpisode);
		Map<String,String> paramMap=new HashMap<String,String>();
		for(CarePathTracker carePathTracker:findAll(sbar,currCarePathCode)){
			for(CarePathTrackerAttr carePathTrackerAttr:carePathTrackerAttrDao.findByCPT(carePathTracker)){
				if( carePathTrackerAttr.getValue()!=null){
					//ChangeInCondition changeInCondition=changeInConditionDao.findBySignsSymptomsLabworkAttrId(patientEpisodeId, signsSymptomsLabworkAttrId);
					SignsSymptomsLabworkAttr signsSymptomsLabworkAttr=signsSymptomsLabworkAttrDao.findByCarePathAttrCode( carePathTrackerAttr.getCarePathAttrCode());
					if(signsSymptomsLabworkAttr!=null){

						String cicValue=getValueFromChangeInCondition(sbar, carePathTrackerAttr.getCarePathAttrCode());
						if(cicValue!=null&&!signsSymptomsLabworkAttr.getDatatype().equals(Constants.BOOLEAN_DATATYPE)){
							paramMap.put(carePathTrackerAttr.getCarePathAttrCode(), cicValue);
						}
					}
					else{
						paramMap.put(carePathTrackerAttr.getCarePathAttrCode(), carePathTrackerAttr.getValue());
					}
				}else{
					paramMap.put(carePathTrackerAttr.getCarePathAttrCode(), carePathTrackerAttr.getCarePathAttrCode());
				}
			}
			for(SignsSymptomsLabworkAttr signsSymptomsLabworkAttr:signsSymptomsLabworkAttrDao.getCarePathCodeRecords())
			{ 
				String carePathAttrCode=signsSymptomsLabworkAttr.getCarePathAttrCode();
				Long signsSymptomsLabworkAttrId=signsSymptomsLabworkAttr.getId();
				//Long patientEpisodeId=patientEpisodeDao.findActiveEpisodeByPatient(patientDao.findById(patientId),facilityId).getId();
				ChangeInCondition changeInCondition=changeInConditionDao.findBySignsSymptomsLabworkAttrId(sbar.getId(), signsSymptomsLabworkAttrId);
				if(changeInCondition!=null){
					String value=changeInCondition.getChangeInConditionValue();
					if(value!=null&& !signsSymptomsLabworkAttr.getDatatype().equals(Constants.BOOLEAN_DATATYPE)){
						paramMap.put(carePathAttrCode, value);
					}
					else{
						paramMap.put(carePathAttrCode, carePathAttrCode);
					}
				}	
			}

		}
		return paramMap;
	}	
	@Transactional
	public CarePathDto invokeCarePath(Long staffId,Long facilityId,CarePathDto carePathDto,Map<String,String> paramMap) 
	{  Long patientId=carePathDto.getPatientId();
	PatientEpisode patientEpisode=patientEpisodeDao.findActiveEpisodeByPatient(patientDao.findById(patientId),facilityId);
	Sbar sbar=sbarDao.findByPatientEpisode(patientEpisode);
	try {
		if(paramMap == null){
			carePathDto = carePathGeneratorService.generate(carePathDto.getCarePathCode(),patientEpisode);
			carePathGeneratorService.populateNextCarePathAttr(carePathDto,patientEpisode);
			carePathDto.setStepId(1);
			carePathDto.setStepOrder(1);
			carePathDto.setPatientId(patientId);
			carePathDto.setPatientEpisodeId(patientEpisode.getId());
			if(findLastStepSequence(patientEpisode,carePathDto.getCarePathCode())!=null){ 
				int lastexceSequence=findLastStepSequence(patientEpisode,carePathDto.getCarePathCode());
				//paramMap have updated CIC values 
				paramMap=getCarePathTrackerAttrMap(patientEpisode,carePathDto.getCarePathCode());
				deleteAll(sbar, carePathDto.getCarePathCode(),facilityId);
				while(carePathDto.getMessage()==null&& carePathDto.getStepOrder()<=lastexceSequence){	
					invokeCarePathStep(staffId,facilityId,carePathDto, patientEpisode.getId(), paramMap);
				}

			}
		}
		else{     
			if(findLastStepSequence(patientEpisode, carePathDto.getCarePathCode())!=null)
				carePathDto.setStepOrder(findLastStepSequence(patientEpisode, carePathDto.getCarePathCode())+1);
			invokeCarePathStep(staffId,facilityId,carePathDto, patientEpisode.getId(), paramMap);
		}
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return carePathDto;
	}
	return carePathDto;
	}
	@Transactional
	public void invokeCarePathStep(Long staffId,Long facilityId,CarePathDto carePathDto,Long patientEpisodeId,Map<String,String>paramMap)
	{
		StepDto step = carePathGeneratorService.getStep(carePathDto, carePathDto.getStepId());

		CarePathStepTrackerDto carePathStepTrackerDto = CarePathUtil.populateAndEvaluateFormData(carePathDto.getStepOrder(),step, paramMap);
		carePathStepTrackerDto.setPatientEpisodeId(patientEpisodeId);
		carePathStepTrackerDto.setCurrCarePathCode(carePathDto.getCarePathCode());
		if(carePathStepTrackerDto.isNotifyDoctorFlag() ){
			carePathDto.setMessage(Constants.CAREPATH_NOTIFY_DOCTOR);
		}else if(carePathStepTrackerDto.getNextStepId() != null){
			carePathDto.setStepId(carePathStepTrackerDto.getNextStepId());
		}else{

			carePathDto.setStepId(carePathStepTrackerDto.getStepId());

			//			carePathDto.setMessage(Constants.CAREPATH_DO_NOTHING);
			//    step.setErrorMsg("Please select atleast one symtom");
		}
		carePathDto.setStepType(carePathStepTrackerDto.getStepType());
		//		carePathLabworksService.deleteByEpisodeIdNdcarePathCode(patientEpisodeId, carePathStepTrackerDto.getCurrCarePathCode());
		//		carePathDto.setMessage("");
		if(!StringUtils.isEmpty(step.getStepType()) && step.getStepType().equalsIgnoreCase(Constants.CONSIDER_ORDERS))
		{
			carePathStepPause(staffId, facilityId, patientEpisodeId, carePathStepTrackerDto,carePathDto);
		}
		carePathDto.setStepOrder(carePathDto.getStepOrder()+1);
		savePatientCarePathTraker(staffId,carePathStepTrackerDto);

	}



	@Transactional
	public CarePathDto getPreviousCarePathDto(CarePathDto carePathDto,Long facilityId) {
		int stepOrder=carePathDto.getStepOrder();
		Long patientId=carePathDto.getPatientId();
		String carePathCode=carePathDto.getCarePathCode();
		Integer lastExceSquence= null;
		PatientEpisode patientEpisode=patientEpisodeDao.findActiveEpisodeByPatient(patientDao.findById(patientId),facilityId);

		CarePathTracker carePathTracker=findByExceSequence(patientEpisode,carePathCode , carePathDto.getStepId());
		/*		if(Constants.CAREPATH_NOTIFY_DOCTOR.equals(carePathDto.getMessage())){
			carePathDto.setMessage(null);
		} 
		 */		if(!Constants.CAREPATH_NOTIFY_DOCTOR.equals(carePathDto.getMessage())){
			 if(carePathTracker != null){
				 //deleteUnselectedAttrsInCIC(patientEpisode, carePathTracker.getCarePathTrackerAttrList());
				 carePathTrackerDao.delete(carePathTracker);
			 }
		 }
		 // this is for delete carepath labworks 
		 //		 if(!StringUtils.isEmpty(carePathDto.getStepType()) && carePathDto.getStepType().equalsIgnoreCase(Constants.CONSIDER_ORDERS)){
		 //			 carePathLabworksDelete(patientEpisode.getId(), carePathCode);
		 //		 }
		 lastExceSquence=findLastStepSequence(patientEpisode, carePathCode);
		 carePathTracker=findByExceSequence(patientEpisode,carePathCode , lastExceSquence);
		 carePathDto.setStepOrder(lastExceSquence);
		 carePathDto.setStepId(carePathTracker.getStepId());
		 return carePathDto;
	}
	@Transactional
	public String getValueFromChangeInCondition(Sbar sbar,String carePathAttrCode){
		String value=null;
		SignsSymptomsLabworkAttr signsSymptomsLabworkAttr=signsSymptomsLabworkAttrDao.findByCarePathAttrCode(carePathAttrCode);
		if(signsSymptomsLabworkAttr!=null)
		{
			Long signsSymptomsLabworkAttrId=signsSymptomsLabworkAttr.getId();
			//Long patientEpisodeId=patientEpisodeDao.findActiveEpisodeByPatient(patientDao.findById(patientId)).getId();
			ChangeInCondition changeInCondition=changeInConditionDao.findBySignsSymptomsLabworkAttrId(sbar.getId(), signsSymptomsLabworkAttrId);
			if(changeInCondition!=null){
				value=changeInCondition.getChangeInConditionValue();
			}
		}
		return value;
	}

	@Transactional
	public Map<String,String> getValueMapFromChangeInCondition(long patientId,Long facilityId){
		Map<String,String> carePathMap=new HashMap<String, String>();
		for(SignsSymptomsLabworkAttr signsSymptomsLabworkAttr:signsSymptomsLabworkAttrDao.getCarePathCodeRecords())
		{ 
			String carePathAttrCode=signsSymptomsLabworkAttr.getCarePathAttrCode();
			Long signsSymptomsLabworkAttrId=signsSymptomsLabworkAttr.getId();
			Long patientEpisodeId=patientEpisodeDao.findActiveEpisodeByPatient(patientDao.findById(patientId),facilityId).getId();
			PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
			ChangeInCondition changeInCondition=changeInConditionDao.findBySignsSymptomsLabworkAttrId(sbarDao.findByPatientEpisode(patientEpisode).getId(), signsSymptomsLabworkAttrId);
			if(changeInCondition!=null){
				String value=changeInCondition.getChangeInConditionValue();
				
				if(value!=null&& !signsSymptomsLabworkAttr.getDatatype().equals( Constants.BOOLEAN_DATATYPE)){
					carePathMap.put(carePathAttrCode, value);
				}
				else{
					carePathMap.put(carePathAttrCode, carePathAttrCode);
				}
			}
		}

		return carePathMap;
	}

	@Transactional
	public void postDataToChangeInCondition(PatientEpisode patientEposode,String carePathAttrCode,String value)
	{       
		if(signsSymptomsLabworkAttrDao.findByCarePathAttrCode(carePathAttrCode)!=null)
		{    Long signsSymptomsLabworkAttrId=signsSymptomsLabworkAttrDao.findByCarePathAttrCode(carePathAttrCode).getId();
		//Long patientEpisodeId=patientEposode.getId();
		ChangeInCondition changeInCondition=changeInConditionDao.findBySignsSymptomsLabworkAttrId(sbarDao.findByPatientEpisode(patientEposode).getId(), signsSymptomsLabworkAttrId);
		if(changeInCondition!=null){
			changeInCondition.setChangeInConditionValue(value);
		}
		else{
			changeInCondition=new ChangeInCondition();
			changeInCondition.setSbarId(sbarDao.findByPatientEpisode(patientEposode).getId());
			changeInCondition.setChangeInConditionValue(value);
			changeInCondition.setSignsSymptomsLabworkAttrId(signsSymptomsLabworkAttrId);
			changeInCondition.setStartedDate(new Date());

		}
		changeInConditionDao.saveOrUpdate(changeInCondition);
		}
	}

	@Transactional
	public List<CarePathListingDto> getRefCarePathBasedOnCarePathAttr(Long patientEpisodeId){
		List<CarePathListingDto> runningCarePahts = new ArrayList<CarePathListingDto>();
		List<CarePathListingDto> suggestedCarePahts = new ArrayList<CarePathListingDto>();
		PatientEpisode patientepisode=patientEpisodeDao.findById(patientEpisodeId);
		List<RunningCarePathAttrDetails> runningCarePathDetails = carePathTrackerAttrDao.getRunningCarePathAndAttrDetails(sbarDao.findByPatientEpisode(patientepisode).getId());

		Set<String> runningCarePathCodes = new HashSet<String>();
		populateRunningCarePathCodes(runningCarePathCodes, runningCarePathDetails);

		for(String runningCarePathCode:runningCarePathCodes){
			CarePathListingDto runningCarePath = new CarePathListingDto();
			runningCarePath.setCarePathCode(runningCarePathCode);
			runningCarePath.setStatus("RUNNING_OR_COMPLETED");
			runningCarePahts.add(runningCarePath);
		}
		populateRefCarePathCodesAndSuggestions(suggestedCarePahts, runningCarePahts, runningCarePathDetails);

		List<CicAttrRefCarePathDto> cicAttrRefCarePaths = 
				changeInConditionDao.getSuggestedCarePathsForPatientChangeInCondition(sbarDao.findByPatientEpisode(patientepisode).getId());

		populateRefCarePathCodesAndSuggestionsFromCIC(suggestedCarePahts, runningCarePahts, cicAttrRefCarePaths);

		List<CarePath> allCarePaths = carePathDao.findAll();
		List<CarePathListingDto> listingDto = new ArrayList<CarePathListingDto>();
		for(CarePath cp : allCarePaths){
			String carePathCode = cp.getCarePathCode();
			CarePathListingDto dto = getCarePathListingDtoFromRunningOrSuggested(carePathCode, suggestedCarePahts, runningCarePahts);
			if(dto != null){
				dto.setCarePathId(cp.getId());
				dto.setCarePathName(cp.getCarePathName());
			}else{
				dto = new CarePathListingDto();
				dto.setCarePathCode(carePathCode);
				dto.setCarePathId(cp.getId());
				dto.setCarePathName(cp.getCarePathName());
				dto.setStatus("OTHERS");
			}
			listingDto.add(dto);
		}
		return listingDto;
	}


	private void populateRefCarePathCodesAndSuggestionsFromCIC(
			List<CarePathListingDto> suggestedCarePaths,
			List<CarePathListingDto> runningCarePaths, 
			List<CicAttrRefCarePathDto> cicAttrRefCarePaths){
		for(CicAttrRefCarePathDto cicAttrRefCarePathDto:cicAttrRefCarePaths){
			CarePathListingDto carePathListing = getCarePathListingDtoFromRunningOrSuggested(cicAttrRefCarePathDto.getRefCarePathCode(), suggestedCarePaths, runningCarePaths);
			if(carePathListing == null){
				carePathListing = new CarePathListingDto();
				carePathListing.setCarePathCode(cicAttrRefCarePathDto.getRefCarePathCode());
				carePathListing.setStatus("SUGGESTING");
				suggestedCarePaths.add(carePathListing);
			}
			carePathListing.getSuggestions().add(getSuggestion(cicAttrRefCarePathDto));	
		}
	}

	private void populateRefCarePathCodesAndSuggestions(
			List<CarePathListingDto> suggestedCarePaths,
			List<CarePathListingDto> runningCarePaths, 
			List<RunningCarePathAttrDetails> runningCarePathDetails) {
		String carePathCode = null;
		String xmlFileLocation = null;
		XPath xpath = null;
		Document doc = null;
		if(!CollectionUtils.isEmpty(runningCarePathDetails)){
			for(RunningCarePathAttrDetails runningCarePathDetail: runningCarePathDetails){
				try{
					if(!runningCarePathDetail.getCarePathCode().equals(carePathCode)){
						xmlFileLocation = runningCarePathDetail.getXmlFileLoacation();
						doc = generateDocObject(xmlFileLocation);
						xpath = generateXPathObject(xmlFileLocation, doc);
					}
					XPathExpression expr =
							xpath.compile("//symptom[care_path_attr_code='"+runningCarePathDetail.getCarePathAttrCode()+"']/next_care_path[step_flag='false']/care_path_code/text()");
					//evaluate expression result on XML document
					if(expr != null){
						NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
						if(nodes != null && nodes.item(0) != null){
							String nextCarePathCode = nodes.item(0).getNodeValue();
							if(!StringUtils.isEmpty(nextCarePathCode)){
								CarePathListingDto carePathListing = getCarePathListingDtoFromRunningOrSuggested(nextCarePathCode, suggestedCarePaths, runningCarePaths);
								if(carePathListing == null){
									carePathListing = new CarePathListingDto();
									carePathListing.setCarePathCode(runningCarePathDetail.getCarePathCode());
									carePathListing.setStatus("SUGGESTING");
									suggestedCarePaths.add(carePathListing);
								}
								carePathListing.getSuggestions().add(getSuggestion(runningCarePathDetail));
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

	}

	private String getSuggestion(
			RunningCarePathAttrDetails runningCarePathDetail) {
		return "CP: "+runningCarePathDetail.getCarePathName()+"-> " + runningCarePathDetail.getCarePathAttrName();
	}

	private String getSuggestion(
			CicAttrRefCarePathDto cicAttrRefCarePathDto) {
		return "CIC: "+cicAttrRefCarePathDto.getAttrName();
	}

	private CarePathListingDto getCarePathListingDtoFromRunningOrSuggested(String nextCarePathCode,
			List<CarePathListingDto> suggestedCarePaths, 
			List<CarePathListingDto> runningCarePaths){
		CarePathListingDto carePathListing = getCarePathListing(nextCarePathCode, runningCarePaths);
		if(carePathListing == null){
			carePathListing = getCarePathListing(nextCarePathCode, suggestedCarePaths);
		}
		return carePathListing;
	}

	private CarePathListingDto getCarePathListing(
			String nextCarePathCode, List<CarePathListingDto> carePaths) {
		for(CarePathListingDto carePathListing:carePaths){
			if(nextCarePathCode.equals(carePathListing.getCarePathCode())){
				return carePathListing;
			}
		}
		return null;
	}

	private void populateRunningCarePathCodes(Set<String> runningCarePathCodes, List<RunningCarePathAttrDetails> runningCarePathDetails) {
		if(!CollectionUtils.isEmpty(runningCarePathDetails)){
			for(RunningCarePathAttrDetails runningCarePathDetail: runningCarePathDetails){
				runningCarePathCodes.add(runningCarePathDetail.getCarePathCode());
			}
		}
	}

	private Document generateDocObject(String xmlFileLocation) throws Exception{
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFileLocation);
			return doc;
		}catch(Exception e){
			throw e;
		}		
	}
	private XPath generateXPathObject(String xmlFileLocation, Document doc) throws Exception{

		try {
			// Create XPathFactory object
			XPathFactory xpathFactory = XPathFactory.newInstance();

			// Create XPath object
			XPath xpath = xpathFactory.newXPath();
			return xpath;
		}catch(Exception e){
			throw e;
		}
	}
	@Transactional
	public List<CarePathStepsResponseDto> viewCarePath(Long staffId,Long facilityId,Long sbarId,CarePathDto carePathDto) throws JAXBException {
		List<CarePathStepsResponseDto> carePathStepList=new ArrayList<CarePathStepsResponseDto>();
		invokeCarePath(staffId,facilityId,carePathDto,null);

		//PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
		CarePath carePath=carePathDao.findByCarePathCode(carePathDto.getCarePathCode());

		for(CarePathTracker carePathTracker: carePathTrackerDao.findAllSteps(sbarDao.findById(sbarId), carePath)){
			CarePathStepsResponseDto carePathStepDto=new CarePathStepsResponseDto();
			carePathStepDto.setStepId(carePathTracker.getStepId());
			carePathStepDto.setStepName(carePathTracker.getStepName());
			carePathStepDto.setCarePathName(carePath.getCarePathName());
			carePathStepDto.setAttrValues(carePathTrackerAttrDao.getCPTAttr(carePathTracker));
			carePathStepList.add(carePathStepDto);
		}
		return carePathStepList;
	}

	@Transactional
	public void invikeAllCarePaths(Long staffId,Long facilityId, Long patientEposodeId) {
		PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEposodeId);

		for(CarePath carepath:carePathTrackerDao.findAllRuningCarePath(sbarDao.findByPatientEpisode(patientEpisode).getId())){
			CarePathDto carePathDto=new CarePathDto();
			Long patientId=patientEpisode.getPatient().getId();
			carePathDto.setPatientId(patientId);
			carePathDto.setPatientEpisodeId(patientEposodeId);
			carePathDto.setCarePathCode(carepath.getCarePathCode());
			invokeCarePath(staffId,facilityId, carePathDto, null);
		}

	}

	@Transactional
	public CarePathTracker test() {
		//		PatientEpisode patientEpisode=patientEpisodeDao.findById(66l);
		//		CarePath carePath=carePathDao.findById(5l);
		return carePathTrackerDao.findById(4799l);
	}
	@Transactional
	public List<ViewAllCarePathsResponseDto> getViewAllCarePath(Long staffId,Long facilityId,
			Long patientEpisodeId,Long encounterId) {
		List<ViewAllCarePathsResponseDto> viewAllCarePathsList=new  ArrayList<ViewAllCarePathsResponseDto>();
		List<CarePath> runingCarePaths=new ArrayList<CarePath>();
		PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
		Long sbarId=null;
		if(encounterId!=null){
			sbarId=sbarService.getSbarIdbyEncounterId(encounterId);
			//runingCarePaths=carePathTrackerDao.findAllRuningCarePath(sbarId);
		}else{
			sbarId=sbarDao.findByPatientEpisode(patientEpisode).getId();
			//runingCarePaths=carePathTrackerDao.findAllRuningCarePath(sbarDao.findByPatientEpisode(patientEpisode).getId());
		}
		
		runingCarePaths=carePathTrackerDao.findAllRuningCarePath(sbarId);
		for(CarePath carePath:runingCarePaths){
			ViewAllCarePathsResponseDto  viewCarePathsResponse=new ViewAllCarePathsResponseDto();
			viewCarePathsResponse.setCarePathName(carePath.getCarePathName());
			viewCarePathsResponse.setCarePathId(carePath.getId());
			CarePathDto carePathDto = new CarePathDto();
			carePathDto.setCarePathCode(carePath.getCarePathCode());
			carePathDto.setPatientId(patientEpisode.getPatient().getId());
			carePathDto.setPatientEpisodeId(patientEpisodeId);
			List<CarePathStepsResponseDto> carePathStepList=null;
			try { 
				carePathStepList=viewCarePath(staffId,facilityId,sbarId	,carePathDto);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewCarePathsResponse.setCarePathSteps(carePathStepList);

			viewAllCarePathsList.add(viewCarePathsResponse);

		}

		return viewAllCarePathsList;
	}

	@Transactional
	public List<CarePath> getRunningCarePaths(Long patientEpisodeId) {
		PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
		List<CarePath> getRunningCarePaths=carePathTrackerDao.findAllRuningCarePath(sbarDao.findByPatientEpisode(patientEpisode).getId());

		// TODO Auto-generated method stub
		return getRunningCarePaths;
	}

	private void carePathStepPause(Long staffId,Long facilityId,Long patientEpisodeId,CarePathStepTrackerDto carePathStepTrackerDto,CarePathDto carePathDto){

		FacilityStaff fStaff= facilityStaffDao.findFacilityStaff(facilityId,staffId);
//		Long responsibiliyId= fStaff.getStaffRoles().getResponsibilitiesId();
//		Responsibilities responsibilities= responsibilitiesDao.findById(responsibiliyId);
		PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
		Sbar sbar=sbarDao.findByPatientEpisode(patientEpisode);
		Long sbarId = sbar.getId();
		if(!fStaff.getFacility().isStandingOrders() && !StringUtils.isEmpty(carePathStepTrackerDto.getCurrCarePathCode())){
			List<CarePathLabworksDto> list = carePathLabworksService.getExistedCarePathLabworks(sbarId, carePathStepTrackerDto.getCurrCarePathCode());
			CarePathLabworksDto cplabWorksDto =new CarePathLabworksDto();
			List<Long> signSymLabIds = new ArrayList<Long>();
			boolean pauseFlag = true;
			for(CPAttrValueDto dto:carePathStepTrackerDto.getAttrValues()){
				signSymLabIds.add(signsSymptomsLabworkDao.getSignSympLabworkIdByCPLWCode(dto.getCarePathAttrCode()).getId());
			}
			if(CollectionUtils.isEmpty(list)){
				for(Long signSymLabId:signSymLabIds){
					cplabWorksDto.setSbarId(sbar.getId());
					cplabWorksDto.setSignsSymptomsLabworkId(signSymLabId);
					cplabWorksDto.setCarePathCode(carePathStepTrackerDto.getCurrCarePathCode());
					cplabWorksDto.setStandingOrders(fStaff.getFacility().isStandingOrders());
					cplabWorksDto.setFacilityStaffId(fStaff.getId());
					cplabWorksDto.setStartDate(new Date());
					cplabWorksDto.setApproved(false);
					cplabWorksDto.setRejected(false);
					carePathLabworksService.saveOrUpdate(cplabWorksDto);
				}
			}
			for(Long id:signSymLabIds){
				if(patientEpisodeDao.isApprovedOrRejected(id, sbarId)){
					pauseFlag= false;
					break;
				}
			}
			if(pauseFlag){
				carePathDto.setMessage(Constants.NEED_APPROVE);
			}
		}else if(!StringUtils.isEmpty(carePathStepTrackerDto.getCurrCarePathCode())){
			List<CarePathLabworksDto> list = carePathLabworksService.getExistedCarePathLabworks(sbar.getId(), carePathStepTrackerDto.getCurrCarePathCode());
			CarePathLabworksDto cplabWorksDto =new CarePathLabworksDto();
			if(CollectionUtils.isEmpty(list)){
				for(CPAttrValueDto dto:carePathStepTrackerDto.getAttrValues()){
					Long signSymLabId= signsSymptomsLabworkDao.getSignSympLabworkIdByCPLWCode(dto.getCarePathAttrCode()).getId();
					cplabWorksDto.setPatientEpisodeId(patientEpisodeId);
					cplabWorksDto.setSignsSymptomsLabworkId(signSymLabId);
					cplabWorksDto.setCarePathCode(carePathStepTrackerDto.getCurrCarePathCode());
					cplabWorksDto.setFacilityStaffId(fStaff.getId());
					cplabWorksDto.setStandingOrders(fStaff.getFacility().isStandingOrders());
					cplabWorksDto.setStartDate(new Date());
					cplabWorksDto.setApproved(true);
					cplabWorksDto.setRejected(false);
					carePathLabworksService.saveOrUpdate(cplabWorksDto);
				}

			}

		}
	}

	private void carePathLabworksDelete(Long episodeId,String carePathCode){
		carePathLabworksService.deleteByEpisodeIdNdcarePathCode(episodeId, carePathCode);
	}


}
