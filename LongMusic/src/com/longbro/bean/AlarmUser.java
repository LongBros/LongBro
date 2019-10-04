package com.longbro.bean;

/**
 * <pre>
 *  
 * 描述：闹铃用户表实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-10-01 00:42:29
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class AlarmUser {

	protected Integer AUserid;

	protected String AUsername; 
	protected String AUsersign; 
	protected String AMusics;
	protected String ATips;
	protected String AUserback; 
	protected String ACreatetime; 
	public Integer getAUserid() {
		return this.AUserid;
	}

	
	public void setAUserid(Integer aValue) {
		this.AUserid = aValue;
	}
	
	public void setAUsername(String AUsername) {
		this.AUsername = AUsername;
	}
	
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getAUsername() {
		return this.AUsername;
	}
	public void setAUsersign(String AUsersign) {
		this.AUsersign = AUsersign;
	}
	
	/**
	 * 返回 用户签名
	 * @return
	 */
	public String getAUsersign() {
		return this.AUsersign;
	}
	public void setAUserback(String AUserback) {
		this.AUserback = AUserback;
	}
	
	public String getAMusics() {
		return AMusics;
	}


	public void setAMusics(String aMusics) {
		AMusics = aMusics;
	}


	public String getATips() {
		return ATips;
	}


	public void setATips(String aTips) {
		ATips = aTips;
	}


	/**
	 * 返回 用户桌面背景
	 * @return
	 */
	public String getAUserback() {
		return this.AUserback;
	}
	public void setACreatetime(String ACreatetime) {
		this.ACreatetime = ACreatetime;
	}
	
	/**
	 * 返回 用户生成时间
	 * @return
	 */
	public String getACreatetime() {
		return this.ACreatetime;
	}
	
}



