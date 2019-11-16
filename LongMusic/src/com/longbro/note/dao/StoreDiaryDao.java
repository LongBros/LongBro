package com.longbro.note.dao;

import java.util.List;

import com.longbro.note.bean.StoreDiary;
import com.longbro.note.dao.StoreDiaryDao;
import com.longbro.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：d_store DAO接口
 * 作者:longbro
 * 日期:2019-10-22 20:28:10
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class StoreDiaryDao extends BaseDao{

	public String getNamespace() {
		return StoreDiary.class.getName();
	}
	public void create(StoreDiary diary) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".create", diary);
	}
	public void remove(StoreDiary diary) {
		// TODO Auto-generated method stub
		this.delete(getNamespace()+".remove",diary);
	}
	public StoreDiary get(StoreDiary diary) {
		// TODO Auto-generated method stub
		return (StoreDiary)this.selectOne(getNamespace()+".get", diary);
	}
	//2019-11-16设置所有未读消息为已读
	public void setAsReaded(String userId){
		this.update(getNamespace()+".setAsReaded", userId);
	}
}

