package spide;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mysql.jdbc.TimeUtil;
/**
 * 爬取:zuopinj.com的书
 * @author pc
 *
 */
public class SpideBooks {
	static Connection con=null;
	static PreparedStatement ps=null;
	static Statement st=null;
	public static void main(String[] args) {
//		String authors[]={"jinyong","feiwosuosi","yishu","xijuan","moyan","luxun"};
		String author="";
		String authors[]={"jiapingwa"};//"luxun","moyan","jinyong"
		//luxun、moyan、jinyong已录完
//		getBooksByAuthor(author);
		for(String a:authors){
			List<HashMap<String,String>> books=getBooksByAuthor(a);
			getChapterByBookId(books);

		}
	}
	/**
	 * 根据作者爬取其所有书籍
	 * 注意：有些作者，如金庸，其小说tab分为全集和短篇小说，存在重复录入现象，需去掉短篇小说的
	 * @author LongBro
	 * 2019年11月19日
	 * 下午6:35:37
	 * @param author
	 * @return
	 */
	private static List<HashMap<String,String>> getBooksByAuthor(String author) {
		List<HashMap<String,String>> list=new ArrayList<>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//serverTimezone服务器时区，UTC是统一标准世界时间。
			//useUnicode=true&characterEncoding=utf-8解决中文乱码
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/music?useUnicode=true"
					+ "&characterEncoding=utf-8&serverTimezone=UTC", "root", "ZCLZY");
			Document doc=Jsoup.connect("http://"+author+".zuopinj.com/").get(); 
			Elements es=doc.getElementsByClass("books");
			
			String books[]=es.toString().split("class=\"pic\"");
			System.out.println(books.length-1);
			HashMap<String,String> map=new HashMap<>();
			for(int i=1;i<books.length;i++){
				String book=books[i];//单本书集
				String url=book.substring(book.indexOf("href")+6, book.indexOf("img")-4);
				String img=book.substring(book.indexOf("img")+9, book.indexOf("alt")-2);
				String bName=book.substring(book.indexOf("alt")+5, book.indexOf("\"></a>"));
				String desc=book.substring(book.indexOf("<p>")+3, book.indexOf("</p>"));
				map.put("url", url);
				map.put("img", img);
				map.put("bName", bName);
				map.put("desc", desc);
				list.add(map);
				map=new HashMap<>();
//				String sql="INSERT INTO `music`.`books` (`b_BookId`, `b_name`, `b_Type`, `b_Writer`, `b_Time`, `b_desc`, `b_face`) VALUES "
//						+ "('"+url+"', '"+bName+"', NULL, '"+author+"', '"+spide.TimeUtil.time()+"', '"+desc+"', '"+img+"');";
//				st=con.createStatement();
//				st.execute(sql);
////				System.out.println(sql);
//				System.out.println(author+"的“"+bName+"”已存入数据库>>"+spide.TimeUtil.time());

//				System.out.println(url);
//				System.out.println(img);
//				System.out.println(bName);
//				System.out.println(desc);
//				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		}catch(Exception e){
			
		}
		return list;

	}
	/**
	 * 根据书籍ID（url）获取书籍的所有章节、及书籍详情
	 * @author LongBro
	 * 2019年11月19日
	 * 下午6:37:27
	 */
	public static void getChapterByBookId(List<HashMap<String,String>> books){
		String url="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//serverTimezone服务器时区，UTC是统一标准世界时间。
			//useUnicode=true&characterEncoding=utf-8解决中文乱码
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/music?useUnicode=true"
					+ "&characterEncoding=utf-8&serverTimezone=UTC", "root", "ZCLZY");
			for(int j=books.size()-1;j<books.size();j++){
				url=books.get(j).get("url");
				Document doc = Jsoup.connect(url).get();
				Elements info=doc.getElementsByClass("book_info");
				String infos=info+"";
//				String img=infos.substring(infos.indexOf("src"), infos.indexOf("alt"));
//				String name=infos.substring(infos.indexOf("<h1>"), infos.indexOf("</h1>"));
//				String lastUp=infos.substring(infos.indexOf("<h1>"), infos.indexOf("</h1>"));
				
				List<HashMap<String, String>> chas=new ArrayList<>();
				
				String desc=infos.substring(infos.indexOf("<p>")+3, infos.indexOf("</p>"));
				Elements es=doc.getElementsByClass("book_list");
				String chapters[]=es.toString().split("<li>");
				System.out.println(chapters.length);
				HashMap<String, String> cha=new HashMap<>();
				for(int i=12;i<chapters.length;i++){
					String chapter=chapters[i];
					String u=chapter.substring(chapter.indexOf("href")+6, chapter.indexOf(".html")+5);
					
					Document docCon = Jsoup.connect(u).get();
					String content=docCon.getElementById("htmlContent")+"";
					content=content.substring(content.indexOf("contentbox")+14, content.length()-6);
					String name=chapter.substring(chapter.indexOf("title")+7, chapter.indexOf("\">"));
					cha.put("url", u);
					cha.put("name", name);
					cha.put("content", content);
					chas.add(cha);
					String sql="INSERT INTO `chapter` (`bookId`, `page`, `name`, `content`,`inputTime`) VALUES "
							+ "('"+url+"','"+u+"','"+name+"','"+content+"','"+spide.TimeUtil.time()+"');";
					st=con.createStatement();
					st.execute(new String(sql.getBytes(),"utf-8"));
					System.out.println(url+"的“"+name+"”已存入数据库>>"+spide.TimeUtil.time());

				}
				System.out.println("》》》》》》》》》》》》》》》》》》》》》");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
