package edu.almabridge.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.almabridge.dao.BlogDAO;
import edu.almabridge.dao.Blog_CommentDAO;
import edu.almabridge.dao.EventDAO;
import edu.almabridge.model.Blog;
import edu.almabridge.model.Blog_Comment;
import edu.almabridge.model.Event;

@RestController
public class Blog_CommentController {
	
	@Autowired
	private Blog blog;
	
	@Autowired
	private Blog_Comment blog_Comment;
	
	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private Blog_CommentDAO blog_CommentDAO;

	//Tested
	@RequestMapping("/blog_Comments/{blogId}")
	public ResponseEntity<List<Blog_Comment>> getComments(@PathVariable("blogId") int blogId){
		List <Blog_Comment> blog_Comments = blog_CommentDAO.getComments(blogId);
				if(blog_Comments.isEmpty()){
					Blog_Comment comment = new Blog_Comment();
					comment.setErrorCode("404");
					comment.setErrorMsg("No Comments posted for this blog!");
					blog_Comments.add(comment);
				}
				return new ResponseEntity<List<Blog_Comment>>(blog_Comments, HttpStatus.OK);
	}
	//Tested
	@RequestMapping("/allCommets")
	public ResponseEntity<List<Blog_Comment>> getAllComments(){
		List <Blog_Comment> blog_Comments = blog_CommentDAO.getAllComments();
				if(blog_Comments.isEmpty()){
					Blog_Comment comment = new Blog_Comment();
					comment.setErrorCode("404");
					comment.setErrorMsg("No Comments posted for this blog!");
					blog_Comments.add(comment);
				}
				return new ResponseEntity<List<Blog_Comment>>(blog_Comments, HttpStatus.OK);
	}
	
	//Tested
	@RequestMapping("/createComment")
	public ResponseEntity<Blog_Comment> createComment(@RequestBody Blog_Comment newComment){
		blog_CommentDAO.saveComment(newComment);
		return new ResponseEntity<Blog_Comment>(newComment , HttpStatus.OK);
	}
	//Tested
	@RequestMapping("/deleteComment/{commentId}")
	public boolean deleteComment(@PathVariable("commentId") int commentId){
		blog_CommentDAO.deleteComment(commentId);
		return true ;
	}
	//Tested
	@RequestMapping(value="/updateCommnet/{commentId}", method = RequestMethod.POST)
	public ResponseEntity<Blog_Comment> updateComment(@PathVariable("commentId") int commentId, @RequestBody Blog_Comment blog_Commnet){
		if(blog_CommentDAO.getComm(commentId)==null){
			blog_Comment.setErrorCode("404");
			blog_Comment.setErrorMsg("No comment found !");
			
		}
		blog_CommentDAO.updateComment(blog_Commnet);
		return new ResponseEntity<Blog_Comment>(blog_Commnet, HttpStatus.OK);
	}
	//Tested
	@RequestMapping("/comment/{id}")
	public ResponseEntity<Blog_Comment> getBlogComment(@PathVariable("id") int id){
		Blog_Comment bc =  blog_CommentDAO.getComm(id);
		if(bc == null){
		    bc = new Blog_Comment();
		    bc.setCommentId(0);
			bc.setErrorCode("404");
			bc.setErrorMsg("No Comments for this id!");
		}
		return new ResponseEntity<Blog_Comment>(bc, HttpStatus.OK);
	}

}
