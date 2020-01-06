package com.longbro.note.dao;

import java.util.List;
import com.longbro.note.bean.Call;
import com.longbro.note.dao.CallDao;
import com.longbro.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：用户之间的@记录表 
 * 作者:longbro
 * 日期:2019-12-31 23:03:47
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class CallDao extends BaseDao{

	public String getNamespace() {
		return Call.class.getName();
	}
	public void create(Call bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".create", bean);
	}
}

