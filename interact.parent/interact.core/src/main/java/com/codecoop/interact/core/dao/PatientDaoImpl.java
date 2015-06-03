package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.Patient;
import com.codecoop.interact.core.dto.PatientDto;
import com.codecoop.interact.core.dto.SSNCheckDto;
import com.codecoop.interact.core.model.PatientDetailsModel;

@Component
public class PatientDaoImpl extends BaseDao<Patient>{
	@SuppressWarnings("unchecked")
	public List<PatientDto> findByName(String patient_name,Long facilityId){

		Session session = getCurrentSession();
		String sql="select p.ID patientId, p.FIRST_NAME patientFirstName,p.LAST_NAME patientLastName from INTERACT.PATIENT p,INTERACT.PATIENT_EPISODE pe where p.ID=pe.PATIENT_ID and (p.FIRST_NAME like :patientName or p.LAST_NAME like :patientName ) and pe.FACILITY_ID=:facilityId and pe.FACILITY_DISCHARGE_DATE is null ";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("patientName", patient_name+"%");
		query.setParameter("facilityId", facilityId);

		query.addScalar("patientFirstName");
		query.addScalar("patientLastName");
		query.addScalar("patientId",new LongType());
		query.setResultTransformer(Transformers.aliasToBean(PatientDto.class));
		return (List<PatientDto>)query.list();
	}



	public Patient findPatientBySSNumber(String ssn){
		Criteria crit = getCurrentSession().createCriteria(Patient.class);
		crit.add(Restrictions.like("sSNumber", ssn,MatchMode.EXACT));
		return (Patient)crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<PatientDetailsModel> findFacilityPatientsByPatientId(Long facilityId,String[] patientName)
	{
		String firstName=patientName[0].trim();
		String lastName=null;
		String checkNameCondition="pnt.FIRST_NAME like :firstName or pnt.LAST_NAME like :firstName";
		if(patientName.length>1){
			if(patientName[1].trim().length()>0){
				lastName=patientName[1].trim();}
			else{
				lastName=patientName[2].trim();
			}
			checkNameCondition="pnt.FIRST_NAME like :firstName or  pnt.LAST_NAME like :lastName";
		}

		String sql ="select pnt.ID as id,TRIM(CONCAT(IFNULL(pnt.FIRST_NAME,''),' ',IFNULL(pnt.LAST_NAME,''))) as fullName,p.ID as patientEpisodeId,p.S_AND_W_ELIGIBLE as stopAndWatchEligible, "
				+ "(select count(*) from PATIENT_EPISODE where ID=patientEpisodeId ) admissionQueue,"
				+" (select count(*) from STOP_AND_WATCH sw1 where sw1.PATIENT_EPISODE_ID=patientEpisodeId and sw1.END_DATE is NULL) stopAndWathQueue,"
				+" (select count(*) from SBAR sb1 where sb1.PATIENT_EPISODE_ID=patientEpisodeId and sb1.TRANSFER_TO_HOSPITAL_FLAG=0 and MANAGE_IN_FACILITY_FLAG=0) observationQueue, "
				+" (select count(*) from HOSPITAL_TRANSFER where STAY_IN_ACUTECARE_FLAG=0 and SEND_TO_HOSPITAL_FLAG=0 and PATIENT_EPISODE_ID=patientEpisodeId) acutecareQueue, "
				+" (select count(*) from PATIENT_EPISODE where ID=patientEpisodeId ) activePatient, "
				+" (select count(*) from PATIENT_EPISODE pe where pe.FACILITY_DISCHARGE_DATE is null and pe.FACILITY_ID=:facilityId) admissionQueueCount, "
				+" (select count(*) from STOP_AND_WATCH sw1,PATIENT_EPISODE pe1 where sw1.END_DATE is NULL and sw1.PATIENT_EPISODE_ID=pe1.ID and pe1.FACILITY_ID=:facilityId and pe1.FACILITY_DISCHARGE_DATE is null) stopAndWathQueueCount, "
				+" (select count(*) from SBAR sb1,PATIENT_EPISODE pe1 where sb1.TRANSFER_TO_HOSPITAL_FLAG=0 and MANAGE_IN_FACILITY_FLAG=0 and sb1.PATIENT_EPISODE_ID=pe1.ID and pe1.FACILITY_ID=:facilityId and pe1.FACILITY_DISCHARGE_DATE is null) observationQueueCount, "
				+" (select count(*) from HOSPITAL_TRANSFER ht1,PATIENT_EPISODE pe1 where ht1.STAY_IN_ACUTECARE_FLAG=0 and ht1.SEND_TO_HOSPITAL_FLAG=0 and ht1.PATIENT_EPISODE_ID=pe1.ID and pe1.FACILITY_ID=:facilityId and pe1.FACILITY_DISCHARGE_DATE is null) acutecareQueueCount, "
				+ " exists (select * from STOP_AND_WATCH_HISTORY stpwHist,STOP_AND_WATCH stpw where stpwHist.STOP_AND_WATCH_ID=stpw.ID and  stpw.PATIENT_EPISODE_ID=p.ID and (stpwHist.FACILITY_STAF_RESPONSE !='' and stpwHist.FACILITY_STAF_RESPONSE is not null) and stpw.END_DATE is null) as hasResponse, "
				+ " (select count(*) from STOP_AND_WATCH_HISTORY stpwHist,STOP_AND_WATCH stpw where  stpwHist.STOP_AND_WATCH_ID=stpw.ID and stpwHist.INVALIDATE_FLAG=0 and stpwHist.DELETE_FLAG=0 and stpw.PATIENT_EPISODE_ID=p.ID and stpw.END_DATE is null) as stpwCount "
				+" from PATIENT_EPISODE p,PATIENT pnt "
				+" where p.FACILITY_ID=:facilityId and ("+checkNameCondition +") and p.FACILITY_DISCHARGE_DATE is null and pnt.ID = p.PATIENT_ID order by pnt.FIRST_NAME ";
		//	pnt.FIRST_NAME like :patientName or pnt.LAST_NAME like :patientName
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId",facilityId);
		query.setParameter("firstName",firstName+"%");
		if(lastName!=null){
			query.setParameter("lastName",lastName+"%");
		}
		query.addScalar("id",LongType.INSTANCE);
		query.addScalar("fullName");
		query.addScalar("patientEpisodeId",LongType.INSTANCE);
		query.addScalar("hasResponse",BooleanType.INSTANCE);
		query.addScalar("stopAndWatchEligible",BooleanType.INSTANCE);
		query.addScalar("stpwCount",LongType.INSTANCE);
		query.addScalar("admissionQueue",BooleanType.INSTANCE);
		query.addScalar("stopAndWathQueue",BooleanType.INSTANCE);
		query.addScalar("observationQueue",BooleanType.INSTANCE);
		query.addScalar("acutecareQueue",BooleanType.INSTANCE);
		query.addScalar("admissionQueueCount",LongType.INSTANCE);
		query.addScalar("stopAndWathQueueCount",LongType.INSTANCE);
		query.addScalar("observationQueueCount",LongType.INSTANCE);
		query.addScalar("acutecareQueueCount",LongType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(PatientDetailsModel.class));
		return (List<PatientDetailsModel>)query.list();
	}


	@SuppressWarnings("unchecked")
	public List<PatientDetailsModel> populateAssignedPatients(Long facilityId,Long pcpId)
	{
		String sql ="select pnt.ID as id,TRIM(CONCAT(IFNULL(pnt.FIRST_NAME,''),' ',IFNULL(pnt.LAST_NAME,''))) as fullName,p.ID as patientEpisodeId,p.S_AND_W_ELIGIBLE as stopAndWatchEligible, "
				+ "(select count(*) from PATIENT_EPISODE where ID=patientEpisodeId ) admissionQueue,"
				+" (select count(*) from STOP_AND_WATCH sw1 where sw1.PATIENT_EPISODE_ID=patientEpisodeId and sw1.END_DATE is NULL) stopAndWathQueue,"
				+" (select count(*) from SBAR sb1 where sb1.PATIENT_EPISODE_ID=patientEpisodeId and sb1.TRANSFER_TO_HOSPITAL_FLAG=0 and MANAGE_IN_FACILITY_FLAG=0) observationQueue, "
				+" (select count(*) from HOSPITAL_TRANSFER where STAY_IN_ACUTECARE_FLAG=0  and PATIENT_EPISODE_ID=patientEpisodeId) acutecareQueue, "
				+" (select count(*) from PATIENT_EPISODE where ID=patientEpisodeId ) activePatient, "
				+" (select count(*) from PATIENT_EPISODE pe where pe.FACILITY_DISCHARGE_DATE is null and pe.FACILITY_ID=:facilityId and pe.PCP_ID=:pcpId) admissionQueueCount, "
				+" (select count(*) from STOP_AND_WATCH sw1,PATIENT_EPISODE pe1 where sw1.END_DATE is NULL and sw1.PATIENT_EPISODE_ID=pe1.ID and pe1.FACILITY_ID=:facilityId and pe1.PCP_ID=:pcpId and pe1.FACILITY_DISCHARGE_DATE is null) stopAndWathQueueCount, "
				+" (select count(*) from SBAR sb1,PATIENT_EPISODE pe1 where sb1.TRANSFER_TO_HOSPITAL_FLAG=0 and MANAGE_IN_FACILITY_FLAG=0 and sb1.PATIENT_EPISODE_ID=pe1.ID and pe1.PCP_ID=:pcpId and pe1.FACILITY_ID=:facilityId and pe1.FACILITY_DISCHARGE_DATE is null) observationQueueCount, "
				+" (select count(*) from HOSPITAL_TRANSFER ht1,PATIENT_EPISODE pe1 where ht1.STAY_IN_ACUTECARE_FLAG=0  and ht1.PATIENT_EPISODE_ID=pe1.ID and pe1.FACILITY_ID=:facilityId and pe1.PCP_ID=:pcpId and pe1.FACILITY_DISCHARGE_DATE is null) acutecareQueueCount, "
				+ " exists (select * from STOP_AND_WATCH_HISTORY stpwHist,STOP_AND_WATCH stpw where stpwHist.STOP_AND_WATCH_ID=stpw.ID and  stpw.PATIENT_EPISODE_ID=p.ID and (stpwHist.FACILITY_STAF_RESPONSE !='' and stpwHist.FACILITY_STAF_RESPONSE is not null) and stpw.END_DATE is null) as hasResponse, "
				+ " (select count(*) from STOP_AND_WATCH_HISTORY stpwHist,STOP_AND_WATCH stpw where  stpwHist.STOP_AND_WATCH_ID=stpw.ID and stpwHist.INVALIDATE_FLAG=0 and stpwHist.DELETE_FLAG=0 and  stpw.PATIENT_EPISODE_ID=p.ID and stpw.END_DATE is null) as stpwCount, "
				+ "exists (select SEND_TO_HOSPITAL_FLAG from HOSPITAL_TRANSFER where SEND_TO_HOSPITAL_FLAG=1 and STAY_IN_ACUTECARE_FLAG=0 and PATIENT_EPISODE_ID=patientEpisodeId) as stayedInHosp "
				+" from PATIENT_EPISODE p,PATIENT pnt "
				+" where p.FACILITY_ID=:facilityId and p.PCP_ID=:pcpId and p.FACILITY_DISCHARGE_DATE is null and pnt.ID = p.PATIENT_ID order by pnt.FIRST_NAME ";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId",facilityId);
		query.setParameter("pcpId",pcpId);
		query.addScalar("id",LongType.INSTANCE);
		query.addScalar("fullName");
		query.addScalar("patientEpisodeId",LongType.INSTANCE);
		query.addScalar("hasResponse",BooleanType.INSTANCE);
		query.addScalar("stayedInHosp",BooleanType.INSTANCE);
		query.addScalar("stopAndWatchEligible",BooleanType.INSTANCE);
		query.addScalar("stpwCount",LongType.INSTANCE);
		query.addScalar("admissionQueue",BooleanType.INSTANCE);
		query.addScalar("stopAndWathQueue",BooleanType.INSTANCE);
		query.addScalar("observationQueue",BooleanType.INSTANCE);
		query.addScalar("acutecareQueue",BooleanType.INSTANCE);
		query.addScalar("admissionQueueCount",LongType.INSTANCE);
		query.addScalar("stopAndWathQueueCount",LongType.INSTANCE);
		query.addScalar("observationQueueCount",LongType.INSTANCE);
		query.addScalar("acutecareQueueCount",LongType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(PatientDetailsModel.class));
		return (List<PatientDetailsModel>)query.list();
	}

	public SSNCheckDto checKSSNforPatient(String ssn) {
		String sql = "select p.ID as patientId,p.SSN as SSN,pe.FACILITY_ID as facilityId,pe.FACILITY_DISCHARGE_DATE as fdischrgeDt from PATIENT p,PATIENT_EPISODE pe where p.SSN=:ssn and pe.PATIENT_ID=p.ID";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("ssn", ssn);
		query.addScalar("SSN");
		query.addScalar("facilityId", LongType.INSTANCE);
		query.addScalar("fdischrgeDt", DateType.INSTANCE);
		query.addScalar("patientId", LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(SSNCheckDto.class));
		Integer count = query.list().size();
		if (count > 0)
			count = count - 1;
		if (count == 0) {
			return (SSNCheckDto) query.uniqueResult();
		}
		return (SSNCheckDto) query.list().get(count);
	}

}