package com.longbro.note.bean;
/**
 *  
 * 描述：笔记本实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-10-09 21:11:32
 * 版权：多啦学娱网络科技有限公司
 */
public class NoteBook{

	protected Integer NId;

	protected Integer NType; 
	protected Integer NBookid; 
	protected String NWritter; 
	protected String NTitle; 
	protected String NContent; 
	protected String NTime; 
	protected Integer NWeather; 
	protected Integer NMood; 
	protected String NLocation; 
	protected Integer NAllowComment; 
	protected Integer nAuthority; 

	public Integer getNId() {
		return this.NId;
	}
	public void setNId(Integer aValue) {
		this.NId = aValue;
	}
	
	public void setNType(Integer NType) {
		this.NType = NType;
	}
	
	/**
	 * 返回 所属类型（0日记本，1记事本，2……）
	 * @return
	 */
	public Integer getNType() {
		return this.NType;
	}
	public void setNBookid(Integer NBookid) {
		this.NBookid = NBookid;
	}
	
	/**
	 * 返回 所属笔记本编号
	 * @return
	 */
	public Integer getNBookid() {
		return this.NBookid;
	}
	public void setNWritter(String NWritter) {
		this.NWritter = NWritter;
	}
	
	/**
	 * 返回 所属用户
	 * @return
	 */
	public String getNWritter() {
		return this.NWritter;
	}
	public void setNTitle(String NTitle) {
		this.NTitle = NTitle;
	}
	
	/**
	 * 返回 标题
	 * @return
	 */
	public String getNTitle() {
		return this.NTitle;
	}
	public void setNContent(String NContent) {
		this.NContent = NContent;
	}
	
	/**
	 * 返回 内容
	 * @return
	 */
	public String getNContent() {
		return this.NContent;
	}
	public void setNTime(String NTime) {
		this.NTime = NTime;
	}
	
	/**
	 * 返回 日期时间，包括星期
	 * @return
	 */
	public String getNTime() {
		return this.NTime;
	}
	public void setNWeather(Integer NWeather) {
		this.NWeather = NWeather;
	}
	
	/**
	 * 返回 气候(0晴,1雾,2霾,3阴,4雨,5雪)
	 * @return
	 */
	public Integer getNWeather() {
		return this.NWeather;
	}
	public void setNMood(Integer NMood) {
		this.NMood = NMood;
	}
	
	/**
	 * 返回 心情(0开心,1微笑,2伤心,3愤怒)
	 * @return
	 */
	public Integer getNMood() {
		return this.NMood;
	}
	public void setNLocation(String NLocation) {
		this.NLocation = NLocation;
	}
	
	/**
	 * 返回 位置
	 * @return
	 */
	public String getNLocation() {
		return this.NLocation;
	}
	public Integer getNAllowComment() {
		return NAllowComment;
	}
	public void setNAllowComment(Integer nAllowComment) {
		NAllowComment = nAllowComment;
	}
	public Integer getNAuthority() {
		return nAuthority;
	}
	public void setNAuthority(Integer NAuthority) {
		 nAuthority=NAuthority ;
	}
	
}



