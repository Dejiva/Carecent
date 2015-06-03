package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.States;

@Component
public class StatesDao extends BaseDao<States>{
	
	@SuppressWarnings("unchecked")
	public List<States> getStates(){
		Criteria crit = getCurrentSession().createCriteria(States.class);
		crit.list().size();
		return crit.list();
	}
}
