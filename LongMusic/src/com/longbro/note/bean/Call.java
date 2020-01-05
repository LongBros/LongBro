package com.longbro.note.bean;
/**
 *  
 * 描述：用户之间的@记录表实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-12-31 23:03:47
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class Call{

	protected Integer AId;

	protected Integer AType; //@类型(0:日记,1:评论)
	protected String ADiary; //发起@的日记或评论
	protected String AAtorUser; //@者(即在日记中发起@的用户)
	protected String AAtedUser; //被@者(即被日记@的作者)
	protected String AAtTime; //@时间
	protected Integer AReadStatus; //@情况被读状态(0:未读,1:已读,2:删除)
	
	
	public Integer getAId() {
		return this.AId;
	}
	public void setAId(Integer aValue) {
		this.AId = aValue;
	}
	public void setAType(Integer AType) {
		this.AType = AType;
	}
	
	/**
	 * 返回 @类型(0:日记,1:评论)
	 * @return
	 */
	public Integer getAType() {
		return this.AType;
	}
	public void setADiary(String ADiary) {
		this.ADiary = ADiary;
	}
	
	/**
	 * 返回 发起@的日记或评论
	 * @return
	 */
	public String getADiary() {
		return this.ADiary;
	}
	public void setAAtorUser(String AAtorUser) {
		this.AAtorUser = AAtorUser;
	}
	
	/**
	 * 返回 @者(即在日记中发起@的用户)
	 * @return
	 */
	public String getAAtorUser() {
		return this.AAtorUser;
	}
	public void setAAtedUser(String AAtedUser) {
		this.AAtedUser = AAtedUser;
	}
	
	/**
	 * 返回 被@者(即被日记@的作者)
	 * @return
	 */
	public String getAAtedUser() {
		return this.AAtedUser;
	}
	public void setAAtTime(String AAtTime) {
		this.AAtTime = AAtTime;
	}
	
	/**
	 * 返回 @时间
	 * @return
	 */
	public String getAAtTime() {
		return this.AAtTime;
	}
	public void setAReadStatus(Integer AReadStatus) {
		this.AReadStatus = AReadStatus;
	}
	
	/**
	 * 返回 @情况被读状态(0:未读,1:已读,2:删除)
	 * @return
	 */
	public Integer getAReadStatus() {
		return this.AReadStatus;
	}
	
}



