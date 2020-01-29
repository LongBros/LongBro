/**
 * 1.设置分页
 * @param author
 * @param perPage
 */
function setPage1(perPage){
	$(".pages").text('');
	var num=0;
	$.ajax({
		url:"note/diary/getDiaryNumBy.do",
		type:"get",
		async:false,
		data:{
			NWritter:author,
			NLocation:"0,1,2"//做权限使用
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
		$(".pages").append("<span onclick=\"loadMyDiary('1','"+perPage+"')\">首</span>&emsp;")
		$(".pages").append("<span onclick=\"loadMyDiary('"+(curPage-1)+"','"+perPage+"')\">《</span>&emsp;");
	}
	if(page>5){//多于5页，只显示5页
		if(curPage>page-5){//当前页码大于总页码-5，输出后六页
			for(var i=page-4;i<=page;i++){
	              if(i==curPage){
	  				   $(".pages").append("<span onclick=\"loadMyDiary('"+i+"','"+perPage+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
		          }else{
					   $(".pages").append("<span onclick=\"loadMyDiary('"+i+"','"+perPage+"')\">"+i+"</span>&emsp;")
	         	  }
	        }
		}else{//当前页码小于总页码-6，输出当前页码后的六页
            for(var i=curPage;i<curPage+5;i++){
                if(i==curPage){
	  				   $(".pages").append("<span onclick=\"loadMyDiary('"+i+"','"+perPage+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
                }else{
					   $(".pages").append("<span onclick=\"loadMyDiary('"+i+"','"+perPage+"')\">"+i+"</span>&emsp;")
                }
            }
         }
		
	}else{//少于5页，显示所有页码
		if(page!=1){//只有一页无需显示页码
			for(var i=1;i<=page;i++){
				if(curPage==i){
					$(".pages").append("<span onclick=\"loadMyDiary('"+i+"','"+perPage+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
				}else{
					$(".pages").append("<span onclick=\"loadMyDiary('"+i+"','"+perPage+"')\">"+i+"</span>&emsp;")
				}
			}
		}
	}
	if(curPage+1<=page){//＜＞
		$(".pages").append("<span onclick=\"loadMyDiary('"+(curPage+1)+"','"+perPage+"')\">》</span>&emsp;")
		$(".pages").append("<span onclick=\"loadMyDiary('"+page+"','"+perPage+"')\">尾</span>&emsp;")
	}
	
}
/**
 * 2.设置每页多少篇
 * @param pernum
 */
function setPer1(pernum){
	perPage=pernum;
	setPage1(perPage);
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
	document.getElementById("my").style.color="black";
	document.getElementById("love").style.color="white";
	document.getElementById("store").style.color="white";
	document.getElementById("setting").style.color="white";
	document.getElementById("attention").style.color="white";
	document.getElementById("fans").style.color="white";
	document.getElementById("comment").style.color="white";
	var au="0,1,2";//0完全公开,1自己可见,2登录可见
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
				var top=data[i].nUserTop;
				if(top){
					top="<span style='color:#c88326' onclick='diaryToTop("+data[i].nid+",\"0\")'>取消置顶</span>";
				}else{
					top="<span style='color:blue' onclick='diaryToTop("+data[i].nid+",\"1\")'>置顶</span>";
				}
				$("#myDiary").append(
						"<div class=\"diary\">&nbsp;<span class='diaryTitle' title='"+data[i].ntitle
						+"' style='color:black;font-size:18px;' onclick='openOther(0,"+data[i].nid+")'>"+title+"</span><br><span onclick='openOther(0,"+data[i].nid+")' style='color:gray'>"+con+"</span><br>"
						+"<div class='info'>"
						+"</span>&emsp;<i class=\"Hui-iconfont\">&#xe690;</i>"+data[i].ntime+"&emsp;"+lock+"&emsp;心情："+mood+"&emsp;&emsp;天气："+wea
						+"&emsp;分类："+cate+"&emsp;"
						+"<div class='zan'><i class=\"Hui-iconfont\">&#xe725;</i>"+data[i].visitNum
						+"&nbsp;<i class=\"Hui-iconfont\">&#xe622;</i><span id='commentNum'>"+data[i].commentNum+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe66d;</i><span>"+data[i].praiseNum
						+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe630;</i><span>"+data[i].storeNum
						+"</span>&nbsp;<span style='color:#c88326' onclick='editDiary("+data[i].nid
						+")'>编辑</span>&nbsp;<span style='color:#c88326' onclick='delDiary("+data[i].nid
						+")'>删除</span>&nbsp;"+top+"</div></div>"
						+"</div><hr width='880px'>");
			}

		}
	});
	curPage=parseInt(page);
	perPage=parseInt(perPage);
	setPage1(perPage);
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
	if(user==""){
		alert("请登录");
		return;
	}
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
		type:"POST",
		async:false,
		dataType:"Json",
		data:{
			NId:id,
			nAuthority:3
		},
		success:function(res){
			alert("日记已删除");
			loadMyDiary(curPage,perPage);
		}
	});
}
/**
 * 9.日记置顶
 */
function diaryToTop(id,which){
	//alert("不好意思喔，开发中…敬请期待。^_^");
	$.ajax({
		url:"note/diary/addOrEditNote.do",
		type:"post",
		async:false,
		dataType:"Json",
		data:{
			NId:id,
			nUserTop:which
		},
		success:function(res){
			if(res.code==200){
				if(which==1){
					alert("置顶成功");
				}else if(which==0){
					alert("已取消置顶");
				}
			}
		}
	});
	loadMyDiary(1,10);
}
/**
 * 10.
 * 12-01打开设置tab
 */
function openSetting(){
	$("#myDiary").text('');
	$(".pages").text('');
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
	var array=new Array("back0.jpg","back1.jpg","back2.jpg","back3.jpg","back4.jpg","back5.jpg","back6.jpg","back7.jpg"
			,"back0.png","back1.png","back2.png","back3.png","back4.png","back5.png","back6.png","back7.png","back8.png","back9.png","back10.png","back11.png"
			,"back0.gif","back1.gif","back2.gif","back3.gif","back4.gif","back5.gif","back6.gif");
//	$("#myDiary").append("<div style='height:100px;'>");
	for(var i=0;i<array.length;i++){
		if(i%5==0){
			$("#myDiary").append("<br>");
		}
		$("#myDiary").append("<img src='res/images/back/"+array[i]+"' title='点击即可设置当前\r背景为朕的背景^_^' class='backDemo' onclick='setBackground(\""+array[i]+"\")'>");
	}
//	$("#myDiary").append("</div>");
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
		success:function(res){
			var data=res.result;
			var sex=data.uuserSex+"";//性别：1男，2女
			var showNum=data.uShowWordnum+"";//是否显示日记字数
			var perpageNum=data.perpageNum+"";//每页展示日记数量
			var homesongName=data.homeSongName+"";
			var homesong=data.uhomeSong+"";
			//不看
			var blackNameIds=data.blackNameList+"";//用户id
			var blackIds=blackNameIds.split(",");
			var blackNames=data.blackNames+"";//用户名
			var blacks=blackNames.split(",");
			//不给看
			var bIds=data.blackList+"";//用户id
			var bIdss=bIds.split(",");
			var bNames=data.blacks+"";//用户名
			var bNamess=bNames.split(",");
			
			var bornTime=data.ubornTime+"";//2020-01-27新增
			var year="",month="",day="";
			if(bornTime.length>5){//有出生日期
				year=bornTime.substring(0,4);
				month=bornTime.substring(5,7);
				day=bornTime.substring(8,10);
			}
			var string="<form name='info'><span>昵称：</span><input name='nickName' value='"+data.uuserName+"'><i class=\"Hui-iconfont\" style='cursor:pointer' onclick='saveInfo(1)' title='点击保存'>&#xe676;</i><br>";
			string=string+"<span>个性签名：</span><input name='signature' value='"+data.signature+"'><i class=\"Hui-iconfont\" style='cursor:pointer' onclick='saveInfo(2)' title='点击保存'>&#xe676;</i><br>";
			string=string+"<span>默认日记地址：</span><input name='location' value='"+data.location+"'><i class=\"Hui-iconfont\" style='cursor:pointer' onclick='saveInfo(3)' title='点击保存'>&#xe676;</i><br>";
			if(day!=""){//有出生日期的不可再设置
				string=string+"<span>出生日期：</span><span>"+bornTime+"</span>&nbsp;<span style='color:gray'>(出生日期设置后不可更改)</span><br>";
			}else{
				string=string+"<span>出生日期：</span><input name='year' value='"+year+"' style='width:45px;'>-<input name='month' value='"+month+"' style='width:35px;'>-<input name='day' value='"+day+"' style='width:35px;'>&nbsp;<i class=\"Hui-iconfont\" style='cursor:pointer' onclick='saveInfo(10)' title='点击保存'>&#xe676;</i>&nbsp;<span style='color:gray'>(请于三个框中分别输入年、月、日并点击保存，谨慎输入！设置后不可更改)</span><br>";
			}
			
			if(sex=="1"){
				string=string+"<span>性别:<input type='radio' onchange='saveInfo(7,1)' name='sex' checked='true'>男</input>";
				string=string+"<input type='radio' onchange='saveInfo(7,0)' name='sex'>女</input></span><br>";
			}else if(sex=="0"){
				string=string+"<span>性别:<input type='radio' onchange='saveInfo(7,1)' name='sex'>男</input>";
				string=string+"<input type='radio' onchange='saveInfo(7,0)' name='sex' checked='true'>女</input></span><br>";
			}else{
				string=string+"<span>性别:<input type='radio' onchange='saveInfo(7,1)' name='sex'>男</input>";
				string=string+"<input type='radio' onchange='saveInfo(7,0)' name='sex'>女</input></span><br>";
			}
			
			if(showNum=="1"){
				string=string+"<span>首页显示日记字数:<input type='radio' onchange='saveInfo(6,1)' name='wordsize' value='' checked='true'>显示</input>";
				string=string+"<input type='radio' onchange='saveInfo(6,0)' name='wordsize' value=''>隐藏</input></span>";
			}else if(showNum=="0"){
				string=string+"<span>首页显示日记字数:<input type='radio' onchange='saveInfo(6,1)' name='wordsize' value=''>显示</input>";
				string=string+"<input type='radio' onchange='saveInfo(6,0)' name='wordsize' value='' checked='true'>隐藏</input></span>";
			}else{
				string=string+"<span>首页显示日记字数:<input type='radio' onchange='saveInfo(6,1)' name='wordsize' value=''>显示</input>";
				string=string+"<input type='radio' onchange='saveInfo(6,0)' name='wordsize' value=''>隐藏</input></span>";
			}
			
//			每页加载日记篇数设置
			var per=new Array("0","10","20","30","40","50");
			string=string+"<br><span>每页加载日记篇数：<select onchange='saveInfo(8,options[selectedIndex].value)'>";
			for(var i in per){
				if(per[i]==perpageNum){
					string=string+"<option value='"+per[i]+"' selected>&emsp;"+per[i]+"&emsp;</option>";
				}else{
					string=string+"<option value='"+per[i]+"'>&emsp;"+per[i]+"&emsp;</option>";
				}
			}
			string=string+"</select>&emsp;(0表示显示下拉列表,可切换每页篇数)</span>&nbsp;<span style='color:gray'>(每页加载篇数越多耗时越长，请谨慎选择)</span>";
			
			string=string+"<br><span>不看名单(你看不到列表中的用户在首页的日记，点击可移出):";
//			for(var i=0;i<blackIds.length;i++){
//				string=string+"<a onclick='removeFromList(\""+blackIds[i]+"\",\""+blacks[i]+"\")' style='color:red'>"+blacks[i]+"</a>&emsp;&emsp;";
//			}
			//另一种for循环
			for(var key in blackIds){
				string=string+"<a onclick='removeFromList(\"1\",\""+blackIds[key]+"\",\""+blacks[key]+"\")' style='color:red'>"+blacks[key]+"</a>&emsp;&emsp;";
			}
			string=string+"</span><br>";
			
			string=string+"<span>不给看名单(列表中的用户在首页看不到你的日记，点击可移出):";
			for(var key in bIdss){
				string=string+"<a onclick='removeFromList(\"0\",\""+bIdss[key]+"\",\""+bNamess[key]+"\")' style='color:red'>"+bNamess[key]+"</a>&emsp;&emsp;";
			}
			string=string+"</span><br>";
			
			string=string+"<span>家歌选择(其他用户访问你的家园时会播放家歌)：";
			string=string+"<input name='homesongName' id='songName' readonly value='"+homesongName+"'>";
			string=string+"<input name='homesong' style='display: none' id='sourceId' readonly value='"+homesong+"'>";
			string=string+"<a onclick='openMusic()' title='点击搜索歌曲'><font color='red'>切换</font></a>&emsp;";
			string=string+"<a id='clearBtn' style='display:none' onClick='clearSong()'>清空所选歌曲</a>&emsp;";
			string=string+"<i class=\"Hui-iconfont\" style='cursor:pointer' onclick='saveInfo(9)' title='点击保存'>&#xe676;</i>";
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
	}else if(t=="6"){//日记列表是否显示日记字数~0：隐藏，1：显示
		url=url+"&uShowWordnum="+value;
	}else if(t=="7"){//用户性别~0：女，1：男
		url=url+"&UUserSex="+value;
	}else if(t=="8"){//每页加载日记篇数
		url=url+"&perpageNum="+value;
	}else if(t=="9"){//家歌
		url=url+"&UHomeSong="+document.info.homesong.value;
	}else if(t=="10"){//出生日期
		var year=document.info.year.value+"";
		var month=document.info.month.value+"";
		var day=document.info.day.value+"";
		if(isNaN(year)||isNaN(month)||isNaN(day)){
			alert("输入错误");
			return;
		}
		if(year.length!=4){
			alert("年输入错误");
			return;
		}
		if(month.length>2||month.length==0||day.length>2||day.length==0){
			alert("月或日输入错误");
			return;
		}
		var c=confirm("确认保存你的生日嘛？保存后不可修改呢。");
		if(c==false){
			return;
		}
		var birth=document.info.year.value+"-"+document.info.month.value+"-"+document.info.day.value;
		url=url+"&UBornTime="+birth;
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
	openSetting();
}
/**
 * 15.加载我关注的人
 */
function loadMyAtten(){
	$("#myDiary").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		return;
	}
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
					$("#myDiary").append("<div class='notice'><img src='image/tx/"+data[i].headImg+".jpg'><a href='author.html?author="+data[i].noticedId+"' target='_blank'>"+data[i].noticedName+"</a>&emsp;<font  color='gray' size='1px'>"+data[i].joinDay+"天共"+data[i].diaryNum+"篇日记</font><font color='gray' size='2px'><span>"+data[i].noticeTime+"</span></font></div><hr>");
					
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
 * @param which 1:不看列表，0：不被看列表
 * @param userId
 * @param userName
 */
function removeFromList(which,userId,userName)
{
	var w="不看";
	if(which==0){
		w="不给看";
	}
	var r=window.confirm("确定从"+w+"列表移出‘"+userName+"’？");
	if(r==false){
		return;
	}
	//user:当前登录用户,userId:待移除用户
	$.ajax({
		url:"note/userinfo/addToOrRemoveFromList.do?type=1&user="+user+"&userId="+userId+"&which="+which,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(res){
			if(res.code==200){
				alert(res.message);
			    openSetting();
			}
		}
	});
}
/**
 * 17.加载当前登录人的评论
 */
function loadMyCom(){
	$("#myDiary").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		return;
	}
	$.ajax({
		url:"note/comment/getMyComment.do?userId="+user,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			var res=data.result;
			if(res.length>0){
				$("#myDiary").append("<div class='notice'><center>共有<font color='red'>"+res.length+"</font>条评论消息</center></div>");
			}
			if(res.length<1){
				$("#myDiary").append("<div class='notice'><center>你还没有评论过别人的日记喔，快去评论别人的日记吧！</center></div>");
			}
			for(var i=0;i<res.length;i++){
				var con=res[i].reviewCon+"";
				if(con.length>15){
					con=con.substring(0,15)+"...";
				}
				$("#myDiary").append("<div class='notice'>你评论了<a href='author.html?author="+res[i].reviewed+"' target='_blank'>"+res[i].viewedName+"</a>&emsp;的日记&emsp;<a href='diary.html?id="+res[i].diaryId+"' target='_blank'>"+res[i].diaryTitle+"</a>&emsp;<font style='color:gray;font-size:5px;'>"+con+"</font><font color='gray' size='2px'><span>"+res[i].reviewTime+"</span></font></div><hr>");
			}
		}
	});
}
/**
 * 18.加载当前登录人的粉丝
 */
function loadMyFans(){
	$("#myDiary").text('');
	$(".pages").text('');
	if(user==""){
		alert("请登录");
		return;
	}
	$.ajax({
		url:"note/notice/getMyMessage.do?userId="+user,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(res){
			if(res.code==200){
				var data=res.result;
				if(data.length<1){
					$("#myDiary").append("<center>还没有人关注你呢，快去找喜欢你的人关注你吧！</center>");
				}else{
					$("#myDiary").append("<center>共<font color='red' size='2px'>"+data.length+"</font>个小伙伴关注了你</center>");
				}
				for(var i=0;i<data.length;i++){
					$("#myDiary").append("<div class='notice'><img src='image/tx/"+data[i].headImg+".jpg'><a href='author.html?author="+data[i].noticerId+"' target='_blank'>"+data[i].noticerName+"</a>&emsp;<font  color='gray' size='1px'>"+data[i].joinDay+"天共"+data[i].diaryNum+"篇日记</font><font color='gray' size='2px'><span>"+data[i].noticeTime+"</span></font></div><hr>");
					
				}
			}else{
				alert("查询失败");
			}
		}
	});
}
function openTab(which){
	//01-27 用以下代码避免在各个方法中写代码来控制tab颜色
	var tabs=new Array("my","love","store","comment","attention","fans","setting");
	for(var i=0;i<7;i++){
		if(i==which){
//			$("#"+tabs[i]).css("background","black");
			$("#"+tabs[i]).css("color","black");
		}else{
//			$("#"+tabs[i]).css("background","white");
			$("#"+tabs[i]).css("color","white");
		}
	}
	if(which==0){
		loadMyDiary('1','10');
	}else if(which==1){
		loadMyLove()
	}else if(which==2){
		loadMyStore()
	}else if(which==3){
		loadMyCom()
	}else if(which==4){
		loadMyAtten()
	}else if(which==5){
		loadMyFans()
	}else if(which==6){
		openSetting()
	}
}