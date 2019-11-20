package com.longbro.note.bean;
/**
 *  
 * 描述：关注表(notice,attention,concern,watch)实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-11-20 00:03:42
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class Attention{

	protected Integer NId;

	protected String NNoticer; //关注者
	protected String NNoticed; //被关注者
	protected String NNoticeTime; //关注时间
	protected Integer NReadStatus; //关注消息的被读状态(0:未读,1:已读)
	
	
	public Integer getNId() {
		return this.NId;
	}
	public void setNId(Integer aValue) {
		this.NId = aValue;
	}
	public void setNNoticer(String NNoticer) {
		this.NNoticer = NNoticer;
	}
	
	/**
	 * 返回 关注者
	 * @return
	 */
	public String getNNoticer() {
		return this.NNoticer;
	}
	public void setNNoticed(String NNoticed) {
		this.NNoticed = NNoticed;
	}
	
	/**
	 * 返回 被关注者
	 * @return
	 */
	public String getNNoticed() {
		return this.NNoticed;
	}
	public void setNNoticeTime(String NNoticeTime) {
		this.NNoticeTime = NNoticeTime;
	}
	
	/**
	 * 返回 关注时间
	 * @return
	 */
	public String getNNoticeTime() {
		return this.NNoticeTime;
	}
	public void setNReadStatus(Integer NReadStatus) {
		this.NReadStatus = NReadStatus;
	}
	
	/**
	 * 返回 关注消息的被读状态(0:未读,1:已读)
	 * @return
	 */
	public Integer getNReadStatus() {
		return this.NReadStatus;
	}
	
}



