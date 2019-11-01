getUser();
function getUser(){
	var user=getCookie("user")+"";
	if(user==""){//cookie中没有userId，则请求后台生成。并返回
		var source="";
		if (document.referrer === '') {// 当没有上一级url链接的时候，返回上一级按钮的链接改成项目首页url链接地址，这也是很符合项目逻辑的
		    source="无";
		}else{
			source=document.referrer;
		}
		$.ajax({
			url:"/LongMusic/genNoteUserId.do?source="+source,
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
	//if(userPass!=""){//已登录用户，隐藏登录按钮
		document.getElementById("login").style.display="none";
	//}
	return user;
}
function login_popup() {
    $("#loginModal").modal("show")
}

function login(){
	var acc=document.login_form.account_l.value;//账号，即哆啦id
	var pass=document.login_form.password_l.value;
	$.ajax({
		url:"/LongMusic/loginNote.do?acc="+acc+"&pass="+pass,
		async:true,
		type:"GET",
		dataType:"text",
		success:function(data){
			
			if(data=="true"){//存至cookie
				$("#loginModal").modal("hide")
				setCookie("userNote", acc);
				//setCookie("userPass", pass);
				alert("登陆成功")
				getUser();

			}else{
				alert("请检查你输入的账号和密码!")
			}
		}
	});
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