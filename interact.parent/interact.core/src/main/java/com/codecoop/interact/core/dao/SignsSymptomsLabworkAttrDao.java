package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.SignsSymptomsLabworkAttr;
import com.codecoop.interact.core.dto.CPAttrValueResponseDto;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkAttrDto;
import com.codecoop.interact.core.util.Constants;

@Component
public class SignsSymptomsLabworkAttrDao extends
		BaseDao<SignsSymptomsLabworkAttr> {

	@SuppressWarnings("unchecked")
	public List<SignsSymptomsLabworkAttr> findByParentId(Long parentId) {

		Criteria criteria = getCurrentSession().createCriteria(SignsSymptomsLabworkAttr.class);
		criteria.add(Restrictions.eq("parentId", parentId));
		List<SignsSymptomsLabworkAttr> signsAttrList = criteria.list();
		return signsAttrList;

	}
	public SignsSymptomsLabworkAttr findByCarePathAttrCode(String carePathAttrCode) {

		Criteria criteria = getCurrentSession().createCriteria(SignsSymptomsLabworkAttr.class);
		criteria.add(Restrictions.eq("carePathAttrCode", carePathAttrCode));
		return (SignsSymptomsLabworkAttr) criteria.uniqueResult();

	}
	@SuppressWarnings("unchecked")
	public List<SignsSymptomsLabworkAttr> getCarePathCodeRecords() {

		Criteria criteria = getCurrentSession().createCriteria(SignsSymptomsLabworkAttr.class);
	//	criteria.add(Restrictions.eq("parentId", parentId));
		criteria.add(Restrictions.isNotNull("carePathAttrCode"));
		
		List<SignsSymptomsLabworkAttr> signsAttrList = criteria.list();
		return signsAttrList;

	}
	@SuppressWarnings("unchecked")
	public List<SignsSymptomsLabworkAttrDto> getChangeInConditionAttrOnPatient(	String sign_symptom_Labwork_Flag,Long sbarId) {
		String queryCloumn="";
		switch (sign_symptom_Labwork_Flag) {
		case Constants.SIGN_FLAG:
			queryCloumn="SIGN_FLAG";
			break;
		case Constants.SYMPTOM_FLAG:
			queryCloumn="SYMPTOM_FLAG";
			break;
		case Constants.LABWORK_FLAG:
			queryCloumn="LABWORK_FLAG";
			break;
		}

		String sql="SELECT ssla.ID id, ssla.PARENT_ID parentId,ssla.ATTR_NAME attrName,ssla.DATATYPE datatype,ssla.UNITS  units ,ssla.CARE_PATH_ATTR_CODE carePathAttrCode ,cc.CHANGE_IN_CONDITION_VALUE attrValue FROM SIGNS_SYMPTOMS_LABWORK_ATTR ssla inner join SIGNS_SYMPTOMS_LABWORK ss on ss.ID=ssla.PARENT_ID and ss."+queryCloumn+"=1 left join CHANGE_IN_CONDITION cc  on cc.SIGNS_SYMPTOMS_LABWORK_ATTR_ID=ssla.ID and cc.SBAR_ID=:sbarId";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		
		query.addScalar("id",new LongType());
		query.addScalar("parentId", new LongType());
		query.addScalar("attrName");
		query.addScalar("attrValue");
		query.addScalar("datatype");
		query.addScalar("units");
		query.setParameter("sbarId", sbarId);
		query.addScalar("carePathAttrCode");
		query.setResultTransformer(Transformers.aliasToBean(SignsSymptomsLabworkAttrDto.class));
		return query.list();
	}

}
