package com.longbro.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SysProperty {
	/**
	 * @desc 根据name得到cookie的value
	 * @author zcl
	 * @date 2019年12月10日
	 * @param name
	 * @param request
	 * @return
	 */
	public String getCookie(String name,HttpServletRequest request){
		Cookie cookies[]=request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals(name)){
				return cookie.getValue();
			}
		}
		return "";
	}
}
