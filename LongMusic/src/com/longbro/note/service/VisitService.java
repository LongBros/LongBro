package com.longbro.note.service;

import java.util.List;
import com.longbro.note.bean.Visit;
import com.longbro.note.dao.VisitDao;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
/**
 * 
 * <pre> 
 * 描述：日记访问记录表 DAO接口
 * 作者:longbro
 * 日期:2019-11-18 21:04:53
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class VisitService{
	@Autowired VisitDao dao;
	public void create(Visit bean) {
		// TODO Auto-generated method stub
		dao.create(bean);
	}
}

