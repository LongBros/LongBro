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
  <title>553Music</title>
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
  <script type="text/javascript" src="../js/myjs/comment.js"></script>
  <script type="text/javascript" src="../js/myjs/plist.js"></script>
  <script type="text/javascript" src="../js/myjs/timeDeal.js"></script>
   <script src="../scripts/boot.js" type="text/javascript"></script>
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
          <li style="display:none" id="closeBtn"><a onclick="oacRecommend('close')"><span class="am-icon-power-off"></span> 关闭随机推荐</a></li>
          <li style="display:inline-block" id="openBtn"><a onclick="oacRecommend('open')"><span class="am-icon-power-off"></span> 开启随机推荐</a></li>
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
        <li><a href=""><span class="am-icon-home"></span>首页</a></li>
        
        <li class="admin-parent">
          <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>账单展示 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
            <li><a href="showAccount.jsp" target="_blank" class="am-cf"><span class="am-icon-check"></span> 列表展示<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
            <li><a href="" target="_blank"><span class="am-icon-puzzle-piece"></span> 条形图分析</a></li>
            <li><a href="analysis.jsp" target="_blank"><span class="am-icon-th"></span> 收支汇总<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
            <li><a href=""><span class="am-icon-calendar"></span> 饼状图分析</a></li>
          </ul>
        </li>
        <li><a href="showCate.jsp"><span class="am-icon-table"></span> 日记本</a></li>
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
	             <br> 随机生成播放列表:<select data-am-selected="{btnSize: 'sm'}" onchange="randomPList(options[selectedIndex].value)">
		              <option>选择歌曲数量</option>
		              <option value="5">5首</option>
		              <option value="10">10首</option>
		              <option value="15">15首</option>
		              <option value="20">20首</option>
		              <option value="25">25首</option>
		              <option value="30">30首</option>
		              <option value="35">35首</option>
		              <option value="40">40首</option>
		              <option value="45">45首</option>
		              <option value="50">50首</option>
		            </select>
          		</div>
          		热歌榜:<select data-am-selected="{btnSize: 'sm'}" onchange="loadHotSongs(options[selectedIndex].value)">
		              <option>选择热歌数量</option>
		              <option value="5">榜5首</option>
		              <option value="10">榜10首</option>
		              <option value="15">榜15首</option>
		              <option value="20">榜20首</option>
		              <option value="25">榜25首</option>
		              <option value="30">榜30首</option>
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
              <input type="text" id="key" placeholder="搜一搜，听你想听" onchange="search()" class="am-form-field">
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
        <form id="newList" name="newList" style="display:none;background: gray;width:250px;margin-left: 400px;margin-top:120px;position:fixed;">
            	<ul>
            		<span onclick="closes()" style="color:white;margin-left: 180px;">X</span><br>
	            	歌单名:<input name="songlist"><br>
	            	歌单描述:<input name="listdesc"><br>
	            	<span style="background: cyan" onclick="create()">建单</span>
            	</ul>
            	
        </form>
	  <div class="am-cf">
	  	  <div class="am-fr"> 
	  	  	  <select id="songList" onchange="querySongList(options[selectedIndex].value)">
	  	  	  		<option>歌单</option>
	  	  	  </select>
	  	  </div>
	  		<center>
	  	  	<%
	  	  	for(int i=1;i<=13;i++){
          		out.write("<a onclick='querySongs("+i+")'>&emsp;"+i+"</a>");
          	}
	  	  	  %> 
	  	  	</center>  
	  	  <%-- <div class="am-fr">
              <select onchange="querySongs(options[selectedIndex].value)">
              <option value='0'>页码</option>
              	<%
              	for(int i=1;i<=13;i++){
              		out.write("<option value='"+i+"'>&emsp;"+i+"</option>");
              	}
               %>
              </select>
          </div> --%>
      </div>
      <div class="am-g">
        <div class="am-u-sm-12">
          <form class="am-form">
            <table id="songsList" class="am-table am-table-striped am-table-hover table-main">
              <thead>
              <tr><!-- <th class="table-id">ID</th> -->
                <th class="table-check"><input type="checkbox" id="songscheck" onclick="changed()"/></th><th class="table-id">ID</th><th class="table-payutil">歌曲名</th>
                <th class="table-inout">歌手</th><th class="table-cate am-hide-sm-only">时长</th><th class="table-amount am-hide-sm-only">专辑</th>
                <th class="table-remark am-hide-sm-only">发行时间</th><th class="table-time am-hide-sm-only">源网站</th><th class="table-set">操作</th>
              </tr>
              </thead>
              
              <tbody id="song" style="cursor: pointer;">
               
              </tbody>
            </table>
           	 <div id="nums"></div>
           	 <div id="songListCom"></div>
			  <audio id="audio" style="display:none;" controls="controls"
  			 		src="http://music.163.com/song/media/outer/url?id=486814412.mp3">
			  </audio>
            
            <hr/>
            <script type="text/javascript">
	            
            </script>
            <!-- 单句歌词 -->
            <center>
            	<img alt="" id="singer" src="../image/singer2.jpg" style="height:100px;position:fixed;top:90px;right:306px;">
            	<span id="lyric" style="color: green;cursor:all-scroll;font-size:35px;position:fixed;top:110px;right:446px;"></span>
            </center>
            <!-- 点击歌添加歌曲至歌单 -->
            <div id="addSong" style="cursor:pointer;position:fixed;overflow:scroll;background:gray;right:450px;top:200px;width:220px;height:350px;display: none">
            	<span style="display: none" id="id"></span>
            	<span onclick="closes()" style="color:white;margin-left: 180px;">X</span>
            	<span onclick="createNewList()" style="color:white;">新建歌单</span><br>
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
			  		loadSongList();
			  </script>
            <!--start 播放列表，全部歌词，及其他工具 -->
            <div id="bottom"style="width:80%;height:300px;position:fixed;visibility:hidden;">
            	<!--start 播放列表，全部歌词 -->
            	<div id="plistAalrc" style="background:gray;position:fixed;bottom:40px;cursor: pointer;display:none;margin-left: 5px;">
	          	 <div id="plist" style="color:white;background-image:url(../image/artist/me080502.jpg);background-size:240px 250px; margin-left:20px;width:500px;height:250px;overflow:scroll;overflow-x:hidden;float:left">
	          	 	 <table>
	          	 	 	
	          	 	 </table>
	          	 </div>
	          	 <div id="alyric" style="color:white;margin-left:20px;width:500px;height:250px;overflow:scroll;overflow-x:hidden;float:right"></div>
       		  </div>
       		  <!--end 播放列表，全部歌词 -->
       		  <!-- 其他工具#3f4156：上一曲，下一曲，播放暂停，进度条，音量加减，播放列表显示与隐藏按钮 -->
				<div id="bottom" style="background: #009688;position:fixed;bottom:0; left:260px;width:80%;height:60px;">
					<img style="width: 40px;height: 40px" title="左键--上一曲" onclick="preview()" alt="" src="../image/play_previous.png">&emsp;
					<img style="width: 40px;height: 40px" title="P键--暂停/播放" id="pause" alt="" onclick="pause_play()" src="../image/play.png">&emsp;
				    <img style="width: 40px;height: 40px" title="右键--下一曲" onclick="next()" alt="" src="../image/play_next.png">
					<img id="mode" title="顺序播放--C键切换" style="width:50px;height:60px;" src="../image/play_random.png" onclick="change()">
					<progress title="A键---快退10秒,D键---快进10秒;Q键---快退5秒,E键---快进5秒" style="width:566px;height:10px" draggable="false" id="pro" value="0" max="100"></progress>
					<span id="time" class="time" title="已播放/总时长" style="color:white"></span>
					<a onclick="minus()" class="minus" title="下键--音量减">一</a>
					<progress style="width:90px;" draggable="false" id="voice" value="100" max="100"></progress>
					<a onclick="add()" class="add" title="上键--音量加">✚</a>
					<span onclick="showHide()" title="Ctrl+shift+S"  style="color:white" id="sah">显示</span>
					&emsp;
					<span id="lock" onclick="fixBottom()" style="display: inline-block;"><i class="Hui-iconfont" style="font-size: 15px">&#xe605;</i></span><!-- 锁定：&#xe60e; 解锁：&#xe605;-->
					<span id="unlock" onclick="unfixBottom()" style="display:none;"><i class="Hui-iconfont" style="font-size: 15px">&#xe60e;</i></span>
				</div>
			</div>
			<!--end 播放列表，歌词，及其他工具 -->
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
