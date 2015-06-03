package com.codecoop.interact.core.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.FacilityStaffDaoImpl;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.dao.RestDao;
import com.codecoop.interact.core.dao.SbarDaoImpl;
import com.codecoop.interact.core.dao.SbarNotesDaoImpl;
import com.codecoop.interact.core.dao.StaffDaoImpl;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.SbarNotes;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.rest.dto.RestAlertDto;
import com.codecoop.interact.core.rest.dto.RestFacilitiesDto;
import com.codecoop.interact.core.rest.dto.RestLabOrdersDto;
import com.codecoop.interact.core.rest.dto.RestPatientObservationDto;
import com.codecoop.interact.core.rest.dto.RestPractitionerNotesDto;
import com.codecoop.interact.core.rest.dto.ShortNotesDto;
import com.codecoop.interact.core.util.Constants;


/**
 * @author Ramesh
 *
 */
@Service
public class RestService {

	private static final Logger LOG =  Logger.getLogger(RestService.class);
	@Autowired
	private RestDao restDao;

	@Autowired
	private StaffDaoImpl staffDao;

	@Autowired
	private SbarDaoImpl sbarDao;

	@Autowired
	private FacilityStaffDaoImpl facilityStaffDao;

	@Autowired
	private PatientEpisodeDao patientEpisodeDao;

	@Autowired
	SbarNotesDaoImpl sbarNotesDao;

	@Transactional
	public List<RestFacilitiesDto> getFacilitiesByUserName(String userName,String passwd){
		return restDao.getFacilitiesByUserName(userName,passwd);
	}

	@Transactional
	public Boolean updateDeviceDetailsOfTheStaff(String userName,String deviceToken,String deviceType,Boolean deviceLoggedStatus){
		try{
			Staff staff = staffDao.findStaffByUserName(userName);
			staff.setDeviceToken(deviceToken);
			staff.setDeviceType(deviceType);
			staff.setDeviceLoggedStatus(deviceLoggedStatus);
			staffDao.saveOrUpdate(staff);
			return true;
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	@Transactional
	public RestPatientObservationDto ObservationOfPatient(Long pEpisodeId){
		return restDao.observationOfPatient(pEpisodeId);
	}

	@Transactional
	public RestPatientObservationDto updateLabwork(RestLabOrdersDto labOrder){
		return restDao.updateLabwork(labOrder.getId(), labOrder.getSbarId(), labOrder.getSignsSymptomsLabworkId(), labOrder.getApproved(), labOrder.getRejected(), labOrder.getFacilityStaffId(), labOrder.getSignSymtomLabworkName(),labOrder.getStndingOrder());
	}
	@Transactional
	public List<RestAlertDto> alertsData(String userName,String type,String order) {

		// TODO Auto-generated method stub
		return restDao.getAlertsByUserName(userName,type,order);
	}
	@Transactional
	public List<ShortNotesDto> getShortNotes() {
		// TODO Auto-generated method stub
		return restDao.getShortcutNotes();
	}

	@Transactional
	public Sbar findByPatientEpisodeId(Long patientEposodeId) {
		return sbarDao.findByPatientEpisode(patientEpisodeDao
				.findById(patientEposodeId));

	}

	@Transactional
	public String getStaffRoleType(String roleName) {
		//		public String getStaffRoleType(Long staffId, long facilityId) {
		//		String roleName = facilityStaffDao
		//				.findFacilityStaff(facilityId, staffId).getStaffRoles()
		//				.getRoleName();
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
	public Boolean saveSbarNotes(RestPractitionerNotesDto request1) {

		try{
			Sbar sbar = findByPatientEpisodeId(request1.getPatientEpisodeId());
			//		Long staffId = request1.getNursingStaffId();
			//		Staff nursingStaff = staffDao.findById(staffId);
			FacilityStaff nursingFacilityStaff=facilityStaffDao.findById(request1.getfStaffId());
			PatientEpisode patientEpisode = patientEpisodeDao.findById(request1.getPatientEpisodeId());
			if (sbar == null) {
				sbar = new Sbar();
				sbar.setPatientEpisode(patientEpisode);
				sbar.setUserCreated(nursingFacilityStaff.getStaff().getFirstName());
				sbar.setDateCreated(new Date());
				patientEpisode.setSbarRised(true);
				patientEpisodeDao.saveOrUpdate(patientEpisode);

			}

			SbarNotes sbarNotes = new SbarNotes();
			sbarNotes.setSbar(sbar);
			sbarNotes.setNotes(request1.getNotes());
			sbarNotes.setNursingFacilityStaff(nursingFacilityStaff);
			sbarNotes.setNursingStaffType(getStaffRoleType(nursingFacilityStaff.getStaffRoles().getRoleName()));
			sbarNotes.setUserCreated(nursingFacilityStaff.getStaff().getFirstName());
			sbarNotes.setDateCreated(new Date());
			sbarNotes.setDateModified(new Date());
			sbarNotesDao.saveOrUpdate(sbarNotes);
			return true;
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

}
