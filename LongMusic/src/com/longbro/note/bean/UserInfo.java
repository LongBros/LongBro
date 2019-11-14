package com.longbro.note.bean;
/**
 * 描述：d_user_info实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-10-26 13:07:00
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class UserInfo{

	protected Integer UId;

	protected String UUserName; //用户名
	protected Integer UUserId; //系统生成的8位id
	protected Integer UUserSex; //性别
	protected Integer UCommentNum; //总被评论数量
	protected Integer UPraisedNum; //总被赞数量
	protected String UJoinTime; //加入哆啦日记网时间
	protected Integer UStatus; //在线状态(1:在线，0:离线)
	protected String UBornTime; //出生时间
	protected String UHomeSong; //家歌
	protected String signature;
	protected String headImage;
	protected String password;//2019-10-27
	protected String location;//2019-11-15
	public Integer getUId() {
		return this.UId;
	}
	public void setUId(Integer aValue) {
		this.UId = aValue;
	}
	public void setUUserName(String UUserName) {
		this.UUserName = UUserName;
	}
	
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getUUserName() {
		return this.UUserName;
	}
	public void setUUserId(Integer UUserId) {
		this.UUserId = UUserId;
	}
	
	/**
	 * 返回 系统生成的8位id
	 * @return
	 */
	public Integer getUUserId() {
		return this.UUserId;
	}
	public void setUUserSex(Integer UUserSex) {
		this.UUserSex = UUserSex;
	}
	
	/**
	 * 返回 性别
	 * @return
	 */
	public Integer getUUserSex() {
		return this.UUserSex;
	}
	public void setUCommentNum(Integer UCommentNum) {
		this.UCommentNum = UCommentNum;
	}
	
	/**
	 * 返回 总被评论数量
	 * @return
	 */
	public Integer getUCommentNum() {
		return this.UCommentNum;
	}
	public void setUPraisedNum(Integer UPraisedNum) {
		this.UPraisedNum = UPraisedNum;
	}
	
	/**
	 * 返回 总被赞数量
	 * @return
	 */
	public Integer getUPraisedNum() {
		return this.UPraisedNum;
	}
	public void setUJoinTime(String UJoinTime) {
		this.UJoinTime = UJoinTime;
	}
	
	/**
	 * 返回 加入哆啦日记网时间
	 * @return
	 */
	public String getUJoinTime() {
		return this.UJoinTime;
	}
	public void setUStatus(Integer UStatus) {
		this.UStatus = UStatus;
	}
	
	/**
	 * 返回 在线状态(1:在线，0:离线)
	 * @return
	 */
	public Integer getUStatus() {
		return this.UStatus;
	}
	public void setUBornTime(String UBornTime) {
		this.UBornTime = UBornTime;
	}
	
	/**
	 * 返回 出生时间
	 * @return
	 */
	public String getUBornTime() {
		return this.UBornTime;
	}
	public void setUHomeSong(String UHomeSong) {
		this.UHomeSong = UHomeSong;
	}
	
	/**
	 * 返回 家歌
	 * @return
	 */
	public String getUHomeSong() {
		return this.UHomeSong;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
