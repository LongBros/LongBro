package com.longbro.note.bean;

public class Author extends UserInfo{
	private int noticeNum;//关注数量
	private int fanNum;//粉丝数量
	private int likeNum;//作者喜欢的日记数量
	private int storeNum;//作者收藏的日记数量
	private int visitedNum;//作者日记的总被阅读量
	private int commentedNum;//作者日记的总被评论数
	private int praisedNum;//作者日记的总被喜欢数量
	public int getNoticeNum() {
		return noticeNum;
	}
	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}
	public int getFanNum() {
		return fanNum;
	}
	public void setFanNum(int fanNum) {
		this.fanNum = fanNum;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public int getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}
	public int getVisitedNum() {
		return visitedNum;
	}
	public void setVisitedNum(int visitedNum) {
		this.visitedNum = visitedNum;
	}
	public int getCommentedNum() {
		return commentedNum;
	}
	public void setCommentedNum(int commentedNum) {
		this.commentedNum = commentedNum;
	}
	public int getPraisedNum() {
		return praisedNum;
	}
	public void setPraisedNum(int praisedNum) {
		this.praisedNum = praisedNum;
	}
	
}
