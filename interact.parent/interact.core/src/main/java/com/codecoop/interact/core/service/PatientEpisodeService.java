package com.codecoop.interact.core.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.FacilityStaffDaoImpl;
import com.codecoop.interact.core.dao.PatientDaoImpl;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.dao.StaffDaoImpl;
import com.codecoop.interact.core.domain.Patient;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.dto.FacilityStaffDto;
import com.codecoop.interact.core.model.PatientDetailsModel;

@Service
public class PatientEpisodeService {

	private static final Logger LOG = Logger.getLogger(PatientEpisodeService.class);
	@Autowired
	StaffDaoImpl staffDao;
	@Autowired
	PatientDaoImpl patientDao;
	@Autowired
	PatientEpisodeDao patientEpisodeDao;
	@Autowired
	FacilityStaffDaoImpl facilityStaffDao;

	@Transactional
	public List<Patient> findAllPatientsFromFacility(Long facilityId) {
		//		List<Patient> patientList=new ArrayList<Patient>();
		//		for(PatientEpisode	patientEpisode: patientEpisodeDao.findAllPatientsByFacilityId(facilityId))
		//			patientList.add(patientEpisode.getPatient());
		return patientEpisodeDao.findAllPatientsByFacilityId(facilityId);
	}

	@Transactional
	public List<PatientDetailsModel> findAllPatientsFromFacility1(Long facilityId) {
		return patientEpisodeDao.findAllPatientsByFacilityId1(facilityId);
	}

	@Transactional
	public List<PatientEpisode> findAllPatientsInFacility(Long facilityId) {

		return patientEpisodeDao.findAllByFacilityId(facilityId);
	}
	@Transactional
	public PatientEpisode findActicePatientEpisode(Long patientId ,Long facilityId) {
		Patient patient=patientDao.findById(patientId);
		return patientEpisodeDao.findActiveEpisodeByPatient(patient,facilityId);
	}
	/*@Transactional
	public Patient getPatient(Long patientEpisodeId) {
		// TODO Auto-generated method stub
		return patientEpisodeDao.findById(patientEpisodeId).getPatient();

	}*/
	@Transactional
	public PatientEpisode findById(Long patientEpisodeId) {
		return patientEpisodeDao.findById(patientEpisodeId);

	}
	@Transactional
	public List<PatientDetailsModel> findByPatientId(Long facilityId,String[] patientName) {
		return patientDao.findFacilityPatientsByPatientId(facilityId,patientName);

	}

	@Transactional
	public FacilityStaffDto getPatientAsignedDoc(
			Long patientEpisodeId) {
		return facilityStaffDao.getPatientAsignedDoc(patientEpisodeId);

	}

	@Transactional
	public List<PatientDetailsModel> populateAssignedPatients(Long facilityId,Long pcpId){
		return patientDao.populateAssignedPatients(facilityId, pcpId);
	}

}
