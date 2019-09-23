/******************************************
 * 开做时间：2018-11-5
 * 作者：LongBro
 * 旗下主要作品:www.zy52113.com(553影院)
	         www.longqcloud.cn(LongBro博客)
 *******************************************/
//Ajax异步加载歌词
var xmlHttp=false;
if(window.XMLHttpRequest){
	xmlHttp=new XMLHttpRequest();
}else if(window.ActiveXObject){
	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
}
var id=new Array("http://eng.zy52113.com/music/douyin.mp3","http://eng.zy52113.com/music/love3.mp3",
				
				"487885426",
				"551816010","95843","25643328","254574",
				"174934","174962","26609879","174960","174961","25918133","27890395","174963","26609877",
				"375381","354620","167815","394748","122535","28853662",
				"452986458","486111543","515143305","31134193","31654343","29764564",
				"27731177","27731176","29764562","133998","27678655","149787",
				"25864481","149751","25640407","149778","149789","149763","167888",
				"167873","167827","167876","553755659","175072","28661853","69827","156193",
				"1330348068","77131","26599373","27808044","169185","415792881","27955654",
				"32192436","32507038","163123","5256103",
				"19081573","2006865","19081569","21157332","472361096","25795016","165367",
				"36990266","150361","77437","557584888","486814412","29019227","1210461",
				"28832241","4875306","4872532","4872530","413812448","110184","178176",
				"88926","526464293","523251118","4172882","126443","482999696",
				"483671599","569200213","569213220","28481105","28240119","413829859",
				
				"004295Et37taLD.html","000TxCnP0ec8HP.html","0014yS112MT0AY.html","0009kDV81svES9.html",
				"0042xeq137FRKW.html","004EiYrC4B7uxf.html","000cmjCD3LZCQC.html","002pk70j0rkJOE.html",

				"001cP3t82HI31e.html","004NIK6u00TtHx.html",
				"000ocSdp4SSl6P.html","002xdDqK3GkHVO.html","001Sslz80qPWXv.html","003TLWoN0gQnP5.html",
				"002KxrDn2GgfeM.html","004Fk5Kq4g6V2U.html","001dsnaq0z9dYk.html","003bVCqX2SRdEW.html",
				"002V8Vde2dKIEx.html","0012qZwz22SKoE.html","003FHIfD1BOxqF.html","003iTLLw3X7eOP.html",
				"002I8lz81fs013.html","003agYKX4IcPOI.html","000zUuBN4Z6Zjq.html","002u4gmu3wh1mh.html",
				"004DmvlX3mwkkL.html","002iKSnv1OXzty.html","001UvMQj20a8tA.html","003SLsGR3oflmW.html",
				
				"002kkJSE0IeS1F.html","002SXeJf3ppKmQ.html","002hZ2IM0NdiiX.html","001tak5C33Hf6F.html",		
				"004Xpzat0WcMPz.html","002GsyiY1AtogH.html","000LBcVm0d9raf.html","0031QMjB24NVHu.html",
				"001fkWNw2eaEYT.html","0043OVRE2y9ysn.html","002R7LZY3LnrDw.html","0025kBtP2ip6bM.html",
				"002ePCbF198ZRl.html","002WshFv11u44c.html","003Zl6mC43Y3Yj.html","003AOPLH0K9aWT.html",
				"003vTRXd4Wtsli.html","004Gyue33FERTT.html","002pKRoX4Qbafa.html","001yS0N33yPm1B.html",
				
				"000bzSQE1IZOXJ.html","001e9dH11YeXGp.html","003gab3g2kUMwd.html","003eZ03f3yrLl5.html",
				"002GkZ5918bzeh.html","002tJYL13Lxh8p.html","004Sa0wx3Qgta0.html","002Vquk42isCVn.html",
				"002xpzFk0aUnGA.html","001Nl0W80sBSwJ.html","00299V1h3ndPE1.html","003HN1mm2OJICC.html",
				"000UnflD1SQq6f.html","003aNE463RHQws.html","003mkTmw0vPHlE.html","000X8RgD0FO49o.html",
				"002ejEdb4KTwBw.html","004CU50m2JjBjr.html","004EzHKM2jXY9i.html","004M3yRr3kOfnS.html",
				
				"001OyHbk2MSIi4.html","003zJGba3VVI0M.html","000t7dhP0tQaSi.html","002PpAAE06QO73.html",
				"003ziBRN4WIQuj.html","004IIvBM1S55sG.html","001dxwSX448FAp.html","002mbqv20UPkNT.html",
				"002Ba1Td137VC7.html","003RcyNl3PXViA.html","004Zeemn1ScQMX.html","003vMyw03XyybQ.html",
				"001GCsGH2vhsDY.html","000QQdwq1siwHc.html","0049XyvY2W8K4w.html","00171CUM1DoG3A.html",
				"001t8QJq4MutIo.html","000ogOhu4QDz13.html","003a0aj54RzyXf.html","003MuKtY3dYALN.html",
				"004fM2tk11tisY.html","001hdfMm3ikrxL.html","000E7XC60YyAzx.html","000VHofZ4BkjL5.html",
				"000XyM5Z23ds7t.html","003dIT9t46SLw8.html","000JATzt4MqJqP.html","004PfD8q2uWFZO.html",
				
				"000zQiKM16gZGn.html","004HlEON40PR9g.html","004SdqaN2SasLc.html","002iTkrk2tpZ0o.html",
				"003W3cOz3VU0WK.html","002PPVe038LnTv.html","001n2tYj1ETlAr.html","000DaBzq4OC49B.html",
				"000rV4Ng040NwQ.html","000T2LP908lQ6C.html","000ddTHO1mmXOM.html","000wYRrK4BAeR3.html",
				"0041nyxj4H5QDJ.html","000Tmn2f0Nw4vj.html","0031Hx6M2taxQv.html","001UoKIp0opArh.html",
				"001TDWMK2f5pKw.html","002AFutb4I2v6v.html","004IuFgE0FFSaP.html","000Foied2MchaC.html",
				
				"002QbYjB4C67Ps.html","001FOt7p33gCRq.html","003Pz4dV4fcOjL.html","001K0AjL2huSxx.html",
				"0027oMO61wWi55.html","003xv4w313tZHV.html","002hXDfk0LX9KO.html","004BhQke4adHcf.html",
				"003aAYrm3GE0Ac.html","0039MnYb0qxYhV.html","0042QMDR1VzSsx.html","003OUlho2HcRHC.html",
				"001J5QJL1pRQYB.html","003uL8IE3o37CL.html","000Md1wq0vnwzE.html","000LXZJ0222CcP.html",
				"003k58qK2XoA2u.html","0008Xbt23AGrr6.html","004WwYrR0Uhdzk.html","001yYM0I30CzdP.html",
				
				
				"40926193.kw");
var names=new Array(
		//其他
		"余生都是你-原版","余生都是你-男声",
		//网易云音乐的歌
		"余香-张小九",
		"我们-陈奕迅","栀子花开-何炅","信仰-张信哲","后来-刘若英",
		"无颜女-徐良,小凌","和平分手-徐良",
		"情话-徐良,孙羽幽","犯贱-徐良","红装-徐良","那时雨-徐良","抽离-徐良,刘丹萌",
		"客官不可以-徐良,小凌","七秒钟的记忆-徐良,孙羽幽","不想长大-S.H.E","只对你有感觉-飞轮海 ,田馥甄","情侣装-许嵩",
		"棉花糖-至上励合","最后一次的温柔","秋殇别恋","红昭愿","7538(Me U-Remix)",
		"9420","出卖","不将就","自拍","太坦白",
		"模特","作曲家","老街","李白","我不是没脸的男孩",
		"微情歌","那些学校没有教过的事儿","一千个分手的理由","最简单的声音","致命的甜蜜",
		"毕业后你不是我的","认错","多余的解释","素颜","有何不可",
		"可不可以-张紫豪","不再联系-夏天Alex","我配不上你-夏天Alex","太多-陈冠蒲","为你写诗",
		"起风了","有多少爱可以重来-迪克牛仔","当我孤独时还可以抱你-郑源","丑八怪-薛之谦","认真的雪-薛之谦",
		"刚刚好-薛之谦","其实-薛之谦","绅士-薛之谦","演员","秋天不回来-王强",
		"死了都要爱","Util You","Only Love","Breathless","One Day",
		"Despacito (Remix)","有没有人告诉你-陈楚生","三国杀-汪苏泷","Faded-Alan Walker / Iselin Solheim","三国恋-Tank",
		"2002年的第一场雪-刀郎","往后余生-马良","沙漠骆驼","sugar","what are words",
		"superheroes","逍遥叹","爱的华尔兹","让我为你唱首歌","大鱼",
		"谢谢你的爱","擦肩而过","想你的夜","空空如也","说散就散",
		"Fairy Tale","窗外","夏至未至","追光者","消愁",
		"像我这样的人","机器铃 砍菜刀-张卫","倍儿爽-大张伟","童话镇-暗杠",
		
		//QQ音乐
		"可惜没如果","我们的爱-狮子合唱团","别说我的眼泪你无所谓-东来东往","只是没有如果-张靓颖/王铮亮",
  		"感谢你曾来过-Ayo97/阿涵","类似爱情-萧亚轩","枷锁-何野","天亮以前说再见-何野",

		"以后的以后-庄心妍","走着走着就散了-庄心妍",
		"再见只是陌生人-庄心妍","你一定要幸福-简弘亦","童话-光良","成都-赵雷",
		"伤心的人别听慢歌（贯彻快乐) (《诺亚方舟3D》2013电影主题曲,2013可口可乐主题歌曲)-五月天","求佛 (2017)-誓言","想把我唱给你听-老狼,王婧","痴心绝对-李圣杰",
		"答案-杨坤,郭采洁","走心 (《必胜大丈夫》台剧片尾曲)-贺敬轩","凌晨三点-陈硕子","白狐 (《狐仙》电视剧主题曲,《聊斋2》电视剧片尾曲)-陈瑞",
		"你到底爱谁-阿兰","做你的新娘-童可可","心醉-希亚","一定要爱你-田一龙",
		"忘不掉的伤-姜玉阳","丢了幸福的猪-姜玉阳","我就是最爱你的人-黑龙","最爱的人伤我最深-张惠妹,张雨生",
		
		"慢慢喜欢你-莫文蔚","都要好好的-小沈阳 ,沈春阳","最幸福的人-曾春年","铁血丹心-李玉刚",
		"为了谁-李玉刚","新贵妃醉酒-李玉刚","刚好遇见你-李玉刚","当我想你的时候-汪峰",
		"北京北京 (《北京爱情故事》电视剧片头曲)-汪峰","存在 (《北京青年》电视剧片尾曲)-汪峰","再见青春 (《北京爱情故事》电视剧插曲)-汪峰","像梦一样自由-汪峰",
		"勇敢的心 (《西游记之大圣归来》电影插曲,《北京青年》电视剧插曲)-汪峰","春天里-汪峰","怒放的生命 (《北京青年》电视剧插曲)-汪峰","一起摇摆 (《这！就是街舞》第七期背景音乐)-汪峰",
		"摇啊摇 (DJ版)-舞曲","真的爱你 (《摆渡人》电影插曲)-BEYOND","光辉岁月 (粤语)-BEYOND","海阔天空 (《九五2班》网络电影插曲)-BEYOND",
		
		"月亮惹的祸 (《都是你的错》国语版)-张宇","雨一直下-张宇","没有什么不同-曲婉婷","我的歌声里 (《在线爱》电视剧主题曲)-曲婉婷",
		"领悟 (《夜市人生》电视剧插曲)-辛晓琪","我是不是你最疼爱的人-李代沫","坏女人-王建树","新回心转意-黑龙",
		"回心转意-黑龙","凉凉 (《三生三世十里桃花》电视剧片尾曲)-杨宗纬,张碧晨","国王与乞丐 (Live)-华晨宇","分手在那个秋天-浩瀚",
		"不要再来伤害我-张振宇","情人-刀郎","冲动的惩罚-刀郎","十一年-邱永传",
		"浮夸-陈奕迅","单车-陈奕迅","红玫瑰 (《白玫瑰》国语版)-陈奕迅","好久不见 (《失恋33天》电影插曲《不如不见》国语版)-陈奕迅",
		
		"十年 (《明年今日》国语版《隐婚男女》电影插曲《摆渡人》电影插曲)-陈奕迅","没有你陪伴真的好孤单-梦然","爱情买卖-慕容晓晓","爱情乞丐-牛朝阳",
		"最炫民族风 (《这！就是街舞》第五期背景音乐)-凤凰传奇","月亮之上-凤凰传奇","我和草原有个约定-凤凰传奇","我从草原来-凤凰传奇",
		"自由飞翔-凤凰传奇","郎的诱惑-凤凰传奇","荷塘月色-凤凰传奇","全是爱-凤凰传奇",
		"狼爱上羊-汤潮","愤怒的情人-汤潮","爱大了受伤了-汤潮","有一种爱叫做放手-阿木",
		"怎么忍心放开手 (《真爱天涯》电视剧插曲)-山野（李昊瀚）","残缺的美丽-山野（李昊瀚）","如果爱能早些说出来-山野（李昊瀚）","从相爱到分开-山野（李昊瀚）",
		"甜蜜的伤口 (《夏家三千金》电视剧插曲)-山野（李昊瀚）","我们的无奈 (《小夫妻时代》电视剧主题曲)-山野（李昊瀚）","分开不一定分手-山野（李昊瀚）","伤心太平洋 (《神雕侠侣》电视剧片尾曲,《路边野餐》电影插曲)-任贤齐",
		"别想她-高进","终于等到你 (《咱们结婚吧》电视剧主题曲)-张靓颖","我只在乎你-邓丽君","那些花儿 (《那时花开》电影片尾曲《笔仙2》电影插曲)朴树",
		
		"心痛2011-小山","多喜欢你-小贱（谭冰尧）","当我唱起这首歌-小贱（谭冰尧）&星弟","说好了不见面-小贱（谭冰尧）",
		"心痛2009-欢子","得到你的人却得不到你的心-欢子","伤心的时候可以听情歌-欢子","其实很寂寞-欢子",
		"多情的人总被无情的伤-六哲","如果没有他你还爱我吗-六哲","错错错-六哲/陈娟儿","我好喜欢你-六哲",
		"相爱分开都是罪-六哲","被伤过的心还可以爱谁-六哲","不要在我寂寞的时候说爱我-郑源","披着羊皮的狼-郑源",
		"爱情里没有谁对谁错-郑源,T.R.Y","擦肩而过-郑源","一万个理由-郑源","怎么会狠心伤害我-郑源",
		
		"打工行-龙军","爱情码头-郑源","包容-郑源","珊瑚海-周杰伦.梁心颐",
		"发如雪-周杰伦","红尘客栈-周杰伦","听妈妈的话-周杰伦","给我一首歌的时间-周杰伦",
		"稻香-周杰伦","晴天-周杰伦","说好的幸福呢-周杰伦","告白气球-周杰伦",
		"等你下课(with 杨瑞代)-周杰伦","一个人的寂寞两个人的错-贺一航","体面-于文文","一个人的北京-海鸣威",
		"别把疼你的人弄丢了-雨宗林","多幸运-韩安旭","小幸运 (Live)-田馥甄","云烟成雨 (《我是江小白》动画片尾曲)-房东的猫",
		
		//酷我音乐
		"9277-深七");
var nowplay=0;//当前播放歌曲在数组中的序号
var cname="";//当前播放歌曲歌名
var mode="order";//播放模式---默认为顺序播放模式
/**
 * 改变背景
 */
function changeBack(){
	var i=Math.round(Math.random()*28);//随机生成一个整数
	var body=document.getElementById("body");
	body.background="/image/back/back"+i+".jpg";//16.22.21.(19.20.18G)
}
/**
 * 切换页码
 * @param page
 */
function tabPage(page){
	var from=(page-1)*50;
	var to=parseInt(from+50);
//	alert(to);
//	alert(names.length);
	if(names.length<to){
		to=names.length;
	}
	var m=document.getElementById("music");
	var s="";
	for(i=from;i<to;i++){
		s=s+"<p class=\"song\" onclick=\"play('"+names[i]+"','"+i+"')\" style=\"color: black;\" id=\"music"+i+"\"><span>"+(i+1)+".</span><a>"+names[i]+"</a></p>";
	}
//	alert(s);
	m.innerHTML=s;
}

/**
 * 播放歌曲	1.正在播放的歌名颜色特殊（黑色）显示2.网页title改为正在播放歌曲名
 * @param name 歌曲名
 * @param mid  歌曲在数组中的位置指针
 */
function play(name,mid) {
	var np=parseInt(mid);//当前播放指针转为整型
	var p=document.getElementById("audio");
	nowplay=mid;//将当前播放歌曲编号赋值给全局变量，以供其他函数使用
//	alert(id[mid]);

	cname=name;
	//当前播放歌名白色显示，非当前播放歌名黑色显示
	for(i=0;i<id.length;i++){
		if(i==np){//正在播放的歌名颜色置为白色
			document.getElementById("music"+i+"").style.color="white";
			//document.getElementById("music"+i+"").innerHTML="<marquee>"+name+"</marquee>";
		}else{
			document.getElementById("music"+i+"").style.color="black";
		}
	}
	if((id[np]+"").substring((id[np]+"").length-5)==".html"){//QQ音乐的歌
		var mmm=(id[np]+"").substring(0, (id[np]+"").length-5);//截取.html
		p.setAttribute("src", "http://link.hhtjim.com/qq/"+mmm+".mp3");

	}else if((id[np]+"").substring((id[np]+"").length-3)==".kw"){//酷我音乐的歌
		var mmm=(id[np]+"").substring(0, (id[np]+"").length-3);//截取.kw
		p.setAttribute("src", "http://link.hhtjim.com/kw/"+mmm+".mp3");
	}else if((id[np]+"").substring((id[np]+"").length-4)==".mp3"){//非音乐网站资源
		p.setAttribute("src", id[np]+"");
	}else{//网易音乐的歌
		//alert(id[np]);
//		p.setAttribute("src", "http://music.163.com/song/media/outer/url?id="+id[np]+".mp3");
		p.setAttribute("src", "http://link.hhtjim.com/163/"+id[np]+".mp3");
	}
	pause();//判断，若正在播放，则暂停；反之则播放
	
	var mname=encodeURI(encodeURI(name));//编码中文歌词名
	var url="loadLyric.jsp?name="+mname+"&time=&type=2";
	/*xmlHttp.open("post", url, true);
	xmlHttp.onreadystatechange=function() {
		if(xmlHttp.readyState==4){
			//异步获取的歌词加载到歌词显示区域
			document.getElementById("alyric").innerHTML=xmlHttp.responseText;
		}else{
			//document.getElementById("alyric").innerHTML="正在加载歌词，请稍后...";
		}
	}
	xmlHttp.send();*/
	//利用jQuery加载歌词
	jQuery.ajax({
		type:"post",
		url:url,
		//dataType:"html",
		//data:{name:mname,time:'',type:'2',},
		success:function(data){
			document.getElementById("alyric").innerHTML=data;
	}
	});
	document.title=name+"-正在播放|LongBro音乐";
}
/**
 * 实现下一曲按钮功能------
 * 顺序播放模式时播放下一曲，
 * 随机播放模式时随机生成一个歌曲序号来播放
 * 单曲循环模式时只播放当前歌曲
 */
function next(){
	if(mode=="order"){
		var now=parseInt(nowplay);
		//判断当前播放是否为最后一曲，若是，则播放第一曲，反之，播放下一曲
		if(now==(id.length-1)){
			now=0;
		}else{
			now=now+1;
		}
	}else if(mode=="random"){//随机播放
		//产生一个歌曲数量以内的随机数，作为歌曲索引播放			
		now=Math.round(Math.random()*(id.length-1-0)+0); 
	}else{//单曲循环
		now=parseInt(nowplay);
	}
	play(names[now],now);
}
/**
 * 实现上一曲按钮功能------
 * 顺序播放模式时播放上一首，
 * 随机播放模式时随机生成一个歌曲序号来播放
 * 单曲循环模式时只播放当前歌曲
 */
function preview(){
	if(mode=="order"){//顺序播放
		var now=parseInt(nowplay);//当前播放序号转为整型
		//若当前播放不为第一首，点击上一首将当前播放序号指向歌曲数量-1，反之将当前播放指向当前播放-1
		if(now==0){
			now=id.length-1;
		}else{
			now=now-1;	
		}
	}else if(mode=="random"){//随机播放
		//产生一个歌曲数量以内的随机数，作为歌曲索引播放			
		now=Math.round(Math.random()*(id.length-1-0)+0); 
	}else{
		now=parseInt(nowplay);
	}
	play(names[now],now);
}
/**
 * 暂停与播放的切换功能
 * 若当前在播放点击后暂停；若当前出于暂停状态，则点击后播放
 * 播放与暂停按钮相应改变
 */
function pause() {
	var p=document.getElementById("audio");
	var btn=document.getElementById("pause");
	if(p.paused==false){//原本是播放状态，则置为暂停状态
		p.pause();
		btn.src="/image/pause.png";
	}else{
		p.play();
		btn.src="/image/play.png";
		p.paused=false;
	}
}
/**
 * 快进
 * @param bei
 */
function moveon(bei){
	bei=parseInt(bei);
	var p=document.getElementById("audio");
	p.currentTime=p.currentTime+5*bei;
}
/**
 * 快退
 * @param bei
 */
function back(bei){
	bei=parseInt(bei);
	var p=document.getElementById("audio");
	p.currentTime=p.currentTime-5*bei;
}
/**
 * 切换播放模式
 */
function change(){
	var m=document.getElementById("mode");
	if(mode=="order"){//当前模式为顺序，则切换为随机
		mode="random";
		m.src="/image/play_random.png";//http://longqcloud/Minimusic/
		m.title="随机播放--C键切换";
	}else if(mode=="random"){//当前播放为随机，则切换为单曲
		mode="single";
		m.src="/image/play_single.png";
		m.title="单曲循环--C键切换";
	}else{//当前播放为单曲，则切换为顺序循环
		mode="order";
		m.src="/image/play_order.png";
		m.title="顺序循环--C键切换";
	}
}
/*
 * round 方法
 *作用：返回与给出的数值表达式最接近的整数-即四舍五入。
 *语法：Math.round(number) ，必选项 number 参数是要舍入到最接近整数的值。
 *说明：如果 number 的小数部分大于等于 0.5，返回值是大于 number 的最小整数。
 *否则，round 返回小于等于 number 的最大整数。
 * 如果取一位则乘以 10 之后再除以 10,取两位，三位... 以此类推
 * */
//该函数检测当前歌曲播放完毕后，自动播放下一首
function monitor() {
	var p=document.getElementById("audio");
	var per=document.getElementById("time");
	var pro=document.getElementById("pro");
//	if(p.networkState==3){//2正常，3异常
//		alert("出现了异常~_~");
//	}
	//已播放音频长度/音频总时长（以秒计）=已播放的百分比
	//per.innerText=(Math.round((p.currentTime/p.duration*100)*100)/100)+"%";
    
	//逍遥叹  313.547755总时长应为05:13
	//往后余生195.970612总时长应为03:15
	//三国恋   246.204082  总时长应为04:06
	//沙漠骆驼338.076735 总时长应为05:38
	//per.innerText=p.duration;//音频的长度（以秒计）
    //per.innerText=p.currentTime;//音频当前播放时长
    var time=p.duration+"";
	var ctime=p.currentTime+"";
    //设置音乐播放进度条，和音乐已播放时长和总时长
    per.innerText=getTime(ctime)+"/"+getTime(time);
	pro.value=(p.currentTime/p.duration*100.00);
	
	var mname=encodeURI(encodeURI(cname));//编码中文歌词名
	var url="/loadLyric.jsp?name="+mname+"&time="+getTime(ctime)+"&type=1";
	xmlHttp.open("post", url, true);
	xmlHttp.onreadystatechange=function() {
		//if(xmlHttp.readyState==4){
			//异步获取的歌词加载到歌词显示区域
			document.getElementById("lyric").innerHTML=xmlHttp.responseText;
	//	}else{
			//document.getElementById("lyric").innerHTML="正在加载歌词，请稍后...";
		//}
	}
	xmlHttp.send();
	if(p.ended==true){//若当前音频播放结束，自动播放下一首
		next();
	}
}
/**
 * 音量减，音量进度条随之变化
 */
function minus() {
	var p=document.getElementById("audio");
	var vo=document.getElementById("voice");
	if(vo.value==0){
		alert("使出了洪荒之力，音量已经到静音了，不能再减了");
	}else{
		vo.value=vo.value-10;
		p.volume=p.volume-0.1;
	}
}
/**
 * 音量加，音量进度条随之变化
 */
function add() {
	var p=document.getElementById("audio");
	var vo=document.getElementById("voice");//声音从0-1
	if(vo.value==100){
		alert("哎呀，音量已经到最大了，不能再加了");
	}else{
		vo.value=vo.value+10;
		p.volume=p.volume+0.1;
	}
}
/**
 * 根据秒参数，将其转换为分和秒的组合00:00格式并返回
 * 例若time=195，返回03:15
 * @param time
 * @returns {String}
 */
function getTime(time) {//根据秒换算为分和秒
	time=time.substring(0, time.indexOf(".", 0));
    //var min=Math.round(time/60)+"";//分钟---此方法在秒为31时会直接将分得为1，不可靠
	//整型分钟数加空字符变为字符型方可执行下方的.length方法
	var min=parseInt(time/60)+"";
    var sec=time%60+"";//秒
    //保持格式为00:00
    if(min.length==1){
    	min="0"+min;
    }
    if(sec.length==1){
    	sec="0"+sec;
    }
    return min+":"+sec;
}
/**
 * P键--播放/暂停，左键--上一首，右键--下一首
 * @param event
 */
function keydown(event) {
	var p=document.getElementById("audio");
	var btn=document.getElementById("pause");
	//以下两种均可1.状态转为字符串再和"false"比较，2.直接和false比较
	//var sta=p.paused+"";//状态转为String
	//if(event.keyCode=="80"&&sta=="false"){//80=p
	//	p.pause();
	//}
	if(event.keyCode=="80"&&p.paused==false){//80=p   32空格键
		p.pause();
		btn.src="/image/pause.png";
	}else if(event.keyCode=="80"&&p.paused==true){
		p.play();
		btn.src="/image/play.png";
	}else if(event.keyCode=="37"){//左键
		preview();
	}else if(event.keyCode=="39"){//右键
		next();
	}else if(event.keyCode=="38"){//上键
		add();
	}else if(event.keyCode=="40"){//下键
		minus();
	}else if(event.keyCode=="67"){//C键--切换播放模式
		change();
	}else if(event.keyCode=="66"){//B键--改变页面背景
		changeBack();
	}else if(event.keyCode=="65"){//A键--快退十秒
		back(2);
	}else if(event.keyCode=="68"){//D键--快进十秒
		moveon(2);
	}else if(event.keyCode=="81"){//Q键--快退5秒
		back(1);
	}else if(event.keyCode=="69"){//E键--快进5秒
		moveon(1);
	}
}
//检测当前播放是否结束，若是，则播放下一首
window.setInterval("monitor()", 1000);
document.addEventListener("keydown", keydown);
			
function canvas(){
	var can=document.getElementById("can");
	//alert(can.width);
	var cxt=can.getContext("2d");
	//绘制圆
	//cxt.fillStyle="#FF0000";
	//cxt.beginPath();
	//cxt.arc(70,18,15,0,Math.PI*2,true);
	//cxt.closePath();
	//cxt.fill();
	
	//绘制渐变背景
	//var grd=cxt.createLinearGradient(0,0,175,50);
	//grd.addColorStop(0,"#FF0000");
	//grd.addColorStop(1,"#00FF00");
	//cxt.fillStyle=grd;
	//cxt.fillRect(0,0,175,50);
	
	ctx.fillStyle="#0f0f0f";
	ctx.fill();
	ctx.fillRect(0,0,800,600);
	
	//绘制线
	//cxt.moveTo(10,10);
	//cxt.lineTo(150,50);
	//cxt.lineTo(10,50);
	//cxt.stroke();
}