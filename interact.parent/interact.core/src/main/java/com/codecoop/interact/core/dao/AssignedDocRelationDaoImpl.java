package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.AssignedDocRelation;
import com.codecoop.interact.core.domain.MessageEligibilityStaff;

@Component
public class AssignedDocRelationDaoImpl  extends BaseDao<AssignedDocRelation>{

	public List<Long> getDocFacilityStaffList(Long docFacilityStaffId)
	{
		Session session = getCurrentSession();
		String sql="SELECT FACILITY_STAFF_ID FROM ASSIGNED_DOC_RELATION ADR,FACILITY_STAFF FS WHERE ADR.DOCTOR_FACILITY_STAFF_ID=:docFacilityStaffId and FS.ID=ADR.FACILITY_STAFF_ID AND FS.RELIEVING_DATE IS NULL";
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("FACILITY_STAFF_ID", new LongType());
		query.setParameter("docFacilityStaffId", docFacilityStaffId);
		return (List<Long>)query.list();
		
}
	
	
	public AssignedDocRelation getAssignDocRelation(Long facilityStaffId){
		
		Session session = getCurrentSession();
		String sql="SELECT * FROM ASSIGNED_DOC_RELATION WHERE FACILITY_STAFF_ID=:facilityStaffId";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(AssignedDocRelation.class);
		query.setParameter("facilityStaffId", facilityStaffId);
		return (AssignedDocRelation)query.uniqueResult();
	}
}
