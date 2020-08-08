package com.precise.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.precise.model.Question;
import com.precise.model.Register;

public interface RegisterService {
	public void saveRegistration(Register reg);

	public void saveQuestions(Question ques,int userId,int roleId);

	public List<Question> getAllQuestion(String threadId);
	
	public Map<String,String> getQuestionData(String questionId);
	
	public String updateQuestionData(String question,String questionId);
	
	public String deleteQuestion(String questionId);

	public List<Question> getComQuestions(String parameter);
	
	public String getThreadId(String companyId) throws SQLException;
	
}
