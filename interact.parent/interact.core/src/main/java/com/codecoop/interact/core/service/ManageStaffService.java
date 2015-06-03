package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.dao.AssignedDocRelationDaoImpl;
import com.codecoop.interact.core.dao.FacilityDaoImpl;
import com.codecoop.interact.core.dao.FacilityStaffDaoImpl;
import com.codecoop.interact.core.dao.StaffDaoImpl;
import com.codecoop.interact.core.dao.StaffRolesDaoImpl;
import com.codecoop.interact.core.domain.AssignedDocRelation;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.domain.StaffAddress;
import com.codecoop.interact.core.domain.StaffContact;
import com.codecoop.interact.core.dto.FStaffRegistrationDto;
import com.codecoop.interact.core.util.Constants;

@Service
public class ManageStaffService {

	private static final Logger LOG = Logger.getLogger(ManageStaffService.class);
	@Autowired
	private FacilityStaffDaoImpl facilityStaffDao;

	@Autowired
	private FacilityDaoImpl facilityDao;

	@Autowired
	private StaffDaoImpl staffDao;

	@Autowired
	private StaffRolesDaoImpl StaffRolesDao;
	
	@Autowired
	private AssignedDocRelationDaoImpl assignedDocRelationDaoImpl;

	@Transactional
	public String addOrEditOrDeleteUserInFacility(FStaffRegistrationDto fStaffFormDto){

		Staff staff=new Staff();

		//		staff=facilityStaffDao.findStaffByUserName(fStaffFormDto.getUserName());
		//		staff=facilityStaffDao.findStaffByUserName("kavikavi1");
		staff=staffDao.findById(fStaffFormDto.getStaffId());

		FacilityStaff facilityStaff=new FacilityStaff();

		if(fStaffFormDto.getOperation().equals("add")){

			facilityStaff.setStaff(staff);
			facilityStaff.setFacility(facilityDao.findById(fStaffFormDto.getFacilityId()));
			facilityStaff.setEmpId(fStaffFormDto.getEmpId());
			facilityStaff.setDateOfJoining(fStaffFormDto.getDateOfHire());
			facilityStaff.setStaffRoles(StaffRolesDao.findById(fStaffFormDto.getStaffRoleId()));
			facilityStaff.setWorkEmail(fStaffFormDto.getWorkEmail());
			facilityStaff.setWorkNumber(fStaffFormDto.getWorkNumber());
			facilityStaff.setUserModified(fStaffFormDto.getUserName());
			facilityStaff.setDateModified(new Date());

			//		staff.setFacilityStaffs(new HashSet<FacilityStaff>());
			//		staff.getFacilityStaffs().add(facilityStaff);

			facilityStaffDao.addOrUpdateFacilityStaff(facilityStaff);
		}else{

			facilityStaff.setStaff(staff);
			facilityStaff.setFacility(facilityDao.findById(fStaffFormDto.getFacilityId()));
			facilityStaff.setEmpId(fStaffFormDto.getEmpId());
			facilityStaff.setDateOfJoining(fStaffFormDto.getDateOfHire());
			facilityStaff.setStaffRoles(StaffRolesDao.findById(fStaffFormDto.getStaffRoleId()));
			facilityStaff.setWorkEmail(fStaffFormDto.getWorkEmail());
			facilityStaff.setWorkNumber(fStaffFormDto.getWorkNumber());
			facilityStaff.setUserModified(fStaffFormDto.getUserName());
			facilityStaff.setDateModified(new Date());

			for(StaffAddress sAddress:staff.getStaffAddresses()){
				sAddress.setAddress(fStaffFormDto.getAddress());
				sAddress.setCity(fStaffFormDto.getCity());
				sAddress.setState(fStaffFormDto.getState());
				sAddress.setStreet(fStaffFormDto.getStreet());
				sAddress.setDateModified(new Date());
			}

			for(StaffContact sContac:staff.getStaffContacts()){

				if(fStaffFormDto.getMobileNo() != null){
					sContac.setContactType("mobile");
					sContac.setContact(fStaffFormDto.getMobileNo());
				}
				if(fStaffFormDto.getEmail() != null){
					sContac.setContactType("email");
					sContac.setContact(fStaffFormDto.getEmail());

				}
			}

			staffDao.saveOrUpdate(staff);


		}
		return "SUCCESS";
	}


	@Transactional
	public FStaffRegistrationDto getAllDataForAStaff(Long staffId, Long facilityId){

		Staff staff=null;
		FStaffRegistrationDto staffDto=new FStaffRegistrationDto();

		staff=staffDao.findById(staffId);

		staffDto.setFirstName(staff.getFirstName());
		staffDto.setMiddleName(staff.getMiddleName());
		staffDto.setLastName(staff.getLastName());
		staffDto.setUserName(staff.getUsername());
		staffDto.setGender(staff.getGender());

		if(!CollectionUtils.isEmpty(staff.getFacilityStaffs())){
			for(FacilityStaff fs:staff.getFacilityStaffs()){
				if(fs.getFacility().getId() == facilityId){
					AssignedDocRelation assignedDocRelation = assignedDocRelationDaoImpl.getAssignDocRelation(fs.getId());
					staffDto.setDocRoleId(StringUtils.isEmpty(assignedDocRelation)?null:assignedDocRelation.getDocFacilityStaffId());
					staffDto.setWorkEmail(fs.getWorkEmail());
					staffDto.setWorkNumber(fs.getWorkNumber());
					staffDto.setEmpId(fs.getEmpId());
					staffDto.setStaffRoleId(fs.getStaffRoles().getId());
					staffDto.setDateOfHire(fs.getDateOfJoining());
					staffDto.setRelievingDate(fs.getRelievingDate());
				}
			}
		}

		if(!CollectionUtils.isEmpty(staff.getStaffAddresses())){
			List<StaffAddress> staffAddressList = new ArrayList<StaffAddress>();
			staffAddressList.addAll(staff.getStaffAddresses());
			staffDto.setAddress(staffAddressList.get(0).getAddress());
			staffDto.setStreet(staffAddressList.get(0).getStreet());
			staffDto.setState(staffAddressList.get(0).getState());
			staffDto.setCity(staffAddressList.get(0).getCity());
			staffDto.setZipcode(staffAddressList.get(0).getZipcode());
		}

		if(!CollectionUtils.isEmpty(staff.getStaffContacts())){
			for(StaffContact staffContact:staff.getStaffContacts()){
				if(Constants.CONTACT_TYPE_EMAIL.equals(staffContact.getContactType()) && staffContact.getPrimaryFlag()){
					staffDto.setEmail(staffContact.getContact());
				}else if(Constants.CONTACT_TYPE_MOBILE.equals(staffContact.getContactType()) && staffContact.getPrimaryFlag()){
					staffDto.setMobileNo(staffContact.getContact());
				}
			}
		}

		return staffDto;
	}



}
