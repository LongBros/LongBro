package com.longbro.dao;

import java.util.List;
import java.util.Map;

import com.longbro.bean.Comment;

public interface CommentDao {
	public void addComment(Map<String, String> map);
	public List<Comment> queryComments(int c_Type,String c_Reviewed);
}
