package com.codecoop.interact.core.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.CarePathTrackerAttr;
import com.codecoop.interact.core.domain.ChangeInCondition;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.domain.StopAndWatch;
import com.codecoop.interact.core.dto.CarePathLabworksDto;
import com.codecoop.interact.core.dto.SAWActivePatientsDto;
import com.codecoop.interact.core.dto.StopAndWatchPatientsMapDto;

@Component
public class StopAndWatchDaoImpl extends BaseDao<StopAndWatch>{

	public StopAndWatch  findActiveStopAndWatch(PatientEpisode patientEpisode){
		String sql = "select SA.* from  STOP_AND_WATCH SA where SA.PATIENT_EPISODE_ID=:patientEpisode and SA.END_DATE is null";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("patientEpisode", patientEpisode);
		query.addEntity(StopAndWatch.class);
		return  (StopAndWatch)query.uniqueResult();

	}
	@SuppressWarnings("unchecked")
	public List<StopAndWatch> findAllByFacilityID(long facilityId) {
		String sql = "select SA.* from  STOP_AND_WATCH SA,PATIENT_EPISODE PE where SA.PATIENT_EPISODE_ID=PE.ID and PE.FACILITY_ID=:facilityId and SA.END_DATE is null";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId", facilityId);
		query.addEntity(StopAndWatch.class);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<StopAndWatch>findAllActiveStopAndWatches() {
		String sql = "select SA.* from  STOP_AND_WATCH SA,PATIENT_EPISODE PE,PATIENT P where SA.PATIENT_EPISODE_ID=PE.ID and PE.PATIENT_ID=P.ID and SA.END_DATE is null order by P.FIRST_NAME";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.addEntity(StopAndWatch.class);
		return query.list();
	
	}
	public StopAndWatch findRecentlyClosedStopAndWatch(PatientEpisode patientEpisode) {
		String sql = "select SA.* from  STOP_AND_WATCH SA where SA.PATIENT_EPISODE_ID=:patientEpisode and SA.END_DATE is not null order by SA.END_DATE desc limit 1";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("patientEpisode", patientEpisode);
		query.addEntity(StopAndWatch.class);
		return  (StopAndWatch)query.uniqueResult();

	}
	@SuppressWarnings("unchecked")
	public List<SAWActivePatientsDto> findAllActiveStopAndWatchesByFacilityId(
			long facilityId) {
		String sql = "select P.ID patientId, TRIM(CONCAT(IFNULL(P.FIRST_NAME,''),' ',IFNULL(P.LAST_NAME,'')))  patientName from  STOP_AND_WATCH SA,PATIENT_EPISODE PE,PATIENT P where SA.PATIENT_EPISODE_ID=PE.ID and PE.PATIENT_ID=P.ID and PE.FACILITY_ID=:facilityId and  SA.END_DATE is null order by P.FIRST_NAME";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId",facilityId);
		query.addScalar("patientId",LongType.INSTANCE);
		query.addScalar("patientName");
		query.setResultTransformer(Transformers.aliasToBean(SAWActivePatientsDto.class));
		return (List<SAWActivePatientsDto>)query.list();
	}
	@SuppressWarnings("unchecked")
	public List<StopAndWatch> getStopAndWatchsOnStartAndEnd(PatientEpisode patientEpisode,Date sDate,
			Date eDate) {
		Date cuurDate=new Date();
		String sql = "select SA.* from  STOP_AND_WATCH SA where SA.PATIENT_EPISODE_ID=:patientEpisode  and SA.START_DATE>=:startDate and "+(eDate!=null?"SA.END_DATE<=:endDate":"(SA.END_DATE is null or SA.END_DATE<=:currDate)");
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("patientEpisode", patientEpisode);
		query.setParameter("startDate",sDate);
		if(eDate!=null){
        query.setParameter("endDate",eDate);
        }else{
        	query.setParameter("currDate",new Date());
        }
		query.addEntity(StopAndWatch.class);
		return query.list();
	}
  
	
}
