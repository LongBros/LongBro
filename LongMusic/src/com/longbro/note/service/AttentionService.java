package com.longbro.note.service;

import java.util.List;
import com.longbro.note.bean.Attention;
import com.longbro.note.dao.AttentionDao;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
/**
 * 
 * <pre> 
 * 描述：关注表(notice,attention,concern,watch) DAO接口
 * 作者:longbro
 * 日期:2019-11-20 00:03:42
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class AttentionService{
	@Autowired AttentionDao dao;
	public void create(Attention bean) {
		// TODO Auto-generated method stub
		dao.create(bean);
	}
	//判断当前登录用户是否已关注某作者2019年11月20日
	public List<Attention> whetherNotice(Attention att){
    	return dao.whetherNotice(att);
    }
	//取消关注11-20
	public void cancelAtten(Attention bean) {
		// TODO Auto-generated method stub
		dao.cancelAtten(bean);
	}
}

