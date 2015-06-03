package com.codecoop.interact.core.dao;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.CarePath;
import com.codecoop.interact.core.domain.PatientCarePathTracker;
import com.codecoop.interact.core.domain.PatientEpisode;

@Component
public class PatientCarePathTrackerDaoImpl extends BaseDao<PatientCarePathTracker> {

	public PatientCarePathTracker findByCPathCodeAndStepId(String carePathAttrCode, int stepId,PatientEpisode patientEpisode ,CarePath carePath) {
	
		Criteria crit = getCurrentSession().createCriteria(PatientCarePathTracker.class);
		crit.add(Restrictions.eq("carePathAttrCode", carePathAttrCode));
		crit.add(Restrictions.eq("stepId",stepId));
		crit.add(Restrictions.eq("patientEpisode", patientEpisode));
		crit.add(Restrictions.eq("carePath", carePath));
		return (PatientCarePathTracker) crit.uniqueResult();

	}
	
	}
