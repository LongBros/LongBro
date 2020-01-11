package com.longbro.util;

import java.util.ArrayList;
import java.util.Random;
/**
 * 账单分类、歌曲名及id数组
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年8月3日 下午2:07:45
 * @description
 * @version
 */
public class Strings {
	public static String category[]={"交通","早餐","午餐","晚餐","生活","话费","娱乐","日常","零食","饮料","服装","学习"};
	public static String Incate[]={"刷单","工资","兼职","软件","代缴电费","其他"};

	/**
	 * 使用递归得到歌曲的所有时间点，并作为一个数组链表返回
	 * @desc 
	 * @author zcl
	 * @date 2019年5月3日
	 * @param lrc
	 * @param times
	 * @return
	 */
	public static ArrayList<String> getTime(String lrc,ArrayList<String> times){
		String time=lrc.substring(lrc.indexOf("<")+1, lrc.indexOf(">"));
		times.add(time);
		lrc=lrc.substring(lrc.indexOf(">")+1);
		if(lrc.contains(">")){
			getTime(lrc, times);
		}
		return times;
	}
	public static String name[]={"余生都是你-原版","余生都是你-男声",
				
				"你一定要幸福-简弘亦","童话-光良","成都-赵雷","伤心的人别听慢歌（贯彻快乐) (《诺亚方舟3D》2013电影主题曲,2013可口可乐主题歌曲)-五月天",	
				"求佛 (2017)-誓言","想把我唱给你听-老狼,王婧","痴心绝对-李圣杰","答案-杨坤,郭采洁",
				"走心 (《必胜大丈夫》台剧片尾曲)-贺敬轩","再见只是陌生人-庄心妍","凌晨三点-陈硕子","白狐 (《狐仙》电视剧主题曲,《聊斋2》电视剧片尾曲)-陈瑞",
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
				"等你下课(with 杨瑞代)-周杰伦","一个人的寂寞两个人的错-贺一航","走着走着就散了-庄心妍","体面-于文文",
				"别把疼你的人弄丢了-雨宗林","多幸运-韩安旭","小幸运 (Live)-田馥甄","云烟成雨 (《我是江小白》动画片尾曲)-房东的猫",
				//网易云音乐的歌
				"栀子花开-何炅","信仰-张信哲","后来-刘若英",
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
				//酷我音乐的歌
				"9277-深七"
				};
	
	public static String id[]={
			"http://eng.zy52113.com/music/douyin.mp3","http://eng.zy52113.com/music/love3.mp3",
			
			"002xdDqK3GkHVO.html","001Sslz80qPWXv.html","003TLWoN0gQnP5.html","002KxrDn2GgfeM.html",
			"004Fk5Kq4g6V2U.html","001dsnaq0z9dYk.html","003bVCqX2SRdEW.html","002V8Vde2dKIEx.html",
			"0012qZwz22SKoE.html","000ocSdp4SSl6P.html","003FHIfD1BOxqF.html","003iTLLw3X7eOP.html",
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
			"001J5QJL1pRQYB.html","003uL8IE3o37CL.html","004NIK6u00TtHx.html","000Md1wq0vnwzE.html",
			"003k58qK2XoA2u.html","0008Xbt23AGrr6.html","004WwYrR0Uhdzk.html","001yYM0I30CzdP.html",
			
			"95843","25643328","254574",
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
			
			"40926193.kw"
	};
	/**
	 * 随机生成一个含有wei个数字的数
	 * @desc 
	 * @author zcl
	 * @date 2019年10月1日
	 * @param wei
	 * @return
	 */
	public static int allotNum(int wei){
		Random ran=new Random();
		String num="";
		for(int i=0;i<wei;i++){
			int seven=ran.nextInt(10);
			num=num+seven;
		}
		int number=Integer.parseInt(num);
		if(number>pow(10, wei)){//如果不是0开头，则返回
			return number;
		}else{//否则重新生成
			return allotNum(wei);
		}
	}
	public static int allotEight1(){
		int id;
		Random ran=new Random();
		int eig=ran.nextInt(99999999);
		if(eig>10000000){
			 id=eig;
			 return id;
		}
		else{
			return allotEight1();
		}
	}
	public static void main(String[] args) {
//		for(int i=0;i<10;i++){
//			System.out.println(allotNum(8));
//		}
		System.out.println(genNumber(10,3000));
		
		//计算10的八次方
		
	}
	/**
	 * 计算who的wei次幂
	 * @desc 
	 * @author zcl
	 * @date 2019年10月1日
	 * @param who
	 * @param wei
	 * @return
	 */
	public static int pow(int who,int wei){
		int c=1;
		for(int i=1;i<8;i++){
			c=c*who;
		}
		return c;
	}
	public static String backs[]={"back0.jpg","back1.jpg","back2.jpg","back3.jpg","back4.jpg","back5.jpg","back6.jpg","back7.jpg"
		,"back0.png","back1.png","back2.png","back3.png","back4.png","back5.png","back6.png","back7.png"
		,"back0.gif","back1.gif","back2.gif"};
	/**
	 * @desc 随机生成n个max以内的正整数
	 * @author zcl
	 * @date 2019年12月25日
	 * @param n
	 * @param max
	 * @return
	 */
	public static String genNumber(int n,int max){
		String s="";
		for(int i=0;i<n;i++){
			s=s+new Random().nextInt(max)+",";
		}
		s=s.substring(0,s.length()-1);
		return s;
		
	}
	
}
