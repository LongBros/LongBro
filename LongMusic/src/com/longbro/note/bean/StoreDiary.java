package com.longbro.note.bean;
/**
 *  
 * 描述：d_store实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-10-22 20:28:10
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class StoreDiary{

	protected Integer SId;

	protected String SDiary; //被收藏的日记
	protected String SStorer; //收藏者(即日记被收藏的用户)
	protected String SStored; //被收藏日记的作者
	protected String SStoreTime; //收藏时间
	protected Integer SReadStatus; //收藏消息的被读状态(0:未读,1:已读)
	
	
	public Integer getSId() {
		return this.SId;
	}
	public void setSId(Integer aValue) {
		this.SId = aValue;
	}
	public void setSDiary(String SDiary) {
		this.SDiary = SDiary;
	}
	
	/**
	 * 返回 被收藏的日记
	 * @return
	 */
	public String getSDiary() {
		return this.SDiary;
	}
	public void setSStorer(String SStorer) {
		this.SStorer = SStorer;
	}
	
	/**
	 * 返回 收藏者(即日记被收藏的用户)
	 * @return
	 */
	public String getSStorer() {
		return this.SStorer;
	}
	public void setSStored(String SStored) {
		this.SStored = SStored;
	}
	
	/**
	 * 返回 被收藏日记的作者
	 * @return
	 */
	public String getSStored() {
		return this.SStored;
	}
	public void setSStoreTime(String SStoreTime) {
		this.SStoreTime = SStoreTime;
	}
	
	/**
	 * 返回 收藏时间
	 * @return
	 */
	public String getSStoreTime() {
		return this.SStoreTime;
	}
	public void setSReadStatus(Integer SReadStatus) {
		this.SReadStatus = SReadStatus;
	}
	
	/**
	 * 返回 收藏消息的被读状态(0:未读,1:已读)
	 * @return
	 */
	public Integer getSReadStatus() {
		return this.SReadStatus;
	}
	
}



