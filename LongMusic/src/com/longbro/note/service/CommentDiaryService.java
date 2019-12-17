package com.longbro.note.service;

import java.util.HashMap;
import java.util.List;

import com.longbro.note.bean.CommentDiary;
import com.longbro.note.dao.CommentDiaryDao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
/**
 * 
 * <pre> 
 * 描述：日记评论表 DAO接口
 * 作者:longbro
 * 日期:2019-10-26 07:50:14
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class CommentDiaryService{
	@Autowired CommentDiaryDao dao;
	public void create(CommentDiary bean) {
		// TODO Auto-generated method stub
		dao.create(bean);
	}
	public List<HashMap<String, String>> getComByDiaryId(int id) {
		return dao.getComByDiaryId(id);
	}
	//2019-11-16设置所有未读消息为已读
	public void setAsReaded(String userId){
		dao.setAsReaded(userId);
	}
	//2019-12-03得到我的所有被关注的消息
	public List<HashMap<String, String>> getMyMessage(String userId){
		return dao.getMyMessage(userId);
	}
	//2019-12-17得到我的所有评论
	public List<HashMap<String, String>> getMyComment(String userId){
		return dao.getMyComment(userId);
	}
}

