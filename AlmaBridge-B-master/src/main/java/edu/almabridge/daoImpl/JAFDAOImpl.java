package edu.almabridge.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.almabridge.dao.JAFDAO;
import edu.almabridge.model.JAF;
import edu.almabridge.model.JobDetails;

@Repository("JAFDAO")
public class JAFDAOImpl implements JAFDAO{
	@Autowired
	SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

	public JAFDAOImpl() {
		
	}


	public JAFDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}

	
	@Transactional
	public boolean saveJAF(JAF jaf) {
		try {
			sessionFactory.getCurrentSession().save(jaf);
			return true ;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false ;
		}
		
	}
	
	@Transactional
	public boolean removeJAF(int jafId) {
		try {
			JAF jAF = new JAF();
			jAF.setjAFId(jafId);
			jAF.setStatus('N');
			sessionFactory.getCurrentSession().update(jAF);
			return true ;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false ;
		}
		
	}

	@Transactional
	public JAF getJAF(int jafId) {
		return (JAF)sessionFactory.getCurrentSession().get(JAF.class, jafId);
	}
	@Transactional
	public List<JAF> JAFList(String userId) {
		System.out.println("logged in user "+ userId);
		
		
		Session session=getSessionFactory().getCurrentSession();
		Query query =session.createQuery("from JAF where userId=?");
		query.setString(0, userId);
		
		return query.list();
		
	}
	@Transactional
	public List<JAF> signedJAFList(String userId) {
		
		Session session=getSessionFactory().getCurrentSession();
		Query query =session.createQuery("from JAF where status='Y' and userId =?");
		query.setString(0, userId);
		
		return query.list();
		
		
	}
	@Transactional
	public List<JAF> notSignedJAFList(String userId) {
		Session session=getSessionFactory().getCurrentSession();
		Query query =session.createQuery("from JAF where status='N' and userId =?");
		query.setString(0, userId);
		
		return query.list();
		
	}


	public boolean ifAlreadySigned(String userId, int jobId) {
		
		Session session=getSessionFactory().openSession();
		Query query =session.createQuery("from JAF where status='Y' and userId =? and jobId = "+jobId);
		query.setString(0, userId);
		
		return !query.list().isEmpty();
		
		
	}

}
