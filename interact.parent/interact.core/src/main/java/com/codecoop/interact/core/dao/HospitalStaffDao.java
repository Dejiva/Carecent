package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.HospitalStaff;

@Component
public class HospitalStaffDao extends BaseDao<HospitalStaff> {
	
	
	
	@SuppressWarnings("unchecked")
	public List<HospitalStaff> findHospitalStaffByPatientEncounter(Long pepisodeId){
		Criteria crit = getCurrentSession().createCriteria(HospitalStaff.class);
		crit.add(Restrictions.eq("patientEncounterId", pepisodeId));
		return (List<HospitalStaff>) crit.list();
	}

}
