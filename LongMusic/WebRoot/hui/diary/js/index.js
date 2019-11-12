function getUser(){
	var user=getCookie("userNote")+"";
	if(user==""){//cookie中没有userId，则请求后台生成。并返回
		var source="";
		if (document.referrer === '') {// 当没有上一级url链接的时候，返回上一级按钮的链接改成项目首页url链接地址，这也是很符合项目逻辑的
		    source="无";
		}else{
			source=document.referrer;
		}
		$.ajax({
			url:"../../note/userinfo/genNoteUserId.do?source="+source,
			async:true,
			type:"GET",
			dataType:"text",
			success:function(data){
				//将生成的userId赋给user，并存至cookie
				user=data;
				setCookie("user", user);
			}
		});
	}
	var userPass=getCookie("userPass")+"";
	if(userPass!=""){//已登录用户，隐藏登录按钮
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
function login_popup() {
    $("#loginModal").modal("show")
}

function login(){
	var acc=document.login_form.account_l.value;//账号，即哆啦id
	var pass=document.login_form.password_l.value;
	$.ajax({
		url:"../../note/userinfo/loginNote.do?acc="+acc+"&pass="+pass,
		async:true,
		type:"GET",
		dataType:"Json",
		success:function(data){
			if(data.result!=null){//存至cookie
				$("#loginModal").modal("hide")
				//setCookie("userNote", acc);
				//setCookie("userPass", pass);
				document.cookie="userNote="+acc+";";
				document.cookie="userPass="+pass+";";
				document.cookie="userNick="+data.result.uuserName+";";
				window.location="";
				//getUser();

			}else{
				alert('未匹配到你输入的账号')
			}

		}
	});

}
//打开新页面
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
		document.cookie="userPass=;";
		document.cookie="userNote=;";
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
			if(arr[0].indexOf(name) != -1){
				//window.alert(arr[1]);
				return arr[1];
			}
		}
	}
	return "";
}