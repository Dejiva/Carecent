package com.codecoop.interact.web.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecoop.interact.core.dto.FacilityAddressDto;
import com.codecoop.interact.core.dto.FacilityContactDto;
import com.codecoop.interact.core.dto.FacilityDto;
import com.codecoop.interact.core.service.ManageFacilityService;
import com.codecoop.interact.web.model.FacilityModel;

@Controller
@RequestMapping("/facility")
public class FacilityManagementController {
	private static final Logger LOG = Logger.getLogger(FacilityManagementController.class);
	@Autowired
	ManageFacilityService manageFacilityService;

	@RequestMapping(value="/create",method={RequestMethod.GET})
	public @ResponseBody FacilityModel createFacility(HttpServletRequest request,@ModelAttribute("facilityModel") FacilityModel facilityModel){

		FacilityModel formData = facilityModel;
		String LogedUser = request.getUserPrincipal().getName();
		FacilityDto facilityDto = new FacilityDto();
		Set<FacilityAddressDto> facilityAddressDtoList = new HashSet<FacilityAddressDto>();
		Set<FacilityContactDto> facilityContactDtoList = new HashSet<FacilityContactDto>();
		try{
			facilityDto.setFacilityName(formData.getFacilityName());
			facilityDto.setStandingOrders(formData.getStandingOrders().equalsIgnoreCase("true")?true:false);
			facilityDto.setDateCreated(new Date());
			facilityDto.setUserCreated(LogedUser);
			FacilityAddressDto facilityAddressDto = new FacilityAddressDto();
			FacilityContactDto facilityContactDto = new FacilityContactDto();

			facilityAddressDto.setAddress(formData.getFaddress());
			facilityAddressDto.setCity(formData.getFcity());
			facilityAddressDto.setState(formData.getFstate());
			facilityAddressDto.setStreet(formData.getFstreet());
			facilityAddressDto.setZipcode(formData.getFzipcode());
			facilityAddressDtoList.add(facilityAddressDto);

			facilityContactDto.setContact(formData.getFcontact());
		//	facilityContactDto.setContactType(formData.getFcontactType());
			facilityContactDto.setContactType("Mobile/Phone No");
			facilityContactDtoList.add(facilityContactDto);

			facilityDto.setFacilityAddresses(facilityAddressDtoList);
			facilityDto.setFacilityContacts(facilityContactDtoList);

			String status = manageFacilityService.saveFacility(facilityDto);
			if(status.equalsIgnoreCase("success")){
				LOG.info("Facility Was created with Name:"+formData.getFacilityName()+"Standing Order:"+facilityDto.isStandingOrders());
				facilityModel.setFstatus("facility registered successfully");
			}else if(status.equalsIgnoreCase("Facility Exists")){
				facilityModel.setFstatus("Facility Already Exists");	
			}else{
				facilityModel.setFstatus(status);
			}
			return facilityModel;

		}catch(Exception e){
			facilityModel.setFstatus("Facility Registeration Failed");
			LOG.error("Error while creating Facility.....", e);
			return facilityModel;
		}

	}

	@RequestMapping(value="/update",method={RequestMethod.GET})
	public void updateFacility(){

	}

	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public void deleteFacility(){

	}

}
