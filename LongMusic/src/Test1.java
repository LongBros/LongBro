import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 根据循环遍历出海量网易云歌曲的id，提取出海量歌曲id
 * 按位数遍历5-6-7-8-9-10
 * 分析可知，网易云歌曲id最少五位，最多十位
 * @author 赵成龙
 * @website www.longqcloud.cn & www.zy52113.com
 * @date 2019年4月7日 上午11:17:38
 * @description
 * @version
 */
public class Test1 {

	public static void main(String[] args) {
		ArrayList<String> arr=new ArrayList<String>();
		for(int i=66666;i<99999;i++){
			if(spideLyric(i+"")){
				arr.add(i+"");
				System.out.println(i+"是网易云音乐的歌曲id");
			}
//			System.out.println(i+"不是网易云音乐的歌曲id");
		}
		System.out.println(arr);
	}
	/**
	 * 
	 * @desc 
	 * @author zcl
	 * @date 2019年4月7日
	 * @param id
	 * @return	返回是否是网易云歌曲id，是true，不是false
	 */
	public static boolean spideLyric(String id){
		String lyricUrl="http://music.163.com/api/song/lyric?id="+id+"&lv=1&kv=1&tv=-1";
		Document doc=null;
		try {
			doc = Jsoup.connect(lyricUrl).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("请检查你输入的id");
		}
		if(!doc.toString().contains("lyric")){
			return false;
		}
		return true;
	}
}
