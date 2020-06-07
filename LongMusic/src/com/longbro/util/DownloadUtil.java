package com.longbro.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sun.star.setup.CopyFileAction;

/**
 * 下载mp3歌曲、下载网易云音乐歌词
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年5月1日 下午6:23:05
 * @description
 * @version
 */
public class DownloadUtil {
	public static void main(String[] args) {
//		writeToFile(spideLyric("407679465"), "407679465");
		//https://www.kugou.com/song/152uma13.html
		downloadMp3("562598081","献世");//
	}
	/**
	 *1.下载单首mp3歌曲
	 * @desc 下载歌曲文件较大时，不能像下载单词发音那样，而要使用逐字节下载
	 * @author zcl
	 * @date 2019年5月1日
	 * @param sourceId
	 * @param songName
	 */ 
	public static void downloadMp3(String sourceId,String songName){
		String url="";
		String fName="";
		//如果sourceId长度可能小于5需，加入“sourceId.length()>5&&”
		if(sourceId.substring(sourceId.length()-5).equals(".html")){//QQ音乐
			url="http://link.hhtjim.com/qq/"+sourceId.substring(0, sourceId.length()-5)+".mp3";
			fName=sourceId.substring(0,sourceId.length()-5);
		}else if(sourceId.substring(sourceId.length()-3).equals(".kw")){//酷我音乐
			url="http://link.hhtjim.com/kw/"+sourceId.substring(0, sourceId.length()-3)+".mp3";
			fName=sourceId;
		}else if(sourceId.substring(sourceId.length()-4).equals(".mp3")){//我的服务器
			url=sourceId;
		}else{//网易云音乐
			url="http://link.hhtjim.com/163/"+sourceId+".mp3";
			fName=sourceId;
		}
		try {
			System.out.println("开始从"+url+"下载歌曲");
			URL u=new URL(url);
			HttpURLConnection con=(HttpURLConnection)u.openConnection();
			
			DataInputStream dis=new DataInputStream(con.getInputStream());
			
			byte[] b=new byte[dis.available()];
			int len=dis.available();
			File file=new File("F:/553Music/"+fName+".mp3");
			file.createNewFile();
			FileOutputStream fos=new FileOutputStream(file);
//			DataOutputStream dos=new DataOutputStream(fos);
			int count=0;
			String po="";
			while((count=dis.read(b))>0){
				fos.write(b,0,count);
				//歌曲文件总大小
				po=po+".";
				if(po.length()==100){
					System.out.println(po);
					po="";
				}
			}
			System.out.println("下载完成至"+file.getAbsolutePath());
//			copyFile(fName, file,len);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 2.把路径为fPath的文件复制到
	 * @desc 
	 * @author zcl
	 * @date 2019年7月25日
	 * @param 文件名-原文件-文件流长度
	 */
	public static void copyFile(String name,File file,int length){
		try {
			byte b[]=new byte[length];//缓冲数组
			int len;
			FileInputStream fis=new FileInputStream(file);
			FileOutputStream fos=new FileOutputStream(new File("F:/Music/songs/"+name+".mp3"));
			while((len=fis.read(b))!=-1){
				fos.write(b,0,len);
			}
			System.out.println("文件已复制到"+"F:/Music/songs/"+name+".mp3");
			fis.close();fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @desc 3.爬取歌词
	 * @author zcl
	 * @date 2019年4月23日
	 * @param id	歌曲id
	 * @return	歌词
	 */
	public static String spideLyric(String id){
		String lyricUrl="http://music.163.com/api/song/lyric?id="+id+"&lv=1&kv=1&tv=-1";
		Document doc=null;
		try {
			doc = Jsoup.connect(lyricUrl).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!doc.toString().contains("lyric")){
			return "请检查你输入的id";
		}
		String lyric=doc.toString();
		lyric=lyric.substring(lyric.indexOf("lyric\"")+8, lyric.indexOf("klyric")-4);//去掉其他json格式
		lyric=lyric.replace("\\n", "\n");//\n替换为\r
		try {
			lyric=new String(lyric.getBytes(),"utf-8");
			//System.out.println(lyric);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return lyric;
	}
	/**
	 * 
	 * @desc 4.歌词写入文件
	 * @author zcl
	 * @date 2019年4月7日
	 * @param lyric 歌词
	 * @param name  保存的文件名-也是资源id
	 */
	public static String writeToFile(String lyric,String name){
		System.out.println(lyric);
		File file=null;
		File dir=new File("F:/553Music");
		if(!dir.exists()){
			file=new File("/home/ubuntu/apache-tomcat-8.0.53/webapps/LongMusic/res/alyric/"+name+".txt");
		}else{
			file=new File("F:/553Music/lyric/"+name+".txt");
		}
		FileOutputStream fos;
		try {
			//歌词保存至文本文件
			file.createNewFile();
			fos = new FileOutputStream(file);
			fos.write(lyric.getBytes());
			fos.close();
			//歌词同步保存至数据库对应歌曲的相应字段
			JdbcUtil.getCon().executeUpdate("update song set lyric=\""+lyric+"\" where sourceId='"+name+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "已保存《"+name+"》的歌词至“"+file.getAbsolutePath()+"”文件";
	}
}
