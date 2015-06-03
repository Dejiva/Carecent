package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.Announcement;

@Component
public class AnnouncementDao extends BaseDao<Announcement> {

	public void save(Announcement announcememnt) {
		Session session = getCurrentSession();
		session.saveOrUpdate(announcememnt);
	}

	@SuppressWarnings("unchecked")
	public List<Announcement> getAllAnnouncement(Long facilityId) {
		
		String sql = "select A.* from ANNOUNCEMENT A where A.DELETED_FLAG=0 and A.FACILITY_ID=:facilityId order by A. MODIFIED_DATE desc ";
		SQLQuery qry = getCurrentSession().createSQLQuery(sql);
		qry.setParameter("facilityId",  facilityId);
		qry.addEntity(Announcement.class);
		return qry.list();
//		Criteria crit = getCurrentSession().createCriteria(Announcement.class);
//		crit.add(Restrictions.eq("deletedFlag", 0));
//		crit.addOrder(Order.desc("modifiedDate"));
//		return crit.list();
	}

	public Announcement getAnnounceMent(Integer id) {
		Criteria crit = getCurrentSession().createCriteria(Announcement.class);
		crit.add(Restrictions.eq("id", id));
		return (Announcement) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Announcement> getAllActiveAnnouncement(long facilityId) {
		
		String sql = "select A.* from ANNOUNCEMENT A where A.DELETED_FLAG=0 and A.ACTIVE_FLAG=1 and A.FACILITY_ID=:facilityId ";
		SQLQuery qry = getCurrentSession().createSQLQuery(sql);
		qry.setParameter("facilityId",  facilityId);
		qry.addEntity(Announcement.class);
		return qry.list();
		
	} 
}
