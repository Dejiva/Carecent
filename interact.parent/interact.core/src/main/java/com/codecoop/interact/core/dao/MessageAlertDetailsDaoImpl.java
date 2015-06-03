package com.codecoop.interact.core.dao;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import com.codecoop.interact.core.domain.MessageAlert;
import com.codecoop.interact.core.domain.MessageAlertDetails;
import com.codecoop.interact.core.domain.MessageAlertDetails;
import com.codecoop.interact.core.domain.StopAndWatchHistory;
import com.codecoop.interact.core.dto.GenericDto;
import com.codecoop.interact.core.dto.MessageDto;
import com.codecoop.interact.core.dto.SecondAlertMapperDto;
import com.codecoop.interact.core.dto.StopAndWatchHistoryDto;

@Component

public class MessageAlertDetailsDaoImpl extends BaseDao<MessageAlertDetails>{

	
	
	
	
	@SuppressWarnings("unchecked")
	public Long deleteMessages(Long messageAlertDetailsId)
	{
		Session session = getCurrentSession();
		String sql="update MESSAGE_ALERT_DETAILS set DELETE_FLAG=1 WHERE ID =:messageAlertDetailsId";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("messageAlertDetailsId", messageAlertDetailsId);
		  return (long) query.executeUpdate();
   }
	@SuppressWarnings("unchecked")
	public Long deleteAlerts(Long messageAlertDetailsId)
	{
		Session session = getCurrentSession();
		String sql="update MESSAGE_ALERT_DETAILS set DELETE_FLAG=1 WHERE ID=:messageAlertDetailsId";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("messageAlertDetailsId", messageAlertDetailsId);
		  return (long) query.executeUpdate();
   }

	public List<String> getMsgSubj(Long facilitystaffId){
		Session session = getCurrentSession();
		String sql="select distinct M.MSG_SUBJ from MESSAGE_ALERT M, MESSAGE_ALERT_DETAILS MAD  where M.ID=MAD.MESSAGE_ALERT_ID and MAD.DELETE_FLAG=0 and MAD.SUSPENDED_FLAG=0 and  M.MSG_ALERT_TYPE='MESSAGE' and MAD.TO_FACILITY_STAFF_ID=:facilitystaffId";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("facilitystaffId", facilitystaffId);
		//query.addScalar("MSG_SUBJ");
		return (List<String>)query.list();
	}
	
	public List<MessageAlert> getMessageAlertList(long stopAndWatchHistoryId) {
		Session session = getCurrentSession();
		String sql="SELECT * FROM MESSAGE_ALERT WHERE EVENT_ID=:stopAndWatchHistoryId AND MSG_ALERT_TYPE='MESSAGE'";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("stopAndWatchHistoryId", stopAndWatchHistoryId);
		query.addEntity(MessageAlert.class);
		return (List<MessageAlert>)query.list();
	}
	
	
	public List<MessageAlertDetails> getMessageAlertDetails(Long eventId){
		Session session = getCurrentSession();
		String sql="SELECT MD.* FROM MESSAGE_ALERT_DETAILS MD,MESSAGE_ALERT M where M.EVENT_ID=:eventId  and MD.SUSPENDED_FLAG=FALSE AND MD.MESSAGE_ALERT_ID=M.ID";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("eventId", eventId);
		query.addEntity(MessageAlertDetails.class);
		return (List<MessageAlertDetails>)query.list();
	}
	
	
	 public  List<GenericDto>  getSecondAlertViewData()
	 {
			Session session = getCurrentSession();
			String sql="SELECT EVENT_ID eventId,MAX_WAIT_TIME maxWaitTime,CURRENT_SCENARIO_CODE currentScenarioCode,REPORTEDTO_FACILITYSTAFF_ID reportedFacilityStaffId,STOP_AND_WATCH_ID stopAndWatchId,PREVIOUS_SCENARIO_CODE previousScenarioCode,REPORTED_DATE_AND_TIME reportedDateTime,WAIT_TIME waitTime,REFERENCE_TABLE_NAME tableName,PATIENT_EPISODE_ID patientEpisodeId,FACILITY_ID facilityId,MSGSUBJ msgSubj FROM SECOND_ALERT_VIEW";
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("eventId",new LongType());
			query.addScalar("maxWaitTime",new LongType());
			query.addScalar("currentScenarioCode");
			query.addScalar("reportedFacilityStaffId",new LongType());
			query.addScalar("stopAndWatchId",new LongType());
			query.addScalar("previousScenarioCode");
			query.addScalar("reportedDateTime");
			query.addScalar("waitTime",new LongType());
			query.addScalar("tableName");
			query.addScalar("patientEpisodeId",new LongType());
			query.addScalar("facilityId",new LongType());
			query.addScalar("msgSubj");
			query.setResultTransformer(Transformers.aliasToBean(GenericDto.class));
			return (List<GenericDto>)query.list();
	 }
	public Long updateMailAlertAndScenarioCode(String referenceTable,String msgScenarioCode,Long eventId){
		Session session = getCurrentSession();
		String sql="update "+referenceTable+" set ALERT_MAIL_FLAG=1, MSG_SCENARIO_CODE=:msgScenarioCode where ID=:eventId";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("eventId", eventId);
		query.setParameter("msgScenarioCode", msgScenarioCode);
		  return (long) query.executeUpdate();
	}
} 
