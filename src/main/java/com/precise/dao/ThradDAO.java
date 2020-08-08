package com.precise.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.precise.model.ThreadBlog;

public interface ThradDAO {
	public void saveThrad(ThreadBlog thread);

	public List<ThreadBlog> getPost(String forumId);
	public List<ThreadBlog> getPostPermission();
	public List<ThreadBlog> getEditPermission();
	public List<ThreadBlog> getDeletePermission();
	public List<ThreadBlog> getAllThread(String forumId) throws ParseException;
	public Map<String,Object> getForumName(String threadId);
	public String updateThreadData(ThreadBlog threadBlog,String threadId);
	public String deleteThreadData(String threadId);
}
