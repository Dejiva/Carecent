package com.codecoop.interact.core.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.Facility;
import com.codecoop.interact.core.dto.FacilityDto;

@Component
public class FacilityDaoImpl extends BaseDao<Facility> {
	
	public Facility  findFacilityByName(String facilityName){
		
		String sql = "select * from  FACILITY  where FACILITY_NAME=:facilityName";
		SQLQuery query =  getCurrentSession().createSQLQuery(sql);
		query.setParameter("facilityName",facilityName);
		query.addEntity(Facility.class);
		return (Facility)query.uniqueResult();
		//Criteria crit = getCurrentSession().createCriteria(Facility.class);
		//crit.add(Restrictions.eq("facilityName", facilityName));
		//return (Facility) crit.uniqueResult();
	}
	
	

	@SuppressWarnings("unchecked")
	public List<FacilityDto> findAllFacilities() {
		String sql = "select ID as id,FACILITY_NAME as facilityName,STANDING_ORDERS as standingOrders from  FACILITY  ";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.addScalar("id",new LongType());
		query.addScalar("facilityName");
		query.addScalar("standingOrders",new BooleanType());
		query.setResultTransformer(Transformers.aliasToBean(FacilityDto.class));
		return (List<FacilityDto>)query.list();
	}
	

}
