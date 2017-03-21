package edu.almabridge.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.almabridge.dao.FriendDAO;
import edu.almabridge.dao.UserDetailsDAO;
import edu.almabridge.model.Friend;
import edu.almabridge.model.UserDetails;
@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Friend friend ;
	
	@Autowired
	FriendDAO friendDAO ;
	
	public FriendDAOImpl() { }
	 

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public List<Friend> myFriendsList(String userId) {
		Session session;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		
		String hql = "from Friend where ( userId ="+"'"+userId + "'" + " or friendId ="+"'"+userId + "'" + " )and status = '"+"Y'";
		System.out.println("friend retrieving query ="+ hql);
		Query query = session.createQuery(hql);
		
		return query.list();
		
	}
	@Transactional
	public List<Friend> pendingFriendRequests(String userId) {
		
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		String hql = "from Friend where ( userId ="+"'"+userId + "'" + " or friendId ="+"'"+userId + "'" + " )and status = '"+"N'";
		Query query = session.createQuery(hql);
		
		return query.list();
		
	}
	//Tested
	@Transactional
	public Friend getFriend(String userId, String friendId) {
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		String hql = "from Friend where userId ="+"'"+userId + "' and friendId = '"+friendId +"' and status = '"+"Y'";
		Query query = session.createQuery(hql);
		
		return  (Friend) query.uniqueResult();
		
	}
	//Tested
	@Transactional
	public Friend getFriendToChangeStatus(String userId, String friendId) {
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		String hql = "from Friend where userId ="+"'"+userId + "' and friendId = '"+friendId +"' and status = '"+"N'";
		Query query = session.createQuery(hql);
		
		return  (Friend) query.uniqueResult();
		
	}
	//Tested
	@Transactional
	public boolean saveFriend(Friend friend) {
		try {
			//friend.setId(0);
			sessionFactory.getCurrentSession().save(friend);
			return true ;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false ;
		}
		
	}
	//Tested
	@Transactional
	public boolean updateFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true ;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false ;
		}
		
	}
	//Tested
	@Transactional
	public void rejectFriend(String userId, String friendId) {
		Session session;
		System.out.println("Inside detele frinedddd");
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		
		session.delete(getFriendToChangeStatus(userId,friendId));
		
		
	}
	//Tested
	@Transactional
	public void unFriend(String userId, String friendId) {
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		
		session.delete(getFriend(friendId,userId));
		
		
	}
	@Transactional
	public void setOnline(String userId) {
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		String hql = "update Friend set isOnline = 'Y' where friendId ="+"'"+userId + "'" ;
		Query query = session.createQuery(hql);
	    query.executeUpdate();
		
		
		
	}
	@Transactional
	public void setOffline(String userId) {
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		String hql = "update Friend set isOnline = 'N' where friendId ="+"'"+userId + "'" ;
		Query query = session.createQuery(hql);
	    query.executeUpdate();
		
	}

   //Tested
	public boolean isRequestAlreadySent(String userId, String friendId) {
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		String hql = "from Friend where userId ="+"'"+userId + "' and friendId = '"+friendId +"' and status = '"+"N'";
		Query query = session.createQuery(hql);
		
		return   query.list().isEmpty();
	}

//Tested
	public boolean isAlreadyAccepted(String userId, String friendId) {
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		String hql = "from Friend where userId ="+"'"+userId + "' and friendId = '"+friendId +"' and status = '"+"Y'";
		Query query = session.createQuery(hql);
		
		return   query.list().isEmpty();
	}

}
