package test;

import java.util.Random;

/**
 * 判断某字符串中是否含有英文、字母、中文
 * @author zcl
 * @date 2019年5月6日
 * @time 下午4:46:54
 */
public class Test2 {
	public static void main(String[] args) {
		isEnglish("123421");
	}
	public static void isEnglish(String str){
		//【全为英文】返回true  否则false  
		boolean result1 = str.matches("[a-zA-Z]+");
		//【全为数字】返回true
		Boolean result6 = str.matches("[0-9]+");
		//【除英文和数字外无其他字符(只有英文数字的字符串)】返回true 否则false
		boolean result2 = str.matches("[a-zA-Z0-9]+");
		//【含有英文】true
		String regex1 = ".*[a-zA-z].*";  
		boolean result3 = str.matches(regex1);
		//【含有数字】true
		String regex2 = ".*[0-9].*";  
		boolean result4 = str.matches(regex2);
		//判断是否为纯中文，不是返回false
		String regex3 = "[\\u4e00-\\u9fa5]+";
		boolean result5 = str.matches(regex3);
		System.out.println(result1+"-result2-"+result2+"-result3-"+result3
				+"-result4-"+result4+"-result5-"+result5+"-result6-"+result6);
	}
	public static String generate(){
		int ran=new Random().nextInt(100);
		String two="";
		if(ran<10){
			two="0"+ran;
		}else{
			two=ran+"";
		}
		return two;
	}
}
