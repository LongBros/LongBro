package test;

import java.math.BigDecimal;

public class August {
	static String pro="1.75E7";
	public static void main(String[] args) {
		int n=Integer.parseInt(pro.substring(pro.indexOf("E")+1));//n¥Œ√›	7
		double fo=Double.parseDouble(pro.substring(0, pro.indexOf("E")));//1.75
//		pro=Double.parseDouble(pro.substring(0, pro.indexOf("E")))*Math.pow(10,n )+"";
		
		BigDecimal bd=new BigDecimal(pro);
		System.out.println(Integer.parseInt(bd.setScale(0)+""));
	}
}
