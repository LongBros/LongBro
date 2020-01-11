package com.longbro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longbro.bean.Comment;
import com.longbro.dao.CommentDao;
import com.longbro.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired 
	CommentDao dao;
	@Override
	public void addComment(Map<String, String> map) {
		// TODO Auto-generated method stub
		dao.addComment(map);
	}
	@Override
	public List<Comment> queryComments(int c_Type, String c_Reviewed) {
		// TODO Auto-generated method stub
		return dao.queryComments(c_Type, c_Reviewed);
	}
}
