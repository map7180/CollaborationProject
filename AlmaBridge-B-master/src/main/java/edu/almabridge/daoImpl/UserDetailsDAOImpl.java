package edu.almabridge.daoImpl;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.almabridge.dao.UserDetailsDAO;
import edu.almabridge.model.UserDetails;

@Repository("userDetailsDAO")
public class UserDetailsDAOImpl implements UserDetailsDAO {
	
	

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	UserDetails userDetails ;
	
	@Autowired
	UserDetailsDAO userDetailsDAO ;
	
	public UserDetailsDAOImpl() { }
	 

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public UserDetailsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveUser(UserDetails userDetails) {
		try {
			sessionFactory.getCurrentSession().save(userDetails);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Transactional
	public boolean updateUser(UserDetails userDetails) {
		try {
			sessionFactory.getCurrentSession().update(userDetails);
			return true ;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false ;
		}
		
	}

	@Transactional
	public boolean removeUser(String userId) {
		UserDetails userDetail = new UserDetails();
		userDetail.setUserId(userId);
		sessionFactory.getCurrentSession().delete(userDetail);
		return true;
	}

	@Transactional
	public UserDetails getUser(String userId) {
		/*Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserDetails where userId=?");
		query.setString(0, userId);
		UserDetails userDetails = (UserDetails) query.uniqueResult();*/
		return (UserDetails) sessionFactory.getCurrentSession().get(UserDetails.class, userId);
	}

	@Transactional
	public List<UserDetails> getUserList() {
		/*List<UserDetails> listUserDetails = sessionFactory.getCurrentSession().createCriteria(UserDetails.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUserDetails;*/
		String hql = "from UserDetails";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Transactional
	public void setOnline(String userId) {
		UserDetails user = getUser(userId);
		user.setIsOnline('Y');
		sessionFactory.getCurrentSession().update(user);
	}

	@Transactional
	public void setOffline(String userId) {
		UserDetails user = getUser(userId);
		user.setIsOnline('N');
		sessionFactory.getCurrentSession().update(user);

	}

	@Transactional
	public UserDetails authenticate(String userId, String password) {
		String hql = "from UserDetails where userId='" + userId + "' and password='" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		/*List listUser = query.list();
		if (listUser != null && !listUser.isEmpty()) {
			return getUser(userId);
		} else {
			return null;
		}*/
		
		return (UserDetails)query.uniqueResult();
	}

	@Transactional
	public boolean isUser(String userId) {
		List<UserDetails> userList = userDetailsDAO.getUserList();
		int count = 0 ;
		for(int i=0;i<userList.size()-1;i++){
			
		boolean b=userList.get(i).getUserId().equals(userId);
		if(b==true)
			count++;
		}
		if(count>0){
			return true;
		}
		else 
		{
			return false;
		}
	}
	@Transactional
	public void reject(String userId) {
		UserDetails user = getUser(userId);
		user.setStatus('N');
		user.setReason("Insert correct info");
		sessionFactory.getCurrentSession().update(user);

	}
	@Transactional
	public void approve(String userId) {
		UserDetails user = getUser(userId);
		user.setStatus('Y');
		user.setReason("Insert correct info");
		sessionFactory.getCurrentSession().update(user);

	}

}
