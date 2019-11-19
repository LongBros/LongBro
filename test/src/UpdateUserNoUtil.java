import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 通过程序获取批量用户编号的数据库更新语句，然后在数据库中执行来修改用户编号
 * @author zcl
 * @date 2019年5月6日
 * @time 上午10:24:04
 */
public class UpdateUserNoUtil {
	static String driver="com.mysql.jdbc.Driver";
	static String urlD="jdbc:mysql://10.10.1.151:43572/liangce_loan_bpm?useUnicode=true&characterEncoding=utf-8";
	static String urlT="jdbc:mysql://10.10.1.15:3306/liangce_loan_bpm?useUnicode=true&characterEncoding=utf-8";

	static ArrayList<HashMap> maps=new ArrayList<>();
	/**
	 * 随机生成00-99
	 */
	public static String generate(){
		int ran=new Random().nextInt(100);
		String two="";
		if(ran<10){
			two="0"+ran;
		}else{
			two=ran+"";
		}
		return two;
	}
	//通过文件获取
	public static ArrayList<HashMap> getFromFile(){
		String text="";
		try {
			File file=new File("C:/Users/pc/Desktop/longbro/work/zhengshi.txt");

			FileReader reader=new FileReader(file);
			BufferedReader br=new BufferedReader(reader);;
			text=br.readLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s[]=text.split("》");
		for(int i=0;i<s.length;i++){
			String ss[]=s[i].split(",");
			HashMap map=new HashMap<>();
			map.put("id", ss[0]);
			map.put("no", ss[1]);
			maps.add(map);
		}
		return maps;
	}
	//通过jdbc获取
	public static ArrayList<HashMap> getFromDb() {
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(urlT,"mysqldb","mysqldb");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from os_user");
			while(rs.next()){
				HashMap map=new HashMap<>();
				String id=rs.getString(1);
				String no=rs.getString(3);
				map.put("id", id);
				map.put("no", no);
				maps.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}
	public static void update(ArrayList<HashMap> ms){
		for(int i=0;i<ms.size();i++){
			String id=(i+100001)+generate();
			String sql="update os_user set USER_NO_="+id+" where USER_ID_="+ms.get(i).get("id")+";";
			System.out.println(sql);
		}
	}
	public static void main(String[] args) {
		update(getFromFile());
//		for(int i=0;i<100;i++){
//			int userNo=10011105;//库中最大的编号
//			int no=Integer.parseInt((userNo+"").substring(0, 6))+1;//新增用户的真实编号前六位
////			System.out.println(no);
//			
//			String id=no+generate();
//			System.out.println(id);//10011277
//			System.out.println(Integer.toHexString(Integer.parseInt(id)));//98c28d
//		}
		
	}
}
