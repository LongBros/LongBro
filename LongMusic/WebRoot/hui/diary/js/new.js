//ue.ready(function() {
//    //设置编辑器的内容
//    //ue.setContent('');
//    //获取html内容，返回: <p>hello</p>
//    var html = ue.getContent();
//    //获取纯文本内容，返回: hello
//    var txt = ue.getContentTxt();
//    
//    var $content=$('#content');
//    $content.val(html);
//});

//1.写日记
function writeNote(){
	if(user==""){
		alert("登录后方可写日记");
		document.getElementById("tip").innerText="请先登录后再写日记！";
		login_popup();
		return;
	}
	var loc=document.form.location.value;
	var wea=document.form.weather.value;
	var mood=document.form.mood.value;
	var title=document.form.title.value+"";
	var content=document.form.content.value+"";//手机端未使用富文本编辑器
	if(content==""){//表示是电脑端
		content = ue.getContent();
	}
	var allowcomment=document.form.allowcomment;
	var authority=document.form.authority.value;
	var category=document.form.category.value;
	var sourceId=document.form.sourceId.value;//12-28添加音乐选择
	if(title.length<1){//标题为空时，使用时间作为标题
		title=formatW2(new Date()+"");
	}
	if(content.length<10){
		alert("日记内容最少10字符喔^-^");
		return;
	}
	var com=1;//不允许评论
	if(allowcomment.checked){
		com=0;//允许评论
	}
	var time="";
	var updateTime="";
	if(id!=null&&id!=""){//编辑日记时，updateTime在java中定义
	}else{
		id=0;
		time=formatW2(new Date()+"");
	}
	var device = navigator.platform;
	$.ajax({
		url:"note/diary/addOrEditNote.do",
		type:"post",
		async:false,
		dataType:"Json",
		data:{
			NId:id,
			NWritter:user,
			NType:category,
			NTitle:title,
			NContent:content,
			NWeather:wea,
			NMood:mood,
			NLocation:loc,
			NAllowComment:com,
			nAuthority:authority,
			NTime:time,
			nWriteDevice:device,
			nSongId:sourceId
		},
		success:function(res){
			alert(res.message)
		}
	});
	if(id!=null&&id!=""){//编辑日记时，updateTime在java中定义
		window.open("diary.html?id="+id,"_self");
	}
}
//2.天气切换函数
function changeWea(which){
	var weas=new Array("sunny","foggy","hazing","overcast","rainy","snowy");
	for(var i=0;i<6;i++){
		if((i+"")==which){//特殊显示
			$("#"+weas[i]+"").css("color","pink");
		}else{
			$("#"+weas[i]+"").css("color","black");
		}
	}
	document.form.weather.value=which;
}
//3.心情切换函数
function changeMood(which){
	var moods=new Array("happy","smile","cry","angry");
	for(var i=0;i<4;i++){
		if((i+"")==which){//特殊显示
			$("#"+moods[i]+"").css("color","pink");
		}else{
			$("#"+moods[i]+"").css("color","black");
		}
	}
	document.form.mood.value=which;
}
/**
 * 4.加载各种小表情
 * @param type 表情类型
 * @param from 0：写日记，1：评论
 */
function oocImage(type,from){
	//::表示gif,:::表示png,::::表示jpg
	//&&表示嗷大喵，&&&表示小黄脸,&&&&表示微博,<<贴吧表情,<<<表示新贴吧
	var expre=document.getElementById("expre").innerHTML+"";
	if(type=='0'){
		document.getElementById("expre").style.width="230px";
		$('#expre').text('');
		if(expre.indexOf("aodamiao") != -1){
		}else{
			var imgs=new Array("baibai","bishi","caidao","cangsang","chanle","chijing","dengyan"
	   				,"dese","deyi","guzhang","haixiu","haode","jingdaile","jingjingkan","keai"
	   				,"kun","lianhong","nidongde","qidai","qinqin","shangxin","shengqi"
	   				,"shuai","sikao","tongxin","touxiao","wabikong","weixiao","wulian"
	   				,"wuyu","xiaoku","xiaozheku","xihuan","yaobai","yihuo","zan"
	   				,"zhayan","zhenjing","zhenjingku","zhuakuang");
			for(var i=0;i<imgs.length;i++){
				$('#expre').append("<img alt='' src='image/expre/aodamiao/"+imgs[i]+".gif' onclick='appendValue(\"&&"+imgs[i]+"::\","+from+")'>");
			}
		}
	}else if(type=='1'){
		document.getElementById("expre").style.width="280px";
		$('#expre').text('');
		if(expre.indexOf("huang") != -1){
		}else{
			var imgs=new Array("wx","pz","se","fd","dy","ll","hx","bz","shui","dk","gg","fn"
		    		   ,"tp","cy","jy","ng","ku","lh","zk","tu","tx","ka","by","am"
		    		   ,"jie","kun","jk","liuh","hanx","db","fend","zm","yw","xu","yun","zm"
		    		   ,"shuai","kl","qiao","zj","ch","kb","gz","qd","huaix","zhh","yhh","hq");
			for(var i=0;i<imgs.length;i++){
				$('#expre').append("<img alt='' src='image/expre/huang/"+imgs[i]+".gif' onclick='appendValue(\"&&&"+imgs[i]+"::\","+from+")'>");
			}
		}
	}else if(type=='2'){
		document.getElementById("expre").style.width="480px";
		$('#expre').text('');
		if(expre.indexOf("aru") != -1){
		}else{
			var imgs=new Array("1","10","100","101","102","103","104","105","106","107","108","109"
		    		   ,"11","110","112","113","114","115","116","117","118","119","12","120"
		    		   ,"121","122","123","124","125","126","127","128","129","13","130","131"
		    		   ,"132","133","134","135","136","137","138","139","14","140","141","142"
		    		   ,"143","144","145","146","147","148","149","15","150","151","152","153"
		    		   ,"154","155","156","157","158","159","16","160","161","162","163","164"
		    		   ,"17","18","19","2","20");
			for(var i=0;i<imgs.length;i++){
				$('#expre').append("<img alt='' src='image/expre/aru/"+imgs[i]+".png' onclick='appendValue(\"&&&"+imgs[i]+":::\","+from+")'>");
			}
		}
	}else if(type=='3'){
		document.getElementById("expre").style.width="260px";
		$('#expre').text('');
		if(expre.indexOf("newtieba") != -1){
		}else{
			var imgs=new Array("a","bishi","bugaoxing","caihong","chabei","damuzhi","dengpao","guai","haha","hahaxiao","han","hehe"
		    		   ,"heixian","hu","huaji","huaxin","jingku","jingya","kaixin","ku","kuanghan","lei","len","liwu"
		    		   ,"meiguai","mianqiang","pen","qian","qianbi","ruo","shengli","shengqi","shuijiao","taikaixin","tu","tushe"
		    		   ,"weiqu","xiaoyan","yi","yinxian","yinyue","yiwen","zhenbang");
			for(var i=0;i<imgs.length;i++){
				$('#expre').append("<img alt='' src='image/expre/newtieba/"+imgs[i]+".png' onclick='appendValue(\"<<<"+imgs[i]+":::\","+from+")'>");
			}
		}
	}else if(type=='5'){
		document.getElementById("expre").style.width="260px";
		$('#expre').text('');
		if(expre.indexOf("/tieba") != -1){
		}else{
			var imgs=new Array("a","bishi","bugaoxing","caihong","chabei","damuzhi","dengpao","guai","haha","hahaxiao","han","hehe"
		    		   ,"heixian","hu","huaji","huaxin","jingku","jingya","kaixin","ku","kuanghan","lei","len","liwu"
		    		   ,"meiguai","mianqiang","pen","qian","qianbi","ruo","shengli","shengqi","shuijiao","taikaixin","tu","tushe"
		    		   ,"weiqu","xiaoyan","yi","yinxian","yinyue","yiwen","zhenbang");
			for(var i=0;i<imgs.length;i++){
				$('#expre').append("<img alt='' src='image/expre/tieba/"+imgs[i]+".png' onclick='appendValue(\"<<"+imgs[i]+":::\","+from+")'>");
			}
		}
	}else if(type=='6'){//微博表情
		document.getElementById("expre").style.width="430px";
		$('#expre').text('');
		if(expre.indexOf("weibo") != -1){
		}else{
			var imgs=new Array("aini","aoteman","baibai","baobao","beishang","bingbujiandan","bishi","bizui","chanzui","chigua","chijing","chongjing"
		    		   ,"dahaqi","dalian","dingding","dog1","dog2","dog3","dog4","dog5","dog6","dog7","dog8","dog9"
		    		   ,"dog10","dog11","dog12","dog13","dog4","dog5","doge","erha","feijie","feizao","ganmao","geili"
		    		   ,"guile","guzhang","haha","haixiu","hehe","heixian","heng","huaxin","hufen","jiong","jiyan","keai",
		    		   "kelian","ku","kun","landelini","lei","meng","miao","nanhaier","nu","numa","nvhaier","qian","qinqin",
		    		   "shayan","shengbing","shenma","shenshou","shiwang","shuai","shuijiao","sikao","taikaixin","tanshou",
		    		   "tianping","touxiao","tu","tuzi","v5","wabishi","weiqu","wu","xi","xiaoerbuyu","xiaoku","xiongmao","xixi",
		    		   "xu","yinxian","yiwen","youhengheng","yun","yunbei","zhi","zhuakuang","zhutou","zuiyou","zuohengheng");
			for(var i=0;i<imgs.length;i++){
				$('#expre').append("<img alt='' src='image/expre/weibo/"+imgs[i]+".png' onclick='appendValue(\"&&&&"+imgs[i]+":::\","+from+")'>");
			}
		}
	}
}
/**
 * 5.选中表情后将表情同步到输入框---
 * 11-07取消前两行采用根据id是为了同时可以在diary.html中使用
 * 12-01添加from参数，是判断新写日记还是新写评论
 * @param img
 * @param from 0：写日记，1：写评论
 */

function appendValue(img,from){
//	var con=document.form.content.value;
//	document.form.content.value=con+img;
	if(from=="0")
		ue.setContent(ue.getContent()+img);
	else if(from=="1"){//评论时赋值评论的表情
		var con=document.getElementById("content").value;
		document.getElementById("content").value=con+img;
	}
}
//6.当使用普通文本框时实时计算字数2019-10-27（已用富文本编辑器，该函数弃用）
 function calcInput(){
 	//var co=document.form.content.value;//吐槽内容
	var co=document.getElementById("content").value;
 	co=co+"";
 	document.getElementById("inputNum").innerHTML="<font color=\"red\">"+co.length+"</font>";
 }
 //7.设置位置2019-11-15
 function setLocation(){
 	var userAddr=decodeURI(decodeURI(getCookie("userAddr")+""));
 	document.form.location.value=userAddr;
 }
 /**
  * 8.编辑根据日记时根据id加载日记，并回显日记以供编辑
  * @param id
  */
 function loadDiaryById(id){
 	$.ajax({
 		url:"note/diary/getDiaryById.do?id="+id,
 		type:"get",
 		async:false,
 		dataType:"Json",
 		success:function(data){
 				//处理内容和标题
 				var con=data.ncontent+"";
 				var title=data.ntitle+"";
 				var loc=data.nlocation+"";

 				//分类
 				var cate=data.ntype;
 				var mood=data.nmood;
 				var wea=data.nweather;

 	 			changeMood(mood);
 	 			changeWea(wea);

 				document.getElementById("location").value=loc;
 				document.getElementById("title").value=title;
 				document.getElementById("mood").value=mood;
 				document.getElementById("category").value=data.ntype;
 				var com=data.nallowComment;
 				if(com=='1'){//不允许评论
 					document.getElementById("allowcomment").checked=false;
 				} 	 			
 				document.getElementById("authority").value=data.nauthority;
 				var sid=data.nSongId+"";
 				if(data.nSongId){//有音乐则回显
 					document.getElementById("sourceId").value=data.nSongId;
 					if(sid.length<30){
 						document.getElementById("songName").value=getSongNameBySId(data.nSongId);
 					}else{
 						document.getElementById("songName").value="非歌曲音频资源";
 					}
 				}
 				
// 				$('#location').text(loc);
// 				$('#title').text(title);
 				if(!isPhone()){//电脑端
 					ue.ready(function() {//必须加上此行
 	 					ue.setContent(con);
 	 				});
 				}else{//手机端
 	 				document.form.content.value=con;
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
 
 
 //以下定义内容导致此文件中所有函数失效
// var browser={
//		versions:function(){
//		  ar u = navigator.userAgent, app = navigator.appVersion;
//		   return {//移动终端浏览器版本信息
//		               　　trident: u.indexOf('Trident') > -1, //IE内核
//		            　　   presto: u.indexOf('Presto') > -1, //opera内核
//		            　 　  webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
//		             　 　gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
//		          　   　mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
//		          　　   ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
//		          　　  android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
//		            　　iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
//		           　　iPad: u.indexOf('iPad') > -1, //是否iPad
//		          　　 webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
//		        };
//		}(),
//		language:(navigator.browserLanguage || navigator.language).toLowerCase()
//}
 /***************************************
 *为什么在appendValue函数中可直接使用ue.setContent，无需ue.ready，而loadDiaryById中必须要
 *先ue.ready？是因为后者是在ajax中异步的？
 *
 *
 ****************************************/