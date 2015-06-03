package com.codecoop.interact.core.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.ChangeInCondition;
import com.codecoop.interact.core.dto.ChangeInConditionDataForEvaluationDto;
import com.codecoop.interact.core.dto.CicAttrRefCarePathDto;
import com.codecoop.interact.core.enumpack.ChangeInConditionType;


@Component
@SuppressWarnings("unchecked")
public class ChangeInConditionDao extends BaseDao<ChangeInCondition> {
	

	
	public List<ChangeInCondition> getChangeInCondByAttrId(Long signsSymptomsLabworkAttrId){
		
		String sql = "select CIA.* from  CHANGE_IN_CONDITION CIA where CIA.SIGNS_SYMPTOMS_LABWORK_ATTR_ID=:signsSymptomsLabworkAttrId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("signsSymptomsLabworkAttrId",signsSymptomsLabworkAttrId);
		query.addEntity(ChangeInCondition.class);
		return query.list();
		
		//Criteria criteria = getCurrentSession().createCriteria(ChangeInCondition.class);
		//criteria.add(Restrictions.eq("signsSymptomsLabworkAttrId", attrId));
		//List<ChangeInCondition> changeInCondList =  criteria.list();
		//return changeInCondList;
		
	}
	
	public List<ChangeInCondition> getChangeInCondByAttrIdAndStartedDate(Long signsSymptomsLabworkAttrId,Date startedDate){
		
		
		String sql = "select CIA.* from  CHANGE_IN_CONDITION CIA where CIA.SIGNS_SYMPTOMS_LABWORK_ATTR_ID=:signsSymptomsLabworkAttrId and CIA.STARTED_DATE >=:startedDate";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("signsSymptomsLabworkAttrId", signsSymptomsLabworkAttrId);
		query.setParameter("startedDate", startedDate);
		query.addEntity(ChangeInCondition.class);
		return query.list();
		
		//Criteria criteria = getCurrentSession().createCriteria(ChangeInCondition.class);
		//criteria.add(Restrictions.eq("signsSymptomsLabworkAttrId", attrId));
		//criteria.add(Restrictions.ge("startedDate", startedDate));
		//List<ChangeInCondition> changeInCondList =  criteria.list();
		//return changeInCondList;
		
	}
	
	public List<ChangeInCondition> getChangeinCondByEpisodeId(Long sbarId){
		
		String sql = "select CIA.* from  CHANGE_IN_CONDITION CIA where CIA.SBAR_ID=:sbarId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId", sbarId);
		query.addEntity(ChangeInCondition.class);
		return query.list();
		
		//Criteria criteria = getCurrentSession().createCriteria(ChangeInCondition.class);
		//criteria.add(Restrictions.eq("patientEpisodeId", episodeId));
		//List<ChangeInCondition> changeInCondList =  criteria.list();
		//return changeInCondList;
	}
	
	public List<ChangeInCondition> getChangeinCondByEpisodeIdAndSymptomId(Long sbarId, Long symptomId){
		
		String sql = "select c.* from CHANGE_IN_CONDITION c, SIGNS_SYMPTOMS_LABWORK_ATTR sa where c.SBAR_ID=:sbarId and c.SIGNS_SYMPTOMS_LABWORK_ATTR_ID=sa.id and sa.parent_id=:symptomId";
		SQLQuery qry = getCurrentSession().createSQLQuery(sql);
		qry.setParameter("sbarId", sbarId);
		qry.setParameter("symptomId", symptomId);
		qry.addEntity(ChangeInCondition.class);
		return qry.list();
	}
	
	public List<ChangeInCondition> getChangeinCondByEpisodeIdAndType(Long sbarId, ChangeInConditionType type){
		String sql = "select c.* from CHANGE_IN_CONDITION c, SIGNS_SYMPTOMS_LABWORK_ATTR sa, SIGNS_SYMPTOMS_LABWORK s where c.SBAR_ID=:sbarId and c.SIGNS_SYMPTOMS_LABWORK_ATTR_ID=sa.id  and sa.parent_id=s.id  ";
		switch (type) {
		case SIGN_FLAG:
			sql += " and sign_flag=true ";
			break;
		case SYMPTOM_FLAG:
			sql += " and symptom_flag=true ";
			break;
		case LABWORK_FLAG:
			sql += " and labwork_flag=true ";
			break;
		}
		SQLQuery qry = getCurrentSession().createSQLQuery(sql);
		qry.setParameter("sbarId", sbarId);
		qry.addEntity(ChangeInCondition.class);
		return qry.list();
	}
	
	public ChangeInCondition findBySignsSymptomsLabworkAttrId(Long sbarId,Long signsSymptomsLabworkAttrId){
		
		String sql = "select CIA.* from  CHANGE_IN_CONDITION CIA where CIA.SIGNS_SYMPTOMS_LABWORK_ATTR_ID=:signsSymptomsLabworkAttrId and CIA.SBAR_ID =:sbarId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("signsSymptomsLabworkAttrId", signsSymptomsLabworkAttrId);
		query.setParameter("sbarId", sbarId);
		query.addEntity(ChangeInCondition.class);
		return (ChangeInCondition)query.uniqueResult();
		
		//Criteria criteria = getCurrentSession().createCriteria(ChangeInCondition.class);
		//criteria.add(Restrictions.eq("patientEpisodeId", patientEpisodeId));
		//criteria.add(Restrictions.eq("signsSymptomsLabworkAttrId", signsSymptomsLabworkAttrId));
		//return (ChangeInCondition)criteria.uniqueResult();
	}
	
	public void deleteChangeInConditionRecordByEpisodeAndSymptomAttrId(Long sbarId,Long signsSymptomsLabworkAttrId){
		String sql = "delete from CHANGE_IN_CONDITION where SBAR_ID=:sbarId and SIGNS_SYMPTOMS_LABWORK_ATTR_ID=:signsSymptomsLabworkAttrId";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId", sbarId);
		query.setParameter("signsSymptomsLabworkAttrId", signsSymptomsLabworkAttrId);
		query.executeUpdate();
	}
	
	public List<CicAttrRefCarePathDto> getSuggestedCarePathsForPatientChangeInCondition(Long sbarId){
		String sql = "select distinct S.SIGNS_SYMPTOMS_LABWORK_NAME attrName, S.REF_CARE_PATH_CODE refCarePathCode from CHANGE_IN_CONDITION C, SIGNS_SYMPTOMS_LABWORK_ATTR SA, SIGNS_SYMPTOMS_LABWORK S where C.SBAR_ID=:sbarId and SA.ID = C.SIGNS_SYMPTOMS_LABWORK_ATTR_ID and S.ID=SA.PARENT_ID and S.REF_CARE_PATH_CODE is not null";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId", sbarId);
		query.addScalar("attrName");
		query.addScalar("refCarePathCode");
		query.setResultTransformer(Transformers.aliasToBean(CicAttrRefCarePathDto.class));		
		
		return query.list();
	}

	public List<ChangeInConditionDataForEvaluationDto> getChangeInConditionDataForEvaluation(
			Long sbarId) {
		String sql = "select S.SIGNS_SYMPTOMS_LABWORK_NAME signsSymptomsLabworkName, "
				+ "C.CHANGE_IN_CONDITION_VALUE changeInConditionValue, "
				+ "CDP.MAX_VALUE maxVal, CDP.MIN_VALUE minVal, SA.DATATYPE datatype, "
				+ "CDP.IMMEDIATE_FLAG immediateFlag, S.SIGN_FLAG signFlag, "
				+ "S.SYMPTOM_FLAG symptomFlag, S.LABWORK_FLAG labworkFlag  "
				+ "from CHANGE_IN_CONDITION C, CIC_DECISION_PARMS CDP, "
				+ "SIGNS_SYMPTOMS_LABWORK_ATTR SA, SIGNS_SYMPTOMS_LABWORK S "
				+ "where C.SBAR_ID=:sbarId "
				+ "and SA.ID = C.SIGNS_SYMPTOMS_LABWORK_ATTR_ID "
				+ "and S.ID=SA.PARENT_ID and SA.ID=CDP.SIGNS_SYMPTOMS_LABWORK_ATTR_ID";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId", sbarId);
		query.addScalar("signsSymptomsLabworkName");
		query.addScalar("changeInConditionValue");
		query.addScalar("maxVal");
		query.addScalar("minVal");
		query.addScalar("datatype");
		query.addScalar("immediateFlag");
		query.addScalar("signFlag");
		query.addScalar("symptomFlag");
		query.addScalar("labworkFlag");
		query.setResultTransformer(Transformers.aliasToBean(ChangeInConditionDataForEvaluationDto.class));		
		
		return query.list();
	}

	public ChangeInCondition getChangeinCondByEpisodeIdAndSymptomAttId(
			Long patientEpisodeId, Long symptomId) {
		// TODO Auto-generated method stub
		return null;
	}

//	public List<ChangeInCondition> findAllPatients() {
//		Criteria criteria = getCurrentSession().createCriteria(ChangeInCondition.class);
//		criteria.setProjection( Projections.distinct( Projections.property( "patientEpisode" ) ) );
//		return (List<ChangeInCondition> )criteria.list();
//	}
}

