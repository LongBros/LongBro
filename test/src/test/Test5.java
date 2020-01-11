package test;
/**
 * 测试StatisticsBankReferralVo类中某功能
 * @author LongBro
 * @date 2019年8月12日
 * @time 上午8:48:19
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
