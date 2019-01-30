package com.journaldev.spring.daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Person saved successfully, Person Details="+p);
	}

	@Override
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Person updated successfully, Person Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		System.out.println("Reading the person table");
		
		Session session = this.sessionFactory.getCurrentSession(); 
		List<Person> personsList = new ArrayList<Person>();//session.createQuery("from Person").list();
		List<?> personsList1 = session.createSQLQuery("select person0_.id as id1_0_, person0_.country as country2_0_, person0_.name as name3_0_ from PERSON person0_").list();
		//System.out.println("personsList1 === " + personsList1.get(0).getId());
		Person person=null;
		
		for (Iterator iterator = personsList1.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			System.out.println("id="+object[0]);
			System.out.println("name="+object[1]);
			System.out.println("country="+object[2]);
			person=new Person();
			person.setId(Integer.parseInt(object[0].toString()));
			person.setName(object[1].toString());
			person.setCountry(object[2].toString());
			personsList.add(person);
			
		}
		
		//for(Person p : personsList1){ 
		//	logger.info("Person List::"+p); 
		//}
		
		return personsList;
	}

	@Override
	public Person getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Person p = (Person) session.load(Person.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

	@Override
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Person deleted successfully, person details="+p);
	}

}
