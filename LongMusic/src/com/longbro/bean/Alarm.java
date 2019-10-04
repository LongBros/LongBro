
package com.longbro.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  
 * 描述：闹铃表(Alarm)实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-10-01 00:42:08
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class Alarm{

	protected Integer AId;

	protected String AUserid; 
	protected String ATime; 
	protected String ATips; 
	protected String AMusic; 
	protected Integer AStatus; 
	protected String ACreatetime; 
	
	public Integer getAId() {
		return this.AId;
	}

	public void setAId(Integer aValue) {
		this.AId = aValue;
	}
	
	public void setAUserid(String AUserid) {
		this.AUserid = AUserid;
	}
	
	/**
	 * 返回 闹铃所属用户
	 * @return
	 */
	public String getAUserid() {
		return this.AUserid;
	}
	public void setATime(String ATime) {
		this.ATime = ATime;
	}
	
	/**
	 * 返回 闹铃时间
	 * @return
	 */
	public String getATime() {
		return this.ATime;
	}
	public void setATips(String ATips) {
		this.ATips = ATips;
	}
	
	/**
	 * 返回 闹铃提示语
	 * @return
	 */
	public String getATips() {
		return this.ATips;
	}
	public void setAMusic(String AMusic) {
		this.AMusic = AMusic;
	}
	
	/**
	 * 返回 闹铃音乐
	 * @return
	 */
	public String getAMusic() {
		return this.AMusic;
	}
	public void setAStatus(Integer AStatus) {
		this.AStatus = AStatus;
	}
	
	/**
	 * 返回 闹铃状态-开启，关闭，删除
	 * @return
	 */
	public Integer getAStatus() {
		return this.AStatus;
	}
	public void setACreatetime(String ACreatetime) {
		this.ACreatetime = ACreatetime;
	}
	
	/**
	 * 返回 创建时间
	 * @return
	 */
	public String getACreatetime() {
		return this.ACreatetime;
	}
}



