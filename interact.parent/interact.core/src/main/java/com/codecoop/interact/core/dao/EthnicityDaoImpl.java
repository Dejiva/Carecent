package com.codecoop.interact.core.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.Ethnicity;

@Component
public class EthnicityDaoImpl extends BaseDao<Ethnicity>{

public Ethnicity  findByName(String ethnicity_name){
		
		Criteria crit = getCurrentSession().createCriteria(Ethnicity.class);
		crit.add(Restrictions.eq("Ethnicity_Name", ethnicity_name));
		return (Ethnicity) crit.uniqueResult();
	}
	
	
	

	
}
