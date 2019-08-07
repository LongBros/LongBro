/**
 * 使用Ajax时的返回值类型:xml、html、script、JSON、jsonp、text
 * 4-24实现搜索时将搜索到的歌曲中的搜索关键字特殊显示
 * 4-22貌似QQ音乐限客户端的歌也可以播放（不想说再见），而网易云音乐需会员的不可以（可不可以）
 * 2019-04-20
 * 从数据库中加载出的歌曲和链表中的写死的歌曲不太一样，所以重写了js
 * 1.按分页查询歌曲 		2.根据关键词模糊搜索歌曲
 * 3.根据歌手搜索歌曲 		4.播放歌曲
 * 5.暂停与播放的切换功能 	6.实现下一曲按钮功能
 * 7.实现上一曲按钮功能  	8.切换播放模式
 * 9.快进、快退 			10.monitor函数
 * 11.根据秒参数，将其转换为分和秒的组合00:00格式并返回 12.音量减、加，音量进度条随之变化
 * 13.键盘监听事件
 */
var nowplay=0;//当前播放歌曲的序号
var mode="order";//播放模式---默认为顺序播放模式
var sid="";//资源id
var url="";

var pList=new Array();//播放列表数组
for(var i=1;i<498;i++){
	pList[i]=i;
}
showList();
var i=0;//在addList函数中执行加1来逐个存放歌曲至播放列表数组
var t=0;//当前播放歌曲在播放列表数组中的序号
/**
 * 1.按分页查询歌曲
 * @param page
 */
function querySongs(page){
	//清空原有歌曲列表
	$('#song').text('');
	$('#num').text('');
	//加载歌曲列表
	$.ajax({
		type:"Get",
		async:false,
		url:"../queryAllSongs.do?page="+page,
		dataType:"Json",
		success:function(data){
			//alert(data.length);
			for(var k=0;k<data.length;k++){
				var na=data[k].songName+"";
				if(na.length>9){
					na=na.substring(0, 9)+"...";
				}
				var url="";//源网址
				var id=data[k].sourceId+"";//网址标识部分
				if(id.charAt(id.length-1)=="w"){//酷我音乐，需去掉后缀.kw
					id=id.substring(0, id.length-3);
				}
				var web=data[k].website+"";
				if(web=="QQ音乐"){
					url="https://y.qq.com/n/yqq/song/"+id;
				}else if(web=="网易云音乐"){
					url="https://music.163.com/#/song?id="+id;
				}else if(web=="酷我音乐"){
					url="http://www.kuwo.cn/play_detail/"+id;
				}
				$('#song').append("<tr>" +
						"<td><input type='checkbox'/></td><td>"+data[k].id+"</td>" +
						"<td><font size='+1' onclick='showListName("+data[k].id+")'>+</font>&emsp;<a title='"+data[k].songName+"' onclick=\"play('"+data[k].id+"')\">"+na+"</a></td><td>"+data[k].singer+"</td>" +
						"<td>"+data[k].duration+"</td><td>"+data[k].album+"</td>" +
						"<td title='"+data[k].releaseTime+"'>"+data[k].releaseTime+"</td>" +
						"<td title='"+web+"'><a href=\""+url+"\" target='_blank'>"+web+"</a></td>" +
						"<td><div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\">" +
						"<button class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">" +
						"<span class=\"am-icon-pencil-square-o\"></span><a href='../editSong.jsp?id="+data[k].id+"' target='_blank'>编辑</a></button>" +
						"</div></div></td>" +
						"</tr>");
//				var s="<tr>";
//				var id="<td><input type='checkbox'/></td><td>"+data[k].id+"</td>";
//				var songName="<td>"+data[k].songName+"</a></td>";
//				var singerName="<td>"+data[k].singer+"</td>";
//				var dur="<td>"+data[k].duration+"</td>";
//				var al="<td>"+data[k].album+"</td>";
//				var retime="<td title='"+data[k].releaseTime+"'>"+data[k].releaseTime+"</td>";
//				var web="<td title='"+website+"'>"+website+"</td>";
//				var e="</tr>";
//				alert(s+web);
			}
		}
	});
}
/**
 * 2.根据关键词模糊搜索歌曲
 */
function querySongsByKey(){
	var key=document.getElementById("key").value;
	if(key==""){
		alert("必须输入搜索内容");
		return;
	}
//	alert(key);
	//清空原有歌曲列表
	$('#song').text('');
	$('#num').text('');
	//加载搜索到的歌曲列表
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongs.do?key="+key,
		dataType:"json",
		success:function(data){
//			alert(data.length);
//			alert(data);
			for(var k=0;k<data.length;k++){
				//特殊显示搜索关键字
				var na=data[k].songName+"";
				if(na.length>9){
					na=na.substring(0, 9)+"...";
				}
				na=na.replace(key, "<font color='red'>"+key+"</font>")
				
				var url="";//源网址
				var id=data[k].sourceId+"";//网址标识部分
				if(id.charAt(id.length-1)=="w"){//酷我音乐，需去掉后缀.kw
					id=id.substring(0, id.length-3);
				}
				var web=data[k].website+"";
				if(web=="QQ音乐"){
					url="https://y.qq.com/n/yqq/song/"+id;
				}else if(web=="网易云音乐"){
					url="https://music.163.com/#/song?id="+id;
				}else if(web=="酷我音乐"){
					url="http://www.kuwo.cn/play_detail/"+id;
				}
				$('#song').append("<tr>" +
						"<td><input type='checkbox'/></td><td>"+data[k].id+"</td>" +
						"<td><font size='+1' onclick='showListName("+data[k].id+")'>+</font>&emsp;<a title='"+data[k].songName+"' onclick=\"play('"+data[k].id+"')\">"+na+"</a></td><td>"+data[k].singer+"</td>" +
						"<td>"+data[k].duration+"</td><td>"+data[k].album+"</td>" +
						"<td title='"+data[k].releaseTime+"'>"+data[k].releaseTime+"</td>" +
						"<td title='"+web+"'><a href=\""+url+"\" target='_blank'>"+web+"</a></td>" +
						"<td><div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\">" +
						"<button class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick='edit("+data[k].id+")'>" +
						"<span class=\"am-icon-pencil-square-o\"></span><a href='../editSong.jsp?id="+data[k].id+"' target='_blank'>编辑</a></button>" +
						"</div></div></td>" +
						"</tr>");				
			}
			document.getElementById("nums").innerHTML="<center>共搜索到<font color='red'>"+(data.length)+"</font>首歌曲";
		}
	});
}
/**
 * 3.根据歌手搜索歌曲
 * 如果参数为空，则根据输入框中内容作为歌手查询；反之根据参数查询--参数为点击歌手时所传
 */
function querySongsBySinger(){
//	var key=$('#key').value;
//	alert(singer);
//	var sin=singer+"";
//	var key;
//	if(singer==""){
		key=document.getElementById("key").value;
//	}else{
//		key=sin;
//		alert(sin);
//	}
	if(key==""){
		alert("必须输入搜索内容");
		return;
	}
	//清空原有歌曲列表
	$('#song').text('');
	$('#num').text('');
	//加载搜索到的歌曲列表
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongsBySinger.do?singer="+key,
		dataType:"json",
		success:function(data){
			for(var k=0;k<data.length;k++){
				var singer=data[k].singer+"";
				if(singer.length>5){
					singer=singer.substring(0, 5)+"...";
				}
				singer=singer.replace(key, "<font color='red'>"+key+"</font>")
				
				var url="";//源网址
				var id=data[k].sourceId+"";//网址标识部分
				if(id.charAt(id.length-1)=="w"){//酷我音乐，需去掉后缀.kw
					id=id.substring(0, id.length-3);
				}
				var web=data[k].website+"";
				if(web=="QQ音乐"){
					url="https://y.qq.com/n/yqq/song/"+id;
				}else if(web=="网易云音乐"){
					url="https://music.163.com/#/song?id="+id;
				}else if(web=="酷我音乐"){
					url="http://www.kuwo.cn/play_detail/"+id;
				}
				
				$('#song').append("<tr>" +
						"<td><input type='checkbox'/></td><td>"+data[k].id+"</td>" +
						"<td><font size='+1' onclick='showListName("+data[k].id+")'>+</font>&emsp;<a title='"+data[k].songName+"' onclick=\"play('"+data[k].id+"')\">"+data[k].songName+"</a></td><td>"+	singer+"</td>" +
						"<td>"+data[k].duration+"</td><td>"+data[k].album+"</td>" +
						"<td title='"+data[k].releaseTime+"'>"+data[k].releaseTime+"</td>" +
						"<td title='"+web+"'><a href=\""+url+"\" target='_blank'>"+web+"</a></td>" +
						"<td><div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\">" +
						"<button class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick='edit("+data[k].id+")'>" +
						"<span class=\"am-icon-pencil-square-o\"></span><a href='../editSong.jsp?id="+data[k].id+"' target='_blank'>编辑</a></button>" +
						"</div></div></td>" +
						"</tr>");
			}
			document.getElementById("nums").innerHTML="<center>共搜索到<font color='red'>"+(data.length)+"</font>首歌曲";
		}
	});
}
/**
 * 强力搜索功能，可根据搜索关键词搜索歌曲名、歌手、歌词
 */
function strongQuerySongs(){
	var key=document.getElementById("key").value;
	if(key==""){
		alert("必须输入搜索内容");
		return;
	}
	//清空原有歌曲列表
	$('#song').text('');
	$('#num').text('');
	//加载搜索到的数据
	$.ajax({
		type:"Get",
		async:false,
		url:"../strongQuerySongs.do?key="+key,
		dataType:"json",
		success:function(data){
			//alert(data[2].length);查询的歌词
			for(var k=0;k<data[0].length;k++){//歌曲
				var na=data[0][k].songName+"";
				if(na.length>9){
					na=na.substring(0, 9)+"...";
				}
				na=na.replace(key, "<font color='red'>"+key+"</font>")
				
				var url="";//源网址
				var id=data[0][k].sourceId+"";//网址标识部分
				if(id.charAt(id.length-1)=="w"){//酷我音乐，需去掉后缀.kw
					id=id.substring(0, id.length-3);
				}
				var web=data[0][k].website+"";
				if(web=="QQ音乐"){
					url="https://y.qq.com/n/yqq/song/"+id;
				}else if(web=="网易云音乐"){
					url="https://music.163.com/#/song?id="+id;
				}else if(web=="酷我音乐"){
					url="http://www.kuwo.cn/play_detail/"+id;
				}
				
				$('#song').append("<tr>" +
						"<td><input type='checkbox'/></td><td>"+data[0][k].id+"</td>" +
						"<td><font size='+1' onclick='showListName("+data[0][k].id+")'>+</font>&emsp;<a title='"+data[0][k].songName+"' onclick=\"play('"+data[0][k].id+"')\">"+na+"</a></td><td>"+data[0][k].singer+"</td>" +
						"<td>"+data[0][k].duration+"</td><td>"+data[0][k].album+"</td>" +
						"<td title='"+data[0][k].releaseTime+"'>"+data[0][k].releaseTime+"</td>" +
						"<td title='"+web+"'><a href=\""+url+"\" target='_blank'>"+web+"</a></td>" +
						"<td><div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\">" +
						"<button class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick='edit("+data[0][k].id+")'>" +
						"<span class=\"am-icon-pencil-square-o\"></span><a href='../editSong.jsp?id="+data[0][k].id+"' target='_blank'>编辑</a></button>" +
						"</div></div></td>" +
						"</tr>");
			}
			for(var k=0;k<data[1].length;k++){//歌手
				var singer=data[1][k].singer+"";
				if(singer.length>6){
					singer=singer.substring(0, 6)+"...";
				}
				singer=singer.replace(key, "<font color='red'>"+key+"</font>")
				
				var na=data[1][k].songName+"";
				if(na.length>6){
					na=na.substring(0, 6)+"...";
				}
				
				var url="";//源网址
				var id=data[1][k].sourceId+"";//网址标识部分
				if(id.charAt(id.length-1)=="w"){//酷我音乐，需去掉后缀.kw
					id=id.substring(0, id.length-3);
				}
				var web=data[1][k].website+"";
				if(web=="QQ音乐"){
					url="https://y.qq.com/n/yqq/song/"+id;
				}else if(web=="网易云音乐"){
					url="https://music.163.com/#/song?id="+id;
				}else if(web=="酷我音乐"){
					url="http://www.kuwo.cn/play_detail/"+id;
				}
				
				$('#song').append("<tr>" +
						"<td><input type='checkbox'/></td><td><font  color='blue'>"+data[1][k].id+"</font></td>" +
						"<td><font size='+1' onclick='showListName("+data[1][k].id+")'>+</font>&emsp;<a title='"+na+"' onclick=\"play('"+data[1][k].id+"')\">"+data[1][k].songName+"</a></td><td>"+singer+"</td>" +
						"<td>"+data[1][k].duration+"</td><td>"+data[1][k].album+"</td>" +
						"<td title='"+data[1][k].releaseTime+"'>"+data[1][k].releaseTime+"</td>" +
						"<td title='"+web+"'><a href=\""+url+"\" target='_blank'>"+web+"</a></td>" +
						"<td><div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\">" +
						"<button class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick='edit("+data[1][k].id+")'>" +
						"<span class=\"am-icon-pencil-square-o\"></span><a href='../editSong.jsp?id="+data[1][k].id+"' target='_blank'>编辑</a></button>" +
						"</div></div></td>" +
						"</tr>");
			}
			for(var k=0;k<data[2].length;k++){//歌词
				var na=data[2][k].songName+"";
				if(na.length>6){
					na=na.substring(0, 6)+"...";
				}
				var url="";//源网址
				var id=data[2][k].sourceId+"";//网址标识部分
				if(id.charAt(id.length-1)=="w"){//酷我音乐，需去掉后缀.kw
					id=id.substring(0, id.length-3);
				}
				var web=data[2][k].website+"";
				if(web=="QQ音乐"){
					url="https://y.qq.com/n/yqq/song/"+id;
				}else if(web=="网易云音乐"){
					url="https://music.163.com/#/song?id="+id;
				}else if(web=="酷我音乐"){
					url="http://www.kuwo.cn/play_detail/"+id;
				}
				//歌词
				var lyric=data[2][k].lyric+"";
				lyric=lyric.replace(key, "<font color='red'>"+key+"</font>");
				//如何取关键词所在那句歌词显示？
				lyric=lyric.substring(lyric.indexOf(key)-18, lyric.indexOf(key)+20);
				$('#song').append("<tr>" +
						"<td><input type='checkbox'/></td><td><font  color='red'>"+data[2][k].id+"</font></td>" +
						"<td><font size='+1' onclick='showListName("+data[2][k].id+")'>+</font>&emsp;<a title='"+data[2][k].songName+"' onclick=\"play('"+data[2][k].id+"')\">"+na+"</a>("+lyric+")</td><td>"+data[2][k].singer+"</td>" +
						"<td>"+data[2][k].duration+"</td><td>"+data[2][k].album+"</td>" +
						"<td title='"+data[2][k].releaseTime+"'>"+data[2][k].releaseTime+"</td>" +
						"<td title='"+web+"'><a href=\""+url+"\" target='_blank'>"+web+"</a></td>" +
						"<td><div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\">" +
						"<button class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick='edit("+data[2][k].id+")'>" +
						"<span class=\"am-icon-pencil-square-o\"></span><a href='../editSong.jsp?id="+data[2][k].id+"' target='_blank'>编辑</a></button>" +
						"</div></div></td>" +
						"</tr>");
			}
			document.getElementById("nums").innerHTML="<center>共搜索到<font color='red'>"+(data[0].length+data[1].length+data[2].length)+"</font>首歌曲,其中歌曲<font color='red'>"+data[0].length+"</font>首，歌手<font color='red'>"+data[1].length+"</font>首,歌词<font color='red'>"+data[2].length+"</font>首</center>";
		}
	});
}
/**
 * 歌单
 * @param list
 */
function querySongList(list){
	var l=list+"";
	var title="";//歌单名
	var lid="";//歌单id
	if(l!="歌单"){//歌单由】将歌曲与歌单名及歌单id区分开
//		list=l.substring(0, l.indexOf("】"));
//		title=l.substring(l.indexOf("】")+1);
		list=l.split("】")[0];//歌单
		title=l.split("】")[1];//歌单名
		lid=l.split("】")[2];//歌单id
	}else{//第一个默认选项
		querySongs(1);
		return;
	}
	//list中的190,191,192,193,194,197,202,203,204,214为字符串，执行split方法后返回数组
	pList=list.split(",");
	showList();
	t=0;
	//清空原有歌曲列表
	$('#song').text('');
	$('#num').text('');
	//加载歌曲列表
	$.ajax({
		type:"Get",
		async:false,
		url:"../queryPListSong.do?pList="+list,
		dataType:"Json",
		success:function(data){
			//alert(data.length);
			for(var k=0;k<data.length;k++){
				var na=data[k].songName+"";
				if(na.length>9){
					na=na.substring(0, 9)+"...";
				}
				var url="";//源网址
				var id=data[k].sourceId+"";//网址标识部分
				if(id.charAt(id.length-1)=="w"){//酷我音乐，需去掉后缀.kw
					id=id.substring(0, id.length-3);
				}
				var web=data[k].website+"";
				if(web=="QQ音乐"){
					url="https://y.qq.com/n/yqq/song/"+id;
				}else if(web=="网易云音乐"){
					url="https://music.163.com/#/song?id="+id;
				}else if(web=="酷我音乐"){
					url="http://www.kuwo.cn/play_detail/"+id;
				}
				$('#song').append("<tr>" +
						"<td><input type='checkbox'/></td><td>"+data[k].id+"</td>" +
						"<td><font size='+1' onclick='showListName("+data[k].id+")'>+</font>&emsp;<font size='+1' title='从\""+title+"\"移除\""+data[k].songName+"\" ' onclick='remove("+data[k].id+","+lid+")'>-</font>&emsp;<a title='"+data[k].songName+"' onclick=\"play('"+data[k].id+"')\">"+na+"</a></td><td>"+data[k].singer+"</td>" +
						"<td>"+data[k].duration+"</td><td>"+data[k].album+"</td>" +
						"<td title='"+data[k].releaseTime+"'>"+data[k].releaseTime+"</td>" +
						"<td title='"+web+"'><a href=\""+url+"\" target='_blank'>"+web+"</a></td>" +
						"<td><div class=\"am-btn-toolbar\"><div class=\"am-btn-group am-btn-group-xs\">" +
						"<button class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">" +
						"<span class=\"am-icon-pencil-square-o\"></span><a href='../editSong.jsp?id="+data[k].id+"' target='_blank'>编辑</a></button>" +
						"</div></div></td>" +
						"</tr>");
			}
		}
	});
}
/**
 * 播放歌曲	
 * 1.正在播放的歌名颜色特殊（黑色）显示
 * 2.网页title改为正在播放歌曲名
 * @param k 歌曲在数字中的序号
 */
function play(k) {
	var singerImg=document.getElementById("singer");//动态搞笑图

//	var playList=document.getElementById("playList").getElementsByTagName("tr");
//	for (var i = 0; i < playList.length; i++) {
//		playList[i].onclick = function () {
//			 for (var j = 0; j < playList.length; j++) {
//				 playList[j].style.color = '';
//			 }
//			 this.style.color = 'red';//backgroundColor
//		 };
//	}
//	k=parseInt(k)+1;
	nowplay=k;
//	alert(nowplay);
	var name="";//歌名
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongById.do?id="+k,
		dataType:"Json",
		success:function(data){
			sid=data.sourceId+"";
//			if(sid.substring(sid.length-5)==".html"){
//				url="http://link.hhtjim.com/qq/"+sid.substring(0, sid.length-5)+".mp3";
//			}else if(sid.substring(sid.length-3)==".kw"){
//				url="http://link.hhtjim.com/kw/"+sid.substring(0, sid.length-3)+".mp3";
//			}else if(sid.substring(sid.length-4)==".mp3"){
//				url=sid;
//			}else{
//				url="http://music.163.com/song/media/outer/url?id="+sid+".mp3";
//			}
			//设置播放资源路径
			if(sid.substring(sid.length-5)==".html"){//QQ音乐截去.html，其他无需截取
				sid=sid.substring(0, sid.indexOf(".html"));
			}
			url="http://localhost/util/songs/"+sid+".mp3";
			name=data.songName+"-"+data.singer;
			//设置图片
			var img=data.imgPath+"";
			var artist="";
			if(img.substring(img.length-4, img.length)==".jpg"){//网易云音乐的img
				artist="http://p1.music.126.net/"+img;
			}else if(img.substring(0,4)=="T002"){//QQ音乐的img
				artist="https://y.gtimg.cn/music/photo_new/"+img+".jpg?max_age=2592000";//https://y.gtimg.cn/music/photo_new/T002R300x300M000002iWU6B2ZvA8V.jpg?max_age=2592000
			}else{
				artist="../image/artist/yingying1.jpg";
			}
			artist="url("+artist+")";
			document.getElementById('alyric').style.backgroundImage=artist;
			//加载歌词资源
			var au="../loadLyric3.jsp?sid="+sid+"&type=2";
			$.ajax({
				type:"Get",
				async:false,
				url:au,
				dataType:"text",
				success:function(data){
					document.getElementById("alyric").innerHTML="<center><font color='yellow'>"+name+"</font></center>"+data;
				}
			});
			
		}
	});
//	alert(url);
	var p=document.getElementById("audio");
	p.setAttribute("src", url);
	singerImg.src="../image/sing.gif";

	p.play();
	document.title=name+"-正在播放|LongBro音乐";
}
/**
 * 5.暂停与播放的切换功能
 * 若当前在播放点击后暂停；若当前出于暂停状态，则点击后播放
 * 播放与暂停按钮相应改变
 * 2019-05-12此处修改了函数名，因为用pause无效，可能框架的js里用到了吧
 */
function pause_play() {
	var p=document.getElementById("audio");
	var btn=document.getElementById("pause");
	var singerImg=document.getElementById("singer");

	if(p.paused==false){//原本是播放状态，则置为暂停状态
		p.pause();
		btn.src="../image/pause.png";
		singerImg.src="../image/singer2.jpg";

	}else{
		p.play();
		btn.src="../image/play.png";
		singerImg.src="../image/sing.gif";
		p.paused=false;
	}
}
/**
 * 6.实现下一曲按钮功能------
 * 顺序播放模式时播放下一曲，
 * 随机播放模式时随机生成一个歌曲序号来播放
 * 单曲循环模式时只播放当前歌曲
 */
function next(){
	if(mode=="order"){//顺序播放
		if(t<(pList.length-1)){
			t=t+1;
		}else{
			t=0;
		}
	}else if(mode=="random"){//随机播放
		t=Math.round(Math.random()*(pList.length-1-0)+0); 
	}else{
		t=t;
	}
	play(pList[t]);
}
/**
 * 7.实现上一曲按钮功能------
 * 顺序播放模式时播放上一首，
 * 随机播放模式时随机生成一个歌曲序号来播放
 * 单曲循环模式时只播放当前歌曲
 */
function preview(){
	if(mode=="order"){//顺序播放
		if(t>0){
			t=t-1;
		}else{
			t=pList.length-1;
		}
	}else if(mode=="random"){//随机播放
		t=Math.round(Math.random()*(pList.length-1-0)+0); 
	}else{//单曲循环
		t=t;
	}
	play(pList[t]);
}
/**
 * 8.切换播放模式
 */
function change(){
	var m=document.getElementById("mode");
	if(mode=="order"){//当前模式为顺序，则切换为随机
		mode="random";
		m.src="../image/play_random.png";//http://longqcloud/Minimusic/
		m.title="随机播放--C键切换";
	}else if(mode=="random"){//当前播放为随机，则切换为单曲
		mode="single";
		m.src="../image/play_single.png";
		m.title="单曲循环--C键切换";
	}else{//当前播放为单曲，则切换为顺序循环
		mode="order";
		m.src="../image/play_order.png";
		m.title="顺序循环--C键切换";
	}
}
/**
 * 9.快进
 * @param bei
 */
function moveon(bei){
	bei=parseInt(bei);
	var p=document.getElementById("audio");
	p.currentTime=p.currentTime+5*bei;
}
/**
 * 快退
 * @param bei
 */
function back(bei){
	bei=parseInt(bei);
	var p=document.getElementById("audio");
	p.currentTime=p.currentTime-5*bei;
}
/**
 * 10.该函数检测当前歌曲播放完毕后，自动播放下一首
 */
function monitor() {
	var p=document.getElementById("audio");//音乐播放控件
	var per=document.getElementById("time");//
	var pro=document.getElementById("pro");//进度条
	var time=document.getElementById("countDown").innerText+"";
	if(time=="0"&&p.paused==false){//倒计时为0执行
		//p.pause();
		alert('已到时间');
	}else if(time!="0"&&p.paused==false){
		var left=parseInt(time)-1;
		document.getElementById("countDown").innerText=left+"";
	}else if(time!="0"&&p.paused==true){
		
	}
	timeCount();getNow();
	if(p.networkState==3){//2正常，3异常
//		alert("出现了异常~_~");
		next();
	}
    var time=p.duration+"";
	var ctime=p.currentTime+"";
    //设置音乐播放进度条，和音乐已播放时长和总时长
    per.innerText=getTime(ctime)+"/"+getTime(time);
	pro.value=(p.currentTime/p.duration*100.00);
	var url="../loadLyric3.jsp?sid="+sid+"&time="+getTime(ctime)+"&type=1";
	$.ajax({
		type:"Get",
		async:false,
		url:url,
		dataType:"text",
		success:function(data){
			document.getElementById("lyric").innerHTML=data;
		}
	});
	
	if(p.ended==true){//若当前音频播放结束，自动播放下一首
		next();
	}
}
/**
 * 11.根据秒参数，将其转换为分和秒的组合00:00格式并返回
 * 例若time=195，返回03:15
 * @param time
 * @returns {String}
 */
function getTime(time) {//根据秒换算为分和秒
	time=time.substring(0, time.indexOf(".", 0));
    //var min=Math.round(time/60)+"";//分钟---此方法在秒为31时会直接将分得为1，不可靠
	//整型分钟数加空字符变为字符型方可执行下方的.length方法
	var min=parseInt(time/60)+"";
    var sec=time%60+"";//秒
    //保持格式为00:00
    if(min.length==1){
    	min="0"+min;
    }
    if(sec.length==1){
    	sec="0"+sec;
    }
    return min+":"+sec;
}
/**
 * 12.音量减，音量进度条随之变化
 */
function minus() {
	var p=document.getElementById("audio");
	var vo=document.getElementById("voice");
	if(vo.value==0){
		alert("使出了洪荒之力，音量已经到静音了，不能再减了");
	}else{
		vo.value=vo.value-10;
		p.volume=p.volume-0.1;
	}
}
/**
 * 音量加，音量进度条随之变化
 */
function add() {
	var p=document.getElementById("audio");
	var vo=document.getElementById("voice");//声音从0-1
	if(vo.value==100){
		alert("哎呀，音量已经到最大了，不能再加了");
	}else{
		vo.value=vo.value+10;
		p.volume=p.volume+0.1;
	}
}
/**
 * 13.键盘监听事件
 * P键--播放/暂停，左键--上一首，右键--下一首
 * A~G	65-71
 * H~N	72-78
 * O~T	79-84
 * U~Z	85-90
 * @param event
 */
function keydown(event) {
	var p=document.getElementById("audio");
	var btn=document.getElementById("pause");
	//以下两种均可1.状态转为字符串再和"false"比较，2.直接和false比较
	//var sta=p.paused+"";//状态转为String
	//if(event.keyCode=="80"&&sta=="false"){//80=p
	//	p.pause();
	//}
	if(event.keyCode=="80"&&p.paused==false){//80=p   32空格键
		p.pause();
		btn.src="../image/pause.png";
	}else if(event.keyCode=="80"&&p.paused==true){
		p.play();
		btn.src="../image/play.png";
	}else if(event.keyCode=="37"){//左键
		preview();
	}else if(event.keyCode=="39"){//右键
		next();
	}else if(event.keyCode=="38"){//上键
		add();
	}else if(event.keyCode=="40"){//下键
		minus();
	}else if(event.keyCode=="67"){//C键--切换播放模式
		change();
	}else if(event.keyCode=="66"){//B键--改变页面背景
		changeBack();
	}else if(event.keyCode=="65"){//A键--快退十秒
		back(2);
	}else if(event.keyCode=="68"){//D键--快进十秒
		moveon(2);
	}else if(event.keyCode=="81"){//Q键--快退5秒
		back(1);
	}else if(event.keyCode=="69"){//E键--快进5秒
		moveon(1);
	}else if(event.keyCode=="83"){//Q键--快退5秒
		showHide();
	}
}
//检测当前播放是否结束，若是，则播放下一首
window.setInterval("monitor()", 1000);
document.addEventListener("keydown", keydown);
/**
 * 设置倒计时时间
 * @param min
 */
function setCount(min){
//	alert(min);
	var sec=60*parseInt(min);
	document.getElementById("countDown").innerText=sec+"";
}

/**
 * 点击新增后显示歌曲信息输入表单
 */
function showAdd(){
	var what=document.getElementById("form").style.display;
	if(what=="none"){
		document.getElementById("form").style.display="inline-block";
	}else{
		document.getElementById("form").style.display="none";
	}
//	document.getElementById("form").style.display="inline-block";
}
/**
 * 录歌曲
 * 输入表单后异步添加歌曲
 */
function addSong(){
	var form=document.getElementById("form");
	var sourceId=form.sourceId.value+"";
	var songName=form.songName.value+"";
	var singer=form.singer.value+"";
	var duration=form.duration.value+"";
	
	var album=form.album.value+"";
	var imgPath=form.imgPath.value+"";
	var website=form.website.value+"";
	var descr=form.descr.value+"";
	var releaseTime=form.releaseTime.value+"";
	var time=form.time.value+"";
	if(sourceId.length==0||songName.length==0){
		alert("部分信息未输入！！！")
		return ;
	}
	var picture="";
//	alert(util);
	$.ajax({
		type:"GET",
		async:true,
		url:"../addSong.do?sourceId="+sourceId+"&songName="+songName+"&singer="+singer+"&duration="+duration+"&album="+album+"&imgPath="+imgPath+"&website="+website+"&descr="+descr+"&releaseTime="+releaseTime+"&time="+time,
		dataType:"Json",
		success:function(res){
			if(res){
				alert(res.msg);
			}
		}
	});
	querySongs('10');
}
/**
 * 将id为id的歌曲添加至播放列表
 * @param id
 */
function addList(id){
	if(pList.length>80){//歌曲播放列表中大于80首歌时将播放列表清空
		pList=new Array();
	}
	for(var j=0;j<pList.length;j++){//如果播放列表已含已选歌曲，则再不添加
		if(pList[j]==id)
			return;
	}
	pList[i]=id;
	i++;
	showList();//每添加一首刷新一次播放列表
}
/**
 * 刷新播放列表
 */
function showList(){
	$('#plist').text('');
	$.ajax({
		type:"Get",
		async:true,
		url:"../queryPListSong.do?pList="+pList,
		dataType:"Json",
		success:function(data){
			$('#plist').append("<center><font color='yellow'>播放列表</font>("+data.length+")</center>");
			for(var k=0;k<data.length;k++){
				var na=data[k].songName+"";
				if(na.length>9){
					na=na.substring(0, 9)+"...";
				}
				$('#plist').append("<tr><td width='30%'><span title='"+data[k].songName+"' onclick=\"play('"+data[k].id+"')\">"+na+"</span></td><td width='30%'>"+data[k].singer+"</td><td  width='10%'>"+data[k].duration+"</td></tr>");
			}
		}
	});
}
/**
 * 显示与隐藏播放列表和歌词
 */
function showHide(){
	var status=document.getElementById("plistAalrc").style.display+"";
	if(status=="none"){
		document.getElementById("plistAalrc").style.display="inline-block";
		document.getElementById("sah").innerHTML="隐藏("+pList.length+")";
	}else{
		document.getElementById("plistAalrc").style.display="none";
		document.getElementById("sah").innerHTML="显示("+pList.length+")";
	}
}
/**
 * 弹出歌单框
 */
function showListName(id){
	var name="";
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongById.do?id="+id,
		dataType:"Json",
		success:function(data){
			name=data.songName+"-"+data.singer;
		}
	});
	document.getElementById("songName").innerHTML="'<font color='red'>"+name+"</font>'";
	document.getElementById("addSong").style.display="inline-block";//打开添加至歌单或播放列表的弹框
	document.getElementById("id").innerHTML=id;
}
/**
 * 关闭添加至歌单或播放列表的弹框
 */
function closes(){
	document.getElementById("addSong").style.display="none";
	document.getElementById("newList").style.display="none";
}
/**
 * 添加某首歌至id为l_id的歌单
 * @param l_id
 */
function addToList(l_id){
	var id=document.getElementById("id").innerHTML;//歌曲id
	var name=getNameById(id);
	var lName=getLNameByLId(l_id);
	if(l_id=="0"){//歌单id
		addList(id);
	}else{
		var r=confirm("添加'"+name+"'至歌单'"+lName+"'?");
		if(r==true){
			var url="../addToList.do?sid="+id+"&lid="+l_id;
			$.ajax({
				type:"Get",
				async:false,
				url:url,
				dataType:"text",
				success:function(data){
					alert("添加成功");
				}
			});
		}else{
			
		}
		
	}
}
/**
 * 从歌单移除歌曲
 * @param id
 * @param lid
 */
function remove(id,lid){
	var name=getNameById(id);
	var lName=getLNameByLId(lid);
	var r=confirm("确定从歌单'"+lName+"'移除'"+name+"'?");
	if(r==true){
		var url="../removeFromList.do?sid="+id+"&lid="+lid;
		$.ajax({
			type:"Get",
			async:false,
			url:url,
			dataType:"Json",
			success:function(data){
				alert("已移除");
			}
		});
	}
	else{
//		alert("已取消");
	}
}
/**
 * 新建歌单点击函数
 */
function newList(){
	document.getElementById("addSong").style.display="none";//关闭添加至歌单或播放列表的弹框
	document.getElementById("newList").style.display="inline-block";//打开新建歌单的弹框
//	document.getElementById("id").innerHTML=id;
}
/**
 * 创建歌单
 */
function create(){
	var name=document.newList.songlist.value+"";
	var desc=document.newList.listdesc.value+"";
	if(name==""||desc==""){
		alert("部分信息为空");
		return;
	}
	var url="../newList.do?name="+name+"&desc="+desc;
	$.ajax({
		type:"Get",
		async:false,
		url:url,
		dataType:"Json",
		success:function(data){
			
		}
	});
}
/**
 * 根据歌曲id得到歌曲名
 * @param id
 * @returns {String}
 */
function getNameById(id){
	var name="";
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongById.do?id="+id,
		dataType:"Json",
		success:function(data){
			name=data.songName+"-"+data.singer;
		}
	});
	return name;
}
/**
 * 根据歌单id得到歌单的信息
 * @param id
 * @returns
 */
function getLNameByLId(id){
	var name;
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongList.do?id="+id,
		dataType:"Json",
		success:function(data){
			//songs，name，id
			name=data[0].name;
		}
	});
	return name;
}