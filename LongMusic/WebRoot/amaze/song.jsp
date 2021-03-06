<%@page import="com.longbro.util.Strings"%>
<%@page import="com.longbro.util.TimeUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id=request.getParameter("id");
int sId=Integer.parseInt(id);
%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>553Music</title>
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
  <script type="text/javascript" src="../js/player.js"></script>
  <script type="text/javascript" src="../js/plist.js"></script>
  <script type="text/javascript" src="../js/timeDeal.js"></script>
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
<body id="body" background="https://y.gtimg.cn/music/photo_new/T002R300x300M000001BA0x61Hh7AR.jpg?max_age=2592000">
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header">
  <div class="am-topbar-brand">
    <strong>LongBro工具之</strong> <small>553音乐</small>
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
        <li><a href=""><span class="am-icon-home"></span> 首页</a></li>
        
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>账单展示 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
            <li><a href="showAccount.jsp" class="am-cf"><span class="am-icon-check"></span> 列表展示<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
            <li><a href="" target="_blank"><span class="am-icon-puzzle-piece"></span> 条形图分析</a></li>
            <li><a href="analysis.jsp" target="_blank"><span class="am-icon-th"></span> 收支汇总<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
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
        <div class="am-fl am-cf">
	        <strong class="am-text-primary am-text-lg">音乐列表</strong> / 
	        <small>553音乐</small>&emsp;
	        <span style="color: red" id="countDown">86400</span>s后暂停音乐&emsp;&emsp;&emsp;
	        <span id="timer">24:00:00</span>
	       	 当前时间：<span id="nowTime"></span>
        </div>
			 <div class="am-form-group">
		                      定时关歌:<select data-am-selected="{btnSize: 'sm'}" id="searCate" onchange="setCount(options[selectedIndex].value)">
		              <option value="5">5分钟</option>
		              <option value="10">10分钟</option>
		              <option value="15">15分钟</option>
		              <option value="20">20分钟</option>
		              <option value="25">25分钟</option>
		              <option value="30">30分钟</option>
		              <option value="35">35分钟</option>
		              <option value="40">40分钟</option>
		              <option value="45">45分钟</option>
		              <option value="50">50分钟</option>
		              <option value="55">55分钟</option>
		              <option value="60">60分钟</option>
		              
		            </select>
          		</div>
          </div>
         
      <hr>

      <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
          <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default" onclick="showAdd()"><span class="am-icon-plus"></span> 新增</button>
              <button type="button" class="am-btn am-btn-default" onclick="addSong()"><span class="am-icon-save"></span> 保存</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>
              <button type="button" class="am-btn am-btn-default" onclick="clearAcc()"><span class="am-icon-trash-o"></span> 清空</button>
            </div>
          </div>
        </div>
      </div>
      <!-- <div id="insertSong" style="position:fixed;overflow:scroll;background:gray;right:450px;top:200px;width:220px;height:350px;display: none">
		 <span onclick="closes()" style="color:white;margin-left: 180px;">X</span>
		 新增歌曲 -->
		  
		  <form id="form" style="display:none">
		  		<input type="text" name="sourceId" placeholder="资源id">
		  		<input type="text" name="songName" placeholder="歌曲名">
		  		<input type="text" name="singer" placeholder="歌手">
		  		<input type="text" name="duration" placeholder="时长">
		  		<input type="text" name="album" placeholder="专辑">
		  		<input type="text" name="imgPath" placeholder="图片路径">
		  		<input type="text" name="releaseTime" placeholder="发行时间">
		  		<select name="website">
		  			<option value="网易云音乐">网易云音乐</option>
		  			<option value="QQ音乐">QQ音乐</option>
		  			<option value="酷我音乐">酷我音乐</option>
		  		</select>
		  		<input type="text" name="descr" placeholder="描述">
		  		<input type="text" name="time" value="<%=TimeUtil.time()%>">
		  </form>
		  <!-- </div> -->
         <br>
        <div class="am-u-sm-12 am-u-md-3">
          <div class="am-input-group am-input-group-sm">
              <input type="text" id="key" placeholder="搜一搜，听你想听" class="am-form-field">
	          <span class="am-input-group-btn">
	            	<button class="am-btn am-btn-default" type="button" onclick="strongQuerySongs()">强力搜索</button>
	          </span>
	          <!-- <span class="am-input-group-btn">
	            	<button class="am-btn am-btn-default" type="button" onclick="querySongsByKey()">歌曲</button>
	          </span>
	          <span class="am-input-group-btn">
	               <button class="am-btn am-btn-default" type="button" onclick="querySongsBySinger()">歌手</button>
	          </span> -->
          </div>
        </div>
        <!-- 新建歌单 -->
        <form id="newList" name="newList">
            	<span onclick="closes()" style="color:white;margin-left: 180px;">X</span>
            	歌单名:<input name="songlist">
            	歌单描述:<input name="listdesc">
            	<button onclick="create()">建单</button>
        </form>
	  <div class="am-cf">
	  	  <div class="am-fr"> 
	  	  	  <select id="songList" onchange="querySongList(options[selectedIndex].value)">
	  	  	  		<option>歌单</option>
	  	  	  </select>
	  	  </div>
	  		<center>
	  	  	<%
	  	  	for(int i=1;i<=11;i++){
          		out.write("<a onclick='querySongs("+i+")'>&emsp;"+i+"</a>");
          	}
	  	  	  %> 
	  	  	</center>  
	  	  <%-- <div class="am-fr">
              <select onchange="querySongs(options[selectedIndex].value)">
              <option value='0'>页码</option>
              	<%
              	for(int i=1;i<=11;i++){
              		out.write("<option value='"+i+"'>&emsp;"+i+"</option>");
              	}
               %>
              </select>
          </div> --%>
      </div>
      <div class="am-g">
        <div class="am-u-sm-12">
          <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
              <thead>
              <tr><!-- <th class="table-id">ID</th> -->
                <th class="table-check"><input type="checkbox" id="songscheck" onclick="changed()"/></th><th class="table-id">ID</th><th class="table-payutil">歌曲名</th>
                <th class="table-inout">歌手</th><th class="table-cate am-hide-sm-only">时长</th><th class="table-amount am-hide-sm-only">专辑</th>
                <th class="table-remark am-hide-sm-only">发行时间</th><th class="table-time am-hide-sm-only">源网站</th><th class="table-set">操作</th>
              </tr>
              </thead>
              
              <tbody id="song">
               
              </tbody>
            </table>
           	 <div id="nums"></div>
			  <audio id="audio" style="display:none;" controls="controls"
  			 		src="http://music.163.com/song/media/outer/url?id=486814412.mp3">
			  </audio>
            
            <hr/>
            <!-- 单句歌词 -->
            <center>
            	<img alt="" id="singer" src="../image/singer2.jpg" style="height:100px;position:fixed;top:90px;right:306px;">
            	<span id="lyric" style="color: green;font-size:35px;position:fixed;top:110px;right:446px;"></span>
            </center>
            <!-- 点击歌添加歌曲至歌单 -->
            <div id="addSong" style="position:fixed;overflow:scroll;background:gray;right:450px;top:200px;width:220px;height:350px;display: none">
            	<span style="display: none" id="id"></span>
            	<span onclick="closes()" style="color:white;margin-left: 180px;">X</span>
            	<span onclick="newList()">新建歌单</span><br>
            	添加<span id="songName"></span>至<br>
            	<ul id="songLists">
            		<li onclick="addToList('0')">播放列表</li>
            	</ul>
            </div>
            <!-- 点击歌添加歌曲至歌单 -->
            <script type="text/javascript">
			  		$(function(){
			  			querySongs(1);
				   });
			  		//加载歌单
			  		$.ajax({
			  			type:"Get",
			  			async:false,
			  			url:"../querySongList.do",
			  			dataType:"Json",
			  			success:function(data){
			  				for(var k=0;k<data.length;k++){
			  					$('#songList').append("<option value='"+data[k].songs+"】"+data[k].name+"】"+data[k].id+"'>"+data[k].name+"</option>");
			  					$('#songLists').append("<li onclick=\"addToList('"+(k+1)+"')\">"+data[k].name+"</li>");
			  				}
			  			}
			  		});
			  </script>
            
            <div style="height:300px;position:fixed;"><!-- 播放列表，全部歌词，及其他工具 -->
            	<!-- 播放列表，全部歌词 -->
            	<div id="plistAalrc" style="background:gray;position:fixed;bottom:40px;cursor: pointer;display:none;margin-left: 5px;">
	          	 <div id="plist" style="color:white;background-image:url(../image/artist/me080502.jpg);background-size:240px 250px; margin-left:20px;width:500px;height:250px;overflow:scroll;overflow-x:hidden;float:left">
	          	 	 <table>
	          	 	 	
	          	 	 </table>
	          	 </div>
	          	 <div id="alyric" style="color:white;margin-left:20px;width:500px;height:250px;overflow:scroll;overflow-x:hidden;float:right"></div>
       		  </div>
       		  <!-- 播放列表，全部歌词 -->
       		  <!-- 其他工具：上一曲，下一曲，播放暂停，进度条，音量加减，播放列表显示与隐藏按钮 -->
				<div id="bottom" style="background: gray;position:fixed;bottom:0; left:260px;width:80%;height:40px;">
					<img style="width: 40px;height: 40px" title="左键--上一曲" onclick="preview()" alt="" src="../image/play_previous.png">&emsp;
					<img style="width: 40px;height: 40px" title="P键--暂停/播放" id="pause" alt="" onclick="pause_play()" src="../image/play.png">&emsp;
				    <img style="width: 40px;height: 40px" title="右键--下一曲" onclick="next()" alt="" src="../image/play_next.png">
					<img id="mode" title="顺序播放--C键切换" style="width:50px;height:40px;" src="../image/play_random.png" onclick="change()">
					<progress title="A键---快退10秒,D键---快进10秒;Q键---快退5秒,E键---快进5秒" style="width:566px;height:10px" draggable="false" id="pro" value="0" max="100"></progress>
					<span id="time" class="time" title="已播放/总时长"></span>
					<a onclick="minus()" class="minus" title="下键--音量减">一</a>
					<progress style="width:100px;" draggable="false" id="voice" value="100" max="100"></progress>
					<a onclick="add()" class="add" title="上键--音量加">✚</a>
					<span onclick="showHide()" title="Ctrl+shift+S" id="sah">显示</span>
				</div>
			</div><!-- 播放列表，歌词，及其他工具 -->
          </form>          
        
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
