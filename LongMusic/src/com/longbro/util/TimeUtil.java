package com.longbro.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
/**
 * Date date=new Date();后面的参数若为全局变量会有问题--时间不会变
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年10月1日 下午6:52:37
 * @description
 * @version
 */
public class TimeUtil {
	static long oneday=1*24*60*60*1000;//一天的毫秒数
	static long onehour=60*60*1000;//一小时的毫秒数
	static long onemin=60*1000;//一分钟的毫秒数
	static long onesec=1000;//一分钟的毫秒数
	static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdfD=new SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args) {
//		System.out.print(getCWeek());
		long c=System.currentTimeMillis();
		long oneday=1*24*60*60*1000;//一天的毫秒数
		System.out.println(sdf.format(new Date(c-oneday)));//一天前
		System.out.println(sdf.format(new Date(c-oneday*7)));//一周前
		System.out.println(sdf.format(new Date(c-oneday*30)));//一月前
	}
	
	public static String driver="com.mysql.jdbc.Driver";
	public static String url="jdbc:mysql://localhost:3306/longvideos?useUnicode=true&characterEncoding=utf-8";
	public static String user="root";
	public static String pass="ZCLZY";
	/**
	 * 1.返回yyyy-MM-dd HH:mm:ss格式的时间
	 * @desc 
	 * @author zcl
	 * @date 2019年9月14日
	 * @return
	 */
	public static String time(){
		Date date=new Date(System.currentTimeMillis());
		String time=sdf.format(date);
		return time;
	}
	/**
	 * 2.得到今天的年-月-日
	 * @desc 
	 * @author zcl
	 * @date 2019年9月20日
	 * @return
	 */
	public static String getToday(){
		return time().substring(0, 10);
	}
	/**
	 * 3.得到当前月
	 * @desc 
	 * @author zcl
	 * @date 2019年9月22日
	 * @return
	 */
	public static String getThisMonth(){
		return time().substring(0, 7);
	}
	/**
	 * 3.得到昨天的年月日
	 * @desc 
	 * @author zcl
	 * @date 2019年9月20日
	 * @return
	 */
	public static String getYesterday(){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(new Date(System.currentTimeMillis()-oneday));
	}
	/**
	 * 4.得到n天前的时间点
	 * @desc 
	 * @author zcl
	 * @date 2019年9月20日
	 * @return
	 */
	public static String getAgo(int n){
		return sdfD.format(new Date(System.currentTimeMillis()-n*oneday));
	}
	/**
	 * 5.得到汉语星期几
	 * @return
	 */
	public static String getCWeek(){
		Calendar calendar=Calendar.getInstance();
		String cweekNum="";//中文，如：星期一
		calendar.setTime(new Date());
		switch(calendar.get(Calendar.DAY_OF_WEEK)){
			case 1:cweekNum="日";break;	
			case 2:cweekNum="一";break;
			case 3:cweekNum="二";break;
			case 4:cweekNum="三";break;
			case 5:cweekNum="四";break;
			case 6:cweekNum="五";break;
			case 7:cweekNum="六";break;
			
		}
		return "星期"+cweekNum;
	}
	/**
	 * 6.得到英语星期？,如Sunday
	 * @return
	 */
	public static String getEWeek(){
		Calendar calendar=Calendar.getInstance();
		String eweek="";//英文，如：Monday
		calendar.setTime(new Date());
		switch(calendar.get(Calendar.DAY_OF_WEEK)){
			case 1:eweek="Sunday";break;	
			case 2:eweek="Monday";break;
			case 3:eweek="Tuesday";break;
			case 4:eweek="Wednesday";break;
			case 5:eweek="Thursday";break;
			case 6:eweek="Friday";break;
			case 7:eweek="Saturday";break;
		}
		return eweek;
	}
	/**
	 * 7.利用数组得到汉语星期几
	 * @return
	 */
	public static String getWeek(){
		Calendar calendar=Calendar.getInstance();
		System.out.println(calendar.DAY_OF_WEEK);
		String week[]={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		String cweekNum="";//中文，如：星期一
		calendar.setTime(new Date());//设置calendar的时间为当前时间
//		System.out.println(new Date());
		/**
		 * yyyy-MM-dd HH:mm:ss	2019-03-12 15:07:08
		 * EEEE  星期二
		 * MMMM  三月
		 * MM	 03
		 */
//		SimpleDateFormat sdf=new SimpleDateFormat("MM");//
//		System.out.println(sdf.format(new Date()));
		for(int i=1;i<=7;i++){
			if(i==calendar.get(Calendar.DAY_OF_WEEK)){
				cweekNum=week[i-1];
				break;
			}
			System.out.println(i);
		}
		return cweekNum;
	}
	/**
	 * @desc 8.随机生成时间  HH:mm:ss
	 * @author zcl
	 * @date 2019年10月26日
	 */
	public static String genRandomTime(){
		Random ran=new Random();
		int hour=ran.nextInt(24);
		int min=ran.nextInt(60);
		int sec=ran.nextInt(60);
		return handleNum(hour)+":"+handleNum(min)+":"+handleNum(sec);
	}
	/**
	 * @desc 9.补0操作，若m为个位数，则在其前补零
	 * @author zcl
	 * @date 2019年10月26日
	 * @param m
	 * @return
	 */
	public static String handleNum(int m){
		if(m<10){
			return "0"+m;
		}else{
			return m+"";
		}
	}
	/**
	 * 10.得到上月：例：2019-12的上月是2019-11
	 * @author LongBro
	 * 2019年12月13日
	 * 下午4:28:00
	 * @return
	 */
	public static String getLastMonth(){
		int mon=Integer.parseInt(getThisMonth().substring(5, 7));
		if(mon==1){
			mon=12;
		}else{
			mon--;
		}
		return getThisMonth().substring(0, 5)+mon;
	}
	/**
	 * 11.得到当前年，例：2019
	 * @author LongBro
	 * 2019年12月13日
	 * 下午4:31:15
	 * @return
	 */
	public static String getThisYear(){
		return getThisMonth().substring(0,4);
	}
	/**
	 * 12.得到上一年，例：2018
	 * @author LongBro
	 * 2019年12月13日
	 * 下午4:32:16
	 * @return
	 */
	public static String getLastYear(){
		return (Integer.parseInt(getThisYear())-1)+"";
	}
	/**
	 * 13.返回几分钟之前或几秒钟之前的时间
	 * @author LongBro
	 * 2019年12月13日
	 * 下午4:57:39
	 * @param t 数字
	 * @param w 类型：0：分，1：秒
	 * @return
	 */
	public static String getBefore(int t,int w){
		if(w==0){
			return sdf.format(new Date(System.currentTimeMillis()-t*onemin));
		}
		return sdf.format(new Date(System.currentTimeMillis()-t*onesec));
	}  
	/**
	 * ~分钟之前、
	 * 今天、昨天、本月~日、上月~日、今年~月~日、去年~月~日
	 * 例：今天 10:43:14 星期五、昨天 10:43:14 星期五、本月09日 22:31:28 星期一、上月21日 21:36:46 星期四、今年12月28日 14:25:27 星期五、去年12月28日 14:25:27 星期五
	 * @author LongBro
	 * 2019年12月13日
	 * 下午4:07:02
	 */
	public static String timeConvert(String time){
		if(time.contains("今天")){//今天 10:43:14 星期五
			return getToday()+" "+time.substring(3);
		}else if(time.contains("昨天")){//昨天 10:43:14 星期五
			return getYesterday()+" "+time.substring(3);
		}else if(time.contains("本月")){//本月09日 22:31:28 星期一
			return getThisMonth()+"-"+time.substring(2,4)+" "+time.substring(6);
		}else if(time.contains("上月")){//上月21日 21:36:46 星期四
			return getLastMonth()+"-"+time.substring(2,4)+" "+time.substring(6);
		}else if(time.contains("今年")){
			return getThisYear()+"-"+time.substring(2,4)+"-"+time.substring(5,7)+time.substring(8);
		}else if(time.contains("去年")){//去年12月28日 14:25:27 星期五
			return getLastYear()+"-"+time.substring(2,4)+"-"+time.substring(5,7)+time.substring(8);
		}else if(time.contains("分钟之前")){//20 分钟之前，年月日固定
			return getBefore(Integer.parseInt(time.substring(0,1)), 0);
		}else if(time.contains("秒")){//20秒分钟之前，年月日固定
			return getBefore(Integer.parseInt(time.substring(0,1)), 1);
		}
		return "";
	}
}
