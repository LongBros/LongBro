package com.longbro.note.service;

import java.util.HashMap;
import java.util.List;

import com.longbro.note.bean.Confide;
import com.longbro.note.dao.ConfideDao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
/**
 * 
 * <pre> 
 * 描述：倾诉墙表 DAO接口
 * 作者:longbro
 * 日期:2020-03-14 12:37:00
 * 版权：哆啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class ConfideService{
	@Autowired ConfideDao dao;
	public void create(Confide bean) {
		// TODO Auto-generated method stub
		dao.create(bean);
	}
	public List<HashMap> getConfides(HashMap map){
		return dao.getConfides(map);
	}
}

