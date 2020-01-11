
public class Test3 {
	public static void main(String[] args) {
		 Thread t=new Thread() {
			public void run() {
				Test3 te=new Test3();
				te.get(1);
			}
		 };
		t.start();
	}
	public void get(int t){
			for(int i=0;i<100;i++){
//				synchronized (this) {
					System.out.println(t+">>>"+i);
//				}
			}
		
	}
}
