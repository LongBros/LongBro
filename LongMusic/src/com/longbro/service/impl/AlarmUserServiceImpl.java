package com.longbro.service.impl;
import com.longbro.bean.AlarmUser;
import com.longbro.dao.AlarmUserDao;
import com.longbro.service.AlarmUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * <pre> 
 * 描述：闹铃用户表 DAO接口
 * 作者:longbro
 * 日期:2019-10-01 00:42:29
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@Service
public class AlarmUserServiceImpl implements AlarmUserService {
	@Autowired AlarmUserDao dao;
	@Override
	public AlarmUser getById(String userId) {
		// TODO Auto-generated method stub
		return dao.getById(userId);
	}
	@Override
	public void addAlarmUser(AlarmUser au) {
		// TODO Auto-generated method stub
		dao.addAlarmUser(au);
	}
	@Override
	public void updateAlarmUser(AlarmUser au) {
		// TODO Auto-generated method stub
		dao.updateAlarmUser(au);
	}
}

