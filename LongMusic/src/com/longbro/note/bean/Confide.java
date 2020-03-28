package com.longbro.note.bean;
/**
 *  
 * 描述：倾诉墙表实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2020-03-14 12:37:00
 * 版权：哆啦学娱网络科技有限公司www.duola.vip
 * </pre>
 */
public class Confide{

	protected Integer WId;

	protected String WPourType; //倾诉的类型
	protected String WPourCon; //倾诉内容
	protected String WConfider; //倾诉者
	protected String WConfided; //被倾诉者
	protected String WConfideTime; //倾诉时间
	protected Integer WReadStatus; //倾诉的被读状态(0:未读,1:已读,2:删除)
	
	
	public Integer getWId() {
		return this.WId;
	}
	public void setWId(Integer aValue) {
		this.WId = aValue;
	}
	public void setWPourType(String WPourType) {
		this.WPourType = WPourType;
	}
	
	/**
	 * 返回 倾诉的类型
	 * @return
	 */
	public String getWPourType() {
		return this.WPourType;
	}
	public void setWPourCon(String WPourCon) {
		this.WPourCon = WPourCon;
	}
	
	/**
	 * 返回 倾诉内容
	 * @return
	 */
	public String getWPourCon() {
		return this.WPourCon;
	}
	public void setWConfider(String WConfider) {
		this.WConfider = WConfider;
	}
	
	/**
	 * 返回 倾诉者
	 * @return
	 */
	public String getWConfider() {
		return this.WConfider;
	}
	public void setWConfided(String WConfided) {
		this.WConfided = WConfided;
	}
	
	/**
	 * 返回 被倾诉者
	 * @return
	 */
	public String getWConfided() {
		return this.WConfided;
	}
	public void setWConfideTime(String WConfideTime) {
		this.WConfideTime = WConfideTime;
	}
	
	/**
	 * 返回 倾诉时间
	 * @return
	 */
	public String getWConfideTime() {
		return this.WConfideTime;
	}
	public void setWReadStatus(Integer WReadStatus) {
		this.WReadStatus = WReadStatus;
	}
	
	/**
	 * 返回 倾诉的被读状态(0:未读,1:已读,2:删除)
	 * @return
	 */
	public Integer getWReadStatus() {
		return this.WReadStatus;
	}
	
}



