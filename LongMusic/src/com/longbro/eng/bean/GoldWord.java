package com.longbro.eng.bean;
/**
 *  
 * 描述：goldword实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-11-09 21:27:43
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class GoldWord{

	protected Integer id;

	protected String chinese; //中文
	protected String english; //英文
	protected String voiceUrl; //发音资源路径
	protected String pronounce; //音标
	protected String bookName; //书名
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChinese() {
		return chinese;
	}
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getVoiceUrl() {
		return voiceUrl;
	}
	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
	public String getPronounce() {
		return pronounce;
	}
	public void setPronounce(String pronounce) {
		this.pronounce = pronounce;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	
	
	
}



