package com.codecoop.interact.core.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.CarePath;
import com.codecoop.interact.core.domain.CarePathTracker;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.SbarNotes;
import com.codecoop.interact.core.dto.carepath.CarePathDto;

@Component
public class CarePathTrackerDaoImpl extends BaseDao<CarePathTracker> {

	public CarePathTracker findByStepId(Sbar sbar, CarePath carePath, int stepId){
		
		String sql = "select CT.* from CARE_PATH_TRACKER CT where CT.SBAR_ID=:sbar and CT.CARE_PATH_ID=:carePathId and CT.STEP_ID=:stepId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbar", sbar.getId());
		query.setParameter("carePathId", carePath.getId());
		query.setParameter("stepId", stepId);
		query.addEntity(CarePathTracker.class);
		return (CarePathTracker) query.uniqueResult();
		
//		Criteria crit = getCurrentSession().createCriteria(CarePathTracker.class);
//		crit.add(Restrictions.eq("stepId", stepId));
//		crit.add(Restrictions.eq("patientEpisode", patientEpisode));
//		crit.add(Restrictions.eq("carePath", carePath));
//		return (CarePathTracker)crit.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<CarePathTracker> findAll(Sbar sbar, CarePath carePath){
		String sql = "select CT.* from CARE_PATH_TRACKER CT where CT.SBAR_ID=:sbar and CT.CARE_PATH_ID=:carePathId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbar", sbar.getId());
		query.setParameter("carePathId", carePath.getId());
		query.addEntity(CarePathTracker.class);
		return query.list();

	}
	public Integer findLastStepSequence(Sbar sbar, CarePath carePath){
		Criteria crit = getCurrentSession().createCriteria(CarePathTracker.class);
		crit.add(Restrictions.eq("sbar", sbar));
		crit.add(Restrictions.eq("carePath", carePath));
		crit.setProjection(Projections.max("exceSequence"));
		return (Integer)crit.uniqueResult();
	}
	public CarePathTracker findByExceSequence(
			Sbar sbar, CarePath carePath, int exceSequence) {
		String sql = "select CT.* from CARE_PATH_TRACKER CT where CT.SBAR_ID=:sbar and CT.CARE_PATH_ID=:carePathId and CT.EXCE_SEQUENCE=:exceSequence";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbar", sbar.getId());
		query.setParameter("carePathId", carePath.getId());
		query.setParameter("exceSequence", exceSequence);
		query.addEntity(CarePathTracker.class);
		return (CarePathTracker) query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<String> findStepTypesForShowInSbar(Sbar sbar) {
		String sql ="select distinct CT.STEP_TYPE from CARE_PATH_TRACKER CT where CT.SBAR_ID=:sbar and CT.SHOW_IN_SBAR=1";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbar", sbar.getId());
	//	query.addEntity(CarePathTracker.class);
		return  query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<CarePathTracker> findByStepType(Sbar sbar, String stepType) {
		
		String sql ="select CT.* from CARE_PATH_TRACKER CT where CT.SBAR_ID=:sbar and CT.SHOW_IN_SBAR=1 and CT.STEP_TYPE=:stepType";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbar", sbar.getId());
		query.setParameter("stepType",stepType);
		query.addEntity(CarePathTracker.class);
		return  query.list();
	}
	@SuppressWarnings("unchecked")
	public List<CarePathTracker> getNotifyDoctorRecords(PatientEpisode patientEpisode) {
		// TODO Auto-generated method stub
		Criteria crit = getCurrentSession().createCriteria(CarePathTracker.class);
		crit.add(Restrictions.eq("patientEpisode", patientEpisode));
		return (List<CarePathTracker>)crit.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CarePathTracker> findAllSteps(Sbar sbar, CarePath carePath) {
		
		String sql ="select CT.* from CARE_PATH_TRACKER CT where CT.SBAR_ID=:sbar and CT.CARE_PATH_ID=:carePathId order by CT.EXCE_SEQUENCE";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbar", sbar.getId());
		query.setParameter("carePathId", carePath.getId());
		query.addEntity(CarePathTracker.class);
		return  query.list();
	}
	@SuppressWarnings("unchecked")
	public List<CarePathTracker> findAllStepsInDesc(Sbar sbar, CarePath carePath) {
		String sql ="select CT.* from CARE_PATH_TRACKER CT where CT.SBAR_ID=:sbar and CT.CARE_PATH_ID=:carePathId order by CT.EXCE_SEQUENCE desc";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbar", sbar.getId());
		query.setParameter("carePathId", carePath.getId());
		query.addEntity(CarePathTracker.class);
		return  query.list();
	}
	@SuppressWarnings("unchecked")
	public List<CarePath> findAllRuningCarePath(Long  sbarId) {
		String sql ="select distinct CP.* from CARE_PATH_TRACKER CT ,CARE_PATH CP where CT.SBAR_ID=:sbarId and CT.CARE_PATH_ID=CP.ID";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId", sbarId);
		query.addEntity(CarePath.class);
		return  query.list();

	}
	
	
}
