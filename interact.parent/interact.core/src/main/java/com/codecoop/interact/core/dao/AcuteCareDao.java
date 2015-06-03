package com.codecoop.interact.core.dao;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.AcuteCare;

@Component
public class AcuteCareDao extends BaseDao<AcuteCare> {
	@SuppressWarnings("unchecked")
	public AcuteCare getAcuteCareByEpisodeId(Long EpisodeId){
		String sql = "select HT.* from HOSPITAL_TRANSFER HT  where  HT.PATIENT_EPISODE_ID=:patientEpisodeId and HT.STAY_IN_ACUTECARE_FLAG=0";
		SQLQuery qry = getCurrentSession().createSQLQuery(sql);
		qry.setParameter("patientEpisodeId", EpisodeId);
		qry.addEntity(AcuteCare.class);
		return (AcuteCare)qry.uniqueResult();
		

	}

}
