<%@page import="com.longbro.util.TimeUtil"%>
<%@page import="com.longbro.util.JdbcUtil"%>
<%@page import="com.longbro.util.Strings"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<!-- 加载歌词 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sid=request.getParameter("sid");//歌曲id
String time=request.getParameter("time");//时间点，仅加载单句歌词时有
String from=request.getParameter("from");//决定是否对播放次数加1
String l="";//最终为当前播放歌词
if(request.getParameter("type").equals("1")){//加载单句歌词
	if(sid.contains(".html")){//QQ音乐的歌词
		sid=sid.substring(0, sid.indexOf(".html"));
		try{
			File file=new File(Strings.imgPath+"alyric/"+sid+".txt");
			if(!file.exists()){
				file=new File("E:/AAAA/alyric/"+sid+".txt");
				if(!file.exists()){
					out.println("无歌词文件");
					return ;
				}
			}
			byte[] s=new byte[2048];
			FileInputStream fis=new FileInputStream(file);
			int m=fis.read(s);
			String ly=new String(s,"utf-8");//所有歌词
			if(ly.contains(time)){
				//把文件中与当前时间匹配的时间点前面的歌词截取出来
				l=ly.substring(0,ly.indexOf(time)+5);
				String ls[]=l.split("《");
				l=ls[ls.length-2];//当前播放的歌词
				if(l.contains(":")){
					l=l.substring(l.indexOf(":")+3);
				}
				//System.out.println(l);
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
		}catch(Exception e){
		}
	}else{//网易云
		File file=new File(Strings.imgPath+"alyric/"+sid+".txt");
		if(!file.exists()){
			file=new File("E:/AAAA/alyric/"+sid+".txt");
			if(!file.exists()){
				out.println("无歌词文件");
				return;
			}
		}
		try {
			//此处使用流没问题，若使用FileReader会乱码
			byte[] s=new byte[2048];
			FileInputStream fis=new FileInputStream(file);
			int m=fis.read(s);
			String lrc=new String(s,"utf-8");//所有歌词
			
		    if(lrc.contains(time)){//含有该时间点
		    	lrc=lrc.replaceAll("\\[", "<");
		    	lrc=lrc.replaceAll("]", ">");			
		        String ss[]=lrc.split("<[.[^<]]*>");//所有单句歌词
		    	ArrayList<String> times=new ArrayList<String>();
		    	times=Strings.getTime(lrc, times);//歌词的时间点
		    	for(int i=0;i<times.size();i++){
		   	    	if(times.get(i).contains(time)){
		   	    		l=ss[i+1];
		   	    		//将这句词存入cookie以供下一句歌词出现之前显示
		   				Cookie cookie=new Cookie("lyric",URLEncoder.encode(ss[i+1],"utf-8"));
		   				cookie.setMaxAge(1*60);
		   				response.addCookie(cookie);
		   	    	}
		    	}
		    }else{//如果歌词文件没有该时间点，显示利用cookie记住的歌词
		    	Cookie[] cs=request.getCookies();
				for(int i=0;i<cs.length;i++){
					if(cs[i].getName().toString().equals("lyric")){
						l=cs[i].getValue().toString();
						l=URLDecoder.decode(l,"utf-8");
					}
				}
		    }
		}catch(Exception e){
			
		}
	}
	if(l.length()>100){//较长时，使用流水灯
	    out.println("<marquee>"+l+"</marquee>");
	}else{
	    out.println(l);
	}
}else{//加载所有歌词
	//从js文件的play函数中进入时将当前歌曲播放次数加1
	if(from.equals("1")){
		String update="update song set playNum=playNum+1 where sourceId='"+sid+"'";
		JdbcUtil.getCon().executeUpdate(update);
		System.out.println(update);
		String insert="insert into play_record(songId,playTime) values('"+sid+"','"+TimeUtil.time()+"');";
		JdbcUtil.getCon().execute(insert);
		System.out.println(insert);

	}
	//加载QQ音乐或网易云音乐的所有歌词
	if(sid.contains(".html")){//QQ音乐的歌词
		sid=sid.substring(0, sid.indexOf(".html"));
		try{
			File file=new File("E:/AAAA/alyric/"+sid+".txt");
			if(!file.exists()){
				out.println("无歌词文件");
				return ;
			}
			byte[] s=new byte[2048];
			FileInputStream fis=new FileInputStream(file);
			int m=fis.read(s);
			String ly=new String(s,"utf-8");//所有歌词
			out.write(ly);
		}catch(Exception e){
		}
	}else{
		File file=new File("E:/AAAA/alyric/"+sid+".txt");
		if(!file.exists()){
			out.println("无歌词文件");
			return ;
		}
		try {
			//此处使用流没问题，若使用FileReader会乱码
			byte[] s=new byte[2048];
			FileInputStream fis=new FileInputStream(file);
			int m=fis.read(s);
			String lrc=new String(s,"utf-8");//所有歌词
			lrc=lrc.replaceAll("\\[", "<");
	    	lrc=lrc.replaceAll("]", ">");	
			lrc=lrc.replaceAll("<[.[^<]]*>", "</center><center>");
			System.out.println("<center>"+lrc+"</center>");
			out.write("<center>"+lrc+"</center>");
		}catch(Exception e){
			
		}
	}
}
%>