package test;
/**
 * Integer�ĸ��ַ���ʵ�ֽ��Ƽ��ת��
 * @author zcl
 * @date 2019��5��6��
 * @time ����4:46:15
 */
public class Test1 {
	public static void main(String[] args) {
		  int n1 = 10000;
		    //ʮ����ת��ʮ�����ƣ�
		    System.out.println(""+Integer.toHexString(n1));
		    //ʮ����ת�ɰ˽��� 
		    Integer.toOctalString(n1);
		    //ʮ����ת�ɶ�����
		    System.out.println(Integer.toBinaryString(11111));

		    //ʮ������ת��ʮ����
		    Integer.valueOf("FFFF",16).toString();
		    //ʮ������ת�ɶ�����
		    Integer.toBinaryString(Integer.valueOf("FFFF",16));
		    //ʮ������ת�ɰ˽���
		    Integer.toOctalString(Integer.valueOf("FFFF",16));

		    //�˽���ת��ʮ����
		    Integer.valueOf("576",8).toString();
		    //�˽���ת�ɶ�����
		    Integer.toBinaryString(Integer.valueOf("23",8));
		    //�˽���ת��ʮ������
		    Integer.toHexString(Integer.valueOf("23",8));

		//������תʮ����
		    System.out.println(Integer.valueOf("1111",2).toString());
		    //������ת�˽���
		    Integer.toOctalString(Integer.parseInt("0101", 2));
		    //������תʮ������
		    Integer.toHexString(Integer.parseInt("0101", 2));
	}
}
