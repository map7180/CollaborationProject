package edu.almabridge.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.almabridge.dao.BlogDAO;
import edu.almabridge.model.Blog;

@RestController
public class BlogController {
	@Autowired
	private BlogDAO blogDAO;
	@Autowired
	private Blog blog;
	
	private static Logger log = LoggerFactory.getLogger(BlogController.class);
	
	//Tested
	@RequestMapping(value = "/postBlog", method = RequestMethod.POST)
	public boolean addNewPost(@RequestBody Blog blog) {
		log.debug("Starting of add Blog");
		 blog.setStatus('Y');

		if (blogDAO.saveBlog(blog)) {
			
			blog.setErrorCode("200");
			blog.setErrorMsg("Seccessfully posted!");
			log.debug("log posted");
			return true;
		} else {
			
			blog.setErrorCode("404");
			blog.setErrorMsg("Not registered!");
			log.debug("Blog Posting Failed");
			return false;

		}

	}
	//Tested
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("/blog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable("id") int id){
		log.debug("starting of getBlog");
		Blog blog1 =  blogDAO.getBlog(id);
		if(blog1 == null){
		    blog1 = new Blog();
		    blog1.setBlogId(0);
			blog1.setErrorCode("404");
			blog1.setErrorMsg("No Events posted!");
		}
		log.debug("getBlog Completed");
		return new ResponseEntity<Blog>(blog1, HttpStatus.OK);
	}
	//Tested
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getBlogList() {
		log.debug("starting of getBlogList ");
		List<Blog> blogList = blogDAO.blogList();
		if (blogList.isEmpty()) {
			Blog blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMsg("Blogs are not available");
			blogList.add(blog);
		}
		log.debug("GetBlogList Completed");
		return new ResponseEntity<List<Blog>>(blogList, HttpStatus.OK);
	}
	//Tested
	@RequestMapping("/deleteBlog/{id}")
	public boolean deleteEvent(@PathVariable("id") int id){
		log.debug("Starting of Delete Blog");
		if(blogDAO.getBlog(id) == null){
			Blog blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMsg("No Events posted!");
		}
		blogDAO.deleteBlog(id);
		log.debug("Blog Deleted");
		return true ;
	}
	//Tested
	@RequestMapping(value = "/updateBlog/{id}", method = RequestMethod.POST)
	public ResponseEntity<Blog> updateEvent(@PathVariable("id") int id, @RequestBody Blog blog){
		log.debug("Starting of Update Blog");
		if(blogDAO.getBlog(id) == null){
			
			blog.setErrorCode("404");
			blog.setErrorMsg("No Events posted!");
		}
			blogDAO.updateBlog(blog);
			log.debug("Update Blog Completed");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

}
