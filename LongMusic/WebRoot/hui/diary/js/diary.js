/*1.个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}

/*2.根据日记id加载日记*/
function loadDiary(id){
	$("#diary").text("");
	$.ajax({
		url:"../../note/diary/getDiaryById.do?id="+id,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
				//处理内容和标题
				var con=handleCon(data.ncontent);
				if(con.length>50){
					//con=con.substring(0,50)+"...";
				}
				author=data.nwritter;
				document.getElementById("userId").innerText=author;
				
				var songId=data.nSongId+"";
				var title=data.ntitle+"";
				var title1="";
				if(songId.length<5){
					title1=data.ntitle+"";
				}else{
					var r=confirm("当前日记有对应音频，是否播放");
					if(r==true){
						playAudio(songId);
					}
					title1=data.ntitle+"<span title=\"点击可播放喔\" style=\"cursor:pointer;color:red;\" onclick=\"playAudio('"+songId+"')\">▷</span><img style=\"width: 28px;height: 28px;\" src=\"../../image/picture/hot1.gif\">";
				}
				//加载当前日记作者的关注、粉丝、喜欢、收藏及其他基本信息以显示
				document.title="《"+title+"》~'";
				if(title.length>10){
					title=title.substring(0,8)+"...";
				}
				//分类
				var cate=getCateById(data.ntype);
				$("#diary").append("<h2><center>"+title1+"</center></h2>"
						+"<div class='info'><i class=\"Hui-iconfont\">&#xe60d;</i><span style='cursor:pointer' onclick='openAuthor(\""+data.userName+"\")'>"+data.userName
						+"</span>&emsp;<i class=\"Hui-iconfont\">&#xe690;</i>"+data.ntime
						+"&emsp;<i class=\"Hui-iconfont\">&#xe681;</i>"+cate+"&nbsp;:<span title='"+data.ntitle+"'>《"+title+"》</span>&emsp;<i class=\"Hui-iconfont\">&#xe6c9;</i>"+data.nlocation
						+"<div class='zan'><i class=\"Hui-iconfont\">&#xe725;</i><span id='browseNum'>2000</span>&nbsp;<i class=\"Hui-iconfont\">&#xe622;</i><span id='commentNum'>"
						+data.commentNum+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe66d;</i><span id='praiseNum'>"+data.praiseNum
						+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe66e;</i>18&nbsp;<i class=\"Hui-iconfont\">&#xe630;</i><span id='storeNum'>"+data.storeNum+"</span></div></div>"
						+"<div class='content'>"+con+"</div>"

						);
		}
	});
}
/*3.根据分类id得到分类名*/
function getCateById(id){
	var cate="0";
	if(id=="0"){
		cate="生活日记";
	}else if(id=="1"){
		cate="工作笔记";
	}else if(id=="2"){
		cate="idea";
	}else if(id=="3"){
		cate="诗词(文学)";
	}
	return cate;
}
/*4.处理日记内容*/
function handleCon(content){
	var con="&emsp;&emsp;"+content;
	con=con.replace(new RegExp("::::","gm"), ".jpg'>");
	con=con.replace(new RegExp(":::","gm"), ".png'>");
	con=con.replace(new RegExp("::","gm"), ".gif'>");
	con=con.replace(new RegExp("<<<","gm"), "<img alt='' src='../../image/expre/newtieba/");
	con=con.replace(new RegExp("<<","gm"), "<img alt='' src='../../image/expre/tieba/");
	con=con.replace(new RegExp("&&&&","gm"), "<img alt='' src='../../image/expre/weibo/");
	con=con.replace(new RegExp("&&&","gm"), "<img alt='' src='../../image/expre/huang/");
	con=con.replace(new RegExp("&&","gm"),"<img alt='' src='../../image/expre/aodamiao/");
	return con.replace(new RegExp("<br>","gm"), "<br>&emsp;&emsp;");
}
/*5.打开作者的界面*/
function openAuthor(author){
	alert(author);
}
//6.判断当前登录人是否已点赞、收藏该日记，并对图标做修改
function setIcon(id){
	var user=getCookie("userNote")+"";
	var praise=0;//该篇日记当前登录人是否已点赞
	var store=0;//该篇日记当前登录人是否已收藏
	$.ajax({
		url:"../../note/praise/getPraise.do",
		type:"get",
		async:false,
		dataType:"Json",
		data:{
			PDiary:id,
			PPraiser:user
		},
		success:function(data){
			if(data.pdiary==id){//当前用户已喜欢
				praise=1;
			}
		}
	});
	$.ajax({
		url:"../../note/store/getStore.do",
		type:"get",
		async:false,
		dataType:"Json",
		data:{
			SDiary:id,
			SStorer:user
		},
		success:function(data){
			if(data.sdiary==id){//当前用户已收藏
				store=1;
			}
		}
	});
	
	var pra=document.getElementById("praise");
	var st=document.getElementById("store");
	if(praise==0){//点赞记录表中无登录用户对于当前日记的点赞记录，视为没点赞
		pra.title="点赞";
		pra.innerHTML="&#xe649;";
	}else{
		pra.title="取消点赞";
		pra.innerHTML="&#xe648;";
	}
	if(store==0){
		st.title="收藏";
		st.innerHTML="&#xe69e;";
	}else{
		st.title="取消收藏";
		st.innerHTML="&#xe630;";
	}
}
//7.点赞与取消点赞
function praise(){
	if(!ifLogin()){
		alert("请先登录");
		return;
	}
	var user=getCookie("userNote")+"";
	var pra=document.getElementById("praise");
	var pNum=document.getElementById("praiseNum");
	if(pra.title=="点赞"){
		$.ajax({
			url:"../../note/praise/praiseDiary.do",
			type:"get",
			async:false,
			dataType:"text",
			data:{
				PDiary:id,
				PPraiser:user,
				PPraised:author,
				PPraiseTime:formatW2(new Date()+"")
			},
			success:function(res){
				//alert("点赞成功")
			}
		});
		pra.title="取消点赞";
		pra.innerHTML="&#xe648;";
		pNum.innerText=parseInt(pNum.innerText)+1;
	}else{
		$.ajax({
			url:"../../note/praise/removePraiseDiary.do",
			type:"get",
			async:false,
			dataType:"text",
			data:{
				PDiary:id,
				PPraiser:user
			},
			success:function(res){
				//alert("取消喜欢成功")
			}
		});
		pra.title="点赞";
		pra.innerHTML="&#xe649;";
		pNum.innerText=parseInt(pNum.innerText)-1;
	}
}
//8.收藏与取消收藏
function store(){
	if(!ifLogin()){
		alert("请先登录");
		return;
	}
	var user=getCookie("userNote")+"";
	var st=document.getElementById("store");
	var sNum=document.getElementById("storeNum");
	if(st.title=="收藏"){
		$.ajax({
			url:"../../note/store/storeDiary.do",
			type:"get",
			async:false,
			dataType:"text",
			data:{
				SDiary:id,
				SStorer:user,
				SStored:author,
				SStoreTime:formatW2(new Date()+"")
			},
			success:function(res){
				//alert("收藏成功")
			}
		});
		st.title="取消收藏";
		st.innerHTML="&#xe630;";
		sNum.innerText=parseInt(sNum.innerText)+1;
	}else{
		$.ajax({
			url:"../../note/store/removeStoreDiary.do",
			type:"get",
			async:false,
			dataType:"text",
			data:{
				SDiary:id,
				SStorer:user
			},
			success:function(res){
				//alert("取消收藏成功")
			}
		});
		st.title="收藏";
		st.innerHTML="&#xe69e;";
		sNum.innerText=parseInt(sNum.innerText)-1;

	}
}
//9.2019-10-26加载作者的信息:关注信息，互动计数，基本信息，活跃信息
function loadAuthorInfo(){
	$.ajax({
		url:"../../note/userinfo/getAuthorInfoByUserId.do?UUserId="+author,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			document.title=document.title+data.uuserName+"'的日记~哆啦官网";
			document.getElementById("userId").innerText=author;
			document.getElementById("userNameT").innerText=data.uuserName;
			document.getElementById("userName").innerText=data.uuserName;
			document.getElementById("homeSong").innerText=data.uhomeSong;
			document.getElementById("signature").innerText=data.signature;
			document.getElementById("sex").innerText=data.uuserSex;
			document.getElementById("joinTime").innerText="加入时间："+data.ujoinTime;
		}
	});
}
//10.得到歌曲文章的上下篇
function getBeforeAndNextId(){
	$.ajax({
		url:"../../note/diary/getBeforeAndNextId.do?author="+author+"&id="+id,
		type:"get",
		async:false,
		dataType:"Json",
		success:function(data){
			for(var i=0;i<data.length;i++){//返回的list有0,1,2两种可能
				if(data[i].t=='1'){
					//上一篇的id
					var prev=data[i].id;
					$("#pre").append("<a href='diary.html?id="+prev+"'>上一篇:"+data[i].title+"</a>");
					$("#pre1").append("<a href='diary.html?id="+prev+"'>上一篇:"+data[i].title+"</a>");

				}else if(data[i].t=='2'){
					//下一篇的id
					var nextT=data[i].id;
					$("#next").append("<a href='diary.html?id="+nextT+"'>下一篇:"+data[i].title+"</a>");
					$("#next1").append("<a href='diary.html?id="+nextT+"'>下一篇:"+data[i].title+"</a>");

				}
			}
		}
	});
}
//11.评论
function submit_comment(){
	var con=document.getElementById("content").value+"";//内容
	if(con.length<5){
		alert("评论内容不得低于5字符");
		return;
	}
//	alert(id);//当前被评论日记的id
//	alert(author);//被评论日记的作者
	$.ajax({
		url:"../../note/comment/commentDiary.do",
		type:"get",
		async:false,
		data:{
			CReviewedDiary:id,
			CComment:con,
			CReviewed:author
		},
		success:function(){
			
		}
	});
	loadCom();
}
//12.加载当前文章的评论
function loadCom(){
	$('#comments').text("");
	$.ajax({
		url:"../../note/comment/getComByDiaryId.do?id="+id,
		type:"get",
		async:false,
		success:function(data){
			for(var k=0;k<data.length;k++){
				var con=data[k].ccomment;
				con=con.replace(new RegExp("::::","gm"), ".jpg'>");
				con=con.replace(new RegExp(":::","gm"), ".png'>");
				con=con.replace(new RegExp("::","gm"), ".gif'>");
				con=con.replace(new RegExp("<<<","gm"), "<img alt='' src='../../image/expre/newtieba/");
				con=con.replace(new RegExp("<<","gm"), "<img alt='' src='../../image/expre/tieba/");
				con=con.replace(new RegExp("&&&&","gm"), "<img alt='' src='../../image/expre/weibo/");
				con=con.replace(new RegExp("&&&","gm"), "<img alt='' src='../../image/expre/huang/");
				con=con.replace(new RegExp("&&","gm"),"<img alt='' src='../../image/expre/aodamiao/");
				
				$('#comments').append("<hr>");
				$('#comments').append("&nbsp;"+data[k].creviewTime);
				$('#comments').append("<br>"+con);
			}
			
		}
	});
}
/**
 * 13.有音频时点击title后的播放按钮播放音频
 * @param songId
 */
function playAudio(sid){
	var url="";
	if(sid.substring(sid.length-5)==".html"){
		url="http://link.hhtjim.com/qq/"+sid.substring(0, sid.length-5)+".mp3";
	}else if(sid.substring(sid.length-3)==".kw"){
		url="http://link.hhtjim.com/kw/"+sid.substring(0, sid.length-3)+".mp3";
	}else if(sid.substring(sid.length-4)==".m4a"){
		url=sid;
	}else{
		url="http://music.163.com/song/media/outer/url?id="+sid+".mp3";
	}
	var song=document.getElementById("song");
	song.src=url;
	song.style.display="inline-block";
}
//判断是否已登录
function ifLogin(){
	var userPass=getCookie("userPass")+"";
	if(userPass!=""){//已登录用户，隐藏登录按钮
		return true;
	}
	return false;
}
