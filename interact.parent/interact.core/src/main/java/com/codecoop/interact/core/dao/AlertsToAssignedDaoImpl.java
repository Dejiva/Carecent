package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.AlertsToAssigned;
import com.codecoop.interact.core.domain.AlertsToOtherStaff;
import com.codecoop.interact.core.domain.FacilityStaff;

@Component
public class AlertsToAssignedDaoImpl extends BaseDao<AlertsToAssigned> {
	
	public AlertsToAssigned getAlertsToAssignedStaff(Long msgAlertscenarioId) {
		Session session = getCurrentSession();
		String sql="SELECT * FROM ALERTS_TO_ASSIGNED WHERE MESSAGE_ALERT_SCENARIO_ID=:msgAlertscenarioId";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(AlertsToAssigned.class);
		query.setParameter("msgAlertscenarioId", msgAlertscenarioId);
		return (AlertsToAssigned)query.uniqueResult();
		
	}
	
	
	public List<AlertsToOtherStaff> getAlertsToOtherStaff(Long alertsAssignId) {
		Session session = getCurrentSession();
		String sql="SELECT * FROM ALERTS_TO_OTHERSTAFF WHERE  ALERTS_TO_ASSIGNED_ID=:alertsAssignId";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(AlertsToOtherStaff.class);
		query.setParameter("alertsAssignId", alertsAssignId);
		return (List<AlertsToOtherStaff>)query.list();
		
	}
	
	public List<FacilityStaff> getStaffList(Long AlertsToAssignedStaffId) {
		Session session = getCurrentSession();
		String sql="SELECT fs.* FROM FACILITY_STAFF fs, STAFF_ROLES sr WHERE sr.ID=:AlertsToAssignedStaffId AND  fs.ROLE_ID=sr.ID";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(FacilityStaff.class);
		query.setParameter("AlertsToAssignedStaffId", AlertsToAssignedStaffId);
		return (List<FacilityStaff>)query.list();
		
	}
	
	
	

}
