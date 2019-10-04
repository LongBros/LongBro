<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 tdansitional//EN" "http://www.w3.org/td/xhtml1/DTD/xhtml1-tdansitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>欢迎使用-小可爱版小闹钟</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="keywords" content="功能强大的网页版闹铃,实用网页版小闹铃,电脑工作者必备闹铃,哆啦工具,LongBro工具">
    <meta name="description" content="实用网页版小闹铃，网页上的闹铃，电脑工作者必备工具，提供存储多个闹铃设置，自由铃声设置，提示语设置等多重功能,打卡必备工具">
    <script src="scripts/boot.js" type="text/javascript"></script>
    <link rel="icon" type="image/png" href="image/logo/dlam.jpg">
    <!-- 使用以下文件的头像 -->
    <link rel="stylesheet" type="text/css" href="hui/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <!-- 使用以下mini文件的alert -->
    <script src="scripts/boot.js" type="text/javascript"></script>
    
    <link rel="stylesheet" type="text/css" href="css/lovelyAlarm.css">
    <script src="js/myjs/player.js"></script><!-- 调用其函数：根据资源id得到资源名 -->
    <script src="js/myjs/lovelyAlarm.js"></script>
    <script src="js/myjs/timeDeal.js"></script>
    <script src="js/myjs/setting.js" type="text/javascript"></script><!-- 填充音乐和提示语以供选择 -->
</head>
<body style="" id="body"> 
	<div>
		<span class="topmenu" title="温馨提示" onmouseover="showTips()" onmouseout="hideTips()">?</span>
		<span class="topmenu" onclick="notice()" title="通知"><i class="Hui-iconfont" style="font-size:18px">&#xe62f;</i></span>
		<span class="topmenu" onclick="copyLink()" title="复制并分享"><i class="Hui-iconfont" style="font-size:18px">&#xe6aa;</i></span>
		<span class="topmenu" onclick="myInfo()" title="个人"><i class="Hui-iconfont" style="font-size:18px">&#xe60a;</i></span>
		<span class="topmenu" onclick="setting()" title="设置"><i class="Hui-iconfont" style="font-size:18px">&#xe61d;</i><img alt="" style="width: 18px;height: 18px;" src="image/picture/hot1.gif"></span>
		<span class="topmenu" onclick="changeMode()" title="点击切换背景"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></span>
	</div>
	<div><span id="tips"></span></div>
	<form id="person" name="person" style="float: right;margin-top: 40px;" class="newAlarm">
		<span onclick="closesPerson()" class="close">X</span><br>
		<center>个人资料</center>
		id:<span id="userId" name="userId"></span><br>
		用户名:<input class="mini-textbox" style="width: 60px;" id="userName" name="userName"> <br>
		性别:<input name="sex" id="sex" class="mini-radiobuttonlist" textField="name" valueField="id" value="male" data="[{'name':'男','id':'male'},{'name':'女','id':'female'}]">
		签名:<input id="sign" class="mini-textbox" style="width: 160px;" name="sign">
		<center><a onclick="editInfo()">提交</a></center>
	</form>
	
     <center>
		<div class="remark">
			<div id="array" style="display: none"></div>
			<div id="alarm"></div>
		</div>
		<form id="newAlarm" name="newAlarm" class="newAlarm">
            	<table>
            		<span onclick="closesNew()" class="close">X</span><br>
	            	<tr>	
	            		<td><input name="hour" id="hour" allowinput="true" class="mini-combobox" style="width:50px;" emptytext="时" valuefield="name" textField="name" showNullItem="true" data=""></td>
            			<td><input name="minute" id="minute" allowinput="true" class="mini-combobox" style="width:50px;" emptytext="分" valuefield="name" textField="name" showNullItem="true" data=""></td>
            			<td><input name="second" id="second" allowinput="true" class="mini-combobox" style="width:50px;" emptytext="秒" valuefield="name" textField="name" showNullItem="true" data=""></td>	
	            	</tr>
	            	<tr>
	            		<td><a href="setting.html" title="去提示语库" target="_blank">提示</a>:</td>
	            		<td colspan="2"><input name="tip" id="myTips" allowinput="true" class="mini-combobox" style="width:120px;" emptytext="请选择提示语" valuefield="tip" textField="tip" showNullItem="true"
                	 	data=""></td>
                	</tr> 
	            	<tr>
	            		<td><a href="setting.html" title="去铃声库-可以试听喔" target="_blank">铃声</a>:</td>
	            		<td colspan="2"><input name="music" id="myMusic" class="mini-combobox"
	            	 style="width:120px;" emptytext="请选择音乐" valuefield="sourceId"
	            	  textField="songName" showNullItem="true" data=""></td>
                	</tr>
	            	<tr><td></td><td colspan="2"><span style="background: cyan" onclick="create()">添加</span></td></tr>
            	</table>
        </form>
        <div id="allAlarm" class="allAlarm">
        	
		</div>
        <img id="smilePic" src="image/picture/smile1.png">
		<div class="praise">
			<img id="heart" src="image/picture/heart.png">加油哟，你是最棒哒
			<img id="hug" src="image/picture/hug.png">
		</div>
		
		<div id="time"></div>
	 </center>
    <audio id="song" src="" autoplay></audio>
    <script type="text/javascript">
		var hour=new Array();
		var minOsec=new Array();
		for(var i=0;i<60;i++){
			if(i<24){
				hour.push("{'name':'"+i+"','value':'"+i+"'}");
			}
			minOsec.push("{'name':'"+i+"','value':'"+i+"'}");
		}
		hour="["+hour+"]";
		minOsec="["+minOsec+"]";
		mini.parse();
		mini.get("hour").setData(hour);
		mini.get("minute").setData(minOsec);
		mini.get("second").setData(minOsec);
		fillMyGit();

		//mini.get("tip").setData(dataTip);
		getAlarmTime();
	</script>
	<div class="bottom" id="bottom">
		<div class="sitemap">
			<dl>
				<dt>本站他站</dt>
				<dd><a href="amaze/songsList.jsp" target="_blank">553Music</a></dd>
				<dd><a href="/LongVideos" target="_blank">553影院</a></dd>
				<dd><a href="/LongBlog" target="_blank">LongBro博客</a></dd>
				<dd><a href="index.jsp" target="_blank">哆拉音乐</a></dd>
				<dd><a href="hui" target="_blank">LongBro工具</a></dd>
				<dd><a href="mini/myAccount.html" target="_blank">Mini账单</a></dd>
			</dl>
			<dl>
				<dt>常用站</dt>
				<dd><a href="https://www.baidu.com" target="_blank">百度</a></dd>
				<dd><a href="https://www.youku.com" target="_blank">优酷视频</a></dd>
				<dd><a href="https://www.iqiyi.com" target="_blank">爱奇艺视频</a></dd>
				<dd><a href="https://v.qq.com/" target="_blank">腾讯视频</a></dd>
				<dd><a href="https://music.163.com/" target="_blank">网易云音乐</a></dd>
				<dd><a href="https://y.qq.com/" target="_blank">QQ音乐</a></dd>
			
			</dl>
		</div>
	</div>
</body>
</html>
