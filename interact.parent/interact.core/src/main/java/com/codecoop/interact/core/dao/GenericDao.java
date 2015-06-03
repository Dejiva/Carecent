package com.codecoop.interact.core.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;



@Repository
@SuppressWarnings("unchecked")
public class GenericDao extends BaseDao<Object>{

	    private SessionFactory sessionFactory;
	    Class<?> domainClass;

	    @Resource
	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public Session getCurrentSession() {
	        return sessionFactory.getCurrentSession();
	    }

	    public void setDomainClass(Class<?> domainClass) {
	        this.domainClass = domainClass;
	    }

	    public <T> List<T> findAll(Class<?> domainClass) {
	    	Criteria criteria = getCurrentSession().createCriteria(domainClass);
			return (List<T>)criteria.list();
	    }
	    
	    public <T> List<T> findByExample(T object) {
	        Criteria criteria = getCurrentSession().createCriteria(object.getClass());
	        criteria.add(Example.create(object));
	        return criteria.list();
	    }
	  
}
