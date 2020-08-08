package com.precise.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.precise.model.ThreadBlog;

public interface ThreadService {
	public void saveThrad(ThreadBlog thread);
	public List<ThreadBlog> getPost(String forumId);
	public List<ThreadBlog> getReply();
	public List<ThreadBlog> getEdit();
	public List<ThreadBlog> getDelete();
	public List<ThreadBlog> getAllThread(String forumId) throws ParseException;
	public Map<String,Object> getForumName(String thredId);
	public String updateThreadData(ThreadBlog threadBlog,String threadId);
	public String deleteThread(String threadid);
}
