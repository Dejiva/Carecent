package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.StaffDaoImpl;
import com.codecoop.interact.core.dao.StatesDao;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.domain.States;
import com.codecoop.interact.core.dto.UserManageDto;

@Service
public class StaffService {

	private static final Logger LOG = Logger.getLogger(StaffService.class);
	@Autowired
	StaffDaoImpl staffDao;
	
	@Autowired
	StatesDao statesDao;
	

	@Transactional
	public Staff findStaffByUserName(String userName) {
			Staff fStaff = staffDao.findStaffByUserName(userName);
		return fStaff;
	}
	
	@Transactional
	public Long getStaffIdByUserName(String userName){
		Staff fStaff = findStaffByUserName(userName);
		if(fStaff != null){
			return fStaff.getId();
		}
		return null;
	}
	
	@Transactional
	public Staff findStaffById(Long staffId) {
			Staff fStaff = staffDao.findById(staffId);
		return fStaff;
	}
	
	@Transactional
	public List<UserManageDto> getAllStaffMembersInFacility(Long facilityId){
		return staffDao.getAllStaffMembersInFacility(facilityId);		
	}
	
	@Transactional	
	public List<UserManageDto> getAllStaffMembersIByUserName(String userName, Long facilityId){
		return staffDao.getAllStaffMembersIByUserName(userName, facilityId);
	}
	
	@Transactional
	public List<String> getAllStates(){
		List<String> statesList = new ArrayList<String>();
		
		for(States states:statesDao.getStates()){
			statesList.add(states.getState());
		} 
		return statesList;
	}
}
