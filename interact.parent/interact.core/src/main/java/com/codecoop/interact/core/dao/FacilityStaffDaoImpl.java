package com.codecoop.interact.core.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.StaffRoles;
import com.codecoop.interact.core.dto.FacilityStaffDto;
import com.codecoop.interact.core.model.ForgotPasswordModel;
import com.codecoop.interact.core.model.LoggedUserModel;

@Component
public class FacilityStaffDaoImpl extends BaseDao<FacilityStaff> {

	public FacilityStaff findFacilityStaff(Long facilityId, Long staffId) {

		String sql = "select FS.* from  FACILITY_STAFF FS where FS.FACILITY_ID=:facilityId and FS.STAFF_ID=:staffId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId",facilityId);
		query.setParameter("staffId",staffId);
		query.addEntity(FacilityStaff.class);
		return (FacilityStaff)query.uniqueResult();


	}

	public FacilityStaff addOrUpdateFacilityStaff(FacilityStaff fstaff){
		Session session=getCurrentSession();
		session.saveOrUpdate(fstaff);
		return fstaff;

	}

	public StaffRoles  findFacilityStaffByRole(String roleName) {

		String sql = "select distinct SR.* from  FACILITY_STAFF FS,STAFF_ROLES SR where FS.ROLE_ID=:SR.ID and SR.ROLE_NAME=:roleName";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("roleName",roleName);
		//query.addEntity(StaffRoles.class);
		return (StaffRoles)query.uniqueResult();

		//Criteria crit = getCurrentSession().createCriteria(StaffRoles.class);
		//crit.add(Restrictions.eq("roleName", role));
		//return (StaffRoles) crit.uniqueResult();

	}

	public StaffRoles addFacilityStaffRole(StaffRoles role){
		Session session=getCurrentSession();
		session.saveOrUpdate(role);
		return role;
	}



	@SuppressWarnings("unchecked")
	public List<FacilityStaff> findFacilityStaffListByStaffId(Long staffId){

		String sql = "select FS.* from  FACILITY_STAFF FS where FS.STAFF_ID=:staffId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("staffId",staffId);
		query.addEntity(FacilityStaff.class);
		return query.list();

		//Criteria crit = getCurrentSession().createCriteria(FacilityStaff.class);
		//crit.add(Restrictions.eq("staff.id", staffId));
		//return (List<FacilityStaff>)crit.list();
	}



	//	public StaffRoles findRoleByRoleId(Long roleId){
	//		
	//		String sql = "select distinct SR.* from STAFF_ROLES SR where SR.ID=:roleId";
	//		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
	//		query.setParameter("roleId",roleId);
	//		query.addEntity(StaffRoles.class);
	//		return (StaffRoles)query.uniqueResult();
	//		
	//		//Criteria crit = getCurrentSession().createCriteria(StaffRoles.class);
	//		//crit.add(Restrictions.eq("id", roleId));
	//		//return (StaffRoles) crit.uniqueResult();
	//		
	//	}
	// addeed by chandu
	public FacilityStaff findStaffByFacilityAndStaffId(Long facilityId,Long staffId){

		String sql = "select FS.* from  FACILITY_STAFF FS where FS.FACILITY_ID=:facilityId and FS.STAFF_ID=:staffId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId",facilityId);
		query.setParameter("staffId",staffId);
		query.addEntity(FacilityStaff.class);
		return (FacilityStaff)query.uniqueResult();

		//Criteria crit = getCurrentSession().createCriteria(FacilityStaff.class);
		//crit.add(Restrictions.eq("facility.id", facilityId));
		//crit.add(Restrictions.eq("staff.id", staffId));
		//return (FacilityStaff)crit.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<FacilityStaff> findAllActiveFacilityStaffByFaclityId(Long facilityId) {

		String sql = "select FS.* from  FACILITY_STAFF FS where FS.FACILITY_ID=:facilityId and (FS.RELIEVING_DATE is null or  FS.RELIEVING_DATE > CURDATE())";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId",facilityId);
		query.addEntity(FacilityStaff.class);
		return query.list();

		//Criteria crit = getCurrentSession().createCriteria(FacilityStaff.class);
		//crit.add(Restrictions.eq("facility.id", facilityId));
		//crit.add(Restrictions.isNull("relievingDate"));
		//return (List<FacilityStaff>)crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<ForgotPasswordModel> findStaffByWorkMail(String workMail){

		String sql = "select DISTINCT  S.ID as staffId,S.USERNAME as userName,FS.STAFF_ID as fStaffId,FS.WORK_EMAIL as workEmail from  FACILITY_STAFF FS,STAFF S where S.ID=FS.STAFF_ID and FS.WORK_EMAIL=:workMail";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("workMail",workMail);
		query.addScalar("staffId", LongType.INSTANCE);
		query.addScalar("userName");
		query.addScalar("fStaffId", LongType.INSTANCE);
		query.addScalar("workEmail");
		query.setResultTransformer(Transformers.aliasToBean(ForgotPasswordModel.class));
		return (List<ForgotPasswordModel>)query.list();


		//Criteria crit = getCurrentSession().createCriteria(FacilityStaff.class);
		//crit.add(Restrictions.eq("workEmail",workMail));
		//return  (FacilityStaff)crit.list().get(0); 
		//	  Criteria crit = getCurrentSession().createCriteria(FacilityStaff.class);
		//		crit.add(Restrictions.eq("workEmail",workMail));
		//		return  crit.list(); 
	}

	public List<FacilityStaffDto> getMDPAofCurrentFacility(Long facilityId) {
		String sql="select FS.ID id, FS.STAFF_ID staffId, concat(IFNULL(S.FIRST_NAME,''),' ',IFNULL(S.LAST_NAME,''),' (',R.DESCRIPTION,')') name ,FS.WORK_NUMBER workNumber from FACILITY_STAFF FS,STAFF S,STAFF_ROLES R where FS.FACILITY_ID=:facilityId and FS.STAFF_ID=S.ID and FS.ROLE_ID=R.ID and R.ROLE_NAME in ('ROLE_PA','ROLE_MD','ROLE_NP')";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId",facilityId);
		query.addScalar("id",new LongType());
		query.addScalar("name");
		query.addScalar("staffId",new LongType());
		query.addScalar("workNumber");
		query.setResultTransformer(Transformers.aliasToBean(FacilityStaffDto.class));
		// TODO Auto-generated method stub
		return query.list();
	}

	public List<FacilityStaffDto> getMDofCurrentFacility(Long facilityId) {
		String sql="select FS.ID id, FS.STAFF_ID staffId, concat(IFNULL(S.FIRST_NAME,''),' ',IFNULL(S.LAST_NAME,''),' (',R.DESCRIPTION,')') name ,FS.WORK_NUMBER workNumber from FACILITY_STAFF FS,STAFF S,STAFF_ROLES R where FS.FACILITY_ID=:facilityId and FS.STAFF_ID=S.ID and FS.ROLE_ID=R.ID and R.ROLE_NAME in ('ROLE_MD') and (FS.RELIEVING_DATE is null or FS.RELIEVING_DATE > CURDATE()) ";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId",facilityId);
		query.addScalar("id",new LongType());
		query.addScalar("name");
		query.addScalar("staffId",new LongType());
		query.addScalar("workNumber");
		query.setResultTransformer(Transformers.aliasToBean(FacilityStaffDto.class));
		// TODO Auto-generated method stub
		return query.list();
	}
	public FacilityStaffDto getPatientAsignedDoc(Long patientEpisodeId) {
		String sql="select FS.ID id, FS.STAFF_ID staffId, FS.RELIEVING_DATE relievingDate, concat(IFNULL(S.FIRST_NAME,''),' ',IFNULL(S.LAST_NAME,''),' (',R.DESCRIPTION,')') name ,FS.WORK_NUMBER workNumber from FACILITY_STAFF FS,STAFF S,STAFF_ROLES R,PATIENT_EPISODE PE where PE.ID=:patientEpsiodeId and PE.PCP_ID= FS.ID and FS.STAFF_ID=S.ID and FS.ROLE_ID=R.ID ";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("patientEpsiodeId",patientEpisodeId);
		query.addScalar("id",new LongType());
		query.addScalar("name");
		query.addScalar("staffId",new LongType());
		query.addScalar("workNumber");
		query.addScalar("relievingDate");
		query.setResultTransformer(Transformers.aliasToBean(FacilityStaffDto.class));
		// TODO Auto-generated method stub
		return (FacilityStaffDto)query.uniqueResult();
	}

	public LoggedUserModel getLoggedUser(Long staffId,Long facilityId){
		String sql = "select st.ID as id,fs.ID as fstaffId,st.USERNAME as userName,fs.FACILITY_ID as facilityId,f.FACILITY_NAME as facilityName, concat(IFNULL(st.FIRST_NAME,''),' ',IFNULL(st.MIDDLE_NAME,''),' ',IFNULL(st.LAST_NAME,'')) as fullName,st.USER_MODIFIED as userModified, "
				+ " fs.ROLE_ID as roleId, resp.RESPONSIBILITY_ROLE as responsibilityName, srole.DESCRIPTION as roleName,resp.ID as responsibilityId,srole.ROLE_NAME as roleValue,  "
				+ " f.STANDING_ORDERS as standingOrders,(select fc.CONTACT from FACILITY_CONTACT fc where fc.CONTACT_TYPE='Mobile/Phone No' and fc.FACILITY_ID=f.ID) as fcontact  from STAFF st,FACILITY_STAFF fs, STAFF_ROLES srole,FACILITY f, "
				+ "RESPONSIBILITIES resp where  "
				+ "st.ID=fs.STAFF_ID and fs.FACILITY_ID=:facilityId and st.ID=:staffId and  srole.RESPONSIBILITIES_ID = resp.ID "
				+ "and srole.ID=fs.ROLE_ID and f.ID=fs.FACILITY_ID";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("staffId",staffId);
		query.setParameter("facilityId",facilityId);
		query.addScalar("id",LongType.INSTANCE);
		query.addScalar("fstaffId",LongType.INSTANCE);
		query.addScalar("userName");
		query.addScalar("userModified");
		query.addScalar("facilityId",LongType.INSTANCE);
		query.addScalar("facilityName");
		query.addScalar("fcontact");
		query.addScalar("standingOrders",BooleanType.INSTANCE);
		query.addScalar("roleId",LongType.INSTANCE);
		query.addScalar("roleName");
		query.addScalar("roleValue");
		query.addScalar("fullName");
		query.addScalar("responsibilityId",LongType.INSTANCE);
		query.addScalar("responsibilityName");
		query.setResultTransformer(Transformers.aliasToBean(LoggedUserModel.class));
		return (LoggedUserModel)query.uniqueResult();
	}

	public Long getAssignedMDByFStaffId(Long fStaffId){
		String sql = "select adr.DOCTOR_FACILITY_STAFF_ID from ASSIGNED_DOC_RELATION adr,FACILITY_STAFF fs where fs.ID=adr.DOCTOR_FACILITY_STAFF_ID and (fs.RELIEVING_DATE is null or fs.RELIEVING_DATE > CURDATE()) and adr.FACILITY_STAFF_ID=:fStaffId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("fStaffId",fStaffId);
		BigInteger result = (BigInteger)query.uniqueResult();
		if(StringUtils.isEmpty(result)){
			return null;
		}
		return result.longValue();
	}

	@SuppressWarnings("unchecked")
	public List<FacilityStaffDto> getNursesOfCurrentFacility(Long facilityId){
		String sql = " select fs.ID as id,concat(IFNULL(s.FIRST_NAME,''),' ',IFNULL(s.MIDDLE_NAME,''),' ',IFNULL(s.LAST_NAME,''),' (',srole.DESCRIPTION,')') as name,fs.WORK_NUMBER as workNumber from FACILITY_STAFF "
				+ " fs,RESPONSIBILITIES resp,STAFF_ROLES srole,STAFF s where fs.FACILITY_ID=:facilityId "
				+ " and resp.RESPONSIBILITY_ROLE='NURSE' and srole.ID=fs.ROLE_ID and resp.ID=srole.RESPONSIBILITIES_ID and s.ID=fs.STAFF_ID  and (fs.RELIEVING_DATE is null or fs.RELIEVING_DATE > CURDATE()) ";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId", facilityId);
		query.addScalar("id",LongType.INSTANCE);
		query.addScalar("name");
		query.addScalar("workNumber");
		query.setResultTransformer(Transformers.aliasToBean(FacilityStaffDto.class));
		return (List<FacilityStaffDto>)query.list();
	}

}
