package com.precise.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.precise.model.UserBean;

public interface LoginService {
	
	public Map<String,String> checkValidUser(UserBean userBean,HttpSession session) throws Exception;

	public Map<String, String> loginGoogle(UserBean userBean, HttpSession session);

	public Map<String, String> getStdRoles(HttpSession session);

}
