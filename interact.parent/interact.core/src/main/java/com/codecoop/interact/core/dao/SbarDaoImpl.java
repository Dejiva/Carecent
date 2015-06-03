package com.codecoop.interact.core.dao;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.util.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.PatientEncounter;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.Sbar;

@Component
public class SbarDaoImpl extends BaseDao<Sbar> {

	public Sbar findByPatientEpisode(PatientEpisode patientEpisode) {
		
		String sql = "select s.* from SBAR s where s.PATIENT_EPISODE_ID=:patientEpisode and s.MANAGE_IN_FACILITY_FLAG =0 and s.TRANSFER_TO_HOSPITAL_FLAG=0 ";
		SQLQuery qry = getCurrentSession().createSQLQuery(sql);
		qry.setParameter("patientEpisode", patientEpisode);
		qry.addEntity(Sbar.class);
		return (Sbar) qry.uniqueResult();
	
		
	}

	@SuppressWarnings("unchecked")
	public List<Sbar> findByFacilityId(Long facilityId) {
		String sql = "select s.* from SBAR s,PATIENT pat, PATIENT_EPISODE p  where p.FACILITY_ID=:faicilityId and p.ID=s.PATIENT_EPISODE_ID and s.MANAGE_IN_FACILITY_FLAG =0 and s.TRANSFER_TO_HOSPITAL_FLAG=0  and pat.ID=p.PATIENT_ID order by pat.FIRST_NAME ASC";
		SQLQuery qry = getCurrentSession().createSQLQuery(sql);
		qry.setParameter("faicilityId", facilityId);
		qry.addEntity(Sbar.class);
		return qry.list();
	}

	public Long findSbarIdbyEncounter(PatientEncounter patientEncounter) {
		String sql = "select s.ID from SBAR s  where s.PATIENT_EPISODE_ID=:patientEpisode and s.DATE_CREATED>=:startDate and "+(patientEncounter.getEndDate()!=null?"s.DATE_MODIFIED <=:endDate":"( s.DATE_MODIFIED <=:currDate or s.DATE_MODIFIED is null )");
		SQLQuery qry = getCurrentSession().createSQLQuery(sql);
		qry.setParameter("patientEpisode", patientEncounter.getPatientEpisode());
		qry.setParameter("startDate",patientEncounter.getStartDate());
		if(patientEncounter.getEndDate()!=null){
			qry.setParameter("endDate",patientEncounter.getEndDate());
        }else{
        	qry.setParameter("currDate",new Date());
        }
		BigInteger result = (BigInteger)qry.uniqueResult();
		if(StringUtils.isEmpty(result)){
			return null;
		}
		return result.longValue();
	}
	
	
}
