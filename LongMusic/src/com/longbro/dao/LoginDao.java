package com.longbro.dao;

import java.util.Map;

import com.longbro.bean.User;

public interface LoginDao {
	public User queryUser(Map<Object, Object> map);
	public void addUser(Map<Object, Object> map);
	public void resetPassword(Map<Object, Object> map);

}
