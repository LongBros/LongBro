/**
 * 1.加载他喜欢的
 * @param which
 * @param page
 * @param perPage
 * @param author
 */
function loadMyLove(which,page,perPage,author){
	$("#diarys").text('');
	$(".pages").text('');
	if(user==""){
		alert("请先登录，登录后方可查看作者喜欢的日记");
		login_popup();
		return;
	}
	$.ajax({
		url:"note/praise/getMyLikeDiary.do",
		type:"get",
		async:false,
		data:{
			userId:author,
			//author:author,
			page:page,
			perPage:perPage
		},
		dataType:"Json",
		success:function(data){
			var res=data.result;
			if(res.length<1){
//				$("#diarys").append("<br><br><center>"+call+"还没有喜欢的日记喔！</center><br><br>");
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

		}
	});
	curPage=parseInt(page);
	perPage=parseInt(perPage);
	setPage1(which,perPage);
}
/**
 * 2.加载他收藏的
 * @param which
 * @param page
 * @param perPage
 * @param author
 */
function loadMyStore(which,page,perPage,author){
	$("#diarys").text('');
	$(".pages").text('');
	if(user==""){
		alert("请先登录，登录后方可查看作者收藏的日记");
		login_popup();
		return;
	}
	$.ajax({
		url:"note/store/getMyStoreDiary.do",
		type:"get",
		async:false,
		data:{
			userId:author,
			//author:author,
			page:page,
			perPage:perPage
		},
		dataType:"Json",
		success:function(data){
			var res=data.result;
			if(res.length<1){
//				$("#diarys").append("<br><br><center>"+call+"还没有收藏过日记喔！</center><br><br>");
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

		}
	});
	curPage=parseInt(page);
	perPage=parseInt(perPage);
	setPage1(which,perPage);
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
 * @param which
 * @param page
 * @param perPage
 * @param author
 */
function loadMyAtten(which,page,perPage,author){
	$("#diarys").text('');
	$(".pages").text('');
	if(user==""){
		alert("请先登录，登录后方可查看作者关注的用户");
		login_popup();
		return;
	}
	$.ajax({
		url:"note/notice/getMyAtten.do",
		type:"get",
		async:false,
		data:{
			userId:user,
			author:author,
			page:page,
			perPage:perPage
		},
		dataType:"Json",
		success:function(res){
			if(res.code==200){
				var data=res.result;
				if(data.length<1){
//					$("#diarys").append("<center>"+call+"还没有关注别人呢！</center>");
				}else{
//					$("#diarys").append("<center>"+call+"共关注了<font color='red' size='2px'>"+data.length+"</font>个小伙伴</center>");
				}
				for(var i=0;i<data.length;i++){
					var btn="";
					if(data[i].ifAtten>0){
						btn="&emsp;&emsp;<font style='background:white;color:black;font-size:1px;' onclick='attenUser()'>已关注</span>";
					}else{
						btn="&emsp;&emsp;<font style='background:red;color:white;font-size:1px;' onclick='attenUser()'>关注</span>";
					}
					$("#diarys").append("<div class='notice'><img src='image/tx/"+data[i].headImg+".jpg'><a href='author.html?author="+data[i].noticedId+"' target='_blank'>"+data[i].noticedName+"</a>&emsp;<font color='gray' size='1px'>"+data[i].fanNums+"粉丝</font>&emsp;<font  color='gray' size='1px'>"+data[i].joinDay+"天共"+data[i].diaryNum+"篇日记</font>"+btn+"<font color='gray' size='2px'><span>"+data[i].noticeTime+"</span></font></div><hr>");
				}
			}else{
				alert("查询失败");
			}
		}
	});
	curPage=parseInt(page);
	perPage=parseInt(perPage);
	setPage1(which,perPage);
}
/**
 * 5.加载他的评论
 */
function loadMyCom(author){
	$("#diarys").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		login_popup();
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
 * @param which
 * @param page
 * @param perPage
 * @param author
 */
function loadMyFans(which,page,perPage,author){
	$("#diarys").text('');
	$(".pages").text('');
	if(user==""){
		alert("请先登录，登录后方可查看作者的粉丝");
		login_popup();
		return;
	}
	$.ajax({
		url:"note/notice/getMyMessage.do",
		type:"get",
		async:false,
		data:{
			userId:user,
			author:author,
			page:page,
			perPage:perPage
		},
		dataType:"Json",
		success:function(res){
			if(res.code==200){
				var data=res.result;
				if(data.length<1){
//					$("#diarys").append("<center>还没有人关注"+call+"呢，期待你成为"+call+"的第一个粉丝啦！</center>");
				}else{
//					$("#diarys").append("<center>共<font color='red' size='2px'>"+data.length+"</font>个小伙伴关注了"+call+"</center>");
				}
				for(var i=0;i<data.length;i++){
					var btn="";
					if(data[i].ifAtten>0){
						btn="&emsp;&emsp;<font style='background:white;color:black;font-size:1px;width:160px;height:29px' onclick='attenUser()'>已关注</span>";
					}else{
						btn="&emsp;&emsp;<font style='background:red;color:white;font-size:1px;width:160px;height:29px' onclick='attenUser()'>关注</span>";
					}
					$("#diarys").append("<div class='notice' style='cursor:pointer'><img src='image/tx/"+data[i].headImg+".jpg'><a href='author.html?author="+data[i].noticerId+"' target='_blank'>"+data[i].noticerName+"</a>&emsp;<font color='gray' size='1px'>"+data[i].fanNums+"粉丝</font>&emsp;<font color='gray' size='1px'>"+data[i].joinDay+"天"+data[i].diaryNum+"篇日记</font>"+btn+"<font color='gray' size='2px'><span>"+data[i].noticeTime+"</span></font></div><hr>");
					
				}
			}else{
				alert("查询失败");
			}
		}
	});
	curPage=parseInt(page);
	perPage=parseInt(perPage);
	setPage1(which,perPage);
}
/**
 * 7.切换tab
 * @param which
 */
function openTab(which){
	$("#diarys").text('');
	$(".pages").text('');
	document.getElementById("comment").style.display="none";
	$("#comments").text('');
	var tabs=new Array("my","love","store","notice","fans","picture","wall");
	for(var i=0;i<tabs.length;i++){
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
		loadMyLove('1','1','10',author)
	}else if(which==2){
		loadMyStore('2','1','10',author)
	}else if(which==3){
		loadMyAtten('3','1','10',author)
	}else if(which==4){
		loadMyFans('4','1','10',author)
	}else if(which==5){
	}else if(which==6){
		loadMyWall();
	}
}
/**
 * 8.设置分页
 * @param author
 * @param perPage
 */
function setPage1(which,perPage){
	$(".pages").text('');
	var num=0;//数量
	var fun="";//函数名
	/*var url="";//
	var param="";
	if(which==1){
		fun="loadMyLove";
		url="note/praise/getPraiseNum.do";
	}else if(which==2){
		fun="loadMyStore";
		url="note/store/getStoreNum.do";
	}*/
	if(which==1){//喜欢的日记的数量
		$.ajax({
			url:"note/praise/getPraiseNum.do",
			type:"get",
			async:false,
			data:{
				PPraiser:author,
				type:0//点赞类型为日记
			},
			dataType:"text",
			success:function(data){
				num=data;
			}
		});
		fun="loadMyLove";
	}else if(which==2){//收藏的日记的数量
		$.ajax({
			url:"note/store/getStoreNum.do",
			type:"get",
			async:false,
			data:{
				SStorer:author
			},
			dataType:"text",
			success:function(data){
				num=data;
			}
		});
		fun="loadMyStore";
	}else if(which==3){//关注的人的数量
		$.ajax({
			url:"note/notice/getAttenNum.do",
			type:"get",
			async:false,
			data:{
				NNoticer:author
			},
			dataType:"text",
			success:function(data){
				num=data;
			}
		});
		fun="loadMyAtten";
	}else if(which==4){//粉丝的数量
		$.ajax({
			url:"note/notice/getAttenNum.do",
			type:"get",
			async:false,
			data:{
				NNoticed:author
			},
			dataType:"text",
			success:function(data){
				num=data;
			}
		});
		fun="loadMyFans";
	}
	
	var page=0;
	if(num%perPage==0){//可整除的话，页码数为总日记数除以10
		page=num/perPage;
	}else{//不可整除的话，页码数为总日记数除以10加1
		page=parseInt(num/perPage)+1;
	}
	var value=new Array("10","20","30","40");
	var sel="";
	if(which!=3&&which!=4){
		sel="<select onchange='setPer1("+which+",options[selectedIndex].value)'>";
		for(var i=0;i<value.length;i++){
			if(value[i]==perPage){
				sel=sel+"<option value='"+value[i]+"' selected>每页"+value[i]+"篇</option>";
			}else{
				sel=sel+"<option value='"+value[i]+"'>每页"+value[i]+"篇</option>";
			}
		}
		sel=sel+"</select>";
	}
	if(num>10){
		if(which!=3&&which!=4){
			$(".pages").append(sel+"&nbsp;共"+num+"篇日记&nbsp;"+curPage+"/"+page+"&emsp;");
		}else{
			$(".pages").append(num+"条数据&nbsp;"+curPage+"/"+page+"&emsp;");
		}
	}else{//只有10篇无需显示选择每页多少篇
		if(which!=3&&which!=4){
			$(".pages").append("&nbsp;共"+num+"篇日记&nbsp;");
		}else{
			$(".pages").append(num+"条数据"+"");
		}
	}
	if(curPage!=1){
		$(".pages").append("<span onclick=\""+fun+"('"+which+"','1','"+perPage+"','"+author+"')\">首</span>&emsp;")
		$(".pages").append("<span onclick=\""+fun+"('"+which+"','"+(curPage-1)+"','"+perPage+"','"+author+"')\">《</span>&emsp;");
	}
	if(page>5){//多于5页，只显示5页
		if(curPage>page-5){//当前页码大于总页码-5，输出后六页
			for(var i=page-4;i<=page;i++){
	              if(i==curPage){
	  				   $(".pages").append("<span onclick=\""+fun+"('"+which+"','"+i+"','"+perPage+"','"+author+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
		          }else{
					   $(".pages").append("<span onclick=\""+fun+"('"+which+"','"+i+"','"+perPage+"','"+author+"')\">"+i+"</span>&emsp;")
	         	  }
	        }
		}else{//当前页码小于总页码-6，输出当前页码后的六页
            for(var i=curPage;i<curPage+5;i++){
                if(i==curPage){
	  				   $(".pages").append("<span onclick=\""+fun+"('"+which+"','"+i+"','"+perPage+"','"+author+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
                }else{
					   $(".pages").append("<span onclick=\""+fun+"('"+which+"','"+i+"','"+perPage+"','"+author+"')\">"+i+"</span>&emsp;")
                }
            }
         }
	}else{//少于5页，显示所有页码
		if(page!=1){//只有一页无需显示页码
			for(var i=1;i<=page;i++){
				if(curPage==i){
					$(".pages").append("<span onclick=\""+fun+"('"+which+"','"+i+"','"+perPage+"','"+author+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
				}else{
					$(".pages").append("<span onclick=\""+fun+"('"+which+"','"+i+"','"+perPage+"','"+author+"')\">"+i+"</span>&emsp;")
				}
			}
		}
	}
	if(curPage+1<=page){//＜＞
		$(".pages").append("<span onclick=\""+fun+"('"+which+"','"+(curPage+1)+"','"+perPage+"','"+author+"')\">》</span>&emsp;")
		$(".pages").append("<span onclick=\""+fun+"('"+which+"','"+page+"','"+perPage+"','"+author+"')\">尾</span>&emsp;")
	}
	
}
/**
 * 9.设置每页多少篇
 * @param pernum
 */
function setPer1(which,pernum){
	perPage=pernum;
	setPage1(which,perPage);
	if(which=="0"){//自己的
		loadMyDiary(which,curPage,perPage,author);
	}else if(which=="1"){//喜欢的
		loadMyLove(which,curPage,perPage,author);
	}
}
/**
 * 10.关注或取消关注某人
 */
function attenUser(){
	alert("此处暂不支持关注，请进入作者详情页进行关注，谢谢")
}
/**
 * 11.加载他收藏的
 * @param which
 * @param page
 * @param perPage
 * @param author
 */
function loadMyWall(){
	
	if(user==""){
		alert("请先登录，登录后方可查看作者的倾诉墙");
		login_popup();
		return;
	}
	document.getElementById("comment").style.display="inline-block";
	loadConfide();
}
/**
 * 12.发布倾诉
 */
function submit_confide(){
	var con=document.getElementById("content").value+"";//内容
	if(con.length<5){
		alert("倾诉内容不得低于5字符");
		return;
	}
//	alert(author);//被评论日记的作者
	var cid="";
	$.ajax({
		url:"note/confide/confideOther.do",
		type:"get",
		async:false,
		data:{
			WConfider:user==""?"":user,
			WPourType:1,
			WPourCon:con,
			WConfided:author,
			WConfideTime:formatW2(new Date()+""),
			WReadStatus:0
		},
		dataType:"Json",
		success:function(res){
			cid=res.result;
			alert(res.message);
		}
	});
	loadConfide();
	$("#content").val("");
}
/**
 * 13.加载当前作者的倾诉墙
 */
function loadConfide(){
	$('#comments').text("");
	$.ajax({
		url:"note/confide/getConfides.do",
		type:"get",
		data:{
			author:author,
			page:1,
			perPage:100
		},
		async:false,
		dataType:"Json",
		success:function(res){
			var data=res.result;
			if(data.length<1){
				$('#comments').append("<center></center>");
			}else{
				$('#comments').append("&emsp;共<font color='red'>"+data.length+"</font>条倾诉");
			}
			var l=data.length;
			for(var k=0;k<data.length;k++){
//				var confided=data[k].confided;//倾诉id
				var confider=data[k].confider;//倾诉者
				var con=data[k].confideCon;
				con=con.replace(new RegExp("::::","gm"), ".jpg'>");
				con=con.replace(new RegExp(":::","gm"), ".png'>");
				con=con.replace(new RegExp("::","gm"), ".gif'>");
				con=con.replace(new RegExp("<<<","gm"), "<img alt='' src='image/expre/newtieba/");
				con=con.replace(new RegExp("<<","gm"), "<img alt='' src='image/expre/tieba/");
				con=con.replace(new RegExp("&&&&","gm"), "<img alt='' src='image/expre/weibo/");
				con=con.replace(new RegExp("&&&","gm"), "<img alt='' src='image/expre/huang/");
				con=con.replace(new RegExp("&&","gm"),"<img alt='' src='image/expre/aodamiao/");
				
				$('#comments').append("<hr>");
				var href="某本站访客";
				var img="dlam";
				if(confider){
					href="<a href='author.html?author="+confider+"' target='_blank'>"+data[k].confiderName+"</a>&emsp;&emsp;<span style='color:gray;font-size:1px'>"+l+"L</span>";
				}
				if(data[k].headImg){
					img=data[k].headImg;
				}
				$('#comments').append("<img src='image/tx/"+img+".jpg'>");
				$('#comments').append(href+"&nbsp;&nbsp;<span style='color:gray;font-size:10px;float:right;margin-right:20px'>"+data[k].confideTime+"</span>");
				$('#comments').append("<br><div class='content1'>"+con+"</div>");
				
				l--;
			}
			
		}
	});
}