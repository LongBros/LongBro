/**
 * 1.设置分页
 * @param author
 * @param perPage
 */
function setPage1(author,perPage){
	$(".pages").text('');
	var num=0;
	$.ajax({
		url:"note/diary/getDiaryNumBy.do",
		type:"get",
		async:false,
		data:{
			NWritter:author
		},
		dataType:"text",
		success:function(data){
			num=data;
		}
	});
	var page=0;
	if(num%perPage==0){//可整除的话，页码数为总日记数除以10
		page=num/perPage;
	}else{//不可整除的话，页码数为总日记数除以10加1
		page=parseInt(num/perPage)+1;
	}
	var value=new Array("10","20","30","40");
	var sel="<select onchange='setPer1(options[selectedIndex].value)'>";
	for(var i=0;i<value.length;i++){
		if(value[i]==perPage){
			sel=sel+"<option value='"+value[i]+"' selected>每页"+value[i]+"篇</option>";
		}else{
			sel=sel+"<option value='"+value[i]+"'>每页"+value[i]+"篇</option>";
		}
	}
	sel=sel+"</select>";
	if(num>10){
		$(".pages").append(sel+"&nbsp;共"+num+"篇日记&nbsp;"+curPage+"/"+page+"&emsp;");
	}else{//只有10篇无需显示选择每页多少篇
		$(".pages").append("&nbsp;共"+num+"篇日记&nbsp;");
	}
	if(curPage!=1){
		$(".pages").append("<span onclick=\"loadMyDiary('"+author+"','1','"+perPage+"')\">首</span>&emsp;")
		$(".pages").append("<span onclick=\"loadMyDiary('"+author+"','"+(curPage-1)+"','"+perPage+"')\">←</span>&emsp;");
	}
	if(page>5){//多于5页，只显示5页
		if(curPage>page-5){//当前页码大于总页码-5，输出后六页
			for(var i=page-4;i<=page;i++){
	              if(i==curPage){
	  				   $(".pages").append("<span onclick=\"loadMyDiary('"+author+"','"+i+"','"+perPage+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
		          }else{
					   $(".pages").append("<span onclick=\"loadMyDiary('"+author+"','"+i+"','"+perPage+"')\">"+i+"</span>&emsp;")
	         	  }
	        }
		}else{//当前页码小于总页码-6，输出当前页码后的六页
            for(var i=curPage;i<curPage+5;i++){
                if(i==curPage){
	  				   $(".pages").append("<span onclick=\"loadMyDiary('"+author+"','"+i+"','"+perPage+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
                }else{
					   $(".pages").append("<span onclick=\"loadMyDiary('"+author+"','"+i+"','"+perPage+"')\">"+i+"</span>&emsp;")
                }
            }
         }
		
	}else{//少于5页，显示所有页码
		if(page!=1){//只有一页无需显示页码
			for(var i=1;i<=page;i++){
				if(curPage==i){
					$(".pages").append("<span onclick=\"loadMyDiary('"+author+"','"+i+"','"+perPage+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
				}else{
					$(".pages").append("<span onclick=\"loadMyDiary('"+author+"','"+i+"','"+perPage+"')\">"+i+"</span>&emsp;")
				}
			}
		}
	}
	if(curPage+1<=page){//＜＞
		$(".pages").append("<span onclick=\"loadMyDiary('"+author+"','"+(curPage+1)+"','"+perPage+"')\">→</span>&emsp;")
		$(".pages").append("<span onclick=\"loadMyDiary('"+author+"','"+page+"','"+perPage+"')\">尾</span>&emsp;")
	}
	
}
/**
 * 2.设置每页多少篇
 * @param pernum
 */
function setPer1(pernum){
	perPage=pernum;
	setPage1(author,perPage);
	loadMyDiary(curPage,perPage);
}
/**
 * 3.加载我的日记
 * @param page
 * @param perPage
 */
function loadMyDiary(page,perPage){
	$("#myDiary").text('');
	if(user==""){
		alert("请登录");
		return;
	}
	document.getElementById("my").style.color="red";
	document.getElementById("love").style.color="black";
	document.getElementById("store").style.color="black";
	document.getElementById("setting").style.color="black";
	document.getElementById("attention").style.color="black";
	var au="0,1,2";//完全公开的
	//$("#myDiary").text("");
	$.ajax({
		url:"note/diary/getDiaryBy.do",
		type:"get",
		async:false,
		dataType:"Json",
		data:{
			author:author,
			page:page,
			authority:au,
			perPage:perPage
		},
		success:function(data){
			if(data.length<1){
				$("#myDiary").append("<br><br><center>你还没有写过日记呢，快去写一篇吧！</center><br><br>");
				return;
			}
			for(var i=0;i<data.length;i++){
				//处理内容和标题
				var title=data[i].ntitle+"";
				var con=handleCon(data[i].ncontent);
				if(con.length>250){
					con=con.substring(0,250)+"......";
				}
				if(title.length>10){
					//title=title.substring(0,8)+"...";
				}
				var lock="";
				if(data[i].nauthority==1){//仅自己可见
					lock="<i class='Hui-iconfont' style='font-size: 15px;color:red' title='自己可见'>&#xe60e;</i>";
				}
				var mood=getMoodById(data[i].nmood);
				var wea=getWeaById(data[i].nweather);
				//分类
				var cate=getCateById(data[i].ntype);
				$("#myDiary").append("<div class=\"diary\">&nbsp;<span class='diaryTitle' title='"+data[i].ntitle
				+"' style='color:black;font-size:18px;' onclick='openOther(0,"+data[i].nid+")'>"+title+"</span><br><span onclick='openOther(0,"+data[i].nid+")' style='color:gray'>"+con+"</span><br>"
						+"<div class='info'>"
						+"</span>&emsp;<i class=\"Hui-iconfont\">&#xe690;</i>"+data[i].ntime+"&emsp;"+lock+"&emsp;心情："+mood+"&emsp;&emsp;天气："+wea
						+"&emsp;分类："+cate+"&emsp;"
						+"<div class='zan'><i class=\"Hui-iconfont\">&#xe725;</i>"+data[i].visitNum
						+"&nbsp;<i class=\"Hui-iconfont\">&#xe622;</i><span id='commentNum'>"+data[i].commentNum+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe66d;</i><span>"+data[i].praiseNum
						+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe630;</i><span>"+data[i].storeNum
						+"</span>&nbsp;<span style='color:red' onclick='editDiary("+data[i].nid
						+")'>编辑</span>&nbsp;<span style='color:red' onclick='delDiary("+data[i].nid
						+")'>删除</span>&nbsp;<span style='color:red' onclick='diaryToTop("+data[i].nid+")'>置顶</span></div></div>"
						+"</div><hr width='880px'>");
			}

		}
	});
	curPage=parseInt(page);
	perPage=parseInt(perPage);
	setPage1(user,perPage);
}
/**
 * 4.加载我喜欢的
 */
function loadMyLove(){
	$("#myDiary").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		return;
	}
	document.getElementById("my").style.color="black";
	document.getElementById("love").style.color="red";
	document.getElementById("store").style.color="black";
	document.getElementById("setting").style.color="black";
	document.getElementById("attention").style.color="black";
	$.ajax({
		url:"note/praise/getMyLikeDiary.do?userId="+user,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			var res=data.result;
			if(res.length<1){
				$("#myDiary").append("<br><br><center>你还没有喜欢的日记喔！</center><br><br>");
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
				$("#myDiary").append("<div class=\"diary\" title='"+res[i].likeTime+"喜欢'><img src='image/tx/"+res[i].headImg+".jpg' class='touxiang'><span onclick='openOther(0,"+res[i].id+")'>"+con+"</span><br>"
						+"<div class='info'><i class=\"Hui-iconfont\">&#xe60d;</i><span style='cursor:pointer' onclick='openOther(1,\""+res[i].writter+"\")'>"+res[i].u_User_Name
						+"</span>&emsp;<i class=\"Hui-iconfont\">&#xe690;</i>"+res[i].time
						+"&emsp;<i class=\"Hui-iconfont\">&#xe681;</i>"+cate+"&nbsp;:<span title='"+res[i].title+"'>《"+title+"》</span>&emsp;<i class=\"Hui-iconfont\">&#xe6c9;</i>"+res[i].loc
						+"<div class='zan'><i class=\"Hui-iconfont\">&#xe725;</i>"+res[i].visitNum+"&nbsp;<i class=\"Hui-iconfont\">&#xe622;</i><span id='commentNum'>"+res[i].commentNum+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe66d;</i><span>"+res[i].praiseNum
						+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe630;</i><span>"+res[i].storeNum+"</span></div></div>"
						+"</div><hr width='880px'>");
			}
			$("#myDiary").append("<br><br><center>朕共喜欢<font color='red'>"+res.length+"</font>篇日记</center><br>");

		}
	});
}
/**
 * 5.加载我收藏的
 */
function loadMyStore(){
	$("#myDiary").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		return;
	}
	document.getElementById("my").style.color="black";
	document.getElementById("love").style.color="black";
	document.getElementById("store").style.color="red";
	document.getElementById("setting").style.color="black";
	document.getElementById("attention").style.color="black";
	$.ajax({
		url:"note/store/getMyStoreDiary.do?userId="+user,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			var res=data.result;
			if(res.length<1){
				$("#myDiary").append("<br><br><center>你还没有收藏过日记喔！</center><br><br>");
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
				$("#myDiary").append("<div class=\"diary\" title='"+res[i].storeTime+"收藏'><img src='image/tx/"+res[i].headImg+".jpg' class='touxiang'><span onclick='openOther(0,"+res[i].id+")'>"+con+"</span><br>"
						+"<div class='info'><i class=\"Hui-iconfont\">&#xe60d;</i><span style='cursor:pointer' onclick='openOther(1,\""+res[i].writter+"\")'>"+res[i].u_User_Name
						+"</span>&emsp;<i class=\"Hui-iconfont\">&#xe690;</i>"+res[i].time
						+"&emsp;<i class=\"Hui-iconfont\">&#xe681;</i>"+cate+"&nbsp;:<span title='"+res[i].title+"'>《"+title+"》</span>&emsp;<i class=\"Hui-iconfont\">&#xe6c9;</i>"+res[i].loc
						+"<div class='zan'><i class=\"Hui-iconfont\">&#xe725;</i>"+res[i].visitNum+"&nbsp;<i class=\"Hui-iconfont\">&#xe622;</i><span id='commentNum'>"+res[i].commentNum+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe66d;</i><span>"+res[i].praiseNum
						+"</span>&nbsp;&nbsp;<i class=\"Hui-iconfont\">&#xe630;</i><span>"+res[i].storeNum+"</span></div></div>"
						+"</div><hr width='880px'>");
			}
			$("#myDiary").append("<br><br><center>朕共收藏了<font color='red'>"+res.length+"</font>篇日记</center><br>");

		}
	});
}
/**
 * 6.加载我看过的
 */
function loadMyFeet(){
	
}
/**
 * 7.编辑日记
 * @param id
 */
function editDiary(id){
	if(isPhone()){
		window.open("newDiary.html?id="+id,"_blank");
	}else{
		//window.location="new.html?id="+id;
		window.open("new.html?id="+id,"_blank");
	}
}
/**
 * 8.删除日记
 * @param id
 */
function delDiary(id){
	var r=confirm("确认删除？");
	if(r==false){
		return;
	}
	$.ajax({
		url:"note/diary/addOrEditNote.do",
		type:"get",
		async:false,
		dataType:"Json",
		data:{
			NId:id,
			nAuthority:3
		},
		success:function(res){
			alert("日记已删除");
			loadMyDiary(author,curPage,perPage);
		}
	});
}
/**
 * 9.日记置顶
 */
function diaryToTop(){
	alert("不好意思喔，开发中…敬请期待。^_^")
}
/**
 * 10.
 * 12-01打开设置tab
 */
function openSetting(){
	$("#myDiary").text('');
	$(".pages").text('');
	document.getElementById("my").style.color="black";
	document.getElementById("love").style.color="black";
	document.getElementById("store").style.color="black";
	document.getElementById("setting").style.color="red";
	document.getElementById("attention").style.color="black";
	loadInfo();//加载出作者信息以供编辑
	$("#myDiary").append("<span>背景选择：</span>");
	/* $("#myDiary").append("<a onclick='setBack()'>设置当前背景为默认朕的背景</a>"); */
	loadAllBack();
}
/**
 * 11.
 * 12-01 加载所有的背景图
 */
function loadAllBack(){
	var array=new Array("back0.jpg","back1.jpg","back2.jpg","back3.jpg","back4.jpg","back5.jpg","back6.jpg"
			,"back0.png","back1.png","back2.png","back3.png","back4.png","back5.png","back6.png","back7.png"
			,"back0.gif","back1.gif");
	for(var i=0;i<array.length;i++){
		if(i%5==0){
			$("#myDiary").append("<br>");
		}
		$("#myDiary").append("<img src='res/images/back/"+array[i]+"' title='点击即可设置当前\r背景为朕的背景^_^' class='backDemo' onclick='setBackground(\""+array[i]+"\")'>");
	}
	
}

/**
 *12. 设置背景
 */
function setBackground(which){
	var body=document.getElementById("bodys");
	body.style.background="url(res/images/back/"+which+")";
	saveInfo(4,which);//保存背景设置至数据库
}
/**
 * 13.加载作者信息
 */
function loadInfo(){
	//加载出作者信息以供编辑
	$.ajax({
		url:"note/userinfo/getAuthorInfoByUserId.do?UUserId="+author,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			var sex=getSexById(data.uuserSex);
			var blackNameIds=data.blackNameList+"";//用户id
			var blackIds[]=blackNameIds.split(",");
			var blackNames=data.blackNames;//用户名
			var blacks[]=blackNames.split(",");
			var string="<form name='info'><span>昵称：</span><input name='nickName' value='"+data.uuserName+"'><i class=\"Hui-iconfont\" style='cursor:pointer' onclick='saveInfo(1)' title='点击保存'>&#xe676;</i><br>";
			string=string+"<span>个性签名：</span><input name='signature' value='"+data.signature+"'><i class=\"Hui-iconfont\" style='cursor:pointer' onclick='saveInfo(2)' title='点击保存'>&#xe676;</i><br>";
			string=string+"<span>默认日记地址：</span><input name='location' value='"+data.location+"'><i class=\"Hui-iconfont\" style='cursor:pointer' onclick='saveInfo(3)' title='点击保存'>&#xe676;</i><br>";
			//string=string+"<span>家歌选择：</span><font color='red'>"+data.uhomeSong+"</font><i class=\"Hui-iconfont\">&#xe6df;</i>(其他用户访问你的家园时会播放家歌)<br>";
			string=string+"<span>我的黑名单(不看名单):";
			for(var i=0;i<blackIds.length;i++){
				string=string+"<a onclick='removeFromList(\'"+blackIds[i]+"\')'>"+blacks[i]+"</a>&emsp;&emsp;";
			}
			string=string+"</span><br>";
			string=string+"<span>音频自动播放(日记音频及用户家歌)：</span>";
			var play=data.autoPlay;
			var tips=new Array("弹出提示是否播放","自动播放","不自动播放");
			for(var i=0;i<tips.length;i++){
				if(i==play){
					string=string+"<input type='radio' name='autoplay' onchange='saveInfo(5,"+i+")' value='"+i+"' checked='true'>"+tips[i]+"</input>&emsp;";
				}else{
					string=string+"<input type='radio' name='autoplay' onchange='saveInfo(5,"+i+")' value='"+i+"'>"+tips[i]+"</input>&emsp;";
				}
			}
			string=string+"<br></form>";
			$("#myDiary").append(string);
		}
	});
}
/* $("input[name=autoplay]").click(function(){
    var value = $(this).val();
    saveInfo(5,value);
}); */
/**
 * 14.保存信息
 * 1-昵称，2-个性签名，3-默认地址，4-背景图(结合value参数)
 */
function saveInfo(which,value){
	var t=which+"";
	var url="note/userinfo/updateUserInfo.do?UUserId="+author;
	if(t=="1"){//昵称
		url=url+"&UUserName="+document.info.nickName.value;
	}else if(t=="2"){//个性签名
		url=url+"&signature="+document.info.signature.value;
	}else if(t=="3"){//默认地址
		url=url+"&location="+document.info.location.value;
	}else if(t=="4"){//背景图
		url=url+"&back="+value;
	}else if(t=="5"){//是否自动播放
		url=url+"&autoPlay="+value;
	}
	
	$.ajax({
		url:url,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(res){
			if(res.code==200){
				alert(res.message);
			}else{
				alert("修改失败");
			}
		}
	});
}
/**
 * 15.加载我关注的人
 */
function loadMyAtten(){
	$("#myDiary").text('');
	$(".pages").text('');
	document.getElementById("my").style.color="black";
	document.getElementById("love").style.color="black";
	document.getElementById("store").style.color="black";
	document.getElementById("setting").style.color="black";
	document.getElementById("attention").style.color="red";
	$.ajax({
		url:"note/notice/getMyAtten.do?userId="+user,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(res){
			if(res.code==200){
				var data=res.result;
				if(data.length<1){
					$("#myDiary").append("<center>你还没有关注别人呢，快去关注你喜欢的人吧！</center>");
				}else{
					$("#myDiary").append("<center>你共关注了<font color='red' size='2px'>"+data.length+"</font>个小伙伴</center>");
				}
				for(var i=0;i<data.length;i++){
					$("#myDiary").append("<div class='notice'><a href='author.html?author="+data[i].noticedId+"' target='_blank'>"+data[i].noticedName+"</a><font color='gray' size='2px'><span>"+data[i].noticeTime+"</span></font></div><hr>");
					
				}
			}else{
				alert("查询失败");
			}
		}
	});
}
/**
 * 16.检测访问设备，手机返回true，其他返回false
 * 平台、设备和操作系统
 * @returns {Boolean}
 */
function isPhone(){
	//平台、设备和操作系统
	var system ={win : false,mac : false,xll : false};
	//检测平台
	var p = navigator.platform;
	system.win = p.indexOf("Win") == 0;
	system.mac = p.indexOf("Mac") == 0;
	system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
	//跳转语句，如果是手机访问就自动跳转到wap.baidu.com页面
	if(!system.win && !system.mac && !system.xll){//手机访问
	    return true;
	}
	return false;
}
/**
 * 17.将userId移出不看他列表
 * @param userId
 */
function removeFromList(userId)
{
	//user:当前登录用户,userId:待移除用户
	$.ajax({
		url:"note/userinfo/addToOrRemoveFromList.do?type=1&user="+user+"&userId="+userId,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(res){
			if(res.code==200){
				
			}
		}
	});
}