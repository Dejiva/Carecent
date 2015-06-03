package com.codecoop.interact.core.service;

import java.util.ArrayList;
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

import com.codecoop.interact.core.dao.AdmissionAttributesDao;
import com.codecoop.interact.core.dao.AdmissionAttributesValuesDao;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.domain.AdmissionAttributes;
import com.codecoop.interact.core.domain.AdmissionAttributesValues;
import com.codecoop.interact.core.dto.AdmissionAttributeValuesDto;
import com.codecoop.interact.core.dto.AdmissionAttributesDto;
import com.codecoop.interact.core.dto.AdmissionAttributesValuesDto;
import com.codecoop.interact.core.dto.AdmissionFormAttribNdValuesDto;

@Service
public class AttributesService {

	private static final Logger LOG = Logger.getLogger(AttributesService.class);

	@Autowired
	AdmissionAttributesDao admissionAttributesDao;

	@Autowired
	AdmissionAttributesValuesDao admissionAttributesValuesDao;

	@Autowired
	PatientEpisodeDao	patientEpisodeDao; 

	/**
	 * 
	 * @param valuesDto
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveAdmissionAttributesValues(
			AdmissionAttributesValuesDto valuesDto) {
		if (valuesDto != null && valuesDto.getpEpisodeId() != null
				&& valuesDto.getAttributeValuesMap() != null) {
			List<AdmissionAttributesValues> attrValues = new ArrayList<AdmissionAttributesValues>();

			Long pEpisodeId = valuesDto.getpEpisodeId();
			Map<String, String> valuesMap = valuesDto.getAttributeValuesMap();

			Set<String> attrCodeSet = valuesMap.keySet();
			List<AdmissionAttributes> admissionAtrributes = getAllAdmissionAttributes();

			for (String attributeCode : attrCodeSet) {
				AdmissionAttributes attr = new AdmissionAttributes();
				attr.setAttributeCode(attributeCode);

				AdmissionAttributesValues attrValue = new AdmissionAttributesValues();
				List<AdmissionAttributesValues> admissionAttibValList = patientEpisodeDao.findById(pEpisodeId).getAdmissionAttributesValuesList();
				if(admissionAttibValList.size() > 0 ){
					for(AdmissionAttributesValues admiAttribVal:admissionAttibValList){
						attrValue = admiAttribVal;

						/*if(attrValue.getAdmissionAttributes().getId() ==  getAdmissionAttribute(admissionAtrributes, attributeCode).getId()){
							attrValue.setAttributeValue(valuesMap.get(attributeCode));
							attrValue.setpEpisodeId(pEpisodeId);
							attrValue.setAdmissionAttributes(getAdmissionAttribute(admissionAtrributes, attributeCode));
							attrValues.add(attrValue);
						}*/// TODO: need to uncomment
					}

				}else{
					attrValue.setAttributeValue(valuesMap.get(attributeCode));
					attrValue.setpEpisodeId(pEpisodeId);
					//					attrValue.setAdmissionAttributes(getAdmissionAttribute(admissionAtrributes, attributeCode)); TODO: need to uncomment
					attrValues.add(attrValue);
				}

			}

			admissionAttributesValuesDao.saveAdmissionAttributeValues(attrValues);
		}
	}

	private AdmissionAttributes getAdmissionAttribute(
			List<AdmissionAttributes> admissionAtrributes, String attributeCode) {
		for(AdmissionAttributes adminAttr : admissionAtrributes){
			if(attributeCode.equals(adminAttr.getAttributeCode())){
				return adminAttr;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<AdmissionAttributes> getAllAdmissionAttributes() {
		List<AdmissionAttributes> admissionAtrributes = admissionAttributesDao.findAll();
		return admissionAtrributes;
	}


	/**
	 * 
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<String> getAllAdmissionAttributeCodes() {
		List<AdmissionAttributes> admissionAtrributes = getAllAdmissionAttributes();
		List<String> attributeCodeList = new ArrayList<String>();
		if(!CollectionUtils.isEmpty(admissionAtrributes)){
			for(AdmissionAttributes attribute : admissionAtrributes){
				attributeCodeList.add(attribute.getAttributeCode());
			}
		}
		return attributeCodeList;
	}

	/**
	 * 
	 * @param pEpisodeId
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public Map<String, String> getPatientAdmissionAttributeValuesAsMap(
			Long pEpisodeId) {

		Map<String, String> attrValuesMap = new HashMap<String, String>();
		if (pEpisodeId != null) {
			List<AdmissionAttributesValues> admissionAttrValues = admissionAttributesValuesDao
					.findAdmissionAttributesValuesByEpisodeId(pEpisodeId, true);
			for (AdmissionAttributesValues admissionAttrValue : admissionAttrValues) {
				/*attrValuesMap.put(admissionAttrValue.getAdmissionAttributes()
						.getAttributeCode(), admissionAttrValue
						.getAttributeValue());*///TODO : need to uncomment
			}
		}

		return attrValuesMap;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<AdmissionAttributesDto> findAdmissionAttributeByFormField(String fieldName){
		return admissionAttributesDao.findAdmissionAttributeByFormField(fieldName);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public void saveAdmissionAttributeValues(List<AdmissionAttributeValuesDto> attrValues,Long patientEpisodeId){
		admissionAttributesValuesDao.deleteAdmissionAttributeValuesByPatientEpisodeId(patientEpisodeId);
		for(AdmissionAttributeValuesDto dto:attrValues){
			AdmissionAttributesValues value=new AdmissionAttributesValues();
			value.setAdmissionAttributeId(dto.getAdmissionAttributeId());
			value.setAttributeValue(dto.getAttributeValue());
			value.setpEpisodeId(patientEpisodeId);
			admissionAttributesValuesDao.saveAdmissionAttributeValues(value);
		}

	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<AdmissionFormAttribNdValuesDto> getAdmissionAttribValuesByPatientEpisodeId(Long pEpisodeId){
		return admissionAttributesValuesDao.getAdmissionAttribValuesByPatientEpisodeId(pEpisodeId);
	}

}
