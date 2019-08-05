package com.longbro.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
//Tue May 14 2019 00:00:00 GMT+0800 (中国标准时间)
public class DateUtil {
    /**
     * 根据月份的英文简称获得对应月份
     * @desc 
     * @author zcl
     * @date 2019年5月12日
     * @param m
     * @return
     */
    public static String getMon(String m){
    	String ms[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    	String mons[]={"01","02","03","04","05","06","07","08","09","10","11","12"};
    	String mon="";
    	for(int i=0;i<ms.length;i++){
    		if(m.equals(ms[i])){
    			mon=mons[i];
    		}
    	}
    	return mon;
    }
    public static String formatDate(String str){
    	String ss[]=str.split(" ");
//    	System.out.println(ss.length);
//    	String week=ss[0];
//    	System.out.println(week);
    	String mon=getMon(ss[1]);
//    	System.out.println(mon);
    	String day=ss[2];
//    	System.out.println(day);
    	String year=ss[3];
//    	System.out.println(year);
    	String time=ss[4];
//    	return year+"-"+mon+"-"+day+" "+time;
    	return year+"-"+mon+"-"+day;
    }
  
    public static void main(String[] args) {
    	String str="Thu Aug 23 2018 00:00:00 GMT+0800 (中国标准时间)";
    	System.out.println(formatDate(str));
    }
}
