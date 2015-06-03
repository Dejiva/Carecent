package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.AcuteCareAttributesValues;

@Component
public class AcuteCareAttibuteValuesDao extends BaseDao<AcuteCareAttributesValues>{
	
	@SuppressWarnings("unchecked")
	public List<AcuteCareAttributesValues> getAcuteCareAttributeValuesByEpisodeId(Long episodeId){
		Criteria crit = getCurrentSession().createCriteria(AcuteCareAttributesValues.class);
		crit.add(Restrictions.eq("pEpisodeId", episodeId));
		return crit.list();
		
	}

}
