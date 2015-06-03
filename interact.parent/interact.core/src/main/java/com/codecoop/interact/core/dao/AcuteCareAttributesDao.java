package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.AcuteCareAttributes;

@Component
public class AcuteCareAttributesDao extends BaseDao<AcuteCareAttributes>{
	
	@SuppressWarnings("unchecked")
	public List<AcuteCareAttributes> getAttibuteDetailByFormAttrib(String attrib){
		Criteria crit = getCurrentSession().createCriteria(AcuteCareAttributes.class);
		crit.add(Restrictions.eq("formAttrib", attrib));
		return crit.list();
	}
}
