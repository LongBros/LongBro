/***************************************************************
 * 1.根据是否登录设置菜单栏2.弹出登录框3.登录校验4.打开新页面5.设置添加cookie
 * 6.得到name的value7.加载公告、收到的赞、关注……的数量并显示8.一键设置为已读
 ****************************************************************/

var user=getCookie("userId")+"";
var userNick=getCookie("userNick")+"";
loadNotice();

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
		document.getElementById("alarm").style.display="none";
		document.getElementById("image").innerHTML=""+getCookie("userNick")+"";
	}else{
		document.getElementById("exit").style.display="none";
		document.getElementById("login").style.display="block";
		document.getElementById("myHome").style.display="none";
		document.getElementById("write").style.display="none";
		document.getElementById("alarm").style.display="none";
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
	$.ajax({
		url:"../../note/userinfo/loginNote.do?acc="+acc+"&pass="+pass,
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
		window.open("http://112.74.173.44/LongMusic/index0.jsp", "_blank")
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
		document.getElementById("alarm").style.display="none";
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
	$.ajax({
		url:"../../note/userinfo/queryUnReadNum.do",
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
	$.ajax({
		url:"../../note/userinfo/setAsReaded.do",
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
	$.ajax({
		url:"../../note/userinfo/getAuthorInfoByUserId.do?UUserId="+author,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			var sex=getSexById(data.uuserSex);
			document.title=document.title+data.uuserName+"'的日记~哆啦官网";
			document.getElementById("userId").innerText=author;
			document.getElementById("userNameT").innerText=data.uuserName;
			document.getElementById("userName").innerText=data.uuserName;
			document.getElementById("homeSong").innerText=data.uhomeSong;
			document.getElementById("signature").innerText=data.signature;
			document.getElementById("sex").innerText=sex;
			document.getElementById("joinTime").innerText="加入时间："+data.ujoinTime;
		}
	});
	setInteractNum(author);
}
//加载并设置某人的互动数量信息
function setInteractNum(user){
	$.ajax({
		url:"../../note/userinfo/queryInteractNum.do",
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