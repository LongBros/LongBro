package com.longbro.vo;
/**
 * 
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年8月3日 下午2:10:25
 * @description
 * @version
 */
public class CateAmountVo {
	private String cate;//分类
	private String amount;//金额
	private String percent;//所占百分比
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
