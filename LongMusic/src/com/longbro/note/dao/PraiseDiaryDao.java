package com.longbro.note.dao;

import java.util.List;

import com.longbro.note.bean.PraiseDiary;
import com.longbro.note.bean.StoreDiary;
import com.longbro.note.dao.PraiseDiaryDao;
import com.longbro.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：日记点赞表 DAO接口
 * 作者:longbro
 * 日期:2019-10-25 21:51:50
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class PraiseDiaryDao extends BaseDao{

	public String getNamespace() {
		return PraiseDiary.class.getName();
	}
	public void create(PraiseDiary bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".create", bean);
	}
	public void remove(PraiseDiary diary) {
		// TODO Auto-generated method stub
		this.delete(getNamespace()+".remove",diary);
	}
	/**
	 * @desc 判断当前用户是否已为当前日记点赞
	 * @author zcl
	 * @date 2019年10月25日
	 * @param diary
	 * @return
	 */
	public PraiseDiary get(PraiseDiary diary) {
		// TODO Auto-generated method stub
		return (PraiseDiary)this.selectOne(getNamespace()+".get", diary);
	}
	public int getPraiseNum(String diary){
		return (Integer)this.selectOne(getNamespace()+".getPraiseNum", diary);
	}
	public List<PraiseDiary> getLikeDiarybyUser(String userId){
		return (List<PraiseDiary>)this.selectList(getNamespace()+".getLikeDiarybyUser", userId);
	}
}

