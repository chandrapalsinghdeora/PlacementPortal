package com.precise.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.precise.dao.ForumDAO;
import com.precise.model.Forum;
import com.precise.model.Reply;
import com.precise.model.ThreadBlog;
@Service("forumServ")
@Transactional
public class FormServiceImpl implements ForumService {

	@Autowired
	private ForumDAO fdao;
	@Override
	public void saveForum(Forum forum) {
		System.out.println("FormServiceImpl.saveForum() --" +forum.getForumName());
		fdao.saveForum(forum);
	}
	@Override
	public List<Forum> getAllFourm(int roleId) {
		System.out.println("FormServiceImpl.getAllFourm()");
		return fdao.getAllFourm(roleId);
	}
	@Override
	public List<ThreadBlog> getPostPermission() {
		return fdao.getPostPermission();
	}
	@Override
	public void updateForum(Forum forum) {
		fdao.updateForum(forum);		
	}
	@Override
	public void deleteForum(int forumId) {
		System.out.println("FormServiceImpl.deleteForum()"+forumId);
		fdao.deleteForum(forumId);
		
	}
	@Override
	public void saveReply(Reply reply) {
		System.out.println("FormServiceImpl.saveReply()");
		fdao.saveReply(reply);
	}
	@Override
	public List<Reply> getAllReplyByQuestionId(int qid) {
		System.out.println("FormServiceImpl.getQuestionReply()"+qid);
		return fdao.getAllReplyByQuestionId(qid);
	}
	@Override
	public String getQuestionByQuestionId(int qid) {
		System.out.println("FormServiceImpl.getQuestionByQuestionId()"+qid);
		return fdao.getQuestionByQuestionId(qid);
	}
	
	@Override
	public Map<String,String> getForumData(String forumId){
		return fdao.getForumData(forumId);
	}
	
	@Override
	public List<ThreadBlog> getReplyId(int threadreplyId){
		
		return fdao.getReplyId(threadreplyId);
	}
}
