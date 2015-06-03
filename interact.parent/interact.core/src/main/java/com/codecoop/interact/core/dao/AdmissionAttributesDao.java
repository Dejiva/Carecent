package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.domain.AdmissionAttributes;
import com.codecoop.interact.core.dto.AdmissionAttributesDto;

@Component
public class AdmissionAttributesDao extends BaseDao<AdmissionAttributes>{
	
	/**
	 * 
	 * @return
	 */
	public List<AdmissionAttributes> findAllAdmissionAttributes() {
		return findAll();
	}
	
	/**
	 * 
	 * @param attributeCode
	 * @return
	 */
	public AdmissionAttributes findAdmissionAttributeByAttributeCode(
			String attributeCode) {
		if (StringUtils.isEmpty(attributeCode)) {
			return null;
		}
		Criteria crit = getCurrentSession().createCriteria(AdmissionAttributes.class);
		crit.add(Restrictions.eq("attributeCode", attributeCode));
		return (AdmissionAttributes)crit.uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmissionAttributesDto> findAdmissionAttributeByFormField(String fieldName){
		String sql = "select ID as id,ATTRIBUTE_NAME as attributeName,ATTRIBUTE_CODE as attributeCode,VALUE_TYPE as valueType, CODE_EXPLAINED as codeExplained,FORM_ATTRIB as formAttribute from ADMISSION_ATTRIBUTES where  FORM_ATTRIB=:fieldName";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("fieldName", fieldName);
		query.addScalar("id", LongType.INSTANCE);
		query.addScalar("attributeName");
		query.addScalar("attributeCode");
		query.addScalar("valueType");
		query.addScalar("codeExplained");
		query.addScalar("formAttribute");
		query.setResultTransformer(Transformers.aliasToBean(AdmissionAttributesDto.class));
		return (List<AdmissionAttributesDto>)query.list();
		
	}
			
}
