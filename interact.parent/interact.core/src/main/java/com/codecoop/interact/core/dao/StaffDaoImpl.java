package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.dto.UserManageDto;

@Component
public class StaffDaoImpl extends BaseDao<Staff> {

	public Staff findStaffByUserName(String userName) {
		Criteria crit = getCurrentSession().createCriteria(Staff.class);
		crit.add(Restrictions.eq("username", userName));
		return (Staff) crit.uniqueResult();

	}
	public Staff findStaffByName(String firstName) {
		Criteria crit = getCurrentSession().createCriteria(Staff.class);
		crit.add(Restrictions.eq("firstName", firstName));
		return (Staff) crit.uniqueResult();

	}

	public Staff findStaffByStaffId(Long staffId) {
		Criteria crit = getCurrentSession().createCriteria(Staff.class);
		crit.add(Restrictions.eq("id", staffId));
		return (Staff) crit.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<UserManageDto> getAllStaffMembersInFacility(Long facilityId){

		Session session = getCurrentSession();

		String sql = "select s.ID staffId, concat(IFNULL(s.FIRST_NAME,''),' ',IFNULL(s.MIDDLE_NAME,''),' ',IFNULL(s.LAST_NAME,'')) name, s.USERNAME userName, "
				+ "fs.WORK_NUMBER as mobileNumber, "
				+ "fs.WORK_EMAIL as personalEmail, "
				+ "fs.FACILITY_ID facilityId, fs.ROLE_ID roleId, "
				+ "fs.RELIEVING_DATE relivingDate,f.FACILITY_NAME as facilityName,str.DESCRIPTION as roleDescription "
				+ "from STAFF s, FACILITY_STAFF fs,FACILITY f,STAFF_ROLES str "
				+ "where s.ID = fs.STAFF_ID and f.ID=fs.FACILITY_ID and str.ID=fs.ROLE_ID	and fs.FACILITY_ID =:facilityId and fs.relieving_date  is null";
		//		String sql = "select s.ID staffId, CONCAT(s.FIRST_NAME, ' ', s.LAST_NAME) name, s.USERNAME userName, "
		//				+ "(select sc.CONTACT from STAFF_CONTACT sc where sc.STAFF_ID = s.ID and sc.CONTACT_TYPE = 'mobile' and sc.PRIMARY_FLAG = true limit 1) mobileNumber, "
		//				+ "(select sc.CONTACT from STAFF_CONTACT sc where sc.STAFF_ID = s.ID and sc.CONTACT_TYPE = 'email' and sc.PRIMARY_FLAG = true limit 1) personalEmail, "
		//				+ "fs.FACILITY_ID facilityId, fs.ROLE_ID roleId, "
		//				+ "fs.RELIEVING_DATE relivingDate "
		//				+ "from STAFF s, FACILITY_STAFF fs "
		//				+ "where s.ID = fs.STAFF_ID	and fs.FACILITY_ID =:facilityId and fs.relieving_date  is null";
		SQLQuery query =  session.createSQLQuery(sql);
		query.setParameter("facilityId", facilityId);
		query.addScalar("name");
		query.addScalar("staffId", new LongType());
		query.addScalar("userName");
		query.addScalar("mobileNumber");
		query.addScalar("personalEmail");
		query.addScalar("roleDescription");
		query.addScalar("facilityName");
		query.addScalar("facilityId", new LongType());
		query.addScalar("relivingDate", new DateType());
		query.addScalar("roleId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(UserManageDto.class));
		List<UserManageDto> userManageDtoList =	query.list();
		if(!CollectionUtils.isEmpty(userManageDtoList)){
			return userManageDtoList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<UserManageDto> getAllStaffMembersIByUserName(String userName, Long facilityId){

		Session session = getCurrentSession();

		String sql = "select s.ID as staffId,concat(IFNULL(s.FIRST_NAME,''),' ',IFNULL(s.MIDDLE_NAME,''),' ',IFNULL(s.LAST_NAME,'')) as name,s.USERNAME as userName, "
				+ " fs.FACILITY_ID as facilityId,fs.WORK_NUMBER as mobileNumber,  "
				+ " fs.WORK_EMAIL as personalEmail,fs.ROLE_ID as roleId,f.FACILITY_NAME as facilityName,str.DESCRIPTION as roleDescription, "
				+ " fs.RELIEVING_DATE as relivingDate from FACILITY_STAFF fs,STAFF s,FACILITY f,STAFF_ROLES str  "
				+ " where fs.STAFF_ID=s.ID and f.ID=fs.FACILITY_ID and  str.ID=fs.ROLE_ID and s.USERNAME like '%"+userName+"%' and s.USERNAME <> 'guest' ";
		SQLQuery query =  session.createSQLQuery(sql);
		//query.setParameter("facilityId", facilityId);
		//query.setParameter("userName", userName);
		query.addScalar("name");
		query.addScalar("staffId", new LongType());
		query.addScalar("userName");
		query.addScalar("mobileNumber");
		query.addScalar("personalEmail");
		query.addScalar("roleDescription");
		query.addScalar("facilityName");
		query.addScalar("facilityId", new LongType());
		query.addScalar("relivingDate", new DateType());
		query.addScalar("roleId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(UserManageDto.class));
		List<UserManageDto> userManageDtoList =	query.list();
		if(!CollectionUtils.isEmpty(userManageDtoList)){
			boolean isExists=false;
			for(UserManageDto item:userManageDtoList){
				if(item.getFacilityId() == facilityId){
					isExists=true;
				}
			}
			if(isExists){
				String sql2 = "select s.ID as staffId,concat(IFNULL(s.FIRST_NAME,''),' ',IFNULL(s.MIDDLE_NAME,''),' ',IFNULL(s.LAST_NAME,'')) as name,s.USERNAME as userName, "
						+ " fs.FACILITY_ID as facilityId,fs.WORK_NUMBER as mobileNumber,  "
						+ " fs.WORK_EMAIL as personalEmail,fs.ROLE_ID as roleId,f.FACILITY_NAME as facilityName,str.DESCRIPTION as roleDescription, "
						+ " fs.RELIEVING_DATE as relivingDate from FACILITY_STAFF fs,STAFF s,FACILITY f,STAFF_ROLES str  "
						+ " where fs.STAFF_ID=s.ID and f.ID=fs.FACILITY_ID and  str.ID=fs.ROLE_ID and s.USERNAME like '%"+userName+"%' and s.USERNAME <> 'guest' and fs.FACILITY_ID="+facilityId;
				SQLQuery query2 =  session.createSQLQuery(sql2);
				//query.setParameter("facilityId", facilityId);
				//query.setParameter("userName", userName);
				query2.addScalar("name");
				query2.addScalar("staffId", new LongType());
				query2.addScalar("userName");
				query2.addScalar("mobileNumber");
				query2.addScalar("personalEmail");
				query2.addScalar("roleDescription");
				query2.addScalar("facilityName");
				query2.addScalar("facilityId", new LongType());
				query2.addScalar("relivingDate", new DateType());
				query2.addScalar("roleId", new LongType());
				query2.setResultTransformer(Transformers.aliasToBean(UserManageDto.class));
				List<UserManageDto> userManageDtoList2 =	query2.list();
				return userManageDtoList2;
			}
			return userManageDtoList;
		}
		return null;
	}
	public void addMdAssigned(FacilityStaff fStaff) {
		Session session = getCurrentSession();

	}	


}
