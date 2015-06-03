package com.codecoop.interact.core.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.domain.SignsSymptomsLabwork;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkAttrDto;
import com.codecoop.interact.core.dto.SymptomsAgainstUnplannedDto;
import com.codecoop.interact.core.enumpack.ChangeInConditionType;
@Component
public class SignsSymptomsLabworkDao extends BaseDao<SignsSymptomsLabwork> {


	@SuppressWarnings("unchecked")
	public List<SignsSymptomsLabwork> getSignSymptomsBySignFlag(){

		Criteria criteria= getCurrentSession().createCriteria(SignsSymptomsLabwork.class);
		criteria.add(Restrictions.eq("signFlag", true));
		List<SignsSymptomsLabwork> signFlagList =	criteria.list();
		return signFlagList;
	}

	@SuppressWarnings("unchecked")
	public List<SignsSymptomsLabwork> getSignSymptomsBySymptomFalg(){

		Criteria criteria= getCurrentSession().createCriteria(SignsSymptomsLabwork.class);
		criteria.add(Restrictions.eq("symptomFlag", true));
		List<SignsSymptomsLabwork> symptomFlagList =	criteria.list();
		return symptomFlagList;

	}

	@SuppressWarnings("unchecked")
	public List<SignsSymptomsLabwork> getSignSymptomsByLabWorkFalg(){

		Criteria criteria= getCurrentSession().createCriteria(SignsSymptomsLabwork.class);
		criteria.add(Restrictions.eq("labworkFlag", true));
		List<SignsSymptomsLabwork> labworkFlagList = criteria.list();
		return labworkFlagList;

	}

	@SuppressWarnings("unchecked")
	public List<SignsSymptomsLabwork> getSignSymptomsByFalgType(ChangeInConditionType type){

		Criteria criteria= getCurrentSession().createCriteria(SignsSymptomsLabwork.class);

		switch (type) {
		case SIGN_FLAG:
			criteria.add(Restrictions.eq("signFlag", true));
			break;
		case SYMPTOM_FLAG:
			criteria.add(Restrictions.eq("symptomFlag", true));
			break;
		case LABWORK_FLAG:
			criteria.add(Restrictions.eq("labworkFlag", true));
			break;
		}
		List<SignsSymptomsLabwork> signSymptomsList = criteria.list();
		return signSymptomsList;

	}

	public SignsSymptomsLabwork getSignSympLabworkIdByCPLWCode(String carePathLabWorkCode){
		Criteria criteria= getCurrentSession().createCriteria(SignsSymptomsLabwork.class);
		criteria.add(Restrictions.eq("carepathLabworkCode", carePathLabWorkCode));
		criteria.add(Restrictions.eq("labworkFlag", true));
		return	(SignsSymptomsLabwork)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<SymptomsAgainstUnplannedDto> getDataForSymptomsAgainstUnplanned(
			Long facilityId, String startDate, String endDate) {
		
		String sql="select ssl1.SIGNS_SYMPTOMS_LABWORK_NAME symtomName , kk.countValue numberOfTransfers from SIGNS_SYMPTOMS_LABWORK ssl1 left join (select count(cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID) countValue ,sls.SIGNS_SYMPTOMS_LABWORK_NAME sysName from CHANGE_IN_CONDITION cic ,SIGNS_SYMPTOMS_LABWORK_ATTR ssla ,SIGNS_SYMPTOMS_LABWORK sls ,SBAR sb where cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID =ssla.ID and ssla.PARENT_ID=sls.ID and cic.SBAR_ID=sb.ID and sls.SYMPTOM_FLAG=1 	and sb.ID in(select distinct sb1.ID from SBAR sb1,HOSPITAL_TRANSFER hf1, PATIENT_EPISODE pe1 where hf1.PATIENT_EPISODE_ID=sb1.PATIENT_EPISODE_ID and pe1.ID=sb1.PATIENT_EPISODE_ID and pe1.FACILITY_ID=:facilityId and hf1.SEND_TO_HOSPITAL_FLAG=1 and sb1.PLANNED_DETAILS='unplanned' and (hf1.DATE_OF_TRANSFER BETWEEN '"+startDate+"' and '"+endDate+ "')) group by ssla.PARENT_ID) kk on ssl1.SIGNS_SYMPTOMS_LABWORK_NAME=sysName where ssl1.SYMPTOM_FLAG=1 order by ssl1.ID";
		
//		String sql="select ssl1.SIGNS_SYMPTOMS_LABWORK_NAME  symtomName, kk.countValue numberOfTransfers from SIGNS_SYMPTOMS_LABWORK ssl1 "+
//			   "left join"+
//				"(select count(cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID) countValue ,	sls.SIGNS_SYMPTOMS_LABWORK_NAME sysName"+
//				"from CHANGE_IN_CONDITION cic ,SIGNS_SYMPTOMS_LABWORK_ATTR ssla ,SIGNS_SYMPTOMS_LABWORK sls ,SBAR sb "+
//				"where cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID =ssla.ID and ssla.PARENT_ID=sls.ID and cic.SBAR_ID=sb.ID and sls.SYMPTOM_FLAG=1 and "+
//				"sb.ID 	in(select distinct sb1.ID from SBAR sb1,HOSPITAL_TRANSFER hf1, PATIENT_EPISODE pe1 "+
//				"where hf1.PATIENT_EPISODE_ID=sb1.PATIENT_EPISODE_ID and  pe1.ID=sb1.PATIENT_EPISODE_ID and pe1.FACILITY_ID=:facilityId and hf1.SEND_TO_HOSPITAL_FLAG=1 and sb1.PLANNED_DETAILS='unplanned') 	group by ssla.PARENT_ID) kk "+
//               "on ssl1.SIGNS_SYMPTOMS_LABWORK_NAME=sysName where ssl1.SYMPTOM_FLAG=1 ";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		
		query.addScalar("numberOfTransfers",new LongType());
		query.addScalar("symtomName");
		query.setParameter("facilityId", facilityId);
//		query.setParameter("startDate", startDate);
//		query.setParameter("endDate", endDate);
		query.setResultTransformer(Transformers.aliasToBean(SymptomsAgainstUnplannedDto.class));
		return query.list();
			
		
	}
	public Long getNumberOfTranfersBySymptoms(Long facilityId, String startDate, String endDate){
//		String sql = "select count(*) c from (select count(sb.ID)  from CHANGE_IN_CONDITION cic ,SBAR sb,HOSPITAL_TRANSFER hf,SIGNS_SYMPTOMS_LABWORK_ATTR ssla ,SIGNS_SYMPTOMS_LABWORK sls,PATIENT_EPISODE pe where cic.SBAR_ID=sb.ID and pe.ID=hf.PATIENT_EPISODE_ID and cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID=ssla.ID and ssla.PARENT_ID=sls.ID and hf.PATIENT_EPISODE_ID=sb.PATIENT_EPISODE_ID and sls.SYMPTOM_FLAG=1 and hf.SEND_TO_HOSPITAL_FLAG=1 and sb.PLANNED_DETAILS='unplanned' and pe.FACILITY_ID=:facilityId 	and (hf.DATE_OF_TRANSFER BETWEEN  '2015-04-27 00:00:00' and '2016-04-27 00:00:00') group by sb.ID) abc";
//				+ ""
		String sql = "select count(*) c from (select count(sb.ID)  from CHANGE_IN_CONDITION cic ,SBAR sb,HOSPITAL_TRANSFER hf,SIGNS_SYMPTOMS_LABWORK_ATTR ssla ,SIGNS_SYMPTOMS_LABWORK sls,PATIENT_EPISODE pe where cic.SBAR_ID=sb.ID and pe.ID=hf.PATIENT_EPISODE_ID and cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID=ssla.ID and ssla.PARENT_ID=sls.ID and hf.PATIENT_EPISODE_ID=sb.PATIENT_EPISODE_ID and sls.SYMPTOM_FLAG=1 and hf.SEND_TO_HOSPITAL_FLAG=1 and sb.PLANNED_DETAILS='unplanned' and pe.FACILITY_ID=:facilityId 	and (hf.DATE_OF_TRANSFER BETWEEN  '"+startDate+"' and '"+endDate+ "') group by sb.ID) abc";

//				+ ""
//				+ "select count(*) from (select count(sb.ID) value from CHANGE_IN_CONDITION cic ,SBAR sb,HOSPITAL_TRANSFER hf,SIGNS_SYMPTOMS_LABWORK_ATTR ssla ,SIGNS_SYMPTOMS_LABWORK sls,PATIENT_EPISODE pe"+
//					  "where cic.SBAR_ID=sb.ID and pe.ID=hf.PATIENT_EPISODE_ID and cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID=ssla.ID and ssla.PARENT_ID=sls.ID and hf.PATIENT_EPISODE_ID=sb.PATIENT_EPISODE_ID and sls.SYMPTOM_FLAG=1 and hf.SEND_TO_HOSPITAL_FLAG=1 and sb.PLANNED_DETAILS='unplanned' and pe.FACILITY_ID=:facilityId group by sb.ID) abc";
//		
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId", facilityId);
//		query.setParameter("startDate", startDate);
//		query.setParameter("endDate", startDate);
		BigInteger result = (BigInteger)query.uniqueResult();
		if(StringUtils.isEmpty(result)){
			return null;
		}
		return result.longValue();
	}

	  
}
