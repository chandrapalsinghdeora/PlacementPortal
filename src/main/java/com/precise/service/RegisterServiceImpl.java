package com.precise.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.precise.dao.RegisterDAO;
import com.precise.model.Question;
import com.precise.model.Register;

@Service("serv")
@Transactional
public class RegisterServiceImpl implements RegisterService {
	@Autowired
    private RegisterDAO registerDAO;

	public void saveRegistration(Register reg) {
		//System.out.println("RegisterServiceImpl.saveRegistration()");
		registerDAO.saveRegistration(reg);
	}

	@Override
	public void saveQuestions(Question ques,int userId,int roleId) {
		//System.out.println("RegisterServiceImpl.saveRegistration()");
		registerDAO.saveQuestions(ques,userId,roleId);
	}

	@Override
	public List<Question> getAllQuestion(String threadId) {
		System.out.println("RegisterServiceImpl.getAllQuestion()");
		return registerDAO.getAllQuestion(threadId);
	}
	
	@Override
	public Map<String,String> getQuestionData(String questionId){
		return registerDAO.getQuestionData(questionId);
	}
	
	
	@Override
	public String updateQuestionData(String question,String questionId){
		return registerDAO.updateQuestionData(question,questionId);
	}

	@Override
	public String deleteQuestion(String questionId){
		return registerDAO.deleteQuestion(questionId);
	}

	@Override
	public List<Question> getComQuestions(String parameter) {
		// TODO Auto-generated method stub
		return registerDAO.getComQuestions(parameter);
	}
	
	@Override
	public String getThreadId(String threadId) throws SQLException{
		return registerDAO.getThreadId(threadId);
	}
	
}
