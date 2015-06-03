package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.MessageEligibilityStaff;
@Component
public class MessageEligibilityStaffDaoImpl extends BaseDao<MessageEligibilityStaff> {
	 
	public List<MessageEligibilityStaff> getEligibiltyStaff(Long scenarioId) {
		Session session = getCurrentSession();
		String sql="SELECT * FROM MESSAGE_ELIGIBILITY_STAFF WHERE MESSAGE_ALERT_SCENARIO_ID=:scenarioId";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(MessageEligibilityStaff.class);
		query.setParameter("scenarioId", scenarioId);
		return (List<MessageEligibilityStaff>)query.list();
		
	}
	public List<FacilityStaff> getStaffList(Long facilityId, Long responsibilitiesId) {
		Session session = getCurrentSession();
		String sql="SELECT fs.* FROM FACILITY_STAFF fs, STAFF_ROLES sr WHERE sr.RESPONSIBILITIES_ID=:responsibilitiesId and fs.ROLE_ID=sr.ID and fs.FACILITY_ID=:facilityId";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(FacilityStaff.class);	
		query.setParameter("responsibilitiesId", responsibilitiesId);
		query.setParameter("facilityId", facilityId);
		return (List<FacilityStaff>)query.list();
		
	}
	
	public Boolean getSuspendedFlag(Long msgAlertscenarioId) {
		Session session = getCurrentSession();
		String sql="SELECT DISTINCT SUSPENDED_PREV_SCENARIO_FLAG FROM MESSAGE_ELIGIBILITY_STAFF WHERE MESSAGE_ALERT_SCENARIO_ID=:msgAlertscenarioId";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("msgAlertscenarioId", msgAlertscenarioId);
		return (Boolean)query.uniqueResult();
	}
	
	
	

}
