var curPage=1;//当前页码
var perPage=10;//当前一页展示日记数量
var user=getCookie("userId")+"";
/*个人信息*/
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

/*
 * 根据页码加载日记--用于首页和作者页
 * @param 作者	页码
*/
function loadDiary(author,page,perPage){
	var au="0";//完全公开的
	if(user!=null){//登录用户可看到完全公开和登录可见的
		au="0,2";
	}
	$("#diarys").text("");
	$.ajax({
		url:"../../note/diary/getDiaryBy.do",
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
			for(var i=0;i<data.length;i++){
				//处理内容和标题
				var title=data[i].ntitle+"";
				var con=handleCon(data[i].ncontent);
				console.log(con);

				if(con.length>250){
					con=con.substring(0,250)+"......";
				}
				
				if(title.length>10){
					title="《"+title.substring(0,8)+"...》";
				}else{
					title="《"+title+"》";
				}
				if(data[i].nTop==1){
					title=title+"<font color='red'>(置顶)</font>"
				}
				var userName=data[i].userName;
				if(user==data[i].nwritter){//当前登录人的日记特殊显示作者
					userName="<font color='red'>"+data[i].userName+"(朕)</font>";
				}
				//分类
				var cate=getCateById(data[i].ntype);
				//
				//<i class=\"Hui-iconfont\">&#xe66e;</i>22&nbsp;
				$("#diarys").append("<div class=\"diary\"><img src='../../image/tx/"+data[i].headImage+".jpg' class='touxiang'><span onclick='openOther(0,"+data[i].nid+")'>"+con+"</span><br>"
				+"<div class='info'><i class=\"Hui-iconfont\">&#xe60d;</i><span style='cursor:pointer' onclick='openOther(1,\""+data[i].nwritter+"\")'>"+userName
				+"</span>&emsp;<i class=\"Hui-iconfont\">&#xe690;</i>"+data[i].ntime
				+"&emsp;<i class=\"Hui-iconfont\">&#xe681;</i>"+cate+"&nbsp;:<span title='"+data[i].ntitle+"'>"+title+"</span>&emsp;<i class=\"Hui-iconfont\">&#xe6c9;</i>"+data[i].nlocation
				+"<div class='zan'><i class=\"Hui-iconfont\">&#xe725;</i>"+data[i].visitNum+"&nbsp;<i class=\"Hui-iconfont\">&#xe622;</i><span id='commentNum'>"+data[i].commentNum+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe66d;</i><span>"+data[i].praiseNum
				+"</span>&nbsp;<i class=\"Hui-iconfont\">&#xe630;</i><span>"+data[i].storeNum+"</span></div></div>"
				+"</div><hr width='740px'>");
			}
		}
	});
	curPage=parseInt(page);
	perPage=parseInt(perPage);
	setPage(author,perPage);
}
/*根据分类id得到分类名*/
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
	}else if(id=="4"){
		cate="深度好文";
	}
	return cate;
}
/*根据分类id得到分类名*/
function getMoodById(id){
	var mood="0";
	if(id=="0"){
		mood="开心";
	}else if(id=="1"){
		mood="微笑";
	}else if(id=="2"){
		mood="哭脸";
	}else if(id=="3"){
		mood="愤怒";
	}
	return mood;
}
function getWeaById(id){
	var wea="0";
	if(id=="0"){
		wea="晴";
	}else if(id=="1"){
		wea="雾";
	}else if(id=="2"){
		wea="霾";
	}else if(id=="3"){
		wea="阴";
	}else if(id=="4"){
		wea="雨";
	}else if(id=="5"){
		wea="雪";
	}
	return wea;
}
/*处理日记内容*/
function handleCon(content){
	var reg=/<[^<>]+>/g ;
	//var reg1=/<(?!img).*?>/g;//保留img标签
	//var reg2=/<\/?((?!img).)*?\/?>/g;
	//var reg3=/<(?!img|p|/p).*?>/g;//保留img、p标签
	var con=content+"";
	con=con.replace(reg, "");
//	con=con.replace(new RegExp("::::","gm"), ".jpg'>");
//	con=con.replace(new RegExp(":::","gm"), ".png'>");
//	con=con.replace(new RegExp("::","gm"), ".gif'>");
//	con=con.replace(new RegExp("<<<","gm"), "<img alt='' src='../../image/expre/newtieba/");
//	con=con.replace(new RegExp("<<","gm"), "<img alt='' src='../../image/expre/tieba/");
//	con=con.replace(new RegExp("&&&&","gm"), "<img alt='' src='../../image/expre/weibo/");
//	con=con.replace(new RegExp("&&&","gm"), "<img alt='' src='../../image/expre/huang/");
//	con=con.replace(new RegExp("&&","gm"),"<img alt='' src='../../image/expre/aodamiao/");
	return con;
}
/*打开其他的界面：作者、某日记*/
function openOther(type,value){
	if(type==0){
		window.open("diary.html?id="+value, "_blank")
	}else if(type==1){
		window.open("author.html?author="+value, "_blank")
	}
}
/*
 * 根据日记数量初始化页码按钮
 * @param 作者
*/
function setPage(author,perPage){
	$(".pages").text('');
	var num=0;
	$.ajax({
		url:"../../note/diary/getDiaryNumBy.do",
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
	var sel="<select onchange='setPer(options[selectedIndex].value)'>";
	for(var i=0;i<value.length;i++){
		if(value[i]==perPage){
			sel=sel+"<option value='"+value[i]+"' selected>每页"+value[i]+"篇</option>";
		}else{
			sel=sel+"<option value='"+value[i]+"'>每页"+value[i]+"篇</option>";
		}
	}
	sel=sel+"</select>";
	$(".pages").append(sel);
	$(".pages").append("&nbsp;共"+num+"篇日记&nbsp;"+curPage+"/"+page+"&emsp;");
	if(curPage!=1){
		$(".pages").append("<span onclick=\"loadDiary('"+author+"','1','"+perPage+"')\">首</span>&emsp;")
		$(".pages").append("<span onclick=\"loadDiary('"+author+"','"+(curPage-1)+"','"+perPage+"')\">←</span>&emsp;");
	}
	if(page>5){//多于5页，只显示5页
		if(curPage>page-5){//当前页码大于总页码-5，输出后六页
			for(var i=page-4;i<=page;i++){
	              if(i==curPage){
	  				   $(".pages").append("<span onclick=\"loadDiary('"+author+"','"+i+"','"+perPage+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
		          }else{
					   $(".pages").append("<span onclick=\"loadDiary('"+author+"','"+i+"','"+perPage+"')\">"+i+"</span>&emsp;")
	         	  }
	        }
		}else{//当前页码小于总页码-6，输出当前页码后的六页
            for(var i=curPage;i<curPage+5;i++){
                if(i==curPage){
	  				   $(".pages").append("<span onclick=\"loadDiary('"+author+"','"+i+"','"+perPage+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
                }else{
					   $(".pages").append("<span onclick=\"loadDiary('"+author+"','"+i+"','"+perPage+"')\">"+i+"</span>&emsp;")
                }
            }
         }
		
	}else{
		for(var i=1;i<=page;i++){
			if(curPage==i){
				$(".pages").append("<span onclick=\"loadDiary('"+author+"','"+i+"','"+perPage+"')\" style=\"color:white;background:black;\">"+i+"</span>&emsp;")
			}else{
				$(".pages").append("<span onclick=\"loadDiary('"+author+"','"+i+"','"+perPage+"')\">"+i+"</span>&emsp;")
			}
		}
	}
	if(curPage+1<=page){//＜＞
		$(".pages").append("<span onclick=\"loadDiary('"+author+"','"+(curPage+1)+"','"+perPage+"')\">→</span>&emsp;")
		$(".pages").append("<span onclick=\"loadDiary('"+author+"','"+page+"','"+perPage+"')\">尾</span>&emsp;")
	}
}
//2019-10-26	加载当前登录用户有多少未读喜欢、收藏、被关注等消息
function initUnReadMessage(){
	
	$.ajax({
		url:"../../note/praise/getPraiseNum.do?PPraised=",
		type:"get",
		async:false,
		dataType:"text",
		success:function(data){
			document.getElementById("unReadNum").innerText=data;
		}
	});
}

function setPer(pernum){
	perPage=pernum;
	setPage(author,perPage);
	loadDiary(author,curPage,perPage);
}