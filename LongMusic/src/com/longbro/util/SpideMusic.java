package com.longbro.util;
import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 * 网易云音乐新歌榜、热歌榜歌曲爬取~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~已被网易云音乐废掉！
 * 使用JackSon解析json数据，取得其中的歌曲信息
 * -https://blog.csdn.net/taiyangdao/article/details/80668416
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年9月22日 下午5:31:14
 * @description
 * @version
 */
public class SpideMusic {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		for(int i=0;i<100;i++){
			ttt();
//		}
	}

	private static void ttt() {
		try {//ftype:23,status:42,transNames:7,type:32,publishTime:5,privilege:12
			//https://music.163.com/#/discover/toplist?id=3779629新歌榜
			//https://music.163.com/#/discover/toplist?id=19723756飙升榜
			Document doc=Jsoup.connect("https://music.163.com/m/discover/toplist?id=19723756").get();
			Elements es=doc.getElementsByTag("textarea");
			String songs=es.get(0).toString();
			songs=songs.replaceAll("<[.[^<]]*>", "");//<[.[^<]]*>
//			songs=songs.substring(songs.indexOf("display:none")+15, songs.indexOf("</textarea>"));
//			System.out.println(songs);
			String swhat=songs.substring(1, 18);
			System.out.println(swhat);
//			String []songsL=songs.split("\\},\\{");
//			for(int i=0;i<songsL.length;i++){
//				System.out.println(songsL[i]);
//			}

//			byFactory(songs);
			//使用ObjectMapper
//			ObjectMapper mapper = new ObjectMapper();
//			SongInfo infoMap = mapper.readValue(songs, SongInfo.class);
//			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void byFactory(String songs) throws IOException,
			JsonParseException {
		JsonFactory jf=new JsonFactory();
		JsonParser jp=jf.createJsonParser(songs);

		if (jp.nextToken() != JsonToken.START_OBJECT) {
		//	throw new IOException(...);
		}
		while(jp.nextToken() != JsonToken.END_OBJECT){
			String fieldName = jp.getCurrentName();
				System.out.println(fieldName);
			if ("name".equals(fieldName)){
				System.out.println(jp.getText());//jp.getText()
			}
			jp.nextToken();
		}
	}

}
