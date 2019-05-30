package test;
/**
 * 获取孟坤博客所有小表情的url路径，以用程序批量下载
 */
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

public class DownImage {
	public static void main(String[] args) throws IOException {
		String path="C:/Users/pc/Desktop/longbro/work/小表情/image.txt";
		
		String url="https://mkblog.cn/wp-content/themes/mkBlog/images/emoji/aru/";
		int j=1;
		while(j<165){
			System.out.println(url+j+".png");
//			downImage(url+j+".png", "aru/"+j+".png");
			j++;
		}
		System.out.println("---------------------------------------------");
		String lat="";//后缀名
		String folder="";
		byte b[]=new byte[9192];
		File file=new File(path);
		FileInputStream fis=new FileInputStream(file);
		fis.read(b);
		String s=new String(b,"utf-8");
		String t[]=s.split("》");
		for(int i=0;i<4;i++){
			if(i==0){
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
			}
			String ss[]=t[i].split(",");
			for(String st:ss){
				if(st.equals("")){
					return;
				}else{
					st=st.substring(st.indexOf(":")+1);
					st=st.replace("\"", "");
					String url1=url+st+lat;
					System.out.println(url1);
					if(folder.equals("qq")){
						downImage(url1,folder+"/"+st+lat);

					}
				}
			}
			System.out.println("---------------------------------------------");
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
			System.out.println("下载成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
