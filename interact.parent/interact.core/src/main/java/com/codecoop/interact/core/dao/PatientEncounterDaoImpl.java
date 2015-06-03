package com.codecoop.interact.core.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.CarePathTrackerAttr;
import com.codecoop.interact.core.domain.PatientEncounter;
import com.codecoop.interact.core.domain.States;
import com.codecoop.interact.core.dto.PatientEncounterDto;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkAttrDto;

@Component
public class PatientEncounterDaoImpl extends BaseDao<PatientEncounter>{

	public PatientEncounter findActiveEncounter(Long patientEpisodeId) {
		// TODO Auto-generated method stub
		String sql = "select * from PATIENT_ENCOUNTER  where PATIENT_EPISODE_ID=:patientEpisodeId and END_DATE is null";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("patientEpisodeId", patientEpisodeId);
	
		query.addEntity(PatientEncounter.class);
		return  (PatientEncounter)query.uniqueResult();
		
	}
	@SuppressWarnings("unchecked")
	public List<PatientEncounterDto> findAllEncounters(Long patientEpisodeId) {
		
		String sql = "select ID id, PATIENT_EPISODE_ID patientEpisodeId,ENCOUNTER_NUM encounterNum,START_DATE startDate,END_DATE endDate from PATIENT_ENCOUNTER  where PATIENT_EPISODE_ID=:patientEpisodeId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("patientEpisodeId", patientEpisodeId);
		query.addScalar("id", new LongType());
		query.addScalar("patientEpisodeId", new LongType());
		query.addScalar("encounterNum", new IntegerType());
		query.addScalar("startDate");
		query.addScalar("endDate");
		
		query.setResultTransformer(Transformers.aliasToBean(PatientEncounterDto.class));
		return  query.list();
		
	}

	public PatientEncounter findLastEcounterNumber(Long patientEpisodeId) {
		String sql = "select * from PATIENT_ENCOUNTER  where PATIENT_EPISODE_ID=:patientEpisodeId and END_DATE is not null order by END_DATE desc limit 1";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("patientEpisodeId", patientEpisodeId);
		query.addEntity(PatientEncounter.class);
		return  (PatientEncounter)query.uniqueResult();
	}
	
	
}
