package com.longbro.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longbro.bean.User;
import com.longbro.dao.LoginDao;
import com.longbro.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired LoginDao dao;
	@Override
	public User queryUser(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return dao.queryUser(map);
	}
	@Override
	public void addUser(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		dao.addUser(map);
	}
	@Override
	public void resetPassword(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		dao.resetPassword(map);
	}

}
