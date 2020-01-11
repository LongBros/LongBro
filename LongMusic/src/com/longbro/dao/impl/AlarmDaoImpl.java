package com.longbro.dao.impl;

/**
 * 
 * 描述：闹铃表(Alarm) DAO接口
 * 作者:longbro
 * 日期:2019-10-01 00:42:08
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */

import java.util.HashMap;
import java.util.List;

import com.longbro.bean.Alarm;
import com.longbro.common.BaseDao;
import com.longbro.dao.AlarmDao;

import org.springframework.stereotype.Repository;

@Repository
public class AlarmDaoImpl extends BaseDao implements AlarmDao {
	final String pre="com.longbro.bean.Alarm.";

	@Override
	public List<Alarm> getAlarmByUserId(String userId) {
		// TODO Auto-generated method stub
		return (List<Alarm>)this.selectList(pre+"getAlarmByUserId",userId);
	}
	@Override
	public void addAlarm(Alarm alarm) {
		// TODO Auto-generated method stub
		this.insert(pre+"create", alarm);
	}
	@Override
	public Integer getAlarmNums(String userId, String time) {
		HashMap<String, String> map=new HashMap<>();
		map.put("userId", userId);map.put("time", time);
		// TODO Auto-generated method stub
		int num=(int)this.selectOne(pre+"getAlarmNums", map);
		return num;
	}
	@Override
	public void updateAStatusById(int aId) {
		// TODO Auto-generated method stub
		this.update(pre+"updateAStatusById", aId);
	}
}

