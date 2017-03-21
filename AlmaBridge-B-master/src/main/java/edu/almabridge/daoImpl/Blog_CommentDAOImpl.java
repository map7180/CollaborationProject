package edu.almabridge.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.almabridge.dao.BlogDAO;
import edu.almabridge.dao.Blog_CommentDAO;
import edu.almabridge.model.Blog;
import edu.almabridge.model.Blog_Comment;
@Repository("Blog_CommentDAO")
public class Blog_CommentDAOImpl implements Blog_CommentDAO  {
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Blog blog ;
	
	@Autowired
	BlogDAO blogDAO ;
	
	@Autowired
	Blog_Comment blog_Comment ;
	
	@Autowired
	Blog_CommentDAO blog_CommentDAO ;
	
	
	public Blog_CommentDAOImpl() { }
	 

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public Blog_CommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveComment(Blog_Comment comment) {
		try {
			sessionFactory.getCurrentSession().save(comment);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false ;
		}
	}

	@Transactional
	public Blog_Comment updateComment(Blog_Comment comment) {
		try {
			sessionFactory.getCurrentSession().update(comment);
			return comment ;
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return blog_Comment;
		}
	}

	@Transactional
	public void deleteComment(int commentId) {
		Blog_Comment blog_Comment = new Blog_Comment();
		blog_Comment.setCommentId(commentId);
		sessionFactory.getCurrentSession().delete(blog_Comment);
		
		
	}

	@Transactional
	public Blog_Comment getComment(String userId) {
		return (Blog_Comment) sessionFactory.getCurrentSession().get(Blog_Comment.class, userId);
		
	}

	@Transactional
	public List<Blog_Comment> getComments(int blogId) {
		String hql = "from Blog_Comment where blogId = " + blogId ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
		
	}


	@Transactional
	public Blog_Comment getComm(int commentId) {
		return (Blog_Comment) sessionFactory.getCurrentSession().get(Blog_Comment.class, commentId);
	}


	@Transactional
	public List<Blog_Comment> getAllComments() {
		String hql = "from Blog_Comment "  ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

}
