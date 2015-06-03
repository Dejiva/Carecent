package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.CarePathLabworks;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.dto.CarePathLabworkAppRejDto;
import com.codecoop.interact.core.dto.CarePathLabworksDto;
import com.codecoop.interact.core.dto.CarePathLabworksValuesFrmCICDto;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkDto;

/**
 * @author Ramesh
 *
 */
@Component
public class CarePathLabworksDao extends BaseDao<CarePathLabworks>{

	public Integer deleteByEpisodeIdNdcarePathCode(Long sbarId,String carePathCode){
		String sql = "delete  from  CARE_PATH_LABWORKS  where SBAR_ID=:sbarId and CARE_PATH_CODE=:carePathCode";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId",sbarId);
		query.setParameter("carePathCode",carePathCode);
		Integer result=query.executeUpdate();
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<CarePathLabworksDto> getCarePathLabworksEntriesByEpisodeId(Long psbarId){
		String sql = " select cpl.ID as id,cpl.SBAR_ID as sbarId, "
				+ " cpl.SIGNS_SYMPTOMS_LABWORK_ID as signsSymptomsLabworkId,cpl.APPROVED as approved,cpl.REJECTED as rejected, "
				+ " cpl.FACILITY_STAFF_ID as facilityStaffId,cpl.CARE_PATH_CODE as carePathCode, "
				+ " sl.SIGNS_SYMPTOMS_LABWORK_NAME as signSymtomLabworkName from  CARE_PATH_LABWORKS cpl,SIGNS_SYMPTOMS_LABWORK sl where cpl.SBAR_ID=:psbarId and sl.ID = cpl.SIGNS_SYMPTOMS_LABWORK_ID"
				+ " and cpl.END_DATE is  null GROUP BY cpl.SIGNS_SYMPTOMS_LABWORK_ID";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("psbarId",psbarId);
		query.addScalar("id",LongType.INSTANCE);
		query.addScalar("sbarId",LongType.INSTANCE);
		query.addScalar("signsSymptomsLabworkId",LongType.INSTANCE);
		query.addScalar("approved",BooleanType.INSTANCE);
		query.addScalar("rejected",BooleanType.INSTANCE);
		query.addScalar("facilityStaffId",LongType.INSTANCE);
		query.addScalar("carePathCode");
		query.addScalar("signSymtomLabworkName");
		query.setResultTransformer(Transformers.aliasToBean(CarePathLabworksDto.class));
		return (List<CarePathLabworksDto>)query.list();
	}

	@SuppressWarnings("unchecked")
	public List<CarePathLabworkAppRejDto> getCarePathLabworkAppRejUserById(Long cLWId){
		String sql = "select st.ID as staffId,st.USERNAME as userName,fstaff.ID as fStaffId,srole.DESCRIPTION as role, "
				+ " cpl.APPROVED as approvedFlag,cpl.REJECTED as rejectedFlag "
				+ " from STAFF st,FACILITY_STAFF fstaff,CARE_PATH_LABWORKS cpl,STAFF_ROLES srole "
				+ " where srole.ID =fstaff.ROLE_ID and st.ID = fstaff.STAFF_ID "
				+ " and  fstaff.ID = cpl.FACILITY_STAFF_ID "
				+ "  and cpl.ID=:cLWId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("cLWId",cLWId);
		query.addScalar("staffId",LongType.INSTANCE);
		query.addScalar("userName");
		query.addScalar("fStaffId",LongType.INSTANCE);
		query.addScalar("role");
		query.addScalar("approvedFlag",BooleanType.INSTANCE);
		query.addScalar("rejectedFlag",BooleanType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(CarePathLabworkAppRejDto.class));
		return (List<CarePathLabworkAppRejDto>)query.list();
	}

	@SuppressWarnings("unchecked")
	public List<CarePathLabworksValuesFrmCICDto> getLabworkValuesFromCIC(Long psbarId,Long signSymptomLabworkId){
		String sql="select cic.ID as cicId,cic.SBAR_ID as sbarId,cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID as sslattrId,  "
				+ " sslattr.ATTR_NAME as sslattrName, cic.CHANGE_IN_CONDITION_VALUE as cicValue from SIGNS_SYMPTOMS_LABWORK_ATTR sslattr, "
				+ " CHANGE_IN_CONDITION cic where cic.SBAR_ID=:psbarId and cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID "
				+ " in (select sslat.ID from SIGNS_SYMPTOMS_LABWORK sslw,SIGNS_SYMPTOMS_LABWORK_ATTR sslat where sslw.ID=sslat.PARENT_ID and sslw.ID=:signSymptomLabworkId) "
				+ " and sslattr.ID=cic.SIGNS_SYMPTOMS_LABWORK_ATTR_ID";

		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("psbarId",psbarId);
		query.setParameter("signSymptomLabworkId",signSymptomLabworkId);
		query.addScalar("cicId",LongType.INSTANCE);
		query.addScalar("sbarId",LongType.INSTANCE);
		query.addScalar("sslattrId",LongType.INSTANCE);
		query.addScalar("sslattrName");
		query.addScalar("cicValue");
		query.setResultTransformer(Transformers.aliasToBean(CarePathLabworksValuesFrmCICDto.class));
		return (List<CarePathLabworksValuesFrmCICDto>)query.list();
	}

	@SuppressWarnings("unchecked")
	public List<SignsSymptomsLabworkDto> showLabworksNotInCarePathLabworks(Long sbarId){
		
		String sql = " select distinct sslw.ID as id, sslw.SIGNS_SYMPTOMS_LABWORK_NAME as signsSymptomsLabworkName "
				+ "  from SIGNS_SYMPTOMS_LABWORK sslw "
				+ " where sslw.LABWORK_FLAG = true and sslw.ID not in (select cpl.SIGNS_SYMPTOMS_LABWORK_ID from CARE_PATH_LABWORKS cpl "
				+ " where  cpl.SBAR_ID =:sbarId and cpl.END_DATE is  null group by cpl.SIGNS_SYMPTOMS_LABWORK_ID)";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("sbarId",sbarId);
		query.addScalar("id",LongType.INSTANCE);
		query.addScalar("signsSymptomsLabworkName");
		query.setResultTransformer(Transformers.aliasToBean(SignsSymptomsLabworkDto.class));
		return (List<SignsSymptomsLabworkDto>)query.list();
	}

	@SuppressWarnings("unchecked")
	public List<CarePathLabworksDto> getExistedCarePathLabworks(Long psbarId,String carePathCode){
		try{
			String sql = "select cpl.ID as id,cpl.SBAR_ID as sbarId,cpl.SIGNS_SYMPTOMS_LABWORK_ID as signsSymptomsLabworkId, "
					+ " cpl.APPROVED as approved,cpl.REJECTED as rejected, "
					+ " cpl.FACILITY_STAFF_ID as facilityStaffId, "
					+ " cpl.CARE_PATH_CODE  as carePathCode from  CARE_PATH_LABWORKS cpl  where cpl.SBAR_ID=:psbarId and cpl.CARE_PATH_CODE=:carePathCode"
					+ " and cpl.END_DATE is null";
			SQLQuery query =  getCurrentSession().createSQLQuery(sql);
			query.setParameter("psbarId",psbarId);
			query.setParameter("carePathCode",carePathCode);
			query.addScalar("id",LongType.INSTANCE);
			query.addScalar("sbarId",LongType.INSTANCE);
			query.addScalar("signsSymptomsLabworkId",LongType.INSTANCE);
			query.addScalar("facilityStaffId",LongType.INSTANCE);
			query.addScalar("approved",BooleanType.INSTANCE);
			query.addScalar("rejected",BooleanType.INSTANCE);
			query.addScalar("carePathCode");
			query.setResultTransformer(Transformers.aliasToBean(CarePathLabworksDto.class));
			return (List<CarePathLabworksDto>)query.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CarePathLabworks> getExistedCarePathLabworksByEpisodeNdSSLId(Long sbarId,Long SSLId){
		Criteria crit = getCurrentSession().createCriteria(CarePathLabworks.class);
		crit.add(Restrictions.eq("signsSymptomsLabworkId", SSLId));
		crit.add(Restrictions.eq("sbarId", sbarId));
		return (List<CarePathLabworks>)crit.list();
	}

}
