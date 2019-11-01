package com.longbro.note.service;

import java.util.List;

import com.longbro.note.bean.Author;
import com.longbro.note.bean.UserInfo;
import com.longbro.note.dao.UserInfoDao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
/**
 * 
 * <pre> 
 * 描述：d_user_info DAO接口
 * 作者:longbro
 * 日期:2019-10-26 13:07:00
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class UserInfoService{
	@Autowired UserInfoDao dao;
	public Author get(int UUserId) {
		return dao.get(UUserId);
	}
	public void create(UserInfo bean) {
		// TODO Auto-generated method stub
		dao.create(bean);
	}
	//根据账号和密码来登录网站
	public UserInfo loginNote(String acc,String pass){
		return dao.loginNote(acc, pass);
	}
}

