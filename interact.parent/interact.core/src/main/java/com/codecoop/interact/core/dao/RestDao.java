package com.codecoop.interact.core.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.rest.dto.RestAlertDto;
import com.codecoop.interact.core.rest.dto.RestCICDto;
import com.codecoop.interact.core.rest.dto.RestFacilitiesDto;
import com.codecoop.interact.core.rest.dto.RestLabOrdersDto;
import com.codecoop.interact.core.rest.dto.RestNotesDto;
import com.codecoop.interact.core.rest.dto.RestPatientObservationDto;
import com.codecoop.interact.core.rest.dto.ShortNotesDto;

/**
 * @author Ramesh
 *
 */
@Component
public class RestDao {

	@Resource
	private SessionFactory sessionFactory;

	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<RestFacilitiesDto> getFacilitiesByUserName(String userName,String passwd){

		String sql1 = "select count(*) from STAFF where USERNAME=:userName and PASSWD=:passwd";
		SQLQuery query1 =  getCurrentSession().createSQLQuery(sql1);
		query1.setParameter("userName",userName);
		query1.setParameter("passwd",passwd);
		if(((Number)query1.uniqueResult()).intValue() == 0){
			return null;
		}
		String sql2 = "select fst.ID as fStaffId,faclty.ID as facilityId,faclty.FACILITY_NAME as facilityName,fst.RELIEVING_DATE as relievingDate  from STAFF st,FACILITY_STAFF fst,FACILITY faclty where USERNAME=:userName and fst.STAFF_ID=st.ID and faclty.ID=fst.FACILITY_ID";
		SQLQuery query2 =  getCurrentSession().createSQLQuery(sql2);
		query2.setParameter("userName",userName);
		query2.addScalar("fStaffId", LongType.INSTANCE);
		query2.addScalar("facilityId", LongType.INSTANCE);
		query2.addScalar("relievingDate", DateType.INSTANCE);
		query2.addScalar("facilityName");
		query2.setResultTransformer(Transformers.aliasToBean(RestFacilitiesDto.class));
		return (List<RestFacilitiesDto>)query2.list();
	}


	public RestPatientObservationDto observationOfPatient(Long pEpisodeId){
		Long sbarId;
		String sbarSql = "select ID from SBAR where PATIENT_EPISODE_ID=:pEpisodeId and TRANSFER_TO_HOSPITAL_FLAG=0 and MANAGE_IN_FACILITY_FLAG=0";
		SQLQuery sbarQuery =  getCurrentSession().createSQLQuery(sbarSql);
		sbarQuery.setParameter("pEpisodeId",pEpisodeId);
		if(!StringUtils.isEmpty(sbarQuery.uniqueResult())){
			sbarId = ((Number)sbarQuery.uniqueResult()).longValue();
			return getObservation(sbarId);
		}else{
			return null;
		}
	}


	public RestPatientObservationDto updateLabwork(Long id,Long sbarId,Long signsSymptomsLabworkId,Boolean approved,Boolean rejected,Long facilityStaffId,String signSymtomLabworkName,Boolean stndingOrder){
		Date today = new Date();
		String labWorkEndDateSql = "update CARE_PATH_LABWORKS set END_DATE=:today where ID=:id";
		SQLQuery  labWorkEndDateSqlQuery =  getCurrentSession().createSQLQuery(labWorkEndDateSql);
		labWorkEndDateSqlQuery.setParameter("today", today);
		labWorkEndDateSqlQuery.setParameter("id", id);
		if(labWorkEndDateSqlQuery.executeUpdate() > 0){
			String createNewlabWorkSql = "insert into CARE_PATH_LABWORKS (SIGNS_SYMPTOMS_LABWORK_ID, APPROVED, REJECTED, FACILITY_STAFF_ID, START_DATE, END_DATE, STANDING_ORDERS, SBAR_ID) VALUES (:signsSymptomsLabworkId, :approved, :rejected, :facilityStaffId,:today, NULL, :stndingOrder, :sbarId)";
			SQLQuery  updatelabOrderQuery =  getCurrentSession().createSQLQuery(createNewlabWorkSql);
			updatelabOrderQuery.setParameter("sbarId",sbarId);
			updatelabOrderQuery.setParameter("signsSymptomsLabworkId",signsSymptomsLabworkId);
			updatelabOrderQuery.setParameter("approved",approved);
			updatelabOrderQuery.setParameter("rejected",rejected);
			updatelabOrderQuery.setParameter("facilityStaffId",facilityStaffId);
			updatelabOrderQuery.setParameter("today",today);
			updatelabOrderQuery.setParameter("stndingOrder",stndingOrder);
			if(updatelabOrderQuery.executeUpdate() > 0){
				return getObservation(sbarId);
			}
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private RestPatientObservationDto getObservation(Long sbarId){
		RestPatientObservationDto result = new RestPatientObservationDto();
		String cic ="SELECT cic.ID as id,cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID as sslAttrId,cic.CHANGE_IN_CONDITION_VALUE cicVal,ssla.ATTR_NAME sslAttrName, ssla.UNITS units "
				+ " FROM CHANGE_IN_CONDITION cic,SIGNS_SYMPTOMS_LABWORK_ATTR ssla  WHERE SBAR_ID=:sbarId and cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID=ssla.ID and ssla.PARENT_ID "
				+ " in (select ID from SIGNS_SYMPTOMS_LABWORK where SIGN_FLAG=1) ";
		SQLQuery cicQuery =  getCurrentSession().createSQLQuery(cic);
		cicQuery.setParameter("sbarId",sbarId);
		cicQuery.addScalar("id", LongType.INSTANCE);
		cicQuery.addScalar("sslAttrId", LongType.INSTANCE);
		cicQuery.addScalar("cicVal");
		cicQuery.addScalar("units");
		cicQuery.addScalar("sslAttrName");
		cicQuery.setResultTransformer(Transformers.aliasToBean(RestCICDto.class));
		result.setCicValues((List<RestCICDto>)cicQuery.list());

		String labOrderSql ="select cpl.ID as id,cpl.SBAR_ID as sbarId, cpl.SIGNS_SYMPTOMS_LABWORK_ID as signsSymptomsLabworkId,cpl.APPROVED as approved,cpl.REJECTED as rejected, "
				+ " cpl.FACILITY_STAFF_ID as facilityStaffId,cpl.CARE_PATH_CODE as carePathCode,cpl.STANDING_ORDERS as stndingOrder,  sl.SIGNS_SYMPTOMS_LABWORK_NAME as signSymtomLabworkName "
				+ " from  CARE_PATH_LABWORKS cpl,SIGNS_SYMPTOMS_LABWORK sl where cpl.SBAR_ID=:sbarId and sl.ID = cpl.SIGNS_SYMPTOMS_LABWORK_ID and cpl.END_DATE is  null GROUP BY cpl.SIGNS_SYMPTOMS_LABWORK_ID";
		SQLQuery labOrderQuery =  getCurrentSession().createSQLQuery(labOrderSql);
		labOrderQuery.setParameter("sbarId",sbarId);
		labOrderQuery.addScalar("id", LongType.INSTANCE);
		labOrderQuery.addScalar("sbarId", LongType.INSTANCE);
		labOrderQuery.addScalar("signsSymptomsLabworkId", LongType.INSTANCE);
		labOrderQuery.addScalar("facilityStaffId", LongType.INSTANCE);
		labOrderQuery.addScalar("approved",BooleanType.INSTANCE);
		labOrderQuery.addScalar("rejected",BooleanType.INSTANCE);
		labOrderQuery.addScalar("stndingOrder",BooleanType.INSTANCE);
		labOrderQuery.addScalar("signSymtomLabworkName");
		labOrderQuery.setResultTransformer(Transformers.aliasToBean(RestLabOrdersDto.class));
		result.setLabOrderValues((List<RestLabOrdersDto>)labOrderQuery.list());

		String notesSql ="select ID as id,SBAR_ID as sbarId,NOTES as notes,NURSING_STAFF_TYPE as staffType,NURSING_FACILITY_STAFF_ID as fStaffId,USER_MODIFIED as modifiedBy, DATE_MODIFIED date from SBAR_NOTES where SBAR_ID=:sbarId";
		SQLQuery notesQuery =  getCurrentSession().createSQLQuery(notesSql);
		notesQuery.setParameter("sbarId",sbarId);
		notesQuery.addScalar("id", LongType.INSTANCE);
		notesQuery.addScalar("sbarId", LongType.INSTANCE);
		notesQuery.addScalar("fStaffId", LongType.INSTANCE);
		notesQuery.addScalar("notes");
		notesQuery.addScalar("staffType");
		notesQuery.addScalar("date");
		notesQuery.addScalar("modifiedBy");
		notesQuery.setResultTransformer(Transformers.aliasToBean(RestNotesDto.class));
		result.setNotesValues((List<RestNotesDto>)notesQuery.list());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List getAlertsByUserName(String userName,String type,String order) {
		if(type.equals("DATE")){
			type="mad.SENT_DATE";
		}else{
			type="fs.FACILITY_NAME";
		}
		if(order.equals("ASC")){
			order="ASC";
		}else{
			order="DESC";
		}
		String sql="select  fs.FACILITY_NAME facilityName, concat(pt.FIRST_NAME,pt.LAST_NAME) patientName, fas.ID fStaffId, pe.ID patientEpisodeId, mad.SENT_DATE sentDate , ma.MSG_BODY msgBody,sfr.ROLE_NAME roleName from FACILITY fs,PATIENT pt,STAFF st, PATIENT_EPISODE pe, MESSAGE_ALERT ma,FACILITY_STAFF fas, MESSAGE_ALERT_DETAILS mad, STAFF_ROLES sfr where fas.ROLE_ID=sfr.ID and fas.ID=mad.TO_FACILITY_STAFF_ID and fas.FACILITY_ID=fs.ID and ma.PATIENT_EPISODE_ID=pe.ID and ma.ID=mad.MESSAGE_ALERT_ID and pe.PATIENT_ID=pt.ID and fas.STAFF_ID= st.ID and st.USERNAME=:userName and mad.DELETE_FLAG=0 and mad.SUSPENDED_FLAG=0 and ma.MSG_ALERT_TYPE='ALERT' order by "+type+" "+order;
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter("userName", userName);
		query.addScalar("facilityName");
		query.addScalar("sentDate");
		query.addScalar("fStaffId",LongType.INSTANCE);
		query.addScalar("patientName");
		query.addScalar("msgBody");
		query.addScalar("roleName");
		query.addScalar("patientEpisodeId",LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(RestAlertDto.class));
		return query.list();
	}

	public List<ShortNotesDto> getShortcutNotes() {
		String sql="select ID id,SHORT_NOTES shortNotes from SHORTCUT_NOTES ";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.addScalar("id",LongType.INSTANCE);
		query.addScalar("shortNotes");
		query.setResultTransformer(Transformers.aliasToBean(ShortNotesDto.class));
		return query.list();

	}

}
