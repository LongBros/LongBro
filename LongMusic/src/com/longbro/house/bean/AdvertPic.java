package com.longbro.house.bean;
/**
 *  
 * 描述：house_advert_pic实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-11-09 21:27:43
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
public class AdvertPic{

	protected Integer AId;

	protected String ATitle; //图片标题
	protected String ALink; //图片跳转链接
	protected String AMessage; //图片的提示信息
	protected String AImage; //图片信息
	protected String ATime; //图片的添加时间
	
	
	public Integer getAId() {
		return this.AId;
	}
	public void setAId(Integer aValue) {
		this.AId = aValue;
	}
	public void setATitle(String ATitle) {
		this.ATitle = ATitle;
	}
	
	/**
	 * 返回 图片标题
	 * @return
	 */
	public String getATitle() {
		return this.ATitle;
	}
	public void setALink(String ALink) {
		this.ALink = ALink;
	}
	
	/**
	 * 返回 图片跳转链接
	 * @return
	 */
	public String getALink() {
		return this.ALink;
	}
	public void setAMessage(String AMessage) {
		this.AMessage = AMessage;
	}
	
	/**
	 * 返回 图片的提示信息
	 * @return
	 */
	public String getAMessage() {
		return this.AMessage;
	}
	public void setAImage(String AImage) {
		this.AImage = AImage;
	}
	
	/**
	 * 返回 图片信息
	 * @return
	 */
	public String getAImage() {
		return this.AImage;
	}
	public void setATime(String ATime) {
		this.ATime = ATime;
	}
	
	/**
	 * 返回 图片的添加时间
	 * @return
	 */
	public String getATime() {
		return this.ATime;
	}
	
}



