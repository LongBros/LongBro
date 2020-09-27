package test1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;

public class Test  {
	public static void main(String[] args) {
		test();
	}
	
	public static void test(){
		int num[] =new int[2];
		int i=0;
		boolean flag=false;
		while(true){
			Random r=new Random();
			int n=r.nextInt();			
			if(flag){
				num[i]=n;
				compare(num[0],num[1]);
			}else{
				flag=true;
				num[i]=n;
			}
			i=i==0?1:0;
		}
	}
 
	public static void compare(int n1,int n2){
		//System.out.println(n1+"、"+n2+"\n n1>n2："+(n1>n2)+"\t"+"n1==n2："+(n1==n2)+"\t"+"n1<n2："+(n1<n2));
		if(n1>n2){
			System.out.println(n1+"大于"+n2);
		}else if(n1<n2){
			System.out.println(n1+"小于"+n2);
		}else {
			System.out.println(n1+"等于"+n2);
		}
	}
}
