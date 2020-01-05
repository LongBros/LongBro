package com.longbro.note.service;

import java.util.List;
import com.longbro.note.bean.Call;
import com.longbro.note.dao.CallDao;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
/**
 * 
 * <pre> 
 * 描述：用户之间的@记录表 DAO接口
 * 作者:longbro
 * 日期:2019-12-31 23:03:47
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class CallService{
	@Autowired CallDao dao;
	public void create(Call bean) {
		// TODO Auto-generated method stub
		dao.create(bean);
	}
}

