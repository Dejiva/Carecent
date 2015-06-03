package com.codecoop.interact.core.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.ChangeInCondition;
import com.codecoop.interact.core.domain.CicDecisionParms;
import com.codecoop.interact.core.domain.SignsSymptomsLabworkAttr;
import com.codecoop.interact.core.dto.ChangeInConditionDataForEvaluationDto;
import com.codecoop.interact.core.dto.CicAttrRefCarePathDto;
import com.codecoop.interact.core.enumpack.ChangeInConditionType;


@Component
@SuppressWarnings("unchecked")
public class CICDecisionParmsDaoImpl extends BaseDao<CicDecisionParms> {
	

	
	public List<CicDecisionParms> findBySignSymptomLabAttrId(Long signsSymptomsLabworkAttrId){
		
		Criteria criteria = getCurrentSession().createCriteria(CicDecisionParms.class);
		criteria.add(Restrictions.eq("signsSymptomsLabworkAttrId", signsSymptomsLabworkAttrId));
		List<CicDecisionParms> cicDecisionParms =  criteria.list();
		return cicDecisionParms;
		
	}
	

}

