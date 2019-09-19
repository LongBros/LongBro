package com.longbro.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.longbro.bean.Comment;
import com.longbro.common.BaseDao;
import com.longbro.dao.CommentDao;
@Repository
public class CommentDaoImpl  extends BaseDao implements CommentDao{
	@Override
	public void addComment(Map<String, String> map) {
		// TODO Auto-generated method stub
		this.insert("com.longbro.bean.comment.addComment", map);
	}
	@Override
	public List<Comment> queryComments(int c_Type,String c_Reviewed) {
		// TODO Auto-generated method stub
		HashMap<String,String> map=new HashMap<String, String>();
		map.put("c_Type", c_Type+"");map.put("c_Reviewed", c_Reviewed);
		return (List<Comment>)this.selectList("com.longbro.bean.comment.queryComment", map);
	}
}
