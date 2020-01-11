package com.longbro.house.dao;

import java.util.List;
import com.longbro.house.bean.AdvertPic;
import com.longbro.house.dao.AdvertPicDao;
import com.longbro.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：house_advert_pic 
 * 作者:longbro
 * 日期:2019-11-09 21:27:43
 * 版权：多啦学娱网络科技有限公司
 */
@Repository
public class AdvertPicDao extends BaseDao{

	public String getNamespace() {
		return AdvertPic.class.getName();
	}
	public AdvertPic get(String AId) {
		// TODO Auto-generated method stub
		return (AdvertPic)this.selectOne(getNamespace()+".get", AId);
	}
	public void create(AdvertPic bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".create", bean);
	}
	public void remove(String AId) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".remove", AId);
	}
	public void update(AdvertPic bean) {
		// TODO Auto-generated method stub
		this.insert(getNamespace()+".update", bean);
	}
}

