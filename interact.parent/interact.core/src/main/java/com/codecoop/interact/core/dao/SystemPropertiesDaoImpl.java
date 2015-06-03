package com.codecoop.interact.core.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.SystemProperties;

@Component
public class SystemPropertiesDaoImpl extends BaseDao<SystemProperties>{

	
	public SystemProperties findByPropertyName(String propertyName) {
		Criteria crit = getCurrentSession().createCriteria(SystemProperties.class);
		crit.add(Restrictions.eq("propertyName", propertyName));
		return (SystemProperties) crit.uniqueResult();
		
	}

}
