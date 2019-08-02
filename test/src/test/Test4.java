package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test4 {
	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("1."+System.currentTimeMillis());
		System.out.println("2."+new Date(System.currentTimeMillis()));//2.Mon Jul 08 14:12:31 CST 2019
		System.out.println("3."+sdf.format(new Date(System.currentTimeMillis())));
		System.out.println("4."+sdf.format(System.currentTimeMillis()));
		try {
			System.out.print("5."+sdf.format(sdf.parse("2019-07-08 11:17:0")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
