package com.longbro.note.dao;

import java.util.List;
import com.longbro.note.bean.Attention;
import com.longbro.note.dao.AttentionDao;
import com.longbro.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：关注表(notice,attention,concern,watch) 
 * 作者:longbro
 * 日期:2019-11-20 00:03:42
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class AttentionDao extends BaseDao{

	public String getNamespace() {
		return Attention.class.getName();
	}
	public void create(Attention bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".create", bean);
	}
}

