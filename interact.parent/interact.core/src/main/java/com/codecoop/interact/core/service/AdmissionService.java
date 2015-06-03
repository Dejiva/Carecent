package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.dao.AdmissionAttributesValuesDao;
import com.codecoop.interact.core.dao.AssignedDocRelationDaoImpl;
import com.codecoop.interact.core.dao.EthnicityDaoImpl;
import com.codecoop.interact.core.dao.FacilityStaffDaoImpl;
import com.codecoop.interact.core.dao.GenericDao;
import com.codecoop.interact.core.dao.GuardianDao;
import com.codecoop.interact.core.dao.HospitalDaoImpl;
import com.codecoop.interact.core.dao.HospitalStaffDao;
import com.codecoop.interact.core.dao.HsptlStaffSpecialityDao;
import com.codecoop.interact.core.dao.HsptlStaffTypeDao;
import com.codecoop.interact.core.dao.MedicalReconciliationDao;
import com.codecoop.interact.core.dao.PatientDaoImpl;
import com.codecoop.interact.core.dao.PatientEncounterDaoImpl;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.domain.Ethnicity;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.Guardian;
import com.codecoop.interact.core.domain.GuardianContact;
import com.codecoop.interact.core.domain.Hospital;
import com.codecoop.interact.core.domain.HospitalStaff;
import com.codecoop.interact.core.domain.HsptlStaffSpeciality;
import com.codecoop.interact.core.domain.MedicalReconciliation;
import com.codecoop.interact.core.domain.MedicineResolution;
import com.codecoop.interact.core.domain.PLanguage;
import com.codecoop.interact.core.domain.Patient;
import com.codecoop.interact.core.domain.PatientEncounter;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.dto.AdmissionDto;
import com.codecoop.interact.core.dto.GuardianDto;
import com.codecoop.interact.core.dto.HospitalDto;
import com.codecoop.interact.core.dto.HospitalStaffDto;
import com.codecoop.interact.core.dto.HospitalStaffSpecialityDto;
import com.codecoop.interact.core.dto.HsptlPhyCareTeam;
import com.codecoop.interact.core.dto.MedicalReconciliationDto;
import com.codecoop.interact.core.dto.MessageDto;
import com.codecoop.interact.core.dto.PatientDto;
import com.codecoop.interact.core.dto.PatientEpisodeDto;
import com.codecoop.interact.core.dto.SSNCheckDto;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.core.util.CoreDateUtils;
import com.codecoop.interact.core.util.PatientUtils;

@Service
public class AdmissionService{

	private static final Logger LOG = Logger.getLogger(AdmissionService.class);



	@Autowired
	PatientEpisodeDao patientEpisodeDao;

	@Autowired
	PatientDaoImpl patientDao;

	@Autowired
	HospitalDaoImpl hospitalDao;

	@Autowired
	GuardianDao guardianDao;

	@Autowired
	EthnicityDaoImpl ethnicityDao;

	@Autowired
	GenericDao genericDao;

	@Autowired
	PatientEncounterDaoImpl patientEncounterDao;
	
	@Autowired
	HospitalStaffDao hospitalStaffDao;

	@Autowired
	AdmissionAttributesValuesDao admissionAttributesValuesDao;

	@Autowired
	HsptlStaffSpecialityDao hsptlStaffSpecialityDao;

	@Autowired
	HsptlStaffTypeDao hsptlStaffTypeDao;

	@Autowired
	MedicalReconciliationDao medicalReconciliationDao;

	@Autowired
	MessageService messageService;
	@Autowired
	FacilityStaffDaoImpl facilityStaffDao;
	@Autowired
	AssignedDocRelationDaoImpl assignedDocRelationDao;
	@Transactional
	public List<HospitalStaffSpecialityDto> getAllSpecialities(){

		List<HospitalStaffSpecialityDto> specialityList = new ArrayList<HospitalStaffSpecialityDto>();

		for(HsptlStaffSpeciality specialities: hsptlStaffSpecialityDao.findAll()){
			HospitalStaffSpecialityDto speciality= new HospitalStaffSpecialityDto();
			speciality.setId(specialities.getId());
			speciality.setSpecialityName(specialities.getSpecialityName());
			specialityList.add(speciality);
		}
		return specialityList;

	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void admitPatient(PatientEpisodeDto patientEpisodeDto){
		boolean editAdmistion;
		Long lastAsignedPcpId=null;
		Date presentDate = new Date();
		PatientDto patientDto = patientEpisodeDto.getPatient();
		String userId = patientEpisodeDto.getUserId();
		Long ethnicityId = getEthnicityIdByType(patientDto.getEthnicityType());
		PatientEpisode patientEpisode = new PatientEpisode();

		Patient patient=null;
		if(patientDto.getPatientId() !=null){
			patient =patientDao.findById(patientDto.getPatientId());
			patient.setPatientFirstName(patientDto.getPatientFirstName());
			patient.setPatientLastName(patientDto.getPatientLastName());
			patient.setDob(patientDto.getDob());
			patient.setsSNumber(patientDto.getsSNumber());
			patient.setGender(patientDto.getGender());
			patient.setBloodgroup(patientDto.getBloodgroup());
			patient.setDateCreated(presentDate);
			patient.setUserModified(userId);
			patient.setEthnicityId(ethnicityId);
			patient.setEthnicityUd(patientDto.getEthnicityUd());

			for(PLanguage pLanguage:patient.getpLanguages()){

				pLanguage.setPatient(patient);
				pLanguage.setLanguageName(patientDto.getLanguage());
				pLanguage.setPrimaryFlag(true);
				pLanguage.setDateModified(presentDate);
				pLanguage.setUserModified(userId);
				patient.getpLanguages().add(pLanguage);
			}

			if( patient.getpLanguages().size() == 0 && !StringUtils.isEmpty(patientDto.getLanguage())){
				PLanguage planguge=new PLanguage(patient, patientDto.getLanguage(), true, presentDate, userId, presentDate, userId);
				patient.getpLanguages().add(planguge);
			}

		}else{
			patient = new Patient(patientDto.getPatientFirstName(),patientDto.getPatientLastName(), patientDto.getDob(), patientDto.getGender(), 
					patientDto.getBloodgroup(), presentDate,	userId,  userId);
			patient.setEthnicityId(ethnicityId);
			patient.setEthnicityUd(patientDto.getEthnicityUd());
			patient.setsSNumber(patientDto.getsSNumber());
			PLanguage planguge=new PLanguage(patient, patientDto.getLanguage(), true, presentDate, userId, presentDate, userId);
			patient.getpLanguages().add(planguge);
		}

		patientDao.saveOrUpdate(patient);
		Long patientId = patient.getId();
		patientDto.setPatientId(patientId);
		GuardianDto careGiver = patientDto.getCareGiver();
		if(careGiver != null){

			Guardian guardian = new Guardian();
			if(careGiver.getGuardianId() !=null){
				guardian = guardianDao.findById(careGiver.getGuardianId());
			}
			guardian.setGuardianName(careGiver.getGuardianName());
			guardian.setPatientId(patientId);
			guardian.setRelationToPatient("FAMILY_OR_CARE_GIVER");
			guardian.setUserCreated(userId);
			guardian.setUserModified(userId);
			guardian.setDateCreated(presentDate);
			guardian.setDateModified(presentDate);
			GuardianContact contact = new GuardianContact();
			if(!StringUtils.isEmpty( guardian.getGuardianContacts())){
				for(GuardianContact cont:guardian.getGuardianContacts()){
					contact=cont;
				}
			}
			contact.setContact(careGiver.getContactNumber());
			contact.setContactType("TELEPHONE");
			contact.setPrimaryFlag(true);
			contact.setGuardian(guardian);
			contact.setUserCreated(userId);
			contact.setUserModified(userId);
			contact.setDateCreated(presentDate);
			contact.setDateModified(presentDate);

			guardian.getGuardianContacts().add(contact);
			//			}
			guardianDao.saveOrUpdate(guardian);
		}

		GuardianDto guardianOrProxy = patientDto.getGuardianOrProxy();
		Long guardianId = null;
		if(guardianOrProxy != null){
			Guardian guardian = new Guardian();
			if(guardianOrProxy.getGuardianId() !=null){
				guardian=guardianDao.findById(guardianOrProxy.getGuardianId());
			}
			guardian.setGuardianName(guardianOrProxy.getGuardianName());
			guardian.setPatientId(patientId);
			guardian.setRelationToPatient("GUARDIAN_OR_PROXY_CONTACT");
			guardian.setUserCreated(userId);
			guardian.setUserModified(userId);
			guardian.setDateCreated(presentDate);
			guardian.setDateModified(presentDate);
			GuardianContact contact = new GuardianContact();
			if(!StringUtils.isEmpty( guardian.getGuardianContacts())){
				for(GuardianContact cont:guardian.getGuardianContacts()){
					contact=cont;
				}
			}
			contact.setContact(guardianOrProxy.getContactNumber());
			contact.setContactType("TELEPHONE");
			contact.setPrimaryFlag(true);
			contact.setGuardian(guardian);
			contact.setUserCreated(userId);
			contact.setUserModified(userId);
			contact.setDateCreated(presentDate);
			contact.setDateModified(presentDate);

			guardian.getGuardianContacts().add(contact);
			//			}
			guardianDao.saveOrUpdate(guardian);
			guardianId = guardian.getId();
		}

		HospitalDto fromHospitalDto = patientEpisodeDto.getFromHospital();
		Long fromHospitalId = fromHospitalDto.getHospitalId();
		if(!StringUtils.isEmpty(fromHospitalDto)){
			Hospital hospital = new Hospital();
			if(fromHospitalId !=null){
				hospital = hospitalDao.findById(fromHospitalId);
			}
			hospital.setHospitalName(fromHospitalDto.getHospitalName());
			hospital.setUserCreated(userId);
			hospital.setUserModified(userId);
			hospital.setDateCreated(presentDate);
			hospital.setDateModified(presentDate);
			genericDao.saveOrUpdate(hospital); 
			fromHospitalId = hospital.getId();			
		}

		if(patientEpisodeDto.getPatientEpisodeId() != null){
			patientEpisode = patientEpisodeDao.findById(patientEpisodeDto.getPatientEpisodeId());
			editAdmistion=true;
			lastAsignedPcpId=patientEpisode.getPcpId();
		}
		else
		{
			editAdmistion=false;
			patientEpisode.setSbarRised(false);

		}
		MessageDto messageDto=new MessageDto();
		patientEpisode.setAdmittedByStaffId(patientEpisodeDto.getAdmittedByStaffId());
		patientEpisode.setBedNo(patientEpisodeDto.getBedNo());
		patientEpisode.setRoomNumber(patientEpisodeDto.getRoomNumber());
		patientEpisode.setComfortCare(patientEpisodeDto.getComfortCare());
		patientEpisode.setHospiceCare(patientEpisodeDto.getHospiceCare());
		patientEpisode.setDnh(patientEpisodeDto.getDnh());
		patientEpisode.setDni(patientEpisodeDto.getDni());
		patientEpisode.setDnr(patientEpisodeDto.getDnr());
		patientEpisode.setPatientInsurencePlan(patientEpisodeDto.getPatientInsurencePlan());
		patientEpisode.setFacilityAdmissionDate(patientEpisodeDto.getFacilityAdmissionDate());
		patientEpisode.setPcpId(patientEpisodeDto.getPcpAtSite());
		patientEpisode.setFacilityDischargeDate(patientEpisodeDto.getFacilityDischargeDate());
		patientEpisode.setFacilityId(patientEpisodeDto.getFacilityId());
		patientEpisode.setFacilityStayType(patientEpisodeDto.getFacilityStayType());
		patientEpisode.setFollowUpTests(patientEpisodeDto.getFollowUpTests());
/*		patientEpisode.setFormHospitalUnit(patientEpisodeDto.getFromHospitalUnit()); commented on 06-05-2015
		patientEpisode.setFromHospitalId(fromHospitalId);
		patientEpisode.setFromHsptalAdmissionDate(patientEpisodeDto.getFromHospitalAdmissionDate());
		patientEpisode.setFromHsptalDischargeDate(patientEpisodeDto.getFromHospitalDischargeDate());*/
		patientEpisode.setFullCode(patientEpisodeDto.getFullCode());
		patientEpisode.setGoalsCareDiscussionText(patientEpisodeDto.getGoalsCareDiscussionText());
		patientEpisode.setGuardianId(guardianId);
		patientEpisode.setHospitalNurseVerbalReport(patientEpisodeDto.getHospitalNurseVerbalReport());
		//		patientEpisode.setHsptalDischargeDoctorId(doctorHospitalStaffId);				//need modification
		//		patientEpisode.setHsptalDischargeNurseId(nurseHospitalStaffId);					//need modificaiton
		patientEpisode.setHsptalPriorityCareNeeds(patientEpisodeDto.getHospitalPriorityCareNeeds());
		patientEpisode.setKeyFindings(patientEpisodeDto.getKeyFindings());
		patientEpisode.setListProcedures(patientEpisodeDto.getListProcedures());
		patientEpisode.setNoArtificialFeeding(patientEpisodeDto.getNoArtificialFeeding());
		patientEpisode.setOtherCare(patientEpisodeDto.getOtherCare());
		patientEpisode.setPendingLabResult(patientEpisodeDto.getPendingLabResult());
		patientEpisode.setToHospitalId(null);
		patientEpisode.setToHsptalUnit(patientEpisodeDto.getToHospitalUnit());
		patientEpisode.setPatientCareTypes(patientEpisodeDto.getPatientCareTypes());
		patientEpisode.setGoalsOfCareDiscussed(patientEpisodeDto.getGoalsOfCareDiscussed());
		patientEpisode.setCanTakeDecision(patientEpisodeDto.getCanTakeDecision());
		patientEpisode.setReqProxy(patientEpisodeDto.getReqProxy());
		patientEpisode.setNurseToNurseVerbalReport(patientEpisodeDto.getNurseToNurseVerbalReport());
		patientEpisode.setUserCreated(userId);
		patientEpisode.setUserModified(userId);
		patientEpisode.setDateCreated(presentDate);
		patientEpisode.setDateModified(presentDate);
		patientEpisode.setStopAndWatchEligible(patientEpisodeDto.getStopAndWatchEligiblity());
		patientEpisode.setPatient(patient);	
		messageDto.setFacilityId(patientEpisodeDto.getFacilityId());
		patientEpisodeDao.saveOrUpdate(patientEpisode);

		if((!editAdmistion&&patientEpisode.getPcpId()!=null)||(editAdmistion&&lastAsignedPcpId!=patientEpisode.getPcpId())){
			messageDto.setMsgSubj(Constants.ADMISSION_SUBJECT);
			messageDto.setReadFlag(false);
			messageDto.setSentDate(new Date());
			messageDto.setPrevScenarioCode(patientEpisode.getMsgScenarioCode());
			String msgScenarioCode=Constants.PATIENT_ADMISSION_ASSIGNED_DOC;
			List<Long> facilityStaffIds=assignedDocRelationDao.getDocFacilityStaffList(patientEpisode.getPcpId());
			messageDto.setDocList(facilityStaffIds);
			FacilityStaff docfacilityStaff=facilityStaffDao.findById(patientEpisode.getPcpId());
			messageDto.setReportedToDocter(docfacilityStaff.getId());
			messageDto.setPatientId(patientEpisode.getPatient().getId());
			messageDto.setPrevScenarioCode(patientEpisode.getMsgScenarioCode());
			messageDto.setCurrentScenarioCode(Constants.PATIENT_ADMISSION_ASSIGNED_DOC);
			patientEpisode.setMsgScenarioCode(msgScenarioCode);
			patientEpisodeDao.saveOrUpdate(patientEpisode);
			messageDto.setPatientEpisodeId(patientEpisode.getId());
			messageDto.setEventId(patientEpisode.getId());
			messageService.saveMessages(messageDto);
			try {
				messageService.saveAlerts(messageDto);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		
		Long patientEpisodeId = patientEpisode.getId();
		PatientEncounter patientEncounter=patientEncounterDao.findActiveEncounter(patientEpisodeId);
		if(patientEncounter==null){
			int encounterNum=0;
			patientEncounter=patientEncounterDao.findLastEcounterNumber(patientEpisodeId);
			if(patientEncounter!=null){
				encounterNum=patientEncounter.getEncounterNum();
			}
	    	patientEncounter=new PatientEncounter(patientEpisode, encounterNum+1, presentDate, null, null, presentDate, userId, null, null,fromHospitalId,patientEpisodeDto.getFromHospitalAdmissionDate(),patientEpisodeDto.getFromHospitalDischargeDate(),patientEpisodeDto.getFromHospitalUnit());
		}
		patientEncounter.setFromHsptalAdmissionDate(patientEpisodeDto.getFromHospitalAdmissionDate());
		patientEncounter.setFromHsptalDischargeDate(patientEpisodeDto.getFromHospitalDischargeDate());
		patientEncounter.setFormHospitalUnit(patientEpisodeDto.getFromHospitalUnit());
		patientEncounterDao.saveOrUpdate(patientEncounter);
		Long patientEncounterId = patientEncounter.getId();
		HospitalStaffDto hospitalDischargeNurse = patientEpisodeDto.getHospitalDischargeNurse();
		Long nurseHospitalStaffId = hospitalDischargeNurse.getHospitalStaffId();
		if(!StringUtils.isEmpty(hospitalDischargeNurse)){
			HospitalStaff staff = new HospitalStaff();
			if(nurseHospitalStaffId != null){
				staff = hospitalStaffDao.findById(nurseHospitalStaffId);
			}
			staff.setStaffName(hospitalDischargeNurse.getStaffName());
			staff.setHsptlStaffTypeId(2L);
			staff.setSpecialityId(0L);
			staff.setUserCreated(userId);
			staff.setUserModified(userId);
			staff.setDateCreated(presentDate);
			staff.setDateModified(presentDate);
			try{
				if(hospitalDischargeNurse.getContactNumber() !="" || hospitalDischargeNurse.getContactNumber() != null ){
					staff.setContactno(hospitalDischargeNurse.getContactNumber());
				}}catch(Exception e){e.printStackTrace();}
			staff.setEmailid(hospitalDischargeNurse.getEmailId());
			staff.setPatientEncounterId(patientEncounterId);
			genericDao.saveOrUpdate(staff);
			nurseHospitalStaffId = staff.getId();
		}

		HospitalStaffDto hospitalDischargeDoctor = patientEpisodeDto.getHospitalDischargeDoctor();
		Long doctorHospitalStaffId = hospitalDischargeDoctor.getHospitalStaffId();
		if(!StringUtils.isEmpty(hospitalDischargeDoctor)){
			HospitalStaff staff = new HospitalStaff();
			if(doctorHospitalStaffId != null){
				staff = hospitalStaffDao.findById(hospitalDischargeDoctor.getHospitalStaffId());
			}
			staff.setStaffName(hospitalDischargeDoctor.getStaffName());
			staff.setHsptlStaffTypeId(1L);
			staff.setSpecialityId(0L);
			try{
				if(hospitalDischargeDoctor.getContactNumber() != null){
					staff.setContactno(hospitalDischargeDoctor.getContactNumber());
				}}catch(Exception e){e.printStackTrace();}
			staff.setEmailid(hospitalDischargeDoctor.getEmailId());
			staff.setPatientEncounterId(patientEncounterId);
			genericDao.saveOrUpdate(staff);	
			doctorHospitalStaffId = staff.getId();
		}

		List<HospitalStaffDto> physicianCareTeamListDto =patientEpisodeDto.getPhysicianCareTeamListDto();


		if(!CollectionUtils.isEmpty(physicianCareTeamListDto)){
			for(HospitalStaffDto phycareTeamDto:physicianCareTeamListDto){

				HospitalStaff hsptlStaff=new HospitalStaff();
				Long hsptlStaffId = phycareTeamDto.getHospitalStaffId();

				if( hsptlStaffId != null){
					hsptlStaff = hospitalStaffDao.findById(hsptlStaffId);
				}
				try{
					hsptlStaff.setStaffName(phycareTeamDto.getStaffName());
					if(phycareTeamDto.getContactNumber() !="" || phycareTeamDto.getContactNumber() != null )
						hsptlStaff.setContactno(phycareTeamDto.getContactNumber());
					hsptlStaff.setHsptlStaffTypeId(1L);	
					hsptlStaff.setSpecialityId(phycareTeamDto.getSpecialityId());
					hsptlStaff.setPatientEncounterId(patientEncounterId);
					hsptlStaff.setUserCreated(userId);
					hsptlStaff.setDateCreated(presentDate);
					hospitalStaffDao.saveOrUpdate(hsptlStaff);	//TODO:	check saving
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		patientEpisodeDto.setPatientEncounterId(patientEncounterId);
		patientEpisodeDto.setPatientEpisodeId(patientEpisodeId);
	}

	private Long getEthnicityIdByType(String ethnicityType) {
		Ethnicity ethnicity = new Ethnicity();
		ethnicity.setEthnicity_type(ethnicityType);

		List<Ethnicity> ethnicityList = genericDao.findByExample(ethnicity);

		return CollectionUtils.isEmpty(ethnicityList)?null : ethnicityList.get(0).getId();
	}

	@Transactional
	public void saveMedicalReconciliation(List<MedicalReconciliationDto> reconcileDtoList, String userId){
		Date presentDate = new Date();
		if(!CollectionUtils.isEmpty(reconcileDtoList)){
			for(MedicalReconciliationDto dto : reconcileDtoList){

				MedicalReconciliation reconcile = new MedicalReconciliation();
				if(dto.getMedicalReconciliationId() != null){
					reconcile =  medicalReconciliationDao.findById(dto.getMedicalReconciliationId());
				}
				if(!StringUtils.isEmpty(dto.getTobeDeleted())){
					medicalReconciliationDao.delete(reconcile);
				}else{
					reconcile.setPatientEncounterId(dto.getPatientEncounterId());
					reconcile.setMedicineDetail(dto.getMedicationDetails());
					if(!StringUtils.isEmpty(dto.getClarificationNeeded())){
						reconcile.setClarificationNeeded(dto.getClarificationNeeded());
					}
					if(!StringUtils.isEmpty(dto.getComments())){
						reconcile.setComments(dto.getComments());
					}
					reconcile.setMedicineRecommendation(dto.getRecommendation());
					reconcile.setMedicineResolutionId(dto.getResolutionId());
					reconcile.setDateCreated(presentDate);
					reconcile.setDateModified(presentDate);
					reconcile.setUserCreated(userId);
					reconcile.setUserModified(userId);
					genericDao.saveOrUpdate(reconcile);
					dto.setMedicalReconciliationId(reconcile.getId());
				}
			}
		}
	}


	public Map<Long, String> getMedicineResolutionTypes(){
		List<MedicineResolution> resolutionTypes = 
				genericDao.findAll(MedicineResolution.class);
		Map<Long, String> resolutionTypesMap = new HashMap<Long, String>();
		if(!CollectionUtils.isEmpty(resolutionTypesMap)){
			for(MedicineResolution resolution : resolutionTypes){
				resolutionTypesMap.put(resolution.getId(), resolution.getResolutionType());
			}
		}
		return resolutionTypesMap;
	}

	@Transactional
	public List<MedicalReconciliationDto> getMedicalReconcilationDetailsByEncounterId(Long patientEncounterId){
		MedicalReconciliation reconcile = new MedicalReconciliation();
		reconcile.setPatientEncounterId(patientEncounterId);
		List<MedicalReconciliation> medicalReconciliations = genericDao.findByExample(reconcile);
		List<MedicalReconciliationDto> dtoList = new ArrayList<MedicalReconciliationDto>();

		if(!CollectionUtils.isEmpty(medicalReconciliations)){
			Map<Long, String> resolutionTypesMap = getMedicineResolutionTypes();

			for(MedicalReconciliation medReconcile : medicalReconciliations){
				MedicalReconciliationDto dto = new MedicalReconciliationDto();
				dto.setMedicalReconciliationId(medReconcile.getId());
				dto.setMedicationDetails(medReconcile.getMedicineDetail());
				dto.setClarificationNeeded(medReconcile.getClarificationNeeded());
				dto.setComments(medReconcile.getComments());
				dto.setPatientEncounterId(medReconcile.getPatientEncounterId());
				dto.setRecommendation(medReconcile.getMedicineRecommendation());
				dto.setResolutionId(medReconcile.getMedicineResolutionId());
				dto.setResolution(resolutionTypesMap.get(medReconcile.getMedicineResolutionId()));
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Transactional
	public AdmissionDto getAdmissionForm(AdmissionDto aFormDto,Long facilityId,Boolean ssnCheck){
		AdmissionDto admissionDto=new AdmissionDto();
		Patient patient=null;
		if(!StringUtils.isEmpty(aFormDto.getPatientId())){
			patient = patientDao.findById(aFormDto.getPatientId());
		}else {
			patient = patientDao.findPatientBySSNumber(aFormDto.getsSNumber());	
		}
		if(!StringUtils.isEmpty(patient)){
			PatientEpisode pAEpisode = patientEpisodeDao.findActiveEpisodeByPatient(patient,facilityId);
			if(!StringUtils.isEmpty(pAEpisode) && pAEpisode.getPcpId() != null){
				FacilityStaff fstaff = facilityStaffDao.findById(pAEpisode.getPcpId());
				if(fstaff!=null){
					String staffFullName  = PatientUtils.getPatientFullName(fstaff.getStaff().getFirstName(), fstaff.getStaff().getLastName());
					if(!StringUtils.isEmpty(staffFullName)){
						admissionDto.setPcpAtSiteName(staffFullName);
					}
					if(fstaff.getWorkNumber() !=null){
						admissionDto.setPcpWorkNumber(fstaff.getWorkNumber());
					}
					admissionDto.setPcpDesig(fstaff.getStaffRoles().getDescription());
				}
			}
			admissionDto.setPatientId(patient.getId());
			admissionDto.setPatientFirstName(patient.getPatientFirstName());
			admissionDto.setPatientLastName(patient.getPatientLastName());
			admissionDto.setsSNumber(patient.getsSNumber());
			admissionDto.setDob(CoreDateUtils.parseStringDate(patient.getDob(), "MM/dd/yyyy"));
			admissionDto.setPatientGender(patient.getGender());
			admissionDto.setOtherEthnicityType(patient.getEthnicityUd());

			Set<PLanguage> pLang=patient.getpLanguages();

			for (PLanguage lang : pLang) {
				if (lang.getLanguageName().equalsIgnoreCase("ENGLISH")) {
					admissionDto.setCanSpeakEnglish(true);
				} else {
					admissionDto.setOtherLanguage(lang.getLanguageName());
				}
			}

			if(patient.getEthnicityId() !=null){
				Ethnicity ethnicity=ethnicityDao.findById(patient.getEthnicityId());
				admissionDto.setEthnicityType(ethnicity.getEthnicity_type());
			}

			Set<Guardian> guardians=patient.getGurdianList();

			for(Guardian guardian:guardians){

				if(guardian.getRelationToPatient().equalsIgnoreCase("GUARDIAN_OR_PROXY_CONTACT")){
					admissionDto.setgProxyId(guardian.getId());
					admissionDto.setGuardianName(guardian.getGuardianName());
					Set<GuardianContact> gContact=guardian.getGuardianContacts();
					for(GuardianContact cont:gContact){
						admissionDto.setGuardianTelephoneNumber(cont.getContact());
					}

				}
				if(guardian.getRelationToPatient().equalsIgnoreCase("FAMILY_OR_CARE_GIVER")){
					admissionDto.setgCareGiverId(guardian.getId());
					admissionDto.setCaregiverName(guardian.getGuardianName());
					Set<GuardianContact> gContact=guardian.getGuardianContacts();
					for(GuardianContact cont:gContact){
						admissionDto.setCareGiverTelephoneNumber(cont.getContact());
					}

				}

			}
			/** replaced list of patientEpisodes with Acitive episodes*/
			PatientEpisode episode=patientEpisodeDao.findActiveEpisodeByPatient(patient,facilityId);
			if(StringUtils.isEmpty(episode)){
				ssnCheck = true;
			}
			if(ssnCheck){
				episode = patientEpisodeDao.findEpisodeByPatient(patient);
			}
			List<HsptlPhyCareTeam> careTeamDtoList = new ArrayList<HsptlPhyCareTeam>();
			if(ssnCheck){
				admissionDto.setPatientEpisodeId(null);
			}else{
				admissionDto.setPatientEpisodeId(episode.getId());
			}
			admissionDto.setFullCode( (episode.getFullCode()== null || episode.getFullCode().equalsIgnoreCase("NO")) ?false:true);
			admissionDto.setDnr(episode.getDnr());
			admissionDto.setDni(episode.getDni());
			admissionDto.setDnh(episode.getDnh());
			admissionDto.setNoArtificialFeeding(episode.getNoArtificialFeeding());
			admissionDto.setComfortCare(episode.getComfortCare());
			admissionDto.setHospiceCare(episode.getHospiceCare());
			admissionDto.setOtherAdvanceDirectives(episode.getOtherCare());
			admissionDto.setComfortCare(episode.getComfortCare());
			if(!StringUtils.isEmpty(episode.getFacilityAdmissionDate())){
				admissionDto.setDoj(CoreDateUtils.parseStringDate(episode.getFacilityAdmissionDate(), "MM/dd/yyyy"));
			}
			admissionDto.setPatientCareTypes(episode.getPatientCareTypes());
			admissionDto.setRoomNumber(episode.getRoomNumber());
			admissionDto.setPcpAtSite(episode.getPcpId());
			admissionDto.setSpecifyGoalsOfCareDiscussed(episode.getGoalsCareDiscussionText());
			admissionDto.setSummarizeHighPriorityNeeds(episode.getHsptalPriorityCareNeeds());
			admissionDto.setRecommendedFollowupTests(episode.getFollowUpTests());
			admissionDto.setKeyFindings(episode.getKeyFindings());
			admissionDto.setListProcedures(episode.getListProcedures());
			admissionDto.setPendingLabAndTestResults(episode.getPendingLabResult());
			admissionDto.setStopAndWatchEligibility(episode.getStopAndWatchEligible());
			admissionDto.setGoalsOfCareDiscussed(episode.getGoalsOfCareDiscussed());
			admissionDto.setCanTakeDecision(episode.getCanTakeDecision());
			admissionDto.setReqProxy(episode.getReqProxy());
			admissionDto.setPatientInsurencePlan(episode.getPatientInsurencePlan());
			admissionDto.setNurseToNurseVerbalReportText(episode.getHospitalNurseVerbalReport());
			admissionDto.setNurseToNurseVerbalReport(episode.getNurseToNurseVerbalReport());
			Long hospitalStaffId = null;
			Long hospitalStaffSpecialityId = null;
			Long hospitalStaffType = null;
			PatientEncounter patientEncounter = patientEncounterDao.findActiveEncounter(episode.getId());
			if(!StringUtils.isEmpty(patientEncounter))
			for(HospitalStaff careTeam:hospitalStaffDao.findHospitalStaffByPatientEncounter(patientEncounter.getId())){	
				hospitalStaffId = careTeam.getId();
				hospitalStaffSpecialityId = careTeam.getSpecialityId();
				hospitalStaffType = careTeam.getHsptlStaffTypeId();
				try{
					if(hospitalStaffId != null && (hospitalStaffSpecialityId !=null &&  hospitalStaffSpecialityId!= 0)){
						HsptlPhyCareTeam careTeamDto=new HsptlPhyCareTeam();
						HospitalStaff hStaff= hospitalStaffDao.findById(hospitalStaffId);
						HsptlStaffSpeciality hsptlStaffSpec = hsptlStaffSpecialityDao.findById(hospitalStaffSpecialityId);
						careTeamDto.setHospitalStaffId(hStaff.getId());
						careTeamDto.setStaffName(hStaff.getStaffName());
						careTeamDto.setHsptlStaffTypeId(hStaff.getHsptlStaffTypeId());
						careTeamDto.setSpecialityId(hStaff.getSpecialityId());
						careTeamDto.setSpecialityName(hsptlStaffSpec.getSpecialityName());
						careTeamDto.setContactNumber(String.valueOf(hStaff.getContactno()));
						careTeamDtoList.add(careTeamDto);
					}
					if(hospitalStaffId !=null && hospitalStaffSpecialityId !=null && hospitalStaffSpecialityId == 0 && hospitalStaffType == 2 ){
						HospitalStaff disRN=hospitalStaffDao.findById(hospitalStaffId);
						admissionDto.setDischargingRN(disRN.getStaffName());
						admissionDto.setDischargingRNId(disRN.getId());
						if(disRN.getContactno() != null)
							admissionDto.setDishcargingRNTelephoneNumber(String.valueOf(disRN.getContactno()));
					}
					if(hospitalStaffId !=null && hospitalStaffSpecialityId !=null && hospitalStaffSpecialityId == 0 && hospitalStaffType == 1 ){
						HospitalStaff disDoc=hospitalStaffDao.findById(hospitalStaffId);
						admissionDto.setDischargingMD(disDoc.getStaffName());
						admissionDto.setDischargingMDId(disDoc.getId());
						if(disDoc.getContactno() != null)
							admissionDto.setDishcargingMDTelephoneNumber(String.valueOf(disDoc.getContactno()));
					}
				}catch(Exception e){
					e.printStackTrace();
				}

				admissionDto.setPatientEncounterId(patientEncounter.getId());
			}
			admissionDto.setHsptlPhyCareTeam(careTeamDtoList);
			if(!StringUtils.isEmpty(patientEncounter) && !StringUtils.isEmpty(patientEncounter.getFromHospitalId())){
				Hospital fromHospital=hospitalDao.findById(patientEncounter.getFromHospitalId());
				admissionDto.setHospitalName(fromHospital.getHospitalName());
				admissionDto.setHospitalUnit(patientEncounter.getFormHospitalUnit());
				admissionDto.setHospitalId(fromHospital.getId());
				//					admissionDto.setPatientEpisodeId(episode.getId());
				admissionDto.setFacilityId(episode.getFacilityId());
				if(!StringUtils.isEmpty(patientEncounter.getFromHsptalAdmissionDate()))
					admissionDto.setHospitalAdmissionDate(CoreDateUtils.parseStringDate(patientEncounter.getFromHsptalAdmissionDate(), "MM/dd/yyyy"));
				if(!StringUtils.isEmpty(patientEncounter.getFromHsptalDischargeDate()))
					admissionDto.setFromHospitalDischargeDate(CoreDateUtils.parseStringDate(patientEncounter.getFromHsptalDischargeDate(), "MM/dd/yyyy"));

			}
			return admissionDto;
		}else{
			return null;
		}
	}

	@Transactional
	public List<HospitalStaffDto> getAllHospitalCareTeam(){

		List<HospitalStaff> careTeam = hospitalStaffDao.findAll();
		List<HospitalStaffDto> careTeamDtoList = new ArrayList<HospitalStaffDto>();
		for(HospitalStaff team:careTeam){
			// No specialty with Id 0 ---> specialityId '0' means Nurse
			if(team.getSpecialityId() != null && team.getSpecialityId() != 0 ){
				HsptlStaffSpeciality speciality = hsptlStaffSpecialityDao.findById(team.getSpecialityId());
				HospitalStaffDto dto=new HospitalStaffDto();
				dto.setHospitalStaffId(team.getId());
				dto.setHsptlStaffTypeId(team.getHsptlStaffTypeId());
				dto.setSpecialityId(speciality.getId());
				dto.setSpecialityName(speciality.getSpecialityName());
				//			dto.setPatientEpisodeId(team.getPatientEpisodeId());
				dto.setStaffName(team.getStaffName());
				dto.setContactNumber(String.valueOf(team.getContactno()));
				dto.setEmailId(team.getEmailid());
				careTeamDtoList.add(dto);
			}
		}
		return careTeamDtoList;
	}

	@Transactional
	public SSNCheckDto checKSSNforPatient(String ssn){
		return patientDao.checKSSNforPatient(ssn);
	}

}
