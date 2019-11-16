package com.longbro.note.service;

import java.util.HashMap;
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
	//2019-11-16查询用户未读的被赞、评论、、、数量
	public List<HashMap<String, Object>> queryUnReadNum(String userId){
		return dao.queryUnReadNum(userId);
	}
	//2019-11-16查询某用户或所有用户的互动信息
	public List<HashMap<String, Object>> queryInteractNum(String userId){
		return dao.queryInteractNum(userId);
	}
}

