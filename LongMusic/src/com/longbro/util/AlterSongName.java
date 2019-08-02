package com.longbro.util;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 批量重命名歌曲文件，由歌曲名改为歌曲资源id命名
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年8月2日 下午11:12:28
 * @description
 * @version
 */
public class AlterSongName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
