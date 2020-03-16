package com.longbro.note.bean;
/**
 *  
 * 描述：评论回复表(支持多级回复)实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2020-01-26 16:42:32
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class Reply{

	protected Integer RId;
	protected Integer replyer;//回复者
	protected Integer RCommentId; //回复所属评论(含级别-评论ID.回复1级.回复2级……例：1066.25.4是对评论1066下的ID为25回复的ID为64回复的回复)
	protected String RContent; //回复内容
	protected String RTime; //r_time
	protected Integer RReadStatus; //回复的被读状态(0:未读,1:已读,2:删除)
	
	public Integer getRId() {
		return this.RId;
	}
	public void setRId(Integer aValue) {
		this.RId = aValue;
	}
	
	public Integer getReplyer() {
		return replyer;
	}
	public void setReplyer(Integer replyer) {
		this.replyer = replyer;
	}
	public void setRCommentId(Integer RCommentId) {
		this.RCommentId = RCommentId;
	}
	
	/**
	 * 返回 回复所属评论(含级别-评论ID.回复1级.回复2级……例：1066.25.4是对评论1066下的ID为25回复的ID为64回复的回复)
	 * @return
	 */
	public Integer getRCommentId() {
		return this.RCommentId;
	}
	public void setRContent(String RContent) {
		this.RContent = RContent;
	}
	
	/**
	 * 返回 回复内容
	 * @return
	 */
	public String getRContent() {
		return this.RContent;
	}
	public void setRTime(String RTime) {
		this.RTime = RTime;
	}
	
	/**
	 * 返回 r_time
	 * @return
	 */
	public String getRTime() {
		return this.RTime;
	}
	public void setRReadStatus(Integer RReadStatus) {
		this.RReadStatus = RReadStatus;
	}
	
	/**
	 * 返回 回复的被读状态(0:未读,1:已读,2:删除)
	 * @return
	 */
	public Integer getRReadStatus() {
		return this.RReadStatus;
	}
	
}



