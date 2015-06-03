package com.codecoop.interact.core.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.AlertsToAssigned;
import com.codecoop.interact.core.domain.MessageAlertScenarios;
import com.codecoop.interact.core.domain.StopAndWatchHistory;
import com.codecoop.interact.core.dto.PatientDto;
import com.codecoop.interact.core.dto.StopAndWatchHistoryDto;
@Component
public class MessageAlertScenariosDaoImpl extends BaseDao<MessageAlertScenarios>{
	public MessageAlertScenarios getMsgScenarioById(String msgAlertScenarioCode)
	{
	
		Session session = getCurrentSession();
		String sql="SELECT * FROM MESSAGE_ALERT_SCENARIOS where CODE=:msgAlertScenarioCode";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(MessageAlertScenarios.class);
		query.setParameter("msgAlertScenarioCode", msgAlertScenarioCode);
		
		return(MessageAlertScenarios) query.uniqueResult();
		
	}
	public AlertsToAssigned getAlertsTemplateByScenarioId(Long msgAlertScenarioCode)
	{
		Session session = getCurrentSession();
		String sql="SELECT * FROM ALERTS_TO_ASSIGNED WHERE MESSAGE_ALERT_SCENARIO_ID=:msgAlertScenarioCode";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(AlertsToAssigned.class);
		query.setParameter("msgAlertScenarioCode", msgAlertScenarioCode);
		return(AlertsToAssigned) query.uniqueResult();
		}
		
	
}
