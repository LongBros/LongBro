package com.longbro.note.dao;

import java.util.HashMap;
import java.util.List;

import com.longbro.note.bean.CommentDiary;
import com.longbro.note.dao.CommentDiaryDao;
import com.longbro.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：日记评论表 
 * 作者:longbro
 * 日期:2019-10-26 07:50:14
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class CommentDiaryDao extends BaseDao{

	public String getNamespace() {
		return CommentDiary.class.getName();
	}
	public void create(CommentDiary bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".create", bean);
	}
	public List<HashMap<String, String>> getComByDiaryId(int id) {
		return this.selectList(getNamespace()+".get",id);
	}
	//2019-11-16设置所有未读消息为已读
	public void setAsReaded(String userId){
		this.update(getNamespace()+".setAsReaded", userId);
	}
	//2019-12-03得到我的所有被评论的消息
	public List<HashMap<String, String>> getMyMessage(String userId){
		return this.selectList(getNamespace()+".getMyMessage",userId);
	}
	//2019-12-17得到我的所有评论
	public List<HashMap<String, String>> getMyComment(String userId){
		return this.selectList(getNamespace()+".getMyComment",userId);
	}
	
}

