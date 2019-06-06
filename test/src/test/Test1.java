package test;
/**
 * Integer的各种方法实现进制间的转换
 * @author zcl
 * @date 2019年5月6日
 * @time 下午4:46:15
 */
public class Test1 {
	public static void main(String[] args) {
		  int n1 = 10000;
		    //十进制转成十六进制：
		    System.out.println(""+Integer.toHexString(n1));
		    //十进制转成八进制 
		    Integer.toOctalString(n1);
		    //十进制转成二进制
		    System.out.println(Integer.toBinaryString(11111));

		    //十六进制转成十进制
		    Integer.valueOf("FFFF",16).toString();
		    //十六进制转成二进制
		    Integer.toBinaryString(Integer.valueOf("FFFF",16));
		    //十六进制转成八进制
		    Integer.toOctalString(Integer.valueOf("FFFF",16));

		    //八进制转成十进制
		    Integer.valueOf("576",8).toString();
		    //八进制转成二进制
		    Integer.toBinaryString(Integer.valueOf("23",8));
		    //八进制转成十六进制
		    Integer.toHexString(Integer.valueOf("23",8));

		//二进制转十进制
		    System.out.println(Integer.valueOf("1111",2).toString());
		    //二进制转八进制
		    Integer.toOctalString(Integer.parseInt("0101", 2));
		    //二进制转十六进制
		    Integer.toHexString(Integer.parseInt("0101", 2));
	}
}
