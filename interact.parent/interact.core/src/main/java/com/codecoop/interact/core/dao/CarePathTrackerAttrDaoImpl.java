package com.codecoop.interact.core.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.CarePathTracker;
import com.codecoop.interact.core.domain.CarePathTrackerAttr;
import com.codecoop.interact.core.dto.CPAttrValueDto;
import com.codecoop.interact.core.dto.CPAttrValueResponseDto;
import com.codecoop.interact.core.dto.CarePathNotifyDoctorMessageDto;
import com.codecoop.interact.core.dto.RunningCarePathAttrDetails;
import com.codecoop.interact.core.util.Constants;

@Component
@SuppressWarnings("unchecked")
public class CarePathTrackerAttrDaoImpl extends BaseDao<CarePathTrackerAttr> {

	//@SuppressWarnings("unchecked")
	public CarePathTrackerAttr findByCPathCodeAndCPTId(String carePathAttrCode,CarePathTracker carePathTracker) {
		
		String sql = "select CA.* from CARE_PATH_TRACKER_ATTR CA where CA.CARE_PATH_ATTR_CODE=:carePathAttrCode and CA.CARE_PATH_TRACKER_ID=:carePathTrackerId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("carePathAttrCode", carePathAttrCode);
		query.setParameter("carePathTrackerId", carePathTracker.getId());
		query.addEntity(CarePathTrackerAttr.class);
		return  (CarePathTrackerAttr)query.uniqueResult();
		

	}
	
	public List<CarePathTrackerAttr> findByCPT(CarePathTracker carePathTracker) {
		String sql = "select CA.* from CARE_PATH_TRACKER_ATTR CA where  CA.CARE_PATH_TRACKER_ID=:carePathTrackerId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("carePathTrackerId", carePathTracker.getId());
		query.addEntity(CarePathTrackerAttr.class);
		return query.list();
	}
		
	
	public List<RunningCarePathAttrDetails> getRunningCarePathAndAttrDetails(Long sbarId){
		String sql = "select C.CARE_PATH_CODE carePathCode, C.CARE_PATH_NAME carePathName, C.XML_FILE_LOCATION xmlFileLoacation, CA.CARE_PATH_ATTR_CODE carePathAttrCode, CA.CARE_PATH_ATTR_NAME carePathAttrName from CARE_PATH C, CARE_PATH_TRACKER CT, CARE_PATH_TRACKER_ATTR CA where CT.SBAR_ID=:sbarId and CA.CARE_PATH_TRACKER_ID=CT.ID and C.ID=CT.CARE_PATH_ID";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId", sbarId);
		query.addScalar("carePathCode");
		query.addScalar("carePathName");
		query.addScalar("xmlFileLoacation");
		query.addScalar("carePathAttrCode");
		query.addScalar("carePathAttrName");
		query.setResultTransformer(Transformers.aliasToBean(RunningCarePathAttrDetails.class));
		return query.list();
		
		
	}

	public 	List<CarePathNotifyDoctorMessageDto> getNotifyDoctorRecords(Long sbarId) {
		
		String sql = "select C.CARE_PATH_NAME carePathName,CT.STEP_NAME stepName, CA.CARE_PATH_ATTR_NAME carePathAttrName from CARE_PATH C, CARE_PATH_TRACKER CT, CARE_PATH_TRACKER_ATTR CA where CT.SBAR_ID=:sbarId and CA.CARE_PATH_TRACKER_ID=CT.ID and C.ID=CT.CARE_PATH_ID and CA.NOTIFY_DOCTOR_FLAG=:notifyDoctorFlag";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId", sbarId);
		query.setParameter("notifyDoctorFlag",true);
		query.addScalar("carePathName");
		query.addScalar("stepName");
		query.addScalar("carePathAttrName");
		query.setResultTransformer(Transformers.aliasToBean(CarePathNotifyDoctorMessageDto.class));
		return query.list();
	}

	public List<CarePathTrackerAttr> findByCareapthCode(String carePathAttrCode,Long sbarId) {

		String sql = "select CA.* from CARE_PATH_TRACKER CT, CARE_PATH_TRACKER_ATTR CA where CT.SBAR_ID=:sbarId and CA.CARE_PATH_TRACKER_ID=CT.ID and CA.CARE_PATH_ATTR_CODE=:carePathAttrCode";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId", sbarId);
		query.setParameter("carePathAttrCode", carePathAttrCode);
		return query.list();
	}

	public List<CPAttrValueResponseDto> getCPTAttr(CarePathTracker carePathTracker) {
		
		String sql = "select CA.CARE_PATH_ATTR_CODE carePathAttrCode,CA.CARE_PATH_ATTR_NAME carePathAttrName,CA.VALUE value,CA.NOTIFY_DOCTOR_FLAG notifyDoctorFlag,CA.MAX_VALUE maxVal ,CA.MIN_VALUE minVal,CA.DATA_TYPE dataType from  CARE_PATH_TRACKER_ATTR CA where CA.CARE_PATH_TRACKER_ID=:carePathTracker";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("carePathTracker", carePathTracker);
		
		query.addScalar("carePathAttrCode");
		query.addScalar("value");
		query.addScalar("carePathAttrName");
		query.addScalar("notifyDoctorFlag");
		query.addScalar("minVal");
		query.addScalar("maxVal");
		query.addScalar("dataType");
		query.setResultTransformer(Transformers.aliasToBean(CPAttrValueResponseDto.class));
		return query.list();
		
	}

	public void deleteAllCarePathTrackerAtt(CarePathTracker carePathTracker) {
		String sql = "delete from CARE_PATH_TRACKER_ATTR where CARE_PATH_TRACKER_ID:=carePathTrackerId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("carePathTrackerId", carePathTracker.getId());
	}

	public List<CarePathTrackerAttr> getNotifyDocterRecords(CarePathTracker carePathTracker) {
		
		String sql = "select CA.* from CARE_PATH_TRACKER_ATTR CA where  CA.CARE_PATH_TRACKER_ID=:carePathTracker and CA.NOTIFY_DOCTOR_FLAG=1";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("carePathTracker", carePathTracker);
		query.addEntity(CarePathTrackerAttr.class);
		return query.list();
	}

	public List<String> findAllLabWorkForPatient(Long sbarId) {
		String considerorders=Constants.CONSIDER_ORDERS;
		String sql = "select distinct CA.CARE_PATH_ATTR_CODE from CARE_PATH_TRACKER_ATTR CA ,CARE_PATH_TRACKER CT where  CT.SBAR_ID=:sbarId and CA.CARE_PATH_TRACKER_ID=CT.ID and CT.STEP_TYPE=:considerorders";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId", sbarId);
		query.setParameter("considerorders", considerorders);
		//query.addEntity(CarePathTrackerAttr.class);
		return query.list();
	}

	
}
