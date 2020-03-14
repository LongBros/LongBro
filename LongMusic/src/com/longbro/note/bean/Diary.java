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
	private int wordSize;//日记内容字数	12-13新增，统计方法 1.数据库查询，弊端是包含了HTML标签 2.Java程序统计，弊端是增加查询时间
	private int authorSex;//作者性别		2020-01-22新增
	private int ifNotice;//登录用户是否已关注日记作者 2020-03-07新增      1已关注，0未关注
	private int ifPraise;//登录用户是否已点赞当前日记 2020-03-07新增      1已点赞，0未点赞
	private String audioInfo;//包含歌曲名、歌手的音频信息 2020-03-07
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
	public int getWordSize() {
		return wordSize;
	}
	public void setWordSize(int wordSize) {
		this.wordSize = wordSize;
	}
	public int getAuthorSex() {
		return authorSex;
	}
	public void setAuthorSex(int authorSex) {
		this.authorSex = authorSex;
	}
	public int getIfNotice() {
		return ifNotice;
	}
	public void setIfNotice(int ifNotice) {
		this.ifNotice = ifNotice;
	}
	public int getIfPraise() {
		return ifPraise;
	}
	public void setIfPraise(int ifPraise) {
		this.ifPraise = ifPraise;
	}
	public String getAudioInfo() {
		return audioInfo;
	}
	public void setAudioInfo(String audioInfo) {
		this.audioInfo = audioInfo;
	}
}
