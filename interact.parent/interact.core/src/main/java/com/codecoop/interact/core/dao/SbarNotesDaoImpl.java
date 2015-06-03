package com.codecoop.interact.core.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.ChangeInCondition;
import com.codecoop.interact.core.domain.FacilityStaff;

import com.codecoop.interact.core.domain.MessageEligibilityStaff;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.SbarNotes;
import com.codecoop.interact.core.domain.Staff;

@Component
public class SbarNotesDaoImpl extends BaseDao<SbarNotes> {

	@SuppressWarnings("unchecked")
	public List<SbarNotes> findBySbarIdAndStaffType(Sbar sbar,String nursingStaffType) {
		
		String sql = "select SN.* from  SBAR_NOTES SN where SN. SBAR_ID=:sbar and SN.NURSING_STAFF_TYPE=:nursingStaffType";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbar", sbar);
		query.setParameter("nursingStaffType", nursingStaffType);
		query.addEntity(SbarNotes.class);
		return query.list();
	
		
	}

	@SuppressWarnings("unchecked")
	public List<FacilityStaff> findAllNursingStaff(Sbar sbar, String nursingStaffType) {
		
		String sql = "select distinct FS.* from  SBAR_NOTES SN ,FACILITY_STAFF FS where FS.ID=SN.NURSING_FACILITY_STAFF_ID and SN.SBAR_ID=:sbar and SN.NURSING_STAFF_TYPE=:nursingStaffType";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbar", sbar);
		query.setParameter("nursingStaffType", nursingStaffType);
		query.addEntity(FacilityStaff.class);
		return query.list();
	
		
		
	
	}

	@SuppressWarnings("unchecked")
	public List<SbarNotes> findBySbarIdAndStaff(Sbar sbar, FacilityStaff nursingFStaff) {

	
		String sql="select * from SBAR_NOTES WHERE SBAR_ID=:sbarId and NURSING_FACILITY_STAFF_ID=:nursingFStaffId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId", sbar.getId());
		query.setParameter("nursingFStaffId", nursingFStaff.getId());
		query.addEntity(SbarNotes.class);
		return query.list();
	}
	
	
}
