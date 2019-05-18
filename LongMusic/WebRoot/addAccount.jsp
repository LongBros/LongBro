<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>记账</title>
    
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
      <form action="<%=path%>/addBill.do" method="post">
      		时间<input type="text" name="time"><br>
       		支出/收入方式<input type="text" name="payutil"><br>
       		支出/收入<select name="in_out">
    					<option value="pay">支出</option>
    					<option value="income">收入</option>
    		   </select><br>
       		账单分类<input type="text" name="cate"><br>
       		金额<input type="text" name="amount"><br>
       		备注<input type="text" name="remark"><br>
       		图片<input type="text" name="picture"><br>
       		
       		<input type="submit" value="添加">
       </form>
  </body>
</html>
