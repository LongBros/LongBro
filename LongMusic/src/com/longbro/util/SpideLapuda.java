package com.longbro.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬取拉普达某用户的日记
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年12月8日 下午2:40:32
 * @description
 * @version
 */
public class SpideLapuda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		spideDiary();
//		getIds();
	}

	private static void spideDiary() {
		int ids1[]={91908,91858,91808,91807,91657,91615,91512,91511,91385,91313,87168,87152,87049,87001,86961,86889,86788,86688,86649,86562,86516,86476,86416,86349,86245,86147,86119,86118,86116,86115,86114,86113,86112,85605,85604,85603,85602,85601,856000,85599,85598,85597,84382,84262,84205,84147,84145,84144,84143,84142,84141,84140,84139,84139,84137,84136,84135,84133,84132,84131,84130,84129,84128,84127,84126,84125,84124,84123,84122,84120,84119,84118,84117,84116,84114,84113,84112,84111,84110,84109,84108,84107,84106,84105,84104,84103,84102,84101,84100,84099,84098,84097,84096,84095,84094,84093,84092,84091,84090,84089};
		int ids[]={84088,84087,84086,84085,84084,84083,84082,84081,84080,84079,84078,84077,84076,84075,84074,84073,84072,84071,84070,84069,84068,84067,84066,84065,84064,84063,84062,84061,84060,84058,84057,84056,84055,84054,84053,84052,84051,84050,84048,84046,84045,84042,84041,84040,84039,84038,84037,84036,84035,84034,84033,84031,84030,84029,84028,84027,84026,84025,84024,84023,84022,84021,84020,84019,84018,84017,84016,84015,84014,84013,84012,84011,84010,84009,84008,84007,84006,84005,84004,84003,84002,84001,84000,83999,83998,83997,83996,83995,83994,83992,83991,83990,83989,83988,83987,83986,83985,83984,83983,83982,83981,83980,83979,83978,83977,83976,83975,83974,83973,83972,83971,83970,83969,83968,83967,83966,83965,83964,83963,83962,83961,83960,83959,83958,83957,83954,83953,83952,83951,83950,83949,83948,83947,83946,83945,83944,83943,83942,83941,83940,83939,83938,83937,83936,83935,83934,83933,83932,83931,83930,83929,83928,83927,83925,83924,83922,83921,83920};
		Document doc;
		System.out.println(ids.length);//200-500
		try {
			int j=398;
			for(int i=0;i<ids.length;i++){//ids.length
				String url="http://www.lapuda.org/diary/"+ids[i]+"/";
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
				String sql="INSERT INTO `music`.`d_diary` (`n_Id`, `n_Type`, `n_BookId`, `n_Writter`, `n_Title`, `n_Content`, `n_Time`, `n_Weather`, `n_Mood`, `n_Location`, `n_AllowComment`, `n_Authority`, `n_song_id`, `n_top`, `n_user_top`, `n_update_time`) VALUES ('"+j+"', '0', NULL, '17134922', '"+s+"', '"+s2+"', '"+s+"', '0', '0', '北京市', '0', '0', NULL, NULL, NULL, NULL);";
				System.out.println(sql);
				j--;

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getIds(){
		for(int k=11;k<27;k++){
			String path="C:/Users/Administrator/Desktop/banjia/"+k+".txt";
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
