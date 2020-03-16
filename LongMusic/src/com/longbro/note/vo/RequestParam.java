package com.longbro.note.vo;
/**
 * 分页参数
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2020年1月29日 下午5:12:27
 * @description
 * @version
 */
public class RequestParam {
	private int ifSep;//是否分页，1：分页 0：不分页
	private int start;//起始
	private int page;//页码
	private int perPage;//每页数量
	
	public int getIfSep() {
		return ifSep;
	}
	public void setIfSep(int ifSep) {
		this.ifSep = ifSep;
	}
	public int getStart() {
		return perPage*(page-1);
	}
	public void setStart(int start) {
		this.start = perPage*(page-1);
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
}
