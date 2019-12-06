import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 1.测试String及编码	2.测试异常
 * @author pc
 *
 */
public class Test1 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		for(int i=1;i<34;i++){
			System.out.print("@a"+i+",");
		}
		
		
//		test1();
//
//		System.out.println(new String("天河区".getBytes(),"UTF-8"));
//		test();
//		String s=URLDecoder.decode("%E7%AE%A1%E7%90%86%E5%91%98");
//		System.out.println(s);
	}
	/**
	 * 测试string的split、list的remove
	 * @author LongBro
	 * 2019年12月4日
	 * 下午3:40:12
	 */
	private static void test1() {
		List<HashMap<String, String>> list=new ArrayList();
		HashMap<String, String> map=new HashMap<String, String>();
		HashMap<String, String> map1=new HashMap<String, String>();
		map.put("name", 0+"");
		map1.put("name", "12");
		list.add(map1);
		list.add(map);
		System.out.println("List的长度为"+list.size());
		String ess[]="0,1,2".split(",");
		System.out.println(">>>>>>>>>>>"+ess.length);
		String css[]="3,4".split(",");
		for(int j=0;j<10;j++){
			System.out.println("j="+j);
			for(int i=0;i<ess.length;i++){
				System.out.println("i="+i);
				if(map.get("name").equals(ess[i])){
					list.remove(map);
					break;
				}
			}
		}
		
		for(int i=0;i<css.length;i++){
			System.out.println(i);
			if(map.get("name").equals(css[i])){
				list.remove(map);
				break;
			}
		}
		System.out.println("List的长度为"+list.size());
	}
	
	public static void test(){
		try{
			int i=0/0;
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
