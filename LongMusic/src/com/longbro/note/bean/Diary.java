package com.longbro.note.bean;

/**
 * 包含互动数量的日记实体
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年10月26日 上午7:40:05
 * @description
 * @version
 */
public class Diary extends NoteBook{
	private String headImage;//日记作者的头像
	private String userName;//日记的作者用户名
	private int visitNum;//浏览量
	private int praiseNum;//点赞数量
	private int critiNum;//批评数量
	private int storeNum;//收藏数量
	private int commentNum;//评论数量
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(int visitNum) {
		this.visitNum = visitNum;
	}
	public int getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}
	public int getCritiNum() {
		return critiNum;
	}
	public void setCritiNum(int critiNum) {
		this.critiNum = critiNum;
	}
	public int getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
}
