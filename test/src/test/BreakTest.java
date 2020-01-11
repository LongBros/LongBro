package test;

public class BreakTest {
	public static void main(String[] args) {
		for(int i=0;i<7;i++){
			if(i==7){
				System.out.println("break³É¹¦");
				break;
			}
			System.out.println(i);
			if(i==6){
				System.out.println("breakÊ§Ð§");
			}
		}
	}
}
