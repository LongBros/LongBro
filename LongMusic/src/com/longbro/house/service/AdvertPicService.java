package com.longbro.house.service;

import java.util.List;

import com.longbro.house.bean.AdvertPic;
import com.longbro.house.dao.AdvertPicDao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
/**
 * 
 * <pre> 
 * 描述：house_advert_pic DAO接口
 * 作者:longbro
 * 日期:2019-11-09 21:27:43
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class AdvertPicService{
	@Autowired AdvertPicDao dao;
	
	public AdvertPic getPicById(String AId){
		return dao.get(AId);
	}
	public void create(AdvertPic bean) {
		// TODO Auto-generated method stub
		dao.create(bean);
	}
	public void delPicById(String AId) {
		dao.remove(AId);
	}
	public void updatePicInfo(AdvertPic bean) {
		dao.update(bean);
	}
}

