package com.longbro.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 爬取https://so.gushiwen.org/shiwen/default_1Ada5883bac56aA1.aspx的文言文
 * 2020-02-03
 */
public class SpideClassical {
	static Connection con=null;
	static PreparedStatement ps=null;
	static Statement st=null,st1=null;	
	static ResultSet rs=null;
    public static  void  main(String args[]){
    	for(int i=1;i<11;i++){
        	getClassicalChinese(i);
        	System.out.println("第"+i+"页爬取成功");
    	}
    }

    public static void getClassicalChinese(int page){
    	Document doc;
		try {
			Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/music?useUnicode=true"
    				+ "&characterEncoding=utf-8&serverTimezone=UTC", "root", "ZCLZY");
            st=con.createStatement();
			
			doc = Jsoup.connect("https://so.gushiwen.org/shiwen/default_1Ada5883bac56aA"+page+".aspx").get();
			Elements ccs = doc.getElementsByClass("cont");
			for(Element cs: ccs){
				String classical=cs.toString();
				if(classical.contains("yizhu")){
					String ss[]=classical.split("<a");
					int i=0;
					String name=ss[1];
					name=name.substring(name.indexOf("<b>")+3, name.indexOf("</b>"));
					String dyna=ss[2];
					dyna=dyna.substring(dyna.indexOf("\">")+2, dyna.indexOf("</a>"));
					String au=ss[3];
					au=au.substring(au.indexOf("\">")+2, au.indexOf("</a>"));
					String con=ss[3].substring(ss[3].indexOf("id="));
					con=con.substring(con.indexOf("\">")+6,con.length()-16);

					System.out.println(name);
//					System.out.println(dyna);
//					System.out.println(au);
//					System.out.println(con);
//					String sql="insert into d_classical_chinese (c_Name,c_Author,c_Dynasty,c_Content,c_InputTime) values('"+name+"','"+au+"','"+dyna+"','"+con+"','"+TimeUtil.time()+"')";
//					
					String sql="INSERT INTO `music`.`d_diary` (`n_Type`, `n_BookId`, `n_Writter`, `n_Title`, `n_Content`, `n_Time`, `n_Weather`, `n_Mood`, `n_Location`, `n_AllowComment`, `n_Authority`, `n_song_id`, `n_top`, `n_user_top`, `n_write_device`) VALUES ('3', NULL, '35631224', '"+name+" "+dyna+":"+au+"', '"+con+"', '2019-11-24 08:44:33 星期日 ', '2', '2', '湖北省武汉市', '0', '2', NULL, NULL, NULL, NULL);";
					st.execute(sql);
					//					for(String s:ss){
//						System.out.println(s);
//						System.out.println(i+"----"+i+"----"+i+"----"+i);
//						i++;
//					}
					System.out.println("-----------");
				}
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
