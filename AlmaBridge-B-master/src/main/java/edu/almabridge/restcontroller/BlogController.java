package edu.almabridge.restcontroller;

import java.util.List;

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
import edu.almabridge.model.Event;
import edu.almabridge.model.UserDetails;

@RestController
public class BlogController {
	@Autowired
	private BlogDAO blogDAO;
	@Autowired
	private Blog blog;
	
	//Tested
	@RequestMapping(value = "/postBlog", method = RequestMethod.POST)
	public boolean addNewPost(@RequestBody Blog blog) {
		 blog.setStatus('Y');

		if (blogDAO.saveBlog(blog)) {
			
			blog.setErrorCode("200");
			blog.setErrorMsg("Seccessfully posted!");
			return true;
		} else {
			
			blog.setErrorCode("404");
			blog.setErrorMsg("Not registered!");
			return false;

		}

	}
	//Tested
	@CrossOrigin(origins = "http://localhost:8500")
	@RequestMapping("/blog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable("id") int id){
		Blog blog1 =  blogDAO.getBlog(id);
		if(blog1 == null){
		    blog1 = new Blog();
		    blog1.setBlogId(0);
			blog1.setErrorCode("404");
			blog1.setErrorMsg("No Events posted!");
		}
		return new ResponseEntity<Blog>(blog1, HttpStatus.OK);
	}
	//Tested
	@CrossOrigin(origins = "http://localhost:8500")
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getBlogList() {
		List<Blog> blogList = blogDAO.blogList();
		if (blogList.isEmpty()) {
			Blog blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMsg("Blogs are not available");
			blogList.add(blog);
		}
		return new ResponseEntity<List<Blog>>(blogList, HttpStatus.OK);
	}
	//Tested
	@RequestMapping("/deleteBlog/{id}")
	public boolean deleteEvent(@PathVariable("id") int id){
		if(blogDAO.getBlog(id) == null){
			Blog blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMsg("No Events posted!");
		}
		blogDAO.deleteBlog(id);
		return true ;
	}
	//Tested
	@RequestMapping(value = "/updateBlog/{id}", method = RequestMethod.POST)
	public ResponseEntity<Blog> updateEvent(@PathVariable("id") int id, @RequestBody Blog blog){
		if(blogDAO.getBlog(id) == null){
			
			blog.setErrorCode("404");
			blog.setErrorMsg("No Events posted!");
		}
			blogDAO.updateBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

}
