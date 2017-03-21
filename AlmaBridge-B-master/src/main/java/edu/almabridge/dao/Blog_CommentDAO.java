package edu.almabridge.dao;

import java.util.List;

import edu.almabridge.model.Blog_Comment;

public interface Blog_CommentDAO {
	public boolean saveComment(Blog_Comment comment);
	public Blog_Comment updateComment(Blog_Comment comment);
	public void deleteComment(int commentId);
	public Blog_Comment getComment(String userId);
	public Blog_Comment getComm(int commentId);
	public List<Blog_Comment> getComments(int blogId);
	public List<Blog_Comment> getAllComments();

}
