package com.longbro.note.bean;
/**
 *  
 * 描述：日记访问记录表实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-11-18 21:04:53
 * 版权：哆啦学娱网络科技有限公司www.duola.vip
 * </pre>
 */
public class Visit{

	protected Integer VId;

	protected String VDiary; //被访问日记
	protected String VVisitor; //访问者
	protected String VVisited; //被访问者(即被访问日记的作者)
	protected String VVisitTime; //访问时间
	protected String VReadStatus; //访问情况被读状态(0:未读,1:已读)
	
	
	public Integer getVId() {
		return this.VId;
	}
	public void setVId(Integer aValue) {
		this.VId = aValue;
	}
	public void setVDiary(String VDiary) {
		this.VDiary = VDiary;
	}
	
	/**
	 * 返回 被访问日记
	 * @return
	 */
	public String getVDiary() {
		return this.VDiary;
	}
	public void setVVisitor(String VVisitor) {
		this.VVisitor = VVisitor;
	}
	
	/**
	 * 返回 访问者
	 * @return
	 */
	public String getVVisitor() {
		return this.VVisitor;
	}
	public void setVVisited(String VVisited) {
		this.VVisited = VVisited;
	}
	
	/**
	 * 返回 被访问者(即被访问日记的作者)
	 * @return
	 */
	public String getVVisited() {
		return this.VVisited;
	}
	public void setVVisitTime(String VVisitTime) {
		this.VVisitTime = VVisitTime;
	}
	
	/**
	 * 返回 访问时间
	 * @return
	 */
	public String getVVisitTime() {
		return this.VVisitTime;
	}
	public void setVReadStatus(String VReadStatus) {
		this.VReadStatus = VReadStatus;
	}
	
	/**
	 * 返回 访问情况被读状态(0:未读,1:已读)
	 * @return
	 */
	public String getVReadStatus() {
		return this.VReadStatus;
	}
	
}



