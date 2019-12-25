
public class Test2 {
	public static void main(String[] args) {
		for(int i=591;i<705;i++){
			System.out.println("UPDATE song SET sortNum="+i+" WHERE id="+i+";");
		}
	}
}
