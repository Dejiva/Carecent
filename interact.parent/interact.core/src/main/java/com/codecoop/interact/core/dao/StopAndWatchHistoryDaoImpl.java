package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.Patient;
import com.codecoop.interact.core.domain.StopAndWatch;
import com.codecoop.interact.core.domain.StopAndWatchHistory;
import com.codecoop.interact.core.util.Constants;
@Component
public class StopAndWatchHistoryDaoImpl extends BaseDao<StopAndWatchHistory>{
	@SuppressWarnings("unchecked")
	public List<StopAndWatchHistory>  findALLStopAndWatchHistorys(StopAndWatch stopAndWatch){
		String sql = "select SWH.* from  STOP_AND_WATCH_HISTORY SWH where SWH.STOP_AND_WATCH_ID=:stopAndWatch and SWH.INVALIDATE_FLAG=0 and SWH.DELETE_FLAG=0";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("stopAndWatch", stopAndWatch.getId());
		query.addEntity(StopAndWatchHistory.class);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<StopAndWatchHistory>  findALLSortedStopAndWatchHistorys(StopAndWatch stopAndWatch){
		String sql = "select SWH.* from  STOP_AND_WATCH_HISTORY SWH where SWH.STOP_AND_WATCH_ID=:stopAndWatch and SWH.INVALIDATE_FLAG=0 and SWH.DELETE_FLAG=0 order by SWH.DATE_CREATED desc";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("stopAndWatch", stopAndWatch.getId());
		query.addEntity(StopAndWatchHistory.class);
		return query.list();
	}
    @SuppressWarnings("unchecked")
	public List<Patient> findByReportedId(FacilityStaff facilityStaff,Long staffId){
		Session session = getCurrentSession();
		//String sql="select p.* FROM PATIENT p,STOP_AND_WATCH s,PATIENT_EPISODE pe1 where  p.ID=pe1.PATIENT_ID and pe1.ID=s.PATIENT_EPISODE_ID and pe1.FACILITY_DISCHARGE_DATE is null and pe1.FACILITY_ID=:facilityId and s.ID in(select distinct sh.STOP_AND_WATCH_ID  from STOP_AND_WATCH_HISTORY sh where sh.REPORTED_TO_FACILITY_STAFF_ID=:facilityStaff and sh.DELETE_FLAG=0) and s.END_DATE is null union all select p2.* from SBAR_NOTES sbn,SBAR sb,PATIENT p2,PATIENT_EPISODE pe2 where sbn.NURSING_FACILITY_STAFF_ID=:facilityStaff and sbn.NURSING_STAFF_TYPE=:nurseType and sbn.SBAR_ID=sb.ID and p2.ID=pe2.PATIENT_ID and pe2.ID=sb.PATIENT_EPISODE_ID and pe2.FACILITY_DISCHARGE_DATE is null and pe2.FACILITY_ID=:facilityId union all select p3.* from SBAR sb,PATIENT p3,PATIENT_EPISODE pe3 where sb.DOCTOR_ID=:staffId and  p3.ID=pe3.PATIENT_ID and  pe3.ID=sb.PATIENT_EPISODE_ID and  pe3.FACILITY_DISCHARGE_DATE is null and pe3.FACILITY_ID=:facilityId union all select p4.* from PATIENT p4 ,PATIENT_EPISODE pe4 where pe4.PCP_ID=:facilityStaff and p4.ID=pe4.PATIENT_ID and pe4.FACILITY_DISCHARGE_DATE is null and pe4.FACILITY_ID=:facilityId union all select p5.* from PATIENT p5 ,PATIENT_EPISODE pe5 ,ASSIGNED_DOC_RELATION asd where  p5.ID=pe5.PATIENT_ID and pe5.FACILITY_DISCHARGE_DATE is null and pe5.FACILITY_ID=:facilityId and pe5.PCP_ID=asd.DOCTOR_FACILITY_STAFF_ID union all select p6.* from PATIENT p6,PATIENT_EPISODE pe6 where pe6.PCP_ID=:facilityStaff AND pe6.FACILITY_ID=:facilityId and p6.ID=pe6.PATIENT_ID and pe6.FACILITY_DISCHARGE_DATE is null order by FIRST_NAME ASC";
		//String sql="select p6.* from PATIENT p6,PATIENT_EPISODE pe6 where pe6.PCP_ID=:facilityStaff AND pe6.FACILITY_ID=:facilityId and p6.ID=pe6.PATIENT_ID and pe6.FACILITY_DISCHARGE_DATE is null order by FIRST_NAME ASC";
		String sql="select p5.* from PATIENT p5 ,PATIENT_EPISODE pe5 ,ASSIGNED_DOC_RELATION asd where  p5.ID=pe5.PATIENT_ID and pe5.FACILITY_DISCHARGE_DATE is null and pe5.FACILITY_ID=:facilityId and pe5.PCP_ID=asd.DOCTOR_FACILITY_STAFF_ID and asd.FACILITY_STAFF_ID=:facilityStaffId union all select p6.* from PATIENT p6,PATIENT_EPISODE pe6 where pe6.PCP_ID=:facilityStaffId and pe6.FACILITY_ID=:facilityId and p6.ID=pe6.PATIENT_ID and pe6.FACILITY_DISCHARGE_DATE is null order by FIRST_NAME ASC";
		SQLQuery query =  session.createSQLQuery(sql);
		 query.setParameter("facilityId",facilityStaff.getFacility().getId());
		query.setParameter("facilityStaffId",facilityStaff.getId());
//	    query.setParameter("staffId",staffId);
	   
//        query.setParameter("nurseType",Constants.NURSE_TYPE);
	    query.addEntity(Patient.class);
		return (List<Patient>)query.list();
	}
@SuppressWarnings("unchecked")
public List<StopAndWatchHistory> findAllActiveStopAndWatchHistorys(Long patientEpisodeId){
	Session session = getCurrentSession();
	String sql="SELECT sh.* from STOP_AND_WATCH_HISTORY sh where sh.DELETE_FLAG=false AND sh.INVALIDATE_FLAG=false and sh.STOP_AND_WATCH_ID IN (SELECT sa.ID FROM STOP_AND_WATCH sa WHERE sa.END_DATE IS NULL AND sa.PATIENT_EPISODE_ID IN (SELECT ID FROM PATIENT_EPISODE pe where pe. FACILITY_DISCHARGE_DATE is NULL));";
	SQLQuery query =  session.createSQLQuery(sql);
	query.addEntity(StopAndWatchHistory.class);
	return (List<StopAndWatchHistory>)query.list();
}
}
