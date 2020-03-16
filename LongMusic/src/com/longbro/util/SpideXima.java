package com.longbro.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 爬取https://www.ximalaya.com/waiyu/14804689/的内容
 * 2020-02-03
 */
public class SpideXima {
	static Connection con=null;
	static PreparedStatement ps=null;
	static Statement st=null,st1=null;	
	static ResultSet rs=null;
    public static  void  main(String args[]){
//    	for(int i=1;i<11;i++){
//        	getClassicalChinese(i);
//        	System.out.println("第"+i+"页爬取成功");
//    	}
    	getClassicalChinese(1);
    }

    public static void getClassicalChinese(int page){
    	Document doc;
		try {
			Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/music?useUnicode=true"
    				+ "&characterEncoding=utf-8&serverTimezone=UTC", "root", "ZCLZY");
            st=con.createStatement();
			
			doc = Jsoup.connect("https://www.ximalaya.com/waiyu/14804689/p"+page+"").ignoreContentType(true).userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)").get();
			Elements titles = doc.getElementsByClass("text");
//			System.out.println(titles);
//			FileWriter fw=new FileWriter("C:\\Users\\Administrator\\Desktop\\fatLuo/1.txt");
//			fw.write(docs);
			int i=0;

			for(Element cs: titles){
				String title=cs.toString();
				if(title.contains("_Vc")){
					String tip=title.substring(title.indexOf("title")+7, title.indexOf("href")-2);
					String href=title.substring(title.indexOf("href")+6,title.indexOf("<span")-2);
					System.out.println(tip);
					System.out.println("https://www.ximalaya.com"+href);
					href="https://www.ximalaya.com"+href;
					Document con = Jsoup.connect(href).ignoreContentType(true).
							userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)").get();
//					System.out.println(con);
					Elements es=con.getElementsByClass("tj_");
					String content=con.toString();
					content=content.substring(content.indexOf("<article class=\"intro  tj_\">"), content.indexOf("</article>"));
					content=content.replaceAll("<[.[^<]]*>", "");
					File file=new File("C:\\Users\\Administrator\\Desktop\\fatLuo/as/"+tip+".txt");
					file.createNewFile();
					FileWriter fw=new FileWriter(file);
					fw.write(content);
					//String sql="insert into d_classical_chinese (c_Name,c_Author,c_Dynasty,c_Content,c_InputTime) values('"+name+"','"+au+"','"+dyna+"','"+con+"','"+TimeUtil.time()+"')";
//					
//					String sql="INSERT INTO `music`.`d_diary` (`n_Type`, `n_BookId`, `n_Writter`, `n_Title`, `n_Content`, `n_Time`, `n_Weather`, `n_Mood`, `n_Location`, `n_AllowComment`, `n_Authority`, `n_song_id`, `n_top`, `n_user_top`, `n_write_device`) VALUES ('3', NULL, '35631224', '"+name+" "+dyna+":"+au+"', '"+con+"', '2019-11-24 08:44:33 星期日 ', '2', '2', '湖北省武汉市', '0', '2', NULL, NULL, NULL, NULL);";
//					st.execute(sql);
					//					for(String s:ss){
//						System.out.println(s);
//						System.out.println(i+"----"+i+"----"+i+"----"+i);
//					}

					System.out.println("-----------");
				}
				i++;

			}
			st.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    public static void insertIntoDiary(){
    	
    }
}
