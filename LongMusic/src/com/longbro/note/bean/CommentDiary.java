package com.longbro.note.bean;
/**
 *  
 * 描述：日记评论表实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-10-26 07:50:14
 * 版权：哆啦学娱网络科技有限公司www.duola.vip
 * </pre>
 */
public class CommentDiary{

	protected Integer CId;

	protected String CReviewedDiary; //被评论的日记
	protected String CComment; //评论内容
	protected String CReviewer; //写评论的用户
	protected String CReviewed; //被评论的作者
	protected String CReviewTime; //评论时间
	protected Integer CReadStatus; //评论的被读状态(0:未读,1:已读)
	
	
	public Integer getCId() {
		return this.CId;
	}
	public void setCId(Integer aValue) {
		this.CId = aValue;
	}
	public void setCReviewedDiary(String CReviewedDiary) {
		this.CReviewedDiary = CReviewedDiary;
	}
	
	/**
	 * 返回 被评论的日记
	 * @return
	 */
	public String getCReviewedDiary() {
		return this.CReviewedDiary;
	}
	public void setCComment(String CComment) {
		this.CComment = CComment;
	}
	
	/**
	 * 返回 评论内容
	 * @return
	 */
	public String getCComment() {
		return this.CComment;
	}
	public void setCReviewer(String CReviewer) {
		this.CReviewer = CReviewer;
	}
	
	/**
	 * 返回 写评论的用户
	 * @return
	 */
	public String getCReviewer() {
		return this.CReviewer;
	}
	public void setCReviewed(String CReviewed) {
		this.CReviewed = CReviewed;
	}
	
	/**
	 * 返回 被评论的作者
	 * @return
	 */
	public String getCReviewed() {
		return this.CReviewed;
	}
	public void setCReviewTime(String CReviewTime) {
		this.CReviewTime = CReviewTime;
	}
	
	/**
	 * 返回 评论时间
	 * @return
	 */
	public String getCReviewTime() {
		return this.CReviewTime;
	}
	public void setCReadStatus(Integer CReadStatus) {
		this.CReadStatus = CReadStatus;
	}
	
	/**
	 * 返回 评论的被读状态(0:未读,1:已读)
	 * @return
	 */
	public Integer getCReadStatus() {
		return this.CReadStatus;
	}
	
}



