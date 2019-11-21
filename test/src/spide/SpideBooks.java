package spide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 * 爬取:zuopinj.com的书
 * @author pc
 *
 */
public class SpideBooks {
	public static void main(String[] args) {
//		String authors[]={"jinyong","feiwosuosi","yishu","xijuan","moyan","luxun"};
//		String author="moyan";
//		getBooksByAuthor(author);
		List<HashMap<String,String>> books=getBooksByAuthor("luxun");
				getChapterByBookId(books);
	}
	/**
	 * 根据作者爬取其所有书籍
	 * @author LongBro
	 * 2019年11月19日
	 * 下午6:35:37
	 * @param author
	 * @return
	 */
	private static List<HashMap<String,String>> getBooksByAuthor(String author) {
		List<HashMap<String,String>> list=new ArrayList<>();
		try{
			Document doc=Jsoup.connect("http://"+author+".zuopinj.com/").get(); 
			Elements es=doc.getElementsByClass("books");
			
			String books[]=es.toString().split("class=\"pic\"");
			System.out.println(books.length);
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
		
		try {
			for(HashMap<String,String> book:books){
				
			}
			Document doc = Jsoup.connect("http://").get();
			Elements info=doc.getElementsByClass("book_info");
			String infos=info+"";
//			String img=infos.substring(infos.indexOf("src"), infos.indexOf("alt"));
//			String name=infos.substring(infos.indexOf("<h1>"), infos.indexOf("</h1>"));
//			String lastUp=infos.substring(infos.indexOf("<h1>"), infos.indexOf("</h1>"));
			
			List<HashMap<String, String>> chas=new ArrayList<>();
			
			String desc=infos.substring(infos.indexOf("<p>")+3, infos.indexOf("</p>"));
			System.out.println(desc);
			Elements es=doc.getElementsByClass("book_list");
			String chapters[]=es.toString().split("<li>");
			System.out.println(chapters.length);
			HashMap<String, String> cha=new HashMap<>();
			for(int i=1;i<chapters.length;i++){
				System.out.println(">>>>>>>>>>>>>");
				String chapter=chapters[i];
				String u=chapter.substring(chapter.indexOf("href")+6, chapter.indexOf(".html")+5);
				String name=chapter.substring(chapter.indexOf("title")+7, chapter.indexOf("\">"));
				cha.put("url", u);
				cha.put("name", name);
				chas.add(cha);
				System.out.println(u);
				System.out.println(name);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
