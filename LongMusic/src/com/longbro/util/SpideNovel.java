package com.longbro.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/**
 * 爬取https://www.biqudao.com的小说
 * 1.爬取某本小说的所有章节2.爬取某一章节的内容
 * 3.将小说保存至数据库4.将小说保存至文件
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年8月3日 下午2:08:32
 * @description
 * @version
 */
public class SpideNovel {
	static Connection con=null;
	static PreparedStatement ps=null;
	static Statement st=null;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String bookId="2271";
		String bookName="校花的贴身高手";
		saveNovel(bookId,bookName);
	}
	/**
	 * 1.爬取某本小说的所有章节
	 * @desc 
	 * @author zcl
	 * @date 2019年6月3日
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<HashMap<String, String>> spideList(String bookId) throws IOException{
		ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
		
		Document doc=Jsoup.connect("https://www.biqudao.com/bqge"+bookId+"/").get();
		Element e=doc.getElementById("list");
		System.out.println("----------------");
		System.out.println(e);
		String chapterList=e.toString();
		String d[]=chapterList.split("<dt>");
		String titles[]=d[2].split("href");
		for(int i=1;i<titles.length;i++){
			//章节链接
			String page=titles[i].substring(titles[i].indexOf("=")+2, titles[i].indexOf("\">"));
			page=page.substring(page.indexOf("bqge"));
			page=page.substring(page.indexOf("/")+1,page.indexOf(".html"));
			//章节名
			String title=titles[i].substring(titles[i].indexOf(">")+1, titles[i].indexOf("</a>"));
//			System.out.println(page);System.out.println(title);
//			System.out.println("----------------");
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("link", page);
			map.put("title", title);
			list.add(map);
		}
		return list;
	}
	/**
	 * 2.爬取某一章节的内容
	 * @date 2019年6月3日
	 * @time 上午11:45:49
	 * @author zcl
	 * @param page	章节码
	 * @param name	章节名
	 * @return
	 * @throws IOException
	 */
	public static String spideContent(String bookId,String page,String name) throws IOException{
		Document doc=Jsoup.connect("https://www.biqudao.com/bqge"+bookId+"/"+page+".html").get();
		Element e=doc.getElementById("content");
		String chapterCon=e.toString().replaceAll("<div id=\"content\">", "")
				.replaceAll("<br>", "")
				.replace("</div>", "");
		chapterCon=chapterCon.substring(0, chapterCon.indexOf("<script>"));
		return chapterCon;
	}
	/**
	 * 3.将小说保存至数据库
	 * @date 2019年6月3日
	 * @time 上午11:45:56
	 * @author zcl
	 * @throws IOException 
	 */
	public static void saveToDb(String bookId,String bookName) throws Exception{
		long begin=System.currentTimeMillis();//开始下载时的时间
		String content=new String();
		String suse="";//使用的秒数
		long use=0;
		Class.forName("com.mysql.jdbc.Driver");
		//serverTimezone服务器时区，UTC是统一标准世界时间。
		//useUnicode=true&characterEncoding=utf-8解决中文乱码
		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/longnovel?useUnicode=true"
				+ "&characterEncoding=utf-8&serverTimezone=UTC", "root", "ZCLZY");
		ArrayList<HashMap<String, String>> list=spideList(bookId);
		//增强for循环
		for(HashMap<String, String> map:list){
			String chapterCon=spideContent(bookId,map.get("link"),map.get("title"));
			String sql="INSERT INTO `chapter` (`bookId`,`bookName`, `page`, `name`, `content`) VALUES "
					+ "('"+bookId+"','"+bookName+"','"+map.get("link")+"','"+map.get("title")+"','"+chapterCon+"');";
			st=con.createStatement();
			st.execute(sql);
			System.out.println("\""+map.get("title")+"\"下载成功");
		}
		/*for(int i=229;i<list.size();i++){
			String chapterCon=spideContent(list.get(i).get("link"),list.get(i).get("title"));
			String sql="INSERT INTO `chapter` (`bookId`,`bookName`, `page`, `name`, `content`) VALUES "
					+ "('"+book+"','"+bookName+"','"+list.get(i).get("link")+"','"+list.get(i).get("title")+"','"+chapterCon+"');";
			st=con.createStatement();
			st.execute(sql);
			System.out.println("INSERT INTO `novel` (`bookId`, `page`, `name`, `content`) VALUES ('"+book+"','"+list.get(i).get("link")+"','"+list.get(i).get("title")+"')");
		}*/
	}
	/**
	 * 4.将小说保存至文件
	 * @date 2019年6月3日
	 * @time 上午11:45:08
	 * @author zcl
	 * @throws IOException
	 */
	public static void saveNovel(String bookId,String bookName) throws IOException{
		long begin=System.currentTimeMillis();//开始下载时的时间
		String content=new String();
		String suse="";//使用的秒数
		long use=0;
		
		ArrayList<HashMap<String, String>> list=spideList(bookId);//爬取小说的所有章节
		for(HashMap<String, String> map:list){
			String chapterCon=spideContent(bookId,map.get("link"),map.get("title"));
			content=content+map.get("title")+chapterCon;
			//new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())
			long now=System.currentTimeMillis();//拼接本章时的时间
			use=now-begin;//使用的毫秒数
			suse=use/1000+"";//使用的秒数
			System.out.println("\""+map.get("title")+"\"拼接成功，已使用"+use+"毫秒，"+suse+"s");
		}
//		System.out.println(content);
		//使用字节流
//		FileOutputStream fos=new FileOutputStream(file);
//		fos.write(content.getBytes());
		File file=new File("D:/long/"+bookName+".txt");
		file.createNewFile();
		//使用字符流
		FileWriter out=new FileWriter(file);
		out.write(content);
		System.out.println("下载小说成功，共使用"+suse+"s");

		out.close();
	}
	

}
