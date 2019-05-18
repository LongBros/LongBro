<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<!-- 加载歌词 -->
<!-- 
1.仅显示所有歌词---只需在play函数中加入异步ajax即可
2.将当前播放的歌词特殊显示-需在monitor函数中加入ajax
     每秒执行一次ajax，将歌词中的当前播放的歌词前后各加上<font color='red'>和</font>
    亟待解决的问题是，有的歌词一首有好几句，如何只让播放的那句歌词特殊显示   
3.仅显示当前播放的歌词-需在monitor函数中加入ajax
     亟待解决的问题是，如何让当前播放的歌词停留的时间不是一秒，而是从开始唱这一句到这一句的结束？
  2018.11.09以上问题已通过cookie技术解决，但新的问题：歌词闪，因为歌词一秒改变一次
 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String name=request.getParameter("name");
System.out.println(name);
name=URLDecoder.decode(name,"utf-8");
String time=request.getParameter("time");
String type=request.getParameter("type");
if(type.equals("1")){//monitor函数中调用，当前播放歌词
	try{
		File file=new File("E:/AAAA/lyric/Time_"+name+".txt");
		if(!file.exists()){//说明是在云服务器上
			file=new File("/home/ubuntu/tomcat/webapps/Minimusic/lyric/Time_"+name+".txt");
		}
		//FileReader fr=new FileReader(file);
		//char s[]=new char[2048];
		byte[] s=new byte[2048];
		FileInputStream fis=new FileInputStream(file);
		int m=fis.read(s);
		String ly=new String(s,"utf-8");//所有歌词
		String l="";//最终为当前播放歌词
		if(ly.contains(time)){
			//把文件中与当前时间匹配的时间点前面的歌词截取出来
			l=ly.substring(0,ly.indexOf(time)+5);
			String ls[]=l.split("《");
			l=ls[ls.length-2];//当前播放的歌词
			if(l.contains(":")){
				l=l.substring(l.indexOf(":")+3);
			}
			//将这句词存入cookie以供下一句歌词出现之前显示
			Cookie cookie=new Cookie("lyric",URLEncoder.encode(l,"utf-8"));
			cookie.setMaxAge(1*60);
			response.addCookie(cookie);
			//ly=ly.replace(l, "<font color='red'>"+l+"</font>");
		}else{//如果歌词文件没有该时间点，显示利用cookie记住的歌词
			Cookie[] cs=request.getCookies();
			for(int i=0;i<cs.length;i++){
				if(cs[i].getName().toString().equals("lyric")){
					l=cs[i].getValue().toString();
					l=URLDecoder.decode(l,"utf-8");
				}
			}
		}
		out.write(l);
	}catch(Exception e){
		out.write("暂无单句歌词");
	}
}else{//type=2，play函数中调用，所有歌词
	//同时添加播放记录
	try{
		File file=new File("E:/AAAA/lyric/"+name+".txt");
		if(!file.exists()){
			file=new File("/home/ubuntu/tomcat/webapps/Minimusic/lyric/"+name+".txt");
		}
		//FileReader fr=new FileReader(file);
		//char s[]=new char[2048];
		byte[] s=new byte[6144];//注意要足够大以保证所有歌词正常显示
		FileInputStream fis=new FileInputStream(file);
		int m=fis.read(s);
		String ly=new String(s,"utf-8");//所有歌词
		
		//原本想用以下方法为文件每一行自动添加<br>标签，但是由于乱码问题失败了
		//String ss;
		//String ly="";
		//BufferedReader br=new BufferedReader(new FileReader(file));
		//while((ss=br.readLine())!=null){
		//	ly=ly+"<br>"+ss;
		//}
		//ly=new String(ly.getBytes(),"utf-8");
		//System.out.println(ly);
		out.println("<font size='-1' color='white'>"+ly+"</font>");
	}catch(Exception e){
		out.write("暂无歌词");
	}
}
%>