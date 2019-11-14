package com.longbro.note.service;

import java.util.List;

import com.longbro.note.bean.PraiseDiary;
import com.longbro.note.bean.StoreDiary;
import com.longbro.note.dao.PraiseDiaryDao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
/**
 * 
 * <pre> 
 * 描述：日记点赞表 DAO接口
 * 作者:longbro
 * 日期:2019-10-25 21:51:50
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class PraiseDiaryService{
	@Autowired PraiseDiaryDao dao;
	public void create(PraiseDiary bean) {
		// TODO Auto-generated method stub
		dao.create(bean);
	}
	public void remove(PraiseDiary diary) {
		// TODO Auto-generated method stub
		dao.remove(diary);
	}
	public PraiseDiary get(PraiseDiary diary) {
		// TODO Auto-generated method stub
		return dao.get(diary);
	}
	public int getPraiseNum(String diary){
		return dao.getPraiseNum(diary);
	}
	public List<PraiseDiary> getMyLikeDiary(String userId){
		return dao.getLikeDiarybyUser(userId);
	}
}
