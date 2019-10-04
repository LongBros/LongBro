window.setInterval(clock, 1000);
//1.闹铃
function clock(){
	var ar=document.getElementById("array").innerText;//因在Ajax中赋完值出来又被清空，故加个“array”作为中间值赋值
	//Courier New,serif			font-style:italic;													<h1 class="long-shadow"><span class="animateTitle1"></span></h1>
	var senM=new Array("不是花中偏爱菊，此花开尽更无花","兰有秀兮菊有芳，怀佳人兮不能忘",
					   "秋菊有佳色，裛露掇其英","耐寒唯有东篱菊，金粟初开晓更清 —— 唐 白居易《咏菊》",
					   "问篱边黄菊，知为谁开 —— 宋 · 秦观《满庭芳·碧水惊秋》");
	var senN=new Array("下班啦，别忘记打卡哦~一天的工作快结束了，让忙碌的自己歇歇^-^",
						"下班啦，别忘记打卡哦~放下工作的压力，用笑容面对身边的人^-^",
					   "下班啦，别忘记打卡哦~因为努力，今天的你比昨天更强大^-^",
					   "下班啦，别忘记打卡哦~生活是忙忙碌碌，重要的是开开心心^-^"
	);
	var songs=new Array("1369798757","4875075","287035","566442496","165375","165379");
	var songsName=new Array("芒种","遇到","遇见","浪人琵琶","专属味道","小星星");
	var all=new Date();
	var year=all.getFullYear();
	var mon=addZero(all.getMonth()+1);
	var day=addZero(all.getDate());
	var hour=addZero(all.getHours());
	var min=addZero(all.getMinutes());
	var sec=addZero(all.getSeconds());
	var time=year+"-"+mon+"-"+day+" "+hour+":"+min+":"+sec;
	var time1=hour+":"+min+":"+sec;
	var song=document.getElementById("song");
	var als=ar.split("&,");
	//alert(als[0]);
	for(var i=0;i<als.length;i++){
		var t=als[i].split("&")[0];//闹铃时间
		var m=als[i].split("&")[1];//闹铃音乐
		var tip=als[i].split("&")[2];//闹铃提示
		//alert(tip);
		if(time1==t){
			var sName=getNameBySId(m);//
			alert(tip);
		    mini.showTips(myAlert("即将为你播放铃声^-^  "+sName))
			song.src="https://link.hhtjim.com/163/"+m+".mp3";
		}
	}
//	if(time1=="08:35:00"||time1=="08:45:30"||time1=="09:00:30"){//早上打卡时间
//		var w=random(0,senM.length-1);
//		var s=random(0,songs.length-1);
//		alert(time1+"来自LongBro的话~\n上班的时间到了，努力的你真棒，加油么么哒\n"+senM[w]+"\n\n将为你播放随机歌曲《"+songsName[s]+"》，如不方便听请关闭此网页或关掉声音，谢谢");
//		song.src="https://link.hhtjim.com/163/"+songs[s]+".mp3";
//	}else if(time1=="18:00:00"){
//		var w=random(0,senN.length-1);
//		var s=random(0,songs.length-1);
//		alert(senN[w]+"\n\n将为你播放随机歌曲《"+songsName[s]+"》，如不方便听请关闭此网页或关掉声音，谢谢");
//		song.src="https://link.hhtjim.com/163/"+songs[s]+".mp3";
//	}   		
	document.getElementById("time").innerText=time;
}
//2.补零格式化
function addZero(param){
	var param=param+"";
	if(param.length==1){
		param="0"+param;
	}
	return param;
}
	
 //3.生成min-max的随机整数（包括min,max）
//var random = function(min, max){
function random(min, max){
	// 若max不存在 min 赋值给max,并重新赋值min
	if(max == null){
		max = min;  
		min = 0;
	}
	return min+ Math.floor(Math.random()*(max-min+1))
}
//4.随机设置背景色和开心图片
function randBack(){
	var colors=new Array("pink","orange","cyan","purple");//"white",
	var m=random(colors.length-1);
	//随机设置背景色
	var body=document.getElementById("body");
	body.style.background=colors[m];

	//随机设置图片
	var pics=new Array("smile1.png","smile2.png","smile3.png","smile4.png","smile5.png","smile6.gif","smile7.jpg");
	var n=random(pics.length-1);
	//alert(pics[n])
	document.getElementById("smilePic").src="image/picture/"+pics[n];
}
//5.显示提示语
function showTips(){
	var title="网页版实用小闹铃<br>主要面向对象：从事电脑工作的小伙伴<br>使用方法：浏览器" +
			"基本是电脑工作者每日必使用的一款软件，通过浏览器打开网页<br>作用举例：1.每日上" +
			"下班钉钉等打卡自动提示，<br>2.上班情况下如果有事(比如某个群定好上午十点要发红" +
			"包)需定闹铃使用手机会有铃声影响";
	document.getElementById("tips").innerHTML=title;
}
//6.隐藏提示语
function hideTips(){
	document.getElementById("tips").innerHTML="";
}
//7.打开、关闭添加闹铃框
function addAlarm(){
	var alarm=document.getElementById("newAlarm");
	alarm.style.display="inline-block";
	closeAll();
}
function closesNew(){
	var alarm=document.getElementById("newAlarm");
	alarm.style.display="none";
}
//8.打开、关闭删除闹铃框
function minusAlarm(){
	var user=getCookie("user");
	var alarm=document.getElementById("allAlarm");
	$("#allAlarm").text("");
	$("#allAlarm").append("<span onclick='closeAll()' class='close'>X</span><br>");
	
	$.ajax({
		url:"getAlarmByUserId.do?userId="+user,
		async:true,
		type:"GET",
		dataType:"Json",
		success:function(data){
			for(var i=0;i<data.length;i++){
				$("#allAlarm").append("<span style='color:red;'>"+data[i].atime+"</span>&emsp;<a onclick='delAlarm("+data[i].aid+")'>删除</a><br>");
			}
		}
	});
	var alarm=document.getElementById("allAlarm");
	alarm.style.display="inline-block";
	closesNew();
}
function closeAll(){
	var alarm=document.getElementById("allAlarm");
	alarm.style.display="none";
}
//将时间存储到cookie中相对有点麻烦，因此考虑使用id，根据昵称将该昵称下的时间点存入数据库
//9.创建闹铃
function create(){
	var user=getCookie("user");
	var hour=document.newAlarm.hour.value;
	var minute=document.newAlarm.minute.value;
	var second=document.newAlarm.second.value;
	if(hour>23||minute>59||second>59||isNaN(hour)||isNaN(minute)||isNaN(second)||hour==""||minute==""||second==""){
		mini.showTips(myAlert("时、分、秒输入不合法"));
		return "";
	}
	
	var tip=document.newAlarm.tip.value;
	var music=document.newAlarm.music.value;
	var time=formatW1(hour)+":"+formatW1(minute)+":"+formatW1(second);
//	time=encodeURI(encodeURI("&"+time));
	$.ajax({
		url:"addAlarmForUser.do?userId="+user+"&time="+time+"&tip="+tip+"&music="+music,
		async:true,
		type:"GET",
		dataType:"text",
		success:function(data){
			mini.showTips(myAlert(data));
		}
	});
	setAlarmText(user);//刷新闹铃区域
	closesNew();
}

//10.取出当前用户闹铃时间点显示至时间点区域
function getAlarmTime(){
	var user=getCookie("user")+"";
	if(user==""){//cookie中没有userId，则请求后台生成。并返回
		$.ajax({
			url:"genUserId.do",
			async:true,
			type:"GET",
			success:function(data){
				//将生成的userId赋给user，并存至cookie
				user=data;
				setCookie("user", user);
			}
		});
	}
	setAlarmText(user);
	
}
//11.设置闹铃时间点、同时向id为array的div传递包含“闹铃、提示语、音乐”的数组
function setAlarmText(user){
	var as=new Array();
	//根据userId得到其设置的闹铃
	var alarm=document.getElementById("alarm");
	$.ajax({
		url:"getAlarmByUserId.do?userId="+user,
		async:true,
		type:"GET",
		dataType:"Json",
		success:function(data){

			if(data.length>0){
				$("#alarm").text("我的闹铃:");
				for(var i=0;i<data.length;i++){
					var a=data[i].atime+"&"+data[i].amusic+"&"+data[i].atips+"&";
					as.push(a);
					if(i!=0){
						$("#alarm").append("、");
					}
					$("#alarm").append("<span style='color:red;'>"+data[i].atime+"</span>");
				}
				$("#array").text(as);
			}else{
				$("#alarm").text("点击右侧添加闹铃喔     ->");
			}
			$("#alarm").append("&emsp;<span class='add' onclick='addAlarm()' title='添加闹铃'><i class='Hui-iconfont' style='font-size:18px'>&#xe604;</i></span>&emsp;");
			$("#alarm").append("<span class='minus' onclick='minusAlarm()' title='删除闹铃'><i class='Hui-iconfont' style='font-size:18px'>&#xe609;</i></span>");
			
		}
	});
}
//12.切换背景
function changeMode(){
	var body=document.getElementById("body");
	body.style.background="pink";
	//改变背景
	randBack();
}
//13.删除闹铃-删除后需刷新
function delAlarm(id){
	var user=getCookie("user")+"";
	$.ajax({
		url:"delAlarmByAId.do?aId="+id,
		async:true,
		type:"GET",
		dataType:"Json",
		success:function(data){
			alert(data);
		}
	});
	setAlarmText(user);
	minusAlarm();
	closeAll();
}
//14.点击分享将本站网站复制至剪切板
function copyLink(){
	if (window.clipboardData) {
		window.clipboardData.clearData();
		clipboardData.setData("Text", "http://www.duola.vip");
		alert("复制成功！，请粘贴到你的QQ/微信上推荐给你的好友");
	} else if (navigator.userAgent.indexOf("Opera") != -1) {
		window.location = "http://www.duola.vip";
	} 
}
//打开、关闭个人信息框
function myInfo(){
	var person=document.getElementById("person");
	person.style.display="inline-block";
	var userId=document.getElementById("userId");
	userId.innerText=getCookie("user");
	$.ajax({
    	type:"GET",
    	url:"getByUserId.do",
    	async:true,
    	dataType:"Json",
    	success:function(data){
    		
    	}
    })
}
function closesPerson(){
	var person=document.getElementById("person");
	person.style.display="none";
}
function editInfo(){
	var userId=document.getElementById("userId");
	var userName=document.getElementById("userName");
	var sex=document.getElementById("sex");
	var sign=document.getElementById("sign");
	alert(sign)
	$.ajax({
    	type:"GET",
    	url:"updateInfo.do",
    	async:true,
    	data:{
    		userId:userId,
    		userName:userName,
    		sex:sex,
    		sign:sign
    	}
    	
    })
}
function setting(){
	var user=getCookie("user")+"";
//	window.location.href="setting.html";
//	window.location="setting.html";
	window.open("setting.html","_blank");
}
//填充我的铃声库和提示语库
function fillMyGit(){
	var userId=getCookie("user")+"";
	$.ajax({
    	url:"getByUserId.do?userId="+userId,
    	type:"GET",
    	async:true,
    	dataType:"Json",
    	success:function(data){
			var datas="";
			//我的铃声
    		var musics=data.amusics;
    		var arrs=musics.split("&&&");
    		for(var i=0;i<arrs.length;i++){
    			var music=arrs[i].split(":::");
    			datas=datas+"{'songName':'"+music[1]+"','sourceId':'"+music[0]+"'},";
    		}
    		datas="["+datas+"]";
    		mini.get("myMusic").setData(datas);

    		//我的提示语
    		var tips=data.atips;
    		datas="";
    		var ts=tips.split("&&&");
    		for(var i=0;i<ts.length;i++){
    			datas=datas+"{'tip':'"+ts[i]+"','createtime':''},";
    		}
    		datas="["+datas+"]";
    		mini.get("myTips").setData(datas);
    	}
    	
    })
}
//设置添加cookie
function setCookie(name,value)  
{  
    var Days = 30;  
    var exp = new Date();  
    exp.setTime(exp.getTime() + Days*24*60*60*1000);  
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();  
    var strsec = getsec(time);  
    var exp = new Date();  
    exp.setTime(exp.getTime() + strsec*1);  
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();  
}  
//得到name的value
function getCookie(name){
	if(document.cookie!=null){
		var co=document.cookie.split(";");
		for(var i=0;i<co.length;i++){
			//window.alert(co[i]);
			var arr=co[i].split("=");
			//window.alert(arr[1]);
			if(arr[0]==(name+"")){
				return arr[1];
			}
		}
	}
	return "";
}
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