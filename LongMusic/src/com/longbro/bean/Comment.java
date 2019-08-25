package com.longbro.bean;
/**
 * 评论实体（包括对歌曲：c_Type=0、歌单：c_Type=1的评论）
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年8月20日 下午10:25:17
 * @description
 * @version
 */
public class Comment {
	private int id;
	private int c_Type;//评论类型（对歌曲、歌单的评论，歌曲：0，歌单1）
	private String c_Reviewed;//被评论者id（若c_Type=0，则指歌曲id；若c_Type=1，则指歌单id）
	private String c_Content;//评论内容
	private String c_Time;//评论时间
	private String c_Reviewer;//评论者
	private String c_Ip;//评论者ip
	private String c_Address;//评论者地址
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getC_Type() {
		return c_Type;
	}
	public void setC_Type(int c_Type) {
		this.c_Type = c_Type;
	}
	public String getC_Reviewed() {
		return c_Reviewed;
	}
	public void setC_Reviewed(String c_Reviewed) {
		this.c_Reviewed = c_Reviewed;
	}
	public String getC_Content() {
		return c_Content;
	}
	public void setC_Content(String c_Content) {
		this.c_Content = c_Content;
	}
	public String getC_Time() {
		return c_Time;
	}
	public void setC_Time(String c_Time) {
		this.c_Time = c_Time;
	}
	public String getC_Reviewer() {
		return c_Reviewer;
	}
	public void setC_Reviewer(String c_Reviewer) {
		this.c_Reviewer = c_Reviewer;
	}
	public String getC_Ip() {
		return c_Ip;
	}
	public void setC_Ip(String c_Ip) {
		this.c_Ip = c_Ip;
	}
	public String getC_Address() {
		return c_Address;
	}
	public void setC_Address(String c_Address) {
		this.c_Address = c_Address;
	}
	
}
