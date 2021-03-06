/**
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
var nowplay=0;//当前播放歌曲在数组中的序号
var mode="order";//播放模式---默认为顺序播放模式
var sid="";//资源id
var url="";
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
		url:"queryAllSongs.do?page="+page,
		dataType:"Json",
		success:function(data){
			//alert(data.length);
			for(var k=0;k<data.length;k++){
				
				$('#song').append("<tr>");
				$('#song').append("<td>"+data[k].id+"</td>");
				$('#song').append("<td><a title='"+data[k].desc+"' onclick=\"play('"+data[k].id+"')\">"+data[k].songName+"</a></td>");

//				$('#song').append("<td><a onclick=\"querySongsBySinger('"+data[k].singer+"')\">"+data[k].singer+"</a></td>");
				$('#song').append("<td>"+data[k].singer+"</td>");
				$('#song').append("<td>"+data[k].duration+"</td>");
				$('#song').append("<td>"+data[k].album+"</td>");
				$('#song').append("<td>"+data[k].imgPath+"</td>");
				$('#song').append("<td>"+data[k].releaseTime+"</td>");
				$('#song').append("<td>"+data[k].website+"</td>");
//				$('#song').append("<td><a href='querySongById.do?id="+data[k].id+"' target='_blank'>编辑</a></td>");
				$('#song').append("<td><a href='editSong.jsp?id="+data[k].id+"' target='_blank'>编辑</a></td>");
				$('#song').append("</tr>");
				//$('#song').append("<a href='"+data[k].sourceId+"'>"+data[k].songName+"</a><br>");
				//$('#song').append("<p class='song' style='color: black;' id='music"+k+"'"><span>"+(k+1)+".</span>"+data[k].songName+"</p>");
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
		url:"querySongs.do?key="+key,
		dataType:"json",
		success:function(data){
//			alert(data.length);
//			alert(data);
			for(var k=0;k<data.length;k++){
				
				$('#song').append("<tr>");
				$('#song').append("<td>"+data[k].id+"</td>");
				//特殊显示搜索关键字
				var na=data[k].songName+"";
				na=na.replace(key, "<font color='red'>"+key+"</font>")
				
				$('#song').append("<td><a title='"+data[k].desc+"'onclick=\"play('"+data[k].id+"')\">"+na+"</a></td>");
				$('#song').append("<td>"+data[k].singer+"</td>");
				$('#song').append("<td>"+data[k].duration+"</td>");
				$('#song').append("<td>"+data[k].album+"</td>");
				$('#song').append("<td>"+data[k].imgPath+"</td>");
				$('#song').append("<td>"+data[k].releaseTime+"</td>");
				$('#song').append("<td>"+data[k].website+"</td>");
				$('#song').append("<td><a href='editSong.jsp?id="+data[k].id+"' target='_blank'>编辑</a></td>");
				$('#song').append("</tr>");
			}
			$('#num').append("共搜索到"+data.length+"首歌曲");
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
		url:"querySongsBySinger.do?singer="+key,
		dataType:"json",
		success:function(data){
//			alert(data.length);
//			alert(data);
			
			for(var k=0;k<data.length;k++){
				
				$('#song').append("<tr>");
				$('#song').append("<td>"+data[k].id+"</td>");
				//特殊显示搜索关键字
				var sin=data[k].singer+"";
				sin=sin.replace(key, "<font color='red'>"+key+"</font>")
				$('#song').append("<td><a title='"+data[k].desc+"' onclick=\"play('"+data[k].id+"')\">"+data[k].songName+"</a></td>");
				$('#song').append("<td>"+sin+"</td>");
				$('#song').append("<td>"+data[k].duration+"</td>");
				$('#song').append("<td>"+data[k].album+"</td>");
				$('#song').append("<td>"+data[k].imgPath+"</td>");
				$('#song').append("<td>"+data[k].releaseTime+"</td>");
				$('#song').append("<td>"+data[k].website+"</td>");
				$('#song').append("<td><a href='editSong.jsp?id="+data[k].id+"' target='_blank'>编辑</a></td>");
				$('#song').append("</tr>");
			}
			$('#num').append("共搜索到"+data.length+"首歌曲");
		}
	});
}
/**
 * 播放歌曲	
 * 1.正在播放的歌名颜色特殊（黑色）显示
 * 2.网页title改为正在播放歌曲名
 * @param k 歌曲的序号
 */
function play(k) {
//	k=parseInt(k)+1;
	nowplay=k;
//	alert(nowplay);

	var name="";//歌名
	$.ajax({
		type:"Get",
		async:false,
		url:"querySongById.do?id="+k,
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
			url="http://localhost/LongMusic/songs/"+data.songName+".mp3";
			name=data.songName+"-"+data.singer;
		}
	});
//	alert(url);
	var p=document.getElementById("audio");
	p.setAttribute("src", url);
	p.play();
	document.title=name+"-正在播放|LongBro音乐";
}
/**
 * 5.暂停与播放的切换功能
 * 若当前在播放点击后暂停；若当前出于暂停状态，则点击后播放
 * 播放与暂停按钮相应改变
 */
function pause() {
	var p=document.getElementById("audio");
	var btn=document.getElementById("pause");
	if(p.paused==false){//原本是播放状态，则置为暂停状态
		p.pause();
		btn.src="image/pause.png";
	}else{
		p.play();
		btn.src="image/play.png";
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
	if(mode=="order"){
		var now=parseInt(nowplay);
		//判断当前播放是否为最后一曲，若是，则播放第一曲，反之，播放下一曲
		if(now>296){
			now=0;
		}else{
			now=now+1;
		}
	}else if(mode=="random"){//随机播放
		//产生一个歌曲数量以内的随机数，作为歌曲索引播放			
		now=Math.round(Math.random()*(296-1-0)+0); 
	}else{//单曲循环
		now=parseInt(nowplay);
	}
	play(now);
}
/**
 * 7.实现上一曲按钮功能------
 * 顺序播放模式时播放上一首，
 * 随机播放模式时随机生成一个歌曲序号来播放
 * 单曲循环模式时只播放当前歌曲
 */
function preview(){
	if(mode=="order"){//顺序播放
		var now=parseInt(nowplay);//当前播放序号转为整型
		//若当前播放不为第一首，点击上一首将当前播放序号指向歌曲数量-1，反之将当前播放指向当前播放-1
		if(now==0){
			now=296;
		}else{
			now=now-1;	
		}
	}else if(mode=="random"){//随机播放
		//产生一个歌曲数量以内的随机数，作为歌曲索引播放			
		now=Math.round(Math.random()*(296-1-0)+0); 
	}else{
		now=parseInt(nowplay);
	}
	play(now);
}
/**
 * 8.切换播放模式
 */
function change(){
	var m=document.getElementById("mode");
	if(mode=="order"){//当前模式为顺序，则切换为随机
		mode="random";
		m.src="image/play_random.png";//http://longqcloud/Minimusic/
		m.title="随机播放--C键切换";
	}else if(mode=="random"){//当前播放为随机，则切换为单曲
		mode="single";
		m.src="image/play_single.png";
		m.title="单曲循环--C键切换";
	}else{//当前播放为单曲，则切换为顺序循环
		mode="order";
		m.src="image/play_order.png";
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
	if(p.networkState==3){//2正常，3异常
//		alert("出现了异常~_~");
		next();
	}
    var time=p.duration+"";
	var ctime=p.currentTime+"";
    //设置音乐播放进度条，和音乐已播放时长和总时长
    per.innerText=getTime(ctime)+"/"+getTime(time);
	pro.value=(p.currentTime/p.duration*100.00);
//	alert(sid);
	var url="loadLyric3.jsp?sid="+sid+"&time="+getTime(ctime)+"&type=1";
	$.ajax({
		type:"Get",
		async:false,
		url:url,
		dataType:"text",
		success:function(data){
			//alert(data);
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
		btn.src="image/pause.png";
	}else if(event.keyCode=="80"&&p.paused==true){
		p.play();
		btn.src="image/play.png";
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
	}
}
//检测当前播放是否结束，若是，则播放下一首
window.setInterval("monitor()", 1000);
document.addEventListener("keydown", keydown);