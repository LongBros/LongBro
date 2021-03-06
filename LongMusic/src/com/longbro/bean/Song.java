package com.longbro.bean;
/**
 * 注意构造函数正确性，不然Spring无法为其设置正确参数
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年4月23日 下午4:39:43
 * @description
 * @version
 */
public class Song {
	private int id;
	private String sourceId;
	
	private String songName;
	private String singer;
	private String duration;
	private String album;
	private String imgPath;
	private String releaseTime;
	private String inputTime;
	private String website;
	private String descr;
	private String lyric;
	private int playNum;
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	public String getInputTime() {
		return inputTime;
	}
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getLyric() {
		return lyric;
	}
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}
	
	public int getPlayNum() {
		return playNum;
	}
	public void setPlayNum(int playNum) {
		this.playNum = playNum;
	}
	public Song(int id, String sourceId, String songName, String singer,
			String duration, String album, String imgPath, String releaseTime,
			String website, String desc) {
		super();
		this.id = id;
		this.sourceId = sourceId;
		this.songName = songName;
		this.singer = singer;
		this.duration = duration;
		this.album = album;
		this.imgPath = imgPath;
		this.releaseTime = releaseTime;
		this.website = website;
		this.descr = desc;
	}
	public Song() {
		super();
	}
	
	
}
