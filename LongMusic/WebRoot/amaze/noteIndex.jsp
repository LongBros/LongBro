<%@page import="com.longbro.util.Strings"%>
<%@page import="com.longbro.util.TimeUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>哆啦日记-不仅仅是怀旧</title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="../image/logo/dlam.jpg">
  <link rel="apple-touch-icon-precomposed" href="../assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="../assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="../assets/css/admin.css">
  <link rel="stylesheet" type="text/css" href="../hui/lib/Hui-iconfont/1.0.8/iconfont.css" />
  <link href="http://static.h-ui.net/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/myjs/player.js"></script>
  <style type="text/css">
  	Body { 
		scrollbar-arrow-color: #f4ae21; 
		scrollbar-face-color: #333; 
		scrollbar-3dlight-color: #666; 
		scrollbar-highlight-color: #666; 
		scrollbar-shadow-color: #999; 
		scrollbar-darkshadow-color: #666; 
		scrollbar-track-color: #666; 
	} 
  </style>
  
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->
          

<header class="am-topbar am-topbar-inverse admin-header">
  <div class="am-topbar-brand">
    <strong>哆啦日记</strong> <small>Doranote</small>
  </div>
  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>
  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-home"></span> 首页</a></li>
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 通知 <span class="am-badge am-badge-warning">5</span></a></li>
      
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-user"></span> 我 <span class="am-icon-caret-down"></span>
        </a>
        
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-file"></span> 写新日记</a></li>
          <li><a href="#"><span class="am-icon-user"></span> 我的日记</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 个人设置</a></li>
          <li><a onclick="oacRecommend('close')"><span class="am-icon-power-off"></span> 注销登录</a></li>
        </ul>
      </li>
      <li class="am-hide-sm-only">
	      <a href="javascript:;" id="admin-fullscreen">
	      <span class="am-icon-arrows-alt"></span> 
	      <span class="admin-fullText">开启全屏</span>
	      </a>
      </li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">


</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>
<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="../assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="../assets/js/amazeui.min.js"></script>
<script src="../assets/js/app.js"></script>
</body>
</html>
