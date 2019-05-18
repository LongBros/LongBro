package com.longbro.service;

import java.util.Map;

import com.longbro.bean.User;

public interface LoginService {
	public User queryUser(Map<Object, Object> map);
	public void addUser(Map<Object, Object> map);
	public void resetPassword(Map<Object, Object> map);

}
