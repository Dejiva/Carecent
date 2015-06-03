package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.codecoop.interact.core.domain.Facility;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.domain.StaffAddress;
import com.codecoop.interact.core.domain.StaffContact;
import com.codecoop.interact.core.domain.StaffRoles;
import com.codecoop.interact.core.dto.FStaffRegistrationDto;
import com.codecoop.interact.core.dto.FacilityStaffDto;
import com.codecoop.interact.core.dto.FacilityStaffroleDto;
import com.codecoop.interact.core.model.LoggedUserModel;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.core.util.CoreAppPropertyReader;
import com.codecoop.interact.core.util.CoreUtils;
import com.codecoop.interact.core.util.PasswordGenUtil;

/**
 * @author Ramesh
 *
 */
@Service
public class FacilityStaffService {

	private static final Logger LOG = Logger.getLogger(FacilityStaffService.class);

	@Autowired
	FacilityStaffDaoImpl facilityStaffDao;

	@Autowired
	MailService mailService;

	@Autowired
	FacilityDaoImpl facilityDao;

	@Autowired
	StaffDaoImpl staffDao;

	@Autowired
	StaffRolesDaoImpl staffRolesDao;

	@Autowired
	CoreAppPropertyReader coreAppPropertyReader;

	//	@Autowired
	//	NursinghomeStaffDetailsDaoImpl staffDetailsDao;

	//	@Autowired
	//	NursinghomeDaoImpl nursingHomeDao;
	@Autowired
	AssignedDocRelationDaoImpl assignedDocRelationDao;
	@Transactional
	public FacilityStaff findFacilityStaff(Long facilityId, Long StaffId){
		return facilityStaffDao.findFacilityStaff(facilityId, StaffId);
	}


	@Transactional
	public List<FacilityStaffroleDto> findAllRolesDto() {

		List<StaffRoles> roles = staffRolesDao.findAll();

		List<FacilityStaffroleDto> fStaffRoleDto = new ArrayList<FacilityStaffroleDto>();

		for (StaffRoles role : roles) {
			FacilityStaffroleDto rolesDto = new FacilityStaffroleDto(role);
			fStaffRoleDto.add(rolesDto);
		}

		return fStaffRoleDto;
	}

	/*
	 * have to think about this method
	 * 
	 */
	@Transactional
	public List<Facility> findFacilityByUserName(String userName){
		List<Facility> faclilityList=new ArrayList<Facility>(); 
		for(FacilityStaff fstaff:facilityStaffDao.findFacilityStaffListByStaffId(staffDao.findStaffByUserName(userName).getId())){
			faclilityList.add(fstaff.getFacility());
		}
		return faclilityList;
	}
	@Transactional
	public List<FacilityStaff> findAllActiveFacilityStaffByFaclityId(Long facilityId){
		List<FacilityStaff> faclilityStaffList=new ArrayList<FacilityStaff>(); 
		faclilityStaffList =facilityStaffDao.findAllActiveFacilityStaffByFaclityId(facilityId);
		return faclilityStaffList;
	}


	@Transactional
	public List<Facility> findFacilityByStaffId(Long staffId){
		List<Facility> faclilityList=new ArrayList<Facility>(); 
		for(FacilityStaff fstaff:facilityStaffDao.findFacilityStaffListByStaffId(staffDao.findById(staffId).getId())){
			faclilityList.add(fstaff.getFacility());
		}
		return faclilityList;
	}

	@Transactional
	public List<StaffRoles> findAllRoles(){
		return staffRolesDao.findAll();
	}

	@Transactional
	public StaffRoles findFStaffByRole(String role) {

		return facilityStaffDao.findFacilityStaffByRole(role);
	}


	@Transactional
	public StaffRoles findRoleByRoleId(Long roleId) {

		return staffRolesDao.findById(roleId);
	}

	@Transactional
	public void saveNewStaffMember(FStaffRegistrationDto fStaffregDto,String url,Long facilityId) {

		Staff staff=new Staff();
		populateStaff(staff,fStaffregDto);

		FacilityStaff fStaff=new FacilityStaff();
		populateFacilityStaff(fStaff, staff, fStaffregDto);

		StaffAddress staffAddress=new StaffAddress();
		populateStaffAddress(staffAddress, staff, fStaffregDto);

		if(fStaffregDto.getMobileNo() !=null){
			StaffContact staffContact=new StaffContact();
			populateStaffContact(staffContact, staff, fStaffregDto, Constants.CONTACT_TYPE_MOBILE);
		}
		if(fStaffregDto.getEmail() !=null){
			StaffContact staffContact=new StaffContact();
			populateStaffContact(staffContact, staff, fStaffregDto, Constants.CONTACT_TYPE_EMAIL);
		}

		staffDao.saveOrUpdate(staff);
		if(fStaffregDto.getDocRoleId()!=null&&fStaffregDto.getDocRoleId()!=0 ){
			AssignedDocRelation assignedDocRelation=new AssignedDocRelation();
			assignedDocRelation.setFacilityStaffId(fStaff.getId());

			assignedDocRelation.setDocFacilityStaffId(fStaffregDto.getDocRoleId());
			assignedDocRelationDao.save(assignedDocRelation);
		}
		sendMailToStaff(staff,fStaff,fStaffregDto.getPasswd(),url,"You have been added");
	}

	public void sendMailToStaff(Staff staff,FacilityStaff fStaff,String passCode,String url,String text){
		for(StaffContact staffContact:staff.getStaffContacts()){
			String receiver = fStaff.getWorkEmail();
			String facility = fStaff.getFacility().getFacilityName();
			Long facilityId=fStaff.getFacility().getId();
			String role	= fStaff.getStaffRoles().getRoleName();
			if(staffContact.getContactType().equalsIgnoreCase("email")){
				mailService.sendMail("Carecent Staff Registration",
						""+(StringUtils.isEmpty(passCode)?"":("Congratulations!... "))+text+" as a \""+role.split("_")[1]+"\" in \""+facility+"\"\n\n"+
								"UserName:"+staff.getUsername()+"\n"+(StringUtils.isEmpty(passCode)?"":("PassWord:"+passCode))+
								"\n\n\n Note: On first login system will prompt you to change password."
								+"\n\n To access application click on or copy/paste url in a browser:"+url+"login"+ 
								"\n\nGuest Stop And Watch Genrater Url is:"+url+"guest?facilityId="+facilityId,receiver,coreAppPropertyReader.getUserRegMailSender());
			}
		}
	}

	private void populateStaff(Staff staff, FStaffRegistrationDto fStaffregDto ){
		staff.setFirstName(fStaffregDto.getFirstName());
		staff.setLastName(fStaffregDto.getLastName());
		staff.setMiddleName(fStaffregDto.getMiddleName());
		staff.setUsername(fStaffregDto.getUserName());
		if(!StringUtils.isEmpty(fStaffregDto.getPasswd())){
			String encriptPassCode = PasswordGenUtil.encriptPassword(fStaffregDto.getPasswd());
			staff.setPasswd(encriptPassCode);
		}
		staff.setDob(fStaffregDto.getDob());
		staff.setGender(fStaffregDto.getGender());
		if(CoreUtils.isLongIDExists(staff.getId())){
			staff.setDateModified(new Date());
			staff.setUserModified(fStaffregDto.getUserModified());
		}else{
			staff.setDateCreated(new Date());
			staff.setUserCreated(fStaffregDto.getUserCreated());
		}
	}

	private void populateFacilityStaff(FacilityStaff fStaff, Staff staff, FStaffRegistrationDto fStaffregDto){
		fStaff.setDateOfJoining(fStaffregDto.getDateOfHire());
		fStaff.setFacility(facilityDao.findById(fStaffregDto.getFacilityId()));
		if(fStaffregDto.getRelievingDate() != null && fStaffregDto.isDeactive())
		{
			fStaff.setRelievingDate(fStaffregDto.getRelievingDate());
		}else if(fStaffregDto.getRelievingDate() == null && fStaffregDto.isDeactive()){
			fStaff.setRelievingDate(null);
		}
		fStaff.setEmpId(fStaffregDto.getEmpId());
		fStaff.setWorkEmail(fStaffregDto.getWorkEmail());
		fStaff.setWorkNumber(fStaffregDto.getWorkNumber());
		if(CoreUtils.isLongIDExists(fStaff.getId())){
			fStaff.setDateModified(new Date());
			fStaff.setUserModified(fStaffregDto.getUserModified());	
		}else{
			fStaff.setDateCreated(new Date());
			fStaff.setUserCreated(fStaffregDto.getUserCreated());		
		}
		fStaff.setStaffRoles(staffRolesDao.findById(fStaffregDto.getStaffRoleId()));
		fStaff.setStaff(staff);
		if(CollectionUtils.isEmpty(staff.getFacilityStaffs())){
			staff.setFacilityStaffs(new HashSet<FacilityStaff>());
		}
		staff.getFacilityStaffs().add(fStaff);		
	}

	private void populateStaffAddress(StaffAddress staffAddress, Staff staff, FStaffRegistrationDto fStaffregDto){
		staffAddress.setAddress(fStaffregDto.getAddress());
		staffAddress.setState(fStaffregDto.getState());
		staffAddress.setStreet(fStaffregDto.getStreet());
		staffAddress.setCity(fStaffregDto.getCity());
		staffAddress.setZipcode(fStaffregDto.getZipcode());
		staffAddress.setDateCreated(new Date());
		staffAddress.setUserCreated(fStaffregDto.getUserCreated());
		staffAddress.setStaff(staff);
		if(CollectionUtils.isEmpty(staff.getStaffAddresses())){
			staff.setStaffAddresses(new HashSet<StaffAddress>());
		}
		staff.getStaffAddresses().add(staffAddress);
	}

	private void populateStaffContact(StaffContact staffContact, Staff staff, FStaffRegistrationDto fStaffregDto, String contactType){
		staffContact.setContactType(contactType);
		if(Constants.CONTACT_TYPE_EMAIL.equals(contactType)){
			staffContact.setContact(fStaffregDto.getEmail());
		}else if(Constants.CONTACT_TYPE_MOBILE.equals(contactType)){
			staffContact.setContact(fStaffregDto.getMobileNo());
		}
		staffContact.setPrimaryFlag(fStaffregDto.getPrimaryFlag());
		if(CoreUtils.isLongIDExists(staffContact.getId())){
			staffContact.setDateModified(new Date());
			staffContact.setUserModified(fStaffregDto.getUserCreated());
		}else{
			staffContact.setDateCreated(new Date());
			staffContact.setUserCreated(fStaffregDto.getUserCreated());
		}
		staffContact.setStaff(staff);
		if(CollectionUtils.isEmpty(staff.getStaffContacts())){
			staff.setStaffContacts(new HashSet<StaffContact>());
		}
		staff.getStaffContacts().add(staffContact);		
	}

	@Transactional
	public void editStaffMember(FStaffRegistrationDto fStaffregDto,String url){
		Staff staff= staffDao.findById(fStaffregDto.getStaffId());
		populateStaff(staff,fStaffregDto);

		FacilityStaff fStaff=facilityStaffDao.findFacilityStaff(fStaffregDto.getFacilityId(), fStaffregDto.getStaffId());
		populateFacilityStaff(fStaff, staff, fStaffregDto);

		StaffAddress staffAddress=new StaffAddress();
		if(!CollectionUtils.isEmpty(staff.getStaffAddresses())){
			staffAddress = (StaffAddress)staff.getStaffAddresses().toArray()[0];
		}
		populateStaffAddress(staffAddress, staff, fStaffregDto);

		if(fStaffregDto.getMobileNo() !=null){
			StaffContact staffContact= getContact(staff, Constants.CONTACT_TYPE_MOBILE);
			if(staffContact == null){
				staffContact = new StaffContact();
			}
			populateStaffContact(staffContact, staff, fStaffregDto, Constants.CONTACT_TYPE_MOBILE);
		}
		if(fStaffregDto.getEmail() !=null){
			StaffContact staffContact= getContact(staff, Constants.CONTACT_TYPE_EMAIL);
			if(staffContact == null){
				staffContact = new StaffContact();
			}
			populateStaffContact(staffContact, staff, fStaffregDto, Constants.CONTACT_TYPE_EMAIL);
		}

		//		staffDao.saveOrUpdate(staff);
		if(fStaffregDto.getDocRoleId()!=null){ 
			AssignedDocRelation assignedDocRelation=assignedDocRelationDao.getAssignDocRelation(fStaff.getId());
			if(assignedDocRelation==null)
			{
				assignedDocRelation=new AssignedDocRelation();
				assignedDocRelation.setFacilityStaffId(fStaff.getId());
			}

			assignedDocRelation.setDocFacilityStaffId(fStaffregDto.getDocRoleId());
			assignedDocRelationDao.saveOrUpdate(assignedDocRelation);
		}
		if(!StringUtils.isEmpty(fStaffregDto.getRelievingDate()) && fStaffregDto.isDeactive()){
			//			fStaffregDto.setPasswd(PasswordGenUtil.generatePswd());
			//			staff.setPasswd(PasswordGenUtil.encriptPassword(fStaffregDto.getPasswd()));
			staffDao.saveOrUpdate(staff);
			sendMailToStaff(staff,fStaff,null,url,"Your account has been deactivated");
		}else if(fStaffregDto.isDeactive() && StringUtils.isEmpty(fStaffregDto.getRelievingDate())){
			fStaffregDto.setPasswd(PasswordGenUtil.generatePswd());
			staff.setPasswd(PasswordGenUtil.encriptPassword(fStaffregDto.getPasswd()));
			staffDao.saveOrUpdate(staff);
			sendMailToStaff(staff,fStaff,fStaffregDto.getPasswd(),url,"Your account has been activated");	
		}
	}
	private StaffContact getContact(Staff staff, String contactType){
		for(StaffContact staffContact:staff.getStaffContacts()){
			if(contactType.equals(staffContact.getContactType())){
				return staffContact;
			}
		}
		return null;
	}

	@Transactional	
	public void addStaffMemberToFacility(FStaffRegistrationDto fStaffregDto,String url){
		FacilityStaff fStaff=new FacilityStaff();

		fStaff.setDateOfJoining(fStaffregDto.getDateOfHire());

		fStaff.setFacility(facilityDao.findById(fStaffregDto.getFacilityId()));
		//		nStaff.setRelievingDate(registrationForm.getRelievingDate().equals("")?null:registrationForm.getRelievingDate());
		fStaff.setRelievingDate(fStaffregDto.getRelievingDate());
		fStaff.setEmpId(fStaffregDto.getEmpId());
		fStaff.setWorkEmail(fStaffregDto.getWorkEmail());
		fStaff.setWorkNumber(fStaffregDto.getWorkNumber());
		fStaff.setDateCreated(fStaffregDto.getDateCreated());
		fStaff.setUserCreated(fStaffregDto.getUserCreated());
		fStaff.setStaffRoles(staffRolesDao.findById(fStaffregDto.getStaffRoleId()));

		//		fStaff.setStaffRoles(staffRolesDao.findById(fStaffregDto.getDocRoleId()));

		Staff staff = staffDao.findById(fStaffregDto.getStaffId());

		fStaff.setStaff(staff);

		if(CollectionUtils.isEmpty(staff.getFacilityStaffs())){
			staff.setFacilityStaffs(new HashSet<FacilityStaff>());
		}
		staff.getFacilityStaffs().add(fStaff);

		facilityStaffDao.saveOrUpdate(fStaff);

		if(fStaffregDto.getDocRoleId()!=null){
			AssignedDocRelation assignedDocRelation=new AssignedDocRelation();
			assignedDocRelation.setFacilityStaffId(fStaff.getId());
			assignedDocRelation.setDocFacilityStaffId(fStaffregDto.getDocRoleId());
			assignedDocRelationDao.save(assignedDocRelation);
		}
		sendMailToStaff(staff,fStaff,null,url,"You have been added");
	}

	@Transactional
	public Boolean editStaffDetails(FStaffRegistrationDto fStaffFormDto){
		Staff staff=new Staff();
		staff=staffDao.findStaffByUserName(fStaffFormDto.getUserName());
		if(staff.getPasswd().equals(fStaffFormDto.getCurrentPassword())){
			staff.setPasswd(fStaffFormDto.getPasswd());
			staff.setDateModified(fStaffFormDto.getDateModified());
			staff.setUserModified(fStaffFormDto.getUserModified());
			staffDao.saveOrUpdate(staff);
			return true;
		}else{
			return false;
		}
	}

	@Transactional
	public List<FacilityStaffDto> findStaffListByStaffId(Long staffId){
		List<FacilityStaff> fStaffList=facilityStaffDao.findFacilityStaffListByStaffId(staffId);
		List<FacilityStaffDto> staffListDto=new ArrayList<FacilityStaffDto>();

		for(FacilityStaff fStaff:fStaffList){
			FacilityStaffDto staffDto=new FacilityStaffDto();
			staffDto.setId(fStaff.getId());
			staffDto.setFacilityId(fStaff.getFacility().getId());
			staffDto.setStaffId(fStaff.getStaff().getId());
			staffDto.setEmpId(fStaff.getEmpId());
			staffDto.setRoleId(fStaff.getStaffRoles().getId());
			staffDto.setRole(staffRolesDao.findById(fStaff.getStaffRoles().getId()).getRoleName());
			staffDto.setRelievingDate(fStaff.getRelievingDate());
			staffDto.setWorkEmail(fStaff.getWorkEmail());
			staffDto.setWorkNumber(fStaff.getWorkNumber());
			staffListDto.add(staffDto);

		}

		return staffListDto;

	}
	@Transactional
	public List<FacilityStaffDto> getMDPAofCurrentFacility(Long facilityId){

		List<FacilityStaffDto> facilityStaffDtoList = facilityStaffDao.getMDPAofCurrentFacility(facilityId);

		return facilityStaffDtoList;
	}

	@Transactional
	public List<FacilityStaffDto> getMDofCurrentFacility(Long facilityId){

		List<FacilityStaffDto> facilityStaffDtoList =facilityStaffDao.getMDofCurrentFacility( facilityId);

		return facilityStaffDtoList;
	}
	@Transactional
	public Facility findFacilityById(Long facilityId)
	{
		Facility facility=facilityDao.findById(facilityId);
		return facility;
	}

	@Transactional
	public LoggedUserModel getLoggedUser(Long staffId,Long facilityId){
		return facilityStaffDao.getLoggedUser(staffId, facilityId);
	}

	@Transactional
	public Long getAssignedMDByFStaffId(Long fStaffId){
		return facilityStaffDao.getAssignedMDByFStaffId(fStaffId);
	}

	@Transactional
	public List<FacilityStaffDto> getNursesOfCurrentFacility(Long facilityId){
		return facilityStaffDao.getNursesOfCurrentFacility(facilityId);
	}

}