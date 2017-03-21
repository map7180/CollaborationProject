package edu.almabridge.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.almabridge.dao.JobDetailsDAO;
import edu.almabridge.model.JobDetails;

@Repository("jobDetailsDAO")
public class JobDetailsDAOImpl implements JobDetailsDAO{


	@Autowired
	SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public JobDetailsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	


	public JobDetailsDAOImpl() {
		
	}

	@Transactional
	public boolean saveJobDetails(JobDetails job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true ;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false ;
		}
		
		
	}
	@Transactional
	public boolean removeJobDetails(int jobId) {
		try {
			sessionFactory.getCurrentSession().delete(getJobDetails(jobId));
			return true ;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false ;
		}
		
	}
	@Transactional
	public JobDetails getJobDetails(int jobId) {
		return (JobDetails)sessionFactory.getCurrentSession().get(JobDetails.class, jobId);
		
	}
	@Transactional
	public boolean updateJobDetails(JobDetails job) {
		sessionFactory.getCurrentSession().update(job);
		return true ;
		
	}
	@Transactional
	public List<JobDetails> jobList() {
		String hql = "from JobDetails";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
		
	}

}
