package com.precise.dao;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.precise.model.UserBean;

public interface LoginDao {
	public Map<String,String> checkUserLoginData(UserBean userBean,HttpSession session) throws Exception;

	public Map<String, String> loginGoogle(UserBean userBean, HttpSession session);

	public Map<String, String> getStdRoles(HttpSession session);
}
