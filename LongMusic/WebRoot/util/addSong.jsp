<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>向数据库添加歌曲</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
      <form action="<%=path%>/addSong.do" method="post">
      		资源id<input type="text" name="sourceId"><br>
       		歌曲名<input type="text" name="songName"><br>
       		歌手<input type="text" name="singer"><br>
       		时长<input type="text" name="duration"><br>
       		专辑<input type="text" name="album"><br>
       		图片路径<input type="text" name="imgPath"><br>
       		发行时间<input type="text" name="releaseTime"><br>
       		来源<select name="website">
    					<option value="网易云音乐">网易云音乐</option>
    					<option value="QQ音乐">QQ音乐</option>
    		   </select><br>
       		描述<input type="text" name="desc"><br>
       		<input type="submit" value="添加">
       </form>
  </body>
</html>
