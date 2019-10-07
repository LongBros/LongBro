

package com.longbro.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.longbro.bean.Alarm;
/**
 * 
 * <pre> 
 * 描述：闹铃表(Alarm) DAO接口
 * 作者:longbro
 * 日期:2019-10-01 00:42:08
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public interface AlarmDao{
	public List<Alarm> getAlarmByUserId(String userId);
	public void addAlarm(Alarm alarm);
	public Integer getAlarmNums(String userId,String time);
	public void updateAStatusById(int aId);
}

