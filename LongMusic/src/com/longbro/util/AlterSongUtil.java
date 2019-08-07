package com.longbro.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 1.批量重命名歌曲文件，由歌曲名改为歌曲资源id命名
 * 2.批量将歌词文件中歌词录入数据库中对应歌词字段
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年8月2日 下午11:12:28
 * @description
 * @version
 */
public class AlterSongUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		alterSonName();
		saveLyricToDb();
	}

	private static void saveLyricToDb() {
		//将歌词文件中的歌词，批量修改至数据库中对应的歌词字段
		try {
			ResultSet rs=JdbcUtil.getCon().executeQuery(
					"select sourceId,songName from song where id>152");
			while(rs.next()){
				byte b[]=new byte[4096];
				String sourceId=rs.getString("sourceId");
				File file=new File("E:/AAAA/alyric/"+sourceId+".txt");
				if(file.exists()){//有该网易云音乐歌的歌词，加载出来保存至数据库
//					FileInputStream fis=new FileInputStream(file);
//					fis.read(b);
//					String lyric=new String(b);
//					System.out.println("update song set lyric='"+lyric+"' where sourceId="+sourceId);
//					JdbcUtil.getCon().executeUpdate("update song set lyric=\""+lyric+"\" where sourceId='"+sourceId+"'");
				}else if(sourceId.contains(".html")){//QQ音乐
					file=new File("E:/AAAA/alyric/"+sourceId.substring(0, sourceId.indexOf(".html"))+".txt");
					System.out.println("E:/AAAA/alyric/"+sourceId.substring(0, sourceId.indexOf(".html"))+".txt");
					if(file.exists()){
						FileInputStream fis=new FileInputStream(file);
						fis.read(b);
						String lyric=new String(b);
						JdbcUtil.getCon().executeUpdate("update song set lyric=\""+lyric+"\" where sourceId='"+sourceId+"'");
						System.out.println(rs.getString("songName")+"歌词已存入至数据库");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void alterSonName() {
		try {
			ResultSet rs=JdbcUtil.getCon().executeQuery(
					"select sourceId,songName from song");
			while(rs.next()){
				String sourceId=rs.getString("sourceId");
				String songName=rs.getString("songName");
				File file=new File("D:/apache-tomcat-8.5.35/webapps/util/songs/"+
						songName+".mp3");
				if(sourceId.contains(".html")){//QQ音乐
					System.out.println(sourceId.substring(0, sourceId.indexOf(".html"))+".mp3");
					if(file.exists()){//有该音乐，重命名为资源id的
						file.renameTo(new File(
								"D:/apache-tomcat-8.5.35/webapps/util/songs/"+
						sourceId.substring(0, sourceId.indexOf(".html"))+".mp3"));
					}
				}else if(sourceId.contains(".kw")){//酷我音乐
					if(file.exists()){//有该音乐，重命名为资源id的
						file.renameTo(new File(
								"D:/apache-tomcat-8.5.35/webapps/util/songs/"+sourceId+".mp3"));
					}
				}else if(sourceId.contains("http:")){//其他音乐
				}else{//网易云音乐
					if(file.exists()){//有该音乐，重命名为资源id的
						file.renameTo(new File(
								"D:/apache-tomcat-8.5.35/webapps/util/songs/"+sourceId+".mp3"));
					}
				}
				System.out.println("已重命名"+file.getName()+"为"+sourceId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
