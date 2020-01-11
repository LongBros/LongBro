/**
 * 循环批量生成需要的SQL语句
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年8月25日 上午10:05:36
 * @description
 * @version
 */
public class GenerateSql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String sql="UPDATE song SET sortNum=553 WHERE id=553;";
		int i=553;
//		do-while
		do{
			String sql1="UPDATE song SET sortNum="+i+" WHERE id="+i+";";
			System.out.println(sql1);
			i++;
		}while(i<591);
//		i=1;
//		while(i<553){
//			System.out.println(i);
//			i++;
//		}
	}

}
