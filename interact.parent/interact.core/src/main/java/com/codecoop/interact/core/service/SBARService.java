package com.codecoop.interact.core.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.AcuteCareDao;
import com.codecoop.interact.core.dao.AdmissionAttributesDao;
import com.codecoop.interact.core.dao.AdmissionAttributesValuesDao;
import com.codecoop.interact.core.dao.AssignedDocRelationDaoImpl;
import com.codecoop.interact.core.dao.CarePathDaoImpl;
import com.codecoop.interact.core.dao.CarePathTrackerAttrDaoImpl;
import com.codecoop.interact.core.dao.CarePathTrackerDaoImpl;
import com.codecoop.interact.core.dao.ChangeInConditionDao;
import com.codecoop.interact.core.dao.FacilityDaoImpl;
import com.codecoop.interact.core.dao.FacilityStaffDaoImpl;
import com.codecoop.interact.core.dao.MessageAlertDaoImpl;
import com.codecoop.interact.core.dao.PatientDaoImpl;
import com.codecoop.interact.core.dao.PatientEncounterDaoImpl;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.dao.PrescribeMedicineDaoImpl;
import com.codecoop.interact.core.dao.SbarDaoImpl;
import com.codecoop.interact.core.dao.SbarNotesDaoImpl;
import com.codecoop.interact.core.dao.SbarSymptomsDaoImpl;
import com.codecoop.interact.core.dao.SignsSymptomsLabworkAttrDao;
import com.codecoop.interact.core.dao.StaffDaoImpl;
import com.codecoop.interact.core.dao.StopAndWatchDaoImpl;
import com.codecoop.interact.core.dao.StopAndWatchHistoryDaoImpl;
import com.codecoop.interact.core.domain.AcuteCare;
import com.codecoop.interact.core.domain.AdmissionAttributesValues;
import com.codecoop.interact.core.domain.CarePath;
import com.codecoop.interact.core.domain.CarePathTracker;
import com.codecoop.interact.core.domain.CarePathTrackerAttr;
import com.codecoop.interact.core.domain.Facility;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.Guardian;
import com.codecoop.interact.core.domain.PatientEncounter;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.PrescribeMedicine;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.SbarNotes;
import com.codecoop.interact.core.domain.SbarSymptoms;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.domain.StopAndWatch;
import com.codecoop.interact.core.domain.StopAndWatchHistory;
import com.codecoop.interact.core.dto.CarePathSuggestionMessageDto;
import com.codecoop.interact.core.dto.ChangeInConditionDataForEvaluationDto;
import com.codecoop.interact.core.dto.ChangeInConditionMessageDto;
import com.codecoop.interact.core.dto.EvaluationMessageDto;
import com.codecoop.interact.core.dto.MessageDto;
import com.codecoop.interact.core.dto.NurseNotesDto;
import com.codecoop.interact.core.dto.PatientEncounterDto;
import com.codecoop.interact.core.dto.PdfResponseDto;
import com.codecoop.interact.core.dto.PrescribeMedicineDto;
import com.codecoop.interact.core.dto.SbarDto;
import com.codecoop.interact.core.dto.SbarNotesDto;
import com.codecoop.interact.core.dto.SbarNotesResponseDto;
import com.codecoop.interact.core.dto.SbarSymtomDto;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkAttrDto;
import com.codecoop.interact.core.dto.SystemNotesDto;
import com.codecoop.interact.core.dto.carepath.LabWorkDto;
import com.codecoop.interact.core.dto.carepath.VitalSignDto;
import com.codecoop.interact.core.dto.sbar.SbarReleventSymptomsDto;
import com.codecoop.interact.core.dto.sbar.SectionDto;
import com.codecoop.interact.core.dto.sbar.SymptomDto;
import com.codecoop.interact.core.model.LoggedUserModel;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.core.util.CoreDateUtils;
import com.codecoop.interact.core.util.LabWorkUtils;
import com.codecoop.interact.core.util.PatientUtils;
import com.codecoop.interact.core.util.VitalSignUtils;

@Service
public class SBARService {

	private static final Logger LOG = Logger.getLogger(SBARService.class);
	@Autowired
	CarePathDaoImpl carePathDao;
	@Autowired
	SbarNotesDaoImpl sbarNotesDao;
	@Autowired
	PatientEncounterDaoImpl patientEncounterDao;
	@Autowired
	CarePathTrackerDaoImpl carePathTrackerDao;
	@Autowired
	CarePathTrackerAttrDaoImpl carePathTrackerAttrDao;
	@Autowired
	PatientEpisodeDao patientEpisodeDao;
	@Autowired
	PatientDaoImpl patientDao;
	@Autowired
	StopAndWatchHistoryDaoImpl stopAndWatchHistoryDao;
	@Autowired
	StopAndWatchDaoImpl stopAndWatchDao;
	@Autowired
	StopAndWatchService stopAndWatchService;
	@Autowired
	CarePathGeneratorService carePathGeneratorService;
	@Autowired
	SignsSymptomsLabworkAttrDao signsSymptomsLabworkAttrDao;
	@Autowired
     SbarSymptomsDaoImpl sbarSymptomsDao;
	@Autowired
    FacilityDaoImpl facilityDao;
    
	@Autowired
	AdmissionAttributesDao admissionAttributesDao;

	@Autowired
	AdmissionAttributesValuesDao admissionAttributesValuesDao;

	@Autowired
	ChangeInConditionDao changeInConditionDao;
	@Autowired
	AttributesService attributesService;
	@Autowired
	SbarDaoImpl sbarDao;
	@Autowired
	StaffDaoImpl staffDao;
	@Autowired
	FacilityStaffDaoImpl facilityStaffDao;
	@Autowired
	StopAndWatchDaoImpl StopAndWatchDao;
	@Autowired
	MessageService messageService;
	@Autowired
	PrescribeMedicineDaoImpl prescribeMedicineDao;
	@Autowired
	AcuteCareDao acuteCareDao;
	@Autowired
	AssignedDocRelationDaoImpl assignedDocRelationDao;
	@Autowired
	MessageAlertDaoImpl messageDao;
	@Transactional
	public SbarDto populateSbarData(Long patientEpisodeId, boolean isFromEdit) {
		SbarDto sbarDto = new SbarDto();

		if (isFromEdit) {
			sbarDto = populateSbarDatafromSbar(sbarDto, patientEpisodeId);
		}
		sbarDto = populateSbarDatafromAdmission(sbarDto, patientEpisodeId);

		return sbarDto;
	}

	@Transactional
	public SbarDto populateSbarDatafromAdmission(SbarDto sbarDto,
			Long patientEpisodeId) {
		PatientEpisode patientEpisode = patientEpisodeDao
				.findById(patientEpisodeId);
		// sbarDto.setPatientEpisode(patientEpisode);
		sbarDto.setAdvCarePlanDNHflag(patientEpisode.getDnh());
		sbarDto.setAdvCarePlanDNIFlag(patientEpisode.getDni());
		sbarDto.setAdvCarePlanDNRFlag(patientEpisode.getDnr());
		sbarDto.setAdvCarePlanNoEtrnalFeedFlag(patientEpisode
				.getNoArtificialFeeding());
		sbarDto.setOtherPreferences(patientEpisode.getOtherCare());
		sbarDto.setOtherOrderOrWill(patientEpisode.getOtherCare());
		for (Guardian guardian : patientEpisode.getPatient().getGurdianList()) {
			if (guardian.getRelationToPatient().equalsIgnoreCase(
					"GUARDIAN_OR_PROXY_CONTACT")) {
				sbarDto.setGaurdianName(guardian.getGuardianName());
			}
		}

		Map<String, String> admissionAttrMap = attributesService
				.getPatientAdmissionAttributeValuesAsMap(patientEpisode.getId());
		String primaryDiagnoses = admissionAttrMap
				.get(Constants.PRIMARYDIAGNOSES);
		String otherPertinentHistory = admissionAttrMap
				.get(Constants.OTHERPERTINENTHISTORY);
		sbarDto.setPrimaryDiagnoses(primaryDiagnoses);
		sbarDto.setOtherPertinentHistory(otherPertinentHistory);

		return sbarDto;
	}

	@Transactional
	public SbarDto populateSbarDatafromSbar(SbarDto sbarDto,
			Long patientEpisodeId) {
		PatientEpisode patientEpisode = patientEpisodeDao
				.findById(patientEpisodeId);
		Sbar sbar = sbarDao.findByPatientEpisode(patientEpisode);
		if (sbar != null) {
			sbarDto.setAllergies(sbar.getAllergies());
			sbarDto.setLatestInrDate(CoreDateUtils.parseStringDate(
			sbar.getLatestInrDate(), "mm/dd/yyyy"));
			sbarDto.setMedAlertChangeLastWeek(sbar.getMedAlertChangeLastWeek());
			sbarDto.setMedAlertInWarfarin(sbar.getMedAlertInWarfarin());
			sbarDto.setOtherNewOrders(sbar.getOtherNewOrders());
			sbarDto.setOtherNewOrdersFlag(sbar.getOtherNewOrdersFlag());
			sbarDto.setProviderVisitFlag(sbar.getProviderVisitFlag());
			sbarDto.setResultOfLastInr(sbar.getResultOfLastInr());
			sbarDto.setTransferToHospitalFlag(sbar.getTransferToHospitalFlag());
			try{  
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
				
				String repotedDate = df.format(sbar.getRepotedToDate());
				sbarDto.setRepotedToDate(repotedDate);
			}catch (Exception e){
				sbar.setRepotedToDate(new Date());
			}
			/*sbarDto.setRepotedToDate(CoreDateUtils.parseStringDate(
					sbar.getRepotedToDate(), "mm/dd/yyyy"));*/
			if (sbar.getDoctor() != null) {
				sbarDto.setDoctorId(sbar.getDoctor().getId());
			} 
			sbarDto.setProviderVisitFlag(sbar.getProviderVisitFlag());
			sbarDto.setOximetryOnO2(sbar.getOximetryOnO2());
			sbarDto.setOximetryOnRoomair(sbar.getOximetryOnRoomair());
			sbarDto.setOtherResidentCare(sbar.getOtherResidentCare());
		}
		return sbarDto;
	}

	
	
	public Sbar createIntialSbar(PatientEpisode patientEpisode,String userName) throws Exception
	{  
		    Sbar sbar=new Sbar();
			sbar.setPatientEpisode(patientEpisode);
			sbar.setDateCreated(new Date());
			// sbar.setUserCreated(userCreated);
			patientEpisode.setSbarRised(true);
			patientEpisodeDao.saveOrUpdate(patientEpisode);
			StopAndWatch stopAndWatch=StopAndWatchDao.findActiveStopAndWatch(patientEpisode);
			Sbar recentlyClosedSbr=sbarDao.recentClosedSabr(patientEpisode);
			if(recentlyClosedSbr!=null){
				 String prevScenariocode=recentlyClosedSbr.getMsgScenariocode();
				 messageService.suspendedPreviousMessages(recentlyClosedSbr.getId(),prevScenariocode,Constants.MESSAGE);
				 messageService.suspendedPreviousAlerts(recentlyClosedSbr.getId(),prevScenariocode,Constants.ALERT);
			
			}
			if(stopAndWatch!=null){
			stopAndWatch.setEndDate(new Date());
			StopAndWatchDao.saveOrUpdate(stopAndWatch);
			 List<StopAndWatchHistory> stopAndWatchHistoryList=messageDao.getPrevScenarioCodeByStopAndWatchId(stopAndWatch.getId());
			 for(StopAndWatchHistory stopAndWatchHistory:stopAndWatchHistoryList){
				 messageService.suspendedPreviousMessages(stopAndWatchHistory.getId(),stopAndWatchHistory.getMsgScenarioCode(),Constants.MESSAGE);
				 messageService.suspendedPreviousAlerts(stopAndWatchHistory.getId(),stopAndWatchHistory.getMsgScenarioCode(),Constants.ALERT);
			 }
			}
			sbar.setPatientRecoverdFlag(false);
			sbar.setTransferToHospitalFlag(false);
				MessageDto messageDto=new MessageDto();
				String prevScenariocode=messageDao.getPrevScenarioCodeByEventId(patientEpisode.getId());
			    messageService.suspendedPreviousMessages(patientEpisode.getId(),prevScenariocode,Constants.MESSAGE);
			    messageService.suspendedPreviousAlerts(patientEpisode.getId(),prevScenariocode,Constants.ALERT);
			    
			
			String msgScenarioCode=Constants.OBSERVATION_CREATED;
			messageDto.setFacilityId(patientEpisode.getFacilityId());
			messageDto.setPatientEpisodeId(patientEpisode.getId());
			messageDto.setPatientId(patientEpisode.getPatient().getId());
			messageDto.setPrevScenarioCode(sbar.getMsgScenariocode());
			messageDto.setCurrentScenarioCode(msgScenarioCode);
			messageDto.setMsgSubj(Constants.OBSERVATION_SUBJECT);
			messageDto.setDeleteFlag(false);
			messageDto.setReadFlag(false);
			messageDto.setSentDate(sbar.getDateCreated());
			sbar.setMsgScenariocode(msgScenarioCode);
			sbarDao.saveOrUpdate(sbar);
			PatientEncounter patientEncounter=patientEncounterDao.findActiveEncounter(patientEpisode.getId());
			if(patientEncounter==null){
				int encounterNum=0;
				patientEncounter=patientEncounterDao.findLastEcounterNumber(patientEpisode.getId());
				if(patientEncounter!=null){
					encounterNum=patientEncounter.getEncounterNum();
				}
	
		    	patientEncounter=new PatientEncounter(patientEpisode, encounterNum+1, new Date(), null, null, new Date(), userName, null, null,null,null,null,null);
		    	patientEncounterDao.saveOrUpdate(patientEncounter);
			}
			messageDto.setEventId(sbar.getId());
			messageService.saveMessages(messageDto);
			messageService.saveAlerts(messageDto);
	  
		return sbar;
	}

	@Transactional
	public void sbarsaveIntialSbar(PatientEpisode patientEpisode,String userName) throws Exception {

		Sbar sbar = sbarDao.findByPatientEpisode(patientEpisode);
		if (sbar == null) {
			sbar= createIntialSbar(patientEpisode,userName);
		}
		
	
	}

	@Transactional
	public List<Sbar> findAll() {
		return sbarDao.findAll();
	}

	@Transactional
	public Sbar findByPatientEpisode(PatientEpisode patientEpisode) {
		return sbarDao.findByPatientEpisode(patientEpisode);
	}

	@Transactional
	public void saveSbarData(Long facilityId, Long staffId, SbarDto sbarDto) throws Exception {
		 String msgSenarioCode =null;
		Long patientEpisodeId = sbarDto.getPatientEpisodeId();
		PatientEpisode patientEpisode = patientEpisodeDao
				.findById(patientEpisodeId);
		Sbar sbar = findByPatientEpisode(patientEpisode);
		Staff doctor;
		FacilityStaff facilityStaff = facilityStaffDao.findFacilityStaff(
				facilityId, staffId);
		String staffRole = facilityStaff.getStaffRoles().getRoleName();

		if (sbar == null) {
			sbar=createIntialSbar(patientEpisode,facilityStaff.getStaff().getUsername());

		} else {
			sbar.setDateModified(new Date());
			sbar.setUserModified(facilityStaff.getStaff().getFirstName());
		}
		patientEpisode.setDnh(sbarDto.getAdvCarePlanDNHflag());
		patientEpisode.setDni(sbarDto.getAdvCarePlanDNIFlag());
		patientEpisode.setDnr(sbarDto.getAdvCarePlanDNRFlag());
		patientEpisode.setNoArtificialFeeding(sbarDto
				.getAdvCarePlanNoEtrnalFeedFlag());
		patientEpisode.setOtherCare(sbarDto.getOtherOrderOrWill());
		//sbar.setOtherResidentCare(sbarDto.getOtherResidentCare());
		for (Guardian guardian : patientEpisode.getPatient().getGurdianList()) {
			if (guardian.getRelationToPatient().equalsIgnoreCase(
					"GUARDIAN_OR_PROXY_CONTACT")) {
				guardian.setGuardianName(sbarDto.getGaurdianName());
			}
		}
		patientEpisodeDao.saveOrUpdate(patientEpisode);
		AdmissionAttributesValues admissionAttributesValue = null;
		admissionAttributesValue = admissionAttributesValuesDao
				.findAdmissionAttrValueByAttributeCodeAndEpisodeId(
						Constants.PRIMARYDIAGNOSES, patientEpisodeId);
		if (admissionAttributesValue != null) {
			admissionAttributesValue.setAttributeValue(sbarDto
					.getPrimaryDiagnoses());
			admissionAttributesValuesDao.saveOrUpdate(admissionAttributesValue);
		}
		if (admissionAttributesValue != null) {
			admissionAttributesValue = admissionAttributesValuesDao
					.findAdmissionAttrValueByAttributeCodeAndEpisodeId(
							Constants.OTHERPERTINENTHISTORY, patientEpisodeId);
			admissionAttributesValue.setAttributeValue(sbarDto
					.getOtherPertinentHistory());
			admissionAttributesValuesDao.saveOrUpdate(admissionAttributesValue);
		}

		sbar.setAllergies(sbarDto.getAllergies());
		if (sbarDto.getDoctorId() != null) {
			doctor = staffDao.findById(sbarDto.getDoctorId());
			sbar.setDoctor(doctor);
		}
		if(sbarDto.getLatestInrDate()!=null&&!sbarDto.getLatestInrDate().isEmpty()){
		sbar.setLatestInrDate(CoreDateUtils.parseDate(
				sbarDto.getLatestInrDate(), "mm/dd/yyyy"));
		}
		sbar.setMedAlertInWarfarin(sbarDto.getMedAlertInWarfarin());
		// sbar.setNotesOnChangeInCondition(sbarForm.);
		// sbar.setNursingStaff(sbarForm.);
		sbar.setOtherNewOrders(sbarDto.getOtherNewOrders());
		sbar.setOtherNewOrdersFlag(sbarDto.getOtherNewOrdersFlag());
		sbar.setMedAlertChangeLastWeek(sbarDto.getMedAlertChangeLastWeek());
		sbar.setProviderVisitFlag(sbarDto.getProviderVisitFlag());
		sbar.setResultOfLastInr(sbarDto.getResultOfLastInr());
//		if(sbarDto.getTransferToHospitalFlag()!=null){
//		sbar.setTransferToHospitalFlag(sbarDto.getTransferToHospitalFlag());}
//		if(sbarDto.getManageInFacilityFlag()!=null){
//		sbar.setManageInFacilityFlag(sbarDto.getManageInFacilityFlag());}
		if(sbarDto.getRepotedToDate()!=null){
		try{  
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
			Date repotedDate = (Date) df.parse(sbarDto.getRepotedToDate());
		
			sbar.setRepotedToDate(repotedDate);
		}catch (Exception e){
			sbar.setRepotedToDate(new Date());
		}
		}
		
		//sbar.setRepotedToDate(CoreDateUtils.parseDate(
				//sbarDto.getRepotedToDate(), "mm/dd/yyyy"));
		sbar.setOximetryOnRoomair(sbarDto.getOximetryOnRoomair());
		sbar.setOximetryOnO2(sbarDto.getOximetryOnO2());
		sbar.setOtherResidentCare(sbarDto.getOtherResidentCare());
	//	sbar.setSymtomList(sbarDto.getSbarSymtomList());
		
		
		
		MessageDto messageDto=new MessageDto();


		sbarDao.saveOrUpdate(sbar);
		for(SbarSymtomDto sbarSymtom:sbarDto.getSbarSymtomList())
		{  Long sbarId=sbar.getId();
		   String code=sbarSymtom.getCode();
			SbarSymptoms sbarSymptoms=sbarSymptomsDao.findBySbarIdAndCode(sbar.getId(), sbarSymtom.getCode());
			if(sbarSymptoms==null){
				sbarSymptoms=new SbarSymptoms();
				sbarSymptoms.setCode(code);
				sbarSymptoms.setSbarId(sbarId);
				sbarSymptoms.setDateCreated(new Date());
				sbarSymptoms.setUserCreated(facilityStaff.getStaff().getFirstName());
			}else
			{
				sbarSymptoms.setUserModified(facilityStaff.getStaff().getFirstName());
				sbarSymptoms.setDateModified(new Date());
			}
	 		if(sbarSymtom.getBoolValue()!=null){
			sbarSymptoms.setBoolValue(sbarSymtom.getBoolValue());
		 	}
		 	if(sbarSymtom.getTextValue()!=null){
			sbarSymptoms.setTextValue(sbarSymtom.getTextValue());
		 	}
		 	if(sbarSymtom.getDateValue()!=null){
			try{  
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
				Date dateValue = (Date) df.parse(sbarSymtom.getDateValue());
			
				sbarSymptoms.setDateValue(dateValue);
			}catch (Exception e){
			//	sbar.setRepotedToDate(new Date());
			}
		 }
			sbarSymptomsDao.saveOrUpdate(sbarSymptoms);
			
		}
	
	}

	@Transactional
	public Sbar findByPatientEpisodeId(Long patientEposodeId) {
		return sbarDao.findByPatientEpisode(patientEpisodeDao
				.findById(patientEposodeId));

	}

	@Transactional
	public void saveSbarNotes(SbarNotesDto sbarNotesDto) {
		Sbar sbar = findByPatientEpisodeId(sbarNotesDto.getPatientEpisodeId());
		Long staffId = sbarNotesDto.getNursingStaffId();
		Staff nursingStaff = staffDao.findById(staffId);
		FacilityStaff nursingFacilityStaff=facilityStaffDao.findFacilityStaff(sbarNotesDto.getFacilityId(), staffId);
		PatientEpisode patientEpisode = patientEpisodeDao.findById(sbarNotesDto	.getPatientEpisodeId());
		if (sbar == null) {
			sbar = new Sbar();
			sbar.setPatientEpisode(patientEpisode);
			sbar.setUserCreated(nursingStaff.getFirstName());
			sbar.setDateCreated(new Date());
			patientEpisode.setSbarRised(true);
			patientEpisodeDao.saveOrUpdate(patientEpisode);

		}

		SbarNotes sbarNotes = new SbarNotes();
		sbarNotes.setSbar(sbar);
		sbarNotes.setNotes(sbarNotesDto.getNotes());
		sbarNotes.setNursingFacilityStaff(nursingFacilityStaff);
		Long facilityId = sbar.getPatientEpisode().getFacilityId();
		sbarNotes.setNursingStaffType(getStaffRoleType(staffId, facilityId));
		sbarNotes.setUserCreated(nursingStaff.getFirstName());
		sbarNotes.setDateCreated(new Date());
		sbarNotes.setDateModified(new Date());
		sbarNotesDao.saveOrUpdate(sbarNotes);

	}

	@Transactional
	public String getStaffRoleType(Long staffId, long facilityId) {
		String roleName = facilityStaffDao
				.findFacilityStaff(facilityId, staffId).getStaffRoles()
				.getRoleName();
		String roleType;
		if (roleName.equals(Constants.MD_ROLE)
				|| roleName.equals(Constants.PA_ROLE)
				|| roleName.equals(Constants.NP_ROLE)) {
			roleType = Constants.DOCTOR_TYPE;
		} else {
			if (roleName.equals(Constants.RN_ROLE)
					|| roleName.equals(Constants.LPN_ROLE)) {
				roleType = Constants.NURSE_TYPE;
			} else {
				roleType = "NONE";
			}
		}
		return roleType;
	}

	@Transactional
	public List<SbarNotesResponseDto> getSbarNotes(Sbar sbar,String nursingStaffType) {
		List<SbarNotesResponseDto> sbarNotesResponseList = new ArrayList<SbarNotesResponseDto>();
		List<NurseNotesDto> NurseNoteslist;
		for (FacilityStaff nursingStaff : sbarNotesDao.findAllNursingStaff(sbar,nursingStaffType))// Constants.NURSE_TYPE))
		{
			String staffName = nursingStaff.getStaff().getFirstName();

			SbarNotesResponseDto sbarNotesResponse = new SbarNotesResponseDto();
			NurseNoteslist = new ArrayList<NurseNotesDto>();
			//List<SbarNotes> list=sbarNotesDao.findBySbarIdAndStaff(sbar,nursingStaff);
			for (SbarNotes sbarNotes : sbarNotesDao.findBySbarIdAndStaff(sbar,nursingStaff)) {
				NurseNotesDto nurseNotes = new NurseNotesDto();
				if (sbarNotes.getNotes() != null) {
					nurseNotes.setNotes(sbarNotes.getNotes());
				} else {
					nurseNotes.setNotes("");
				}
				nurseNotes.setSbarNotesId(sbarNotes.getId());
				nurseNotes
						.setCreatedDate(sbarNotes.getDateCreated().toString());
				NurseNoteslist.add(nurseNotes);
			}

			sbarNotesResponse.setStaffName(staffName);
			//need to change staffId to FacilityStaffId in sbarNotesResponse
			sbarNotesResponse.setStaffId(nursingStaff.getStaff().getId());
			sbarNotesResponse.setNurseNotesList(NurseNoteslist);

			sbarNotesResponseList.add(sbarNotesResponse);
		}
		return sbarNotesResponseList;
	}

	@Transactional
	public List<SystemNotesDto> getSbarSystemNotes(Long patientEpisodeId) {
		List<SystemNotesDto> systemNotesList = new ArrayList<SystemNotesDto>();
		PatientEpisode patientEpisode = patientEpisodeDao
				.findById(patientEpisodeId);
		for (String stepType : carePathTrackerDao
				.findStepTypesForShowInSbar(sbarDao.findByPatientEpisode(patientEpisode))) {
			SystemNotesDto systemNotes = new SystemNotesDto();
			systemNotes.setStepType(stepType);
			Set<String> instructionSet = new HashSet<String>();
			List<String> instructionsList = new ArrayList<String>();
			for (CarePathTracker carePathTracker : carePathTrackerDao
					.findByStepType(sbarDao.findByPatientEpisode(patientEpisode), stepType)) {
				for (CarePathTrackerAttr carePathTrackerAttr : carePathTrackerAttrDao
						.findByCPT(carePathTracker)) {
					instructionSet.add(carePathTrackerAttr
							.getCarePathAttrName());
				}
			}
			instructionsList.addAll(instructionSet);
			systemNotes.setInstuctionsList(instructionsList);
			systemNotesList.add(systemNotes);
		}
		return systemNotesList;
	}

	@Transactional
	public void deleteSbarNotes(Long notesId) {
		sbarNotesDao.delete(sbarNotesDao.findById(notesId));

	}

	@Transactional
	public SbarNotes findById(Long notesId) {
		return sbarNotesDao.findById(notesId);
	}

	@Transactional
	public void editSbarNotes(Long notesId, String notes, String staffName) {
		SbarNotes sbarNotes = sbarNotesDao.findById(notesId);
		if (sbarNotes != null) {
			sbarNotes.setNotes(notes);
			sbarNotes.setDateModified(new Date());
			sbarNotes.setUserModified(staffName);
			sbarNotesDao.saveOrUpdate(sbarNotes);

		}

	}

	@Transactional
	public EvaluationMessageDto getEvaluationResult(Long patientEpisodeId) {
		// List<EvaluationMessageDto> EvaluationMessageList= new
		// ArrayList<EvaluationMessageDto>();
		
		EvaluationMessageDto evaluationMessage = new EvaluationMessageDto();
		PatientEpisode patientEpisode = patientEpisodeDao.findById(patientEpisodeId);
		evaluationMessage.setPatientId(patientEpisode.getPatient().getId());
	    List<CarePathSuggestionMessageDto> carePathSuggestionMessageList=getCarePathSuggestionMessages(patientEpisodeId);
	    evaluationMessage.setCarePathSuggestionMessageList(carePathSuggestionMessageList);
	    evaluationMessage.setCarePathNotifyDoctorMessageList(carePathTrackerAttrDao.getNotifyDoctorRecords(sbarDao.findByPatientEpisode(patientEpisode).getId()));
		List<ChangeInConditionMessageDto> changeInConditionMessagelist = getChangeInConditionDateForEvaluation(patientEpisodeId);
		evaluationMessage.setChangeInConditionMessageList(changeInConditionMessagelist);
		return evaluationMessage;
	}

	private List<CarePathSuggestionMessageDto> getCarePathSuggestionMessages(
			Long patientEpisodeId) {
		 List<CarePathSuggestionMessageDto> CarePathSuggestionMessageList=new  ArrayList<CarePathSuggestionMessageDto>();
		 PatientEpisode patientEpisode = patientEpisodeDao.findById(patientEpisodeId);
		 
		List<CarePath> patientCarePathList= carePathTrackerDao.findAllRuningCarePath(sbarDao.findByPatientEpisode(patientEpisode).getId());
		
		for(CarePath carePath:patientCarePathList){
			CarePathSuggestionMessageDto carePathSuggestionMessage=new CarePathSuggestionMessageDto();
			   String sugestion="None";
			   List<CarePathTracker> carePathTrackerSteps=carePathTrackerDao.findAllStepsInDesc(sbarDao.findByPatientEpisode(patientEpisode), carePath);
			   for(CarePathTracker carePathTracker:carePathTrackerSteps){
				   sugestion=getCrePathStepSug(carePathTracker);
				   if(!sugestion.equals("None")){
					   break;
				   }
			   }
			   carePathSuggestionMessage.setCarePathName(carePath.getCarePathName());
			   carePathSuggestionMessage.setSugestion(sugestion);
			   CarePathSuggestionMessageList.add(carePathSuggestionMessage);
		}
		
		return CarePathSuggestionMessageList;
	}

	private String getCrePathStepSug(CarePathTracker carePathTracker) {
		if(carePathTracker.getShowInSbar()!=null&&carePathTracker.getShowInSbar())
		{
			return carePathTracker.getStepType();
		}
		else
		{
			if(carePathTrackerAttrDao.getNotifyDocterRecords(carePathTracker).size()>0){
				return Constants.CAREPATH_NOTIFY_DOCTOR;
			}
			
		}
		return "None";
	}

	@Transactional
	public List<ChangeInConditionMessageDto> getChangeInConditionDateForEvaluation(
			Long patientEpisodeId) {
		List<ChangeInConditionMessageDto> changeInConditionMessageList = new ArrayList<ChangeInConditionMessageDto>();
		PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
		for (ChangeInConditionDataForEvaluationDto changeInConditionData : changeInConditionDao
				.getChangeInConditionDataForEvaluation(sbarDao.findByPatientEpisode(patientEpisode).getId())) {
			ChangeInConditionMessageDto changeInConditionMessage = new ChangeInConditionMessageDto();
			changeInConditionMessage.setSignsSymptomsLabworkName(changeInConditionData.getSignsSymptomsLabworkName());
			if (changeInConditionData.getSignFlag()) {
				changeInConditionMessage.setAttrType("Vital Sign");
			} else if (changeInConditionData.getSymptomFlag()) {
				changeInConditionMessage.setAttrType("Symptom");
			} else if (changeInConditionData.getLabworkFlag()) {
				changeInConditionMessage.setAttrType("LabWork");
			}
			if (changeInConditionData.getDatatype()!=null&&changeInConditionData.getDatatype().equals(
					Constants.BOOLEAN_DATATYPE)) {
				if (changeInConditionData.getDatatype()!=null&&changeInConditionData.getChangeInConditionValue().equals(
						"true")) {
					if (changeInConditionData.getImmediateFlag()) {
						changeInConditionMessage
								.setMessage(Constants.CIC_NOTIFY_IMMEDIATE);
					} else {
						changeInConditionMessage
								.setMessage(Constants.CIC_NOTIFY_NEXT_DAY);
					}
				}
			} else {
				if (changeInConditionData.getSignFlag()) {
					VitalSignDto vitalSign = new VitalSignDto();
					vitalSign.setMax(changeInConditionData.getMaxVal());
					vitalSign.setMin(changeInConditionData.getMinVal());
					vitalSign.setDatatype(changeInConditionData.getDatatype());
					vitalSign.setValue(changeInConditionData
							.getChangeInConditionValue());
					if (VitalSignUtils.isMet(vitalSign,
							changeInConditionData.getChangeInConditionValue())) {
						if (changeInConditionData.getImmediateFlag()) {
							changeInConditionMessage
									.setMessage(Constants.CIC_NOTIFY_IMMEDIATE);
						} else {
							changeInConditionMessage
									.setMessage(Constants.CIC_NOTIFY_NEXT_DAY);
						}
					}
				} else if (changeInConditionData.getLabworkFlag()) {
					LabWorkDto labWork = new LabWorkDto();
					labWork.setMax(changeInConditionData.getMaxVal());
					labWork.setMin(changeInConditionData.getMinVal());
					labWork.setDatatype(changeInConditionData.getDatatype());
					labWork.setValue(changeInConditionData
							.getChangeInConditionValue());
					if (!LabWorkUtils.isMet(labWork,
							changeInConditionData.getChangeInConditionValue())) {
						if (changeInConditionData.getImmediateFlag()) {
							changeInConditionMessage
									.setMessage(Constants.CIC_NOTIFY_IMMEDIATE);
						} else {
							changeInConditionMessage
									.setMessage(Constants.CIC_NOTIFY_NEXT_DAY);
						}
					}
				}

			}
			if (changeInConditionMessage.getMessage() != null
					&& changeInConditionMessage.getMessage() != "") {
				changeInConditionMessageList.add(changeInConditionMessage);
			}

		}

		return changeInConditionMessageList;
	}
	@Transactional
	public List<Sbar> findByFacilityId(Long facilityId) {
		
		return sbarDao.findByFacilityId(facilityId);
	}

	@Transactional
	public SbarReleventSymptomsDto genrateSbarReleventSymptoms(Long patientEposodeId) {
		SbarReleventSymptomsDto sbarReleventSymptomsDto=null;
		try {
			sbarReleventSymptomsDto=carePathGeneratorService.generateSbarReleventSymptoms();
			sbarReleventSymptomsDto.setPatientEpisodeId(patientEposodeId);
			sbarReleventSymptomsDto=populateSbarReleventSysmtoms(sbarReleventSymptomsDto);
		
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sbarReleventSymptomsDto;
	}
	@Transactional
	private SbarReleventSymptomsDto populateSbarReleventSysmtoms(
			SbarReleventSymptomsDto sbarReleventSymptomsDto) {
		SbarSymptoms sbarSymptom;
		Long patientEpisodeId=sbarReleventSymptomsDto.getPatientEpisodeId();
		 Sbar sbar=sbarDao.findByPatientEpisode(patientEpisodeDao.findById(patientEpisodeId));
		 
		 for(SectionDto sectionDto:sbarReleventSymptomsDto.getSection()){
			  for(SymptomDto symtomDto:sectionDto.getSymptoms().getSymptom()){
				    if(symtomDto.getCarePathAttrCode()!=null){
				    	  if(carePathTrackerAttrDao.findByCareapthCode(symtomDto.getCarePathAttrCode(),sbar.getId()).size()>0){
				    		  symtomDto.setValue("true");
				    	  }
				    }
				    else{
				    	
				      if(sbar!=null){
				    	
				        sbarSymptom=sbarSymptomsDao.findBySbarIdAndCode(sbar.getId(), symtomDto.getSbarAttrCode());
				       if(sbarSymptom!=null){
				        switch(symtomDto.getDatatype()){
				        case "BOOLEAN":
				        	if(sbarSymptom.getBoolValue()!=null&&sbarSymptom.getBoolValue()){
				        	     symtomDto.setValue("true");
				        	}
				        	break;
				        case "VARCHAR":
				        	if(sbarSymptom.getTextValue()!=null){
				        	     symtomDto.setValue(sbarSymptom.getTextValue());
				        	}else
				        	{
				        		symtomDto.setValue("");
				        	}
				        	
				          break;
				        case "DATE":
				        	if(sbarSymptom.getDateValue()!=null){
				        	     symtomDto.setValue(sbarSymptom.getDateValue().toString());
				        	}
				        	else
				        	{
				        		symtomDto.setValue("");
				        	}
				        	break;
				        }
				       }
				    }
				    	
				    }
			  }
			 
		 }
		 return sbarReleventSymptomsDto;
	}
	@Transactional
	public List<String> getStopAndWatchSymtoms(Long patientEposodeId) {
		  List<String> stopAndWatchSymtoms=new ArrayList<String>();
		  Set<String> stopAndWatchSymtomsSet=new HashSet<String>();
		   Sbar sbar = findByPatientEpisodeId(patientEposodeId);
		   StopAndWatch stopAndWatch=null;
		   if(sbar==null){
			   stopAndWatch=stopAndWatchDao.findActiveStopAndWatch(patientEpisodeDao.findById(patientEposodeId));
		   } else{
			   stopAndWatch=stopAndWatchDao.findRecentlyClosedStopAndWatch(patientEpisodeDao.findById(patientEposodeId));
		   }
		   if(stopAndWatch!=null){
		   for(StopAndWatchHistory stopAndWatchHistory:stopAndWatchHistoryDao.findALLStopAndWatchHistorys(stopAndWatch)){
			   
			   stopAndWatchSymtomsSet.addAll(stopAndWatchService.getStopAndWatchSymtomList(stopAndWatchHistory));
		   }
		   stopAndWatchSymtoms.addAll(stopAndWatchSymtomsSet);
		   }
		return stopAndWatchSymtoms;
	}
	
	@Transactional
	public List<SignsSymptomsLabworkAttrDto> getChangeInConditionAttrOnPatient(
			String sign_symptom_Labwork_Type,Long patientEpisodeId) {
		PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
		List<SignsSymptomsLabworkAttrDto>  signsSymptomsLabworkAttrList= signsSymptomsLabworkAttrDao.getChangeInConditionAttrOnPatient(sign_symptom_Labwork_Type, sbarDao.findByPatientEpisode(patientEpisode).getId());
		return signsSymptomsLabworkAttrList;
	}
	@Transactional
	public PdfResponseDto getPdfPatientInfo(Long patientId, Long facilityId) {
		// TODO Auto-generated method stub
		PdfResponseDto pdfResponseDto=patientEpisodeDao.findPdfPatientDetailsByPatientIdAndFacilityId(patientId);
		Facility facility=facilityDao.findById(facilityId);
		pdfResponseDto.setFacilityName(facility.getFacilityName());
		return pdfResponseDto;
	}
	@Transactional
	public void saveprescribeMedicine(PrescribeMedicineDto prescribeMedicineDto) {
		PatientEpisode patientEpisode=patientEpisodeDao.findById(prescribeMedicineDto.getPatientEpisodeId());
		FacilityStaff docterFStaff=facilityStaffDao.findFacilityStaff(patientEpisode.getFacilityId(), prescribeMedicineDto.getStaffId());
		PrescribeMedicine prescribeMedicine=new PrescribeMedicine();
		prescribeMedicine.setFacilityStaffId(docterFStaff.getId());
		prescribeMedicine.setMedicineName(prescribeMedicineDto.getMedicineName());
		prescribeMedicine.setPatientEpisodeId(prescribeMedicineDto.getPatientEpisodeId());
		prescribeMedicineDao.saveOrUpdate(prescribeMedicine);
		
	}
	@Transactional
	public List<PrescribeMedicineDto> getMedicine(Long patientEpisodeId) {
	     return  prescribeMedicineDao.getMedicine(patientEpisodeId);
	}
@Transactional
	public void saveRepotedDoctorInSbar(Long patientEpisodeId, Long fDoctorId,String notes,Long nurseStaffId)throws Exception {
		PatientEpisode patientEpisode = patientEpisodeDao
				.findById(patientEpisodeId);
		Long facilityId=patientEpisode.getFacilityId();
		FacilityStaff repotingFStaff=facilityStaffDao.findFacilityStaff(facilityId, nurseStaffId);
	    String contactInfo=getContactInfoForMail(repotingFStaff);
		Sbar sbar = findByPatientEpisode(patientEpisode);
		FacilityStaff faclityStaf=facilityStaffDao.findById(fDoctorId);
	//	FacilityStaff fnurseId=facilityStaffDao.findFacilityStaff(patientEpisode.getFacilityId(), nurseStaffId);
		if(sbar!=null){
		sbar.setDoctor(faclityStaf.getStaff());
		
		MessageDto messageDto=new MessageDto();
		String prevScenariocode=messageDao.getPrevScenarioCodeByEventId(patientEpisode.getId());
	    messageService.suspendedPreviousMessages(patientEpisode.getId(),prevScenariocode,Constants.MESSAGE);
	    messageService.suspendedPreviousAlerts(patientEpisode.getId(),prevScenariocode,Constants.ALERT);
		List<Long> facilityStaffIds=assignedDocRelationDao.getDocFacilityStaffList(patientEpisode.getPcpId());
		messageDto.setDocList(facilityStaffIds);
		messageDto.setReportedToDocter(faclityStaf.getId());
		messageDto.setFacilityId(patientEpisode.getFacilityId());
		messageDto.setPatientEpisodeId(patientEpisodeId);
		messageDto.setPatientId(patientEpisode.getPatient().getId());
		messageDto.setMsgSubj(Constants.OBSERVATION_SUBJECT);
		messageDto.setPrevScenarioCode(sbar.getMsgScenariocode());
		messageDto.setCurrentScenarioCode(Constants.NURSE_NOTIFY_DOC);
		sbar.setMsgScenariocode(Constants.NURSE_NOTIFY_DOC);
		sbarDao.saveOrUpdate(sbar);
		messageDto.setEventId(sbar.getId());
		messageDto.setDeleteFlag(false);
		messageDto.setReadFlag(false);
		messageDto.setSentDate(new Date());
		messageDto.setContactInfo(contactInfo);
		messageService.saveMessages(messageDto);
		messageService.saveAlerts(messageDto);
		SbarNotesDto sbarNotesDto = new SbarNotesDto();
		sbarNotesDto.setNotes(notes);
		sbarNotesDto.setPatientEpisodeId(patientEpisodeId);
		sbarNotesDto.setNursingStaffId(nurseStaffId);
		sbarNotesDto.setFacilityId(faclityStaf.getFacility().getId());
		saveSbarNotes(sbarNotesDto);
		}else{
			throw new Exception();
		}
		
	}
	private String getContactInfoForMail(FacilityStaff repotingFStaff) {
	   String contactInfo="";
	   String role=repotingFStaff.getStaffRoles().getRoleName();
	  if(role.equals(Constants.LPN_ROLE)||role.equals(Constants.RN_ROLE)){
		  contactInfo+="Reported Nurse ";
	  }
	  if(role.equals(Constants.MD_ROLE)||role.equals(Constants.PA_ROLE)||role.equals(Constants.NP_ROLE)){
		  contactInfo+="Doctor ";
	  }
	  contactInfo+="Contact number is : ";
	  contactInfo+=repotingFStaff.getWorkNumber();
		return contactInfo;
}

	@Transactional
	public void moveToManagingFacility(Long patientEpisodeId,LoggedUserModel user)throws Exception {
		PatientEpisode patientEpisode = patientEpisodeDao
				.findById(patientEpisodeId);
		Sbar sbar = findByPatientEpisode(patientEpisode);
		if(sbar!=null){
			
			sbar.setManageInFacilityFlag(true);
			sbar.setUserModified(user.getFullName());
			sbar.setDateModified(new Date());
			String reason="Doctor decision is manage in facility";
		    encounterColse(patientEpisode,user.getFullName(),reason);
		    MessageDto messageDto=new MessageDto();
			String prevScenariocode=messageDao.getPrevScenarioCodeByEventId(patientEpisode.getId());
		    messageService.suspendedPreviousMessages(patientEpisode.getId(),prevScenariocode,Constants.MESSAGE);
		    messageService.suspendedPreviousAlerts(patientEpisode.getId(),prevScenariocode,Constants.ALERT);
		    
			messageDto.setReportedToDocter(user.getFstaffId());
			messageDto.setFacilityId(patientEpisode.getFacilityId());
			messageDto.setPatientEpisodeId(patientEpisodeId);
			messageDto.setPatientId(patientEpisode.getPatient().getId());
			messageDto.setMsgSubj(Constants.MANG_FACILITY_SUBJECT);
			messageDto.setPrevScenarioCode(sbar.getMsgScenariocode());
			messageDto.setCurrentScenarioCode(Constants.PATIENT_TRANSFER_MANGFACILITY);
			sbar.setMsgScenariocode(Constants.PATIENT_TRANSFER_MANGFACILITY);
			sbarDao.saveOrUpdate(sbar);
			messageDto.setEventId(sbar.getId());
			messageDto.setDeleteFlag(false);
			messageDto.setReadFlag(false);
			messageDto.setSentDate(new Date());
			messageService.saveMessages(messageDto);
			messageService.saveAlerts(messageDto);
			
			
			}else{
				throw new Exception();
			}
		
	}
	@Transactional
	public void moveToAdmisionFromCA(Long patientEpisodeId,LoggedUserModel user)throws Exception {
		PatientEpisode patientEpisode = patientEpisodeDao
				.findById(patientEpisodeId);
		Sbar sbar = findByPatientEpisode(patientEpisode);
		if(sbar!=null){
			
			sbar.setPatientRecoverdFlag(true);
			sbar.setUserModified(user.getFullName());
			sbar.setDateModified(new Date());
			String reason="Doctor decision is patient is recoverd";
		    encounterColse(patientEpisode,user.getFullName(),reason);
		    MessageDto messageDto=new MessageDto();
			String prevScenariocode=messageDao.getPrevScenarioCodeByEventId(patientEpisode.getId());
		    messageService.suspendedPreviousMessages(patientEpisode.getId(),prevScenariocode,Constants.MESSAGE);
		    messageService.suspendedPreviousAlerts(patientEpisode.getId(),prevScenariocode,Constants.ALERT);
		    
			messageDto.setReportedToDocter(user.getFstaffId());
			messageDto.setFacilityId(patientEpisode.getFacilityId());
			messageDto.setPatientEpisodeId(patientEpisodeId);
			messageDto.setPatientId(patientEpisode.getPatient().getId());
			messageDto.setMsgSubj(Constants.PATIENT_RECOVERD_SUBJECT);
			messageDto.setPrevScenarioCode(sbar.getMsgScenariocode());
			messageDto.setCurrentScenarioCode(Constants.PATIENT_RECOVERD_TRANSFER);
			sbar.setMsgScenariocode(Constants.PATIENT_RECOVERD_TRANSFER);
			sbarDao.saveOrUpdate(sbar);
			messageDto.setEventId(sbar.getId());
			messageDto.setDeleteFlag(false);
			messageDto.setReadFlag(false);
			messageDto.setSentDate(new Date());
			messageService.saveMessages(messageDto);
			messageService.saveAlerts(messageDto);
			
			
			}else{
				throw new Exception();
			}
		
	}
@Transactional
public PrescribeMedicine  updateMedicine(Long prescribeMedicineId,String medicineName) {
	PrescribeMedicine prescribeMedicine=prescribeMedicineDao.findById(prescribeMedicineId);
   if(prescribeMedicine!=null){
	   prescribeMedicine.setMedicineName(medicineName);
	   prescribeMedicineDao.saveOrUpdate(prescribeMedicine);
	}
   
	return prescribeMedicine;
}
	@Transactional 
	public void encounterColse(PatientEpisode patientEpisode,String staffFullName,String reason) {
		patientEpisode.setSbarRised(false);
		patientEpisodeDao.saveOrUpdate(patientEpisode);
		PatientEncounter patientEncounter=patientEncounterDao.findActiveEncounter(patientEpisode.getId());
		if(patientEncounter!=null)
		{
			patientEncounter.setEndDate(new Date());
			patientEncounter.setReason(reason);
			patientEncounter.setUserModified(staffFullName);
			patientEncounter.setDateModified(new Date());
		}
		
	}
	@Transactional
	public void moveToAcuteCare(Long patientEpisodeId,String plannedDetails, FacilityStaff fStaff) throws Exception {
		
		PatientEpisode patientEpisode = patientEpisodeDao
				.findById(patientEpisodeId);
		Sbar sbar = findByPatientEpisode(patientEpisode);
		if(sbar!=null){
			sbar.setTransferToHospitalFlag(true);
			sbar.setUserModified(PatientUtils.getPatientFullName(fStaff.getStaff().getFirstName(),fStaff.getStaff().getLastName()));
			sbar.setDateModified(new Date());
		    intialAcuteCareTranfer(patientEpisode,fStaff);
			MessageDto messageDto=new MessageDto();
			String prevScenariocode=messageDao.getPrevScenarioCodeByEventId(patientEpisode.getId());
		    messageService.suspendedPreviousMessages(patientEpisode.getId(),prevScenariocode,Constants.MESSAGE);
		    messageService.suspendedPreviousAlerts(patientEpisode.getId(),prevScenariocode,Constants.ALERT);
		    
		     messageDto.setReportedToDocter(fStaff.getId());
		     messageDto.setFacilityId(patientEpisode.getFacilityId());
		     messageDto.setPatientEpisodeId(patientEpisodeId);
		     messageDto.setPatientId(patientEpisode.getPatient().getId());
		     messageDto.setMsgSubj(Constants.ACUTECARE_SUBJECT);
		     messageDto.setCurrentScenarioCode(Constants.PATIENT_TRANSFER_ACUTECARE);
		     messageDto.setPrevScenarioCode(sbar.getMsgScenariocode());
		     sbar.setMsgScenariocode(Constants.PATIENT_TRANSFER_ACUTECARE);
		     sbar.setPlannedDetails(plannedDetails);
		     sbarDao.saveOrUpdate(sbar);
		   
		     messageDto.setEventId(sbar.getId());
		     messageDto.setDeleteFlag(false);
		     messageDto.setReadFlag(false);
		     messageDto.setSentDate(new Date());
		     messageService.saveMessages(messageDto);
		     messageService.saveAlerts(messageDto);
			
			}else{
				throw new Exception();
			}
	}

	@Transactional
	private void intialAcuteCareTranfer(PatientEpisode patientEpisode,FacilityStaff fStaff) {
		
		AcuteCare acuteCare=new AcuteCare();
		acuteCare.setPatientEpisodeId(patientEpisode.getId());
		acuteCareDao.saveOrUpdate(acuteCare);
	}
	@Transactional
	public Long removeMedicine(Long prescribeMedicineId) {
		return prescribeMedicineDao.removeMedicine(prescribeMedicineId);
		
	     }
	@Transactional
	public Sbar getActiveSbar(PatientEpisode patientEpisode) {
		return sbarDao.findByPatientEpisode(patientEpisode);
	}
	@Transactional
	public List<PatientEncounterDto> findAllEncounterss(Long patientEpisodeId) {
		 
		return patientEncounterDao.findAllEncounters(patientEpisodeId);
	}
	
	@Transactional
	public Long getSbarIdbyEncounterId(Long encounterId){
		PatientEncounter patientEncounter=patientEncounterDao.findById(encounterId);
		Long sbarId= sbarDao.findSbarIdbyEncounter(patientEncounter);
		 return sbarId;
	}


}

