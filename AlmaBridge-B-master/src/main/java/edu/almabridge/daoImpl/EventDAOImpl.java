package edu.almabridge.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.almabridge.dao.EventDAO;
import edu.almabridge.model.Event;
import edu.almabridge.model.JobDetails;

@Repository("EventDAO")
public class EventDAOImpl implements EventDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

	public EventDAOImpl() {
		
	}


	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveEvent(Event event) {
		try {
			sessionFactory.getCurrentSession().save(event);
			return true ;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false ;
		}
		
	}
	@Transactional
	public boolean updateEvent(Event event) {
		sessionFactory.getCurrentSession().update(event);
		return true ;
	}
	@Transactional
	public Event getEvent(int eventId) {
		return (Event)sessionFactory.getCurrentSession().get(Event.class, eventId);
		
	}
	@Transactional
	public List<Event> eventList() {
		String hql = "from Event";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
		
	}
	
	@Transactional
	public boolean deleteEvent(int eventId) {
		try {
			Event event = new Event();
			event.setEventId(eventId);
			Session session = sessionFactory.getCurrentSession();
			session.delete(getEvent(eventId));
			return true ;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false ;
		}
		
	}

}
