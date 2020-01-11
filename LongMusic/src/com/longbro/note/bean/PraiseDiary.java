package com.longbro.note.bean;
/**
 *  
 * 描述：日记点赞表实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-10-25 21:51:50
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class PraiseDiary{

	protected Integer PId;

	protected String PDiary; //被赞的日记
	protected String PPraiser; //点赞的人
	protected String PPraised; //被点赞的人
	protected String PPraiseTime; //点赞时间
	protected Integer PReadStatus; //赞对于被赞的人的被读状态(0：未读，1：已读)
	
	
	public Integer getPId() {
		return this.PId;
	}
	public void setPId(Integer aValue) {
		this.PId = aValue;
	}
	public void setPDiary(String PDiary) {
		this.PDiary = PDiary;
	}
	
	/**
	 * 返回 被赞的日记
	 * @return
	 */
	public String getPDiary() {
		return this.PDiary;
	}
	public void setPPraiser(String PPraiser) {
		this.PPraiser = PPraiser;
	}
	
	/**
	 * 返回 点赞的人
	 * @return
	 */
	public String getPPraiser() {
		return this.PPraiser;
	}
	public void setPPraised(String PPraised) {
		this.PPraised = PPraised;
	}
	
	/**
	 * 返回 被点赞的人
	 * @return
	 */
	public String getPPraised() {
		return this.PPraised;
	}
	public void setPPraiseTime(String PPraiseTime) {
		this.PPraiseTime = PPraiseTime;
	}
	
	/**
	 * 返回 点赞时间
	 * @return
	 */
	public String getPPraiseTime() {
		return this.PPraiseTime;
	}
	public void setPReadStatus(Integer PReadStatus) {
		this.PReadStatus = PReadStatus;
	}
	
	/**
	 * 返回 赞对于被赞的人的被读状态(0：未读，1：已读)
	 * @return
	 */
	public Integer getPReadStatus() {
		return this.PReadStatus;
	}
	
}



