package com.longbro.note.bean;

public class Author extends UserInfo{
	private int noticeNum;//关注数量
	private int fanNum;//粉丝数量
	private int likeNum;//作者喜欢的日记数量
	private int storeNum;//作者收藏的日记数量
	private int visitedNum;//作者日记的总被阅读量
	private int commentedNum;//作者日记的总被评论数
	private int praisedNum;//作者日记的总被喜欢数量
	private String homeSongName;//家歌歌名
	private String blackNames;//blackNameList是黑名单id，这个是黑名单用户名
	private int diaryDayNum;//12-15新增总写日记天数
	private String blacks;//2020-01-28不给看名单昵称
	private int diaryNum;//2020-03-17 日记数量
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
	public String getHomeSongName() {
		return homeSongName;
	}
	public void setHomeSongName(String homeSongName) {
		this.homeSongName = homeSongName;
	}
	public String getBlackNames() {
		return blackNames;
	}
	public void setBlackNames(String blackNames) {
		this.blackNames = blackNames;
	}
	public int getDayNum() {
		return diaryDayNum;
	}
	public void setDayNum(int diaryDayNum) {
		this.diaryDayNum = diaryDayNum;
	}
	public String getBlacks() {
		return blacks;
	}
	public void setBlacks(String blacks) {
		this.blacks = blacks;
	}
	public int getDiaryNum() {
		return diaryNum;
	}
	public void setDiaryNum(int diaryNum) {
		this.diaryNum = diaryNum;
	}
	
}
