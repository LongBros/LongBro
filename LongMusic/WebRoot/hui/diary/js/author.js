
/**
 * 1.加载他喜欢的
 */
function loadMyLove(author){
	$("#diarys").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		return;
	}
	$.ajax({
		url:"note/praise/getMyLikeDiary.do?userId="+author,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			var res=data.result;
			if(res.length<1){
				$("#diarys").append("<br><br><center>"+call+"还没有喜欢的日记喔！</center><br><br>");
				return;
			}
			for(var i=0;i<res.length;i++){
				var con=handleCon(res[i].content);
				var title=res[i].title+"";
				if(con.length>250){
					con=con.substring(0,250)+"......";
				}
				if(title.length>10){
					title=title.substring(0,8)+"...";
				}
				//分类
				var cate=getCateById(res[i].cate);
				$("#diarys").append("<div class=\"diary\" title='"+res[i].likeTime+"喜欢'><img src='image/tx/"+res[i].headImg+".jpg' class='touxiang'><span onclick='openOther(0,"+res[i].id+")'>"+con+"</span><br>"
						+"<div class='info'><i class=\"Hui-iconfont\">&#xe60d;</i><span style='cursor:pointer' onclick='openOther(1,\""+res[i].writter+"\")'>"+res[i].u_User_Name
						+"</span>&emsp;<i class=\"Hui-iconfont\">&#xe690;</i>"+res[i].time
						+"&emsp;<i class=\"Hui-iconfont\">&#xe681;</i>"+cate+"&nbsp;:<span title='"+res[i].title+"'>《"+title+"》</span>&emsp;<i class=\"Hui-iconfont\">&#xe6c9;</i>"+res[i].loc
						+"<div class='zan'><i class=\"Hui-iconfont\">&#xe725;</i>"+res[i].visitNum+"&nbsp;<i class=\"Hui-iconfont\">&#xe622;</i><span id='commentNum'>"+res[i].commentNum+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe66d;</i><span>"+res[i].praiseNum
						+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe630;</i><span>"+res[i].storeNum+"</span></div></div>"
						+"</div><hr width='100%'>");
			}
			$("#diarys").append("<br><br><center>"+call+"共喜欢<font color='red'>"+res.length+"</font>篇日记</center><br>");

		}
	});
}
/**
 * 2.加载他收藏的
 */
function loadMyStore(author){
	$("#diarys").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		return;
	}
	$.ajax({
		url:"note/store/getMyStoreDiary.do?userId="+author,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			var res=data.result;
			if(res.length<1){
				$("#diarys").append("<br><br><center>"+call+"还没有收藏过日记喔！</center><br><br>");
				return;
			}
			for(var i=0;i<res.length;i++){
				var con=handleCon(res[i].content);
				var title=res[i].title+"";
				if(con.length>250){
					con=con.substring(0,250)+"......";
				}
				if(title.length>10){
					title=title.substring(0,8)+"...";
				}
				//分类
				var cate=getCateById(res[i].cate);
				$("#diarys").append("<div class=\"diary\" title='"+res[i].storeTime+"收藏'><img src='image/tx/"+res[i].headImg+".jpg' class='touxiang'><span onclick='openOther(0,"+res[i].id+")'>"+con+"</span><br>"
						+"<div class='info'><i class=\"Hui-iconfont\">&#xe60d;</i><span style='cursor:pointer' onclick='openOther(1,\""+res[i].writter+"\")'>"+res[i].u_User_Name
						+"</span>&emsp;<i class=\"Hui-iconfont\">&#xe690;</i>"+res[i].time
						+"&emsp;<i class=\"Hui-iconfont\">&#xe681;</i>"+cate+"&nbsp;:<span title='"+res[i].title+"'>《"+title+"》</span>&emsp;<i class=\"Hui-iconfont\">&#xe6c9;</i>"+res[i].loc
						+"<div class='zan'><i class=\"Hui-iconfont\">&#xe725;</i>"+res[i].visitNum+"&nbsp;<i class=\"Hui-iconfont\">&#xe622;</i><span id='commentNum'>"+res[i].commentNum+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe66d;</i><span>"+res[i].praiseNum
						+"</span>&nbsp;&nbsp;<i class=\"Hui-iconfont\">&#xe630;</i><span>"+res[i].storeNum+"</span></div></div>"
						+"</div><hr width='100%'>");
			}
			$("#diarys").append("<br><br><center>"+call+"共收藏了<font color='red'>"+res.length+"</font>篇日记</center><br>");

		}
	});
}
/**
 * 3.加载他看过的
 */
function loadMyFeet(author){
	if(user==""){
		alert("请登录");
		return;
	}
}

/**
 * 4.加载他关注的人
 */
function loadMyAtten(author){
	$("#diarys").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		return;
	}
	$.ajax({
		url:"note/notice/getMyAtten.do?userId="+author,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(res){
			if(res.code==200){
				var data=res.result;
				if(data.length<1){
					$("#diarys").append("<center>"+call+"还没有关注别人呢！</center>");
				}else{
					$("#diarys").append("<center>"+call+"共关注了<font color='red' size='2px'>"+data.length+"</font>个小伙伴</center>");
				}
				for(var i=0;i<data.length;i++){
					$("#diarys").append("<div class='notice'><img src='image/tx/"+data[i].headImg+".jpg'><a href='author.html?author="+data[i].noticedId+"' target='_blank'>"+data[i].noticedName+"</a>&emsp;<font  color='gray' size='1px'>"+data[i].joinDay+"天共"+data[i].diaryNum+"篇日记</font><font color='gray' size='2px'><span>"+data[i].noticeTime+"</span></font></div><hr>");
				}
			}else{
				alert("查询失败");
			}
		}
	});
}
/**
 * 5.加载他的评论
 */
function loadMyCom(author){
	$("#diarys").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		return;
	}
	$.ajax({
		url:"note/comment/getMyComment.do?userId="+author,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			var res=data.result;
			if(res.length>0){
				$("#diarys").append("<div class='notice'><center>共有<font color='red'>"+res.length+"</font>条评论消息</center></div>");
			}
			if(res.length<1){
				$("#diarys").append("<div class='notice'><center>"+call+"还没有评论过别人的日记！</center></div>");
			}
			for(var i=0;i<res.length;i++){
				var con=res[i].reviewCon+"";
				if(con.length>15){
					con=con.substring(0,15)+"...";
				}
				$("#diarys").append("<div class='notice'>"+call+"评论了<a href='author.html?author="+res[i].reviewed+"' target='_blank'>"+res[i].viewedName+"</a>&emsp;的日记&emsp;<a href='diary.html?id="+res[i].diaryId+"' target='_blank'>"+res[i].diaryTitle+"</a>&emsp;<font style='color:gray;font-size:5px;'>"+con+"</font><font color='gray' size='2px'><span>"+res[i].reviewTime+"</span></font></div><hr>");
			}
		}
	});
}
/**
 * 6.加载他的粉丝
 */
function loadMyFans(author){
	$("#diarys").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		return;
	}
	$.ajax({
		url:"note/notice/getMyMessage.do?userId="+author,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(res){
			if(res.code==200){
				var data=res.result;
				if(data.length<1){
					$("#diarys").append("<center>还没有人关注"+call+"呢，期待你成为"+call+"的第一个粉丝啦！</center>");
				}else{
					$("#diarys").append("<center>共<font color='red' size='2px'>"+data.length+"</font>个小伙伴关注了"+call+"</center>");
				}
				for(var i=0;i<data.length;i++){
					$("#diarys").append("<div class='notice'><img src='image/tx/"+data[i].headImg+".jpg'><a href='author.html?author="+data[i].noticerId+"' target='_blank'>"+data[i].noticerName+"</a>&emsp;<font color='gray' size='1px'>"+data[i].joinDay+"天共"+data[i].diaryNum+"篇日记</font><font color='gray' size='2px'><span>"+data[i].noticeTime+"</span></font></div><hr>");
					
				}
			}else{
				alert("查询失败");
			}
		}
	});
}
/**
 * 7.切换tab
 * @param which
 */
function openTab(which){
	var tabs=new Array("my","love","store","notice","fans");
	for(var i=0;i<5;i++){
		if(i==which){
			$("#"+tabs[i]).css("color","red");
		}else{
			$("#"+tabs[i]).css("color","white");
		}
	}
	if(which==0){//
		loadDiary('author',author,1,perPage,'');//作者页不需过滤黑名单，当前登录人不传
		document.getElementById("my").style.color="red";
		document.getElementById("love").style.color="white";
		document.getElementById("store").style.color="white";
		document.getElementById("notice").style.color="white";
		document.getElementById("fans").style.color="white";
	}else if(which==1){
		loadMyLove(author)
	}else if(which==2){
		loadMyStore(author)
	}else if(which==3){
		loadMyAtten(author)
	}else if(which==4){
		loadMyFans(author)
	}
}