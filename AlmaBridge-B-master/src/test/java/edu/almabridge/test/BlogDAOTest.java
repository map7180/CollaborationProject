package edu.almabridge.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.almabridge.dao.BlogDAO;
import edu.almabridge.model.Blog;

public class BlogDAOTest {
	
	private static ClassPathXmlApplicationContext context; 
	private static BlogDAO blogDAO;
	private Blog blog;

	@BeforeClass
	public static void init()
	{
		context = new ClassPathXmlApplicationContext("ApplicationContextConfig.xml");
		context.refresh();
		blogDAO = (BlogDAO) context.getBean("BlogDAO");
	}

	@Test
	public void testsaveBlog()
	{
		blog = new Blog();
		blog.setBlogDate(new Date().toString());
		blog.setBlogId(0);
		blog.setDescription("This is Another Blog Added through Test Case on 24 april");
		assertEquals("Blog Added Successfully ",true, blogDAO.saveBlog(blog));
	}
	
//	@Test
	public void testUpdateBlog()
	{
		blog = new Blog();
		blog.setBlogDate(new Date().toString());
		blog.setBlogId(62);
		blog.setDescription("This Blog is Updated through Test Case");
		assertEquals("Blog Added Successfully ",true, blogDAO.updateBlog(blog));
	}
	
	//@Test
	public void testDeleteBlog()
	{
		//blog = new Blog();
		//blog.setBlogId(61);
		assertEquals("Blog Added Successfully ",true, blogDAO.deleteBlog(61));	
	}
	
	@Test
	public void testgetBlog(){
		
		blog = new Blog();
		assertEquals("Blog Added Successfully ",blog.getClass().getName(), blogDAO.getBlog(62).getClass().getName());
		
	}
	
	
}
