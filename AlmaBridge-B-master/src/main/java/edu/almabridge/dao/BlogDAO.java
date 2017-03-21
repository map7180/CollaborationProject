package edu.almabridge.dao;

import java.util.List;

import edu.almabridge.model.Blog;

public interface BlogDAO {
	public boolean saveBlog(Blog blog);
	public boolean deleteBlog(int blogId);
	public void updateBlog(Blog blog);
	public Blog getBlog(int blogId);
	public List<Blog> blogList();
}
