package com.journaldev.spring.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.journaldev.spring.dao.GenericDao;

public abstract class GenericDaoImpl<T> implements GenericDao<T>{

	private static final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public T save(T t) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.persist(t);
			return t;			
		} catch (Exception e) {
			logger.info("Exception came while saving the Entoty : " + e.getMessage());
			return null;
		}
	}



}
