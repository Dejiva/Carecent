package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.PrescribeMedicine;
import com.codecoop.interact.core.dto.GenericDto;
import com.codecoop.interact.core.dto.PrescribeMedicineDto;

@Component
public class PrescribeMedicineDaoImpl extends BaseDao<PrescribeMedicine>{
public List<PrescribeMedicineDto> getMedicine(Long patientEpisodeId){
	Session session = getCurrentSession();
	String sql="SELECT ID id,DOCTER_FACILITY_STAFF_ID staffId, PATIENT_EPISODE_ID patientEpisodeId,MEDICINE_NAME medicineName,DELETE_FLAG deleteFlag FROM PRESCRIBE_MEDICINE WHERE  PATIENT_EPISODE_ID=:patientEpisodeId and DELETE_FLAG=0";
	SQLQuery query = session.createSQLQuery(sql);
	query.setParameter("patientEpisodeId", patientEpisodeId);
	query.addScalar("id",new LongType());
	query.addScalar("staffId",new LongType());
	query.addScalar("patientEpisodeId",new LongType());
	query.addScalar("medicineName");
	query.addScalar("deleteFlag");
	query.setResultTransformer(Transformers.aliasToBean(PrescribeMedicineDto.class));
	return (List<PrescribeMedicineDto>)query.list();
	}

public PrescribeMedicineDto getPrescribeMedicineByPatientEpisodeId(Long patientEposodeId,String medicineName) {
	Session session = getCurrentSession();
	String sql="select ID id from PRESCRIBE_MEDICINE where PATIENT_EPISODE_ID=:patientEposodeId and MEDICINE_NAME=:medicineName";
	SQLQuery query = session.createSQLQuery(sql);
	query.setParameter("patientEposodeId", patientEposodeId);
	query.setParameter("medicineName", medicineName);
	query.addScalar("id",new LongType());
	query.setResultTransformer(Transformers.aliasToBean(PrescribeMedicineDto.class));
	return (PrescribeMedicineDto)query.uniqueResult();
}

public long removeMedicine(Long prescribeMedicineId) {
	Session session = getCurrentSession();
	String sql="update PRESCRIBE_MEDICINE set DELETE_FLAG=1 WHERE ID=:prescribeMedicineId";
	SQLQuery query = session.createSQLQuery(sql);
	query.setParameter("prescribeMedicineId", prescribeMedicineId);
	return query.executeUpdate();
}
}
