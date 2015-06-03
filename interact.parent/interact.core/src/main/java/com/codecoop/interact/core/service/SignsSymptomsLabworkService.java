package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.codecoop.interact.core.dao.CarePathTrackerAttrDaoImpl;
import com.codecoop.interact.core.dao.ChangeInConditionDao;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.dao.SbarDaoImpl;
import com.codecoop.interact.core.dao.SignsSymptomsLabworkAttrDao;
import com.codecoop.interact.core.dao.SignsSymptomsLabworkDao;
import com.codecoop.interact.core.domain.ChangeInCondition;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.SignsSymptomsLabwork;
import com.codecoop.interact.core.domain.SignsSymptomsLabworkAttr;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkAttrByGroupDto;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkAttrDto;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkDto;
import com.codecoop.interact.core.enumpack.ChangeInConditionType;

@Service
public class SignsSymptomsLabworkService {

	private static final Logger LOG = Logger.getLogger(SignsSymptomsLabworkService.class);
	@Autowired
	private SignsSymptomsLabworkDao signsSymptomsLabworkDao;

	@Autowired
	private SignsSymptomsLabworkAttrDao signsSymptomsLabworkAttrDao;

	@Autowired
	CarePathTrackerAttrDaoImpl carePathTrackerAttrDao;

	@Autowired
	ChangeInConditionDao changeInConditionDao;
	@Autowired
	PatientEpisodeDao patientEpisodeDao;
	@Autowired
	private SbarDaoImpl sbarDao;
	@Transactional
	public List<SignsSymptomsLabworkDto> getSignsSymptomsLabWorkByFlagType(ChangeInConditionType type){

		List<SignsSymptomsLabwork> signsSymptomsLabWorkList = signsSymptomsLabworkDao.getSignSymptomsByFalgType(type);

		List<SignsSymptomsLabworkDto> signsSymptomsDtoList = new ArrayList<SignsSymptomsLabworkDto>();

		for (SignsSymptomsLabwork signsSymptomsLabwork : signsSymptomsLabWorkList) {

			SignsSymptomsLabworkDto signsSymptomsLabworkDto = new SignsSymptomsLabworkDto();
			BeanUtils.copyProperties(signsSymptomsLabwork,signsSymptomsLabworkDto);
			signsSymptomsDtoList.add(signsSymptomsLabworkDto);
		}
		return signsSymptomsDtoList;
	}


	@Transactional
	public List<SignsSymptomsLabworkAttrDto> getSignSymptomsLabWorkAttributesByParentId(Long parentId,Long patientEpisodeId){
		List<SignsSymptomsLabworkAttr> attrList = signsSymptomsLabworkAttrDao.findByParentId(parentId);
		List<SignsSymptomsLabworkAttrDto> signsSymptomsDtoList = new ArrayList<SignsSymptomsLabworkAttrDto>();
		if(!CollectionUtils.isEmpty(attrList)){
			for(SignsSymptomsLabworkAttr attr : attrList){
				SignsSymptomsLabworkAttrDto signsSymptomsLabworkAttrDto = new SignsSymptomsLabworkAttrDto();
				BeanUtils.copyProperties(attr, signsSymptomsLabworkAttrDto);
				if(patientEpisodeId !=null){
					PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
					ChangeInCondition cic=changeInConditionDao.findBySignsSymptomsLabworkAttrId(sbarDao.findByPatientEpisode(patientEpisode).getId(), attr.getId());
					if(cic !=null){
						signsSymptomsLabworkAttrDto.setAttrValue(cic.getChangeInConditionValue()); 
					}
				}
				signsSymptomsDtoList.add(signsSymptomsLabworkAttrDto);
			}
		}
		return signsSymptomsDtoList;
	}

	@Transactional
	public SignsSymptomsLabworkAttrDto getSignSymptomsLabWorkAttributesById(Long attrId){
		SignsSymptomsLabworkAttr attr = signsSymptomsLabworkAttrDao.findById(attrId);

		SignsSymptomsLabworkAttrDto signsSymptomsLabworkAttrDto = new SignsSymptomsLabworkAttrDto();
		BeanUtils.copyProperties(attr, signsSymptomsLabworkAttrDto);

		return signsSymptomsLabworkAttrDto;
	}

	@Transactional
	public SignsSymptomsLabworkDto getSignSymptomsLabWorkById(Long id){
		SignsSymptomsLabwork signsSymptomsLabwork = signsSymptomsLabworkDao.findById(id);

		SignsSymptomsLabworkDto signsSymptomsLabworkDto = new SignsSymptomsLabworkDto();
		BeanUtils.copyProperties(signsSymptomsLabwork,signsSymptomsLabworkDto);

		return signsSymptomsLabworkDto;
	}

	@Transactional
	public List<SignsSymptomsLabworkAttrByGroupDto> getSignsSymptomsLabWorkByFlagTypeOnPatient(Long patientEpisodeId,
			ChangeInConditionType labworkFlag) {
		PatientEpisode patientEpisode=patientEpisodeDao.findById(patientEpisodeId);
		List<SignsSymptomsLabworkAttrByGroupDto> signsSymptomsLabworkAttrByGroupList=new ArrayList<SignsSymptomsLabworkAttrByGroupDto>();
		List<SignsSymptomsLabworkDto> signsSymptomsLabworklist=getSignsSymptomsLabWorkByFlagType(ChangeInConditionType.LABWORK_FLAG);
		List<String> carePathLabWorkAttrCodes=carePathTrackerAttrDao.findAllLabWorkForPatient(sbarDao.findByPatientEpisode(patientEpisode).getId());
		for(SignsSymptomsLabworkDto signsSymptomsLabworkDto:signsSymptomsLabworklist){
			if(StringUtils.isNotEmpty(signsSymptomsLabworkDto.getRefCarePathCode())){
				if(carePathLabWorkAttrCodes.indexOf(signsSymptomsLabworkDto.getRefCarePathCode())!=-1){
					SignsSymptomsLabworkAttrByGroupDto signsSymptomsLabworkAttrByGroup=new SignsSymptomsLabworkAttrByGroupDto();
					signsSymptomsLabworkAttrByGroup.setSignSymtomLabworkId(signsSymptomsLabworkDto.getId());
					signsSymptomsLabworkAttrByGroup.setSignSymtomLabworkName(signsSymptomsLabworkDto.getSignsSymptomsLabworkName());
					signsSymptomsLabworkAttrByGroup.setSignsSymptomsLabworkAttrlist(getSignSymptomsLabWorkAttributesByParentId(signsSymptomsLabworkDto.getId(),patientEpisodeId));
				}
			}
		}
		return signsSymptomsLabworkAttrByGroupList;
	}

	@Transactional
	public SignsSymptomsLabworkDto getSignSympLabworkIdByCPLWCode(String carePathLabWorkCode){
		SignsSymptomsLabwork ssl=signsSymptomsLabworkDao.getSignSympLabworkIdByCPLWCode(carePathLabWorkCode);
		SignsSymptomsLabworkDto dto = new SignsSymptomsLabworkDto();
		if(ssl != null){
			dto.setId(ssl.getId());
			dto.setLabworkFlag(ssl.getLabworkFlag());
			dto.setCarepathLabworkCode(ssl.getCarepathLabworkCode());
		}
		return dto;
	}

}
