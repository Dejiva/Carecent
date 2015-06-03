package com.codecoop.interact.core.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.MedicalReconciliation;
import com.codecoop.interact.core.domain.MessageAlertDetails;
import com.codecoop.interact.core.domain.SbarSymptoms;
import com.codecoop.interact.sbar.SbarReleventSymptoms;

@Component
public class SbarSymptomsDaoImpl extends BaseDao<SbarSymptoms> {

	public SbarSymptoms findBySbarIdAndCode(Long sbarId, String code) {
		Criteria crit = getCurrentSession().createCriteria(SbarSymptoms.class);
		crit.add(Restrictions.eq("sbarId", sbarId));
		crit.add(Restrictions.eq("code", code));
		return (SbarSymptoms)crit.uniqueResult();	
	}

}
