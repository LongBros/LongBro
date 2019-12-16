package com.longbro.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

/**
 * 爬取拉普达某用户的日记
 * 庆兔兔65313340
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年12月8日 下午2:40:32
 * @description
 * @version
 */
public class SpideLapuda {
	
	public static void main(String[] args) {//id=2000,,12-12
		// TODO Auto-generated method stub
//		String ss[]={"5秒钟之前","5分钟之前","今天 10:43:14 星期五","昨天 10:43:14 星期五","本月09日 22:31:28 星期一","上月21日 21:36:46 星期四","今年12月28日 14:25:27 星期五","去年12月28日 14:25:27 星期五"};
//		for(String s:ss){
//			System.out.println(TimeUtil.timeConvert(s));
//			
//		}
		int diaryId=0;
		for(int i=0;i<15;i++){
			diaryId=spideIndex(i);
			if(diaryId!=0){
				break;
			}
		}
		System.out.println(diaryId);
//		getIds();
//		setDate();
	}
	/**
	 * @desc 爬取首页，
	 * @author zcl
	 * @date 2019年12月16日
	 * @return
	 */
	public static Integer spideIndex(int page){
		HashMap<String, String> map=new HashMap<String, String>();
		int diaryId=0;
		Document doc;
		try{
			String url="http://www.lapuda.org/diary/page"+page+"/";
			doc = Jsoup.connect(url).get();
//			System.out.println(doc);
			Elements es3=doc.getElementsByClass("meta");
			for(Element e:es3){		
				System.out.println(page+"---------------------");
				String s=e.toString();
				String a[]=s.split("<a href=");
				String author=a[1].substring(a[1].indexOf(">")+1, a[1].indexOf("</a>"));
				String id=a[a.length-1].substring(8,a[a.length-1].indexOf("title")-3);
				if("kfzwqxzy".equals(author)){
					diaryId=Integer.parseInt(id);
				}
//				System.out.println(author);
//				System.out.println(id);
			}
		}catch(Exception e){
			
		}
		return diaryId;
	}
	/**
	 * 为日记设置日期，适用于每天写日记的账号---庆兔兔
	 * @author LongBro
	 * 2019年12月12日
	 * 下午2:07:17
	 */
	private static void setDate() {
		int months[]={1,2,3,4,5,6,7,8,9,10,11,12};
//		int d1[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
//		int d2[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
//		int d3[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
		int year=2019;
		int month=2;
		int d=28;
		for(int i=1719;i>1600;i--){
			System.out.println("UPDATE `music`.`d_diary` SET  `n_Time`='"+year+"-"+TimeUtil.handleNum(month)+"-"+TimeUtil.handleNum(d)+" 22:22:22' WHERE (`n_Id`='"+i+"');");
			if(d>1){
				d--;
			}else{
				if(month>1){
					month--;
					if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
						d=31;
					else if(month==2)
						d=29;
					else
						d=30;
				}else{
					year--;
					month=12;
				}
				
						
			}
		}
	}
	/**
	 * 根据日记ID爬取日记内容
	 * @author LongBro
	 * 2019年12月09日
	 * 上午9:46:19
	 */
	private static void spideDiary() {
		int ids1[]={91908,91858,91808,91807,91657,91615,91512,91511,91385,91313,87168,87152,87049,87001,86961,86889,86788,86688,86649,86562,86516,86476,86416,86349,86245,86147,86119,86118,86116,86115,86114,86113,86112,85605,85604,85603,85602,85601,856000,85599,85598,85597,84382,84262,84205,84147,84145,84144,84143,84142,84141,84140,84139,84139,84137,84136,84135,84133,84132,84131,84130,84129,84128,84127,84126,84125,84124,84123,84122,84120,84119,84118,84117,84116,84114,84113,84112,84111,84110,84109,84108,84107,84106,84105,84104,84103,84102,84101,84100,84099,84098,84097,84096,84095,84094,84093,84092,84091,84090,84089};
		int ids2[]={84088,84087,84086,84085,84084,84083,84082,84081,84080,84079,84078,84077,84076,84075,84074,84073,84072,84071,84070,84069,84068,84067,84066,84065,84064,84063,84062,84061,84060,84058,84057,84056,84055,84054,84053,84052,84051,84050,84048,84046,84045,84042,84041,84040,84039,84038,84037,84036,84035,84034,84033,84031,84030,84029,84028,84027,84026,84025,84024,84023,84022,84021,84020,84019,84018,84017,84016,84015,84014,84013,84012,84011,84010,84009,84008,84007,84006,84005,84004,84003,84002,84001,84000,83999,83998,83997,83996,83995,83994,83992,83991,83990,83989,83988,83987,83986,83985,83984,83983,83982,83981,83980,83979,83978,83977,83976,83975,83974,83973,83972,83971,83970,83969,83968,83967,83966,83965,83964,83963,83962,83961,83960,83959,83958,83957,83954,83953,83952,83951,83950,83949,83948,83947,83946,83945,83944,83943,83942,83941,83940,83939,83938,83937,83936,83935,83934,83933,83932,83931,83930,83929,83928,83927,83925,83924,83922,83921,83920};
		
		//庆兔兔日记-前10页
		int ids3[]={92182,92115,92029,91967,91902,91854,91796,91728,91655,91584,91505,91424,91353,91281,91208,91133,87169,87109,87045,86996,86927,86845,86787,86687,86615,86561,86510,86445,86380,86305,86228,86146,86075,85994,85917,85847,85786,85699,85641,85553,85496,85432,85289,85201,85121,85051,84987,84911,84825,84773,84705,84623,84541,84445,84361,84269,84185,83890,83819,83722,83622,83476,83376,83290,83159,83078,83018,82951,82895,82689,82611,82540,82472,82404,82327,82266,82198,82117,82049,81976,81907,81844,81787,81703,81618,81529,81460,81430,81352,81288,81204,81136,81065,80998,80967,80845,80778,80694,80595,80516};
		//庆兔兔日记-前10~20页
		int ids4[]={80458,80384,80301,80219,80139,80066,79999,79946,79868,79789,79706,79614,79540,79488,79433,79380,78901,78869,78800,78694,78618,78559,78499,78426,78352,78266,78196,78135,78058,77999,77953,77887,77834,77786,77733,77688,77622,77580,77513,77450,77377,77306,77221,77176,77175,77119,77057,77000,76927,76865,76794,76735,76684,76614,76555,76490,76431,76380,76311,76264,76202,76138,76084,75994,75935,75880,75827,75745,75683,75611,75534,75462,75386,75320,75228,75155,75067,75014,74921,74851,74808,74737,74687,74626,74573,74495,74424,74383,74308,74249,74178,74125,74061,73998,73950,73891,73833,73769,73634};
		//庆兔兔日记-前20~30页
		int ids5[]={73563,73504,73439,73379,73306,73247,73167,73119,73072,73023,72902,72813,72736,72657,72595,72549,72486,72431,72395,72347,72270,72208,72134,72074,72019,71949,71891,71802,71752,71696,71622,71531,71468,71399,71337,71261,71193,71117,71059,70985,70912,70853,70774,70728,70664,70600,70544,70458,70370,70307,70250,70181,70089,70028,69971,69858,69776,69715,69599,69535,69456,69382,69289,69222,69194,69134,69057,69026,68919,68850,68771,68696,68632,68573,68547,68454,68423,68279,68203,68141,68071,68000,67926,67851,67780,67722,67697,67607,67540,67482,67436,67376,67327,67266,67198,67073,67015,66966,66918,66856};
		int ids6[]={66813,66762,66718,66661,66614,66567,66519,66458,66409,66358,66289,66206,66085,65957,65881,65797,65749,65704,65632,65584,65539,65496};
		int ids[]=ids1;
		Document doc;
		System.out.println(ids.length+">>");//200-500
		try {
			int j=1700;
//			int file=100;
//			File f=null;

			for(int i=0;i<30;i++){//ids.length,43~48登录可见
//				System.out.print(ids[i]);
//				if(i%20==0){//每20条换一个文件
//					file++;
//					f=new File("C:/Users/pc/Desktop/庆兔兔/"+file+".txt");
//					f.createNewFile();
//				}
				HashMap<String,String> map=spideLapuda(ids[i]);
//				System.out.println(new Gson().toJson(map));
				/*String url="http://www.lapuda.org/diary/"+ids[i]+"/";
				doc = Jsoup.connect(url).get();
				Elements es=doc.getElementsByClass("post-title");
				Elements es1=doc.getElementsByClass("time");
				Elements es2=doc.getElementsByClass("post-content");
				String s=es.toString();
				String s1=es1.first()+"";
				String s2=es2.toString();
				s=s.substring(s.indexOf("title\">")+8, s.indexOf("</h1>")-1);
				s1=s1.substring(s1.indexOf("time\">")+6, s1.indexOf("</span>"));
				s2=s2.substring(s2.indexOf("<pre>"), s2.indexOf("</div>"));*/
				String sql="INSERT INTO `music`.`d_diary` (`n_Id`, `n_Type`, `n_BookId`, `n_Writter`, `n_Title`, `n_Content`, `n_Time`, `n_Weather`, `n_Mood`, `n_Location`, `n_AllowComment`, `n_Authority`, `n_song_id`, `n_top`, `n_user_top`, `n_update_time`) VALUES ('"+j+"', '0', NULL, '65313340', '"+(map.get("title")==null?ids[i]+"":map.get("title"))+"', '"+map.get("content")+"', '"+TimeUtil.time()+"', '0', '0', '兔子窝', '0', '0', NULL, '0', NULL, NULL);";
//				System.out.println(sql);
//				FileWriter fw=new FileWriter(f);
//				FileReader fr=new FileReader(f);
//				char c[]=new char[65535];
//				fr.read(c);
//				c=c;
//				for(char c1:c){
//					fw.append(c1);
//				}
				j--;

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @author LongBro
	 * 2019年12月13日
	 * 下午1:08:09
	 * @param id
	 * @return
	 */
	public static HashMap<String, String> spideLapuda(int id){
		HashMap<String, String> map=new HashMap<String, String>();
		Document doc;
		try{
			String url="http://www.lapuda.org/diary/"+id+"/";
			doc = Jsoup.connect(url).get();
			Elements es=doc.getElementsByClass("post-title");
			Elements es1=doc.getElementsByClass("time");
			Elements es2=doc.getElementsByClass("post-content");
			String s=es.toString();
			String s1=es1.first()+"";
			String s2=es2.toString();
			s=s.substring(s.indexOf("title\">")+8, s.indexOf("</h1>")-1);
			s1=s1.substring(s1.indexOf("time\">")+6, s1.indexOf("</span>"));
			s2=s2.substring(s2.indexOf("<pre>"), s2.indexOf("</div>"));
			map.put("title", s);
			map.put("time", TimeUtil.timeConvert(s1));
			System.out.println("时间已成功转换为:"+TimeUtil.timeConvert(s1));
			map.put("content", s2);
		}catch(Exception e){
			
		}
		return map;
	}
	/**
	 * 得到日记ID
	 * @author LongBro
	 * 2019年12月13日
	 * 下午12:47:31
	 */
	public static void getIds(){
		for(int k=31;k<34;k++){
			String path="C:/Users/pc/Desktop/庆兔兔/"+k+".txt";
			File file=new File(path);

			try {
				byte b[]=new byte[65535];
				FileInputStream fis=new FileInputStream(file);
				fis.read(b);
				String s=new String(b,"UTF-8");
				String ids[]=s.split("<a href=\"/diary/");
				int j=0;
				for(int i=2;i<ids.length;i=i+2){
//					System.out.println(ids[i].substring(0,5));
//					System.out.println("---------------------------------");
					String idss="";
					idss=","+ids[i].substring(0,5);
					System.out.println(idss);

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
