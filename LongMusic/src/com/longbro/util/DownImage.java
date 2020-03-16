package com.longbro.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * 批量下载孟坤博客收录的各大表情包
 * @author LongBro
 * @date 2019年9月19日
 * @time 下午2:02:50
 */
public class DownImage {
	public static void main(String[] args) throws IOException {
//		spideMk();
		String url="http://www.17419.vip/wp-content/themes/LightSNS_1.6.14/images/member_bg/";
		for(int i=10;i<20;i++){
			downImage(url+""+i+"_thum.jpg","background"+i+".jpg");
		}
	}

	private static void spideMk() throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		String path="D:/aaaaaaa/LongBro/LongMusic/src/小表情/image.txt";
		
		String url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/aru/";
		int j=1;
		while(j<165){
//			System.out.println(url+j+".png");
//			downImage(url+j+".png", "aru/"+j+".png");
			j++;
		}
//		System.out.println("---------------------------------------------");
		String lat="";//后缀
		String folder="";//文件夹
		byte b[]=new byte[9192];
		File file=new File(path);
		FileInputStream fis=new FileInputStream(file);
		fis.read(b);
		String s=new String(b,"utf-8");
		String t[]=s.split("》");//分离表情类型，比如QQ表情、微博表情、贴吧表情
		for(int i=0;i<4;i++){
			if(i==0){
				url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/qq/";
				lat=".gif";
				folder="qq";
				String ss[]=t[i].split(",");//分离某个类型的所有单个表情
				for(String st:ss){
					if(st.equals("")){
						return;
					}else{
						st=st.substring(st.indexOf(":")+1);
						st=st.replace("\"", "");
						String url1=url+st+lat;
						System.out.println(st+lat+"下载成功");
						if(folder.equals("qq")){
							downImage(url1,folder+"/"+st+lat);
						}
					}
				}
			}else if(i==1){
				url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/weibo/";
				folder="weibo";
				lat=".png";
			}else if(i==2){
				url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/newtieba/";
				folder="newtieba";
				lat=".png";
			}else{
				url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/tieba/";
				folder="tieba";
				lat=".png";
			}
			
//			System.out.println("---------------------------------------------");
		}
	}
	
	public static void downImage(String url,String name){
		try {
			URL u=new URL(url);
			HttpURLConnection con=(HttpURLConnection) u.openConnection();
			DataInputStream dis=new DataInputStream(con.getInputStream());
			
			byte[] b=new byte[dis.available()];
			int len=dis.available();
			File file=new File("D:/long/"+name);
			file.createNewFile();
			FileOutputStream fos=new FileOutputStream(file);
			DataOutputStream dos=new DataOutputStream(fos);
			int count=0;
			while((count=dis.read(b))>0){
				fos.write(b,0,count);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
