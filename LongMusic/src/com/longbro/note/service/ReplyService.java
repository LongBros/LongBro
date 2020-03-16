package com.longbro.note.service;

import java.util.HashMap;
import java.util.List;

import com.longbro.note.bean.Reply;
import com.longbro.note.dao.ReplyDao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
/**
 * 
 * <pre> 
 * 描述：评论回复表(支持多级回复) DAO接口
 * 作者:longbro
 * 日期:2020-01-26 16:42:32
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class ReplyService{
	@Autowired ReplyDao dao;
	public void create(Reply bean) {
		// TODO Auto-generated method stub
		dao.create(bean);
	}
	public List<HashMap<String,String>> getReplyById(int cid){
		return dao.getReplyById(cid);
	}
}

