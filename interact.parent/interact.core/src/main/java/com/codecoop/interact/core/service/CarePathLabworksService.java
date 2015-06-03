package com.codecoop.interact.core.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.dao.CarePathLabworksDao;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.dao.SbarDaoImpl;
import com.codecoop.interact.core.domain.CarePathLabworks;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.dto.CarePathLabworksDto;
import com.codecoop.interact.core.dto.CarePathLabworksValuesFrmCICDto;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkDto;

/**
 * @author Ramesh
 *
 */

@Service
public class CarePathLabworksService {

	@Autowired
	public CarePathLabworksDao carePathLabworksDao;

	@Autowired
	public PatientEpisodeDao patientEpisodeDao;
    @Autowired
    SbarDaoImpl sbarDao;
	private static final Logger LOG=Logger.getLogger(CarePathLabworksService.class);

	@Transactional
	public String saveOrUpdate(CarePathLabworksDto dto){
		
		CarePathLabworks carePathLabwork=new CarePathLabworks();
		try{
			if(!StringUtils.isEmpty(dto.getId())){
				carePathLabwork = carePathLabworksDao.findById(dto.getId());
				if(carePathLabwork!=null){
				  List<CarePathLabworks> labworksList = carePathLabworksDao.getExistedCarePathLabworksByEpisodeNdSSLId(carePathLabwork.getSbarId(), carePathLabwork.getSignsSymptomsLabworkId());
				  for(CarePathLabworks labwork:labworksList){
					labwork.setEndDate(new Date());
					carePathLabworksDao.saveOrUpdate(labwork);
				  }
				}
			}
			carePathLabwork =new CarePathLabworks(); 
			
			if(dto.getSbarId()==null){
				dto.setSbarId(sbarDao.findByPatientEpisode(patientEpisodeDao.findById(dto.getPatientEpisodeId())).getId());
			}
			carePathLabwork.setSbarId(dto.getSbarId());
			carePathLabwork.setSignsSymptomsLabworkId(dto.getSignsSymptomsLabworkId());
			carePathLabwork.setApproved(dto.getApproved()?true:false);	
			carePathLabwork.setRejected(dto.getRejected()?true:false);	
			carePathLabwork.setFacilityStaffId(dto.getFacilityStaffId());
			carePathLabwork.setStandingOrders(dto.getStandingOrders());
			carePathLabwork.setCarePathCode(dto.getCarePathCode()); 
			carePathLabwork.setStartDate(new Date());
			carePathLabworksDao.saveOrUpdate(carePathLabwork);

		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			return "Failed";
		}
		return "success";

	}

	@Transactional
	public Integer deleteByEpisodeIdNdcarePathCode(Long episodeId,String carePathCode){
		PatientEpisode patientEpisode=patientEpisodeDao.findById(episodeId);
		return carePathLabworksDao.deleteByEpisodeIdNdcarePathCode(sbarDao.findByPatientEpisode(patientEpisode).getId(), carePathCode);
	}

	@Transactional
	public List<CarePathLabworksDto> getCarePathLabworksEntries(Long patientEpisodeId){
		PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
		
		try{
			List<CarePathLabworksDto> list = carePathLabworksDao.getCarePathLabworksEntriesByEpisodeId(sbarDao.findByPatientEpisode(patientEpisode).getId());
			for(CarePathLabworksDto cLabwork:list){
				cLabwork.setCarePathLabworkAppRej(carePathLabworksDao.getCarePathLabworkAppRejUserById(cLabwork.getId()));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	@Transactional
	public List<CarePathLabworksValuesFrmCICDto> getLabworkValuesFromCIC(Long episodeId,Long signSymptomLabworkId){
		PatientEpisode patientEpisode=patientEpisodeDao.findById(episodeId);
		return carePathLabworksDao.getLabworkValuesFromCIC(sbarDao.findByPatientEpisode(patientEpisode).getId(), signSymptomLabworkId);
	}

	@Transactional
	public List<SignsSymptomsLabworkDto> showLabworksNotInCarePathLabworks(Long patientEpisodeId){
		PatientEpisode patientEpisode= patientEpisodeDao.findById(patientEpisodeId);
		Sbar sbar=sbarDao.findByPatientEpisode(patientEpisode);
		return carePathLabworksDao.showLabworksNotInCarePathLabworks(sbar.getId());
	}

	@Transactional
	public List<CarePathLabworksDto> getExistedCarePathLabworks(Long sbarId,String carePathCode){
	
		return carePathLabworksDao.getExistedCarePathLabworks(sbarId,carePathCode);
	}
	
	@Transactional
	public boolean isApprovedOrRejected(Long signSymptomLabworkId,Long sbarId){
		return patientEpisodeDao.isApprovedOrRejected(signSymptomLabworkId,sbarId);
	}

	



}
