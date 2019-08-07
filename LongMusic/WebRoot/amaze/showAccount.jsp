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
  <title>我的账单</title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="../assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="../assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="../assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="../assets/css/admin.css">
  <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/account.js"></script>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header">
  <div class="am-topbar-brand">
    <strong>LongBro工具之</strong> <small>智能账本</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
          <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
      <ul class="am-list admin-sidebar-list">
        <li><a href="admin-index.html"><span class="am-icon-home"></span> 首页</a></li>
        <!-- <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 页面模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
            <li><a href="admin-user.html" class="am-cf"><span class="am-icon-check"></span> 个人资料<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
            <li><a href="admin-help.html"><span class="am-icon-puzzle-piece"></span> 帮助页</a></li>
            <li><a href="admin-gallery.html"><span class="am-icon-th"></span> 相册页面<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
            <li><a href="admin-log.html"><span class="am-icon-calendar"></span> 系统日志</a></li>
            <li><a href="admin-404.html"><span class="am-icon-bug"></span> 404</a></li>
          </ul>
        </li> -->
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>账单展示 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
            <li><a href="showAccount.jsp" class="am-cf"><span class="am-icon-check"></span> 列表展示<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
            <li><a href="analysis.jsp"><span class="am-icon-th"></span> 收支汇总<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
            <li><a href=""><span class="am-icon-puzzle-piece"></span> 条形图分析</a></li>
            
            <li><a href=""><span class="am-icon-calendar"></span> 饼状图分析</a></li>
          </ul>
        </li>
        <li><a href="showCate.jsp"><span class="am-icon-table"></span> 分类管理</a></li>
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#collapse-nav1'}"><span class="am-icon-file"></span>其他 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav1">
            <li><a href="showAccount.jsp" class="am-cf"><span class="am-icon-check"></span> 借钱情况<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
            <li><a href="songsList.jsp" target="_blank"><span class="am-icon-calendar"></span> 553音乐</a></li>
            <li><a href=""><span class="am-icon-puzzle-piece"></span> 友人生日</a></li>
            <li><a href=""><span class="am-icon-th"></span> 托管赚钱<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
            <li><a href=""><span class="am-icon-calendar"></span> 我的记录</a></li>
            <li><a href=""><span class="am-icon-calendar"></span> 淘宝刷单</a></li>
          </ul>
        </li>
        
      </ul>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-bookmark"></span> 公告</p>
          <p>时光静好，与君语；细水流年，与君同。—— Amaze UI</p>
        </div>
      </div>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-tag"></span> wiki</p>
          <p>Welcome to the Amaze UI wiki!</p>
        </div>
      </div>
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">账单列表</strong> / <small>新增账单</small></div>
      </div>

      <hr>

      <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
          <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default" onclick="showAdd()"><span class="am-icon-plus"></span> 新增</button>
              <button type="button" class="am-btn am-btn-default" onclick="addAcc()"><span class="am-icon-save"></span> 保存</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span>删除</button>
              <button type="button" class="am-btn am-btn-default" onclick="clearAcc()"><span class="am-icon-trash-o"></span>清空</button>
            </div>
          </div>
        </div>
      </div>
      <br>
      <div>
		  <form id="form" style="display: none">
		  		&nbsp;&emsp;<select name="payutil">
		  			<option value="微信钱包">微信钱包</option>
		  			<option value="支付宝">支付宝</option>
		  			<option value="现金">现金</option>
		  		</select>
		  		<select name="in_out" onchange="setCate('add')">
		  			<option value="支出">支出</option>
		  			<option value="收入">收入</option>
		  		</select>
		  		<select name="category" id="category">
		  		<%
	               for(int i=0;i<Strings.category.length;i++){
	            	   out.write("<option value='"+Strings.category[i]+"'>"+Strings.category[i]+"</option>");
	               }
	              %>
	            </select>  
		  		<input type="text" name="amount" placeholder="金额">
		  		<input type="text" name="remark" placeholder="备注">
		  		<input type="text" name="time" value="<%=TimeUtil.time()%>">
		  </form>
	  </div><br>
	    <div class="am-u-sm-12 am-u-md-3">
          <div class="am-form-group">
           工具:<select data-am-selected="{btnSize: 'sm'}" id="payutil">
              <option value="请选择">请选择</option>
              <option value="微信钱包">微信钱包</option>
              <option value="支付宝">支付宝</option>
              <option value="现金">现金</option>
            </select>
          </div>
        </div>
	   <div class="am-u-sm-12 am-u-md-3">
          <div class="am-form-group">
             收支:<select data-am-selected="{btnSize: 'sm'}" id="searCate" onchange="setCate('search')">
              <option value="请选择">请选择</option>
              <option value="支出">支出</option>
              <option value="收入">收入</option>
            </select>
          </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">
          <div class="am-form-group">
            分类:<select data-am-selected="{btnSize: 'sm'}" id="category1">
              	<option>请选择</option>
              <%
               for(int i=0;i<Strings.category.length;i++){
            	   out.write("<option value='"+Strings.category[i]+"'>"+Strings.category[i]+"</option>");
               }
              %>
            </select>
          </div>
        </div>
        <select id="year">
        	<%
               for(int i=2019;i>1995;i--){
            	   out.write("<option value='"+i+"'>"+i+"</option>");
               }
              %>
        </select>
        <select id="month">
        	<option value='请选择'>请选择</option>
        	<%
               for(int i=1;i<13;i++){
            	   if(i<10){
                	   out.write("<option value='0"+i+"'>0"+i+"</option>");
            	   }else{
                	   out.write("<option value='"+i+"'>"+i+"</option>");
            	   }
               }
              %>
        </select>
        
        <div class="am-u-sm-12 am-u-md-3">
          <div class="am-input-group am-input-group-sm">
            <input type="text" id="key" class="am-form-field">
          <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button" onclick="queryBillBy()">搜索</button>
          </span>
          </div>
        </div>
	  
      <div class="am-g">
        <div class="am-u-sm-12">
          <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
              <thead>
              <tr><!-- <th class="table-id">ID</th> -->
                <th class="table-check"><input type="checkbox"/></th><th class="table-id">ID</th><th class="table-payutil">支收方式</th><th class="table-inout">支收</th><th class="table-cate am-hide-sm-only">分类</th><th class="table-amount am-hide-sm-only">金额</th><th class="table-remark am-hide-sm-only">备注</th><th class="table-time am-hide-sm-only">时间</th><th class="table-set">操作</th>
              </tr>
              </thead>
              <script type="text/javascript">
			  		$(function(){
			  			queryBill(1);
				   });
			  </script>
              <tbody id="bill">
               
              </tbody>
            </table>
            <div class="am-cf">
              共 n条记录
              
              
              <div class="am-fr">
              <select onchange="queryBill(options[selectedIndex].value)">
              <option value='0'>页码</option>
              	<%
              	for(int i=1;i<40;i++){
              		out.write("<option value='"+i+"'>&emsp;"+i+"</option>");
              	}
               %>
              </select>
                <ul class="am-pagination">
                
                 
                </ul>
              </div>
            </div>
            <hr />
            <p>注：.....</p>
          </form>
        </div>

      </div>
    </div>

    <footer class="admin-content-footer">
      <hr>
      <p class="am-padding-left">© 2018 LongBro, Inc. Licensed under Tencent Cloud.</p>
    </footer>

  </div>
  <!-- content end -->
</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<footer>
  <hr>
  <p class="am-padding-left">© 2018 LongBro, Inc. Licensed under Tencent Cloud.</p>
</footer>

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
