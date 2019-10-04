package com.longbro.dao.impl;

/**
 * 
 * 描述：闹铃用户表 DAO接口
 * 作者:longbro
 * 日期:2019-10-01 00:42:29
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
import java.util.List;

import com.longbro.bean.AlarmUser;
import com.longbro.common.BaseDao;
import com.longbro.dao.AlarmUserDao;

import org.springframework.stereotype.Repository;

@Repository
public class AlarmUserDaoImpl extends BaseDao implements AlarmUserDao {
	final String pre="com.longbro.bean.AlarmUser.";
	@Override
	public AlarmUser getById(String userId) {
		// TODO Auto-generated method stub
		return (AlarmUser)this.selectOne(pre+"get", userId);
	}
	@Override
	public void addAlarmUser(AlarmUser au) {
		// TODO Auto-generated method stub
		this.insert(pre+"create", au);
	}
	@Override
	public void updateAlarmUser(AlarmUser au) {
		// TODO Auto-generated method stub
		this.update(pre+"update", au);
	}
}

