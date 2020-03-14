package com.longbro.note.dao;

import java.util.HashMap;
import java.util.List;

import com.longbro.note.bean.Confide;
import com.longbro.note.dao.ConfideDao;
import com.longbro.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：倾诉墙表 
 * 作者:longbro
 * 日期:2020-03-14 12:37:00
 * 版权：哆啦学娱网络科技有限公司
 */
@Repository
public class ConfideDao extends BaseDao{

	public String getNamespace() {
		return Confide.class.getName();
	}
	public void create(Confide bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".create", bean);
	}
	public List<HashMap> getConfides(HashMap map){
		return this.selectList(getNamespace()+".get", map);
	}
}

