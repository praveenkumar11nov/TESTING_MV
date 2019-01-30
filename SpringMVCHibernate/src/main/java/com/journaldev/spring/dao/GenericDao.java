package com.journaldev.spring.dao;

public interface GenericDao<T> {
	
	T save(T t);
	
}
