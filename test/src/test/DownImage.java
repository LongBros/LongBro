package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * 获取孟坤博客所有小表情的url路径，以用程序批量下载
 */
public class DownImage {
	public static void main(String[] args) throws IOException {
		String path="C:/Users/pc/Desktop/longbro/work/小表情/image.txt";//图片名称所在文件，待拆分逐个获取url
		//下载aru的图片
		String url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/aru/";
		int j=1;
		while(j<165){
			System.out.println(url+j+".png");
//			downImage(url+j+".png", "aru/"+j+".png");
			j++;
		}
		System.out.println("---------------------------------------------");
		//下载其他图片:qq,tieba,newtieba,weibo
		String lat="";//后缀名
		String folder="";
		byte b[]=new byte[9192];
		File file=new File(path);
		FileInputStream fis=new FileInputStream(file);
		fis.read(b);//将文件流读入字节数组
		String s=new String(b,"utf-8");//字节数组转为字符串
		String t[]=s.split("》");//拆分四大类型:qq,tieba,newtieba,weibo
		for(int i=0;i<4;i++){
			switch(i){
				case 0:
					url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/qq/";
					lat=".gif";
					folder="qq";
					break;
				case 1:
					url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/weibo/";
					folder="weibo";
					lat=".png";
					break;
				case 2:
					url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/newtieba/";
					folder="newtieba";
					lat=".png";
					break;
				default:
					url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/tieba/";
					folder="tieba";
					lat=".png";
					break;
			}
			/*if(i==0){
				url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/qq/";
				lat=".gif";
				folder="qq";
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
			}*/
			String ss[]=t[i].split(",");//拆分各个图片的name和value
			for(String st:ss){
				if(st.equals("")){
					return;
				}else{
					st=st.substring(st.indexOf(":")+1);
					st=st.replace("\"", "");
					String url1=url+st+lat;
					System.out.println(url1);
					if(folder.equals("qq")){
//						downImage(url1,folder+"/"+st+lat);
					}
				}
			}
			System.out.println("---------------------------------------------");
		}
		
	}
	/**
	 * 下载图片文件
	 * @date 2019年5月30日
	 * @time 下午4:50:59
	 * @author zcl
	 * @param url
	 * @param name
	 */
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
			System.out.println("下载成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
