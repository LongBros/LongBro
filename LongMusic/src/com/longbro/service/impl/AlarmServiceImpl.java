/**
 * 
 * <pre> 
 * 描述：闹铃表(Alarm) DAO接口
 * 作者:longbro
 * 日期:2019-10-01 00:42:08
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
package com.longbro.service.impl;

import java.util.List;

import com.longbro.bean.Alarm;
import com.longbro.dao.AlarmDao;
import com.longbro.service.AlarmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl implements AlarmService {
	@Autowired AlarmDao dao;
	@Override
	public List<Alarm> getAlarmByUserId(String userId) {
		// TODO Auto-generated method stub
		return dao.getAlarmByUserId(userId);
	}
	@Override
	public void addAlarm(Alarm alarm) {
		// TODO Auto-generated method stub
		dao.addAlarm(alarm);
	}
	@Override
	public Integer getAlarmNums(String userId, String time) {
		// TODO Auto-generated method stub
		return dao.getAlarmNums(userId, time);
	}
	@Override
	public void updateAStatusById(int aId) {
		// TODO Auto-generated method stub
		dao.updateAStatusById(aId);
	}
}

