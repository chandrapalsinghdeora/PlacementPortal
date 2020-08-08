package com.precise.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.precise.dao.LoginDao;
import com.precise.model.UserBean;


@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDao;
	
	@Transactional(rollbackFor=Exception.class)
	public Map<String,String> checkValidUser(UserBean userBean,HttpSession session) throws Exception{
		return loginDao.checkUserLoginData(userBean,session);
	}

	@Override
	public Map<String, String> loginGoogle(UserBean userBean, HttpSession session) {
		// TODO Auto-generated method stub
		return loginDao.loginGoogle(userBean,session);
	}

	@Override
	public Map<String, String> getStdRoles(HttpSession session) {
		// TODO Auto-generated method stub
		return loginDao.getStdRoles(session);
	}

}
