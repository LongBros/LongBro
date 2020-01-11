package com.longbro.note.dao;

import java.util.List;
import com.longbro.note.bean.Visit;
import com.longbro.note.dao.VisitDao;
import com.longbro.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：日记访问记录表 
 * 作者:longbro
 * 日期:2019-11-18 21:04:53
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class VisitDao extends BaseDao{

	public String getNamespace() {
		return Visit.class.getName();
	}
	public void create(Visit bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".create", bean);
	}
}

