package com.codecoop.interact.core.dao;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.CarePath;
import com.codecoop.interact.core.domain.PatientCarePaths;
import com.codecoop.interact.core.domain.PatientEpisode;

@Component
public class PatientCarePathsDaoImpl extends BaseDao<PatientCarePaths> {
	

	public PatientCarePaths findByCPCodeAndP_EpisodeID(CarePath carePath,PatientEpisode patientEpiosode) {
		Criteria crit = getCurrentSession().createCriteria(PatientCarePaths.class);
		crit.add(Restrictions.eq("carePath", carePath));
		crit.add(Restrictions.eq("patientEpisode", patientEpiosode));
		return (PatientCarePaths) crit.uniqueResult();
		
	}
 
}
