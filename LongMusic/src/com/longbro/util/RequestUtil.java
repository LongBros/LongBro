package com.longbro.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	/**
	 * 	获取客户端的IP地址
	 * @param request
	 */
	public static String getIp(HttpServletRequest request) {
		String l_Ip=request.getHeader("x-forwarded-for");
		if(l_Ip==null||l_Ip.length()==0){
		   l_Ip=request.getHeader("Proxy-Client-IP");
		}if(l_Ip==null||l_Ip.length()==0||"unknown".equalsIgnoreCase(l_Ip)){
		    l_Ip=request.getHeader("WL-Proxy-Client-IP");
		}if(l_Ip==null||l_Ip.length()==0||"unknown".equalsIgnoreCase(l_Ip)){
		    l_Ip=request.getRemoteAddr();
		    if(l_Ip.equals("127.0.0.1") ||l_Ip.equals("0:0:0:0:0:0:0:1")){
			    //根据网卡取本机配置的IP
			    InetAddress in=null;
			    try {
					in=InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    l_Ip=in.getHostAddress();//输出的为本机的IP为172.24.21.129，
			    //但是宿舍无线分配的IP为219.157.79.134手机热点分配的是223.104.105.248，而需要记录的正是这，而非本机 
		   }
		}
		return l_Ip;
	} 
}
