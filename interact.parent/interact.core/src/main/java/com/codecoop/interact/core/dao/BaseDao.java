package com.codecoop.interact.core.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao<T> {
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> type;

	@SuppressWarnings("unchecked")
	public BaseDao(){
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>)pt.getActualTypeArguments()[0];		
	}
	
	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	public Class<T> getType() {
		return type;
	}
	public void setType(Class<T> type) {
		this.type = type;
	}

	public T findById(Long id){
		return (T)getCurrentSession().get(type, id);
	}
	
	public List<T> findAll(){
		Criteria criteria = getCurrentSession().createCriteria(type);
		return (List<T>)criteria.list();
	}
	
	public void save(T entity) {
		getCurrentSession().save(entity);
	}
	public void persist(T entity) {
		getCurrentSession().persist(entity);
	}
	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}


}
