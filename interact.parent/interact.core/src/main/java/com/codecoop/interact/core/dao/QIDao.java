package com.codecoop.interact.core.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.dto.QIEvaluateNdManageCICDto;
import com.codecoop.interact.core.dto.QIHospTransInfostatisticsDOWDto;
import com.codecoop.interact.core.dto.QIHospTransInfostatisticsDto;
import com.codecoop.interact.core.dto.QIHospTransInfostatisticsTODDto;
import com.codecoop.interact.core.dto.QIHospitalTransferInfoDto;
import com.codecoop.interact.core.dto.UtilsDto;


/**
 * @author Ramesh
 *
 */
@Component
public class QIDao{

	private static final Logger LOG = Logger.getLogger(QIDao.class);
	@Resource
	private SessionFactory sessionFactory;

	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	public QIHospTransInfostatisticsDto getHospitalTransferStatisticsOfCurrentFacilityQIG(Long facilityID,Date checkDate){
		QIHospTransInfostatisticsDto result = new QIHospTransInfostatisticsDto();
		String timeOfDaySql = "select (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.SEND_TO_HOSPITAL_FLAG=1 and ht.PATIENT_EPISODE_ID=pe.ID and ht.DATE_OF_TRANSFER  BETWEEN ADDDATE(:checkDate, INTERVAL +7 HOUR) and  ADDDATE(:checkDate, INTERVAL   '11:59:59' HOUR_SECOND)) as morning,"
				+ " (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.PATIENT_EPISODE_ID=pe.ID and ht.SEND_TO_HOSPITAL_FLAG=1 and ht.DATE_OF_TRANSFER  BETWEEN ADDDATE(:checkDate, INTERVAL +12 HOUR) and  ADDDATE(:checkDate, INTERVAL  '18:59:59' HOUR_SECOND)) as afterNoon, "
				+ " (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.PATIENT_EPISODE_ID=pe.ID and ht.SEND_TO_HOSPITAL_FLAG=1 and ht.DATE_OF_TRANSFER  BETWEEN ADDDATE(:checkDate, INTERVAL +19 HOUR) and  ADDDATE(:checkDate, INTERVAL  '23:59:59' HOUR_SECOND)) as evening, "
				+ " (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.PATIENT_EPISODE_ID=pe.ID and ht.SEND_TO_HOSPITAL_FLAG=1 and ht.DATE_OF_TRANSFER  BETWEEN ADDDATE(:checkDate, INTERVAL +24 HOUR) and  ADDDATE(:checkDate, INTERVAL  '30:59:59' HOUR_SECOND)) as night " 
				+ " from HOSPITAL_TRANSFER limit 1 ";
		SQLQuery timeOfDayQuery =  getCurrentSession().createSQLQuery(timeOfDaySql);
		timeOfDayQuery.setParameter("facilityID", facilityID);
		timeOfDayQuery.setParameter("checkDate", checkDate);
		timeOfDayQuery.addScalar("morning", LongType.INSTANCE);
		timeOfDayQuery.addScalar("afterNoon", LongType.INSTANCE);
		timeOfDayQuery.addScalar("evening", LongType.INSTANCE);
		timeOfDayQuery.addScalar("night", LongType.INSTANCE);
		timeOfDayQuery.setResultTransformer(Transformers.aliasToBean(QIHospTransInfostatisticsTODDto.class));
		result.setTimeOfDayStatistics((QIHospTransInfostatisticsTODDto)timeOfDayQuery.uniqueResult());

		String dayOfWeekSql = " select (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.SEND_TO_HOSPITAL_FLAG=1 and ht.PATIENT_EPISODE_ID=pe.ID and date(ht.DATE_OF_TRANSFER)=DATE_ADD(:checkDate, INTERVAL 1-DAYOFWEEK(:checkDate) DAY)) as sunday, "
				+" (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.PATIENT_EPISODE_ID=pe.ID and ht.SEND_TO_HOSPITAL_FLAG=1 and date(ht.DATE_OF_TRANSFER)=DATE_ADD(:checkDate, INTERVAL 2-DAYOFWEEK(:checkDate) DAY)) as monday, "
				+" (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.PATIENT_EPISODE_ID=pe.ID and ht.SEND_TO_HOSPITAL_FLAG=1 and date(ht.DATE_OF_TRANSFER)=DATE_ADD(:checkDate, INTERVAL 3-DAYOFWEEK(:checkDate) DAY)) as tuesday, "
				+" (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.PATIENT_EPISODE_ID=pe.ID and ht.SEND_TO_HOSPITAL_FLAG=1 and date(ht.DATE_OF_TRANSFER)=DATE_ADD(:checkDate, INTERVAL 4-DAYOFWEEK(:checkDate) DAY)) as wednesday, "
				+" (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.PATIENT_EPISODE_ID=pe.ID and ht.SEND_TO_HOSPITAL_FLAG=1 and date(ht.DATE_OF_TRANSFER)=DATE_ADD(:checkDate, INTERVAL 5-DAYOFWEEK(:checkDate) DAY)) as thursday, "
				+" (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.PATIENT_EPISODE_ID=pe.ID and ht.SEND_TO_HOSPITAL_FLAG=1 and date(ht.DATE_OF_TRANSFER)=DATE_ADD(:checkDate, INTERVAL 6-DAYOFWEEK(:checkDate) DAY)) as friday, "
				+" (select count(*)  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID and ht.PATIENT_EPISODE_ID=pe.ID and ht.SEND_TO_HOSPITAL_FLAG=1 and date(ht.DATE_OF_TRANSFER)=DATE_ADD(:checkDate, INTERVAL 7-DAYOFWEEK(:checkDate) DAY)) as saturday "
				+" from HOSPITAL_TRANSFER limit 1 ";
		SQLQuery dayOfWeekQuery =  getCurrentSession().createSQLQuery(dayOfWeekSql);
		dayOfWeekQuery.setParameter("facilityID", facilityID);
		dayOfWeekQuery.setParameter("checkDate", checkDate);
		dayOfWeekQuery.addScalar("sunday", LongType.INSTANCE);
		dayOfWeekQuery.addScalar("monday", LongType.INSTANCE);
		dayOfWeekQuery.addScalar("tuesday", LongType.INSTANCE);
		dayOfWeekQuery.addScalar("wednesday", LongType.INSTANCE);
		dayOfWeekQuery.addScalar("thursday", LongType.INSTANCE);
		dayOfWeekQuery.addScalar("friday", LongType.INSTANCE);
		dayOfWeekQuery.addScalar("saturday", LongType.INSTANCE);
		dayOfWeekQuery.setResultTransformer(Transformers.aliasToBean(QIHospTransInfostatisticsDOWDto.class));
		result.setDayOfWeekStatistics((QIHospTransInfostatisticsDOWDto)dayOfWeekQuery.uniqueResult());
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<UtilsDto> getBetweenDatesofYearFromFDischarge(Long facilityID,String from,String to){
		String betweenDtsSql = "SELECT date(ht.DATE_OF_TRANSFER) as date  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe "
				+"where pe.FACILITY_ID=:facilityID and ht.SEND_TO_HOSPITAL_FLAG=1 and ht.PATIENT_EPISODE_ID=pe.ID and ht.DATE_OF_TRANSFER between "
				+((!StringUtils.isEmpty(from) &&  !StringUtils.isEmpty(to)) ? " :from and :to " : " ( MAKEDATE(year(now()),1) ) and  ( DATE_ADD(MAKEDATE(year(now()),32), INTERVAL 11 MONTH) )");
		SQLQuery betweenDtsQuery =  getCurrentSession().createSQLQuery(betweenDtsSql);
		betweenDtsQuery.setParameter("facilityID", facilityID);
		betweenDtsQuery.addScalar("date", DateType.INSTANCE);
		if((!StringUtils.isEmpty(from) &&  !StringUtils.isEmpty(to))){
			betweenDtsQuery.setParameter("from", from);
			betweenDtsQuery.setParameter("to", to);
		}
		betweenDtsQuery.setResultTransformer(Transformers.aliasToBean(UtilsDto.class));
		return (List<UtilsDto>)betweenDtsQuery.list();
	}

	@SuppressWarnings("unchecked")
	public QIEvaluateNdManageCICDto getEvaluateNdManageChangeInConditionQIG(Long facilityID,String from,String to){
		String pEpisodesSql = "select distinct ht.PATIENT_EPISODE_ID  from HOSPITAL_TRANSFER ht,PATIENT_EPISODE pe where pe.FACILITY_ID=:facilityID "
				+"and ht.SEND_TO_HOSPITAL_FLAG=1 and ht.PATIENT_EPISODE_ID=pe.ID and ht.DATE_OF_TRANSFER  between "
				+((!StringUtils.isEmpty(from) &&  !StringUtils.isEmpty(to)) ? " :from and :to group by ht.PATIENT_EPISODE_ID  " : " ( MAKEDATE(year(now()),1) ) and  ( DATE_ADD(MAKEDATE(year(now()),32), INTERVAL 11 MONTH) ) group by ht.PATIENT_EPISODE_ID  ");
		SQLQuery pEpisodesQuery =  getCurrentSession().createSQLQuery(pEpisodesSql);
		pEpisodesQuery.setParameter("facilityID", facilityID);
		if((!StringUtils.isEmpty(from) &&  !StringUtils.isEmpty(to))){
			pEpisodesQuery.setParameter("from", from);
			pEpisodesQuery.setParameter("to", to);
		}
		List<Long> episodeIds = (List<Long>)pEpisodesQuery.list();
		String sbarIdsSql = "select sbr.ID from SBAR sbr where sbr.TRANSFER_TO_HOSPITAL_FLAG=1 and  sbr.PATIENT_EPISODE_ID in (:episodeIds) and sbr.DATE_MODIFIED between "
				+((!StringUtils.isEmpty(from) &&  !StringUtils.isEmpty(to)) ? " :from and :to " : " ( MAKEDATE(year(now()),1) ) and  ( DATE_ADD(MAKEDATE(year(now()),32), INTERVAL 11 MONTH) ) ");
		SQLQuery sbarIdsQuery =  getCurrentSession().createSQLQuery(sbarIdsSql);
		sbarIdsQuery.setParameterList("episodeIds", episodeIds);
		if((!StringUtils.isEmpty(from) &&  !StringUtils.isEmpty(to))){
			sbarIdsQuery.setParameter("from", from);
			sbarIdsQuery.setParameter("to", to);
		}
		List<Long>  sbarIds =  (List<Long>)sbarIdsQuery.list();
		String vistsSql = " select ( select count(*)  from(select  sr.DESCRIPTION,cpl.FACILITY_STAFF_ID from CARE_PATH_LABWORKS cpl,STAFF_ROLES sr,FACILITY_STAFF fs where fs.ROLE_ID=sr.ID and (sr.DESCRIPTION='NP' or sr.DESCRIPTION='PA') "
				+ "and cpl.FACILITY_STAFF_ID=fs.ID and (cpl.APPROVED=1 or cpl.REJECTED=1) and  cpl.SBAR_ID in (:sbarIds) "
				+ "group by cpl.FACILITY_STAFF_ID) a ) as nppaCount, "
				+ "(select count(*)  from(select  sr.DESCRIPTION,cpl.FACILITY_STAFF_ID from CARE_PATH_LABWORKS cpl,STAFF_ROLES sr,FACILITY_STAFF fs where fs.ROLE_ID=sr.ID and sr.DESCRIPTION='MD' "
				+"and cpl.FACILITY_STAFF_ID=fs.ID and (cpl.APPROVED=1 or cpl.REJECTED=1) and  cpl.SBAR_ID in (:sbarIds) "
				+"group by cpl.FACILITY_STAFF_ID) a ) as mdCount, "
				+"(select count(*)  from(select  sr.DESCRIPTION,cpl.FACILITY_STAFF_ID from CARE_PATH_LABWORKS cpl,STAFF_ROLES sr,FACILITY_STAFF fs where fs.ROLE_ID=sr.ID and (sr.DESCRIPTION !='NP' and sr.DESCRIPTION !='PA' and sr.DESCRIPTION !='MD') "
				+"and cpl.FACILITY_STAFF_ID=fs.ID and (cpl.APPROVED=1 or cpl.REJECTED=1) and  cpl.SBAR_ID in (:sbarIds) "
				+ " group by cpl.FACILITY_STAFF_ID) a ) as telCount ";
		SQLQuery vistsQuery =  getCurrentSession().createSQLQuery(vistsSql);
		vistsQuery.setParameterList("sbarIds", sbarIds);
		vistsQuery.addScalar("nppaCount", LongType.INSTANCE);
		vistsQuery.addScalar("mdCount", LongType.INSTANCE);
		vistsQuery.addScalar("telCount", LongType.INSTANCE);
		vistsQuery.setResultTransformer(Transformers.aliasToBean(QIEvaluateNdManageCICDto.class));
		return (QIEvaluateNdManageCICDto)vistsQuery.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<QIHospitalTransferInfoDto> getHospitalTranferPerntageOfCurrentAndOtherFacilities(
			Long facilityId, String fromDate, String toDate) {
		System.out.println("i am in controller2");
		
		String sql=  "select currentFacility.MONTH month, ROUND(COALESCE(currentFacility.CFPercentage,0 )) cfPercentage, ROUND(COALESCE(otherFacility.OFPercentage,0 )) "+
				 " ofPercentage from (SELECT m1.MONTHNAME MONTH,(currentFacilityTransfers.cnt *100)/((select count(*) from (select distinct pent.*, hf.DATE_OF_TRANSFER from "+ "hospital_transfer hf, sbar sb, patient_episode pe, patient_encounter pent where pe.id = hf.patient_episode_id and "+ 
				 " hf.patient_episode_id =sb.patient_episode_id and sb.patient_episode_id = pent.patient_episode_id and hf.SEND_TO_HOSPITAL_FLAG=1 and "+ "sb.PLANNED_DETAILS='unplanned' and pe.FACILITY_ID=: facilityId and (hf.DATE_OF_TRANSFER between '2014-12-01' and '2015-12-01')) abc))  CFPercentage"+
				 " FROM qi_months m1 LEFT  JOIN (select count(*) cnt, MONTHNAME(pqr.Transfer_Date) month1  from ((SELECT @sno:= @sno + 1 as sno, abc.* "+
				 " FROM  (select distinct pent.*, hf.DATE_OF_TRANSFER Transfer_Date from hospital_transfer hf, sbar sb, patient_episode pe, patient_encounter pent where "+ "pe.id=hf.patient_episode_id and hf.patient_episode_id=sb.patient_episode_id and sb.patient_episode_id=pent.patient_episode_id and "+ "hf.SEND_TO_HOSPITAL_FLAG=1 and sb.PLANNED_DETAILS='unplanned' and pe.FACILITY_ID=: facilityId and "+
				 " (hf.DATE_OF_TRANSFER between '2014-12-01' and '2015-12-01')) abc,"+
				" (SELECT @sno:= 0) s) xyz, (SELECT @sno1:= @sno1 + 1 as sno1, abc.* FROM  (select distinct pent.*, hf.DATE_OF_TRANSFER Transfer_Date from hospital_transfer"+ " hf, sbar sb, patient_episode pe, patient_encounter pent where pe.id=hf.patient_episode_id and hf.patient_episode_id=sb.patient_episode_id and "+ "sb.patient_episode_id=pent.patient_episode_id and hf.SEND_TO_HOSPITAL_FLAG=1 and sb.PLANNED_DETAILS='unplanned' and pe.FACILITY_ID=: facilityId and "+ "(hf.DATE_OF_TRANSFER between '2014-12-01' and '2015-12-01')) abc, (SELECT @sno1:= 0) s) pqr ) where pqr.PATIENT_EPISODE_ID=xyz.PATIENT_EPISODE_ID  and "+ "xyz.sno-pqr.sno1=1 and DATEDIFF(xyz.START_DATE,pqr.Transfer_Date) <=30 group by MONTH(pqr.Transfer_Date)) currentFacilityTransfers on "+ "currentFacilityTransfers.month1=m1.MONTHNAME order by m1.ID) currentFacility "+
				" left join (SELECT m1.MONTHNAME MONTH, (currentFacilityTransfers.cnt *100)/((select count(*) from (select distinct pent.*, hf.DATE_OF_TRANSFER from "+ "hospital_transfer hf, sbar sb, patient_episode pe, patient_encounter pent where pe.id=hf.patient_episode_id and hf.patient_episode_id=sb.patient_episode_id "+ " and sb.patient_episode_id=pent.patient_episode_id and hf.SEND_TO_HOSPITAL_FLAG=1 and sb.PLANNED_DETAILS='unplanned' and pe.FACILITY_ID!=:facilityId and "+ "(hf.DATE_OF_TRANSFER between '2014-12-01' and '2015-12-01')) abc))  OFPercentage FROM qi_months m1 LEFT  JOIN "+
				" (select count(*) cnt, MONTHNAME(pqr.Transfer_Date) month1  from ((SELECT @sno:= @sno + 1 as sno, abc.* FROM  (select distinct pent.*,hf.DATE_OF_TRANSFER "+ "Transfer_Date from hospital_transfer hf, sbar sb, patient_episode pe, patient_encounter pent where pe.id=hf.patient_episode_id and "+ "hf.patient_episode_id=sb.patient_episode_id and sb.patient_episode_id=pent.patient_episode_id and hf.SEND_TO_HOSPITAL_FLAG=1 and "+
				" sb.PLANNED_DETAILS='unplanned' and pe.FACILITY_ID!=: facilityId and (hf.DATE_OF_TRANSFER between '2014-12-01' and '2015-12-01')) abc, " +
				" (SELECT @sno:= 0) s) xyz, (SELECT @sno1:= @sno1 + 1 as sno1, abc.* FROM  (select distinct pent.*,hf.DATE_OF_TRANSFER Transfer_Date from "+
				" hospital_transfer hf,  sbar sb, patient_episode pe, patient_encounter pent where pe.id=hf.patient_episode_id and "+
				" hf.patient_episode_id=sb.patient_episode_id  and sb.patient_episode_id=pent.patient_episode_id and hf.SEND_TO_HOSPITAL_FLAG=1 and "+ "sb.PLANNED_DETAILS='unplanned' and pe.FACILITY_ID!=:facilityId and (hf.DATE_OF_TRANSFER between '2014-12-01' and '2015-12-01')) abc, "+
				" (SELECT @sno1:= 0) s) pqr) where pqr.PATIENT_EPISODE_ID=xyz.PATIENT_EPISODE_ID  and  xyz.sno-pqr.sno1=1 and DATEDIFF(xyz.START_DATE,pqr.Transfer_Date) <=30"+ " group by MONTH(pqr.Transfer_Date)) currentFacilityTransfers on currentFacilityTransfers.month1=m1.MONTHNAME order by m1.ID) otherFacility "+
				" on currentFacility.MONTH=otherFacility.MONTH";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.addScalar("month");
		query.addScalar("cfPercentage", IntegerType.INSTANCE);
		query.addScalar("ofPercentage",IntegerType.INSTANCE);
		query.setParameter("facilityId", facilityId);
		query.setResultTransformer(Transformers.aliasToBean(QIHospitalTransferInfoDto.class));
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public   List<QIHospitalTransferInfoDto> selectedData(Long facilityId, String fromDate, String toDate){
		
		SQLQuery query =  getCurrentSession().createSQLQuery("CALL QI_TRANSFERS_CF_VS_OF(?,?,?)");
		query.setParameter(0, facilityId);
		query.setParameter(1, fromDate);
		query.setParameter(2, toDate);
		query.addScalar("monthName");
		query.addScalar("cfPercentage", IntegerType.INSTANCE);
		query.addScalar("ofPercentage",IntegerType.INSTANCE);
		//query.addEntity(QIHospitalTransferInfoDto.class);
         query.setResultTransformer(Transformers.aliasToBean(QIHospitalTransferInfoDto.class));
         
         System.out.println("i am in controller2");
		return ( List<QIHospitalTransferInfoDto>)query.list();
		

      }


}
