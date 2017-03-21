package edu.almabridge.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.almabridge.dao.BlogDAO;

import edu.almabridge.model.Blog;

@Repository("BlogDAO")
public class BlogDAOImpl implements BlogDAO{
	
	

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Blog blog ;
	
	@Autowired
	BlogDAO blogDAO ;
	
	public BlogDAOImpl() { }
	 

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public boolean saveBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false ;
		}
	}
	@Transactional
	public boolean deleteBlog(int blogId) {
		Blog blog = new Blog();
		blog.setBlogId(blogId);
		sessionFactory.getCurrentSession().delete(blog);
		return true;
	}
	@Transactional
	public void updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			
		}
		
	}
	@Transactional
	public Blog getBlog(int blogId) {
		return (Blog) sessionFactory.getCurrentSession().get(Blog.class, blogId);
	}

	public List<Blog> blogList() {
		
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		String hql = "from Blog";
		Query query = session.createQuery(hql) ;
		
		return query.list();
		
	}

}
