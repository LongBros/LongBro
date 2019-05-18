<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>LongBro音乐</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="image/logo.png" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/index3.js" charset="utf-8"></script>
	
  </head>
  <style>
 		#songs{
 			
 		}
 		input[type=text] {
		  width: 20%;
		  padding: 12px 20px;
		  margin: 8px 0;
		   box-sizing: border-box;
		  border: 2px solid red;
		  border-radius: 4px;
		  color: black;
		}
 		button {
		    background-color: #4CAF50; /* Green */
		    border: none;
		    color: white;
		    padding: 11px 22px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
		    font-size: 16px;
		}
  </style>
  <script type="text/javascript">
  		$(function(){
		   querySongs(1);
	   });
  </script>
  <body background="image/back/back25.jpg" style="height: 48px;">
  		<audio id="audio" style="display:none;" controls="controls"
  			 src="http://music.163.com/song/media/outer/url?id=486814412.mp3">
		</audio>
		  	<input type="text" name="key" id="key">
			<button onclick="querySongsByKey()">搜索关键字</button>
			<button onclick="querySongsBySinger()">搜索歌手歌曲</button>
			<a href="addSong.jsp" target="_blank">录歌</a>
			<a href="http://music.163.com" target="_blank">网易云音乐</a>
			<a href="https://y.qq.com/" target="_blank">QQ音乐</a>
      <div id="songs">
  			<table cellpadding="1" cellspacing="1" border="1" bordercolor="blue">
	  			<thead>
		  			<tr><th>序号</th><th>歌曲名</th><th>歌手</th><th>时长</th><th>专辑</th>
		  			<th>图片路径</th><th>发行时间</th><th>网站</th><th>编辑</th></tr>
	  			</thead>
	  			<tbody id="song">
	  				
	  			</tbody>
  			</table>
  	 </div>
  	 <div id="num"></div>
  	 <a onclick="querySongs(1)">1</a>&emsp;&emsp;
  	 <a onclick="querySongs(2)">2</a>&emsp;&emsp;
  	 <a onclick="querySongs(3)">3</a>&emsp;&emsp;
  	 <a onclick="querySongs(4)">4</a>&emsp;&emsp;
  	 <a onclick="querySongs(5)">5</a>&emsp;&emsp;
  	 <a onclick="querySongs(6)">6</a>&emsp;&emsp;
  	 <a onclick="querySongs(7)">7</a>&emsp;&emsp;
     <br><span id="lyric"></span>
  	
  	 <p id="bottom">
			<img style="width: 40px;height: 40px" title="左键--上一曲" onclick="preview()" alt="" src="image/play_previous.png">&emsp;
			<img style="width: 40px;height: 40px" title="P键--暂停/播放" id="pause" alt="" onclick="pause()" src="image/play.png">&emsp;
		    <img style="width: 40px;height: 40px" title="右键--下一曲" onclick="next()" alt="" src="image/play_next.png">
			<img id="mode" title="顺序播放--C键切换" style="width:50px;height:40px;" src="image/play_order.png" onclick="change()">
			<progress title="A键---快退10秒,D键---快进10秒;Q键---快退5秒,E键---快进5秒" style="width:566px;height:10px" draggable="false" id="pro" value="0" max="100"></progress>
			<span id="time" class="time" title="已播放/总时长"></span>
			<a onclick="minus()" class="minus" title="下键--音量减">一</a>
			<progress style="width:100px;" draggable="false" id="voice" value="100" max="100"></progress>
			<a onclick="add()" class="add" title="上键--音量加">✚</a>
		</p>
  </body>
</html>
