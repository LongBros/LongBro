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
	private String website;
	private String descr;
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDesc() {
		return descr;
	}
	public void setDesc(String desc) {
		this.descr = desc;
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
