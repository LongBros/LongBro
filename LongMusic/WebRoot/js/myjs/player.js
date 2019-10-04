/**
 * songList.jsp中的id的解释：songListCom-歌单下的评论或某歌曲的所有信息
 * 1.按分页查询歌曲 		2.根据关键词模糊搜索歌曲
 * 3.根据歌手搜索歌曲 	4.强力搜索功能，可根据搜索关键词搜索歌曲名、歌手、歌词5.加载指定歌单6.播放歌曲	
 * 
 * 5.暂停与播放的切换功能 	6.实现下一曲按钮功能
 * 7.实现上一曲按钮功能  	8.切换播放模式
 * 9.快进、快退 			10.monitor函数
 * 11.根据秒参数，将其转换为分和秒的组合00:00格式并返回-已转移至TimeDeal.js
 * 12.音量减、加，音量进度条随之变化13.键盘监听事件14.设置倒计时时间15.点击新增后显示歌曲信息输入表单
 * 16.录歌曲17.将id为id的歌曲添加至播放列表18.刷新播放列表19.显示与隐藏播放列表和歌词
 * 20.弹出歌单框21.关闭添加至歌单或播放列表的弹框22.添加某首歌至id为l_id的歌单23.从歌单移除歌曲
 * 24.新建歌单点击函数25.创建歌单26.根据歌曲id得到歌曲名27.根据歌单id得到歌单的信息28.生成min-max的随机整数29.全选 or 全不选
 * 30.加载歌曲详细信息，及歌曲下的评论31.加载全部歌词32.随机生成一个包含num首歌曲的播放列表33.加载歌单
 * 34.加载热歌榜35.显示播放按钮和添加按钮36.隐藏播放按钮和添加按钮37.实现单句歌词可随鼠标移动
 * 38.使用迷你UI的弹出提示功能39.随机弹出一首歌曲以供直达播放40.底部栏的显示与隐藏41.回车键搜索歌曲
 * 42.隐藏底部栏43.该函数实现固定底部栏44.该函数实现取消固定底部栏
 */
var nowplay=0;//当前播放歌曲的序号
var mode="order";//播放模式---默认为顺序播放模式
var sid="";//资源id，QQ音乐含.html以区分QQ音乐与网易云音乐加载歌词
var ssid="";//8-8不使用sid是因为会影响单句歌词的加载，歌曲资源名为QQ音乐不含.html
var url="";

var pList=new Array();//播放列表数组
for(var i=1;i<60;i++){
	pList.push(i);
}
showList();
//在addList函数中执行加1来逐个存放歌曲至播放列表数组
var xu=0;//08-25由i改为xu，因此js中很多地方使用到局部变量i，可能会对全局变量产生影响
var t=0;//当前播放歌曲在播放列表数组中的序号
/**
 * 1.按分页查询歌曲
 * @param page
 */
function querySongs(page){
	//清空原有歌曲列表
	$('#song').text('');
	$('#num').text('');
	$('#songListCom').text('');
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
				na=na+"("+data[k].playNum+")";
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
						"<td><input type='checkbox' id='songs"+k+"'/></td><td>"+data[k].id+"</td>" +
						
						"<td><span onmouseout=\"hidePlayBtn(this,"+data[k].id+")\" onmouseover=\"showPlayBtn(this,"+data[k].id+")\" >" +
						"<a title='"+data[k].songName+"' onclick=\"loadSong('"+data[k].id+"')\">"+na+"</a>" +
						"&emsp;<span style='visibility:hidden;' id='playBtn"+data[k].id+"'>" +
						"<font onclick='play(this,"+data[k].id+")'>▷</font>" +
						"&emsp;<font size='+1' onclick='showListName("+data[k].id+")'>+</font>" +
						"</span></span></td>" +
						
						"<td><a onclick=\"querySongsBySinger('"+data[k].singer+"')\">"+data[k].singer+"</a></td>" +
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
 * 2.根据关键词模糊搜索歌曲
 */
function querySongsByKey(){
	var key=document.getElementById("key").value;
	if(key==""){
		alert("必须输入搜索内容");
		return;
	}
	//清空原有歌曲列表
	$('#song').text('');
	$('#num').text('');
	$('#songListCom').text('');
	//加载搜索到的歌曲列表
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongs.do?key="+key,
		dataType:"json",
		success:function(data){
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
						"<td><span onmouseout=\"hidePlayBtn(this,"+data[k].id+")\" onmouseover=\"showPlayBtn(this,"+data[k].id+")\" >" +
						"<a title='"+data[k].songName+"' onclick=\"loadSong('"+data[k].id+"')\">"+na+"</a>" +
						"&emsp;<span style='visibility:hidden;' id='playBtn"+data[k].id+"'>" +
						"<font onclick='play(this,"+data[k].id+")'>▷</font>" +
						"&emsp;<font size='+1' onclick='showListName("+data[k].id+")'>+</font>" +
						"</span></span></td>" +
						
						"<td><a onclick=\"querySongsBySinger('"+data[k].singer+"')\">"+data[k].singer+"</a></td>" +
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
function querySongsBySinger(singer){
//	var key=$('#key').value;
//	alert(singer);
//	var sin=singer+"";
//	var key;
//	if(singer==""){
//		key=document.getElementById("key").value;
//	}else{
//		key=sin;
//		alert(sin);
//	}
//	if(key==""){
//		alert("必须输入搜索内容");
//		return;
//	}
	//清空原有歌曲列表
	$('#song').text('');
	$('#num').text('');
	$('#songListCom').text('');
	//加载搜索到的歌曲列表
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongsBySinger.do?singer="+singer,
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
						"<td><span onmouseout=\"hidePlayBtn(this,"+data[k].id+")\" onmouseover=\"showPlayBtn(this,"+data[k].id+")\" >" +
						"<a title='"+data[k].songName+"' onclick=\"loadSong('"+data[k].id+"')\">"+data[k].songName+"</a>" +
						"&emsp;<span style='visibility:hidden;' id='playBtn"+data[k].id+"'>" +
						"<font onclick='play(this,"+data[k].id+")'>▷</font>" +
						"&emsp;<font size='+1' onclick='showListName("+data[k].id+")'>+</font>" +
						"</span></span></td>" +
						
						"<td>"+	singer+"</td>" +
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
 * 4.强力搜索功能，可根据搜索关键词搜索歌曲名、歌手、歌词
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
	$('#songListCom').text('');
	//加载搜索到的数据
	$.ajax({
		type:"Get",
		async:false,
		url:"../strongQuerySongs.do?key="+key,
		dataType:"json",
		success:function(data){
			for(var k=0;k<data[0].length;k++){//歌曲
				var na=data[0][k].songName+"";
				if(na.length>9){
					na=na.substring(0, 9)+"...";
				}
				na=na.replace(key, "<font color='red'>"+key+"</font>")
				na=na+"("+data[0][k].playNum+")";
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
						"<td><span onmouseout=\"hidePlayBtn(this,"+data[0][k].id+")\" onmouseover=\"showPlayBtn(this,"+data[0][k].id+")\" >" +
						"<a title='"+data[0][k].songName+"' onclick=\"loadSong('"+data[0][k].id+"')\">"+na+"</a>" +
						"&emsp;<span style='visibility:hidden;' id='playBtn"+data[0][k].id+"'>" +
						"<font onclick='play(this,"+data[0][k].id+")'>▷</font>" +
						"&emsp;<font size='+1' onclick='showListName("+data[0][k].id+")'>+</font>" +
						"</span></span></td>" +
						
						"<td><a onclick=\"querySongsBySinger('"+data[0][k].singer+"')\">"+data[0][k].singer+"</a></td>" +
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
						"<td><span onmouseout=\"hidePlayBtn(this,"+data[1][k].id+")\" onmouseover=\"showPlayBtn(this,"+data[1][k].id+")\" >" +
						"<a title='"+data[1][k].songName+"' onclick=\"loadSong('"+data[1][k].id+"')\">"+na+"</a>" +
						"&emsp;<span style='visibility:hidden;' id='playBtn"+data[1][k].id+"'>" +
						"<font onclick='play(this,"+data[1][k].id+")'>▷</font>" +
						"&emsp;<font size='+1' onclick='showListName("+data[1][k].id+")'>+</font>" +
						"</span></span></td>" +
						
						"<td><a onclick=\"querySongsBySinger('"+data[1][k].singer+"')\">"+singer+"</a></td>" +
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
						"<td><span onmouseout=\"hidePlayBtn(this,"+data[2][k].id+")\" onmouseover=\"showPlayBtn(this,"+data[2][k].id+")\" >" +
						"<a title='"+data[2][k].songName+"' onclick=\"loadSong('"+data[2][k].id+"')\">"+na+"</a>" +
						"&emsp;<span style='visibility:hidden;' id='playBtn"+data[2][k].id+"'>" +
						"<font onclick='play(this,"+data[2][k].id+")'>▷</font>" +
						"&emsp;<font size='+1' onclick='showListName("+data[2][k].id+")'>+</font>" +
						"</span></span></td>" +
						
						"<td><a onclick=\"querySongsBySinger('"+data[2][k].singer+"')\">"+data[2][k].singer+"</a></td>" +
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
 * 5.加载指定歌单
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
	xu=pList.length-1;//将歌单歌曲数量赋值给xu，以使可以正常的往歌单所形成的播放列表中添加歌曲
	showList();//刷新播放列表
	t=0;
	//清空原有歌曲列表
	$('#song').text('');
	$('#num').text('');
	$('#songListCom').text('');
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
				na=na+"("+data[k].playNum+")";
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
						"<td><span onmouseout=\"hidePlayBtn(this,"+data[k].id+")\" onmouseover=\"showPlayBtn(this,"+data[k].id+")\" >" +
						"<a title='"+data[k].songName+"' onclick=\"loadSong('"+data[k].id+"')\">"+na+"</a>" +
						"&emsp;<span style='visibility:hidden;' id='playBtn"+data[k].id+"'>" +
						"<font onclick='play(this,"+data[k].id+")'>▷</font>" +
						"&emsp;<font size='+1' onclick='showListName("+data[k].id+")'>+</font>" +
						"&emsp;<font size='+1' title='从\""+title+"\"移除\""+data[k].songName+"\" ' onclick='remove("+data[k].id+","+lid+")'>-</font></span></span></td>" +
						
						"<td><a onclick=\"querySongsBySinger('"+data[k].singer+"')\">"+data[k].singer+"</a></td>" +
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
	$('#songListCom').append("<br><textarea id='comment' column='12' rows='4' placeholder='说你想说'></textarea>");
	$('#songListCom').append("<span style='background:gray;color:white;' onclick='commentMusic(1,"+lid+")'>发表</span>");
	$('#songListCom').append("<div id='comments'></div>");
	loadComment("1",lid);
}
/**
 * 6.播放歌曲	
 * 1.正在播放的歌名颜色特殊（黑色）显示
 * 2.网页title改为正在播放歌曲名
 * @param k 歌曲在数据库表中的序号
 */
function play(obj,k) {
	var singerImg=document.getElementById("singer");//动态搞笑图
	//$(obj).parents('tr').remove();
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
//	loadSong(nowplay);//播放歌曲默认打开歌曲详细信息
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
				ssid=sid.substring(0, sid.indexOf(".html"));
			}else{
				ssid=sid;
			}
			url="http://localhost/util/songs/"+ssid+".mp3";
//			url="file:///F:/Music/songs/"+ssid+".mp3";
			name=data.songName+"-"+data.singer;
			//设置图片
			var img=data.imgPath+"";
			var artist="";
			if(img.substring(img.length-4, img.length)==".jpg"&&navigator.onLine){//网易云音乐的img
				artist="http://p1.music.126.net/"+img;
			}else if(img.substring(0,4)=="T002"&&navigator.onLine){//QQ音乐的img
				artist="https://y.gtimg.cn/music/photo_new/"+img+".jpg?max_age=2592000";//https://y.gtimg.cn/music/photo_new/T002R300x300M000002iWU6B2ZvA8V.jpg?max_age=2592000
			}else{
				var as1=new Array("me","me080501","me080502","me1","me2","me3",
						"zhaoying","zhaoying1","zhaoying2","zhaoying3","zhaoying4","zhaoying5",
						"zhaoying6","zhaoying7","zhaoying8","zhaoying9","zhaoying10",
						"xiehaole","xiehaole1","xiehaole2","xiehaole3","xiehaole4","xiehaole5",
						"tangxinying","tangxinying1","tangxinying2",
						"shiying","yingying1","shiying2");
				var as=new Array("beauty1","beauty2","beauty3","beauty4","beauty5","beauty6","beauty7",
						"beauty8","beauty9","beauty10","beauty11","beauty12","beauty13","beauty14",
						"beauty15","beauty16","beauty17","beauty18","beauty19","beauty20");
				var n=random(0, as.length-1);
				artist="../image/beauty/me080501.jpg";
			}
			artist="url("+artist+")";
			document.getElementById('plist').style.backgroundImage=artist;
			//加载歌词资源
			var au="../loadLyric3.jsp?sid="+sid+"&type=2&from=1";
			$.ajax({
				type:"Get",
				async:false,
				url:au,
				dataType:"text",
				success:function(data){
					document.getElementById("alyric").innerHTML="<center><font color='yellow' onclick='loadSong("+nowplay+")'>"+name+"</font></center>"+data;
				}
			});
			
		}
	});
//	mini.alert(url);
	var p=document.getElementById("audio");
	p.setAttribute("src", url);
	singerImg.src="../image/sing.gif";
	//设置播放按钮为播放状态
	var btn=document.getElementById("pause");
	btn.src="../image/play.png";
	p.play();//播放
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
	}else if(mode=="single"){//单曲循环播放
		t=t;
	}else{
		t=Math.round(Math.random()*(pList.length-1-0)+0); 
	}
	mini.showTips(myAlert(t))
	play(this,pList[t]);
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
	}else if(mode=="single"){//单曲循环
		t=t;
	}else{//随机播放
		t=Math.round(Math.random()*(pList.length-1-0)+0); 
	}
	play(this,pList[t]);
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
		p.pause();
		mini.showTips(myAlert('已到时间'));
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
	var url="../loadLyric3.jsp?sid="+sid+"&time="+getTime(ctime)+"&type=1&from=0";
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
 * 12.音量减，音量进度条随之变化
 */
function minus() {
	var p=document.getElementById("audio");
	var vo=document.getElementById("voice");
	if(vo.value==0){
		mini.showTips(myAlert("使出了洪荒之力，音量已经到静音了，不能再减了"));
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
		mini.showTips(myAlert("哎呀，音量已经到最大了，不能再加了"));
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
	var e = event || window.event || arguments.callee.caller.arguments[0];//组合键
	//shiftKey、altKey、ctrlKey、
	//以下两种均可1.状态转为字符串再和"false"比较，2.直接和false比较
	//var sta=p.paused+"";//状态转为String
	//if(event.keyCode=="80"&&sta=="false"){//80=p
	//	p.pause();
	//}
//	alert(e.ctrlKey)
	/*if(event.keyCode=="80"&&e.altKey&&p.paused==false){//80=p   32空格键
		p.pause();
		btn.src="../image/pause.png";
	}else if(event.keyCode=="80"&&e.altKey&&p.paused==true){
		p.play();
		btn.src="../image/play.png";
	}*/
	if(event.keyCode=="80"&&e.altKey){//9-06用此代替上面，解决了bug
		pause_play();
	}
	else if(event.keyCode=="37"&&e.altKey){//左键
		preview();
	}else if(event.keyCode=="39"&&e.altKey){//右键
		next();
	}else if(event.keyCode=="38"&&e.altKey){//上键
		add();
	}else if(event.keyCode=="40"&&e.altKey){//下键
		minus();
	}else if(event.keyCode=="67"&&e.altKey){//C键--切换播放模式
		change();
	}else if(event.keyCode=="66"&&e.altKey){//B键--改变页面背景
		changeBack();
	}else if(event.keyCode=="65"&&e.altKey){//A键--快退十秒
		back(2);
	}else if(event.keyCode=="68"&&e.altKey){//D键--快进十秒
		moveon(2);
	}else if(event.keyCode=="81"&&e.altKey){//Q键--快退5秒
		back(1);
	}else if(event.keyCode=="69"&&e.altKey){//E键--快进5秒
		moveon(1);
	}else if(event.keyCode=="83"&&e.altKey){//S键--
		showHide();
	}
}

//检测当前播放是否结束，若是，则播放下一首
window.setInterval("monitor()", 1000);
document.addEventListener("keydown", keydown);
/**
 * 14设置倒计时时间
 * @param min
 */
function setCount(min){
//	alert(min);
	var sec=60*parseInt(min);
	document.getElementById("countDown").innerText=sec+"";
}

/**
 * 15点击新增后显示歌曲信息输入表单
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
 * 16.录歌曲
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
				mini.alert(res.msg);
			}
		}
	});
	querySongs('10');
}

/**
 * 17.将id为id的歌曲添加至播放列表
 * @param id
 * @returns 0表示添加失败，1表示添加成功
 */
function addList(id){
	if(pList.length>101){//歌曲播放列表中大于80首歌时将播放列表清空
		pList=new Array();
	}
	for(var j=0;j<pList.length;j++){//如果播放列表已含已选歌曲，则再不添加
		if(pList[j]==id)
			return "0";//添加失败，已存在
	}
	pList[xu]=id;
	xu++;
	showList();//每添加一首刷新一次播放列表
	return "1";//添加成功
}
/**
 * 18.刷新播放列表
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
				$('#plist').append("<tr>" +
//						"<td width='30%'><span title='"+data[k].songName+"' onclick=\"play(this,'"+data[k].id+"')\">"+na+"</span></td>" +
							
						"<td><span onmouseout=\"hidePlayBtn(this,"+data[k].id+",'1')\" onmouseover=\"showPlayBtn(this,"+data[k].id+",'1')\" >" +
						"<span style='color:purple' title='"+data[k].songName+"' onclick=\"loadSong('"+data[k].id+"')\">"+na+"</span>" +
						"&emsp;<span style='visibility:hidden;' id='playBtns"+data[k].id+"'>" +
						"<font onclick='play(this,"+data[k].id+")'>▷</font>" +
						"&emsp;<font size='+1' onclick='showListName("+data[k].id+")'>+</font>" +
						"</span></span></td>" +
						
						"<td width='30%'>"+data[k].singer+"</td><td  width='10%'>"+data[k].duration+"</td>" +
						"</tr>");
			}
		}
	});
}
/**
 * 19.显示与隐藏播放列表和歌词
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
 * 20.弹出歌单框
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
 * 21.关闭添加至歌单或播放列表的弹框
 */
function closes(){
	document.getElementById("addSong").style.display="none";
	document.getElementById("newList").style.display="none";
}
/**
 * 22.添加某首歌至id为l_id的歌单
 * @param l_id
 */
function addToList(l_id){
	var id=document.getElementById("id").innerHTML;//歌曲id
	var name=getNameById(id);//歌曲名
	var lName;//要添加至的歌单名
	if(l_id!="0"){
		lName=getLNameByLId(l_id);
	}
	if(l_id=="0"){//为0时添加至播放列表
		var sof=addList(id)+"";
		if(sof=="0"){
			mini.showTips(myAlert("添加失败，播放列表中已存在该首歌曲！"));
		}else{
			mini.showTips(myAlert("添加至播放列表成功！"));
		}
	}else{//不为0时添加至歌单
		var r=confirm("添加'"+name+"'至歌单'"+lName+"'?");
		if(r==true){
			var url="../addToList.do?sid="+id+"&lid="+l_id;
			$.ajax({
				type:"Get",
				async:true,
				url:url,
				dataType:"text",
				success:function(data){
					mini.showTips(myAlert("添加成功"));
				},
				error:function(){
					mini.showTips(myAlert("添加成功，但出现错误"));
				}
			});
		}else{
			mini.showTips(myAlert("已取消"));
		}
		loadSongList();
	}
}
/**
 * 23.从歌单移除歌曲
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
				mini.showTips(myAlert("已移除"));
			},
			error:function(){
				mini.showTips(myAlert("移除成功，但出现错误"));
			}
		});
		loadSongList();
	}
	else{
		mini.showTips(myAlert("已取消"));
	}
	showList();//刷新播放列表
}
/**
 * 24.新建歌单点击函数
 * 08-25函数名由newList改为createNewList，原函数名无法使用，即无法被调用
 * 估计原因和在映射文件xml文件中使用desc一样吧，和js内置函数重名了
 */
function createNewList(){
	document.getElementById("addSong").style.display="none";//关闭添加至歌单或播放列表的弹框
	document.getElementById("newList").style.display="inline-block";//打开新建歌单的弹框
//	document.getElementById("id").innerHTML=id;
}
/**
 * 25.创建歌单
 */
function create(){
	var name=document.newList.songlist.value+"";
	var desc=document.newList.listdesc.value+"";
	if(name==""||desc==""){
		alert("部分信息为空");
		return;
	}
	var url="../newList.do?name="+name+"&desc="+desc+"&songIds=&creator=LongBro";
	$.ajax({
		type:"Get",
		async:false,
		url:url,
		dataType:"Json",
		success:function(data){
			
		}
	});
	mini.showTips(myAlert("歌单创建成功"));
	document.getElementById("addSong").style.display="none";//关闭添加至歌单或播放列表的弹框
	document.getElementById("newList").style.display="none";//打开新建歌单的弹框
}
/**
 * 26.根据歌曲id得到歌曲名
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
 * 26.根据歌曲id得到歌曲名
 * @param id
 * @returns {String}
 */
function getNameBySId(sourceId){
	var name="";
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongBySId.do?sourceId="+sourceId,
		dataType:"Json",
		success:function(data){
			name=data.songName+"-"+data.singer;
		}
	});
	return name;
}
/**
 * 27.根据歌单id得到歌单的信息
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
//28.生成min-max的随机整数（包括min,max）---随机生成图片名时使用
//var random = function(min, max){
function random(min, max){
	// 若max不存在 min 赋值给max,并重新赋值min
	if(max == null){
		max = min;  
		min = 0;
	}
	return min+ Math.floor(Math.random()*(max-min+1))
}
/**
 * 29.全选 or 全不选
 */
function changed(){
	var sc=document.getElementById("songscheck");
	if(sc.checked==true){
		for(var i=0;i<550;i++){
			var sci=document.getElementById("songs"+i);
			sci.checked=true;
		}
	}
	if(sc.checked==false){
		for(var i=0;i<550;i++){
			var sci=document.getElementById("songs"+i);
			sci.checked=false;
		}
	}
}
/**
 * 30.加载歌曲详细信息，及歌曲下的评论
 * @param id
 */
function loadSong(id){
//	$('#songsList').text('');//不能轻易情况
	$('#song').text('');
	$('#num').text('');
	$('#songListCom').text('');
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongById.do?id="+id,
		dataType:"Json",
		success:function(data){
			//此处若用全局变量“sid”，会导致播放歌曲时切换到别的歌曲显示的单句歌词非当前播放而是切换到的歌曲
			var dsid=data.sourceId+"";
			name=data.songName+"("+data.playNum+")";
			//设置图片
			var img=data.imgPath+"";
			var artist="";
			if(img.substring(img.length-4, img.length)==".jpg"&&navigator.onLine){//网易云音乐的img
				artist="http://p1.music.126.net/"+img;
			}else if(img.substring(0,4)=="T002"&&navigator.onLine){//QQ音乐的img
				artist="https://y.gtimg.cn/music/photo_new/"+img+".jpg?max_age=2592000";//https://y.gtimg.cn/music/photo_new/T002R300x300M000002iWU6B2ZvA8V.jpg?max_age=2592000
			}else{
				var as=new Array("me","me080501","me080502","me1","me2","me3",
						"zhaoying","zhaoying1","zhaoying2","zhaoying3","zhaoying4","zhaoying5",
						"zhaoying6","zhaoying7","zhaoying8","zhaoying9","zhaoying10",
						"xiehaole","xiehaole1","xiehaole2","xiehaole3","xiehaole4","xiehaole5",
						"tangxinying","tangxinying1","tangxinying2",
						"shiying","shiying1","shiying2");
				var n=random(0, as.length-1);
				artist="../image/artist/me080501.jpg";
			}
			if(dsid.substring(dsid.length-5)==".html"){
				url="http://link.hhtjim.com/qq/"+dsid.substring(0, dsid.length-5)+".mp3";
			}else if(dsid.substring(dsid.length-3)==".kw"){
				url="http://link.hhtjim.com/kw/"+sid.substring(0, dsid.length-3)+".mp3";
			}else if(dsid.substring(dsid.length-4)==".mp3"){
				url=dsid;
			}else{
				url="http://link.hhtjim.com/163/"+dsid+".mp3";
			}			
			
			var surl="";//源网址
			var web=data.website+"";
			if(web=="QQ音乐"){
				surl="https://y.qq.com/n/yqq/song/"+dsid;
			}else if(web=="网易云音乐"){
				surl="https://music.163.com/#/song?id="+dsid;
			}else if(web=="酷我音乐"){
				surl="http://www.kuwo.cn/play_detail/"+dsid;
			}
			var purl="http://localhost/util/songs/"+dsid+".mp3";
			
			//加载歌词资源
			var au="../loadLyric3.jsp?sid="+dsid+"&type=2&from=0";
			$.ajax({
				type:"Get",
				async:false,
				url:au,
				dataType:"text",
				success:function(data1){
					var alyric=data1+"";//所有歌词
					var plyric=alyric.substring(0, 222);
					document.getElementById("alyric").innerHTML="<center><font color='yellow'>"+name+"</font></center>"+data1;
					$('#songListCom').append("歌曲本站ID:"+id+"<br>资源id:"+dsid+"<br>歌曲名:<a onclick='play(this,"+id+")'>"+name+"</a>&nbsp;<font size='+1' onclick='showListName("+id+")'>+</font>");
					$('#songListCom').append("<br>歌手:<a onclick=\"querySongsBySinger('"+data.singer+"')\">"+data.singer+"</a><br>专辑:"+data.album);
					$('#songListCom').append("<br>网站来源:"+data.website+"<br>1.源资源路径:<br>"+surl+"<br>"+url);
					$('#songListCom').append("<br>2.资源路径:<br>"+purl);
					$('#songListCom').append("<br>3.歌词路径:<br>E:\\AAAA\\alyric");
					if(web=="网易云音乐"){
						$('#songListCom').append("<br>"+"<a href='http://music.163.com/api/song/lyric?id="+dsid+"&lv=1&kv=1&tv=-1' target='_blank'>网易歌词路径</a>");
					}
					$('#songListCom').append("<br>4.存储路径:<br>D:\\apache-tomcat-8.5.35\\webapps\\util\\songs<br>F:\\Music\\songs");
					$('#songListCom').append("<br><img src='"+artist+"' style='width:140px;height:150px'><br>");
					$('#songListCom').append("<center><font color='red'>"+name+"</font></center>" +
							"<div id='lyrics'>"+plyric+"</div>" +
							"<center><a id='findAllBtn' style='' onclick=\"loadLyric('0','"+dsid+"')\">展开全部</a><a style='display:none' onclick=\"loadLyric('1')\">折叠全部</a></center>");
					
					$('#songListCom').append("<br><textarea id='comment' column='12' rows='4' placeholder='说你想说'></textarea>");
					$('#songListCom').append("<span style='background:gray;color:white;' onclick='commentMusic(0,"+id+")'>发表</span>");
					$('#songListCom').append("<div id='comments'></div>");
				}
			});
			loadComment("0",id);
		}
	});
}
/**
 * 31.加载全部歌词
 * type=0时全部，type=1时加载部分
 * disd表示要加载的歌词的id，不能用sid（当前播放歌曲），要加载歌词并不一定是当前播放的歌曲
 */
function loadLyric(type,dsid){
	$('#lyrics').text('');
	//以下代码加上会导致部分歌曲无法展开全部歌词，去掉会使另外一部分歌曲显示两个“展开全部”按钮
//	document.getElementById("findAllBtn").style.display="none";
	//加载歌词资源
	var au="../loadLyric3.jsp?sid="+dsid+"&type=2&from=0";
	$.ajax({
		type:"Get",
		async:false,
		url:au,
		dataType:"text",
		success:function(data){
			var alyric=data+"";//所有歌词
			var plyric=alyric.substring(0, 222);

			if(type==0){
				$('#lyrics').append(alyric+"<center><a style='display:none' onclick=\"loadLyric('0','"+dsid+"')\">展开全部</a><a onclick=\"loadLyric('1','"+dsid+"')\">折叠全部</a></center>");
			}else if(type==1){
				$('#lyrics').append(plyric+"<center><a onclick=\"loadLyric('0','"+dsid+"')\">展开全部</a><a style='display:none' onclick=\"loadLyric('1','"+dsid+"')\">折叠全部</a></center>");
			}
		}
	});
}
/**
 * 32.随机生成一个包含num首歌曲的播放列表
 */
function randomPList(num){
	pList=new Array();//清空原播放列表数组
	for(var i=0;i<num;i++){
		var n=random(1, 565);
		pList[i]=n;
	}
	var songIds=","+pList+",";
	var url="../newList.do?name="+formatW2(new Date()+"")+"&desc="+formatW2(new Date()+"")+"随机播放歌曲创建的歌单&songIds="+songIds+"&creator=0";
	$.ajax({
		type:"Get",
		async:false,
		url:url,
		dataType:"Json",
		success:function(data){
			
		}
	});
	xu=num;//将xu置为歌曲数量以供向播放列表中添加歌曲
	showList();
	loadSongList();
}
/**
 * 33.加载歌单
 * 08-25将以下js从songsList.jsp中拆分出来作为函数，
 * 以实现向歌单中添加歌曲后可同步，不必再刷新浏览器
 */
function loadSongList(){
	$('#songList').text('');//加载歌单歌曲时的下拉列表
	$('#songLists').text('');//添加至歌单的选择框
	//加载歌单
	$.ajax({
		type:"Get",
		async:false,
		url:"../querySongList.do?creator=LongBro",
		dataType:"Json",
		success:function(data){
			$('#songList').append("<option>歌单</option>");
			$('#songLists').append("<span style='color:white;' onclick=\"addToList('0')\">播放列表</span>");
			for(var k=0;k<data.length;k++){
				$('#songList').append("<option value='"+data[k].songs+"】"+data[k].name+"】"+data[k].id+"'>"+data[k].name+"</option>");
				$('#songLists').append("<li onclick=\"addToList('"+data[k].id+"')\">"+data[k].name+"</li>");
			}
		}
	});
}
/**
 * 34.加载热歌榜
 * @param num
 */
function loadHotSongs(num){
	pList=new Array();//清空原播放列表数组
	$.ajax({
		type:"Get",
		async:false,
		url:"../queryHotSongs.do?num="+num,
		dataType:"Json",
		success:function(data){
			for(var k=0;k<data.length;k++){
				pList.push(data[k].id);
			}
		}
	});
	xu=num;//将i置为歌曲数量以供向播放列表中添加歌曲
	showList();
}
/**
 * 35.显示播放按钮和添加按钮
 * @param obj
 * @param id
 */
function showPlayBtn(obj,id,type){
	if((type+"")=="1"){//播放列表的
		document.getElementById("playBtns"+id).style.visibility="visible";
	}else{
		document.getElementById("playBtn"+id).style.visibility="visible";
	}
}
/**
 * 36.隐藏播放按钮和添加按钮
 * @param obj
 * @param id
 */
function hidePlayBtn(obj,id,type){
	if((type+"")=="1"){//播放列表的
		document.getElementById("playBtns"+id).style.visibility="hidden";
	}else{
		document.getElementById("playBtn"+id).style.visibility="hidden";
	}
}

/**
 * 37.实现单句歌词可随鼠标移动
 */
//$(document).mousedown(function(){
//    $(this).mousemove(function(e){
//		$("#lyric").css({ "left": e.pageX+"px", "top": e.pageY+"px" }); 
//        $(document).mouseup(function(){
//            $(this).unbind('mousemove');
//        })
//    })
//})
//document.oncontextmenu=new Function("event.returnValue=false");
//document.onselectstart=new Function("event.returnValue=false");
//document.oncopy=new Function("event.returnValue=false");
/**
 * 38.使用迷你UI的弹出提示功能
 * 参数：弹出的内容
 * 返回：mini.showTips的参数
 */
function myAlert(content){
	var options={//value必须加引号，不然无法生效
    		content:content,
    		state:"danger",//default|success|info|warning|danger
    		x:"center",//left|center|right
    		y:"center",//top|center|bottom
    		timeout:5000//自动消失间隔时间。默认2000（2秒）
    }; 
	return options;
}
function myAlert1(content,x,y){
	var options={//value必须加引号，不然无法生效
    		content:content,
    		state:"danger",//default|success|info|warning|danger
    		x:x,//left|center|right
    		y:y,//top|center|bottom
    		timeout:5000//自动消失间隔时间。默认2000（2秒）
    }; 
	return options;
}
//39.存放句子的数组
var sens=new Array();
//var pa=1;//初始随机推荐第一页歌曲
////根据页码加载并设置歌曲数组，下方控制每十分钟切换一次歌曲页码
//function changePage(){
//	sens=new Array();
//	pa=random(1,12);
//	$.ajax({
//		type:"Get",
//		async:false,
//		url:"../queryAllSongs.do?page="+pa,
//		dataType:"Json",
//		success:function(data){
//			//alert(data.length);
//			for(var k=0;k<data.length;k++){
//				var na=data[k].songName+"";
//				if(na.length>9){
//					na=na.substring(0, 9)+"...";
//				}
//				na=na+"("+data[k].playNum+")";
//				if((data[k].lyric+"")!="null")
//					//sens.push(na+"<br>"+data[k].lyric);
//					sens.push("<font onclick='play(this,"+data[k].id+")'>"+na+"</font>");
//			}
//		}
//	});
//}
//十分钟切换一页歌曲
//window.setInterval("changePage()", 6000);
//取消以上逻辑，切换为此逻辑，一次性加载所有页码歌曲至数组


/**
 * 40.底部栏的显示与隐藏
 */
function showBottom(){
	var status=document.getElementById("bottom").style.visibility;
	if(status=="hidden"){
		document.getElementById("bottom").style.visibility="visible";
	}else{
		document.getElementById("bottom").style.visibility="hidden";
	}
}
/**
 * 41.回车键搜索歌曲
 */
function search(){
	document.addEventListener("keydown", function onenterdown(event){
		if(event.keyCode=="13"){
			strongQuerySongs();
		}
	});
	
}
window.addEventListener("mousemove",hideBottom);
//260-536	1350-540
//260-574	1350-578
//42.隐藏底部栏
function hideBottom(){
	var width=document.body.clientWidth;//1366
	var height=document.body.clientHeight;//579
	//mini.showTips(myAlert(height))//579+1366=1945
	var e = event || window.event;
	//mini.showTips(myAlert(e.clientX + ',' + e.clientY));
	if(e.clientX>260&&e.clientX<width&&e.clientY>(height-60)&&e.clientY<height){
		document.getElementById("bottom").style.visibility="visible";
	}else{
		document.getElementById("bottom").style.visibility="hidden";
	}
}
//43.该函数实现固定底部栏
function fixBottom(){
	window.removeEventListener("mousemove",hideBottom);
//	window.releaseEvents(Event.MOUSEOVER);
	var html=document.getElementById("lock").style.display="none";
	var html=document.getElementById("unlock").style.display="inline-block";
	mini.showTips(myAlert("已固定底部栏"))
}
//44.该函数实现取消固定底部栏
function unfixBottom(){
	window.addEventListener("mousemove",hideBottom);
	var html=document.getElementById("lock").style.display="inline-block";
	var html=document.getElementById("unlock").style.display="none";
	mini.showTips(myAlert("已取消固定底部栏"));
}
//存放所有歌曲的数组
var songs=new Array();
var ii=random(1, 12);
//更快的加载网页，只随机加载一页
//for(var ii=1;ii<2;ii++){
	$.ajax({
		type:"Get",
		async:false,
		url:"../queryAllSongs.do?page="+ii,
		dataType:"Json",
		success:function(data){
			//alert(data.length);
			for(var k=0;k<data.length;k++){
				var na=data[k].songName+"";
				na=na+"("+data[k].playNum+")";
				//if((data[k].lyric+"")!="null")
					//songs.push(na+"<br>"+data[k].lyric);
				songs.push("<font onclick='play(this,"+data[k].id+")'>"+na+"</font>");
			}
		}
	});
//}
//45.随机推荐一首歌曲，并弹出以供直达播放
function randomSong(){
	mini.showTips(myAlert1("点歌曲名播放哦^-^<br>"+songs[random(0, songs.length)],"center","center"))
}
//每隔10秒钟弹出一首直达歌曲
//var thread=window.setInterval("randomSong()", 10000);
/**
 * 46.开启与关闭歌曲随机推荐 open and close Recommend
 */
function oacRecommend(opera){
	if(opera=='open'){
		thread=window.setInterval("randomSong()", 10000);
		mini.showTips(myAlert("已开启歌曲随机推荐"));
		document.getElementById("openBtn").style.display="none";
		document.getElementById("closeBtn").style.display="inline-block";
	}else{
		window.clearInterval(thread);
		mini.showTips(myAlert("已关闭歌曲随机推荐"));
		document.getElementById("openBtn").style.display="inline-block";
		document.getElementById("closeBtn").style.display="none";
	}
}