package com.precise.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.precise.dao.ThradDAO;
import com.precise.model.Forum;
import com.precise.model.ThreadBlog;

@Service("servic")
@Transactional
public class ThreadServiceImpl implements ThreadService {
	
	@Autowired
	private ThradDAO thradDAo;

	
	public void saveThrad(ThreadBlog thread) {
		System.out.println("ThreadServiceImpl.saveRegistration()");
		thradDAo.saveThrad(thread);
	}
	
	public List<ThreadBlog> getPost(String forumId){
		System.out.println("ThreadServiceImpl.getPost()");
		return thradDAo.getPost(forumId);
		
	}
	@Override
	public List<ThreadBlog> getReply() {
		return thradDAo.getPostPermission();
	}
	@Override
	public List<ThreadBlog> getEdit() {
		return thradDAo.getEditPermission();
	}
	@Override
	public List<ThreadBlog> getDelete() {
		return thradDAo.getDeletePermission();
	}
	@Override
	public List<ThreadBlog> getAllThread(String forumId) throws ParseException {
		return thradDAo.getAllThread(forumId);
	}
	
	@Override
	public Map<String,Object> getForumName(String threadId){
		return thradDAo.getForumName(threadId);
	}
	
	@Override
	public String updateThreadData(ThreadBlog threadBlog,String threadId){
		return thradDAo.updateThreadData(threadBlog,threadId);
	}
	
	@Override
	public String deleteThread(String threadId){
		return thradDAo.deleteThreadData(threadId);
	}
}
