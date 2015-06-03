package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.codecoop.interact.core.domain.AdmissionAttributesValues;
import com.codecoop.interact.core.dto.AdmissionFormAttribNdValuesDto;

@Component
@SuppressWarnings("unchecked")
public class AdmissionAttributesValuesDao extends BaseDao<AdmissionAttributesValues>{

	/**
	 * 
	 * @param episodeId
	 * @param loadAttribute
	 * @return
	 */
	public List<AdmissionAttributesValues> findAdmissionAttributesValuesByEpisodeId(
			Long episodeId, boolean loadAttribute) {
		Criteria crit = getCurrentSession().createCriteria(
				AdmissionAttributesValues.class);
		crit.add(Restrictions.eq("pEpisodeId", episodeId));

		List<AdmissionAttributesValues> admissionAttrValuesList = crit.list();
		if (loadAttribute && !CollectionUtils.isEmpty(admissionAttrValuesList)) {
			for (AdmissionAttributesValues admissionAttrValue : admissionAttrValuesList) {
				//				admissionAttrValue.getAdmissionAttributes(); TODO need To uncomment
			}
		}
		return admissionAttrValuesList;
	}

	/**
	 * 
	 * @param admissionAttrValues
	 */
	public void saveAdmissionAttributeValues(
			List<AdmissionAttributesValues> admissionAttrValues) {
		if (!CollectionUtils.isEmpty(admissionAttrValues)) {
			for (AdmissionAttributesValues admissionAttrValue : admissionAttrValues) {
				saveOrUpdate(admissionAttrValue);
			}
		}
	}

	/**
	 * 
	 * @param attributeCode
	 * @param episodeId
	 * @return
	 */
	public AdmissionAttributesValues findAdmissionAttrValueByAttributeCodeAndEpisodeId(
			String attributeCode, Long episodeId) {
		Criteria crit = getCurrentSession().createCriteria(
				AdmissionAttributesValues.class);
		crit.add(Restrictions.eq("pEpisodeId", episodeId));
		crit.createAlias("admissionAttributes", "admissionAttributes");
		crit.add(Restrictions.eq("admissionAttributes.attributeCode",
				attributeCode));

		return (AdmissionAttributesValues) crit.uniqueResult();
	}

	public void saveAdmissionAttributeValues(AdmissionAttributesValues admissionAttrValue){
		saveOrUpdate(admissionAttrValue);

	}

	public int deleteAdmissionAttributeValuesByPatientEpisodeId(Long patientEpisodeId){
		String sql = "delete from ADMISSION_ATTRIBUTES_VALUES where P_EPISODE_ID=:patientEpisodeId";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("patientEpisodeId", patientEpisodeId);
		return query.executeUpdate();
	}

	public List<AdmissionFormAttribNdValuesDto> getAdmissionAttribValuesByPatientEpisodeId(Long pEpisodeId){
		String sql = "select attr.FORM_ATTRIB as attibute,attr.VALUE_TYPE as type,attrVal.ATTRIBUTE_VALUE as attibValue from ADMISSION_ATTRIBUTES_VALUES attrVal,ADMISSION_ATTRIBUTES attr where attrVal.ADMISSION_ATTRIBUTE_ID=attr.ID and attrVal.P_EPISODE_ID=:pEpisodeId";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("pEpisodeId", pEpisodeId);
		query.addScalar("attibute");
		query.addScalar("type");
		query.addScalar("attibValue");
		query.setResultTransformer(Transformers.aliasToBean(AdmissionFormAttribNdValuesDto.class));
		return (List<AdmissionFormAttribNdValuesDto>)query.list();
	}

}
