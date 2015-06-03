package com.codecoop.interact.core.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.codecoop.interact.core.domain.CarePath;

@Component
public class CarePathDaoImpl extends BaseDao<CarePath> {
	
	public String getCarePathLocation(String carePathCode){
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(CarePath.class);
		criteria.add(Restrictions.eq("carePathCode", carePathCode));
		@SuppressWarnings("unchecked")
		List<CarePath> carePaths = criteria.list();
		if(!CollectionUtils.isEmpty(carePaths)){
			return carePaths.get(0).getCarePathLocation();
		}
		return null;
	}
	public CarePath findByCarePathCode(String carePathCode) {
		Criteria crit = getCurrentSession().createCriteria(CarePath.class);
		crit.add(Restrictions.eq("carePathCode", carePathCode));
		return (CarePath) crit.uniqueResult();
		
	}
 
}
