package com.longbro.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SpideNovel {
	public static ArrayList<HashMap<String, String>> spideList() throws IOException{
		ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
		
		Document doc=Jsoup.connect("https://www.biqudao.com/bqge228154/").get();
		Element e=doc.getElementById("list");
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
	 * 
	 * @desc 
	 * @author zcl
	 * @date 2019年6月2日
	 * @param page	页码
	 * @param name	章节名
	 * @return
	 * @throws IOException
	 */
	public static String spideContent(String page,String name) throws IOException{
		Document doc=Jsoup.connect("https://www.biqudao.com/bqge228154/"+page+".html").get();
		Element e=doc.getElementById("content");
		String chapterCon=e.toString().replaceAll("<div id=\"content\">", "")
				.replaceAll("<br>", "")
				.replace("</div>", "");
		chapterCon=chapterCon.substring(0, chapterCon.indexOf("<script>"));
		return chapterCon;
	}
	public static void saveNovel() throws IOException{
		String content="";
		File file=new File("E:/AAAA/novel/novel1.txt");
		file.createNewFile();
		ArrayList<HashMap<String, String>> list=spideList();
		for(HashMap<String, String> map:list){
			String chapterCon=spideContent(map.get("link"),map.get("title"));
			content=content+map.get("title")+chapterCon;
			
			System.out.println("\""+map.get("title")+"\"拼接成功");
		}
//		System.out.println(content);
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(content.getBytes());
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		saveNovel();
	}

}
