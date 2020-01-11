

package com.longbro.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.longbro.bean.AlarmUser;
/**
 * 
 * <pre> 
 * 描述：闹铃用户表 DAO接口
 * 作者:longbro
 * 日期:2019-10-01 00:42:29
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public interface AlarmUserDao{
	public AlarmUser getById(String userId);
	public void addAlarmUser(AlarmUser au);
	public void updateAlarmUser(AlarmUser au);
	public Integer getAUserNums();

}

