package test;
/**
 * ����StatisticsBankReferralVo����ĳ����
 * @author LongBro
 * @date 2019��8��12��
 * @time ����8:48:19
 */
public class Test5 {
	private int age;
	public Test5(int age){
		System.out.println(this.age);
		this.age=this.age+age;
		System.out.println(this.age);
		this.age=this.age+1;
		System.out.println(this.age);

	}
	public static void main(String[] args) {
		new Test5(4);
	}
}
