package com.codecoop.interact.core.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.AcuteCare;
import com.codecoop.interact.core.domain.MessageAlert;
import com.codecoop.interact.core.domain.MessageAlertDetails;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.StopAndWatchHistory;
import com.codecoop.interact.core.dto.GenericDto;
import com.codecoop.interact.core.dto.MessageDto;
import com.codecoop.interact.core.dto.SbarDto;

@Component
public class MessageAlertDaoImpl extends BaseDao<MessageAlert>{

	public List<MessageDto> getMessages(Long facilityStaffId,String msgSubj,String msgAlertType)
	{
		Session session = getCurrentSession();
		String sql="SELECT COUNT(*) msgCount,MD.ID messageAlertDetailsId, M.MSG_SUBJ msgSubj,M.MSG_BODY msgBody,M.EVENT_ID eventId,M.MSG_SCENARIO_CODE currentScenarioCode,MD.READ_FLAG readFlag,MD.SENT_DATE sentDate,MD.SUSPENDED_FLAG suspendedFlag,MD.DELETE_FLAG deleteFlag FROM MESSAGE_ALERT M, MESSAGE_ALERT_DETAILS MD where M.MSG_ALERT_TYPE=:msgAlertType  AND MD.TO_FACILITY_STAFF_ID=:facilityStaffId AND MD.MESSAGE_ALERT_ID=M.ID AND MD.SUSPENDED_FLAG=0 AND M.MSG_SUBJ LIKE :msgSubj and MD.DELETE_FLAG=0 GROUP BY MD.TO_FACILITY_STAFF_ID,M.PATIENT_EPISODE_ID,M.MSG_SCENARIO_CODE,M.ASSIGN_FACILITY_STAFF_ID ORDER BY MD.SENT_DATE DESC";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("facilityStaffId", facilityStaffId);
		query.setParameter("msgAlertType", msgAlertType);
		query.setParameter("msgSubj", "%"+msgSubj+"%");
		query.addScalar("msgSubj");
		query.addScalar("msgBody");
		query.addScalar("readFlag");
		query.addScalar("sentDate");
		query.addScalar("msgCount",new LongType());
		query.addScalar("suspendedFlag");
		query.addScalar("deleteFlag");
		query.addScalar("eventId",new LongType());
		query.addScalar("currentScenarioCode");
		query.addScalar("messageAlertDetailsId",new LongType());
		query.setResultTransformer(Transformers.aliasToBean(MessageDto.class));
		return (List<MessageDto>)query.list();
	}
	
	public List<MessageDto> getLimitedMessages(Long facilityStaffId,String msgAlertType, int offset, 
            int noOfRecords,String msgSubj)
	{
		Session session = getCurrentSession();
		String sql="SELECT COUNT(*) msgCount,MD.ID messageAlertDetailsId, M.MSG_SUBJ msgSubj,M.MSG_BODY msgBody,M.EVENT_ID eventId,M.MSG_SCENARIO_CODE currentScenarioCode,MD.READ_FLAG readFlag,MD.SENT_DATE sentDate,MD.SUSPENDED_FLAG suspendedFlag,MD.DELETE_FLAG deleteFlag FROM MESSAGE_ALERT M, MESSAGE_ALERT_DETAILS MD where M.MSG_ALERT_TYPE=:msgAlertType AND M.MSG_SUBJ LIKE :msgSubj AND MD.TO_FACILITY_STAFF_ID=:facilityStaffId AND MD.MESSAGE_ALERT_ID=M.ID AND MD.SUSPENDED_FLAG=0  and MD.DELETE_FLAG=0 GROUP BY MD.TO_FACILITY_STAFF_ID,M.PATIENT_EPISODE_ID,M.MSG_SCENARIO_CODE,M.ASSIGN_FACILITY_STAFF_ID ORDER BY MD.SENT_DATE DESC";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("facilityStaffId", facilityStaffId);
		query.setParameter("msgAlertType", msgAlertType);
		query.setParameter("msgSubj", "%"+msgSubj+"%");
		query.addScalar("msgSubj");
		query.addScalar("msgBody");
		query.addScalar("readFlag");
		query.addScalar("sentDate");
		query.addScalar("suspendedFlag");
		query.addScalar("deleteFlag");
		query.addScalar("msgCount",new LongType());
		query.addScalar("eventId",new LongType());
		query.addScalar("currentScenarioCode");
		query.addScalar("messageAlertDetailsId",new LongType());
		query.setResultTransformer(Transformers.aliasToBean(MessageDto.class));
        query.setFirstResult(offset);
	    query.setMaxResults(noOfRecords);
		return (List<MessageDto>)query.list();
	}
	
	public List<MessageDto> getAlerts(Long facilityStaffId,String msgAlertType){
		Session session = getCurrentSession();
		String sql="SELECT COUNT(*) alertCount,MD.ID messageAlertDetailsId, M.MSG_SUBJ msgSubj,M.MSG_BODY msgBody,MD.READ_FLAG readFlag,MD.SENT_DATE sentDate,MD.SUSPENDED_FLAG suspendedFlag,MD.DELETE_FLAG deleteFlag from MESSAGE_ALERT M, MESSAGE_ALERT_DETAILS MD where M.MSG_ALERT_TYPE=:msgAlertType AND MD.TO_FACILITY_STAFF_ID=:facilityStaffId AND  MD.SUSPENDED_FLAG=0 AND  MD.MESSAGE_ALERT_ID=M.ID and MD.DELETE_FLAG=0  GROUP BY MD.TO_FACILITY_STAFF_ID,M.MSG_SCENARIO_CODE,M.PATIENT_EPISODE_ID,M.ASSIGN_FACILITY_STAFF_ID ORDER BY MD.SENT_DATE DESC";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("facilityStaffId", facilityStaffId);
		query.setParameter("msgAlertType", msgAlertType);
		query.addScalar("msgSubj");
		query.addScalar("msgBody");
		query.addScalar("readFlag");
		query.addScalar("sentDate");
		query.addScalar("alertCount",new LongType());
		query.addScalar("suspendedFlag");
		query.addScalar("deleteFlag");
		query.addScalar("messageAlertDetailsId",new LongType());
		query.setResultTransformer(Transformers.aliasToBean(MessageDto.class));
		return (List<MessageDto>)query.list();
		}
	public SbarDto getSbarData(Long Patient_episode_id){
		Session session = getCurrentSession();
		String sql="SELECT DOCTOR_ID doctorId FROM SBAR WHERE PATIENT_EPISODE_ID=:Patient_episode_id";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("Patient_episode_id", Patient_episode_id);
		query.addScalar("doctorId",new LongType());
		query.setResultTransformer(Transformers.aliasToBean(SbarDto.class));
		return(SbarDto)query.uniqueResult();
	}
	
	
		
	public List<MessageDto> findByMsgsubj(String msgSubj,Long facilityStaffId,int offSet,int noOfRecords){
		Session session = getCurrentSession();
		String sql="SELECT  COUNT(*) msgCount,M.MSG_SUBJ msgSubj, MD.SENT_DATE sentDate,MD.ID messageAlertDetailsId ,MD.SUSPENDED_FLAG suspendedFlag,M.MSG_BODY msgBody from  MESSAGE_ALERT M,MESSAGE_ALERT_DETAILS MD where M.ID=MD.MESSAGE_ALERT_ID AND M.MSG_SUBJ LIKE :msgSubj  and MD.TO_FACILITY_STAFF_ID=:facilityStaffId AND  MD.SUSPENDED_FLAG=0 and MD.DELETE_FLAG=0 GROUP BY MD.TO_FACILITY_STAFF_ID,M.PATIENT_EPISODE_ID,M.MSG_SCENARIO_CODE,M.ASSIGN_FACILITY_STAFF_ID ORDER BY MD.SENT_DATE DESC";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("msgSubj", msgSubj);
		query.setParameter("facilityStaffId", facilityStaffId);
		query.addScalar("msgSubj");
		query.addScalar("sentDate");
		query.addScalar("msgBody");
		query.addScalar("suspendedFlag");
		query.addScalar("msgCount",new LongType());
		query.addScalar("messageAlertDetailsId",new LongType());
		query.setResultTransformer(Transformers.aliasToBean(MessageDto.class));
		query.setFirstResult(offSet);
	    query.setMaxResults(noOfRecords);
		return (List<MessageDto>) query.list();
		}
	
	
	public List<MessageAlertDetails> getMessageAlertDetailsData(Long eventId,String prevScenarioCode,String msgAlertType){
		Session session = getCurrentSession();
		String sql="SELECT MD.* FROM MESSAGE_ALERT_DETAILS MD,MESSAGE_ALERT M where M.EVENT_ID=:eventId and M.MSG_SCENARIO_CODE=:prevScenarioCode  and M.MSG_ALERT_TYPE=:msgAlertType and MD.SUSPENDED_FLAG=FALSE AND MD.MESSAGE_ALERT_ID=M.ID";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("eventId", eventId);
		query.setParameter("prevScenarioCode", prevScenarioCode);
		query.setParameter("msgAlertType", msgAlertType);
		query.addEntity(MessageAlertDetails.class);
		return (List<MessageAlertDetails>)query.list();
	}

	public MessageAlert getMessageAlertData(Long eventId,String scenarioCode,String msgAlertType) {
		Session session = getCurrentSession();
		String sql="SELECT  * FROM MESSAGE_ALERT WHERE  EVENT_ID=:eventId and MSG_SCENARIO_CODE=:scenarioCode and MSG_ALERT_TYPE=:msgAlertType";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("eventId", eventId);
		query.setParameter("scenarioCode", scenarioCode);
		query.setParameter("msgAlertType", msgAlertType);
		query.addEntity(MessageAlert.class);
		return (MessageAlert)query.uniqueResult();
		
	}

	/*public List<MessageDto> getMessagesBySubj(String msgSubj, Long facilityStaffId,String msgAlertType) {
		Session session = getCurrentSession();
		String sql="SELECT COUNT(*) msgCount,MD.ID messageAlertDetailsId, M.MSG_SUBJ msgSubj,M.MSG_BODY msgBody,M.EVENT_ID eventId,M.MSG_SCENARIO_CODE currentScenarioCode,MD.READ_FLAG readFlag,MD.SENT_DATE sentDate,MD.SUSPENDED_FLAG suspendedFlag,MD.DELETE_FLAG deleteFlag FROM MESSAGE_ALERT M, MESSAGE_ALERT_DETAILS MD where M.MSG_ALERT_TYPE=:msgAlertType AND M.MSG_SUBJ LIKE :msgSubj AND MD.TO_FACILITY_STAFF_ID=:facilityStaffId AND MD.MESSAGE_ALERT_ID=M.ID AND MD.SUSPENDED_FLAG=0  and MD.DELETE_FLAG=0 GROUP BY MD.TO_FACILITY_STAFF_ID,M.PATIENT_EPISODE_ID,M.MSG_SCENARIO_CODE,M.ASSIGN_FACILITY_STAFF_ID ORDER BY MD.SENT_DATE DESC";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("facilityStaffId", facilityStaffId);
		query.setParameter("msgAlertType", msgAlertType);
		query.setParameter("msgSubj", "%"+msgSubj+"%");
		query.addScalar("msgSubj");
		query.addScalar("msgBody");
		query.addScalar("readFlag");
		query.addScalar("sentDate");
		query.addScalar("suspendedFlag");
		query.addScalar("deleteFlag");
		query.addScalar("msgCount",new LongType());
		query.addScalar("eventId",new LongType());
		query.addScalar("currentScenarioCode");
		query.addScalar("messageAlertDetailsId",new LongType());
		query.setResultTransformer(Transformers.aliasToBean(MessageDto.class));
   
		return (List<MessageDto>)query.list();
	}
	*/
	
	public List<GenericDto> getGenericDtoResponseList(String referenceTable,String referenceColumn) {
		 Session session = getCurrentSession(); 
		 String sql="select ID id,REPORTED_DATE_AND_TIME reportedDate, REPORTED_TO_FACILITY_STAFF_ID reportedToFacilityStaffId from " + referenceTable +" where "+ referenceColumn +" is not null or "+ referenceColumn +" !=''";
		 SQLQuery query = session.createSQLQuery(sql);
		 query.addScalar("id",new LongType());
		 query.addScalar("reportedDate");
		 query.addScalar("reportedToFacilityStaffId",new LongType());
		query.setResultTransformer(Transformers.aliasToBean(GenericDto.class));
		return  (List<GenericDto>)query.list();
	}

	public BigInteger getStopAndWatchIds(Long eventId) {
		Session session = getCurrentSession(); 
		 String sql="SELECT STOP_AND_WATCH_ID FROM STOP_AND_WATCH_HISTORY WHERE ID=:eventId";
		 SQLQuery query = session.createSQLQuery(sql);
		 query.setParameter("eventId", eventId);
		return (BigInteger)query.uniqueResult();
	}

	public void updateData(Long waitTime,BigInteger stopAndwatchId) {
		Session session = getCurrentSession(); 
		 String sql="UPDATE STOP_AND_WATCH_HISTORY SET WAIT_TIME=:waitTime where STOP_AND_WATCH_ID=:stopAndwatchId";
		 SQLQuery query = session.createSQLQuery(sql);
		 query.setParameter("waitTime", waitTime);
		 query.setParameter("stopAndwatchId", stopAndwatchId);
        query.executeUpdate();
		
	
		
	}

	public String getPrevScenarioCodeByEventId(Long eventId) {
		Session session = getCurrentSession(); 
		String sql="select MSG_SCENARIO_CODE from PATIENT_EPISODE where ID=:eventId";
		SQLQuery query = session.createSQLQuery(sql);
		 query.setParameter("eventId", eventId);
		 return (String)query.uniqueResult();
	}

	public List<StopAndWatchHistory> getPrevScenarioCodeByStopAndWatchId(Long stopAndWatchId) {
		Session session = getCurrentSession(); 
		String sql="select * from STOP_AND_WATCH_HISTORY where STOP_AND_WATCH_ID=:stopAndWatchId and MSG_SCENARIO_CODE is not null";
		SQLQuery query = session.createSQLQuery(sql);
		 query.setParameter("stopAndWatchId", stopAndWatchId);
			query.addEntity(StopAndWatchHistory.class);
		 return (List<StopAndWatchHistory>)query.list();
	}
	
	public List<Sbar> getPrevScenarioCodeBySbarId(Long PatientEpisodeId) {
		Session session = getCurrentSession(); 
		String sql="select * from SBAR where PATIENT_EPISODE_ID=:PatientEpisodeId";
		SQLQuery query = session.createSQLQuery(sql);
		 query.setParameter("PatientEpisodeId", PatientEpisodeId);
		query.addEntity(Sbar.class);
		 return (List<Sbar>)query.list();
	}
	
	public List<AcuteCare> getPrevScenarioCodeByAcuteCareId(Long PatientEpisodeId) {
		Session session = getCurrentSession(); 
		String sql="select * from HOSPITAL_TRANSFER where PATIENT_EPISODE_ID=:PatientEpisodeId";
		SQLQuery query = session.createSQLQuery(sql);
		 query.setParameter("PatientEpisodeId", PatientEpisodeId);
		query.addEntity(AcuteCare.class);
		 return (List<AcuteCare>)query.list();
	}
   }
