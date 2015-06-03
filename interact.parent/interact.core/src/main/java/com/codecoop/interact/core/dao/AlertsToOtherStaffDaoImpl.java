package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.AlertsToAssigned;
import com.codecoop.interact.core.domain.AlertsToOtherStaff;
import com.codecoop.interact.core.domain.FacilityStaff;

@Component
 
public class AlertsToOtherStaffDaoImpl extends BaseDao<AlertsToOtherStaff> { 
	
	 public    List<FacilityStaff>  getOtherStaffList(String alertsToOther,Long facilityId)
	 {
	    Session session = getCurrentSession();
		String sql="SELECT  fs.* from FACILITY_STAFF fs,STAFF_ROLES sr where sr.DESCRIPTION=:alertsToOther  and fs.FACILITY_ID=:facilityId and fs.role_id=sr.id";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(FacilityStaff.class);
		query.setParameter("alertsToOther", alertsToOther);
		query.setParameter("facilityId", facilityId);
		return ( List<FacilityStaff>)query.list();
			
		 
	 }
	
}


