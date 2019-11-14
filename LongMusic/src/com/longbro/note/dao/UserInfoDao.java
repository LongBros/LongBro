package com.longbro.note.dao;

import java.util.HashMap;
import java.util.List;

import com.longbro.note.bean.Author;
import com.longbro.note.bean.UserInfo;
import com.longbro.note.dao.UserInfoDao;
import com.longbro.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：d_user_info 
 * 作者:longbro
 * 日期:2019-10-26 13:06:58
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class UserInfoDao extends BaseDao<UserInfo>{

	public String getNamespace() {
		return UserInfo.class.getName();
	}
	public Author get(int UUserId) {
		return (Author)this.selectOne(getNamespace()+".get", UUserId);
	}
	public void create(UserInfo bean) {
		this.insert(getNamespace()+".create", bean);
	}
	public UserInfo loginNote(String acc,String pass){
		HashMap<String,String> map=new HashMap<>();
		map.put("acc", acc);
		map.put("pass", pass);
		return (UserInfo)this.selectOne(getNamespace()+".loginNote", map);
	}
}
