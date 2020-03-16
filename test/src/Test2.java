
public class Test2 {
	public static void main(String[] args) {
		for(int i=769;i<805;i++){
			System.out.println("UPDATE song SET sortNum="+i+" WHERE id="+i+";");
		}
	}
}
