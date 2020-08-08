package com.precise.service;

import java.util.List;
import java.util.Map;

import com.precise.model.Forum;
import com.precise.model.Reply;
import com.precise.model.ThreadBlog;

public interface ForumService {
	public void saveForum(Forum forum);
	public List<Forum> getAllFourm(int roleId);
	public List<ThreadBlog> getPostPermission();
	public void updateForum(Forum forum);
	public void deleteForum(int forumId);public void saveReply(Reply reply);
	public String getQuestionByQuestionId(int qid);
	public List<Reply> getAllReplyByQuestionId(int qid);
	public Map<String,String> getForumData(String forumId);
	public List<ThreadBlog> getReplyId(int threadreplyId);
}
