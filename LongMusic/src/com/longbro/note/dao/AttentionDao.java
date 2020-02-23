package com.longbro.note.dao;

import java.util.HashMap;
import java.util.List;

import com.longbro.note.bean.Attention;
import com.longbro.note.bean.PraiseDiary;
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
	//判断当前登录用户是否已关注某作者2019年11月20日
	public List<Attention> whetherNotice(Attention att){
    	return (List<Attention>)this.selectList(getNamespace()+".get", att);
    }
	//取消关注11-20
	public void cancelAtten(Attention bean) {
		// TODO Auto-generated method stub
		this.delete(getNamespace()+".remove", bean);
	}
	//2019-12-03得到我的所有被关注的消息
	public List<HashMap<String, String>> getMyMessage(HashMap<String,Object> map){
		return this.selectList(getNamespace()+".getMyMessage",map);
	}
	//2019-12-07设置所有未读消息为已读
	public void setAsReaded(String userId){
		this.update(getNamespace()+".setAsReaded", userId);
	}
	public List<HashMap<String, String>> getMyAtten(HashMap<String,Object> map){
		return this.selectList(getNamespace()+".getMyAtten",map);
	}
	//2020年1月30日6.查询关注的人的数量
	public int getAttenNum(Attention atten){
		return (Integer)this.selectOne(getNamespace()+".getAttenNum", atten);
	}
}

