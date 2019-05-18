package com.longbro.bean;
/**
 * 账单bean
 * 2018-11-13
 * @author Administrator
 *
 */
public class Account {
	private String id;//账单在数据库中的id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String time;//记录时间-包括年月日和时分秒
	private String payutil;//支付方式
	private String in_out;//收入或支出
	private String category;//类型（晚餐，午餐，日常等）
	private double amount;//金额
	private String remark;//备注
	private String picture;//图片路径
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPayutil() {
		return payutil;
	}
	public void setPayutil(String payutil) {
		this.payutil = payutil;
	}
	public String getIn_out() {
		return in_out;
	}
	public void setIn_out(String in_out) {
		this.in_out = in_out;
	}
	public String getCate() {
		return category;
	}
	public void setCate(String category) {
		this.category = category;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
