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
  <title>折线图</title>
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
  <script type="text/javascript" src="../js/amazeSong.js"></script>
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
        
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>账单展示 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
            <li><a href="showAccount.jsp" class="am-cf"><span class="am-icon-check"></span> 列表展示<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
            <li><a href=""><span class="am-icon-puzzle-piece"></span> 条形图分析</a></li>
            <li><a href="linecharts.jsp" target="_blank"><span class="am-icon-th"></span> 线形图分析<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
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
          <p>时光静好，与君语；细水流年，与君同。—— LongBro</p>
        </div>
      </div>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-tag"></span> wiki</p>
          <p>Welcome to the LongBro工具!</p>
        </div>
      </div>
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">合计分析</strong> / <small>账单合计</small></div>
      </div>

      <hr>
        
      <div class="am-g">
        <div class="am-u-sm-12">            
              <script type="text/javascript" src="../js/highcharts.js"></script>
					<script type="text/javascript" src="../js/jquery.js"></script>
					<script type="text/javascript">
						var time="";
						var in_="";
						var out="";
						var earn="";
						$(function(){
							load('year');
						});
						function load(yom){
							$('#song').text('');
							$.ajax({
								type:"GET",
								url:"../getAmountByYoM.do?yom="+yom,
								async:false,
								dataType:"Json",
								success:function(data){
									for(var i=(data.length-1);i>0;i--){
										$('#song').append("<tr>");
										$('#song').append("<td>"+data[i].yom+"</td>");
										$('#song').append("<td>"+data[i].in_+"</td>");
										$('#song').append("<td>"+data[i].out+"</td>");
										$('#song').append("<td>"+data[i].earn+"</td>");
										$('#song').append("</tr>");
										//alert(data[i].earn);
										//in_=in_+",'"+data[i].in_+"'";
										//time=time+",'"+data[i].yom+"'";
										//out=out+",'"+data[i].out+"'";
										//earn=earn+",'"+data[i].earn+"'";
									}
									//time="["+time.substring(1)+"]";
									//in_="["+in_.substring(1)+"]";//去除第一个,并在前后添加[和]
									//out="["+out.substring(1)+"]";//去除第一个,
									//earn="["+earn.substring(1)+"]";
									//alert(time);//3200,3200,7400,6200,9800,14257,15117.01,18579.00,5264.58
									//alert(out);
									//alert(earn);//-2900.0,3800.0,-7400.0,-6200.0,-9800.0,-13755.5,-12338.51,-9588.34,-1556.19
								}
							});
						}
						
					</script>
					<input type="radio" name="yom" onclick="load('year')" checked="checked">年
					<input type="radio" name="yom" onclick="load('month')">月
					<table border="1">
						<thead>
							<tr>
								<th>月/年</th>
								<th>收入</th>
								<th>支出</th>
								<th>净收</th>
							</tr>
						</thead>
						<tbody id="song">
							
						</tbody>
					</table>
					
					<div id="cont">
						
						
					</div>    
					<script type="text/javascript">
						Highcharts.chart('container', {
						    title: {
						        text: '账单统计'
						    },
						
						    subtitle: {
						        text: '数据来源: www.zy52113.com'
						    },
						    xAxis: {
					            categories: time
					        },
			
						    yAxis: {
						        title: {
						            text: out
						        }
						    },
						    legend: {
						        layout: 'vertical',
						        align: 'right',
						        verticalAlign: 'middle'
						    },
						
						    /* plotOptions: {
						        series: {
						            label: {
						                connectorAllowed: false
						            },
						            pointStart: 1
						        }
						    },
						 */
						    series: [{
						        name: '访问IP数量',
						        data: out
						    }],
						
						    responsive: {
						        rules: [{
						            condition: {
						                maxWidth: 500
						            },
						            chartOptions: {
						                legend: {
						                    layout: 'horizontal',
						                    align: 'center',
						                    verticalAlign: 'bottom'
						                }
						            }
						        }]
						    }
						
						});
					</script>        
            <hr/>
           
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
