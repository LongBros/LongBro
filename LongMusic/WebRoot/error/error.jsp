<%@page import="com.longbro.util.OtherUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客官，服务器崩啦！</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <link rel="shortcut icon" href="/LongBlog/images/useful/logo2.png" type="image/x-icon"/>

   	<style type="text/css">
	   .name{
	       margin-top:200px;
	       font-size:88px;
	       font:italic bold  arial,Comic Sans;
	   }
	   .notfound{
	       margin-top:20px;
	       font-size:46px;
	       font-family:"仿宋_GB2312","Arial Narrow",HELVETICA;background:#fff;
	   }
	   .skip{
	       margin-top:10px;
	       font-size:30px;
	       font-style: italic;
	   }
	   .skip a{
	   	   text-decoration: none;
	   }
	</style>
  </head>
  
  <body>
    <center>
      <div class="name"><%=OtherUtil.web%></div>
      <div class="notfound"><%=OtherUtil.err %><img src="/LongBlog/images/aodamiao/shangxin.gif" style="width: 28px;height: 28px;"></div>
      <div class="skip"><a href="http://www.longqcloud.cn"><%=OtherUtil.skip%></a></div>
    </center>  
  </body>
</html>
