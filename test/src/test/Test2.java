package test;

import java.util.Random;

/**
 * �ж�ĳ�ַ������Ƿ���Ӣ�ġ���ĸ������
 * @author zcl
 * @date 2019��5��6��
 * @time ����4:46:54
 */
public class Test2 {
	public static void main(String[] args) {
		isEnglish("123421");
	}
	public static void isEnglish(String str){
		//��ȫΪӢ�ġ�����true  ����false  
		boolean result1 = str.matches("[a-zA-Z]+");
		//��ȫΪ���֡�����true
		Boolean result6 = str.matches("[0-9]+");
		//����Ӣ�ĺ��������������ַ�(ֻ��Ӣ�����ֵ��ַ���)������true ����false
		boolean result2 = str.matches("[a-zA-Z0-9]+");
		//������Ӣ�ġ�true
		String regex1 = ".*[a-zA-z].*";  
		boolean result3 = str.matches(regex1);
		//���������֡�true
		String regex2 = ".*[0-9].*";  
		boolean result4 = str.matches(regex2);
		//�ж��Ƿ�Ϊ�����ģ����Ƿ���false
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
