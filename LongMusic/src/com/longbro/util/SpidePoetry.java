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
 * 爬取http://www.exam58.com的诗词
 */
public class SpidePoetry {
	static Connection con=null;
	static PreparedStatement ps=null;
	static Statement st=null,st1=null;	
	static ResultSet rs=null;
    public static  void  main(String args[]){
    	getPoemCon();
    }

    /**
     * 1.得到所有诗人的所有诗的链接，存至poem表
     * 暂未考虑分页，只查询了诗人的第一页诗
     */
    private static void getPoems() {
        try{
            Document doc= Jsoup.connect("http://www.exam58.com/lbds").get();
            ArrayList<String> poets=getPoets(doc,"d6");//得到所有诗人的链接
            Class.forName("com.mysql.jdbc.Driver");
    		//serverTimezone服务器时区，UTC是统一标准世界时间。
    		//useUnicode=true&characterEncoding=utf-8解决中文乱码
    		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/music?useUnicode=true"
    				+ "&characterEncoding=utf-8&serverTimezone=UTC", "root", "ZCLZY");
            for(String poet:poets){
                Document doc1= Jsoup.connect("http://www.exam58.com/"+poet).get();
                Elements e=doc1.getElementsByClass("listbox");
                String poems[]=e.toString().split("</b>]");
                for (int i=1;i<poems.length;i++){
                    System.out.println("--------------------------------------");
                    poems[i]=poems[i].substring(0,poems[i].indexOf("</li>"));
                    String poem=poems[i].substring(poems[i].indexOf("href")+6,poems[i].indexOf("html")+4);//古诗链接
                    String poetT="";//诗人名
                    String poemC="";//古诗诗名
                    if(poems[i].contains("》")){
                        if(poems[i].contains("<b>")){
                            poetT=poems[i].substring(poems[i].indexOf("_blank\">")+11,poems[i].indexOf("《"));
                        }else{
                            poetT=poems[i].substring(poems[i].indexOf("_blank\">")+8,poems[i].indexOf("《"));
                        }
                        poemC=poems[i].substring(poems[i].indexOf("《")+1,poems[i].indexOf("》"));
                    }
                    System.out.println(poems[i]);
                    String sql="insert into poem(p_Name,p_Poet,p_Link) values('"+poemC+"','"+poetT+"','"+poem+"')";
                    st=con.createStatement();
        			st.execute(sql);
                    System.out.println(poem+"--"+poetT+"--《"+poemC+"》");
                }
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }

        }catch (Exception e){

        }
    }
    /**
     * 2.得到所有诗人
     * @desc 
     * @author zcl
     * @date 2019年10月6日
     * @param doc
     * @param d6
     * @return
     */
    private static ArrayList<String> getPoets(Document doc, String d6) {
        ArrayList<String> poets=new ArrayList<String>();
        Elements poet = doc.getElementsByClass(d6);
        String spoet=poet.toString();
        String s[]=spoet.split("<li>");
        for (String spo: s){
            System.out.println(spo);
            if(spo.contains("</li>")){
                String spoeHref="";
                if (spo.contains("class")){
                    spoeHref=spo.substring(spo.indexOf("href")+7,spo.indexOf("class")-3);//诗人链接
                }else{
                    spoeHref=spo.substring(spo.indexOf("href")+7,spo.indexOf("\">")-1);//诗人链接
                }
                String spoeName=spo.substring(spo.indexOf("\">")+2,spo.indexOf("</a>")-2);
                //System.out.println(spoeHref+"--------"+spoeName);
                poets.add(spoeHref);
            }

        }
        return  poets;
    }
    public static void getPoemCon(){
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/music?useUnicode=true"
    				+ "&characterEncoding=utf-8&serverTimezone=UTC", "root", "ZCLZY");
           st=con.createStatement();
           rs=st.executeQuery("select p_Id,p_Link from poem where p_Id>921;");
           while(rs.next()){
				Document doc= Jsoup.connect("http://www.exam58.com"+rs.getString(2)).get();
				Elements poemCon = doc.getElementsByClass("content");
				String poems=poemCon.toString();
				String ps=poems.substring(poems.indexOf("</script>"), poems.indexOf("<table width=\"100%\" border"));
				ps=ps.substring(ps.indexOf("center"));
				ps=ps.substring(ps.indexOf("</div>")+7);
				ps=ps.substring(0, ps.indexOf("<a href"));
				if(ps.contains("'")){
					ps=ps.replaceAll("'", "“");
				}
				String update="update poem set p_PoemCons='"+ps+"' where p_Id="+rs.getInt(1);
				System.out.println("第"+rs.getInt(1)+"更新成功");
				st1=con.createStatement();
				st1.execute(update);
				st1.close();
           }
           rs.close();
           st.close();
           con.close();
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
