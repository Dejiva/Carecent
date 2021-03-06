package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.Patient;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.dto.PdfResponseDto;
import com.codecoop.interact.core.model.PatientDetailsModel;

@Component
public class PatientEpisodeDao extends BaseDao<PatientEpisode>{

	public void savePatientEpisode(PatientEpisode pEpisode){
		saveOrUpdate(pEpisode);
	}
	@SuppressWarnings("unchecked")
	public List<PatientEpisode> findAllByFacilityId(Long facilityId)
	{
		String sql = "select p.* from PATIENT_EPISODE p,PATIENT pnt  where p.FACILITY_ID=:faicilityId and p.FACILITY_DISCHARGE_DATE is null and pnt.ID = p.PATIENT_ID order by  pnt.FIRST_NAME";
		SQLQuery qry = getCurrentSession().createSQLQuery(sql);
		qry.setParameter("faicilityId", facilityId);
		qry.addEntity(PatientEpisode.class);
		return (List<PatientEpisode>)qry.list();
	}

	@SuppressWarnings("unchecked")
	public List<Patient> findAllPatientsByFacilityId(Long facilityId)
	{
		String sql = "select pnt.* from PATIENT_EPISODE p,PATIENT pnt  where p.FACILITY_ID=:faicilityId and p.FACILITY_DISCHARGE_DATE is null and pnt.ID = p.PATIENT_ID order by  pnt.FIRST_NAME";
		SQLQuery qry = getCurrentSession().createSQLQuery(sql);
		qry.setParameter("faicilityId", facilityId);
		qry.addEntity(Patient.class);
		return (List<Patient>)qry.list();
	}

	@SuppressWarnings("unchecked")
	public List<PatientDetailsModel> findAllPatientsByFacilityId1(Long facilityId)
	{
		String sql ="select pnt.ID as id,TRIM(CONCAT(IFNULL(pnt.FIRST_NAME,''),' ',IFNULL(pnt.LAST_NAME,''))) as fullName,p.ID as patientEpisodeId,p.S_AND_W_ELIGIBLE as stopAndWatchEligible, "
				+ "(select count(*) from PATIENT_EPISODE where ID=patientEpisodeId ) admissionQueue,"
				+" (select count(*) from STOP_AND_WATCH sw1 where sw1.PATIENT_EPISODE_ID=patientEpisodeId and sw1.END_DATE is NULL) stopAndWathQueue,"
				+" (select count(*) from SBAR sb1 where sb1.PATIENT_EPISODE_ID=patientEpisodeId and sb1.TRANSFER_TO_HOSPITAL_FLAG=0 and PATIENT_RECOVERED_FLAG=0) observationQueue, "
				+" (select count(*) from HOSPITAL_TRANSFER where STAY_IN_ACUTECARE_FLAG=0 and PATIENT_EPISODE_ID=patientEpisodeId) acutecareQueue, "
				+" (select count(*) from PATIENT_EPISODE where ID=patientEpisodeId ) activePatient, "
				+" (select count(*) from PATIENT_EPISODE pe where pe.FACILITY_DISCHARGE_DATE is null and pe.FACILITY_ID=:facilityId) admissionQueueCount, "
				+" (select count(*) from STOP_AND_WATCH sw1,PATIENT_EPISODE pe1 where sw1.END_DATE is NULL and sw1.PATIENT_EPISODE_ID=pe1.ID and pe1.FACILITY_ID=:facilityId and pe1.FACILITY_DISCHARGE_DATE is null) stopAndWathQueueCount, "
				+" (select count(*) from SBAR sb1,PATIENT_EPISODE pe1 where sb1.TRANSFER_TO_HOSPITAL_FLAG=0 and PATIENT_RECOVERED_FLAG=0 and sb1.PATIENT_EPISODE_ID=pe1.ID and pe1.FACILITY_ID=:facilityId and pe1.FACILITY_DISCHARGE_DATE is null) observationQueueCount, "
				+" (select count(*) from HOSPITAL_TRANSFER ht1,PATIENT_EPISODE pe1 where ht1.STAY_IN_ACUTECARE_FLAG=0  and ht1.PATIENT_EPISODE_ID=pe1.ID and pe1.FACILITY_ID=:facilityId and pe1.FACILITY_DISCHARGE_DATE is null) acutecareQueueCount, "
				+ " exists (select * from STOP_AND_WATCH_HISTORY stpwHist,STOP_AND_WATCH stpw where stpwHist.STOP_AND_WATCH_ID=stpw.ID and  stpw.PATIENT_EPISODE_ID=p.ID and (stpwHist.FACILITY_STAF_RESPONSE !='' and stpwHist.FACILITY_STAF_RESPONSE is not null and stpwHist.INVALIDATE_FLAG=0 and stpwHist.DELETE_FLAG=0) and stpw.END_DATE is null) as hasResponse, "
				+ " (select count(*) from STOP_AND_WATCH_HISTORY stpwHist,STOP_AND_WATCH stpw where  stpwHist.STOP_AND_WATCH_ID=stpw.ID and stpwHist.INVALIDATE_FLAG=0 and stpwHist.DELETE_FLAG=0 and  stpw.PATIENT_EPISODE_ID=p.ID and stpw.END_DATE is null) as stpwCount, "
				+ "exists (select SEND_TO_HOSPITAL_FLAG from HOSPITAL_TRANSFER where SEND_TO_HOSPITAL_FLAG=1 and STAY_IN_ACUTECARE_FLAG=0 and PATIENT_EPISODE_ID=patientEpisodeId) as stayedInHosp "
				+" from PATIENT_EPISODE p,PATIENT pnt "
				+" where p.FACILITY_ID=:facilityId and p.FACILITY_DISCHARGE_DATE is null and pnt.ID = p.PATIENT_ID order by pnt.FIRST_NAME ";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityId",facilityId);
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

	public PatientEpisode findActiveEpisodeByPatient(Patient patient,Long facilityId)
	{
		Criteria crit = getCurrentSession().createCriteria(PatientEpisode.class);
		crit.add(Restrictions.eq("patient", patient));
		crit.add(Restrictions.eq("facilityId", facilityId));
		crit.add(Restrictions.isNull("facilityDischargeDate"));
		return (PatientEpisode) crit.uniqueResult();
	}

	public PatientEpisode findEpisodeByPatient(Patient patient){
		Criteria crit = getCurrentSession().createCriteria(PatientEpisode.class);
		crit.add(Restrictions.eq("patient", patient));
		crit.add(Restrictions.isNotNull("facilityDischargeDate"));
		Integer count = crit.list().size();
		if (count > 0)
			count = count - 1;
		if (count == 0) {
			return (PatientEpisode) crit.uniqueResult();
		}
		return (PatientEpisode) crit.list().get(count);
	}

	public PatientEpisode findActiveEpisodeByPatientId(Long id)
	{
		Criteria crit = getCurrentSession().createCriteria(PatientEpisode.class);
		crit.add(Restrictions.eq("patient.id", id));
		crit.add(Restrictions.isNull("facilityDischargeDate"));
		return (PatientEpisode) crit.uniqueResult();
	}


	public PdfResponseDto findPdfPatientDetailsByPatientIdAndFacilityId(Long patientId)
	{
		String sql = "select TRIM(CONCAT(IFNULL(P.FIRST_NAME,''),' ',IFNULL(P.LAST_NAME,''))) patientName,P.DOB birthDate,PE.FACILITY_ADMISSION_DATE joiningDate from PATIENT P,PATIENT_EPISODE PE where P.ID=:patientId and P.ID=PE.PATIENT_ID and PE.FACILITY_DISCHARGE_DATE is null";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("patientId",patientId);
		query.addScalar("patientName");
		query.addScalar("birthDate",new DateType());
		query.addScalar("joiningDate",new DateType());
		query.setResultTransformer(Transformers.aliasToBean(PdfResponseDto.class));
		return (PdfResponseDto)query.uniqueResult();

	}

	public boolean isApprovedOrRejected(Long signSymptomLabworkId,Long sbarId){
		String sql = " select exists(select * from CARE_PATH_LABWORKS where SIGNS_SYMPTOMS_LABWORK_ID=:signSymptomLabworkId and SBAR_ID=:sbarId and "
				+ " (APPROVED=1 or REJECTED=1) and END_DATE is null) as appOrRej ";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter("signSymptomLabworkId",signSymptomLabworkId);
		query.setParameter("sbarId",sbarId);
		query.addScalar("appOrRej",BooleanType.INSTANCE);
		return (Boolean)query.uniqueResult();
	}

}
