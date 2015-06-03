package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.FacilityDaoImpl;
import com.codecoop.interact.core.domain.Facility;
import com.codecoop.interact.core.domain.FacilityAddress;
import com.codecoop.interact.core.domain.FacilityContact;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.dto.FacilityAddressDto;
import com.codecoop.interact.core.dto.FacilityContactDto;
import com.codecoop.interact.core.dto.FacilityDto;
import com.codecoop.interact.core.dto.FacilityStaffDto;

@Service
public class ManageFacilityService {

	private static final Logger LOG = Logger.getLogger(ManageFacilityService.class);
	
	@Autowired
	FacilityDaoImpl facilityDao;
	
	@Transactional
	public FacilityDto findFacilityById(Long id){
		Facility facility= facilityDao.findById(id);
		FacilityDto dto=new FacilityDto();
		if(facility != null){
		dto.setId(facility.getId());
		dto.setFacilityName(facility.getFacilityName());
		}
		return dto;
	}

	@Transactional
	public List<FacilityDto> findAllFacilities(){

		List<FacilityDto> facilityDtoList =new ArrayList<FacilityDto>();

		for(Facility facility: facilityDao.findAll()){
			FacilityDto facilityDto =new FacilityDto();
			facilityDto.setId(facility.getId());
			facilityDto.setFacilityName(facility.getFacilityName());

			Set<FacilityContactDto> contactDtoList =new HashSet<FacilityContactDto>();
			for(FacilityContact cont:facility.getFacilityContacts()){
				FacilityContactDto contactDto =new FacilityContactDto();
				contactDto.setContact(cont.getContact());
				contactDto.setContactType(cont.getContactType());
				contactDtoList.add(contactDto);
			}

			Set<FacilityAddressDto> addressDtoList = new HashSet<FacilityAddressDto>();
			for(FacilityAddress add:facility.getFacilityAddresses()){
				FacilityAddressDto addressDto = new FacilityAddressDto();
				addressDto.setAddress(add.getAddress());
				addressDto.setCity(add.getCity());
				addressDto.setState(add.getState());
				addressDto.setStreet(add.getStreet());
				addressDto.setZipcode(add.getZipcode());
				addressDtoList.add(addressDto);
			}
			Set<FacilityStaffDto> fStaffDtoList = new HashSet<FacilityStaffDto>();
			for(FacilityStaff staff:facility.getFacilityStaffs()){
				FacilityStaffDto fStaffDto = new FacilityStaffDto();
				fStaffDto.setId(staff.getId());
				fStaffDto.setName(staff.getStaff().getUsername());
				fStaffDto.setRole(staff.getStaffRoles().getRoleName());
				fStaffDtoList.add(fStaffDto);
			}
			facilityDto.setFacilityStaffs(fStaffDtoList);
			facilityDto.setFacilityContacts(contactDtoList);
			facilityDto.setFacilityAddresses(addressDtoList);

			facilityDtoList.add(facilityDto);
		}
		return facilityDtoList;
	}

	@Transactional
	public String saveFacility(FacilityDto facilityDto){
		if(facilityDao.findFacilityByName(facilityDto.getFacilityName()) == null && facilityDto.getFacilityName() !=""){

			Facility facility = new Facility();
			Set<FacilityAddress> addressList =new HashSet<FacilityAddress>();
			Set<FacilityContact> contactList =new HashSet<FacilityContact>();
			facility.setFacilityName(facilityDto.getFacilityName());
			facility.setStandingOrders(facilityDto.isStandingOrders());
			facility.setDateCreated(facilityDto.getDateCreated());
			facility.setUserCreated(facilityDto.getUserCreated());

			for(FacilityAddressDto addDto:facilityDto.getFacilityAddresses()){
				FacilityAddress address =new FacilityAddress();
				address.setAddress(addDto.getAddress());
				address.setCity(addDto.getCity());
				address.setState(addDto.getState());
				address.setStreet(addDto.getStreet());
				address.setZipcode(addDto.getZipcode());
				address.setDateCreated(facilityDto.getDateCreated());
				address.setUserCreated(facilityDto.getUserCreated());
				addressList.add(address);
			}

			for(FacilityContactDto cont:facilityDto.getFacilityContacts()){
				FacilityContact contact =new FacilityContact();
				contact.setContact(cont.getContact());
				contact.setContactType(cont.getContactType());
				contact.setDateCreated(facilityDto.getDateCreated());
				contact.setUserCreated(facilityDto.getUserCreated());
				contactList.add(contact);
			}
			facility.setFacilityAddresses(addressList);
			facility.setFacilityContacts(contactList);
			facilityDao.save(facility);
			return "success";
		}else{
			if(facilityDto.getFacilityName() == ""){
				return "Facility Name won't allow blank";
			}else{
				return "Facility Exists";
			}
		}
	}
	
	@Transactional
	public List<FacilityDto> getAllFacilities(){
		return facilityDao.findAllFacilities();
		
	}

}
