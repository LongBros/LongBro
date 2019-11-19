import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 1.测试String及编码	2.测试异常
 * @author pc
 *
 */
public class Test1 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(new String("天河区".getBytes(),"UTF-8"));
		test();
		String s=URLDecoder.decode("%E7%AE%A1%E7%90%86%E5%91%98");
		System.out.println(s);
	}
	
	public static void test(){
		try{
			int i=0/0;
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
