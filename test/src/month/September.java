package month;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class September {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeptemberOne();
		SeptemberFour();
		String s[]="12".split(",");
		System.out.println(s[0]);
		
		
		Integer i=10000;
		System.out.println(i==10000);
	}
	//����List��addAll����
	private static void SeptemberFour() {
		List<String> list=new ArrayList<>();
		
		List<String> list1=new ArrayList<>();
		list1.add("1");
		list1.add("3");
		List<String> list2=new ArrayList<>();
		list2.add("2");
		list2.add("4");
		list.addAll(list1);
		list.addAll(list2);
		System.out.println(list);
	}
	//����Double��decimal
	private static void SeptemberOne() {
		Double all=0.0;
		Double dou=1000000.00;//һ����
		System.out.println(all+dou);
		
		
		BigDecimal bd2=new BigDecimal(1000000000000000.00);//����ҵ�����
//		Double d2=Double.parseDouble(bd2.setScale(0)+"");//תΪdouble��
		System.out.println(bd2);
		
		BigDecimal bd=new BigDecimal("0");
		System.out.println(bd.equals("0"));
		System.out.println(bd.toString().equals("0"));
		
		int i=1000000000;//10��
		System.out.println(i);
	}

}
