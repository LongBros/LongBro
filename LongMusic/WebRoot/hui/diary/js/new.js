var user=getCookie("userNote")+"";
//写日记
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
	var content=document.form.content.value+"";
	var allowcomment=document.form.allowcomment;
	var authority=document.form.authority.value;
	var category=document.form.category.value;
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
	$.ajax({
		url:"../../note/diary/addNote.do",
		type:"get",
		async:false,
		dataType:"text",
		data:{
			NWritter:user,
			NType:category,
			NTitle:title,
			NContent:content,
			NWeather:wea,
			NMood:mood,
			NLocation:loc,
			NAllowComment:com,
			nAuthority:authority,
			NTime:formatW2(new Date()+"")
		},
		success:function(res){
			alert("日记已发布")
		}
	});
}
//天气切换函数
function changeWea(which){
	var weas=new Array("sunny","foggy","hazing","overcast","rainy","snowy");
	for(var i=0;i<6;i++){
		var wea=document.getElementById(weas[i]);
		if((i+"")==which){//特殊显示
			wea.style.color="pink";
		}else{
			wea.style.color="black";
		}
	}
	document.form.weather.value=which;
}
//心情切换函数
function changeMood(which){
	var weas=new Array("happy","smile","cry","angry");
	for(var i=0;i<4;i++){
		var wea=document.getElementById(weas[i]);
		if((i+"")==which){//特殊显示
			wea.style.color="pink";
		}else{
			wea.style.color="black";
		}
	}
	document.form.mood.value=which;
}
//加载各种小表情
function oocImage(type){
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
				$('#expre').append("<img alt='' src='../../image/expre/aodamiao/"+imgs[i]+".gif' onclick='appendValue(\"&&"+imgs[i]+"::\")'>");
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
				$('#expre').append("<img alt='' src='../../image/expre/huang/"+imgs[i]+".gif' onclick='appendValue(\"&&&"+imgs[i]+"::\")'>");
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
				$('#expre').append("<img alt='' src='../../image/expre/aru/"+imgs[i]+".png' onclick='appendValue(\"&&&"+imgs[i]+":::\")'>");
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
				$('#expre').append("<img alt='' src='../../image/expre/newtieba/"+imgs[i]+".png' onclick='appendValue(\"<<<"+imgs[i]+":::\")'>");
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
				$('#expre').append("<img alt='' src='../../image/expre/tieba/"+imgs[i]+".png' onclick='appendValue(\"<<"+imgs[i]+":::\")'>");
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
				$('#expre').append("<img alt='' src='../../image/expre/weibo/"+imgs[i]+".png' onclick='appendValue(\"&&&&"+imgs[i]+":::\")'>");
			}
		}
	}
}
/**
 * 选中表情后将表情同步到输入框---
 * 11-07取消前两行采用根据id是为了同时可以在diary.html中使用
 */function appendValue(img){
//	var con=document.form.content.value;
//	document.form.content.value=con+img;
	var con=document.getElementById("content").value;
	document.getElementById("content").value=con+img;
}
