<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  		function refresh(img){
  			img.src=img.src+"?"+new Date().getTime();
  			//alert(img.src)
  		}
  </script>
  <body>
		<form action="<%=path%>/register.do" method="post">
       		账号<input type="text" name="account" placeholder="英文字符或数字"><br>
       		密码<input type="text" name="password"><br>
       		验证码<input type="text" name="vcode"><img onclick="refresh(this)" src="util/validatecode.jsp"><br>
       		<input type="submit" value="注册">
       </form>  
   </body>
</html>
