package com.longbro.eng.dao;

import java.util.HashMap;
import java.util.List;

import com.longbro.common.BaseDao;
import com.longbro.eng.bean.GoldWord;
import com.longbro.eng.dao.GoldWordDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：goldword 
 * 作者:longbro
 * 日期:2019-11-09 21:27:43
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class GoldWordDao extends BaseDao{

	public String getNamespace() {
		return GoldWord.class.getName();
	}
	public GoldWord get(String id) {
		// TODO Auto-generated method stub
		return (GoldWord)this.selectOne(getNamespace()+".get", id);
	}
	public void create(GoldWord bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".create", bean);
	}
	public void remove(String id) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".remove", id);
	}
	public void update(GoldWord bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".update", bean);
	}
	
	public List<GoldWord> getWordsBy(HashMap<String, String> map){
		return this.selectList(getNamespace()+".getWordsBy",map);
	}
}

