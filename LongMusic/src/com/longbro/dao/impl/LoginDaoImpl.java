package com.longbro.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.longbro.bean.User;
import com.longbro.common.BaseDao;
import com.longbro.dao.LoginDao;

@Repository
public class LoginDaoImpl extends BaseDao implements LoginDao{
	
	@Override
	public User queryUser(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		User user=(User)this.selectOne("com.longbro.bean.user.queryUser", map);
		return user;
	}

	@Override
	public void addUser(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		this.insert("com.longbro.bean.user.addUser", map);
	}

	@Override
	public void resetPassword(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		this.update("com.longbro.bean.user.resetPassword", map);
	}

}
