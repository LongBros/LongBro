package com.longbro.vo;

import com.longbro.bean.Account;

public class BillVo extends Account{
	private String dayIn;
	private String dayOut;
	public String getDayIn() {
		return dayIn;
	}
	public void setDayIn(String dayIn) {
		this.dayIn = dayIn;
	}
	public String getDayOut() {
		return dayOut;
	}
	public void setDayOut(String dayOut) {
		this.dayOut = dayOut;
	}
	
}
