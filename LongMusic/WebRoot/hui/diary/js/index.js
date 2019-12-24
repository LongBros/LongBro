/***************************************************************
 * 1.根据是否登录设置菜单栏2.弹出登录框3.登录校验4.打开新页面5.设置添加cookie
 * 6.得到name的value7.加载公告、收到的赞、关注……的数量并显示8.一键设置为已读
 ****************************************************************/

var user=getCookie("userId")+"";
var userNick=decodeURI(decodeURI(getCookie("userNick")+""));
loadNotice();
var homeSongId="";
var show=1;//显示日记字数
var perPageNum=0;//显示日记篇数
/**
 * 1.根据是否登录设置菜单栏
 */
function getUser(){
//	if(user==""){//cookie中没有userId，则请求后台生成。并返回
//		var source="";
//		if (document.referrer === '') {// 当没有上一级url链接的时候，返回上一级按钮的链接改成项目首页url链接地址，这也是很符合项目逻辑的
//		    source="无";
//		}else{
//			source=document.referrer;
//		}
//		$.ajax({
//			url:"../../note/userinfo/genNoteUserId.do?source="+source,
//			async:true,
//			type:"GET",
//			dataType:"text",
//			success:function(data){
//				//将生成的userId赋给user，并存至cookie
//				user=data;
//				setCookie("user", user);
//			}
//		});
//	}
	if(userNick!=""){//已登录用户，隐藏登录按钮
		document.getElementById("login").style.display="none";
		document.getElementById("exit").style.display="block";
		document.getElementById("myHome").style.display="block";
		document.getElementById("write").style.display="block";
		document.getElementById("image").innerHTML=""+userNick+"";
		document.getElementById("loginMobile").innerHTML=""+userNick+"";
	}else{
		document.getElementById("exit").style.display="none";
		document.getElementById("login").style.display="block";
		document.getElementById("myHome").style.display="none";
		document.getElementById("write").style.display="none";
		document.getElementById("image").innerHTML="请登录";
	}
}
//2.弹出登录框
function login_popup() {
    $("#loginModal").modal("show");
}
/**
 * 3.登录校验
 */
function login(){
	var acc=document.login_form.account_l.value;//账号，即哆啦id
	var pass=document.login_form.password_l.value;
//	var url="../../note/userinfo/loginNote.do?acc="+acc+"&pass="+pass;
	var url="note/userinfo/loginNote.do?acc="+acc+"&pass="+pass;
	$.ajax({
		url:url,
		async:true,
		type:"POST",
		dataType:"Json",
		success:function(data){
			if(data.code=="200"){//存cookie过程放至后端代码
				$("#loginModal").modal("hide")
				window.location="";
			}else{
				alert('未匹配到你输入的账号')
			}

		}
	});

}
//4.打开新页面
function openNewPage(which){
	if(which=="1"){
		window.open("index.html", "_blank")
	}else if(which=="2"){
		window.open("myHome.html", "_blank")
	}else if(which=="3"){
		window.open("new.html", "_blank")
	}else if(which=="4"){
		window.open("http://www.duola.vip/amaze/songsList.jsp", "_blank")
	}else if(which=="5"){
		alert("login")
	}else if(which=="6"){
		document.cookie="userAddr=;";
		document.cookie="userNick=;";
		document.cookie="userId=;";
		alert("已退出");
		window.location="";
		document.getElementById("login").style.display="block";
		document.getElementById("exit").style.display="none";
		document.getElementById("myHome").style.display="none";
		document.getElementById("write").style.display="none";
		document.getElementById("image").innerHTML="请登录";
	}
}
//5.设置添加cookie
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
//6.得到name的value
function getCookie(name){
	if(document.cookie!=null){
		var co=document.cookie.split(";");
		for(var i=0;i<co.length;i++){
			//window.alert(co[i]);
			var arr=co[i].split("=");
			if(arr[0].indexOf(name) != -1){
				//window.alert(arr[1]);
				return arr[1];
			}
		}
	}
	return "";
}
//7.加载公告、收到的赞、关注……的数量并显示
function loadNotice(){
//	var url="../../note/userinfo/queryUnReadNum.do";
	var url="note/userinfo/queryUnReadNum.do";
	$.ajax({
		url:url,
		type:"post",
		async:true,
		dataType:"json",
		data:{
			userId:user
		},
		success:function(data){
			if(data.result.length!=1){
				return;
			}
			var msgNum=data.result[0];
			var comNum=msgNum.comNum;
			var praiseNum=msgNum.praiseNum;
			var noticedNum=msgNum.noticedNum;
			var unReadNum=comNum+praiseNum+noticedNum;
			if(unReadNum>0){
				document.getElementById("cleanBtn").style.display="inline-block";
			}
			$("#unReadNum").text(unReadNum==0?'':unReadNum);
			$("#comNum").text(comNum==0?'':comNum);
			$("#praiseNum").text(praiseNum==0?'':praiseNum);
			$("#noticeNum").text(noticedNum==0?'':noticedNum);
		}
	});
}
//8.一键设置为已读
function setAsReaded(){
//	var url="../../note/userinfo/setAsReaded.do";
	var url="note/userinfo/setAsReaded.do";
	$.ajax({
		url:url,
		type:"post",
		async:true,
		data:{
			userId:user
		},
		dataType:"json",
		success:function(data){
			if(data.code==200){
				window.location="";
				alert("已设置所有未读消息为已读");
			}
		}
	});
 }
/**
 * 9.2019-10-26加载作者的信息:关注信息，互动计数，基本信息，活跃信息
 */
function loadAuthorInfo(){
//	var url="../../note/userinfo/getAuthorInfoByUserId.do?UUserId="+author;
	var url="note/userinfo/getAuthorInfoByUserId.do?UUserId="+author;
	$.ajax({
		url:url,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			var url=document.URL+"";
			var sex=getSexById(data.uuserSex);
			if(user!=author){//不是当前人时候的title显示
				document.title=""+document.title+"'"+data.uuserName+"'的日记~哆啦日记";
				if(url.indexOf("author.html")!=-1){//别的作者的页面
					var sid=data.uhomeSong;//家歌
					homeSongId=sid;
					ifAutoPlay(homeSongId);
				}
			}else{//当前人
				if(url.indexOf("diary.html")!=-1){
					document.title=document.title+"朕的日记~哆啦日记";
				}else if(url.indexOf("author.html")!=-1){//我的作者页
					document.title="朕的日记~哆啦日记";
					var sid=data.uhomeSong;//家歌
					homeSongId=sid;
					ifAutoPlay(homeSongId);
				}else if(url.indexOf("myHome.html")!=-1){//我的家园页
					document.title="我的家园~哆啦日记";
					var sid=data.uhomeSong;//家歌
					homeSongId=sid;
					ifAutoPlay(homeSongId);
				}
			}
			document.getElementById("touxiang").src="image/tx/"+data.headImage+".jpg";
			document.getElementById("userId").innerText=author;
			/*document.getElementById("userNameT").innerText=data.uuserName;*/
			document.getElementById("userName").innerText=data.uuserName;
			document.getElementById("homeSong").innerText=data.homeSongName;
			document.getElementById("signature").innerText=data.signature;
			document.getElementById("sex").innerText=sex;
			document.getElementById("joinTime").innerText="加入时间："+data.ujoinTime;
			document.getElementById("recentLogin").innerText="最近登录："+data.lastLogin;//(data.lastLogin=="")?"":
			var num=parseInt(data.dayNum+"");
			var text="";
			var n=7;//级别幅度，如一个太阳=4个月亮，一个月亮=4个星星
			if(num==0){
				text="未写过日记呢";
			}else{
				if(num/n<1){//少于n天，显示星星
//					text=getHtml(num,"star");
					text="<img src='image/star.png' style='width:16px;height:16px'>"+num;
				}else{
					if(num/(n*n)<1){//小于n*n天，显示月亮和星星
//						text=getHtml(parseInt(num/n),"moon")+getHtml(num%n,"star");
						text="<img src='image/moon.png' style='width:16px;height:16px'>"+parseInt(num/n);
						if(num%n>0){
							text=text+"<img src='image/star.png' style='width:16px;height:16px'>"+num%n;
						}
					}else{
						if(num/(n*n*n)<1){//小于n*n*n天，显示太阳、月亮和星星、例17
//							text=getHtml(parseInt(num/(n*n)),"sun")+getHtml(parseInt((num%(n*n))/n),"moon")+getHtml((num%(n*n))%n,"star");
							text="<img src='image/sun.png' style='width:16px;height:16px'>"+parseInt(num/(n*n));
							if(parseInt((num%(n*n))/n)>0){
								text=text+"<img src='image/moon.png' style='width:16px;height:16px'>"+parseInt((num%(n*n))/n);
							}
							if((num%(n*n))%n>0){
								text=text+"<img src='image/star.png' style='width:16px;height:16px'>"+(num%(n*n))%n;
							}
						}else{
//							text=getHtml(parseInt(num/(n*n*n)),"crown")+getHtml(parseInt((num%(n*n*n))/(n*n)),"sun")+getHtml(parseInt(((num%(n*n*n))%(n*n))/n),"moon")+getHtml(((num%(n*n*n))%(n*n))%n,"star");
							text="<img src='image/crown.png' style='width:16px;height:16px'>"+parseInt(num/(n*n*n));
							if(parseInt((num%(n*n*n))/(n*n))>0){
								text=text+"<img src='image/sun.png' style='width:16px;height:16px'>"+parseInt((num%(n*n*n))/(n*n));
							}
							if(parseInt(((num%(n*n*n))%(n*n))/n)>0){
								text=text+"<img src='image/moon.png' style='width:16px;height:16px'>"+parseInt(((num%(n*n*n))%(n*n))/n);
							}
							if(((num%(n*n*n))%(n*n))%n>0){
								text=text+"<img src='image/star.png' style='width:16px;height:16px'>"+((num%(n*n*n))%(n*n))%n;
							}
						}
					}
				}
			}
			document.getElementById("diaryDayNum").innerHTML="累计天数："+text;
			document.getElementById("diaryDayNum").title="累积写日记"+num+"天\r图解：星星=1天，月亮="+n+"天，太阳="+n*n+"天，皇冠="+n*n*n+"天";
			var body=document.getElementById("bodys");
			body.style.background="url(res/images/back/"+data.back+")";
		}
	});
	setInteractNum(author);
}
//10.加载并设置某人的互动数量信息
function setInteractNum(user){
//	var url="../../note/userinfo/queryInteractNum.do";
	var url="note/userinfo/queryInteractNum.do";
	$.ajax({
		url:url,
		type:"post",
		async:true,
		dataType:"json",
		data:{
			userId:user
		},
		success:function(data){
			if(data.result.length!=1){
				return;
			}
			var interactNum=data.result[0];
			var comNum=interactNum.comNum;
			var praiseNum=interactNum.praiseNum;
			var noticedNum=interactNum.noticedNum;
			$("#careNum").text(interactNum.noticeNum);
			$("#noticedNum").text(interactNum.noticedNum);
			$("#likeNum").text(interactNum.praiseNum);
			$("#storeNum").text(interactNum.storeNum);
			
			$("#visitNum").text(interactNum.visitNum);
			$("#visitedNum").text(interactNum.visitedNum);
			$("#comedNum").text(interactNum.comedNum);
			$("#praisedNum").text(interactNum.praisedNum);
		}
	});
}
//11.根据性别id获取性别
function getSexById(id){
	var sex="";
	if(id==0){
		sex="女";
	}else if(id==1){
		sex="男";
	}else{
		sex="不详";
	}
	return sex;
}

//12.判断当前登录用户是否已关注当前作者
function ifAttention(){
//	var url="../../note/notice/whetherHasNotice.do";
	var url="note/notice/whetherHasNotice.do";
	if(user==author){//当前登录用户查看自己时，不显示关注、已关注
		return;
	}
	var ifAtt=0;//0表示未关注
	$.ajax({
		url:url,
		type:"get",
		async:false,
		data:{
			NNoticer:user,
			NNoticed:author
		}, 
		dataType:"Json",
		success:function(data){

			if(data.code==200){
				ifAtt=1;
			}
		}
	});
	var attBtn=document.getElementById("attention");
	if(ifAtt==1){//已关注，则隐藏“关注”按钮
		attBtn.innerHTML="已关注<i class=\"Hui-iconfont\">&#xe676;</i>";
	}else{
		attBtn.innerHTML="关注<i class=\"Hui-iconfont\">&#xe716;</i>";
	}
}
/**
 * 13.关注与取消关注
 */
function attenAuthor(){
	if(user==""){
		alert("关注失败，请先登录！");
		login_popup();
		return;
	}
	var attBtn=document.getElementById("attention");
	var fanNum=document.getElementById("noticedNum");//粉丝数
	var text=attBtn.innerHTML+"";
	var url="";//
	if(text.indexOf("已关注")!=-1){//已关注情况取消关注
		url="note/notice/cancelAtten.do";
		attBtn.innerHTML="关注<i class=\"Hui-iconfont\">&#xe716;</i>";
		fanNum.innerText=parseInt(fanNum.innerText)-1;
		alert("已取消关注！");
	}else{
		url="note/notice/noticeAuthor.do";
		attBtn.innerHTML="已关注<i class=\"Hui-iconfont\">&#xe676;</i>";
		fanNum.innerText=parseInt(fanNum.innerText)+1;
		alert("已关注！");
	}
	$.ajax({
		url:url,
		type:"get",
		async:false,
		data:{
			NNoticer:user,
			NNoticed:author
		}, 
	});
}
//14.得到登录用户的一些设置
function getSetting(userId){
	var url="note/userinfo/getAuthorInfoByUserId.do?UUserId="+userId;
	$.ajax({
		url:url,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			//设置背景、是否显示日记字数
			if(user==userId){//各个页均使用，在首页、我的家园页均生效，在作者页会用下方else覆盖掉背景设置显示对应作者的背景
				var body=document.getElementById("bodys");
				body.style.background="url(res/images/back/"+data.back+")";
				show=data.uShowWordnum;
				perPageNum=data.perpageNum;
			}else{//仅作者页使用，在上方if下面再执行以修改背景为作者的背景
				var body=document.getElementById("bodys");
				body.style.background="url(res/images/back/"+data.back+")";
			}
		}
	});
}
//15.检测访问设备
//平台、设备和操作系统
function monitor(){
	//平台、设备和操作系统
	var system ={win : false,mac : false,xll : false};
	//检测平台
	var p = navigator.platform;
	system.win = p.indexOf("Win") == 0;
	system.mac = p.indexOf("Mac") == 0;
	system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
	//跳转语句，如果是手机访问就自动跳转到wap.baidu.com页面
	if(!system.win && !system.mac && !system.xll){
	    $(".rights").text("");
	    document.getElementById("rights").style.display="none";
	    document.getElementById("contents").style.marginLeft="10px";
	    document.getElementById("contents").style.width="400px";
	    document.getElementById("diary").style.width="320px";
	}
}
//16.播放与暂停自己或别人的家歌
function playHomeSong(){
	var btn=document.getElementById("playBtn");
	if(btn.innerText=="▷"){
		$("#playBtn").text("||");
		playAudio(homeSongId);
		btn.title="点击停止播放";
	}else{
		var song=document.getElementById("song");
		song.src="";
		$("#playBtn").text("▷");
		btn.title="点击可播放喔";
	}
	
}
//17.根据登录用户对于音频的设置来处理是否播放音频
function ifAutoPlay(songId){
	var autoPlay=0;//1:自动播放，0、2:不播放
	$.ajax({
		url:"note/userinfo/getAuthorInfoByUserId.do?UUserId="+user,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			autoPlay=data.autoPlay;
		}
	});
	if(autoPlay==1){//播放
		playAudio(songId);
	}
}
//18.播放用户家歌并修改为播放按钮
function playAudio(sid){
	var url="";
	if(sid.substring(sid.length-5)==".html"){
		url="http://link.hhtjim.com/qq/"+sid.substring(0, sid.length-5)+".mp3";
	}else if(sid.substring(sid.length-3)==".kw"){
		url="http://link.hhtjim.com/kw/"+sid.substring(0, sid.length-3)+".mp3";
	}else if(sid.substring(sid.length-4)==".aac"||sid.substring(sid.length-4)==".m4a"||sid.substring(sid.length-4)==".mp3"){
		url=sid;
	}else{
		url="http://music.163.com/song/media/outer/url?id="+sid+".mp3";
	}
	var song=document.getElementById("song");
	song.src=url;
	//修改
	$("#playBtn").text("||");
	var btn=document.getElementById("playBtn");
	btn.title="点击暂停";
}
//19.手机端按钮登录
function loginPhone(){
	if(user!=""){
		window.open("myHome.html", "_blank")
	}else{
		login_popup();
	}
}
/**
 * 20.切换登录和注册
 * @param type
 */
function loginOrRegister(type){
	if(type=="0"){//显示登录框
		document.getElementById("loginBox").style.display="inline-block";
		document.getElementById("registerBox").style.display="none";
	}else if(type=="1"){//显示注册框
		alert("注册需要输入哆啦id，用户名，密码。其中哆啦id可为空，为空后系统会随机为你分配，用户名和密码不能为空。哆啦id和密码是登录凭证");
		document.getElementById("loginBox").style.display="none";
		document.getElementById("registerBox").style.display="inline-block";
	}
}
/**
 * 21.注册账号
 */
function register(){
	var doraId=document.register_form.dora_r.value+"";//账号，即哆啦id
	var userName=document.register_form.userName_r.value+"";
	var pass=document.register_form.password_r.value+"";
	if(userName.length<4||userName.length>15){
		alert("用户名字数应在4和15之间");
		return;
	}
	if(pass.length<3||pass.length>15){
		alert("密码字数应在3和15之间");
		return;
	}
	if(doraId.length>0&&(doraId.length!==8||isNaN(doraId))){
		alert("哆啦id需要为8位阿拉伯数字，你也可以不输入由系统为你分配");
		return;
	}
	var url="note/userinfo/register.do";
	$.ajax({
		url:url,
		async:false,
		type:"POST",
		data:{
			doraId:doraId,
			userName:userName,
			password:pass
		},
		dataType:"Json",
		success:function(data){
			if(data.code=="200"){
				alert(data.message);
				window.open("index.html","_self");
			}else{
				alert(data.message)
			}

		}
	});
}
/**
 * 22.根据数量和类型返回对应HTML
 * @param num	4个
 * @param type  星星
 * @returns {String}
 */
function getHtml(num,type){
	num=parseInt(num);
	var text="";
	for(var i=0;i<num;i++){
		text=text+"<img src='image/"+type+".png' style='width:16px;height:16px'>";
	}
	return text;
}