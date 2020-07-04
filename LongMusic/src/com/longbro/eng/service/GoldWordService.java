package com.longbro.eng.service;

import java.util.HashMap;
import java.util.List;

import com.longbro.eng.bean.GoldWord;
import com.longbro.eng.dao.GoldWordDao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
/**
 * 
 * <pre> 
 * 描述：goldword DAO接口
 * 作者:longbro
 * 日期:2019-11-09 21:27:43
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class GoldWordService{
	@Autowired GoldWordDao dao;
	
	public GoldWord getWordById(String id){
		return dao.get(id);
	}
	public void create(GoldWord bean) {
		// TODO Auto-generated method stub
		dao.create(bean);
	}
	public void delWordById(String id) {
		dao.remove(id);
	}
	public void updateWordInfo(GoldWord bean) {
		dao.update(bean);
	}
	public List<GoldWord> getWordsBy(HashMap<String, String> map){
		return dao.getWordsBy(map);
	}
}

