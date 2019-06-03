package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		long l=Long.parseLong("1558941742000");
		String d=sdf.format(new Date(l));
		System.out.println(d);
	}

}
