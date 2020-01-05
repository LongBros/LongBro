<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我的家园-哆啦网</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
	<meta name="applicable-device" content="pc,mobile">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="我的家园，哆啦网">
	<meta http-equiv="description" content="哆啦网，我的家园。">
	<link rel="shortcut icon" href="image/logo.png" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/index1.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">	
  </head>
  <style>
    table{
    	white-space: normal;
	    line-height: normal;
	    font-weight: normal;
	    font-size: medium;
	    font-style: normal;
    }
  	.logo{
  		width: 200px;
  		font-size: 30px;
  	}
  	.nav{
  		width: 880px;
    	padding-left: 15px;
  	}
  	
  	a {
  		text-decoration:none;
  		color:white;
  		padding-left:20px;
	}
	a:HOVER {
		text-decoration:underline;
		color: blue;
		cursor: pointer;
	}
  </style>
  <body id="body" onload=""	style="background-image:url(image/back/back28.jpg)">
  		<script type="text/javascript" src="js/myjs/index.js" charset="utf-8"></script>
  		<script type="text/javascript" src="js/jquery.js" charset="utf-8"></script>
  		<div id="top" style="background: #FA8723;position:fixed;top:0; left:0px;width:100%;height:60px;">
  			<table style="margin-top: 8px;color: white">
  				<tbody>
  					<tr>
  						<td class="logo">duola.vip<nobr></td>
  						<td class="nav">
	  						<a href="" style="color: blue">发现音乐</a>
			  				<a href="singers.html">歌星singer</a>
			  				<a href="songList.html">歌单广场</a>
			  				<a>排行榜</a>
			  				<a>吐槽墙</a>
			  				<a href="friendsLink.html">友情链接</a>
			  			</td>
		  				<td class="register">
			  				<a class="login">登录</a>
			  				<a>注册</a>
		  				</td>
  					</tr>
  				</tbody>
  			</table>
  		</div>
  		<div class="modal fade" id="loginModal" style="display:none;">
			<div class="modal-dialog modal-sm" style="width:540px;">
				<div class="modal-content" style="border:none;">
					<div class="col-left"></div>
					<div class="col-right">
						<div class="modal-header">
							<button type="button" id="login_close" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
							<h4 class="modal-title" id="loginModalLabel" style="font-size: 18px;">登录</h4>
						</div>
						<div class="modal-body">
							<section class="box-login v5-input-txt" id="box-login">
								<form id="login_form" action="" method="post" autocomplete="off">
									
									<ul>
										<li class="form-group"><input class="form-control" id="id_account_l" maxlength="50" name="account_l" placeholder="请输入邮箱账号/手机号" type="text"></li>
										<li class="form-group"><input class="form-control" id="id_password_l" name="password_l" placeholder="请输入密码" type="password"></li>
									</ul>
								</form>
								<p class="good-tips marginB10"><a id="btnForgetpsw" class="fr">忘记密码？</a>还没有账号？<a href="javascript:;" target="_blank" id="btnRegister">立即注册</a></p>
								<div class="login-box marginB10">
									<button id="login_btn" type="button" class="btn btn-micv5 btn-block globalLogin">登录</button>
									<div id="login-form-tips" class="tips-error bg-danger" style="display: none;">错误提示</div>
								</div>
								<div class="threeLogin"><span>其他方式登录</span><a class="nqq" href="javascript:;"></a><a class="nwx" href="javascript:;"></a><!--<a class="nwb"></a>--></div>
							</section>
						</div>
					</div>
				</div>
			</div>
			
		</div>		
		<script type="text/javascript" src="js/jquery2.2.2.min.js"></script>
		<script type="text/javascript" src="js/modal.js"></script>
		<script type="text/javascript" src="js/script.js"></script>
  		<span id="cback" title="B键--换肤--总有一个你喜欢" onclick="changeBack()">切换背景</span>
  		<img alt="" id="hot" src="image/hot1.gif">
		
		<div class="songs">
			<div id="music">
  		<%
  			String name[]={"余生都是你",
  					
  					//网易云音乐的歌
					"余香-张小九",
					"我们-陈奕迅","栀子花开-何炅","信仰-张信哲","后来-刘若英",
					"无颜女-徐良,小凌","和平分手-徐良","情话-徐良,孙羽幽","犯贱-徐良","红装-徐良","那时雨-徐良","抽离-徐良,刘丹萌","客官不可以-徐良,小凌","七秒钟的记忆-徐良,孙羽幽",
					"不想长大-S.H.E","只对你有感觉-飞轮海 ,田馥甄","情侣装-许嵩","棉花糖-至上励合","最后一次的温柔","秋殇别恋",
					"红昭愿","7538(Me U-Remix)","9420","出卖","不将就","自拍","太坦白","模特","作曲家",
					"老街","李白","我不是没脸的男孩","微情歌","那些学校没有教过的事儿","一千个分手的理由","最简单的声音",
					"致命的甜蜜","毕业后你不是我的","认错","多余的解释","素颜","有何不可","可不可以-张紫豪",
					"不再联系-夏天Alex","我配不上你-夏天Alex","太多-陈冠蒲","为你写诗","起风了","有多少爱可以重来-迪克牛仔",
					"当我孤独时还可以抱你-郑源","丑八怪-薛之谦","认真的雪-薛之谦","刚刚好-薛之谦","其实-薛之谦","绅士-薛之谦","演员","秋天不回来-王强",
					"死了都要爱","Util You","Only Love","Breathless","One Day","Despacito (Remix)",
					"有没有人告诉你-陈楚生","三国杀-汪苏泷","Faded-Alan Walker/Iselin Solheim","三国恋-Tank",
					"2002年的第一场雪-刀郎","往后余生-马良","沙漠骆驼","sugar","what are words","superheroes","逍遥叹",
					"爱的华尔兹","让我为你唱首歌","大鱼","谢谢你的爱","擦肩而过","想你的夜","空空如也",
					"说散就散","Fairy Tale","窗外","夏至未至","追光者","消愁-毛不易","像我这样的人-毛不易"
					,"机器铃 砍菜刀-张卫","倍儿爽-大张伟","童话镇-暗杠",	
  					//QQ音乐的歌
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
  					
  					"心痛2011-小山","多喜欢你-小贱（谭冰尧）","当我唱起这首歌-小贱（谭冰尧）,星弟","说好了不见面-小贱（谭冰尧）",
  					"心痛2009-欢子","得到你的人却得不到你的心-欢子","伤心的时候可以听情歌-欢子","其实很寂寞-欢子",
  					"多情的人总被无情的伤-六哲","如果没有他你还爱我吗-六哲","错错错-六哲/陈娟儿","我好喜欢你-六哲",
  					"相爱分开都是罪-六哲","被伤过的心还可以爱谁-六哲","不要在我寂寞的时候说爱我-郑源","披着羊皮的狼-郑源",
  					"爱情里没有谁对谁错-郑源,T.R.Y","擦肩而过-郑源","一万个理由-郑源","怎么会狠心伤害我-郑源",
  					
  					"打工行-龙军","爱情码头-郑源","包容-郑源","珊瑚海-周杰伦.梁心颐",
  					"发如雪-周杰伦","红尘客栈-周杰伦","听妈妈的话-周杰伦","给我一首歌的时间-周杰伦",
  					"稻香-周杰伦","晴天-周杰伦","说好的幸福呢-周杰伦","告白气球-周杰伦",
  					"等你下课(with 杨瑞代)-周杰伦","一个人的寂寞两个人的错-贺一航","体面-于文文","一个人的北京-海鸣威",
  					"别把疼你的人弄丢了-雨宗林","多幸运-韩安旭","小幸运 (Live)-田馥甄","云烟成雨 (《我是江小白》动画片尾曲)-房东的猫",
  					
  					//酷我音乐的歌
  					"9277-深七"
  					};
  			String author[]={};
  			for(int i=0;i<name.length;i++){// name="logo"
  					String a="";
  					if(i==0){//返回顶部标记
  						a="<a name=\"top\">"+name[i]+"</a>";
  					}else{
  						a="<a>"+name[i]+"</a>";
  					}
  				%>
  				  <p class="song" onclick="play('<%=name[i]%>','<%=i%>')" style="color: black;" id="music<%=i%>">
	  				  <span><%=i+1%>.</span>
	  				  <%=a%>
  				  </p>
  				<%
  			}
  		%>
  				<a href="#top" class="cd-top">Top</a>
	  		</div>
	  		
	  		<div id="alyric">
				暂无播放歌曲
			</div>
  		</div>
		
  		<audio id="audio" style="display:none;" controls="controls"
  		 src="http://music.163.com/song/media/outer/url?id=486814412.mp3">
		</audio>
		<p>
		<span id="lyric"></span>
		<div style="margin-bottom: 70px;">
			<center>
				All Rights Reserved版权所有
				<a href="http://www.zy52113.com/" target="_blank">553影院</a>
				&nbsp;And<a href="http://www.longqcloud.cn/" target="_blank">LongBro博客</a>
				<br>备案号：豫ICP备16023798号-2
			</center>		
		</div>
		
		<div id="bottom" style="background: #009688;position:fixed;bottom:0; left:0px;width:100%;height:60px;">
			<img style="width: 40px;height: 40px" title="左键--上一曲" onclick="preview()" alt="" src="image/play_previous.png">&emsp;
			<img style="width: 40px;height: 40px" title="P键--暂停/播放" id="pause" alt="" onclick="pause()" src="image/play.png">&emsp;
		    <img style="width: 40px;height: 40px" title="右键--下一曲" onclick="next()" alt="" src="image/play_next.png">
			<img id="mode" title="顺序播放--C键切换" style="width:50px;height:60px;" src="image/play_random.png" onclick="change()">
			<progress title="A键---快退10秒,D键---快进10秒;Q键---快退5秒,E键---快进5秒" style="width:566px;height:10px" draggable="false" id="pro" value="0" max="100"></progress>
			<span id="time" class="time" title="已播放/总时长" style="color:white"></span>
			<a onclick="minus()" class="minus" title="下键--音量减">一</a>
			<progress style="width:90px;" draggable="false" id="voice" value="100" max="100"></progress>
			<a onclick="add()" class="add" title="上键--音量加">✚</a>
			<span onclick="showHide()" title="Ctrl+shift+S"  style="color:white" id="sah">显示</span>
			&emsp;
			<span id="lock" onclick="fixBottom()" style="display: inline-block;"><i class="Hui-iconfont" style="font-size: 15px">&#xe605;</i></span><!-- 锁定：&#xe60e; 解锁：&#xe605;-->
			<span id="unlock" onclick="unfixBottom()" style="display:none;"><i class="Hui-iconfont" style="font-size: 15px">&#xe60e;</i></span>
		</div>
		
  </body>
</html>
